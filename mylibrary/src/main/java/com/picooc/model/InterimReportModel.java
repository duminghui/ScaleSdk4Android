package com.picooc.model;

import android.content.Context;
import android.graphics.Color;
import com.picooc.MyApplication;
import com.picooc.arithmetic.ReportDirect;
import com.picooc.db.OperationDB;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.ReportEntity;
import com.picooc.domain.RoleBin;
import com.picooc.utils.DateUtils;
import com.picooc.utils.NumUtils;
import java.io.Serializable;
import java.util.ArrayList;

public class InterimReportModel
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private final int WBChangeDown = 1;
  private final int WBChangeNone = 2;
  private final int WBChangeUp = 3;
  private String _weightTide;
  private ArrayList<RoleBin> allRoleInfos;
  private ArrayList<BodyIndex> cBodyDatas;
  private long cDays;
  private long cEndTime;
  private long cLastWeightingTime;
  private RoleBin cRole;
  private long cStartTime;
  private int cWeekNos;
  private ArrayList<BodyIndex> cacheNatureBodyDates;
  private long cacheNatureDays;
  private long cacheNatureEndTime;
  private long cacheNatureLastWeightingTime;
  private long cacheNatureStartTime;
  private int cacheNatureWeekNos;
  boolean canComeIn;
  boolean currentInterimIsOver;
  float currentWegiht;
  long diffDays;
  float diffWeight;
  int diffWeightStatusCode;
  String endData;
  float firstWeight;
  int firstWeightIndex;
  boolean isFirstTargetWeightError;
  int lastInterimWeightsCount;
  long lastTime;
  float lastWeight;
  int lastWeightIndex;
  String[] lifePattern;
  int lifePatternColor;
  String[] lifePatternMessages;
  int loseWeightColor;
  ArrayList<InterimReportMessageEntityNew> loseWeightMessages;
  String loseWeightPattern;
  float maxWeight;
  int maxWeightCount;
  float minWeight;
  int minWeightCount;
  private String outCycleDownStr;
  private String outCycleUpperStr;
  private float outCycleWeightChange;
  String sectionHeaderText1;
  String sectionHeaderText2;
  boolean showOldInterim;
  String startDate;
  String tableHeaderViewTitle;
  ArrayList<String> todayWeightMessages;
  float totalWeight;
  ArrayList<Float> weightArray;
  int weightChangeType;

  public InterimReportModel(Context paramContext, RoleBin paramRoleBin)
  {
    this.cRole = paramRoleBin;
    this.isFirstTargetWeightError = false;
    this.canComeIn = initPeriod(paramContext);
    if (!this.canComeIn)
    {
      this.outCycleUpperStr = "--";
      this.outCycleDownStr = "连续称重2天以上才能查看哦~";
    }
    label144: label277: label315: 
    while (true)
    {
      return;
      if (DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), this.cEndTime) >= 0L)
      {
        long l = DateUtils.getDayStartTimeAndEndTimeByTimestamp(System.currentTimeMillis())[1];
        ArrayList localArrayList = OperationDB.selectBodyIndexBetweenStartTimeAndEndTimeAndRoleID(paramContext, DateUtils.getDayStartTimeAndEndTimeByTimestamp(this.cEndTime)[0], l, this.cRole.getRole_id());
        if ((localArrayList != null) && (localArrayList.size() > 0))
        {
          this.lastInterimWeightsCount = localArrayList.size();
          this.currentInterimIsOver = true;
          if (this.canComeIn)
            this.showOldInterim = true;
          if (this.cRole.getWeight_change_target() <= 0.0F)
            break label277;
          this.weightChangeType = 3;
          this.sectionHeaderText2 = "增重方式";
        }
      }
      while (true)
      {
        if ((this.cBodyDatas == null) || (this.cBodyDatas.size() <= 0))
          break label315;
        this.weightArray = getAveWeightForSevenDaysAgoWithWeekDates(this.cBodyDatas, DateUtils.getDayStartTimeAndEndTimeByTimestamp(this.cStartTime)[0]);
        dealWithAllWeights(paramContext);
        this.tableHeaderViewTitle = initTableHeaderViewTitle();
        initSectionHeaderText1();
        initSectionHeaderText2();
        initTodayWeightMessages();
        this.diffDays = (1L + DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), this.cStartTime));
        this.loseWeightPattern = calculateLoseWeightStatus(paramContext);
        return;
        this.lastInterimWeightsCount = 0;
        break;
        this.currentInterimIsOver = false;
        this.showOldInterim = false;
        break label144;
        if (this.cRole.getWeight_change_target() == 0.0F)
        {
          this.weightChangeType = 2;
          this.sectionHeaderText2 = "减重方式";
        }
        else
        {
          this.weightChangeType = 1;
          this.sectionHeaderText2 = "减重方式";
        }
      }
    }
  }

  private void caculateNatureStageByNatureStartTime(long paramLong1, long paramLong2, ArrayList<BodyIndex> paramArrayList, RoleBin paramRoleBin)
  {
    BodyIndex localBodyIndex = getFirstWeightingDataByAllData(paramArrayList, DateUtils.getDayStartTimeAndEndTimeByTimestamp(paramLong1)[0]);
    if (localBodyIndex == null)
      return;
    long l1 = DateUtils.getDayStartTimeAndEndTimeByTimestamp(localBodyIndex.getTime())[0];
    float f = caculateWeekNosByRoleAndFirstBodyIndex(paramRoleBin, localBodyIndex);
    long l2 = 7 * NumUtils.roundFloatToInt(f);
    long l3 = 86400000L + (l1 + 86400000L * l2) - 1000L;
    if (l3 >= paramLong2)
      l3 = paramLong2;
    ArrayList localArrayList = filterBodyIndex(paramArrayList, l1, l3);
    int i = 0;
    label97: int j = localArrayList.size();
    long l4;
    long l5;
    if (i >= j)
    {
      l4 = ((BodyIndex)localArrayList.get(0)).getTime();
      l5 = ((BodyIndex)localArrayList.get(-1 + localArrayList.size())).getTime();
      if (DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(l5, l4) > 0L)
      {
        this.cacheNatureWeekNos = NumUtils.roundFloatToInt(f);
        this.cacheNatureDays = l2;
        this.cacheNatureStartTime = l4;
        if ((DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(paramLong2, System.currentTimeMillis()) != 0L) || (isChangeGoalWeightAfterTimeStamp(paramLong2)))
          break label277;
      }
    }
    label277: for (this.cacheNatureEndTime = (l4 + 86400000L * l2); ; this.cacheNatureEndTime = paramLong2)
    {
      this.cacheNatureLastWeightingTime = l5;
      this.cacheNatureBodyDates = localArrayList;
      if (l3 >= paramLong2)
        break;
      caculateNatureStageByNatureStartTime(l3, paramLong2, paramArrayList, paramRoleBin);
      return;
      if (((BodyIndex)localArrayList.get(i)).getBodyFat() == 0.0F)
      {
        localArrayList.remove(i);
        i--;
      }
      i++;
      break label97;
    }
  }

  private boolean caculateStageByStartTimeAndEndTimeAndRole(Context paramContext, long paramLong1, long paramLong2, RoleBin paramRoleBin)
  {
    long l1 = DateUtils.getDayStartTimeAndEndTimeByTimestamp(paramLong1)[0];
    long l2 = DateUtils.getDayStartTimeAndEndTimeByTimestamp(paramLong2)[1];
    ArrayList localArrayList1 = OperationDB.selectBodyIndexBetweenStartTimeAndEndTimeAndRoleID(paramContext, l1, l2, paramRoleBin.getRole_id());
    for (int i = 0; ; i++)
    {
      int j = localArrayList1.size();
      if (i >= j)
      {
        if ((localArrayList1 != null) && (localArrayList1.size() > 1))
          break;
        return false;
      }
      if (((BodyIndex)localArrayList1.get(i)).getBodyFat() == 0.0F)
      {
        localArrayList1.remove(i);
        i--;
      }
    }
    BodyIndex localBodyIndex = (BodyIndex)localArrayList1.get(0);
    long l3 = localBodyIndex.getTime();
    long l4 = ((BodyIndex)localArrayList1.get(-1 + localArrayList1.size())).getTime();
    if (DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(l4, l3) == 0L)
      return false;
    float f = caculateWeekNosByRoleAndFirstBodyIndex(paramRoleBin, localBodyIndex);
    long l5 = 7 * NumUtils.roundFloatToInt(f);
    if (l5 >= DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(l2, l3))
    {
      this.cWeekNos = NumUtils.roundFloatToInt(f);
      this.cDays = l5;
      this.cStartTime = l3;
      if ((DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(l2, System.currentTimeMillis()) == 0L) && (!isChangeGoalWeightAfterTimeStamp(l2)));
      for (this.cEndTime = (l3 + 86400000L * l5); ; this.cEndTime = l2)
      {
        this.cLastWeightingTime = l4;
        this.cBodyDatas = localArrayList1;
        return true;
      }
    }
    long l6 = l3 + 86400000L * l5;
    ArrayList localArrayList2 = filterBodyIndex(localArrayList1, l3, l6);
    long l7 = ((BodyIndex)localArrayList2.get(0)).getTime();
    long l8 = ((BodyIndex)localArrayList2.get(-1 + localArrayList2.size())).getTime();
    if (DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(l8, l7) > 0L)
    {
      this.cacheNatureWeekNos = NumUtils.roundFloatToInt(f);
      this.cacheNatureDays = l5;
      this.cacheNatureStartTime = l7;
      this.cacheNatureEndTime = l6;
      this.cacheNatureLastWeightingTime = l8;
      this.cacheNatureBodyDates = localArrayList2;
    }
    caculateNatureStageByNatureStartTime(l6, l2, localArrayList1, paramRoleBin);
    this.cWeekNos = this.cacheNatureWeekNos;
    this.cDays = this.cacheNatureDays;
    this.cStartTime = this.cacheNatureStartTime;
    this.cEndTime = this.cacheNatureEndTime;
    this.cLastWeightingTime = this.cacheNatureLastWeightingTime;
    this.cBodyDatas = this.cacheNatureBodyDates;
    return (this.cBodyDatas != null) && (this.cBodyDatas.size() > 0);
  }

  private float caculateWeekNosByRoleAndFirstBodyIndex(RoleBin paramRoleBin, BodyIndex paramBodyIndex)
  {
    float f1 = paramRoleBin.getWeight_change_target();
    ReportEntity localReportEntity = ReportDirect.judgeWeightByRole(paramRoleBin, paramBodyIndex.getWeight());
    float f2 = Math.abs(paramRoleBin.getGoal_weight() - paramBodyIndex.getWeight());
    if (f2 <= 0.5D)
      return 2.0F;
    if (f1 > 0.0F)
    {
      if (localReportEntity.getStateCode() <= 1)
      {
        if (f2 > 5.0F)
          return f2 / 1.0F;
        return f2 / 0.5F;
      }
      return f2 / 1.0F;
    }
    if (localReportEntity.getStateCode() >= 3)
    {
      if (f2 > 5.0F)
        return f2 / 1.0F;
      return f2 / 0.5F;
    }
    return f2 / 0.5F;
  }

  private String calculateLoseWeightStatus(Context paramContext)
  {
    MyApplication localMyApplication = (MyApplication)paramContext.getApplicationContext();
    this.loseWeightMessages = new ArrayList();
    String str1 = "";
    float f1 = 0.0F;
    float f2 = 2000.0F;
    int i = -7 + this.weightArray.size();
    int j = -1 + this.weightArray.size();
    int k;
    int m;
    float f3;
    label138: float f4;
    label179: Integer localInteger;
    ArrayList localArrayList1;
    int n;
    label310: float f5;
    float f6;
    int i4;
    String str4;
    float f8;
    String str11;
    if ((j < i) || (j < 0))
    {
      boolean bool = f1 - f2 < 2.0F;
      k = 0;
      if (!bool)
        k = 2;
      m = ReportDirect.judgeWeightByRole(this.cRole, this.firstWeight).getStateCode();
      if (this.weightChangeType > 2)
        break label996;
      if (m <= 2)
        break label988;
      if (this.firstWeight - localMyApplication.getCurrentRole().getGoal_weight() <= 5.0F)
        break label980;
      f3 = 1.0F;
      f4 = (float)(this.diffDays / 7.0D * f3);
      if (this.maxWeight - this.minWeight > f4)
        break label1041;
      this._weightTide = "波动平稳";
      k = 1;
      long l = 1L + DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(this.cLastWeightingTime, this.cStartTime);
      InterimReportMessageEntityNew localInterimReportMessageEntityNew1 = new InterimReportMessageEntityNew();
      localInterimReportMessageEntityNew1.setContent(l + "天内，体重" + this._weightTide + "，体重最高值为" + NumUtils.roundValue(this.maxWeight) + "kg，最低值为" + NumUtils.roundValue(this.minWeight) + "kg。");
      this.loseWeightMessages.add(localInterimReportMessageEntityNew1);
      localInteger = Integer.valueOf(0);
      localArrayList1 = OperationDB.selectDayValuesByTimestampAndRid(paramContext, this.cStartTime, localMyApplication.getCurrentRole().getRole_id());
      n = 0;
      int i1 = localArrayList1.size();
      if (n < i1)
        break label1101;
      if ((localArrayList1 == null) || (localArrayList1.size() == 0))
      {
        localArrayList1 = OperationDB.selectDayValuesBeforeTimestampAndRid(paramContext, this.cStartTime, localMyApplication.getCurrentRole().getRole_id());
        if ((localArrayList1 == null) || (localArrayList1.size() == 0))
          localArrayList1 = OperationDB.selectDayValuesAfterTimestampAndRid(paramContext, this.cStartTime, localMyApplication.getCurrentRole().getRole_id());
      }
      ArrayList localArrayList2 = OperationDB.selectDayValuesByTimestampAndRid(paramContext, this.lastTime, localMyApplication.getCurrentRole().getRole_id());
      if ((localArrayList2 == null) || (localArrayList2.size() == 0))
      {
        localArrayList2 = OperationDB.selectDayValuesBeforeTimestampAndRid(paramContext, this.lastTime, localMyApplication.getCurrentRole().getRole_id());
        if ((localArrayList2 == null) || (localArrayList2.size() == 0))
          localArrayList2 = OperationDB.selectDayValuesAfterTimestampAndRid(paramContext, this.lastTime, localMyApplication.getCurrentRole().getRole_id());
      }
      BodyIndex localBodyIndex1 = BodyIndex.getAvgValueByArrayList(localArrayList1);
      BodyIndex localBodyIndex2 = BodyIndex.getAvgValueByArrayList(localArrayList2);
      int i2 = (int)(localBodyIndex2.getMusde_race() * localBodyIndex2.getWeight()) - (int)(localBodyIndex1.getMusde_race() * localBodyIndex1.getWeight());
      if (Math.abs(i2) < 5)
        i2 = 0;
      f5 = i2 / 100.0F;
      int i3 = (int)(localBodyIndex2.getBodyFat() * localBodyIndex2.getWeight()) - (int)(localBodyIndex1.getBodyFat() * localBodyIndex1.getWeight());
      if (Math.abs(i3) < 5)
        i3 = 0;
      f6 = i3 / 100.0F;
      i4 = (int)(localBodyIndex2.getBasic_metabolism() - localBodyIndex1.getBasic_metabolism());
      String str2 = NumUtils.roundValue(localBodyIndex1.getWeight());
      String str3 = NumUtils.roundValue(localBodyIndex2.getWeight());
      float f7 = Float.parseFloat(str2);
      str4 = NumUtils.roundValue(Float.parseFloat(str3) - f7);
      f8 = Float.parseFloat(str4);
      if (Math.abs(f8) < 0.1F)
        f8 = 0.0F;
      if (this.weightChangeType > 2)
        break label2600;
      if (f8 <= 0.0F)
        break label1522;
      if ((f6 <= 0.0F) && ((f8 != 0.01D) || (f6 != 0.0F)))
        break label1230;
      localInteger = Integer.valueOf(2);
      if (f5 < 0.0F)
        break label1168;
      if (f6 > 0.0F)
        break label1136;
      str11 = "略微上升";
      label711: InterimReportMessageEntityNew localInterimReportMessageEntityNew30 = new InterimReportMessageEntityNew();
      localInterimReportMessageEntityNew30.setContent("在您增加的" + str4 + "kg体重中，体脂肪" + str11 + "。这说明您应该控制热量摄入啦。目前您摄入的热量超过了每天的消耗量，造成脂肪在身体里逐渐堆积。请参照饮食与营养调整自己的生活方式，健康控制体重。");
      this.loseWeightMessages.add(localInterimReportMessageEntityNew30);
      label770: if ((k == 1) && (localInteger.intValue() == 1))
      {
        str1 = "健康";
        this.loseWeightColor = Color.argb(153, 140, 255, 121);
      }
      if ((k == 2) && (localInteger.intValue() == 1))
      {
        str1 = "较健康";
        this.loseWeightColor = Color.argb(153, 190, 255, 205);
      }
      if ((k == 1) && (localInteger.intValue() == 2))
      {
        str1 = "较不健康";
        this.loseWeightColor = Color.argb(153, 255, 155, 177);
      }
      if ((k == 2) && (localInteger.intValue() == 2))
      {
        str1 = "不健康";
        this.loseWeightColor = Color.argb(153, 255, 110, 110);
      }
    }
    while (true)
    {
      return str1;
      float f9 = ((Float)this.weightArray.get(j)).floatValue();
      if (f9 <= -1.0F);
      while (true)
      {
        j--;
        break;
        if (f9 > f1)
          f1 = f9;
        if (f9 < f2)
          f2 = f9;
      }
      label980: f3 = 0.5F;
      break label138;
      label988: f3 = 0.5F;
      break label138;
      label996: if (m < 2)
      {
        if (localMyApplication.getCurrentRole().getGoal_weight() - this.firstWeight > 5.0F)
        {
          f3 = 1.0F;
          break label138;
        }
        f3 = 0.5F;
        break label138;
      }
      f3 = 1.0F;
      break label138;
      label1041: if (this.maxWeight - this.minWeight <= 1.0F + f4)
      {
        this._weightTide = "略有波动";
        k = 1;
        break label179;
      }
      if (this.maxWeight - this.minWeight <= 1.0F + f4)
        break label179;
      this._weightTide = "波动较大";
      k = 2;
      break label179;
      label1101: if (((BodyIndex)localArrayList1.get(n)).getBodyFat() == 0.0F)
      {
        localArrayList1.remove(n);
        n--;
      }
      n++;
      break label310;
      label1136: str11 = "上升了" + NumUtils.roundValue(f6) + "kg之多";
      break label711;
      label1168: InterimReportMessageEntityNew localInterimReportMessageEntityNew29 = new InterimReportMessageEntityNew();
      localInterimReportMessageEntityNew29.setContent("在您增加的" + str4 + "kg体重中，体脂肪上升了" + f6 + "kg之多，并伴有水分和肌肉的损失。这说明您应该控制热量摄入，增加运动啦。否则一方面摄入热量过多会造成脂肪囤积，另一方面由于不运动，肌肉的逐渐流失会造成基础代谢率持续下降，摄入的热量就更加消耗不掉。请参照运动方案和饮食与营养调整自己的生活方式，开始健康减重。");
      this.loseWeightMessages.add(localInterimReportMessageEntityNew29);
      break label770;
      label1230: if (f6 < 0.0F)
      {
        localInteger = Integer.valueOf(1);
        if (i4 > 0)
        {
          InterimReportMessageEntityNew localInterimReportMessageEntityNew33 = new InterimReportMessageEntityNew();
          localInterimReportMessageEntityNew33.setContent("虽然体重增加了" + str4 + "kg，但体脂肪实际下降了" + NumUtils.roundValue(-f6) + "kg，基础代谢率也上升了" + i4 + "kcal。这说明您身材正在变得更好，而且代谢能力增强了，每天消耗更多热量，就不易复胖。请不必担忧体重的上升，您的减重方式非常健康，请继续保持。");
          this.loseWeightMessages.add(localInterimReportMessageEntityNew33);
          break label770;
        }
        InterimReportMessageEntityNew localInterimReportMessageEntityNew34 = new InterimReportMessageEntityNew();
        localInterimReportMessageEntityNew34.setContent("虽然体重增加了" + str4 + "kg，但体脂肪实际下降了" + NumUtils.roundValue(-f6) + "kg。这说明您身材正在变得更好，而且代谢能力增强了，每天消耗更多热量，就不易复胖。请不必担忧体重的上升，您的减重方式非常健康，请继续保持。");
        this.loseWeightMessages.add(localInterimReportMessageEntityNew34);
        break label770;
      }
      if (f5 <= 0.0F)
        break label770;
      localInteger = Integer.valueOf(1);
      if (i4 < 0)
      {
        InterimReportMessageEntityNew localInterimReportMessageEntityNew31 = new InterimReportMessageEntityNew();
        localInterimReportMessageEntityNew31.setContent("虽然体重增加了" + str4 + "kg，但体脂肪并没有增加。这说明您身材正在变得更好，而且代谢能力增强了，每天消耗更多热量，就不易复胖。请不必担忧体重的上升，您的减重方式非常健康，请继续保持。");
        this.loseWeightMessages.add(localInterimReportMessageEntityNew31);
        break label770;
      }
      InterimReportMessageEntityNew localInterimReportMessageEntityNew32 = new InterimReportMessageEntityNew();
      localInterimReportMessageEntityNew32.setContent("虽然体重增加了" + str4 + "kg，但体脂肪并没有增加,基础代谢率上升了" + i4 + "kcal。这说明您身材正在变得更好，而且代谢能力增强了，每天消耗更多热量，就不易复胖。请不必担忧体重的上升，您的减重方式非常健康，请继续保持。");
      this.loseWeightMessages.add(localInterimReportMessageEntityNew32);
      break label770;
      label1522: if (f8 < 0.0F)
      {
        if (f5 >= 0.0F)
        {
          String str10;
          if (f6 < 0.0F)
          {
            str10 = "下降了" + -f6 + "kg";
            if (i4 <= 0)
              break label1666;
            InterimReportMessageEntityNew localInterimReportMessageEntityNew27 = new InterimReportMessageEntityNew();
            localInterimReportMessageEntityNew27.setContent("在您减掉的" + NumUtils.roundValue(-f8) + "kg体重中，体脂肪" + str10 + "，基础代谢率上升了" + i4 + "kcal。您的代谢能力正在增强中，每天消耗更多热量，就不易复胖。请继续保持当前的生活方式。");
            this.loseWeightMessages.add(localInterimReportMessageEntityNew27);
          }
          while (true)
          {
            localInteger = Integer.valueOf(1);
            break;
            str10 = "略微下降";
            break label1570;
            InterimReportMessageEntityNew localInterimReportMessageEntityNew28 = new InterimReportMessageEntityNew();
            localInterimReportMessageEntityNew28.setContent("在您减掉的" + NumUtils.roundValue(-f8) + "kg体重中，体脂肪" + str10 + "。您的代谢能力正在增强中，每天消耗更多热量，就不易复胖。请继续保持当前的生活方式。");
            this.loseWeightMessages.add(localInterimReportMessageEntityNew28);
          }
        }
        if (f6 > 0.0F)
        {
          localInteger = Integer.valueOf(2);
          InterimReportMessageEntityNew localInterimReportMessageEntityNew26 = new InterimReportMessageEntityNew();
          localInterimReportMessageEntityNew26.setContent("您虽然减掉了" + NumUtils.roundValue(-f8) + "kg体重，但体脂肪反而上升了" + NumUtils.roundValue(f6) + "kg。这说明您减掉的都是身体必需的水分和肌肉，影响健康和身材的脂肪却没有减掉。如果您正在过分节食，请马上停止，并参照运动方案和饮食与营养调整生活方式，健康减脂。");
          this.loseWeightMessages.add(localInterimReportMessageEntityNew26);
          break label770;
        }
        if ((f6 < 0.0F) && (f5 < 0.0F))
        {
          if (f6 / f5 >= 1.5D)
          {
            localInteger = Integer.valueOf(1);
            InterimReportMessageEntityNew localInterimReportMessageEntityNew25 = new InterimReportMessageEntityNew();
            localInterimReportMessageEntityNew25.setContent("在您减掉的" + NumUtils.roundValue(-f8) + "kg体重中，体脂肪占" + NumUtils.roundValue(-f6) + "kg。这说明您减掉的大部分都是体脂肪，伴随着一些正常流失的水分，您的减重方式比较健康，请继续保持。");
            this.loseWeightMessages.add(localInterimReportMessageEntityNew25);
            break label770;
          }
          localInteger = Integer.valueOf(2);
          InterimReportMessageEntityNew localInterimReportMessageEntityNew24 = new InterimReportMessageEntityNew();
          localInterimReportMessageEntityNew24.setContent("在您减掉的" + NumUtils.roundValue(-f8) + "kg体重中，体脂肪只占" + NumUtils.roundValue(-f6) + "kg。这说明您减掉的大部分都是水分，体重极易反弹。过度节食的减重方式严重有损健康，请参照运动方案和饮食与营养调整自己的生活方式。");
          this.loseWeightMessages.add(localInterimReportMessageEntityNew24);
          break label770;
        }
        if ((f6 != 0.0F) || (f5 >= 0.0F))
          break label770;
        localInteger = Integer.valueOf(2);
        InterimReportMessageEntityNew localInterimReportMessageEntityNew23 = new InterimReportMessageEntityNew();
        localInterimReportMessageEntityNew23.setContent("您减掉的" + NumUtils.roundValue(-f8) + "kg体重中没有体脂肪的减少，全部是水分，体重极易反弹。过度节食的减重方式严重有损健康，请参照运动方案和饮食与营养调整自己的生活方式。");
        this.loseWeightMessages.add(localInterimReportMessageEntityNew23);
        break label770;
      }
      label1570: if (f5 < 0.0F)
      {
        localInteger = Integer.valueOf(2);
        if (f6 <= 0.0F);
        for (String str9 = ""; ; str9 = NumUtils.roundValue(f6) + "kg之多")
        {
          InterimReportMessageEntityNew localInterimReportMessageEntityNew22 = new InterimReportMessageEntityNew();
          localInterimReportMessageEntityNew22.setContent("您的体重虽然没有变化，但体脂肪上升了" + str9 + "，并伴有水分和肌肉的损失。这说明您应该控制热量摄入，增加运动啦。否则一方面摄入热量过多会造成脂肪囤积，另一方面由于不运动，肌肉的逐渐流失会造成基础代谢率持续下降，摄入的热量就更加消耗不掉。请参照运动方案和饮食与营养调整自己的生活方式，开始健康减重。");
          this.loseWeightMessages.add(localInterimReportMessageEntityNew22);
          break;
        }
      }
      label1666: if (f5 > 0.0F)
      {
        if (f6 >= 0.0F);
        for (String str8 = ""; ; str8 = NumUtils.roundValue(-f6) + "kg")
        {
          localInteger = Integer.valueOf(1);
          if (i4 <= 0)
            break label2295;
          InterimReportMessageEntityNew localInterimReportMessageEntityNew20 = new InterimReportMessageEntityNew();
          localInterimReportMessageEntityNew20.setContent("您的体重虽然没有变化，但体脂肪下降了" + str8 + "，基础代谢率上升了" + i4 + "kcal。您的代谢能力正在增强中，每天消耗更多热量，就不易复胖。请继续保持当前的生活方式。");
          this.loseWeightMessages.add(localInterimReportMessageEntityNew20);
          break;
        }
        label2295: InterimReportMessageEntityNew localInterimReportMessageEntityNew21 = new InterimReportMessageEntityNew();
        localInterimReportMessageEntityNew21.setContent("您的体重虽然没有变化，但体脂肪下降了" + NumUtils.roundValue(-f6) + "kg。您的代谢能力正在增强中，每天消耗更多热量，就不易复胖。请继续保持当前的生活方式。");
        this.loseWeightMessages.add(localInterimReportMessageEntityNew21);
        break label770;
      }
      ArrayList localArrayList4 = OperationDB.selectBodyIndexBetweenStartTimeAndEndTimeAndRoleID(paramContext, this.cStartTime, this.lastTime, localMyApplication.getCurrentRole().getRole_id());
      int i7 = localArrayList4.size();
      int i8 = 0;
      BodyIndex localBodyIndex5;
      if (i7 > 0)
        localBodyIndex5 = (BodyIndex)localArrayList4.get(-1 + localArrayList4.size());
      label2592: label2594: for (int i9 = -2 + localArrayList4.size(); ; i9--)
      {
        i8 = 0;
        if (i9 <= 0);
        while (true)
        {
          if (i8 == 0)
            break label2592;
          InterimReportMessageEntityNew localInterimReportMessageEntityNew17 = new InterimReportMessageEntityNew();
          localInterimReportMessageEntityNew17.setContent("您的体重没有变化，身体成分也没有发生任何变化。请参照运动方案和饮食与营养，开始健康减重吧~");
          this.loseWeightMessages.add(localInterimReportMessageEntityNew17);
          localInteger = Integer.valueOf(1);
          break;
          BodyIndex localBodyIndex6 = (BodyIndex)localArrayList4.get(i9);
          if (localBodyIndex6.getBodyFat() == localBodyIndex5.getBodyFat())
            break label2594;
          if (localBodyIndex6.getBodyFat() < localBodyIndex5.getBodyFat())
          {
            InterimReportMessageEntityNew localInterimReportMessageEntityNew18 = new InterimReportMessageEntityNew();
            localInterimReportMessageEntityNew18.setContent("您的体重没有变化，但体脂肪正在增长中。您应该控制热量摄入，增加运动啦。");
            this.loseWeightMessages.add(localInterimReportMessageEntityNew18);
            i8 = 1;
            localInteger = Integer.valueOf(2);
          }
          else
          {
            if (localBodyIndex6.getBodyFat() <= localBodyIndex5.getBodyFat())
              break label2594;
            InterimReportMessageEntityNew localInterimReportMessageEntityNew19 = new InterimReportMessageEntityNew();
            localInterimReportMessageEntityNew19.setContent("您的体重没有变化，体脂肪也正在减少中。您身材正在变得更好，而且代谢能力增强了，每天消耗更多热量，就不易复胖。");
            this.loseWeightMessages.add(localInterimReportMessageEntityNew19);
            i8 = 1;
            localInteger = Integer.valueOf(1);
          }
        }
        break;
      }
      label2600: if (f8 < 0.0F)
        if (f5 < 0.0F)
          if (f6 > 0.0F)
          {
            localInteger = Integer.valueOf(2);
            InterimReportMessageEntityNew localInterimReportMessageEntityNew16 = new InterimReportMessageEntityNew();
            localInterimReportMessageEntityNew16.setContent("在您减少的" + NumUtils.roundValue(-f8) + "kg体重中，肌肉量占" + NumUtils.roundValue(-f5) + "kg，脂肪反而上升了" + NumUtils.roundValue(f6) + "kg。这说明您的肌肉和水分正在流失，运动严重不足，请参照请运动方案和饮食与营养调整生活方式，健康增重。");
            this.loseWeightMessages.add(localInterimReportMessageEntityNew16);
            if (localInteger.intValue() != 1)
              break label4010;
          }
      label2708: label4002: label4004: label4010: 
      while (true)
      {
        if (localInteger.intValue() != 1)
          break label4013;
        str1 = "健康";
        this.loseWeightColor = Color.argb(153, 140, 255, 121);
        break;
        if (f6 < 0.0F)
        {
          localInteger = Integer.valueOf(2);
          InterimReportMessageEntityNew localInterimReportMessageEntityNew15 = new InterimReportMessageEntityNew();
          localInterimReportMessageEntityNew15.setContent("在您减少的" + NumUtils.roundValue(-f8) + "kg体重中，肌肉量占" + NumUtils.roundValue(-f5) + "kg。您的肌肉和水分正在流失，脂肪也有所下降。应减少有氧运动的比例，增加无氧运动，运动后注意补充营养。请参照运动方案和饮食与营养调整生活方式。");
          this.loseWeightMessages.add(localInterimReportMessageEntityNew15);
          break label2708;
        }
        localInteger = Integer.valueOf(2);
        InterimReportMessageEntityNew localInterimReportMessageEntityNew14 = new InterimReportMessageEntityNew();
        localInterimReportMessageEntityNew14.setContent("在您减少的" + NumUtils.roundValue(-f8) + "kg体重中，肌肉量占" + NumUtils.roundValue(-f5) + "kg。这说明您的肌肉和水分正在流失，身体消耗远高于摄入。请参照饮食与营养调整自己的食谱。");
        this.loseWeightMessages.add(localInterimReportMessageEntityNew14);
        break label2708;
        if (f5 < 0.0F)
          break label2708;
        localInteger = Integer.valueOf(1);
        if (f6 >= 0.0F);
        for (String str7 = ""; ; str7 = NumUtils.roundValue(-f6) + "kg")
        {
          InterimReportMessageEntityNew localInterimReportMessageEntityNew13 = new InterimReportMessageEntityNew();
          localInterimReportMessageEntityNew13.setContent("在您减少的" + NumUtils.roundValue(-f8) + "kg体重中，体脂肪下降了" + str7 + "，肌肉也有所增长。请不必担心暂时性的体重下降，身体正在适应新的代谢平衡。");
          this.loseWeightMessages.add(localInterimReportMessageEntityNew13);
          break;
        }
        if (f8 > 0.0F)
        {
          if (f5 >= 0.0F)
          {
            if (f6 > 0.0F)
            {
              localInteger = Integer.valueOf(1);
              if (f5 / f6 > 3.0F)
              {
                InterimReportMessageEntityNew localInterimReportMessageEntityNew10 = new InterimReportMessageEntityNew();
                localInterimReportMessageEntityNew10.setContent("在您增加的" + NumUtils.roundValue(f8) + "kg体重中，肌肉增加了" + NumUtils.roundValue(f5) + "kg。这说明您在最大限度生长肌肉的同时，增加了尽量少的脂肪，增重方式非常健康，请继续保持。");
                this.loseWeightMessages.add(localInterimReportMessageEntityNew10);
                break label2708;
              }
              if (f5 != 0.0F)
              {
                InterimReportMessageEntityNew localInterimReportMessageEntityNew11 = new InterimReportMessageEntityNew();
                localInterimReportMessageEntityNew11.setContent("在您增加的" + NumUtils.roundValue(f8) + "kg体重中，肌肉增加了" + NumUtils.roundValue(f5) + "kg，脂肪也增加了" + NumUtils.roundValue(f6) + "kg。这说明您在生长肌肉的同时，也增加了较多的脂肪，请参照请运动方案和饮食与营养调整生活方式，健康增重不增肥。");
                this.loseWeightMessages.add(localInterimReportMessageEntityNew11);
                break label2708;
              }
              InterimReportMessageEntityNew localInterimReportMessageEntityNew12 = new InterimReportMessageEntityNew();
              localInterimReportMessageEntityNew12.setContent("在您增加的" + NumUtils.roundValue(f8) + "kg体重中，肌肉没有变化，脂肪增加了" + NumUtils.roundValue(f6) + "kg。这说明您增加了较多的脂肪，请参照请运动方案和饮食与营养调整生活方式，健康增重不增肥。");
              this.loseWeightMessages.add(localInterimReportMessageEntityNew12);
              break label2708;
            }
            if (f6 < 0.0F)
            {
              localInteger = Integer.valueOf(1);
              InterimReportMessageEntityNew localInterimReportMessageEntityNew9 = new InterimReportMessageEntityNew();
              localInterimReportMessageEntityNew9.setContent("在您增加的" + NumUtils.roundValue(f8) + "kg体重中，肌肉增加了" + NumUtils.roundValue(f5) + "kg，脂肪下降了" + NumUtils.roundValue(-f6) + "kg。您的增重方式非常健康，请继续保持。");
              this.loseWeightMessages.add(localInterimReportMessageEntityNew9);
              break label2708;
            }
            localInteger = Integer.valueOf(1);
            InterimReportMessageEntityNew localInterimReportMessageEntityNew8 = new InterimReportMessageEntityNew();
            localInterimReportMessageEntityNew8.setContent("在您增加的" + NumUtils.roundValue(f8) + "kg体重中，肌肉增加了" + NumUtils.roundValue(f5) + "kg。您的增重方式非常健康，请继续保持。");
            this.loseWeightMessages.add(localInterimReportMessageEntityNew8);
            break label2708;
          }
          localInteger = Integer.valueOf(2);
          if (f6 <= 0.0F);
          for (String str6 = ""; ; str6 = NumUtils.roundValue(f6) + "kg之多")
          {
            InterimReportMessageEntityNew localInterimReportMessageEntityNew7 = new InterimReportMessageEntityNew();
            localInterimReportMessageEntityNew7.setContent("在您增加的" + NumUtils.roundValue(f8) + "kg体重中，体脂肪上升了" + str6 + "，并伴有水分和肌肉的损失。这说明您增加的主要是肥肉，肌肉和水分正在流失，运动严重不足，请参照请运动方案和饮食与营养调整生活方式，健康增重不增肥。");
            this.loseWeightMessages.add(localInterimReportMessageEntityNew7);
            break;
          }
        }
        if (f5 < 0.0F)
        {
          localInteger = Integer.valueOf(2);
          InterimReportMessageEntityNew localInterimReportMessageEntityNew6 = new InterimReportMessageEntityNew();
          localInterimReportMessageEntityNew6.setContent("您的体重虽然没有变化，但肌肉量减少了" + NumUtils.roundValue(-f5) + "kg。这说明您的肌肉和水分正在流失，运动严重不足，请参照请运动方案和饮食与营养调整生活方式，健康增重。");
          this.loseWeightMessages.add(localInterimReportMessageEntityNew6);
          break label2708;
        }
        if (f5 > 0.0F)
        {
          localInteger = Integer.valueOf(1);
          if (f6 >= 0.0F);
          for (String str5 = ""; ; str5 = NumUtils.roundValue(-f6) + "kg")
          {
            InterimReportMessageEntityNew localInterimReportMessageEntityNew5 = new InterimReportMessageEntityNew();
            localInterimReportMessageEntityNew5.setContent("您的体重没有变化，但肌肉增加了" + NumUtils.roundValue(f5) + "kg，脂肪也下降了" + str5 + "。您的增重方式非常健康，请继续保持。");
            this.loseWeightMessages.add(localInterimReportMessageEntityNew5);
            break;
          }
        }
        ArrayList localArrayList3 = OperationDB.selectBodyIndexBetweenStartTimeAndEndTimeAndRoleID(paramContext, this.cStartTime, this.lastTime, localMyApplication.getCurrentRole().getRole_id());
        if (localArrayList3.size() <= 0)
          return null;
        BodyIndex localBodyIndex3 = (BodyIndex)localArrayList3.get(-1 + localArrayList3.size());
        for (int i5 = -2 + localArrayList3.size(); ; i5--)
        {
          int i6 = 0;
          if (i5 <= 0);
          while (true)
          {
            if (i6 == 0)
              break label4002;
            InterimReportMessageEntityNew localInterimReportMessageEntityNew3 = new InterimReportMessageEntityNew();
            localInterimReportMessageEntityNew3.setContent("您的体重没有变化，身体成分也没有发生任何变化。请参照运动方案和饮食与营养，开始健康增重吧~");
            this.loseWeightMessages.add(localInterimReportMessageEntityNew3);
            localInteger = Integer.valueOf(1);
            break;
            BodyIndex localBodyIndex4 = (BodyIndex)localArrayList3.get(i5);
            if (localBodyIndex4.getMusde_race() == localBodyIndex3.getMusde_race())
              break label4004;
            if (localBodyIndex4.getMusde_race() < localBodyIndex3.getMusde_race())
            {
              InterimReportMessageEntityNew localInterimReportMessageEntityNew2 = new InterimReportMessageEntityNew();
              localInterimReportMessageEntityNew2.setContent("您的体重没有变化，但肌肉正在增长中。您的增重方式非常健康，请继续保持。");
              this.loseWeightMessages.add(localInterimReportMessageEntityNew2);
              i6 = 1;
              localInteger = Integer.valueOf(1);
            }
            else
            {
              if (localBodyIndex4.getMusde_race() <= localBodyIndex3.getMusde_race())
                break label4004;
              InterimReportMessageEntityNew localInterimReportMessageEntityNew4 = new InterimReportMessageEntityNew();
              localInterimReportMessageEntityNew4.setContent("您的体重没有变化，但肌肉正在减少中。您的运动严重不足，请参照运动方案开始运动吧。");
              this.loseWeightMessages.add(localInterimReportMessageEntityNew4);
              i6 = 1;
              localInteger = Integer.valueOf(2);
            }
          }
          break;
        }
      }
      label4013: str1 = "不健康";
      this.loseWeightColor = Color.argb(153, 255, 110, 110);
    }
  }

  private void dealWithAllWeights(Context paramContext)
  {
    this.currentWegiht = ((MyApplication)paramContext.getApplicationContext()).getTodayBody().getWeight();
    this.totalWeight = this.cRole.getGoal_weight();
    this.startDate = DateUtils.changeTimeStampToFormatTime(this.cStartTime, "MM月dd日");
    this.maxWeight = 0.0F;
    this.minWeight = 2000.0F;
    int i = 0;
    int j = 0;
    int k = -1 + this.weightArray.size();
    float f2;
    if (k < 0)
    {
      this.maxWeightCount = i;
      this.minWeightCount = j;
      this.firstWeight = ((Float)this.weightArray.get(0)).floatValue();
      this.firstWeightIndex = 0;
      this.lastWeight = ((Float)this.weightArray.get(-1 + this.weightArray.size())).floatValue();
      this.lastWeightIndex = (-1 + this.weightArray.size());
      f2 = this.firstWeight - this.lastWeight;
      this.diffWeight = Math.abs(f2);
      if (f2 <= 0.0F)
        break label257;
      this.diffWeightStatusCode = 1;
    }
    while (true)
    {
      this.lastTime = this.cLastWeightingTime;
      return;
      float f1 = ((Float)this.weightArray.get(k)).floatValue();
      if (f1 >= this.maxWeight)
      {
        this.maxWeight = f1;
        i = k;
      }
      if ((f1 < this.minWeight) && (f1 != -1.0F))
      {
        this.minWeight = f1;
        j = k;
      }
      k--;
      break;
      label257: if (f2 < 0.0F)
        this.diffWeightStatusCode = 3;
      else
        this.diffWeightStatusCode = 2;
    }
  }

  private ArrayList<BodyIndex> filterBodyIndex(ArrayList<BodyIndex> paramArrayList, long paramLong1, long paramLong2)
  {
    long l1 = DateUtils.getDayStartTimeAndEndTimeByTimestamp(paramLong1)[0];
    long l2 = DateUtils.getDayStartTimeAndEndTimeByTimestamp(paramLong2)[1];
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayList.size())
        return localArrayList;
      BodyIndex localBodyIndex = (BodyIndex)paramArrayList.get(i);
      double d = localBodyIndex.getTime();
      if ((d >= l1) && (d <= l2))
        localArrayList.add(localBodyIndex);
    }
  }

  private ArrayList<Float> getAveWeightForSevenDaysAgoWithWeekDates(ArrayList<BodyIndex> paramArrayList, long paramLong)
  {
    ArrayList localArrayList = new ArrayList();
    long l1 = ((BodyIndex)paramArrayList.get(0)).getTime();
    long l2 = 1L + DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(((BodyIndex)paramArrayList.get(-1 + paramArrayList.size())).getTime(), l1);
    for (int i = 0; ; i++)
    {
      if (i >= l2)
      {
        if ((paramArrayList != null) && (paramArrayList.size() != 0))
          break;
        return localArrayList;
      }
      localArrayList.add(Float.valueOf(-1.0F));
    }
    long l3 = (((BodyIndex)paramArrayList.get(0)).getTime() - paramLong) / 86400000L;
    long l4 = (System.currentTimeMillis() - paramLong) / 86400000L;
    float f1 = 0.0F;
    float f2 = 0.0F;
    int j = 0;
    label130: BodyIndex localBodyIndex;
    long l5;
    if (j < paramArrayList.size())
    {
      localBodyIndex = (BodyIndex)paramArrayList.get(j);
      l5 = (localBodyIndex.getTime() - paramLong) / 86400000L;
      if (l3 == l5)
        break label254;
      float f4 = f1 / f2;
      localArrayList.set((int)l3, Float.valueOf(f4));
      f1 = localBodyIndex.getWeight();
      f2 = 1.0F;
      label202: if (j == -1 + paramArrayList.size())
      {
        if (l4 != l5)
          break label273;
        localBodyIndex.getWeight();
        localArrayList.set((int)l5, Float.valueOf(localBodyIndex.getWeight()));
      }
    }
    while (true)
    {
      l3 = l5;
      j++;
      break label130;
      break;
      label254: f1 += localBodyIndex.getWeight();
      f2 += 1.0F;
      break label202;
      label273: float f3 = f1 / f2;
      localArrayList.set((int)l5, Float.valueOf(f3));
    }
  }

  private BodyIndex getFirstWeightingDataByAllData(ArrayList<BodyIndex> paramArrayList, long paramLong)
  {
    for (int i = 0; ; i++)
    {
      BodyIndex localBodyIndex;
      if (i >= paramArrayList.size())
        localBodyIndex = null;
      do
      {
        return localBodyIndex;
        localBodyIndex = (BodyIndex)paramArrayList.get(i);
      }
      while (localBodyIndex.getTime() >= paramLong);
    }
  }

  private boolean initPeriod(Context paramContext)
  {
    MyApplication localMyApplication = (MyApplication)paramContext.getApplicationContext();
    this.allRoleInfos = OperationDB.queryAllRoleInfosByRoleIDBeforeTimeStamp(paramContext, this.cRole.getRole_id(), DateUtils.getDayStartTimeAndEndTimeByFlag(0)[1]);
    ArrayList localArrayList;
    if (this.allRoleInfos.size() > 0)
    {
      RoleBin localRoleBin = (RoleBin)this.allRoleInfos.get(-1 + this.allRoleInfos.size());
      long l3 = DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(localMyApplication.getCurrentRole().getChange_goal_weight_time(), System.currentTimeMillis());
      if ((localRoleBin.getChange_goal_weight_time() < localMyApplication.getCurrentRole().getChange_goal_weight_time()) && (l3 <= 0L))
        this.allRoleInfos.add(localMyApplication.getCurrentRole());
      localArrayList = new ArrayList();
      localArrayList.add(Long.valueOf(1L));
    }
    for (int i = 0; ; i++)
    {
      int j = this.allRoleInfos.size();
      if (i >= j)
      {
        localArrayList.add(Long.valueOf(DateUtils.getDayStartTimeAndEndTimeByFlag(0)[1]));
        if (localArrayList.size() >= 2)
          break label219;
        return false;
        this.allRoleInfos.add(localMyApplication.getCurrentRole());
        break;
      }
      localArrayList.add(Long.valueOf(((RoleBin)this.allRoleInfos.get(i)).getChange_goal_weight_time()));
    }
    label219: long l1;
    long l2;
    for (int k = -1 + localArrayList.size(); ; k--)
    {
      if (k <= 0)
        return false;
      l1 = ((Long)localArrayList.get(k)).longValue();
      l2 = ((Long)localArrayList.get(k - 1)).longValue();
      if (DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(l1, l2) > 0L)
        break;
    }
    if (k == 1);
    for (int m = 0; ; m = k - 2)
    {
      this.cRole = ((RoleBin)this.allRoleInfos.get(m));
      if (!caculateStageByStartTimeAndEndTimeAndRole(paramContext, l2, l1, this.cRole))
        break;
      return true;
    }
  }

  private void initSectionHeaderText1()
  {
    if (DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), this.cLastWeightingTime) == 0L)
    {
      this.sectionHeaderText1 = ("今日体重 " + NumUtils.roundValue(this.lastWeight) + "kg");
      this.endData = DateUtils.changeTimeStampToFormatTime(System.currentTimeMillis(), "MM月dd日");
      return;
    }
    this.endData = DateUtils.changeTimeStampToFormatTime(this.cLastWeightingTime, "MM月dd日");
    this.sectionHeaderText1 = (this.endData + "体重 " + NumUtils.roundValue(this.lastWeight) + "kg");
  }

  private void initSectionHeaderText2()
  {
    float f = this.cRole.getWeight_change_target();
    if (f > 0.0F)
    {
      this.weightChangeType = 3;
      this.sectionHeaderText2 = "增重方式";
      return;
    }
    if (f == 0.0F)
    {
      this.weightChangeType = 2;
      this.sectionHeaderText2 = "减重方式";
      return;
    }
    this.weightChangeType = 1;
    this.sectionHeaderText2 = "减重方式";
  }

  private String initTableHeaderViewTitle()
  {
    long l1 = DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), this.cEndTime);
    if (l1 >= 0L)
    {
      if (this.lastInterimWeightsCount > 0)
      {
        if (l1 == 0L)
        {
          String str8 = "上个阶段自" + this.startDate + "起，目前已结束。新的阶段已从今天开始，明天称重后来看看吧~";
          this.outCycleUpperStr = "--";
          this.outCycleDownStr = "新阶段开始了！";
          return str8;
        }
        String str6 = DateUtils.changeTimeStampToFormatTime(DateUtils.getDayStartTimeAndEndTimeByFlag((int)l1)[0], "MM月dd日");
        String str7 = "上个阶段自" + this.startDate + "起，为期" + this.cWeekNos + "周，目前已结束，新的阶段已从" + str6 + "开始，请立刻去称重，查看新阶段的进展吧~";
        this.outCycleUpperStr = "--";
        this.outCycleDownStr = "新阶段开始了！";
        return str7;
      }
      String str5 = "本阶段自" + this.startDate + "起，为期" + this.cWeekNos + "周，目前已结束。请立刻去称重，开始新的阶段吧~";
      this.outCycleUpperStr = "--";
      this.outCycleDownStr = "本阶段已经结束啦！";
      return str5;
    }
    float f = this.cRole.getWeight_change_target();
    if ((f > 0.0F) && (this.lastWeight >= this.cRole.getGoal_weight()))
    {
      String str4 = "本阶段自" + this.startDate + "起，为期" + this.cWeekNos + "周。目前您已达到目标，请去设定新的目标，开始新的阶段吧~";
      this.outCycleUpperStr = (1L + DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), this.cStartTime) + "天");
      this.outCycleWeightChange = (this.lastWeight - this.firstWeight);
      return str4;
    }
    if ((f <= 0.0F) && (this.lastWeight <= this.cRole.getGoal_weight()))
    {
      String str3 = "本阶段自" + this.startDate + "起，为期" + this.cWeekNos + "周。目前您已达到目标，请去设定新的目标，开始新的阶段吧~";
      this.outCycleUpperStr = (1L + DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), this.cStartTime) + "天");
      this.outCycleWeightChange = (this.lastWeight - this.firstWeight);
      return str3;
    }
    if (DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), this.cLastWeightingTime) == 0L)
    {
      long l3 = 1L + DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(this.cLastWeightingTime, this.cStartTime);
      String str2 = "本阶段自" + this.startDate + "起，为期" + this.cWeekNos + "周，今天是第" + l3 + "天";
      this.outCycleUpperStr = (l3 + "天");
      this.outCycleWeightChange = (this.lastWeight - this.firstWeight);
      return str2;
    }
    long l2 = 1L + DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), this.cStartTime);
    String str1 = "本阶段自" + this.startDate + "起，为期" + this.cWeekNos + "周，今天是第" + l2 + "天。请立即去称重，查看体重变化的趋势吧~";
    this.outCycleUpperStr = (l2 + "天");
    this.outCycleWeightChange = (this.lastWeight - this.firstWeight);
    return str1;
  }

  private void initTodayWeightMessages()
  {
    long l = 1L + DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(this.cLastWeightingTime, this.cStartTime);
    String str1 = "";
    if (this.diffWeightStatusCode == 1)
      str1 = "下降" + NumUtils.roundValue(Math.abs(this.diffWeight)) + "kg";
    if (this.diffWeightStatusCode == 3)
      str1 = "上升" + NumUtils.roundValue(Math.abs(this.diffWeight)) + "kg";
    if (this.diffWeightStatusCode == 2)
      str1 = "保持不变";
    String str2;
    if (this.weightChangeType == 1)
      if (this.lastWeight - this.totalWeight <= 0.0F)
        str2 = "已达成目标！";
    while (true)
    {
      this.todayWeightMessages = new ArrayList();
      this.todayWeightMessages.add("初始体重为" + NumUtils.roundValue(this.firstWeight) + "kg。" + l + "天内，体重" + str1 + "，" + str2);
      return;
      str2 = "距目标还有" + NumUtils.roundValue(Math.abs(this.lastWeight - this.totalWeight)) + "kg。";
      continue;
      if (this.weightChangeType == 2)
      {
        if (this.lastWeight - this.totalWeight <= 0.0F)
          str2 = "已达成目标！";
        else
          str2 = "距目标还有" + NumUtils.roundValue(Math.abs(this.lastWeight - this.totalWeight)) + "kg。";
      }
      else if (this.lastWeight - this.totalWeight >= 0.0F)
        str2 = "已达成目标！";
      else
        str2 = "距目标还有" + NumUtils.roundValue(Math.abs(this.totalWeight - this.lastWeight)) + "kg。";
    }
  }

  private boolean isChangeGoalWeightAfterTimeStamp(long paramLong)
  {
    long l = DateUtils.getDayStartTimeAndEndTimeByTimestamp(paramLong)[0];
    for (int i = -1 + this.allRoleInfos.size(); ; i--)
    {
      if (i < 0)
        return false;
      if (((RoleBin)this.allRoleInfos.get(i)).getTime() >= l)
        return true;
    }
  }

  public String getEndDate()
  {
    return this.endData;
  }

  public float getFirstWeight()
  {
    return this.firstWeight;
  }

  public float getLastWeight()
  {
    return this.lastWeight;
  }

  public ArrayList<InterimReportMessageEntityNew> getLoseWeightMessages()
  {
    return this.loseWeightMessages;
  }

  public String getLoseWeightPattern()
  {
    return this.loseWeightPattern;
  }

  public float getMaxWeight()
  {
    return this.maxWeight;
  }

  public int getMaxWeightIndex()
  {
    return this.maxWeightCount;
  }

  public float getMinWeight()
  {
    return this.minWeight;
  }

  public int getMinWeightIndex()
  {
    return this.minWeightCount;
  }

  public String getOutCycleDownStr()
  {
    return this.outCycleDownStr;
  }

  public String getOutCycleUpperStr()
  {
    return this.outCycleUpperStr;
  }

  public float getOutCycleWeightChange()
  {
    return this.outCycleWeightChange;
  }

  public RoleBin getRole()
  {
    return this.cRole;
  }

  public String getSectionHeaderText1()
  {
    return this.sectionHeaderText1;
  }

  public String getSectionHeaderText2()
  {
    return this.sectionHeaderText2;
  }

  public String getStartData()
  {
    return this.startDate;
  }

  public String getTableHeaderViewTitle()
  {
    return this.tableHeaderViewTitle;
  }

  public String getTodayWeightMessage()
  {
    return (String)this.todayWeightMessages.get(0);
  }

  public ArrayList<Float> getWeightArray()
  {
    return this.weightArray;
  }

  public boolean isCanComeIn()
  {
    return this.canComeIn;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     InterimReportModel
 * JD-Core Version:    0.6.2
 */
