package com.picooc.model;

import android.content.Context;
import com.picooc.db.OperationDB;
import com.picooc.domain.ComponentBodyTypeEnum;
import com.picooc.domain.ReportEntity;
import com.picooc.domain.RoleBin;
import com.picooc.utils.DateUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SportPlanModel
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  ArrayList<DayPlan> daySport;
  ArrayList<ReportEntity> reportList;
  private String tipMessage;
  String tips;
  ArrayList<WeekPlan> weekSport;
  int week_no;

  public SportPlanModel(Context paramContext, long paramLong, RoleBin paramRoleBin, BodyTypeEnum paramBodyTypeEnum, ArrayList<ReportEntity> paramArrayList)
  {
    long l = System.currentTimeMillis();
    if (paramLong == 0L)
      paramLong = l;
    this.week_no = DateUtils.getHowManyWeekBetweenNewTimeStampAndOldTimeStamp(l, paramLong);
    if (this.week_no < 1)
      this.week_no = 1;
    this.reportList = paramArrayList;
    HashMap localHashMap = OperationDB.getSportPlanByWeekNo(paramContext, this.week_no, paramRoleBin.getSex(), paramBodyTypeEnum, this.reportList, this);
    this.weekSport = ((ArrayList)localHashMap.get("weekSport"));
    this.daySport = ((ArrayList)localHashMap.get("daySport"));
  }

  public ArrayList<DayPlan> getDaySport()
  {
    return this.daySport;
  }

  public String getTipsMessage(Context paramContext)
  {
    if (this.tipMessage == null)
      this.tipMessage = OperationDB.getTipsMessage(paramContext);
    return this.tipMessage;
  }

  public ArrayList<WeekPlan> getWeekSport()
  {
    return this.weekSport;
  }

  public class DayPlan
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    ArrayList<Integer> helpToAdd;
    ArrayList<Integer> helpToReduce;
    ArrayList<String> messages;
    String sportCount;
    String sportCycle;
    String sportCycleUnit;
    String sportDuringTime;
    String sportDuringTimeUnit;
    SportPlanEnum sportType;
    String sportTypeName;

    public DayPlan(String paramString1, String paramArrayList1, ArrayList<Object> paramArrayList2, ArrayList<Object> paramArrayList3, ArrayList<Object> paramArrayList, ArrayList<ReportEntity> arg7)
    {
      this.sportCount = paramArrayList1;
      this.sportType = paramString1;
      this.sportDuringTime = paramArrayList2.replace(",", "");
      this.messages = new ArrayList();
      if (paramArrayList3 != null);
      ArrayList localArrayList1;
      int k;
      ArrayList localArrayList2;
      Object localObject1;
      int i;
      for (int n = 0; ; n++)
      {
        if (n >= paramArrayList3.size())
        {
          localArrayList1 = new ArrayList();
          if (paramArrayList != null)
          {
            k = 0;
            if (k < paramArrayList.size())
              break;
          }
          this.helpToReduce = localArrayList1;
          localArrayList2 = new ArrayList();
          if (localObject1 != null)
          {
            i = 0;
            if (i < localObject1.size())
              break label521;
          }
          this.helpToAdd = localArrayList2;
        }
        switch ($SWITCH_TABLE$com$picooc$model$SportPlanEnum()[this.sportType.ordinal()])
        {
        default:
          return;
          Object localObject3 = paramArrayList3.get(n);
          if ((localObject3 instanceof String))
            this.messages.add(localObject3.toString());
          break;
        case 1:
        case 3:
        case 2:
        }
      }
      int m = ((Integer)paramArrayList.get(k)).intValue();
      Object localObject2;
      if (m == ComponentBodyTypeEnum.WATER.getIndex())
        if (!((ReportEntity)localObject2.get(3)).getStandardOrNot().booleanValue())
          localArrayList1.add(Integer.valueOf(m));
      while (true)
      {
        k++;
        break;
        if (m == ComponentBodyTypeEnum.BONE.getIndex())
        {
          if (!((ReportEntity)localObject2.get(6)).getStandardOrNot().booleanValue())
            localArrayList1.add(Integer.valueOf(m));
        }
        else if (m == ComponentBodyTypeEnum.BMR.getIndex())
        {
          if (!((ReportEntity)localObject2.get(4)).getStandardOrNot().booleanValue())
            localArrayList1.add(Integer.valueOf(m));
        }
        else if (m == ComponentBodyTypeEnum.WEIGHT.getIndex())
        {
          if (!((ReportEntity)localObject2.get(0)).getStandardOrNot().booleanValue())
            localArrayList1.add(Integer.valueOf(m));
        }
        else if (m == ComponentBodyTypeEnum.FAT.getIndex())
        {
          if (!((ReportEntity)localObject2.get(1)).getStandardOrNot().booleanValue())
            localArrayList1.add(Integer.valueOf(m));
        }
        else if (m == ComponentBodyTypeEnum.INFAT.getIndex())
        {
          if (!((ReportEntity)localObject2.get(5)).getStandardOrNot().booleanValue())
            localArrayList1.add(Integer.valueOf(m));
        }
        else if ((m == ComponentBodyTypeEnum.MUSCLE.getIndex()) && (!((ReportEntity)localObject2.get(2)).getStandardOrNot().booleanValue()))
          localArrayList1.add(Integer.valueOf(m));
      }
      label521: int j = ((Integer)localObject1.get(i)).intValue();
      if (j == ComponentBodyTypeEnum.WATER.getIndex())
        if (!((ReportEntity)localObject2.get(3)).getStandardOrNot().booleanValue())
          localArrayList2.add(Integer.valueOf(j));
      while (true)
      {
        i++;
        break;
        if (j == ComponentBodyTypeEnum.BONE.getIndex())
        {
          if (!((ReportEntity)localObject2.get(6)).getStandardOrNot().booleanValue())
            localArrayList2.add(Integer.valueOf(j));
        }
        else if (j == ComponentBodyTypeEnum.BMR.getIndex())
        {
          if (!((ReportEntity)localObject2.get(4)).getStandardOrNot().booleanValue())
            localArrayList2.add(Integer.valueOf(j));
        }
        else if (j == ComponentBodyTypeEnum.WEIGHT.getIndex())
        {
          if (!((ReportEntity)localObject2.get(0)).getStandardOrNot().booleanValue())
            localArrayList2.add(Integer.valueOf(j));
        }
        else if (j == ComponentBodyTypeEnum.FAT.getIndex())
        {
          if (!((ReportEntity)localObject2.get(1)).getStandardOrNot().booleanValue())
            localArrayList2.add(Integer.valueOf(j));
        }
        else if (j == ComponentBodyTypeEnum.INFAT.getIndex())
        {
          if (!((ReportEntity)localObject2.get(5)).getStandardOrNot().booleanValue())
            localArrayList2.add(Integer.valueOf(j));
        }
        else if ((j == ComponentBodyTypeEnum.MUSCLE.getIndex()) && (!((ReportEntity)localObject2.get(2)).getStandardOrNot().booleanValue()))
          localArrayList2.add(Integer.valueOf(j));
      }
      this.sportTypeName = "低强度运动";
      return;
      this.sportTypeName = "无氧运动";
      return;
      this.sportTypeName = "有氧运动";
    }

    public ArrayList<Integer> getHelpToAdd()
    {
      return this.helpToAdd;
    }

    public ArrayList<Integer> getHelpToReduce()
    {
      return this.helpToReduce;
    }

    public ArrayList<String> getMessages()
    {
      return this.messages;
    }

    public String getSportCount()
    {
      return this.sportCount;
    }

    public String getSportCycle()
    {
      return this.sportCycle;
    }

    public String getSportCycleUnit()
    {
      return this.sportCycleUnit;
    }

    public String getSportDuringTime()
    {
      return this.sportDuringTime;
    }

    public String getSportDuringTimeUnit()
    {
      return this.sportDuringTimeUnit;
    }

    public SportPlanEnum getSportType()
    {
      return this.sportType;
    }

    public String getSportTypeName()
    {
      return this.sportTypeName;
    }
  }

  public class WeekPlan
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    String sportCycle;
    String sportCycleUnit;
    SportPlanEnum sportType;
    String sportTypeName;

    public WeekPlan(SportPlanEnum paramString, String arg3)
    {
      this.sportType = paramString;
      Object localObject;
      String[] arrayOfString = localObject.split(",");
      this.sportCycle = arrayOfString[0];
      this.sportCycleUnit = arrayOfString[1];
      switch ($SWITCH_TABLE$com$picooc$model$SportPlanEnum()[this.sportType.ordinal()])
      {
      default:
        return;
      case 1:
        this.sportTypeName = "低强度运动";
        return;
      case 3:
        this.sportTypeName = "无氧运动";
        return;
      case 2:
      }
      this.sportTypeName = "有氧运动";
    }

    public String getSportCycle()
    {
      return this.sportCycle;
    }

    public String getSportCycleUnit()
    {
      return this.sportCycleUnit;
    }

    public SportPlanEnum getSportType()
    {
      return this.sportType;
    }

    public String getSportTypeName()
    {
      return this.sportTypeName;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SportPlanModel
 * JD-Core Version:    0.6.2
 */
