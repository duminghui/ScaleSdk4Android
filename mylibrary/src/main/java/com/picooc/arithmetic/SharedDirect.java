package com.picooc.arithmetic;

import android.content.Context;
import com.picooc.db.OperationDB;
import com.picooc.domain.BodyFatReport;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.ReportEntity;
import com.picooc.domain.RoleBin;
import com.picooc.domain.SharedEntity;
import com.picooc.emun.SharedEntityTypeEmun;
import com.picooc.utils.NumUtils;

public class SharedDirect
{
  public SharedDirect(RoleBin paramRoleBin, BodyIndex paramBodyIndex)
  {
  }

  public static SharedEntity bmr(Context paramContext, RoleBin paramRoleBin, BodyIndex paramBodyIndex)
  {
    float f1 = ReportDirect.judgeBmrByRole(paramRoleBin, paramBodyIndex).getNum2();
    int i = NumUtils.roundFloatToInt(paramBodyIndex.getBasic_metabolism());
    float f2 = ReportDirect.calculateIdealWeight(paramRoleBin);
    if (i == 0)
      return null;
    SharedEntity localSharedEntity;
    int k;
    if (paramRoleBin.getSex() == 1)
    {
      boolean bool2 = i < 1.1D * f1;
      localSharedEntity = null;
      if (!bool2)
      {
        boolean bool3 = paramBodyIndex.getWeight() < 0.92D * f2;
        localSharedEntity = null;
        if (bool3)
        {
          localSharedEntity = new SharedEntity();
          localSharedEntity.setType(SharedEntityTypeEmun.SharedEntityBasalMetabolicRate);
          localSharedEntity.setImageName("shareEntityImageName_man_bmr.png");
          if ((1.1D * f1 > i) || (i >= 1.13D * f1))
            break label214;
          localSharedEntity.setLevel(1);
          localSharedEntity.setName("哈雷重机");
          localSharedEntity.setPersent(getPersentWithCurrentData(i, f1, SharedEntityTypeEmun.SharedEntityBasalMetabolicRate, paramRoleBin, paramBodyIndex));
          k = OperationDB.queryShareAchievementLevelWithLastTimeByRoleId(paramContext, paramRoleBin, SharedEntityTypeEmun.SharedEntityBasalMetabolicRate, System.currentTimeMillis());
          if (k >= 0)
            break label327;
          localSharedEntity.setMessage("您当前的基础代谢水平高于全国" + localSharedEntity.getPersent() + "%的同龄汉子。");
        }
      }
    }
    label675: 
    while (true)
    {
      return localSharedEntity;
      label214: if ((1.13D * f1 <= i) && (i < 1.16D * f1))
      {
        localSharedEntity.setLevel(2);
        localSharedEntity.setName("悍马王子");
        break;
      }
      if ((1.16D * f1 <= i) && (i < 1.23D * f1))
      {
        localSharedEntity.setLevel(3);
        localSharedEntity.setName("装甲坦克");
        break;
      }
      if (1.23D * f1 >= i)
        break;
      localSharedEntity.setLevel(4);
      localSharedEntity.setName("超马力金刚");
      break;
      label327: if (k < localSharedEntity.getLevel())
      {
        localSharedEntity.setMessage("基础代谢提升！马力已甩开全国" + localSharedEntity.getPersent() + "%的同龄汉子。");
      }
      else if (k > localSharedEntity.getLevel())
      {
        localSharedEntity.setMessage("代谢水平略有下降哦～但仍高于全国" + localSharedEntity.getPersent() + "%的同龄汉子！");
        continue;
        boolean bool1 = i < f1;
        localSharedEntity = null;
        if (bool1)
        {
          localSharedEntity = new SharedEntity();
          localSharedEntity.setType(SharedEntityTypeEmun.SharedEntityBasalMetabolicRate);
          localSharedEntity.setName("小绵羊");
          localSharedEntity.setImageName("shareEntityImageName_woman_bmr.png");
          if (0.88D * f1 > i)
          {
            localSharedEntity.setLevel(1);
            localSharedEntity.setName("奶嘴小绵羊");
          }
          int j;
          while (true)
          {
            localSharedEntity.setPersent(getPersentWithCurrentData(i, f1, SharedEntityTypeEmun.SharedEntityBasalMetabolicRate, paramRoleBin, paramBodyIndex));
            j = OperationDB.queryShareAchievementLevelWithLastTimeByRoleId(paramContext, paramRoleBin, SharedEntityTypeEmun.SharedEntityBasalMetabolicRate, System.currentTimeMillis());
            if (j >= 0)
              break label675;
            localSharedEntity.setMessage("您当前的基础代谢水平高低于全国" + localSharedEntity.getPersent() + "%的同龄mm。");
            break;
            if ((0.88D * f1 <= i) && (i < 0.9300000000000001D * f1))
            {
              localSharedEntity.setLevel(2);
              localSharedEntity.setName("犄角小绵羊");
            }
            else if ((0.9300000000000001D * f1 <= i) && (i < 0.97D * f1))
            {
              localSharedEntity.setLevel(3);
              localSharedEntity.setName("团绒绒大绵羊");
            }
            else if ((0.97D * f1 <= i) && (i <= f1))
            {
              localSharedEntity.setLevel(4);
              localSharedEntity.setName("绵羊公主");
            }
          }
          if (j < localSharedEntity.getLevel())
            localSharedEntity.setMessage("代谢水平有提升！但仍低于全国" + localSharedEntity.getPersent() + "%的同龄mm！");
          else if (j > localSharedEntity.getLevel())
            localSharedEntity.setMessage("代谢水平又下降啦……全国" + localSharedEntity.getPersent() + "%的同龄mm都比您燃烧热量更快。");
        }
      }
    }
  }

