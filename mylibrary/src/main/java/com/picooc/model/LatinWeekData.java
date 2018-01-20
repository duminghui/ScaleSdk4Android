package com.picooc.model;

import android.content.Context;
import com.picooc.db.OperationDB;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.EveryMeEntity;
import com.picooc.domain.RoleBin;
import com.picooc.utils.DateUtils;
import java.util.ArrayList;

public class LatinWeekData
{
  private final long DAYSCEONDS = 86400000L;
  private double[] bmiArray;
  private double[] bodyFaDashArray;
  private double[] bodyFatArray;
  private ArrayList<EveryMeEntity> everys;
  private float fatAxisMax;
  private float fatAxisMin;
  private int fatStandardAnchor = -1;
  private long firstDashPointTime;
  private double[] goalFatArray;
  private double[] goalWeightArray;
  private float nextWeekFirstBodyfat;
  private float nextWeekFirstWeight;
  private float prevWeekLastBodyfat;
  private float prevWeekLastWeight;
  private long[] timeArray;
  private float weekBodyFatChange;
  private ArrayList<BodyIndex> weekBodys;
  private long weekEndTime;
  private int weekFlag;
  private long weekStartTime;
  private float weekWeightChange;
  private int weekWeightingNum;
  private double[] weightArray;
  private float weightAxisMax;
  private float weightAxisMin;
  private double[] weightDashArray;
  private int weightStandardAnchor = -1;

