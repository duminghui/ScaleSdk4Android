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
package com.hcoor.sdk.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;


/**
 * 蓝牙扫描处理
 * Created by dumh on 14/12/13.
 */
public class BluetoothScanService {
    private static final String TAG = "BluetoothScanService";
    private static final BluetoothScanService instance = new BluetoothScanService();
    private BluetoothAdapter adapter;
    private Context context;
    private InnerScanListener listener;
    private IDeviceFilter filter;
    private Handler handler = new Handler();


    public static BluetoothScanService getInstance() {
        return instance;
    }

    private BluetoothScanService() {
        adapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void stopScan() {
        if (adapter.isDiscovering()) {
            adapter.cancelDiscovery();
            adapter.getBondedDevices();
        }
        handler.removeCallbacks(stopRunnable);
    }

    public void setContextAndListeners(Context context, IDeviceFilter filter, InnerScanListener listener) {
        this.context = context;
        this.filter = filter;
        this.listener = listener;
    }

    /**
     * @param scan_duration 扫描时间，单位秒
     */
    public void startScan(int scan_duration) {
        stopScan();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        context.registerReceiver(mReceiver, filter);
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        context.registerReceiver(mReceiver, filter);
//        new Thread(adapter::startDiscovery).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                adapter.startDiscovery();
            }
        }).start();
        listener.scanStart();
        if (scan_duration > 0) {
            handler.postDelayed(stopRunnable, scan_duration * 1000);
        }
    }


    //    private Runnable stopRunnable = this::stopScan;
    private Runnable stopRunnable = new Runnable() {
        @Override
        public void run() {
            stopScan();
        }
    };

    /**
     * The BroadcastReceiver that listens for discovered devices and changes the title when
     * discovery is finished
     */
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        private BluetoothDevice canUseDevice;

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.i(TAG, String.format("扫描到设备:[%s][%s]", device.getName(), device.getAddress()));
                listener.scanDevice(device);
                if (filter != null && filter.filter(device)) {
                    canUseDevice = device;
                    stopScan();
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                Log.i(TAG, "####蓝牙扫描结束####");
                listener.scanFinished(canUseDevice);
                context.unregisterReceiver(this);
            }
        }

    };

}