  public static SharedEntity bodyfat(Context paramContext, RoleBin paramRoleBin, BodyIndex paramBodyIndex)
  {
    BodyFatReport localBodyFatReport = ReportDirect.calculateIdealBodyFat(paramRoleBin, paramBodyIndex, false);
    float f1 = localBodyFatReport.getGoalFatRace();
    int i = localBodyFatReport.getRealFatState();
    float[] arrayOfFloat = localBodyFatReport.getRaceArray();
    float f2 = paramBodyIndex.getBodyFat();
    float f3 = ReportDirect.calculateIdealWeight(paramRoleBin);
    float f4 = arrayOfFloat[3];
    float f5 = arrayOfFloat[1];
    SharedEntity localSharedEntity;
    int j;
    if (paramRoleBin.getSex() == 1)
    {
      localSharedEntity = null;
      if (i >= 4)
      {
        boolean bool1 = paramBodyIndex.getWeight() < 1.1D * f3;
        localSharedEntity = null;
        if (bool1)
        {
          localSharedEntity = new SharedEntity();
          localSharedEntity.setType(SharedEntityTypeEmun.SharedEntityFatRatioMan);
          localSharedEntity.setImageName("sharedEntityImageName_man_fat.png");
          if (9.0F + f4 > f2)
            break label206;
          localSharedEntity.setLevel(1);
          localSharedEntity.setName("巨无霸汉堡王");
          localSharedEntity.setPersent(getPersentWithCurrentData(f2, f1, SharedEntityTypeEmun.SharedEntityFatRatioMan, paramRoleBin, paramBodyIndex));
          j = OperationDB.queryShareAchievementLevelWithLastTimeByRoleId(paramContext, paramRoleBin, SharedEntityTypeEmun.SharedEntityFatRatioMan, System.currentTimeMillis());
          if (j >= 0)
            break label317;
          localSharedEntity.setMessage("您当前比全国" + localSharedEntity.getPersent() + "%的同龄汉子更需要一台跑步机。");
        }
      }
    }
    label206: label493: int k;
    label317: label708: label1017: 
    do
    {
      do
      {
        return localSharedEntity;
        if ((6.0F + f4 <= f2) && (f2 < 9.0F + f4))
        {
          localSharedEntity.setLevel(2);
          localSharedEntity.setName("双层汉堡王");
          break;
        }
        if ((3.0F + f4 <= f2) && (f2 < 6.0F + f4))
        {
          localSharedEntity.setLevel(3);
          localSharedEntity.setName("鸡腿汉堡王");
          break;
        }
        if ((f4 > f2) || (f2 >= 3.0F + f4))
          break;
        localSharedEntity.setLevel(4);
        localSharedEntity.setName("吉士汉堡王");
        break;
        if (j < localSharedEntity.getLevel())
        {
          localSharedEntity.setMessage("有进步！但仍比全国" + localSharedEntity.getPersent() + "%的同龄汉子更急需甩脱肚腩哦！");
          return localSharedEntity;
        }
      }
      while (j <= localSharedEntity.getLevel());
      localSharedEntity.setMessage("肥肉见长……比全国" + localSharedEntity.getPersent() + "%的同龄汉子更需要少吃多动。");
      return localSharedEntity;
      localSharedEntity = null;
      int m;
      if (i <= 2)
      {
        boolean bool2 = f2 < 0.0F;
        localSharedEntity = null;
        if (bool2)
        {
          localSharedEntity = new SharedEntity();
          localSharedEntity.setType(SharedEntityTypeEmun.SharedEntityFatRatioWomanLow);
          localSharedEntity.setName("万人迷");
          localSharedEntity.setImageName("sharedEntityImageName_woman_fat_low.png");
          if ((2.0F + f5 >= f2) || (f2 >= 5.0F + f5))
            break label708;
          localSharedEntity.setLevel(1);
          localSharedEntity.setName("万人迷");
          localSharedEntity.setPersent(getPersentWithCurrentData(f2, f1, SharedEntityTypeEmun.SharedEntityFatRatioWomanLow, paramRoleBin, paramBodyIndex));
          m = OperationDB.queryShareAchievementLevelWithLastTimeByRoleId(paramContext, paramRoleBin, SharedEntityTypeEmun.SharedEntityFatRatioWomanLow, System.currentTimeMillis());
          if (m >= 0)
            break label813;
          localSharedEntity.setMessage("您当前比全国" + localSharedEntity.getPersent() + "%的同龄mm线条更好，回头率更高。");
        }
      }
      if ((f2 >= 2.0F + arrayOfFloat[3]) && (1.1D * f3 < paramBodyIndex.getWeight()))
      {
        localSharedEntity = new SharedEntity();
        localSharedEntity.setType(SharedEntityTypeEmun.SharedEntityFatRatioWomanHeight);
        localSharedEntity.setImageName("sharedEntityImageName_woman_fat_height.png");
        if (9.0F + f4 > f2)
          break label903;
        localSharedEntity.setLevel(1);
        localSharedEntity.setName("奶油大蛋糕");
      }
      while (true)
      {
        localSharedEntity.setPersent(getPersentWithCurrentData(f2, f1, SharedEntityTypeEmun.SharedEntityFatRatioWomanHeight, paramRoleBin, paramBodyIndex));
        k = OperationDB.queryShareAchievementLevelWithLastTimeByRoleId(paramContext, paramRoleBin, SharedEntityTypeEmun.SharedEntityFatRatioWomanHeight, System.currentTimeMillis());
        if (k >= 0)
          break label1017;
        localSharedEntity.setMessage("您当前比全国" + localSharedEntity.getPersent() + "%的mm更需要一场和肚肚的告别。");
        return localSharedEntity;
        if ((f5 - 1.0F < f2) && (f2 <= 2.0F + f5))
        {
          localSharedEntity.setLevel(2);
          localSharedEntity.setName("百万挑一");
          break label493;
        }
        if ((f5 - 4.0F < f2) && (f2 <= f5 - 1.0F))
        {
          localSharedEntity.setLevel(3);
          localSharedEntity.setName("性感魔鬼");
          break label493;
        }
        if (f2 > f5 - 4.0F)
          break label493;
        localSharedEntity.setLevel(4);
        localSharedEntity.setName("惊为天人");
        break label493;
        if (m < localSharedEntity.getLevel())
        {
          localSharedEntity.setMessage("回头率居高不下！比全国" + localSharedEntity.getPersent() + "%的mm线条更好呢！");
          break label560;
        }
        if (m <= localSharedEntity.getLevel())
          break label560;
        localSharedEntity.setMessage("回头率居高不下！比全国" + localSharedEntity.getPersent() + "%mm线条更好呢！");
        break label560;
        break;
        if ((6.0F + f4 <= f2) && (f2 < 9.0F + f4))
        {
          localSharedEntity.setLevel(2);
          localSharedEntity.setName("甜甜圈");
        }
        else if ((3.0F + f4 <= f2) && (f2 < 6.0F + f4))
        {
          localSharedEntity.setLevel(3);
          localSharedEntity.setName("肉包包");
        }
        else if ((f4 <= f2) && (f2 < 3.0F + f4))
        {
          localSharedEntity.setLevel(4);
          localSharedEntity.setName("淡奶馒头");
        }
      }
      if (k < localSharedEntity.getLevel())
      {
        localSharedEntity.setMessage("有进步！但仍比全国" + localSharedEntity.getPersent() + "%的同龄mm更急需甩脱小肚肚哦！");
        return localSharedEntity;
      }
    }
    while (k <= localSharedEntity.getLevel());
    label560: localSharedEntity.setMessage("春日不减肥夏日徒伤悲……您比全国" + localSharedEntity.getPersent() + "%同龄mm更需要少吃多动。");
    label813: return localSharedEntity;
  }

