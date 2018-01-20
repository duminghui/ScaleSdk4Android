package com.picooc.bluetoothscan;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.RoleBin;
import com.picooc.utils.SharedPreferenceUtils;

@SuppressLint({"NewApi", "DefaultLocale"})
public class PicoocBlueToothProfile
{
  private BTBle.NewDeviceScannedListener NewDeviceListener = new BTBle.NewDeviceScannedListener()
  {
    public void onDeviceFound(BluetoothDevice paramAnonymousBluetoothDevice)
    {
      Log.i("temp test", "scanned device to pair......");
      PicoocBlueToothProfile.this.btStandard.directConnect(paramAnonymousBluetoothDevice);
    }
  };
  private BTBle btBle = null;
  private BTStandard btStandard = null;
  private boolean isSupportBle = false;
  private BluetoothAdapter mBtAdapter;
  private Context mContext;
  private Handler mHandler;

  public PicoocBlueToothProfile(Context paramContext, Handler paramHandler, BluetoothAdapter paramBluetoothAdapter)
  {
    this.mBtAdapter = paramBluetoothAdapter;
    this.mContext = paramContext;
    this.mHandler = paramHandler;
    if (paramContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le"))
    {
      this.isSupportBle = true;
      SharedPreferenceUtils.removeKey(paramContext, "picooc.com", "Dual-SPP");
    }
    while (true)
    {
      Log.i("whatlong3", "是否支持低功耗，isSupportBle=" + this.isSupportBle);
      if (this.isSupportBle)
        break;
      this.btStandard = new BTStandard(paramContext, paramHandler, this.mBtAdapter);
      return;
      this.isSupportBle = false;
    }
    this.btBle = new BTBle(paramContext, paramHandler, this.mBtAdapter);
  }

  public static BodyIndex changeDataToBodyIndex(String paramString, RoleBin paramRoleBin)
  {
    BodyIndex localBodyIndex = new BodyIndex();
    if (paramString.length() >= 32)
    {
      float f = Integer.parseInt(paramString.substring(4, 8), 16) / 10.0F / 2.0F;
      localBodyIndex.setWeight(f);
      localBodyIndex.setBodyFat(Integer.parseInt(paramString.substring(8, 12), 16) / 10.0F);
      if ((paramString.length() > 32) && (paramString.length() < 50))
      {
        int i = Integer.parseInt(paramString.substring(32, 36), 16);
        localBodyIndex.setBodyBottomFat(localBodyIndex.getBodyFat());
        localBodyIndex.setBodyFat(i / 10.0F);
      }
      if (localBodyIndex.getBodyFat() > 0.0F)
      {
        localBodyIndex.setWater_race(Integer.parseInt(paramString.substring(12, 16), 16) / 10.0F);
        localBodyIndex.setBone_mass(Integer.parseInt(paramString.substring(20, 24), 16) / 10.0F / 2.0F);
        localBodyIndex.setMusde_race(100.0F * ((f - f * localBodyIndex.getBodyFat() / 100.0F - localBodyIndex.getBone_mass()) / f));
        localBodyIndex.setBasic_metabolism(370.0F + 21.6F * (f - f * localBodyIndex.getBodyFat() / 100.0F));
        localBodyIndex.setViser_fat_level(Integer.parseInt(paramString.substring(28, 30), 16));
        localBodyIndex.setBodyAge(Integer.parseInt(paramString.substring(30, 32), 16));
        localBodyIndex.setProtein_race(Math.abs(localBodyIndex.getMusde_race() - localBodyIndex.getWater_race()));
      }
      localBodyIndex.setTime(System.currentTimeMillis());
      localBodyIndex.setRole_id(paramRoleBin.getRole_id());
      localBodyIndex.setBmi(localBodyIndex.getWeight(), paramRoleBin.getHeight() / 100.0F);
    }
    return localBodyIndex;
  }

  public void destory()
  {
    if (this.btBle != null)
      this.btBle.destory();
    if (this.btStandard != null)
      this.btStandard.destory();
  }

  public void startScanOrConnect()
  {
    if (this.isSupportBle)
    {
      this.btBle.setonFindNewDeviceListener(this.NewDeviceListener);
      this.btBle.scanLeDevice(true);
      return;
    }
    this.btStandard.startScanOrConnect();
  }

  public void stop()
  {
    if (this.btBle != null)
      this.btBle.stop();
    if (this.btStandard != null)
      this.btStandard.stop();
  }

  public void stopScan()
  {
    if (!this.isSupportBle)
      this.btStandard.stopScan();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PicoocBlueToothProfile
 * JD-Core Version:    0.6.2
 */
