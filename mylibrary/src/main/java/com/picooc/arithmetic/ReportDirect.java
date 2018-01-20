package com.picooc.arithmetic;

import com.picooc.domain.BodyFatReport;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.ReportEntity;
import com.picooc.domain.RoleBin;
import com.picooc.utils.DateUtils;
import com.picooc.utils.NumUtils;

public class ReportDirect
{
  static
  {
    System.loadLibrary("picooc");
  }

  public static native int[] caculateBodyTotalNum2(RoleBin paramRoleBin, ReportEntity paramReportEntity1, ReportEntity paramReportEntity2, ReportEntity paramReportEntity3, ReportEntity paramReportEntity4, ReportEntity paramReportEntity5, ReportEntity paramReportEntity6, ReportEntity paramReportEntity7, ReportEntity paramReportEntity8);

  public static native float[] calculateBasicDataByImpedanceOldVersion(int paramInt1, float paramFloat1, int paramInt2, float paramFloat2, int paramInt3);

  public static native float[] calculateBasicDataWhitePeople(int paramInt1, float paramFloat1, int paramInt2, float paramFloat2, int paramInt3);

  public static native float[] calculateBasicDataWhitePeopleByWeightAndFat(int paramInt1, float paramFloat1, int paramInt2, float paramFloat2, float paramFloat3);

  public static native BodyFatReport calculateIdealBodyFat(RoleBin paramRoleBin, BodyIndex paramBodyIndex, boolean paramBoolean);

  public static native float calculateIdealWeight(RoleBin paramRoleBin);

  public static native float[] calculateIdealWeightRange(RoleBin paramRoleBin);

  public static native int calculateImpedanceByBodyFat(int paramInt1, float paramFloat1, int paramInt2, float paramFloat2, float paramFloat3);

