package com.picooc.bluetooth;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.picooc.domain.BluetoothDeviceInfo;
import com.picooc.domain.BodyIndex;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BluetoothService extends Service
{
  public static final int METHOD_CONNECT = 12;
  public static final int METHOD_DISCONNECT = 13;
  private BluetoothAdapter mBluetoothAdapter = null;
  private final Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      case 3:
      default:
      case 1:
      case 2:
        String str2;
        do
        {
          byte[] arrayOfByte;
          do
          {
            int i;
            do
            {
              return;
              switch (paramAnonymousMessage.arg1)
              {
              case 2:
              default:
                return;
              case 3:
              }
              Log.i("picooc", "连接上了");
              return;
              arrayOfByte = (byte[])paramAnonymousMessage.obj;
              i = paramAnonymousMessage.arg1;
            }
            while (i <= 1);
            str2 = BluetoothUtils.bytesToHexString(arrayOfByte, i).toUpperCase();
            if (!str2.startsWith("30"))
              break;
            BluetoothService.this.sendMessage(new byte[] { -15, 3, 48 });
            if (str2.length() == 10)
              (Integer.parseInt(str2.substring(-4 + str2.length()), 16) / 10 / 2.0F);
          }
          while (arrayOfByte[2] != 0);
          BluetoothService.this.sendBasicMessage();
          return;
        }
        while ((str2.startsWith("F0")) || (!str2.startsWith("32")));
        Log.i("picooc", "接收到消息了");
        BluetoothService.this.sendMessage(new byte[] { -15, 3, 50 });
        BodyIndex localBodyIndex = new BodyIndex();
        localBodyIndex.setWeight(Integer.parseInt(str2.substring(4, 8), 16) / 10 / 2.0F);
        localBodyIndex.setBodyFat(Integer.parseInt(str2.substring(8, 12), 16) / 10.0F);
        localBodyIndex.setWater_race(Integer.parseInt(str2.substring(12, 16), 16) / 10.0F);
        localBodyIndex.setMusde_race(Integer.parseInt(str2.substring(16, 20), 16) / 10.0F);
        localBodyIndex.setBone_mass(Integer.parseInt(str2.substring(20, 24), 16) / 10.0F / 2.0F);
        localBodyIndex.setBasic_metabolism(Integer.parseInt(str2.substring(24, 28), 16));
        localBodyIndex.setViser_fat_level(Integer.parseInt(str2.substring(28, 30), 16));
        localBodyIndex.setBodyAge(Integer.parseInt(str2.substring(30, 32), 16));
        localBodyIndex.setBmi(localBodyIndex.getWeight(), 1.73F);
        Intent localIntent2 = new Intent("com.picooc.bluetooth.latin.broadcast");
        localIntent2.putExtra("BLUTTOOTH_STATE", 108);
        localIntent2.putExtra("LATIN_BODY_INDEX", localBodyIndex);
        BluetoothService.this.sendBroadcast(localIntent2);
        return;
      case 4:
      }
      String str1 = paramAnonymousMessage.getData().getString("device_name");
      Toast.makeText(BluetoothService.this.getApplicationContext(), "连接到 " + str1, 0).show();
      Intent localIntent1 = new Intent("com.picooc.bluetooth.latin.broadcast");
      localIntent1.putExtra("BLUTTOOTH_STATE", 105);
      BluetoothService.this.sendBroadcast(localIntent1);
    }
  };
  private LatinBluetooth mLatinService;
  private List<BluetoothDeviceInfo> mNewDevice;
  private List<BluetoothDeviceInfo> mPairdDevice;
  private final BroadcastReceiver mReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      String str = paramAnonymousIntent.getAction();
      if ("android.bluetooth.device.action.FOUND".equals(str))
      {
        BluetoothDevice localBluetoothDevice = (BluetoothDevice)paramAnonymousIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (localBluetoothDevice.getBondState() != 12)
        {
          BluetoothDeviceInfo localBluetoothDeviceInfo = new BluetoothDeviceInfo(localBluetoothDevice.getName(), localBluetoothDevice.getAddress());
          Log.i("picooc", "扫描到一个设备了" + localBluetoothDeviceInfo.toString());
          BluetoothService.this.mNewDevice.add(localBluetoothDeviceInfo);
          if (localBluetoothDeviceInfo.getDeviceName().equals("Dual-SPP"))
          {
            BluetoothService.this.connectDevice(localBluetoothDevice.getAddress(), false);
            if (BluetoothService.this.mBluetoothAdapter.isDiscovering())
              BluetoothService.this.mBluetoothAdapter.cancelDiscovery();
          }
        }
        return;
      }
      "android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(str);
    }
  };

  private void connectDevice(String paramString, boolean paramBoolean)
  {
    BluetoothDevice localBluetoothDevice = this.mBluetoothAdapter.getRemoteDevice(paramString);
    this.mLatinService.connect(localBluetoothDevice, paramBoolean);
  }

  private void doDiscovery()
  {
    if (this.mBluetoothAdapter.isDiscovering())
      this.mBluetoothAdapter.cancelDiscovery();
    this.mBluetoothAdapter.startDiscovery();
  }

  private void register()
  {
    IntentFilter localIntentFilter1 = new IntentFilter("android.bluetooth.device.action.FOUND");
    registerReceiver(this.mReceiver, localIntentFilter1);
    IntentFilter localIntentFilter2 = new IntentFilter("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
    registerReceiver(this.mReceiver, localIntentFilter2);
  }

  private void sendBasicMessage()
  {
    sendMessage(BluetoothUtils.hexStringToBytes("310601" + "01" + Integer.toHexString(170) + Integer.toHexString(26)));
  }

  private void sendMessage(String paramString)
  {
    if (this.mLatinService.getState() != 3)
      Toast.makeText(this, "你还没有连接设备", 0).show();
    while (paramString.length() <= 0)
      return;
    byte[] arrayOfByte = paramString.getBytes();
    this.mLatinService.write(arrayOfByte);
  }

  private void sendMessage(byte[] paramArrayOfByte)
  {
    if (this.mLatinService.getState() != 3)
      Toast.makeText(this, "你还没有连接设备", 0).show();
    while (paramArrayOfByte.length <= 0)
      return;
    this.mLatinService.write(paramArrayOfByte);
  }

  private void unregister()
  {
    if (this.mReceiver != null)
      unregisterReceiver(this.mReceiver);
  }

  public void cancleConnect(View paramView)
  {
    if (this.mLatinService != null)
      this.mLatinService.stop();
    if (this.mBluetoothAdapter.isDiscovering())
      this.mBluetoothAdapter.cancelDiscovery();
  }

  public void connect()
  {
    Set localSet = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
    int i = 1;
    Iterator localIterator;
    if (localSet.size() > 0)
      localIterator = localSet.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (i != 0)
          doDiscovery();
        return;
      }
      BluetoothDevice localBluetoothDevice = (BluetoothDevice)localIterator.next();
      if (localBluetoothDevice.getAddress().equals("8C:DE:52:2C:1D:64"))
      {
        connectDevice(localBluetoothDevice.getAddress(), true);
        i = 0;
      }
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    if (this.mLatinService == null)
      this.mLatinService = new LatinBluetooth(this, this.mHandler);
    if (this.mLatinService.getState() == 0)
      this.mLatinService.start();
    return new BluetoothBinder();
  }

  public void onCreate()
  {
    super.onCreate();
    Log.i("picooc", "服务开启了");
    this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    this.mNewDevice = new ArrayList();
    register();
  }

  public void onDestroy()
  {
    super.onDestroy();
    unregister();
    Log.i("picooc", "服务结束了");
    if (this.mLatinService != null)
      this.mLatinService.stop();
    if (this.mBluetoothAdapter.isDiscovering())
      this.mBluetoothAdapter.cancelDiscovery();
  }

  public boolean onUnbind(Intent paramIntent)
  {
    Log.i("picooc", "服务结束了");
    return super.onUnbind(paramIntent);
  }

  public class BluetoothBinder extends Binder
  {
    public BluetoothBinder()
    {
    }

    public BluetoothService getService()
    {
      return BluetoothService.this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    {
      switch (paramInt1)
      {
      case 13:
      default:
      case 12:
      }
      while (true)
      {
        return false;
        BluetoothService.this.connect();
      }
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BluetoothService
 * JD-Core Version:    0.6.2
 */
