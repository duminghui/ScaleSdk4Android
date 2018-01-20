package com.picooc.widget.anyncImageView;

import android.content.Context;
import android.graphics.Bitmap;
import com.picooc.MyApplication;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class ImageCache
  implements MyApplication.OnLowMemoryListener
{
  private final HashMap<String, SoftReference<Bitmap>> mSoftCache = new HashMap();

  public ImageCache(Context paramContext)
  {
    GDUtils.getGDApplication(paramContext).registerOnLowMemoryListener(this);
  }

  public static ImageCache from(Context paramContext)
  {
    return GDUtils.getImageCache(paramContext);
  }

  public void flush()
  {
    this.mSoftCache.clear();
  }

  public Bitmap get(String paramString)
  {
    SoftReference localSoftReference = (SoftReference)this.mSoftCache.get(paramString);
    Bitmap localBitmap;
    if (localSoftReference == null)
      localBitmap = null;
    do
    {
      return localBitmap;
      localBitmap = (Bitmap)localSoftReference.get();
    }
    while (localBitmap != null);
    this.mSoftCache.remove(paramString);
    return localBitmap;
  }

  public void onLowMemoryReceived()
  {
    flush();
  }

  public void put(String paramString, Bitmap paramBitmap)
  {
    this.mSoftCache.put(paramString, new SoftReference(paramBitmap));
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ImageCache
 * JD-Core Version:    0.6.2
 */
