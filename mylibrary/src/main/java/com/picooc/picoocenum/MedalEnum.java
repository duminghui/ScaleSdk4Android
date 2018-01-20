package com.picooc.picoocenum;

public enum MedalEnum
{
  private int index;
  private String name;

  static
  {
    Medal_HBW = new MedalEnum("Medal_HBW", 1, "汉堡王", 2);
    Medal_JRN = new MedalEnum("Medal_JRN", 2, "肌肉男", 3);
    Medal_HLZJ = new MedalEnum("Medal_HLZJ", 3, "哈雷重机", 4);
    Medal_GLD = new MedalEnum("Medal_GLD", 4, "骨力多", 5);
    Medal_QTWW = new MedalEnum("Medal_QTWW", 5, "晴天娃娃", 6);
    Medal_XRK = new MedalEnum("Medal_XRK", 6, "向日葵", 7);
    Medal_CDB = new MedalEnum("Medal_CDB", 7, "充电宝", 8);
    Medal_XXY = new MedalEnum("Medal_XXY", 8, "星星眼", 9);
    Medal_WDM = new MedalEnum("Medal_WDM", 9, "五顶帽子戏法", 10);
    Medal_JJLL = new MedalEnum("Medal_JJLL", 10, "进击的猎犬", 11);
    Medal_XHHL = new MedalEnum("Medal_XHHL", 11, "巡回的灰狼", 12);
    Medal_LBYS = new MedalEnum("Medal_LBYS", 12, "猎豹勇士", 13);
    Medal_YJS = new MedalEnum("Medal_YJS", 13, "眼镜蛇征服者", 14);
    Medal_SZW = new MedalEnum("Medal_SZW", 14, "狮子王", 15);
    Medal_WCBB = new MedalEnum("Medal_WCBB", 15, "维c宝宝", 16);
    Medal_NMPS = new MedalEnum("Medal_NMPS", 16, "柠檬捕手", 17);
    Medal_LSXF = new MedalEnum("Medal_LSXF", 17, "绿色旋风", 18);
    Medal_LHZS = new MedalEnum("Medal_LHZS", 18, "乐活战士", 19);
    Medal_SGZFZ = new MedalEnum("Medal_SGZFZ", 19, "果蔬征服者", 20);
    MedalEnum[] arrayOfMedalEnum = new MedalEnum[20];
    arrayOfMedalEnum[0] = Medal_ZPR;
    arrayOfMedalEnum[1] = Medal_HBW;
    arrayOfMedalEnum[2] = Medal_JRN;
    arrayOfMedalEnum[3] = Medal_HLZJ;
    arrayOfMedalEnum[4] = Medal_GLD;
    arrayOfMedalEnum[5] = Medal_QTWW;
    arrayOfMedalEnum[6] = Medal_XRK;
    arrayOfMedalEnum[7] = Medal_CDB;
    arrayOfMedalEnum[8] = Medal_XXY;
    arrayOfMedalEnum[9] = Medal_WDM;
    arrayOfMedalEnum[10] = Medal_JJLL;
    arrayOfMedalEnum[11] = Medal_XHHL;
    arrayOfMedalEnum[12] = Medal_LBYS;
    arrayOfMedalEnum[13] = Medal_YJS;
    arrayOfMedalEnum[14] = Medal_SZW;
    arrayOfMedalEnum[15] = Medal_WCBB;
    arrayOfMedalEnum[16] = Medal_NMPS;
    arrayOfMedalEnum[17] = Medal_LSXF;
    arrayOfMedalEnum[18] = Medal_LHZS;
    arrayOfMedalEnum[19] = Medal_SGZFZ;
  }

  private MedalEnum(String arg3, int arg4)
  {
    Object localObject;
    this.name = localObject;
    int i;
    this.index = i;
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
 * Qualified Name:     MedalEnum
 * JD-Core Version:    0.6.2
 */
