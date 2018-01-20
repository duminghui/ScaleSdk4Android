package com.picooc.model;

import android.content.Context;
import com.picooc.db.OperationDB;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.RoleBin;
import com.picooc.utils.DateUtils;
import java.util.ArrayList;

public class LatinMonthData
{
  private final long DAYSCEONDS = 86400000L;
  private double[] bmiArray;
  private double[] bodyFaDashArray;
  private double[] bodyFatArray;
  private int days;
  private float fatAxisMax;
  private float fatAxisMin;
  private double[] goalFatArray;
  private double[] goalWeightArray;
  private float monthBodyFatChange;
  private long monthEndTime;
  private int monthFlag;
  private long monthStartTime;
  private float monthWeightChange;
  private int monthWeightingNum;
  private float nextMonthFirstBodyfat;
  private float nextMonthFirstWeight;
  private float prevMonthLastBodyfat;
  private float prevMonthLastWeight;
  private ArrayList<BodyIndex> weekBodys;
  private double[] weightArray;
  private float weightAxisMax;
  private float weightAxisMin;
  private double[] weightDashArray;

  public LatinMonthData(int paramInt, RoleBin paramRoleBin, Context paramContext)
  {
    if (paramRoleBin.getAge() > 60)
      paramRoleBin.setGoal_fat(-1.0F);
    this.monthFlag = paramInt;
    long[] arrayOfLong = DateUtils.getMonthStartTimeAndEndTimeByFlag(paramInt);
    this.monthStartTime = arrayOfLong[0];
    this.monthEndTime = arrayOfLong[1];
    this.days = DateUtils.getHowManyDaysOnAmonth(this.monthEndTime);
    this.weightArray = new double[this.days];
    this.bodyFatArray = new double[this.days];
    this.bmiArray = new double[this.days];
    this.weightDashArray = new double[this.days];
    this.bodyFaDashArray = new double[this.days];
    this.goalWeightArray = new double[this.days];
    this.goalFatArray = new double[this.days];
    int i = 0;
    int j = this.days;
    if (i >= j)
      if (paramInt != 0)
        break label263;
    label263: for (this.weekBodys = OperationDB.selectBodyIndexBetweenStartTimeAndEndTimeAndRoleID(paramContext, this.monthStartTime, DateUtils.getDayStartTimeAndEndTimeByFlag(0)[1], paramRoleBin.getRole_id()); ; this.weekBodys = OperationDB.selectBodyIndexBetweenStartTimeAndEndTimeAndRoleID(paramContext, this.monthStartTime, this.monthEndTime, paramRoleBin.getRole_id()))
    {
      if (this.weekBodys.size() != 0)
        break label286;
      return;
      this.weightArray[i] = -1.0D;
      this.bodyFatArray[i] = -1.0D;
      this.bmiArray[i] = -1.0D;
      this.weightDashArray[i] = -1.0D;
      this.bodyFaDashArray[i] = -1.0D;
      this.goalWeightArray[i] = -1.0D;
      this.goalFatArray[i] = -1.0D;
      i++;
      break;
    }
    label286: BodyIndex localBodyIndex1 = BodyIndex.getAvgValueByArrayList(OperationDB.selectDayValuesBeforeTimestampAndRid(paramContext, this.monthStartTime, paramRoleBin.getRole_id()));
    label336: int k;
    long l;
    float f1;
    float f2;
    float f3;
    float f4;
    float f5;
    float f6;
    float f7;
    int m;
    label435: int i3;
    if (localBodyIndex1 == null)
    {
      this.prevMonthLastWeight = -1.0F;
      this.prevMonthLastBodyfat = -1.0F;
      if (paramInt > 0)
        break label606;
      this.nextMonthFirstWeight = -1.0F;
      this.nextMonthFirstBodyfat = -1.0F;
      k = (int)((((BodyIndex)this.weekBodys.get(0)).getTime() - this.monthStartTime) / 86400000L);
      l = (System.currentTimeMillis() - this.monthStartTime) / 86400000L;
      f1 = 0.0F;
      f2 = 0.0F;
      f3 = 0.0F;
      f4 = 0.0F;
      f5 = 0.0F;
      f6 = 0.0F;
      f7 = 0.0F;
      m = 0;
      int n = this.weekBodys.size();
      if (m < n)
        break label676;
      if (this.prevMonthLastWeight > 0.0F)
        break label1047;
      this.monthWeightChange = 0.0F;
      this.monthBodyFatChange = 0.0F;
      this.monthWeightingNum = 0;
      i3 = 0;
      label443: int i4 = this.weightArray.length;
      if (i3 < i4)
        break label1109;
      caculateDashArray(this.weightArray, this.weightDashArray, this.prevMonthLastWeight, this.nextMonthFirstWeight);
      caculateDashArray(this.bodyFatArray, this.bodyFaDashArray, this.prevMonthLastBodyfat, this.nextMonthFirstBodyfat);
      if (this.weekBodys.size() > 0)
      {
        if (paramRoleBin.getGoal_weight() <= 0.0F)
          break label1137;
        this.goalWeightArray[0] = paramRoleBin.getGoal_weight();
        this.goalWeightArray[(-1 + this.days)] = paramRoleBin.getGoal_weight();
        label543: if (paramRoleBin.getGoal_fat() <= 0.0F)
          break label1152;
        this.goalFatArray[0] = paramRoleBin.getGoal_fat();
        this.goalFatArray[(-1 + this.days)] = paramRoleBin.getGoal_fat();
      }
    }
    while (true)
    {
      caulateYAxisMaxAndMinValue(paramRoleBin);
      return;
      this.prevMonthLastWeight = localBodyIndex1.getWeight();
      this.prevMonthLastBodyfat = localBodyIndex1.getBodyFat();
      break;
      label606: ArrayList localArrayList = OperationDB.selectDayValuesAfterTimestampAndRid(paramContext, this.monthEndTime, paramRoleBin.getRole_id());
      if ((localArrayList != null) && (localArrayList.size() > 0))
      {
        BodyIndex localBodyIndex3 = BodyIndex.getAvgValueByArrayList(localArrayList);
        this.nextMonthFirstWeight = localBodyIndex3.getWeight();
        this.nextMonthFirstBodyfat = localBodyIndex3.getBodyFat();
        break label336;
      }
      this.nextMonthFirstWeight = -1.0F;
      this.nextMonthFirstBodyfat = -1.0F;
      break label336;
      label676: BodyIndex localBodyIndex2 = (BodyIndex)this.weekBodys.get(m);
      int i1 = (int)((localBodyIndex2.getTime() - this.monthStartTime) / 86400000L);
      if (k != i1)
      {
        float f11 = f1 / f4;
        f6 = f11;
        this.weightArray[k] = f11;
        float f12 = f2 / f5;
        f7 = f12;
        if (f12 > 0.0F)
          this.bodyFatArray[k] = f12;
        float f13 = f3 / f4;
        this.bmiArray[k] = f13;
        if (localBodyIndex2.getBodyFat() > 0.0F)
        {
          f5 = 1.0F;
          label793: f1 = localBodyIndex2.getWeight();
          f2 = localBodyIndex2.getBodyFat();
          f3 = localBodyIndex2.getBmi();
          f4 = 1.0F;
          label817: int i2 = -1 + this.weekBodys.size();
          if (m == i2)
          {
            if (l != i1)
              break label978;
            f6 = localBodyIndex2.getWeight();
            f7 = localBodyIndex2.getBodyFat();
            this.weightArray[i1] = localBodyIndex2.getWeight();
            if (localBodyIndex2.getBodyFat() > 0.0F)
              this.bodyFatArray[i1] = localBodyIndex2.getBodyFat();
            this.bmiArray[i1] = localBodyIndex2.getBmi();
          }
        }
      }
      while (true)
      {
        k = i1;
        m++;
        break;
        f5 = 0.0F;
        break label793;
        f1 += localBodyIndex2.getWeight();
        f2 += localBodyIndex2.getBodyFat();
        f3 += localBodyIndex2.getBmi();
        if (localBodyIndex2.getBodyFat() > 0.0F)
          f5 += 1.0F;
        f4 += 1.0F;
        break label817;
        label978: float f8 = f1 / f4;
        f6 = f8;
        this.weightArray[i1] = f8;
        float f9 = f2 / f5;
        f7 = f9;
        if (f9 > 0.0F)
          this.bodyFatArray[i1] = f9;
        float f10 = f3 / f4;
        this.bmiArray[i1] = f10;
      }
      label1047: if (f6 <= 0.0F)
      {
        this.monthWeightChange = 0.0F;
        this.monthBodyFatChange = 0.0F;
        break label435;
      }
      this.monthWeightChange = (f6 - this.prevMonthLastWeight);
      if (this.prevMonthLastBodyfat > 0.0F)
      {
        this.monthBodyFatChange = (f7 - this.prevMonthLastBodyfat);
        break label435;
      }
      this.monthBodyFatChange = 0.0F;
      break label435;
      label1109: if (this.weightArray[i3] > 0.0D)
        this.monthWeightingNum = (1 + this.monthWeightingNum);
      i3++;
      break label443;
      label1137: this.goalWeightArray[(-1 + this.days)] = 1.0D;
      break label543;
      label1152: this.goalFatArray[0] = paramRoleBin.getGoal_fat();
    }
  }

