package com.picooc.model;

import android.graphics.Color;
import com.picooc.arithmetic.ReportDirect;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.ReportEntity;
import com.picooc.domain.RoleBin;
import com.picooc.utils.DateUtils;
import com.picooc.utils.NumUtils;
import java.io.Serializable;
import java.util.ArrayList;
import org.achartengine.tools.ModUtils;

public class MainBodyScoreModel
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private ReportEntity bmrReport;
  private BodyIndex body;
  private ReportEntity boneMassReport;
  private int cycleColor;
  private String dateTitle = "";
  private ReportEntity fatReport;
  private ReportEntity infatReport;
  private ReportEntity muscleReport;
  private ArrayList<itemData> noStandardLists;
  private String noStandardTitle = "";
  private ReportEntity proteinReport;
  private RoleBin role;
  private String scoreTips = "";
  private ArrayList<itemData> subHealthLists;
  private String subHealthTitle = "";
  private String title = "";
  private int totalScore;
  private ReportEntity waterReport;
  private ReportEntity weightReport;

  public MainBodyScoreModel(RoleBin paramRoleBin, BodyIndex paramBodyIndex)
  {
    if (paramBodyIndex.getBodyFat() <= 0.0F)
      return;
    this.role = paramRoleBin;
    this.body = paramBodyIndex;
    this.weightReport = ReportDirect.judgeWeightByRole(paramRoleBin, paramBodyIndex.getWeight());
    this.fatReport = ReportDirect.judgeBodyFatByRole(paramRoleBin, paramBodyIndex);
    this.muscleReport = ReportDirect.judgeMuscleRaceByRole(paramRoleBin, paramBodyIndex);
    this.boneMassReport = ReportDirect.judgeBoneMassByRole(paramRoleBin, paramBodyIndex);
    this.waterReport = ReportDirect.judgeWaterRaceByRole(paramRoleBin, paramBodyIndex);
    this.proteinReport = ReportDirect.judgeProteinRaceByRole(paramRoleBin, paramBodyIndex);
    this.bmrReport = ReportDirect.judgeBmrByRole(paramRoleBin, paramBodyIndex);
    this.infatReport = ReportDirect.judgeInFatByRole(paramRoleBin, paramBodyIndex);
    this.totalScore = ReportDirect.caculateBodyTotalNum2(paramRoleBin, this.weightReport, this.fatReport, this.muscleReport, this.boneMassReport, this.waterReport, this.proteinReport, this.bmrReport, this.infatReport)[8];
  }

  private void initTitle()
  {
    long l = DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), this.body.getTime());
    if (l == 0L)
    {
      this.dateTitle = DateUtils.changeTimeStampToFormatTime(this.body.getTime(), "MM月dd日");
      this.title = "今日身体得分";
      return;
    }
    if (l >= 1L)
    {
      String str2 = DateUtils.changeTimeStampToFormatTime(this.body.getTime(), "MM月dd日");
      this.dateTitle = (l + "天前(" + str2 + ")");
      this.title = (str2 + "身体得分");
      return;
    }
    String str1 = DateUtils.changeTimeStampToFormatTime(this.body.getTime(), "MM月dd日");
    this.dateTitle = (l + "天后(" + str1 + ")");
    this.title = (str1 + "身体得分");
  }

  public int getCycleColor()
  {
    if (this.cycleColor != 0)
      return this.cycleColor;
    if (this.totalScore >= 100)
      this.cycleColor = Color.argb(255, 112, 250, 0);
    while (true)
    {
      return this.cycleColor;
      if ((this.totalScore >= 80) && (this.totalScore < 100))
        this.cycleColor = Color.argb(255, 198, 255, 0);
      else if ((this.totalScore >= 60) && (this.totalScore < 80))
        this.cycleColor = Color.argb(255, 255, 234, 0);
      else if ((this.totalScore >= 40) && (this.totalScore < 60))
        this.cycleColor = Color.argb(255, 255, 84, 0);
      else
        this.cycleColor = Color.argb(255, 255, 0, 0);
    }
  }

  public String getDateTilte()
  {
    if (!this.dateTitle.equals(""))
      return this.dateTitle;
    initTitle();
    return this.dateTitle;
  }

  public ReportEntity getFatReport()
  {
    return this.fatReport;
  }

  public ArrayList<itemData> getNoStandardLists()
  {
    if (this.noStandardLists == null)
      getNoStandardTitle();
    return this.noStandardLists;
  }

  public String getNoStandardTitle()
  {
    if (!"".equals(this.noStandardTitle))
      return this.noStandardTitle;
    float f5;
    if (this.noStandardLists == null)
    {
      this.noStandardLists = new ArrayList();
      if (!this.weightReport.getStandardOrNot().booleanValue())
      {
        float f6 = (float)(this.body.getWeight() - 1.1D * this.weightReport.getNum2());
        this.noStandardLists.add(new itemData(2130837619, "体重", ModUtils.caclutSaveOnePoint(this.body.getWeight()) + "kg", 2, ModUtils.caclutSaveOnePoint(Math.abs(f6)) + "kg", null));
      }
      if (!this.fatReport.getStandardOrNot().booleanValue())
      {
        if (this.role.getAge() <= 60)
          break label948;
        f5 = this.body.getBodyFat() - this.fatReport.getRegionArray()[1];
        this.noStandardLists.add(new itemData(2130837622, "脂肪率", ModUtils.caclutSaveOnePoint(this.body.getBodyFat()) + "%", 2, ModUtils.caclutSaveOnePoint(Math.abs(f5)) + "kg", null));
      }
      if (!this.muscleReport.getStandardOrNot().booleanValue())
      {
        float f4 = this.body.getMusde_race() - this.muscleReport.getRegionArray()[0];
        this.noStandardLists.add(new itemData(2130837614, "肌肉率", ModUtils.caclutSaveOnePoint(this.body.getMusde_race()) + "%", 1, ModUtils.caclutSaveOnePoint(Math.abs(f4)) + "%", null));
      }
      if (!this.infatReport.getStandardOrNot().booleanValue())
        this.noStandardLists.add(new itemData(2130837616, "内脏脂肪指数", ModUtils.caclutSaveOnePoint(this.body.getViser_fat_level()), 0, "", null));
      if (!this.bmrReport.getStandardOrNot().booleanValue())
      {
        int i = NumUtils.roundFloatToInt(this.body.getBasic_metabolism()) - NumUtils.roundFloatToInt(this.bmrReport.getNum2());
        this.noStandardLists.add(new itemData(2130837608, "基础代谢率", NumUtils.roundFloatToInt(this.body.getBasic_metabolism()) + "kcal", 1, Math.abs(i) + "kcal", null));
      }
      if (!this.waterReport.getStandardOrNot().booleanValue())
      {
        float f3 = this.body.getWater_race() - this.waterReport.getRegionArray()[0];
        this.noStandardLists.add(new itemData(2130837617, "水分", ModUtils.caclutSaveOnePoint(this.body.getWater_race()) + "%", 1, ModUtils.caclutSaveOnePoint(Math.abs(f3)) + "%", null));
      }
      if (!this.proteinReport.getStandardOrNot().booleanValue())
      {
        float f2 = this.body.getProtein_race() - this.proteinReport.getRegionArray()[0];
        this.noStandardLists.add(new itemData(2130837608, "蛋白质", ModUtils.caclutSaveOnePoint(this.body.getProtein_race()) + "%", 1, ModUtils.caclutSaveOnePoint(Math.abs(f2)) + "%", null));
      }
      if (!this.boneMassReport.getStandardOrNot().booleanValue())
      {
        float f1 = this.body.getBone_mass() - this.boneMassReport.getRegionArray()[0];
        this.noStandardLists.add(new itemData(2130837611, "骨量", ModUtils.caclutSaveOnePoint(this.body.getBone_mass()) + "kg", 1, ModUtils.caclutSaveOnePoint(Math.abs(f1)) + "kg", null));
      }
    }
    if (this.noStandardLists.size() > 0);
    for (this.noStandardTitle = ("共检测8项指标，其中" + this.noStandardLists.size() + "项未达标"); ; this.noStandardTitle = "共检测8项指标，已全部达标")
    {
      return this.noStandardTitle;
      label948: f5 = this.body.getBodyFat() - this.fatReport.getRegionArray()[3];
      break;
    }
  }

  public String getScoreTips()
  {
    if (!"".equals(this.scoreTips))
      return this.scoreTips;
    if (this.totalScore >= 100)
      this.scoreTips = "身体非常健康！\n请继续保持您目前的生活方式～";
    while (true)
    {
      return this.scoreTips;
      if ((this.totalScore >= 80) && (this.totalScore < 100))
        this.scoreTips = "身体比较健康，还有提升空间哦～请参照运动方案和饮食与营养调整您的生活方式。";
      else if ((this.totalScore >= 60) && (this.totalScore < 80))
        this.scoreTips = "健康状况一般，精力和体力水平正在下降。请立刻参照运动方案和饮食与营养调整生活方式。";
      else if ((this.totalScore >= 40) && (this.totalScore < 60))
        this.scoreTips = "健康状况较差，精力和体力水平很低，患病风险很高。请立刻参照运动方案和饮食与营养调整生活方式。";
      else
        this.scoreTips = "健康状况很差，建议去医院进行全面检查。并立刻参照运动方案和饮食与营养调整生活方式。";
    }
  }

  public ArrayList<itemData> getSubHealthLists()
  {
    if (this.subHealthLists == null)
      getSubHealthTitle();
    return this.subHealthLists;
  }

  public String getSubHealthTitle()
  {
    if (!"".equals(this.subHealthTitle))
      return this.subHealthTitle;
    if (this.subHealthLists == null)
    {
      this.subHealthLists = new ArrayList();
      if (this.weightReport.getIsSubHealth().booleanValue())
        this.subHealthLists.add(new itemData(2130837619, "体重", ModUtils.caclutSaveOnePoint(this.body.getWeight()) + "kg", 0, "", null));
      if (this.fatReport.getIsSubHealth().booleanValue())
        this.subHealthLists.add(new itemData(2130837622, "脂肪率", ModUtils.caclutSaveOnePoint(this.body.getBodyFat()) + "%", 0, "", null));
      if (this.muscleReport.getIsSubHealth().booleanValue())
        this.subHealthLists.add(new itemData(2130837614, "肌肉率", ModUtils.caclutSaveOnePoint(this.body.getMusde_race()) + "%", 0, "", null));
      if (this.infatReport.getIsSubHealth().booleanValue())
        this.subHealthLists.add(new itemData(2130837616, "内脏脂肪指数", ModUtils.caclutSaveOnePoint(this.body.getViser_fat_level()), 0, "", null));
    }
    if (this.subHealthLists.size() > 0)
      this.subHealthTitle = ("健康警示指标为" + this.subHealthLists.size() + "项");
    return this.subHealthTitle;
  }

  public String getTitle()
  {
    if (!"".equals(this.title))
      return this.title;
    initTitle();
    return this.title;
  }

  public int getTotalNum()
  {
    return this.totalScore;
  }

  public class itemData
  {
    public String changeValue;
    public int imageID;
    public String itemString;
    public String itemValue;
    public int state;

    private itemData(int paramString1, String paramString2, String paramInt1, int paramString3, String arg6)
    {
      this.imageID = paramString1;
      this.itemString = paramString2;
      this.itemValue = paramInt1;
      this.state = paramString3;
      Object localObject;
      this.changeValue = localObject;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     MainBodyScoreModel
 * JD-Core Version:    0.6.2
 */
