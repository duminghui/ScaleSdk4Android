package com.picooc.widget;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout.LayoutParams;

public class ViewExpandAnimation extends Animation
{
  private View mAnimationView = null;
  private int mEnd = 0;
  private int mStart = 0;
  private LinearLayout.LayoutParams mViewLayoutParams = null;

  public ViewExpandAnimation(View paramView)
  {
    animationSettings(paramView, 200);
  }

  public ViewExpandAnimation(View paramView, int paramInt)
  {
    animationSettings(paramView, paramInt);
  }

  private void animationSettings(View paramView, int paramInt)
  {
    setDuration(paramInt);
    this.mAnimationView = paramView;
    this.mViewLayoutParams = ((LinearLayout.LayoutParams)paramView.getLayoutParams());
    this.mStart = this.mViewLayoutParams.bottomMargin;
    if (this.mStart == 0);
    for (int i = 0 - paramView.getHeight(); ; i = 0)
    {
      this.mEnd = i;
      paramView.setVisibility(0);
      return;
    }
  }

  protected void applyTransformation(float paramFloat, Transformation paramTransformation)
  {
    super.applyTransformation(paramFloat, paramTransformation);
    if (paramFloat < 1.0F)
    {
      this.mViewLayoutParams.bottomMargin = (this.mStart + (int)(paramFloat * (this.mEnd - this.mStart)));
      this.mAnimationView.requestLayout();
    }
    do
    {
      return;
      this.mViewLayoutParams.bottomMargin = this.mEnd;
      this.mAnimationView.requestLayout();
    }
    while (this.mEnd == 0);
    this.mAnimationView.setVisibility(8);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ViewExpandAnimation
 * JD-Core Version:    0.6.2
 */