  private void caculateDashArray(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, float paramFloat1, float paramFloat2)
  {
    int i = -1;
    double d1 = -1.0D;
    int j = -1;
    double d2 = -1.0D;
    int k = 0;
    int m = 0;
    int n;
    if (m >= paramArrayOfDouble1.length)
    {
      n = 1;
      if (d1 >= 0.0D)
        break label132;
      if ((paramFloat1 > 0.0F) && (paramFloat2 > 0.0F))
      {
        paramArrayOfDouble2[0] = paramFloat1;
        paramArrayOfDouble2[(-1 + paramArrayOfDouble1.length)] = paramFloat2;
        n = 0;
      }
    }
    while (true)
    {
      if (n != 0)
        paramArrayOfDouble2[0] = 1.0D;
      return;
      double d3 = paramArrayOfDouble1[m];
      if (d3 > 0.0D)
      {
        j = m;
        d2 = d3;
      }
      if (k != 0);
      while (true)
      {
        m++;
        break;
        if (d3 > 0.0D)
        {
          i = m;
          d1 = d3;
          k = 1;
        }
      }
      label132: if ((i >= 1) && (paramFloat1 > 0.0F))
      {
        paramArrayOfDouble2[0] = paramFloat1;
        paramArrayOfDouble2[i] = d1;
        n = 0;
      }
      if ((j != -1 + paramArrayOfDouble2.length) && (paramFloat2 > 0.0F))
      {
        paramArrayOfDouble2[(-1 + paramArrayOfDouble2.length)] = paramFloat2;
        paramArrayOfDouble2[j] = d2;
        n = 0;
      }
      if ((i >= 1) && (paramFloat1 > 0.0F) && (j != -1 + paramArrayOfDouble2.length) && (paramFloat2 > 0.0F))
      {
        int i1 = i + 1;
        while (i1 < j)
        {
          paramArrayOfDouble2[i1] = paramArrayOfDouble1[i1];
          i1++;
          n = 0;
        }
      }
    }
  }

