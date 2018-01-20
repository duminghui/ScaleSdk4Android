package com.picooc.widget.anyncImageView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AsyncImageView extends ImageView
  implements ImageRequest.ImageRequestCallback
{
  private static final int IMAGE_SOURCE_BITMAP = 2;
  private static final int IMAGE_SOURCE_DRAWABLE = 1;
  private static final int IMAGE_SOURCE_RESOURCE = 0;
  private static final int IMAGE_SOURCE_UNKNOWN = -1;
  private static final String LOG_TAG = AsyncImageView.class.getSimpleName();
  private Bitmap mBitmap;
  private Bitmap mDefaultBitmap;
  private Drawable mDefaultDrawable;
  private int mDefaultResId;
  private ImageProcessor mImageProcessor;
  private int mImageSource;
  private OnImageViewLoadListener mOnImageViewLoadListener;
  private BitmapFactory.Options mOptions;
  private boolean mPaused;
  private ImageRequest mRequest;
  private String mUrl;

  public AsyncImageView(Context paramContext)
  {
    this(paramContext, null);
  }

  public AsyncImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public AsyncImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initializeDefaultValues();
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AsyncImageView, paramInt, 0);
    Drawable localDrawable = localTypedArray.getDrawable(0);
    if (localDrawable != null)
      setDefaultImageDrawable(localDrawable);
    int i = localTypedArray.getInt(2, -1);
    if (i != -1)
      setInDensity(i);
    setUrl(localTypedArray.getString(1));
    localTypedArray.recycle();
  }

  private void initializeDefaultValues()
  {
    this.mImageSource = -1;
    this.mPaused = false;
  }

  private void setDefaultImage()
  {
    if (this.mBitmap == null);
    switch (this.mImageSource)
    {
    default:
      setImageDrawable(null);
      return;
    case 2:
      setImageBitmap(this.mDefaultBitmap);
      return;
    case 1:
      setImageDrawable(this.mDefaultDrawable);
      return;
    case 0:
    }
    setImageResource(this.mDefaultResId);
  }

  public boolean isLoaded()
  {
    return (this.mRequest == null) && (this.mBitmap != null);
  }

  public boolean isLoading()
  {
    return this.mRequest != null;
  }

  public void onImageRequestCancelled(ImageRequest paramImageRequest)
  {
    this.mRequest = null;
    if (this.mOnImageViewLoadListener != null)
      this.mOnImageViewLoadListener.onLoadingFailed(this, null);
  }

  public void onImageRequestEnded(ImageRequest paramImageRequest, Bitmap paramBitmap)
  {
    this.mBitmap = paramBitmap;
    setImageBitmap(paramBitmap);
    if (this.mOnImageViewLoadListener != null)
      this.mOnImageViewLoadListener.onLoadingEnded(this, paramBitmap);
    this.mRequest = null;
  }

  public void onImageRequestFailed(ImageRequest paramImageRequest, Throwable paramThrowable)
  {
    this.mRequest = null;
    if (this.mOnImageViewLoadListener != null)
      this.mOnImageViewLoadListener.onLoadingFailed(this, paramThrowable);
  }

  public void onImageRequestStarted(ImageRequest paramImageRequest)
  {
    if (this.mOnImageViewLoadListener != null)
      this.mOnImageViewLoadListener.onLoadingStarted(this);
  }

  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    SavedState localSavedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(localSavedState.getSuperState());
    setUrl(localSavedState.url);
  }

  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    localSavedState.url = this.mUrl;
    return localSavedState;
  }

  public void reload()
  {
    reload(false);
  }

  public void reload(boolean paramBoolean)
  {
    if ((this.mRequest == null) && (this.mUrl != null))
    {
      this.mBitmap = null;
      if (!paramBoolean)
        this.mBitmap = GDUtils.getImageCache(getContext()).get(this.mUrl);
      if (this.mBitmap != null)
        setImageBitmap(this.mBitmap);
    }
    else
    {
      return;
    }
    setDefaultImage();
    this.mRequest = new ImageRequest(this.mUrl, this, this.mImageProcessor, this.mOptions);
    this.mRequest.load(getContext());
  }

  public void setDefaultImageBitmap(Bitmap paramBitmap)
  {
    this.mImageSource = 2;
    this.mDefaultBitmap = paramBitmap;
    setDefaultImage();
  }

  public void setDefaultImageDrawable(Drawable paramDrawable)
  {
    this.mImageSource = 1;
    this.mDefaultDrawable = paramDrawable;
    setDefaultImage();
  }

  public void setDefaultImageResource(int paramInt)
  {
    this.mImageSource = 0;
    this.mDefaultResId = paramInt;
    setDefaultImage();
  }

  public void setImageProcessor(ImageProcessor paramImageProcessor)
  {
    this.mImageProcessor = paramImageProcessor;
  }

  public void setInDensity(int paramInt)
  {
    if (this.mOptions == null)
    {
      this.mOptions = new BitmapFactory.Options();
      this.mOptions.inDither = true;
      this.mOptions.inScaled = true;
      this.mOptions.inTargetDensity = getContext().getResources().getDisplayMetrics().densityDpi;
    }
    this.mOptions.inDensity = paramInt;
  }

  public void setOnImageViewLoadListener(OnImageViewLoadListener paramOnImageViewLoadListener)
  {
    this.mOnImageViewLoadListener = paramOnImageViewLoadListener;
  }

  public void setOptions(BitmapFactory.Options paramOptions)
  {
    this.mOptions = paramOptions;
  }

  public void setPaused(boolean paramBoolean)
  {
    if (this.mPaused != paramBoolean)
    {
      this.mPaused = paramBoolean;
      if (!paramBoolean)
        reload();
    }
  }

  public void setUrl(String paramString)
  {
    stopLoading();
    if ((this.mBitmap != null) && (paramString != null) && (paramString.equals(this.mUrl)))
    {
      setImageBitmap(this.mBitmap);
      return;
    }
    this.mUrl = paramString;
    if (TextUtils.isEmpty(this.mUrl))
    {
      this.mBitmap = null;
      setDefaultImage();
      return;
    }
    if (!this.mPaused)
    {
      reload();
      return;
    }
    this.mBitmap = GDUtils.getImageCache(getContext()).get(this.mUrl);
    if (this.mBitmap != null)
    {
      setImageBitmap(this.mBitmap);
      return;
    }
    setDefaultImage();
  }

  public void stopLoading()
  {
    if (this.mRequest != null)
    {
      this.mRequest.cancel();
      this.mRequest = null;
    }
  }

  public static abstract interface OnImageViewLoadListener
  {
    public abstract void onLoadingEnded(AsyncImageView paramAsyncImageView, Bitmap paramBitmap);

    public abstract void onLoadingFailed(AsyncImageView paramAsyncImageView, Throwable paramThrowable);

    public abstract void onLoadingStarted(AsyncImageView paramAsyncImageView);
  }

  static class SavedState extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new SavedState(paramAnonymousParcel, null);
      }

      public SavedState[] newArray(int paramAnonymousInt)
      {
        return new SavedState[paramAnonymousInt];
      }
    };
    String url;

    private SavedState(Parcel paramParcel)
    {
      super();
      this.url = paramParcel.readString();
    }

    SavedState(Parcelable paramParcelable)
    {
      super();
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeString(this.url);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     AsyncImageView
 * JD-Core Version:    0.6.2
 */
