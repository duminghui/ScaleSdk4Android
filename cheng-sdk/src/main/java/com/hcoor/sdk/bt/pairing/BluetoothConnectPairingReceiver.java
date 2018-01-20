package com.hcoor.sdk.bt.pairing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BluetoothConnectPairingReceiver extends BroadcastReceiver {
    private static final String TAG = "BCPR";

    public BluetoothConnectPairingReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, String.format("onReceive:%s", intent.getAction()));
//        if (intent.getAction().equals(
//                "android.bluetooth.device.action.PAIRING_REQUEST")) {
//            BluetoothDevice btDevice = intent
//                    .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//            try
//            {
//                ClsUtils.setPin(btDevice.getClass(), btDevice,"123456" ); // 手机和蓝牙采集器配对
//                ClsUtils.createBond(btDevice.getClass(), btDevice);
//                ClsUtils.cancelPairingUserInput(btDevice.getClass(), btDevice);
//            }
//            catch (Exception e)
//            {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
    }
}
