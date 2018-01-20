package com.picooc.camera;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"NewApi"})
public class CameraPreview extends SurfaceView
  implements SurfaceHolder.Callback
{
  public static int HEIGHT = 0;
  private static final int MAXIMUM_SIZE = 1280;
  public static int WIDTH = 1024;
  boolean aa = true;
  boolean bb = true;
  private Camera camera;
  private int cameraPosition = 1;
  private String focusMode = "fixed";
  private SurfaceHolder holder = getHolder();
  private OnCameraStatusListener listener;
  private Context mContext;
  private int picFenBianLvW = 0;
  private Camera.PictureCallback pictureCallback = new Camera.PictureCallback()
  {
    public void onPictureTaken(byte[] paramAnonymousArrayOfByte, Camera paramAnonymousCamera)
    {
      paramAnonymousCamera.stopPreview();
      if (CameraPreview.this.listener != null)
        CameraPreview.this.listener.onCameraStopped(CameraPreview.this.setImageBitmap(CameraPreview.this.byte2Bitmap(paramAnonymousArrayOfByte)));
    }
  };
  private int screenHeight = 960;
  private int screenWidth = 720;

  public CameraPreview(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.holder.setType(3);
    this.holder.addCallback(this);
    this.mContext = paramContext;
    DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
    this.screenWidth = localDisplayMetrics.widthPixels;
    this.screenHeight = localDisplayMetrics.heightPixels;
  }

  private Bitmap byte2Bitmap(byte[] paramArrayOfByte)
  {
    return BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  private Camera.Size getOptimalPreviewSize(List<Camera.Size> paramList)
  {
    int i = 10000;
    Object localObject = (Camera.Size)paramList.get(0);
    Iterator localIterator = paramList.iterator();
    Camera.Size localSize;
    do
    {
      int j;
      do
      {
        do
        {
          if (!localIterator.hasNext())
            return localObject;
          localSize = (Camera.Size)localIterator.next();
        }
        while (localSize.height < this.screenWidth);
        j = Math.abs(this.screenWidth - localSize.height);
      }
      while (j > i);
      i = j;
      localObject = localSize;
    }
    while (localSize.width != this.screenHeight);
    return localObject;
  }

  private Camera.Size getOptimalPreviewSize(List<Camera.Size> paramList, Camera.Size paramSize)
  {
    Log.i("size", "WIDTH=" + WIDTH + "  HEIGHT=" + HEIGHT);
    int i = 10000;
    Object localObject = (Camera.Size)paramList.get(0);
    Iterator localIterator = paramList.iterator();
    Camera.Size localSize;
    do
    {
      int j;
      do
      {
        do
        {
          if (!localIterator.hasNext())
            return localObject;
          localSize = (Camera.Size)localIterator.next();
        }
        while (localSize.height < paramSize.height);
        j = Math.abs(paramSize.height - localSize.height);
      }
      while (j > i);
      i = j;
      localObject = localSize;
    }
    while (localSize.width != paramSize.width);
    return localObject;
  }

  private Camera.Size getOptimalPreviewSize2(List<Camera.Size> paramList, int paramInt1, int paramInt2)
  {
    double d1 = paramInt1 / paramInt2;
    if (paramList == null)
    {
      localObject = null;
      return localObject;
    }
    Object localObject = null;
    double d2 = 1.7976931348623157E+308D;
    Iterator localIterator1 = paramList.iterator();
    while (true)
    {
      if (!localIterator1.hasNext())
      {
        if (localObject != null)
          break;
        double d3 = 1.7976931348623157E+308D;
        Iterator localIterator2 = paramList.iterator();
        while (localIterator2.hasNext())
        {
          Camera.Size localSize2 = (Camera.Size)localIterator2.next();
          if (Math.abs(localSize2.height - paramInt2) < d3)
          {
            localObject = localSize2;
            d3 = Math.abs(localSize2.height - paramInt2);
          }
        }
        break;
      }
      Camera.Size localSize1 = (Camera.Size)localIterator1.next();
      if ((Math.abs(localSize1.width / localSize1.height - d1) <= 0.1D) && (Math.abs(localSize1.height - paramInt2) < d2))
      {
        localObject = localSize1;
        d2 = Math.abs(localSize1.height - paramInt2);
      }
    }
  }

  public static int getPreviewDegree(Activity paramActivity)
  {
    switch (paramActivity.getWindowManager().getDefaultDisplay().getRotation())
    {
    default:
      return 0;
    case 0:
      return 90;
    case 1:
      return 0;
    case 2:
      return 270;
    case 3:
    }
    return 180;
  }

  public void destory()
  {
    if (this.camera != null)
    {
      this.camera.stopPreview();
      this.camera.release();
      this.camera = null;
    }
  }

  public int getPicFenBianLvW()
  {
    return this.picFenBianLvW;
  }

  public void invitCamara(boolean paramBoolean)
  {
    if (this.camera == null)
      return;
    Camera.Parameters localParameters = this.camera.getParameters();
    while (true)
    {
      int i;
      try
      {
        this.camera.setPreviewDisplay(this.holder);
        if (getResources().getConfiguration().orientation != 2)
        {
          localParameters.set("orientation", "portrait");
          this.camera.setDisplayOrientation(90);
          localParameters.setPictureFormat(256);
          Camera.Size localSize = CameraSizeUtil.getSizeByWH(localParameters.getSupportedPreviewSizes(), localParameters.getSupportedPictureSizes(), this.screenHeight, this.screenWidth);
          Log.i("size", "预览大小，w=" + localSize.width + "  h=" + localSize.height + "  screenWidth=" + this.screenWidth + "     screenHeight=" + this.screenHeight);
          this.picFenBianLvW = localSize.height;
          localParameters.setPreviewSize(localSize.width, localSize.height);
          float f1 = this.screenHeight / this.screenWidth;
          float f2 = localSize.width / localSize.height;
          i = localSize.height;
          int j = localSize.width;
          if (i < this.screenWidth)
          {
            if (f2 <= f1)
              break label423;
            f3 = this.screenHeight / j;
            i = (int)(f3 * i);
            j = (int)(f3 * j);
          }
          Log.i("size", "sufaceWidth=" + i + "    surfaceHeight=" + j);
          RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
          localLayoutParams.addRule(13);
          localLayoutParams.height = j;
          localLayoutParams.width = i;
          setLayoutParams(localLayoutParams);
          localParameters.setPictureSize(localSize.width, localSize.height);
          this.camera.setParameters(localParameters);
          if (!paramBoolean)
            break;
          this.camera.startPreview();
          return;
        }
        localParameters.set("orientation", "landscape");
        this.camera.setDisplayOrientation(0);
        continue;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        this.camera.release();
        this.camera = null;
        Toast.makeText(this.mContext, "您的相机出现异常，请重启手机", 1).show();
        return;
      }
      label423: float f3 = this.screenWidth / i;
    }
  }

  public void invitCameraFirst()
  {
    if (this.camera != null);
    do
    {
      return;
      this.camera = Camera.open();
      try
      {
        this.camera.setPreviewDisplay(this.holder);
        this.camera.setDisplayOrientation(getPreviewDegree((Activity)this.mContext));
        return;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
    }
    while (this.camera == null);
    this.camera.stopPreview();
    this.camera.release();
    this.camera = null;
  }

  public void openLed()
  {
    if (this.aa)
    {
      this.aa = false;
      Log.i("picooc", "dakai led ");
      this.camera.getParameters();
      Camera.Parameters localParameters2 = this.camera.getParameters();
      localParameters2.setFlashMode("on");
      this.camera.setParameters(localParameters2);
      return;
    }
    Log.i("picooc", "guanbi led ");
    this.aa = true;
    this.camera.getParameters();
    Camera.Parameters localParameters1 = this.camera.getParameters();
    localParameters1.setFlashMode("off");
    this.camera.setParameters(localParameters1);
  }

  public Bitmap setImageBitmap(Bitmap paramBitmap)
  {
    Matrix localMatrix = new Matrix();
    localMatrix.setRotate(getPreviewDegree((Activity)this.mContext));
    return Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
  }

  public void setOnCameraStatusListener(OnCameraStatusListener paramOnCameraStatusListener)
  {
    this.listener = paramOnCameraStatusListener;
  }

  public void startPrivew()
  {
    Log.i("picooc", "startPrivew====" + this.camera);
    this.cameraPosition = 1;
    if (this.camera != null)
    {
      this.camera.startPreview();
      Log.i("picooc", "startPrivew2222222222222====" + this.camera);
      return;
    }
    Log.i("picooc", "startPrivew33333333333====" + this.camera);
    invitCameraFirst();
    invitCamara(true);
  }

  public void stopPreview()
  {
    if (this.camera != null)
      this.camera.stopPreview();
  }

  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
    WIDTH = paramInt2;
    HEIGHT = paramInt3;
    Log.i("picooc", "----w==" + paramInt2 + "===h==" + paramInt3);
    invitCamara(true);
  }

  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    invitCameraFirst();
    this.holder = paramSurfaceHolder;
  }

  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    if (this.camera != null)
    {
      this.camera.stopPreview();
      this.camera.release();
      this.camera = null;
    }
  }

  public void takePicture()
  {
    if (this.camera != null)
      this.camera.takePicture(null, null, this.pictureCallback);
  }

  public void translateCamera(ImageView paramImageView)
  {
    Camera.CameraInfo localCameraInfo = new Camera.CameraInfo();
    int i = Camera.getNumberOfCameras();
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      Camera.getCameraInfo(j, localCameraInfo);
      if (this.cameraPosition == 1)
      {
        if (localCameraInfo.facing != 1)
          continue;
        this.camera.stopPreview();
        this.camera.release();
        this.camera = null;
        this.camera = Camera.open(j);
        try
        {
          this.camera.setPreviewDisplay(this.holder);
          this.camera.setDisplayOrientation(getPreviewDegree((Activity)this.mContext));
          Camera.Parameters localParameters = this.camera.getParameters();
          localParameters.set("rotation", 180);
          this.camera.setParameters(localParameters);
          invitCamara(true);
          this.cameraPosition = 0;
          paramImageView.setVisibility(8);
          return;
        }
        catch (IOException localIOException2)
        {
          while (true)
          {
            localIOException2.printStackTrace();
            if (this.camera != null)
            {
              this.camera.stopPreview();
              this.camera.release();
              this.camera = null;
            }
          }
        }
      }
      if (localCameraInfo.facing == 0)
      {
        this.camera.stopPreview();
        this.camera.release();
        this.camera = null;
        this.camera = Camera.open(j);
        try
        {
          this.camera.setPreviewDisplay(this.holder);
          this.camera.setDisplayOrientation(getPreviewDegree((Activity)this.mContext));
          invitCamara(true);
          this.cameraPosition = 1;
          paramImageView.setVisibility(0);
          return;
        }
        catch (IOException localIOException1)
        {
          while (true)
          {
            localIOException1.printStackTrace();
            if (this.camera != null)
            {
              this.camera.stopPreview();
              this.camera.release();
              this.camera = null;
            }
          }
        }
      }
    }
  }

  public static final class ComparatorValues
    implements Comparator<Camera.Size>
  {
    public int compare(Camera.Size paramSize1, Camera.Size paramSize2)
    {
      int i = paramSize1.height;
      int j = paramSize2.height;
      int k = 0;
      if (i > j)
        k = 1;
      if (i < j)
        k = -1;
      return k;
    }
  }

  public static abstract interface OnCameraStatusListener
  {
    public abstract void onAutoFocus(boolean paramBoolean);

    public abstract void onCameraStopped(Bitmap paramBitmap);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     CameraPreview
 * JD-Core Version:    0.6.2
 */
