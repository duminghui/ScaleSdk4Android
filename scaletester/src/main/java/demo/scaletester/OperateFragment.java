package demo.scaletester;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hcoor.sdk.ADataListener;
import com.hcoor.sdk.BTNotReadyException;
import com.hcoor.sdk.BTStatusListener;
import com.hcoor.sdk.BodyInfo;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import demo.scanbt.DeviceListActivity;
import hcoor.library_del_bonded_devices.DeleteBondedDevices;


/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_operate)
@OptionsMenu(R.menu.menu_scaletest)
public class OperateFragment extends Fragment {
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final String TAG = "OperateFragment";
    @ViewById
    protected LinearLayout llInfo;
    @ViewById
    protected TextView tvInfo;
    @ViewById
    protected Button btnScan;
    @ViewById
    protected Button btnConnect;
    @ViewById
    protected Button btnDisconnect;
    @ViewById
    protected TextView tvConnectInfo;
    @ViewById
    protected TextView tvName;
    @ViewById
    protected TextView tvMac;
    @ViewById
    protected TextView tvSoftwareVersion;
    @ViewById
    protected TextView tvHardwareId;
    @ViewById
    protected EditText etHeight;
    @ViewById
    protected EditText etAge;
    @ViewById
    protected RadioGroup rgSex;
    @ViewById
    protected RadioButton rbMan;
    @ViewById
    protected RadioButton rbWoman;
    @ViewById
    protected Button btnSend;
    @ViewById
    protected TextView tvSendResult;
    @ViewById
    protected TextView tvWeighting;
    @ViewById
    protected TextView tvTime;
    @ViewById
    protected TextView tvWeight;
    @ViewById
    protected TextView tvBf;
    @ViewById
    protected TextView tvWater;
    @ViewById
    protected TextView tvMuscle;
    @ViewById
    protected TextView tvBone;
    @ViewById
    protected TextView tvBmr;
    @ViewById
    protected TextView tvInFat;
    @ViewById
    protected TextView tvBodyAge;

    private BluetoothManager manager;

    private String address;
    private String name;

    @OptionsItem(R.id.action_del_bonded_devices)
    protected void delBondedDevices() {
        llInfo.setVisibility(View.VISIBLE);
        setInfoHidden(getResources().getString(R.string.delBondedDevicesIng), false);
        DeleteBondedDevices.getInstance().toDel();
        setInfoHidden(getResources().getString(R.string.delBondedDevicesEnd), false);
    }

    private void setInfoHidden(String info, boolean isError) {
        tvInfo.setText(info);
        if (isError) {
            tvInfo.setTextColor(getResources().getColor(R.color.reg));
        } else {
            tvInfo.setTextColor(getResources().getColor(R.color.blue));
        }
        llInfo.removeCallbacks(hiddenInfoRunnable);
        llInfo.postDelayed(hiddenInfoRunnable, 10000L);
    }

