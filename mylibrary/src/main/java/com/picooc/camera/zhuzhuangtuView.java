package com.picooc.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.picooc.MyApplication;
import org.achartengine.tools.ModUtils;

public class zhuzhuangtuView extends View
{
  public static final int x = 5;
  private MyApplication app;
  public Context mContext;
  int[] num;

  public zhuzhuangtuView(Context paramContext, float paramFloat1, float paramFloat2, int[] paramArrayOfInt)
  {
    super(paramContext);
    this.mContext = paramContext;
    this.num = paramArrayOfInt;
  }

  public zhuzhuangtuView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public zhuzhuangtuView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public static int getPaintColor(int paramInt)
  {
    int i = Color.parseColor("#ff2000");
    if ((paramInt >= 0) && (paramInt <= 24))
      i = Color.parseColor("#ff2000");
    do
    {
      return i;
      if ((paramInt > 24) && (paramInt <= 44))
        return Color.parseColor("#ff5a00");
      if ((paramInt > 44) && (paramInt <= 59))
        return Color.parseColor("#ff8a00");
      if ((paramInt > 59) && (paramInt <= 79))
        return Color.parseColor("#ffba00");
      if ((paramInt > 79) && (paramInt <= 89))
        return Color.parseColor("#fffc00");
      if ((paramInt > 89) && (paramInt <= 99))
        return Color.parseColor("#84ff00");
    }
    while (paramInt != 100);
    return Color.parseColor("#1eff00");
  }

  public Paint draXuXianPaint(int paramInt)
  {
    Paint localPaint = new Paint(1);
    localPaint.setAntiAlias(true);
    localPaint.setStyle(Paint.Style.STROKE);
    localPaint.setStrokeWidth(1.0F);
    localPaint.setColor(paramInt);
    localPaint.setPathEffect(new DashPathEffect(new float[] { 5.0F, 5.0F, 5.0F, 5.0F }, 1.0F));
    return localPaint;
  }

  public Paint getPaint(int paramInt1, int paramInt2)
  {
    Paint localPaint = new Paint();
    localPaint.setColor(paramInt1);
    localPaint.setTextSize(ModUtils.dip2px(this.mContext, 8.0F));
    localPaint.setStrokeWidth(paramInt2);
    return localPaint;
  }

  public Path getPath(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    Path localPath = new Path();
    localPath.moveTo(paramFloat1, paramFloat2);
    localPath.lineTo(paramFloat3, paramFloat4);
    return localPath;
  }

