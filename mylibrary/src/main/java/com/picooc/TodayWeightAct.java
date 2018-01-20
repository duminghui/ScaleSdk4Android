package com.picooc;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.adapter.TodayWeightAdpter;
import com.picooc.adapter.TodayWeightAdpter.deleteListener;
import com.picooc.arithmetic.BodyCompositionSectionGlobal;
import com.picooc.arithmetic.ReportDirect;
import com.picooc.db.OperationDB;
import com.picooc.domain.BodyFatReport;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.ReportEntity;
import com.picooc.guide.GuideAct;
import com.picooc.guide.GuideModel;
import com.picooc.oldhumen.Age;
import com.picooc.utils.DateUtils;
import com.picooc.utils.NumUtils;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.AnimUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.achartengine.tools.ModUtils;

public class TodayWeightAct extends PicoocActivity
  implements deleteListener
{
  AnimUtils anim;
  private MyApplication app;
  ImageView back;
  ImageView bianjiImage;
  TextView bmiText;
  private int bootomDistance;
  private int clickID = 1;
  private List<BodyIndex> contentStrings = new ArrayList();
  TextView curentweight;
  private ImageView delImageView;
  int heith = 800;
  private Boolean isOpen = Boolean.valueOf(true);
  int itemWight = 0;
  LinearLayout linearBottom;
  LinearLayout linearUp;
  private ListView listView;
  private LinearLayout ll_receive;
  private LinearLayout ll_send;
  private int[] locationDelImageView;
  private GuideModel mGuide;
  private List<GuideModel> mGuideList;
  private TodayWeightAdpter mMyAnimListAdapter;
  private int mOldAgeTextSize;
  ImageView mark;
  public View.OnClickListener monClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      case 2131100420:
      case 2131100745:
      default:
      case 2131100099:
      case 2131099980:
      case 2131100718:
      case 2131100438:
      case 2131100441:
      }
      do
      {
        do
        {
          return;
          if (TodayWeightAct.this.isOpen.booleanValue())
          {
            TodayWeightAct.this.isOpen = Boolean.valueOf(false);
            TodayWeightAct.this.bianjiImage.setImageResource(2130837537);
            TodayWeightAct.this.mMyAnimListAdapter.setBeanlen(Boolean.valueOf(false));
            TodayWeightAct.this.mMyAnimListAdapter.notifyDataSetChanged();
            return;
          }
          TodayWeightAct.this.isOpen = Boolean.valueOf(true);
          TodayWeightAct.this.mMyAnimListAdapter.setBeanlen(Boolean.valueOf(true));
          TodayWeightAct.this.mMyAnimListAdapter.notifyDataSetChanged();
          TodayWeightAct.this.bianjiImage.setImageResource(2130837538);
          return;
          TodayWeightAct.this.finish();
          return;
          TodayWeightAct.this.anim.missAnima(TodayWeightAct.this.zhidaole, 200L);
          TodayWeightAct.this.anim.upMove(TodayWeightAct.this.listView, 0, -TodayWeightAct.this.zhidaole.getHeight(), 400);
          SharedPreferenceUtils.saveShowImageTime(TodayWeightAct.this);
          return;
        }
        while (TodayWeightAct.this.clickID == 1);
        TodayWeightAct.this.ll_receive.setBackgroundResource(2130837970);
        TodayWeightAct.this.ll_send.setBackgroundResource(2130837973);
        TodayWeightAct.this.clickID = 1;
        TodayWeightAct.this.rightRelative.setVisibility(8);
        if (TodayWeightAct.this.contentStrings.size() > 0)
        {
          TodayWeightAct.this.linearUp.setVisibility(0);
          TodayWeightAct.this.linearBottom.setVisibility(8);
          return;
        }
        TodayWeightAct.this.linearUp.setVisibility(8);
        TodayWeightAct.this.linearBottom.setVisibility(0);
        return;
      }
      while (TodayWeightAct.this.clickID == 2);
      TodayWeightAct.this.ll_send.setBackgroundResource(2130837972);
      TodayWeightAct.this.ll_receive.setBackgroundResource(2130837971);
      TodayWeightAct.this.clickID = 2;
      TodayWeightAct.this.rightRelative.setVisibility(0);
      TodayWeightAct.this.linearUp.setVisibility(8);
      TodayWeightAct.this.linearBottom.setVisibility(8);
    }
  };
  private int nListItem = 0;
  private LinearLayout oldTishi;
  private TextView rightDate;
  private LinearLayout rightRelative;
  private TextView textFourBootom;
  TextView textNow;
  private TextView textOne;
  private TextView textOneBootom;
  private TextView textThree;
  private TextView textThreeBootom;
  private TextView textTwo;
  private TextView textTwoBootom;
  private LinearLayout titleHengTiao;
  private LinearLayout translateImage;
  private TextView tv_receive;
  private TextView tv_send;
  private int upDistance;
  TextView weightCount;
  TextView weightState;
  int witht = 480;
  RelativeLayout zhidaole;
  Button zhidaoleclick;

  private void addOldBtn()
  {
    Age.setTextSize(this, findViewById(2131100713));
    Age.setTextSize(this, findViewById(2131100720));
    TextView localTextView = (TextView)findViewById(2131100721);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(localTextView.getLayoutParams());
    localLayoutParams.setMargins(0, 0, 0, 0);
    localTextView.setLayoutParams(localLayoutParams);
    Age.setTextSize(this, localTextView);
  }

  public void delete()
  {
    refruse();
  }

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968576, 2130968581);
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
    setLaoutPam(this.textOneBootom, this.itemWight / 2 - this.bootomDistance / 5, this.bootomDistance);
    setLaoutPam(this.textTwoBootom, this.itemWight + this.itemWight / 2 - this.bootomDistance / 5, this.bootomDistance);
    setLaoutPam(this.textThreeBootom, 2 * this.itemWight + this.itemWight / 2 - this.bootomDistance / 5, this.bootomDistance);
    setLaoutPam(this.textFourBootom, 3 * this.itemWight + this.itemWight / 2 - ModUtils.dip2px(this, 40.0F) / 4, ModUtils.dip2px(this, 40.0F));
    setLaoutPamUp(this.textOne, this.itemWight - this.upDistance / 5);
    setLaoutPamUp(this.textTwo, 2 * this.itemWight - this.upDistance / 5);
    setLaoutPamUp(this.textThree, 3 * this.itemWight - this.upDistance / 5);
    AnimUtils.LiftandRightMove(this.translateImage, 0, this.itemWight * (-1 + localReportEntity.getStateCode()) + this.itemWight / 2 - ModUtils.dip2px(this, 60.0F) / 2, 0);
  }

  public void invitlist()
  {
    this.contentStrings = OperationDB.selectDayValuesByTimestampAndRid(this, System.currentTimeMillis(), this.app.getCurrentRole().getRole_id());
  }

  public void notDelete()
  {
    this.bianjiImage.setImageResource(2130837536);
    this.bianjiImage.setClickable(false);
    this.bianjiImage.setEnabled(false);
    this.mMyAnimListAdapter.setBeanlen(Boolean.valueOf(true));
    this.mMyAnimListAdapter.notifyDataSetChanged();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903226);
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.heith = localDisplayMetrics.heightPixels;
    this.witht = localDisplayMetrics.widthPixels;
    this.zhidaoleclick = ((Button)findViewById(2131100718));
    this.bootomDistance = ModUtils.dip2px(this, 20.0F);
    this.ll_send = ((LinearLayout)findViewById(2131100441));
    this.ll_receive = ((LinearLayout)findViewById(2131100438));
    this.ll_send.setOnClickListener(this.monClick);
    this.ll_receive.setOnClickListener(this.monClick);
    this.tv_receive = ((TextView)findViewById(2131100439));
    this.tv_send = ((TextView)findViewById(2131100442));
    this.tv_send.setTypeface(ModUtils.getTypeface(this));
    this.tv_receive.setTypeface(ModUtils.getTypeface(this));
    this.textOne = ((TextView)findViewById(2131100372));
    this.textTwo = ((TextView)findViewById(2131100373));
    this.textThree = ((TextView)findViewById(2131100374));
    this.translateImage = ((LinearLayout)findViewById(2131099689));
    this.textOneBootom = ((TextView)findViewById(2131100375));
    this.textTwoBootom = ((TextView)findViewById(2131100376));
    this.textThreeBootom = ((TextView)findViewById(2131100377));
    this.textFourBootom = ((TextView)findViewById(2131100378));
    this.rightRelative = ((LinearLayout)findViewById(2131100370));
    this.titleHengTiao = ((LinearLayout)findViewById(2131100711));
    this.curentweight = ((TextView)findViewById(2131099690));
    this.oldTishi = ((LinearLayout)findViewById(2131100712));
    this.textOne.setTypeface(ModUtils.getTypeface(this));
    this.textTwo.setTypeface(ModUtils.getTypeface(this));
    this.textThree.setTypeface(ModUtils.getTypeface(this));
    this.curentweight.setTypeface(ModUtils.getTypeface(this));
    this.bmiText = ((TextView)findViewById(2131100380));
    this.bmiText.setTypeface(ModUtils.getTypeface(this));
    TextView localTextView1 = (TextView)findViewById(2131100720);
    this.mark = ((ImageView)findViewById(2131099691));
    this.rightDate = ((TextView)findViewById(2131100371));
    this.rightDate.setTypeface(ModUtils.getTypeface(this));
    localTextView1.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.linearUp = ((LinearLayout)findViewById(2131100714));
    this.linearBottom = ((LinearLayout)findViewById(2131100719));
    TextView localTextView2 = (TextView)findViewById(2131100717);
    this.textNow = ((TextView)findViewById(2131100709));
    localTextView2.setTypeface(ModUtils.getTypeface(this));
    this.listView = ((ListView)findViewById(2131100084));
    View localView1 = LayoutInflater.from(this).inflate(2130903134, null);
    this.app = ((MyApplication)getApplication().getApplicationContext());
    this.bianjiImage = ((ImageView)localView1.findViewById(2131100099));
    this.zhidaole = ((RelativeLayout)findViewById(2131100412));
    TextView localTextView3;
    label948: int i;
    if (getIntent().getBooleanExtra("isShown", false))
      if (Age.isOld(this.app))
        if (SharedPreferenceUtils.isShowOldThis(this))
        {
          this.zhidaole.setVisibility(0);
          float f2 = ReportDirect.calculateIdealWeight(this.app.getCurrentRole());
          localTextView2.setText("您的标准体重范围是" + ModUtils.caclutSaveOnePoint(0.9F * f2) + "kg~" + ModUtils.caclutSaveOnePoint(1.1F * f2) + "kg");
          this.oldTishi.setVisibility(8);
          this.zhidaoleclick.setVisibility(4);
          this.back = ((ImageView)findViewById(2131099980));
          this.back.setOnClickListener(this.monClick);
          this.bianjiImage.setOnClickListener(this.monClick);
          View localView2 = LayoutInflater.from(this).inflate(2130903133, null);
          this.listView.addHeaderView(localView1);
          this.listView.addFooterView(localView2);
          this.weightState = ((TextView)localView2.findViewById(2131099736));
          this.weightCount = ((TextView)localView2.findViewById(2131100098));
          this.weightCount.setTypeface(ModUtils.getTypeface(this));
          localTextView3 = (TextView)localView2.findViewById(2131099737);
          localTextView3.setTypeface(ModUtils.getTypeface(this));
          TextView localTextView4 = (TextView)localView1.findViewById(2131099757);
          localTextView4.setText(DateUtils.getCurentDate());
          localTextView4.setTypeface(ModUtils.getTypeface(this));
          AnimUtils localAnimUtils = new AnimUtils(this);
          this.anim = localAnimUtils;
          this.zhidaoleclick.setOnClickListener(this.monClick);
          if (this.app.getCurrentRole().getWeight_change_target() <= 0.0F)
            break label1560;
          this.weightState.setBackgroundResource(2130838000);
          this.weightState.setText("增重中");
          if (this.app.getCurrentRole().getWeight_change_target() <= 0.0F)
            break label1621;
          boolean bool3 = this.app.getTodayBody().getWeight() < this.app.getCurrentRole().getGoal_weight();
          i = 0;
          if (bool3)
          {
            boolean bool4 = this.app.getTodayBody().getBodyFat() < this.app.getCurrentRole().getGoal_fat();
            i = 0;
            if (bool4)
              i = 1;
          }
          label1028: if (i == 0)
            break label1853;
          if (this.app.getCurrentRole().getWeight_change_target() <= 0.0F)
            break label1746;
          if (this.app.getTodayBody().getWeight() < this.app.getCurrentRole().getGoal_weight())
            break label1689;
          localTextView3.setText("恭喜您体重已经达标!");
          label1080: invitlist();
          if (this.contentStrings.size() <= 0)
            break label1926;
          this.linearUp.setVisibility(0);
          this.linearBottom.setVisibility(8);
          this.tv_receive.setText("今日体重");
          this.rightDate.setText(DateUtils.getCurentDate());
          label1133: TodayWeightAdpter localTodayWeightAdpter = new TodayWeightAdpter(this, 2130903056, this.contentStrings);
          this.mMyAnimListAdapter = localTodayWeightAdpter;
          this.listView.setAdapter(this.mMyAnimListAdapter);
          refruse();
          this.mMyAnimListAdapter.setDeleteListener(this);
          this.nListItem = this.mMyAnimListAdapter.getCount();
          if (this.mMyAnimListAdapter.getCount() < 2)
          {
            this.bianjiImage.setImageResource(2130837536);
            this.bianjiImage.setClickable(false);
            this.bianjiImage.setEnabled(false);
          }
          Log.i("picooc", "listcout==" + this.mMyAnimListAdapter.getCount());
          if (!Age.isOld(this.app))
            break label2086;
          Age.setTextSize(this, localTextView4);
          Age.setTextSize(this, localTextView3);
          Age.setTextSize(this, this.weightState);
          Age.setTextSize(this, localTextView1);
          Age.setTextSize(this, this.weightCount);
          Age.setTextSize(this, findViewById(2131100716));
          Age.setTextSize(this, findViewById(2131100717));
          addOldBtn();
          this.titleHengTiao.setVisibility(8);
          this.textNow.setVisibility(0);
        }
    while (true)
    {
      if ((SharedPreferenceUtils.isFirstEnterCurPage(this, "today_weight")) && (this.nListItem >= 2))
      {
        Handler localHandler = new Handler(Looper.getMainLooper());
        Runnable local2 = new Runnable()
        {
          public void run()
          {
            TodayWeightAct.this.mGuideList = new ArrayList();
            Rect localRect = new Rect();
            TodayWeightAct.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
            int i = localRect.top;
            TodayWeightAct.this.locationDelImageView = new int[2];
            TodayWeightAct.this.bianjiImage.setDrawingCacheEnabled(true);
            TodayWeightAct.this.bianjiImage.getLocationInWindow(TodayWeightAct.this.locationDelImageView);
            TodayWeightAct.this.mGuide = new GuideModel(TodayWeightAct.this.bianjiImage.getDrawingCache(), TodayWeightAct.this.locationDelImageView[0], TodayWeightAct.this.locationDelImageView[1]);
            TodayWeightAct.this.mGuideList.add(TodayWeightAct.this.mGuide);
            Intent localIntent = new Intent(TodayWeightAct.this, GuideAct.class);
            localIntent.putExtra("pageId", 4);
            localIntent.putExtra("statusBarHeight", i);
            localIntent.putExtra("guideList", (Serializable)TodayWeightAct.this.mGuideList);
            TodayWeightAct.this.startActivity(localIntent);
            TodayWeightAct.this.overridePendingTransition(-1, -1);
          }
        };
        localHandler.postDelayed(local2, 1000L);
        SharedPreferenceUtils.resetCurPage(this, false, "today_weight");
      }
      return;
      this.zhidaole.setVisibility(8);
      this.oldTishi.setVisibility(0);
      break;
      if (!SharedPreferenceUtils.isShowImage(this))
        break;
      this.zhidaole.setVisibility(0);
      float f1 = ReportDirect.calculateIdealWeight(this.app.getCurrentRole());
      localTextView2.setText("您的标准体重范围是" + ModUtils.caclutSaveOnePoint(0.9F * f1) + "kg~" + ModUtils.caclutSaveOnePoint(1.1F * f1) + "kg");
      break;
      if (Age.isOld(this.app))
      {
        this.oldTishi.setVisibility(0);
        this.zhidaole.setVisibility(8);
        break;
      }
      this.oldTishi.setVisibility(8);
      this.zhidaole.setVisibility(8);
      break;
      label1560: if (this.app.getCurrentRole().getWeight_change_target() < 0.0F)
      {
        this.weightState.setBackgroundResource(2130837999);
        this.weightState.setText("减重中");
        break label948;
      }
      this.weightState.setBackgroundResource(2130837998);
      this.weightState.setText("保持中");
      break label948;
      label1621: boolean bool1 = this.app.getTodayBody().getWeight() < this.app.getCurrentRole().getGoal_weight();
      i = 0;
      if (!bool1)
        break label1028;
      boolean bool2 = this.app.getTodayBody().getBodyFat() < this.app.getCurrentRole().getGoal_fat();
      i = 0;
      if (!bool2)
        break label1028;
      i = 1;
      break label1028;
      label1689: localTextView3.setText("距离目标体重还有" + NumUtils.roundValue(Math.abs(this.app.getCurrentRole().getGoal_weight() - this.app.getTodayBody().getWeight())) + "kg");
      break label1080;
      label1746: if (this.app.getCurrentRole().getWeight_change_target() > 0.0F)
        break label1080;
      if (this.app.getTodayBody().getWeight() <= this.app.getCurrentRole().getGoal_weight())
      {
        localTextView3.setText("恭喜您体重已经达标!");
        break label1080;
      }
      localTextView3.setText("距离目标体重还有" + NumUtils.roundValue(Math.abs(this.app.getCurrentRole().getGoal_weight() - this.app.getTodayBody().getWeight())) + "kg");
      break label1080;
      label1853: ReportEntity localReportEntity = ReportDirect.judgeWeightByRole(this.app.getCurrentRole(), this.app.getTodayBody().getWeight());
      BodyFatReport localBodyFatReport = ReportDirect.calculateIdealBodyFat(this.app.getCurrentRole(), this.app.getTodayBody(), false);
      localTextView3.setText(ReportDirect.getWeightMessageByRole(this.app.getCurrentRole(), this.app.getTodayBody(), localReportEntity, localBodyFatReport)[0]);
      break label1080;
      label1926: long l1 = this.app.getTodayBody().getTime();
      long l2 = DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), l1);
      String str = DateUtils.changeTimeStampToFormatTime(l1, "yyyy年MM月dd日");
      localTextView1.setText("上次称重时间为" + l2 + "天前(" + str + ")");
      this.linearUp.setVisibility(8);
      this.linearBottom.setVisibility(0);
      this.textNow.setVisibility(8);
      this.tv_receive.setText(DateUtils.changeTimeStampToFormatTime(l1, "MM月dd日"));
      this.rightDate.setText(l2 + "天前(" + DateUtils.changeTimeStampToFormatTime(l1, "MM月dd日") + ")");
      break label1133;
      label2086: this.titleHengTiao.setVisibility(0);
      this.textNow.setVisibility(8);
      invitBmi();
    }
  }

  public void refruse()
  {
    List localList = this.contentStrings;
    float f1 = 0.0F;
    int i = 0;
    if (localList != null)
    {
      int j = this.contentStrings.size();
      f1 = 0.0F;
      i = 0;
      if (j > 0)
        i = this.contentStrings.size();
    }
    for (int k = 0; ; k++)
    {
      if (k >= i)
      {
        if (i != 0)
        {
          float f2 = f1 / i;
          this.weightCount.setText("今天称重" + i + "次，均值为" + NumUtils.roundValue(f2) + "kg");
        }
        return;
      }
      f1 += ((BodyIndex)this.contentStrings.get(k)).getWeight();
    }
  }

  public void setLaoutPam(TextView paramTextView, int paramInt1, int paramInt2)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt2, -2);
    localLayoutParams.setMargins(paramInt1, 0, 0, 0);
    paramTextView.setGravity(17);
    paramTextView.setLayoutParams(localLayoutParams);
    paramTextView.setPadding(0, ModUtils.dip2px(this, 37.0F), 0, 0);
  }

  public void setLaoutPamUp(TextView paramTextView, int paramInt)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.setMargins(paramInt, 12, 0, 2);
    paramTextView.setGravity(17);
    paramTextView.setLayoutParams(localLayoutParams);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     TodayWeightAct
 * JD-Core Version:    0.6.2
 */