  public static SharedEntity boneMass(Context paramContext, RoleBin paramRoleBin, BodyIndex paramBodyIndex)
  {
    float f1 = paramBodyIndex.getBone_mass();
    ReportEntity localReportEntity = ReportDirect.judgeBoneMassByRole(paramRoleBin, paramBodyIndex);
    float f2 = 100.0F * localReportEntity.getRegionArray()[1];
    int i = NumUtils.roundFloatToInt(100.0F * paramBodyIndex.getBone_mass());
    SharedEntity localSharedEntity;
    int n;
    if (paramRoleBin.getSex() == 1)
    {
      int m = localReportEntity.getStateCode();
      localSharedEntity = null;
      if (m == 3)
      {
        localSharedEntity = new SharedEntity();
        localSharedEntity.setType(SharedEntityTypeEmun.SharedEntityBoneMass);
        localSharedEntity.setImageName("shareEntityImageName_man_bone.png");
        if ((10.0F + f2 > i) || (i >= f2 + 20.0F))
          break label195;
        localSharedEntity.setLevel(1);
        localSharedEntity.setName("牛犊骨力多");
        localSharedEntity.setPersent(getPersentWithCurrentData(f1, 0.0F, SharedEntityTypeEmun.SharedEntityBoneMass, paramRoleBin, paramBodyIndex));
        n = OperationDB.queryShareAchievementLevelWithLastTimeByRoleId(paramContext, paramRoleBin, SharedEntityTypeEmun.SharedEntityBoneMass, System.currentTimeMillis());
        if (n >= 0)
          break label311;
        localSharedEntity.setMessage("您当前比全国" + localSharedEntity.getPersent() + "%的同龄汉子骨骼更强壮。");
      }
    }
    label195: int k;
    label311: 
    do
    {
      int j;
      do
      {
        do
        {
          return localSharedEntity;
          if ((f2 + 20.0F <= i) && (i < f2 + 30.0F))
          {
            localSharedEntity.setLevel(2);
            localSharedEntity.setName("牛角骨力多");
            break;
          }
          if ((f2 + 30.0F <= i) && (i < f2 + 40.0F))
          {
            localSharedEntity.setLevel(3);
            localSharedEntity.setName("牛壮壮骨力多");
            break;
          }
          if (f2 + 40.0F > i)
            break;
          localSharedEntity.setLevel(4);
          localSharedEntity.setName("牦牛骨力多");
          break;
          if (n < localSharedEntity.getLevel())
          {
            localSharedEntity.setMessage("骨骼强壮中，已超越了全国" + localSharedEntity.getPersent() + "%的同龄汉子！");
            return localSharedEntity;
          }
        }
        while (n <= localSharedEntity.getLevel());
        localSharedEntity.setMessage("骨钙量缩水，但仍比全国" + localSharedEntity.getPersent() + "%的同龄汉子骨骼强壮！");
        return localSharedEntity;
        j = localReportEntity.getStateCode();
        localSharedEntity = null;
      }
      while (j != 3);
      localSharedEntity = new SharedEntity();
      localSharedEntity.setType(SharedEntityTypeEmun.SharedEntityBoneMass);
      localSharedEntity.setName("骨力多");
      localSharedEntity.setImageName("shareEntityImageName_woman_bone.png");
      if ((10.0F + f2 <= i) && (i < f2 + 20.0F))
      {
        localSharedEntity.setLevel(1);
        localSharedEntity.setName("牛犊骨力多");
      }
      while (true)
      {
        localSharedEntity.setPersent(getPersentWithCurrentData(f1, 0.0F, SharedEntityTypeEmun.SharedEntityBoneMass, paramRoleBin, paramBodyIndex));
        k = OperationDB.queryShareAchievementLevelWithLastTimeByRoleId(paramContext, paramRoleBin, SharedEntityTypeEmun.SharedEntityBoneMass, System.currentTimeMillis());
        if (k >= 0)
          break;
        localSharedEntity.setMessage("您当前比全国" + localSharedEntity.getPersent() + "%的同龄mm骨骼更强壮。");
        return localSharedEntity;
        if ((f2 + 20.0F <= i) && (i < f2 + 30.0F))
        {
          localSharedEntity.setLevel(2);
          localSharedEntity.setName("牛角骨力多");
        }
        else if ((f2 + 30.0F <= i) && (i < f2 + 40.0F))
        {
          localSharedEntity.setLevel(3);
          localSharedEntity.setName("牛壮壮骨力多");
        }
        else if (f2 + 40.0F <= i)
        {
          localSharedEntity.setLevel(4);
          localSharedEntity.setName("牦牛骨力多");
        }
      }
      if (k < localSharedEntity.getLevel())
      {
        localSharedEntity.setMessage("骨骼强壮中，已超越了全国" + localSharedEntity.getPersent() + "%的同龄mm！");
        return localSharedEntity;
      }
    }
    while (k <= localSharedEntity.getLevel());
    localSharedEntity.setMessage("骨钙量缩水，但仍比全国" + localSharedEntity.getPersent() + "%的同龄mm骨骼强壮！");
    return localSharedEntity;
  }