  @SuppressLint({"DrawAllocation"})
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int i = getWidth();
    int j = getHeight();
    float f = 2.0F * (j / 3.0F) - j / 15.6F;
    paramCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(), 2130837807), i / 18.0F, j / 30.0F, new Paint());
    paramCanvas.drawPath(getPath(i / 18 + i / 28, j / 15.4F, i - i / 9, j / 15.4F), draXuXianPaint(Color.parseColor("#1eff00")));
    paramCanvas.drawText("太棒了", i - i / 9, j / 13, getPaint(Color.parseColor("#1eff00"), 1));
    paramCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(), 2130837808), i / 18.0F + i / 150.0F, j / 28.0F + 0.4F * f, new Paint());
    paramCanvas.drawPath(getPath(i / 18.0F + i / 19.0F, 1.0F + (j / 15.4F + 0.4F * f), i - i / 9.0F, 1.0F + (j / 15.4F + 0.4F * f)), draXuXianPaint(Color.parseColor("#ffba00")));
    paramCanvas.drawText("刚及格", i - i / 9, j / 13.0F + 0.4F * f, getPaint(Color.parseColor("#ffba00"), 1));
    paramCanvas.drawLine(i / 18.0F + i / 150.0F, 2.0F * (j / 3.0F), i - i / 12.0F, 2.0F * (j / 3.0F), getPaint(-7829368, 5));
    paramCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(), 2130837826), i / 9, 2 * (j / 3) + j / 25, new Paint());
    paramCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(), 2130837783), i / 9.0F + i / 9.0F, 2.0F * (j / 3) + j / 25.0F, new Paint());
    paramCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(), 2130837809), 3.0F * (i / 9), 2.0F * (j / 3) + j / 25.0F, new Paint());
    paramCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(), 2130837782), 4.0F * (i / 9), 2.0F * (j / 3) + j / 25.0F, new Paint());
    paramCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(), 2130837817), 5.0F * (i / 9), 2.0F * (j / 3) + j / 25.0F, new Paint());
    paramCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(), 2130837749), 6.0F * (i / 9), 2.0F * (j / 3) + j / 25.0F, new Paint());
    paramCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(), 2130837771), 7.0F * (i / 9), 2.0F * (j / 3) + j / 25.0F, new Paint());
    paramCanvas.drawText("脂肪率", i / 9.0F - i / 140.0F, 2.0F * (j / 3) + j / 6.0F, getPaint(-1, 1));
    paramCanvas.drawText("肌肉率", 2.0F * (i / 9) - i / 140.0F, 2.0F * (j / 3) + j / 6.0F, getPaint(-1, 1));
    paramCanvas.drawText("内脏脂肪", 3.0F * (i / 9) - i / 50.0F, 2.0F * (j / 3) + j / 6.0F, getPaint(-1, 1));
    paramCanvas.drawText("基础代谢率", 4.0F * (i / 9) - i / 90.0F, 2.0F * (j / 3) + j / 6.0F, getPaint(-1, 1));
    paramCanvas.drawText("水分", 5.0F * (i / 9) + i / 110.0F, 2.0F * (j / 3) + j / 6.0F, getPaint(-1, 1));
    paramCanvas.drawText("蛋白质", 6.0F * (i / 9) - i / 140.0F, 2.0F * (j / 3) + j / 6.0F, getPaint(-1, 1));
    paramCanvas.drawText("骨量", 7.0F * (i / 9) + i / 140.0F, 2.0F * (j / 3) + j / 6.0F, getPaint(-1, 1));
    if (this.num[1] != 0)
    {
      paramCanvas.drawRect(i / 9.0F + i / 85.0F, j / 15.6F + f * (1.0F - this.num[1] / 100.0F), i / 9.0F + i / 85 + i / 28, 2.0F * (j / 3.0F) - j / 170.0F, getPaint(getPaintColor(this.num[1]), 1));
      if (this.num[2] == 0)
        break label1754;
      paramCanvas.drawRect(2.0F * (i / 9.0F) + i / 85.0F, j / 15.6F + f * (1.0F - this.num[2] / 100.0F), 2.0F * (i / 9.0F) + i / 85.0F + i / 28.0F, 2.0F * (j / 3.0F) - j / 170.0F, getPaint(getPaintColor(this.num[2]), 1));
      label1037: if (this.num[3] == 0)
        break label1829;
      paramCanvas.drawRect(3.0F * (i / 9.0F) + i / 85.0F, j / 15.6F + f * (1.0F - this.num[3] / 100.0F), 3.0F * (i / 9.0F) + i / 85.0F + i / 28.0F, 2.0F * (j / 3.0F) - j / 170.0F, getPaint(getPaintColor(this.num[3]), 1));
      label1132: if (this.num[4] == 0)
        break label1906;
      paramCanvas.drawRect(4.0F * (i / 9.0F) + i / 85.0F, j / 15.6F + f * (1.0F - this.num[4] / 100.0F), 4.0F * (i / 9.0F) + i / 85.0F + i / 28.0F, 2.0F * (j / 3.0F) - j / 170.0F, getPaint(getPaintColor(this.num[4]), 1));
      label1227: if (this.num[5] == 0)
        break label1983;
      paramCanvas.drawRect(5.0F * (i / 9.0F) + i / 85.0F, j / 15.6F + f * (1.0F - this.num[5] / 100.0F), 5.0F * (i / 9.0F) + i / 85.0F + i / 28.0F, 2.0F * (j / 3.0F) - j / 170.0F, getPaint(getPaintColor(this.num[5]), 1));
      label1322: if (this.num[6] == 0)
        break label2060;
      paramCanvas.drawRect(6.0F * (i / 9.0F) + i / 85.0F, j / 15.6F + f * (1.0F - this.num[6] / 100.0F), 6.0F * (i / 9.0F) + i / 85.0F + i / 28.0F, 2.0F * (j / 3.0F) - j / 170.0F, getPaint(getPaintColor(this.num[6]), 1));
      label1420: if (this.num[7] == 0)
        break label2137;
      paramCanvas.drawRect(7 * (i / 9) + i / 85.0F, j / 15.6F + f * (1.0F - this.num[7] / 100.0F), 7.0F * (i / 9.0F) + i / 85.0F + i / 28.0F, 2.0F * (j / 3.0F) - j / 170.0F, getPaint(getPaintColor(this.num[7]), 1));
    }
    while (true)
    {
      paramCanvas.save(31);
      Log.i("picooc", "num[1]==" + this.num[1] + "  90==" + 0.1F * f + "  80==" + 0.2F * f + "  70==" + 0.3F * f + " 60==" + 0.4F * f + "  50==" + 0.5F * f + "  40==" + 0.6F * f + " 30==" + 0.7F * f + " 20=" + 0.8F * f + " 10=" + 0.9F * f);
      paramCanvas.restore();
      return;
      paramCanvas.drawRect(i / 9.0F + i / 85.0F, j / 15.6F + f * (1.0F - 0.018F), i / 9.0F + i / 85.0F + i / 28, 2.0F * (j / 3.0F) - j / 170.0F, getPaint(getPaintColor(1), 1));
      break;
      label1754: paramCanvas.drawRect(2.0F * (i / 9.0F) + i / 85.0F, j / 15.6F + f * (1.0F - 0.018F), 2.0F * (i / 9.0F) + i / 85.0F + i / 28.0F, 2.0F * (j / 3.0F) - j / 170.0F, getPaint(getPaintColor(1), 1));
      break label1037;
      label1829: paramCanvas.drawRect(3.0F * (i / 9.0F) + i / 85.0F, j / 15.6F + f * (1.0F - 0.018F), 3.0F * (i / 9.0F) + i / 85.0F + i / 28.0F, 2.0F * (j / 3.0F) - j / 170.0F, getPaint(getPaintColor(1), 1));
      break label1132;
      label1906: paramCanvas.drawRect(4.0F * (i / 9.0F) + i / 85.0F, j / 15.6F + f * (1.0F - 0.018F), 4.0F * (i / 9.0F) + i / 85.0F + i / 28.0F, 2.0F * (j / 3.0F) - j / 170.0F, getPaint(getPaintColor(1), 1));
      break label1227;
      label1983: paramCanvas.drawRect(5.0F * (i / 9.0F) + i / 85.0F, j / 15.6F + f * (1.0F - 0.018F), 5.0F * (i / 9.0F) + i / 85.0F + i / 28.0F, 2.0F * (j / 3.0F) - j / 170.0F, getPaint(getPaintColor(1), 1));
      break label1322;
      label2060: paramCanvas.drawRect(6.0F * (i / 9.0F) + i / 85.0F, j / 15.6F + f * (1.0F - 0.018F), 6.0F * (i / 9.0F) + i / 85.0F + i / 28.0F, 2.0F * (j / 3.0F) - j / 170.0F, getPaint(getPaintColor(1), 1));
      break label1420;
      label2137: paramCanvas.drawRect(7 * (i / 9) + i / 85.0F, j / 15.6F + f * (1.0F - 0.018F), 7.0F * (i / 9.0F) + i / 85.0F + i / 28.0F, 2.0F * (j / 3.0F) - j / 170.0F, getPaint(getPaintColor(1), 1));
    }
  }

  public void setHeightAndWight(float paramFloat1, float paramFloat2)
  {
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     zhuzhuangtuView
 * JD-Core Version:    0.6.2
 */
