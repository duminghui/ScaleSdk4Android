package com.picooc.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class DashedLine extends View
{
  private final String namespace = "http://com.smartmap.driverbook";

  public DashedLine(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    Paint localPaint = new Paint();
    localPaint.setStyle(Paint.Style.STROKE);
    localPaint.setColor(-12303292);
    Path localPath = new Path();
    localPath.moveTo(0.0F, 5.0F);
    localPath.lineTo(10.0F, 480.0F);
    localPaint.setPathEffect(new DashPathEffect(new float[] { 2.0F, 2.0F, 2.0F, 2.0F }, 1.0F));
    paramCanvas.drawPath(localPath, localPaint);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     DashedLine
 * JD-Core Version:    0.6.2
 */
