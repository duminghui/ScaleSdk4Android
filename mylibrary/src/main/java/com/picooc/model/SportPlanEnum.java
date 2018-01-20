package com.picooc.model;

public enum SportPlanEnum
{
  private int index;
  private String name;

  static
  {
    WY = new SportPlanEnum("WY", 2, 3, "无氧运动");
    SportPlanEnum[] arrayOfSportPlanEnum = new SportPlanEnum[3];
    arrayOfSportPlanEnum[0] = DQD;
    arrayOfSportPlanEnum[1] = YY;
    arrayOfSportPlanEnum[2] = WY;
  }

  private SportPlanEnum(int arg3, String arg4)
  {
    int i;
    this.index = i;
  }

  public static SportPlanEnum getEmnuByIndex(int paramInt)
  {
    SportPlanEnum[] arrayOfSportPlanEnum = values();
    int i = arrayOfSportPlanEnum.length;
    for (int j = 0; ; j++)
    {
      SportPlanEnum localSportPlanEnum;
      if (j >= i)
        localSportPlanEnum = DQD;
      do
      {
        return localSportPlanEnum;
        localSportPlanEnum = arrayOfSportPlanEnum[j];
      }
      while (localSportPlanEnum.getIndex() == paramInt);
    }
  }

  public int getIndex()
  {
    return this.index;
  }

  public String getName()
  {
    return this.name;
  }

  public String getNameByIndex(int paramInt)
  {
    return getEmnuByIndex(paramInt).getName();
  }

  public void setIndex(int paramInt)
  {
    this.index = paramInt;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SportPlanEnum
 * JD-Core Version:    0.6.2
 */
