package com.picooc.utils;

import android.content.Context;
import android.graphics.Typeface;

public class TextUtils
{
  public static Typeface getTypeface(Context paramContext, String paramString)
  {
    if (paramString == null)
      paramString = "fonts/BRI293.TTF";
    return Typeface.createFromAsset(paramContext.getAssets(), paramString);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     TextUtils
 * JD-Core Version:    0.6.2
 */
