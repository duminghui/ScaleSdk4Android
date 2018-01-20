package com.picooc.widget.picoocProgress;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import com.picooc.utils.ColorUtils;

public class ProgressTextView extends TextView
{
  private int color;
  private float textSize;

  public ProgressTextView(Context paramContext)
  {
    super(paramContext);
  }

  public ProgressTextView(Context paramContext, int paramInt, float paramFloat, String paramString, Typeface paramTypeface)
  {
    super(paramContext);
    this.color = paramInt;
    this.textSize = paramFloat;
    setTextColor(paramInt);
    setTextSize(0, paramFloat);
    setTypeface(paramTypeface);
    if ((paramString != null) && (!paramString.equals("")))
      setText(paramString);
  }

  public ProgressTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public ProgressTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public int getColor()
  {
    return this.color;
  }

  public float getTextSize()
  {
    return this.textSize;
  }

  public void setColor(int paramInt)
  {
    this.color = paramInt;
  }

  public void setText(String paramString)
  {
    SpannableString localSpannableString = new SpannableString(paramString);
    int i = paramString.indexOf(".");
    localSpannableString.setSpan(new RelativeSizeSpan(0.65F), i, paramString.length(), 33);
    setText(localSpannableString);
  }

  public void setTextAlpha(float paramFloat)
  {
    setTextColor(ColorUtils.getAlphaColor(paramFloat, this.color));
  }

  public void setTextSize(float paramFloat)
  {
    this.textSize = paramFloat;
  }

  public void startAlphaAnimation(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this, "alpha", new float[] { paramFloat1, paramFloat2 });
    localObjectAnimator.setDuration(2000L);
    localObjectAnimator.start();
  }

  public void startZoomoutAndZoominAnim(final String paramString)
  {
    ScaleAnimation localScaleAnimation = new ScaleAnimation(1.0F, 0.0F, 1.0F, 0.0F, 1, 0.5F, 1, 0.5F);
    localScaleAnimation.setRepeatCount(1);
    localScaleAnimation.setRepeatMode(2);
    localScaleAnimation.setDuration(600L);
    localScaleAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
        ProgressTextView.this.setText(paramString);
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    });
    startAnimation(localScaleAnimation);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ProgressTextView
 * JD-Core Version:    0.6.2
 */
