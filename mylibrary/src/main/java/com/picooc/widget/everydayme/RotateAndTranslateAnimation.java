package com.picooc.widget.everydayme;

import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class RotateAndTranslateAnimation extends Animation
{
  private float mFromDegrees;
  private float mFromXDelta;
  private int mFromXType = 0;
  private float mFromXValue = 0.0F;
  private float mFromYDelta;
  private int mFromYType = 0;
  private float mFromYValue = 0.0F;
  private float mPivotX;
  private int mPivotXType = 0;
  private float mPivotXValue = 0.0F;
  private float mPivotY;
  private int mPivotYType = 0;
  private float mPivotYValue = 0.0F;
  private float mToDegrees;
  private float mToXDelta;
  private int mToXType = 0;
  private float mToXValue = 0.0F;
  private float mToYDelta;
  private int mToYType = 0;
  private float mToYValue = 0.0F;

  public RotateAndTranslateAnimation(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
  {
    this.mFromXValue = paramFloat1;
    this.mToXValue = paramFloat2;
    this.mFromYValue = paramFloat3;
    this.mToYValue = paramFloat4;
    this.mFromXType = 0;
    this.mToXType = 0;
    this.mFromYType = 0;
    this.mToYType = 0;
    this.mFromDegrees = paramFloat5;
    this.mToDegrees = paramFloat6;
    this.mPivotXValue = 0.5F;
    this.mPivotXType = 1;
    this.mPivotYValue = 0.5F;
    this.mPivotYType = 1;
  }

  protected void applyTransformation(float paramFloat, Transformation paramTransformation)
  {
    float f1 = this.mFromDegrees + paramFloat * (this.mToDegrees - this.mFromDegrees);
    if ((this.mPivotX == 0.0F) && (this.mPivotY == 0.0F))
      paramTransformation.getMatrix().setRotate(f1);
    while (true)
    {
      float f2 = this.mFromXDelta;
      float f3 = this.mFromYDelta;
      if (this.mFromXDelta != this.mToXDelta)
        f2 = this.mFromXDelta + paramFloat * (this.mToXDelta - this.mFromXDelta);
      if (this.mFromYDelta != this.mToYDelta)
        f3 = this.mFromYDelta + paramFloat * (this.mToYDelta - this.mFromYDelta);
      paramTransformation.getMatrix().postTranslate(f2, f3);
      return;
      paramTransformation.getMatrix().setRotate(f1, this.mPivotX, this.mPivotY);
    }
  }

  public void initialize(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.initialize(paramInt1, paramInt2, paramInt3, paramInt4);
    this.mFromXDelta = resolveSize(this.mFromXType, this.mFromXValue, paramInt1, paramInt3);
    this.mToXDelta = resolveSize(this.mToXType, this.mToXValue, paramInt1, paramInt3);
    this.mFromYDelta = resolveSize(this.mFromYType, this.mFromYValue, paramInt2, paramInt4);
    this.mToYDelta = resolveSize(this.mToYType, this.mToYValue, paramInt2, paramInt4);
    this.mPivotX = resolveSize(this.mPivotXType, this.mPivotXValue, paramInt1, paramInt3);
    this.mPivotY = resolveSize(this.mPivotYType, this.mPivotYValue, paramInt2, paramInt4);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     RotateAndTranslateAnimation
 * JD-Core Version:    0.6.2
 */
