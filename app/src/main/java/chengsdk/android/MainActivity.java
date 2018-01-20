package chengsdk.android;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.hcoor.sdk.ADataListener;
import com.hcoor.sdk.BTNotReadyException;
import com.hcoor.sdk.BTStatusListener;
import com.hcoor.sdk.BluetoothControl;
import com.hcoor.sdk.BluetoothSDK;
import com.hcoor.sdk.BodyInfo;
import com.hcoor.sdk.data.DataSdk;
import com.hcoor.sdk.net.base.VolleyRequestQueue;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";

    @ViewById
    protected Button btnConnection;
    @ViewById
    protected TextView tvConnection;
//    @ViewById
//    protected Button btnCreateUser;
//    @ViewById
//    protected TextView tvCreateUser;
//    @ViewById
//    protected Button btnDelUser;
//    @ViewById
//    protected TextView tvDelUser;
//    @ViewById
//    protected Button btnUpdateUser;
//    @ViewById
//    protected TextView tvUpdateUser;
//    @ViewById
//    protected Button btnReadUserInfos;
//    @ViewById
//    protected TextView tvReadUserInfos;
//    @ViewById
//    protected Button btnSelectUserMetage;
//    @ViewById
//    protected TextView tvSelectUserMetage;
    @ViewById
    protected TextView tvBodyInfoTitle;
    @ViewById
    protected TextView tvBodyInfo;
    @ViewById
    protected TextView tvMetagingWeightTitle;
    @ViewById
    protected TextView tvMetagingWeight;
    @ViewById
    protected Button btnVisitorUserMetage;
    @ViewById
    protected TextView tvVisitorUserMetage;
//    @ViewById
//    protected Button btnReadUserBodyInfos;
//    @ViewById
//    protected TextView tvReadUserBodyInfos;
//    @ViewById
//    protected Button btnDelUserBodyInfos;
//    @ViewById
//    protected TextView tvDelUserBodyInfos;
    @ViewById
    protected Button btnReadHardwareId;
    @ViewById
    protected TextView tvReadHardwareId;
    @ViewById
    protected Button btnWriteHardwareId;
    @ViewById
    protected TextView tvWriteHardwareId;
    @ViewById
    protected Button btnResetHardwareId;
    @ViewById
    protected TextView tvResetHardwareId;
    @ViewById
    protected Button btnReadHardwareVar;
    @ViewById
    protected TextView tvReadHardwareVar;

    private BluetoothSDK bluetoothSDK;

    //    private BluetoothSDKOld bluetoothSDKOld;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VolleyRequestQueue.init(this);
        DataSdk.setDebug(true);
        bluetoothSDK = new BluetoothSDK(this, new BTStatusListenerImpl(), new DataListenerImpl());
//        bluetoothSDKOld = new BluetoothSDKOld(this, new BTStatusListenerImpl(), new DataListenerImpl());
//        btSdkSPP = new BTSdkSPP(this, new BTStatusListenerImpl(), new DataListenerImpl(), "hcoor hs 02");
    }

    private void setBtnEnable(boolean enable) {
//        btnCreateUser.setEnabled(enable);
//        btnDelUser.setEnabled(enable);
//        btnUpdateUser.setEnabled(enable);
//        btnReadUserInfos.setEnabled(enable);
//        btnSelectUserMetage.setEnabled(enable);
        btnVisitorUserMetage.setEnabled(enable);
//        btnReadUserBodyInfos.setEnabled(enable);
//        btnDelUserBodyInfos.setEnabled(enable);
        btnReadHardwareId.setEnabled(enable);
        btnWriteHardwareId.setEnabled(enable);
        btnResetHardwareId.setEnabled(enable);
        btnReadHardwareVar.setEnabled(enable);
    }

    private class BTStatusListenerImpl implements BTStatusListener {

        @Override
        public void onScanStart() {
            tvConnection.setText("开始扫描");
        }

        @Override
        public void onScanEnd(boolean isFindDevice) {
            tvConnection.setText(String.format("扫描结束:%s", isFindDevice));
        }

        @Override
        public void onConnectStart() {
            tvConnection.setText("开始连接");
        }

        @Override
        public void onConnecting() {
            tvConnection.setText("连接ing");
        }

        @Override
        public void onConnectSuccess() {
            tvConnection.setText("连接成功");
//            setBtnEnable(true);
        }

        @Override
        public void onConnectFailed() {
            tvConnection.setText("连接失败");
            setBtnEnable(false);
        }

        @Override
        public void onDisconnected() {
            setBtnEnable(false);
        }

        @Override
        public void onReadWriteStatus(boolean isCanRead, boolean isCanWrite) {
            setBtnEnable(true);
            try {
                bluetoothSDK.weighVisitor(170, 32, 0);
            } catch (BTNotReadyException e) {
                e.printStackTrace();
            }
        }
    }

    private class DataListenerImpl extends ADataListener {

//        @Override
//        public void onCreateUser(int result) {
//            tvCreateUser.setText(String.valueOf(result));
//        }
//
//        @Override
//        public void onDelUser(int result) {
//            tvDelUser.setText(String.valueOf(result));
//        }
//
//        @Override
//        public void onUpdateUser(int result) {
//            tvUpdateUser.setText(String.valueOf(result));
//        }
//
//        @Override
//        public void onWeighSelectUser(int result) {
//            tvSelectUserMetage.setText(String.valueOf(result));
//        }
//
//        @Override
//        public void onReadUserBodyInfos(BodyInfo[] infos) {
//            Log.i(TAG, String.format("BodyInfos:%s", Arrays.toString(infos)));
//            tvReadUserBodyInfos.setText(Arrays.toString(infos));
//        }
//
//        @Override
//        public void onDelUserBodyInfos(int result) {
//            tvDelUserBodyInfos.setText(String.valueOf(result));
//        }
//
//        @Override
//        public void onReadUserInfos(UserInfo[] infos) {
//            Log.i(TAG, String.format("UserInfos:%s", Arrays.toString(infos)));
//            tvReadUserInfos.setText(Arrays.toString(infos));
//        }

        @Override
        public void onReadHardwareID(String id) {
            tvReadHardwareId.setText(id);
        }

        @Override
        public void onSetHardwareId(int result) {
            tvWriteHardwareId.setText(String.valueOf(result));
        }

        @Override
        public void onResetHardwareId(int result) {
            tvResetHardwareId.setText(String.valueOf(result));
        }

        @Override
        public void onReadHardwareSoftVar(int var) {
            tvReadHardwareVar.setText(String.valueOf(var));
        }

        @Override
        public void onNotificationWeight(int stableFlag, float weight) {
            Log.i(TAG, String.format("stable:%s,weight:%s", stableFlag, weight));
            tvMetagingWeight.setText(String.valueOf(weight));
        }

        @Override
        public void onNotificationBodyInfo(BodyInfo bodyInfo) {
            Log.i(TAG, String.format("bodyInfo:%s", bodyInfo.toString()));
            tvBodyInfo.setText(bodyInfo.toString());
//            DataSdk.addMemberRecord("10007462", "b517c34d-cd05-4236-bde7-31f1446fcc8e", "SN", bodyInfo, new NetRequestListener() {
//                @Override
//                public void onNetSuccess(String json) {
//                    Log.i(TAG, String.format("addMemberRecord:%s", json));
//                }
//
//                @Override
//                public void onError(int code, String msg) {
//                    Log.i(TAG, String.format("Error:%s,%s", code, msg));
//                }
//            });
        }

        @Override
        public void onScaleUser(int result) {
//            super.onScaleUser(result);
            tvVisitorUserMetage.setText(String.valueOf(result));
        }
    }

    @Click(R.id.btnConnection)
    protected void connect() {
        boolean btIsOpen = BluetoothControl.btIsOpen();
        Log.d(TAG, String.format("btIsOpe:%s", btIsOpen));
        boolean operateSuccess = false;
        if (!btIsOpen) {
            operateSuccess = BluetoothControl.openBT();
        }
        Log.d(TAG, String.format("operateSuccess:%s", operateSuccess));
        bluetoothSDK.startScan();
//        bluetoothSDKOld.startScan();

//        BluetoothScanService scanService = BluetoothScanService.getInstance();
//        scanService.setContextAndListeners(this, device -> device.getName().equals("Healthcare"), new InnerScanListener() {
//            @Override
//            public void scanStart() {
//                Log.i(TAG, "开始扫描");
//            }
//
//            @Override
//            public void scanFinished(BluetoothDevice device) {
//                Log.i(TAG, String.format("扫描结束:%s,%s", device.getName(), device.getAddress()));
//            }
//        });
//        scanService.startScan();
    }