  private static float getPersentWithCurrentData(float paramFloat1, float paramFloat2, SharedEntityTypeEmun paramSharedEntityTypeEmun, RoleBin paramRoleBin, BodyIndex paramBodyIndex)
  {
    boolean bool1 = paramFloat2 < 0.0F;
    float f1 = 0.0F;
    if (bool1)
      f1 = paramFloat1 / paramFloat2;
    int i = $SWITCH_TABLE$com$picooc$emun$SharedEntityTypeEmun()[paramSharedEntityTypeEmun.ordinal()];
    float f2 = 0.0F;
    switch (i)
    {
    default:
    case 1:
    case 2:
    case 7:
    case 6:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      if (f2 > 99.0F)
        f2 = 99.0F;
      return f2;
      boolean bool8 = f1 < 0.85D;
      f2 = 0.0F;
      if (!bool8)
        f2 = 90.0F - 10.0F * ((f1 - 0.85F) / 0.07F);
      if ((f1 >= 0.8D) && (f1 < 0.85D))
        f2 = 95.0F - 5.0F * ((f1 - 0.8F) / 0.05F);
      if ((f1 >= 0.7D) && (f1 < 0.8D))
        f2 = 98.0F - 3.0F * ((f1 - 0.7F) / 0.1F);
      if ((f1 > 0.0D) && (f1 < 0.7D))
      {
        f2 = 99.0F;
        continue;
        float[] arrayOfFloat3 = ReportDirect.calculateIdealBodyFat(paramRoleBin, paramBodyIndex, false).getRaceArray();
        float f9 = arrayOfFloat3[3];
        float f10 = arrayOfFloat3[4];
        boolean bool7 = paramFloat1 < f10;
        f2 = 0.0F;
        if (!bool7)
          f2 = 60.0F + 30.0F * ((paramFloat1 - f9) / (f10 - f9));
        if ((paramFloat1 > f10) && (paramFloat1 < 50.0F))
          f2 = 90.0F + 9.0F * ((paramFloat1 - f10) / (50.0F - f10));
        if (paramFloat1 >= 50.0F)
        {
          f2 = 99.0F;
          continue;
          float[] arrayOfFloat2 = ReportDirect.calculateIdealBodyFat(paramRoleBin, paramBodyIndex, false).getRaceArray();
          float f6 = arrayOfFloat2[0];
          float f7 = arrayOfFloat2[1];
          float f8 = arrayOfFloat2[2];
          boolean bool5 = paramFloat1 < f7;
          f2 = 0.0F;
          if (bool5)
          {
            boolean bool6 = paramFloat1 < f8;
            f2 = 0.0F;
            if (!bool6)
              f2 = 70.0F + 20.0F * ((f8 - paramFloat1) / (f8 - f7));
          }
          if ((paramFloat1 <= f7) && (paramFloat1 > f6))
          {
            f2 = 90.0F + 9.0F * ((f7 - paramFloat1) / (f7 - f6));
            continue;
            float[] arrayOfFloat1 = ReportDirect.calculateIdealBodyFat(paramRoleBin, paramBodyIndex, false).getRaceArray();
            float f4 = 2.0F + arrayOfFloat1[3];
            float f5 = arrayOfFloat1[4];
            boolean bool4 = paramFloat1 < f5;
            f2 = 0.0F;
            if (!bool4)
              f2 = 60.0F + 30.0F * ((paramFloat1 - f4) / (f5 - f4));
            if ((paramFloat1 > f5) && (paramFloat1 < 50.0F))
              f2 = 90.0F + 9.0F * ((paramFloat1 - f5) / (50.0F - f5));
            if (paramFloat1 >= 50.0F)
            {
              f2 = 99.0F;
              continue;
              boolean bool3 = paramFloat1 < 9.0F + paramFloat2;
              f2 = 0.0F;
              if (!bool3)
                f2 = 60.0F + 30.0F * ((paramFloat1 - (6.0F + paramFloat2)) / 3.0F);
              if ((paramFloat1 > 9.0F + paramFloat2) && (paramFloat1 < 90.0F))
                f2 = 90.0F + 8.0F * ((paramFloat1 - (9.0F + paramFloat2)) / (90.0F - (9.0F + paramFloat2)));
              if (paramFloat1 >= 90.0F)
              {
                f2 = 98.0F;
                continue;
                ReportEntity localReportEntity = ReportDirect.judgeBoneMassByRole(paramRoleBin, paramBodyIndex);
                int j = localReportEntity.getRegionArray().length;
                float f3 = 0.0F;
                if (j > 1)
                  f3 = localReportEntity.getRegionArray()[1];
                if (paramFloat1 <= 0.4D + f3)
                {
                  f2 = 80.0F + 15.0F * ((paramFloat1 - f3) / 0.4F);
                }
                else if (paramFloat1 < 0.6D + f3)
                {
                  f2 = 95.0F + 4.0F * ((paramFloat1 - (0.4F + f3)) / 0.2F);
                }
                else
                {
                  f2 = 99.0F;
                  continue;
                  if ((f1 > 1.1D) && (f1 <= 1.2D))
                    f2 = 70.0F + 20.0F * ((f1 - 1.1F) / 0.1F);
                  while (true)
                  {
                    if ((f1 < 0.9D) || (f1 >= 1.0F))
                      break label924;
                    f2 = 75.0F - 15.0F * ((f1 - 0.9F) / 0.1F);
                    break;
                    if ((f1 > 1.2D) && (f1 <= 1.5D))
                    {
                      f2 = 90.0F + 5.0F * ((f1 - 1.2F) / 0.3F);
                    }
                    else
                    {
                      boolean bool2 = f1 < 1.5D;
                      f2 = 0.0F;
                      if (bool2)
                        f2 = 96.0F;
                    }
                  }
                  label924: if ((f1 >= 0.85D) && (f1 < 0.9D))
                    f2 = 85.0F - 10.0F * ((f1 - 0.85F) / 0.04999995F);
                  else if ((f1 >= 0.5D) && (f1 < 0.85D))
                    f2 = 99.0F - 14.0F * ((f1 - 0.5F) / 0.35F);
                  else if (f1 < 0.5D)
                    f2 = 99.0F;
                }
              }
            }
          }
        }
      }
    }
  }

