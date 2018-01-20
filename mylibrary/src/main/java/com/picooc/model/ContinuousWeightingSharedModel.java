package com.picooc.model;

import android.content.Context;
import com.picooc.db.OperationDB;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.RoleBin;
import com.picooc.utils.DateUtils;
import com.picooc.utils.NumUtils;
import java.util.HashMap;
import java.util.Random;

public class ContinuousWeightingSharedModel
{
  private int[] images;
  private boolean isCanShared = true;
  private boolean isGood = true;
  private Context mContext;
  private int mainFlag = 0;
  private String message1;
  private String message2;
  private String message3;
  private String[] messages;
  private BodyIndex newBodyIndex;
  private RoleBin role;
  private int showFlag = 2;
  private String title1;
  private String title2;

  public ContinuousWeightingSharedModel(Context paramContext, RoleBin paramRoleBin, BodyIndex paramBodyIndex1, BodyIndex paramBodyIndex2)
  {
    this.role = paramRoleBin;
    this.newBodyIndex = paramBodyIndex1;
    this.mContext = paramContext;
    HashMap localHashMap = getContinuousWeightChangeDatas(paramBodyIndex1);
    int i = ((Integer)localHashMap.get("continuousDays")).intValue();
    BodyIndex localBodyIndex1 = (BodyIndex)localHashMap.get("bodyIndex");
    if (localBodyIndex1 != null)
    {
      long l1 = DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(paramBodyIndex1.getTime(), paramBodyIndex2.getTime());
      if (l1 >= 1L)
        this.mainFlag = 1;
      label124: int j;
      label378: 
      do
      {
        int k;
        do
        {
          do
          {
            do
            {
              break label378;
              break label378;
              break label378;
              break label378;
              if (DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), paramBodyIndex1.getTime()) == 0L)
              {
                this.showFlag = 3;
                j = NumUtils.roundFloatToInt(10.0F * (paramBodyIndex1.getWeight() - localBodyIndex1.getWeight()));
                if (i < 2)
                  break label1310;
                this.title1 = "跟昨天比";
                BodyIndex localBodyIndex2 = (BodyIndex)localHashMap.get("yesterdayBody");
                k = NumUtils.roundFloatToInt(10.0F * (paramBodyIndex1.getWeight() - localBodyIndex2.getWeight()));
                if (paramRoleBin.getWeight_change_target() <= 0.0F)
                  break;
                if (j <= 0)
                  continue;
                this.message1 = ("连胖" + Math.abs(i) + "天了～");
                this.message2 = ("增重" + Math.abs(k / 10.0D) + "kg");
                this.message3 = ("连续" + i + "天啦，怒增" + j / 10.0D + "kg，fight！");
                String[] arrayOfString8 = new String[1];
                arrayOfString8[0] = (i + "天就增" + j / 10.0D + "kg，轻松吃吃轻松壮哦～");
                this.messages = arrayOfString8;
                if (paramRoleBin.getSex() != 1)
                  break label411;
              }
              for (this.images = new int[] { 2130837794 }; ; this.images = new int[] { 2130837758 })
              {
                this.isGood = true;
                return;
                if ((l1 != 0L) || (paramBodyIndex1.getId() != 0L))
                  break;
                this.mainFlag = 2;
                break;
                this.showFlag = 2;
                break label124;
              }
            }
            while (j >= 0);
            this.message1 = ("连瘦" + Math.abs(i) + "天了～");
            this.message2 = ("缩水" + Math.abs(k / 10.0D) + "kg");
            this.message3 = ("连续" + i + "天啦，怒降" + Math.abs(j / 10.0D) + "kg，hold住！");
            String[] arrayOfString7 = new String[1];
            arrayOfString7[0] = (i + "天缩水" + Math.abs(j / 10.0D) + "kg，真不是来炫耀的......\n想长胖容易吗我！");
            this.messages = arrayOfString7;
            if (paramRoleBin.getSex() == 1);
            for (this.images = new int[] { 2130837800 }; ; this.images = new int[] { 2130837764 })
            {
              this.isGood = false;
              return;
            }
          }
          while (paramRoleBin.getWeight_change_target() > 0.0F);
          if (j > 0)
          {
            this.message1 = ("连胖" + Math.abs(i) + "天啦");
            this.message2 = ("胖了" + Math.abs(k / 10.0D) + "kg");
            this.message3 = ("连胖" + Math.abs(i) + "天啦，暴涨" + j / 10.0D + "kg，hold住！");
            String[] arrayOfString6 = new String[4];
            arrayOfString6[0] = ("连胖" + Math.abs(i) + "天，一场说胖就胖的旅行......");
            arrayOfString6[1] = ("连胖" + Math.abs(i) + "天，" + j / 10.0D + "kg肥肉正在向我袭来，\n先让我去死一死......");
            arrayOfString6[2] = ("连胖" + Math.abs(i) + "天，长成球的节奏！");
            arrayOfString6[3] = (i + "天连胖" + j / 10.0D + "kg，小圆脸儿养成中......");
            this.messages = arrayOfString6;
            if (paramRoleBin.getSex() == 1);
            for (this.images = new int[] { 2130837797, 2130837799, 2130837798, 2130837804 }; ; this.images = new int[] { 2130837761, 2130837763, 2130837762, 2130837768 })
            {
              this.isGood = false;
              return;
            }
          }
        }
        while (j >= 0);
        this.message1 = ("连瘦" + Math.abs(i) + "天啦");
        this.message2 = ("瘦了" + Math.abs(k / 10.0D) + "kg");
        this.message3 = ("连瘦" + Math.abs(i) + "天啦，怒减" + Math.abs(j / 10.0D) + "kg，fight！");
        String[] arrayOfString5 = new String[4];
        arrayOfString5[0] = ("万万没想到！连瘦" + Math.abs(i) + "天不是梦！");
        arrayOfString5[1] = ("连瘦" + Math.abs(i) + "天，怒减" + Math.abs(j / 10.0D) + "kg！");
        arrayOfString5[2] = ("开启甩肉模式，连瘦" + Math.abs(i) + "天了哦～");
        arrayOfString5[3] = ("连瘦" + Math.abs(i) + "天，感觉掉肉一身轻呢～");
        this.messages = arrayOfString5;
        if (paramRoleBin.getSex() == 1);
        for (this.images = new int[] { 2130837787, 2130837791, 2130837790, 2130837795 }; ; this.images = new int[] { 2130837751, 2130837755, 2130837754, 2130837759 })
        {
          this.isGood = true;
          return;
        }
        long l2 = ((Long)localHashMap.get("days")).longValue();
        if (l2 == 1L)
          this.title1 = "跟昨天比";
        while (paramRoleBin.getWeight_change_target() > 0.0F)
          if (j > 0)
          {
            this.message1 = "长胖些啦~";
            this.message2 = ("增重" + j / 10.0D + "kg");
            String[] arrayOfString4 = new String[1];
            arrayOfString4[0] = ("又壮" + j / 10.0D + "kg，增重无难事，只怕是吃货～");
            this.messages = arrayOfString4;
            if (paramRoleBin.getSex() == 1)
            {
              this.images = new int[] { 2130837801 };
              return;
              this.title1 = "跟上次比";
              if (l2 >= 2L)
                this.title2 = (l2 + "天前(" + DateUtils.changeTimeStampToFormatTime(localBodyIndex1.getTime(), "MM月dd日") + ")");
            }
            else
            {
              this.images = new int[] { 2130837765 };
            }
          }
          else
          {
            if (j < 0)
            {
              this.message1 = "瘦回去啦~";
              this.message2 = ("缩水" + Math.abs(j / 10.0D) + "kg");
              String[] arrayOfString3 = new String[1];
              arrayOfString3[0] = ("想长肉想到cry，可又瘦了" + Math.abs(j / 10.0D) + "kg。。。");
              this.messages = arrayOfString3;
              if (paramRoleBin.getSex() == 1)
              {
                this.images = new int[] { 2130837789 };
                return;
              }
              this.images = new int[] { 2130837753 };
              return;
            }
            this.isCanShared = false;
            this.message2 = "惊呆了，体重居然毫无变化！\n说好的一定胖呢？";
            return;
          }
      }
      while (paramRoleBin.getWeight_change_target() > 0.0F);
      label411: label1310: if (j > 0)
      {
        this.message1 = "又胖啦～";
        this.message2 = ("胖了" + j / 10.0D + "kg");
        String[] arrayOfString2 = new String[4];
        arrayOfString2[0] = ("臣妾瘦不下去啊！比昨儿又胖" + j / 10.0D + "kg......");
        arrayOfString2[1] = ("又胖" + j / 10.0D + "kg，求鞭打，求骂醒！");
        arrayOfString2[2] = ("又胖" + j / 10.0D + "kg，快没有勇气瘦下去了......");
        arrayOfString2[3] = ("微微肥了" + j / 10.0D + "kg……再胖这最后一天，\n明天我一定减肥......");
        this.messages = arrayOfString2;
        if (paramRoleBin.getSex() == 1)
        {
          this.images = new int[] { 2130837803, 2130837792, 2130837802, 2130837788 };
          return;
        }
        this.images = new int[] { 2130837767, 2130837756, 2130837766, 2130837752 };
        return;
      }
      if (j < 0)
      {
        this.message1 = "又瘦啦～";
        this.message2 = ("瘦了" + Math.abs(j / 10.0D) + "kg");
        String[] arrayOfString1 = new String[4];
        arrayOfString1[0] = ("小瘦" + Math.abs(j / 10.0D) + "kg，这都不是事儿～");
        arrayOfString1[1] = ("又瘦" + Math.abs(j / 10.0D) + "kg，马上有线条～");
        arrayOfString1[2] = ("身未动，" + Math.abs(j / 10.0D) + "kg肉已远......");
        arrayOfString1[3] = ("开门见瘦～喜瘦" + Math.abs(j / 10.0D) + "kg哦！");
        this.messages = arrayOfString1;
        if (paramRoleBin.getSex() == 1)
        {
          this.images = new int[] { 2130837806, 2130837796, 2130837793, 2130837805 };
          return;
        }
        this.images = new int[] { 2130837770, 2130837760, 2130837757, 2130837769 };
        return;
      }
      this.isCanShared = false;
      this.message2 = "惊呆了，体重居然毫无变化！\n说好的一定瘦呢？";
      return;
    }
    this.showFlag = 1;
    this.title1 = "跟昨天比";
  }

  public ContinuousWeightingSharedModel(String paramString, int paramInt)
  {
    this.mainFlag = 0;
    this.isCanShared = false;
    this.title1 = paramString;
  }

  private HashMap<String, Object> getContinuousWeightChangeDatas(BodyIndex paramBodyIndex)
  {
    Object localObject = getWeightChangeState(paramBodyIndex);
    boolean bool = ((Boolean)((HashMap)localObject).get("flag")).booleanValue();
    int i = -1000;
    BodyIndex localBodyIndex = null;
    if (bool)
    {
      localBodyIndex = (BodyIndex)((HashMap)localObject).get("bodyIndex");
      i = ((Integer)((HashMap)localObject).get("weightChangeFlag")).intValue();
    }
    int j = 1;
    while (true)
    {
      if (!bool)
      {
        ((HashMap)localObject).put("yesterdayBody", localBodyIndex);
        ((HashMap)localObject).put("continuousDays", Integer.valueOf(j));
        return localObject;
      }
      HashMap localHashMap = getWeightChangeState((BodyIndex)((HashMap)localObject).get("bodyIndex"));
      bool = ((Boolean)localHashMap.get("flag")).booleanValue();
      int k = ((Integer)localHashMap.get("weightChangeFlag")).intValue();
      if ((i > -1000) && (k > -1000) && (i > 0) && (k > 0))
      {
        j++;
        localObject = localHashMap;
      }
      else if ((i > -1000) && (k > -1000) && (i < 0) && (k < 0))
      {
        j++;
        localObject = localHashMap;
      }
      else if ((i > -1000) && (k == 0))
      {
        bool = false;
      }
      else
      {
        bool = false;
      }
    }
  }

  private HashMap<String, Object> getWeightChangeState(BodyIndex paramBodyIndex)
  {
    HashMap localHashMap = new HashMap();
    long[] arrayOfLong = DateUtils.getDayStartTimeAndEndTimeByTimestamp(paramBodyIndex.getTime());
    BodyIndex localBodyIndex = BodyIndex.getAvgValueByArrayList(OperationDB.selectDayValuesBeforeTimestampAndRid(this.mContext, arrayOfLong[0], this.role.getRole_id()));
    localHashMap.put("days", Long.valueOf(-100L));
    if (localBodyIndex != null)
    {
      localHashMap.put("flag", Boolean.valueOf(false));
      long l = DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(paramBodyIndex.getTime(), localBodyIndex.getTime());
      localHashMap.put("days", Long.valueOf(l));
      if (l == 1L)
      {
        localHashMap.put("flag", Boolean.valueOf(true));
        localHashMap.put("bodyIndex", localBodyIndex);
        localHashMap.put("weightChangeFlag", Integer.valueOf(NumUtils.roundFloatToInt(10.0F * (paramBodyIndex.getWeight() - localBodyIndex.getWeight()))));
        return localHashMap;
      }
      localHashMap.put("bodyIndex", localBodyIndex);
    }
    localHashMap.put("weightChangeFlag", Integer.valueOf(-1000));
    localHashMap.put("flag", Boolean.valueOf(false));
    return localHashMap;
  }

  public HashMap<String, Object> getDialogMessageAndImage()
  {
    String[] arrayOfString = this.messages;
    HashMap localHashMap = null;
    if (arrayOfString != null)
    {
      int i = this.messages.length;
      localHashMap = null;
      if (i >= 1)
      {
        localHashMap = new HashMap();
        int j = Math.abs(new Random().nextInt() / this.messages.length);
        if (j > -1 + this.messages.length)
          j = -1 + this.messages.length;
        localHashMap.put("image", Integer.valueOf(this.images[j]));
        localHashMap.put("message", this.messages[j]);
      }
    }
    return localHashMap;
  }

  public int[] getImages()
  {
    return this.images;
  }

  public int getMainFlag()
  {
    return this.mainFlag;
  }

  public String getMessage1()
  {
    return this.message1;
  }

  public String getMessage2()
  {
    return this.message2;
  }

  public String getMessage3()
  {
    return this.message3;
  }

  public String[] getMessages()
  {
    return this.messages;
  }

  public int getShowFlag()
  {
    return this.showFlag;
  }

  public String getTitle1()
  {
    return this.title1;
  }

  public String getTitle2()
  {
    return this.title2;
  }

  public boolean isCanShared()
  {
    return this.isCanShared;
  }

  public boolean isGood()
  {
    return this.isGood;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ContinuousWeightingSharedModel
 * JD-Core Version:    0.6.2
 */
