package com.picooc.oldhumen;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class HalfScrollView extends ScrollView
{
  private float xDistance;
  private float xLast;
  private float yDistance;
  private float yLast;

  public HalfScrollView(Context paramContext)
  {
    super(paramContext);
    initHeight();
  }

  public HalfScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initHeight();
  }

  public HalfScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initHeight();
  }

  public void initHeight()
  {
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    case 1:
    default:
    case 0:
    case 2:
    }
    do
    {
      while (true)
      {
        return super.onInterceptTouchEvent(paramMotionEvent);
        this.yDistance = 0.0F;
        this.xDistance = 0.0F;
        this.xLast = paramMotionEvent.getX();
        this.yLast = paramMotionEvent.getY();
      }
      float f1 = paramMotionEvent.getX();
      float f2 = paramMotionEvent.getY();
      this.xDistance += Math.abs(f1 - this.xLast);
      this.yDistance += Math.abs(f2 - this.yLast);
      this.xLast = f1;
      this.yLast = f2;
    }
    while (this.xDistance <= this.yDistance);
    return false;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     HalfScrollView
 * JD-Core Version:    0.6.2
 */
