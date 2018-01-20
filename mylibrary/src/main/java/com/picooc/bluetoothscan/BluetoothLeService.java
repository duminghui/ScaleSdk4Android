package com.picooc.bluetoothscan;

import android.annotation.SuppressLint;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.List;
import java.util.UUID;

@SuppressLint({"NewApi"})
public class BluetoothLeService extends Service
{
  public static final String ACTION_DATA_AVAILABLE = "com.picooc.bluetooth.le.ACTION_DATA_AVAILABLE";
  public static final String ACTION_GATT_BT_ERROR = "com.picooc.bluetooth.le.error";
  public static final String ACTION_GATT_CONNECTED = "com.picooc.bluetooth.le.ACTION_GATT_CONNECTED";
  public static final String ACTION_GATT_DISCONNECTED = "com.picooc.bluetooth.le.ACTION_GATT_DISCONNECTED";
  public static final String ACTION_GATT_SERVICES_DISCOVERED = "com.picooc.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED";
  public static final String ACTION_GATT_WRITE_OK = "write_ok";
  public static final String EXTRA_DATA = "com.picooc.bluetooth.le.EXTRA_DATA";
  private static final int STATE_CONNECTED = 2;
  private static final int STATE_CONNECTING = 1;
  private static final int STATE_DISCONNECTED;
  private static final String TAG = BluetoothLeService.class.getSimpleName();
  boolean connectflag = false;
  private final IBinder mBinder = new LocalBinder();
  private BluetoothAdapter mBluetoothAdapter;
  private String mBluetoothDeviceAddress;
  private BluetoothGatt mBluetoothGatt;
  private BluetoothManager mBluetoothManager;
  private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback()
  {
    public void onCharacteristicChanged(BluetoothGatt paramAnonymousBluetoothGatt, BluetoothGattCharacteristic paramAnonymousBluetoothGattCharacteristic)
    {
      BluetoothLeService.this.broadcastUpdate("com.picooc.bluetooth.le.ACTION_DATA_AVAILABLE", paramAnonymousBluetoothGattCharacteristic);
      if (paramAnonymousBluetoothGattCharacteristic.getValue() != null)
        System.out.println(paramAnonymousBluetoothGattCharacteristic.getStringValue(0));
      Log.i("picooc", "--------(特征值变化？)onCharacteristicChanged-----" + paramAnonymousBluetoothGattCharacteristic.getUuid().toString());
    }

    public void onCharacteristicRead(BluetoothGatt paramAnonymousBluetoothGatt, BluetoothGattCharacteristic paramAnonymousBluetoothGattCharacteristic, int paramAnonymousInt)
    {
      if (paramAnonymousInt == 0)
        BluetoothLeService.this.broadcastUpdate("com.picooc.bluetooth.le.ACTION_DATA_AVAILABLE", paramAnonymousBluetoothGattCharacteristic);
    }

    public void onCharacteristicWrite(BluetoothGatt paramAnonymousBluetoothGatt, BluetoothGattCharacteristic paramAnonymousBluetoothGattCharacteristic, int paramAnonymousInt)
    {
      Log.e("picooc", "--------(写成功)write success----- status:" + paramAnonymousInt + "   内容=" + BluetoothUtils.bytesToHexString(paramAnonymousBluetoothGattCharacteristic.getValue(), paramAnonymousBluetoothGattCharacteristic.getValue().length));
    }

    public void onConnectionStateChange(BluetoothGatt paramAnonymousBluetoothGatt, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      System.out.println("=======(状态)status:" + paramAnonymousInt1);
      if (paramAnonymousInt2 == 2)
      {
        Log.i(BluetoothLeService.TAG, "(连接成功)Connected to GATT server.");
        Log.i("bluetoothW", "(尝试去发现服务) mBluetoothGatt = " + BluetoothLeService.this.mBluetoothGatt);
        if (BluetoothLeService.this.mBluetoothGatt != null)
          BluetoothLeService.this.mBluetoothGatt.discoverServices();
      }
      while (paramAnonymousInt2 != 0)
      {
        return;
        BluetoothLeService.this.broadcastUpdate("com.picooc.bluetooth.le.error");
        return;
      }
      Log.i(BluetoothLeService.TAG, "(断开连接了)Disconnected from GATT server.");
      BluetoothLeService.this.broadcastUpdate("com.picooc.bluetooth.le.ACTION_GATT_DISCONNECTED");
    }

    public void onDescriptorWrite(BluetoothGatt paramAnonymousBluetoothGatt, BluetoothGattDescriptor paramAnonymousBluetoothGattDescriptor, int paramAnonymousInt)
    {
      Log.i("picooc", "写描述onDescriptorWrite = " + paramAnonymousInt + ", (描述)descriptor =" + paramAnonymousBluetoothGattDescriptor.getUuid().toString());
      if (paramAnonymousInt == 0)
        BluetoothLeService.this.broadcastUpdate("com.picooc.bluetooth.le.ACTION_GATT_CONNECTED");
    }

    public void onReadRemoteRssi(BluetoothGatt paramAnonymousBluetoothGatt, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      System.out.println("蓝牙信号 = " + paramAnonymousInt1);
    }

    public void onServicesDiscovered(BluetoothGatt paramAnonymousBluetoothGatt, int paramAnonymousInt)
    {
      if (paramAnonymousInt == 0)
      {
        BluetoothLeService.this.broadcastUpdate("com.picooc.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED");
        return;
      }
      Log.w(BluetoothLeService.TAG, "(发现服务)onServicesDiscovered received: " + paramAnonymousInt);
    }
  };

