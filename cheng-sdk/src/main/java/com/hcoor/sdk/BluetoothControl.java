package com.hcoor.sdk;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by dumh on 14/10/22.
 */
public class BluetoothControl {

    public static boolean btIsOpen() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

        return adapter.isEnabled();
    }

    public static boolean openBT() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        return adapter.isEnabled() || adapter.enable();
    }

    public static boolean closeBt() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        return !adapter.isEnabled() || adapter.disable();
    }
}
