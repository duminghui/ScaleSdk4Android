package com.picooc.utils;

import android.content.Context;
import android.graphics.Typeface;

public class TypefaceUtils
{
  public static Typeface normalTypeface;

  public static Typeface getTypeface(Context paramContext, String paramString)
  {
    if (paramString == null)
      paramString = "fonts/BRI293.TTF";
    if (normalTypeface == null)
      normalTypeface = Typeface.createFromAsset(paramContext.getAssets(), paramString);
    return normalTypeface;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     TypefaceUtils
 * JD-Core Version:    0.6.2
 */
