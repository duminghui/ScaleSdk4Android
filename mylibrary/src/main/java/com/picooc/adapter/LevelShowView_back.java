package com.picooc.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import com.picooc.oldhumen.Age.FAT;
import com.umeng.socom.Log;
import java.io.PrintStream;
import org.achartengine.tools.ModUtils;

public class LevelShowView_back extends View
{
  private static int HEIGH = 5;
  private static int RECT_PADDING_TOP = 20;
  private Drawable icon;
  private int[] mColors;
  private float mCurrentLevel;
  private int mCurrentLevelPaddingBottom = 12;
  private int mCurrentLevelPaddingLeft = 14;
  private int mCurrentLevelPaddingRight = 14;
  private int mCurrentLevelPaddingTop = 12;
  private String[] mLevelTexts;
  private float[] mLevels;
  private boolean mNoIndicator;
  private int mOffset = 10;
  private Paint mPaint;
  private float mTextSizeFloat;
  private float mTextSizeString;
  private int mWidth;

  public LevelShowView_back(Context paramContext)
  {
    super(paramContext);
    initTextSize(paramContext);
  }

  public LevelShowView_back(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initTextSize(paramContext);
  }

  public LevelShowView_back(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initTextSize(paramContext);
  }

  private int drawColorRects(Canvas paramCanvas, int[] paramArrayOfInt, int paramInt)
  {
    Rect[] arrayOfRect = new Rect[paramArrayOfInt.length];
    int i = getLen(paramArrayOfInt.length);
    int j = 0;
    int k = 0;
    if (k >= arrayOfRect.length);
    for (int i1 = 0; ; i1++)
    {
      if (i1 >= arrayOfRect.length)
      {
        return paramInt + HEIGH;
        int m = paramInt + HEIGH;
        int n = j + i;
        arrayOfRect[k] = new Rect(j, paramInt, n, m);
        j = n + this.mOffset;
        k++;
        break;
      }
      this.mPaint = new Paint();
      this.mPaint.setColor(paramArrayOfInt[i1]);
      paramCanvas.drawRect(arrayOfRect[i1], this.mPaint);
    }
  }

