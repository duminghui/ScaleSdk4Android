package com.picooc.contwheel;

public class CotrlItemsRange
{
  private int count;
  private int first;

  public CotrlItemsRange()
  {
    this(0, 0);
  }

  public CotrlItemsRange(int paramInt1, int paramInt2)
  {
    this.first = paramInt1;
    this.count = paramInt2;
  }

  public boolean contains(int paramInt)
  {
    return (paramInt >= getFirst()) && (paramInt <= getLast());
  }

  public int getCount()
  {
    return this.count;
  }

  public int getFirst()
  {
    return this.first;
  }

  public int getLast()
  {
    return -1 + (getFirst() + getCount());
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     CotrlItemsRange
 * JD-Core Version:    0.6.2
 */
