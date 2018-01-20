package com.picooc.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import org.achartengine.tools.ModUtils;

public class VerticalLine extends View
{
  Context mContext;

  public VerticalLine(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
  }

  public VerticalLine(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    Paint localPaint = new Paint();
    localPaint.setStyle(Paint.Style.STROKE);
    localPaint.setColor(Color.parseColor("#30ffffff"));
    localPaint.setStrokeWidth(ModUtils.dip2px(this.mContext, 2.0F));
    Path localPath = new Path();
    localPath.moveTo(0.0F, 0.0F);
    localPath.lineTo(0.0F, 580.0F);
    localPaint.setPathEffect(new DashPathEffect(new float[] { 5.0F, 5.0F, 5.0F, 5.0F }, 1.0F));
    paramCanvas.drawPath(localPath, localPaint);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     VerticalLine
 * JD-Core Version:    0.6.2
 */