  public LatinWeekData(int paramInt, RoleBin paramRoleBin, Context paramContext)
  {
    if (paramRoleBin.getAge() > 60)
      paramRoleBin.setGoal_fat(-1.0F);
    this.weekFlag = paramInt;
    long[] arrayOfLong = DateUtils.getWeekStartTimeAndEndTimeByFlag(paramInt);
    this.weekStartTime = arrayOfLong[0];
    this.weekEndTime = arrayOfLong[1];
    this.weightArray = new double[7];
    this.bodyFatArray = new double[7];
    this.bmiArray = new double[7];
    this.weightDashArray = new double[7];
    this.bodyFaDashArray = new double[7];
    this.goalWeightArray = new double[7];
    this.goalFatArray = new double[7];
    this.timeArray = new long[7];
    this.everys = new ArrayList();
    int i = 0;
    label174: BodyIndex localBodyIndex4;
    label218: BodyIndex localBodyIndex5;
    if (i >= 7)
    {
      if (paramInt != 0)
        break label522;
      this.weekBodys = OperationDB.selectBodyIndexBetweenStartTimeAndEndTimeAndRoleID(paramContext, this.weekStartTime, DateUtils.getDayStartTimeAndEndTimeByFlag(0)[1], paramRoleBin.getRole_id());
      if (this.weekBodys.size() != 0)
        break label590;
      localBodyIndex4 = BodyIndex.getAvgValueByArrayList(OperationDB.selectDayValuesBeforeTimestampAndRid(paramContext, this.weekStartTime, paramRoleBin.getRole_id()));
      if (localBodyIndex4 != null)
        break label545;
      this.prevWeekLastWeight = -1.0F;
      this.prevWeekLastBodyfat = -1.0F;
      ArrayList localArrayList3 = OperationDB.selectDayValuesAfterTimestampAndRid(paramContext, this.weekEndTime, paramRoleBin.getRole_id());
      if ((localArrayList3 == null) || (localArrayList3.size() <= 0))
        break label575;
      localBodyIndex5 = BodyIndex.getAvgValueByArrayList(localArrayList3);
      this.nextWeekFirstWeight = localBodyIndex5.getWeight();
    }
    for (this.nextWeekFirstBodyfat = localBodyIndex5.getBodyFat(); ; this.nextWeekFirstBodyfat = -1.0F)
    {
      boolean bool1 = this.prevWeekLastWeight < 0.0F;
      int i9 = 0;
      if (bool1)
      {
        boolean bool2 = this.nextWeekFirstWeight < 0.0F;
        i9 = 0;
        if (bool2)
        {
          this.weightDashArray[0] = this.prevWeekLastWeight;
          this.weightDashArray[6] = this.nextWeekFirstWeight;
          this.goalWeightArray[0] = paramRoleBin.getGoal_weight();
          this.goalWeightArray[6] = paramRoleBin.getGoal_weight();
          i9 = 1;
        }
      }
      if ((this.prevWeekLastBodyfat > 0.0F) && (this.nextWeekFirstBodyfat > 0.0F))
      {
        this.bodyFaDashArray[0] = this.prevWeekLastBodyfat;
        this.bodyFaDashArray[6] = this.nextWeekFirstBodyfat;
        this.goalFatArray[0] = paramRoleBin.getGoal_fat();
        this.goalFatArray[6] = paramRoleBin.getGoal_fat();
        i9 = 1;
      }
      if (i9 != 0)
        caulateYAxisMaxAndMinValue(paramRoleBin);
      return;
      this.weightArray[i] = -1.0D;
      this.bodyFatArray[i] = -1.0D;
      this.bmiArray[i] = -1.0D;
      this.weightDashArray[i] = -1.0D;
      this.bodyFaDashArray[i] = -1.0D;
      this.goalWeightArray[i] = -1.0D;
      this.goalFatArray[i] = -1.0D;
      this.timeArray[i] = 1L;
      this.everys.add(null);
      i++;
      break;
      label522: this.weekBodys = OperationDB.selectBodyIndexBetweenStartTimeAndEndTimeAndRoleID(paramContext, this.weekStartTime, this.weekEndTime, paramRoleBin.getRole_id());
      break label174;
      label545: this.prevWeekLastWeight = localBodyIndex4.getWeight();
      this.prevWeekLastBodyfat = localBodyIndex4.getBodyFat();
      this.firstDashPointTime = localBodyIndex4.getTime();
      break label218;
      label575: this.nextWeekFirstWeight = -1.0F;
    }
    label590: BodyIndex localBodyIndex1 = BodyIndex.getAvgValueByArrayList(OperationDB.selectDayValuesBeforeTimestampAndRid(paramContext, this.weekStartTime, paramRoleBin.getRole_id()));
    label624: int j;
    label640: long l;
    float f1;
    float f2;
    float f3;
    float f4;
    float f5;
    float f6;
    float f7;
    int k;
    int i2;
    label723: label756: int i4;
    if (localBodyIndex1 == null)
    {
      this.prevWeekLastWeight = -1.0F;
      this.prevWeekLastBodyfat = -1.0F;
      if (paramInt > 0)
        break label1004;
      this.nextWeekFirstWeight = -1.0F;
      this.nextWeekFirstBodyfat = -1.0F;
      j = (int)((((BodyIndex)this.weekBodys.get(0)).getTime() - this.weekStartTime) / 86400000L);
      l = (System.currentTimeMillis() - this.weekStartTime) / 86400000L;
      f1 = 0.0F;
      f2 = 0.0F;
      f3 = 0.0F;
      f4 = 0.0F;
      f5 = 0.0F;
      f6 = 0.0F;
      f7 = 0.0F;
      k = 0;
      int m = this.weekBodys.size();
      if (k < m)
        break label1074;
      i2 = 0;
      int i3 = this.timeArray.length;
      if (i2 < i3)
        break label1445;
      if (this.prevWeekLastWeight > 0.0F)
        break label1470;
      this.weekWeightChange = 0.0F;
      this.weekBodyFatChange = 0.0F;
      this.weekWeightingNum = 0;
      i4 = 0;
      label764: int i5 = this.weightArray.length;
      if (i4 < i5)
        break label1532;
      caculateDashArray(this.weightArray, this.weightDashArray, this.prevWeekLastWeight, this.nextWeekFirstWeight);
      caculateDashArray(this.bodyFatArray, this.bodyFaDashArray, this.prevWeekLastBodyfat, this.nextWeekFirstBodyfat);
      if (this.weekBodys.size() > 0)
      {
        if (paramRoleBin.getGoal_weight() <= 0.0F)
          break label1560;
        this.goalWeightArray[0] = paramRoleBin.getGoal_weight();
        this.goalWeightArray[6] = paramRoleBin.getGoal_weight();
        label860: if (paramRoleBin.getGoal_fat() <= 0.0F)
          break label1571;
        this.goalFatArray[0] = paramRoleBin.getGoal_fat();
        this.goalFatArray[6] = paramRoleBin.getGoal_fat();
      }
    }
    while (true)
    {
      caulateYAxisMaxAndMinValue(paramRoleBin);
      ArrayList localArrayList2 = OperationDB.queryEveryMeByStartTimeAndTime(paramContext, paramRoleBin.getRole_id(), this.weekStartTime, this.weekEndTime);
      for (int i6 = 0; ; i6++)
      {
        int i7 = localArrayList2.size();
        if (i6 >= i7)
          break;
        EveryMeEntity localEveryMeEntity = (EveryMeEntity)localArrayList2.get(i6);
        int i8 = DateUtils.getWeekendByTimestamp(localEveryMeEntity.getTime());
        this.everys.set(i8 - 1, localEveryMeEntity);
      }
      this.prevWeekLastWeight = localBodyIndex1.getWeight();
      this.prevWeekLastBodyfat = localBodyIndex1.getBodyFat();
      this.firstDashPointTime = localBodyIndex1.getTime();
      break label624;
      label1004: ArrayList localArrayList1 = OperationDB.selectDayValuesAfterTimestampAndRid(paramContext, this.weekEndTime, paramRoleBin.getRole_id());
      if ((localArrayList1 != null) && (localArrayList1.size() > 0))
      {
        BodyIndex localBodyIndex3 = BodyIndex.getAvgValueByArrayList(localArrayList1);
        this.nextWeekFirstWeight = localBodyIndex3.getWeight();
        this.nextWeekFirstBodyfat = localBodyIndex3.getBodyFat();
        break label640;
      }
      this.nextWeekFirstWeight = -1.0F;
      this.nextWeekFirstBodyfat = -1.0F;
      break label640;
      label1074: BodyIndex localBodyIndex2 = (BodyIndex)this.weekBodys.get(k);
      int n = (int)((localBodyIndex2.getTime() - this.weekStartTime) / 86400000L);
      if (j != n)
      {
        float f11 = f1 / f4;
        f6 = f11;
        this.weightArray[j] = f11;
        float f12 = f2 / f5;
        f7 = f12;
        if (f12 > 0.0F)
          this.bodyFatArray[j] = f12;
        float f13 = f3 / f4;
        this.bmiArray[j] = f13;
        f1 = localBodyIndex2.getWeight();
        if (localBodyIndex2.getBodyFat() > 0.0F)
        {
          f5 = 1.0F;
          label1198: f2 = localBodyIndex2.getBodyFat();
          f3 = localBodyIndex2.getBmi();
          f4 = 1.0F;
          label1215: int i1 = -1 + this.weekBodys.size();
          if (k == i1)
          {
            if (l != n)
              break label1376;
            f6 = localBodyIndex2.getWeight();
            f7 = localBodyIndex2.getBodyFat();
            this.weightArray[n] = localBodyIndex2.getWeight();
            if (localBodyIndex2.getBodyFat() > 0.0F)
              this.bodyFatArray[n] = localBodyIndex2.getBodyFat();
            this.bmiArray[n] = localBodyIndex2.getBmi();
          }
        }
      }
      while (true)
      {
        j = n;
        k++;
        break;
        f5 = 0.0F;
        break label1198;
        f1 += localBodyIndex2.getWeight();
        f2 += localBodyIndex2.getBodyFat();
        f3 += localBodyIndex2.getBmi();
        if (localBodyIndex2.getBodyFat() > 0.0F)
          f5 += 1.0F;
        f4 += 1.0F;
        break label1215;
        label1376: float f8 = f1 / f4;
        f6 = f8;
        this.weightArray[n] = f8;
        float f9 = f2 / f5;
        f7 = f9;
        if (f9 > 0.0F)
          this.bodyFatArray[n] = f9;
        float f10 = f3 / f4;
        this.bmiArray[n] = f10;
      }
      label1445: this.timeArray[i2] = (this.weekStartTime + 86400000L * i2);
      i2++;
      break label723;
      label1470: if (f6 <= 0.0F)
      {
        this.weekWeightChange = 0.0F;
        this.weekBodyFatChange = 0.0F;
        break label756;
      }
      this.weekWeightChange = (f6 - this.prevWeekLastWeight);
      if (this.prevWeekLastBodyfat > 0.0F)
      {
        this.weekBodyFatChange = (f7 - this.prevWeekLastBodyfat);
        break label756;
      }
      this.weekBodyFatChange = 0.0F;
      break label756;
      label1532: if (this.weightArray[i4] > 0.0D)
        this.weekWeightingNum = (1 + this.weekWeightingNum);
      i4++;
      break label764;
      label1560: this.goalWeightArray[6] = 1.0D;
      break label860;
      label1571: this.goalFatArray[0] = 1.0D;
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
      if ((d2 < this.prevWeekLastWeight) || (this.prevWeekLastWeight <= 0.0F))
        break label567;
      d2 = this.prevWeekLastWeight;
      if ((this.nextWeekFirstWeight <= 0.0F) || (d2 < this.nextWeekFirstWeight))
        break label595;
      d2 = this.nextWeekFirstWeight;
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
      label355: if ((d6 < this.prevWeekLastBodyfat) || (this.prevWeekLastBodyfat <= 0.0F))
        break label736;
      d6 = this.prevWeekLastBodyfat;
      label382: if ((this.nextWeekFirstBodyfat <= 0.0F) || (d6 < this.nextWeekFirstBodyfat))
        break label766;
      d6 = this.nextWeekFirstBodyfat;
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
      label567: if ((d1 > this.prevWeekLastWeight) || (this.prevWeekLastWeight <= 0.0F))
        break label157;
      d1 = this.prevWeekLastWeight;
      break label157;
      label595: if ((this.nextWeekFirstWeight <= 0.0F) || (d1 > this.nextWeekFirstWeight))
        break label184;
      d1 = this.nextWeekFirstWeight;
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
      label736: if ((d5 > this.prevWeekLastBodyfat) || (this.prevWeekLastBodyfat <= 0.0F))
        break label382;
      d5 = this.prevWeekLastBodyfat;
      break label382;
      label766: if ((this.nextWeekFirstBodyfat > 0.0F) && (d5 <= this.nextWeekFirstBodyfat))
        d5 = this.nextWeekFirstBodyfat;
    }
  }

