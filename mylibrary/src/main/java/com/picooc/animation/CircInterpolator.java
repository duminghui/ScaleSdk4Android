package com.picooc.animation;

import android.view.animation.Interpolator;

public class CircInterpolator
  implements Interpolator
{
  private EasingType.Type type;

  public CircInterpolator(EasingType.Type paramType)
  {
    this.type = paramType;
  }

  private float in(float paramFloat)
  {
    return (float)-(Math.sqrt(1.0F - paramFloat * paramFloat) - 1.0D);
  }

  private float inout(float paramFloat)
  {
    float f1 = paramFloat * 2.0F;
    if (f1 < 1.0F)
      return (float)(-0.5D * (Math.sqrt(1.0F - f1 * f1) - 1.0D));
    float f2 = f1 - 2.0F;
    return (float)(0.5D * (1.0D + Math.sqrt(1.0F - f2 * f2)));
  }

  private float out(float paramFloat)
  {
    float f = paramFloat - 1.0F;
    return (float)Math.sqrt(1.0F - f * f);
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
 * Qualified Name:     CircInterpolator
 * JD-Core Version:    0.6.2
 */
