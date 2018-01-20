package com.picooc.widget.everydayme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;

import com.picooc.utils.PixelUtils;

public class ArcLayout extends ViewGroup
{
  public static final float DEFAULT_FROM_DEGREES = 270.0F;
  public static final float DEFAULT_TO_DEGREES = 360.0F;
  private int MIN_RADIUS = 60;
  private int mChildPadding = 15;
  private int mChildSize;
  private boolean mExpanded = false;
  private float mFromDegrees = 270.0F;
  private int mLayoutPadding = 30;
  private int mRadius;
  private float mToDegrees = 360.0F;

  public ArcLayout(Context paramContext)
  {
    super(paramContext);
  }

  public ArcLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    if (paramAttributeSet != null)
    {
      TypedArray localTypedArray = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.ArcLayout, 0, 0);
      this.mFromDegrees = localTypedArray.getFloat(1, 270.0F);
      this.mToDegrees = localTypedArray.getFloat(2, 360.0F);
      this.mChildSize = Math.max(localTypedArray.getDimensionPixelSize(0, 0), 0);
      localTypedArray.recycle();
    }
    this.mLayoutPadding = PixelUtils.dip2px(paramContext, 28.0F);
  }

  private void bindChildAnimation(View paramView, int paramInt, long paramLong)
  {
    boolean bool1 = this.mExpanded;
    int i = getWidth() / 2;
    int j = getHeight() / 2;
    int k;
    int i1;
    int i2;
    Object localObject;
    label113: long l;
    Animation localAnimation;
    if (bool1)
    {
      k = 0;
      int m = getChildCount();
      float f = 0.0F + 360 / m * paramInt;
      int n = this.mChildSize;
      Rect localRect = computeChildFrame(i, j, k, f, n);
      i1 = localRect.left - paramView.getLeft();
      i2 = localRect.top - paramView.getTop();
      if (!this.mExpanded)
        break label219;
      localObject = new AnticipateInterpolator(5.5F);
      l = computeStartOffset(m, this.mExpanded, paramInt, 0.13F, paramLong, (Interpolator)localObject);
      if (!this.mExpanded)
        break label233;
      localAnimation = createShrinkAnimation(0.0F, i1, 0.0F, i2, l, paramLong, (Interpolator)localObject);
      label155: if (getTransformedIndex(bool1, m, paramInt) != m - 1)
        break label254;
    }
    label219: label233: label254: for (final boolean bool2 = true; ; bool2 = false)
    {
      Animation.AnimationListener local1 = new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          if (bool2)
            ArcLayout.this.postDelayed(new Runnable()
            {
              public void run()
              {
                ArcLayout.this.onAllAnimationsEnd();
              }
            }
            , 0L);
        }

        public void onAnimationRepeat(Animation paramAnonymousAnimation)
        {
        }

        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
        }
      };
      localAnimation.setAnimationListener(local1);
      if (!this.mExpanded)
        requestLayout();
      paramView.setAnimation(localAnimation);
      return;
      k = this.mRadius;
      break;
      localObject = new OvershootInterpolator(2.2F);
      break label113;
      localAnimation = createExpandAnimation(0.0F, i1, 0.0F, i2, l, paramLong, (Interpolator)localObject);
      break label155;
    }
  }

  private static Rect computeChildFrame(int paramInt1, int paramInt2, int paramInt3, float paramFloat, int paramInt4)
  {
    double d1 = paramInt1 + paramInt3 * Math.cos(Math.toRadians(paramFloat));
    double d2 = paramInt2 + paramInt3 * Math.sin(Math.toRadians(paramFloat));
    return new Rect((int)(d1 - paramInt4 / 2), (int)(d2 - paramInt4 / 2), (int)(d1 + paramInt4 / 2), (int)(d2 + paramInt4 / 2));
  }

  private static int computeRadius(float paramFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt1 < 2)
      return paramInt4;
    float f = paramFloat / paramInt1 / 2.0F;
    return Math.max((int)((paramInt2 + paramInt3) / 2 / Math.sin(Math.toRadians(f))), paramInt4);
  }

  private static long computeStartOffset(int paramInt1, boolean paramBoolean, int paramInt2, float paramFloat, long paramLong, Interpolator paramInterpolator)
  {
    float f1 = paramFloat * (float)paramLong;
    long l = ()(f1 * getTransformedIndex(paramBoolean, paramInt1, paramInt2));
    float f2 = f1 * paramInt1;
    return ()(f2 * paramInterpolator.getInterpolation((float)l / f2));
  }

  private static Animation createExpandAnimation(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong1, long paramLong2, Interpolator paramInterpolator)
  {
    ScaleAnimation localScaleAnimation = new ScaleAnimation(0.0F, 1.0F, 0.0F, 1.0F, 1, 0.5F, 1, 0.5F);
    localScaleAnimation.setStartOffset(paramLong1);
    localScaleAnimation.setDuration(2L * paramLong2);
    localScaleAnimation.setInterpolator(paramInterpolator);
    localScaleAnimation.setFillAfter(true);
    return localScaleAnimation;
  }

  private static Animation createShrinkAnimation(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong1, long paramLong2, Interpolator paramInterpolator)
  {
    long l = (int)(1.8D * paramLong2);
    RotateAndTranslateAnimation localRotateAndTranslateAnimation = new RotateAndTranslateAnimation(0.0F, paramFloat2, 0.0F, paramFloat4, 360.0F, 720.0F);
    localRotateAndTranslateAnimation.setStartOffset(paramLong1);
    localRotateAndTranslateAnimation.setDuration(l - paramLong1);
    localRotateAndTranslateAnimation.setInterpolator(paramInterpolator);
    localRotateAndTranslateAnimation.setFillAfter(true);
    return localRotateAndTranslateAnimation;
  }

  private static int getTransformedIndex(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    if (paramBoolean)
      paramInt2 = paramInt1 - 1 - paramInt2;
    return paramInt2;
  }

  private void onAllAnimationsEnd()
  {
    int i = getChildCount();
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        requestLayout();
        return;
      }
      getChildAt(j).clearAnimation();
    }
  }

  public boolean isExpanded()
  {
    return this.mExpanded;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getHeight() / 2;
    int j;
    int k;
    if (this.mExpanded)
    {
      j = this.mRadius;
      k = getChildCount();
      if (k > 0)
        break label39;
    }
    while (true)
    {
      return;
      j = 0;
      break;
      label39: float f1 = Math.abs(360) / k;
      float f2 = 0.0F;
      for (int m = 0; m < k; m++)
      {
        Rect localRect = computeChildFrame(i, i, j, f2, this.mChildSize);
        f2 += f1;
        getChildAt(m).layout(localRect.left, localRect.top, localRect.right, localRect.bottom);
      }
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = computeRadius(Math.abs(360), getChildCount(), this.mChildSize, this.mChildPadding, this.MIN_RADIUS);
    this.mRadius = i;
    int j = i * 2 + this.mChildSize + this.mChildPadding + 2 * this.mLayoutPadding;
    setMeasuredDimension(j, j);
    int k = getChildCount();
    for (int m = 0; ; m++)
    {
      if (m >= k)
        return;
      getChildAt(m).measure(View.MeasureSpec.makeMeasureSpec(this.mChildSize, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mChildSize, 1073741824));
    }
  }

  public void setChildSize(int paramInt)
  {
    if ((this.mChildSize == paramInt) || (paramInt < 0))
      return;
    this.mChildSize = paramInt;
    requestLayout();
  }

  public void setMinRadius(int paramInt)
  {
    this.MIN_RADIUS = paramInt;
  }

  public void switchState(boolean paramBoolean)
  {
    int j;
    if (paramBoolean)
    {
      int i = getChildCount();
      j = 0;
      if (j < i);
    }
    else
    {
      if (!this.mExpanded)
        break label66;
    }
    label66: for (boolean bool = false; ; bool = true)
    {
      this.mExpanded = bool;
      if (!paramBoolean)
        requestLayout();
      invalidate();
      return;
      bindChildAnimation(getChildAt(j), j, 300L);
      j++;
      break;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ArcLayout
 * JD-Core Version:    0.6.2
 */
