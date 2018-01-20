package com.picooc.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import org.achartengine.tools.ModUtils;

public class StickerSpan extends ImageSpan
{
  Context context;

  public StickerSpan(Drawable paramDrawable, int paramInt, Context paramContext)
  {
    super(paramDrawable, paramInt);
    this.context = paramContext;
  }

  public void draw(Canvas paramCanvas, CharSequence paramCharSequence, int paramInt1, int paramInt2, float paramFloat, int paramInt3, int paramInt4, int paramInt5, Paint paramPaint)
  {
    Drawable localDrawable = getDrawable();
    paramCanvas.save();
    int i = paramInt5 - localDrawable.getBounds().bottom - ModUtils.dip2px(this.context, 2.0F);
    int j;
    if (this.mVerticalAlignment == 1)
      j = paramCharSequence.length();
    for (int k = 0; ; k++)
    {
      if (k >= j);
      while (true)
      {
        paramCanvas.translate(paramFloat, i);
        localDrawable.draw(paramCanvas);
        paramCanvas.restore();
        return;
        if (!Character.isLetterOrDigit(paramCharSequence.charAt(k)))
          break;
        i -= paramPaint.getFontMetricsInt().descent;
      }
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     StickerSpan
 * JD-Core Version:    0.6.2
 */
