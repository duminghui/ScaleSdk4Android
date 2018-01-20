package com.picooc.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class ClipView extends View
{
  private static float mHeight = 0.0F;
  private static float mWidth = 0.0F;
  public static final int x = 5;
  private float nHight = 0.0F;
  private float nWidth = 0.0F;

  public ClipView(Context paramContext, float paramFloat1, float paramFloat2)
  {
    super(paramContext);
    mWidth = paramFloat1;
    mHeight = paramFloat2;
    this.nHight = ((mWidth - 10.0F) * mHeight / mWidth);
    this.nWidth = (mWidth - 10.0F);
  }

  public ClipView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public ClipView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public static float getNheight()
  {
    return (mWidth - 10.0F) * mHeight / mWidth;
  }

  public static float oldbili(int paramInt1, int paramInt2)
  {
    return paramInt1 / paramInt2;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int i = getWidth();
    int j = getHeight();
    Paint localPaint = new Paint();
    localPaint.setColor(-1442840576);
    paramCanvas.drawRect(0.0F, 0.0F, i, (j - this.nHight) / 2.0F, localPaint);
    paramCanvas.drawRect(0.0F, (j - this.nHight) / 2.0F, 5.0F, j - (j - this.nHight) / 2.0F, localPaint);
    paramCanvas.drawRect(i - 5, (j - this.nHight) / 2.0F, i, j - (j - this.nHight) / 2.0F, localPaint);
    paramCanvas.drawRect(0.0F, j - (j - this.nHight) / 2.0F, i, j, localPaint);
    localPaint.setColor(-1426063361);
    localPaint.setStyle(Paint.Style.STROKE);
    paramCanvas.drawRect(5.0F, (j - this.nHight) / 2.0F, i - 5, j - (j - this.nHight) / 2.0F, localPaint);
  }

  public void setHeightAndWight(float paramFloat1, float paramFloat2)
  {
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ClipView
 * JD-Core Version:    0.6.2
 */
