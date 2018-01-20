package com.picooc.guide;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class Contant
{
  public static final String[] guidCount = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
  static float scale = 1.5F;

  public static int dip2px(Context paramContext, float paramFloat)
  {
    scale = paramContext.getResources().getDisplayMetrics().density;
    return (int)(0.5F + paramFloat * scale);
  }

  public static int px2dip(Context paramContext, float paramFloat)
  {
    scale = paramContext.getResources().getDisplayMetrics().density;
    return (int)(paramFloat / scale - 0.5F);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     Contant
 * JD-Core Version:    0.6.2
 */
