package com.picooc.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Stack;
import org.achartengine.tools.ModUtils;

public class ImageLoader
{
  private HashMap<String, SoftReference<Bitmap>> cache = new HashMap();
  private File cacheDir;
  private PhotosLoader photoLoaderThread = new PhotosLoader();
  private PhotosQueue photosQueue = new PhotosQueue();
  private int sampleType;
  private int stub_id = 2130838460;

  public ImageLoader(Context paramContext)
  {
    this.photoLoaderThread.setPriority(4);
    if (Environment.getExternalStorageState().equals("mounted"));
    for (this.cacheDir = new File(Environment.getExternalStorageDirectory().getPath() + "/picooc/head"); ; this.cacheDir = paramContext.getCacheDir())
    {
      if (!this.cacheDir.exists())
        this.cacheDir.mkdirs();
      return;
    }
  }

  private Bitmap decodeFile(File paramFile)
  {
    try
    {
      if (this.sampleType == 0)
      {
        BitmapFactory.Options localOptions = new BitmapFactory.Options();
        localOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(paramFile.getPath(), localOptions);
        localOptions.inSampleSize = ModUtils.computeSampleSize(localOptions, -1, 60000);
        localOptions.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(new FileInputStream(paramFile), null, localOptions);
      }
      Bitmap localBitmap = BitmapFactory.decodeStream(new FileInputStream(paramFile), null, null);
      return localBitmap;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      localOutOfMemoryError.printStackTrace();
      return null;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public static Bitmap drawShadow(Bitmap paramBitmap, int paramInt)
  {
    if (paramBitmap == null)
      return null;
    BlurMaskFilter localBlurMaskFilter = new BlurMaskFilter(paramInt, BlurMaskFilter.Blur.OUTER);
    Paint localPaint = new Paint();
    localPaint.setMaskFilter(localBlurMaskFilter);
    int[] arrayOfInt = new int[2];
    Bitmap localBitmap = paramBitmap.extractAlpha(localPaint, arrayOfInt).copy(Bitmap.Config.ARGB_8888, true);
    new Canvas(localBitmap).drawBitmap(paramBitmap, -arrayOfInt[0], -arrayOfInt[1], null);
    return localBitmap;
  }

  private Bitmap getBitmap(String paramString)
  {
    try
    {
      String str = String.valueOf(paramString.hashCode());
      File localFile = new File(this.cacheDir, str);
      Log.i("qianmo7", "picUrl==" + paramString);
      if (localFile.exists())
      {
        Bitmap localBitmap2 = decodeFile(localFile);
        if (localBitmap2 != null)
          return localBitmap2;
      }
      InputStream localInputStream = new URL(paramString).openStream();
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
      ModUtils.CopyStream(localInputStream, localFileOutputStream);
      localFileOutputStream.close();
      localInputStream.close();
      Bitmap localBitmap1 = decodeFile(localFile);
      return localBitmap1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  private void queuePhoto(String paramString, Activity paramActivity, ImageView paramImageView)
  {
    this.photosQueue.Clean(paramImageView);
    PhotoToLoad localPhotoToLoad = new PhotoToLoad(paramString, paramImageView);
    synchronized (this.photosQueue.photosToLoad)
    {
      this.photosQueue.photosToLoad.push(localPhotoToLoad);
      this.photosQueue.photosToLoad.notifyAll();
      if (this.photoLoaderThread.getState() == Thread.State.NEW)
        this.photoLoaderThread.start();
      return;
    }
  }

  public void DisplayImage(String paramString, Activity paramActivity, ImageView paramImageView)
  {
    while (true)
    {
      try
      {
        if ((this.cache.containsKey(paramString)) && (this.cache.get(paramString) != null) && (((SoftReference)this.cache.get(paramString)).get() != null) && (paramImageView.getTag() != null) && (paramImageView.getTag().equals(paramString)))
        {
          paramImageView.setImageBitmap(ModUtils.toRoundBitmap((Bitmap)((SoftReference)this.cache.get(paramString)).get()));
          return;
        }
        if ((paramImageView == null) || (!paramImageView.getTag().equals(paramString)))
          continue;
        Bitmap localBitmap1 = getBitmap(paramString);
        if (localBitmap1 != null)
        {
          Bitmap localBitmap2 = ModUtils.toRoundBitmap(localBitmap1);
          paramImageView.setImageBitmap(localBitmap2);
          this.cache.put(paramString, new SoftReference(localBitmap2));
          continue;
        }
      }
      finally
      {
      }
      queuePhoto(paramString, paramActivity, paramImageView);
      paramImageView.setImageResource(this.stub_id);
    }
  }

  public void DisplayImage(String paramString, Activity paramActivity, ImageView paramImageView, int paramInt)
  {
    this.sampleType = paramInt;
    DisplayImage(paramString, paramActivity, paramImageView);
  }

  public void clearCache()
  {
    this.cache.clear();
  }

  public void clearSDCardCache()
  {
    File[] arrayOfFile = this.cacheDir.listFiles();
    int i = arrayOfFile.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      arrayOfFile[j].delete();
    }
  }

  public void setStub_id(int paramInt)
  {
    this.stub_id = paramInt;
  }

  public void stopThread()
  {
    this.photoLoaderThread.interrupt();
  }

  class BitmapDisplayer
    implements Runnable
  {
    Bitmap bitmap;
    ImageView imageView;

    public BitmapDisplayer(Bitmap paramImageView, ImageView arg3)
    {
      this.bitmap = paramImageView;
      Object localObject;
      this.imageView = localObject;
    }

    public void run()
    {
      if (this.bitmap != null)
      {
        this.imageView.setImageBitmap(ModUtils.toRoundBitmap(this.bitmap));
        return;
      }
      this.imageView.setImageResource(ImageLoader.this.stub_id);
    }
  }

  private class PhotoToLoad
  {
    public ImageView imageView;
    public String url;

    public PhotoToLoad(String paramImageView, ImageView arg3)
    {
      this.url = paramImageView;
      Object localObject;
      this.imageView = localObject;
    }
  }

  class PhotosLoader extends Thread
  {
    PhotosLoader()
    {
    }

    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 10	com/picooc/widget/ImageLoader$PhotosLoader:this$0	Lcom/picooc/widget/ImageLoader;
      //   4: invokestatic 22	com/picooc/widget/ImageLoader:access$0	(Lcom/picooc/widget/ImageLoader;)Lcom/picooc/widget/ImageLoader$PhotosQueue;
      //   7: invokestatic 27	com/picooc/widget/ImageLoader$PhotosQueue:access$0	(Lcom/picooc/widget/ImageLoader$PhotosQueue;)Ljava/util/Stack;
      //   10: invokevirtual 33	java/util/Stack:size	()I
      //   13: ifne +34 -> 47
      //   16: aload_0
      //   17: getfield 10	com/picooc/widget/ImageLoader$PhotosLoader:this$0	Lcom/picooc/widget/ImageLoader;
      //   20: invokestatic 22	com/picooc/widget/ImageLoader:access$0	(Lcom/picooc/widget/ImageLoader;)Lcom/picooc/widget/ImageLoader$PhotosQueue;
      //   23: invokestatic 27	com/picooc/widget/ImageLoader$PhotosQueue:access$0	(Lcom/picooc/widget/ImageLoader$PhotosQueue;)Ljava/util/Stack;
      //   26: astore 11
      //   28: aload 11
      //   30: monitorenter
      //   31: aload_0
      //   32: getfield 10	com/picooc/widget/ImageLoader$PhotosLoader:this$0	Lcom/picooc/widget/ImageLoader;
      //   35: invokestatic 22	com/picooc/widget/ImageLoader:access$0	(Lcom/picooc/widget/ImageLoader;)Lcom/picooc/widget/ImageLoader$PhotosQueue;
      //   38: invokestatic 27	com/picooc/widget/ImageLoader$PhotosQueue:access$0	(Lcom/picooc/widget/ImageLoader$PhotosQueue;)Ljava/util/Stack;
      //   41: invokevirtual 38	java/lang/Object:wait	()V
      //   44: aload 11
      //   46: monitorexit
      //   47: aload_0
      //   48: getfield 10	com/picooc/widget/ImageLoader$PhotosLoader:this$0	Lcom/picooc/widget/ImageLoader;
      //   51: invokestatic 22	com/picooc/widget/ImageLoader:access$0	(Lcom/picooc/widget/ImageLoader;)Lcom/picooc/widget/ImageLoader$PhotosQueue;
      //   54: invokestatic 27	com/picooc/widget/ImageLoader$PhotosQueue:access$0	(Lcom/picooc/widget/ImageLoader$PhotosQueue;)Ljava/util/Stack;
      //   57: invokevirtual 33	java/util/Stack:size	()I
      //   60: ifeq +173 -> 233
      //   63: aload_0
      //   64: getfield 10	com/picooc/widget/ImageLoader$PhotosLoader:this$0	Lcom/picooc/widget/ImageLoader;
      //   67: invokestatic 22	com/picooc/widget/ImageLoader:access$0	(Lcom/picooc/widget/ImageLoader;)Lcom/picooc/widget/ImageLoader$PhotosQueue;
      //   70: invokestatic 27	com/picooc/widget/ImageLoader$PhotosQueue:access$0	(Lcom/picooc/widget/ImageLoader$PhotosQueue;)Ljava/util/Stack;
      //   73: astore_3
      //   74: aload_3
      //   75: monitorenter
      //   76: aload_0
      //   77: getfield 10	com/picooc/widget/ImageLoader$PhotosLoader:this$0	Lcom/picooc/widget/ImageLoader;
      //   80: invokestatic 22	com/picooc/widget/ImageLoader:access$0	(Lcom/picooc/widget/ImageLoader;)Lcom/picooc/widget/ImageLoader$PhotosQueue;
      //   83: invokestatic 27	com/picooc/widget/ImageLoader$PhotosQueue:access$0	(Lcom/picooc/widget/ImageLoader$PhotosQueue;)Ljava/util/Stack;
      //   86: invokevirtual 42	java/util/Stack:firstElement	()Ljava/lang/Object;
      //   89: checkcast 44	com/picooc/widget/ImageLoader$PhotoToLoad
      //   92: astore 5
      //   94: aload_0
      //   95: getfield 10	com/picooc/widget/ImageLoader$PhotosLoader:this$0	Lcom/picooc/widget/ImageLoader;
      //   98: invokestatic 22	com/picooc/widget/ImageLoader:access$0	(Lcom/picooc/widget/ImageLoader;)Lcom/picooc/widget/ImageLoader$PhotosQueue;
      //   101: invokestatic 27	com/picooc/widget/ImageLoader$PhotosQueue:access$0	(Lcom/picooc/widget/ImageLoader$PhotosQueue;)Ljava/util/Stack;
      //   104: invokevirtual 33	java/util/Stack:size	()I
      //   107: ifle +18 -> 125
      //   110: aload_0
      //   111: getfield 10	com/picooc/widget/ImageLoader$PhotosLoader:this$0	Lcom/picooc/widget/ImageLoader;
      //   114: invokestatic 22	com/picooc/widget/ImageLoader:access$0	(Lcom/picooc/widget/ImageLoader;)Lcom/picooc/widget/ImageLoader$PhotosQueue;
      //   117: invokestatic 27	com/picooc/widget/ImageLoader$PhotosQueue:access$0	(Lcom/picooc/widget/ImageLoader$PhotosQueue;)Ljava/util/Stack;
      //   120: iconst_0
      //   121: invokevirtual 48	java/util/Stack:remove	(I)Ljava/lang/Object;
      //   124: pop
      //   125: aload_3
      //   126: monitorexit
      //   127: aload_0
      //   128: getfield 10	com/picooc/widget/ImageLoader$PhotosLoader:this$0	Lcom/picooc/widget/ImageLoader;
      //   131: aload 5
      //   133: getfield 52	com/picooc/widget/ImageLoader$PhotoToLoad:url	Ljava/lang/String;
      //   136: invokestatic 56	com/picooc/widget/ImageLoader:access$1	(Lcom/picooc/widget/ImageLoader;Ljava/lang/String;)Landroid/graphics/Bitmap;
      //   139: astore 6
      //   141: aload_0
      //   142: getfield 10	com/picooc/widget/ImageLoader$PhotosLoader:this$0	Lcom/picooc/widget/ImageLoader;
      //   145: invokestatic 60	com/picooc/widget/ImageLoader:access$2	(Lcom/picooc/widget/ImageLoader;)Ljava/util/HashMap;
      //   148: aload 5
      //   150: getfield 52	com/picooc/widget/ImageLoader$PhotoToLoad:url	Ljava/lang/String;
      //   153: new 62	java/lang/ref/SoftReference
      //   156: dup
      //   157: aload 6
      //   159: invokespecial 65	java/lang/ref/SoftReference:<init>	(Ljava/lang/Object;)V
      //   162: invokevirtual 71	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   165: pop
      //   166: aload 5
      //   168: getfield 75	com/picooc/widget/ImageLoader$PhotoToLoad:imageView	Landroid/widget/ImageView;
      //   171: invokevirtual 80	android/widget/ImageView:getTag	()Ljava/lang/Object;
      //   174: astore 8
      //   176: aload 8
      //   178: ifnull +55 -> 233
      //   181: aload 8
      //   183: checkcast 82	java/lang/String
      //   186: aload 5
      //   188: getfield 52	com/picooc/widget/ImageLoader$PhotoToLoad:url	Ljava/lang/String;
      //   191: invokevirtual 86	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   194: ifeq +39 -> 233
      //   197: new 88	com/picooc/widget/ImageLoader$BitmapDisplayer
      //   200: dup
      //   201: aload_0
      //   202: getfield 10	com/picooc/widget/ImageLoader$PhotosLoader:this$0	Lcom/picooc/widget/ImageLoader;
      //   205: aload 6
      //   207: aload 5
      //   209: getfield 75	com/picooc/widget/ImageLoader$PhotoToLoad:imageView	Landroid/widget/ImageView;
      //   212: invokespecial 91	com/picooc/widget/ImageLoader$BitmapDisplayer:<init>	(Lcom/picooc/widget/ImageLoader;Landroid/graphics/Bitmap;Landroid/widget/ImageView;)V
      //   215: astore 9
      //   217: aload 5
      //   219: getfield 75	com/picooc/widget/ImageLoader$PhotoToLoad:imageView	Landroid/widget/ImageView;
      //   222: invokevirtual 95	android/widget/ImageView:getContext	()Landroid/content/Context;
      //   225: checkcast 97	android/app/Activity
      //   228: aload 9
      //   230: invokevirtual 101	android/app/Activity:runOnUiThread	(Ljava/lang/Runnable;)V
      //   233: invokestatic 105	java/lang/Thread:interrupted	()Z
      //   236: istore_2
      //   237: iload_2
      //   238: ifeq -238 -> 0
      //   241: return
      //   242: astore 12
      //   244: aload 11
      //   246: monitorexit
      //   247: aload 12
      //   249: athrow
      //   250: astore_1
      //   251: aload_1
      //   252: invokevirtual 108	java/lang/InterruptedException:printStackTrace	()V
      //   255: return
      //   256: astore 4
      //   258: aload_3
      //   259: monitorexit
      //   260: aload 4
      //   262: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   31	47	242	finally
      //   244	247	242	finally
      //   0	31	250	java/lang/InterruptedException
      //   47	76	250	java/lang/InterruptedException
      //   127	176	250	java/lang/InterruptedException
      //   181	233	250	java/lang/InterruptedException
      //   233	237	250	java/lang/InterruptedException
      //   247	250	250	java/lang/InterruptedException
      //   260	263	250	java/lang/InterruptedException
      //   76	125	256	finally
      //   125	127	256	finally
      //   258	260	256	finally
    }
  }

  class PhotosQueue
  {
    private Stack<PhotoToLoad> photosToLoad = new Stack();

    PhotosQueue()
    {
    }

    public void Clean(ImageView paramImageView)
    {
      for (int i = 0; ; i++)
        try
        {
          while (true)
          {
            if (i >= this.photosToLoad.size())
              return;
            if (((PhotoToLoad)this.photosToLoad.get(i)).imageView != paramImageView)
              break;
            this.photosToLoad.remove(i);
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          return;
        }
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ImageLoader
 * JD-Core Version:    0.6.2
 */
