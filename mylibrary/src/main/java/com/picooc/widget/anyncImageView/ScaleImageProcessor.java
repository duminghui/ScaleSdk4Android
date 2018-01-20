package com.picooc.widget.anyncImageView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;

public class ScaleImageProcessor
  implements ImageProcessor
{
  private int mHeight;
  private final Matrix mMatrix = new Matrix();
  private ImageView.ScaleType mScaleType;
  private final RectF mTempDst = new RectF();
  private final RectF mTempSrc = new RectF();
  private int mWidth;

  public ScaleImageProcessor(int paramInt1, int paramInt2, ImageView.ScaleType paramScaleType)
  {
    this.mWidth = paramInt1;
    this.mHeight = paramInt2;
    this.mScaleType = paramScaleType;
  }

  public Bitmap processImage(Bitmap paramBitmap)
  {
    if (paramBitmap == null)
      return null;
    this.mMatrix.reset();
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    switch ($SWITCH_TABLE$android$widget$ImageView$ScaleType()[this.mScaleType.ordinal()])
    {
    default:
      this.mTempSrc.set(0.0F, 0.0F, i, j);
      this.mTempDst.set(0.0F, 0.0F, this.mWidth, this.mHeight);
      this.mMatrix.setRectToRect(this.mTempSrc, this.mTempDst, Matrix.ScaleToFit.FILL);
    case 2:
    case 1:
      while (true)
      {
        Bitmap localBitmap = Bitmap.createBitmap(this.mWidth, this.mHeight, Bitmap.Config.ARGB_8888);
        new Canvas(localBitmap).drawBitmap(paramBitmap, this.mMatrix, null);
        return localBitmap;
        float f4 = 0.0F;
        float f5;
        if (i * this.mHeight > j * this.mWidth)
          f5 = this.mHeight / j;
        for (float f6 = 0.5F * (this.mWidth - f5 * i); ; f6 = 0.0F)
        {
          this.mMatrix.setScale(f5, f5);
          this.mMatrix.postTranslate((int)(f6 + 0.5F), (int)(f4 + 0.5F));
          break;
          f5 = this.mWidth / i;
          f4 = 0.5F * (this.mHeight - f5 * j);
        }
        int k = (int)(0.5F + 0.5F * (this.mWidth - i));
        int m = (int)(0.5F + 0.5F * (this.mHeight - j));
        this.mMatrix.setTranslate(k, m);
      }
    case 3:
    }
    if ((i <= this.mWidth) && (j <= this.mHeight));
    for (float f1 = 1.0F; ; f1 = Math.min(this.mWidth / i, this.mHeight / j))
    {
      float f2 = (int)(0.5F + 0.5F * (this.mWidth - f1 * i));
      float f3 = (int)(0.5F + 0.5F * (this.mHeight - f1 * j));
      this.mMatrix.setScale(f1, f1);
      this.mMatrix.postTranslate(f2, f3);
      break;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ScaleImageProcessor
 * JD-Core Version:    0.6.2
 */
