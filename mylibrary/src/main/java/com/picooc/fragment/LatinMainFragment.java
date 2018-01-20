package com.picooc.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.BodyScore;
import com.picooc.LiftPhysicalQualityTwo;
import com.picooc.MyApplication;
import com.picooc.NetWorkAct;
import com.picooc.PicoocActivity3;
import com.picooc.ShareAct;
import com.picooc.TodayBodyFatAct;
import com.picooc.TodayWeightAct;
import com.picooc.animation.AnimationUtils;
import com.picooc.arithmetic.ReportDirect;
import com.picooc.constant.ThemeConstant;
import com.picooc.db.OperationDB;
import com.picooc.domain.BodyFatReport;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.ReportEntity;
import com.picooc.domain.RoleBin;
import com.picooc.guide.GuideAct;
import com.picooc.guide.GuideModel;
import com.picooc.internet.AsyncMessageUtils;
import com.picooc.internet.HttpUtils;
import com.picooc.model.LatinWeekData;
import com.picooc.model.MainBodyScoreModel;
import com.picooc.model.ReportModel;
import com.picooc.oldhumen.Age;
import com.picooc.utils.DateUtils;
import com.picooc.utils.NumUtils;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.WeekWeightState;
import com.picooc.widget.everydayme.EverydayMe;
import com.picooc.widget.loading.PicoocToast;
import com.picooc.widget.picoocProgress.PicoocProgressLayout;
import com.picooc.widget.picoocProgress.PicoocScrollTextView;
import com.picooc.widget.picoocProgress.ProgressTextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.achartengine.tools.ModUtils;

