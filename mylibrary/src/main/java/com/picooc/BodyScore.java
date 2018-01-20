package com.picooc;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.arithmetic.BodyCompositionSectionGlobal;
import com.picooc.arithmetic.ReportDirect;
import com.picooc.domain.ReportEntity;
import com.picooc.model.MainBodyScoreModel;
import com.picooc.model.MainBodyScoreModel.itemData;
import com.picooc.oldhumen.Age;
import com.picooc.utils.DateUtils;
import com.picooc.utils.NumUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.widgetAddView;
import java.util.ArrayList;
import org.achartengine.tools.ModUtils;

public class BodyScore extends PicoocActivity
  implements View.OnClickListener
{
  private TextView WeiDabiaoText;
  private MyApplication app;
  private TextView bmiText;
  private int bootomDistance;
  private LinearLayout bootomLinera;
  TextView curentweight;
  private TextView defenMiaoShu;
  private TextView defen_text;
  int heith = 800;
  int itemWight = 0;
  private TextView jingGaoText;
  private ImageView jinggaoImage;
  private TextView leftDate;
  private MainBodyScoreModel mainBodyScoreModel;
  ImageView mark;
  private LinearLayout midleLinerLayout;
  ArrayList<itemData> noStandardLists;
  private RelativeLayout relativeLayout;
  private TextView rightDate;
  private LinearLayout rightRelative;
  ArrayList<itemData> subStandardLists;
  private TextView text3;
  private TextView textFourBootom;
  private TextView textOne;
  private TextView textOneBootom;
  private TextView textThree;
  private TextView textThreeBootom;
  private TextView textTwo;
  private TextView textTwoBootom;
  private LinearLayout titleHengTiao;
  private LinearLayout translateImage;
  private int upDistance;
  int witht = 480;

  private void invitBodyScore()
  {
    if (Age.isOld(this.app))
    {
      this.rightRelative.setVisibility(0);
      this.relativeLayout.setVisibility(8);
      return;
    }
    this.relativeLayout.setVisibility(0);
    this.rightRelative.setVisibility(8);
    this.noStandardLists = this.mainBodyScoreModel.getNoStandardLists();
    this.subStandardLists = this.mainBodyScoreModel.getSubHealthLists();
    this.text3.setText(this.mainBodyScoreModel.getTitle());
    this.leftDate.setText(this.mainBodyScoreModel.getDateTilte());
    this.defen_text.setText(this.mainBodyScoreModel.getTotalNum());
    ModUtils.replaceString(this.defenMiaoShu, this, this.mainBodyScoreModel.getScoreTips(), false);
    SpannableStringBuilder localSpannableStringBuilder1 = new SpannableStringBuilder(this.mainBodyScoreModel.getNoStandardTitle());
    int i = ModUtils.cacluteString("其中", this.mainBodyScoreModel.getNoStandardTitle());
    localSpannableStringBuilder1.setSpan(new ForegroundColorSpan(Color.parseColor("#ffea00")), i, i + 1, 34);
    localSpannableStringBuilder1.setSpan(new AbsoluteSizeSpan(ModUtils.dip2px(this, 25.0F)), i, i + 1, 34);
    localSpannableStringBuilder1.insert(i, "  ");
    localSpannableStringBuilder1.insert(i + 3, " ");
    label242: label378: boolean bool1;
    label391: int j;
    int k;
    label409: itemData localitemData2;
    LinearLayout localLinearLayout2;
    if (i != 1)
    {
      this.WeiDabiaoText.setText(localSpannableStringBuilder1);
      if (this.subStandardLists.size() <= 0)
        break label526;
      this.bootomLinera.setVisibility(0);
      int m = ModUtils.cacluteString("标为", this.mainBodyScoreModel.getSubHealthTitle());
      SpannableStringBuilder localSpannableStringBuilder2 = new SpannableStringBuilder(this.mainBodyScoreModel.getSubHealthTitle());
      localSpannableStringBuilder2.setSpan(new ForegroundColorSpan(Color.parseColor("#ffea00")), m, m + 1, 34);
      localSpannableStringBuilder2.setSpan(new AbsoluteSizeSpan(ModUtils.dip2px(this, 20.0F)), m, m + 1, 34);
      localSpannableStringBuilder2.insert(m, "  ");
      localSpannableStringBuilder2.insert(m + 3, " ");
      if (m == 1)
        break label509;
      this.jingGaoText.setText(localSpannableStringBuilder2);
      if (this.subStandardLists.size() <= 0)
        break label556;
      bool1 = true;
      j = 0;
      if (j < this.noStandardLists.size())
        break label562;
      k = 0;
      if (k < this.subStandardLists.size())
      {
        localitemData2 = (itemData)this.subStandardLists.get(k);
        localLinearLayout2 = this.bootomLinera;
        if (k != -1 + this.subStandardLists.size())
          break label646;
      }
    }
    label646: for (boolean bool3 = true; ; bool3 = false)
    {
      widgetAddView.addview(localLinearLayout2, this, bool3, localitemData2.imageID, localitemData2.itemString, localitemData2.itemValue, localitemData2.state);
      k++;
      break label409;
      break;
      this.WeiDabiaoText.setText(this.mainBodyScoreModel.getNoStandardTitle());
      break label242;
      label509: this.jingGaoText.setText(this.mainBodyScoreModel.getSubHealthTitle());
      break label378;
      label526: this.bootomLinera.setVisibility(8);
      this.jingGaoText.setVisibility(8);
      this.jinggaoImage.setVisibility(8);
      break label378;
      label556: bool1 = false;
      break label391;
      label562: itemData localitemData1 = (itemData)this.noStandardLists.get(j);
      LinearLayout localLinearLayout1 = this.midleLinerLayout;
      if (j == -1 + this.noStandardLists.size());
      for (boolean bool2 = true; ; bool2 = false)
      {
        widgetAddView.addview(localLinearLayout1, this, bool1, bool2, localitemData1.imageID, localitemData1.itemString, localitemData1.itemValue, localitemData1.changeValue, localitemData1.state);
        j++;
        break;
      }
    }
  }

  private void scaleTextSizeForOld()
  {
    MyApplication localMyApplication = (MyApplication)getApplication();
    if (Age.isOld(localMyApplication))
    {
      Age.setTextSize(localMyApplication, this.rightDate);
      Age.setTextSize(localMyApplication, this.defen_text);
      Age.setTextSize(localMyApplication, this.bmiText);
      float f = getResources().getDimension(2131230871);
      this.textOneBootom.setTextSize(f);
      this.textTwoBootom.setTextSize(f);
      this.textThreeBootom.setTextSize(f);
      this.textFourBootom.setTextSize(f);
      Age.setTextSize(localMyApplication, this.curentweight);
      Age.setTextSizeSmall(localMyApplication, findViewById(2131100372));
      Age.setTextSizeSmall(localMyApplication, findViewById(2131100373));
      Age.setTextSizeSmall(localMyApplication, findViewById(2131100374));
      ((ImageView)findViewById(2131099679)).setBackgroundResource(2130838086);
    }
  }

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968576, 2130968581);
  }

  public void init()
  {
    this.app = ((MyApplication)getApplication().getApplicationContext());
    this.midleLinerLayout = ((LinearLayout)findViewById(2131100394));
    this.bootomLinera = ((LinearLayout)findViewById(2131100398));
    this.leftDate = ((TextView)findViewById(2131100383));
    this.defen_text = ((TextView)findViewById(2131100386));
    this.leftDate.setTypeface(ModUtils.getTypeface(this));
    this.defen_text.setTypeface(ModUtils.getTypeface(this));
    this.defenMiaoShu = ((TextView)findViewById(2131100391));
    this.defenMiaoShu.setTypeface(ModUtils.getTypeface(this));
    this.WeiDabiaoText = ((TextView)findViewById(2131100393));
    this.WeiDabiaoText.setTypeface(ModUtils.getTypeface(this));
    this.jingGaoText = ((TextView)findViewById(2131100397));
    this.jingGaoText.setTypeface(ModUtils.getTypeface(this));
    this.rightRelative = ((LinearLayout)findViewById(2131100370));
    this.rightDate = ((TextView)findViewById(2131100371));
    this.rightDate.setTypeface(ModUtils.getTypeface(this));
    this.jinggaoImage = ((ImageView)findViewById(2131100396));
    this.text3 = ((TextView)findViewById(2131100096));
    this.text3.setTypeface(ModUtils.getTypeface(this));
    this.relativeLayout = ((RelativeLayout)findViewById(2131100381));
    this.mark = ((ImageView)findViewById(2131099691));
    long l1 = this.app.getTodayBody().getTime();
    long l2 = DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), l1);
    if (l2 == 0L)
    {
      this.text3.setText("今日BMI");
      this.rightDate.setText(DateUtils.changeTimeStampToFormatTime(l1, "MM月dd日"));
    }
    while (true)
    {
      this.curentweight = ((TextView)findViewById(2131099690));
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      this.heith = localDisplayMetrics.heightPixels;
      this.witht = localDisplayMetrics.widthPixels;
      this.bootomDistance = ModUtils.dip2px(this, 20.0F);
      this.textOne = ((TextView)findViewById(2131100372));
      this.textTwo = ((TextView)findViewById(2131100373));
      this.textThree = ((TextView)findViewById(2131100374));
      this.translateImage = ((LinearLayout)findViewById(2131099689));
      this.textOne.setTypeface(ModUtils.getTypeface(this));
      this.textTwo.setTypeface(ModUtils.getTypeface(this));
      this.textThree.setTypeface(ModUtils.getTypeface(this));
      this.curentweight.setTypeface(ModUtils.getTypeface(this));
      this.textOneBootom = ((TextView)findViewById(2131100375));
      this.textTwoBootom = ((TextView)findViewById(2131100376));
      this.textThreeBootom = ((TextView)findViewById(2131100377));
      this.textFourBootom = ((TextView)findViewById(2131100378));
      this.bmiText = ((TextView)findViewById(2131100380));
      this.bmiText.setTypeface(ModUtils.getTypeface(this));
      invitBmi();
      scaleTextSizeForOld();
      return;
      this.rightDate.setText(l2 + "天前（" + DateUtils.changeTimeStampToFormatTime(l1, "MM月dd日") + ")");
      this.text3.setText(DateUtils.changeTimeStampToFormatTime(l1, "MM月dd日") + "BMI");
    }
  }

  public void invitBmi()
  {
    ReportEntity localReportEntity = ReportDirect.judgeBMIByRole(this.app.getCurrentRole(), this.app.getTodayBody());
    this.curentweight.setText(this.app.getTodayBody().getBmi());
    this.mark.setImageResource(BodyCompositionSectionGlobal.getBMIMarkeImage(localReportEntity.getStateCode()));
    this.curentweight.setTextColor(BodyCompositionSectionGlobal.getBMIMarkeColor(localReportEntity.getStateCode()));
    this.curentweight.setBackgroundResource(BodyCompositionSectionGlobal.getBMIMarkekunag(localReportEntity.getStateCode()));
    String[] arrayOfString = BodyCompositionSectionGlobal.BMI;
    float[] arrayOfFloat = localReportEntity.getRegionArray();
    this.textOne.setText(NumUtils.roundValue(arrayOfFloat[0]));
    this.textTwo.setText(NumUtils.roundValue(arrayOfFloat[1]));
    this.textThree.setText(NumUtils.roundValue(arrayOfFloat[2]));
    this.textOneBootom.setText(arrayOfString[0]);
    this.textTwoBootom.setText(arrayOfString[1]);
    this.textThreeBootom.setText(arrayOfString[2]);
    this.textFourBootom.setText(arrayOfString[3]);
    this.itemWight = ((this.witht - ModUtils.dip2px(this, 41.0F)) / 4);
    this.upDistance = ModUtils.dip2px(this, 30.0F);
    setLaoutPamUp(this.textOne, this.itemWight - this.upDistance / 5);
    setLaoutPamUp(this.textTwo, 2 * this.itemWight - this.upDistance / 5);
    setLaoutPamUp(this.textThree, 3 * this.itemWight - this.upDistance / 5);
    AnimUtils.LiftandRightMove(this.translateImage, 0, this.itemWight * (-1 + localReportEntity.getStateCode()) + this.itemWight / 2 - ModUtils.dip2px(this, 60.0F) / 2, 0);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131099946:
    case 2131100094:
    default:
      return;
    case 2131099980:
    }
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903180);
    this.mainBodyScoreModel = ((MainBodyScoreModel)getIntent().getSerializableExtra("model"));
    init();
    invitBodyScore();
  }

  public void setLaoutPam(TextView paramTextView, int paramInt1, int paramInt2)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt2, -2);
    localLayoutParams.setMargins(paramInt1, 0, 0, 0);
    paramTextView.setGravity(17);
    paramTextView.setLayoutParams(localLayoutParams);
    paramTextView.setPadding(0, ModUtils.dip2px(this, 30.0F), 0, 0);
  }

  public void setLaoutPamUp(TextView paramTextView, int paramInt)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.setMargins(paramInt, ModUtils.dip2px(this, 8.0F), 0, 2);
    paramTextView.setGravity(17);
    paramTextView.setLayoutParams(localLayoutParams);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BodyScore
 * JD-Core Version:    0.6.2
 */
