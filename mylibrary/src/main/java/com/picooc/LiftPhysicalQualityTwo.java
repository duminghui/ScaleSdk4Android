package com.picooc;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableStringBuilder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.adapter.LevelShowView;
import com.picooc.arithmetic.BodyCompositionSectionGlobal;
import com.picooc.domain.ComponentBodyTypeEnum;
import com.picooc.domain.ReportEntity;
import com.picooc.guide.GuideAct;
import com.picooc.guide.GuideModel;
import com.picooc.model.BodyCompositionAnalysisModel;
import com.picooc.oldhumen.Age;
import com.picooc.utils.NumUtils;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.widget.ViewExpandAnimation;
import com.picooc.widget.picoocProgress.ArcProgress;
import com.picooc.widget.widgetAddView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.achartengine.tools.ModUtils;

public class LiftPhysicalQualityTwo extends PicoocActivity
  implements View.OnClickListener
{
  ArrayList<Object> data;
  private int fatWight = 123;
  View isOpenView = null;
  private LinearLayout linearList;
  private View listView;
  ArrayList<LinearLayout> listiten = new ArrayList();
  private int[] locImageView;
  private float mDensity = 0.0F;
  private GuideModel mGuide;
  private List<GuideModel> mGuideList;
  private BodyCompositionAnalysisModel model;
  private int nowWidth = 480;
  private int tatePading = 10;
  private LinearLayout tempLayout;
  private View view;
  widgetAddView wigeht;

  private void initBMR(ViewHolder paramViewHolder, ReportEntity paramReportEntity, View paramView)
  {
    paramView.setVisibility(0);
    paramViewHolder.hengtiaoRelative.setVisibility(8);
    paramViewHolder.textState.setVisibility(0);
    paramViewHolder.shuzhi.setVisibility(0);
    paramViewHolder.hengtiao_bg.setBackgroundResource(2130838218);
    paramViewHolder.liftImage.setImageResource(BodyCompositionSectionGlobal.getBmrLeftImage(paramReportEntity.getStandardOrNot()));
    paramViewHolder.tvTitle.setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getBmrStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setBackgroundResource(BodyCompositionSectionGlobal.getBmrStateImage(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setPadding(this.tatePading, 0, this.tatePading, 0);
    paramViewHolder.textline.setVisibility(8);
    paramViewHolder.linear.setClickable(true);
    paramViewHolder.linear.setEnabled(true);
    paramViewHolder.shuzhi.setText(NumUtils.roundFloatToInt(paramReportEntity.getNum()) + "kcal");
    String[] arrayOfString = paramReportEntity.getMessages();
    int i = arrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      String str = arrayOfString[j];
      if ((str != null) && (!str.equals("")))
        this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  private void initBodyFat(ViewHolder paramViewHolder, ReportEntity paramReportEntity, View paramView)
  {
    paramView.setVisibility(0);
    this.fatWight = ((-24 + (this.nowWidth - ModUtils.dip2px(this, 50.0F))) / 5);
    paramViewHolder.hengtiaoRelative.setVisibility(0);
    paramViewHolder.shuzhi.setVisibility(0);
    paramViewHolder.textState.setVisibility(0);
    paramViewHolder.translateImage.setImageResource(BodyCompositionSectionGlobal.getFatMarkeImage(paramReportEntity.getStateCode()));
    paramViewHolder.hengtiao_bg.setBackgroundResource(2130838218);
    paramViewHolder.liftImage.setImageResource(BodyCompositionSectionGlobal.getFatLeftImage(paramReportEntity.getStandardOrNot()));
    paramViewHolder.tvTitle.setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getFatStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setBackgroundResource(BodyCompositionSectionGlobal.getFatStateImage(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setPadding(this.tatePading, 0, this.tatePading, 0);
    String[] arrayOfString1 = BodyCompositionSectionGlobal.FAT;
    float[] arrayOfFloat = paramReportEntity.getRegionArray();
    paramViewHolder.textOne.setText(NumUtils.roundValue(arrayOfFloat[1]) + "%");
    paramViewHolder.textTwo.setText(NumUtils.roundValue(arrayOfFloat[2]) + "%");
    paramViewHolder.textThree.setText(NumUtils.roundValue(arrayOfFloat[3]) + "%");
    paramViewHolder.textFour.setText(NumUtils.roundValue(arrayOfFloat[4]) + "%");
    paramViewHolder.textOneBottom.setText(arrayOfString1[0]);
    paramViewHolder.textTwoBottom.setText(arrayOfString1[1]);
    paramViewHolder.textThreeBottom.setText(arrayOfString1[2]);
    paramViewHolder.textFourBottom.setText(arrayOfString1[3]);
    paramViewHolder.textFiveBottom.setText(arrayOfString1[4]);
    paramViewHolder.textOne.setVisibility(0);
    paramViewHolder.textTwo.setVisibility(0);
    paramViewHolder.textThree.setVisibility(0);
    paramViewHolder.textFour.setVisibility(0);
    paramViewHolder.textOneBottom.setVisibility(0);
    paramViewHolder.textTwoBottom.setVisibility(0);
    paramViewHolder.textThreeBottom.setVisibility(0);
    paramViewHolder.textFourBottom.setVisibility(0);
    paramViewHolder.textFiveBottom.setVisibility(0);
    setLaoutPam(paramViewHolder.translateImage, 25 + this.fatWight * (-1 + paramReportEntity.getStateCode()) + this.fatWight / 2, 0, 2);
    setLaoutPam(paramViewHolder.textOne, 15 + this.fatWight, 0.0F, 2);
    setLaoutPam(paramViewHolder.textTwo, 15 + 2 * this.fatWight, 0.0F, 2);
    setLaoutPam(paramViewHolder.textThree, 15 + 3 * this.fatWight, 0.0F, 2);
    setLaoutPam(paramViewHolder.textFour, 15 + 4 * this.fatWight, 0.0F, 2);
    setLaoutPamBotton(paramViewHolder.textOneBottom, 23 + this.fatWight / 2, 0.0F, 2);
    setLaoutPamBotton(paramViewHolder.textTwoBottom, 17 + this.fatWight + this.fatWight / 2, 2.0F, 0);
    setLaoutPamBotton(paramViewHolder.textThreeBottom, 19 + 2 * this.fatWight + this.fatWight / 2, 2.0F, 0);
    setLaoutPamBotton(paramViewHolder.textFourBottom, 19 + 3 * this.fatWight + this.fatWight / 2, 2.0F, 0);
    setLaoutPamBotton(paramViewHolder.textFiveBottom, 19 + 4 * this.fatWight + this.fatWight / 2, 2.0F, 0);
    paramViewHolder.textline.setVisibility(8);
    paramViewHolder.linear.setClickable(true);
    paramViewHolder.linear.setEnabled(true);
    paramViewHolder.shuzhi.setText(NumUtils.roundValue(paramReportEntity.getNum()) + "%");
    if (paramReportEntity.getIsSubHealth().booleanValue())
    {
      paramViewHolder.jingshiImage.setVisibility(0);
      this.wigeht.addviewYajiank(paramViewHolder.LinearLayout, paramReportEntity.getSubHealthMsg(), 30, 0);
    }
    String[] arrayOfString2 = paramReportEntity.getMessages();
    int i = arrayOfString2.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      String str = arrayOfString2[j];
      if ((str != null) && (!str.equals("")))
        this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  private View initBodyFatOld(ReportEntity paramReportEntity)
  {
    View localView = LayoutInflater.from(this).inflate(2130903085, null);
    ImageView localImageView = (ImageView)localView.findViewById(2131099702);
    int i = BodyCompositionSectionGlobal.getInfatLeftImage(paramReportEntity.getStandardOrNot());
    localImageView.setImageResource(i);
    System.out.println("resId =" + i);
    ((TextView)localView.findViewById(2131099728)).setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
    ((TextView)localView.findViewById(2131099730)).setText(BodyCompositionSectionGlobal.getFatStateName(paramReportEntity.getStateCode()));
    ((TextView)localView.findViewById(2131099729)).setTag(NumUtils.roundValue(paramReportEntity.getNum()) + "%");
    LevelShowView localLevelShowView = (LevelShowView)localView.findViewById(2131099930);
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    localLevelShowView.setMinimumHeight(100);
    localLevelShowView.noIndicator();
    localLevelShowView.resText(30);
    localLevelShowView.setCurrentLevel(paramReportEntity.getNum());
    ((View)localLevelShowView.getParent()).setVisibility(0);
    LinearLayout localLinearLayout = (LinearLayout)localView.findViewById(2131099932);
    String[] arrayOfString = paramReportEntity.getMessages();
    int j = arrayOfString.length;
    for (int k = 0; ; k++)
    {
      if (k >= j)
      {
        localLinearLayout.setVisibility(0);
        return localView;
      }
      String str = arrayOfString[k];
      if ((str != null) && (!str.equals("")))
        this.wigeht.addview(localLinearLayout, str, 30, 0);
    }
  }

  private void initBodyFatOld(ViewHolder paramViewHolder, ReportEntity paramReportEntity, View paramView)
  {
    paramView.setVisibility(0);
    this.fatWight = ((-24 + (this.nowWidth - ModUtils.dip2px(this, 50.0F))) / 3);
    paramViewHolder.shuzhi.setVisibility(0);
    paramViewHolder.textState.setVisibility(0);
    paramViewHolder.hengtiaoRelative.setVisibility(0);
    paramViewHolder.translateImage.setImageResource(BodyCompositionSectionGlobal.OldFAT_MARKEIMAGE(-1 + paramReportEntity.getStateCode()));
    paramViewHolder.hengtiao_bg.setBackgroundResource(2130838196);
    paramViewHolder.liftImage.setImageResource(BodyCompositionSectionGlobal.getFatLeftImage(paramReportEntity.getStandardOrNot()));
    paramViewHolder.tvTitle.setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getFatStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setBackgroundResource(BodyCompositionSectionGlobal.getOldfat_image(-1 + paramReportEntity.getStateCode()));
    paramViewHolder.textState.setPadding(this.tatePading, 0, this.tatePading, 0);
    String[] arrayOfString1 = BodyCompositionSectionGlobal.FAT;
    float[] arrayOfFloat = paramReportEntity.getRegionArray();
    paramViewHolder.textOne.setText(NumUtils.roundValue(arrayOfFloat[0]) + "%");
    paramViewHolder.textTwo.setText(NumUtils.roundValue(arrayOfFloat[1]) + "%");
    paramViewHolder.textOneBottom.setText(arrayOfString1[1]);
    paramViewHolder.textTwoBottom.setText(arrayOfString1[2]);
    paramViewHolder.textThreeBottom.setText(arrayOfString1[3]);
    paramViewHolder.textOne.setVisibility(0);
    paramViewHolder.textTwo.setVisibility(0);
    paramViewHolder.textThree.setVisibility(8);
    paramViewHolder.textFour.setVisibility(8);
    paramViewHolder.textOneBottom.setVisibility(0);
    paramViewHolder.textTwoBottom.setVisibility(0);
    paramViewHolder.textThreeBottom.setVisibility(0);
    paramViewHolder.textFourBottom.setVisibility(8);
    paramViewHolder.textFiveBottom.setVisibility(8);
    setLaoutPam(paramViewHolder.translateImage, 25 + this.fatWight * (-2 + paramReportEntity.getStateCode()) + this.fatWight / 2, 0, 2);
    setLaoutPam(paramViewHolder.textOne, 15 + this.fatWight, 0.0F, 2);
    setLaoutPam(paramViewHolder.textTwo, 15 + 2 * this.fatWight, 0.0F, 2);
    setLaoutPamBotton(paramViewHolder.textOneBottom, 23 + this.fatWight / 2, 0.0F, 2);
    setLaoutPamBotton(paramViewHolder.textTwoBottom, 17 + this.fatWight + this.fatWight / 2, 2.0F, 0);
    setLaoutPamBotton(paramViewHolder.textThreeBottom, 19 + 2 * this.fatWight + this.fatWight / 2, 2.0F, 0);
    paramViewHolder.textline.setVisibility(8);
    paramViewHolder.linear.setClickable(true);
    paramViewHolder.linear.setEnabled(true);
    paramViewHolder.shuzhi.setText(NumUtils.roundValue(paramReportEntity.getNum()) + "%");
    if (paramReportEntity.getIsSubHealth().booleanValue())
    {
      paramViewHolder.jingshiImage.setVisibility(0);
      this.wigeht.addviewYajiank(paramViewHolder.LinearLayout, paramReportEntity.getSubHealthMsg(), 30, 0);
    }
    String[] arrayOfString2 = paramReportEntity.getMessages();
    int i = arrayOfString2.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      String str = arrayOfString2[j];
      if ((str != null) && (!str.equals("")))
        this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  private void initBoneMass(ViewHolder paramViewHolder, ReportEntity paramReportEntity, View paramView)
  {
    paramView.setVisibility(0);
    this.fatWight = ((-24 + (this.nowWidth - ModUtils.dip2px(this, 50.0F))) / 3);
    paramViewHolder.hengtiaoRelative.setVisibility(0);
    paramViewHolder.shuzhi.setVisibility(0);
    paramViewHolder.textState.setVisibility(0);
    paramViewHolder.translateImage.setImageResource(BodyCompositionSectionGlobal.getBoneMarkeImage(paramReportEntity.getStateCode()));
    paramViewHolder.hengtiao_bg.setBackgroundResource(2130838205);
    paramViewHolder.liftImage.setImageResource(BodyCompositionSectionGlobal.getBoneLeftImage(paramReportEntity.getStandardOrNot()));
    paramViewHolder.tvTitle.setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getBoneStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setBackgroundResource(BodyCompositionSectionGlobal.getBoneStateImage(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setPadding(this.tatePading, 0, this.tatePading, 0);
    String[] arrayOfString1 = BodyCompositionSectionGlobal.BONE;
    float[] arrayOfFloat = paramReportEntity.getRegionArray();
    paramViewHolder.textOne.setText(NumUtils.roundValue(arrayOfFloat[0]) + "kg");
    paramViewHolder.textTwo.setText(NumUtils.roundValue(arrayOfFloat[1]) + "kg");
    paramViewHolder.textOneBottom.setText(arrayOfString1[0]);
    paramViewHolder.textTwoBottom.setText(arrayOfString1[1]);
    paramViewHolder.textThreeBottom.setText(arrayOfString1[2]);
    paramViewHolder.textOne.setVisibility(0);
    paramViewHolder.textTwo.setVisibility(0);
    paramViewHolder.textThree.setVisibility(8);
    paramViewHolder.textFour.setVisibility(8);
    paramViewHolder.textOneBottom.setVisibility(0);
    paramViewHolder.textTwoBottom.setVisibility(0);
    paramViewHolder.textThreeBottom.setVisibility(0);
    paramViewHolder.textFourBottom.setVisibility(8);
    paramViewHolder.textFiveBottom.setVisibility(8);
    setLaoutPam(paramViewHolder.translateImage, 25 + this.fatWight * (-1 + paramReportEntity.getStateCode()) + this.fatWight / 2, 0, 2);
    setLaoutPam(paramViewHolder.textOne, 15 + this.fatWight, 0.0F, 2);
    setLaoutPam(paramViewHolder.textTwo, 15 + 2 * this.fatWight, 0.0F, 2);
    setLaoutPamBotton(paramViewHolder.textOneBottom, 23 + this.fatWight / 2, 0.0F, 2);
    setLaoutPamBotton(paramViewHolder.textTwoBottom, 17 + this.fatWight + this.fatWight / 2, 2.0F, 0);
    setLaoutPamBotton(paramViewHolder.textThreeBottom, 19 + 2 * this.fatWight + this.fatWight / 2, 2.0F, 0);
    paramViewHolder.textline.setVisibility(8);
    paramViewHolder.linear.setClickable(true);
    paramViewHolder.linear.setEnabled(true);
    paramViewHolder.shuzhi.setText(NumUtils.roundValue(paramReportEntity.getNum()) + "kg");
    String[] arrayOfString2 = paramReportEntity.getMessages();
    int i = arrayOfString2.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      String str = arrayOfString2[j];
      if ((str != null) && (!str.equals("")))
        this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  private void initHead1(ViewHolder paramViewHolder, View paramView)
  {
    if (this.model.get_noStandardNum() == 0)
    {
      paramView.setVisibility(8);
      return;
    }
    paramView.setVisibility(0);
    paramViewHolder.verlline.setVisibility(0);
    paramViewHolder.textline.setVisibility(8);
    paramViewHolder.shuzhi.setVisibility(8);
    paramViewHolder.textState.setVisibility(8);
    String str = "共检测8项指标,未达标" + this.model.get_noStandardNum() + "项";
    int i = ModUtils.cacluteString("达标", str);
    if (i != 1)
      paramViewHolder.tvTitle.setText(ModUtils.cacludeString(this, i, str));
    while (true)
    {
      paramViewHolder.linear.setClickable(false);
      paramViewHolder.linear.setEnabled(false);
      paramViewHolder.liftImage.setImageResource(2130838214);
      return;
      paramViewHolder.tvTitle.setText(str);
    }
  }

  private void initHead2(ViewHolder paramViewHolder, View paramView)
  {
    paramView.setVisibility(0);
    paramViewHolder.verlline.setVisibility(0);
    paramViewHolder.linear.setEnabled(false);
    paramViewHolder.textline.setVisibility(8);
    paramViewHolder.shuzhi.setVisibility(8);
    paramViewHolder.textState.setVisibility(8);
    String str3;
    if (this.model.get_standardNum() == 8)
      if (this.model.getSubHealthCount() != 0)
      {
        str3 = "已达标" + this.model.get_standardNum() + "项  其中健康警示指标" + this.model.getSubHealthCount() + "项";
        SpannableStringBuilder localSpannableStringBuilder2 = ModUtils.replaceImage(str3, this);
        if (localSpannableStringBuilder2 != null)
          paramViewHolder.tvTitle.setText(localSpannableStringBuilder2);
      }
    while (true)
    {
      paramViewHolder.linear.setClickable(false);
      paramViewHolder.textState.setPadding(this.tatePading, 0, this.tatePading, 0);
      paramViewHolder.liftImage.setImageResource(2130838181);
      return;
      paramViewHolder.tvTitle.setText(str3);
      continue;
      paramViewHolder.tvTitle.setText("共检测8项指标，已全部达标");
      continue;
      if (this.model.get_standardNum() == 0)
      {
        paramView.setVisibility(8);
      }
      else if (this.model.getSubHealthCount() != 0)
      {
        String str2 = "已达标" + this.model.get_standardNum() + "项  其中健康警示指标" + this.model.getSubHealthCount() + "项";
        SpannableStringBuilder localSpannableStringBuilder1 = ModUtils.replaceImage(str2, this);
        if (localSpannableStringBuilder1 != null)
          paramViewHolder.tvTitle.setText(localSpannableStringBuilder1);
        else
          paramViewHolder.tvTitle.setText(str2);
      }
      else
      {
        String str1 = "已达标" + this.model.get_standardNum() + "项";
        int i = ModUtils.cacluteString("达标", str1);
        if (i != 1)
          paramViewHolder.tvTitle.setText(ModUtils.cacludeString(this, i, str1));
        else
          paramViewHolder.tvTitle.setText(str1);
      }
    }
  }

  private void initInFat(ViewHolder paramViewHolder, ReportEntity paramReportEntity, View paramView)
  {
    paramView.setVisibility(0);
    this.fatWight = ((-24 + (this.nowWidth - ModUtils.dip2px(this, 50.0F))) / 3);
    paramViewHolder.hengtiaoRelative.setVisibility(0);
    paramViewHolder.shuzhi.setVisibility(0);
    paramViewHolder.textState.setVisibility(0);
    paramViewHolder.translateImage.setImageResource(BodyCompositionSectionGlobal.getInfatMarkeImage(paramReportEntity.getStateCode()));
    paramViewHolder.hengtiao_bg.setBackgroundResource(2130838195);
    paramViewHolder.liftImage.setImageResource(BodyCompositionSectionGlobal.getInfatLeftImage(paramReportEntity.getStandardOrNot()));
    paramViewHolder.tvTitle.setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getInfatStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setBackgroundResource(BodyCompositionSectionGlobal.getInfatStateImage(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setPadding(this.tatePading, 0, this.tatePading, 0);
    String[] arrayOfString1 = BodyCompositionSectionGlobal.INFAT;
    float[] arrayOfFloat = paramReportEntity.getRegionArray();
    paramViewHolder.textOne.setText(NumUtils.roundValue(arrayOfFloat[0]));
    paramViewHolder.textTwo.setText(NumUtils.roundValue(arrayOfFloat[1]));
    paramViewHolder.textOneBottom.setText(arrayOfString1[0]);
    paramViewHolder.textTwoBottom.setText(arrayOfString1[1]);
    paramViewHolder.textThreeBottom.setText(arrayOfString1[2]);
    paramViewHolder.textOne.setVisibility(0);
    paramViewHolder.textTwo.setVisibility(0);
    paramViewHolder.textThree.setVisibility(8);
    paramViewHolder.textFour.setVisibility(8);
    paramViewHolder.textOneBottom.setVisibility(0);
    paramViewHolder.textTwoBottom.setVisibility(0);
    paramViewHolder.textThreeBottom.setVisibility(0);
    paramViewHolder.textFourBottom.setVisibility(8);
    paramViewHolder.textFiveBottom.setVisibility(8);
    setLaoutPam(paramViewHolder.translateImage, 25 + this.fatWight * (-1 + paramReportEntity.getStateCode()) + this.fatWight / 2, 0, 2);
    setLaoutPam(paramViewHolder.textOne, 15 + this.fatWight, 0.0F, 2);
    setLaoutPam(paramViewHolder.textTwo, 15 + 2 * this.fatWight, 0.0F, 2);
    setLaoutPamBotton(paramViewHolder.textOneBottom, 23 + this.fatWight / 2, 0.0F, 2);
    setLaoutPamBotton(paramViewHolder.textTwoBottom, 17 + this.fatWight + this.fatWight / 2, 2.0F, 0);
    setLaoutPamBotton(paramViewHolder.textThreeBottom, 19 + 2 * this.fatWight + this.fatWight / 2, 2.0F, 0);
    paramViewHolder.textline.setVisibility(8);
    paramViewHolder.linear.setClickable(true);
    paramViewHolder.linear.setEnabled(true);
    paramViewHolder.shuzhi.setText(NumUtils.roundFloatToInt(paramReportEntity.getNum()));
    if (paramReportEntity.getIsSubHealth().booleanValue())
    {
      paramViewHolder.jingshiImage.setVisibility(0);
      this.wigeht.addviewYajiank(paramViewHolder.LinearLayout, paramReportEntity.getSubHealthMsg(), 30, 0);
    }
    String[] arrayOfString2 = paramReportEntity.getMessages();
    int i = arrayOfString2.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      String str = arrayOfString2[j];
      if ((str != null) && (!str.equals("")))
        this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  private void initLine(ViewHolder paramViewHolder, View paramView)
  {
    if ((this.model.get_noStandardNum() == 0) || (this.model.get_noStandardNum() == 8))
    {
      paramView.setVisibility(8);
      return;
    }
    paramView.setVisibility(0);
    paramViewHolder.verlline.setVisibility(8);
    paramViewHolder.textline.setVisibility(0);
    paramViewHolder.linear.setVisibility(8);
    paramViewHolder.expandable.setVisibility(8);
  }

  private void initMuscle(ViewHolder paramViewHolder, ReportEntity paramReportEntity, View paramView)
  {
    paramView.setVisibility(0);
    this.fatWight = ((-24 + (this.nowWidth - ModUtils.dip2px(this, 50.0F))) / 3);
    paramViewHolder.hengtiaoRelative.setVisibility(0);
    paramViewHolder.shuzhi.setVisibility(0);
    paramViewHolder.textState.setVisibility(0);
    paramViewHolder.translateImage.setImageResource(BodyCompositionSectionGlobal.getMuscleMarkeImage(paramReportEntity.getStateCode()));
    paramViewHolder.hengtiao_bg.setBackgroundResource(2130838205);
    paramViewHolder.liftImage.setImageResource(BodyCompositionSectionGlobal.getMuscleLeftImage(paramReportEntity.getStandardOrNot()));
    paramViewHolder.tvTitle.setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getMuscleStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setBackgroundResource(BodyCompositionSectionGlobal.getMuscleStateImage(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setPadding(this.tatePading, 0, this.tatePading, 0);
    String[] arrayOfString1 = BodyCompositionSectionGlobal.MUSCLE;
    float[] arrayOfFloat = paramReportEntity.getRegionArray();
    paramViewHolder.textOne.setText(NumUtils.roundValue(arrayOfFloat[0]) + "%");
    paramViewHolder.textTwo.setText(NumUtils.roundValue(arrayOfFloat[1]) + "%");
    paramViewHolder.textOneBottom.setText(arrayOfString1[0]);
    paramViewHolder.textTwoBottom.setText(arrayOfString1[1]);
    paramViewHolder.textThreeBottom.setText(arrayOfString1[2]);
    paramViewHolder.textOne.setVisibility(0);
    paramViewHolder.textTwo.setVisibility(0);
    paramViewHolder.textThree.setVisibility(8);
    paramViewHolder.textFour.setVisibility(8);
    paramViewHolder.textOneBottom.setVisibility(0);
    paramViewHolder.textTwoBottom.setVisibility(0);
    paramViewHolder.textThreeBottom.setVisibility(0);
    paramViewHolder.textFourBottom.setVisibility(8);
    paramViewHolder.textFiveBottom.setVisibility(8);
    setLaoutPam(paramViewHolder.translateImage, 25 + this.fatWight * (-1 + paramReportEntity.getStateCode()) + this.fatWight / 2, 0, 2);
    setLaoutPam(paramViewHolder.textOne, 15 + this.fatWight, 0.0F, 2);
    setLaoutPam(paramViewHolder.textTwo, 15 + 2 * this.fatWight, 0.0F, 2);
    setLaoutPamBotton(paramViewHolder.textOneBottom, 23 + this.fatWight / 2, 0.0F, 2);
    setLaoutPamBotton(paramViewHolder.textTwoBottom, 17 + this.fatWight + this.fatWight / 2, 2.0F, 0);
    setLaoutPamBotton(paramViewHolder.textThreeBottom, 19 + 2 * this.fatWight + this.fatWight / 2, 2.0F, 0);
    paramViewHolder.textline.setVisibility(8);
    paramViewHolder.linear.setClickable(true);
    paramViewHolder.linear.setEnabled(true);
    paramViewHolder.shuzhi.setText(NumUtils.roundValue(paramReportEntity.getNum()) + "%");
    if (paramReportEntity.getIsSubHealth().booleanValue())
    {
      paramViewHolder.jingshiImage.setVisibility(0);
      this.wigeht.addviewYajiank(paramViewHolder.LinearLayout, paramReportEntity.getSubHealthMsg(), 30, 0);
    }
    String[] arrayOfString2 = paramReportEntity.getMessages();
    int i = arrayOfString2.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      String str = arrayOfString2[j];
      if ((str != null) && (!str.equals("")))
        this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  private void initOldWeight(ViewHolder paramViewHolder, ReportEntity paramReportEntity, View paramView)
  {
    paramView.setVisibility(0);
    this.fatWight = ((-24 + (this.nowWidth - ModUtils.dip2px(this, 50.0F))) / 3);
    paramViewHolder.shuzhi.setVisibility(0);
    paramViewHolder.textState.setVisibility(0);
    paramViewHolder.hengtiaoRelative.setVisibility(0);
    paramViewHolder.translateImage.setImageResource(BodyCompositionSectionGlobal.getWeightoldMrakeImage(paramReportEntity.getStateCode()));
    paramViewHolder.hengtiao_bg.setBackgroundResource(2130838196);
    paramViewHolder.liftImage.setImageResource(BodyCompositionSectionGlobal.getWeightLeftImage(paramReportEntity.getStandardOrNot(), ((MyApplication)getApplicationContext()).getCurrentRole().getSex()));
    paramViewHolder.tvTitle.setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getWeightStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setBackgroundResource(BodyCompositionSectionGlobal.getWeightOldStateImage(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setPadding(this.tatePading, 0, this.tatePading, 0);
    String[] arrayOfString1 = BodyCompositionSectionGlobal.WEIGHT;
    float[] arrayOfFloat = paramReportEntity.getRegionArray();
    paramViewHolder.textOne.setText(NumUtils.roundValue(arrayOfFloat[0]) + "kg");
    paramViewHolder.textTwo.setText(NumUtils.roundValue(arrayOfFloat[1]) + "kg");
    paramViewHolder.textOneBottom.setText(arrayOfString1[0]);
    paramViewHolder.textTwoBottom.setText(arrayOfString1[1]);
    paramViewHolder.textThreeBottom.setText(arrayOfString1[2]);
    paramViewHolder.textOne.setVisibility(0);
    paramViewHolder.textTwo.setVisibility(0);
    paramViewHolder.textThree.setVisibility(8);
    paramViewHolder.textFour.setVisibility(8);
    paramViewHolder.textOneBottom.setVisibility(0);
    paramViewHolder.textTwoBottom.setVisibility(0);
    paramViewHolder.textThreeBottom.setVisibility(0);
    paramViewHolder.textFourBottom.setVisibility(8);
    paramViewHolder.textFiveBottom.setVisibility(8);
    setLaoutPam(paramViewHolder.translateImage, 25 + this.fatWight * (-1 + paramReportEntity.getStateCode()) + this.fatWight / 2, 0, 2);
    setLaoutPam(paramViewHolder.textOne, 15 + this.fatWight, 0.0F, 2);
    setLaoutPam(paramViewHolder.textTwo, 15 + 2 * this.fatWight, 0.0F, 2);
    setLaoutPamBotton(paramViewHolder.textOneBottom, 23 + this.fatWight / 2, 0.0F, 2);
    setLaoutPamBotton(paramViewHolder.textTwoBottom, 17 + this.fatWight + this.fatWight / 2, 2.0F, 0);
    setLaoutPamBotton(paramViewHolder.textThreeBottom, 19 + 2 * this.fatWight + this.fatWight / 2, 2.0F, 0);
    paramViewHolder.textline.setVisibility(8);
    paramViewHolder.linear.setClickable(true);
    paramViewHolder.linear.setEnabled(true);
    paramViewHolder.shuzhi.setText(NumUtils.roundValue(paramReportEntity.getNum()) + "kg");
    if (paramReportEntity.getIsSubHealth().booleanValue())
    {
      paramViewHolder.jingshiImage.setVisibility(0);
      this.wigeht.addviewYajiank(paramViewHolder.LinearLayout, paramReportEntity.getSubHealthMsg(), 30, 0);
    }
    String[] arrayOfString2 = paramReportEntity.getMessages();
    int i = arrayOfString2.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      String str = arrayOfString2[j];
      if ((str != null) && (!str.equals("")))
        this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  private void initProtein(ViewHolder paramViewHolder, ReportEntity paramReportEntity, View paramView)
  {
    paramView.setVisibility(0);
    this.fatWight = ((-24 + (this.nowWidth - ModUtils.dip2px(this, 50.0F))) / 3);
    paramViewHolder.hengtiaoRelative.setVisibility(0);
    paramViewHolder.shuzhi.setVisibility(0);
    paramViewHolder.textState.setVisibility(0);
    paramViewHolder.translateImage.setImageResource(BodyCompositionSectionGlobal.getProteinMarkeImage(paramReportEntity.getStateCode()));
    paramViewHolder.hengtiao_bg.setBackgroundResource(2130838205);
    paramViewHolder.liftImage.setImageResource(BodyCompositionSectionGlobal.getProteinLeftImage(paramReportEntity.getStandardOrNot()));
    paramViewHolder.tvTitle.setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getProteinStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setBackgroundResource(BodyCompositionSectionGlobal.getProteinStateImage(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setPadding(this.tatePading, 0, this.tatePading, 0);
    String[] arrayOfString1 = BodyCompositionSectionGlobal.PROTEIN;
    float[] arrayOfFloat = paramReportEntity.getRegionArray();
    paramViewHolder.textOne.setText(NumUtils.roundValue(arrayOfFloat[0]) + "%");
    paramViewHolder.textTwo.setText(NumUtils.roundValue(arrayOfFloat[1]) + "%");
    paramViewHolder.textOneBottom.setText(arrayOfString1[0]);
    paramViewHolder.textTwoBottom.setText(arrayOfString1[1]);
    paramViewHolder.textThreeBottom.setText(arrayOfString1[2]);
    paramViewHolder.textOne.setVisibility(0);
    paramViewHolder.textTwo.setVisibility(0);
    paramViewHolder.textThree.setVisibility(8);
    paramViewHolder.textFour.setVisibility(8);
    paramViewHolder.textOneBottom.setVisibility(0);
    paramViewHolder.textTwoBottom.setVisibility(0);
    paramViewHolder.textThreeBottom.setVisibility(0);
    paramViewHolder.textFourBottom.setVisibility(8);
    paramViewHolder.textFiveBottom.setVisibility(8);
    setLaoutPam(paramViewHolder.translateImage, 25 + this.fatWight * (-1 + paramReportEntity.getStateCode()) + this.fatWight / 2, 0, 2);
    setLaoutPam(paramViewHolder.textOne, 15 + this.fatWight, 0.0F, 2);
    setLaoutPam(paramViewHolder.textTwo, 15 + 2 * this.fatWight, 0.0F, 2);
    setLaoutPamBotton(paramViewHolder.textOneBottom, 23 + this.fatWight / 2, 0.0F, 2);
    setLaoutPamBotton(paramViewHolder.textTwoBottom, 17 + this.fatWight + this.fatWight / 2, 2.0F, 0);
    setLaoutPamBotton(paramViewHolder.textThreeBottom, 19 + 2 * this.fatWight + this.fatWight / 2, 2.0F, 0);
    paramViewHolder.textline.setVisibility(8);
    paramViewHolder.linear.setClickable(true);
    paramViewHolder.linear.setEnabled(true);
    paramViewHolder.shuzhi.setText(NumUtils.roundValue(paramReportEntity.getNum()) + "%");
    String[] arrayOfString2 = paramReportEntity.getMessages();
    int i = arrayOfString2.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      String str = arrayOfString2[j];
      if ((str != null) && (!str.equals("")))
        this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  private void initWater(ViewHolder paramViewHolder, ReportEntity paramReportEntity, View paramView)
  {
    paramView.setVisibility(0);
    this.fatWight = ((-24 + (this.nowWidth - ModUtils.dip2px(this, 50.0F))) / 3);
    paramViewHolder.hengtiaoRelative.setVisibility(0);
    paramViewHolder.shuzhi.setVisibility(0);
    paramViewHolder.textState.setVisibility(0);
    paramViewHolder.translateImage.setImageResource(BodyCompositionSectionGlobal.getWaterMarkeImage(paramReportEntity.getStateCode()));
    paramViewHolder.hengtiao_bg.setBackgroundResource(2130838205);
    paramViewHolder.liftImage.setImageResource(BodyCompositionSectionGlobal.getWaterLeftImage(paramReportEntity.getStandardOrNot()));
    paramViewHolder.tvTitle.setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getWaterStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setBackgroundResource(BodyCompositionSectionGlobal.getWaterStateImage(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setPadding(this.tatePading, 0, this.tatePading, 0);
    String[] arrayOfString1 = BodyCompositionSectionGlobal.WATER;
    float[] arrayOfFloat = paramReportEntity.getRegionArray();
    paramViewHolder.textOne.setText(NumUtils.roundValue(arrayOfFloat[0]) + "%");
    paramViewHolder.textTwo.setText(NumUtils.roundValue(arrayOfFloat[1]) + "%");
    paramViewHolder.textOneBottom.setText(arrayOfString1[0]);
    paramViewHolder.textTwoBottom.setText(arrayOfString1[1]);
    paramViewHolder.textThreeBottom.setText(arrayOfString1[2]);
    paramViewHolder.textOne.setVisibility(0);
    paramViewHolder.textTwo.setVisibility(0);
    paramViewHolder.textThree.setVisibility(8);
    paramViewHolder.textFour.setVisibility(8);
    paramViewHolder.textOneBottom.setVisibility(0);
    paramViewHolder.textTwoBottom.setVisibility(0);
    paramViewHolder.textThreeBottom.setVisibility(0);
    paramViewHolder.textFourBottom.setVisibility(8);
    paramViewHolder.textFiveBottom.setVisibility(8);
    setLaoutPam(paramViewHolder.translateImage, 25 + this.fatWight * (-1 + paramReportEntity.getStateCode()) + this.fatWight / 2, 0, 2);
    setLaoutPam(paramViewHolder.textOne, 15 + this.fatWight, 0.0F, 2);
    setLaoutPam(paramViewHolder.textTwo, 15 + 2 * this.fatWight, 0.0F, 2);
    setLaoutPamBotton(paramViewHolder.textOneBottom, 23 + this.fatWight / 2, 0.0F, 2);
    setLaoutPamBotton(paramViewHolder.textTwoBottom, 17 + this.fatWight + this.fatWight / 2, 2.0F, 0);
    setLaoutPamBotton(paramViewHolder.textThreeBottom, 19 + 2 * this.fatWight + this.fatWight / 2, 2.0F, 0);
    paramViewHolder.textline.setVisibility(8);
    paramViewHolder.linear.setClickable(true);
    paramViewHolder.linear.setEnabled(true);
    paramViewHolder.shuzhi.setText(NumUtils.roundValue(paramReportEntity.getNum()) + "%");
    String[] arrayOfString2 = paramReportEntity.getMessages();
    int i = arrayOfString2.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      String str = arrayOfString2[j];
      if ((str != null) && (!str.equals("")))
        this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  private void initWeight(ViewHolder paramViewHolder, ReportEntity paramReportEntity, View paramView)
  {
    paramView.setVisibility(0);
    this.fatWight = ((-24 + (this.nowWidth - ModUtils.dip2px(this, 50.0F))) / 3);
    paramViewHolder.shuzhi.setVisibility(0);
    paramViewHolder.textState.setVisibility(0);
    paramViewHolder.hengtiaoRelative.setVisibility(0);
    paramViewHolder.translateImage.setImageResource(BodyCompositionSectionGlobal.getWeightMrakeImage(paramReportEntity.getStateCode()));
    paramViewHolder.hengtiao_bg.setBackgroundResource(2130838210);
    paramViewHolder.liftImage.setImageResource(BodyCompositionSectionGlobal.getWeightLeftImage(paramReportEntity.getStandardOrNot(), ((MyApplication)getApplicationContext()).getCurrentRole().getSex()));
    paramViewHolder.tvTitle.setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getWeightStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setBackgroundResource(BodyCompositionSectionGlobal.getWeightStateImage(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setPadding(this.tatePading, 0, this.tatePading, 0);
    String[] arrayOfString1 = BodyCompositionSectionGlobal.WEIGHT;
    float[] arrayOfFloat = paramReportEntity.getRegionArray();
    paramViewHolder.textOne.setText(NumUtils.roundValue(arrayOfFloat[0]) + "kg");
    paramViewHolder.textTwo.setText(NumUtils.roundValue(arrayOfFloat[1]) + "kg");
    paramViewHolder.textOneBottom.setText(arrayOfString1[0]);
    paramViewHolder.textTwoBottom.setText(arrayOfString1[1]);
    paramViewHolder.textThreeBottom.setText(arrayOfString1[2]);
    paramViewHolder.textOne.setVisibility(0);
    paramViewHolder.textTwo.setVisibility(0);
    paramViewHolder.textThree.setVisibility(8);
    paramViewHolder.textFour.setVisibility(8);
    paramViewHolder.textOneBottom.setVisibility(0);
    paramViewHolder.textTwoBottom.setVisibility(0);
    paramViewHolder.textThreeBottom.setVisibility(0);
    paramViewHolder.textFourBottom.setVisibility(8);
    paramViewHolder.textFiveBottom.setVisibility(8);
    setLaoutPam(paramViewHolder.translateImage, 25 + this.fatWight * (-1 + paramReportEntity.getStateCode()) + this.fatWight / 2, 0, 2);
    setLaoutPam(paramViewHolder.textOne, 15 + this.fatWight, 0.0F, 2);
    setLaoutPam(paramViewHolder.textTwo, 15 + 2 * this.fatWight, 0.0F, 2);
    setLaoutPamBotton(paramViewHolder.textOneBottom, 23 + this.fatWight / 2, 0.0F, 2);
    setLaoutPamBotton(paramViewHolder.textTwoBottom, 17 + this.fatWight + this.fatWight / 2, 2.0F, 0);
    setLaoutPamBotton(paramViewHolder.textThreeBottom, 19 + 2 * this.fatWight + this.fatWight / 2, 2.0F, 0);
    paramViewHolder.textline.setVisibility(8);
    paramViewHolder.linear.setClickable(true);
    paramViewHolder.linear.setEnabled(true);
    paramViewHolder.shuzhi.setText(NumUtils.roundValue(paramReportEntity.getNum()) + "kg");
    if (paramReportEntity.getIsSubHealth().booleanValue())
    {
      paramViewHolder.jingshiImage.setVisibility(0);
      this.wigeht.addviewYajiank(paramViewHolder.LinearLayout, paramReportEntity.getSubHealthMsg(), 30, 0);
    }
    String[] arrayOfString2 = paramReportEntity.getMessages();
    int i = arrayOfString2.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      String str = arrayOfString2[j];
      if ((str != null) && (!str.equals("")))
        this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  private void initWeightOne(ViewHolder paramViewHolder, ReportEntity paramReportEntity, View paramView)
  {
    ModUtils.dip2px(this, 10.0F);
    float f1 = -24 + (this.nowWidth - ModUtils.dip2px(this, 50.0F));
    paramView.setVisibility(0);
    this.fatWight = ((-24 + (this.nowWidth - ModUtils.dip2px(this, 50.0F))) / 3);
    float f2 = f1 * 0.263158F;
    float f3 = f1 * 0.175439F;
    paramViewHolder.shuzhi.setVisibility(0);
    paramViewHolder.textState.setVisibility(0);
    paramViewHolder.hengtiaoRelative.setVisibility(0);
    ImageView localImageView1 = paramViewHolder.translateImage;
    int i;
    int j;
    label142: int k;
    label227: float f4;
    label468: String[] arrayOfString2;
    int m;
    if (paramReportEntity.getWeightState() == 2)
    {
      i = BodyCompositionSectionGlobal.getWeightMarkLight(paramReportEntity.getWeightAnchorFlag());
      localImageView1.setImageResource(i);
      ImageView localImageView2 = paramViewHolder.hengtiao_bg;
      if (paramReportEntity.getWeightState() != 2)
        break label734;
      j = 2130837583;
      localImageView2.setBackgroundResource(j);
      paramViewHolder.liftImage.setImageResource(BodyCompositionSectionGlobal.getWeightLeftImage(paramReportEntity.getStandardOrNot(), ((MyApplication)getApplicationContext()).getCurrentRole().getSex()));
      paramViewHolder.tvTitle.setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
      paramViewHolder.textState.setText(paramReportEntity.getState());
      TextView localTextView = paramViewHolder.textState;
      if (paramReportEntity.getWeightState() != 2)
        break label742;
      k = BodyCompositionSectionGlobal.getWeightStateBgLight(paramReportEntity.getWeightAnchorFlag());
      localTextView.setBackgroundResource(k);
      paramViewHolder.textState.setPadding(this.tatePading, 0, this.tatePading, 0);
      String[] arrayOfString1 = BodyCompositionSectionGlobal.WEIGHT;
      float[] arrayOfFloat = paramReportEntity.getRegionArray();
      paramViewHolder.textOne.setText(NumUtils.roundValue(arrayOfFloat[0]) + "kg");
      paramViewHolder.textTwo.setText(NumUtils.roundValue(arrayOfFloat[1]) + "kg");
      paramViewHolder.textOneBottom.setText(arrayOfString1[0]);
      paramViewHolder.textTwoBottom.setText(arrayOfString1[1]);
      paramViewHolder.textThreeBottom.setText("略微超标");
      paramViewHolder.textFourBottom.setText("严重超标");
      paramViewHolder.textOne.setVisibility(0);
      paramViewHolder.textTwo.setVisibility(0);
      paramViewHolder.textThree.setVisibility(8);
      paramViewHolder.textFour.setVisibility(8);
      paramViewHolder.textOneBottom.setVisibility(0);
      paramViewHolder.textTwoBottom.setVisibility(0);
      paramViewHolder.textThreeBottom.setVisibility(0);
      paramViewHolder.textFourBottom.setVisibility(0);
      paramViewHolder.textFiveBottom.setVisibility(8);
      if (paramReportEntity.getWeightAnchorFlag() != 3)
        break label754;
      f4 = 15.0F + (2.0F * f2 + f3 / 2.0F);
      setLaoutPam(paramViewHolder.translateImage, 25.0F + f4, 0, 2);
      setLaoutPam(paramViewHolder.textOne, 15.0F + f2, 0.0F, 2);
      setLaoutPam(paramViewHolder.textTwo, 17.0F + 2.0F * f2, 0.0F, 2);
      setLaoutPamBotton(paramViewHolder.textOneBottom, 23.0F + f2 / 2.0F, 0.0F, 2);
      setLaoutPamBotton(paramViewHolder.textTwoBottom, 21.0F + f2 + f2 / 2.0F, 2.0F, 0);
      setLaoutPamBotton(paramViewHolder.textThreeBottom, 14.0F + 2.0F * f2 + f3 / 2.0F, 2.0F, 0);
      setLaoutPamBotton(paramViewHolder.textFourBottom, f3 + (17.0F + 2.0F * f2) + f2 / 2.0F, 2.0F, 0);
      paramViewHolder.textline.setVisibility(8);
      paramViewHolder.linear.setClickable(true);
      paramViewHolder.linear.setEnabled(true);
      paramViewHolder.shuzhi.setText(NumUtils.roundValue(paramReportEntity.getNum()) + "kg");
      if (paramReportEntity.getIsSubHealth().booleanValue())
      {
        paramViewHolder.jingshiImage.setVisibility(0);
        this.wigeht.addviewYajiank(paramViewHolder.LinearLayout, paramReportEntity.getSubHealthMsg(), 30, 0);
      }
      arrayOfString2 = paramReportEntity.getMessages();
      m = arrayOfString2.length;
    }
    for (int n = 0; ; n++)
    {
      if (n >= m)
      {
        return;
        i = BodyCompositionSectionGlobal.getWeightMarkYanZhong(paramReportEntity.getWeightAnchorFlag());
        break;
        label734: j = 2130838178;
        break label142;
        label742: k = BodyCompositionSectionGlobal.getWeightStateBgYanZhong(paramReportEntity.getWeightAnchorFlag());
        break label227;
        label754: f4 = 12.0F + (f3 + 2.0F * f2 + f2 / 2.0F);
        break label468;
      }
      String str = arrayOfString2[n];
      if ((str != null) && (!str.equals("")))
        this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  public void dismissOrShowOne(View paramView)
  {
    try
    {
      paramView.startAnimation(new ViewExpandAnimation(paramView));
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968576, 2130968581);
  }

  public void invit()
  {
    this.linearList = ((LinearLayout)findViewById(2131100495));
    ImageView localImageView1 = (ImageView)findViewById(2131099651);
    ImageView localImageView2 = (ImageView)findViewById(2131099650);
    localImageView1.setImageResource(2130838428);
    localImageView1.setOnClickListener(this);
    localImageView2.setOnClickListener(this);
    localImageView2.setImageResource(2130838406);
    localImageView1.setVisibility(8);
    ((TextView)findViewById(2131099699)).setText("身体成分评测");
    TextView localTextView1 = (TextView)findViewById(2131099717);
    localTextView1.setTypeface(ModUtils.getTypeface(this));
    localTextView1.setText(this.model.getTotalScore());
    localTextView1.setTextColor(this.model.getCycleColor());
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int i = localDisplayMetrics.densityDpi;
    int j;
    if ((i < 420) && (i >= 300))
      j = 6;
    while (true)
    {
      ArcProgress localArcProgress = new ArcProgress(this, Integer.valueOf(this.model.getCycleColor()), Integer.valueOf(-90), Integer.valueOf(360), false, j);
      localArcProgress.setBackGroundLineColor(0);
      RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
      localArcProgress.initProgress(this.model.getTotalScore());
      RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(2131099716);
      TextView localTextView2 = (TextView)findViewById(2131099718);
      localTextView2.setLineSpacing(3.4F, 1.2F);
      localTextView2.setTypeface(ModUtils.getTypeface(this));
      if ((this.model.getTotalString() != null) && (!this.model.getTotalString().equals("")))
        ModUtils.replaceString(localTextView2, this, this.model.getTotalString(), false);
      localRelativeLayout.addView(localArcProgress, localLayoutParams);
      invitItem();
      return;
      if (i >= 420)
        j = 8;
      else
        j = 4;
    }
  }

  public void invitGoneTop(View paramView)
  {
    paramView.measure(View.MeasureSpec.makeMeasureSpec((int)(this.nowWidth - 10.0F * this.mDensity), 1073741824), 0);
    ((LinearLayout.LayoutParams)paramView.getLayoutParams()).bottomMargin = (-paramView.getMeasuredHeight());
    paramView.setVisibility(8);
  }

  public void invitItem()
  {
    int i = 0;
    if (i >= 12)
      return;
    this.view = LayoutInflater.from(this).inflate(2130903054, null);
    final LinearLayout localLinearLayout1 = (LinearLayout)this.view.findViewById(2131099703);
    LinearLayout localLinearLayout2 = (LinearLayout)this.view.findViewById(2131099701);
    ViewHolder localViewHolder = new ViewHolder();
    this.wigeht = new widgetAddView(this);
    localViewHolder.tvTitle = ((TextView)this.view.findViewById(2131099728));
    localViewHolder.textline = ((ImageView)this.view.findViewById(2131099719));
    localViewHolder.shuzhi = ((TextView)this.view.findViewById(2131099729));
    localViewHolder.verlline = ((TextView)this.view.findViewById(2131099732));
    localViewHolder.textState = ((TextView)this.view.findViewById(2131099730));
    localViewHolder.textOne = ((TextView)this.view.findViewById(2131099680));
    localViewHolder.textTwo = ((TextView)this.view.findViewById(2131099681));
    localViewHolder.textThree = ((TextView)this.view.findViewById(2131099682));
    localViewHolder.textFour = ((TextView)this.view.findViewById(2131099683));
    localViewHolder.hengtiao_bg = ((ImageView)this.view.findViewById(2131099679));
    localViewHolder.hengtiaoRelative = ((RelativeLayout)this.view.findViewById(2131099733));
    localViewHolder.textOneBottom = ((TextView)this.view.findViewById(2131099684));
    localViewHolder.textTwoBottom = ((TextView)this.view.findViewById(2131099685));
    localViewHolder.textThreeBottom = ((TextView)this.view.findViewById(2131099686));
    localViewHolder.textFourBottom = ((TextView)this.view.findViewById(2131099687));
    localViewHolder.textFiveBottom = ((TextView)this.view.findViewById(2131099688));
    localViewHolder.liftImage = ((ImageView)this.view.findViewById(2131099702));
    localViewHolder.translateImage = ((ImageView)this.view.findViewById(2131099689));
    localViewHolder.jingshiImage = ((ImageView)this.view.findViewById(2131099731));
    localViewHolder.linear = ((LinearLayout)this.view.findViewById(2131099701));
    localViewHolder.expandable = ((LinearLayout)this.view.findViewById(2131099703));
    this.view.setTag(localViewHolder);
    localViewHolder.item = ((RelativeLayout)this.view.findViewById(2131099700));
    localViewHolder.LinearLayout = ((LinearLayout)this.view.findViewById(2131099705));
    localViewHolder.shuzhi.setTypeface(ModUtils.getTypeface(this));
    localViewHolder.tvTitle.setTypeface(ModUtils.getTypeface(this));
    localViewHolder.textOne.setTypeface(ModUtils.getTypeface(this));
    localViewHolder.textTwo.setTypeface(ModUtils.getTypeface(this));
    localViewHolder.textThree.setTypeface(ModUtils.getTypeface(this));
    localViewHolder.textFour.setTypeface(ModUtils.getTypeface(this));
    if (i == this.model.get_noStandardNum())
      localViewHolder.verlline.setVisibility(8);
    while (true)
    {
      if (i == 10)
        localViewHolder.verlline.setVisibility(8);
      if (i != 11)
        break;
      this.view.setVisibility(4);
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, ModUtils.dip2px(this, 35.0F));
      this.view.setLayoutParams(localLayoutParams);
      this.linearList.addView(this.view);
      return;
      localViewHolder.verlline.setVisibility(0);
    }
    if ((this.data.get(i) instanceof Integer))
      if (((Integer)this.data.get(i)).intValue() == 1)
        initHead1(localViewHolder, this.view);
    while (true)
    {
      localLinearLayout2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ((ViewGroup)paramAnonymousView.getParent());
          LiftPhysicalQualityTwo.this.miss(localLinearLayout1);
          LiftPhysicalQualityTwo.this.dismissOrShowOne(localLinearLayout1);
        }
      });
      this.listiten.add(localLinearLayout1);
      invitGoneTop(localLinearLayout1);
      this.linearList.addView(this.view);
      i++;
      break;
      if (((Integer)this.data.get(i)).intValue() == 2)
      {
        initLine(localViewHolder, this.view);
      }
      else if (((Integer)this.data.get(i)).intValue() == 3)
      {
        initHead2(localViewHolder, this.view);
        continue;
        if ((this.data.get(i) instanceof ReportEntity))
        {
          ReportEntity localReportEntity = (ReportEntity)this.data.get(i);
          switch ($SWITCH_TABLE$com$picooc$domain$ComponentBodyTypeEnum()[ComponentBodyTypeEnum.getEmnuByIndex(localReportEntity.getReportType()).ordinal()])
          {
          default:
            break;
          case 1:
            initMuscle(localViewHolder, localReportEntity, this.view);
            break;
          case 8:
            initBMR(localViewHolder, localReportEntity, this.view);
            break;
          case 7:
            initProtein(localViewHolder, localReportEntity, this.view);
            break;
          case 3:
            initBoneMass(localViewHolder, localReportEntity, this.view);
            break;
          case 4:
            if (Age.isOld((MyApplication)getApplication()))
              initOldWeight(localViewHolder, localReportEntity, this.view);
            else if (localReportEntity.getWeightState() == 1)
              initWeight(localViewHolder, localReportEntity, this.view);
            else
              initWeightOne(localViewHolder, localReportEntity, this.view);
            break;
          case 2:
            if (Age.isOld((MyApplication)getApplication()))
              initBodyFatOld(localViewHolder, localReportEntity, this.view);
            else
              initBodyFat(localViewHolder, localReportEntity, this.view);
            break;
          case 6:
            initWater(localViewHolder, localReportEntity, this.view);
            break;
          case 5:
            initInFat(localViewHolder, localReportEntity, this.view);
          }
        }
      }
    }
  }

  public void miss(LinearLayout paramLinearLayout)
  {
    Iterator localIterator = this.listiten.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      LinearLayout localLinearLayout = (LinearLayout)localIterator.next();
      if ((localLinearLayout != paramLinearLayout) && (localLinearLayout.isShown()))
        dismissOrShowOne(localLinearLayout);
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131099650:
    }
    finish();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903198);
    this.model = ((BodyCompositionAnalysisModel)getIntent().getSerializableExtra("model"));
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.nowWidth = localDisplayMetrics.widthPixels;
    this.mDensity = localDisplayMetrics.density;
    this.data = this.model.getBodyCompositionData();
    this.tatePading = ModUtils.dip2px(this, 7.0F);
    invit();
    if ((SharedPreferenceUtils.isFirstEnterCurPage(this, "physical_quality")) && (this.model.get_noStandardNum() >= 1))
    {
      this.listView = this.linearList.getChildAt(1);
      dismissOrShowOne((LinearLayout)this.listView.findViewById(2131099703));
      this.tempLayout = ((LinearLayout)this.listView.findViewById(2131099701));
      new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
      {
        public void run()
        {
          LiftPhysicalQualityTwo.this.mGuideList = new ArrayList();
          Rect localRect = new Rect();
          LiftPhysicalQualityTwo.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
          int i = localRect.top;
          LiftPhysicalQualityTwo.this.locImageView = new int[2];
          LiftPhysicalQualityTwo.this.tempLayout.setDrawingCacheEnabled(true);
          LiftPhysicalQualityTwo.this.tempLayout.getLocationInWindow(LiftPhysicalQualityTwo.this.locImageView);
          LiftPhysicalQualityTwo.this.mGuide = new GuideModel(LiftPhysicalQualityTwo.this.tempLayout.getDrawingCache(), LiftPhysicalQualityTwo.this.locImageView[0], LiftPhysicalQualityTwo.this.locImageView[1]);
          LiftPhysicalQualityTwo.this.mGuideList.add(LiftPhysicalQualityTwo.this.mGuide);
          Intent localIntent = new Intent(LiftPhysicalQualityTwo.this, GuideAct.class);
          localIntent.putExtra("pageId", 9);
          localIntent.putExtra("statusBarHeight", i);
          localIntent.putExtra("guideList", (Serializable)LiftPhysicalQualityTwo.this.mGuideList);
          LiftPhysicalQualityTwo.this.startActivity(localIntent);
        }
      }
      , 1000L);
      SharedPreferenceUtils.resetCurPage(this, false, "physical_quality");
    }
  }

  public void setLaoutPam(ImageView paramImageView, float paramFloat, int paramInt1, int paramInt2)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.setMargins((int)paramFloat, paramInt1, 0, paramInt2);
    paramImageView.setLayoutParams(localLayoutParams);
  }

  public void setLaoutPam(TextView paramTextView, float paramFloat1, float paramFloat2, int paramInt)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.setMargins((int)paramFloat1, (int)paramFloat2, 0, paramInt);
    paramTextView.setLayoutParams(localLayoutParams);
  }

  public void setLaoutPamBotton(TextView paramTextView, float paramFloat1, float paramFloat2, int paramInt)
  {
    Log.i("picooc", "magLift=" + paramFloat1);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.setMargins((int)paramFloat1, (int)paramFloat2, 0, paramInt);
    localLayoutParams.addRule(3, 2131099679);
    paramTextView.setLayoutParams(localLayoutParams);
  }

  static final class ViewHolder
  {
    LinearLayout LinearLayout;
    LinearLayout expandable;
    RelativeLayout hengtiaoRelative;
    ImageView hengtiao_bg;
    RelativeLayout item;
    ImageView jingshiImage;
    ImageView liftImage;
    LinearLayout linear;
    LinearLayout linearitem;
    TextView shuzhi;
    TextView textFiveBottom;
    TextView textFour;
    TextView textFourBottom;
    TextView textOne;
    TextView textOneBottom;
    TextView textState;
    TextView textThree;
    TextView textThreeBottom;
    TextView textTwo;
    TextView textTwoBottom;
    ImageView textline;
    ImageView translateImage;
    TextView tvLetter;
    TextView tvTitle;
    TextView verlline;
    TextView viewline;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LiftPhysicalQualityTwo
 * JD-Core Version:    0.6.2
 */
