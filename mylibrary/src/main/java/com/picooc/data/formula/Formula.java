package com.picooc.data.formula;

public class Formula
{
  static
  {
    System.loadLibrary("dataFormula");
  }

  public static native float[] calculateBasicDataByImpedance(int paramInt1, float paramFloat1, int paramInt2, float paramFloat2, int paramInt3);

  public static native float[] calculateBasicDataByImpedanceOldVersion(int paramInt1, float paramFloat1, int paramInt2, float paramFloat2, int paramInt3);

  public static native int calculateImpedanceByBodyFat(int paramInt1, float paramFloat1, int paramInt2, float paramFloat2, float paramFloat3);

  public static native float transformOldDataToNewData(int paramInt1, float paramFloat1, int paramInt2, float paramFloat2, float paramFloat3);
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     Formula
 * JD-Core Version:    0.6.2
 */