  private void broadcastUpdate(String paramString)
  {
    sendBroadcast(new Intent(paramString));
  }

  private void broadcastUpdate(String paramString, BluetoothGattCharacteristic paramBluetoothGattCharacteristic)
  {
    Intent localIntent = new Intent(paramString);
    byte[] arrayOfByte = paramBluetoothGattCharacteristic.getValue();
    if ((arrayOfByte != null) && (arrayOfByte.length > 0))
    {
      BluetoothUtils.bytesToHexString(arrayOfByte, arrayOfByte.length).toUpperCase();
      localIntent.putExtra("com.picooc.bluetooth.le.EXTRA_DATA", arrayOfByte);
    }
    sendBroadcast(localIntent);
  }

  public void close()
  {
    if (this.mBluetoothGatt == null)
      return;
    this.mBluetoothGatt.close();
    this.mBluetoothGatt = null;
  }

  public boolean connect(String paramString)
  {
    boolean bool = true;
    if ((this.mBluetoothAdapter == null) || (paramString == null))
    {
      Log.w(TAG, "蓝牙适配器没有初始化，或者没有指定蓝牙设备的地址");
      bool = false;
    }
    do
    {
      return bool;
      if ((this.mBluetoothDeviceAddress == null) || (!paramString.equals(this.mBluetoothDeviceAddress)) || (this.mBluetoothGatt == null))
        break;
      Log.d(TAG, "尝试使用一个已经存在的mBluetoothGatt进行连接");
      new Handler(getMainLooper()).post(new Runnable()
      {
        public void run()
        {
          BluetoothLeService.this.connectflag = BluetoothLeService.this.mBluetoothGatt.connect();
          Log.e("bluetoothW", "用现有的mBluetoothGatt进行连接的结果，connectflag=" + BluetoothLeService.this.connectflag);
        }
      });
    }
    while (this.connectflag);
    return false;
    final BluetoothDevice localBluetoothDevice = this.mBluetoothAdapter.getRemoteDevice(paramString);
    if (localBluetoothDevice == null)
    {
      Log.w(TAG, "没有找到设备，无法去建立连接，Device not found.  Unable to connect.");
      return false;
    }
    new Handler(getMainLooper()).post(new Runnable()
    {
      public void run()
      {
        Log.d(BluetoothLeService.TAG, "舱室去创建一个新的连接Trying to create a new connection.");
        BluetoothLeService.this.mBluetoothGatt = localBluetoothDevice.connectGatt(BluetoothLeService.this, false, BluetoothLeService.this.mGattCallback);
        Log.i("bluetoothW", "mBluetoothGatt = " + BluetoothLeService.this.mBluetoothGatt);
      }
    });
    return bool;
  }

