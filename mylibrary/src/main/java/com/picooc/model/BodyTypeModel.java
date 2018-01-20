package com.picooc.model;

import com.picooc.arithmetic.ReportDirect;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.ReportEntity;
import com.picooc.domain.RoleBin;

public class BodyTypeModel
{
  private BodyTypeEnum Rtype;
  private String _fatStr;
  private String _muscleStr;
  private String _weightStr;
  private String bodyTypeStr;
  private String cautionStr;
  private String factorStr;
  private ReportEntity fatReport;
  private float idealWeight;
  private String improveStr;
  private ReportEntity infatReport;
  private ReportEntity muscleReport;
  private String programme;
  private RoleBin role;
  private ReportEntity weightReport;

  public BodyTypeModel(RoleBin paramRoleBin, BodyIndex paramBodyIndex, ReportEntity paramReportEntity1, ReportEntity paramReportEntity2, ReportEntity paramReportEntity3, ReportEntity paramReportEntity4)
  {
    this.muscleReport = paramReportEntity3;
    this.fatReport = paramReportEntity2;
    this.weightReport = paramReportEntity1;
    this.infatReport = paramReportEntity4;
    this.role = paramRoleBin;
    this.idealWeight = ReportDirect.calculateIdealWeight(paramRoleBin);
    this.Rtype = BodyTypeEnum.getEmnuByIndex(ReportDirect.judgeBodyType(paramRoleBin, paramBodyIndex));
    initOutPutProgramme(paramReportEntity1, paramReportEntity2, paramReportEntity3);
  }

