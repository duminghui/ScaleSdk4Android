package com.picooc.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class HorzhiLine_Translucent extends View
{
  private float endX;
  private float endY;
  private Rect mRect;
  private final String namespace = "http://com.smartmap.driverbook";
  private float startX;
  private float startY;

  public HorzhiLine_Translucent(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    Paint localPaint = new Paint();
    localPaint.setStyle(Paint.Style.STROKE);
    localPaint.setColor(1090519039);
    Path localPath = new Path();
    localPath.moveTo(0.0F, 10.0F);
    localPath.lineTo(1000.0F, 10.0F);
    localPaint.setPathEffect(new DashPathEffect(new float[] { 6.0F, 6.0F, 6.0F, 6.0F }, 1.0F));
    paramCanvas.drawPath(localPath, localPaint);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     HorzhiLine_Translucent
 * JD-Core Version:    0.6.2
 */
