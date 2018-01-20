package com.picooc.emun;

public enum SharedEntityTypeEmun
{
  private int index;
  private String name;

  static
  {
    SharedEntityFatRatioMan = new SharedEntityTypeEmun("SharedEntityFatRatioMan", 1, 10002, "汉堡王");
    SharedEntityMuscleRatio = new SharedEntityTypeEmun("SharedEntityMuscleRatio", 2, 10003, "肌肉男");
    SharedEntityBoneMass = new SharedEntityTypeEmun("SharedEntityBoneMass", 3, 10004, "骨力多");
    SharedEntityBasalMetabolicRate = new SharedEntityTypeEmun("SharedEntityBasalMetabolicRate", 4, 10005, "哈雷重机");
    SharedEntityFatRatioWomanHeight = new SharedEntityTypeEmun("SharedEntityFatRatioWomanHeight", 5, 10006, "派大星");
    SharedEntityFatRatioWomanLow = new SharedEntityTypeEmun("SharedEntityFatRatioWomanLow", 6, 10007, "万人迷");
    SharedEntityTypeEmun[] arrayOfSharedEntityTypeEmun = new SharedEntityTypeEmun[7];
    arrayOfSharedEntityTypeEmun[0] = SharedEntityWeight;
    arrayOfSharedEntityTypeEmun[1] = SharedEntityFatRatioMan;
    arrayOfSharedEntityTypeEmun[2] = SharedEntityMuscleRatio;
    arrayOfSharedEntityTypeEmun[3] = SharedEntityBoneMass;
    arrayOfSharedEntityTypeEmun[4] = SharedEntityBasalMetabolicRate;
    arrayOfSharedEntityTypeEmun[5] = SharedEntityFatRatioWomanHeight;
    arrayOfSharedEntityTypeEmun[6] = SharedEntityFatRatioWomanLow;
  }

  private SharedEntityTypeEmun(int arg3, String arg4)
  {
    int i;
    this.index = i;
    Object localObject;
    this.name = localObject;
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
 * Qualified Name:     SharedEntityTypeEmun
 * JD-Core Version:    0.6.2
 */