  private void initOutPutProgramme(ReportEntity paramReportEntity1, ReportEntity paramReportEntity2, ReportEntity paramReportEntity3)
  {
    int i = this.role.getSex();
    if (this.role.getAge() <= 60)
    {
      switch ($SWITCH_TABLE$com$picooc$model$BodyTypeEnum()[this.Rtype.ordinal()])
      {
      default:
        this.programme = "机器错误";
        return;
      case 1:
        switch (i)
        {
        default:
          if (paramReportEntity3.getStateCode() >= 2)
          {
            this.programme = "体重虽然正常，但体脂肪水平已经超标，属于隐性胖。隐性胖体型的人是高血压、高血脂、高血糖、脂肪肝等慢性代谢疾病的高发人群，需要引起足够重视。";
            if (paramReportEntity1.getStateCode() > 1)
              break label196;
          }
          break;
        case 0:
        }
        for (this._weightStr = "偏低"; ; this._weightStr = "正常")
        {
          this._fatStr = "偏高";
          if (paramReportEntity3.getStateCode() != 2)
            break label205;
          this._muscleStr = "正常";
          return;
          if (paramReportEntity3.getStateCode() >= 2)
          {
            this.programme = "体重虽然正常，但体脂肪水平已经超标，属于隐性胖。隐性胖容易令您患上高血脂、高血糖、脂肪肝等病症；也是身体各部位肌肉松松垮垮，不够紧实的元凶哦。";
            break;
          }
          this.programme = "体重虽然正常，但体脂肪水平已经超标，属于隐性胖。隐性胖容易令您患上高血脂、高血糖、脂肪肝等病症；也是身体各部位肌肉松松垮垮，不够紧实的元凶哦，您可以通过适量的无氧运动来使肌肉变紧实。";
          break;
          this.programme = "体重虽然正常，但体脂肪水平已经超标，属于隐性胖。隐性胖体型的人是高血压、高血脂、高血糖、脂肪肝等慢性代谢疾病的高发人群，需要引起足够重视。另外您的肌肉量不足，建议开始通过无氧或器械运动增加肌肉。";
          break;
        }
        this._muscleStr = "偏低";
        return;
      case 2:
        switch (i)
        {
        default:
          if (paramReportEntity2.getStateCode() <= 3)
          {
            this.programme = "体重虽然严重超标，但体脂肪水平尚属正常，属于结实的偏胖体型。如果喜好健壮身材，暂无需减重。但您需要格外关注自己的脂肪率，脂肪率超标是心脏病、糖尿病、胆固醇过高、高血压等病症的导火索。";
            if (paramReportEntity1.getNum() <= 1.15D * this.idealWeight)
              break label334;
            this._weightStr = "严重偏高";
            if (paramReportEntity2.getStateCode() <= 3)
              break label343;
          }
          break;
        case 0:
        }
        for (this._fatStr = "偏高"; ; this._fatStr = "正常")
        {
          if (paramReportEntity3.getStateCode() != 2)
            break label352;
          this._muscleStr = "正常";
          return;
          if (paramReportEntity2.getStateCode() <= 3)
          {
            this.programme = "体重虽然严重超标，但体脂肪水平尚属正常，属于结实的偏胖体型。您需要格外关注自己的脂肪率，脂肪率超标是心脏病、糖尿病、胆固醇过高、高血压等病症的导火索，也是体型臃肿的元凶哦。要想身材更好的话，需要开始控制食量啦。";
            break;
          }
          this.programme = "体重和体脂肪水平均已超标，属于偏胖体型。您需要警惕心脏病、糖尿病、胆固醇过高、高血压等肥胖高发病，更要变瘦变美变漂亮，必须立刻开始减重啦。";
          break;
          this.programme = "体重和体脂肪水平均已超标，属于偏胖体型。偏胖体型的人是心脏病、糖尿病、胆固醇过高、高血压等病症的高发人群，必须立刻开始减重啦。";
          break;
          this._weightStr = "略微偏高";
          break label270;
        }
        this._muscleStr = "偏低";
        return;
      case 3:
        switch (i)
        {
        default:
        case 0:
        }
        for (this.programme = "体脂肪水平虽然正常，但体重已经严重超标，属于肌肉型偏胖。如果喜好健壮体型，暂无需减重，但您需要格外关注自己的脂肪率，脂肪率超标是心脏病、糖尿病、胆固醇过高、高血压等肥胖高发病的导火索。您的肌肉量比较充足，进行有氧运动可以帮您更快地甩掉多余重量。"; ; this.programme = "体脂肪水平虽然正常，但体重已经严重超标，属于运动型偏胖。您需要格外关注自己的脂肪率，脂肪率超标是心脏病、糖尿病、胆固醇过高、高血压等肥胖高发病的导火索。要想身材更好的话，您可以通过调整饮食结构和增加有氧运动甩掉多余重量。")
        {
          this._weightStr = "严重偏高";
          this._fatStr = "正常";
          if (paramReportEntity3.getStateCode() != 2)
            break;
          this._muscleStr = "正常内偏高";
          return;
        }
        this._muscleStr = "偏高";
        return;
      case 6:
        switch (i)
        {
        default:
        case 0:
        }
        for (this.programme = "体重相对正常，体脂肪水平标准，肌肉量充足，身体状态不错。这得益于健康积极的生活方式，继续保持哦。"; ; this.programme = "体重和体脂肪水平都在正常范围内，充足的肌肉保有量让您可以燃烧更多热量，不易发胖。这得益于健康积极的生活方式，继续保持哦。")
        {
          this._weightStr = "正常";
          this._fatStr = "偏低";
          this._muscleStr = "偏高";
          return;
        }
      case 5:
        if (paramReportEntity1.getStateCode() == 1)
          if (i == 1)
          {
            this.programme = "体脂肪水平在正常范围内，但体重有些偏低哦。建议您每天保持定量的运动，尤其是无氧或器械运动，同时调整饮食结构和增加食量。让体重保持在标准范围，免疫力更强，身材也更棒。";
            if (paramReportEntity1.getStateCode() > 1)
              break label572;
            this._weightStr = "略微偏低";
            if (paramReportEntity2.getStateCode() != 3)
              break label598;
          }
        for (this._fatStr = "正常"; ; this._fatStr = "偏低")
        {
          this._muscleStr = "正常";
          return;
          this.programme = "体脂肪水平在正常范围内，但体重有些偏低哦。建议您每天保持定量的运动，尤其是适当的无氧运动，让您的身体变紧实，线条更美，还能提高免疫力哦。";
          break;
          if (paramReportEntity1.getStateCode() == 2)
          {
            this.programme = "体重和体脂肪水平都在正常范围内，身体状态不错。这得益于健康积极的生活方式，继续保持哦。";
            break;
          }
          this.programme = "体重略微超标，体脂肪和肌肉水平都在正常范围内，身体状态比较好。这得益于健康积极的生活方式，继续保持哦。";
          break;
          if (paramReportEntity1.getStateCode() >= 3)
          {
            this._weightStr = "略微偏高";
            break label516;
          }
          this._weightStr = "正常";
          break label516;
        }
      case 4:
        if (i == 1)
        {
          this.programme = "体重和体脂肪水平虽然正常，但肌肉量较低，属于缺乏锻炼型。您比较容易发胖、免疫力也较低下，容易生病。每天保持定量的运动，尤其是无氧或器械运动，可以增加肌肉量，提高免疫力，也让身材变得更好。";
          if (paramReportEntity1.getStateCode() != 1)
            break label663;
        }
        for (this._weightStr = "偏低"; ; this._weightStr = "正常")
        {
          this._fatStr = "正常";
          if (paramReportEntity3.getStateCode() != 2)
            break label672;
          this._muscleStr = "正常内偏低";
          return;
          this.programme = "体重和体脂肪水平虽然正常，但肌肉量较低，属于运动不足型。您比较容易发胖、免疫力也较低下，容易生病。每天保持定量的运动，尤其是适当的无氧运动，让您的身体变紧实，线条更美，还能提高免疫力哦。";
          break;
        }
        this._muscleStr = "偏低";
        return;
      case 9:
        label516: label663: label672: this.programme = "体重虽然超标，但体脂肪水平较低，肌肉量充足，属于健美的运动员体型。日常只要注意饮食结构，就可以轻松地控制体重啦。";
        if (paramReportEntity1.getNum() > 1.15D * this.idealWeight);
        for (this._weightStr = "严重偏高"; ; this._weightStr = "略微偏高")
        {
          this._fatStr = "偏低";
          if (paramReportEntity3.getStateCode() != 2)
            break;
          this._muscleStr = "正常内偏高";
          return;
        }
        this._muscleStr = "偏高";
        return;
      case 8:
        label196: label205: label334: label343: label352: if (i == 1);
        label270: label572: label598: for (this.programme = "体重和体脂肪水平较低，请注意饮食营养和睡眠，保证心情愉快。多食多餐，以摄取碳水化合物和蛋白质类食物为主。"; ; this.programme = "体重和体脂肪水平较低，体型很完美啦。肌肉量比较充足，让您可以燃烧更多热量，不易发胖。这得益于健康积极的生活方式，继续保持哦。")
        {
          this._weightStr = "偏低";
          this._fatStr = "偏低";
          this._muscleStr = "偏高";
          return;
        }
      case 7:
      }
      switch (i)
      {
      default:
      case 0:
      }
      for (this.programme = "体重和体脂肪水平较低，您需要开始增重了。请增加力量训练，注意饮食营养和睡眠，保证心情愉快。多食多餐，以摄取碳水化合物和蛋白质类食物为主。"; ; this.programme = "您太瘦啦，体重和体脂肪水平都偏低。女性过瘦会容易导致内分泌紊乱和月经失调，贫血、胆结石和骨质疏松等疾病也容易找上门来哦。")
      {
        this._weightStr = "偏低";
        this._fatStr = "偏低";
        this._muscleStr = "正常";
        return;
      }
    }
    switch ($SWITCH_TABLE$com$picooc$model$BodyTypeEnum()[this.Rtype.ordinal()])
    {
    case 4:
    case 6:
    default:
      this.programme = "机器错误";
      return;
    case 1:
      if (paramReportEntity1.getStateCode() <= 1)
        this._weightStr = "偏低";
      while (true)
      {
        this._fatStr = "偏高";
        this.programme = "体重虽然正常，但体脂肪水平已经超标，属于隐性肥胖。隐性肥胖体型的人是高血压、高血脂、高血糖、脂肪肝等慢性代谢疾病的高发人群，需要引起足够重视。";
        return;
        if (paramReportEntity1.getStateCode() == 2)
          this._weightStr = "正常";
      }
    case 2:
      this._weightStr = "偏高";
      this._fatStr = "偏高";
      this.programme = "体重和体脂肪水平均已超标，属于肥胖体型。肥胖体型的人是心脏病、糖尿病、胆固醇过高、高血压等病症的高发人群，必须立刻开始调整饮食结构和进行运动啦。";
      return;
    case 7:
      this._weightStr = "偏低";
      if (paramReportEntity2.getStateCode() >= 3);
      for (this._fatStr = "正常"; ; this._fatStr = "偏低")
      {
        this.programme = "您的体重和脂肪量都偏低，属于消瘦型。身体消瘦会导致您乏力、免疫力下降，容易感染疾病。请增加运动量，合理膳食，并坚持测量，及时发现疾病风险。";
        return;
      }
    case 5:
      this._weightStr = "正常";
      if (paramReportEntity2.getStateCode() >= 3);
      for (this._fatStr = "正常"; ; this._fatStr = "偏低")
      {
        this.programme = "体重和体脂肪水平都在正常范围内，身体状态不错。这得益于健康积极的生活方式，继续保持哦。";
        return;
      }
    case 3:
    }
    this._weightStr = "偏高";
    if (paramReportEntity2.getStateCode() >= 3);
    for (this._fatStr = "正常"; ; this._fatStr = "偏低")
    {
      this.programme = "体脂肪水平虽然正常，但体重已经超标，属于肌肉型肥胖。要警惕心脏病、糖尿病、胆固醇过高、高血压等肥胖高发病了。您的肌肉量比较充足，适当进行爬山、健身操等有氧运动可以帮您更快地甩掉多余重量。";
      return;
    }
  }

