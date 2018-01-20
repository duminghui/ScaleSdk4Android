package com.picooc.widget.picoocProgress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class ArcProgress extends View
{
  private final int PER_FRAME_DURING_TIME = 4;
  private final float PER_FRAME_GO_DEGREE = 0.4F;
  private int alpha = 255;
  private int blurMaskWidth = 6;
  private int fg_color;
  final Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default:
      case 1:
      case 2:
      }
      while (true)
      {
        super.handleMessage(paramAnonymousMessage);
        return;
        if ((ArcProgress.this.mSweep >= ArcProgress.this.schedule) && (ArcProgress.this.isExcute))
        {
          ArcProgress.this.isExcute = false;
          ArcProgress.this.timer.cancel();
          ArcProgress.this.picoocTimeTask.cancel();
          ArcProgress.this.timer.purge();
          ArcProgress.this.progressHandler.sendEmptyMessage(201);
          return;
        }
        if (ArcProgress.this.isExcute)
        {
          ArcProgress localArcProgress2 = ArcProgress.this;
          localArcProgress2.mSweep = (0.4F + localArcProgress2.mSweep);
          ArcProgress.this.invalidate();
          continue;
          ArcProgress localArcProgress1 = ArcProgress.this;
          localArcProgress1.alpha = (-7 + localArcProgress1.alpha);
          if (ArcProgress.this.alpha < 0)
          {
            ArcProgress.this.timer.cancel();
            return;
          }
          ArcProgress.this.mArcPaint.setAlpha(ArcProgress.this.alpha);
          ArcProgress.this.invalidate();
        }
      }
    }
  };
  private boolean isExcute = true;
  private Paint mArcBGPaint;
  private Paint mArcPaint;
  private RectF mOval;
  private float mSpeedArcWidth_bg = 1.0F;
  private float mSpeedArcWidth_fg = 8.0F;
  private float mSweep = 0.0F;
  private int maxDegree = 360;
  private float paintWight = 0.0F;
  private float percent;
  private PicoocTimeTask picoocTimeTask;
  private Handler progressHandler;
  private int schedule;
  private boolean showFg = true;
  private int startDegree;
  private Timer timer;

  public ArcProgress(Context paramContext)
  {
    super(paramContext);
  }

  public ArcProgress(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, -1);
  }

  public ArcProgress(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.arcsProgress);
    this.fg_color = localTypedArray.getColor(3, Color.argb(250, 250, 250, 250));
    this.startDegree = localTypedArray.getInteger(4, 0);
    this.percent = localTypedArray.getFloat(5, 0.0F);
    setPercent(this.percent);
    localTypedArray.recycle();
    this.mArcPaint = new Paint(1);
    this.mArcPaint.setStyle(Paint.Style.STROKE);
    this.mArcPaint.setStrokeWidth(this.mSpeedArcWidth_fg);
    this.mArcPaint.setColor(this.fg_color);
    BlurMaskFilter localBlurMaskFilter = new BlurMaskFilter(5.0F, BlurMaskFilter.Blur.INNER);
    this.mArcPaint.setMaskFilter(localBlurMaskFilter);
    this.mArcBGPaint = new Paint(1);
    this.mArcBGPaint.setStyle(Paint.Style.STROKE);
    this.mArcBGPaint.setStrokeWidth(this.mSpeedArcWidth_bg);
    this.mArcBGPaint.setColor(Color.argb(50, 255, 255, 255));
  }

  public ArcProgress(Context paramContext, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Handler paramHandler, boolean paramBoolean, float paramFloat)
  {
    this(paramContext);
    this.showFg = paramBoolean;
    this.fg_color = paramInteger1.intValue();
    this.startDegree = paramInteger2.intValue();
    this.maxDegree = paramInteger3.intValue();
    this.progressHandler = paramHandler;
    this.mSpeedArcWidth_bg = getResources().getDimension(2131230738);
    if (paramBoolean)
    {
      this.mSpeedArcWidth_fg = getResources().getDimension(2131230739);
      this.blurMaskWidth = ((int)this.mSpeedArcWidth_fg);
      if (paramFloat != 0.0F)
        this.mSpeedArcWidth_fg = paramFloat;
      this.mArcPaint = new Paint(1);
      this.mArcPaint.setAntiAlias(true);
      this.mArcPaint.setStyle(Paint.Style.STROKE);
      this.mArcPaint.setStrokeWidth(this.mSpeedArcWidth_fg);
      this.mArcPaint.setColor(paramInteger1.intValue());
      BlurMaskFilter localBlurMaskFilter = new BlurMaskFilter(this.mSpeedArcWidth_fg / 2.0F, BlurMaskFilter.Blur.SOLID);
      this.mArcPaint.setMaskFilter(localBlurMaskFilter);
    }
    this.mArcBGPaint = new Paint(1);
    this.mArcBGPaint.setStyle(Paint.Style.STROKE);
    this.mArcBGPaint.setStrokeWidth(this.mSpeedArcWidth_bg);
    this.mArcBGPaint.setColor(Color.argb(80, 255, 255, 255));
  }

  public ArcProgress(Context paramContext, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, boolean paramBoolean, int paramInt)
  {
    this(paramContext);
    this.fg_color = paramInteger1.intValue();
    this.startDegree = paramInteger2.intValue();
    this.maxDegree = paramInteger3.intValue();
    this.mSpeedArcWidth_bg = getResources().getDimension(2131230738);
    if (this.showFg)
    {
      this.mSpeedArcWidth_fg = getResources().getDimension(2131230739);
      this.blurMaskWidth = paramInt;
      this.mArcPaint = new Paint(1);
      this.mArcPaint.setAntiAlias(true);
      this.mArcPaint.setStyle(Paint.Style.STROKE);
      this.mArcPaint.setStrokeWidth(this.mSpeedArcWidth_fg);
      this.mArcPaint.setColor(paramInteger1.intValue());
      BlurMaskFilter localBlurMaskFilter = new BlurMaskFilter(this.mSpeedArcWidth_fg / 2.0F, BlurMaskFilter.Blur.SOLID);
      this.mArcPaint.setMaskFilter(localBlurMaskFilter);
    }
    this.mArcBGPaint = new Paint(1);
    this.mArcBGPaint.setStyle(Paint.Style.STROKE);
    this.mArcBGPaint.setStrokeWidth(this.mSpeedArcWidth_bg);
    this.mArcBGPaint.setColor(Color.argb(80, 255, 255, 255));
  }

  private void drawSpeed(Canvas paramCanvas)
  {
    paramCanvas.drawArc(this.mOval, this.startDegree, this.maxDegree, false, this.mArcBGPaint);
    if (this.showFg)
      paramCanvas.drawArc(this.mOval, this.startDegree, this.mSweep, false, this.mArcPaint);
  }

  public float getSweep()
  {
    return this.mSweep;
  }

  public void initProgress(float paramFloat)
  {
    setPercent(paramFloat);
    this.mSweep = this.schedule;
    invalidate();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    drawSpeed(paramCanvas);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt2 + (paramInt3 - paramInt1));
    if (paramBoolean)
    {
      RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)getLayoutParams();
      if (localLayoutParams != null)
      {
        localLayoutParams.height = getWidth();
        setLayoutParams(localLayoutParams);
      }
    }
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.mOval = new RectF(0 + this.blurMaskWidth, 0 + this.blurMaskWidth, paramInt1 - this.blurMaskWidth, paramInt1 - this.blurMaskWidth);
  }

  public void reDraw(int paramInt1, int paramInt2)
  {
    this.startDegree = paramInt1;
    this.maxDegree = paramInt2;
    this.showFg = true;
  }

  public void setBackGroundLineColor(int paramInt)
  {
    this.mArcBGPaint.setColor(paramInt);
  }

  public void setFontGroundColor(int paramInt)
  {
    this.mArcPaint.setColor(paramInt);
    this.fg_color = paramInt;
  }

  public void setMessage(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    invalidate();
  }

  public void setPaintColor(int paramInt)
  {
    this.mArcPaint.setColor(paramInt);
    invalidate();
  }

  public void setPaintWight(float paramFloat)
  {
    this.mArcPaint.setStrokeWidth(paramFloat);
    invalidate();
  }

  public void setPercent(float paramFloat)
  {
    if (paramFloat >= 100.0F)
      paramFloat = 100.0F;
    this.percent = paramFloat;
    this.schedule = ((int)(paramFloat / 100.0F * this.maxDegree));
  }

  public void setmArcBGPaintColor(int paramInt)
  {
    this.mArcBGPaint.setColor(paramInt);
    invalidate();
  }

  public int startProgress(float paramFloat)
  {
    setPercent(paramFloat);
    this.mSweep = 0.0F;
    this.alpha = 255;
    this.mArcPaint.setAlpha(this.alpha);
    this.timer = new Timer(true);
    this.picoocTimeTask = new PicoocTimeTask();
    this.timer.schedule(this.picoocTimeTask, 0L, 4L);
    this.isExcute = true;
    return (int)(4.0F * (this.schedule / 0.4F));
  }

  public void startProgressReduceAlpha()
  {
    if (this.mSweep > 0.0F)
    {
      this.timer = new Timer(true);
      this.timer.schedule(new ReduceAlphaTimeTask(), 0L, 20L);
    }
  }

  class PicoocTimeTask extends TimerTask
  {
    PicoocTimeTask()
    {
    }

    public void run()
    {
      Message localMessage = new Message();
      localMessage.what = 1;
      ArcProgress.this.handler.sendMessage(localMessage);
    }
  }

  class ReduceAlphaTimeTask extends TimerTask
  {
    ReduceAlphaTimeTask()
    {
    }

    public void run()
    {
      Message localMessage = new Message();
      localMessage.what = 2;
      ArcProgress.this.handler.sendMessage(localMessage);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ArcProgress
 * JD-Core Version:    0.6.2
 */
