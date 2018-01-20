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
import android.util.Log;

import com.hcoor.sdk.bt.BluetoothScanService;
import com.hcoor.sdk.bt.IDeviceFilter;
import com.hcoor.sdk.bt.InnerScanListener;

/**
 * 新的蓝牙SDK
 * Created by dumh on 14/12/13.
 */
public class BluetoothSDK extends BluetoothConnectAndDataManager {
    private static final String TAG = "BluetoothSDK";
    private BTStatusListener btStatusListener;
    private BluetoothScanService scanService = BluetoothScanService.getInstance();
    /**
     * 扫描持续时间 10秒
     */
    private static final int SCAN_DURATION = 30000;

    public BluetoothSDK(Context context, BTStatusListener btStatusListener, IDataListener dataListener) {
        super(context, btStatusListener, dataListener);
        this.btStatusListener = btStatusListener;
        scanService.setContextAndListeners(context, new DeviceFilter(), new InnerScanListenerImpl());
    }

    public void startScan() {
        scanService.startScan(SCAN_DURATION);
    }


    private class DeviceFilter implements IDeviceFilter {

        @Override
        public boolean filter(BluetoothDevice device) {
            return device != null && device.getName() != null && (device.getName().equals(BluetoothConnectAndDataManager.HARD_NAME_01) || device.getName().equals(BluetoothConnectAndDataManager.HARD_NAME_02));
        }
    }

    private class InnerScanListenerImpl implements InnerScanListener {

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
                connectBT(device.getName(), device.getAddress());
            }
        }
    }
}