@SuppressLint({"ValidFragment"})
public class LatinMainFragment extends Fragment
{
  public static final int BLUETOOTH_CONNECTINT = 403;
  public static final int BLUETOOTH_CONNECT_FAILD = 404;
  public static final int BLUETOOTH_CONNECT_SUCCESS = 405;
  public static final int BLUETOOTH_CONNECT_TIMEOUT = 406;
  public static final int BLUETOOTH_RESET = 401;
  public static final int BLUETOOTH_STAND_ON_SCALE_PLEASE = 402;
  public static final int GET_FAT = 2;
  public static final int GET_NONE = 0;
  public static final int GET_WEIGHT = 1;
  public static final int GET_WEIGHT_FAT = 3;
  private static int curGetType = 0;
  public static final int resultMoveCode = 10;
  private Boolean LiftIsShow = Boolean.valueOf(true);
  private TextView ShareText;
  private MyApplication app;
  private EverydayMe arcMenu;
  private ImageView askImageView;
  private View bmiClickView;
  private View.OnClickListener bmiCycleClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (LatinMainFragment.this.app.getCurrentRole().getGoal_weight() <= 0.0F);
      do
      {
        return;
        if (!Age.isOld(LatinMainFragment.this.app))
        {
          if (LatinMainFragment.this.app.getTodayBody().getBodyFat() > 0.0F)
          {
            Intent localIntent2 = new Intent(LatinMainFragment.this.getActivity(), BodyScore.class);
            localIntent2.putExtra("model", LatinMainFragment.this.mainBodyScoreModel);
            LatinMainFragment.this.startActivity(localIntent2);
            LatinMainFragment.this.getActivity().overridePendingTransition(2130968580, 2130968577);
            return;
          }
          LatinMainFragment.this.mAnim.getBodyFatWrongPop();
          return;
        }
      }
      while (LatinMainFragment.this.app.getTodayBody().getBmi() <= 0.0F);
      Intent localIntent1 = new Intent(LatinMainFragment.this.getActivity(), BodyScore.class);
      LatinMainFragment.this.startActivity(localIntent1);
      LatinMainFragment.this.getActivity().overridePendingTransition(2130968580, 2130968577);
    }
  };
  private TextView bmiState;
  private TextView bmi_RrongImage;
  private TextView bmi_defenTv;
  private LinearLayout bmi_defenliner;
  private PicoocProgressLayout bmi_layout;
  private ProgressTextView bmi_scrollText;
  private TextView bodyFatWrongTip;
  private View.OnClickListener bodyScoreClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (LatinMainFragment.this.app.getTodayBody().getWeight() <= 0.0F)
        return;
      if (LatinMainFragment.this.app.getTodayBody().getBodyFat() > 0.0F)
      {
        Intent localIntent = new Intent(LatinMainFragment.this.getActivity(), LiftPhysicalQualityTwo.class);
        localIntent.putExtra("model", new ReportModel(LatinMainFragment.this.getActivity(), LatinMainFragment.this.app.getCurrentRole(), LatinMainFragment.this.app.getTodayBody()).getBodyComposition());
        LatinMainFragment.this.startActivity(localIntent);
        LatinMainFragment.this.getActivity().overridePendingTransition(2130968580, 2130968577);
        return;
      }
      LatinMainFragment.this.mAnim.getBodyFatWrongPop();
    }
  };
  private RelativeLayout bodyScoreLayout;
  private PicoocProgressLayout bodyScorePP;
  private TextView bodyScoreTv;
  private LinearLayout bodyScoreliner;
  private TextView bodyUnit;
  private PicoocProgressLayout body_fat_layout;
  private ProgressTextView body_fat_scrollText;
  private ImageView defenRrongImage;
  private int density = 1;
  DisplayMetrics dm;
  private TextView fatChange;
  private View fatClickView;
  private View.OnClickListener fatCycleClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (Age.isOld(LatinMainFragment.this.app))
        if (LatinMainFragment.this.app.getTodayBody().getBodyFat() > 0.0F)
        {
          Intent localIntent2 = new Intent(LatinMainFragment.this.getActivity(), TodayBodyFatAct.class);
          LatinMainFragment.this.startActivity(localIntent2);
          LatinMainFragment.this.getActivity().overridePendingTransition(2130968580, 2130968577);
        }
      do
      {
        do
        {
          do
            return;
          while (LatinMainFragment.this.bodyFatWrongTip.getAlpha() <= 0.0F);
          LatinMainFragment.this.mAnim.getBodyFatWrongPop();
          return;
          if ((LatinMainFragment.this.app.getCurrentRole().getGoal_fat() > 0.0F) && (LatinMainFragment.this.app.getTodayBody().getBodyFat() > 0.0F))
          {
            Intent localIntent1 = new Intent(LatinMainFragment.this.getActivity(), TodayBodyFatAct.class);
            LatinMainFragment.this.startActivity(localIntent1);
            LatinMainFragment.this.getActivity().overridePendingTransition(2130968580, 2130968577);
            return;
          }
          if (LatinMainFragment.this.app.getTodayBody().getBodyFat() > 0.0F)
            break;
        }
        while (LatinMainFragment.this.bodyFatWrongTip.getAlpha() <= 0.0F);
        LatinMainFragment.this.mAnim.getBodyFatWrongPop();
        return;
      }
      while ((LatinMainFragment.this.app.getCurrentRole().getGoal_fat() > 0.0F) || (LatinMainFragment.this.app.getTodayBody().getBodyFat() <= 0.0F) || (LatinMainFragment.this.question.getAlpha() <= 0.0F));
      long l = DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), LatinMainFragment.this.app.getCurrentRole().getChange_goal_weight_time());
      if (l <= 0L);
      for (String str = "您在今天修改了目标体重，请先称重才能计算您的目标体脂率哦！"; ; str = "您在" + l + "天前修改了目标体重，请先称重才能计算您的目标体脂率哦！")
      {
        PicoocToast.showToast(LatinMainFragment.this.getActivity(), str);
        return;
      }
    }
  };
  private TextView fatDabiaoText;
  private LinearLayout frameLayout;
  private TextView goleBodyFat;
  private TextView goleWeight;
  private LinearLayout huizhangclick;
  private ImageView imageRight;
  private ImageView imagehong;
  private ImageView imagehui;
  private Boolean isshow = Boolean.valueOf(true);
  private ViewGroup layout;
  private LinearLayout liftLinearlaout;
  private int liftMaginTop = 0;
  private TextView liftTextview;
  private LinearLayout linearLayout_bg;
  private LinearLayout linerid;
  private int[] loactionTextView;
  private int[] locationAskImageView;
  private int[] locationImageView;
  private int loogcount = 1;
  private AnimUtils mAnim;
  private GuideModel mGuide;
  private List<GuideModel> mGuideList;
  private MainBodyScoreModel mainBodyScoreModel;
  private RelativeLayout netRelative;
  private int offectHeight;
  private ImageView pangImage;
  private ImageView phoneImageView;
  private View.OnClickListener picoocMainClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      default:
        return;
      case 2131099905:
        int i = LatinMainFragment.this.whyImage.getLeft() + LatinMainFragment.this.whyImage.getWidth() / 2 - LatinMainFragment.this.shuxian.getWidth() / 2;
        RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, 40);
        localLayoutParams.setMargins(i, ModUtils.dip2px(LatinMainFragment.this.getActivity(), 35.0F), 0, 0);
        LatinMainFragment.this.shuxian.setLayoutParams(localLayoutParams);
        if (LatinMainFragment.this.isshow.booleanValue())
        {
          LatinMainFragment.this.shuxian.setVisibility(0);
          LatinMainFragment.this.mAnim.showAnima(LatinMainFragment.this.shuxian, 400L);
          LatinMainFragment.this.titelpop.setVisibility(0);
          LatinMainFragment.this.body_fat_layout.setClickable(false);
          LatinMainFragment.this.isshow = Boolean.valueOf(false);
          LatinMainFragment.this.mAnim.downMove(LatinMainFragment.this.titelpop, -LatinMainFragment.this.frameLayout.getHeight(), 0, 400);
          return;
        }
        LatinMainFragment.this.body_fat_layout.setClickable(true);
        LatinMainFragment.this.isshow = Boolean.valueOf(true);
        LatinMainFragment.this.mAnim.upMove(LatinMainFragment.this.titelpop, 0, -LatinMainFragment.this.frameLayout.getHeight(), 400);
        LatinMainFragment.this.mAnim.missAnima(LatinMainFragment.this.shuxian, 400L);
        return;
      case 2131099915:
        if (LatinMainFragment.this.app.getTodayBody().getTime() == 0L)
        {
          PicoocToast.showToast(LatinMainFragment.this.getActivity(), "Hi，不如先去Latin一下？才能开始趣妙横生的分享哦~");
          return;
        }
        if (LatinMainFragment.this.ShareText.getVisibility() == 0)
          LatinMainFragment.this.ShareText.setVisibility(8);
        Intent localIntent2 = new Intent(LatinMainFragment.this.getActivity(), ShareAct.class);
        LatinMainFragment.this.startActivity(localIntent2);
        LatinMainFragment.this.getActivity().overridePendingTransition(2130968580, 2130968577);
        return;
      case 2131099912:
      }
      Intent localIntent1 = new Intent(LatinMainFragment.this.getActivity(), NetWorkAct.class);
      LatinMainFragment.this.startActivity(localIntent1);
      LatinMainFragment.this.getActivity().overridePendingTransition(2130968580, 2130968577);
    }
  };
  private ImageView question;
  private ImageView shakeImage;
  private ImageView shuxian;
  private TextView textMiddle;
  private ThemeConstant themeConstant;
  private LinearLayout titelLinear;
  private RelativeLayout titelpop;
  private TextView waveTextView;
  private TextView weightChange;
  private View weightClickView;
  private View.OnClickListener weightCycleClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if ((LatinMainFragment.this.app.getCurrentRole().getGoal_weight() > 0.0F) && (LatinMainFragment.this.app.getTodayBody().getWeight() > 0.0F))
      {
        Intent localIntent = new Intent(LatinMainFragment.this.getActivity(), TodayWeightAct.class);
        if (ReportDirect.judgeWeightByRole(LatinMainFragment.this.app.getCurrentRole(), LatinMainFragment.this.app.getTodayBody().getWeight()).getStateCode() > 2)
          localIntent.putExtra("isShown", true);
        LatinMainFragment.this.startActivity(localIntent);
        LatinMainFragment.this.getActivity().overridePendingTransition(2130968580, 2130968577);
      }
    }
  };
  private TextView weightDabiaoText;
  private WeekWeightState weightState;
  private TextView weightTime;
  private PicoocProgressLayout weight_layout;
  private PicoocScrollTextView weight_scrollText;
  private ImageView whyImage;
  private ImageView why___what;
  private ImageView yellowImage;

  private int getBmiColor(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return Color.rgb(140, 255, 121);
    case 1:
      return Color.rgb(121, 255, 238);
    case 2:
      return Color.rgb(140, 255, 121);
    case 3:
      return Color.rgb(255, 194, 88);
    case 4:
    }
    return Color.rgb(255, 110, 110);
  }

  private String getBmiStateString(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return "";
    case 1:
      return "偏瘦";
    case 2:
      return "标准";
    case 3:
      return "超重";
    case 4:
    }
    return "肥胖";
  }

  private void init()
  {
    this.whyImage = ((ImageView)this.frameLayout.findViewById(2131099905));
    this.whyImage.setOnClickListener(this.picoocMainClick);
    this.titelpop = ((RelativeLayout)this.frameLayout.findViewById(2131099892));
    this.why___what = ((ImageView)this.frameLayout.findViewById(2131099918));
    this.themeConstant = new ThemeConstant(getActivity());
    refreshWhyImage();
    this.dm = new DisplayMetrics();
    getActivity().getWindowManager().getDefaultDisplay().getMetrics(this.dm);
    int i = this.dm.widthPixels;
    int j = this.dm.heightPixels;
    int k = this.dm.densityDpi;
    if ((k < 420) && (k >= 300))
      this.density = 2;
    while (true)
    {
      this.weightTime = ((TextView)this.frameLayout.findViewById(2131099904));
      this.offectHeight = (this.weightTime.getLineHeight() + this.weightTime.getPaddingTop());
      this.layout = ((RelativeLayout)this.frameLayout.findViewById(2131099900));
      this.shakeImage = ((ImageView)this.frameLayout.findViewById(2131099903));
      this.shuxian = ((ImageView)this.frameLayout.findViewById(2131099692));
      this.titelLinear = ((LinearLayout)this.frameLayout.findViewById(2131099902));
      this.huizhangclick = ((LinearLayout)this.frameLayout.findViewById(2131099915));
      this.huizhangclick.setOnClickListener(this.picoocMainClick);
      this.yellowImage = ((ImageView)this.frameLayout.findViewById(2131099916));
      this.ShareText = ((TextView)this.frameLayout.findViewById(2131099917));
      this.ShareText.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
      this.textMiddle = ((TextView)this.frameLayout.findViewById(2131099909));
      this.imageRight = ((ImageView)this.frameLayout.findViewById(2131099910));
      this.imagehui = ((ImageView)this.frameLayout.findViewById(2131099911));
      this.linerid = ((LinearLayout)this.frameLayout.findViewById(2131099907));
      this.imagehong = ((ImageView)this.frameLayout.findViewById(2131099912));
      this.netRelative = ((RelativeLayout)this.frameLayout.findViewById(2131099906));
      this.imagehong.setOnClickListener(this.picoocMainClick);
      initWeightLayout(i, j);
      initBMILayout(i, j);
      initBodyFatLayout(i, j);
      initEverydayMe(i, j);
      initWeightState();
      initBodyScoreLayout(i, j);
      refreshUI();
      if (HttpUtils.isNetworkConnected(getActivity()))
        break;
      this.imagehong.setClickable(true);
      this.linerid.setAlpha(1.0F);
      this.imagehui.setVisibility(0);
      this.imagehong.setVisibility(0);
      startNetAnim();
      return;
      if (k >= 420)
        this.density = 3;
      else
        this.density = 1;
    }
    this.imagehong.setClickable(false);
    this.linerid.setAlpha(0.0F);
    this.imagehui.setVisibility(8);
    this.imagehong.setVisibility(8);
  }

  private void initBMILayout(int paramInt1, int paramInt2)
  {
    int i = (int)(0.3F * paramInt1);
    if ((this.density == 2) || (this.density == 3))
      i = (int)(0.33F * paramInt1);
    int j = getResources().getColor(2131165202);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(i, (int)(1.25F * i));
    localLayoutParams1.setMargins((int)(0.05F * paramInt1), (int)(0.29F * paramInt2) + this.offectHeight, 0, 0);
    this.bmi_layout = new PicoocProgressLayout(getActivity(), j, -90, 360, "BMI", true, 1.3F);
    this.layout.addView(this.bmi_layout, localLayoutParams1);
    this.bmi_defenTv = new TextView(getActivity());
    this.bmi_defenTv.setId(this.bmi_defenTv.hashCode());
    this.bmi_defenTv.setTextColor(-1);
    this.bmi_defenTv.setTextSize(getActivity().getResources().getDimension(2131230798));
    this.bmi_defenTv.setTypeface(ModUtils.getTypeface(getActivity()));
    this.bmi_defenTv.setGravity(17);
    TextView localTextView = new TextView(getActivity());
    localTextView.setText("分");
    localTextView.setTextColor(-1);
    this.bmi_defenliner = new LinearLayout(getActivity());
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
    this.bmi_defenliner.setOrientation(0);
    localLayoutParams2.topMargin = (i / 2 - i / 7);
    localLayoutParams2.addRule(14);
    this.bmi_defenliner.addView(this.bmi_defenTv);
    this.bmi_defenliner.addView(localTextView);
    this.bmi_layout.addView(this.bmi_defenliner, localLayoutParams2);
    this.bmi_RrongImage = new TextView(getActivity());
    this.bmi_RrongImage.setBackgroundResource(2130837566);
    RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams3.topMargin = (i / 2 - i / 7);
    localLayoutParams3.addRule(14);
    this.bmi_RrongImage.setAlpha(0.0F);
    this.bmi_layout.addView(this.bmi_RrongImage, localLayoutParams3);
    this.bmi_scrollText = new ProgressTextView(getActivity(), j, 0.38F * i, "", TypefaceUtils.getTypeface(getActivity(), null));
    RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams4.addRule(14);
    localLayoutParams4.setMargins(0, (int)(i / 2 - 0.42F * i / 2.0F), 0, 0);
    this.bmi_scrollText.setId(this.bmi_scrollText.hashCode());
    this.bmi_layout.addView(this.bmi_scrollText, localLayoutParams4);
    this.bmi_scrollText.setText("00.0");
    this.bmi_scrollText.setAlpha(0.0F);
    this.bmiState = new TextView(getActivity());
    this.bmiState.setTextColor(j);
    this.bmiState.setTextSize(0, 0.11F * i);
    RelativeLayout.LayoutParams localLayoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams5.addRule(14);
    localLayoutParams5.addRule(3, this.bmi_scrollText.getId());
    this.bmi_layout.addView(this.bmiState, localLayoutParams5);
    this.bmiClickView = new View(getActivity());
    this.bmiClickView.setClickable(true);
    RelativeLayout.LayoutParams localLayoutParams6 = new RelativeLayout.LayoutParams(i, i);
    this.bmi_layout.addView(this.bmiClickView, localLayoutParams6);
    this.bmiClickView.setTag(Integer.valueOf((int)(0.05F * paramInt1)));
    this.bmiClickView.setOnClickListener(this.bmiCycleClick);
  }

  private void initBodyFatLayout(int paramInt1, int paramInt2)
  {
    int i = (int)(0.38F * paramInt1);
    int j = (int)(0.37F * paramInt2) + this.offectHeight;
    if (this.density == 2)
    {
      i = (int)(0.4F * paramInt1);
      j = (int)(0.37F * paramInt2) + this.offectHeight;
      int k = getResources().getColor(2131165203);
      RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(i, (int)(1.25F * i));
      localLayoutParams1.setMargins((int)(0.45F * paramInt1), j, 0, 0);
      this.body_fat_layout = new PicoocProgressLayout(getActivity(), k, -71, 322, "脂肪率", true, 0.0F);
      this.body_fat_layout.setId(this.body_fat_layout.hashCode());
      this.body_fat_layout.setClickable(false);
      this.layout.addView(this.body_fat_layout, localLayoutParams1);
      this.body_fat_scrollText = new ProgressTextView(getActivity(), k, 0.32F * i, null, TypefaceUtils.getTypeface(getActivity(), null));
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams2.addRule(14);
      this.body_fat_scrollText.setText("00.0");
      this.body_fat_scrollText.setAlpha(0.0F);
      localLayoutParams2.setMargins(0, (int)(i / 2 - 0.29F * i / 2.0F), 0, 0);
      this.body_fat_scrollText.setId(this.body_fat_scrollText.hashCode());
      this.body_fat_layout.addView(this.body_fat_scrollText, localLayoutParams2);
      this.body_fat_layout.setBackGroundLineColor(k);
      this.goleBodyFat = new TextView(getActivity());
      this.goleBodyFat.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
      this.goleBodyFat.setTextSize(getActivity().getResources().getDimension(2131230794));
      RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams3.addRule(14);
      this.goleBodyFat.setId(this.goleBodyFat.hashCode());
      this.goleBodyFat.setTextColor(-1);
      this.body_fat_layout.addView(this.goleBodyFat, localLayoutParams3);
      RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams4.addRule(14);
      this.question = new ImageView(getActivity());
      this.question.setImageResource(2130838660);
      this.question.setAlpha(0);
      this.body_fat_layout.addView(this.question, localLayoutParams4);
      this.bodyFatWrongTip = new TextView(getActivity());
      this.bodyFatWrongTip.setCompoundDrawablesWithIntrinsicBounds(0, 2130837566, 0, 0);
      this.bodyFatWrongTip.setCompoundDrawablePadding((int)(0.1F * i));
      this.bodyFatWrongTip.setText("测量错误");
      this.bodyFatWrongTip.setTextSize(20.0F);
      this.bodyFatWrongTip.setTextColor(Color.rgb(121, 255, 238));
      this.bodyFatWrongTip.setAlpha(0.0F);
      RelativeLayout.LayoutParams localLayoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams5.addRule(14);
      localLayoutParams5.addRule(3, this.goleBodyFat.getId());
      localLayoutParams5.setMargins(0, (int)(0.16D * i), 0, 0);
      this.body_fat_layout.addView(this.bodyFatWrongTip, localLayoutParams5);
      this.fatDabiaoText = new TextView(getActivity());
      RelativeLayout.LayoutParams localLayoutParams6 = new RelativeLayout.LayoutParams(-2, -2);
      this.fatDabiaoText.setCompoundDrawablesWithIntrinsicBounds(2130838060, 0, 0, 0);
      this.fatDabiaoText.setText("达标啦！");
      this.fatDabiaoText.setTextColor(k);
      this.fatDabiaoText.setCompoundDrawablePadding(10);
      localLayoutParams6.setMargins(0, (int)(i / 2 - 0.62F * i / 2.0F), 0, 0);
      localLayoutParams6.addRule(14);
      this.body_fat_layout.addView(this.fatDabiaoText, localLayoutParams6);
      this.fatChange = new TextView(getActivity());
      this.fatChange.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
      this.fatChange.setTextColor(k);
      this.fatChange.setCompoundDrawablePadding(5);
      this.fatChange.setCompoundDrawablesWithIntrinsicBounds(2130838440, 0, 0, 0);
      this.fatChange.setAlpha(0.0F);
      if (this.density != 1)
        break label968;
      this.fatChange.setTextSize(0.21F * (0.32F * i));
    }
    while (true)
    {
      RelativeLayout.LayoutParams localLayoutParams7 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams7.addRule(14);
      localLayoutParams7.addRule(3, this.body_fat_scrollText.getId());
      localLayoutParams7.setMargins(0, (int)getResources().getDimension(2131230743), 0, 0);
      this.body_fat_layout.addView(this.fatChange, localLayoutParams7);
      this.fatClickView = new View(getActivity());
      this.fatClickView.setClickable(true);
      RelativeLayout.LayoutParams localLayoutParams8 = new RelativeLayout.LayoutParams(i, i);
      this.body_fat_layout.addView(this.fatClickView, localLayoutParams8);
      this.fatClickView.setOnClickListener(this.fatCycleClick);
      return;
      if (this.density != 3)
        break;
      i = (int)(0.42F * paramInt1);
      j = (int)(0.39F * paramInt2) + this.offectHeight;
      break;
      label968: if (this.density == 2)
        this.fatChange.setTextSize(0.16F * (0.32F * i));
      else
        this.fatChange.setTextSize(0.106F * (0.32F * i));
    }
  }

  private void initBodyScoreLayout(int paramInt1, int paramInt2)
  {
    this.bodyScoreLayout = ((RelativeLayout)this.frameLayout.findViewById(2131099913));
    int i = (int)(0.23F * paramInt1);
    int j = (int)(0.03F * paramInt2) + this.offectHeight;
    if (this.density == 2)
    {
      i = (int)(0.25F * paramInt1);
      j = (int)(0.03F * paramInt2) + this.offectHeight;
    }
    while (true)
    {
      int k = (int)(0.5F * i);
      int m = i;
      int n = (int)(1.25F * i);
      System.out.println(m + " : " + n);
      RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(m, n);
      localLayoutParams1.setMargins(k, j, 0, 0);
      this.bodyScorePP = new PicoocProgressLayout(getActivity(), -65536, -90, 360, "身体得分", true, 4.3F);
      this.bodyScorePP.initProgress(40.0F);
      this.bodyScorePP.setBackGroundLineColor(Color.parseColor("#25ffffff"));
      this.bodyScoreLayout.addView(this.bodyScorePP, 0, localLayoutParams1);
      this.bodyScoreTv = new TextView(getActivity());
      this.bodyScoreTv.setId(this.bodyScoreTv.hashCode());
      this.bodyScoreTv.setTextColor(-1);
      this.bodyScoreTv.setTextSize(getActivity().getResources().getDimension(2131230797));
      this.bodyScoreTv.setTypeface(ModUtils.getTypeface(getActivity()));
      this.bodyUnit = new TextView(getActivity());
      this.bodyUnit.setText("分");
      this.bodyUnit.setTextSize(getActivity().getResources().getDimension(2131230796));
      this.bodyUnit.setTextColor(-1);
      this.bodyScoreliner = new LinearLayout(getActivity());
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
      this.bodyScoreliner.setOrientation(0);
      localLayoutParams2.topMargin = (i / 2 - i / 6);
      localLayoutParams2.addRule(14);
      this.bodyScoreliner.addView(this.bodyScoreTv);
      this.bodyScoreliner.addView(this.bodyUnit);
      this.bodyScorePP.addView(this.bodyScoreliner, localLayoutParams2);
      this.defenRrongImage = new ImageView(getActivity());
      this.defenRrongImage.setImageResource(2130837566);
      RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams3.topMargin = (j - j / 2);
      localLayoutParams3.addRule(14);
      this.defenRrongImage.setVisibility(8);
      this.bodyScorePP.addView(this.defenRrongImage, localLayoutParams3);
      this.bodyScorePP.setOnClickListener(this.bodyScoreClick);
      return;
      if (this.density == 3)
      {
        i = (int)(0.27F * paramInt1);
        j = (int)(0.03F * paramInt2) + this.offectHeight;
      }
    }
  }

  private void initWeightLayout(int paramInt1, int paramInt2)
  {
    int i = (int)(0.43F * paramInt1);
    int j = (int)(0.03F * paramInt2) + this.offectHeight;
    int k;
    if (this.density == 2)
    {
      i = (int)(0.45F * paramInt1);
      j = (int)(0.03F * paramInt2) + this.offectHeight;
      this.liftMaginTop = (-19 + (i / 2 + this.offectHeight));
      k = getResources().getColor(2131165201);
      RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(i, (int)(1.25F * i));
      localLayoutParams1.setMargins((int)(0.5F * paramInt1), j, 0, 0);
      this.weight_layout = new PicoocProgressLayout(getActivity(), k, -71, 322, "体重", true, 0.0F);
      this.layout.addView(this.weight_layout, 0, localLayoutParams1);
      this.goleWeight = new TextView(getActivity());
      this.goleWeight.setId(this.goleWeight.hashCode());
      this.goleWeight.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
      this.goleWeight.setTextColor(-1);
      this.goleWeight.setTextSize(getActivity().getResources().getDimension(2131230793));
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams2.addRule(14);
      this.weight_layout.addView(this.goleWeight, localLayoutParams2);
      this.weight_layout.setBackGroundLineColor(k);
      this.pangImage = new ImageView(getActivity());
      this.pangImage.setImageResource(2130838093);
      RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams3.addRule(14);
      localLayoutParams3.addRule(3, this.goleWeight.getId());
      localLayoutParams3.setMargins(0, 2, 0, 0);
      this.pangImage.setVisibility(4);
      this.weight_layout.addView(this.pangImage, localLayoutParams3);
      this.weight_scrollText = new PicoocScrollTextView(getActivity(), k, 0.29F * i, 3000, 1);
      RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams4.addRule(14);
      localLayoutParams4.setMargins(0, (int)(i / 2 - 0.29F * i / 2.0F), 0, 0);
      this.weight_scrollText.setId(this.weight_scrollText.hashCode());
      this.weight_layout.addView(this.weight_scrollText, localLayoutParams4);
      this.weight_scrollText.setClickable(true);
      this.weightChange = new TextView(getActivity());
      this.weightChange.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
      this.weightChange.setTextColor(k);
      this.weightChange.setCompoundDrawablePadding(5);
      this.weightChange.setCompoundDrawablesWithIntrinsicBounds(2130838442, 0, 0, 0);
      this.weightChange.setAlpha(0.0F);
      if (this.density != 1)
        break label964;
      this.weightChange.setTextSize(0.2F * (0.29F * i));
    }
    while (true)
    {
      RelativeLayout.LayoutParams localLayoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams5.addRule(14);
      localLayoutParams5.addRule(3, this.weight_scrollText.getId());
      localLayoutParams5.setMargins(0, (int)getResources().getDimension(2131230742), 0, 0);
      this.weight_layout.addView(this.weightChange, localLayoutParams5);
      this.weightDabiaoText = new TextView(getActivity());
      RelativeLayout.LayoutParams localLayoutParams6 = new RelativeLayout.LayoutParams(-2, -2);
      this.weightDabiaoText.setCompoundDrawablesWithIntrinsicBounds(2130838066, 0, 0, 0);
      this.weightDabiaoText.setText("达标啦！");
      this.weightDabiaoText.setTextColor(k);
      this.weightDabiaoText.setCompoundDrawablePadding(10);
      localLayoutParams6.setMargins(0, (int)(i / 2 - 0.62F * i / 2.0F), 0, 0);
      localLayoutParams6.addRule(14);
      this.weight_layout.addView(this.weightDabiaoText, localLayoutParams6);
      this.liftLinearlaout = new LinearLayout(getActivity());
      this.liftLinearlaout.setVisibility(8);
      this.liftLinearlaout.setClickable(false);
      RelativeLayout.LayoutParams localLayoutParams7 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams7.addRule(0, this.weight_layout.getId());
      localLayoutParams7.setMargins(0, j + i / 2, 0, 0);
      this.liftTextview = new TextView(getActivity());
      this.liftTextview.setBackgroundResource(2130838018);
      RelativeLayout.LayoutParams localLayoutParams8 = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams7.setMargins(3, 3, 3, 3);
      this.liftLinearlaout.addView(this.liftTextview, localLayoutParams8);
      this.layout.addView(this.liftLinearlaout, localLayoutParams7);
      this.weightClickView = new View(getActivity());
      this.weightClickView.setClickable(true);
      RelativeLayout.LayoutParams localLayoutParams9 = new RelativeLayout.LayoutParams(i, i);
      this.weight_layout.addView(this.weightClickView, localLayoutParams9);
      this.weightClickView.setOnClickListener(this.weightCycleClick);
      return;
      if (this.density != 3)
        break;
      i = (int)(0.47F * paramInt1);
      j = (int)(0.035F * paramInt2) + this.offectHeight;
      break;
      label964: if (this.density == 2)
        this.weightChange.setTextSize(0.15F * (0.29F * i));
      else
        this.weightChange.setTextSize(0.1F * (0.29F * i));
    }
  }

  private void initWeightState()
  {
    this.weightState = ((WeekWeightState)this.frameLayout.findViewById(2131099919));
  }

  private void isToGoal(BodyIndex paramBodyIndex, boolean paramBoolean)
  {
    if (this.app.getCurrentRole().getWeight_change_target() > 0.0F)
      if ((this.app.getCurrentRole().getGoal_weight() > 0.0F) && (paramBodyIndex.getWeight() >= this.app.getCurrentRole().getGoal_weight()))
      {
        this.weightDabiaoText.setAlpha(1.0F);
        if ((this.app.getCurrentRole().getGoal_fat() <= 0.0F) || (!paramBoolean) || (paramBodyIndex.getBodyFat() <= 0.0F))
          break label154;
        if (paramBodyIndex.getBodyFat() < this.app.getCurrentRole().getGoal_fat())
          break label145;
        this.fatDabiaoText.setCompoundDrawablesWithIntrinsicBounds(2130838067, 0, 0, 0);
        this.fatDabiaoText.setText("增重上限");
        this.fatDabiaoText.setAlpha(1.0F);
      }
    label145: label154: 
    while (this.app.getCurrentRole().getWeight_change_target() > 0.0F)
    {
      while (true)
      {
        return;
        this.weightDabiaoText.setAlpha(0.0F);
      }
      this.fatDabiaoText.setAlpha(0.0F);
      return;
      this.fatDabiaoText.setAlpha(0.0F);
      return;
    }
    if ((this.app.getCurrentRole().getGoal_weight() > 0.0F) && (paramBodyIndex.getWeight() <= this.app.getCurrentRole().getGoal_weight()))
      this.weightDabiaoText.setAlpha(1.0F);
    while ((this.app.getCurrentRole().getGoal_fat() > 0.0F) && (paramBoolean) && (paramBodyIndex.getBodyFat() > 0.0F))
      if (paramBodyIndex.getBodyFat() <= this.app.getCurrentRole().getGoal_fat())
      {
        this.fatDabiaoText.setCompoundDrawablesWithIntrinsicBounds(2130838060, 0, 0, 0);
        this.fatDabiaoText.setText("达标啦！");
        this.fatDabiaoText.setAlpha(1.0F);
        return;
        this.weightDabiaoText.setAlpha(0.0F);
      }
      else
      {
        this.fatDabiaoText.setAlpha(0.0F);
        return;
      }
    this.fatDabiaoText.setAlpha(0.0F);
  }

  private void refreshBMICircle()
  {
    float f = this.app.getTodayBody().getBmi();
    if (Age.isOld(this.app))
    {
      this.bmi_defenliner.setVisibility(8);
      this.bmi_scrollText.setVisibility(0);
      this.bmiState.setVisibility(0);
      this.bmi_RrongImage.setAlpha(0.0F);
      if (f > 0.0F)
      {
        ReportEntity localReportEntity = ReportDirect.judgeBMIByRole(this.app.getCurrentRole(), this.app.getTodayBody());
        int m = getBmiColor(localReportEntity.getStateCode());
        this.bmiState.setAlpha(1.0F);
        this.bmiState.setVisibility(0);
        this.bmiState.setText(getBmiStateString(localReportEntity.getStateCode()));
        this.bmiState.setTextColor(m);
        this.bmi_layout.setBackGroundLineColor(Color.parseColor("#25ffffff"));
        this.bmi_layout.setPaintColor(m);
        this.bmi_layout.setPaintWight(1.3F);
        this.bmi_layout.initProgress(100.0F);
        this.bmi_scrollText.setAlpha(1.0F);
        this.bmi_scrollText.setTextColor(m);
        this.bmi_scrollText.setText(f);
        this.bmi_layout.setProgressName("BMI");
      }
      while (true)
      {
        if (this.app.getCurrentRole().getGoal_weight() <= 0.0F)
        {
          this.bmi_defenliner.setAlpha(0.0F);
          this.bmi_RrongImage.setAlpha(0.0F);
          this.bmi_layout.initProgress(0.0F);
        }
        return;
        this.bmiState.setAlpha(0.0F);
        this.bmi_scrollText.setAlpha(0.0F);
        this.bmi_layout.initProgress(0.0F);
        int k = Color.argb(50, 255, 255, 255);
        this.bmi_layout.setBackGroundLineColor(k);
      }
    }
    this.bmi_defenliner.setVisibility(0);
    this.bmi_scrollText.setVisibility(8);
    this.bmiState.setVisibility(8);
    BodyIndex localBodyIndex = this.app.getTodayBody();
    RoleBin localRoleBin = this.app.getCurrentRole();
    if (localBodyIndex.getBodyFat() > 0.0F)
    {
      int i = getBodyScore(localBodyIndex, localRoleBin);
      int j = this.mainBodyScoreModel.getCycleColor();
      this.bmi_defenTv.setTextColor(j);
      this.bmi_layout.setPaintColor(j);
      this.bmi_defenTv.setText(i);
      this.bmi_layout.initProgress(i);
      this.bmi_RrongImage.setAlpha(0.0F);
      this.bmi_defenliner.setAlpha(1.0F);
    }
    while (true)
    {
      this.bmi_layout.setProgressName("身体得分");
      this.bmi_layout.setPaintWight(4.3F);
      break;
      this.bmi_defenliner.setAlpha(0.0F);
      this.bmi_RrongImage.setAlpha(1.0F);
      this.bmi_layout.initProgress(0.0F);
    }
  }

  private void refreshEverydayMe()
  {
    this.arcMenu.refreshEveryMe(this.app.getEveryMeToday());
  }

  private void refreshFatCircle()
  {
    float f = this.app.getTodayBody().getBodyFat();
    System.out.println("fat =" + f);
    if (f > 0.0F)
    {
      this.body_fat_scrollText.setAlpha(1.0F);
      this.body_fat_scrollText.setText(f + "%");
      if (Age.isOld(this.app))
      {
        this.body_fat_layout.setAlpha(1.0F);
        this.goleBodyFat.setVisibility(8);
        this.body_fat_layout.setmArcBGPaintColor(Color.parseColor("#25ffffff"));
        this.body_fat_layout.setPaintColor(Age.getColor(this.mainBodyScoreModel.getFatReport()));
        this.body_fat_layout.resetProgress(0, 360);
        this.body_fat_layout.initProgress(100.0F);
        this.fatChange.setTextColor(Age.getColor(this.mainBodyScoreModel.getFatReport()));
        this.body_fat_scrollText.setTextColor(Age.getColor(this.mainBodyScoreModel.getFatReport()));
        this.fatChange.setText(Age.getFatText(this.mainBodyScoreModel.getFatReport()));
        this.fatChange.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        this.fatChange.setAlpha(1.0F);
        if (this.bodyFatWrongTip.getAlpha() == 1.0F)
          this.bodyFatWrongTip.setAlpha(0.0F);
        return;
      }
      int i = getResources().getColor(2131165203);
      this.body_fat_layout.setmArcBGPaintColor(i);
      this.fatChange.setTextColor(i);
      this.body_fat_scrollText.setTextColor(i);
      this.body_fat_layout.setPaintColor(i);
      this.body_fat_layout.resetProgress(-71, 322);
      this.goleBodyFat.setVisibility(0);
      if (this.app.getBodyFaChangeValue() <= -1000.0F)
      {
        this.fatChange.setAlpha(0.0F);
        this.bodyFatWrongTip.setAlpha(0.0F);
        this.body_fat_layout.initProgress(this.app.calculateFatDegree());
        return;
      }
      if (this.app.getBodyFaChangeValue() <= 0.0F)
        this.fatChange.setCompoundDrawablesWithIntrinsicBounds(2130838440, 0, 0, 0);
      while (true)
      {
        this.fatChange.setAlpha(1.0F);
        if (this.app.getBodyFaChangeValue() <= -1000.0F);
        this.fatChange.setText(NumUtils.roundValue(Math.abs(this.app.getBodyFaChangeValue())) + "%");
        break;
        this.fatChange.setCompoundDrawablesWithIntrinsicBounds(2130838441, 0, 0, 0);
      }
    }
    this.body_fat_scrollText.setAlpha(0.0F);
    this.fatChange.setAlpha(0.0F);
    this.body_fat_layout.initProgress(0.0F);
    if (Age.isOld(this.app))
    {
      this.goleBodyFat.setVisibility(8);
      this.body_fat_layout.resetProgress(0, 360);
      this.body_fat_layout.setmArcBGPaintColor(Color.parseColor("#25ffffff"));
    }
    while (this.app.getTodayBody().getWeight() > 0.0F)
    {
      this.bodyFatWrongTip.setAlpha(1.0F);
      return;
      this.goleBodyFat.setVisibility(0);
      this.body_fat_layout.resetProgress(-71, 322);
      this.body_fat_layout.setmArcBGPaintColor(getResources().getColor(2131165203));
    }
    this.bodyFatWrongTip.setAlpha(0.0F);
  }

  private void refreshGoalFat()
  {
    if (Age.isOld(this.app))
    {
      this.question.setAlpha(0);
      return;
    }
    float f = this.app.getCurrentRole().getGoal_fat();
    System.out.println("goleFat =" + f);
    if (f > 0.0F)
    {
      this.goleBodyFat.setText(NumUtils.roundValue(f) + "%");
      this.question.setAlpha(0);
      return;
    }
    this.goleBodyFat.setText("");
    if ((this.app.getCurrentRole().getGoal_weight() > 0.0F) && (this.app.getTodayBody().getWeight() > 0.0F) && (this.app.getTodayBody().getBodyFat() > 0.0F))
    {
      this.question.setAlpha(255);
      return;
    }
    this.question.setAlpha(0);
  }

  private void refreshGoalWeight()
  {
    Log.i("picooc", "--------app=" + this.app + "----getActivity()==" + getActivity());
    if (this.app == null)
      this.app = ((MyApplication)PicoocActivity3.context.getApplicationContext());
    float f = this.app.getCurrentRole().getGoal_weight();
    if (f > 0.0F)
    {
      this.goleWeight.setText(NumUtils.roundValue(f) + "kg");
      return;
    }
    this.goleWeight.setText("");
  }

  private void refreshSharedButton()
  {
    if (this.app.getCurrentRole().getRole_id() == this.app.getCurrentUser().getRole_id())
    {
      this.huizhangclick.setVisibility(0);
      if (!Age.isOld(this.app))
        this.yellowImage.setVisibility(0);
      while (true)
      {
        if ((this.app.getContinuousWeightingSharedModel() != null) && (this.app.getContinuousWeightingSharedModel().getMainFlag() == 2) && (this.app.getContinuousWeightingSharedModel().getMessage1() != null))
        {
          this.ShareText.setVisibility(0);
          this.ShareText.setText(this.app.getContinuousWeightingSharedModel().getMessage1());
        }
        return;
        ((View)this.yellowImage.getParent()).setVisibility(8);
      }
    }
    this.huizhangclick.setVisibility(8);
    this.yellowImage.setVisibility(8);
  }

  private void refreshWeightCircle()
  {
    float f1 = this.app.getTodayBody().getWeight();
    this.weight_scrollText.setText(f1);
    if (f1 > 0.0F)
    {
      this.body_fat_scrollText.setAlpha(1.0F);
      this.weight_layout.setBackGroundLineColorAndRefesh(getResources().getColor(2131165201));
      if (this.app.getWeightChangeValue() <= -1000.0F)
      {
        this.weightChange.setAlpha(0.0F);
        this.weight_layout.initProgress(this.app.calculateWeightDegree());
        if (ReportDirect.judgeWeightByRole(this.app.getCurrentRole(), this.app.getTodayBody().getWeight()).getStateCode() <= 2)
          break label260;
        if (this.weightDabiaoText.getAlpha() != 1.0F)
        {
          this.mAnim.showAnima(this.pangImage, 100L);
          this.pangImage.setVisibility(0);
        }
      }
      else
      {
        if (this.app.getWeightChangeValue() <= 0.0F)
          this.weightChange.setCompoundDrawablesWithIntrinsicBounds(2130838442, 0, 0, 0);
        while (true)
        {
          this.weightChange.setAlpha(1.0F);
          float f2 = this.app.getWeightChangeValue();
          if (f2 <= -1000.0F)
            f2 = 0.0F;
          this.weightChange.setText(NumUtils.roundValue(Math.abs(f2)) + "kg");
          break;
          this.weightChange.setCompoundDrawablesWithIntrinsicBounds(2130838443, 0, 0, 0);
        }
      }
      this.pangImage.setVisibility(4);
      return;
      label260: this.pangImage.setVisibility(4);
      return;
    }
    this.body_fat_scrollText.setAlpha(0.0F);
    this.weightChange.setAlpha(0.0F);
    this.weight_layout.initProgress(0.0F);
    this.pangImage.setVisibility(4);
  }

  private void refreshWeightData()
  {
    LatinWeekData localLatinWeekData = this.app.getWeekDataByFlag(0);
    this.weightState.init(localLatinWeekData.getWeightArray(), this.app.getCurrentRole().getFirst_use_time());
  }

  private void shakeAnim(View paramView, String paramString)
  {
    try
    {
      paramView.setVisibility(0);
      paramView.setAlpha(1.0F);
      if ((paramView instanceof TextView))
        ((TextView)paramView).setText(paramString);
      AnimationUtils.startBounceDouble(paramView, 1.5F, paramView, true, null);
      return;
    }
    finally
    {
    }
  }

  private void startNetAnim()
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        AnimUtils.LiftandRightMove(LatinMainFragment.this.textMiddle, 0, -LatinMainFragment.this.textMiddle.getWidth(), 3000);
        AnimUtils.LiftandRightMove(LatinMainFragment.this.imageRight, 0, -LatinMainFragment.this.textMiddle.getWidth(), 3000);
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            LatinMainFragment.this.mAnim.missAnima(LatinMainFragment.this.linerid, 1000L);
            LatinMainFragment.this.mAnim.missAnima(LatinMainFragment.this.imagehui, 1000L);
            LatinMainFragment.this.mAnim.showAnima(LatinMainFragment.this.imagehong, 1200L);
          }
        }
        , 3010L);
      }
    }
    , 10000L);
  }

  private void stop(final BodyIndex paramBodyIndex)
  {
    Log.i("whatlong", "接收到数据，stop");
    this.weight_scrollText.stopNumberBounceOnNum(paramBodyIndex.getWeight());
    this.mAnim.showAnima(this.weight_scrollText, 1500L);
    int i = this.weight_layout.stopAtNum(this.app.calculateWeightDegree());
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        LatinMainFragment.this.stop_step2(paramBodyIndex);
      }
    }
    , i);
  }

  private void stop_step2(final BodyIndex paramBodyIndex)
  {
    float f1 = paramBodyIndex.getBmi();
    if (Age.isOld(this.app))
    {
      if (f1 > 0.0F)
      {
        ReportEntity localReportEntity = ReportDirect.judgeBMIByRole(this.app.getCurrentRole(), paramBodyIndex);
        int i = getBmiColor(localReportEntity.getStateCode());
        this.bmi_scrollText.setTextColor(i);
        this.bmi_layout.initProgress(0.0F);
        this.bmi_layout.setPaintColor(i);
        this.bmiState.setTextColor(i);
        this.bmiState.setText(getBmiStateString(localReportEntity.getStateCode()));
        this.bmi_scrollText.startZoomoutAndZoominAnim(NumUtils.roundValue(f1));
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            LatinMainFragment.this.mAnim.showAnima(LatinMainFragment.this.bmi_scrollText, 1500L);
          }
        }
        , 600L);
      }
    }
    else
    {
      float f2 = paramBodyIndex.getBodyFat();
      if (f2 <= 0.0F)
        break label323;
      this.body_fat_scrollText.startZoomoutAndZoominAnim(NumUtils.roundValue(f2) + "%");
      if (!Age.isOld(this.app))
        break label260;
      this.body_fat_scrollText.setTextColor(Age.getColor(this.mainBodyScoreModel.getFatReport()));
      label197: new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          LatinMainFragment.this.mAnim.showAnima(LatinMainFragment.this.body_fat_scrollText, 1500L);
        }
      }
      , 600L);
    }
    while (true)
    {
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          LatinMainFragment.this.stop_step3(paramBodyIndex);
        }
      }
      , 1000L);
      return;
      this.mAnim.missAnima(this.bmi_scrollText, 500L);
      break;
      label260: this.body_fat_scrollText.setTextColor(getResources().getColor(2131165203));
      AnimUtils.startZoomoutAndZoominAnim(this.bmi_defenliner, this.bmi_defenTv, getBodyScore(paramBodyIndex, this.app.getCurrentRole()), this.mainBodyScoreModel.getCycleColor());
      break label197;
      label323: Log.i("qianmo2", "执行到fat错我了");
      this.mAnim.missAnima(this.body_fat_scrollText, 500L);
      this.mAnim.showAnima(this.bodyFatWrongTip, 500L);
      if (!Age.isOld(this.app))
      {
        this.mAnim.missAnima(this.bmi_defenliner, 500L);
        this.mAnim.showAnima(this.bmi_RrongImage, 500L);
        this.bmi_layout.initProgress(0.0F);
      }
    }
  }

  private void stop_step3(final BodyIndex paramBodyIndex)
  {
    Log.i("picooc", "stop_step3==" + this.app.calculateFatDegree());
    long l;
    if (Age.isOld(this.app))
    {
      this.bmi_RrongImage.setAlpha(0.0F);
      if (paramBodyIndex.getBodyFat() > 0.0F)
      {
        this.goleBodyFat.setVisibility(8);
        this.body_fat_layout.setPaintColor(Age.getColor(this.mainBodyScoreModel.getFatReport()));
        this.body_fat_layout.stopAtNum(80.0F);
        this.body_fat_layout.resetProgress(0, 360);
        this.body_fat_layout.initProgress(100.0F);
      }
      Log.i("qianmo2", "fatSweep==" + this.body_fat_layout.getSweep());
      l = 1000L;
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          ReportEntity localReportEntity = ReportDirect.judgeWeightByRole(LatinMainFragment.this.app.getCurrentRole(), paramBodyIndex.getWeight());
          LatinMainFragment.this.fatChange.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
          LatinMainFragment.this.fatChange.setTextColor(Age.getColor(LatinMainFragment.this.mainBodyScoreModel.getFatReport()));
          if (paramBodyIndex.getBodyFat() > 0.0F)
          {
            LatinMainFragment.this.shakeAnim(LatinMainFragment.this.fatChange, Age.getFatText(LatinMainFragment.this.mainBodyScoreModel.getFatReport()));
            LatinMainFragment.this.bodyFatWrongTip.setAlpha(0.0F);
            LatinMainFragment.this.isToGoal(paramBodyIndex, false);
            if (LatinMainFragment.this.app.getWeightChangeValue() > -1000.0F)
            {
              if (LatinMainFragment.this.app.getWeightChangeValue() > 0.0F)
                break label426;
              LatinMainFragment.this.weightChange.setCompoundDrawablesWithIntrinsicBounds(2130838442, 0, 0, 0);
            }
          }
          while (true)
          {
            LatinMainFragment.this.weightChange.setAlpha(1.0F);
            LatinMainFragment.this.shakeAnim(LatinMainFragment.this.weightChange, NumUtils.roundValue(Math.abs(LatinMainFragment.this.app.getWeightChangeValue())) + "kg");
            if ((localReportEntity.getStateCode() > 2) && (LatinMainFragment.this.weightDabiaoText.getAlpha() != 1.0F))
              LatinMainFragment.this.shakeAnim(LatinMainFragment.this.pangImage, null);
            LatinMainFragment.this.shakeAnim(LatinMainFragment.this.bmiState, LatinMainFragment.this.bmiState.getText().toString());
            LatinMainFragment.this.bmi_layout.stopAtNum(50.0F);
            LatinMainFragment.this.bmi_layout.initProgress(100.0F);
            if (paramBodyIndex.getBodyFat() <= 0.0F)
            {
              LatinMainFragment.this.mAnim.showAnima(LatinMainFragment.this.defenRrongImage, 10L);
              LatinMainFragment.this.defenRrongImage.setVisibility(0);
              LatinMainFragment.this.bodyScoreliner.setAlpha(0.0F);
            }
            if (LatinMainFragment.this.app.getCurrentRole().getGoal_weight() <= 0.0F)
              new Handler().postDelayed(new Runnable()
              {
                public void run()
                {
                  LatinMainFragment.this.mAnim.getPopupWindowMakeGoal();
                }
              }
              , 1000L);
            return;
            LatinMainFragment.this.bodyFatWrongTip.setAlpha(1.0F);
            break;
            label426: LatinMainFragment.this.weightChange.setCompoundDrawablesWithIntrinsicBounds(2130838443, 0, 0, 0);
          }
        }
      }
      , l);
      if (paramBodyIndex.getBodyFat() > 0.0F)
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            RoleBin localRoleBin = LatinMainFragment.this.app.getCurrentRole();
            int i = LatinMainFragment.this.getBodyScore(paramBodyIndex, localRoleBin);
            LatinMainFragment.this.defenRrongImage.setVisibility(8);
            int j = LatinMainFragment.this.mainBodyScoreModel.getCycleColor();
            LatinMainFragment.this.bodyScoreTv.setTextColor(j);
            LatinMainFragment.this.bodyScorePP.setPaintColor(j);
            LatinMainFragment.this.bodyScoreTv.setText(i);
            LatinMainFragment.this.bodyScoreliner.setAlpha(1.0F);
            LatinMainFragment.this.bodyScorePP.stopAtNum(i);
          }
        }
        , l * 2L);
    }
    while (true)
    {
      if (!SharedPreferenceUtils.isFirstEnterCurPage(getActivity(), "fragment_weight"))
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
        {
          public void run()
          {
            if (!Age.isOld(LatinMainFragment.this.app))
              LatinMainFragment.this.guideSetFatOnly();
          }
        }
        , 100L + l * 2L);
      return;
      this.body_fat_scrollText.setTextColor(getResources().getColor(2131165203));
      this.body_fat_layout.setPaintColor(getResources().getColor(2131165203));
      l = this.body_fat_layout.stopAtNum(this.app.calculateFatDegree());
      this.bmi_layout.setPaintColor(this.mainBodyScoreModel.getCycleColor());
      if (paramBodyIndex.getBodyFat() > 0.0F)
        this.bmi_layout.stopAtNum(getBodyScore(paramBodyIndex, this.app.getCurrentRole()));
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          ReportEntity localReportEntity = ReportDirect.judgeWeightByRole(LatinMainFragment.this.app.getCurrentRole(), paramBodyIndex.getWeight());
          if (LatinMainFragment.this.app.getWeightChangeValue() > -1000.0F)
          {
            if (LatinMainFragment.this.app.getWeightChangeValue() <= 0.0F)
            {
              LatinMainFragment.this.weightChange.setCompoundDrawablesWithIntrinsicBounds(2130838442, 0, 0, 0);
              LatinMainFragment.this.weightChange.setAlpha(1.0F);
              LatinMainFragment.this.shakeAnim(LatinMainFragment.this.weightChange, NumUtils.roundValue(Math.abs(LatinMainFragment.this.app.getWeightChangeValue())) + "kg");
            }
          }
          else
          {
            LatinMainFragment.this.isToGoal(paramBodyIndex, true);
            if ((localReportEntity.getStateCode() > 2) && (LatinMainFragment.this.weightDabiaoText.getAlpha() != 1.0F))
              LatinMainFragment.this.shakeAnim(LatinMainFragment.this.pangImage, null);
            Log.i("picooc", "小胖人re==" + localReportEntity.getStateCode());
            if (LatinMainFragment.this.app.getBodyFaChangeValue() > -1000.0F)
            {
              if (LatinMainFragment.this.app.getBodyFaChangeValue() > 0.0F)
                break label511;
              LatinMainFragment.this.fatChange.setCompoundDrawablesWithIntrinsicBounds(2130838440, 0, 0, 0);
            }
          }
          while (true)
          {
            if (LatinMainFragment.this.app.getTodayBody().getBodyFat() > 0.0F)
            {
              LatinMainFragment.this.fatChange.setAlpha(1.0F);
              LatinMainFragment.this.shakeAnim(LatinMainFragment.this.fatChange, NumUtils.roundValue(Math.abs(LatinMainFragment.this.app.getBodyFaChangeValue())) + "%");
            }
            if (Age.isOld(LatinMainFragment.this.app))
            {
              LatinMainFragment.this.bmiState.setAlpha(1.0F);
              LatinMainFragment.this.shakeAnim(LatinMainFragment.this.bmiState, LatinMainFragment.this.bmiState.getText().toString());
            }
            LatinMainFragment.this.weightState.refreshState(paramBodyIndex.getTime());
            if (LatinMainFragment.this.app.getCurrentRole().getGoal_weight() <= 0.0F)
              new Handler().postDelayed(new Runnable()
              {
                public void run()
                {
                  LatinMainFragment.this.mAnim.getPopupWindowMakeGoal();
                }
              }
              , 1000L);
            Log.i("picooc", "merime==" + SharedPreferenceUtils.iSMeRiMeTime(LatinMainFragment.this.getActivity()));
            if (SharedPreferenceUtils.iSMeRiMeTime(LatinMainFragment.this.getActivity()).booleanValue())
              LatinMainFragment.this.arcMenu.setChooseLayoutShow(true);
            return;
            LatinMainFragment.this.weightChange.setCompoundDrawablesWithIntrinsicBounds(2130838443, 0, 0, 0);
            break;
            label511: LatinMainFragment.this.fatChange.setCompoundDrawablesWithIntrinsicBounds(2130838441, 0, 0, 0);
          }
        }
      }
      , l);
      new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
      {
        public void run()
        {
          if (LatinMainFragment.this.app.getContinuousWeightingSharedModel() == null);
          do
          {
            HashMap localHashMap;
            do
            {
              return;
              localHashMap = LatinMainFragment.this.app.getContinuousWeightingSharedModel().getDialogMessageAndImage();
            }
            while (LatinMainFragment.this.app.getCurrentRole().getRole_id() != LatinMainFragment.this.app.getCurrentUser().getRole_id());
            if ((LatinMainFragment.this.app.getContinuousWeightingSharedModel().getMainFlag() == 1) && (localHashMap != null))
            {
              LatinMainFragment.this.mAnim.getPopupWindowShare(localHashMap.get("message"), Integer.parseInt(localHashMap.get("image")));
              return;
            }
          }
          while ((LatinMainFragment.this.app.getContinuousWeightingSharedModel().getMainFlag() != 2) || (LatinMainFragment.this.app.getContinuousWeightingSharedModel().getMessage1() == null) || (Age.isOld(LatinMainFragment.this.app)));
          LatinMainFragment.this.ShareText.setVisibility(0);
          LatinMainFragment.this.ShareText.setText(LatinMainFragment.this.app.getContinuousWeightingSharedModel().getMessage1());
        }
      }
      , l * 2L);
    }
  }

  public int getBodyScore(BodyIndex paramBodyIndex, RoleBin paramRoleBin)
  {
    if (this.mainBodyScoreModel == null)
      this.mainBodyScoreModel = new MainBodyScoreModel(this.app.getCurrentRole(), this.app.getTodayBody());
    return this.mainBodyScoreModel.getTotalNum();
  }

  public void guideSetFatOnly()
  {
    curGetType = 2;
    if (SharedPreferenceUtils.isFirstEnterCurPage(getActivity(), "fragment_fat"))
    {
      moveGuideWeight();
      SharedPreferenceUtils.resetCurPage(getActivity(), false, "fragment_fat");
    }
  }

  public void guideSetWeightAndFat()
  {
    curGetType = 3;
    boolean bool1 = SharedPreferenceUtils.isFirstEnterCurPage(getActivity(), "fragment_weight");
    boolean bool2 = SharedPreferenceUtils.isFirstEnterCurPage(getActivity(), "fragment_fat");
    if ((bool1) && (bool2))
    {
      moveGuideWeight();
      SharedPreferenceUtils.resetCurPage(getActivity(), false, "fragment_weight");
      SharedPreferenceUtils.resetCurPage(getActivity(), false, "fragment_fat");
    }
  }

  public void guideSetWeightOnly()
  {
    curGetType = 1;
    if (SharedPreferenceUtils.isFirstEnterCurPage(getActivity(), "fragment_weight"))
    {
      moveGuideWeight();
      SharedPreferenceUtils.resetCurPage(getActivity(), false, "fragment_weight");
    }
  }

  public void guideWeightOrFatSetting()
  {
    if ((this.app.getCurrentRole().getGoal_weight() > 0.0F) && (this.app.getCurrentRole().getGoal_fat() <= 0.0F))
      guideSetWeightOnly();
    do
    {
      do
      {
        return;
        if ((this.app.getCurrentRole().getGoal_weight() > 0.0F) || (this.app.getCurrentRole().getGoal_fat() <= 0.0F))
          break;
      }
      while (Age.isOld(this.app));
      guideSetFatOnly();
      return;
    }
    while ((this.app.getCurrentRole().getGoal_weight() <= 0.0F) || (this.app.getCurrentRole().getGoal_fat() <= 0.0F));
    if (Age.isOld(this.app))
    {
      guideSetWeightOnly();
      return;
    }
    guideSetWeightAndFat();
  }

  public void iknow()
  {
    if (SharedPreferenceUtils.isFirstEnterCurPage(getActivity(), "main_fragment"))
    {
      moveGuide();
      SharedPreferenceUtils.resetCurPage(getActivity(), false, "main_fragment");
    }
  }

  public void initEverydayMe(int paramInt1, int paramInt2)
  {
    this.arcMenu = ((EverydayMe)this.frameLayout.findViewById(2131099914));
  }

  public void moveGuide()
  {
    this.phoneImageView = ((ImageView)this.frameLayout.findViewById(2131099903));
    this.waveTextView = ((TextView)this.frameLayout.findViewById(2131099904));
    this.askImageView = ((ImageView)this.frameLayout.findViewById(2131099905));
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        LatinMainFragment.this.mGuideList = new ArrayList();
        Rect localRect = new Rect();
        LatinMainFragment.this.getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        int i = localRect.top;
        LatinMainFragment.this.locationImageView = new int[2];
        LatinMainFragment.this.phoneImageView.setDrawingCacheEnabled(true);
        LatinMainFragment.this.phoneImageView.getLocationInWindow(LatinMainFragment.this.locationImageView);
        LatinMainFragment.this.mGuide = new GuideModel(LatinMainFragment.this.phoneImageView.getDrawingCache(), LatinMainFragment.this.locationImageView[0], LatinMainFragment.this.locationImageView[1]);
        LatinMainFragment.this.mGuideList.add(LatinMainFragment.this.mGuide);
        LatinMainFragment.this.loactionTextView = new int[2];
        LatinMainFragment.this.waveTextView.setDrawingCacheEnabled(true);
        LatinMainFragment.this.waveTextView.getLocationInWindow(LatinMainFragment.this.loactionTextView);
        LatinMainFragment.this.mGuide = new GuideModel(LatinMainFragment.this.waveTextView.getDrawingCache(), LatinMainFragment.this.loactionTextView[0], LatinMainFragment.this.loactionTextView[1]);
        LatinMainFragment.this.mGuideList.add(LatinMainFragment.this.mGuide);
        LatinMainFragment.this.locationAskImageView = new int[2];
        LatinMainFragment.this.askImageView.setDrawingCacheEnabled(true);
        LatinMainFragment.this.askImageView.getLocationInWindow(LatinMainFragment.this.locationAskImageView);
        LatinMainFragment.this.mGuide = new GuideModel(LatinMainFragment.this.askImageView.getDrawingCache(), LatinMainFragment.this.locationAskImageView[0], LatinMainFragment.this.locationAskImageView[1]);
        LatinMainFragment.this.mGuideList.add(LatinMainFragment.this.mGuide);
        Intent localIntent = new Intent(LatinMainFragment.this.getActivity(), GuideAct.class);
        localIntent.putExtra("pageId", 2);
        localIntent.putExtra("statusBarHeight", i);
        localIntent.putExtra("guideList", (Serializable)LatinMainFragment.this.mGuideList);
        LatinMainFragment.this.startActivityForResult(localIntent, 10);
        LatinMainFragment.this.getActivity().overridePendingTransition(-1, -1);
      }
    }
    , 100L);
  }

  public void moveGuideWeight()
  {
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        LatinMainFragment.this.mGuideList = new ArrayList();
        Rect localRect = new Rect();
        LatinMainFragment.this.getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        int i = localRect.top;
        if ((LatinMainFragment.curGetType == 1) || (LatinMainFragment.curGetType == 3))
        {
          LatinMainFragment.this.locationImageView = new int[2];
          LatinMainFragment.this.weight_layout.getArcs().setDrawingCacheEnabled(true);
          LatinMainFragment.this.weight_layout.getArcs().getLocationInWindow(LatinMainFragment.this.locationImageView);
          LatinMainFragment.this.mGuide = new GuideModel(LatinMainFragment.this.weight_layout.getArcs().getDrawingCache(), LatinMainFragment.this.locationImageView[0], LatinMainFragment.this.locationImageView[1]);
          LatinMainFragment.this.mGuideList.add(LatinMainFragment.this.mGuide);
          LatinMainFragment.this.loactionTextView = new int[2];
          LatinMainFragment.this.goleWeight.setDrawingCacheEnabled(true);
          LatinMainFragment.this.goleWeight.getLocationInWindow(LatinMainFragment.this.loactionTextView);
          LatinMainFragment.this.mGuide = new GuideModel(LatinMainFragment.this.goleWeight.getDrawingCache(), LatinMainFragment.this.loactionTextView[0], LatinMainFragment.this.loactionTextView[1]);
          LatinMainFragment.this.mGuideList.add(LatinMainFragment.this.mGuide);
        }
        if ((LatinMainFragment.curGetType == 2) || (LatinMainFragment.curGetType == 3))
        {
          LatinMainFragment.this.locationImageView = new int[2];
          LatinMainFragment.this.body_fat_layout.getArcs().setDrawingCacheEnabled(true);
          LatinMainFragment.this.body_fat_layout.getArcs().getLocationInWindow(LatinMainFragment.this.locationImageView);
          LatinMainFragment.this.mGuide = new GuideModel(LatinMainFragment.this.body_fat_layout.getArcs().getDrawingCache(), LatinMainFragment.this.locationImageView[0], LatinMainFragment.this.locationImageView[1]);
          LatinMainFragment.this.mGuideList.add(LatinMainFragment.this.mGuide);
          LatinMainFragment.this.loactionTextView = new int[2];
          LatinMainFragment.this.goleBodyFat.setDrawingCacheEnabled(true);
          LatinMainFragment.this.goleBodyFat.getLocationInWindow(LatinMainFragment.this.loactionTextView);
          LatinMainFragment.this.mGuide = new GuideModel(LatinMainFragment.this.goleBodyFat.getDrawingCache(), LatinMainFragment.this.loactionTextView[0], LatinMainFragment.this.loactionTextView[1]);
          LatinMainFragment.this.mGuideList.add(LatinMainFragment.this.mGuide);
        }
        com.picooc.guide.ModelData.mGuideList = LatinMainFragment.this.mGuideList;
        Intent localIntent = new Intent(LatinMainFragment.this.getActivity(), GuideAct.class);
        if (LatinMainFragment.curGetType == 3)
          localIntent.putExtra("pageId", 8);
        while (true)
        {
          localIntent.putExtra("statusBarHeight", i);
          LatinMainFragment.this.startActivity(localIntent);
          LatinMainFragment.this.getActivity().overridePendingTransition(-1, -1);
          Log.i("picooc", "mainfragment:startActivity");
          return;
          if (LatinMainFragment.curGetType == 1)
            localIntent.putExtra("pageId", 10);
          else if (LatinMainFragment.curGetType == 2)
            localIntent.putExtra("pageId", 11);
        }
      }
    }
    , 200L);
  }

  public void netWorkChange(boolean paramBoolean)
  {
    if (this.imagehong == null)
      return;
    if (paramBoolean)
    {
      this.mAnim.missAnima(this.imagehong, 1000L);
      this.imagehong.setClickable(false);
      return;
    }
    this.imagehong.setVisibility(0);
    this.imagehong.setClickable(true);
    this.mAnim.showAnima(this.imagehong, 1000L);
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt2 == 10) && (paramIntent != null) && (paramIntent.getStringExtra("page").equalsIgnoreCase("LatinMainFragment")))
      guideWeightOrFatSetting();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mAnim = new AnimUtils(getActivity());
    this.app = ((MyApplication)getActivity().getApplicationContext());
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903083, paramViewGroup, false);
    this.frameLayout = ((LinearLayout)localView.findViewById(2131099899));
    init();
    startShake();
    Log.i("picooc", "------------onCreateView");
    iknow();
    return localView;
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public void onResume()
  {
    super.onResume();
    this.linearLayout_bg = ((LinearLayout)getActivity().findViewById(2131099739));
    if (this.themeConstant.getbgResource().intValue() == 2130837526)
      if (((Integer)SharedPreferenceUtils.getValue(getActivity(), "user-Info", "has_device", Integer.class)).intValue() > 1)
      {
        this.why___what.setImageResource(2130837534);
        this.linearLayout_bg.setBackgroundResource(2130837526);
      }
    while (true)
    {
      this.arcMenu.setChooseTheme();
      this.arcMenu.setEveryDayTheme();
      return;
      this.why___what.setImageResource(2130837531);
      break;
      if (this.themeConstant.getbgResource().intValue() == 2130837527)
      {
        this.linearLayout_bg.setBackgroundResource(2130837527);
        if (((Integer)SharedPreferenceUtils.getValue(getActivity(), "user-Info", "has_device", Integer.class)).intValue() > 1)
          this.why___what.setImageResource(2130837535);
        else
          this.why___what.setImageResource(2130837532);
      }
      else
      {
        this.linearLayout_bg.setBackgroundResource(2130837525);
        if (((Integer)SharedPreferenceUtils.getValue(getActivity(), "user-Info", "has_device", Integer.class)).intValue() > 1)
          this.why___what.setImageResource(2130837533);
        else
          this.why___what.setImageResource(2130837530);
      }
    }
  }

  public void onStart()
  {
    super.onStart();
    this.arcMenu.refresh();
  }

  public void refreshBodyScore()
  {
    Log.i("qianmo2", "isold===" + Age.isOld(this.app));
    if (Age.isOld(this.app))
    {
      this.arcMenu.setVisibility(8);
      this.bodyScoreLayout.setVisibility(0);
      if (this.bodyScorePP != null);
    }
    else
    {
      this.arcMenu.setVisibility(0);
      this.bodyScoreLayout.setVisibility(8);
      return;
    }
    BodyIndex localBodyIndex = this.app.getTodayBody();
    RoleBin localRoleBin = this.app.getCurrentRole();
    if (this.app.getCurrentRole().getGoal_weight() != 0.0F)
    {
      if ((localBodyIndex.getWeight() > 0.0F) && (localBodyIndex.getBodyFat() > 0.0F) && (localRoleBin.getGoal_weight() > 0.0F))
      {
        int i = getBodyScore(localBodyIndex, localRoleBin);
        int j = this.mainBodyScoreModel.getCycleColor();
        this.bodyScoreTv.setText(i);
        this.bodyScoreTv.setTextColor(j);
        this.bodyScorePP.initProgress(i);
        this.defenRrongImage.setVisibility(8);
        this.bodyScoreliner.setAlpha(1.0F);
        this.bodyScorePP.setPaintColor(j);
        return;
      }
      this.defenRrongImage.setVisibility(0);
      this.bodyScoreliner.setAlpha(0.0F);
      this.bodyScorePP.initProgress(0.0F);
      return;
    }
    this.defenRrongImage.setVisibility(8);
    this.bodyScoreliner.setAlpha(0.0F);
    this.bodyScorePP.initProgress(0.0F);
  }

  public void refreshEverydayMe(int paramInt1, int paramInt2)
  {
    this.arcMenu = ((EverydayMe)this.frameLayout.findViewById(2131099914));
    if (Age.isOld(this.app))
    {
      this.arcMenu.setVisibility(8);
      if (this.bodyScoreLayout != null)
      {
        this.bodyScoreLayout.setVisibility(0);
        refreshBodyScore();
      }
    }
    do
    {
      return;
      if (this.bodyScoreLayout != null)
        this.bodyScoreLayout.setVisibility(8);
    }
    while (this.arcMenu == null);
    this.arcMenu.setVisibility(0);
  }

  public void refreshUI()
  {
    if (this.app == null)
      this.app = ((MyApplication)getActivity().getApplication());
    this.mainBodyScoreModel = new MainBodyScoreModel(this.app.getCurrentRole(), this.app.getTodayBody());
    if (Age.isOld(this.app))
    {
      this.arcMenu.mChooseLaout();
      isToGoal(this.app.getTodayBody(), false);
    }
    while (true)
    {
      refreshGoalWeight();
      refreshGoalFat();
      refreshWeightCircle();
      refreshFatCircle();
      refreshBMICircle();
      refreshWeightData();
      refreshEverydayMe();
      refreshBodyScore();
      refreshSharedButton();
      if ((this.app.getCurrentRole().getGoal_weight() <= 0.0F) && (this.app.getTodayBody().getWeight() > 0.0F))
        this.mAnim.getPopupWindowMakeGoal();
      return;
      isToGoal(this.app.getTodayBody(), true);
    }
  }

  public void refreshUIWhenSettingGoalWeight()
  {
    refreshGoalWeight();
    refreshGoalFat();
    Log.i("picooc", "refresh after set goal weight");
    if ((this.app.getTodayBody().getWeight() > 0.0F) && (this.app.getTodayBody().getBodyFat() > 0.0F))
      if (Age.isOld(this.app))
        guideSetWeightOnly();
    do
    {
      return;
      guideSetWeightAndFat();
      return;
      if (this.app.getTodayBody().getWeight() > 0.0F)
      {
        guideSetWeightOnly();
        return;
      }
    }
    while ((this.app.getTodayBody().getBodyFat() <= 0.0F) || (Age.isOld(this.app)));
    guideSetFatOnly();
  }

  public void refreshUIWhenUpdataRoleMessage()
  {
    this.mainBodyScoreModel = new MainBodyScoreModel(this.app.getCurrentRole(), this.app.getTodayBody());
    if (Age.isOld(this.app))
    {
      this.arcMenu.mChooseLaout();
      isToGoal(this.app.getTodayBody(), false);
    }
    while (true)
    {
      refreshGoalWeight();
      refreshGoalFat();
      refreshWeightCircle();
      refreshFatCircle();
      refreshBMICircle();
      refreshSharedButton();
      refreshBodyScore();
      if (!Age.isOld(this.app))
        break;
      this.arcMenu.mChooseLaout();
      isToGoal(this.app.getTodayBody(), false);
      return;
      isToGoal(this.app.getTodayBody(), true);
    }
    isToGoal(this.app.getTodayBody(), true);
  }

  public void refreshWhyImage()
  {
    if (((Integer)SharedPreferenceUtils.getValue(getActivity(), "user-Info", "has_device", Integer.class)).intValue() <= 0)
      this.whyImage.setVisibility(4);
    while (this.themeConstant.getbgResource().intValue() == 2130837526)
      if (((Integer)SharedPreferenceUtils.getValue(getActivity(), "user-Info", "has_device", Integer.class)).intValue() > 1)
      {
        this.why___what.setImageResource(2130837534);
        return;
        this.whyImage.setVisibility(0);
      }
      else
      {
        this.why___what.setImageResource(2130837531);
        return;
      }
    if (this.themeConstant.getbgResource().intValue() == 2130837527)
    {
      if (((Integer)SharedPreferenceUtils.getValue(getActivity(), "user-Info", "has_device", Integer.class)).intValue() > 1)
      {
        this.why___what.setImageResource(2130837535);
        return;
      }
      this.why___what.setImageResource(2130837532);
      return;
    }
    if (((Integer)SharedPreferenceUtils.getValue(getActivity(), "user-Info", "has_device", Integer.class)).intValue() > 1)
    {
      this.why___what.setImageResource(2130837533);
      return;
    }
    this.why___what.setImageResource(2130837530);
  }

  public void setConnectBluetoothMessage(int paramInt, String paramString)
  {
    switch (paramInt)
    {
    case 404:
    default:
    case 402:
    case 403:
    case 405:
    case 401:
    case 406:
    }
    do
    {
      do
      {
        do
        {
          int i;
          do
          {
            do
              return;
            while ((this.shakeImage == null) || (this.weightTime == null));
            this.shakeImage.setVisibility(8);
            this.weightTime.setText(paramString);
            i = getResources().getColor(2131165201);
          }
          while (this.weight_layout == null);
          this.weight_layout.setBackGroundLineColorAndRefesh(i);
          return;
        }
        while ((this.shakeImage == null) || (this.weightTime == null));
        this.shakeImage.setVisibility(8);
        this.weightTime.setText(paramString);
        return;
        start();
      }
      while ((this.shakeImage == null) || (this.weightTime == null));
      this.shakeImage.setVisibility(8);
      this.weightTime.setText(paramString);
      return;
      updateShakeImage(paramString);
      return;
      Log.i("picooc", "收到了全局定时器的消息了");
    }
    while ((this.shakeImage == null) || (this.shakeImage.isShown()));
    this.shakeImage.setVisibility(0);
    this.weightTime.setText(paramString);
  }

  public void showLiftText(String paramString)
  {
    int i = ModUtils.getX(this.weight_layout);
    if (this.loogcount > 0)
    {
      this.loogcount = (-1 + this.loogcount);
      RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(i - 10, -2);
      localLayoutParams.setMargins(20, this.liftMaginTop, 0, 0);
      this.liftLinearlaout.setLayoutParams(localLayoutParams);
      this.liftLinearlaout.setVisibility(0);
      this.liftTextview.setTextColor(-65536);
      this.liftTextview.setTextSize(11.0F);
      this.liftTextview.setLineSpacing(3.4F, 1.0F);
      this.liftTextview.setPadding(15, 5, 25, 2);
      SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder("您  体重又增加了......离您的目标越来越远啦! 现在需要减重" + paramString + "kg才能达标  ~");
      localSpannableStringBuilder.setSpan(new ImageSpan(getActivity(), 2130837997), 0, 1, 34);
      this.liftTextview.setText(localSpannableStringBuilder);
    }
    if (this.LiftIsShow.booleanValue())
    {
      AnimUtils.LiftandRightMove(this.liftTextview, i - 10, 0, 1200);
      this.LiftIsShow = Boolean.valueOf(false);
      return;
    }
    this.LiftIsShow = Boolean.valueOf(true);
    AnimUtils.LiftandRightMove(this.liftTextview, 0, i - 10, 1000);
  }

  public void start()
  {
    if (this.weight_layout != null)
    {
      this.weight_scrollText.startScroll();
      this.mAnim.changeAlphaAnima(this.weight_scrollText, 1500L, 1.0F, 0.3F);
      if (this.pangImage.getAlpha() == 1.0F)
        this.mAnim.missAnima(this.pangImage, 1600L);
      this.mAnim.missAnima(this.weightChange, 1600L);
      if (this.weight_layout.getSweep() > 0.0F)
        this.weight_layout.startProgressReduceAlpha();
      if (this.weightDabiaoText.getAlpha() == 1.0F)
        this.mAnim.missAnima(this.weightDabiaoText, 1600L);
    }
    if (this.bmi_layout != null)
    {
      if (this.bmi_scrollText.getAlpha() == 1.0F)
        this.mAnim.changeAlphaAnima(this.bmi_scrollText, 1500L, 1.0F, 0.3F);
      this.mAnim.missAnima(this.bmiState, 1600L);
      Log.i("qianmo2", "bmi_layout.getSweep()=" + this.bmi_layout.getSweep());
      if (this.bmi_layout.getSweep() > 0.0F)
        this.bmi_layout.startProgressReduceAlpha();
      if (this.bmi_RrongImage.getAlpha() == 1.0F)
        this.mAnim.missAnima(this.bmi_RrongImage, 1600L);
    }
    if ((this.bmi_defenliner != null) && (this.bmi_defenliner.getAlpha() == 1.0F))
      this.mAnim.changeAlphaAnima(this.bmi_defenliner, 1500L, 1.0F, 0.3F);
    if (this.body_fat_layout != null)
    {
      if (this.body_fat_scrollText.getAlpha() == 1.0F)
        this.mAnim.changeAlphaAnima(this.body_fat_scrollText, 1500L, 1.0F, 0.3F);
      if (this.bodyFatWrongTip.getAlpha() == 1.0F)
        this.mAnim.missAnima(this.bodyFatWrongTip, 1600L);
      this.mAnim.missAnima(this.fatChange, 1600L);
      if (this.body_fat_layout.getSweep() > 0.0F)
        this.body_fat_layout.startProgressReduceAlpha();
      if (this.fatDabiaoText.getAlpha() == 1.0F)
        this.mAnim.missAnima(this.fatDabiaoText, 1600L);
    }
    if ((this.bodyScorePP != null) && (Age.isOld(this.app)) && (this.bodyScorePP.getSweep() > 0.0F))
      this.bodyScorePP.startProgressReduceAlpha();
    if ((this.bodyScoreTv != null) && (this.bodyScoreTv.getAlpha() == 1.0F))
      this.mAnim.missAnima(this.bodyScoreliner, 1600L);
    if ((this.defenRrongImage != null) && (this.defenRrongImage.getAlpha() == 1.0F))
      this.mAnim.missAnima(this.defenRrongImage, 1600L);
  }

  public void startShake()
  {
    AnimationDrawable localAnimationDrawable = (AnimationDrawable)getResources().getDrawable(2130968592);
    this.shakeImage.setImageDrawable(localAnimationDrawable);
    localAnimationDrawable.start();
  }

  public void updateShakeImage(String paramString)
  {
    this.weight_scrollText.stopNumberBounce();
    this.shakeImage.setVisibility(0);
    this.weightTime.setText(paramString);
  }

  public void updateUIWhenMeasureSuccess()
  {
    this.mainBodyScoreModel = new MainBodyScoreModel(this.app.getCurrentRole(), this.app.getTodayBody());
    if ((this.app == null) || (this.app.getCurrentRole() == null))
      return;
    if ((this.app.getCurrentRole().getGoal_weight() > 0.0F) && (this.app.getCurrentRole().getGoal_fat() <= 0.0F) && (this.app.getTodayBody().getWeight() > 0.0F) && (this.app.getTodayBody().getBodyFat() > 0.0F))
    {
      BodyFatReport localBodyFatReport = ReportDirect.calculateIdealBodyFat(this.app.getCurrentRole(), this.app.getTodayBody(), true);
      this.app.getCurrentRole().setGoal_fat(localBodyFatReport.getGoalFatRace());
      if (this.app.getCurrentRole().getFirst_fat() <= 0.0F)
        this.app.getCurrentRole().setFirst_fat(this.app.getTodayBody().getBodyFat());
      OperationDB.updateRoleDB(getActivity(), this.app.getCurrentRole());
      AsyncMessageUtils.updateRoleMessage(getActivity(), this.app.getCurrentRole(), null);
      OperationDB.insertToRoleInfos(getActivity(), this.app.getCurrentRole());
      refreshGoalFat();
      Log.i("picooc", "first weight and fat");
      new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
      {
        public void run()
        {
          if (Age.isOld(LatinMainFragment.this.app))
          {
            LatinMainFragment.this.guideSetWeightOnly();
            return;
          }
          LatinMainFragment.this.guideSetWeightAndFat();
        }
      }
      , 1000L);
    }
    if ((this.app.getCurrentRole().getGoal_weight() > 0.0F) && (this.app.getTodayBody().getWeight() > 0.0F))
    {
      if (this.app.isFirstWeightChange())
      {
        Log.i("picooc", "picooc.com--------------------------");
        this.weight_layout.setBackGroundLineColorAndRefesh(Color.rgb(255, 110, 110));
        showLiftText(NumUtils.roundValue(Math.abs(this.app.getTodayBody().getWeight() - this.app.getCurrentRole().getGoal_weight())));
        this.app.setFirstWeightChange(false);
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            LatinMainFragment.this.showLiftText(NumUtils.roundValue(Math.abs(LatinMainFragment.this.app.getTodayBody().getWeight() - LatinMainFragment.this.app.getCurrentRole().getGoal_weight())));
          }
        }
        , 3600L);
      }
      Log.i("picooc", "first weight no fat");
      new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
      {
        public void run()
        {
          LatinMainFragment.this.guideSetWeightOnly();
        }
      }
      , 1000L);
    }
    stop(this.app.getTodayBody());
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LatinMainFragment
 * JD-Core Version:    0.6.2
 */
