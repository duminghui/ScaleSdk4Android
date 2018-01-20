package com.picooc.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class PixelUtils
{
  public static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(0.5F + paramFloat * paramContext.getResources().getDisplayMetrics().density);
  }

  public static int getDensity(Context paramContext, float paramFloat)
  {
    return (int)paramContext.getResources().getDisplayMetrics().density;
  }

  public static int[] getPxOnScreenWithPersent(Context paramContext, float paramFloat1, float paramFloat2)
  {
    int[] arrayOfInt = getScreenResolution(paramContext);
    arrayOfInt[0] = ((int)(paramFloat1 * arrayOfInt[0]));
    arrayOfInt[1] = ((int)(paramFloat2 * arrayOfInt[1]));
    return arrayOfInt;
  }

  public static int[] getScreenResolution(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = localDisplayMetrics.widthPixels;
    arrayOfInt[1] = localDisplayMetrics.heightPixels;
    return arrayOfInt;
  }

  public static int px2dip(Context paramContext, float paramFloat)
  {
    return (int)(0.5F + paramFloat / paramContext.getResources().getDisplayMetrics().density);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PixelUtils
 * JD-Core Version:    0.6.2
 */
