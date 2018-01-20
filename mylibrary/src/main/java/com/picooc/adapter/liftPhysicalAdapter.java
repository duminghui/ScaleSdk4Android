package com.picooc.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.MyApplication;
import com.picooc.arithmetic.BodyCompositionSectionGlobal;
import com.picooc.domain.ComponentBodyTypeEnum;
import com.picooc.domain.ReportEntity;
import com.picooc.model.BodyCompositionAnalysisModel;
import com.picooc.utils.NumUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.widgetAddView;
import java.util.ArrayList;
import org.achartengine.tools.ModUtils;

public class liftPhysicalAdapter extends BaseAdapter
{
  private ArrayList<Object> data;
  private int fatWight = 123;
  private AnimUtils mAnim;
  private Context mContext;
  private BodyCompositionAnalysisModel model;
  private int nowHeiht = 800;
  private int nowWidth = 480;
  widgetAddView wigeht;

  public liftPhysicalAdapter(Context paramContext, BodyCompositionAnalysisModel paramBodyCompositionAnalysisModel)
  {
    this.mContext = paramContext;
    this.model = paramBodyCompositionAnalysisModel;
    this.data = paramBodyCompositionAnalysisModel.getBodyCompositionData();
    this.wigeht = new widgetAddView(paramContext);
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((Activity)paramContext).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.nowWidth = localDisplayMetrics.widthPixels;
    this.nowHeiht = localDisplayMetrics.heightPixels;
    this.mAnim = new AnimUtils(paramContext);
  }

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
    paramViewHolder.textState.setPadding(10, 0, 10, 0);
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getBmrStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textline.setVisibility(8);
    paramViewHolder.linear.setClickable(true);
    paramViewHolder.linear.setEnabled(true);
    paramViewHolder.shuzhi.setText(NumUtils.roundValue(paramReportEntity.getNum()) + "kcal");
    String[] arrayOfString = paramReportEntity.getMessages();
    int i = arrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        notifyDataSetChanged();
        return;
      }
      String str = arrayOfString[j];
      this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  private void initBodyFat(ViewHolder paramViewHolder, ReportEntity paramReportEntity, View paramView)
  {
    paramView.setVisibility(0);
    this.fatWight = ((-24 + (this.nowWidth - ModUtils.dip2px(this.mContext, 50.0F))) / 5);
    paramViewHolder.hengtiaoRelative.setVisibility(0);
    paramViewHolder.shuzhi.setVisibility(0);
    paramViewHolder.textState.setVisibility(0);
    paramViewHolder.translateImage.setImageResource(BodyCompositionSectionGlobal.getFatMarkeImage(paramReportEntity.getStateCode()));
    paramViewHolder.hengtiao_bg.setBackgroundResource(2130838218);
    paramViewHolder.liftImage.setImageResource(BodyCompositionSectionGlobal.getFatLeftImage(paramReportEntity.getStandardOrNot()));
    paramViewHolder.tvTitle.setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getFatStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setBackgroundResource(BodyCompositionSectionGlobal.getFatStateImage(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getFatStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setPadding(10, 0, 10, 0);
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
    setLaoutPam(paramViewHolder.textOne, 15 + this.fatWight, 0, 2);
    setLaoutPam(paramViewHolder.textTwo, 15 + 2 * this.fatWight, 0, 2);
    setLaoutPam(paramViewHolder.textThree, 15 + 3 * this.fatWight, 0, 2);
    setLaoutPam(paramViewHolder.textFour, 15 + 4 * this.fatWight, 0, 2);
    setLaoutPamBotton(paramViewHolder.textOneBottom, 23 + this.fatWight / 2, 0, 2);
    setLaoutPamBotton(paramViewHolder.textTwoBottom, 17 + this.fatWight + this.fatWight / 2, 2, 0);
    setLaoutPamBotton(paramViewHolder.textThreeBottom, 19 + 2 * this.fatWight + this.fatWight / 2, 2, 0);
    setLaoutPamBotton(paramViewHolder.textFourBottom, 19 + 3 * this.fatWight + this.fatWight / 2, 2, 0);
    setLaoutPamBotton(paramViewHolder.textFiveBottom, 19 + 4 * this.fatWight + this.fatWight / 2, 2, 0);
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
      this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  private void initBoneMass(ViewHolder paramViewHolder, ReportEntity paramReportEntity, View paramView)
  {
    paramView.setVisibility(0);
    this.fatWight = ((-24 + (this.nowWidth - ModUtils.dip2px(this.mContext, 50.0F))) / 3);
    paramViewHolder.hengtiaoRelative.setVisibility(0);
    paramViewHolder.shuzhi.setVisibility(0);
    paramViewHolder.textState.setVisibility(0);
    paramViewHolder.translateImage.setImageResource(BodyCompositionSectionGlobal.getBoneMarkeImage(paramReportEntity.getStateCode()));
    paramViewHolder.hengtiao_bg.setBackgroundResource(2130838205);
    paramViewHolder.liftImage.setImageResource(BodyCompositionSectionGlobal.getBoneLeftImage(paramReportEntity.getStandardOrNot()));
    paramViewHolder.tvTitle.setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getBoneStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setBackgroundResource(BodyCompositionSectionGlobal.getBoneStateImage(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getBoneStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setPadding(10, 0, 10, 0);
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
    setLaoutPam(paramViewHolder.textOne, 15 + this.fatWight, 0, 2);
    setLaoutPam(paramViewHolder.textTwo, 15 + 2 * this.fatWight, 0, 2);
    setLaoutPamBotton(paramViewHolder.textOneBottom, 23 + this.fatWight / 2, 0, 2);
    setLaoutPamBotton(paramViewHolder.textTwoBottom, 17 + this.fatWight + this.fatWight / 2, 2, 0);
    setLaoutPamBotton(paramViewHolder.textThreeBottom, 19 + 2 * this.fatWight + this.fatWight / 2, 2, 0);
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
      this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  private void initHead1(ViewHolder paramViewHolder, View paramView)
  {
    if (this.model.get_noStandardNum() != 0)
    {
      paramView.setVisibility(0);
      paramViewHolder.verlline.setVisibility(0);
      paramViewHolder.textline.setVisibility(8);
      paramViewHolder.shuzhi.setVisibility(8);
      paramViewHolder.textState.setVisibility(8);
      paramViewHolder.tvTitle.setText("共检测8项指标，未达标 " + this.model.get_noStandardNum() + " 项");
      paramViewHolder.linear.setClickable(false);
      paramViewHolder.linear.setEnabled(false);
      paramViewHolder.liftImage.setImageResource(2130838214);
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
    if (this.model.get_standardNum() == 8)
      paramViewHolder.tvTitle.setText("共检测8项指标，已全部达标");
    while (true)
    {
      paramViewHolder.linear.setClickable(false);
      paramViewHolder.textState.setPadding(10, 0, 10, 0);
      paramViewHolder.liftImage.setImageResource(2130838181);
      return;
      paramViewHolder.tvTitle.setText("已达标" + this.model.get_standardNum() + "项");
    }
  }

  private void initInFat(ViewHolder paramViewHolder, ReportEntity paramReportEntity, View paramView)
  {
    paramView.setVisibility(0);
    this.fatWight = ((-24 + (this.nowWidth - ModUtils.dip2px(this.mContext, 50.0F))) / 3);
    paramViewHolder.hengtiaoRelative.setVisibility(0);
    paramViewHolder.shuzhi.setVisibility(0);
    paramViewHolder.textState.setVisibility(0);
    paramViewHolder.translateImage.setImageResource(BodyCompositionSectionGlobal.getInfatMarkeImage(paramReportEntity.getStateCode()));
    paramViewHolder.hengtiao_bg.setBackgroundResource(2130838195);
    paramViewHolder.liftImage.setImageResource(BodyCompositionSectionGlobal.getInfatLeftImage(paramReportEntity.getStandardOrNot()));
    paramViewHolder.tvTitle.setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getInfatStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setBackgroundResource(BodyCompositionSectionGlobal.getInfatStateImage(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getInfatStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setPadding(10, 0, 10, 0);
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
    setLaoutPam(paramViewHolder.textOne, 15 + this.fatWight, 0, 2);
    setLaoutPam(paramViewHolder.textTwo, 15 + 2 * this.fatWight, 0, 2);
    setLaoutPamBotton(paramViewHolder.textOneBottom, 23 + this.fatWight / 2, 0, 2);
    setLaoutPamBotton(paramViewHolder.textTwoBottom, 17 + this.fatWight + this.fatWight / 2, 2, 0);
    setLaoutPamBotton(paramViewHolder.textThreeBottom, 19 + 2 * this.fatWight + this.fatWight / 2, 2, 0);
    paramViewHolder.textline.setVisibility(8);
    paramViewHolder.linear.setClickable(true);
    paramViewHolder.linear.setEnabled(true);
    paramViewHolder.shuzhi.setText(NumUtils.roundValue(paramReportEntity.getNum()));
    String[] arrayOfString2 = paramReportEntity.getMessages();
    int i = arrayOfString2.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      String str = arrayOfString2[j];
      this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  private void initLine(ViewHolder paramViewHolder, View paramView)
  {
    if (this.model.get_noStandardNum() != 0)
    {
      paramView.setVisibility(0);
      paramViewHolder.verlline.setVisibility(8);
      paramViewHolder.textline.setVisibility(0);
      paramViewHolder.linear.setVisibility(8);
      paramViewHolder.expandable.setVisibility(8);
    }
  }

  private void initMuscle(ViewHolder paramViewHolder, ReportEntity paramReportEntity, View paramView)
  {
    paramView.setVisibility(0);
    this.fatWight = ((-24 + (this.nowWidth - ModUtils.dip2px(this.mContext, 50.0F))) / 3);
    paramViewHolder.hengtiaoRelative.setVisibility(0);
    paramViewHolder.shuzhi.setVisibility(0);
    paramViewHolder.textState.setVisibility(0);
    paramViewHolder.translateImage.setImageResource(BodyCompositionSectionGlobal.getMuscleMarkeImage(paramReportEntity.getStateCode()));
    paramViewHolder.hengtiao_bg.setBackgroundResource(2130838205);
    paramViewHolder.liftImage.setImageResource(BodyCompositionSectionGlobal.getMuscleLeftImage(paramReportEntity.getStandardOrNot()));
    paramViewHolder.tvTitle.setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getMuscleStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setBackgroundResource(BodyCompositionSectionGlobal.getMuscleStateImage(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getMuscleStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setPadding(10, 0, 10, 0);
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
    setLaoutPam(paramViewHolder.textOne, 15 + this.fatWight, 0, 2);
    setLaoutPam(paramViewHolder.textTwo, 15 + 2 * this.fatWight, 0, 2);
    setLaoutPamBotton(paramViewHolder.textOneBottom, 23 + this.fatWight / 2, 0, 2);
    setLaoutPamBotton(paramViewHolder.textTwoBottom, 17 + this.fatWight + this.fatWight / 2, 2, 0);
    setLaoutPamBotton(paramViewHolder.textThreeBottom, 19 + 2 * this.fatWight + this.fatWight / 2, 2, 0);
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
      this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  private void initProtein(ViewHolder paramViewHolder, ReportEntity paramReportEntity, View paramView)
  {
    paramView.setVisibility(0);
    this.fatWight = ((-24 + (this.nowWidth - ModUtils.dip2px(this.mContext, 50.0F))) / 3);
    paramViewHolder.hengtiaoRelative.setVisibility(0);
    paramViewHolder.shuzhi.setVisibility(0);
    paramViewHolder.textState.setVisibility(0);
    paramViewHolder.translateImage.setImageResource(BodyCompositionSectionGlobal.getProteinMarkeImage(paramReportEntity.getStateCode()));
    paramViewHolder.hengtiao_bg.setBackgroundResource(2130838205);
    paramViewHolder.liftImage.setImageResource(BodyCompositionSectionGlobal.getProteinLeftImage(paramReportEntity.getStandardOrNot()));
    paramViewHolder.tvTitle.setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getProteinStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setBackgroundResource(BodyCompositionSectionGlobal.getProteinStateImage(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getProteinStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setPadding(10, 0, 10, 0);
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
    setLaoutPam(paramViewHolder.textOne, 15 + this.fatWight, 0, 2);
    setLaoutPam(paramViewHolder.textTwo, 15 + 2 * this.fatWight, 0, 2);
    setLaoutPamBotton(paramViewHolder.textOneBottom, 23 + this.fatWight / 2, 0, 2);
    setLaoutPamBotton(paramViewHolder.textTwoBottom, 17 + this.fatWight + this.fatWight / 2, 2, 0);
    setLaoutPamBotton(paramViewHolder.textThreeBottom, 19 + 2 * this.fatWight + this.fatWight / 2, 2, 0);
    paramViewHolder.textline.setVisibility(8);
    paramViewHolder.linear.setClickable(true);
    paramViewHolder.linear.setEnabled(true);
    paramViewHolder.shuzhi.setText(NumUtils.roundValue(paramReportEntity.getNum()) + "%");
    String[] arrayOfString2 = paramReportEntity.getMessages();
    int i = arrayOfString2.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        notifyDataSetChanged();
        return;
      }
      String str = arrayOfString2[j];
      this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  private void initWater(ViewHolder paramViewHolder, ReportEntity paramReportEntity, View paramView)
  {
    paramView.setVisibility(0);
    this.fatWight = ((-24 + (this.nowWidth - ModUtils.dip2px(this.mContext, 50.0F))) / 3);
    paramViewHolder.hengtiaoRelative.setVisibility(0);
    paramViewHolder.shuzhi.setVisibility(0);
    paramViewHolder.textState.setVisibility(0);
    paramViewHolder.translateImage.setImageResource(BodyCompositionSectionGlobal.getWaterMarkeImage(paramReportEntity.getStateCode()));
    paramViewHolder.hengtiao_bg.setBackgroundResource(2130838205);
    paramViewHolder.liftImage.setImageResource(BodyCompositionSectionGlobal.getWaterLeftImage(paramReportEntity.getStandardOrNot()));
    paramViewHolder.tvTitle.setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getWaterStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setBackgroundResource(BodyCompositionSectionGlobal.getWaterStateImage(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getWaterStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setPadding(10, 0, 10, 0);
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
    setLaoutPam(paramViewHolder.textOne, 15 + this.fatWight, 0, 2);
    setLaoutPam(paramViewHolder.textTwo, 15 + 2 * this.fatWight, 0, 2);
    setLaoutPamBotton(paramViewHolder.textOneBottom, 23 + this.fatWight / 2, 0, 2);
    setLaoutPamBotton(paramViewHolder.textTwoBottom, 17 + this.fatWight + this.fatWight / 2, 2, 0);
    setLaoutPamBotton(paramViewHolder.textThreeBottom, 19 + 2 * this.fatWight + this.fatWight / 2, 2, 0);
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
      this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  private void initWeight(ViewHolder paramViewHolder, ReportEntity paramReportEntity, View paramView)
  {
    paramView.setVisibility(0);
    this.fatWight = ((-24 + (this.nowWidth - ModUtils.dip2px(this.mContext, 50.0F))) / 3);
    paramViewHolder.shuzhi.setVisibility(0);
    paramViewHolder.textState.setVisibility(0);
    paramViewHolder.hengtiaoRelative.setVisibility(0);
    paramViewHolder.translateImage.setImageResource(BodyCompositionSectionGlobal.getWeightMrakeImage(paramReportEntity.getStateCode()));
    paramViewHolder.hengtiao_bg.setBackgroundResource(2130838210);
    paramViewHolder.liftImage.setImageResource(BodyCompositionSectionGlobal.getWeightLeftImage(paramReportEntity.getStandardOrNot(), ((MyApplication)this.mContext.getApplicationContext()).getCurrentRole().getSex()));
    paramViewHolder.tvTitle.setText(ComponentBodyTypeEnum.getEmnuByIndex(paramReportEntity.getReportType()).getName());
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getWeightStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setBackgroundResource(BodyCompositionSectionGlobal.getWeightStateImage(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setText(BodyCompositionSectionGlobal.getWeightStateName(paramReportEntity.getStateCode()));
    paramViewHolder.textState.setPadding(10, 0, 10, 0);
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
    setLaoutPam(paramViewHolder.textOne, 15 + this.fatWight, 0, 2);
    setLaoutPam(paramViewHolder.textTwo, 15 + 2 * this.fatWight, 0, 2);
    setLaoutPamBotton(paramViewHolder.textOneBottom, 23 + this.fatWight / 2, 0, 2);
    setLaoutPamBotton(paramViewHolder.textTwoBottom, 17 + this.fatWight + this.fatWight / 2, 2, 0);
    setLaoutPamBotton(paramViewHolder.textThreeBottom, 19 + 2 * this.fatWight + this.fatWight / 2, 2, 0);
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
      this.wigeht.addview(paramViewHolder.LinearLayout, str, 30, 0);
    }
  }

  public int getCount()
  {
    return this.data.size();
  }

  public Object getItem(int paramInt)
  {
    return this.data.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    ViewHolder localViewHolder = new ViewHolder();
    View localView = LayoutInflater.from(this.mContext).inflate(2130903054, null);
    localViewHolder.tvTitle = ((TextView)localView.findViewById(2131099728));
    localViewHolder.textline = ((ImageView)localView.findViewById(2131099719));
    localViewHolder.shuzhi = ((TextView)localView.findViewById(2131099729));
    localViewHolder.verlline = ((TextView)localView.findViewById(2131099732));
    localViewHolder.textState = ((TextView)localView.findViewById(2131099730));
    localViewHolder.textOne = ((TextView)localView.findViewById(2131099680));
    localViewHolder.textTwo = ((TextView)localView.findViewById(2131099681));
    localViewHolder.textThree = ((TextView)localView.findViewById(2131099682));
    localViewHolder.textFour = ((TextView)localView.findViewById(2131099683));
    localViewHolder.hengtiao_bg = ((ImageView)localView.findViewById(2131099679));
    localViewHolder.hengtiaoRelative = ((RelativeLayout)localView.findViewById(2131099733));
    localViewHolder.textOneBottom = ((TextView)localView.findViewById(2131099684));
    localViewHolder.textTwoBottom = ((TextView)localView.findViewById(2131099685));
    localViewHolder.textThreeBottom = ((TextView)localView.findViewById(2131099686));
    localViewHolder.textFourBottom = ((TextView)localView.findViewById(2131099687));
    localViewHolder.textFiveBottom = ((TextView)localView.findViewById(2131099688));
    localViewHolder.liftImage = ((ImageView)localView.findViewById(2131099702));
    localViewHolder.translateImage = ((ImageView)localView.findViewById(2131099689));
    localViewHolder.linear = ((LinearLayout)localView.findViewById(2131099701));
    localViewHolder.expandable = ((LinearLayout)localView.findViewById(2131099703));
    localView.setTag(localViewHolder);
    localViewHolder.item = ((RelativeLayout)localView.findViewById(2131099700));
    localViewHolder.LinearLayout = ((LinearLayout)localView.findViewById(2131099705));
    localViewHolder.shuzhi.setTypeface(ModUtils.getTypeface(this.mContext));
    localViewHolder.tvTitle.setTypeface(ModUtils.getTypeface(this.mContext));
    localViewHolder.textOne.setTypeface(ModUtils.getTypeface(this.mContext));
    localViewHolder.textTwo.setTypeface(ModUtils.getTypeface(this.mContext));
    localViewHolder.textThree.setTypeface(ModUtils.getTypeface(this.mContext));
    localViewHolder.textFour.setTypeface(ModUtils.getTypeface(this.mContext));
    if (paramInt == this.model.get_noStandardNum())
    {
      localViewHolder.verlline.setVisibility(8);
      if (!(getItem(paramInt) instanceof Integer))
        break label596;
      if (((Integer)getItem(paramInt)).intValue() != 1)
        break label544;
      initHead1(localViewHolder, localView);
    }
    label544: label596: 
    while (!(getItem(paramInt) instanceof ReportEntity))
    {
      do
      {
        return localView;
        localViewHolder.verlline.setVisibility(0);
        break;
        if (((Integer)getItem(paramInt)).intValue() == 2)
        {
          initLine(localViewHolder, localView);
          return localView;
        }
      }
      while (((Integer)getItem(paramInt)).intValue() != 3);
      initHead2(localViewHolder, localView);
      return localView;
    }
    ReportEntity localReportEntity = (ReportEntity)getItem(paramInt);
    switch ($SWITCH_TABLE$com$picooc$domain$ComponentBodyTypeEnum()[ComponentBodyTypeEnum.getEmnuByIndex(localReportEntity.getReportType()).ordinal()])
    {
    default:
      return localView;
    case 1:
      initMuscle(localViewHolder, localReportEntity, localView);
      return localView;
    case 8:
      initBMR(localViewHolder, localReportEntity, localView);
      return localView;
    case 7:
      initProtein(localViewHolder, localReportEntity, localView);
      return localView;
    case 3:
      initBoneMass(localViewHolder, localReportEntity, localView);
      return localView;
    case 4:
      initWeight(localViewHolder, localReportEntity, localView);
      return localView;
    case 2:
      initBodyFat(localViewHolder, localReportEntity, localView);
      return localView;
    case 6:
      initWater(localViewHolder, localReportEntity, localView);
      return localView;
    case 5:
    }
    initInFat(localViewHolder, localReportEntity, localView);
    return localView;
  }

  public void setLaoutPam(ImageView paramImageView, int paramInt1, int paramInt2, int paramInt3)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.setMargins(paramInt1, paramInt2, 0, paramInt3);
    paramImageView.setLayoutParams(localLayoutParams);
  }

  public void setLaoutPam(TextView paramTextView, int paramInt1, int paramInt2, int paramInt3)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.setMargins(paramInt1, paramInt2, 0, paramInt3);
    paramTextView.setLayoutParams(localLayoutParams);
  }

  public void setLaoutPamBotton(TextView paramTextView, int paramInt1, int paramInt2, int paramInt3)
  {
    Log.i("picooc", "magLift=" + paramInt1);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.setMargins(paramInt1, paramInt2, 0, paramInt3);
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
 * Qualified Name:     liftPhysicalAdapter
 * JD-Core Version:    0.6.2
 */