  public void caulateYAxisMaxAndMinValue(RoleBin paramRoleBin)
  {
    double d1 = this.weightArray[0];
    double d2 = this.weightArray[0];
    int i = 1;
    label53: label80: float f1;
    label130: label157: label184: double d5;
    double d6;
    int j;
    label274: label301: float f2;
    if (i >= this.weightArray.length)
    {
      if (d1 < 0.0D)
      {
        if (paramRoleBin.getFirst_weight() <= 0.0F)
          break label537;
        d1 = 1.15D * paramRoleBin.getFirst_weight();
      }
      if (d2 < 0.0D)
      {
        if (paramRoleBin.getFirst_weight() <= 0.0F)
          break label544;
        d2 = 1.15D * paramRoleBin.getFirst_weight();
      }
      if ((d1 == d2) || (d1 - d2 <= 1.0D))
      {
        d1 += 2.0D;
        d2 -= 2.0D;
      }
      f1 = paramRoleBin.getGoal_weight();
      if (d2 < f1)
        break label552;
      d2 = f1;
      if ((d2 < this.prevMonthLastWeight) || (this.prevMonthLastWeight <= 0.0F))
        break label567;
      d2 = this.prevMonthLastWeight;
      if ((this.nextMonthFirstWeight <= 0.0F) || (d2 < this.nextMonthFirstWeight))
        break label595;
      d2 = this.nextMonthFirstWeight;
      this.weightAxisMax = ((float)(d1 + 0.2D * (d1 - d2)));
      this.weightAxisMin = ((float)(this.weightAxisMax - 2.599999904632568D * (d1 - d2)));
      d5 = this.bodyFatArray[0];
      d6 = this.bodyFatArray[0];
      j = 1;
      if (j < this.bodyFatArray.length)
        break label623;
      if (d5 <= 0.0D)
      {
        if (paramRoleBin.getFirst_fat() <= 0.0F)
          break label703;
        d5 = 1.15D * paramRoleBin.getFirst_fat();
      }
      if (d6 < 0.0D)
      {
        if (paramRoleBin.getFirst_fat() <= 0.0F)
          break label711;
        d6 = 1.15D * paramRoleBin.getFirst_fat();
      }
      if ((d6 == d5) || (d5 - d6 <= 1.0D))
      {
        d5 += 2.0D;
        d6 -= 2.0D;
      }
      f2 = paramRoleBin.getGoal_fat();
      if (d6 < f2)
        break label719;
      d6 = f2;
      label355: if ((d6 < this.prevMonthLastBodyfat) || (this.prevMonthLastBodyfat <= 0.0F))
        break label736;
      d6 = this.prevMonthLastBodyfat;
      label382: if ((this.nextMonthFirstBodyfat <= 0.0F) || (d6 < this.nextMonthFirstBodyfat))
        break label766;
      d6 = this.nextMonthFirstBodyfat;
    }
    while (true)
    {
      this.fatAxisMin = ((float)(d6 - 0.2D * (d5 - d6)));
      this.fatAxisMax = ((float)(this.fatAxisMin + 2.6D * (d5 - d6)));
      if (this.fatAxisMin < 0.0F)
        this.fatAxisMin = 0.0F;
      return;
      double d3 = this.weightArray[i];
      if (d3 < 0.0D);
      while (true)
      {
        i++;
        break;
        if (d1 < 0.0D)
          d1 = d3;
        if (d2 < 0.0D)
          d2 = d3;
        double d4 = this.weightArray[i];
        if (d1 < d4)
          d1 = d4;
        if (d2 >= d4)
          d2 = d4;
      }
      label537: d1 = 150.0D;
      break label53;
      label544: d2 = 150.0D;
      break label80;
      label552: if (d1 > f1)
        break label130;
      d1 = f1;
      break label130;
      label567: if ((d1 > this.prevMonthLastWeight) || (this.prevMonthLastWeight <= 0.0F))
        break label157;
      d1 = this.prevMonthLastWeight;
      break label157;
      label595: if ((this.nextMonthFirstWeight <= 0.0F) || (d1 > this.nextMonthFirstWeight))
        break label184;
      d1 = this.nextMonthFirstWeight;
      break label184;
      label623: double d7 = this.bodyFatArray[j];
      if (d7 < 0.0D);
      while (true)
      {
        j++;
        break;
        if (d6 < 0.0D)
          d6 = d7;
        if (d5 < 0.0D)
          d5 = d7;
        double d8 = this.bodyFatArray[j];
        if (d5 < d8)
          d5 = d8;
        if (d6 >= d8)
          d6 = d8;
      }
      label703: d5 = 50.0D;
      break label274;
      label711: d6 = 50.0D;
      break label301;
      label719: if (d5 > f2)
        break label355;
      d5 = f2;
      break label355;
      label736: if ((d5 > this.prevMonthLastBodyfat) || (this.prevMonthLastBodyfat <= 0.0F))
        break label382;
      d5 = this.prevMonthLastBodyfat;
      break label382;
      label766: if ((this.nextMonthFirstBodyfat > 0.0F) && (d5 <= this.nextMonthFirstBodyfat))
        d5 = this.nextMonthFirstBodyfat;
    }
  }