  public double[] getBmiArray()
  {
    return this.bmiArray;
  }

  public float getBmiByIndex(int paramInt)
  {
    return (float)this.bmiArray[paramInt];
  }

  public double[] getBodyFaDashArray()
  {
    return this.bodyFaDashArray;
  }

  public double[] getBodyFatArray()
  {
    return this.bodyFatArray;
  }

  public float getBodyFatByIndex(int paramInt)
  {
    float f = (float)this.bodyFatArray[paramInt];
    if (f < 0.0F)
      f = 0.0F;
    return f;
  }

  public EveryMeEntity getEveryMeByIndex(int paramInt)
  {
    return (EveryMeEntity)this.everys.get(paramInt);
  }

  public float getFatAxisMax()
  {
    return this.fatAxisMax;
  }

  public float getFatAxisMin()
  {
    return this.fatAxisMin;
  }

  public int getFatStandardAnchor()
  {
    return this.fatStandardAnchor;
  }

  public long getFirstDashPointTime()
  {
    return this.firstDashPointTime;
  }

  public double[] getGoalFatArray()
  {
    return this.goalFatArray;
  }

  public double[] getGoalWeightArray()
  {
    return this.goalWeightArray;
  }

  public float getNextWeekFirstBodyfat()
  {
    return this.nextWeekFirstBodyfat;
  }

