package com.picooc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.sony.tv.CameraManager;
import com.picooc.sony.tv.InactivityTimer;
import com.picooc.sony.tv.SonyTVActHandler;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import com.tencent.mm.sdk.platformtools.Log;
import java.io.IOException;
import org.json.JSONObject;

public class SonyTVAct extends Activity
  implements SurfaceHolder.Callback
{
  private static final float BEEP_VOLUME = 0.5F;
  private static final long VIBRATE_DURATION = 200L;
  ScaleAnimation animation;
  private MyApplication app;
  private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener()
  {
    public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
    {
      paramAnonymousMediaPlayer.seekTo(0);
    }
  };
  private RelativeLayout capture_crop_layout;
  private int cropHeight = 0;
  private int cropWidth = 0;
  boolean flag = true;
  private SonyTVActHandler handler;
  private boolean hasSurface;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(SonyTVAct.this, paramAnonymousString);
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          if (SonyTVAct.this.handler != null)
            SonyTVAct.this.handler.sendEmptyMessage(2131099665);
        }
      }
      , 5000L);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(SonyTVAct.this, "电视登录失败请重新扫描二维码!\n(" + localResponseEntity.getMessage() + ")");
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          if (SonyTVAct.this.handler != null)
          {
            SonyTVAct.this.mQrLineView.setVisibility(0);
            SonyTVAct.this.handler.sendEmptyMessage(2131099665);
            SonyTVAct.this.handler.StartSynchronously();
            SonyTVAct.this.mQrLineView.startAnimation(SonyTVAct.this.animation);
          }
        }
      }
      , 2000L);
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      Log.i("http", "成功了:" + localResponseEntity.toString());
      PicoocToast.showToast(SonyTVAct.this, "电视登录成功");
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          SonyTVAct.this.finish();
        }
      }
      , 2000L);
    }
  };
  private ImageView image_bg;
  private InactivityTimer inactivityTimer;
  private boolean isNeedCapture = false;
  private RelativeLayout mContainer = null;
  private RelativeLayout mCropLayout = null;
  BroadcastReceiver mNetworkStateIntentReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (paramAnonymousIntent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE"))
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)SonyTVAct.this.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localNetworkInfo == null) || (!localNetworkInfo.isAvailable()))
          break label133;
        if ((SonyTVAct.this.handler != null) && (SonyTVAct.this.wuwangRela.isShown()))
        {
          SonyTVAct.this.mQrLineView.setVisibility(0);
          SonyTVAct.this.handler.sendEmptyMessage(2131099665);
          SonyTVAct.this.handler.StartSynchronously();
          SonyTVAct.this.mQrLineView.startAnimation(SonyTVAct.this.animation);
          SonyTVAct.this.wuwangRela.setVisibility(8);
          SonyTVAct.this.playBeepSoundAndVibrate();
        }
      }
      label133: 
      while ((SonyTVAct.this.handler == null) || (SonyTVAct.this.wuwangRela.isShown()))
        return;
      SonyTVAct.this.mQrLineView.clearAnimation();
      SonyTVAct.this.wuwangRela.setVisibility(0);
      SonyTVAct.this.image_bg.setVisibility(0);
      SonyTVAct.this.mQrLineView.setVisibility(8);
      SonyTVAct.this.inactivityTimer.onActivity();
      SonyTVAct.this.handler.quitSynchronously();
      SonyTVAct.this.playBeepSoundAndVibrate();
    }
  };
  ImageView mQrLineView;
  private MediaPlayer mediaPlayer;
  private boolean playBeep;
  private ImageView top_mask;
  private boolean vibrate;
  private RelativeLayout wuwangRela;
  private int x = 0;
  private int y = 0;

  private void initBeepSound()
  {
    AssetFileDescriptor localAssetFileDescriptor;
    if ((this.playBeep) && (this.mediaPlayer == null))
    {
      setVolumeControlStream(3);
      this.mediaPlayer = new MediaPlayer();
      this.mediaPlayer.setAudioStreamType(3);
      this.mediaPlayer.setOnCompletionListener(this.beepListener);
      localAssetFileDescriptor = getResources().openRawResourceFd(2131034112);
    }
    try
    {
      this.mediaPlayer.setDataSource(localAssetFileDescriptor.getFileDescriptor(), localAssetFileDescriptor.getStartOffset(), localAssetFileDescriptor.getLength());
      localAssetFileDescriptor.close();
      this.mediaPlayer.setVolume(0.5F, 0.5F);
      this.mediaPlayer.prepare();
      return;
    }
    catch (IOException localIOException)
    {
      this.mediaPlayer = null;
    }
  }

  private void initCamera(SurfaceHolder paramSurfaceHolder)
  {
    try
    {
      CameraManager.get().openDriver(paramSurfaceHolder);
      Point localPoint = CameraManager.get().getCameraResolution();
      int i = localPoint.y;
      int j = localPoint.x;
      int k = i * this.mCropLayout.getLeft() / this.mContainer.getWidth();
      int m = j * this.mCropLayout.getTop() / this.mContainer.getHeight();
      int n = i * this.mCropLayout.getWidth() / this.mContainer.getWidth();
      int i1 = j * this.mCropLayout.getHeight() / this.mContainer.getHeight();
      setX(k);
      setY(m);
      setCropWidth(n);
      setCropHeight(i1);
      if (this.handler == null)
        this.handler = new SonyTVActHandler(this);
      return;
    }
    catch (IOException localIOException)
    {
    }
    catch (RuntimeException localRuntimeException)
    {
    }
  }

  private void playBeepSoundAndVibrate()
  {
    if ((this.playBeep) && (this.mediaPlayer != null))
      this.mediaPlayer.start();
    if (this.vibrate)
      ((Vibrator)getSystemService("vibrator")).vibrate(200L);
  }

  private void startLogin(String paramString)
  {
    this.mQrLineView.setVisibility(8);
    this.handler.quitSynchronously();
    this.mQrLineView.clearAnimation();
    PicoocLoading.showLoadingDialog(this);
    RequestEntity localRequestEntity = new RequestEntity(HttpUtils.psony_tv_interface, "5.1");
    localRequestEntity.addParam("token", paramString);
    localRequestEntity.addParam("user_id", Long.valueOf(this.app.getCurrentUser().getUser_id()));
    HttpUtils.getJson(this, localRequestEntity, this.httpHandler, HttpUtils.url_suoni);
  }

  public int getCropHeight()
  {
    return this.cropHeight;
  }

  public int getCropWidth()
  {
    return this.cropWidth;
  }

  public Handler getHandler()
  {
    return this.handler;
  }

  public int getX()
  {
    return this.x;
  }

  public int getY()
  {
    return this.y;
  }

  public void handleDecode(String paramString)
  {
    this.inactivityTimer.onActivity();
    playBeepSoundAndVibrate();
    startLogin(paramString);
  }

  public boolean isNeedCapture()
  {
    return this.isNeedCapture;
  }

  public void leftonClick(View paramView)
  {
    finish();
  }

  protected void light()
  {
    if (this.flag)
    {
      this.flag = false;
      CameraManager.get().openLight();
      return;
    }
    this.flag = true;
    CameraManager.get().offLight();
  }

  @SuppressLint({"CutPasteId"})
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903131);
    CameraManager.init(getApplication());
    this.hasSurface = false;
    this.inactivityTimer = new InactivityTimer(this);
    this.app = ((MyApplication)getApplication());
    this.mContainer = ((RelativeLayout)findViewById(2131100085));
    this.mCropLayout = ((RelativeLayout)findViewById(2131100088));
    this.capture_crop_layout = ((RelativeLayout)findViewById(2131100088));
    this.top_mask = ((ImageView)findViewById(2131100087));
    this.wuwangRela = ((RelativeLayout)findViewById(2131100097));
    this.image_bg = ((ImageView)findViewById(2131100090));
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int i = localDisplayMetrics.widthPixels;
    int j = localDisplayMetrics.heightPixels;
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams((int)(i * 411 / 641.0F), (int)(j * 399 / 1135.0F));
    localLayoutParams1.addRule(3, 2131100087);
    localLayoutParams1.addRule(14);
    this.capture_crop_layout.setLayoutParams(localLayoutParams1);
    this.wuwangRela.setLayoutParams(localLayoutParams1);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(i, (int)(j * 254 / 1135.0F));
    localLayoutParams2.addRule(10);
    this.top_mask.setLayoutParams(localLayoutParams2);
    this.mQrLineView = ((ImageView)findViewById(2131100089));
    this.animation = new ScaleAnimation(1.0F, 1.0F, 0.0F, 1.0F);
    this.animation.setRepeatCount(-1);
    this.animation.setRepeatMode(1);
    this.animation.setInterpolator(new LinearInterpolator());
    this.animation.setDuration(2200L);
    this.mQrLineView.startAnimation(this.animation);
    IntentFilter localIntentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    registerReceiver(this.mNetworkStateIntentReceiver, localIntentFilter);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        if (!HttpUtils.isNetworkConnected(SonyTVAct.this))
        {
          SonyTVAct.this.wuwangRela.setVisibility(0);
          SonyTVAct.this.image_bg.setVisibility(0);
          SonyTVAct.this.mQrLineView.clearAnimation();
          SonyTVAct.this.mQrLineView.setVisibility(8);
          SonyTVAct.this.playBeepSoundAndVibrate();
          SonyTVAct.this.inactivityTimer.onActivity();
          if (SonyTVAct.this.handler != null)
            SonyTVAct.this.handler.quitSynchronously();
        }
      }
    }
    , 1000L);
  }

  protected void onDestroy()
  {
    this.inactivityTimer.shutdown();
    unregisterReceiver(this.mNetworkStateIntentReceiver);
    super.onDestroy();
  }

  protected void onPause()
  {
    super.onPause();
    if (this.handler != null)
    {
      this.handler.quitSynchronously();
      this.handler = null;
    }
    CameraManager.get().closeDriver();
  }

  protected void onResume()
  {
    super.onResume();
    SurfaceHolder localSurfaceHolder = ((SurfaceView)findViewById(2131100086)).getHolder();
    if (this.hasSurface)
      initCamera(localSurfaceHolder);
    while (true)
    {
      this.playBeep = true;
      if (((AudioManager)getSystemService("audio")).getRingerMode() != 2)
        this.playBeep = false;
      initBeepSound();
      this.vibrate = true;
      return;
      localSurfaceHolder.addCallback(this);
      localSurfaceHolder.setType(3);
    }
  }

  public void setCropHeight(int paramInt)
  {
    this.cropHeight = paramInt;
  }

  public void setCropWidth(int paramInt)
  {
    this.cropWidth = paramInt;
  }

  public void setNeedCapture(boolean paramBoolean)
  {
    this.isNeedCapture = paramBoolean;
  }

  public void setX(int paramInt)
  {
    this.x = paramInt;
  }

  public void setY(int paramInt)
  {
    this.y = paramInt;
  }

  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    if (!this.hasSurface)
    {
      this.hasSurface = true;
      initCamera(paramSurfaceHolder);
    }
  }

  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    this.hasSurface = false;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SonyTVAct
 * JD-Core Version:    0.6.2
 */