  public static SharedEntity muscle(Context paramContext, RoleBin paramRoleBin, BodyIndex paramBodyIndex)
  {
    float f1 = paramBodyIndex.getMusde_race();
    float f2 = paramBodyIndex.getBodyFat();
    float f3 = ReportDirect.judgeMuscleRaceByRole(paramRoleBin, paramBodyIndex).getRegionArray()[0];
    float f4 = ReportDirect.calculateIdealWeight(paramRoleBin);
    int i = paramRoleBin.getSex();
    SharedEntity localSharedEntity = null;
    int j;
    if (i == 1)
    {
      boolean bool1 = f1 < 6.0F + f3;
      localSharedEntity = null;
      if (!bool1)
      {
        boolean bool2 = f2 < 0.0F;
        localSharedEntity = null;
        if (bool2)
        {
          boolean bool3 = paramBodyIndex.getWeight() < 1.1D * f4;
          localSharedEntity = null;
          if (bool3)
          {
            localSharedEntity = new SharedEntity();
            localSharedEntity.setType(SharedEntityTypeEmun.SharedEntityMuscleRatio);
            localSharedEntity.setImageName("shareEntityImageName_man_muscle.png");
            if ((6.0F + f3 > f1) || (f1 >= 8.0F + f3))
              break label227;
            localSharedEntity.setLevel(1);
            localSharedEntity.setName("未来健美先生");
            localSharedEntity.setPersent(getPersentWithCurrentData(f1, f3, SharedEntityTypeEmun.SharedEntityMuscleRatio, paramRoleBin, paramBodyIndex));
            j = OperationDB.queryShareAchievementLevelWithLastTimeByRoleId(paramContext, paramRoleBin, SharedEntityTypeEmun.SharedEntityMuscleRatio, System.currentTimeMillis());
            if (j >= 0)
              break label333;
            localSharedEntity.setMessage("您当前比全国" + localSharedEntity.getPersent() + "%的同龄汉子更威猛。");
          }
        }
      }
    }
    label227: 
    do
    {
      return localSharedEntity;
      if ((8.0F + f3 <= f1) && (f1 < 10.0F + f3))
      {
        localSharedEntity.setLevel(2);
        localSharedEntity.setName("大块头有大智慧");
        break;
      }
      if ((10.0F + f3 <= f1) && (f1 < 13.0F + f3))
      {
        localSharedEntity.setLevel(3);
        localSharedEntity.setName("人鱼线王子");
        break;
      }
      if (13.0F + f3 >= f1)
        break;
      localSharedEntity.setLevel(4);
      localSharedEntity.setName("施瓦辛肌");
      break;
      if (j < localSharedEntity.getLevel())
      {
        localSharedEntity.setMessage("在猛男的路上一路狂奔！已将全国" + localSharedEntity.getPersent() + "%的同龄汉子甩在了身后！");
        return localSharedEntity;
      }
    }
    while (j <= localSharedEntity.getLevel());
    label333: localSharedEntity.setMessage("肌肉略有缩水，但仍比全国" + localSharedEntity.getPersent() + "%的同龄汉子更威猛。");
    return localSharedEntity;
  }

