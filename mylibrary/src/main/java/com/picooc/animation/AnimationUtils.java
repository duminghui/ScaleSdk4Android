package com.picooc.animation;

import android.animation.AnimatorSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

public class AnimationUtils
{
  public static void animHideShowView(View paramView, Animation.AnimationListener paramAnimationListener, final int paramInt1, final boolean paramBoolean, int paramInt2)
  {
    if (paramInt1 == 0)
      paramInt1 = paramView.getMeasuredHeight();
    Animation local2 = new Animation()
    {
      protected void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation)
      {
        if (paramAnonymousFloat == 1.0F)
        {
          View localView = AnimationUtils.this;
          if (paramBoolean);
          for (int j = 0; ; j = 8)
          {
            localView.setVisibility(j);
            return;
          }
        }
        if (paramBoolean);
        for (int i = (int)(paramAnonymousFloat * paramInt1); ; i = paramInt1 - (int)(paramAnonymousFloat * paramInt1))
        {
          AnimationUtils.this.getLayoutParams().height = i;
          AnimationUtils.this.requestLayout();
          return;
        }
      }

      public boolean willChangeBounds()
      {
        return true;
      }
    };
    if (paramAnimationListener != null)
      local2.setAnimationListener(paramAnimationListener);
    local2.setDuration(paramInt2);
    paramView.startAnimation(local2);
  }

  public static Animation createActivityBonceAnimation(long paramLong)
  {
    AnimationSet localAnimationSet = new AnimationSet(true);
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
    localAlphaAnimation.setDuration(600L);
    localAnimationSet.addAnimation(localAlphaAnimation);
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(0.0F, 0.0F, 50.0F, 0.0F);
    localTranslateAnimation.setDuration(600L);
    localAnimationSet.addAnimation(localTranslateAnimation);
    localAnimationSet.setStartOffset(paramLong);
    return localAnimationSet;
  }

  public static Animation createBounceShowAnimation()
  {
    ScaleAnimation localScaleAnimation = new ScaleAnimation(1.0F, 1.5F, 1.0F, 1.5F, 1, 0.5F, 1, 0.5F);
    localScaleAnimation.setInterpolator(new BounceInterpolator(EasingType.Type.INOUT));
    localScaleAnimation.setDuration(400L);
    return localScaleAnimation;
  }

  public static Animation createReduceAlphaAnimation(float paramFloat)
  {
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, paramFloat);
    localAlphaAnimation.setDuration(1600L);
    return localAlphaAnimation;
  }

  public static Animation createShowAlphaAnimation()
  {
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
    localAlphaAnimation.setDuration(1L);
    return localAlphaAnimation;
  }

  public static void startBounceDouble(final View paramView1, final float paramFloat, View paramView2, final boolean paramBoolean, final AnimationEndListener paramAnimationEndListener)
  {
    ScaleAnimation localScaleAnimation = new ScaleAnimation(1.0F, paramFloat, 1.0F, paramFloat, 1, 0.5F, 1, 0.5F);
    new AnimatorSet();
    localScaleAnimation.setRepeatCount(1);
    localScaleAnimation.setRepeatMode(2);
    localScaleAnimation.setDuration(200L);
    localScaleAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        if (paramBoolean)
        {
          ScaleAnimation localScaleAnimation = new ScaleAnimation(1.0F, 0.85F * paramFloat, 1.0F, 0.85F * paramFloat, 1, 0.5F, 1, 0.5F);
          localScaleAnimation.setRepeatMode(2);
          localScaleAnimation.setRepeatCount(1);
          localScaleAnimation.setDuration(200L);
          localScaleAnimation.setAnimationListener(new Animation.AnimationListener()
          {
            public void onAnimationEnd(Animation paramAnonymous2Animation)
            {
              this.val$clickView.setClickable(true);
              if (this.val$end != null)
                this.val$end.animationEnd();
            }

            public void onAnimationRepeat(Animation paramAnonymous2Animation)
            {
            }

            public void onAnimationStart(Animation paramAnonymous2Animation)
            {
            }
          });
          paramView1.startAnimation(localScaleAnimation);
        }
        do
        {
          return;
          AnimationUtils.this.setClickable(true);
        }
        while (paramAnimationEndListener == null);
        paramAnimationEndListener.animationEnd();
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
        AnimationUtils.this.setClickable(false);
      }
    });
    paramView1.startAnimation(localScaleAnimation);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     AnimationUtils
 * JD-Core Version:    0.6.2
 */
