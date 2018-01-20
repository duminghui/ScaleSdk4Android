//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//                   _ooOoo_
//                  o8888888o
//                  88" . "88
//                  (| -_- |)
//                  O\  =  /O
//               ____/`---'\____
//             .'  \\|     |//  `.
//            /  \\|||  卍  |||//  \
//           /  _||||| -:- |||||-  \
//           |   | \\\  -  /// |   |
//           | \_|  ''\---/''  |   |
//          \  .-\__  `-`  ___/-. /
//         ___`. .'  /--.--\  `. . __
//      ."" '<  `.___\_<|>_/___.'  >'"".
//     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//      \  \ `-.   \_ __\ /__ _/   .-` /  /
//======`-.____`-.___\_____/___.-`____.-'======
//                   `=---='
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//              佛祖保佑    永无BUG
package com.hcoor.sdk;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.hcoor.sdk.bt.BluetoothConnector;
import com.hcoor.sdk.bt.BluetoothScanService;
import com.hcoor.sdk.bt.IDeviceFilter;
import com.hcoor.sdk.bt.InnerBTListener;
import com.hcoor.sdk.bt.InnerScanListener;
import com.hcoor.sdk.bt.le.LeBluetoothConnector;
import com.hcoor.sdk.oldcmd.CmdA1;
import com.hcoor.sdk.oldcmd.CmdA5;
import com.hcoor.sdk.oldcmd.CmdB0;
import com.hcoor.sdk.oldcmd.CmdB101;
import com.hcoor.sdk.oldcmd.CmdB102;

/**
 * 新的蓝牙SDK
 * Created by dumh on 14/12/13.
 */
public class BluetoothSDKOld {
    private static final String TAG = "BluetoothSDKOld";
    private BluetoothConnector connector;
    private Context context;
    private BTStatusListener btStatusListener;
    private IDataListener dataListener;
    private InnerBTListener listener = new InnerBTListenerImpl();
    private Handler handler = new Handler(Looper.myLooper());

    private BodyInfo bodyinfo = new BodyInfo();
    private BodyInfo[] bodyInfos;
    private UserInfo[] userInfos = new UserInfo[8];

    private String hard_name = "hcoor hs 01";

    /**
     * 扫描持续时间 10秒
     */
    private static final int SCAN_DURATION = 30000;

    public BluetoothSDKOld(Context context, BTStatusListener btStatusListener, IDataListener dataListener) {
        this.context = context;
        this.btStatusListener = btStatusListener;
        this.dataListener = dataListener;
    }

    public void startScan() {
        BluetoothScanService scanService = BluetoothScanService.getInstance();
        scanService.setContextAndListeners(context, new IDeviceFilter() {
            @Override
            public boolean filter(BluetoothDevice device) {
                return device != null && device.getName() != null && device.getName().equals(hard_name);
            }
        }, new InnerScanListener() {
            @Override
            public void scanStart() {
                Log.i(TAG, "扫描开始");
                btStatusListener.onScanStart();
            }

            @Override
            public void scanDevice(BluetoothDevice device) {

            }

            @Override
            public void scanFinished(BluetoothDevice device) {
                btStatusListener.onScanEnd(device != null);
                Log.i(TAG, String.format("扫描结束：是否发现设备：%s", device != null));
                if (device != null) {
                    connector = new LeBluetoothConnector(context, listener);
                    connector.connect(device.getAddress(), true);
                }

            }
        });
//        scanService.setContextAndListeners(context, device -> device != null && device.getName() != null && device.getName().equals(hard_name), new InnerScanListener() {
//            @Override
//            public void scanStart() {
//                Log.i(TAG, "扫描开始");
//                btStatusListener.onScanStart();
//            }
//
//            @Override
//            public void scanDevice(BluetoothDevice device) {
//
//            }
//
//            @Override
//            public void scanFinished(BluetoothDevice device) {
//                btStatusListener.onScanEnd(device != null);
//                Log.i(TAG, String.format("扫描结束：是否发现设备：%s", device != null));
//                if (device != null) {
//                    connector = new LeBluetoothConnector(context, listener);
//                    connector.connect(device.getAddress(), true);
//                }
//
//            }
//        });
        scanService.startScan(SCAN_DURATION);
    }

    private class InnerBTListenerImpl implements InnerBTListener {

