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

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.hcoor.sdk.bt.BluetoothConnector;
import com.hcoor.sdk.bt.InnerBTListener;
import com.hcoor.sdk.bt.le.LeBluetoothConnector;
import com.hcoor.sdk.bt.spp.SppBluetoothConnector;
import com.hcoor.sdk.cmd.Cmd15_09;
import com.hcoor.sdk.cmd.Cmd1C;
import com.hcoor.sdk.cmd.Cmd1D;
import com.hcoor.sdk.cmd.Cmd1E;
import com.hcoor.sdk.cmd.Cmd1F;
import com.hcoor.sdk.cmd.Cmd20;
import com.hcoor.sdk.cmd.Cmd21_01;
import com.hcoor.sdk.cmd.Cmd21_02;
import com.hcoor.sdk.cmd.Cmd22;

/**
 * 内部蓝牙处理类<br>
 * 完成扫描
 * Created by dumh on 15/1/2.
 */
public class BluetoothConnectAndDataManager {

    private static final String TAG = "B...hC..tAndDataManager";

    private BluetoothConnector connector;
    private Context context;
    private BTStatusListener btStatusListener;
    private IDataListener dataListener;
    private InnerBTListener listener = new InnerBTListenerImpl();
    private Handler handler = new Handler(Looper.myLooper());

    private BodyInfo bodyinfo = new BodyInfo();
//    private BodyInfo[] bodyInfos;
//    private UserInfo[] userInfos = new UserInfo[8];

    public static final String HARD_NAME_02 = "hcoor hs 02";
    public static final String HARD_NAME_01 = "hcoor hs 01";

    protected BluetoothConnectAndDataManager(Context context, BTStatusListener btStatusListener, IDataListener dataListener) {
        this.context = context;
        this.btStatusListener = btStatusListener;
        this.dataListener = dataListener;
    }

    public void disconnectBT() {
        if (connector != null) {
            connector.disconnect();
        }
        connector = null;
    }

    public boolean isConnected() {
        return connector != null && connector.isConnected();
    }

    public boolean connectBT(String name, String address) {
        if (connector != null) {
            disconnectBT();
        }
        if (name != null && name.equals(HARD_NAME_01)) {
            Log.i(TAG, String.format("使用低耗蓝牙连接到设备：%s[%s]", name, address));
            connector = new LeBluetoothConnector(context, listener);
        } else if (name != null && name.equals(HARD_NAME_02)) {
            connector = new SppBluetoothConnector(listener);
            Log.i(TAG, String.format("使用SPP蓝牙连接到设备：%s[%s]", name, address));
        } else {
            return false;
        }
        connector.connect(address, false);
        return true;
    }

    private class InnerBTListenerImpl implements InnerBTListener {

