package com.picooc.widget.anyncImageView;

import android.graphics.Bitmap;

public class ChainImageProcessor
  implements ImageProcessor
{
  ImageProcessor[] mProcessors;

  public ChainImageProcessor(ImageProcessor[] paramArrayOfImageProcessor)
  {
    this.mProcessors = paramArrayOfImageProcessor;
  }

  public Bitmap processImage(Bitmap paramBitmap)
  {
    ImageProcessor[] arrayOfImageProcessor = this.mProcessors;
    int i = arrayOfImageProcessor.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return paramBitmap;
      paramBitmap = arrayOfImageProcessor[j].processImage(paramBitmap);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ChainImageProcessor
 * JD-Core Version:    0.6.2
 */
