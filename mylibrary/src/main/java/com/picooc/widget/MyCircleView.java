package com.picooc.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyCircleView extends View
{
  private Paint mOvalPaint;
  private int mOval_b;
  private int mOval_l;
  private int mOval_r;
  private int mOval_t;
  private int mStrokeWidth = 2;
  private int padding = 3;

  public MyCircleView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initRocketView();
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.MyCircleView);
    this.mOval_l = localTypedArray.getDimensionPixelOffset(0, this.padding);
    this.mOval_t = localTypedArray.getDimensionPixelOffset(1, this.padding);
    this.mOval_r = localTypedArray.getDimensionPixelOffset(2, 100);
    this.mOval_b = localTypedArray.getDimensionPixelOffset(3, 100);
    localTypedArray.recycle();
  }

  private void initRocketView()
  {
    this.mOvalPaint = new Paint();
    this.mOvalPaint.setAntiAlias(true);
    this.mOvalPaint.setColor(-16776961);
    this.mOvalPaint.setStyle(Paint.Style.FILL);
    this.mOvalPaint.setStrokeWidth(this.mStrokeWidth);
    setPadding(this.padding, this.padding, this.padding, this.padding);
  }

  private int measureHeight(int paramInt)
  {
    int i = View.MeasureSpec.getMode(paramInt);
    int j = View.MeasureSpec.getSize(paramInt);
    int k;
    if (i == 1073741824)
      k = j;
    do
    {
      return k;
      k = this.mOval_b + getPaddingTop() + getPaddingBottom();
    }
    while (i != -2147483648);
    return Math.min(k, j);
  }

  private int measureWidth(int paramInt)
  {
    int i = View.MeasureSpec.getMode(paramInt);
    int j = View.MeasureSpec.getSize(paramInt);
    int k;
    if (i == 1073741824)
      k = j;
    do
    {
      return k;
      k = this.mOval_r + getPaddingLeft() + getPaddingRight();
    }
    while (i != -2147483648);
    return Math.min(k, j);
  }

  @SuppressLint({"DrawAllocation"})
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    paramCanvas.drawColor(0);
    paramCanvas.drawOval(new RectF(this.mOval_l, this.mOval_t, this.mOval_r, this.mOval_b), this.mOvalPaint);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(measureWidth(paramInt1), measureHeight(paramInt2));
  }

  public void setCircle(int paramInt1, int paramInt2)
  {
    setOvalRect(0, 0, paramInt1, paramInt1, paramInt2);
  }

  public void setOvalRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    this.mOval_l = (paramInt1 + this.padding);
    this.mOval_t = (paramInt2 + this.padding);
    this.mOval_r = paramInt3;
    this.mOval_b = paramInt4;
    this.mOvalPaint.setColor(paramInt5);
    requestLayout();
    invalidate();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     MyCircleView
 * JD-Core Version:    0.6.2
 */