        @Override
        public void connecting(String name, String mac) {
            Log.i(TAG, String.format("正在连接:%s,%s", name, mac));
//            handler.post(btStatusListener.onConnecting);
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
                    btStatusListener.onReadWriteStatus(isCanRead, isCanWrite);
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

    private void processData(final byte[] values) throws BTNotReadyException {
        if (values == null || values.length == 0) {
            Log.i(TAG, "接收到的数据为空");
            return;
        }
        if (values.length < 2 || values.length != values[1]) {
            Log.i(TAG, String.format("接收到错误的数据:%s", HexUtils.byte2HexLog(values)));
            return;
        }
        final byte cmd = values[3];
//        if (cmd == Cmd10.CMD_ID) {
//            final Cmd10 cmd10 = new Cmd10();
////            handler.post(() -> dataListener.onCreateUser(cmd10.s2a(values, cmd)));
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    dataListener.onCreateUser(cmd10.s2a(values,cmd));
//                }
//            });
//        } else if (cmd == Cmd11.CMD_ID) {
//            final Cmd11 cmd11 = new Cmd11();
////            handler.post(() -> dataListener.onDelUser(cmd11.s2a(values, cmd)));
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    dataListener.onDelUser(cmd11.s2a(values,cmd));
//                }
//            });
//        } else if (cmd == Cmd12.CMD_ID) {
//            final Cmd12 cmd12 = new Cmd12();
////            handler.post(() -> dataListener.onUpdateUser(cmd12.s2a(values, cmd)));
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    dataListener.onUpdateUser(cmd12.s2a(values,cmd));
//                }
//            });
//        } else if (cmd == Cmd13_23_01_02_03.CMD_ID) {
//            Cmd13_23_01_02_03 cmd13_23_01_02_03 = new Cmd13_23_01_02_03();
//            cmd13_23_01_02_03.s2a(values, userInfos);
//            if (values[4] == 0x03) {
////                handler.post(() -> dataListener.onReadUserInfos(userInfos));
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        dataListener.onReadUserInfos(userInfos);
//                    }
//                });
//            }
//        } else
        if (cmd == Cmd15_09.CMD_ID) {
            final Cmd15_09 cmd15_09 = new Cmd15_09();
//            handler.post(() -> dataListener.onWeighSelectUser(cmd15_09.s2a(values, cmd)));
            handler.post(new Runnable() {
                @Override
                public void run() {
                    dataListener.onScaleUser(cmd15_09.s2a(values, cmd));
//                    dataListener.onWeighSelectUser(cmd15_09.s2a(values, cmd));
                }
            });
        } else if (cmd == Cmd21_01.CMD_ID) {
            if (values[5] == 0x01) {
                bodyinfo.emtpy();
                Cmd21_01 cmd21_01 = new Cmd21_01();
                cmd21_01.s2a(values, bodyinfo);
                write(cmd21_01.a2s());
            } else if (values[5] == 0x02) {
                Cmd21_02 cmd21_02 = new Cmd21_02();
                cmd21_02.s2a(values, bodyinfo);
                write(cmd21_02.a2s());
//                handler.post(() -> dataListener.onNotificationBodyInfo(bodyinfo));
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        dataListener.onNotificationBodyInfo(bodyinfo);
                    }
                });
            }
        }
//        else if (cmd == Cmd16_26.CMD_ID) {
//            Cmd16_26.ResultInfo resultInfo = new Cmd16_26.ResultInfo();
//            Cmd16_26 cmd16_26 = new Cmd16_26();
//            cmd16_26.s2a(values, resultInfo);
//            bodyInfos = new BodyInfo[resultInfo.n];
//        }
//        else if (cmd == Cmd16_26_01.CMD_ID) {
//            if (values[5] == 0x01) {
//                Cmd16_26_01 cmd16_26_01 = new Cmd16_26_01();
//                cmd16_26_01.s2a(values, bodyInfos);
//                write(cmd16_26_01.a2s());
//            } else if (values[5] == 0x02) {
//                Cmd16_26_02 cmd16_26_02 = new Cmd16_26_02();
//                cmd16_26_02.s2a(values, bodyInfos);
//                write(cmd16_26_02.a2s());
//                if (values[4] == bodyInfos.length - 1) {
////                    handler.post(() -> dataListener.onReadUserBodyInfos(bodyInfos));
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            dataListener.onReadUserBodyInfos(bodyInfos);
//                        }
//                    });
//                }
//            }
//        } else if (cmd == Cmd17.CMD_ID) {
//            final Cmd17 cmd17 = new Cmd17();
////            handler.post(() -> dataListener.onDelUserBodyInfos(cmd17.s2a(values, cmd)));
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    dataListener.onDelUserBodyInfos(cmd17.s2a(values,cmd));
//                }
//            });
//        }
        else if (cmd == Cmd20.CMD_ID) {
            Cmd20 cmd20 = new Cmd20();
            final Cmd20.Cmd20Values values1 = new Cmd20.Cmd20Values();
            cmd20.s2a(values, values1);
//            handler.post(() -> dataListener.onNotificationWeight(values1.stableFlag, values1.weight));
            handler.post(new Runnable() {
                @Override
                public void run() {
                    dataListener.onNotificationWeight(values1.stableFlag, values1.weight);
                }
            });
            write(cmd20.a2s());
        } else if (cmd == Cmd1D.CMD_ID) {
            final Cmd1D cmd1D = new Cmd1D();
//            handler.post(() -> dataListener.onReadHardwareID(cmd1D.s2a(values, "")));
            handler.post(new Runnable() {
                @Override
                public void run() {
                    dataListener.onReadHardwareID(cmd1D.s2a(values, ""));
                }
            });
        } else if (cmd == Cmd1C.CMD_ID) {
            final Cmd1C cmd1C = new Cmd1C();
//            handler.post(() -> dataListener.onSetHardwareId(cmd1C.s2a(values, cmd)));
            handler.post(new Runnable() {
                @Override
                public void run() {
                    dataListener.onSetHardwareId(cmd1C.s2a(values, cmd));
                }
            });
        } else if (cmd == Cmd1F.CMD_ID) {
            final Cmd1F cmd1F = new Cmd1F();
//            handler.post(() -> dataListener.onResetHardwareId(cmd1F.s2a(values, cmd)));
            handler.post(new Runnable() {
                @Override
                public void run() {
                    dataListener.onResetHardwareId(cmd1F.s2a(values, cmd));
                }
            });
        } else if (cmd == Cmd1E.CMD_ID) {
            final Cmd1E cmd1D = new Cmd1E();
//            handler.post(() -> dataListener.onReadHardwareSoftVar(cmd1D.s2a(values, cmd)));
            handler.post(new Runnable() {
                @Override
                public void run() {
                    dataListener.onReadHardwareSoftVar(cmd1D.s2a(values, cmd));
                }
            });
        } else if (cmd == Cmd22.CMD_ID) {
            Cmd22 cmd22 = new Cmd22();
            write(cmd22.a2s());
        }
    }

    private boolean write(byte[] values) throws BTNotReadyException {
        if (connector == null) {
            throw new BTNotReadyException();
        }
        boolean writeSuccess = connector.write(values);
        if (!writeSuccess) {
            throw new BTNotReadyException();
        }
        return writeSuccess;
    }

