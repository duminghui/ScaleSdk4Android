package com.picooc.model;

import com.picooc.arithmetic.ReportDirect;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.RoleBin;

public class BodyScoreModel
{
  private String minScoreStr;
  private int[] scores;
  private String title_out;
  private int totalScore;

  public BodyScoreModel(RoleBin paramRoleBin, BodyIndex paramBodyIndex)
  {
    if (paramBodyIndex.getBodyFat() > 0.0F)
      this.scores = ReportDirect.caculateBodyTotalNum2(paramRoleBin, ReportDirect.judgeWeightByRole(paramRoleBin, paramBodyIndex.getWeight()), ReportDirect.judgeBodyFatByRole(paramRoleBin, paramBodyIndex), ReportDirect.judgeMuscleRaceByRole(paramRoleBin, paramBodyIndex), ReportDirect.judgeBoneMassByRole(paramRoleBin, paramBodyIndex), ReportDirect.judgeWaterRaceByRole(paramRoleBin, paramBodyIndex), ReportDirect.judgeProteinRaceByRole(paramRoleBin, paramBodyIndex), ReportDirect.judgeBmrByRole(paramRoleBin, paramBodyIndex), ReportDirect.judgeInFatByRole(paramRoleBin, paramBodyIndex));
    if (this.scores != null)
    {
      this.totalScore = this.scores[(-1 + this.scores.length)];
      if (this.totalScore < 100)
        break label131;
      this.title_out = "全项达标！还不去得瑟得瑟～";
    }
    int i;
    int j;
    while (true)
    {
      i = this.scores[1];
      this.minScoreStr = "脂肪率";
      j = 2;
      if (j < -1 + this.scores.length)
        break;
      return;
      label131: if (this.totalScore >= 90)
        this.title_out = "身体倍儿棒，跟老爸老妈汇报去吧~";
      else if (this.totalScore >= 80)
        this.title_out = "身体不错嘛，加油向90分段前进～";
      else if (this.totalScore >= 70)
        this.title_out = "中段班同学，明天开始锻炼可好？";
      else if (this.totalScore >= 60)
        this.title_out = "及格啦，还能更好一点吧？";
      else if (this.totalScore >= 45)
        this.title_out = "别灰心，注意饮食，开始运动，向及格进军！";
      else if (this.totalScore >= 25)
        this.title_out = "好捉急……咱是不是得关心一下自己身体了？";
      else
        this.title_out = "惊呆了……晒出去让朋友们喷喷吧……";
    }
    if (this.scores[j] < i)
      switch (j)
      {
      default:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      }
    while (true)
    {
      j++;
      break;
      this.minScoreStr = "肌肉率";
      continue;
      this.minScoreStr = "内脏脂肪";
      continue;
      this.minScoreStr = "基础代谢率";
      continue;
      this.minScoreStr = "水分";
      continue;
      this.minScoreStr = "蛋白质";
      continue;
      this.minScoreStr = "骨量";
    }
  }

  public int[] getScores()
  {
    return this.scores;
  }

  public String getSharedMessage()
  {
    String[] arrayOfString;
    if (this.totalScore >= 100)
    {
      arrayOfString = new String[3];
      arrayOfString[0] = "健康成绩无懈可击";
      arrayOfString[1] = "酷毙了";
      arrayOfString[2] = "求超越O(∩_∩)O~~";
    }
    while (true)
    {
      return arrayOfString[((int)(0.0D + 3.0D * Math.random()))];
      if (this.totalScore >= 90)
      {
        arrayOfString = new String[] { "身体倍棒，吃嘛嘛香~", "直逼满分的节奏啊~", "我是标兵我怕谁~" };
      }
      else if (this.totalScore >= 80)
      {
        arrayOfString = new String[] { "身体状态良好哦，还可以小小调整一下~", "保持状态，继续刷榜~", "退一步山穷水尽，进一步海阔天空~" };
      }
      else if (this.totalScore >= 70)
      {
        arrayOfString = new String[3];
        arrayOfString[0] = "敲一敲警钟～";
        arrayOfString[1] = "还不每天动起来？";
        arrayOfString[2] = (this.minScoreStr + "预警！");
      }
      else if (this.totalScore >= 60)
      {
        arrayOfString = new String[] { "及格线徘徊，要注意生活方式了~", "健康警钟大作！", "小心！前方及格线！" };
      }
      else if (this.totalScore >= 45)
      {
        arrayOfString = new String[] { "请尽快告别不良生活习惯~", "这是要闹哪样！~", "快及格了，保持信心！" };
      }
      else if (this.totalScore >= 25)
      {
        arrayOfString = new String[] { "身体状况%>_<%，求关心~", " 别灰心，努力走出健康步伐！", "烟酒腐败，请远离我~(>_<)~" };
      }
      else
      {
        arrayOfString = new String[] { "求喷 ……", "这是找抽的节奏……", "孩子，你爸你妈知道吗……" };
      }
    }
  }

  public String getTitle_out()
  {
    return this.title_out;
  }

  public int getTotalScore()
  {
    return this.totalScore;
  }

  public int isGood()
  {
    if (this.totalScore < 60)
      return -1;
    if ((this.totalScore >= 60) && (this.totalScore <= 79))
      return 0;
    return 1;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BodyScoreModel
 * JD-Core Version:    0.6.2
 */