    //    private Runnable hiddenInfoRunnable = ()-> llInfo.setVisibility(View.GONE);
    private Runnable hiddenInfoRunnable = new Runnable() {
        @Override
        public void run() {
            llInfo.setVisibility(View.GONE);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = new BluetoothManager(getActivity(), new BTStatusListenerImpl(), new DataListenerImpl());
    }

    private class BTStatusListenerImpl implements BTStatusListener {

        @Override
        public void onScanStart() {

        }

        @Override
        public void onScanEnd(boolean isFindCanUseDevice) {

        }

        @Override
        public void onConnectStart() {
            setConnectInfo("开始连接...", false);
        }

        @Override
        public void onConnecting() {
            setConnectInfo("正在连接...", false);
        }

        @Override
        public void onConnectSuccess() {

        }

        @Override
        public void onConnectFailed() {
            emptyInfo();
            setConnectInfo("连接失败", true);
            setBtnEnableByIsConnection(false);
        }

        @Override
        public void onDisconnected() {
//            emptyInfo();
            setConnectInfo("连接已断开", true);
            setBtnEnableByIsConnection(false);
        }

        @Override
        public void onReadWriteStatus(boolean isCanRead, boolean isCanWrite) {
            if (isCanRead && isCanWrite) {
                setConnectInfo("连接成功", false);
                setBtnEnableByIsConnection(true);
//                try {
//                    manager.readHardwareSoftVar();
//                    manager.readHardwareID();
//                } catch (BTNotReadyException e) {
//                    e.printStackTrace();
//                }

//                tvSoftwareVersion.postDelayed(() -> {
//                    try {
//                        manager.readHardwareSoftVar();
//                    } catch (BTNotReadyException e) {
//                        e.printStackTrace();
//                    }
//                }, 10);
                tvSoftwareVersion.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            manager.readHardwareSoftVar();
                        } catch (BTNotReadyException e) {
                            e.printStackTrace();
                        }
                    }
                }, 10);
//                tvHardwareId.postDelayed(() -> {
//                    try {
//                        manager.readHardwareID();
//                    } catch (BTNotReadyException e) {
//                        e.printStackTrace();
//                    }
//                }, 250);
                tvHardwareId.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            manager.readHardwareID();
                        } catch (BTNotReadyException e) {
                            e.printStackTrace();
                        }
                    }
                }, 250);
            } else {
                emptyInfo();
                setConnectInfo("连接失败", true);
                setBtnEnableByIsConnection(false);
            }
        }
    }

    private class DataListenerImpl extends ADataListener {
        @Override
        public void onReadHardwareSoftVar(int var) {
            tvSoftwareVersion.setText(String.format("%s", var));
        }

        @Override
        public void onNotificationWeight(int stableFlag, float weight) {
            if (stableFlag == 0) {
                btnSend.setEnabled(true);
            }
            tvWeighting.setText(String.format("%s(%s)", weight, stableFlag == 0 ? "锁定" : "未锁定"));
        }

        @Override
        public void onNotificationBodyInfo(BodyInfo bodyInfo) {
            setText(tvTime, bodyInfo.time);
            setText(tvWeight, bodyInfo.weight);
            setText(tvBf, bodyInfo.bf);
            setText(tvWater, bodyInfo.water);
            setText(tvMuscle, bodyInfo.muscle);
            setText(tvBone, bodyInfo.bone);
            setText(tvBmr, bodyInfo.bmr);
            setText(tvInFat, bodyInfo.inFat);
            setText(tvBodyAge, bodyInfo.bodyAge);
        }

        @Override
        public void onReadHardwareID(String id) {
            setText(tvHardwareId, id);
        }

        private <T> void setText(TextView tv, T t) {
            tv.setText(String.format("%s", t));
        }
    }

    private void setBtnEnableByIsConnection(boolean isConnection) {
        btnConnect.setEnabled(!isConnection);
        btnDisconnect.setEnabled(isConnection);
        btnSend.setEnabled(isConnection);
    }

    @Click(R.id.btn_scan)
    protected void onScanClick() {
        Intent serverIntent = new Intent(getActivity(), DeviceListActivity.class);
        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);

    }

    @Click(R.id.btn_connect)
    protected void onConnectClick() {
        emptyInfo();
        connectBT();
    }

    @Click(R.id.btn_disconnect)
    protected void onDisconnectClick() {
        manager.disconnectBT();
    }

    @Click(R.id.btn_send)
    protected void onSendClick() {
        int height = Integer.parseInt(etHeight.getText().toString());
        int age = Integer.parseInt(etAge.getText().toString());
        int sex = 0;
        if (rbMan.isChecked()) {
            sex = 0;
        } else if (rbWoman.isChecked()) {
            sex = 1;
        }
        Log.i(TAG, String.format("h:%s,a:%s,s:%s", height, age, sex));
        try {
            manager.weighVisitor(height, age, sex);
        } catch (BTNotReadyException e) {
            e.printStackTrace();
            manager.disconnectBT();
            setConnectInfo("蓝牙状态错误，请重新连接或扫描", true);
            setBtnEnableByIsConnection(false);
        }
        emptyBodyInfo();
        btnSend.setEnabled(false);
    }

    @OnActivityResult(REQUEST_CONNECT_DEVICE)
    protected void onResult(int resultCode, @OnActivityResult.Extra(value = DeviceListActivity.EXTRA_DEVICE_NAME) String name, @OnActivityResult.Extra(value = DeviceListActivity.EXTRA_DEVICE_ADDRESS) String address) {
        if (resultCode == Activity.RESULT_FIRST_USER) {
            Log.i(TAG, String.format("%s[%s]", name, address));
            emptyAllInfo();
            this.address = address;
            this.name = name;
            tvName.setText(name);
            tvMac.setText(address);
            setBtnEnableByIsConnection(false);
            connectBT();
        } else {
            Log.i(TAG, "无设备信息");
        }
    }

    private void connectBT() {
        btnConnect.setEnabled(false);
        boolean toConnect = manager.connectBT(name, address);
        if (!toConnect) {
            setConnectInfo("错误的设备信息", true);
            btnConnect.setEnabled(true);
        }
    }

    private void setConnectInfo(String text, boolean isError) {
        tvConnectInfo.setText(text);
        if (isError) {
            tvConnectInfo.setTextColor(getResources().getColor(R.color.reg));
        } else {
            tvConnectInfo.setTextColor(getResources().getColor(R.color.blue));
        }
    }

    private void emptyAllInfo() {
        setConnectInfo("", false);
        tvName.setText("");
        tvMac.setText("");
        tvSoftwareVersion.setText("");
        tvHardwareId.setText("");
        emptyBodyInfo();
    }


    private void emptyInfo() {
        setConnectInfo("", false);
        tvSoftwareVersion.setText("");
        tvHardwareId.setText("");
        emptyBodyInfo();
    }

    private void emptyBodyInfo() {
        tvSendResult.setText("");
        tvWeighting.setText("");
        tvTime.setText("");
        tvWeight.setText("");
        tvBf.setText("");
        tvWater.setText("");
        tvMuscle.setText("");
        tvBone.setText("");
        tvBmr.setText("");
        tvInFat.setText("");
        tvBodyAge.setText("");
    }
}
