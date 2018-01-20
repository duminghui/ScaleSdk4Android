package com.hcoor.sdk.bt.le;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import com.hcoor.sdk.HexUtils;
import com.hcoor.sdk.bt.BluetoothConnector;
import com.hcoor.sdk.bt.InnerBTListener;

import java.util.List;
import java.util.UUID;

/**
 * 低耗蓝牙控制类
 * Created by dumh on 14/11/2.
 */
@SuppressLint("NewApi")
public class LeBluetoothConnector implements BluetoothConnector {
    private static final String TAG = "LeBluetoothConnector";
    private static final UUID UUID_SERVICE = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");
    private static final UUID UUID_NOTIFICATION = UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb");
    private static final UUID UUID_WRITE = UUID.fromString("0000ffe3-0000-1000-8000-00805f9b34fb");

    /**
     * 蓝牙重试连接会话时间
     */
    private static final long CONNECT_SESSION_INTERVAL = 10000L;
    private BluetoothAdapter btAdapter;
    private Context context;
    private Handler mHandler;
    private BluetoothGatt bluetoothGatt;
    private BluetoothGattCharacteristic writeGattCharacteristic;
    private BluetoothGattCharacteristic readGattCharacteristic;

    private InnerBTListener innerBTListener;
    private boolean isConnected = false;

