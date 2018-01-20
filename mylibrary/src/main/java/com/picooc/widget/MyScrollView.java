package com.picooc.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView
{
  private boolean canScroll = true;
  private GestureDetector mGestureDetector = new GestureDetector(new YScrollDetector());
  View.OnTouchListener mGestureListener;

  public MyScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 1)
      this.canScroll = true;
    return (super.onInterceptTouchEvent(paramMotionEvent)) && (this.mGestureDetector.onTouchEvent(paramMotionEvent));
  }

  class YScrollDetector extends GestureDetector.SimpleOnGestureListener
  {
    YScrollDetector()
    {
    }

    public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      if (MyScrollView.this.canScroll)
        if (Math.abs(paramFloat2) < Math.abs(paramFloat1))
          break label39;
      label39: for (MyScrollView.this.canScroll = true; ; MyScrollView.this.canScroll = false)
        return MyScrollView.this.canScroll;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     MyScrollView
 * JD-Core Version:    0.6.2
 */
