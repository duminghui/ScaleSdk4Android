package com.picooc.bluetoothscan;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.picooc.MyApplication;
import com.picooc.domain.BodyIndex;

import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class BTStandard
{
  public static boolean DEVICE_CONNECTING = false;
  public static boolean DEVICE_PAIRING = false;

  @SuppressLint({"HandlerLeak"})
  private Handler Allhandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      BTStandard.this.mHandler.sendEmptyMessage(406);
      BTStandard.this.stop();
      super.handleMessage(paramAnonymousMessage);
    }
  };
  private BluetoothScanDevice ScanDevice = null;
  private MyApplication app;
  private BluetoothDevice deviceTW;
  private BluetoothScanDevice.scanDeviceListener findDeviceListener = new BluetoothScanDevice.scanDeviceListener()
  {
    public void findDevice(BluetoothDevice paramAnonymousBluetoothDevice)
    {
      if (BTStandard.this.mBtAdapter.isDiscovering())
        BTStandard.this.mBtAdapter.cancelDiscovery();
      BTStandard.this.deviceTW = paramAnonymousBluetoothDevice;
      if (BTStandard.this.deviceTW.getName().equals("Latin-S"))
        BTStandard.this.mHandler.sendEmptyMessage(9);
      do
      {
        return;
        if (BTStandard.this.deviceTW.getBondState() == 11)
        {
          BTStandard.DEVICE_PAIRING = true;
          BTStandard.this.mHandler.sendEmptyMessage(403);
          return;
        }
        if (BTStandard.this.deviceTW.getBondState() == 10)
        {
          BTStandard.DEVICE_PAIRING = false;
          Log.i("temp test", "pairing failed...");
          BTStandard.this.mHandler.sendEmptyMessage(6);
          return;
        }
        BTStandard.DEVICE_PAIRING = false;
        BTStandard.this.mHandler.sendEmptyMessage(403);
        BTStandard.this.mConnectThread.connect(BTStandard.this.deviceTW);
        BTStandard.this.mac = BTStandard.this.deviceTW.getAddress();
        BTStandard.DEVICE_CONNECTING = true;
      }
      while (BTStandard.this.timer == null);
      BTStandard.this.timer.cancel();
    }

    public void scanOver()
    {
      BTStandard.this.mHandler.sendEmptyMessage(401);
    }
  };
  private boolean isWeightingSuccess = false;
  private BluetoothAdapter mBtAdapter;
  private BluetoothConnect mConnectThread;
  private Context mContext;
  private Handler mHandler;
  private String mac;
  private final Handler mblueDataHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      case 3:
      default:
      case 1:
      case 2:
      case 4:
      case 6:
      case 7:
      case 1520:
      case 1643:
      }
      while (true)
      {
        return;
        switch (paramAnonymousMessage.arg1)
        {
        case 1:
        case 2:
        default:
          return;
        case 0:
          BTStandard.DEVICE_CONNECTING = false;
          return;
        case 3:
        }
        BTStandard.DEVICE_CONNECTING = false;
        return;
        byte[] arrayOfByte = (byte[])paramAnonymousMessage.obj;
        String str = BluetoothUtils.bytesToHexString(arrayOfByte, paramAnonymousMessage.arg1).toUpperCase();
        Log.i("picooc", "返回:" + str + "!");
        if (str.equals("42"))
        {
          arrayOfByte[0] = 50;
          str = "3212050E010E023201BF00320509052300FA";
        }
        if (arrayOfByte[0] == 48)
        {
          BTStandard.this.sendMessage(new byte[] { -15, 3, 48 });
          int i = str.length();
          float f = 0.0F;
          if (i == 10)
            f = Integer.parseInt(str.substring(-4 + str.length()), 16) / 10.0F / 2.0F;
          int j = arrayOfByte[2];
          if (j == 0)
          {
            Log.i("qianmo2", "最终体重:  " + str + "-->" + f + " kg");
            Message localMessage2 = new Message();
            localMessage2.what = 1520;
            sendMessageDelayed(localMessage2, 100L);
            return;
          }
          if (j == 1)
            Log.i("qianmo2", "实时体重: " + str + "-->" + f + " kg");
        }
        else
        {
          if (arrayOfByte[0] == 50)
          {
            BTStandard.this.sendMessage(new byte[] { -15, 3, 50 });
            Log.i("qianmo2", "测量成功:: " + str);
            BodyIndex localBodyIndex = PicoocBlueToothProfile.changeDataToBodyIndex(str, BTStandard.this.app.getCurrentRole());
            BTStandard.this.isWeightingSuccess = true;
            sendEmptyMessageDelayed(1643, 300L);
            localBodyIndex.setMac(BTStandard.this.mac);
            Message localMessage1 = new Message();
            localMessage1.obj = localBodyIndex;
            localMessage1.what = 772;
            BTStandard.this.mHandler.sendMessage(localMessage1);
            return;
          }
          if (arrayOfByte[0] == -16)
          {
            if (arrayOfByte[2] == 49)
            {
              Log.i("qianmo2", "基本数据发送成功:" + str);
              return;
            }
            if (arrayOfByte[2] == 53)
            {
              if ((arrayOfByte[3] != 1) && (arrayOfByte[3] == 2))
                Toast.makeText(BTStandard.this.mContext, "设置字符串成功", 0).show();
            }
            else if ((arrayOfByte[2] == 55) && (arrayOfByte[3] != 1))
            {
              arrayOfByte[3];
              return;
              BTStandard.DEVICE_CONNECTING = true;
              BTStandard.this.mHandler.sendEmptyMessage(405);
              return;
              BTStandard.this.mHandler.sendEmptyMessage(6);
              BluetoothUtils.savekey(Boolean.valueOf(false), BTStandard.this.mContext);
              BTStandard.this.isWeightingSuccess = false;
              BTStandard.DEVICE_CONNECTING = false;
              return;
              if (!BTStandard.this.isWeightingSuccess)
                BTStandard.this.mHandler.sendEmptyMessage(7);
              BTStandard.this.isWeightingSuccess = false;
              return;
              Log.i("picooc", "发送基本信息的时间:" + System.currentTimeMillis());
              BTStandard.this.sendBasicMessage();
              return;
              BluetoothUtils.savekey(Boolean.valueOf(true), BTStandard.this.mContext);
              if (BTStandard.this.deviceTW != null)
              {
                BluetoothUtils.saveadress(BTStandard.this.deviceTW.getName(), BTStandard.this.deviceTW.getAddress(), BTStandard.this.mContext.getApplicationContext());
                Log.i("whatlong3", "保存设备的地址=" + BTStandard.this.deviceTW.getAddress());
              }
              while (BTStandard.this.mConnectThread != null)
              {
                BTStandard.this.mConnectThread.stop();
                return;
                Log.i("whatlong3", "保存设备的地址=" + BTStandard.this.deviceTW);
              }
            }
          }
        }
      }
    }
  };
  private Timer timer;

  public BTStandard(Context paramContext, Handler paramHandler, BluetoothAdapter paramBluetoothAdapter)
  {
    this.mContext = paramContext;
    this.mHandler = paramHandler;
    this.mBtAdapter = paramBluetoothAdapter;
    this.app = ((MyApplication)paramContext.getApplicationContext());
    this.mConnectThread = new BluetoothConnect(paramContext, this.mblueDataHandler);
  }

  private BluetoothDevice getHistyDevice()
  {
    Set localSet = this.mBtAdapter.getBondedDevices();
    Log.i("temp test", "本地缓存的旧设备==" + BluetoothUtils.getadress(this.mContext, "Dual-SPP") + "  标记==" + BluetoothUtils.getkey(this.mContext));
    Log.i("temp test", "本地缓存的新设备==" + BluetoothUtils.getadress(this.mContext, "Latin") + "  标记==" + BluetoothUtils.getkey(this.mContext));
    Iterator localIterator;
    if (localSet.size() > 0)
      localIterator = localSet.iterator();
    BluetoothDevice localBluetoothDevice;
    do
    {
      if (!localIterator.hasNext())
        return null;
      localBluetoothDevice = (BluetoothDevice)localIterator.next();
      Log.i("whatlong3", "已经配对的设备====" + localBluetoothDevice.getName() + " address==" + localBluetoothDevice.getAddress());
    }
    while (((!localBluetoothDevice.getName().equals("Dual-SPP")) || (!localBluetoothDevice.getAddress().equals(BluetoothUtils.getadress(this.mContext, "Dual-SPP"))) || (!BluetoothUtils.getkey(this.mContext).booleanValue())) && ((!localBluetoothDevice.getName().equals("Latin")) || (!localBluetoothDevice.getAddress().equals(BluetoothUtils.getadress(this.mContext, "Latin"))) || (!BluetoothUtils.getkey(this.mContext).booleanValue())));
    return this.mBtAdapter.getRemoteDevice(localBluetoothDevice.getAddress());
  }

  private void scan()
  {
    if (this.mContext == null)
      this.mHandler.sendEmptyMessage(401);
    if (this.mBtAdapter.isDiscovering())
      this.mBtAdapter.cancelDiscovery();
    this.mBtAdapter.startDiscovery();
    this.timer = new Timer();
    this.timer.schedule(new TimerTask()
    {
      public void run()
      {
        BTStandard.this.Allhandler.sendEmptyMessage(1);
      }
    }
    , 20000L);
    Log.i("whatlong3", "开始去扫描了。。。。。。");
  }

  private void sendBasicMessage()
  {
    if (this.app.getCurrentRole().getSex() == 1);
    for (String str1 = "01"; ; str1 = "00")
    {
      int i = (int)((10.0F * this.app.getCurrentRole().getHeight() - 1000.0F) / 5.0F);
      int j = this.app.getCurrentRole().getAge();
      if (j < 18)
        j = 18;
      String str2 = Integer.toHexString(j);
      if (str2.length() == 1)
        str2 = "0" + str2;
      String str3 = "310601" + str1 + Integer.toHexString(i) + str2;
      Log.i("picooc", "sss =" + str3);
      sendMessage(BluetoothUtils.hexStringToBytes(str3));
      return;
    }
  }

  private void sendMessage(byte[] paramArrayOfByte)
  {
    if (this.mConnectThread.getState() != 3)
      Toast.makeText(this.mContext, "你还没有连接设备", 0).show();
    while (paramArrayOfByte.length <= 0)
      return;
    this.mConnectThread.write(paramArrayOfByte);
  }

  public void destory()
  {
    if (this.ScanDevice != null)
      this.ScanDevice.unRegist();
    if (this.mConnectThread != null)
      this.mConnectThread.stop();
  }

  public void directConnect(BluetoothDevice paramBluetoothDevice)
  {
    if (paramBluetoothDevice != null)
    {
      if (this.ScanDevice == null)
      {
        this.ScanDevice = new BluetoothScanDevice(this.mContext);
        this.ScanDevice.setonFindDeviceListener(this.findDeviceListener);
      }
      if (this.mBtAdapter.enable())
      {
        if (paramBluetoothDevice.getBondState() != 12)
          break label98;
        Log.i("picooc", "bonded:to connect");
        DEVICE_CONNECTING = true;
        this.mHandler.sendEmptyMessage(403);
        this.mConnectThread.connect(paramBluetoothDevice);
        this.mac = paramBluetoothDevice.getAddress();
      }
    }
    return;
    try
    {
      label98: Log.i("picooc", "no bond:to bond");
      DEVICE_PAIRING = true;
      BluetoothClsUtils.createBond(paramBluetoothDevice.getClass(), paramBluetoothDevice);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void startScanOrConnect()
  {
    if ((DEVICE_PAIRING) || (DEVICE_CONNECTING))
      Log.i("temp test", "pairing or connecting...");
    do
    {
      return;
      if (this.ScanDevice == null)
      {
        this.ScanDevice = new BluetoothScanDevice(this.mContext);
        this.ScanDevice.setonFindDeviceListener(this.findDeviceListener);
      }
      this.deviceTW = getHistyDevice();
      Log.i("whatlong3", "device====" + this.deviceTW);
      if (this.deviceTW == null)
        break;
    }
    while (!this.mBtAdapter.enable());
    this.mHandler.sendEmptyMessage(403);
    this.mConnectThread.connect(this.deviceTW);
    this.mac = this.deviceTW.getAddress();
    DEVICE_CONNECTING = true;
    return;
    scan();
  }

  public void stop()
  {
    if (this.mConnectThread != null)
      this.mConnectThread.stop();
  }

  public void stopScan()
  {
    if (this.ScanDevice != null)
      this.ScanDevice.unRegist();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BTStandard
 * JD-Core Version:    0.6.2
 */