        @Override
        public void connecting(String name, String mac) {
            Log.i(TAG, String.format("正在连接:%s,%s", name, mac));
//            handler.post(btStatusListener::onConnecting);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    btStatusListener.onConnecting();
                }
            });
        }

        @Override
        public void connectStart(String name, String mac) {
            Log.i(TAG, String.format("开始连接:%s,%s", name, mac));
//            handler.post(btStatusListener::onConnectStart);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    btStatusListener.onConnectStart();
                }
            });
        }

        @Override
        public void connectSuccess(String name, String mac) {
            Log.i(TAG, String.format("连接成功:%s,%s", name, mac));
//            handler.post(btStatusListener::onConnectSuccess);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    btStatusListener.onConnectSuccess();
                }
            });
        }

        @Override
        public void connectFailed(String name, String mac) {
            Log.i(TAG, String.format("连接失败:%s,%s", name, mac));
//            handler.post(btStatusListener::onConnectFailed);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    btStatusListener.onConnectFailed();
                }
            });
        }

        @Override
        public void disconnected(String name, String mac) {
            Log.i(TAG, String.format("断开连接:%s,%s", name, mac));
//            handler.post(btStatusListener::onDisconnected);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    btStatusListener.onDisconnected();
                }
            });
        }

        @Override
        public void readWriteStatus(final boolean isCanRead, final boolean isCanWrite) {
            Log.i(TAG, String.format("是否可读取：%s,是否可写入：%s", isCanRead, isCanWrite));
//            handler.post(() -> btStatusListener.onReadWriteStatus(isCanRead, isCanWrite));
            handler.post(new Runnable() {
                @Override
                public void run() {
                    btStatusListener.onReadWriteStatus(isCanRead,isCanWrite);
                }
            });
        }

        @Override
        public void notificationData(String uuid, byte[] values) {
            Log.i(TAG, String.format("接收到数据:%s,%s", uuid, HexUtils.byte2HexLog(values)));
            try {
                processData(values);
            } catch (BTNotReadyException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void writeDataSuccess(String uuid, byte[] values) {
            Log.i(TAG, String.format("写数据成功:%s,%s", uuid, HexUtils.byte2HexLog(values)));
        }

        @Override
        public void writeDateFailed(String uuid, byte[] values) {
            Log.i(TAG, String.format("写数据失败:%s,%s", uuid, HexUtils.byte2HexLog(values)));
        }
    }

    private void processData(byte[] values) throws BTNotReadyException {
        if (values == null || values.length == 0) {
            Log.i(TAG, "接受到的数据为空");
            return;
        }
        if (values[3] == (byte) 0xA1) {
            //回写时间
            CmdA1 cmdA1 = new CmdA1();
            write(cmdA1.a2s());
            Log.i(TAG, "回复时间");
        } else if (values[3] == (byte) 0xB0) {
            CmdB0 cmdB0 = new CmdB0();
            final float weight = cmdB0.s2a(values, 0f);
//            handler.post(() -> dataListener.onNotificationWeight(0, weight));
            handler.post(new Runnable() {
                @Override
                public void run() {
                    dataListener.onNotificationWeight(0,weight);
                }
            });
            Log.i(TAG, String.format("weight:%s", weight));
            write(cmdB0.a2s());
        } else if (values[3] == (byte) 0xA5) {
            CmdA5 a5cmd = new CmdA5();
            Log.i(TAG, String.format("A5Cmd:%s", a5cmd.s2a(values, 0)));
        } else if (values[3] == (byte) 0xB1) {
            if (values[5] == (byte) 0x01) {
                bodyinfo.emtpy();
                CmdB101 cmdB101 = new CmdB101();
                cmdB101.s2a(values, bodyinfo);
                write(cmdB101.a2s());
            } else if (values[5] == (byte) 0x02) {
                CmdB102 cmdB102 = new CmdB102();
                cmdB102.s2a(values, bodyinfo);
                write(cmdB102.a2s());
//                handler.post(() -> dataListener.onNotificationBodyInfo(bodyinfo));
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        dataListener.onNotificationBodyInfo(bodyinfo);
                    }
                });
            }
        }
    }

    private boolean write(byte[] values) throws BTNotReadyException {
        boolean writeSuccess = connector.write(values);
        if (!writeSuccess) {
            throw new BTNotReadyException();
        }
        return writeSuccess;
    }

    /**
     * 称量用户，蓝牙连接成功之后再调用
     *
     * @param height 身高 CM
     * @param age    年龄 大于18
     * @param sex    性别 0:男,1:女
     * @return 是否成功，true：成功，false：失败
     * @throws BTNotReadyException com.hcoor.sdk.BTNotReadyException
     */
    public boolean metageUser(int height, int age, int sex) throws BTNotReadyException {
        CmdA5 a5cmd = new CmdA5();
        a5cmd.setSendDatas(connector.getConnectedDeviceAddress(), height, age, sex, 1);
//        BodyInfo bodyInfo = new BodyInfo();
//        bodyInfo.setGroup1(10000, 65.5f, 10.0f, 20.5f);
//        bodyInfo.setGroup2(30.5f, 2.8f, 1024, 10.9f, 19, 20, 2804);
//        btListener.onNotificationBodyInfo(bodyInfo);
        Log.i(TAG, String.format("发送称重指令：%s,%s,%s", height, age, sex));
        return write(a5cmd.a2s());
    }

    public void disconnectBT() {
        if (connector != null) {
            connector.disconnect();
        }
    }
}
