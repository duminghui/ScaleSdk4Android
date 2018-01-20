package com.picooc.utils;

import android.graphics.Color;

public class ColorUtils
{
  public static int getAlphaColor(float paramFloat, int paramInt)
  {
    int i = Color.red(paramInt);
    int j = Color.green(paramInt);
    int k = Color.blue(paramInt);
    return Color.argb((int)(255.0F * paramFloat), i, j, k);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ColorUtils
 * JD-Core Version:    0.6.2
 */
