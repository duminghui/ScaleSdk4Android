package com.picooc.domain;

public enum ComponentBodyTypeEnum
{
  private int index;
  private String name;

  static
  {
    FAT = new ComponentBodyTypeEnum("FAT", 1, "脂肪率", 2);
    BONE = new ComponentBodyTypeEnum("BONE", 2, "骨量", 3);
    WEIGHT = new ComponentBodyTypeEnum("WEIGHT", 3, "体重", 4);
    INFAT = new ComponentBodyTypeEnum("INFAT", 4, "内脏脂肪指数", 5);
    WATER = new ComponentBodyTypeEnum("WATER", 5, "水分率", 6);
    PROTEIN = new ComponentBodyTypeEnum("PROTEIN", 6, "蛋白质", 7);
    BMR = new ComponentBodyTypeEnum("BMR", 7, "基础代谢率", 8);
    BMI = new ComponentBodyTypeEnum("BMI", 8, "bmi", 9);
    TX = new ComponentBodyTypeEnum("TX", 9, "体型", 10);
    ComponentBodyTypeEnum[] arrayOfComponentBodyTypeEnum = new ComponentBodyTypeEnum[10];
    arrayOfComponentBodyTypeEnum[0] = MUSCLE;
    arrayOfComponentBodyTypeEnum[1] = FAT;
    arrayOfComponentBodyTypeEnum[2] = BONE;
    arrayOfComponentBodyTypeEnum[3] = WEIGHT;
    arrayOfComponentBodyTypeEnum[4] = INFAT;
    arrayOfComponentBodyTypeEnum[5] = WATER;
    arrayOfComponentBodyTypeEnum[6] = PROTEIN;
    arrayOfComponentBodyTypeEnum[7] = BMR;
    arrayOfComponentBodyTypeEnum[8] = BMI;
    arrayOfComponentBodyTypeEnum[9] = TX;
  }

  private ComponentBodyTypeEnum(String arg3, int arg4)
  {
    Object localObject;
    this.name = localObject;
    int i;
    this.index = i;
  }

  public static ComponentBodyTypeEnum getEmnuByIndex(int paramInt)
  {
    ComponentBodyTypeEnum[] arrayOfComponentBodyTypeEnum = values();
    int i = arrayOfComponentBodyTypeEnum.length;
    for (int j = 0; ; j++)
    {
      ComponentBodyTypeEnum localComponentBodyTypeEnum;
      if (j >= i)
        localComponentBodyTypeEnum = WEIGHT;
      do
      {
        return localComponentBodyTypeEnum;
        localComponentBodyTypeEnum = arrayOfComponentBodyTypeEnum[j];
      }
      while (localComponentBodyTypeEnum.getIndex() == paramInt);
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
 * Qualified Name:     ComponentBodyTypeEnum
 * JD-Core Version:    0.6.2
 */
