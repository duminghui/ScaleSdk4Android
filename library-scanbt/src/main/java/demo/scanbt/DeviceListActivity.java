/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demo.scanbt;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.hcoor.sdk.bt.BluetoothScanService;
import com.hcoor.sdk.bt.InnerScanListener;

import java.util.ArrayList;
import java.util.List;

import demo.library_scanbt.R;

/**
 * This Activity appears as a dialog. It lists any paired devices and
 * devices detected in the area after discovery. When a device is chosen
 * by the user, the MAC address of the device is sent back to the parent
 * Activity in the result Intent.
 */
public class DeviceListActivity extends Activity {

    /**
     * Tag for Log
     */
    private static final String TAG = "DeviceListActivity";

    /**
     * Return Intent extra
     */
    public final static String EXTRA_DEVICE_ADDRESS = "device_address";

    public final static String EXTRA_DEVICE_NAME = "device_name";

    /**
     * Newly discovered devices
     */
    private ArrayAdapter<String> mNewDevicesArrayAdapter;
    private List<String> deviceNames = new ArrayList<>();

    private BluetoothScanService scanService = BluetoothScanService.getInstance();

    private Button scanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setup the window
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_device_list);

        // Set result CANCELED in case the user backs out
        setResult(Activity.RESULT_CANCELED);

        // Initialize the button to perform device discovery
        scanButton = (Button) findViewById(R.id.button_scan);
//        scanButton.setOnClickListener(v -> {
//            doDiscovery();
//
//        });
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDiscovery();
            }
        });
        mNewDevicesArrayAdapter = new ArrayAdapter<>(this, R.layout.device_name);

        // Find and set up the ListView for newly discovered devices
        ListView newDevicesListView = (ListView) findViewById(R.id.new_devices);
        newDevicesListView.setAdapter(mNewDevicesArrayAdapter);
        newDevicesListView.setOnItemClickListener(mDeviceClickListener);

        scanService.setContextAndListeners(this, null, new InnerScanListener() {
            @Override
            public void scanStart() {

            }

            @Override
            public void scanDevice(BluetoothDevice device) {
                String text = device.getName() + "\n" + device.getAddress();
//                if (text.startsWith("hcoor hs") && !deviceNames.contains(text)) {
                if (text.startsWith("hcoor hs") && !deviceNames.contains(text)) {
                    deviceNames.add(text);
                    mNewDevicesArrayAdapter.add(text);
                }
            }

            @Override
            public void scanFinished(BluetoothDevice device) {
                setProgressBarIndeterminateVisibility(false);
                setTitle(R.string.select_device);
                if (mNewDevicesArrayAdapter.getCount() == 0) {
                    String noDevices = getResources().getText(R.string.none_found).toString();
                    mNewDevicesArrayAdapter.add(noDevices);
                }
                scanButton.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        doDiscovery();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        scanService.stopScan();
    }

    @Override
    protected void onStop() {
        super.onStop();
        scanService.stopScan();
    }

    /**
     * Start device discover with the BluetoothAdapter
     */
    private void doDiscovery() {
        Log.d(TAG, "doDiscovery()");
        scanButton.setVisibility(View.GONE);
        mNewDevicesArrayAdapter.clear();
        // Indicate scanning in the title
        setProgressBarIndeterminateVisibility(true);
        setTitle(R.string.scanning);

        // Turn on sub-title for new devices
        findViewById(R.id.title_new_devices).setVisibility(View.VISIBLE);
        scanService.startScan(-1);
    }


    /**
     * The on-click listener for all devices in the ListViews
     */
    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            scanService.stopScan();
            // Get the device MAC address, which is the last 17 chars in the View
            String info = ((TextView) view).getText().toString();
            if (info.length() > 17) {
                String address = info.substring(info.length() - 17);
                String name = info.substring(0, info.length() - 18);
                // Create the result Intent and include the MAC address
                Intent intent = new Intent();
                intent.putExtra(EXTRA_DEVICE_ADDRESS, address);
                intent.putExtra(EXTRA_DEVICE_NAME, name);
                // Set result and finish this Activity
                setResult(Activity.RESULT_FIRST_USER, intent);
            }
            finish();
        }
    };
}
