package com.picooc.widget.anyncImageView;

import android.content.Context;
import com.picooc.MyApplication;
import java.util.concurrent.ExecutorService;

public class GDUtils
{
  public static ExecutorService getExecutor(Context paramContext)
  {
    return getGDApplication(paramContext).getExecutor();
  }

  public static MyApplication getGDApplication(Context paramContext)
  {
    return (MyApplication)paramContext.getApplicationContext();
  }

  public static ImageCache getImageCache(Context paramContext)
  {
    return getGDApplication(paramContext).getImageCache();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     GDUtils
 * JD-Core Version:    0.6.2
 */
