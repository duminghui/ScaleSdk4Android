package com.picooc.widget.anyncImageView;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import org.achartengine.tools.ModUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

public class ImageLoader
{
  private static final String LOG_TAG = ImageLoader.class.getSimpleName();
  private static final int ON_END = 258;
  private static final int ON_FAIL = 257;
  private static final int ON_START = 256;
  private static AssetManager sAssetManager;
  private static BitmapFactory.Options sDefaultOptions;
  private static ExecutorService sExecutor;
  private static ImageCache sImageCache;

  public ImageLoader(Context paramContext)
  {
    if (sImageCache == null)
      sImageCache = GDUtils.getImageCache(paramContext);
    if (sExecutor == null)
      sExecutor = GDUtils.getExecutor(paramContext);
    if (sDefaultOptions == null)
    {
      sDefaultOptions = new BitmapFactory.Options();
      sDefaultOptions.inDither = true;
      sDefaultOptions.inScaled = true;
      sDefaultOptions.inDensity = 160;
      sDefaultOptions.inTargetDensity = paramContext.getResources().getDisplayMetrics().densityDpi;
    }
    sAssetManager = paramContext.getAssets();
  }

  public Future<?> loadImage(String paramString, ImageLoaderCallback paramImageLoaderCallback)
  {
    return loadImage(paramString, paramImageLoaderCallback, null);
  }

  public Future<?> loadImage(String paramString, ImageLoaderCallback paramImageLoaderCallback, ImageProcessor paramImageProcessor)
  {
    return loadImage(paramString, paramImageLoaderCallback, paramImageProcessor, null);
  }

  public Future<?> loadImage(String paramString, ImageLoaderCallback paramImageLoaderCallback, ImageProcessor paramImageProcessor, BitmapFactory.Options paramOptions)
  {
    return sExecutor.submit(new ImageFetcher(paramString, paramImageLoaderCallback, paramImageProcessor, paramOptions));
  }

  private class ImageFetcher
    implements Runnable
  {
    private ImageProcessor mBitmapProcessor;
    private ImageHandler mHandler;
    private BitmapFactory.Options mOptions;
    private String mUrl;

    public ImageFetcher(String paramImageLoaderCallback, ImageLoaderCallback paramImageProcessor, ImageProcessor paramOptions, BitmapFactory.Options arg5)
    {
      this.mUrl = paramImageLoaderCallback;
      this.mHandler = new ImageHandler(ImageLoader.this, paramImageLoaderCallback, paramImageProcessor, null);
      this.mBitmapProcessor = paramOptions;
      Object localObject;
      this.mOptions = localObject;
    }

    public void run()
    {
      java.lang.Process.setThreadPriority(10);
      ImageHandler localImageHandler = this.mHandler;
      Object localObject = null;
      localImageHandler.sendMessage(Message.obtain(localImageHandler, 256));
      try
      {
        boolean bool1 = TextUtils.isEmpty(this.mUrl);
        localObject = null;
        if (bool1)
          throw new Exception("The given URL cannot be null or empty");
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        Exception localException2 = localException1;
        if (localObject == null)
        {
          if (localException2 == null)
            localException2 = new Exception("Skia image decoding failed");
          localImageHandler.sendMessage(Message.obtain(localImageHandler, 257, localException2));
          return;
          String str = this.mUrl.substring(1 + this.mUrl.lastIndexOf('/'));
          File localFile1 = new File(Environment.getExternalStorageDirectory().getPath() + "/picooc/head/" + str);
          if (!str.endsWith("png"))
          {
            boolean bool4 = str.endsWith("jpg");
            localObject = null;
            if (!bool4);
          }
          else
          {
            boolean bool2 = Environment.getExternalStorageState().equals("mounted");
            localObject = null;
            if (bool2)
            {
              boolean bool3 = localFile1.exists();
              localObject = null;
              if (bool3)
              {
                FileInputStream localFileInputStream = new FileInputStream(localFile1);
                localObject = BitmapFactory.decodeFileDescriptor(localFileInputStream.getFD(), null, null);
                localFileInputStream.close();
              }
            }
          }
          BufferedInputStream localBufferedInputStream;
          if (localObject == null)
          {
            HttpGet localHttpGet = new HttpGet(this.mUrl);
            DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
            BasicHttpParams localBasicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 5000);
            HttpConnectionParams.setSoTimeout(localBasicHttpParams, 5000);
            localHttpGet.setParams(localBasicHttpParams);
            localBufferedInputStream = new BufferedInputStream(new BufferedHttpEntity(localDefaultHttpClient.execute(localHttpGet).getEntity()).getContent());
            if (this.mOptions != null)
              break label516;
          }
          label516: for (BitmapFactory.Options localOptions = ImageLoader.sDefaultOptions; ; localOptions = this.mOptions)
          {
            localObject = BitmapFactory.decodeStream(localBufferedInputStream, null, localOptions);
            localBufferedInputStream.close();
            File localFile2 = new File(Environment.getExternalStorageDirectory().getPath() + "/picooc/head/");
            if (!localFile2.exists())
              localFile2.mkdirs();
            if ((localFile1.getPath().endsWith(".png")) || (localFile1.getPath().endsWith(".jpg")) || (localFile1.getPath().endsWith(".jpeg")))
            {
              FileOutputStream localFileOutputStream = new FileOutputStream(localFile1);
              ((Bitmap)localObject).compress(Bitmap.CompressFormat.JPEG, 50, localFileOutputStream);
              localFileOutputStream.close();
            }
            localBufferedInputStream.close();
            if ((this.mBitmapProcessor != null) && (localObject != null))
            {
              Bitmap localBitmap = this.mBitmapProcessor.processImage((Bitmap)localObject);
              if (localBitmap != null)
                localObject = localBitmap;
            }
            localObject = ModUtils.toRoundBitmap((Bitmap)localObject);
            localException2 = null;
            break;
          }
        }
        localImageHandler.sendMessage(Message.obtain(localImageHandler, 258, localObject));
      }
    }
  }

  private class ImageHandler extends Handler
  {
    private ImageLoaderCallback mCallback;
    private String mUrl;

    private ImageHandler(String paramImageLoaderCallback, ImageLoaderCallback arg3)
    {
      this.mUrl = paramImageLoaderCallback;
      Object localObject;
      this.mCallback = localObject;
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        super.handleMessage(paramMessage);
      case 256:
      case 257:
      case 258:
      }
      Bitmap localBitmap;
      do
      {
        do
        {
          do
            return;
          while (this.mCallback == null);
          this.mCallback.onImageLoadingStarted(ImageLoader.this);
          return;
        }
        while (this.mCallback == null);
        this.mCallback.onImageLoadingFailed(ImageLoader.this, (Throwable)paramMessage.obj);
        return;
        localBitmap = (Bitmap)paramMessage.obj;
        ImageLoader.sImageCache.put(this.mUrl, localBitmap);
      }
      while (this.mCallback == null);
      this.mCallback.onImageLoadingEnded(ImageLoader.this, localBitmap);
    }
  }

  public static abstract interface ImageLoaderCallback
  {
    public abstract void onImageLoadingEnded(ImageLoader paramImageLoader, Bitmap paramBitmap);

    public abstract void onImageLoadingFailed(ImageLoader paramImageLoader, Throwable paramThrowable);

    public abstract void onImageLoadingStarted(ImageLoader paramImageLoader);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ImageLoader
 * JD-Core Version:    0.6.2
 */
