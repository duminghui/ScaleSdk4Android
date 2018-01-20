package com.picooc.animation;

import android.view.animation.Interpolator;

public class BounceInterpolator
  implements Interpolator
{
  private EasingType.Type type;

  public BounceInterpolator(EasingType.Type paramType)
  {
    this.type = paramType;
  }

  private float in(float paramFloat)
  {
    return 1.0F - out(1.0F - paramFloat);
  }

  private float inout(float paramFloat)
  {
    if (paramFloat < 0.5F)
      return 0.5F * in(paramFloat * 2.0F);
    return 0.5F + 0.5F * out(paramFloat * 2.0F - 1.0F);
  }

  private float out(float paramFloat)
  {
    if (paramFloat < 0.3636363636363637D)
      return paramFloat * (7.5625F * paramFloat);
    if (paramFloat < 0.7272727272727273D)
    {
      float f3 = (float)(paramFloat - 0.5454545454545454D);
      return 0.75F + f3 * (7.5625F * f3);
    }
    if (paramFloat < 0.9090909090909091D)
    {
      float f2 = (float)(paramFloat - 0.8181818181818182D);
      return 0.9375F + f2 * (7.5625F * f2);
    }
    float f1 = (float)(paramFloat - 0.9545454545454546D);
    return 0.984375F + f1 * (7.5625F * f1);
  }

  public float getInterpolation(float paramFloat)
  {
    if (this.type == EasingType.Type.IN)
      return in(paramFloat);
    if (this.type == EasingType.Type.OUT)
      return out(paramFloat);
    if (this.type == EasingType.Type.INOUT)
      return inout(paramFloat);
    return 0.0F;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BounceInterpolator
 * JD-Core Version:    0.6.2
 */