  public double[] getBmiArray()
  {
    return this.bmiArray;
  }

  public double[] getBodyFaDashArray()
  {
    return this.bodyFaDashArray;
  }

  public double[] getBodyFatArray()
  {
    return this.bodyFatArray;
  }

  public int getDays()
  {
    return this.days;
  }

  public float getFatAxisMax()
  {
    return this.fatAxisMax;
  }

  public float getFatAxisMin()
  {
    return this.fatAxisMin;
  }

  public double[] getGoalFatArray()
  {
    return this.goalFatArray;
  }

  public double[] getGoalWeightArray()
  {
    return this.goalWeightArray;
  }

  public float getMonthBodyFatChange()
  {
    return this.monthBodyFatChange;
  }

  public int getMonthFlag()
  {
    return this.monthFlag;
  }

  public float getMonthWeightChange()
  {
    return this.monthWeightChange;
  }

  public int getMonthWeightingNum()
  {
    return this.monthWeightingNum;
  }

  public float getNextMonthFirstBodyfat()
  {
    return this.nextMonthFirstBodyfat;
  }

  public float getNextMonthFirstWeight()
  {
    return this.nextMonthFirstWeight;
  }

  public float getPrevMonthLastBodyfat()
  {
    return this.prevMonthLastBodyfat;
  }

