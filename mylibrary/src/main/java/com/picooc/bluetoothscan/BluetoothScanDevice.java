package com.picooc.bluetoothscan;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class BluetoothScanDevice
{
  public static final String DEFAULT_DEVICE_NAME = "Dual-SPP";
  public static final String DEFAULT_DEVICE_NAME_NEW = "Latin";
  BluetoothDevice deviceTW;
  private int iPairTimes = 0;
  private Boolean isFindDevice = Boolean.valueOf(false);
  private BluetoothAdapter mBtAdapter;
  Context mContext;
  private scanDeviceListener mFinishComposingListener;
  private final BroadcastReceiver mReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      String str = paramAnonymousIntent.getAction();
      BluetoothDevice localBluetoothDevice3;
      if ("android.bluetooth.device.action.FOUND".equals(str))
      {
        localBluetoothDevice3 = (BluetoothDevice)paramAnonymousIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if ((localBluetoothDevice3.getName() != null) && (localBluetoothDevice3.getName().equals("Latin-S")))
        {
          BluetoothScanDevice.this.deviceTW = BluetoothScanDevice.this.mBtAdapter.getRemoteDevice(localBluetoothDevice3.getAddress());
          BluetoothScanDevice.this.mFinishComposingListener.findDevice(BluetoothScanDevice.this.deviceTW);
        }
      }
      label734: 
      do
      {
        while (true)
        {
          return;
          if ((localBluetoothDevice3.getName() != null) && (localBluetoothDevice3.getName().equals("Dual-SPP")))
          {
            if (BluetoothScanDevice.this.mBtAdapter.isDiscovering())
              BluetoothScanDevice.this.mBtAdapter.cancelDiscovery();
            StringBuilder localStringBuilder = new StringBuilder("扫描到一台设备了：");
            int i = localBluetoothDevice3.getBondState();
            boolean bool = false;
            if (i == 12)
              bool = true;
            Log.i("picooc", bool + "   " + localBluetoothDevice3.getName() + "   " + localBluetoothDevice3.getAddress());
            BluetoothScanDevice.this.isFindDevice = Boolean.valueOf(true);
            if (localBluetoothDevice3.getBondState() == 12)
            {
              BluetoothScanDevice.this.deviceTW = BluetoothScanDevice.this.mBtAdapter.getRemoteDevice(localBluetoothDevice3.getAddress());
              BluetoothScanDevice.this.mFinishComposingListener.findDevice(BluetoothScanDevice.this.deviceTW);
              return;
            }
            if (localBluetoothDevice3.getBondState() != 10)
              break;
            try
            {
              Log.i("temp test", "first bond device...");
              BluetoothClsUtils.createBond(localBluetoothDevice3.getClass(), localBluetoothDevice3);
              return;
            }
            catch (Exception localException3)
            {
              localException3.printStackTrace();
              return;
            }
            if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(str))
            {
              if (!BluetoothScanDevice.this.isFindDevice.booleanValue())
              {
                Log.i("temp test", "scanOver isFindDevice=" + BluetoothScanDevice.this.isFindDevice);
                BluetoothScanDevice.this.mFinishComposingListener.scanOver();
              }
              Log.i("picooc", "扫描完成了 isFindDevice=" + BluetoothScanDevice.this.isFindDevice);
              BluetoothScanDevice.this.isFindDevice = Boolean.valueOf(false);
              return;
            }
            if (!"android.bluetooth.device.action.BOND_STATE_CHANGED".equals(str))
              break label734;
            Log.e("BlueToothTestActivity", "配对相关");
            BluetoothDevice localBluetoothDevice2 = (BluetoothDevice)paramAnonymousIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            switch (localBluetoothDevice2.getBondState())
            {
            default:
            case 11:
            case 12:
            case 10:
            }
            while ((localBluetoothDevice2.getBondState() != 10) || (BluetoothScanDevice.this.iPairTimes != 1))
            {
              BluetoothScanDevice.this.deviceTW = BluetoothScanDevice.this.mBtAdapter.getRemoteDevice(localBluetoothDevice2.getAddress());
              BluetoothScanDevice.this.mFinishComposingListener.findDevice(BluetoothScanDevice.this.deviceTW);
              return;
              if (BluetoothScanDevice.this.iPairTimes == 0);
              for (BluetoothScanDevice.this.iPairTimes = 1; ; BluetoothScanDevice.this.iPairTimes = 0)
              {
                Log.i("picooc", "BlueToothTestActivity正在配对......" + localBluetoothDevice2.getName() + "  " + localBluetoothDevice2.getAddress());
                break;
              }
              Log.i("picooc", "完成配对" + localBluetoothDevice2.getName() + "  " + localBluetoothDevice2.getAddress());
              BluetoothScanDevice.this.iPairTimes = 0;
              continue;
              Log.i("picooc", "取消配对" + localBluetoothDevice2.getName() + "  " + localBluetoothDevice2.getAddress());
              if (BluetoothScanDevice.this.iPairTimes == 1)
                try
                {
                  Log.i("temp test", "second bond device...");
                  BluetoothClsUtils.createBond(localBluetoothDevice2.getClass(), localBluetoothDevice2);
                }
                catch (Exception localException2)
                {
                  localException2.printStackTrace();
                }
            }
          }
        }
        if (paramAnonymousIntent.getAction().equals("android.bluetooth.device.action.PAIRING_REQUEST"))
        {
          BluetoothDevice localBluetoothDevice1 = (BluetoothDevice)paramAnonymousIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
          try
          {
            BluetoothClsUtils.setPin(localBluetoothDevice1.getClass(), localBluetoothDevice1, "123");
            BluetoothClsUtils.createBond(localBluetoothDevice1.getClass(), localBluetoothDevice1);
            BluetoothClsUtils.cancelPairingUserInput(localBluetoothDevice1.getClass(), localBluetoothDevice1);
            return;
          }
          catch (Exception localException1)
          {
            localException1.printStackTrace();
            return;
          }
        }
        if ("android.bluetooth.device.action.ACL_CONNECTED".equals(str))
        {
          Log.i("temp test", "acl connected....");
          return;
        }
        if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(str))
        {
          Log.i("temp test", "acl disconnected....");
          return;
        }
      }
      while (!"android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED".equals(str));
      Log.i("temp test", "acl disconnect request....");
    }
  };

  @SuppressLint({"NewApi"})
  public BluetoothScanDevice(Context paramContext)
  {
    this.mContext = paramContext;
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.bluetooth.device.action.FOUND");
    localIntentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
    localIntentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
    localIntentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
    localIntentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
    localIntentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
    localIntentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED");
    this.mContext.registerReceiver(this.mReceiver, localIntentFilter);
    if (Build.VERSION.SDK_INT >= 18)
    {
      this.mBtAdapter = ((BluetoothManager)paramContext.getSystemService("bluetooth")).getAdapter();
      return;
    }
    this.mBtAdapter = BluetoothAdapter.getDefaultAdapter();
  }

  public void setonFindDeviceListener(scanDeviceListener paramscanDeviceListener)
  {
    this.mFinishComposingListener = paramscanDeviceListener;
  }

  public void unRegist()
  {
    this.mContext.unregisterReceiver(this.mReceiver);
  }

  public static abstract interface scanDeviceListener
  {
    public abstract void findDevice(BluetoothDevice paramBluetoothDevice);

    public abstract void scanOver();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BluetoothScanDevice
 * JD-Core Version:    0.6.2
 */
