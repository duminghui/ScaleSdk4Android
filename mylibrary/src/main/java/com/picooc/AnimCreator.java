package com.picooc;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.Display;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class AnimCreator
{
  public static AlphaAnimation alphaIn(long paramLong)
  {
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
    localAlphaAnimation.setDuration(paramLong);
    return localAlphaAnimation;
  }

  public static AlphaAnimation alphaOut(long paramLong)
  {
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.0F);
    localAlphaAnimation.setDuration(paramLong);
    return localAlphaAnimation;
  }

  public static AlphaAnimation createAlphaAnimationIn()
  {
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
    localAlphaAnimation.setDuration(2000L);
    localAlphaAnimation.setFillAfter(true);
    return localAlphaAnimation;
  }

  public static AlphaAnimation createAlphaAnimationSoon(boolean paramBoolean)
  {
    if (paramBoolean);
    for (AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F); ; localAlphaAnimation = new AlphaAnimation(1.0F, 0.0F))
    {
      localAlphaAnimation.setDuration(10L);
      localAlphaAnimation.setFillAfter(true);
      return localAlphaAnimation;
    }
  }

  public static ScaleAnimation createScaleAnimationCenter()
  {
    ScaleAnimation localScaleAnimation = new ScaleAnimation(0.1F, 1.0F, 0.1F, 1.0F, 1, 0.5F, 1, 0.5F);
    localScaleAnimation.setDuration(2000L);
    localScaleAnimation.setFillAfter(true);
    return localScaleAnimation;
  }

  public static ScaleAnimation createScaleAnimationLeftCorner()
  {
    ScaleAnimation localScaleAnimation = new ScaleAnimation(0.1F, 1.0F, 0.1F, 1.0F, 1, 0.0F, 1, 1.0F);
    localScaleAnimation.setDuration(1000L);
    localScaleAnimation.setFillAfter(true);
    return localScaleAnimation;
  }

  public static TranslateAnimation createTranslateAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(0.0F, 0.0F, -300.0F, 0.0F);
    localTranslateAnimation.setDuration(4000L);
    localTranslateAnimation.setInterpolator(new DecelerateInterpolator());
    localTranslateAnimation.setFillAfter(true);
    localTranslateAnimation.setStartOffset(3000L);
    return localTranslateAnimation;
  }

  public static TranslateAnimation createTranslateIn(Activity paramActivity, Interpolator paramInterpolator)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(paramActivity.getWindowManager().getDefaultDisplay().getWidth(), 0.0F, 0.0F, 0.0F);
    localTranslateAnimation.setDuration(1000L);
    localTranslateAnimation.setInterpolator(paramInterpolator);
    localTranslateAnimation.setFillAfter(true);
    return localTranslateAnimation;
  }

  public static TranslateAnimation createTranslateOut(Activity paramActivity, Interpolator paramInterpolator)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(0.0F, -paramActivity.getWindowManager().getDefaultDisplay().getWidth(), 0.0F, 0.0F);
    localTranslateAnimation.setDuration(1000L);
    localTranslateAnimation.setInterpolator(paramInterpolator);
    localTranslateAnimation.setFillAfter(true);
    return localTranslateAnimation;
  }

  public static ObjectAnimator propertAlphaIn(Object paramObject, long paramLong)
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramObject, "alpha", new float[] { 0.0F, 1.0F });
    localObjectAnimator.setDuration(paramLong);
    return localObjectAnimator;
  }

  public static ObjectAnimator propertAlphaOut(Object paramObject, long paramLong)
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramObject, "alpha", new float[] { 1.0F, 0.0F });
    localObjectAnimator.setDuration(paramLong);
    return localObjectAnimator;
  }

  public static TranslateAnimation shake(long paramLong)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(0.0F, 10.0F, 0.0F, 0.0F);
    localTranslateAnimation.setDuration(paramLong);
    localTranslateAnimation.setInterpolator(new CycleInterpolator(7.0F));
    localTranslateAnimation.setFillAfter(true);
    return localTranslateAnimation;
  }

  public static TranslateAnimation translateX(float paramFloat1, float paramFloat2, long paramLong)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(paramFloat1, paramFloat2, 0.0F, 0.0F);
    localTranslateAnimation.setDuration(paramLong);
    localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
    localTranslateAnimation.setFillAfter(false);
    return localTranslateAnimation;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     AnimCreator
 * JD-Core Version:    0.6.2
 */