    public LeBluetoothConnector(Context context, InnerBTListener innerBTListener) {
        if (Build.VERSION.SDK_INT >= 18) {
            this.btAdapter = ((BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter();
        } else {
            this.btAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        this.context = context;
        this.innerBTListener = innerBTListener;
        mHandler = new Handler();
    }

    private long firstConnectTime = 0;

    @Override
    public void disconnect() {
        bluetoothGatt.disconnect();
    }

    private void _disconnect() {
        isConnected = false;
        innerBTListener.disconnected(bluetoothGatt.getDevice().getName(), bluetoothGatt.getDevice().getAddress());
        bluetoothGatt = null;
        writeGattCharacteristic = null;
        readGattCharacteristic = null;
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public String getConnectedDeviceAddress() {
        if (bluetoothGatt != null) {
            return bluetoothGatt.getDevice().getAddress();
        }
        return null;
    }

    @Override
    public void connect(final String address, boolean secure) {
        //兼容三星的连接，三星一定要在子线程中连接才OK
//        new Thread(() -> {
//            BluetoothDevice device = btAdapter.getRemoteDevice(address);
//            isConnected = false;
//            innerBTListener.connectStart(device.getName(), device.getAddress());
//            bluetoothGatt = device.connectGatt(context, false, gattCallback);
//            toConnection(device.getName(), device.getAddress());
//        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                BluetoothDevice device = btAdapter.getRemoteDevice(address);
                isConnected = false;
                innerBTListener.connectStart(device.getName(), device.getAddress());
                bluetoothGatt = device.connectGatt(context, false, gattCallback);
                toConnection(device.getName(), device.getAddress());
            }
        }).start();
    }

    private void toConnection(String name, String address) {
        boolean isConnect = bluetoothGatt.connect();
        Log.i(TAG, String.format("bluetoothGatt.connect:%s", isConnect));
        if (!isConnect) {
            isConnected = false;
            innerBTListener.connectFailed(name, address);
        }
    }

    private BluetoothGattCallback gattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(final BluetoothGatt gatt, int status, int newState) {
            Log.i(TAG, String.format("onConnectionStateChange:status:%s,newState:%s", status, newState));
            switch (newState) {

                case BluetoothProfile.STATE_CONNECTED:
                    firstConnectTime = System.currentTimeMillis();
                    isConnected = true;
                    innerBTListener.connectSuccess(gatt.getDevice().getName(), gatt.getDevice().getAddress());
                    Log.i(TAG, "Connected to GATT server.");
                    Log.i(TAG, "Attempting to start service discovery:" +
                            gatt.discoverServices());

                    break;
                case BluetoothProfile.STATE_CONNECTING:
                    innerBTListener.connecting(gatt.getDevice().getName(), gatt.getDevice().getAddress());
                    break;
                case BluetoothProfile.STATE_DISCONNECTED:
                    final long connection_time = System.currentTimeMillis() - firstConnectTime;
                    if (readGattCharacteristic != null || writeGattCharacteristic != null) {
                        Log.i(TAG, String.format("断开连接:%s,%s", gatt.getDevice().getName(), gatt.getDevice().getAddress()));
                        _disconnect();
                        return;
                    }
                    if (connection_time > CONNECT_SESSION_INTERVAL) {
                        //用于处理连接后就马上失败的情况，再重试几次
                        Log.i(TAG, String.format("重试连接超时:%s:%s,%s", connection_time, gatt.getDevice().getName(), gatt.getDevice().getAddress()));
                        innerBTListener.connectFailed(gatt.getDevice().getName(), gatt.getDevice().getAddress());
                    } else {
//                        mHandler.post(() -> {
//                            Log.i(TAG, String.format("重试连接:%s,%s,%s", connection_time, gatt.getDevice().getName(), gatt.getDevice().getAddress()));
//                            toConnection(gatt.getDevice().getName(), gatt.getDevice().getAddress());
//                        });
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.i(TAG, String.format("重试连接:%s,%s,%s", connection_time, gatt.getDevice().getName(), gatt.getDevice().getAddress()));
                                toConnection(gatt.getDevice().getName(), gatt.getDevice().getAddress());
                            }
                        });
                    }
                    break;
                case BluetoothProfile.STATE_DISCONNECTING:
                    break;
                default:
            }

        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            Log.i(TAG, String.format("onServicesDiscovered:status:%s", status));
            if (status != BluetoothGatt.GATT_SUCCESS) {
                innerBTListener.connectFailed(gatt.getDevice().getName(), gatt.getDevice().getAddress());
            }
            List<BluetoothGattService> bluetoothGattServices = gatt.getServices();
            for (BluetoothGattService bluetoothGattService : bluetoothGattServices) {
                if (bluetoothGattService.getUuid().equals(UUID_SERVICE)) {
                    Log.i(TAG, String.format("==S:UUID:%s", bluetoothGattService.getUuid()));
                    for (BluetoothGattCharacteristic characteristic : bluetoothGattService.getCharacteristics()) {
                        if (characteristic.getUuid().equals(UUID_NOTIFICATION)) {
                            Log.i(TAG, String.format("==C:UUID:%s", characteristic.getUuid()));
                            int properties = characteristic.getProperties();
                            if (((properties | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) || (properties | BluetoothGattCharacteristic.PROPERTY_INDICATE) > 0) {
                                bluetoothGatt.setCharacteristicNotification(characteristic, true);
                                BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID
                                        .fromString(SampleGattAttributes.CLIENT_CHARACTERISTIC_CONFIG));
                                if (descriptor != null) {
                                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                                    bluetoothGatt.writeDescriptor(descriptor);
                                }
                                readGattCharacteristic = characteristic;

                                Log.i(TAG, String.format("获取读取通道成功:%s", readGattCharacteristic.getUuid()));
                            }
                        } else if (characteristic.getUuid().equals(UUID_WRITE)) {
                            Log.i(TAG, String.format("==C:UUID:%s", characteristic.getUuid()));
                            writeGattCharacteristic = characteristic;
                            Log.i(TAG, String.format("获取写入通道成功:%s", writeGattCharacteristic.getUuid()));
                        }

                    }
                }
            }
            boolean isCanRead = true;
            boolean isCanWrite = true;
            if (readGattCharacteristic == null) {
                Log.i("TAG", String.format("获取读取通道失败"));
                isCanRead = false;
            }
            if (writeGattCharacteristic == null) {
                Log.i("TAG", String.format("获取写入通道失败"));
                isCanWrite = false;
            }
            innerBTListener.readWriteStatus(isCanRead, isCanWrite);
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            innerBTListener.notificationData(characteristic.getUuid().toString(), characteristic.getValue());
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                innerBTListener.writeDataSuccess(characteristic.getUuid().toString(), characteristic.getValue());
            } else if (status == BluetoothGatt.GATT_FAILURE || status == BluetoothGatt.GATT_WRITE_NOT_PERMITTED) {
                innerBTListener.writeDateFailed(characteristic.getUuid().toString(), characteristic.getValue());
            } else {
                Log.i(TAG, String.format("WriteFailed:uuid:%s,status:%s", characteristic.getUuid(), status));
            }
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            innerBTListener.notificationData(characteristic.getUuid().toString(), characteristic.getValue());
        }
    };


    @Override
    public final boolean write(byte[] data) {
        writeGattCharacteristic.setValue(data);
        boolean isWrite = bluetoothGatt.writeCharacteristic(writeGattCharacteristic);
        Log.i(TAG, String.format("write:data[%s],isWrite[%s]", HexUtils.byte2HexLog(data), isWrite));
        if (!isWrite) {
            disconnect();
        }
        return isWrite;
    }
}
