package com.picooc.model;

import android.graphics.Color;
import com.picooc.arithmetic.ReportDirect;
import com.picooc.domain.BodyFatReport;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.ReportEntity;
import com.picooc.domain.RoleBin;
import com.picooc.utils.NumUtils;
import java.io.Serializable;
import java.util.ArrayList;

public class BodyCompositionAnalysisModel
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private ReportEntity _bmrReport;
  private ReportEntity _boneMassReport;
  private ReportEntity _fatReport;
  private ReportEntity _infatReport;
  private ReportEntity _muscleReport;
  private int _noStandardNum;
  private ReportEntity _proteinReport;
  private int _standardNum;
  private ReportEntity _waterReport;
  private ReportEntity _weightReport;
  private BodyIndex body;
  private BodyTypeEnum bodyType;
  private int cycleColor;
  private int cycleColorShawdon;
  private BodyFatReport dir;
  private RoleBin role;
  private int subHealthCount;
  private int totalScore;
  private String totalString;

  public BodyCompositionAnalysisModel(RoleBin paramRoleBin, BodyIndex paramBodyIndex, ArrayList<ReportEntity> paramArrayList, BodyTypeEnum paramBodyTypeEnum)
  {
    this.role = paramRoleBin;
    this.body = paramBodyIndex;
    this.bodyType = paramBodyTypeEnum;
    this._weightReport = ((ReportEntity)paramArrayList.get(0));
    this._fatReport = ((ReportEntity)paramArrayList.get(1));
    this._muscleReport = ((ReportEntity)paramArrayList.get(2));
    this._waterReport = ((ReportEntity)paramArrayList.get(3));
    this._bmrReport = ((ReportEntity)paramArrayList.get(4));
    this._infatReport = ((ReportEntity)paramArrayList.get(5));
    this._boneMassReport = ((ReportEntity)paramArrayList.get(6));
    this._proteinReport = ((ReportEntity)paramArrayList.get(7));
    this.dir = ReportDirect.calculateIdealBodyFat(paramRoleBin, paramBodyIndex, false);
    judgeStandardNum();
    analysisWeightByRole();
    analysisBodyFatByRole();
    analysisMuscleByRole();
    analysisWaterByRole();
    analysisBoneMassByRole();
    analysisProteinByRole();
    analysisInFatByRole();
    analysisBmrByRole();
    initSubHealthNum();
    this.totalScore = ReportDirect.caculateBodyTotalNum2(paramRoleBin, this._weightReport, this._fatReport, this._muscleReport, this._boneMassReport, this._waterReport, this._proteinReport, this._bmrReport, this._infatReport)[8];
    getTotalStringAndCycleColor();
  }

  private void analysisBmrByRole()
  {
    if (this._bmrReport.getStateCode() <= 1);
    for (String str = "您的标准基础代谢值为" + NumUtils.roundFloatToInt(this._bmrReport.getNum2()) + "kcal/天，目前您已达标。保持基础代谢率最有效的方式就是每天都进行一些低强度的运动，请参照运动方案每天动一动哦。"; ; str = "您的标准基础代谢值为" + NumUtils.roundFloatToInt(this._bmrReport.getNum2()) + "kcal/天，目前您还未达标。提高基础代谢率最有效的方式就是运动，请参照运动方案开始运动，提高身体代谢和消耗热量的能力吧～")
    {
      this._bmrReport.setMessages(new String[] { "基础代谢率是指人体在非活动状态下，维持生命所需的最低能量。基础代谢率越高，您每天所消耗的热量就越多，也就越不容易发胖。", str });
      return;
    }
  }

  private void analysisBodyFatByRole()
  {
    String str1 = ReportDirect.getBodyFatExMessageByRole(this.role, this.body);
    String str2 = ReportDirect.getBodyFatExMessage2ByRole(this.role, this.body);
    if (this.role.getAge() <= 60)
    {
      this._fatReport.setMessages(new String[] { str1, str2 });
      this._fatReport.setRegionArray(this.dir.getRaceArray());
    }
    while (true)
    {
      if (this._fatReport.getIsSubHealth().booleanValue())
        this._fatReport.setSubHealthMsg("您的脂肪率虽然在标准范围内，但濒临超标啦。一顿油腻的大餐或长时间的静坐不动都可能让您的体脂肪立刻进入到超标范围。要保持标准脂肪率，告别健康警示状态，可要注意健康的生活方式哦。");
      return;
      this._fatReport.setMessages(new String[] { str1 });
      this._fatReport.setRegionArray(this.dir.getRaceArray());
    }
  }

  private void analysisBoneMassByRole()
  {
    if (this.role.getAge() > 60)
    {
      if (this._boneMassReport.getStateCode() <= 1)
      {
        this._boneMassReport.setMessages(new String[] { "您的骨量水平偏低。骨量会随着衰老自然流失，长期接受类固醇、抗痉挛药、利尿剂、止痛药等治疗者骨量流失更快。这会导致您腰背疼痛、驼背、易骨折等症状。", "由于人体衰老的原因，肠钙吸收能力减弱，单纯补钙对骨量流失的补益程度很低。一定要在增加运动量的基础上联合补充钙质和维生素D，才能对骨量的提高产生作用。" });
        return;
      }
      if (this._boneMassReport.getStateCode() >= 3)
      {
        this._boneMassReport.setMessages(new String[] { "您的骨量水平偏高，这说明您的生活习惯比较健康，营养摄入合理。骨量会随着衰老自然流失，您只要保证健康的饮食和适当的锻炼，就可以维持稳定健康的骨量水平啦。" });
        return;
      }
      this._boneMassReport.setMessages(new String[] { "您的骨量水平标准。骨量会随着衰老自然流失，您需要保证健康的饮食和合理的锻炼，就可以维持稳定健康的骨量水平啦。" });
      return;
    }
    if (this._boneMassReport.getStateCode() <= 1)
    {
      if (this.role.getSex() == 1);
      for (String str = "您的骨量水平偏低，这会导致腰背疼痛、驼背、易骨折等症状。注意控制酒精、咖啡和浓茶的摄入，这些都会加速骨头里钙质的流失。"; ; str = "您的骨量水平偏低，这会导致腰背疼痛、驼背、易骨折等症状。盲目节食的减肥可能导致内分泌紊乱，从而使体内雌激素的分泌大大降低，造成骨溶解加强，骨形成减少，引起骨质疏松。")
      {
        this._boneMassReport.setMessages(new String[] { str, "人体对钙的吸收率很低，您需要摄入更多钙含量较高的食物。长期以车代步、过度防晒也会造成骨量逐渐流失，请参照运动方案和饮食与营养调整自己的生活方式。" });
        return;
      }
    }
    if (this._boneMassReport.getStateCode() >= 3)
    {
      this._boneMassReport.setMessages(new String[] { "您的骨量水平偏高，这说明您的生活习惯比较健康，营养摄入合理。由于骨量在短期内不会出现明显的变化，您只要保证健康的饮食和适当的锻炼，就可以维持稳定健康的骨量水平啦。" });
      return;
    }
    this._boneMassReport.setMessages(new String[] { "您的骨量水平标准。骨量在短期内不会出现明显的变化，您只要保证健康的饮食和适当的锻炼，就可以维持稳定健康的骨量水平啦。" });
  }

  private void analysisInFatByRole()
  {
    if (this._infatReport.getIsSubHealth().booleanValue())
      this._infatReport.setSubHealthMsg("您的内脏脂肪指数虽然在标准范围内，但濒临超标啦。长时间静坐不动，过多食用高卡路里、高脂肪、高盐分的食物，以及贪吃夜宵和零食都会使您的内脏脂肪指数进入到超标范围。要保持标准的内脏脂肪指数，告别健康警示状态，可要注意健康的生活方式哦。");
    if (!this._infatReport.getIsSubHealth().booleanValue())
    {
      String str;
      if (this.role.getAge() > 60)
        if (this._infatReport.getStateCode() <= 1)
          str = "内脏脂肪水平正常，请继续保持健康的饮食和适当的运动！";
      while (true)
      {
        this._infatReport.setMessages(new String[] { "内脏脂肪指数反映您内脏脂肪堆积的程度。保持标准的内脏脂肪指数，可以大幅降低您心脏病、高血压、高血脂和2型糖尿病的发病风险。", str });
        return;
        if (this._infatReport.getStateCode() == 2)
        {
          if ((this.bodyType == BodyTypeEnum.JRXFP) || (this.bodyType == BodyTypeEnum.QFDLX))
            str = "体型看起来正常，但内脏脂肪已经开始堆积。请改变动得少、摄入营养过剩的生活状态，并参照运动方案和饮食和营养调整自己的生活方式。";
          else
            str = "内脏脂肪水平偏高，要警惕脂肪肝、高血脂等疾病啦。请改变动得少、摄入营养过剩的生活状态，并参照运动方案和饮食和营养调整自己的生活方式。";
        }
        else if ((this.bodyType == BodyTypeEnum.JRXFP) || (this.bodyType == BodyTypeEnum.QFDLX))
        {
          str = "体型看起来正常，但内脏脂肪水平已经过高，很可能患有脂肪肝、高血脂等疾病，建议去医院诊查。请马上开始参照运动方案和饮食和营养调整自己的生活方式。";
        }
        else
        {
          str = "内脏脂肪水平过高，很可能患有脂肪肝、高血脂等疾病，建议去医院诊查。请马上开始参照运动方案和饮食和营养调整自己的生活方式。";
          continue;
          if (this._infatReport.getStateCode() <= 1)
            str = "内脏脂肪水平正常，请继续保持健康的饮食和适当的运动！";
          else if (this._infatReport.getStateCode() <= 2)
          {
            if (this.bodyType.getIndex() >= 4)
              str = "体型看起来正常，但内脏脂肪已经开始堆积。请改变久坐不动、经常熬夜等不良生活和工作习惯，并参照运动方案和饮食与营养调整自己的生活方式。";
            else
              str = "内脏脂肪水平偏高，要警惕脂肪肝、高血脂等疾病啦。请改变久坐不动、经常熬夜等不良生活和工作习惯，并参照运动方案和饮食与营养调整自己的生活方式。";
          }
          else if (this.bodyType.getIndex() >= 4)
            str = "体型看起来正常，但内脏脂肪水平已经过高，很可能患有脂肪肝、高血脂等疾病，建议去医院诊查。请马上开始参照运动方案和饮食与营养调整自己的生活方式。";
          else
            str = "内脏脂肪水平过高，很可能患有脂肪肝、高血脂等疾病，建议去医院诊查。请马上开始参照运动方案和饮食与营养调整自己的生活方式。";
        }
      }
    }
    this._infatReport.setMessages(new String[] { "内脏脂肪指数反映您内脏脂肪堆积的程度。保持标准的内脏脂肪指数，可以大幅降低您心脏病、高血压、高血脂和2型糖尿病的发病风险。" });
  }

  private void analysisMuscleByRole()
  {
    if (this._muscleReport.getIsSubHealth().booleanValue())
      this._muscleReport.setSubHealthMsg("您的肌肉率虽然在标准范围内，但濒临偏低啦。长时间的静坐不动和缺乏运动都可能让您的肌肉量立刻进入到偏低范围。要保持标准肌肉率，告别健康警示状态，可要注意健康的生活方式哦。");
    if (this.role.getAge() > 60)
    {
      this._muscleReport.setMessages(new String[] { "肌肉是您身体的“生命发动机“。肌肉衰弱首先累及心脏，成为诱发心血管病的导火线；还会使人体基础代谢率降低，形成肥胖；更会加重关节负担，产生关节痛。" });
      return;
    }
    String str;
    if (this._muscleReport.getStateCode() <= 1)
      if (this._proteinReport.getStateCode() <= 1)
        str = "目前您的肌肉量偏低，蛋白质也不足，主要原因是运动量过少和摄入营养不足。请参照运动方案和饮食和营养调整自己的生活方式。";
    while (true)
    {
      this._muscleReport.setMessages(new String[] { "肌肉是您身体消耗能量的主力军。增加肌肉量能让您更快地消耗热量，以最健康的方式减掉多余脂肪和体重。", str });
      return;
      str = "目前您的肌肉量偏低，蛋白质比较充足，主要原因是运动量过少。请继续保持合理饮食，并参照运动方案调整自己的生活方式。";
      continue;
      if (this._muscleReport.getStateCode() == 2)
      {
        if (this._proteinReport.getStateCode() <= 1)
          str = "目前您的肌肉量在标准范围内，但蛋白质偏低，请继续保持适当的运动量，并加大膳食结构中蛋白质的比例。运动量过少和节食是肌肉流失的主要原因。";
        else
          str = "目前您的肌肉量在标准范围内，请继续保持适当的运动量和合理的饮食。运动量过少和节食是肌肉流失的主要原因。";
      }
      else if (this._proteinReport.getStateCode() <= 1)
        str = "目前您的肌肉量比较充足，但蛋白质偏低，请继续保持适当的运动量，并加大膳食结构中蛋白质的比例。";
      else
        str = "目前您的肌肉量比较充足，请继续保持适当的运动量和合理的饮食。";
    }
  }

  private void analysisProteinByRole()
  {
    if (this.role.getAge() > 60)
    {
      if (this._proteinReport.getStateCode() <= 1)
      {
        this._proteinReport.setMessages(new String[] { "蛋白质水平偏低，这会导致您免疫力低下、肌肉无力，还可能出现贫血和水肿。由于身体无法合成一些关键的载体蛋白和激素，更可能增加血管中胆固醇沉积和患上糖尿病的风险。另外，激素类药物会大量消耗体内蛋白质，如果您正在接受此类治疗，请注意大量补充蛋白质类食物。", "老年人对蛋白质分解代谢增强，对蛋白质的利用能力降低，所以在日常饮食中需要比年轻人多摄取10-15%的蛋白质。请参照饮食和营养开始调整自己的饮食。" });
        return;
      }
      if (this._proteinReport.getStateCode() == 3)
      {
        this._proteinReport.setMessages(new String[] { "蛋白质水平偏高，说明您的营养摄入非常充分。但摄取过高的蛋白质会引发肾脏疾病，请您注意平衡膳食，多食用一些高纤维蔬菜。" });
        return;
      }
      this._proteinReport.setMessages(new String[] { "蛋白质水平标准。缺乏蛋白质会引起免疫力下降、肌肉无力和贫血。您只要保证健康的饮食，不过分节食，就可以维持稳定的蛋白质水平啦。" });
      return;
    }
    if (this._proteinReport.getStateCode() <= 1)
    {
      if (this.role.getSex() == 1);
      for (String str2 = "蛋白质水平偏低，这会导致您免疫力低下、头发干枯和脱发、记忆力减退、肌肉无力、容易疲惫。由于身体无法合成一些关键的载体蛋白和激素，更可能增加血管中胆固醇沉积和患上糖尿病的风险。"; ; str2 = "您身体中的蛋白质水平偏低，这会导致您免疫力低下、头发干枯和脱发、皮肤出现皱纹、贫血和水肿，也更容易感到疲惫。")
      {
        this._proteinReport.setMessages(new String[] { str2, "提高身体中蛋白质水平最有效的方式就是通过日常膳食补充。请参照饮食与营养开始调整自己的饮食。" });
        return;
      }
    }
    if (this._proteinReport.getStateCode() == 3)
    {
      if (this.bodyType == BodyTypeEnum.PSX);
      for (String str1 = "蛋白质水平偏高，您当前的营养摄入比较充分。但您的体型偏瘦，说明身体代谢较快，身体组织增加较困难。请您继续保持蛋白质摄入。"; ; str1 = "蛋白质水平偏高，说明您的营养摄入比较充分。请您同时注意平衡膳食，多食用一些高纤维蔬菜。")
      {
        this._proteinReport.setMessages(new String[] { str1 });
        return;
      }
    }
    this._proteinReport.setMessages(new String[] { "蛋白质水平标准。缺乏蛋白质会引起免疫力下降、肌肉无力和贫血。您只要保证健康的饮食，不过分节食，就可以维持稳定的蛋白质水平啦。" });
  }

  private void analysisWaterByRole()
  {
    String str;
    if (this.role.getAge() > 60)
      if (this._waterReport.getStateCode() == 1)
        str = "身体循环不畅。老年人由于知觉较为迟钝，少有口渴感觉，常无法正确判断身体内缺水的情形。当体内水分不足、摄入量太少，就会导致脑血流量降低，使高血压症状加剧，脑含氧及血糖量低，脑脊髓液太黏稠，影响神经传导速度，及大脑活动敏锐与灵敏性。请保证规律的饮食习惯和每天至少6杯水。";
    while (true)
    {
      this._waterReport.setMessages(new String[] { str });
      return;
      if (this._waterReport.getStateCode() == 2)
      {
        str = "身体循环比较正常，规律的饮食习惯和每天6杯水就可以维持正常的体水分水平啦。身体里充足的水分能帮助促进食物的消化吸收和血液流动，还能促进代谢，帮助稀释和排除毒素如尿酸。";
      }
      else
      {
        str = "身体循环正常。身体里充足的水分能帮助促进食物的消化吸收和血液流动，还能促进代谢，帮助稀释和排除毒素如尿酸。";
        continue;
        if (this._waterReport.getStateCode() == 1)
          str = "身体循环不畅，这标示着您的体脂肪水平很可能较高。请严格控制对高盐和高脂肪食物的摄入量，同时增加运动量。身体里充足的水分能促进代谢，带走废物和毒素。";
        else if (this._waterReport.getStateCode() == 2)
          str = "身体循环比较正常，规律的饮食习惯和每天八杯水就可以维持正常的体水分水平啦。身体里充足的水分能帮助您更好地消化食物和吸收养分，并促进代谢，带走废物和毒素。";
        else
          str = "身体循环正常。身体里充足的水分能帮助您更好地消化食物和吸收养分，并促进代谢，带走废物和毒素。";
      }
    }
  }

  private void analysisWeightByRole()
  {
    float f = this._weightReport.getNum2();
    if (this.role.getAge() <= 60);
    switch ($SWITCH_TABLE$com$picooc$model$BodyTypeEnum()[this.bodyType.ordinal()])
    {
    case 4:
    case 6:
    case 7:
    case 8:
    default:
      if (this._weightReport.getIsSubHealth().booleanValue())
      {
        if (this.role.getSex() != 1)
          break label597;
        this._weightReport.setSubHealthMsg("您的体重虽然在标准范围内，但濒临超标啦，属于健康警示状态。饮食不稳定和作息混乱都可能让您的体重立刻进入到超标范围。要保持标准体重，告别健康警示区间，可要注意健康的生活方式哦。");
      }
      break;
    case 2:
    case 3:
    case 5:
    case 9:
    }
    while (true)
    {
      this._weightReport.setMessages(ReportDirect.getWeightMessageByRole(this.role, this.body, this._weightReport, this.dir));
      return;
      if ((this.body.getWeight() > 1.15D * f) && (this.dir.getRealFatState() == 3))
      {
        this._weightReport.setWeightState(2);
        this._weightReport.setWeightAnchorFlag(4);
        this._weightReport.setState("严重超标");
        break;
      }
      if ((this.body.getWeight() > 1.1D * f) && (this.body.getWeight() <= 1.15D * f) && (this.dir.getRealFatState() > 3))
      {
        this._weightReport.setWeightState(3);
        this._weightReport.setWeightAnchorFlag(3);
        this._weightReport.setState("略微超标");
        break;
      }
      if ((this.body.getWeight() <= 1.15D * f) || (this.dir.getRealFatState() <= 3))
        break;
      this._weightReport.setWeightState(3);
      this._weightReport.setWeightAnchorFlag(4);
      this._weightReport.setState("严重超标");
      break;
      if ((this.body.getWeight() <= 1.15D * f) || (this.dir.getRealFatState() != 3))
        break;
      this._weightReport.setWeightState(2);
      this._weightReport.setWeightAnchorFlag(4);
      this._weightReport.setState("严重超标");
      break;
      if ((this.body.getWeight() <= 1.1D * f) || (this.body.getWeight() > 1.15D * f) || (this.dir.getRealFatState() != 3))
        break;
      this._weightReport.setWeightState(2);
      this._weightReport.setWeightAnchorFlag(3);
      this._weightReport.setState("略微超标");
      break;
      if ((this.body.getWeight() > 1.15D * f) && (this.dir.getRealFatState() <= 3))
      {
        this._weightReport.setWeightState(2);
        this._weightReport.setWeightAnchorFlag(4);
        this._weightReport.setState("严重超标");
        break;
      }
      if ((this.body.getWeight() <= 1.1D * f) || (this.body.getWeight() > 1.15D * f) || (this.dir.getRealFatState() >= 3))
        break;
      this._weightReport.setWeightState(2);
      this._weightReport.setWeightAnchorFlag(3);
      this._weightReport.setState("略微超标");
      break;
      label597: this._weightReport.setSubHealthMsg("您的体重虽然在标准范围内，但濒临超标啦，属于健康警示状态。饮食不稳定、作息混乱、生理期都可能让您的体重立刻进入到超标范围。要保持标准体重，告别健康警示状态，可要注意健康的生活方式哦。");
    }
  }

  private void getTotalStringAndCycleColor()
  {
    if (this.totalScore >= 100)
    {
      this.totalString = "身体非常健康！\n请继续保持您目前的生活方式～";
      this.cycleColor = Color.argb(255, 112, 250, 0);
      this.cycleColorShawdon = Color.argb(179, 112, 250, 0);
      return;
    }
    if ((this.totalScore >= 80) && (this.totalScore < 100))
    {
      this.totalString = "身体比较健康，还有提升空间哦～请参照运动方案和饮食与营养调整您的生活方式。";
      this.cycleColor = Color.argb(255, 198, 255, 0);
      this.cycleColorShawdon = Color.argb(179, 198, 255, 0);
      return;
    }
    if ((this.totalScore >= 60) && (this.totalScore < 80))
    {
      this.totalString = "健康状况一般，精力和体力水平正在下降。请立刻参照运动方案和饮食与营养调整生活方式。";
      this.cycleColor = Color.argb(255, 255, 234, 0);
      this.cycleColorShawdon = Color.argb(179, 255, 234, 0);
      return;
    }
    if ((this.totalScore >= 40) && (this.totalScore < 60))
    {
      this.totalString = "健康状况较差，精力和体力水平很低，患病风险很高。请立刻参照运动方案和饮食与营养调整生活方式。";
      this.cycleColor = Color.argb(255, 255, 84, 0);
      this.cycleColorShawdon = Color.argb(179, 255, 84, 0);
      return;
    }
    this.totalString = "健康状况很差，建议去医院进行全面检查。并立刻参照运动方案和饮食与营养调整生活方式。";
    this.cycleColor = Color.argb(255, 255, 0, 0);
    this.cycleColorShawdon = Color.argb(179, 255, 0, 0);
  }

  private void initSubHealthNum()
  {
    this.subHealthCount = 0;
    if (this._weightReport.getIsSubHealth().booleanValue())
      this.subHealthCount = (1 + this.subHealthCount);
    if (this._fatReport.getIsSubHealth().booleanValue())
      this.subHealthCount = (1 + this.subHealthCount);
    if (this._muscleReport.getIsSubHealth().booleanValue())
      this.subHealthCount = (1 + this.subHealthCount);
    if (this._infatReport.getIsSubHealth().booleanValue())
      this.subHealthCount = (1 + this.subHealthCount);
  }

  private void judgeStandardNum()
  {
    if (this._weightReport.getStandardOrNot().booleanValue())
    {
      this._standardNum = (1 + this._standardNum);
      if (!this._fatReport.getStandardOrNot().booleanValue())
        break label198;
      this._standardNum = (1 + this._standardNum);
      label46: if (!this._muscleReport.getStandardOrNot().booleanValue())
        break label211;
      this._standardNum = (1 + this._standardNum);
      label69: if (!this._waterReport.getStandardOrNot().booleanValue())
        break label224;
      this._standardNum = (1 + this._standardNum);
      label92: if (!this._proteinReport.getStandardOrNot().booleanValue())
        break label237;
      this._standardNum = (1 + this._standardNum);
      label115: if (!this._boneMassReport.getStandardOrNot().booleanValue())
        break label250;
      this._standardNum = (1 + this._standardNum);
      label138: if (!this._infatReport.getStandardOrNot().booleanValue())
        break label263;
      this._standardNum = (1 + this._standardNum);
    }
    while (true)
    {
      if (!this._bmrReport.getStandardOrNot().booleanValue())
        break label276;
      this._standardNum = (1 + this._standardNum);
      return;
      this._noStandardNum = (1 + this._noStandardNum);
      break;
      label198: this._noStandardNum = (1 + this._noStandardNum);
      break label46;
      label211: this._noStandardNum = (1 + this._noStandardNum);
      break label69;
      label224: this._noStandardNum = (1 + this._noStandardNum);
      break label92;
      label237: this._noStandardNum = (1 + this._noStandardNum);
      break label115;
      label250: this._noStandardNum = (1 + this._noStandardNum);
      break label138;
      label263: this._noStandardNum = (1 + this._noStandardNum);
    }
    label276: this._noStandardNum = (1 + this._noStandardNum);
  }

  public ArrayList<ReportEntity> getBodyCompositionAnalysisArray()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(this._weightReport);
    localArrayList.add(this._fatReport);
    localArrayList.add(this._muscleReport);
    localArrayList.add(this._infatReport);
    localArrayList.add(this._bmrReport);
    localArrayList.add(this._waterReport);
    localArrayList.add(this._proteinReport);
    localArrayList.add(this._boneMassReport);
    return localArrayList;
  }

  public ArrayList<Object> getBodyCompositionData()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(Integer.valueOf(1));
    localArrayList.add(Integer.valueOf(2));
    localArrayList.add(Integer.valueOf(3));
    int i = 3;
    if (!this._boneMassReport.getStandardOrNot().booleanValue())
    {
      localArrayList.add(1, this._boneMassReport);
      i++;
      if (this._proteinReport.getStandardOrNot().booleanValue())
        break label255;
      localArrayList.add(1, this._proteinReport);
      i++;
      label88: if (this._waterReport.getStandardOrNot().booleanValue())
        break label268;
      localArrayList.add(1, this._waterReport);
      i++;
      label113: if (this._bmrReport.getStandardOrNot().booleanValue())
        break label281;
      localArrayList.add(1, this._bmrReport);
      i++;
      label138: if (this._infatReport.getStandardOrNot().booleanValue())
        break label294;
      localArrayList.add(1, this._infatReport);
      i++;
      label163: if (this._muscleReport.getStandardOrNot().booleanValue())
        break label307;
      localArrayList.add(1, this._muscleReport);
      i++;
      label188: if (this._fatReport.getStandardOrNot().booleanValue())
        break label320;
      localArrayList.add(1, this._fatReport);
      i++;
    }
    while (true)
    {
      if (this._weightReport.getStandardOrNot().booleanValue())
        break label333;
      localArrayList.add(1, this._weightReport);
      (i + 1);
      return localArrayList;
      localArrayList.add(i, this._boneMassReport);
      break;
      label255: localArrayList.add(i, this._proteinReport);
      break label88;
      label268: localArrayList.add(i, this._waterReport);
      break label113;
      label281: localArrayList.add(i, this._bmrReport);
      break label138;
      label294: localArrayList.add(i, this._infatReport);
      break label163;
      label307: localArrayList.add(i, this._muscleReport);
      break label188;
      label320: localArrayList.add(i, this._fatReport);
    }
    label333: localArrayList.add(i, this._weightReport);
    return localArrayList;
  }

  public int getCycleColor()
  {
    return this.cycleColor;
  }

  public int getCycleColorShawdon()
  {
    return this.cycleColorShawdon;
  }

  public int getSubHealthCount()
  {
    return this.subHealthCount;
  }

  public int getTotalScore()
  {
    return this.totalScore;
  }

  public String getTotalString()
  {
    return this.totalString;
  }

  public int get_noStandardNum()
  {
    return this._noStandardNum;
  }

  public int get_standardNum()
  {
    return this._standardNum;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BodyCompositionAnalysisModel
 * JD-Core Version:    0.6.2
 */
