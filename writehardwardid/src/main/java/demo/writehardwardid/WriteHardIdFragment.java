package demo.writehardwardid;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hcoor.sdk.ADataListener;
import com.hcoor.sdk.BTNotReadyException;
import com.hcoor.sdk.BTStatusListener;
import com.orhanobut.logger.Logger;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;

import demo.scanbt.DeviceListActivity;
import demo.writehardwardid.db.HardwareId;
import demo.writehardwardid.db.HardwareIdDao;
import hcoor.library_del_bonded_devices.DeleteBondedDevices;

/**
 */
@EFragment(R.layout.fragment_write_hard_id)
@OptionsMenu(R.menu.menu_writeid)
public class WriteHardIdFragment extends Fragment {
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final String TAG = "WIF";

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
    protected TextView tvName;
    @ViewById
    protected TextView tvMac;
    @ViewById
    protected TextView tvConnectInfo;
    @ViewById
    protected TextView tvSoftwareVersion;
    @ViewById
    protected TextView tvHardwareId;
    @ViewById
    protected EditText etCandidateId;
    @ViewById
    protected LinearLayout llIdIsUseInfo;
    @ViewById
    protected TextView tvIdIsUseInfo;
    @ViewById
    protected LinearLayout llIdChangeInfo;
    @ViewById
    protected TextView tvIdChangeInfo;
    @ViewById
    protected TextView tvWriteIdResult;
    @ViewById
    protected Button btnWriteId;

    @OptionsMenuItem(R.id.action_upload_sn_2_server)
    protected MenuItem menuUploadSn2Server;

    @OptionsMenuItem(R.id.action_uploaded_sn_2_server)
    protected MenuItem menuUploadedSn2Server;

    @OptionsMenuItem(R.id.refresh_loading)
    protected MenuItem menuRefreshLoading;

    private BluetoothManager bluetoothManager;

    private boolean isConnected = false;
    private boolean isRightId = false;
    private String address = "";
    private String name = "";

    private HardwareIdDao hardwareIdDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bluetoothManager = new BluetoothManager(getActivity(), new BTStatusListenerImpl(), new DataListenerImpl());
        hardwareIdDao = new HardwareIdDao(getActivity());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        setMenusTitles();
    }

    private void setMenusTitles() {
        menuUploadSn2Server.setTitle(getResources().getString(R.string.upload_sn_2_server_count, hardwareIdDao.getUnUploadCount()));
        menuUploadedSn2Server.setTitle(getResources().getString(R.string.uploaded_sn_2_server_count, hardwareIdDao.getUploadCount()));
    }

    @AfterViews
    protected void afterViews() {
        etCandidateId.addTextChangedListener(etCandidateTextWatcher);
        tvIdIsUseInfo.setMovementMethod(LinkMovementMethod.getInstance());
        tvIdChangeInfo.setMovementMethod(LinkMovementMethod.getInstance());
        resetHWId();
    }

    @OptionsItem(R.id.action_edit_id)
    protected void edit_id() {
        etCandidateId.setEnabled(true);
        etCandidateId.findFocus();
    }

    @OptionsItem(R.id.action_del_bonded_devices)
    protected void delBondedDevices() {
        llInfo.setVisibility(View.VISIBLE);
        setInfoHidden(getResources().getString(R.string.delBondedDevicesIng), false);
        DeleteBondedDevices.getInstance().toDel();
        setInfoHidden(getResources().getString(R.string.delBondedDevicesEnd), false);
    }

    @OptionsItem(R.id.action_upload_sn_2_server)
    protected void upload_sn_2_server() {
        setLoadingState(true);
        menuUploadSn2Server.setEnabled(false);
        llInfo.setVisibility(View.VISIBLE);
        tvInfo.setText("正在上传ID至服务器");
        tvInfo.setTextColor(getResources().getColor(R.color.green));
        UploadIdToServer uploadIdToServer = UploadIdToServer.getInstance();
        uploadIdToServer.setListener(getActivity(), new IUploadIdListener() {
            @Override
            public void onSuccess(int count) {
                long toUpload = hardwareIdDao.getUnUploadCount();
                setInfoHidden(getResources().getString(R.string.info_success_upload_count, count, toUpload), false);
                menuUploadSn2Server.setEnabled(true);
                setMenusTitles();
                setLoadingState(false);
            }

            @Override
            public void onError(int code, int count) {
                String msg = "网络有问题";
                if (count == IUploadIdListener.CODE_SERVER) {
                    msg = "服务器有问题";
                }
                setInfoHidden(getResources().getString(R.string.info_error_upload_count, count, msg), true);
                setLoadingState(false);
                menuUploadSn2Server.setEnabled(true);
            }
        });
        uploadIdToServer.toUpload();
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

    private void setLoadingState(boolean refreshing) {
        if (menuRefreshLoading != null) {
            if (refreshing) {
                menuRefreshLoading
                        .setActionView(R.layout.actionbar_indeterminate_progress);
                menuRefreshLoading.setVisible(true);
            } else {
                menuRefreshLoading.setVisible(false);
                menuRefreshLoading.setActionView(null);
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        bluetoothManager.disconnectBT();
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
            isConnected = false;
            setConnectInfo("连接失败", true);
            setButtonEnableWithRightIdAndConnect(isRightId, false);
        }

        @Override
        public void onDisconnected() {
            emptyInfo();
            isConnected = false;
            setConnectInfo("连接已断开", true);
            setButtonEnableWithRightIdAndConnect(isRightId, false);
        }

        @Override
        public void onReadWriteStatus(boolean isCanRead, boolean isCanWrite) {
            if (isCanRead && isCanWrite) {
                setConnectInfo("连接成功", false);
                isConnected = true;
                setButtonEnableWithRightIdAndConnect(isRightId, true);
                tvSoftwareVersion.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            bluetoothManager.readHardwareSoftVar();
                        } catch (BTNotReadyException e) {
                            e.printStackTrace();
                        }
                    }
                }, 10);
                tvHardwareId.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            bluetoothManager.readHardwareID();
                        } catch (BTNotReadyException e) {
                            e.printStackTrace();
                        }
                    }
                }, 250);