  public static SharedEntity weight(Context paramContext, RoleBin paramRoleBin, BodyIndex paramBodyIndex)
  {
    float f1 = ReportDirect.calculateIdealWeight(paramRoleBin);
    float f2 = paramBodyIndex.getWeight();
    SharedEntity localSharedEntity;
    int j;
    if (paramRoleBin.getSex() == 1.0F)
    {
      boolean bool3 = f2 < 0.92D * f1;
      localSharedEntity = null;
      if (!bool3)
      {
        boolean bool4 = f2 < 0.0F;
        localSharedEntity = null;
        if (bool4)
        {
          localSharedEntity = new SharedEntity();
          localSharedEntity.setType(SharedEntityTypeEmun.SharedEntityWeight);
          localSharedEntity.setImageName("sharedEntityImageName_man_weight.png");
          if (f2 >= 0.8D * f1)
            break label176;
          localSharedEntity.setLevel(1);
          localSharedEntity.setName("纸片人钉子户");
          localSharedEntity.setPersent(getPersentWithCurrentData(f2, f1, SharedEntityTypeEmun.SharedEntityWeight, paramRoleBin, paramBodyIndex));
          j = OperationDB.queryShareAchievementLevelWithLastTimeByRoleId(paramContext, paramRoleBin, SharedEntityTypeEmun.SharedEntityWeight, System.currentTimeMillis());
          if (j >= 0)
            break label305;
          localSharedEntity.setMessage("您当前比全国" + localSharedEntity.getPersent() + "%的同龄汉子体格单薄。");
        }
      }
    }
    label176: label305: int i;
    do
    {
      boolean bool2;
      do
      {
        boolean bool1;
        do
        {
          do
          {
            return localSharedEntity;
            if ((0.8D * f1 <= f2) && (f2 < 0.84D * f1))
            {
              localSharedEntity.setLevel(2);
              localSharedEntity.setName("软绵绵纸巾人");
              break;
            }
            if ((0.84D * f1 <= f2) && (f2 < 0.88D * f1))
            {
              localSharedEntity.setLevel(3);
              localSharedEntity.setName("脆邦邦报纸人");
              break;
            }
            if ((0.88D * f1 > f2) || (f2 >= 0.92D * f1))
              break;
            localSharedEntity.setLevel(4);
            localSharedEntity.setName("铜板纸片人");
            break;
            if (j < localSharedEntity.getLevel())
            {
              localSharedEntity.setMessage("有进步！但仍比全国" + localSharedEntity.getPersent() + "%的同龄汉子体格单薄哦！");
              return localSharedEntity;
            }
          }
          while (j <= localSharedEntity.getLevel());
          localSharedEntity.setMessage("又瘦啦，比全国" + localSharedEntity.getPersent() + "%的同龄汉子更单薄了:(");
          return localSharedEntity;
          bool1 = f2 < 0.95D * f1;
          localSharedEntity = null;
        }
        while (bool1);
        bool2 = f2 < 0.0F;
        localSharedEntity = null;
      }
      while (!bool2);
      localSharedEntity = new SharedEntity();
      localSharedEntity.setType(SharedEntityTypeEmun.SharedEntityWeight);
      localSharedEntity.setImageName("sharedEntityImageName_woman_weight.png");
      if ((0.92D * f1 <= f2) && (f2 <= 0.95D * f1))
      {
        localSharedEntity.setLevel(1);
        localSharedEntity.setName("灵动的蜻蜓");
      }
      while (true)
      {
        localSharedEntity.setPersent(getPersentWithCurrentData(f2, f1, SharedEntityTypeEmun.SharedEntityWeight, paramRoleBin, paramBodyIndex));
        i = OperationDB.queryShareAchievementLevelWithLastTimeByRoleId(paramContext, paramRoleBin, SharedEntityTypeEmun.SharedEntityWeight, System.currentTimeMillis());
        if (i >= 0)
          break;
        localSharedEntity.setMessage("您当前比全国" + localSharedEntity.getPersent() + "%的同龄mm体态更轻盈。");
        return localSharedEntity;
        if ((0.89D * f1 < f2) && (f2 <= 0.92D * f1))
        {
          localSharedEntity.setLevel(2);
          localSharedEntity.setName("翩翩蝴蝶");
        }
        else if ((0.85D * f1 < f2) && (f2 <= 0.89D * f1))
        {
          localSharedEntity.setLevel(3);
          localSharedEntity.setName("矫健的飞燕");
        }
        else if (f2 <= 0.85D * f1)
        {
          localSharedEntity.setLevel(4);
          localSharedEntity.setName("空中精灵");
        }
      }
      if (i < localSharedEntity.getLevel())
      {
        localSharedEntity.setMessage("又瘦啦～比全国" + localSharedEntity.getPersent() + "%的同龄mm体态更轻盈了～");
        return localSharedEntity;
      }
    }
    while (i <= localSharedEntity.getLevel());
    localSharedEntity.setMessage("稍胖了些，不过还是比全国" + localSharedEntity.getPersent() + "%的同龄mm体态更轻盈哦～");
    return localSharedEntity;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SharedDirect
 * JD-Core Version:    0.6.2
 */