//    @Click(R.id.btnCreateUser)
//    protected void createUser() {
//        try {
//            bluetoothSDK.createUser(172, 30, 0);
//        } catch (BTNotReadyException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Click(R.id.btnDelUser)
//    protected void delUser() {
//        try {
//            bluetoothSDK.delUer(2);
//        } catch (BTNotReadyException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Click(R.id.btnUpdateUser)
//    protected void updateUser() {
//        try {
//            bluetoothSDK.updateUser(1, 172, 35, 0);
//        } catch (BTNotReadyException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Click(R.id.btnReadUserInfos)
//    protected void readUserInfos() {
//        try {
//            bluetoothSDK.readUserInfos();
//        } catch (BTNotReadyException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Click(R.id.btnSelectUserMetage)
//    protected void selectUserMetage() {
//        try {
//            tvBodyInfo.setText("");
//            bluetoothSDK.weighSelectUser(1);
//        } catch (BTNotReadyException e) {
//            e.printStackTrace();
//        }
//    }

    @Click(R.id.btnVisitorUserMetage)
    protected void visitorUserMetage() {
        try {
            tvBodyInfo.setText("");
            bluetoothSDK.weighVisitor(170, 32, 0);
//            bluetoothSDK.metageUser(180, 25, 0);
        } catch (BTNotReadyException e) {
            e.printStackTrace();
        }
    }


//    @Click(R.id.btnReadUserBodyInfos)
//    protected void readBodyInfos() {
//        try {
//            bluetoothSDK.readUserBodyInfos(1);
//        } catch (BTNotReadyException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Click(R.id.btnDelUserBodyInfos)
//    protected void delUserBodyInfos() {
//        try {
//            bluetoothSDK.delUserBodyInfos(1);
//        } catch (BTNotReadyException e) {
//            e.printStackTrace();
//        }
//    }

    @Click(R.id.btnReadHardwareId)
    protected void readHardwareId() {
        try {
            bluetoothSDK.readHardwareID();
        } catch (BTNotReadyException e) {
            e.printStackTrace();
        }
    }

    @Click(R.id.btnWriteHardwareId)
    protected void writeHardwareId() {
        try {
            bluetoothSDK.setHardwareID("020304050607");
        } catch (BTNotReadyException e) {
            e.printStackTrace();
        }
    }

    @Click(R.id.btnResetHardwareId)
    protected void resetHardwareId() {
        try {
            bluetoothSDK.resetHardWareId();
        } catch (BTNotReadyException e) {
            e.printStackTrace();
        }
    }

    @Click(R.id.btnReadHardwareVar)
    protected void readHardwareVar() {
        try {
            bluetoothSDK.readHardwareSoftVar();
        } catch (BTNotReadyException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bluetoothSDK.disconnectBT();
    }
}