  private int drawCurrentLevel(Canvas paramCanvas, float paramFloat, int paramInt)
  {
    int i = getDesXInMiddle(paramFloat);
    if (this.icon == null)
    {
      Log.e("LevelShowView", "icon is need for LevelShowView");
      return 0;
    }
    int j = i - this.icon.getIntrinsicWidth() / 2;
    int k = paramInt - this.icon.getIntrinsicHeight();
    int m = j + this.icon.getIntrinsicWidth();
    this.icon.setBounds(j, k, m, paramInt);
    this.icon.draw(paramCanvas);
    if (!this.mNoIndicator)
    {
      Bitmap localBitmap1 = BitmapFactory.decodeResource(getResources(), 2130838083);
      Paint localPaint = new Paint();
      localPaint.setColor(Color.parseColor("#dedbde"));
      localPaint.setTextSize(this.mTextSizeFloat);
      localPaint.setFlags(1);
      localPaint.setTypeface(ModUtils.getTypeface(getContext()));
      int n = (int)localPaint.measureText(paramFloat + "%");
      int i1 = (int)(localPaint.getFontMetrics().bottom - localPaint.getFontMetrics().top);
      Bitmap localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, n + this.mCurrentLevelPaddingLeft + this.mCurrentLevelPaddingRight, i1 + this.mCurrentLevelPaddingTop + this.mCurrentLevelPaddingBottom, true);
      new Canvas(localBitmap2).drawText(paramFloat + "%", localBitmap2.getWidth() / 2 - n / 2, localBitmap2.getHeight() / 2, localPaint);
      paramCanvas.drawBitmap(localBitmap2, i - localBitmap2.getWidth() / 2, paramInt - this.icon.getIntrinsicHeight() - localBitmap2.getHeight(), localPaint);
    }
    return 0;
  }

  private int drawLevelTexts(Canvas paramCanvas, String[] paramArrayOfString, int paramInt)
  {
    int i = getLen(paramArrayOfString.length);
    int j = 0;
    Paint localPaint = new Paint();
    localPaint.setColor(Color.parseColor("#dedbde"));
    localPaint.setTextSize(this.mTextSizeString);
    localPaint.setTypeface(ModUtils.getTypeface(getContext()));
    int k = (int)(localPaint.getFontMetrics().bottom - localPaint.getFontMetrics().top);
    for (int m = 0; ; m++)
    {
      if (m >= paramArrayOfString.length)
        return (int)(paramInt + (localPaint.getFontMetrics().bottom - localPaint.getFontMetrics().top));
      int n = (int)localPaint.measureText(paramArrayOfString[m]);
      int i1 = (i - n) / 2;
      int i2 = j + i1;
      paramCanvas.drawText(paramArrayOfString[m], i2, paramInt + k + HEIGH / 2 + RECT_PADDING_TOP, localPaint);
      j = i1 + (i2 + n + this.mOffset);
    }
  }

  private int drawLevels(Canvas paramCanvas, float[] paramArrayOfFloat, int paramInt)
  {
    int i = paramInt - HEIGH / 2;
    int j = getLen(1 + paramArrayOfFloat.length);
    this.mPaint = new Paint();
    this.mPaint.setColor(-1);
    this.mPaint.setTextSize(this.mTextSizeFloat);
    this.mPaint.setTypeface(ModUtils.getTypeface(getContext()));
    int k = 0;
    for (int m = 0; ; m++)
    {
      if (m >= paramArrayOfFloat.length)
        return i + 10;
      int n = (int)this.mPaint.measureText(paramArrayOfFloat[m] + "%");
      int i1 = k + (j - n / 2);
      paramCanvas.drawText(paramArrayOfFloat[m] + "%", i1, i - 5, this.mPaint);
      k = i1 + n / 2 + this.mOffset;
    }
  }

  private int getDesXByRate(float paramFloat)
  {
    if (-1 + this.mColors.length == this.mLevels.length)
    {
      int i = getLen(this.mColors.length) + this.mOffset / 2;
      int j;
      if (paramFloat < this.mLevels[0])
        j = (int)(paramFloat * (i / this.mLevels[0]));
      do
      {
        return j;
        if (paramFloat < this.mLevels[1])
          return i + (int)(i / (int)(this.mLevels[1] - this.mLevels[0]) * (paramFloat - this.mLevels[0]));
        if (paramFloat > 50.0F)
          paramFloat = 50.099998F;
        j = i * 2 + (int)(i / (50.0F - this.mLevels[1]) * (paramFloat - this.mLevels[1]));
        System.out.println("22222222222desX " + j + ":" + getMeasuredWidth());
      }
      while (j <= -10 + getMeasuredWidth());
      return -46 + getMeasuredWidth();
    }
    throw new RuntimeException("指示条和区间文字不匹配");
  }

  private int getDesXInMiddle(float paramFloat)
  {
    int i = getLen(this.mColors.length) + this.mOffset / 2;
    if (paramFloat < this.mLevels[0])
      return i / 2;
    if (paramFloat < this.mLevels[1])
      return (int)(1.5F * i);
    return (int)(2.5F * i);
  }

  private float getIndicatorX(Bitmap paramBitmap1, Bitmap paramBitmap2, int paramInt)
  {
    int i = getMeasuredWidth();
    int j = paramBitmap2.getWidth() / 2;
    if (paramInt < j)
      return 0.0F;
    if (paramInt > i - paramBitmap2.getWidth() / 2)
      return i - paramBitmap2.getWidth();
    return paramInt - (j - this.mCurrentLevelPaddingLeft);
  }

  private int getLen(int paramInt)
  {
    return (getMeasuredWidth() - this.mOffset * (paramInt - 1)) / paramInt;
  }

  private void initTextSize(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
    this.mWidth = localDisplayMetrics.widthPixels;
    System.out.println("initTextSize " + localDisplayMetrics.widthPixels + ":" + localDisplayMetrics.heightPixels);
    Resources localResources = getResources();
    this.mTextSizeFloat = localResources.getDimension(2131230777);
    this.mTextSizeString = localResources.getDimension(2131230778);
    RECT_PADDING_TOP = (int)localResources.getDimension(2131230779);
    this.mOffset = ((int)localResources.getDimension(2131230781));
    this.mCurrentLevelPaddingLeft = ((int)localResources.getDimension(2131230876));
    this.mCurrentLevelPaddingRight = ((int)localResources.getDimension(2131230877));
    this.mCurrentLevelPaddingTop = ((int)localResources.getDimension(2131230878));
    this.mCurrentLevelPaddingBottom = ((int)localResources.getDimension(2131230879));
  }

  private void setLevelColor(int[] paramArrayOfInt)
  {
    this.mColors = paramArrayOfInt;
  }

  private void setLevelText(String[] paramArrayOfString)
  {
    this.mLevelTexts = paramArrayOfString;
  }

  public int getMiddleH()
  {
    return -2 + (getMeasuredHeight() / 2 - HEIGH / 2);
  }

  public void noIndicator()
  {
    this.mNoIndicator = true;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int i = getMiddleH();
    System.out.println("onDraw----~~~~~~~~~~~~~~~~~~~~~~~~~" + i);
    if (this.mLevels != null)
      drawLevels(paramCanvas, this.mLevels, i);
    if (this.mColors != null)
      drawColorRects(paramCanvas, this.mColors, i);
    if (this.mLevelTexts != null)
      drawLevelTexts(paramCanvas, this.mLevelTexts, i);
    if (this.mCurrentLevel > 0.0F)
      drawCurrentLevel(paramCanvas, this.mCurrentLevel, i);
  }

  public void resText(int paramInt)
  {
    this.mTextSizeString = paramInt;
  }

  public void setCurrentLevel(float paramFloat)
  {
    setLevelColor(Age.FAT.COLORS);
    setLevelText(Age.FAT.TEXT);
    this.mCurrentLevel = paramFloat;
    this.icon = getResources().getDrawable(Age.FAT.fatResId(paramFloat, getContext()));
  }

  public void setLevelFloat(float[] paramArrayOfFloat)
  {
    this.mLevels = paramArrayOfFloat;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LevelShowView_back
 * JD-Core Version:    0.6.2
 */
