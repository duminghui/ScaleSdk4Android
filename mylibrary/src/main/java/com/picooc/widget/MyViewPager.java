package com.picooc.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager
{
  private boolean isScrollable;

  public MyViewPager(Context paramContext)
  {
    super(paramContext);
  }

  public MyViewPager(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public boolean isScrollable()
  {
    return this.isScrollable;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!this.isScrollable)
      return false;
    try
    {
      boolean bool = super.onInterceptTouchEvent(paramMotionEvent);
      return bool;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!this.isScrollable)
      return false;
    return super.onTouchEvent(paramMotionEvent);
  }

  public void setScrollable(boolean paramBoolean)
  {
    this.isScrollable = paramBoolean;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     MyViewPager
 * JD-Core Version:    0.6.2
 */