  public float getNextWeekFirstWeight()
  {
    return this.nextWeekFirstWeight;
  }

  public float getPrevWeekLastBodyfat()
  {
    return this.prevWeekLastBodyfat;
  }

  public float getPrevWeekLastWeight()
  {
    return this.prevWeekLastWeight;
  }

  public long getTimeByIndex(int paramInt)
  {
    return this.timeArray[paramInt];
  }

  public float getWeekBodyFatChange()
  {
    return this.weekBodyFatChange;
  }

  public int getWeekFlag()
  {
    return this.weekFlag;
  }

  public float getWeekWeightChange()
  {
    return this.weekWeightChange;
  }

  public int getWeekWeightingNum()
  {
    return this.weekWeightingNum;
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

  public float getWeightByIndex(int paramInt)
  {
    float f = (float)this.weightArray[paramInt];
    if (f < 0.0F)
      f = 0.0F;
    return f;
  }

  public double[] getWeightDashArray()
  {
    return this.weightDashArray;
  }

  public int getWeightStandardAnchor()
  {
    return this.weightStandardAnchor;
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

  public void setFatStandardAnchor(int paramInt)
  {
    this.fatStandardAnchor = paramInt;
  }

  public void setGoalFatArray(double[] paramArrayOfDouble)
  {
    this.goalFatArray = paramArrayOfDouble;
  }

  public void setGoalWeightArray(double[] paramArrayOfDouble)
  {
    this.goalWeightArray = paramArrayOfDouble;
  }

  public void setNextWeekFirstBodyfat(float paramFloat)
  {
    this.nextWeekFirstBodyfat = paramFloat;
  }

  public void setNextWeekFirstWeight(float paramFloat)
  {
    this.nextWeekFirstWeight = paramFloat;
  }

  public void setPrevWeekLastBodyfat(float paramFloat)
  {
    this.prevWeekLastBodyfat = paramFloat;
  }

  public void setPrevWeekLastWeight(float paramFloat)
  {
    this.prevWeekLastWeight = paramFloat;
  }

  public void setWeekBodyFatChange(float paramFloat)
  {
    this.weekBodyFatChange = paramFloat;
  }

  public void setWeekFlag(int paramInt)
  {
    this.weekFlag = paramInt;
  }

  public void setWeekWeightChange(float paramFloat)
  {
    this.weekWeightChange = paramFloat;
  }

  public void setWeekWeightingNum(int paramInt)
  {
    this.weekWeightingNum = paramInt;
  }

  public void setWeightArray(double[] paramArrayOfDouble)
  {
    this.weightArray = paramArrayOfDouble;
  }

  public void setWeightDashArray(double[] paramArrayOfDouble)
  {
    this.weightDashArray = paramArrayOfDouble;
  }

  public void setWeightStandardAnchor(int paramInt)
  {
    this.weightStandardAnchor = paramInt;
  }

  public void updateData(BodyIndex paramBodyIndex, RoleBin paramRoleBin)
  {
    int i = DateUtils.getWeekendByTimestamp(paramBodyIndex.getTime());
    if (this.weightArray[(i - 1)] <= 0.0D)
      this.weekWeightingNum = (1 + this.weekWeightingNum);
    this.weightArray[(i - 1)] = paramBodyIndex.getWeight();
    if (paramBodyIndex.getBodyFat() > 0.0F)
    {
      this.bodyFatArray[(i - 1)] = paramBodyIndex.getBodyFat();
      if ((this.goalFatArray[(-1 + this.goalFatArray.length)] <= 0.0D) && (paramRoleBin.getGoal_fat() > 0.0F) && (paramRoleBin.getAge() <= 60))
      {
        this.goalFatArray[0] = paramRoleBin.getGoal_fat();
        this.goalFatArray[(-1 + this.goalFatArray.length)] = paramRoleBin.getGoal_fat();
      }
      if (paramRoleBin.getAge() > 60)
      {
        paramRoleBin.setGoal_fat(-1.0F);
        this.goalFatArray[0] = 1.0D;
      }
      if ((this.goalWeightArray[0] <= 0.0D) && (paramRoleBin.getGoal_weight() > 0.0F))
      {
        this.goalWeightArray[0] = paramRoleBin.getGoal_weight();
        this.goalWeightArray[(-1 + this.goalWeightArray.length)] = paramRoleBin.getGoal_weight();
      }
      this.bmiArray[(i - 1)] = paramBodyIndex.getBmi();
      this.timeArray[(i - 1)] = paramBodyIndex.getTime();
      if (this.prevWeekLastWeight > 0.0F)
        break label263;
      this.weekWeightChange = 0.0F;
      this.weekBodyFatChange = 0.0F;
    }
    label263: label320: label360: label504: 
    while (true)
    {
      caulateYAxisMaxAndMinValue(paramRoleBin);
      return;
      this.bodyFatArray[(i - 1)] = -1.0D;
      break;
      if (paramBodyIndex.getWeight() <= 0.0F)
      {
        this.weekWeightChange = 0.0F;
        this.weekBodyFatChange = 0.0F;
      }
      else
      {
        this.weekWeightChange = (paramBodyIndex.getWeight() - this.prevWeekLastWeight);
        if (this.prevWeekLastBodyfat > 0.0F)
        {
          this.weekBodyFatChange = (paramBodyIndex.getBodyFat() - this.prevWeekLastBodyfat);
          if (i == 1)
            break label452;
          if (this.weightArray[0] > 0.0D)
            break label440;
          this.weightDashArray[0] = this.prevWeekLastWeight;
          this.weightDashArray[(i - 1)] = paramBodyIndex.getWeight();
          if ((paramBodyIndex.getBodyFat() <= 0.0F) || (i == 1))
            break label478;
          if (this.bodyFatArray[0] > 0.0D)
            break label466;
          this.bodyFaDashArray[0] = this.prevWeekLastBodyfat;
          this.bodyFaDashArray[(i - 1)] = paramBodyIndex.getBodyFat();
        }
        while (true)
        {
          if (paramBodyIndex.getBodyFat() > 0.0F)
            break label504;
          this.bodyFaDashArray[(i - 1)] = -1.0D;
          break;
          this.weekBodyFatChange = 0.0F;
          break label320;
          this.weightDashArray[0] = -1.0D;
          break label360;
          this.weightDashArray[0] = paramBodyIndex.getWeight();
          break label360;
          this.bodyFaDashArray[0] = -1.0D;
          continue;
          if ((paramBodyIndex.getBodyFat() > 0.0F) && (i == 1))
            this.bodyFaDashArray[0] = paramBodyIndex.getBodyFat();
        }
      }
    }
  }

  public void updateEveryMe(EveryMeEntity paramEveryMeEntity)
  {
    this.everys.set(-1 + DateUtils.getWeekendByTimestamp(paramEveryMeEntity.getTime()), paramEveryMeEntity);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LatinWeekData
 * JD-Core Version:    0.6.2
 */
