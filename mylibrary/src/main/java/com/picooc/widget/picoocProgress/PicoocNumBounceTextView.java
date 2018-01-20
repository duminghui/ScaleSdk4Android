package com.picooc.widget.picoocProgress;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.picooc.utils.ColorUtils;

public class PicoocNumBounceTextView extends LinearLayout
{
  public static final int BMI_MODE = 2;
  public static final int BODY_FAT_MODE = 3;
  public static final int WEIGHT_MODE = 1;
  public int MODE;
  private float a = 3.0F;
  private int animationCount = 0;
  private ScrollText baiScrollText;
  private int duringTime;
  private ScrollText fenScrollText;
  private ScrollText geScrollText;
  private TextView kg;
  private LinearLayout linearLayout;
  private ScrollText.onAnimationEnd listener = new ScrollText.onAnimationEnd()
  {
    public void animationEnd(View paramAnonymousView)
    {
      PicoocNumBounceTextView localPicoocNumBounceTextView = PicoocNumBounceTextView.this;
      localPicoocNumBounceTextView.animationCount = (-1 + localPicoocNumBounceTextView.animationCount);
      if ((PicoocNumBounceTextView.this.animationCount == 0) && (PicoocNumBounceTextView.this.onAnimationEnd != null))
      {
        PicoocNumBounceTextView.this.onAnimationEnd.animationEnd(PicoocNumBounceTextView.this.MODE);
        PicoocNumBounceTextView.this.kg.setTextColor(PicoocNumBounceTextView.this.textColor);
        PicoocNumBounceTextView.this.point.setTextColor(PicoocNumBounceTextView.this.textColor);
        PicoocNumBounceTextView.this.persentSign.setTextColor(PicoocNumBounceTextView.this.textColor);
      }
      switch (PicoocNumBounceTextView.this.MODE)
      {
      default:
        return;
      case 1:
        PicoocNumBounceTextView.this.animationCount = 3;
        return;
      case 2:
        PicoocNumBounceTextView.this.animationCount = 3;
        return;
      case 3:
      }
      PicoocNumBounceTextView.this.animationCount = 3;
    }
  };
  private onAnimationEnd onAnimationEnd;
  private TextView persentSign;
  private TextView point;
  private ScrollText shiScrollText;
  private float smallTextSize;
  private int textColor;
  private float textSize;

  public PicoocNumBounceTextView(Context paramContext)
  {
    super(paramContext, null);
  }

  public PicoocNumBounceTextView(Context paramContext, int paramInt1, float paramFloat, int paramInt2, int paramInt3)
  {
    this(paramContext);
    findView();
    this.textColor = paramInt1;
    this.textSize = paramFloat;
    this.smallTextSize = (0.55F * paramFloat);
    this.MODE = paramInt3;
    this.duringTime = paramInt2;
    initView();
  }