  public String getBodyTypeStr()
  {
    if (this.bodyTypeStr != null)
      return this.bodyTypeStr;
    if (this.role.getAge() <= 60)
      switch ($SWITCH_TABLE$com$picooc$model$BodyTypeEnum()[this.Rtype.ordinal()])
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      }
    while (true)
    {
      return this.bodyTypeStr;
      if (this.role.getSex() == 1)
      {
        if (this.muscleReport.getStateCode() >= 2)
          this.bodyTypeStr = "体重虽然正常，但体脂肪水平已经超标，属于隐性胖。";
        else if (this.muscleReport.getStateCode() == 1)
          this.bodyTypeStr = "体重虽然正常，但体脂肪水平已经超标，属于隐性胖。";
      }
      else if (this.role.getSex() == 0)
        if (this.muscleReport.getStateCode() >= 2)
        {
          this.bodyTypeStr = "体重虽然正常，但体脂肪水平已经超标，属于隐性胖。";
        }
        else if (this.muscleReport.getStateCode() == 1)
        {
          this.bodyTypeStr = "体重虽然正常，但体脂肪水平已经超标，属于隐性胖。";
          continue;
          if (this.role.getSex() == 1)
          {
            if (this.fatReport.getStateCode() == 3)
            {
              if (this.infatReport.getNum() < 8.0F)
                this.bodyTypeStr = "体重虽然严重偏高，但体脂肪水平尚属正常，属于结实的偏胖体型。";
              else
                this.bodyTypeStr = "虽然体脂肪水平尚属正常，但体重严重偏高，且内脏脂肪指数偏高，属于偏胖体型。";
            }
            else if (this.fatReport.getStateCode() > 3)
              this.bodyTypeStr = "体重和体脂肪水平均已超标，属于偏胖体型。";
          }
          else if (this.role.getSex() == 0)
            if (this.fatReport.getStateCode() == 3)
            {
              this.bodyTypeStr = "体重虽然严重偏高，但体脂肪水平尚属正常，属于结实的偏胖体型。";
            }
            else if (this.fatReport.getStateCode() > 3)
            {
              this.bodyTypeStr = "体重和体脂肪水平均已超标，属于偏胖体型。";
              continue;
              if (this.role.getSex() == 1)
              {
                if (this.infatReport.getNum() < 8.0F)
                  this.bodyTypeStr = "体重虽然严重偏高，但体脂肪水平尚属正常，属于肌肉型偏胖。";
                else
                  this.bodyTypeStr = "体脂肪水平虽然正常，但体重严重偏高，且内脏脂肪指数偏高，属于肌肉型偏胖。";
              }
              else
              {
                this.bodyTypeStr = "体脂肪水平虽然正常，但体重已经严重偏高，属于运动型偏胖。";
                continue;
                if (this.role.getSex() == 1)
                {
                  this.bodyTypeStr = "体重和体脂肪水平虽然正常，但肌肉量较低，属于缺乏锻炼型。";
                }
                else if (this.role.getSex() == 0)
                {
                  this.bodyTypeStr = "体重和体脂肪水平虽然正常，但肌肉量较低，属于运动不足型。";
                  continue;
                  if (this.weightReport.getStateCode() == 1)
                  {
                    if (this.role.getSex() == 1)
                    {
                      if (this.fatReport.getIsSubHealth().booleanValue())
                        this.bodyTypeStr = "体重有些偏低，体脂肪水平虽然还在正常范围内，但濒临超标啦。";
                      else
                        this.bodyTypeStr = "体脂肪水平在正常范围内，但体重有些偏低哦。";
                    }
                    else if (this.fatReport.getIsSubHealth().booleanValue())
                      this.bodyTypeStr = "体重有些偏低，体脂肪水平虽然还在正常范围内，但濒临超标啦。";
                    else
                      this.bodyTypeStr = "体脂肪水平在正常范围内，但体重有些偏低哦。";
                  }
                  else if (this.weightReport.getStateCode() == 2)
                  {
                    if ((this.weightReport.getIsSubHealth().booleanValue()) && (this.fatReport.getIsSubHealth().booleanValue()))
                      this.bodyTypeStr = "体重和体脂肪水平虽然在正常范围内，但频临超标啦。";
                    else if ((!this.weightReport.getIsSubHealth().booleanValue()) && (this.fatReport.getIsSubHealth().booleanValue()))
                      this.bodyTypeStr = "体重还在正常范围内，但体脂肪水平频临超标啦。";
                    else if ((this.weightReport.getIsSubHealth().booleanValue()) && (!this.fatReport.getIsSubHealth().booleanValue()))
                      this.bodyTypeStr = "体脂肪水平虽然在正常范围内，但体重频临超标啦。";
                    else if ((!this.weightReport.getIsSubHealth().booleanValue()) && (!this.fatReport.getIsSubHealth().booleanValue()))
                      this.bodyTypeStr = "体重和体脂肪水平都在正常范围内，身体状态不错。";
                  }
                  else if (this.fatReport.getIsSubHealth().booleanValue())
                  {
                    this.bodyTypeStr = "体重略微超标，体脂肪水平虽然还在正常范围内，但濒临超标啦。";
                  }
                  else
                  {
                    this.bodyTypeStr = "体重略微超标，体脂肪和肌肉水平都在正常范围内，身体状态比较好。";
                    continue;
                    if (this.role.getSex() == 1)
                    {
                      this.bodyTypeStr = "体重相对正常，体脂肪水平标准，肌肉量充足，身体状态不错。";
                    }
                    else if (this.role.getSex() == 0)
                    {
                      this.bodyTypeStr = "体重和体脂肪水平都在正常范围内，充足的肌肉保有量让您可以燃烧更多热量，不易发胖。";
                      continue;
                      if (this.role.getSex() == 1)
                      {
                        this.bodyTypeStr = "体重和体脂肪水平较低，您需要开始增重了。";
                      }
                      else if (this.role.getSex() == 0)
                      {
                        this.bodyTypeStr = "您太瘦啦，体重和体脂肪水平都偏低。";
                        continue;
                        if (this.role.getSex() == 1)
                        {
                          this.bodyTypeStr = "体重和体脂肪水平较低，您可以增加些体重。";
                        }
                        else if (this.role.getSex() == 0)
                        {
                          this.bodyTypeStr = "体重和体脂肪水平较低，体型很完美啦。肌肉量比较充足，让您可以燃烧更多热量，不易发胖。";
                          continue;
                          if (this.weightReport.getNum() <= 1.15D * this.idealWeight)
                          {
                            if (this.infatReport.getNum() < 8.0F)
                              this.bodyTypeStr = "体重虽然偏高，但体脂肪水平较低，肌肉量充足，属于健美的运动员体型";
                            else
                              this.bodyTypeStr = "体重虽然偏高，但体脂肪水平较低，肌肉量充足，属于运动员体型。不过要注意内脏脂肪水平了。";
                          }
                          else if (this.infatReport.getNum() < 8.0F)
                          {
                            this.bodyTypeStr = "体重虽然偏高，但体脂肪水平较低，肌肉量充足，属于较为健美的体型。";
                          }
                          else
                          {
                            this.bodyTypeStr = "体重虽然偏高，但体脂肪水平较低，肌肉量充足，属于较为健美的体型。不过要注意内脏脂肪水平了。";
                            continue;
                            switch ($SWITCH_TABLE$com$picooc$model$BodyTypeEnum()[this.Rtype.ordinal()])
                            {
                            case 4:
                            case 6:
                            default:
                              break;
                            case 1:
                              this.bodyTypeStr = "体重虽然正常，但体脂肪水平已经超标，属于隐性肥胖。";
                              break;
                            case 2:
                              this.bodyTypeStr = "体重和体脂肪水平均已超标，属于肥胖体型。";
                              break;
                            case 7:
                              this.bodyTypeStr = "体重和体脂肪水平较低，您需要开始增重了，保持标准的体重和体脂肪水平是健康的前提。";
                              break;
                            case 5:
                              if ((this.weightReport.getIsSubHealth().booleanValue()) && (this.fatReport.getIsSubHealth().booleanValue()))
                                this.bodyTypeStr = "体重和体脂肪水平虽然在正常范围内，但频临超标啦。";
                              else if ((!this.weightReport.getIsSubHealth().booleanValue()) && (this.fatReport.getIsSubHealth().booleanValue()))
                                this.bodyTypeStr = "体重还在正常范围内，但体脂肪水平频临超标啦。";
                              else if ((this.weightReport.getIsSubHealth().booleanValue()) && (!this.fatReport.getIsSubHealth().booleanValue()))
                                this.bodyTypeStr = "体脂肪水平虽然在正常范围内，但体重频临超标啦。";
                              else
                                this.bodyTypeStr = "体重和体脂肪水平都在正常范围内，身体状态不错。";
                              break;
                            case 3:
                              this.bodyTypeStr = "体重虽然超标，但体脂肪水平较低，肌肉量比较充足，属于老年人中最理想的体型。";
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
        }
    }
  }

  public String getCautionStr()
  {
    if (this.cautionStr != null)
      return this.cautionStr;
    if (this.role.getAge() <= 60)
      switch ($SWITCH_TABLE$com$picooc$model$BodyTypeEnum()[this.Rtype.ordinal()])
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      }
    while (true)
    {
      return this.cautionStr;
      if (this.role.getSex() == 1)
      {
        if (this.muscleReport.getStateCode() >= 2)
          this.cautionStr = "隐性胖体型的人是高血压、高血脂、高血糖、脂肪肝等慢性代谢疾病的高发人群，需要引起足够重视。";
        else if (this.muscleReport.getStateCode() == 1)
          this.cautionStr = "隐性胖体型的人是高血压、高血脂、高血糖、脂肪肝等慢性代谢疾病的高发人群，需要引起足够重视。另外您的肌肉量也不足，应该马上开始锻炼啦。";
      }
      else if (this.role.getSex() == 0)
        if (this.muscleReport.getStateCode() >= 2)
        {
          this.cautionStr = "隐性胖容易令您患上高血脂、高血糖、脂肪肝等病症；也是身体各部位肌肉松松垮垮，不够紧实的元凶哦。";
        }
        else if (this.muscleReport.getStateCode() == 1)
        {
          this.cautionStr = "隐性胖容易令您患上高血脂、高血糖、脂肪肝等病症；也是身体各部位肌肉松松垮垮，不够紧实的元凶哦，您可以通过适量的无氧运动来使肌肉变紧实。";
          continue;
          if (this.role.getSex() == 1)
          {
            if (this.fatReport.getStateCode() == 3)
            {
              if (this.infatReport.getNum() < 8.0F)
                this.cautionStr = "如果喜好健壮身材，暂无需减重。但您需要格外关注自己的脂肪率，脂肪率超标是心脏病、糖尿病、胆固醇过高、高血压等病症的导火索。";
              else
                this.cautionStr = "您需要格外关注自己的脂肪率，脂肪率超标是心脏病、糖尿病、胆固醇过高、高血压等病症的导火索。";
            }
            else if (this.fatReport.getStateCode() > 3)
              this.cautionStr = "偏胖体型的人是心脏病、糖尿病、胆固醇过高、高血压等病症的高发人群，必须立刻开始减重啦。";
          }
          else if (this.role.getSex() == 0)
            if (this.fatReport.getStateCode() == 3)
            {
              this.cautionStr = "您需要格外关注自己的脂肪率，脂肪率超标是心脏病、糖尿病、胆固醇过高、高血压等病症的导火索，也是体型臃肿的元凶哦。";
            }
            else if (this.fatReport.getStateCode() > 3)
            {
              this.cautionStr = "您需要警惕心脏病、糖尿病、胆固醇过高、高血压等肥胖高发病，更要变瘦变美变漂亮，必须立刻开始减重啦。";
              continue;
              if (this.role.getSex() == 1)
              {
                if (this.infatReport.getNum() < 8.0F)
                  this.cautionStr = "如果喜好健壮体型，暂无需减重，但您需要格外关注自己的脂肪率，脂肪率超标是心脏病、糖尿病、胆固醇过高、高血压等肥胖高发病的导火索。您的肌肉量比较充足，有氧运动可以帮您更快甩掉多余重量。";
                else
                  this.cautionStr = "您需要格外关注自己的脂肪率，脂肪率超标是心脏病、糖尿病、胆固醇过高、高血压等肥胖高发病的导火索。您的肌肉量比较充足，有氧运动可以帮您更快甩掉多余重量。";
              }
              else if (this.role.getSex() == 0)
              {
                this.cautionStr = "您需要格外关注自己的脂肪率，脂肪率超标是心脏病、糖尿病、胆固醇过高、高血压等肥胖高发病的导火索。";
                continue;
                if (this.role.getSex() == 1)
                {
                  this.cautionStr = "您比较容易发胖、免疫力也较低下，容易生病。";
                }
                else if (this.role.getSex() == 0)
                {
                  this.cautionStr = "您比较容易发胖、免疫力也较低下，容易生病。";
                  continue;
                  if (this.weightReport.getStateCode() == 1)
                    this.cautionStr = "您免疫力比较低下，容易感到疲劳。";
                  else if (this.weightReport.getStateCode() == 2)
                  {
                    if ((this.weightReport.getIsSubHealth().booleanValue()) && (this.fatReport.getIsSubHealth().booleanValue()))
                      this.cautionStr = "您的身体处于亚健康状态，容易感到疲劳。饮食不稳定、作息混乱、长时间的静坐不动和缺乏运动都会让您的身体越来越不健康哦。";
                    else if ((!this.weightReport.getIsSubHealth().booleanValue()) && (this.fatReport.getIsSubHealth().booleanValue()))
                      this.cautionStr = "您的身体处于亚健康状态，容易感到困乏。饮食油腻、长时间的静坐不动都会让您的精力持续下降哦。";
                    else if ((this.weightReport.getIsSubHealth().booleanValue()) && (!this.fatReport.getIsSubHealth().booleanValue()))
                      this.cautionStr = "您的身体处于亚健康状态，比较容易感到疲劳。饮食不稳定、作息混乱和缺乏运动都会让您的身体越来越不健康哦。";
                    else if ((!this.weightReport.getIsSubHealth().booleanValue()) && (!this.fatReport.getIsSubHealth().booleanValue()))
                      this.cautionStr = "当前生活方式不错，继续保持哦。";
                  }
                  else if (this.weightReport.getStateCode() == 3)
                    if (this.fatReport.getIsSubHealth().booleanValue())
                    {
                      this.cautionStr = "您的身体处于亚健康状态，容易感到疲劳。饮食不稳定、作息混乱、长时间的静坐不动和缺乏运动都会让您的身体越来越不健康哦。";
                    }
                    else
                    {
                      this.cautionStr = "当前生活方式不错，有时会出现长时间静坐不动、缺乏运动的情况，一定要保持适量运动哦。";
                      continue;
                      if (this.role.getSex() == 1)
                      {
                        this.cautionStr = "当前生活方式不错，继续保持哦。";
                      }
                      else if (this.role.getSex() == 0)
                      {
                        this.cautionStr = "当前生活方式不错，继续保持哦。";
                        continue;
                        if (this.role.getSex() == 1)
                        {
                          this.cautionStr = "您容易感到疲惫，免疫力较低，影响雄性激素分泌。";
                        }
                        else if (this.role.getSex() == 0)
                        {
                          this.cautionStr = "容易导致内分泌紊乱和月经失调，贫血、胆结石和骨质疏松等疾病也容易找上门来哦。";
                          continue;
                          if (this.role.getSex() == 1)
                          {
                            this.cautionStr = "当前生活方式还不错，注意保证心情愉快。";
                          }
                          else if (this.role.getSex() == 0)
                          {
                            this.cautionStr = "当前生活方式还不错，注意保证心情愉快。";
                            continue;
                            if (this.weightReport.getNum() <= 1.15D * this.idealWeight)
                            {
                              if (this.infatReport.getNum() < 8.0F)
                                this.cautionStr = "经常运动的生活方式非常好，继续保持哦。";
                              else
                                this.cautionStr = "经常运动的生活方式对降低您的内脏脂肪指数很有好处，要每周保持哦。";
                            }
                            else if (this.infatReport.getNum() < 8.0F)
                            {
                              this.cautionStr = "经常运动的生活方式非常好，继续保持哦。";
                            }
                            else
                            {
                              this.cautionStr = "经常运动的生活方式对降低您的内脏脂肪指数很有好处，要每周保持哦。";
                              continue;
                              switch ($SWITCH_TABLE$com$picooc$model$BodyTypeEnum()[this.Rtype.ordinal()])
                              {
                              case 4:
                              case 6:
                              default:
                                break;
                              case 1:
                                this.cautionStr = "隐性胖体型老人表面上看不出肥胖，但是高血压、高血脂、高血糖、脂肪肝等慢性代谢疾病的高发人群，需要引起足够重视。";
                                break;
                              case 2:
                                this.cautionStr = "偏胖体型老人是冠心病、糖尿病、高血压、动脉硬化与中风等病症的高发人群，必须立刻开始减重啦。";
                                break;
                              case 7:
                                this.cautionStr = "消瘦型老人多有营养不良，免疫力也相对较低，易患感冒、肺炎等传染性疾病，患有重病时康复也较慢哦。您也要警惕贫血、骨质疏松等病症。";
                                break;
                              case 5:
                                if ((this.weightReport.getIsSubHealth().booleanValue()) && (this.fatReport.getIsSubHealth().booleanValue()))
                                  this.cautionStr = "您的身体处于亚健康状态，耐力下降，容易感到疲劳。饮食结构不合理、长时间的静坐不动和缺乏运动都会让您的身体越来越不健康哦。";
                                else if ((!this.weightReport.getIsSubHealth().booleanValue()) && (this.fatReport.getIsSubHealth().booleanValue()))
                                  this.cautionStr = "您的身体处于亚健康状态，耐力下降，容易感到困乏。饮食油腻、长时间的静坐不动都会让您的精力持续下降哦。";
                                else if ((this.weightReport.getIsSubHealth().booleanValue()) && (!this.fatReport.getIsSubHealth().booleanValue()))
                                  this.cautionStr = "您的身体处于亚健康状态，耐力下降，比较容易感到疲劳。饮食结构不合理、作息混乱和缺乏运动都会让您的身体越来越不健康哦。";
                                else
                                  this.cautionStr = "当前生活方式不错，继续保持哦。";
                                break;
                              case 3:
                                this.cautionStr = "当前生活方式不错，继续保持哦。";
                              }
                            }
                          }
                        }
                      }
                    }
                }
              }
            }
        }
    }
  }

  public String getFactorStr()
  {
    if (this.factorStr != null)
      return this.factorStr;
    if (this.role.getAge() <= 60)
      switch ($SWITCH_TABLE$com$picooc$model$BodyTypeEnum()[this.Rtype.ordinal()])
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      }
    while (true)
    {
      return this.factorStr;
      if (this.role.getSex() == 1)
      {
        if (this.muscleReport.getStateCode() >= 2)
          this.factorStr = "靠节食保持体重；久坐不动或以车代步。";
        else if (this.muscleReport.getStateCode() == 1)
          this.factorStr = "靠节食保持体重；久坐不动或以车代步；长期不运动。";
      }
      else if (this.role.getSex() == 0)
        if (this.muscleReport.getStateCode() >= 2)
        {
          this.factorStr = "习惯性减肥；靠节食保持体重；久坐不动。";
        }
        else if (this.muscleReport.getStateCode() == 1)
        {
          this.factorStr = "习惯性减肥；靠节食保持体重；久坐不动；长期不运动。";
          continue;
          if (this.role.getSex() == 1)
          {
            if (this.fatReport.getStateCode() == 3)
              this.factorStr = "曾有运动习惯且食量较大，目前运动量已降低或较少运动。";
            else if (this.fatReport.getStateCode() > 3)
              this.factorStr = "饮食结构失调；食量较大；久坐不动或以车代步；长期不运动。";
          }
          else if (this.role.getSex() == 0)
            if (this.fatReport.getStateCode() == 3)
            {
              this.factorStr = "曾有运动习惯且食量较大，目前运动量已降低或较少运动。";
            }
            else if (this.fatReport.getStateCode() > 3)
            {
              this.factorStr = "饮食结构失调；食量较大；久坐不动；长期不运动。";
              continue;
              if (this.role.getSex() == 1)
              {
                if (this.infatReport.getNum() < 8.0F)
                  this.factorStr = "有运动习惯；食量较大且饮食结构单一，偏爱肉类食物";
                else
                  this.factorStr = "有运动习惯，但频率已大大降低；食量较大且饮食结构单一，偏爱肉类食物";
              }
              else if (this.role.getSex() == 0)
              {
                this.factorStr = "有运动习惯；食量较大且饮食结构单一，偏爱肉类食物。";
                continue;
                if (this.role.getSex() == 1)
                {
                  this.factorStr = "久坐不动或以车代步；长期不运动。";
                }
                else if (this.role.getSex() == 0)
                {
                  this.factorStr = "习惯性减肥；久坐不动；长期不运动。";
                  continue;
                  if (this.weightReport.getStateCode() == 1)
                  {
                    if (this.fatReport.getIsSubHealth().booleanValue())
                      this.factorStr = "食量较少且饮食结构不合理；消化和吸收功能不太好";
                    else
                      this.factorStr = "食量较少且饮食结构单一；消化和吸收功能不太好";
                  }
                  else if (this.weightReport.getStateCode() == 2)
                  {
                    if ((this.weightReport.getIsSubHealth().booleanValue()) && (this.fatReport.getIsSubHealth().booleanValue()))
                      this.factorStr = "身体素质较好，但近期生活方式不够健康。";
                    else if ((!this.weightReport.getIsSubHealth().booleanValue()) && (this.fatReport.getIsSubHealth().booleanValue()))
                      this.factorStr = "身体素质较好，但近期生活方式不够健康。";
                    else if ((this.weightReport.getIsSubHealth().booleanValue()) && (!this.fatReport.getIsSubHealth().booleanValue()))
                      this.factorStr = "身体素质较好，但近期生活方式不够健康。";
                    else if ((!this.weightReport.getIsSubHealth().booleanValue()) && (!this.fatReport.getIsSubHealth().booleanValue()))
                      this.factorStr = "健康积极的生活方式";
                  }
                  else if (this.weightReport.getStateCode() == 3)
                    if (this.fatReport.getIsSubHealth().booleanValue())
                    {
                      this.factorStr = "身体素质还可以，但近期生活方式不够健康。";
                    }
                    else
                    {
                      this.factorStr = "比较健康的生活方式";
                      continue;
                      if (this.role.getSex() == 1)
                      {
                        this.factorStr = "有运动习惯，健康积极的生活方式。";
                      }
                      else if (this.role.getSex() == 0)
                      {
                        this.factorStr = "有运动习惯，健康积极的生活方式。";
                        continue;
                        if (this.role.getSex() == 1)
                        {
                          this.factorStr = "食量较少且饮食结构单一；较少力量训练。";
                        }
                        else if (this.role.getSex() == 0)
                        {
                          this.factorStr = "习惯性减肥和节食；较少运动。";
                          continue;
                          if (this.role.getSex() == 1)
                          {
                            this.factorStr = "食量较少且饮食结构单一。";
                          }
                          else if (this.role.getSex() == 0)
                          {
                            this.factorStr = "食量较少，有运动习惯。";
                            continue;
                            if (this.weightReport.getNum() <= 1.15D * this.idealWeight)
                            {
                              if (this.infatReport.getNum() < 8.0F)
                                this.factorStr = "经常运动，食量较大";
                              else
                                this.factorStr = "有运动习惯，但频率已大大降低；食量较大";
                            }
                            else if (this.infatReport.getNum() < 8.0F)
                            {
                              this.factorStr = "日常体力劳动或运动较多，食量较大";
                            }
                            else
                            {
                              this.factorStr = "日常体力劳动较多，不常运动；食量较大";
                              continue;
                              switch ($SWITCH_TABLE$com$picooc$model$BodyTypeEnum()[this.Rtype.ordinal()])
                              {
                              case 4:
                              case 6:
                              default:
                                break;
                              case 1:
                                this.factorStr = "久坐不动或以车代步；长期不运动；饮食偏精细。";
                                break;
                              case 2:
                                this.factorStr = "饮食结构失调；食量较大；久坐不动或以车代步；长期不运动。";
                                break;
                              case 7:
                                this.factorStr = "饮食过于清淡；进食过少；服药或疾病原因。";
                                break;
                              case 5:
                                if ((this.weightReport.getIsSubHealth().booleanValue()) && (this.fatReport.getIsSubHealth().booleanValue()))
                                  this.factorStr = "身体素质较好，但近期生活方式不够健康。";
                                else if ((!this.weightReport.getIsSubHealth().booleanValue()) && (this.fatReport.getIsSubHealth().booleanValue()))
                                  this.factorStr = "身体素质较好，但近期生活方式不够健康。";
                                else if ((this.weightReport.getIsSubHealth().booleanValue()) && (!this.fatReport.getIsSubHealth().booleanValue()))
                                  this.factorStr = "身体素质较好，但近期生活方式不够健康。";
                                else
                                  this.factorStr = "健康积极的生活方式";
                                break;
                              case 3:
                                this.factorStr = "健康积极的生活方式。";
                              }
                            }
                          }
                        }
                      }
                    }
                }
              }
            }
        }
    }
  }

  public String getImproveStr()
  {
    if (this.improveStr != null)
      return this.improveStr;
    if (this.role.getAge() <= 60)
      switch ($SWITCH_TABLE$com$picooc$model$BodyTypeEnum()[this.Rtype.ordinal()])
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      }
    while (true)
    {
      return this.improveStr;
      if (this.role.getSex() == 1)
      {
        if (this.muscleReport.getStateCode() >= 2)
          this.improveStr = "每周定量有氧运动。";
        else if (this.muscleReport.getStateCode() == 1)
          this.improveStr = "每周定量有氧运动+器械运动。";
      }
      else if (this.role.getSex() == 0)
        if (this.muscleReport.getStateCode() >= 2)
        {
          this.improveStr = "每天低强度运动+每周定量有氧运动。";
        }
        else if (this.muscleReport.getStateCode() == 1)
        {
          this.improveStr = "每周定量有氧运动+适量无氧运动。";
          continue;
          if (this.role.getSex() == 1)
          {
            if (this.fatReport.getStateCode() == 3)
            {
              if (this.infatReport.getNum() < 8.0F)
                this.improveStr = "调整饮食结构，保持每周有氧运动";
              else
                this.improveStr = "调整饮食结构，保持每周有氧运动，每天的低强度运动。";
            }
            else if (this.fatReport.getStateCode() > 3)
              this.improveStr = "调整饮食结构，每周定量有氧运动。";
          }
          else if (this.role.getSex() == 0)
            if (this.fatReport.getStateCode() == 3)
            {
              this.improveStr = "控制食量，调整饮食结构，每天低强度运动。";
            }
            else if (this.fatReport.getStateCode() > 3)
            {
              this.improveStr = "控制食量，调整饮食结构，每周定量有氧运动。";
              continue;
              if (this.role.getSex() == 1)
              {
                this.improveStr = "控制食量，调整饮食结构，每周定量有氧运动。";
              }
              else if (this.role.getSex() == 0)
              {
                this.improveStr = "控制食量，调整饮食结构，每周定量有氧运动。";
                continue;
                if (this.role.getSex() == 1)
                {
                  this.improveStr = "每天保持定量的运动，尤其是无氧或器械运动，可以增加肌肉量，提高免疫力，也让身材变得更好。";
                }
                else if (this.role.getSex() == 0)
                {
                  this.improveStr = "每天保持定量的运动，尤其是适当的无氧运动，让您的身体变紧实，线条更美，还能提高免疫力哦。";
                  continue;
                  if (this.weightReport.getStateCode() == 1)
                  {
                    if (this.role.getSex() == 1)
                      this.improveStr = "每周进行定量的无氧或器械运动，同时调整饮食结构和增加食量。让体重保持在标准范围，免疫力更强，身材也更棒。";
                    else
                      this.improveStr = "每天进行适当的无氧运动，让您的身体变紧实，线条更美，还能提高免疫力哦。";
                  }
                  else if (this.weightReport.getStateCode() == 2)
                  {
                    if ((this.weightReport.getIsSubHealth().booleanValue()) && (this.fatReport.getIsSubHealth().booleanValue()))
                      this.improveStr = "每天保持定量的低强度运动，注意规律饮食和睡眠";
                    else if ((!this.weightReport.getIsSubHealth().booleanValue()) && (this.fatReport.getIsSubHealth().booleanValue()))
                      this.improveStr = "每天保持定量的低强度运动，注意规律饮食和睡眠";
                    else if ((this.weightReport.getIsSubHealth().booleanValue()) && (!this.fatReport.getIsSubHealth().booleanValue()))
                      this.improveStr = "每天保持定量的低强度运动，注意规律饮食和睡眠";
                    else if ((!this.weightReport.getIsSubHealth().booleanValue()) && (!this.fatReport.getIsSubHealth().booleanValue()))
                      this.improveStr = "每天保持定量的低强度运动";
                  }
                  else if (this.weightReport.getStateCode() == 3)
                    if (this.fatReport.getIsSubHealth().booleanValue())
                    {
                      this.improveStr = "每天保持定量的低强度运动，注意规律饮食和睡眠";
                    }
                    else
                    {
                      this.improveStr = "每天保持定量的低强度运动，注意规律饮食和睡眠";
                      continue;
                      if (this.role.getSex() == 1)
                      {
                        this.improveStr = "每天保持定量的低强度运动。";
                      }
                      else if (this.role.getSex() == 0)
                      {
                        this.improveStr = "每天保持定量的低强度运动。";
                        continue;
                        if (this.role.getSex() == 1)
                        {
                          this.improveStr = "增加力量训练，注意饮食营养和睡眠，保证心情愉快。多食多餐，以摄取碳水化合物和蛋白质类食物为主。";
                        }
                        else if (this.role.getSex() == 0)
                        {
                          this.improveStr = "增加食量，以摄取蛋白质类食物为主，可适当摄取甜食。";
                          continue;
                          if (this.role.getSex() == 1)
                          {
                            this.improveStr = "注意饮食营养和睡眠，多食多餐，以摄取碳水化合物和蛋白质类食物为主。";
                          }
                          else if (this.role.getSex() == 0)
                          {
                            this.improveStr = "注意饮食营养和睡眠，多食多餐。";
                            continue;
                            this.improveStr = "日常只要注意饮食结构，就可以轻松地控制体重啦。";
                            continue;
                            switch ($SWITCH_TABLE$com$picooc$model$BodyTypeEnum()[this.Rtype.ordinal()])
                            {
                            case 4:
                            case 6:
                            default:
                              break;
                            case 1:
                              this.improveStr = "调整饮食结构，以粗粮为主；每天步行或低强度运动+每周适量有氧运动。";
                              break;
                            case 2:
                              this.improveStr = "调整饮食结构，每天步行至少1万步或一小时低强度运动+每周适量有氧运动。";
                              break;
                            case 7:
                              this.improveStr = "调整饮食结构，增加蛋白质和碳水化合物的比重；增加食量。";
                              break;
                            case 5:
                              if ((this.weightReport.getIsSubHealth().booleanValue()) && (this.fatReport.getIsSubHealth().booleanValue()))
                                this.improveStr = "每天保持定量的低强度运动，注意规律饮食和睡眠";
                              else if ((!this.weightReport.getIsSubHealth().booleanValue()) && (this.fatReport.getIsSubHealth().booleanValue()))
                                this.improveStr = "每天保持定量的低强度运动，注意规律饮食和睡眠";
                              else if ((this.weightReport.getIsSubHealth().booleanValue()) && (!this.fatReport.getIsSubHealth().booleanValue()))
                                this.improveStr = "每天保持定量的低强度运动，注意规律饮食和睡眠";
                              else
                                this.improveStr = "每天保持定量的低强度运动，保持心情愉快";
                              break;
                            case 3:
                              this.improveStr = "每天保持定量的低强度运动。";
                            }
                          }
                        }
                      }
                    }
                }
              }
            }
        }
    }
  }

  public String getProgramme()
  {
    return this.programme;
  }

  public BodyTypeEnum getRtype()
  {
    return this.Rtype;
  }

  public String get_fatStr()
  {
    return this._fatStr;
  }

  public String get_muscleStr()
  {
    return this._muscleStr;
  }

  public String get_weightStr()
  {
    return this._weightStr;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BodyTypeModel
 * JD-Core Version:    0.6.2
 */
