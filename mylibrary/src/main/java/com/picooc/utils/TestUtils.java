package com.picooc.utils;

import java.util.Random;

public class TestUtils
{
  public static double[] getRandomNo(int paramInt1, int paramInt2, int paramInt3)
  {
    double[] arrayOfDouble = new double[paramInt1];
    for (int i = 0; ; i++)
    {
      if (i >= paramInt1)
        return arrayOfDouble;
      arrayOfDouble[i] = (paramInt3 + new Random().nextInt(paramInt2) % (1 + (paramInt2 - paramInt3)));
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     TestUtils
 * JD-Core Version:    0.6.2
 */
