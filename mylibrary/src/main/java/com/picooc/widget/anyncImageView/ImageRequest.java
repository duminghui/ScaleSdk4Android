package com.picooc.widget.anyncImageView;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.concurrent.Future;

public class ImageRequest
{
  private static ImageLoader sImageLoader;
  private ImageProcessor mBitmapProcessor;
  private ImageRequestCallback mCallback;
  private Future<?> mFuture;
  private BitmapFactory.Options mOptions;
  private String mUrl;

  public ImageRequest(String paramString, ImageRequestCallback paramImageRequestCallback)
  {
    this(paramString, paramImageRequestCallback, null);
  }

  public ImageRequest(String paramString, ImageRequestCallback paramImageRequestCallback, ImageProcessor paramImageProcessor)
  {
    this(paramString, paramImageRequestCallback, paramImageProcessor, null);
  }

  public ImageRequest(String paramString, ImageRequestCallback paramImageRequestCallback, ImageProcessor paramImageProcessor, BitmapFactory.Options paramOptions)
  {
    this.mUrl = paramString;
    this.mCallback = paramImageRequestCallback;
    this.mBitmapProcessor = paramImageProcessor;
    this.mOptions = paramOptions;
  }

  public void cancel()
  {
    if (!isCancelled())
    {
      this.mFuture.cancel(false);
      if (this.mCallback != null)
        this.mCallback.onImageRequestCancelled(this);
    }
  }

  public String getUrl()
  {
    return this.mUrl;
  }

  public final boolean isCancelled()
  {
    return this.mFuture.isCancelled();
  }

  public void load(Context paramContext)
  {
    if (this.mFuture == null)
    {
      if (sImageLoader == null)
        sImageLoader = new ImageLoader(paramContext);
      this.mFuture = sImageLoader.loadImage(this.mUrl, new InnerCallback(null), this.mBitmapProcessor, this.mOptions);
    }
  }

  public void setImageRequestCallback(ImageRequestCallback paramImageRequestCallback)
  {
    this.mCallback = paramImageRequestCallback;
  }

  public static abstract interface ImageRequestCallback
  {
    public abstract void onImageRequestCancelled(ImageRequest paramImageRequest);

    public abstract void onImageRequestEnded(ImageRequest paramImageRequest, Bitmap paramBitmap);

    public abstract void onImageRequestFailed(ImageRequest paramImageRequest, Throwable paramThrowable);

    public abstract void onImageRequestStarted(ImageRequest paramImageRequest);
  }

  private class InnerCallback
    implements ImageLoader.ImageLoaderCallback
  {
    private InnerCallback()
    {
    }

    public void onImageLoadingEnded(ImageLoader paramImageLoader, Bitmap paramBitmap)
    {
      if ((ImageRequest.this.mCallback != null) && (!ImageRequest.this.isCancelled()))
        ImageRequest.this.mCallback.onImageRequestEnded(ImageRequest.this, paramBitmap);
      ImageRequest.this.mFuture = null;
    }

    public void onImageLoadingFailed(ImageLoader paramImageLoader, Throwable paramThrowable)
    {
      if ((ImageRequest.this.mCallback != null) && (!ImageRequest.this.isCancelled()))
        ImageRequest.this.mCallback.onImageRequestFailed(ImageRequest.this, paramThrowable);
      ImageRequest.this.mFuture = null;
    }

    public void onImageLoadingStarted(ImageLoader paramImageLoader)
    {
      if (ImageRequest.this.mCallback != null)
        ImageRequest.this.mCallback.onImageRequestStarted(ImageRequest.this);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ImageRequest
 * JD-Core Version:    0.6.2
 */
