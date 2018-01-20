package com.picooc.widget.picoocProgress;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ScrollText extends RelativeLayout
  implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener
{
  private AnimatorSet animator;
  private float duringPerPx;
  boolean flag = true;
  ObjectAnimator goleAnim;
  private int goleNum;
  private int initNum;
  private int loopAnimDuring = 4000;
  private onAnimationEnd onAnimationEnd;
  ObjectAnimator repeatAnim;
  private ScrollView scrollView = (ScrollView)((RelativeLayout)LayoutInflater.from(getContext()).inflate(2130903126, this)).findViewById(2131100082);
  private int textHeight;
  private float textSize;
  private TextView textView;
  private int textWidth;

  public ScrollText(Context paramContext)
  {
    this(paramContext, null);
  }

  public ScrollText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.scrollView.setVerticalScrollBarEnabled(false);
    this.scrollView.setHorizontalScrollBarEnabled(false);
    this.scrollView.setVerticalFadingEdgeEnabled(false);
    this.textView = ((TextView)this.scrollView.findViewById(2131099808));
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.picoocScrollTextview);
    this.textSize = localTypedArray.getDimension(0, 60.0F);
    this.textView.setTextSize(0, this.textSize);
    this.textView.setTextColor(localTypedArray.getColor(1, -16777216));
    localTypedArray.recycle();
    Typeface localTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/BRI293.TTF");
    this.textView.setTypeface(localTypeface);
    this.textWidth = ((int)this.textView.getPaint().measureText("9"));
  }

  private void createAnimation()
  {
    StringBuffer localStringBuffer = new StringBuffer(this.initNum);
    for (int i = 1; ; i++)
    {
      if (i > 10)
      {
        this.textView.setText(localStringBuffer.toString());
        TextView localTextView1 = this.textView;
        float[] arrayOfFloat1 = new float[2];
        arrayOfFloat1[0] = 0.0F;
        arrayOfFloat1[1] = (-(10 * this.textHeight));
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(localTextView1, "y", arrayOfFloat1).setDuration((int)(1.4D * this.loopAnimDuring));
        localObjectAnimator.setRepeatCount(0);
        localObjectAnimator.setInterpolator(new AccelerateInterpolator(0.8F));
        localObjectAnimator.addUpdateListener(this);
        TextView localTextView2 = this.textView;
        float[] arrayOfFloat2 = new float[2];
        arrayOfFloat2[0] = 0.0F;
        arrayOfFloat2[1] = (-(10 * this.textHeight));
        this.repeatAnim = ObjectAnimator.ofFloat(localTextView2, "y", arrayOfFloat2).setDuration(this.loopAnimDuring);
        this.repeatAnim.setRepeatCount(-1);
        this.repeatAnim.setRepeatMode(1);
        this.repeatAnim.setInterpolator(new LinearInterpolator());
        this.goleAnim = ObjectAnimator.ofFloat(this.textView, "y", new float[] { 0.0F, 0.0F }).setDuration(this.loopAnimDuring);
        this.goleAnim.setInterpolator(new LinearInterpolator());
        this.goleAnim.setRepeatCount(0);
        this.animator = new AnimatorSet();
        this.animator.play(this.repeatAnim).after(localObjectAnimator);
        this.animator.play(this.goleAnim).after(this.repeatAnim);
        this.animator.addListener(this);
        return;
      }
      int j = i + this.initNum;
      if (j >= 10)
        j -= 10;
      localStringBuffer.append("\n" + j);
    }
  }

  public onAnimationEnd getOnAnimationEnd()
  {
    return this.onAnimationEnd;
  }

  public void marginTop(float paramFloat)
  {
    ((LinearLayout.LayoutParams)getLayoutParams()).setMargins(0, (int)paramFloat, 0, 0);
  }

  public void onAnimationCancel(Animator paramAnimator)
  {
  }

  public void onAnimationEnd(Animator paramAnimator)
  {
    if (this.onAnimationEnd != null)
      this.onAnimationEnd.animationEnd(this);
  }

  public void onAnimationRepeat(Animator paramAnimator)
  {
  }

  public void onAnimationStart(Animator paramAnimator)
  {
  }

  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (this.textHeight == 0)
      this.textHeight = this.textView.getHeight();
    setMeasuredDimension(this.textWidth, this.textHeight);
  }

  public void setOnAnimationEnd(onAnimationEnd paramonAnimationEnd)
  {
    this.onAnimationEnd = paramonAnimationEnd;
  }

  public void setText(int paramInt)
  {
    this.initNum = paramInt;
    this.textView.setText(paramInt);
  }

  public void setTextColor(int paramInt)
  {
    this.textView.setTextColor(paramInt);
  }

  public void setTextSize(int paramInt, float paramFloat)
  {
    this.textView.setTextSize(paramInt, paramFloat);
    this.textWidth = ((int)this.textView.getPaint().measureText("9"));
  }

  public void startRepeatScroll(int paramInt)
  {
    Log.i("picooc", "textHeight=" + this.textHeight);
    this.loopAnimDuring = paramInt;
    this.duringPerPx = (this.loopAnimDuring / (10.0F * this.textHeight));
    createAnimation();
    this.animator.start();
    this.scrollView.invalidate();
  }

  public void startScrollNoRepeat(int paramInt)
  {
    if (paramInt > 9)
      paramInt -= 10;
    this.goleNum = paramInt;
    StringBuffer localStringBuffer;
    if (paramInt > this.initNum)
    {
      (paramInt - this.initNum);
      localStringBuffer = new StringBuffer(this.initNum);
    }
    for (int i = 1; ; i++)
    {
      if (i > 10)
      {
        this.textView.setText(localStringBuffer.toString());
        return;
        (paramInt + 10 - this.initNum);
        break;
      }
      int j = i + this.initNum;
      if (j >= 10)
        j -= 10;
      localStringBuffer.append("\n" + j);
    }
  }

  public void stopScrollText(int paramInt)
  {
    if (paramInt > 9)
      paramInt -= 10;
    if ((this.repeatAnim != null) && (!this.repeatAnim.isRunning()))
    {
      this.repeatAnim.setPropertyName("y");
      this.repeatAnim.setDuration(0L);
      this.repeatAnim.setRepeatCount(0);
      this.repeatAnim.setFloatValues(new float[] { 0.0F, 0.0F });
    }
    int i;
    float f1;
    float f2;
    if (paramInt > this.initNum)
    {
      i = paramInt - this.initNum;
      f1 = ((Float)this.repeatAnim.getAnimatedValue("y")).floatValue();
      f2 = i * -this.textHeight;
      if (f1 - f2 < this.textHeight)
        break label266;
      this.goleAnim.setPropertyName("y");
      this.goleAnim.setFloatValues(new float[] { f1, f2 });
      this.goleAnim.setInterpolator(new OvershootInterpolator(0.4F));
      this.goleAnim.setDuration(3 * (int)(this.duringPerPx * (f1 - f2)));
      this.repeatAnim.cancel();
    }
    while (true)
    {
      Log.i("picooc", "目标数字=" + paramInt + " 起始坐标=" + f1 + "   目标坐标=" + f2);
      this.goleNum = paramInt;
      this.initNum = paramInt;
      return;
      i = paramInt + 10 - this.initNum;
      break;
      label266: this.repeatAnim.setRepeatCount(0);
      this.goleAnim.setPropertyName("y");
      this.goleAnim.setFloatValues(new float[] { 0.0F, f2 });
      this.goleAnim.setInterpolator(new OvershootInterpolator(0.3F));
      this.goleAnim.setDuration(3 * (int)(this.duringPerPx * -f2));
      f1 = 0.0F;
    }
  }

  static abstract interface onAnimationEnd
  {
    public abstract void animationEnd(View paramView);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ScrollText
 * JD-Core Version:    0.6.2
 */