  public void disconnect()
  {
    if ((this.mBluetoothAdapter == null) || (this.mBluetoothGatt == null))
    {
      Log.w(TAG, "BluetoothAdapter not initialized");
      return;
    }
    new Handler(getMainLooper()).post(new Runnable()
    {
      public void run()
      {
        BluetoothLeService.this.mBluetoothGatt.disconnect();
      }
    });
  }

  public boolean getRssiVal()
  {
    if (this.mBluetoothGatt == null)
      return false;
    return this.mBluetoothGatt.readRemoteRssi();
  }

  public List<BluetoothGattService> getSupportedGattServices()
  {
    if (this.mBluetoothGatt == null)
      return null;
    return this.mBluetoothGatt.getServices();
  }

  public boolean initialize()
  {
    if (this.mBluetoothManager == null)
    {
      this.mBluetoothManager = ((BluetoothManager)getSystemService("bluetooth"));
      if (this.mBluetoothManager == null)
      {
        Log.e(TAG, "Unable to initialize BluetoothManager.");
        return false;
      }
    }
    this.mBluetoothAdapter = this.mBluetoothManager.getAdapter();
    if (this.mBluetoothAdapter == null)
    {
      Log.e(TAG, "Unable to obtain a BluetoothAdapter.");
      return false;
    }
    return true;
  }

  public IBinder onBind(Intent paramIntent)
  {
    return this.mBinder;
  }

  public void onDestroy()
  {
    super.onDestroy();
    Log.e(TAG, "停止服务！");
  }

  public boolean onUnbind(Intent paramIntent)
  {
    close();
    return super.onUnbind(paramIntent);
  }

  public void readCharacteristic(BluetoothGattCharacteristic paramBluetoothGattCharacteristic)
  {
    if ((this.mBluetoothAdapter == null) || (this.mBluetoothGatt == null))
    {
      Log.w(TAG, "BluetoothAdapter not initialized");
      return;
    }
    this.mBluetoothGatt.readCharacteristic(paramBluetoothGattCharacteristic);
  }

  public void setCharacteristicNotification(BluetoothGattCharacteristic paramBluetoothGattCharacteristic, boolean paramBoolean, int paramInt)
  {
    if ((this.mBluetoothAdapter == null) || (this.mBluetoothGatt == null))
      Log.w(TAG, "BluetoothAdapter not initialized");
    BluetoothGattDescriptor localBluetoothGattDescriptor;
    do
    {
      return;
      this.mBluetoothGatt.setCharacteristicNotification(paramBluetoothGattCharacteristic, paramBoolean);
      localBluetoothGattDescriptor = paramBluetoothGattCharacteristic.getDescriptor(UUID.fromString(SampleGattAttributes.CLIENT_CHARACTERISTIC_CONFIG));
    }
    while (localBluetoothGattDescriptor == null);
    if (paramInt == 2)
      localBluetoothGattDescriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
    while (true)
    {
      this.mBluetoothGatt.writeDescriptor(localBluetoothGattDescriptor);
      return;
      localBluetoothGattDescriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
    }
  }

  public void wirteCharacteristic(BluetoothGattCharacteristic paramBluetoothGattCharacteristic)
  {
    if ((this.mBluetoothAdapter == null) || (this.mBluetoothGatt == null))
    {
      Log.w(TAG, "BluetoothAdapter not initialized");
      return;
    }
    this.mBluetoothGatt.writeCharacteristic(paramBluetoothGattCharacteristic);
  }

  public class LocalBinder extends Binder
  {
    public LocalBinder()
    {
    }

    BluetoothLeService getService()
    {
      return BluetoothLeService.this;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BluetoothLeService
 * JD-Core Version:    0.6.2
 */
