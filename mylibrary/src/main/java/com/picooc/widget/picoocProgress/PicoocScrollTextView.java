package com.picooc.widget.picoocProgress;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.picooc.utils.ColorUtils;
import com.picooc.widget.MtimerTask;

public class PicoocScrollTextView extends LinearLayout
{
  public static final int BMI_MODE = 2;
  public static final int BODY_FAT_MODE = 3;
  public static final int WEIGHT_MODE = 1;
  public int MODE;
  private float a = 3.0F;
  private ScrollText baiScrollText;
  private int duringTime;
  private ScrollText fenScrollText;
  private ScrollText geScrollText;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      if (PicoocScrollTextView.this.isStop.booleanValue())
      {
        PicoocScrollTextView.this.baiScrollText.setVisibility(8);
        int i = (int)(10.0D * Math.random());
        PicoocScrollTextView.this.shiScrollText.setText(i);
        int j = (int)(10.0D * Math.random());
        PicoocScrollTextView.this.fenScrollText.setText(j);
        int k = (int)(10.0D * Math.random());
        PicoocScrollTextView.this.geScrollText.setText(k);
      }
    }
  };
  Boolean isStop = Boolean.valueOf(true);
  private TextView kg;
  private LinearLayout linearLayout;
  LinearLayout.LayoutParams ll;
  private onAnimationEnd onAnimationEnd;
  private TextView persentSign;
  private TextView point;
  private ScrollText shiScrollText;
  private float smallTextSize;
  private int textColor;
  private float textSize;
  MtimerTask timtask;

  public PicoocScrollTextView(Context paramContext)
  {
    super(paramContext, null);
  }

  public PicoocScrollTextView(Context paramContext, int paramInt1, float paramFloat, int paramInt2, int paramInt3)
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

  public PicoocScrollTextView(Context paramContext, AttributeSet paramAttributeSet)
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
    this.linearLayout = ((LinearLayout)LayoutInflater.from(getContext()).inflate(2130903103, this));
    this.baiScrollText = ((ScrollText)this.linearLayout.findViewById(2131099969));
    this.shiScrollText = ((ScrollText)this.linearLayout.findViewById(2131099970));
    this.geScrollText = ((ScrollText)this.linearLayout.findViewById(2131099971));
    this.fenScrollText = ((ScrollText)this.linearLayout.findViewById(2131099717));
    this.kg = ((TextView)findViewById(2131099973));
    this.point = ((TextView)findViewById(2131099972));
    this.persentSign = ((TextView)findViewById(2131099974));
    this.timtask = new MtimerTask(this.handler, 40, Boolean.valueOf(true), 0);
  }

  private void initView()
  {
    this.persentSign.setVisibility(8);
    this.kg.setTextSize(0, this.smallTextSize);
    this.ll = ((LinearLayout.LayoutParams)this.kg.getLayoutParams());
    this.ll.setMargins(0, (int)((this.textSize - this.smallTextSize) / this.a), 0, 0);
    Typeface localTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/BRI293.TTF");
    this.kg.setTypeface(localTypeface);
    this.kg.setTextColor(this.textColor);
    this.baiScrollText.setVisibility(8);
    this.baiScrollText.setTextColor(this.textColor);
    this.baiScrollText.setTextSize(0, this.textSize);
    this.shiScrollText.setTextColor(this.textColor);
    this.geScrollText.setTextColor(this.textColor);
    this.fenScrollText.setTextColor(this.textColor);
    this.shiScrollText.setTextSize(0, this.textSize);
    this.geScrollText.setTextSize(0, this.textSize);
    this.fenScrollText.setTextSize(0, this.smallTextSize);
    this.fenScrollText.marginTop((this.textSize - this.smallTextSize) / this.a);
    this.point.setTextSize(0, 0.8F * this.smallTextSize);
    this.ll = ((LinearLayout.LayoutParams)this.point.getLayoutParams());
    this.ll.setMargins(0, (int)((this.textSize - this.smallTextSize) / this.a), 0, 0);
    this.point.setTextColor(this.textColor);
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
    if (paramFloat <= 1.0F)
    {
      setAlpha(0.0F);
      return;
    }
    setAlpha(1.0F);
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
    this.isStop = Boolean.valueOf(true);
    this.timtask.startTimer();
    invalidate();
  }

  public void stopNumberBounce()
  {
    this.isStop = Boolean.valueOf(false);
    this.timtask.stopTimer();
  }

  public void stopNumberBounceOnNum(float paramFloat)
  {
    stopNumberBounce();
    setText(paramFloat);
  }

  public void stopScroll(float paramFloat)
  {
  }

  public static abstract interface onAnimationEnd
  {
    public abstract void animationEnd(int paramInt);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PicoocScrollTextView
 * JD-Core Version:    0.6.2
 */