//    public boolean createUser(int height, int age, int sex) throws BTNotReadyException {
//        Cmd10 cmd10 = new Cmd10();
//        cmd10.setValues(height, age, sex);
//        return write(cmd10.a2s());
//    }
//
//    public boolean delUer(int p_no) throws BTNotReadyException {
//        Cmd11 cmd11 = new Cmd11();
//        cmd11.setValues(p_no);
//        return write(cmd11.a2s());
//    }
//
//    public boolean updateUser(int p_no, int height, int age, int sex) throws BTNotReadyException {
//        Cmd12 cmd12 = new Cmd12();
//        cmd12.setValues(p_no, height, age, sex);
//        return write(cmd12.a2s());
//    }
//
//    public boolean readUserInfos() throws BTNotReadyException {
//        Cmd13_23 cmd13_23 = new Cmd13_23();
//        return write(cmd13_23.a2s());
//    }
//
//    public boolean weighSelectUser(int p_no) throws BTNotReadyException {
//        Cmd15 cmd15 = new Cmd15();
//        cmd15.setValues(p_no);
//        return write(cmd15.a2s());
//    }
//
//    public boolean readUserBodyInfos(int p_no) throws BTNotReadyException {
//        Cmd16_26 cmd16_26 = new Cmd16_26();
//        cmd16_26.setValues(p_no);
//        return write(cmd16_26.a2s());
//    }
//
//    public boolean delUserBodyInfos(int p_no) throws BTNotReadyException {
//        Cmd17 cmd17 = new Cmd17();
//        cmd17.setValues(p_no);
//        return write(cmd17.a2s());
//    }

    public boolean readHardwareID() throws BTNotReadyException {
        Cmd1D cmd1D = new Cmd1D();
        return write(cmd1D.a2s());
    }

    /**
     * 设备称体ID
     *
     * @param id ID1 ID2 ID3 ID4 ID5 ID6
     * @return 写入结果
     * @throws BTNotReadyException 错误
     */
    public boolean setHardwareID(String id) throws BTNotReadyException {
        Cmd1C cmd1C = new Cmd1C();
        cmd1C.setId(id);
        return write(cmd1C.a2s());
    }

    public boolean resetHardWareId() throws BTNotReadyException {
        Cmd1F cmd1F = new Cmd1F();
        return write(cmd1F.a2s());
    }

    public boolean readHardwareSoftVar() throws BTNotReadyException {
        Cmd1E cmd1E = new Cmd1E();
        return write(cmd1E.a2s());
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

    public boolean weighVisitor(int height, int age, int sex) throws BTNotReadyException {
        Cmd15_09 cmd15_09 = new Cmd15_09();
        cmd15_09.setValues(height, age, sex);
        return write(cmd15_09.a2s());
    }

    public boolean weightVisitorMan(int height, int age) throws BTNotReadyException {
        return weighVisitor(height, age, 0);
    }

    public boolean weightVisitorWoman(int height, int age) throws BTNotReadyException {
        return weighVisitor(height, age, 1);
    }
}
