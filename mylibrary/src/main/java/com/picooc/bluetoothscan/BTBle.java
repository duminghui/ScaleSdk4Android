package com.picooc.bluetoothscan;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import com.picooc.MyApplication;
import com.picooc.arithmetic.ReportDirect;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.RoleBin;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"NewApi"})
public class BTBle
{
  public static final int DEVICE_DUAL_SPP = 1;
  public static final int DEVICE_LATIN_S = 2;
  private static final long SCAN_PERIOD = 15000L;
  private final MyApplication app;
  boolean flag = true;
  private boolean isConnected = false;
  private boolean isScannedDevice = false;
  private boolean isWeightingSuccess = false;
  private BluetoothLeService mBluetoothLeService;
  private final BluetoothAdapter mBtAdapter;
  private final Context mContext;
  private int mDevType = 0;
  private String mDeviceAddress;
  private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      String str = paramAnonymousIntent.getAction();
      Log.i("picooc", "ble广播，action=" + str);
      label102: Iterator localIterator1;
      if ("com.picooc.bluetooth.le.ACTION_GATT_CONNECTED".equals(str))
      {
        Log.e("whatlong3", "连接上了");
        BTBle.this.isConnected = true;
        BTBle.this.flag = true;
        Message localMessage = new Message();
        localMessage.arg1 = BTBle.this.mDevType;
        localMessage.what = 405;
        BTBle.this.mHandler.sendMessage(localMessage);
        return;
      }
      else
      {
        if ("com.picooc.bluetooth.le.ACTION_GATT_DISCONNECTED".equals(str))
        {
          if (!BTBle.this.isWeightingSuccess)
            if (BTBle.this.isConnected)
            {
              BTBle.this.mHandler.sendEmptyMessage(7);
              Log.e("whatlong1", "意外断开");
              BTBle.this.stop();
            }
          while (true)
          {
            BTBle.this.isConnected = false;
            BTBle.this.mContext.unregisterReceiver(this);
            return;
            BTBle.this.mHandler.sendEmptyMessage(6);
            break;
            Log.e("whatlong1", "正常断开");
            BTBle.this.mHandler.sendEmptyMessage(774);
          }
        }
        if (!"com.picooc.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED".equals(str))
          break label427;
        localIterator1 = BTBle.this.mBluetoothLeService.getSupportedGattServices().iterator();
      }
      while (true)
      {
        if (!localIterator1.hasNext())
          break label102;
        BluetoothGattService localBluetoothGattService = (BluetoothGattService)localIterator1.next();
        if (!localBluetoothGattService.getUuid().toString().startsWith("0000fff0"))
          break;
        List localList = localBluetoothGattService.getCharacteristics();
        Log.e("whatlong3", "扫描到服务了");
        Iterator localIterator2 = localList.iterator();
        while (localIterator2.hasNext())
        {
          BluetoothGattCharacteristic localBluetoothGattCharacteristic = (BluetoothGattCharacteristic)localIterator2.next();
          int i = localBluetoothGattCharacteristic.getProperties();
          if (localBluetoothGattCharacteristic.getUuid().toString().startsWith("0000fff2"))
            BTBle.this.mWrightCharacteristic = localBluetoothGattCharacteristic;
          if ((((i | 0x10) > 0) || ((i | 0x20) > 0)) && (localBluetoothGattCharacteristic.getUuid().toString().startsWith("0000fff1")))
          {
            BTBle.this.mNotifyCharacteristic = localBluetoothGattCharacteristic;
            BTBle.this.mBluetoothLeService.setCharacteristicNotification(localBluetoothGattCharacteristic, true, BTBle.this.mDevType);
          }
        }
      }
      label427: if ("com.picooc.bluetooth.le.ACTION_DATA_AVAILABLE".equals(str))
      {
        BTBle.this.displayData(paramAnonymousIntent.getByteArrayExtra("com.picooc.bluetooth.le.EXTRA_DATA"));
        return;
      }
      if ("com.picooc.bluetooth.le.error".equals(str))
      {
        BTBle.this.mHandler.sendEmptyMessage(8);
        return;
      }
      "write_ok".equals(str);
        BluetoothLeScanner scanner =  BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();

    }
  };
  private final Handler mHandler;
  private final BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback()
  {
    public void onLeScan(BluetoothDevice paramAnonymousBluetoothDevice, int paramAnonymousInt, byte[] paramAnonymousArrayOfByte)
    {
      Log.i("picooc", "设备名：" + paramAnonymousBluetoothDevice.getName() + " 设备地址=" + paramAnonymousBluetoothDevice.getAddress());
      if ((paramAnonymousBluetoothDevice.getName().equals("Dual-SPP")) || (paramAnonymousBluetoothDevice.getName().equals("Latin-S")))
      {
        if (!paramAnonymousBluetoothDevice.getName().equals("Dual-SPP"))
          break label276;
        Log.e("whatlong3", "扫描到一台Latin  " + paramAnonymousBluetoothDevice.getAddress());
      }
      for (BTBle.this.mDevType = 1; ; BTBle.this.mDevType = 2)
      {
        if (BTBle.this.mScanning)
        {
          BTBle.this.mBtAdapter.stopLeScan(BTBle.this.mLeScanCallback);
          BTBle.this.mScanning = false;
        }
        BTBle.this.isScannedDevice = true;
        BTBle.this.mHandler.sendEmptyMessage(403);
        BTBle.this.mContext.registerReceiver(BTBle.this.mGattUpdateReceiver, BTBle.access$11());
        BTBle.this.mDeviceAddress = paramAnonymousBluetoothDevice.getAddress();
        if (BTBle.this.mBluetoothLeService != null)
        {
          Log.e("whatlong3", "开始去连接！" + BTBle.this.mDeviceAddress);
          boolean bool = BTBle.this.mBluetoothLeService.connect(BTBle.this.mDeviceAddress);
          Log.d("whatlong3", "(连接请求结果)Connect request result=" + bool);
        }
        return;
        label276: Log.e("whatlong3", "扫描到一台Latin-S  " + paramAnonymousBluetoothDevice.getAddress());
      }
    }
  };
  private NewDeviceScannedListener mNewDeviceListener;
  private BluetoothGattCharacteristic mNotifyCharacteristic;
  private boolean mScanning;
  private final ServiceConnection mServiceConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      BTBle.this.mBluetoothLeService = ((BluetoothLeService.LocalBinder)paramAnonymousIBinder).getService();
      BTBle.this.mBluetoothLeService.initialize();
    }

    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      BTBle.this.mBluetoothLeService = null;
    }
  };
  private BluetoothGattCharacteristic mWrightCharacteristic;
  RoleBin role;
  private final Handler scanTimeOutHandler = new Handler();
  private final Runnable scanTimeOutRunnable = new Runnable()
  {
    public void run()
    {
      BTBle.this.mScanning = false;
      BTBle.this.mBtAdapter.stopLeScan(BTBle.this.mLeScanCallback);
      Log.e("bluetoothW", "扫描时间超过20秒，isConnected=" + BTBle.this.isConnected + "  isWeightingSuccess=" + BTBle.this.isWeightingSuccess);
      if ((!BTBle.this.isConnected) && (!BTBle.this.isWeightingSuccess))
        BTBle.this.mHandler.sendEmptyMessage(406);
    }
  };

  public BTBle(Context paramContext, Handler paramHandler, BluetoothAdapter paramBluetoothAdapter)
  {
    this.mContext = paramContext;
    this.mHandler = paramHandler;
    this.mBtAdapter = paramBluetoothAdapter;
    this.app = ((MyApplication)paramContext.getApplicationContext());
    paramContext.bindService(new Intent(paramContext, BluetoothLeService.class), this.mServiceConnection, 1);
  }

  private void displayData(byte[] paramArrayOfByte)
  {
    String str1 = BluetoothUtils.bytesToHexString(paramArrayOfByte, paramArrayOfByte.length);
    if (paramArrayOfByte[0] == 48)
    {
      this.mWrightCharacteristic.setValue(new byte[] { -15, 3, 48 });
      this.mBluetoothLeService.wirteCharacteristic(this.mWrightCharacteristic);
      if ((paramArrayOfByte[2] == 0) && (this.mDevType == 1))
      {
        Handler localHandler = new Handler();
        Runnable local6 = new Runnable()
        {
          public void run()
          {
            int i = BTBle.this.app.getCurrentRole().getSex();
            int j = BTBle.this.app.getCurrentRole().getAge();
            float f = BTBle.this.app.getCurrentRole().getHeight();
            if (i == 1);
            for (String str1 = "01"; ; str1 = "00")
            {
              int k = (int)((10.0F * f - 1000.0F) / 5.0F);
              int m = j;
              if (m < 18)
                m = 18;
              String str2 = Integer.toHexString(m);
              if (str2.length() == 1)
                str2 = "0" + str2;
              String str3 = "310601" + str1 + Integer.toHexString(k) + str2;
              BTBle.this.mWrightCharacteristic.setValue(BluetoothUtils.hexStringToBytes(str3));
              BTBle.this.mBluetoothLeService.wirteCharacteristic(BTBle.this.mWrightCharacteristic);
              return;
            }
          }
        };
        localHandler.postDelayed(local6, 100L);
      }
    }
    long l1;
    float[] arrayOfFloat;
    do
    {
      do
      {
        return;
        if (paramArrayOfByte[0] == 50)
        {
          Log.e("whatlong3", "接收到最终数据了");
          this.mWrightCharacteristic.setValue(new byte[] { -15, 3, 50 });
          this.mBluetoothLeService.wirteCharacteristic(this.mWrightCharacteristic);
          if (this.flag)
          {
            this.flag = false;
            BodyIndex localBodyIndex2 = PicoocBlueToothProfile.changeDataToBodyIndex(str1, this.app.getCurrentRole());
            this.isWeightingSuccess = true;
            localBodyIndex2.setMac(this.mDeviceAddress);
            Message localMessage2 = new Message();
            localMessage2.obj = localBodyIndex2;
            localMessage2.what = 772;
            this.mHandler.sendMessage(localMessage2);
          }
          this.mContext.unregisterReceiver(this.mGattUpdateReceiver);
          return;
        }
        if (paramArrayOfByte[0] == 53)
        {
          Log.e("whatlong3", "接收到UTC校对请求");
          long l3 = System.currentTimeMillis() / 1000L;
          byte[] arrayOfByte4 = { -15, 7, 53, (byte)(int)((0xFF000000 & l3) >> 24), (byte)(int)((0xFF0000 & l3) >> 16), (byte)(int)((0xFF00 & l3) >> 8), (byte)(int)(0xFF & l3) };
          this.mWrightCharacteristic.setValue(arrayOfByte4);
          this.mBluetoothLeService.wirteCharacteristic(this.mWrightCharacteristic);
          String str6 = BluetoothUtils.bytesToHexString(arrayOfByte4, arrayOfByte4.length);
          Log.i("picooc", "sW = " + str6);
          return;
        }
        if (paramArrayOfByte[0] == 54)
        {
          Log.e("whatlong3", "接收到记忆数据");
          byte[] arrayOfByte3 = { -15, 3, 54 };
          this.mWrightCharacteristic.setValue(arrayOfByte3);
          this.mBluetoothLeService.wirteCharacteristic(this.mWrightCharacteristic);
          long l2 = (0xFF000000 & paramArrayOfByte[2] << 24) + (0xFF0000 & paramArrayOfByte[3] << 16) + (0xFF00 & paramArrayOfByte[4] << 8) + (0xFF & paramArrayOfByte[5]);
          SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
          String str4 = localSimpleDateFormat2.format(new Date(1000L * l2));
          float f2 = Math.round(10.0F * (((0xFF00 & paramArrayOfByte[6] << 8) + (0xFF & paramArrayOfByte[7])) / 20.0F)) / 10.0F;
          int j = ((0xFF00 & paramArrayOfByte[8] << 8) + (0xFF & paramArrayOfByte[9])) / 10;
          String str5 = "time:" + str4 + "\n" + "weight：" + Float.toString(f2) + "\n" + "imp:" + Integer.toString(j);
          Log.i("picooc", "memory data = " + str5);
          return;
        }
        if (paramArrayOfByte[0] == 55)
        {
          Log.e("whatlong3", "缓存数据发送完毕");
          byte[] arrayOfByte2 = { -15, 3, 55 };
          this.mWrightCharacteristic.setValue(arrayOfByte2);
          this.mBluetoothLeService.wirteCharacteristic(this.mWrightCharacteristic);
          return;
        }
      }
      while (paramArrayOfByte[0] != 57);
      Log.e("whatlong3", "接收到新数据");
      this.isWeightingSuccess = true;
      byte[] arrayOfByte1 = { -15, 3, 57 };
      this.mWrightCharacteristic.setValue(arrayOfByte1);
      this.mBluetoothLeService.wirteCharacteristic(this.mWrightCharacteristic);
      l1 = (0xFF000000 & paramArrayOfByte[2] << 24) + (0xFF0000 & paramArrayOfByte[3] << 16) + (0xFF00 & paramArrayOfByte[4] << 8) + (0xFF & paramArrayOfByte[5]);
      SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      String str2 = localSimpleDateFormat1.format(new Date(1000L * l1));
      float f1 = Math.round(10.0F * (((0xFF00 & paramArrayOfByte[6] << 8) + (0xFF & paramArrayOfByte[7])) / 20.0F)) / 10.0F;
      int i = ((0xFF00 & paramArrayOfByte[8] << 8) + (0xFF & paramArrayOfByte[9])) / 10;
      String str3 = "time:" + str2 + "\n" + "weight：" + Float.toString(f1) + "\n" + "imp:" + Integer.toString(i);
      Log.i("picooc", "current data = " + str3);
      this.role = this.app.getCurrentRole();
      arrayOfFloat = ReportDirect.calculateBasicDataByImpedanceOldVersion(this.role.getSex(), this.role.getHeight(), this.role.getAge(), f1, i);
      Log.i("picooc", "calculate result");
    }
    while (!this.flag);
    this.flag = false;
    BodyIndex localBodyIndex1 = getBodyIndex(arrayOfFloat, 1000L * l1);
    Log.i("picooc", "BodyIndex result");
    localBodyIndex1.setMac(this.mDeviceAddress);
    Message localMessage1 = new Message();
    localMessage1.obj = localBodyIndex1;
    localMessage1.what = 773;
    this.mHandler.sendMessage(localMessage1);
  }

  private static IntentFilter makeGattUpdateIntentFilter()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.picooc.bluetooth.le.ACTION_GATT_CONNECTED");
    localIntentFilter.addAction("com.picooc.bluetooth.le.ACTION_GATT_DISCONNECTED");
    localIntentFilter.addAction("com.picooc.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED");
    localIntentFilter.addAction("com.picooc.bluetooth.le.ACTION_DATA_AVAILABLE");
    localIntentFilter.addAction("com.picooc.bluetooth.le.error");
    return localIntentFilter;
  }

  public void destory()
  {
    this.mContext.unbindService(this.mServiceConnection);
    this.mBluetoothLeService = null;
  }

  public BodyIndex getBodyIndex(float[] paramArrayOfFloat, long paramLong)
  {
    BodyIndex localBodyIndex = new BodyIndex();
    if (paramArrayOfFloat == null)
      return localBodyIndex;
    localBodyIndex.setWeight(paramArrayOfFloat[0]);
    localBodyIndex.setBodyFat(paramArrayOfFloat[1]);
    localBodyIndex.setMusde_race(paramArrayOfFloat[2]);
    localBodyIndex.setBone_mass(paramArrayOfFloat[3]);
    localBodyIndex.setWater_race(paramArrayOfFloat[4]);
    localBodyIndex.setBasic_metabolism(Math.round(paramArrayOfFloat[5]));
    localBodyIndex.setProtein_race(paramArrayOfFloat[6]);
    localBodyIndex.setBmi(paramArrayOfFloat[7]);
    localBodyIndex.setViser_fat_level(Math.round(paramArrayOfFloat[8]));
    localBodyIndex.setTime(paramLong);
    localBodyIndex.setRole_id(this.role.getRole_id());
    return localBodyIndex;
  }

  public void scanLeDevice(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.isConnected = false;
      this.isWeightingSuccess = false;
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          BTBle.this.mScanning = false;
          Log.e("bluetoothW", "扫描时间超过20秒，isConnected=" + BTBle.this.isConnected + "  isWeightingSuccess=" + BTBle.this.isWeightingSuccess);
          if ((!BTBle.this.isScannedDevice) && (!BTBle.this.isConnected) && (!BTBle.this.isWeightingSuccess))
          {
            BTBle.this.mBtAdapter.stopLeScan(BTBle.this.mLeScanCallback);
            BTBle.this.mHandler.sendEmptyMessage(406);
          }
        }
      }
      , 15000L);
      Log.e("whatlong3", "开始去扫描");
      this.mScanning = true;
      this.mBtAdapter.startLeScan(this.mLeScanCallback);
      this.isScannedDevice = false;
      return;
    }
    Log.e("whatlong3", "停止扫描！");
    this.mScanning = false;
    this.mBtAdapter.stopLeScan(this.mLeScanCallback);
  }

  public void setonFindNewDeviceListener(NewDeviceScannedListener paramNewDeviceScannedListener)
  {
    this.mNewDeviceListener = paramNewDeviceScannedListener;
  }

  public void stop()
  {
    this.mBluetoothLeService.close();
  }

  public static abstract interface NewDeviceScannedListener
  {
    public abstract void onDeviceFound(BluetoothDevice paramBluetoothDevice);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BTBle
 * JD-Core Version:    0.6.2
 */