  public PicoocNumBounceTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    findView();
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.picoocScrollTextview);
    this.textColor = localTypedArray.getColor(1, -16777216);
    this.textSize = localTypedArray.getDimension(0, 30.0F);
    this.smallTextSize = (0.55F * this.textSize);
    this.MODE = localTypedArray.getInteger(3, 1);
    this.duringTime = localTypedArray.getInteger(2, 4000);
    localTypedArray.recycle();
    initView();
  }

  private void findView()
  {
    this.linearLayout = ((LinearLayout)LayoutInflater.from(getContext()).inflate(2130903100, this));
    this.baiScrollText = ((ScrollText)this.linearLayout.findViewById(2131099969));
    this.shiScrollText = ((ScrollText)this.linearLayout.findViewById(2131099970));
    this.geScrollText = ((ScrollText)this.linearLayout.findViewById(2131099971));
    this.fenScrollText = ((ScrollText)this.linearLayout.findViewById(2131099717));
    this.kg = ((TextView)findViewById(2131099973));
    this.point = ((TextView)findViewById(2131099972));
    this.persentSign = ((TextView)findViewById(2131099974));
    this.baiScrollText.setOnAnimationEnd(this.listener);
    this.shiScrollText.setOnAnimationEnd(this.listener);
    this.geScrollText.setOnAnimationEnd(this.listener);
    this.fenScrollText.setOnAnimationEnd(this.listener);
  }

  private void initView()
  {
    switch (this.MODE)
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      this.shiScrollText.setTextColor(this.textColor);
      this.geScrollText.setTextColor(this.textColor);
      this.fenScrollText.setTextColor(this.textColor);
      this.shiScrollText.setTextSize(0, this.textSize);
      this.geScrollText.setTextSize(0, this.textSize);
      this.fenScrollText.setTextSize(0, this.smallTextSize);
      this.fenScrollText.marginTop((this.textSize - this.smallTextSize) / this.a);
      this.point.setTextSize(0, 0.8F * this.smallTextSize);
      ((LinearLayout.LayoutParams)this.point.getLayoutParams()).setMargins(0, (int)((this.textSize - this.smallTextSize) / this.a), 0, 0);
      this.point.setTextColor(this.textColor);
      return;
      this.animationCount = 3;
      this.persentSign.setVisibility(8);
      this.kg.setTextSize(0, this.smallTextSize);
      ((LinearLayout.LayoutParams)this.kg.getLayoutParams()).setMargins(0, (int)((this.textSize - this.smallTextSize) / this.a), 0, 0);
      Typeface localTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/BRI293.TTF");
      this.kg.setTypeface(localTypeface);
      this.kg.setTextColor(this.textColor);
      this.baiScrollText.setVisibility(8);
      this.baiScrollText.setTextColor(this.textColor);
      this.baiScrollText.setTextSize(0, this.textSize);
      continue;
      this.animationCount = 3;
      this.baiScrollText.setVisibility(8);
      this.kg.setVisibility(8);
      this.persentSign.setVisibility(8);
      continue;
      this.animationCount = 3;
      this.kg.setVisibility(8);
      this.baiScrollText.setVisibility(8);
      this.persentSign.setTextSize(0, this.smallTextSize);
      ((LinearLayout.LayoutParams)this.persentSign.getLayoutParams()).setMargins(0, (int)((this.textSize - this.smallTextSize) / this.a), 0, 0);
      this.persentSign.setTextColor(this.textColor);
    }
  }

  public onAnimationEnd getOnAnimationEnd()
  {
    return this.onAnimationEnd;
  }

  public void setOnAnimationEnd(onAnimationEnd paramonAnimationEnd)
  {
    this.onAnimationEnd = paramonAnimationEnd;
  }

  public void setText(float paramFloat)
  {
    int i = (int)paramFloat;
    int j = (int)(10.0F * paramFloat - i * 10);
    int k = i % 10;
    int m = (i - i % 10) % 100 / 10;
    int n = (i - m) / 100;
    if (n > 0)
      this.baiScrollText.setVisibility(0);
    while (true)
    {
      this.baiScrollText.setText(n);
      this.shiScrollText.setText(m);
      this.geScrollText.setText(k);
      this.fenScrollText.setText(j);
      return;
      this.baiScrollText.setVisibility(8);
    }
  }

  public void setTextAlpha(float paramFloat)
  {
    int i = ColorUtils.getAlphaColor(paramFloat, this.textColor);
    this.baiScrollText.setTextColor(i);
    this.shiScrollText.setTextColor(i);
    this.geScrollText.setTextColor(i);
    this.fenScrollText.setTextColor(i);
    this.kg.setTextColor(i);
    this.point.setTextColor(i);
    this.persentSign.setTextColor(i);
  }

  public void startAlphaAnimation(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this, "alpha", new float[] { paramFloat1, paramFloat2 });
    localObjectAnimator.setDuration(2000L);
    localObjectAnimator.start();
  }

  public void startScroll()
  {
    this.shiScrollText.startRepeatScroll((int)(0.9D * this.duringTime));
    this.geScrollText.startRepeatScroll((int)(1.2D * this.duringTime));
    this.fenScrollText.startRepeatScroll((int)(0.8D * this.duringTime));
    invalidate();
  }

  public void startScrollNoRepeat(float paramFloat)
  {
    int i = (int)paramFloat;
    int j = (int)(10.0F * paramFloat - i * 10);
    int k = i % 10;
    int m = (i - i % 10) % 100 / 10;
    int n = (i - m) / 100;
    if ((this.MODE == 1) && (n > 0))
      this.baiScrollText.stopScrollText(n);
    this.shiScrollText.startScrollNoRepeat(m);
    this.geScrollText.startScrollNoRepeat(k);
    this.fenScrollText.startScrollNoRepeat(j);
  }

  public void stopScroll(float paramFloat)
  {
    int i = (int)paramFloat;
    int j = (int)(10.0F * (paramFloat - i));
    int k = i % 10;
    int m = (i - i % 10) % 100 / 10;
    int n = (i - m) / 100;
    if ((this.MODE == 1) && (n > 0))
      this.baiScrollText.stopScrollText(n);
    this.shiScrollText.stopScrollText(m);
    this.geScrollText.stopScrollText(k);
    this.fenScrollText.stopScrollText(j);
  }

  public static abstract interface onAnimationEnd
  {
    public abstract void animationEnd(int paramInt);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PicoocNumBounceTextView
 * JD-Core Version:    0.6.2
 */