//                    writeId();

            } else {
                emptyInfo();
                setConnectInfo("连接失败", true);
                isConnected = false;
                setButtonEnableWithRightIdAndConnect(isRightId, false);
            }
        }
    }

    private class DataListenerImpl extends ADataListener {
        @Override
        public void onReadHardwareSoftVar(int var) {
            tvSoftwareVersion.setText(String.format("%s", var));
        }

        @Override
        public void onSetHardwareId(int result) {
            if (result == 1) {
                setWriteResultInfo("烧录ID失败", true);
                btnWriteId.setEnabled(true);
                hardwareIdDao.bindMac(getWriteId(), "");
            } else if (result == 2) {
                setWriteResultInfo("烧录ID成功", false);
                hardwareIdDao.saveWrited(getWriteId());
                resetHWId();
                setMenusTitles();
                tvHardwareId.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            bluetoothManager.readHardwareID();
                        } catch (BTNotReadyException e) {
                            e.printStackTrace();
                        }
                    }
                }, 10);
                btnWriteId.setEnabled(false);
            } else {
                setWriteResultInfo(String.format("不能识别反馈的结果：%s", result), true);
                btnWriteId.setEnabled(true);
            }
        }

        @Override
        public void onReadHardwareID(String id) {
            tvHardwareId.setText(id);
        }
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
        bluetoothManager.disconnectBT();
    }

    @Click(R.id.btn_write_id)
    protected void writeId() {
        try {
            String writeId = getWriteId();
            if (writeId.equals("")) {
                setCandidateIdErrorMsg("需要输入12位ID");
                etCandidateId.setEnabled(true);
            } else {
                btnWriteId.setEnabled(false);
                etCandidateId.setEnabled(false);
                bluetoothManager.setHardwareID(writeId);
                hardwareIdDao.bindMac(writeId, address);
                setWriteResultInfo("正在烧录...", false);
            }
        } catch (BTNotReadyException e) {
            e.printStackTrace();
            bluetoothManager.disconnectBT();
            setWriteResultInfo("蓝牙状态错误，请重新连接或扫描", true);
            setButtonEnableWithRightIdAndConnect(isRightId, false);
            etCandidateId.setEnabled(false);
        }
    }

    private TextWatcher etCandidateTextWatcher = new TextWatcher() {
        private String beforeID = "";

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Logger.t(TAG, 2).i("s:%s,start:%s,after:%s,count:%s", s,
                    start, after, count);
            beforeID = s.toString().replaceAll("-", "");
        }

        /**
         * 字符串s从start开始有before个字符被count个字符替换
         * @param s 字符串
         * @param start 开始位置，如果只是单字符添加，则为添加前的位置，如果为单字符减少，则为删除后的位置
         * @param before 被替换的字符长度
         * @param count 替换的字符长度
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Logger.t(TAG, 2).i("s:%s,start:%s,before:%s,count:%s", s,
                    start, before, count);
            int selectionIndex = start;
            //替换之后的字符串
            String toStr = formatCandidateId(s.toString());
            //替换之后的字符串和替换前的字符串的相差值，比原来长为正数
            int addOrDelLength = toStr.length() - s.length();

            if (count > 0) {
                //如果为添加字符串或者替换字符串，不需要考虑before的情况，原本光标的位置向后移动count+新填的字符串的位置
                selectionIndex = start + count + addOrDelLength;
            } else if (before > 0) {
                //这个判断分支只在删除字符串的情况下出现
                if (s.toString().endsWith("-")) {
                    //判断删除的结果字符最后一位是否为特殊字符"-"，如果是则光标前移一位，如果不是则保持不变
                    selectionIndex = start - 1;
                } else {
                    selectionIndex = start;
                }

            }
            etCandidateId.removeTextChangedListener(this);
            etCandidateId.setText(toStr);
            etCandidateId.setSelection(selectionIndex);
            etCandidateId.addTextChangedListener(this);
        }

        @Override
        public void afterTextChanged(Editable s) {
            Logger.t(TAG, 2).i("%s", s.toString());
            String writeId = s.toString().replaceAll("-", "");
            if (writeId.length() == 12) {
                if (isRightId(s.toString())) {
                    HardwareId hardwareId = hardwareIdDao.getHardwareIdById(writeId);
                    if (hardwareId != null && !hardwareId.getMac().equals("")) {
                        showIdIsUseInfo(getResources().getString(R.string.id_is_use, formatCandidateId(s.toString()), hardwareId.getMac()), -1);
                        etCandidateId.setTextColor(getResources().getColor(R.color.purple));
                        isRightId = false;
                    } else {
                        etCandidateId.setTextColor(getResources().getColor(R.color.blue));
                        isRightId = true;
                        //如果是正确的ID，并且之前有没有用过的ID，将之前没有使用的ID清空
                        hardwareIdDao.cleanUnbindID();
                        hardwareIdDao.newHardwareId(writeId);
                    }
                    setButtonEnableWithRightIdAndConnect(isRightId, isConnected);
                    //暂时不强制隐藏
//                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                        imm.hideSoftInputFromWindow(etCandidateId.getWindowToken(), 0);
                } else {
                    setButtonEnableWithRightIdAndConnect(false, isConnected);
                    setCandidateIdErrorMsg("需要输入正确的12位ID");
                }
            } else {
                etCandidateId.setTextColor(getResources().getColor(R.color.purple));
                isRightId = false;
                setButtonEnableWithRightIdAndConnect(false, isConnected);
                if (beforeID.length() == 12 && !hardwareIdDao.idUse4Mac(beforeID).equals("")) {
                    hiddenIdIsUseInfo();
                    hiddenIdChangeInfo();
                }
            }
        }
    };

    private void hiddenIdIsUseInfo() {
        llIdIsUseInfo.removeCallbacks(idIsUseInfoHidden);
        llIdIsUseInfo.setVisibility(View.GONE);
    }

    private void showIdIsUseInfo(CharSequence str, long hiddenMilli) {
        llIdIsUseInfo.removeCallbacks(idIsUseInfoHidden);
        llIdIsUseInfo.setVisibility(View.VISIBLE);

        SpannableString ss = new SpannableString(str);
        setSpan(ss.toString(), ss, new ForegroundColorSpan(getResources().getColor(R.color.reg)));
        tvIdIsUseInfo.setText(ss);
        if (hiddenMilli > 0) {
            llIdIsUseInfo.postDelayed(idIsUseInfoHidden, hiddenMilli);
        }
    }

    //    private Runnable idIsUseInfoHidden = () -> llIdIsUseInfo.setVisibility(View.GONE);
    private Runnable idIsUseInfoHidden = new Runnable() {
        @Override
        public void run() {
            llIdIsUseInfo.setVisibility(View.GONE);
        }
    };


    private void hiddenIdChangeInfo() {
        llIdChangeInfo.removeCallbacks(idChangeInfoHidden);
        llIdChangeInfo.setVisibility(View.GONE);
    }

    private void showIdChangeInfo(CharSequence str, long hiddenMilli) {
        llIdChangeInfo.removeCallbacks(idChangeInfoHidden);
        llIdChangeInfo.setVisibility(View.VISIBLE);
        tvIdChangeInfo.setText(str);
        if (hiddenMilli > 0) {
            llIdChangeInfo.postDelayed(idChangeInfoHidden, hiddenMilli);
        }
    }

    private Runnable idChangeInfoHidden = new Runnable() {
        @Override
        public void run() {
            llIdChangeInfo.setVisibility(View.GONE);
        }
    };


    private void setButtonEnableWithRightIdAndConnect(boolean isRightId, boolean isConnected) {
        if (isRightId && isConnected) {
            btnWriteId.setEnabled(true);
            btnScan.setEnabled(true);
            btnDisconnect.setEnabled(true);
            btnConnect.setEnabled(false);
        } else {
            btnWriteId.setEnabled(false);
            if (isRightId) {
                if (address.length() > 0) {
                    btnConnect.setEnabled(true);
                } else {
                    btnConnect.setEnabled(false);
                }
                btnScan.setEnabled(true);
                btnDisconnect.setEnabled(false);
            } else if (isConnected) {
                btnScan.setEnabled(true);
                btnConnect.setEnabled(false);
                btnDisconnect.setEnabled(true);
            } else {
                btnScan.setEnabled(false);
                btnConnect.setEnabled(false);
                btnDisconnect.setEnabled(false);
            }
        }
    }

    private String formatCandidateId(String src) {
        int[] limits = {3, 9, src.length()};
        src = src.replaceAll("-", "");
        int length = src.length();
        StringBuilder sb = new StringBuilder();
        int start = 0, end;
        for (int tmpEnd : limits) {
            end = tmpEnd;
            if (tmpEnd < length) {
                sb.append(src.substring(start, end)).append("-");
                start = end;
            } else {
                sb.append(src.substring(start, length));
                break;
            }
        }
        return sb.toString();
    }

    private void resetHWId() {
        String writeId = getWriteId();
        if (!writeId.equals("")) {
            long longWriteId = Long.parseLong(writeId);
            writeId = String.valueOf(longWriteId + 1);
            etCandidateId.setText(writeId);
//            HardwareId hardwareId = hardwareIdDao.getHardwareIdById(writeId);
//            if (hardwareId != null && !hardwareId.getMac().equals("")) {
//                showIdIsUseInfo(getResources().getString(R.string.id_is_use, hardwareId.getMac()), true, -1);
//                etCandidateId.setEnabled(true);
//            }else{
//                setButtonEnableWithRightIdAndConnect(true, isConnected);
//            showIdIsUseInfo(getChangeStr(String.valueOf(longWriteId), String.valueOf(writeId)), false, 5000);
            showIdChangeInfo(getChangeStr(String.valueOf(longWriteId), writeId), 5000);
//                etCandidateId.setEnabled(false);
//            }
        } else {
            HardwareId hardwareId = hardwareIdDao.getLastHardwareId();
            if (hardwareId == null) {
                setButtonEnableWithRightIdAndConnect(false, isConnected);
                setCandidateIdErrorMsg("需要输入12位ID");
                etCandidateId.setEnabled(true);
            } else {
                String mac = hardwareId.getMac();
                if (mac.equals("")) {
                    etCandidateId.setText(hardwareId.getHardware_id());
//                    etCandidateId.setEnabled(false);
                    setButtonEnableWithRightIdAndConnect(true, isConnected);
                    etCandidateId.setEnabled(true);
                } else {
                    etCandidateId.removeTextChangedListener(etCandidateTextWatcher);
                    etCandidateId.setText(hardwareId.getHardware_id());
                    etCandidateId.addTextChangedListener(etCandidateTextWatcher);
                    resetHWId();
                }
            }
        }
    }

    private Spannable getChangeStr(String before, String after) {
        SpannableString ss = new SpannableString(getResources().getString(R.string.id_change_to, before, after));
        setSpan(before, ss, new ForegroundColorSpan(getResources().getColor(R.color.purple)));
        setSpan(after, ss, new ForegroundColorSpan(getResources().getColor(R.color.blue)));
        return ss;
    }

    private void setSpan(String sub, SpannableString ss, CharacterStyle span) {
        int start = 0;
        int end = 0;
        if (sub != null && !sub.isEmpty()) {
            start = ss.toString().indexOf(sub);
            end = start + sub.length();
        }
        ss.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }


    private void setCandidateIdErrorMsg(CharSequence msg) {
        etCandidateId.setError(msg);
        etCandidateId.setTextColor(getResources().getColor(R.color.reg));
    }

    private String getWriteId() {
        return etCandidateId.getText().toString().trim().replaceAll("-", "");
    }

    private static final String ID_MATCH_REGEX = "\\d{3}-?\\d{2}(0[1-9]|1[0-2])(3[0-1]|[1-2]\\d|0[1-9])-?\\d{3}";

    private boolean isRightId(String id) {
        return id.matches(ID_MATCH_REGEX);
    }

//    private void setBtnEnableByIsConnection(boolean isConnection) {
//        btnConnect.setEnabled(!isConnection);
//        btnDisconnect.setEnabled(isConnection);
//        btnWriteId.setEnabled(isConnection);
//    }

    private void setWriteResultInfo(String text, boolean isError) {
        tvWriteIdResult.setText(text);
        if (isError) {
            tvWriteIdResult.setTextColor(getResources().getColor(R.color.reg));
        } else {
            tvWriteIdResult.setTextColor(getResources().getColor(R.color.blue));
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

    @OnActivityResult(REQUEST_CONNECT_DEVICE)
    protected void onResult(int resultCode, @OnActivityResult.Extra(value = DeviceListActivity.EXTRA_DEVICE_NAME) String name, @OnActivityResult.Extra(value = DeviceListActivity.EXTRA_DEVICE_ADDRESS) String address) {
        if (resultCode == Activity.RESULT_FIRST_USER) {
//            Log.i(TAG, String.format("%s[%s]", name, address));
            Logger.t(TAG, 2).i("%s[%s]", name, address);
            emptyAllInfo();
            this.address = address;
            this.name = name;
            tvName.setText(name);
            tvMac.setText(address);
            setButtonEnableWithRightIdAndConnect(isRightId, false);
            connectBT();
        } else {
//            Log.i(TAG, "无设备信息");
            Logger.t(TAG, 2).i("无设备信息");
        }
    }

    private void connectBT() {
        btnConnect.setEnabled(false);
        if (!bluetoothManager.connectBT(name, address)) {
            setConnectInfo("错误的设备信息", true);
            btnConnect.setEnabled(true);
        }
    }

    private void emptyInfo() {
        setConnectInfo("", false);
        tvSoftwareVersion.setText("");
        tvHardwareId.setText("");
        tvWriteIdResult.setText("");
    }

    private void emptyAllInfo() {
        setConnectInfo("", false);
        tvName.setText("");
        tvMac.setText("");
        tvSoftwareVersion.setText("");
        tvHardwareId.setText("");
//        tvCandidateId.setText("");
        tvWriteIdResult.setText("");
    }
}