  public float getPrevMonthLastWeight()
  {
    return this.prevMonthLastWeight;
  }

  public ArrayList<BodyIndex> getWeekBodys()
  {
    return this.weekBodys;
  }

  public double[] getWeightArray()
  {
    return this.weightArray;
  }

  public float getWeightAxisMax()
  {
    return this.weightAxisMax;
  }

  public float getWeightAxisMin()
  {
    return this.weightAxisMin;
  }

  public double[] getWeightDashArray()
  {
    return this.weightDashArray;
  }

  public void setBmiArray(double[] paramArrayOfDouble)
  {
    this.bmiArray = paramArrayOfDouble;
  }

  public void setBodyFaDashArray(double[] paramArrayOfDouble)
  {
    this.bodyFaDashArray = paramArrayOfDouble;
  }

  public void setBodyFatArray(double[] paramArrayOfDouble)
  {
    this.bodyFatArray = paramArrayOfDouble;
  }

  public void setDays(int paramInt)
  {
    this.days = paramInt;
  }

  public void setGoalFatArray(double[] paramArrayOfDouble)
  {
    this.goalFatArray = paramArrayOfDouble;
  }

  public void setGoalWeightArray(double[] paramArrayOfDouble)
  {
    this.goalWeightArray = paramArrayOfDouble;
  }

  public void setWeightArray(double[] paramArrayOfDouble)
  {
    this.weightArray = paramArrayOfDouble;
  }

  public void setWeightDashArray(double[] paramArrayOfDouble)
  {
    this.weightDashArray = paramArrayOfDouble;
  }

  public void updateData(BodyIndex paramBodyIndex, RoleBin paramRoleBin)
  {
    int i = (int)DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(paramBodyIndex.getTime(), this.monthStartTime);
    if (i > -1 + this.days)
      i = -1 + this.days;
    if (this.weightArray[i] <= 0.0D)
      this.monthWeightingNum = (1 + this.monthWeightingNum);
    this.weightArray[i] = paramBodyIndex.getWeight();
    if (paramBodyIndex.getBodyFat() > 0.0F)
    {
      this.bodyFatArray[i] = paramBodyIndex.getBodyFat();
      this.bmiArray[i] = paramBodyIndex.getBmi();
      if (this.prevMonthLastWeight > 0.0F)
        break label130;
      this.monthWeightChange = 0.0F;
      this.monthBodyFatChange = 0.0F;
    }
    while (true)
    {
      caulateYAxisMaxAndMinValue(paramRoleBin);
      return;
      this.bodyFatArray[i] = -1.0D;
      break;
      label130: if (paramBodyIndex.getWeight() <= 0.0F)
      {
        this.monthWeightChange = 0.0F;
        this.monthBodyFatChange = 0.0F;
      }
      else
      {
        this.monthWeightChange = (paramBodyIndex.getWeight() - this.prevMonthLastWeight);
        if (this.prevMonthLastBodyfat > 0.0F)
          this.monthBodyFatChange = (paramBodyIndex.getBodyFat() - this.prevMonthLastBodyfat);
        else
          this.monthBodyFatChange = 0.0F;
      }
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LatinMonthData
 * JD-Core Version:    0.6.2
 */
