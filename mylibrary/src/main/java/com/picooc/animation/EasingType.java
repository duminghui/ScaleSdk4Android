package com.picooc.animation;

public class EasingType
{
  public static enum Type
  {
    static
    {
      INOUT = new Type("INOUT", 2);
      Type[] arrayOfType = new Type[3];
      arrayOfType[0] = IN;
      arrayOfType[1] = OUT;
      arrayOfType[2] = INOUT;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     EasingType
 * JD-Core Version:    0.6.2
 */
