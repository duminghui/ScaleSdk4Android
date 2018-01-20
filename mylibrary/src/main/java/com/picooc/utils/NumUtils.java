package com.picooc.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NumUtils
{
  private static DecimalFormat f = new DecimalFormat("#0.0");

  static
  {
    f.setRoundingMode(RoundingMode.HALF_UP);
  }

  public static float round(float paramFloat, int paramInt1, int paramInt2)
  {
    return new BigDecimal(paramFloat).setScale(paramInt1, paramInt2).floatValue();
  }

  public static int roundFloatToInt(float paramFloat)
  {
    return new BigDecimal(paramFloat).setScale(0, 4).intValue();
  }

  public static String roundValue(double paramDouble)
  {
    return f.format(paramDouble);
  }

  public static String roundValue(float paramFloat)
  {
    return f.format(paramFloat);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     NumUtils
 * JD-Core Version:    0.6.2
 */
