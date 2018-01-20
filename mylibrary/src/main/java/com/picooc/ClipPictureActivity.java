package com.picooc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.picooc.camera.ClipView;
import com.picooc.widget.loading.PicoocToast;
import java.io.ByteArrayOutputStream;
import org.achartengine.tools.ModUtils;

public class ClipPictureActivity extends Activity
  implements View.OnTouchListener
{
  static final int DRAG = 1;
  static final int NONE = 0;
  private static final String TAG = "11";
  static final int ZOOM = 2;
  Bitmap bm;
  Bitmap bmp;
  ClipView clipview;
  int heith;
  Matrix matrix = new Matrix();
  PointF mid = new PointF();
  int mode = 0;
  float oldDist = 1.0F;
  RelativeLayout relaView;
  Matrix savedMatrix = new Matrix();
  ImageView srcPic;
  PointF start = new PointF();
  int statusBarHeight = 0;
  Button sure;
  int titleBarHeight = 0;
  int witht;

  private void getBarHeight()
  {
    Rect localRect = new Rect();
    getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
    this.statusBarHeight = localRect.top;
    this.titleBarHeight = (getWindow().findViewById(16908290).getTop() - this.statusBarHeight);
    Log.v("11", "statusBarHeight = " + this.statusBarHeight + ", titleBarHeight = " + this.titleBarHeight);
  }

  private Bitmap getBitmap()
  {
    getBarHeight();
    Bitmap localBitmap = takeScreenShot();
    int i = this.clipview.getWidth();
    int j = this.clipview.getHeight();
    if (localBitmap == null)
      return null;
    return Bitmap.createBitmap(localBitmap, 6, (int)(1.0F + (j - ClipView.getNheight()) / 2.0F + this.statusBarHeight - 20.0F), -6 + (i - 5), (int)(2.0F + ClipView.getNheight()));
  }

  private void midPoint(PointF paramPointF, MotionEvent paramMotionEvent)
  {
    float f1 = paramMotionEvent.getX(0) + paramMotionEvent.getX(1);
    float f2 = paramMotionEvent.getY(0) + paramMotionEvent.getY(1);
    paramPointF.set(f1 / 2.0F, f2 / 2.0F);
  }

  private float spacing(MotionEvent paramMotionEvent)
  {
    float f1 = paramMotionEvent.getX(0) - paramMotionEvent.getX(1);
    float f2 = paramMotionEvent.getY(0) - paramMotionEvent.getY(1);
    return FloatMath.sqrt(f1 * f1 + f2 * f2);
  }

  private Bitmap takeScreenShot()
  {
    View localView = getWindow().getDecorView();
    localView.setDrawingCacheEnabled(true);
    localView.buildDrawingCache();
    return localView.getDrawingCache();
  }

  public void finish()
  {
    overridePendingTransition(2130968597, 2130968594);
    super.finish();
  }

  public void onClicktcancel(View paramView)
  {
    finish();
  }

  public void onClicktrue(View paramView)
  {
    Bitmap localBitmap = getBitmap();
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    if (localBitmap != null)
    {
      localBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localByteArrayOutputStream);
      byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
      Intent localIntent = new Intent();
      localIntent.putExtra("bitmap", arrayOfByte);
      setResult(ShareAct.CUTPIC, localIntent);
    }
    while (true)
    {
      finish();
      return;
      PicoocToast.showToast(this, "选取照片失败！");
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903061);
    this.srcPic = ((ImageView)findViewById(2131099773));
    this.srcPic.setOnTouchListener(this);
    this.sure = ((Button)findViewById(2131099775));
    String str = getIntent().getStringExtra("path");
    this.relaView = ((RelativeLayout)findViewById(2131099772));
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.heith = localDisplayMetrics.heightPixels;
    this.witht = localDisplayMetrics.widthPixels;
    this.clipview = new ClipView(this, getIntent().getFloatExtra("wight", 0.0F), getIntent().getFloatExtra("height", 0.0F));
    this.relaView.addView(this.clipview);
    this.bmp = ModUtils.convertToBitmap(str, this.heith, this.heith);
    if ((this.bm != null) && (!this.bm.isRecycled()))
    {
      this.bm.recycle();
      this.bm = null;
      System.gc();
    }
    if (this.bmp != null)
      this.srcPic.setImageBitmap(this.bmp);
    while (true)
    {
      new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
      {
        public void run()
        {
          ClipPictureActivity.this.srcPic.setScaleType(ImageView.ScaleType.MATRIX);
          if (ClipPictureActivity.this.bmp != null)
            ClipPictureActivity.this.matrix.setTranslate(ClipPictureActivity.this.srcPic.getWidth() / 2 - ClipPictureActivity.this.bmp.getWidth() / 2, ClipPictureActivity.this.srcPic.getHeight() / 2 - ClipPictureActivity.this.bmp.getHeight() / 2);
        }
      }
      , 300L);
      return;
      PicoocToast.showToast(this, "您选取的照片格式不正确，请从新选取");
      finish();
    }
  }

  protected void onDestroy()
  {
    if (this.bm != null)
      this.bm.recycle();
    this.bm = null;
    super.onDestroy();
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    ImageView localImageView = (ImageView)paramView;
    switch (0xFF & paramMotionEvent.getAction())
    {
    case 3:
    case 4:
    default:
    case 0:
    case 5:
    case 1:
    case 6:
    case 2:
    }
    while (true)
    {
      localImageView.setImageMatrix(this.matrix);
      return true;
      this.savedMatrix.set(this.matrix);
      this.start.set(paramMotionEvent.getX(), paramMotionEvent.getY());
      Log.d("11", "mode=DRAG");
      this.mode = 1;
      continue;
      this.oldDist = spacing(paramMotionEvent);
      Log.d("11", "oldDist=" + this.oldDist);
      if (this.oldDist > 10.0F)
      {
        this.savedMatrix.set(this.matrix);
        midPoint(this.mid, paramMotionEvent);
        this.mode = 2;
        Log.d("11", "mode=ZOOM");
        continue;
        this.mode = 0;
        Log.d("11", "mode=NONE");
        continue;
        if (this.mode == 1)
        {
          this.matrix.set(this.savedMatrix);
          this.matrix.postTranslate(paramMotionEvent.getX() - this.start.x, paramMotionEvent.getY() - this.start.y);
        }
        else if (this.mode == 2)
        {
          float f1 = spacing(paramMotionEvent);
          Log.d("11", "newDist=" + f1);
          if (f1 > 10.0F)
          {
            this.matrix.set(this.savedMatrix);
            float f2 = f1 / this.oldDist;
            this.matrix.postScale(f2, f2, this.mid.x, this.mid.y);
          }
        }
      }
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ClipPictureActivity
 * JD-Core Version:    0.6.2
 */
