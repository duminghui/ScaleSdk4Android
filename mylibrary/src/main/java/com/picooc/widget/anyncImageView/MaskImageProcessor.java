package com.picooc.widget.anyncImageView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

public class MaskImageProcessor
  implements ImageProcessor
{
  private static final int CUSTOM = 1;
  private static final int RECTANGLE = 2;
  private final Paint mFillPaint = new Paint(1);
  private Bitmap mMaskBitmap;
  private final Paint mMaskPaint = new Paint(1);
  private final Path mPath = new Path();
  private float[] mRadiiArray;
  private float mRadius;
  private final RectF mRect = new RectF();
  private int mShape;

  public MaskImageProcessor(float paramFloat)
  {
    init();
    this.mShape = 2;
    if (paramFloat < 0.0F)
      paramFloat = 0.0F;
    this.mRadius = paramFloat;
  }

  public MaskImageProcessor(Bitmap paramBitmap)
  {
    init();
    this.mShape = 1;
    this.mMaskBitmap = paramBitmap;
  }

  public MaskImageProcessor(float[] paramArrayOfFloat)
  {
    init();
    this.mShape = 2;
    this.mRadiiArray = paramArrayOfFloat;
    if (paramArrayOfFloat == null)
      this.mRadius = 0.0F;
  }

  private void init()
  {
    this.mFillPaint.setColor(-65536);
    this.mMaskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
  }

  public Bitmap processImage(Bitmap paramBitmap)
  {
    if (paramBitmap == null)
      return null;
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    this.mRect.set(0.0F, 0.0F, i, j);
    Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    switch (this.mShape)
    {
    default:
      if (this.mRadiiArray != null)
      {
        this.mPath.reset();
        this.mPath.addRoundRect(this.mRect, this.mRadiiArray, Path.Direction.CW);
        localCanvas.drawPath(this.mPath, this.mFillPaint);
      }
      break;
    case 1:
    }
    while (true)
    {
      localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, this.mMaskPaint);
      return localBitmap;
      localCanvas.drawBitmap(this.mMaskBitmap, 0.0F, 0.0F, this.mFillPaint);
      continue;
      float f1 = this.mRadius;
      float f2 = 0.5F * Math.min(i, j);
      if (f1 > f2)
        f1 = f2;
      localCanvas.drawRoundRect(this.mRect, f1, f1, this.mFillPaint);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     MaskImageProcessor
 * JD-Core Version:    0.6.2
 */
