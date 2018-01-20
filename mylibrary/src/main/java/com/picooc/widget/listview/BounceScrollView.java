package com.picooc.widget.listview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

public class BounceScrollView extends ScrollView
{
  private View inner;
  private boolean isCount = false;
  private Rect normal = new Rect();
  private float y;

  public BounceScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void animation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(0.0F, 0.0F, this.inner.getTop(), this.normal.top);
    localTranslateAnimation.setDuration(200L);
    this.inner.startAnimation(localTranslateAnimation);
    this.inner.layout(this.normal.left, this.normal.top, this.normal.right, this.normal.bottom);
    this.normal.setEmpty();
  }

  public void commOnTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    case 0:
    default:
    case 1:
      do
        return;
      while (!isNeedAnimation());
      animation();
      this.isCount = false;
      return;
    case 2:
    }
    float f1 = this.y;
    float f2 = paramMotionEvent.getY();
    int i = (int)(f1 - f2);
    if (!this.isCount)
      i = 0;
    this.y = f2;
    if (isNeedMove())
    {
      if (this.normal.isEmpty())
        this.normal.set(this.inner.getLeft(), this.inner.getTop(), this.inner.getRight(), this.inner.getBottom());
      this.inner.layout(this.inner.getLeft(), this.inner.getTop() - i / 2, this.inner.getRight(), this.inner.getBottom() - i / 2);
    }
    this.isCount = true;
  }

  public boolean isNeedAnimation()
  {
    return !this.normal.isEmpty();
  }

  public boolean isNeedMove()
  {
    int i = this.inner.getMeasuredHeight() - getHeight();
    int j = getScrollY();
    return (j == 0) || (j == i);
  }

  protected void onFinishInflate()
  {
    if (getChildCount() > 0)
      this.inner = getChildAt(0);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.inner != null)
      commOnTouchEvent(paramMotionEvent);
    return super.onTouchEvent(paramMotionEvent);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BounceScrollView
 * JD-Core Version:    0.6.2
 */
