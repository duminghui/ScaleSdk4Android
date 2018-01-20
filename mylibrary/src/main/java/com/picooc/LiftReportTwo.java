package com.picooc;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.arithmetic.ReportDirect;
import com.picooc.db.OperationDB;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.ReportEntity;
import com.picooc.domain.RoleBin;
import com.picooc.guide.GuideAct;
import com.picooc.guide.GuideModel;
import com.picooc.model.BodyTypeEnum;
import com.picooc.model.BodyTypeModel;
import com.picooc.model.DietAndNutritionEntity;
import com.picooc.model.ReportModel;
import com.picooc.model.SportPlanModel.DayPlan;
import com.picooc.oldhumen.Age;
import com.picooc.oldhumen.Age.FAT.Res;
import com.picooc.oldhumen.HalfViewPager;
import com.picooc.slidingMenu.SlidingMenu;
import com.picooc.utils.DateUtils;
import com.picooc.utils.NumUtils;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.anyncImageView.AsyncImageView;
import com.picooc.widget.picoocProgress.ArcProgress;
import com.picooc.widget.viewflow.LinePageIndicator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LiftReportTwo extends Fragment
  implements View.OnClickListener, ViewPager.OnPageChangeListener
{
  public static final int resultcode = 15;
  private PicoocActivity3 activity;
  private MyApplication app;
  private ArcProgress arcProgress;
  private BodyIndex body;
  private CheckBox bodyImageView;
  private View bodyTypeChange;
  private View.OnClickListener bodyTypeChangeClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      new AnimUtils(LiftReportTwo.this.getActivity()).getBodyTypeChangeDialog();
    }
  };
  private AsyncImageView head_image;
  private Intent i;
  LinePageIndicator indicator;
  private Boolean isOpen = Boolean.valueOf(true);
  private View layout;
  private ImageView lineImageView;
  private int[] locationLineView;
  private int[] locationbodyImageView;
  private AnimUtils mAnim;
  private GuideModel mGuide;
  private List<GuideModel> mGuideList;
  private final BroadcastReceiver mReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      String str = paramAnonymousIntent.getAction();
      if ("com.picooc.latin.addfamilysuccess".equals(str))
        if (((PicoocActivity3)LiftReportTwo.this.getActivity()).getCurrentFragment() == 4)
          ((PicoocActivity3)LiftReportTwo.this.getActivity()).changeFragment(1);
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                return;
                if (!"com.picooc.latin.addvisitorsuccess".equals(str))
                  break;
              }
              while (((PicoocActivity3)LiftReportTwo.this.getActivity()).getCurrentFragment() != 4);
              ((PicoocActivity3)LiftReportTwo.this.getActivity()).changeFragment(1);
              return;
              if (!"com.picooc.latin.refresh.content".equals(str))
                break;
              if (LiftReportTwo.this.app.getTodayBody().getBodyFat() > 0.0F)
              {
                LiftReportTwo.this.refreshUI();
                return;
              }
            }
            while (((PicoocActivity3)LiftReportTwo.this.getActivity()).getCurrentFragment() != 4);
            ((PicoocActivity3)LiftReportTwo.this.getActivity()).changeFragment(1);
            return;
            if (!"com.picooc.latin.setting.goal.weight".equals(str))
              break;
          }
          while (LiftReportTwo.this.app.getTodayBody().getBodyFat() <= 0.0F);
          LiftReportTwo.this.refreshUI();
          return;
          if (!"com.picooc.setting.updateRoleMessage".equals(str))
            break;
        }
        while (LiftReportTwo.this.app.getTodayBody().getBodyFat() <= 0.0F);
        LiftReportTwo.this.refreshUI();
        return;
      }
      while ((!"com.picooc.latin.weight.success".equals(str)) || (LiftReportTwo.this.app.getTodayBody().getBodyFat() <= 0.0F));
      LiftReportTwo.this.refreshUI();
    }
  };
  private View.OnClickListener menuClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      default:
        return;
      case 2131099650:
        ((PicoocActivity3)LiftReportTwo.this.getActivity()).toggleLeftMenu();
        return;
      case 2131099651:
      }
      ((PicoocActivity3)LiftReportTwo.this.getActivity()).toggleRightMenu();
    }
  };
  CheckBox peplopImage;
  private ReportModel reportModel;
  private RoleBin role;
  private HalfViewPager viewPager;
  LinearLayout xialaLinear;
  LinearLayout xialaLinear_old;
  private TextView z_jieduan_textHistoryDay;
  private TextView z_jieduan_textWeight;
  private TextView z_jieduan_textWeightW;
  private TextView z_textDate;
  private TextView z_textDate1;
  private TextView z_textJirouState;
  private TextView z_textJiroulv;
  private TextView z_textShenTiChengFen;
  private TextView z_textTizhongState;
  private TextView z_textWeight;
  private TextView z_textWeightImage;
  private TextView z_textYundongState;
  private TextView z_textZhiFang;
  private TextView z_textZhiFangState;
  private TextView z_yingyang_textTwo;
  private TextView z_yingyang_textone;
  private TextView z_yundong_textThree;
  private TextView z_yundong_textTwo;
  private TextView z_yundong_textone;

  private void initBodyComAnalyze()
  {
    RelativeLayout localRelativeLayout = (RelativeLayout)this.layout.findViewById(2131100518);
    localRelativeLayout.setOnClickListener(this);
    this.z_textShenTiChengFen = ((TextView)this.layout.findViewById(2131100519));
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int j = localDisplayMetrics.densityDpi;
    int k;
    if ((j < 420) && (j >= 300))
      k = 6;
    while (true)
    {
      this.arcProgress = new ArcProgress(getActivity(), Integer.valueOf(0), Integer.valueOf(-90), Integer.valueOf(360), false, k);
      this.arcProgress.setBackGroundLineColor(0);
      RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams.addRule(13);
      localRelativeLayout.addView(this.arcProgress, localLayoutParams);
      return;
      if (j >= 420)
        k = 8;
      else
        k = 4;
    }
  }

  private void initBodyTypeView()
  {
    this.z_textYundongState = ((TextView)this.layout.findViewById(2131100501));
    this.xialaLinear = ((LinearLayout)this.layout.findViewById(2131100536));
    this.peplopImage = ((CheckBox)this.layout.findViewById(2131100499));
    this.peplopImage.setOnClickListener(this);
  }

  private void initDate()
  {
    this.z_textDate = ((TextView)this.layout.findViewById(2131100498));
    this.z_textDate1 = ((TextView)this.layout.findViewById(2131100497));
  }

  private void initFoodAnd()
  {
    ((LinearLayout)this.layout.findViewById(2131100529)).setOnClickListener(this);
    this.z_yingyang_textone = ((TextView)this.layout.findViewById(2131100530));
    this.z_yingyang_textTwo = ((TextView)this.layout.findViewById(2131100531));
  }

  private void initInterimReport()
  {
    ((LinearLayout)this.layout.findViewById(2131100521)).setOnClickListener(this);
    this.z_jieduan_textHistoryDay = ((TextView)this.layout.findViewById(2131100522));
    this.z_jieduan_textWeightW = ((TextView)this.layout.findViewById(2131100523));
    this.z_jieduan_textWeight = ((TextView)this.layout.findViewById(2131100524));
  }

  private void initSportPlan()
  {
    ((LinearLayout)this.layout.findViewById(2131100525)).setOnClickListener(this);
    this.z_yundong_textone = ((TextView)this.layout.findViewById(2131100526));
    this.z_yundong_textTwo = ((TextView)this.layout.findViewById(2131100527));
    this.z_yundong_textThree = ((TextView)this.layout.findViewById(2131100528));
  }

  private void initWeightAndFatAndMuscleState()
  {
    this.z_textWeightImage = ((TextView)this.layout.findViewById(2131100503));
    this.z_textWeight = ((TextView)this.layout.findViewById(2131100504));
    this.z_textTizhongState = ((TextView)this.layout.findViewById(2131100505));
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder("体放重");
    localSpannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#008cff79")), 1, 2, 34);
    this.z_textWeightImage.setText(localSpannableStringBuilder);
    this.z_textJiroulv = ((TextView)this.layout.findViewById(2131100508));
    this.z_textJirouState = ((TextView)this.layout.findViewById(2131100509));
    this.z_textZhiFang = ((TextView)this.layout.findViewById(2131100512));
    this.z_textZhiFangState = ((TextView)this.layout.findViewById(2131100513));
    this.bodyTypeChange = this.layout.findViewById(2131100502);
  }

  private void refreshBodyComAnalyze()
  {
    this.z_textShenTiChengFen.setTextColor(this.reportModel.getBodyComposition().getCycleColor());
    this.z_textShenTiChengFen.setText(this.reportModel.getBodyComposition().getTotalScore());
    this.arcProgress.setFontGroundColor(this.reportModel.getBodyComposition().getCycleColor());
    this.arcProgress.initProgress(this.reportModel.getBodyComposition().getTotalScore());
  }

  private void refreshBodyType()
  {
    Age.FAT.Res localRes = Age.FAT.Res.getResByIndex(-1 + this.reportModel.getBodyType().getRtype().getIndex(), this.app);
    System.out.println(localRes);
    Drawable localDrawable = getResources().getDrawable(localRes.selectId);
    this.peplopImage.setButtonDrawable(localDrawable);
    this.z_textYundongState.setText(localRes.text);
    this.z_textYundongState.setTextColor(localRes.color);
    this.z_textYundongState.setCompoundDrawablesWithIntrinsicBounds(localRes.circlePointId, 0, 0, 0);
  }

  private void refreshData()
  {
    long l1 = this.app.getTodayBody().getTime();
    long l2 = DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), l1);
    if (l2 == 0L)
      this.z_textDate1.setText("今天");
    while (true)
    {
      this.z_textDate.setText("(" + DateUtils.changeTimeStampToFormatTime(l1, "MM月dd日") + ")");
      return;
      if (l2 > 0L)
        this.z_textDate1.setText(l2 + "天前");
      else
        this.z_textDate1.setText(l2 + "天后");
    }
  }

  private void refreshFoodAnd()
  {
    DietAndNutritionEntity localDietAndNutritionEntity1 = this.reportModel.getDietAndNutritionModel().getEat();
    DietAndNutritionEntity localDietAndNutritionEntity2 = this.reportModel.getDietAndNutritionModel().getNotEat();
    if (localDietAndNutritionEntity1 != null)
    {
      int k = invitimage(localDietAndNutritionEntity1.getFoodTypeName());
      this.z_yingyang_textone.setCompoundDrawablesWithIntrinsicBounds(k, 0, 2130838251, 0);
      this.z_yingyang_textone.setText(localDietAndNutritionEntity1.getFoodTypeName());
    }
    if (localDietAndNutritionEntity2 != null)
    {
      int j = invitimage(localDietAndNutritionEntity2.getFoodTypeName());
      this.z_yingyang_textone.setCompoundDrawablesWithIntrinsicBounds(j, 0, 2130838265, 0);
      this.z_yingyang_textTwo.setText(localDietAndNutritionEntity2.getFoodTypeName());
    }
  }

  private void refreshHeadImage()
  {
    if (!this.app.getCurrentRole().getHead_portrait_url().equals(""))
    {
      this.head_image.setUrl(this.app.getCurrentRole().getHead_portrait_url());
      return;
    }
    if (this.app.getCurrentRole().getSex() == 1)
      this.head_image.setDefaultImageResource(2130838457);
    while (true)
    {
      this.head_image.setUrl(null);
      return;
      this.head_image.setDefaultImageResource(2130838460);
    }
  }

  private void refreshInterimReport()
  {
    String str = this.reportModel.getInterimReport().getOutCycleUpperStr();
    if (str.equals("--"))
    {
      this.z_jieduan_textHistoryDay.setText(str);
      this.z_jieduan_textWeightW.setText(this.reportModel.getInterimReport().getOutCycleDownStr());
      this.z_jieduan_textWeight.setVisibility(8);
      this.z_jieduan_textWeightW.setTextSize(2, getResources().getDimension(2131230842));
      return;
    }
    this.z_jieduan_textHistoryDay.setText(str);
    this.z_jieduan_textWeight.setVisibility(0);
    float f = this.reportModel.getInterimReport().getOutCycleWeightChange();
    this.z_jieduan_textWeightW.setText("体重");
    this.z_jieduan_textWeightW.setTextSize(2, getResources().getDimension(2131230843));
    if (f > 0.0F)
    {
      this.z_jieduan_textWeight.setCompoundDrawablesWithIntrinsicBounds(2130838263, 0, 0, 0);
      this.z_jieduan_textWeight.setText(NumUtils.roundValue(f) + "kg");
      return;
    }
    this.z_jieduan_textWeight.setCompoundDrawablesWithIntrinsicBounds(2130838261, 0, 0, 0);
    this.z_jieduan_textWeight.setText(NumUtils.roundValue(-f) + "kg");
  }

  private void refreshSportPlan()
  {
    this.z_yundong_textone.setVisibility(8);
    this.z_yundong_textTwo.setVisibility(8);
    this.z_yundong_textThree.setVisibility(8);
    Iterator localIterator = this.reportModel.getSportPlan().getDaySport().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      DayPlan localDayPlan = (DayPlan)localIterator.next();
      switch ($SWITCH_TABLE$com$picooc$model$SportPlanEnum()[localDayPlan.getSportType().ordinal()])
      {
      default:
        break;
      case 1:
        this.z_yundong_textone.setVisibility(0);
        this.z_yundong_textone.setText(localDayPlan.getSportDuringTime());
        this.z_yundong_textone.setCompoundDrawablesWithIntrinsicBounds(2130838318, 0, 0, 0);
        break;
      case 2:
        this.z_yundong_textTwo.setVisibility(0);
        this.z_yundong_textTwo.setText(localDayPlan.getSportDuringTime());
        this.z_yundong_textTwo.setCompoundDrawablesWithIntrinsicBounds(2130838244, 0, 0, 0);
        break;
      case 3:
        this.z_yundong_textThree.setVisibility(0);
        this.z_yundong_textThree.setText(localDayPlan.getSportDuringTime());
        this.z_yundong_textThree.setCompoundDrawablesWithIntrinsicBounds(2130838241, 0, 0, 0);
      }
    }
  }

  private void refreshWeightAndFatAndMuscleState()
  {
    this.z_textWeight.setText(NumUtils.roundValue(this.body.getWeight()) + "kg");
    this.z_textTizhongState.setText(this.reportModel.getBodyType().get_weightStr());
    this.z_textJiroulv.setText(NumUtils.roundValue(this.body.getMusde_race()) + "%");
    this.z_textJirouState.setText(this.reportModel.getBodyType().get_muscleStr());
    this.z_textZhiFang.setText(NumUtils.roundValue(this.body.getBodyFat()) + "%");
    this.z_textZhiFangState.setText(this.reportModel.getBodyType().get_fatStr());
  }

  private void viewPageRefresh()
  {
    SlidingMenu localSlidingMenu = ((PicoocActivity3)getActivity()).getSlidingMenu();
    System.out.println(((View)this.viewPager.getParent()).getVisibility());
    if (((View)this.viewPager.getParent()).getVisibility() != 8)
    {
      ((View)this.viewPager.getParent()).setVisibility(8);
      if (localSlidingMenu != null)
        localSlidingMenu.setCanSliding(true, true);
    }
    this.viewPager.restore(this.reportModel.getBodyType().getBodyTypeStr(), this.reportModel.getBodyType().getFactorStr(), this.reportModel.getBodyType().getCautionStr(), this.reportModel.getBodyType().getImproveStr());
    this.isOpen = Boolean.valueOf(true);
    this.indicator.setViewPager(this.viewPager, 0);
  }

  public void click_pepopleImage()
  {
    ((LinearLayout)this.layout.findViewById(2131100520));
    final View localView = (View)this.viewPager.getParent();
    int j = getResources().getDisplayMetrics().heightPixels;
    System.out.println("startY= " + j);
    ObjectAnimator localObjectAnimator = this.mAnim.getMove(localView, -j, 0, 300);
    localObjectAnimator.addListener(new Animator.AnimatorListener()
    {
      public void onAnimationCancel(Animator paramAnonymousAnimator)
      {
      }

      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        SlidingMenu localSlidingMenu = ((PicoocActivity3)LiftReportTwo.this.getActivity()).getSlidingMenu();
        if (LiftReportTwo.this.isOpen.booleanValue())
        {
          localSlidingMenu.setCanSliding(true, true);
          return;
        }
        if ((LiftReportTwo.this.viewPager != null) && (LiftReportTwo.this.viewPager.getCurrentItem() == 0))
        {
          localSlidingMenu.setCanSliding(true, false);
          return;
        }
        if ((LiftReportTwo.this.viewPager != null) && (LiftReportTwo.this.viewPager.getCurrentItem() == 1))
        {
          localSlidingMenu.setCanSliding(false, true);
          return;
        }
        localSlidingMenu.setCanSliding(false, false);
      }

      public void onAnimationRepeat(Animator paramAnonymousAnimator)
      {
      }

      public void onAnimationStart(Animator paramAnonymousAnimator)
      {
        localView.setVisibility(0);
      }
    });
    if (this.isOpen.booleanValue())
    {
      localObjectAnimator.start();
      this.isOpen = Boolean.valueOf(false);
      this.viewPager.updateViews(-1 + this.reportModel.getBodyType().getRtype().getIndex());
      guideBodyTypeFunc();
      return;
    }
    this.isOpen = Boolean.valueOf(true);
    localObjectAnimator.reverse();
    this.peplopImage.setChecked(false);
  }

  public void guideBodyTypeFunc()
  {
    if (SharedPreferenceUtils.isFirstEnterCurPage(getActivity(), "lift_report_bodytype"))
    {
      new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
      {
        public void run()
        {
          LiftReportTwo.this.mGuideList = new ArrayList();
          LiftReportTwo.this.indicator.setDrawingCacheEnabled(true);
          Rect localRect = new Rect();
          LiftReportTwo.this.getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
          int i = localRect.top;
          LiftReportTwo.this.locationbodyImageView = new int[2];
          LiftReportTwo.this.indicator.getLocationInWindow(LiftReportTwo.this.locationbodyImageView);
          LiftReportTwo.this.mGuide = new GuideModel(LiftReportTwo.this.indicator.getDrawingCache(), LiftReportTwo.this.locationbodyImageView[0], LiftReportTwo.this.locationbodyImageView[1]);
          LiftReportTwo.this.mGuideList.add(LiftReportTwo.this.mGuide);
          Intent localIntent = new Intent(LiftReportTwo.this.getActivity(), GuideAct.class);
          localIntent.putExtra("pageId", 6);
          localIntent.putExtra("statusBarHeight", i);
          localIntent.putExtra("guideList", (Serializable)LiftReportTwo.this.mGuideList);
          LiftReportTwo.this.startActivityForResult(localIntent, 15);
          LiftReportTwo.this.getActivity().overridePendingTransition(-1, -1);
        }
      }
      , 200L);
      SharedPreferenceUtils.resetCurPage(getActivity(), false, "lift_report_bodytype");
    }
  }

  public void guideClickOff()
  {
    if (SharedPreferenceUtils.isFirstEnterCurPage(getActivity(), "lift_reporttwo"))
    {
      new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
      {
        public void run()
        {
          LiftReportTwo.this.bodyImageView = ((CheckBox)LiftReportTwo.this.layout.findViewById(2131100499));
          LiftReportTwo.this.mGuideList = new ArrayList();
          Rect localRect = new Rect();
          LiftReportTwo.this.getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
          int i = localRect.top;
          LiftReportTwo.this.locationbodyImageView = new int[2];
          LiftReportTwo.this.bodyImageView.setDrawingCacheEnabled(true);
          LiftReportTwo.this.bodyImageView.getLocationInWindow(LiftReportTwo.this.locationbodyImageView);
          LiftReportTwo.this.mGuide = new GuideModel(LiftReportTwo.this.bodyImageView.getDrawingCache(), LiftReportTwo.this.locationbodyImageView[0], LiftReportTwo.this.locationbodyImageView[1]);
          LiftReportTwo.this.mGuideList.add(LiftReportTwo.this.mGuide);
          Intent localIntent = new Intent(LiftReportTwo.this.getActivity(), GuideAct.class);
          localIntent.putExtra("pageId", 3);
          localIntent.putExtra("statusBarHeight", i);
          localIntent.putExtra("guideList", (Serializable)LiftReportTwo.this.mGuideList);
          LiftReportTwo.this.startActivity(localIntent);
          LiftReportTwo.this.getActivity().overridePendingTransition(-1, -1);
        }
      }
      , 200L);
      SharedPreferenceUtils.resetCurPage(getActivity(), false, "lift_reporttwo");
    }
  }

  public void invit()
  {
    this.viewPager = ((HalfViewPager)this.layout.findViewById(2131100534));
    this.viewPager.init();
    this.viewPager.setOnPageChangeListener(this);
    this.indicator = ((LinePageIndicator)this.layout.findViewById(2131100535));
    this.indicator.setViewPager(this.viewPager);
    this.indicator.setOnPageChangeListener(this);
    LinearLayout localLinearLayout = (LinearLayout)this.layout.findViewById(2131099651);
    this.head_image = ((AsyncImageView)this.layout.findViewById(2131099850));
    ImageView localImageView = (ImageView)this.layout.findViewById(2131099650);
    localLinearLayout.setOnClickListener(this.menuClick);
    localImageView.setOnClickListener(this.menuClick);
    ((TextView)this.layout.findViewById(2131099699)).setText("深度报告");
    initDate();
    initBodyTypeView();
    initWeightAndFatAndMuscleState();
    initBodyComAnalyze();
    initInterimReport();
    initSportPlan();
    initFoodAnd();
    refreshUI();
    Typeface localTypeface = TypefaceUtils.getTypeface(getActivity(), null);
    this.z_textDate.setTypeface(localTypeface);
    this.z_textDate1.setTypeface(localTypeface);
    this.z_textWeightImage.setTypeface(localTypeface);
    this.z_textWeight.setTypeface(localTypeface);
    this.z_textJiroulv.setTypeface(localTypeface);
    this.z_textZhiFang.setTypeface(localTypeface);
    this.z_jieduan_textHistoryDay.setTypeface(localTypeface);
    this.z_jieduan_textWeight.setTypeface(localTypeface);
    this.z_jieduan_textWeightW.setTypeface(localTypeface);
    this.z_yundong_textone.setTypeface(localTypeface);
    this.z_yundong_textTwo.setTypeface(localTypeface);
    this.z_yundong_textThree.setTypeface(localTypeface);
    this.z_yingyang_textone.setTypeface(localTypeface);
    this.z_yingyang_textTwo.setTypeface(localTypeface);
    this.z_textShenTiChengFen.setTypeface(localTypeface);
    this.z_jieduan_textWeight.setTypeface(localTypeface);
  }

  public int invitimage(String paramString)
  {
    int j;
    if (paramString.equals("蛋白质"))
      j = 2130838279;
    boolean bool;
    do
    {
      return j;
      if (paramString.equals("谷物"))
        return 2130838224;
      if (paramString.equals("零食"))
        return 2130838242;
      if (paramString.equals("奶制品"))
        return 2130838254;
      if (paramString.equals("蔬果"))
        return 2130838258;
      bool = paramString.equals("脂肪");
      j = 0;
    }
    while (!bool);
    return 2130838315;
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt2 == 15) && (paramIntent != null) && (paramIntent.getStringExtra("page").equalsIgnoreCase("LiftReportTwo")))
      guideClickOff();
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131099650:
    case 2131099651:
    default:
    case 2131100529:
    case 2131100525:
    case 2131100518:
    case 2131100521:
      do
      {
        return;
        this.i = new Intent(getActivity(), LiftDietAndNutritionAct.class);
        this.i.putExtra("model", this.reportModel.getDietAndNutritionModel());
        startActivity(this.i);
        getActivity().overridePendingTransition(2130968580, 2130968577);
        return;
        this.i = new Intent(getActivity(), LiftMovementPlanAct.class);
        this.i.putExtra("model", this.reportModel.getSportPlan());
        startActivity(this.i);
        getActivity().overridePendingTransition(2130968580, 2130968577);
        return;
        this.i = new Intent(getActivity(), LiftPhysicalQualityTwo.class);
        this.i.putExtra("model", this.reportModel.getBodyComposition());
        startActivity(this.i);
        getActivity().overridePendingTransition(2130968580, 2130968577);
        return;
      }
      while (!this.reportModel.getInterimReport().isCanComeIn());
      this.i = new Intent(getActivity(), LiftInterimReport.class);
      this.i.putExtra("model", this.reportModel.getInterimReport());
      startActivity(this.i);
      getActivity().overridePendingTransition(2130968580, 2130968577);
      return;
    case 2131100499:
    }
    click_pepopleImage();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.app = ((MyApplication)getActivity().getApplicationContext());
    IntentFilter localIntentFilter1 = new IntentFilter("com.picooc.latin.weight.success");
    getActivity().registerReceiver(this.mReceiver, localIntentFilter1);
    IntentFilter localIntentFilter2 = new IntentFilter("com.picooc.latin.refresh.content");
    getActivity().registerReceiver(this.mReceiver, localIntentFilter2);
    IntentFilter localIntentFilter3 = new IntentFilter("com.picooc.setting.updateRoleMessage");
    getActivity().registerReceiver(this.mReceiver, localIntentFilter3);
    IntentFilter localIntentFilter4 = new IntentFilter("com.picooc.latin.setting.goal.weight");
    getActivity().registerReceiver(this.mReceiver, localIntentFilter4);
    IntentFilter localIntentFilter5 = new IntentFilter("com.picooc.latin.addfamilysuccess");
    getActivity().registerReceiver(this.mReceiver, localIntentFilter5);
    IntentFilter localIntentFilter6 = new IntentFilter("com.picooc.latin.addvisitorsuccess");
    getActivity().registerReceiver(this.mReceiver, localIntentFilter6);
    System.out.println("LiftReportTwo ----onCreate");
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.layout = paramLayoutInflater.inflate(2130903200, paramViewGroup, false);
    this.activity = ((PicoocActivity3)getActivity());
    this.mAnim = new AnimUtils(getActivity());
    invit();
    System.out.println("LiftReportTwo ----onCreateView " + this.viewPager);
    if (SharedPreferenceUtils.isFirstEnterCurPage(getActivity(), "lift_report_bodytype"))
    {
      this.peplopImage.setChecked(true);
      click_pepopleImage();
    }
    return this.layout;
  }

  public void onDestroy()
  {
    super.onDestroy();
    getActivity().unregisterReceiver(this.mReceiver);
  }

  public void onPageScrollStateChanged(int paramInt)
  {
  }

  public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
  }

  public void onPageSelected(int paramInt)
  {
    System.out.println("arg0 ==============" + paramInt);
    SlidingMenu localSlidingMenu = ((PicoocActivity3)getActivity()).getSlidingMenu();
    if (localSlidingMenu == null)
      return;
    if ((this.viewPager != null) && (this.viewPager.getCurrentItem() == 0))
    {
      localSlidingMenu.setCanSliding(true, false);
      return;
    }
    if ((this.viewPager != null) && (this.viewPager.getCurrentItem() == 1))
    {
      localSlidingMenu.setCanSliding(false, true);
      return;
    }
    localSlidingMenu.setCanSliding(false, false);
  }

  public void refreshUI()
  {
    this.role = this.app.getCurrentRole();
    this.body = this.app.getTodayBody();
    this.reportModel = new ReportModel(getActivity(), this.role, this.body);
    refreshData();
    refreshBodyType();
    refreshWeightAndFatAndMuscleState();
    refreshBodyComAnalyze();
    refreshInterimReport();
    refreshSportPlan();
    refreshFoodAnd();
    refreshHeadImage();
    BodyIndex localBodyIndex;
    int j;
    if (Age.isOld(this.app))
    {
      this.layout.findViewById(2131100506).setVisibility(8);
      viewPageRefresh();
      localBodyIndex = OperationDB.selectBodyindexBeforeTimestamp(getActivity(), this.role.getRole_id(), this.body.getTime());
      if ((localBodyIndex == null) || (localBodyIndex.getBodyFat() <= 0.0F))
        break label296;
      if (DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(this.body.getTime(), localBodyIndex.getTime()) <= 7L)
        break label205;
      j = 0;
    }
    while (true)
    {
      if (j == 0)
        break label301;
      this.bodyTypeChange.setVisibility(0);
      this.bodyTypeChange.setOnClickListener(this.bodyTypeChangeClickListener);
      return;
      this.layout.findViewById(2131100506).setVisibility(0);
      break;
      label205: ReportEntity localReportEntity1 = ReportDirect.judgeWeightByRole(this.role, localBodyIndex.getWeight());
      ReportEntity localReportEntity2 = ReportDirect.judgeBodyFatByRole(this.role, localBodyIndex);
      ReportEntity localReportEntity3 = ReportDirect.judgeMuscleRaceByRole(this.role, localBodyIndex);
      ReportEntity localReportEntity4 = ReportDirect.judgeInFatByRole(this.role, localBodyIndex);
      BodyTypeModel localBodyTypeModel = new BodyTypeModel(this.role, localBodyIndex, localReportEntity1, localReportEntity2, localReportEntity3, localReportEntity4);
      if (this.reportModel.getBodyType().getRtype() != localBodyTypeModel.getRtype())
      {
        j = 1;
      }
      else
      {
        j = 0;
        continue;
        label296: j = 0;
      }
    }
    label301: this.bodyTypeChange.setVisibility(8);
  }

  public void setMenuVisibility(boolean paramBoolean)
  {
    super.setMenuVisibility(paramBoolean);
    View localView;
    if (getView() != null)
    {
      localView = getView();
      if (!paramBoolean)
        break label29;
    }
    label29: for (int j = 0; ; j = 8)
    {
      localView.setVisibility(j);
      return;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LiftReportTwo
 * JD-Core Version:    0.6.2
 */
