package com.picooc.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Gallery;

public class MyGallery extends Gallery
{
  public MyGallery(Context paramContext)
  {
    super(paramContext);
  }

  public MyGallery(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public MyGallery(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    Log.v("velocityX=========" + paramFloat1, "velocityY  ====" + paramFloat2);
    if (paramFloat1 > 0.0F);
    for (int i = 400; ; i = -400)
      return super.onFling(paramMotionEvent1, paramMotionEvent2, i, paramFloat2);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     MyGallery
 * JD-Core Version:    0.6.2
 */