  public static String getBodyFatExMessage2ByRole(RoleBin paramRoleBin, BodyIndex paramBodyIndex)
  {
    long l1 = paramRoleBin.getChange_goal_weight_time();
    long l2 = paramBodyIndex.getTime();
    if ((l1 > l2) && (paramRoleBin.getGoal_fat() <= 0.0F) && (DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), l1) != DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), l2)));
    ReportEntity localReportEntity;
    do
    {
      return null;
      localReportEntity = judgeBodyFatByRole(paramRoleBin, paramBodyIndex);
      if (paramRoleBin.getWeight_change_target() > 0.0F)
      {
        if (localReportEntity.getStateCode() <= 3)
          return "大多数人在增重时，由于需要摄入非常多的热量，所以伴随肌肉的增加，脂肪也一定会增长。参照运动方案和饮食与营养调整自己的生活方式，便可以在最大限度生长肌肉的同时增加尽量少的脂肪。";
        return "您的脂肪率已经超标啦，继续增重会使脂肪同时增加，心脏病、糖尿病、胆固醇过高、高血压等肥胖高发病的风险都会持续攀升。";
      }
    }
    while (paramRoleBin.getWeight_change_target() > 0.0F);
    if (localReportEntity.getStateCode() <= 1)
    {
      if (paramRoleBin.getSex() == 1)
        return "如果不运动也不调整饮食结构，一味节食，那么体重即便下降，减少的也不是脂肪，而是水分和肌肉。这会令您变为更易胖的体质。请参照运动方案和饮食与营养调整自己的生活方式。";
      return "脂肪是细胞和神经系统的重要组成部分，过分减少身体脂肪会导致内分泌失调和月经紊乱哦。";
    }
    if ((localReportEntity.getStateCode() == 2) || (localReportEntity.getStateCode() == 3))
      return "如果不运动也不调整饮食结构，一味节食，那么体重即便下降，减少的也不是脂肪，而是水分和肌肉。这会令您变为更易胖的体质。请参照运动方案和饮食与营养调整自己的生活方式。";
    return "您需要立即开始减脂啦，过高的体脂肪率不仅造成形体上的臃肿，更是各种慢性疾病的主要导火线。请参照运动方案和饮食与营养调整自己的生活方式。回到标准的体脂水平时，您会惊喜地发现身体的变化。";
  }

  public static String getBodyFatExMessageByRole(RoleBin paramRoleBin, BodyIndex paramBodyIndex)
  {
    if (paramRoleBin.getAge() > 60)
    {
      ReportEntity localReportEntity = judgeBodyFatByRole(paramRoleBin, paramBodyIndex);
      String str3;
      if (localReportEntity.getStateCode() == 2)
        str3 = "您的体脂肪水平偏低，这会导致您摔倒时易骨折，损伤及外科伤口愈合缓慢，易出现神经性症状如失眠等。保有一定的体脂肪量，在生病时就不致消耗到身体中的组织蛋白质。";
      while (true)
      {
        return str3;
        if (localReportEntity.getStateCode() == 4)
          str3 = "您的体脂肪水平偏高，这会导致冠心病、高血压、高血脂、高血糖、脂肪肝等慢性代谢疾病的患病风险大大增高，请保证每天适当的运动量，并调整膳食结构。";
        else
          str3 = "您的体脂肪水平标准。保持健康的体脂肪水平可以帮助身体更好地进行代谢，保护脆弱的骨头，减少骨折机率；提高抗寒、抗病能力。";
      }
    }
    long l1 = paramRoleBin.getChange_goal_weight_time();
    long l2 = paramBodyIndex.getTime();
    if ((l1 > l2) && (paramRoleBin.getGoal_fat() <= 0.0F))
    {
      long l3 = DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), l1);
      if (l3 != DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), l2))
      {
        if (l3 < 0L)
          l3 = -l3;
        if (l3 == 0L);
        for (String str2 = "您在今天修改了目标体重，请先测量当前体重，才能计算您的目标脂肪率!"; ; str2 = "您在" + l3 + "天前修改了目标体重，请先测量当前体重，才能计算您的目标脂肪率!")
          return str2;
      }
    }
    BodyFatReport localBodyFatReport = calculateIdealBodyFat(paramRoleBin, paramBodyIndex, false);
    float f1 = localBodyFatReport.getReduceFatWeight();
    float f2 = localBodyFatReport.getGoalFatRace();
    String str1;
    if (paramRoleBin.getWeight_change_target() > 0.0F)
      if (paramBodyIndex.getWeight() >= paramRoleBin.getGoal_weight())
        if (paramBodyIndex.getBodyFat() >= paramRoleBin.getGoal_fat())
          str1 = "您的脂肪率已经达到增重限定目标，您需要控制热量摄入啦。否则在变壮的同时，也会变胖哦。";
    while (true)
    {
      return str1;
      str1 = "恭喜您，您的体重已达到增重目标，且脂肪率增加较少，注意保持哦！";
      continue;
      if (paramBodyIndex.getBodyFat() >= paramRoleBin.getGoal_fat())
      {
        str1 = "您的脂肪率已经达到增重限定目标，您需要控制热量摄入啦。否则在变壮的同时，也会变胖哦。";
      }
      else
      {
        float f3 = localBodyFatReport.getAddFatWeight();
        str1 = "最适合您增重目标的脂肪率为" + NumUtils.roundValue(f2) + "%，即您最多还能增加" + NumUtils.roundValue(f3) + "kg脂肪。";
        continue;
        if (paramRoleBin.getWeight_change_target() < 0.0F)
        {
          if (paramBodyIndex.getWeight() > paramRoleBin.getGoal_weight())
          {
            if (paramBodyIndex.getBodyFat() > paramRoleBin.getGoal_fat())
              str1 = "最适合您减重目标的脂肪率为" + NumUtils.roundValue(f2) + "%，即还需要减掉" + NumUtils.roundValue(f1) + "kg脂肪。";
            else if (localBodyFatReport.getRealFatState() <= 3)
              str1 = "恭喜您~脂肪率已达标，注意保持哦！";
            else
              str1 = "虽然脂肪率已达到您设定的目标，但与标准水平还有距离哦！请参照运动方案和饮食与营养健康减脂～";
          }
          else if (paramBodyIndex.getBodyFat() > paramRoleBin.getGoal_fat())
            str1 = "虽然体重已达标，但要达到目标脂肪率，仍要努力甩掉" + f1 + "kg脂肪哦！";
          else
            str1 = "恭喜您~脂肪率已达标，注意保持哦！";
        }
        else if ((paramBodyIndex.getWeight() <= paramRoleBin.getGoal_weight()) && (paramBodyIndex.getBodyFat() > paramRoleBin.getGoal_fat()))
        {
          if (judgeWeightByRole(paramRoleBin, paramBodyIndex.getWeight()).getStateCode() <= 2)
            str1 = "虽然体重已达标，但要达到目标脂肪率，仍要努力甩掉" + NumUtils.roundValue(f1) + "kg脂肪哦！";
          else
            str1 = "虽然体重已达到本次设定的目标，但要达到目标脂肪率，仍要努力甩掉" + NumUtils.roundValue(f1) + "kg脂肪哦！";
        }
        else if ((paramBodyIndex.getWeight() > paramRoleBin.getGoal_weight()) && (paramBodyIndex.getBodyFat() <= paramRoleBin.getGoal_fat()))
        {
          if (localBodyFatReport.getRealFatState() <= 3)
            str1 = "恭喜您~脂肪率已达标，注意保持哦！";
          else
            str1 = "虽然脂肪率已达到您设定的目标，但与标准水平还有距离哦！请参照运动方案和饮食与营养健康减脂～";
        }
        else if ((paramBodyIndex.getWeight() > paramRoleBin.getGoal_weight()) && (paramBodyIndex.getBodyFat() > paramRoleBin.getGoal_fat()))
          str1 = "您想保持的目标脂肪率为" + NumUtils.roundValue(paramRoleBin.getGoal_fat()) + "%，回到目标还需要减掉" + NumUtils.roundValue(f1) + "kg脂肪。";
        else if (localBodyFatReport.getRealFatState() <= 3)
          str1 = "恭喜您~脂肪率已达标，注意保持哦！";
        else
          str1 = "虽然脂肪率已达到您设定的目标，但与标准水平还有距离哦！请参照运动方案和饮食与营养健康减脂～";
      }
    }
  }

  public static String[] getWeightMessageByRole(RoleBin paramRoleBin, BodyIndex paramBodyIndex, ReportEntity paramReportEntity, BodyFatReport paramBodyFatReport)
  {
    float f1 = paramBodyFatReport.getAddMuscleWeight();
    float f2 = paramBodyFatReport.getReduceFatWeight();
    float f3 = paramBodyFatReport.getGoalFatRace();
    long l1 = paramRoleBin.getChange_goal_weight_time();
    long l2 = paramBodyIndex.getTime();
    if (l1 > l2)
    {
      long l3 = DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), l1);
      if (l3 != DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), l2))
      {
        if (l3 < 0L)
          l3 = -l3;
        String str3 = "今天";
        if (l3 != 0L)
          str3 = l3 + "天前";
        float f7 = paramBodyIndex.getWeight() - paramRoleBin.getGoal_weight();
        String str4;
        if (f7 > 0.0F)
          str4 = "您在" + str3 + "修改了目标体重，目前您需要减掉" + NumUtils.roundValue(f7) + "kg体重!";
        while (true)
        {
          return new String[] { str4 };
          if (f7 < 0.0F)
            str4 = "您在" + str3 + "修改了目标体重，目前您需要增加" + NumUtils.roundValue(-f7) + "kg体重!";
          else
            str4 = "您在" + str3 + "修改了目标体重，目前您已达标!";
        }
      }
    }
    if (paramRoleBin.getAge() <= 60)
    {
      float f6;
      String str2;
      if (paramRoleBin.getWeight_change_target() < 0.0F)
        if (paramRoleBin.getGoal_weight() < paramBodyIndex.getWeight())
        {
          f6 = paramBodyIndex.getWeight() - paramRoleBin.getGoal_weight();
          if (paramBodyIndex.getBodyFat() <= paramRoleBin.getGoal_fat())
            str2 = "距目标体重还有" + NumUtils.roundValue(f6) + "kg，加油~";
        }
      while (true)
      {
        return new String[] { str2 };
        if (paramRoleBin.getSex() == 1)
        {
          if (f1 > 0.0F)
            str2 = "在您需减掉的" + NumUtils.roundValue(f6) + "kg体重中，需减去的脂肪量为" + NumUtils.roundValue(f2) + "kg，需增加肌肉量" + NumUtils.roundValue(f1) + "kg，目标脂肪率为" + NumUtils.roundValue(f3) + "%。";
          else
            str2 = "在您需减掉的" + NumUtils.roundValue(f6) + "kg体重中，需减去的脂肪量为" + NumUtils.roundValue(f2) + "kg，目标脂肪率为" + NumUtils.roundValue(f3) + "%。";
        }
        else
        {
          str2 = "在您需减掉的" + NumUtils.roundValue(f6) + "kg体重中，需减去的脂肪量为" + NumUtils.roundValue(f2) + "kg，目标脂肪率为" + NumUtils.roundValue(f3) + "%。";
          continue;
          if (paramReportEntity.getStateCode() == 3)
          {
            str2 = "虽然体重已达到您设定的目标，但与标准水平还有距离哦！请去设定新的减重目标，开始健康减重吧~";
          }
          else
          {
            str2 = "恭喜您~您的体重已达标，注意保持哦！";
            continue;
            if (paramRoleBin.getWeight_change_target() > 0.0F)
            {
              if (paramRoleBin.getGoal_weight() <= paramBodyIndex.getWeight())
              {
                if (paramReportEntity.getStateCode() >= 2)
                  str2 = "恭喜您~您的体重已经达到增重目标，继续努力哦！";
                else
                  str2 = "恭喜您~您的体重已经达到本次增重目标，但与标准水平还有" + NumUtils.roundValue(Math.abs(0.9D * paramReportEntity.getNum2() - paramBodyIndex.getWeight())) + "kg差距，继续努力哦！";
              }
              else
              {
                float f4 = paramRoleBin.getGoal_weight() - paramBodyIndex.getWeight();
                if (paramBodyIndex.getBodyFat() >= paramRoleBin.getGoal_fat())
                {
                  str2 = "您还需要增加" + NumUtils.roundValue(f4) + "kg体重，但您的脂肪率已经达到本次增重的限定目标啦。请控制热量摄入，增加运动，健康增重。";
                }
                else
                {
                  float f5 = paramBodyFatReport.getAddFatWeight();
                  if (f1 <= 0.01D)
                    str2 = "在您需增加的" + NumUtils.roundValue(f4) + "kg体重中，可能增加的脂肪量" + NumUtils.roundValue(f5) + "kg。";
                  else
                    str2 = "在您需增加的" + NumUtils.roundValue(f4) + "kg体重中，需增加的肌肉量为" + NumUtils.roundValue(f1) + "kg，可能增加的脂肪量" + NumUtils.roundValue(f5) + "kg。";
                }
              }
            }
            else if ((paramBodyIndex.getWeight() <= paramRoleBin.getGoal_weight()) && (paramBodyIndex.getBodyFat() > paramRoleBin.getGoal_fat()))
            {
              if (paramReportEntity.getStateCode() <= 2)
                str2 = "恭喜您~您的体重已达标，注意保持哦！";
              else
                str2 = "恭喜您~您的体重已达到本次设定的目标，但与标准水平还有" + NumUtils.roundValue(Math.abs(1.1D * paramReportEntity.getNum2() - paramBodyIndex.getWeight())) + "kg差距哦！请去设定新的减重目标，开始健康减重吧~";
            }
            else if ((paramBodyIndex.getWeight() > paramRoleBin.getGoal_weight()) && (paramBodyIndex.getBodyFat() <= paramRoleBin.getGoal_fat()))
              str2 = "距目标体重还有" + NumUtils.roundValue(paramBodyIndex.getWeight() - paramRoleBin.getGoal_weight()) + "kg，加油~";
            else if ((paramBodyIndex.getWeight() > paramRoleBin.getGoal_weight()) && (paramBodyIndex.getBodyFat() > paramRoleBin.getGoal_fat()))
              str2 = "您想保持的体重为" + NumUtils.roundValue(paramRoleBin.getGoal_weight()) + "kg，回到目标还有" + NumUtils.roundValue(paramBodyIndex.getWeight() - paramRoleBin.getGoal_weight()) + "kg，加油～";
            else if (paramReportEntity.getStateCode() >= 3)
              str2 = "恭喜您~您的体重已达到本次设定的目标，但与标准水平还有" + NumUtils.roundValue(Math.abs(1.1D * paramReportEntity.getNum2() - paramBodyIndex.getWeight())) + "kg差距哦！请去设定新的减重目标，开始健康减重吧~";
            else
              str2 = "恭喜您~您的体重已达标，注意保持哦！";
          }
        }
      }
    }
    String str1;
    if (paramRoleBin.getWeight_change_target() > 0.0F)
      if (paramBodyIndex.getWeight() < paramRoleBin.getGoal_weight())
        str1 = "您还需要增加" + NumUtils.roundValue(paramRoleBin.getGoal_weight() - paramBodyIndex.getWeight()) + "kg体重。";
    while (true)
    {
      return new String[] { str1, "请坚持每天称重，保持标准体重是健康的前提，体重的持续下降则是重大疾病的信号。" };
      str1 = "恭喜您~您的体重已达标，注意保持哦！";
      continue;
      boolean bool = paramRoleBin.getWeight_change_target() < 0.0F;
      str1 = null;
      if (!bool)
        if (paramBodyIndex.getWeight() <= paramRoleBin.getGoal_weight())
          str1 = "恭喜您~您的体重已达标，注意保持哦！";
        else
          str1 = "您还需要减少" + NumUtils.roundValue(paramBodyIndex.getWeight() - paramRoleBin.getGoal_weight()) + "kg体重。";
    }
  }

  public static native ReportEntity judgeBMIByRole(RoleBin paramRoleBin, BodyIndex paramBodyIndex);

  public static native ReportEntity judgeBmrByRole(RoleBin paramRoleBin, BodyIndex paramBodyIndex);

  public static native ReportEntity judgeBodyFatByRole(RoleBin paramRoleBin, BodyIndex paramBodyIndex);

  public static native int judgeBodyType(RoleBin paramRoleBin, BodyIndex paramBodyIndex);

  public static native ReportEntity judgeBoneMassByRole(RoleBin paramRoleBin, BodyIndex paramBodyIndex);

  public static native ReportEntity judgeInFatByRole(RoleBin paramRoleBin, BodyIndex paramBodyIndex);

  public static native ReportEntity judgeMuscleRaceByRole(RoleBin paramRoleBin, BodyIndex paramBodyIndex);

  public static native ReportEntity judgeProteinRaceByRole(RoleBin paramRoleBin, BodyIndex paramBodyIndex);

  public static native ReportEntity judgeWaterRaceByRole(RoleBin paramRoleBin, BodyIndex paramBodyIndex);

  public static native ReportEntity judgeWeightByRole(RoleBin paramRoleBin, float paramFloat);
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ReportDirect
 * JD-Core Version:    0.6.2
 */
