package com.picooc.model;

import android.content.Context;
import com.picooc.MyApplication;
import com.picooc.arithmetic.ReportDirect;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.ReportEntity;
import com.picooc.domain.RoleBin;
import java.util.ArrayList;

public class ReportModel
{
  private ReportEntity bmrReport;
  private BodyCompositionAnalysisModel bodyComposition;
  private BodyTypeModel bodyType;
  private ReportEntity boneReport;
  private DietAndNutritionModel dietAndNutritionModel;
  private ReportEntity fatReport;
  private ReportEntity infatReport;
  private InterimReportModel interimReport;
  private ReportEntity muscleReport;
  private ReportEntity proteinReport;
  private SportPlanModel sportPlan;
  private ReportEntity waterReport;
  private ReportEntity weightReport;

  public ReportModel(Context paramContext, RoleBin paramRoleBin, BodyIndex paramBodyIndex)
  {
    this.weightReport = ReportDirect.judgeWeightByRole(paramRoleBin, paramBodyIndex.getWeight());
    this.fatReport = ReportDirect.judgeBodyFatByRole(paramRoleBin, paramBodyIndex);
    this.muscleReport = ReportDirect.judgeMuscleRaceByRole(paramRoleBin, paramBodyIndex);
    this.waterReport = ReportDirect.judgeWaterRaceByRole(paramRoleBin, paramBodyIndex);
    this.bmrReport = ReportDirect.judgeBmrByRole(paramRoleBin, paramBodyIndex);
    this.infatReport = ReportDirect.judgeInFatByRole(paramRoleBin, paramBodyIndex);
    this.boneReport = ReportDirect.judgeBoneMassByRole(paramRoleBin, paramBodyIndex);
    this.proteinReport = ReportDirect.judgeProteinRaceByRole(paramRoleBin, paramBodyIndex);
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(this.weightReport);
    localArrayList.add(this.fatReport);
    localArrayList.add(this.muscleReport);
    localArrayList.add(this.waterReport);
    localArrayList.add(this.bmrReport);
    localArrayList.add(this.infatReport);
    localArrayList.add(this.boneReport);
    localArrayList.add(this.proteinReport);
    this.bodyType = new BodyTypeModel(paramRoleBin, paramBodyIndex, this.weightReport, this.fatReport, this.muscleReport, this.infatReport);
    this.bodyComposition = new BodyCompositionAnalysisModel(paramRoleBin, paramBodyIndex, localArrayList, this.bodyType.getRtype());
    this.interimReport = new InterimReportModel(paramContext, paramRoleBin);
    this.sportPlan = new SportPlanModel(paramContext, ((MyApplication)paramContext.getApplicationContext()).getTodayBody().getTime(), paramRoleBin, this.bodyType.getRtype(), localArrayList);
    this.dietAndNutritionModel = new DietAndNutritionModel(paramContext, this.bodyType.getRtype(), paramRoleBin.getSex(), this.proteinReport);
  }

  public BodyCompositionAnalysisModel getBodyComposition()
  {
    return this.bodyComposition;
  }

  public BodyTypeModel getBodyType()
  {
    return this.bodyType;
  }

  public DietAndNutritionModel getDietAndNutritionModel()
  {
    return this.dietAndNutritionModel;
  }

  public InterimReportModel getInterimReport()
  {
    return this.interimReport;
  }

  public SportPlanModel getSportPlan()
  {
    return this.sportPlan;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ReportModel
 * JD-Core Version:    0.6.2
 */
