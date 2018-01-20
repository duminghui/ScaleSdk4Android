package com.picooc.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.MyApplication;
import com.picooc.model.LatinMonthData;
import com.picooc.model.LatinSeasonData;
import com.picooc.model.LatinWeekData;
import com.picooc.utils.DateUtils;
import com.picooc.utils.NumUtils;
import com.picooc.utils.TextUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.chart.GoHistoryPopView;
import com.picooc.widget.chart.GoHistoryPopView.GoHistoryListener;
import com.picooc.widget.chart.PicoocChart;
import com.picooc.widget.chart.PopView;
import com.picooc.widget.loading.PicoocToast;
import org.achartengine.GraphicalView;
import org.achartengine.GraphicalView.onDrawChartEndListener;
import org.achartengine.model.SeriesSelection;
import org.achartengine.tools.ModUtils;

public class LatinChartFragment extends Fragment
{
  private Animation anim;
  private MyApplication app;
  private View.OnClickListener chartClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      GraphicalView localGraphicalView = (GraphicalView)paramAnonymousView;
      final SeriesSelection localSeriesSelection = localGraphicalView.getCurrentSeriesAndPoint();
      LatinChartFragment.this.historyPop.setVisibility(4);
      if (localSeriesSelection == null)
      {
        LatinChartFragment.this.pop.setVisibility(4);
        localGraphicalView.drawSelectedPoint(-100.0F, 0.0F, 0);
      }
      while (localSeriesSelection.getSeriesIndex() < 4)
        return;
      localGraphicalView.drawSelectedPoint((float)localSeriesSelection.getCenterX(), (float)localSeriesSelection.getCenterY(), localSeriesSelection.getSeriesIndex());
      final RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)LatinChartFragment.this.pop.getLayoutParams();
      final float f = LatinChartFragment.this.getResources().getDimension(2131230752) + LatinChartFragment.this.getResources().getDimension(2131230745);
      LatinChartFragment.this.x = ((int)(localSeriesSelection.getCenterX() + f));
      final int i = (int)(LatinChartFragment.this.x / (LatinChartFragment.this.screenWidth / 7.0F));
      LatinWeekData localLatinWeekData = LatinChartFragment.this.app.getWeekDataByFlag(LatinChartFragment.this.selectWeekNo);
      int j;
      boolean bool1;
      switch (i)
      {
      default:
        j = 2;
        if (LatinChartFragment.this.app.getCurrentRole().getWeight_change_target() <= 0.0F)
          if (localSeriesSelection.getSeriesIndex() == 4)
          {
            int i6 = localSeriesSelection.getPointIndex();
            int i7 = localLatinWeekData.getWeightStandardAnchor();
            bool1 = false;
            if (i6 == i7)
            {
              boolean bool5 = localLatinWeekData.getWeightByIndex(i) < LatinChartFragment.this.app.getCurrentRole().getGoal_weight();
              bool1 = false;
              if (!bool5)
                bool1 = true;
            }
          }
        break;
      case 0:
      case 6:
      }
      while (true)
      {
        LatinChartFragment.this.pop.changeData(localLatinWeekData.getTimeByIndex(i), localLatinWeekData.getWeightByIndex(i), localLatinWeekData.getBodyFatByIndex(i), localLatinWeekData.getBmiByIndex(i), localLatinWeekData.getEveryMeByIndex(i), Boolean.valueOf(bool1), j);
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            LatinChartFragment.this.pop.setVisibility(0);
            if (i == 0)
            {
              LatinChartFragment localLatinChartFragment4 = LatinChartFragment.this;
              localLatinChartFragment4.x = (15 + localLatinChartFragment4.x);
              LatinChartFragment.this.y = ((int)(localSeriesSelection.getCenterY() + LatinChartFragment.this.linearLayout.getTop() - LatinChartFragment.this.pop.getHeight()));
              LatinChartFragment localLatinChartFragment5 = LatinChartFragment.this;
              localLatinChartFragment5.y = (-15 + localLatinChartFragment5.y);
            }
            while (true)
            {
              LatinChartFragment.this.pop.setLayoutParams(localLayoutParams);
              localLayoutParams.setMargins(LatinChartFragment.this.x, LatinChartFragment.this.y, 0, 0);
              LatinChartFragment.this.pop.startAnimation(LatinChartFragment.this.anim);
              return;
              if (i == 6)
              {
                LatinChartFragment.this.x = ((int)(localSeriesSelection.getCenterX() + f - LatinChartFragment.this.pop.getWidth()));
                LatinChartFragment localLatinChartFragment2 = LatinChartFragment.this;
                localLatinChartFragment2.x = (-15 + localLatinChartFragment2.x);
                LatinChartFragment.this.y = ((int)(localSeriesSelection.getCenterY() + LatinChartFragment.this.linearLayout.getTop() - LatinChartFragment.this.pop.getHeight()));
                LatinChartFragment localLatinChartFragment3 = LatinChartFragment.this;
                localLatinChartFragment3.y = (-15 + localLatinChartFragment3.y);
              }
              else
              {
                LatinChartFragment.this.x = ((int)((float)localSeriesSelection.getCenterX() + f - LatinChartFragment.this.pop.getWidth() / 2));
                LatinChartFragment.this.y = ((int)(localSeriesSelection.getCenterY() + LatinChartFragment.this.linearLayout.getTop() - LatinChartFragment.this.pop.getHeight() - LatinChartFragment.this.getResources().getDimension(2131230747)));
                LatinChartFragment localLatinChartFragment1 = LatinChartFragment.this;
                localLatinChartFragment1.y = (-15 + localLatinChartFragment1.y);
              }
            }
          }
        }
        , 100L);
        return;
        j = 1;
        break;
        j = 3;
        break;
        int i3 = localSeriesSelection.getSeriesIndex();
        bool1 = false;
        if (i3 == 5)
        {
          int i4 = localSeriesSelection.getPointIndex();
          int i5 = localLatinWeekData.getWeightStandardAnchor();
          bool1 = false;
          if (i4 == i5)
          {
            boolean bool4 = localLatinWeekData.getBodyFatByIndex(i) < LatinChartFragment.this.app.getCurrentRole().getGoal_fat();
            bool1 = false;
            if (!bool4)
            {
              bool1 = true;
              continue;
              if (localSeriesSelection.getSeriesIndex() == 4)
              {
                int i1 = localSeriesSelection.getPointIndex();
                int i2 = localLatinWeekData.getWeightStandardAnchor();
                bool1 = false;
                if (i1 == i2)
                {
                  boolean bool3 = localLatinWeekData.getWeightByIndex(i) < LatinChartFragment.this.app.getCurrentRole().getGoal_weight();
                  bool1 = false;
                  if (!bool3)
                    bool1 = true;
                }
              }
              else
              {
                int k = localSeriesSelection.getSeriesIndex();
                bool1 = false;
                if (k == 5)
                {
                  int m = localSeriesSelection.getPointIndex();
                  int n = localLatinWeekData.getFatStandardAnchor();
                  bool1 = false;
                  if (m == n)
                  {
                    boolean bool2 = localLatinWeekData.getBodyFatByIndex(i) < LatinChartFragment.this.app.getCurrentRole().getGoal_fat();
                    bool1 = false;
                    if (!bool2)
                      bool1 = true;
                  }
                }
              }
            }
          }
        }
      }
    }
  };
  private int chartMode = 0;
  private GraphicalView chartView;
  private TextView chart_fat;
  private TextView chart_weight;
  private TextView chengzhongtianshu;
  private TextView endDay;
  private ImageView fatStandardFlag;
  private boolean firstSrawChart = true;
  private ImageView goalFatAnchor;
  private ImageView goalFatFlag;
  private TextView goalFatText;
  private ImageView goalWeightAnchor;
  private ImageView goalWeightFlag;
  private TextView goalWeightText;
  private ImageView historyClock;
  private GoHistoryPopView historyPop;
  private View layout;
  private RelativeLayout linearLayout;
  private TextView m1;
  private TextView m2;
  private TextView m3;
  private AnimUtils mAnim;
  private Button nextButton;
  private PicoocChart pc;
  private TextView picoocNum;
  private PopView pop;
  private Button prevButton;
  private RadioGroup radioGroup;
  private float screenWidth = 720.0F;
  private LinearLayout seasonXLable;
  private int selectMonthNo = 0;
  private int selectSeasonNo = 0;
  private int selectWeekNo = 0;
  private TextView startDay;
  View.OnClickListener tishiClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      case 2131099874:
      default:
      case 2131099873:
      case 2131099875:
      }
      do
      {
        do
          return;
        while (LatinChartFragment.this.chart_fat.getAlpha() != 0.0F);
        LatinChartFragment.this.tiShiKuang(LatinChartFragment.this.chart_weight);
        LatinChartFragment.this.mAnim.showAnima(LatinChartFragment.this.chart_weight, 2000L);
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            LatinChartFragment.this.mAnim.missAnima(LatinChartFragment.this.chart_weight, 2000L);
          }
        }
        , 3600L);
        return;
      }
      while (LatinChartFragment.this.chart_weight.getAlpha() != 0.0F);
      LatinChartFragment.this.tiShiKuang(LatinChartFragment.this.chart_fat);
      LatinChartFragment.this.mAnim.showAnima(LatinChartFragment.this.chart_fat, 2000L);
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          LatinChartFragment.this.mAnim.missAnima(LatinChartFragment.this.chart_fat, 2000L);
        }
      }
      , 3600L);
    }
  };
  private TextView tizhi;
  private TextView tizhiText;
  private TextView tizhong;
  private TextView tizhongText;
  private RadioButton weekButton;
  private ImageView weightStandardFlag;
  int x = 0;
  int y = 0;

  private void changeNextButtonAndNextButtonState(int paramInt1, int paramInt2)
  {
    if (paramInt1 <= 0)
    {
      this.nextButton.setClickable(false);
      this.nextButton.setEnabled(false);
      if (paramInt1 < paramInt2)
        break label65;
      this.prevButton.setClickable(false);
      this.prevButton.setEnabled(false);
    }
    while (true)
    {
      refreshThresCycle();
      return;
      this.nextButton.setClickable(true);
      this.nextButton.setEnabled(true);
      break;
      label65: this.prevButton.setClickable(true);
      this.prevButton.setEnabled(true);
    }
  }

  private void changeStartTimeAndEndTime(String[] paramArrayOfString, int paramInt1, int paramInt2)
  {
    if (paramArrayOfString == null)
      return;
    this.startDay.setText(paramArrayOfString[0] + " - ");
    this.endDay.setText(paramArrayOfString[1]);
    drawChart(paramInt1, paramInt2);
  }

  private void drawChart(int paramInt1, int paramInt2)
  {
    this.linearLayout.removeAllViews();
    switch (paramInt1)
    {
    default:
      if (this.firstSrawChart)
      {
        this.chartView.setFlag_int(1);
        this.firstSrawChart = false;
      }
      break;
    case 2131099861:
    case 2131099862:
    case 2131099863:
    }
    while (true)
    {
      this.chartView.setZOrderOnTop(true);
      this.chartView.getHolder().setFormat(-3);
      this.linearLayout.addView(this.chartView);
      this.pop.setVisibility(4);
      this.historyPop.setVisibility(4);
      this.historyClock.setVisibility(4);
      this.chartView.setOnDrawChartEndListener(new GraphicalView.onDrawChartEndListener()
      {
        public void onDrawChartEnd()
        {
          LatinChartFragment.this.refreshAnchorAndFlag();
        }
      });
      return;
      LatinWeekData localLatinWeekData = this.app.getWeekDataByFlag(paramInt2);
      double[] arrayOfDouble3 = { 1.0D, 2.0D, 3.0D, 4.0D, 5.0D, 6.0D, 7.0D };
      this.chartView = this.pc.execute(getActivity(), arrayOfDouble3, localLatinWeekData.getWeightArray(), localLatinWeekData.getBodyFatArray(), localLatinWeekData.getWeightDashArray(), localLatinWeekData.getBodyFaDashArray(), localLatinWeekData.getGoalWeightArray(), localLatinWeekData.getGoalFatArray(), localLatinWeekData.getWeightAxisMax(), localLatinWeekData.getWeightAxisMin(), localLatinWeekData.getFatAxisMax(), localLatinWeekData.getFatAxisMin(), 1);
      this.chartView.setOnClickListener(this.chartClick);
      break;
      LatinMonthData localLatinMonthData = this.app.getMonthDataByFlag(this.selectMonthNo);
      double[] arrayOfDouble2 = new double[localLatinMonthData.getDays()];
      for (int j = 0; ; j++)
      {
        if (j >= localLatinMonthData.getDays())
        {
          this.chartView = this.pc.execute(getActivity(), arrayOfDouble2, localLatinMonthData.getWeightArray(), localLatinMonthData.getBodyFatArray(), localLatinMonthData.getWeightDashArray(), localLatinMonthData.getBodyFaDashArray(), localLatinMonthData.getGoalWeightArray(), localLatinMonthData.getGoalFatArray(), localLatinMonthData.getWeightAxisMax(), localLatinMonthData.getWeightAxisMin(), localLatinMonthData.getFatAxisMax(), localLatinMonthData.getFatAxisMin(), 2);
          break;
        }
        arrayOfDouble2[j] = (j + 1);
      }
      LatinSeasonData localLatinSeasonData = this.app.getSeasonDataByFlag(this.selectSeasonNo);
      double[] arrayOfDouble1 = new double[localLatinSeasonData.getDays()];
      for (int i = 0; ; i++)
      {
        if (i >= localLatinSeasonData.getDays())
        {
          this.chartView = this.pc.execute(getActivity(), arrayOfDouble1, localLatinSeasonData.getWeightArray(), localLatinSeasonData.getBodyFatArray(), localLatinSeasonData.getWeightDashArray(), localLatinSeasonData.getBodyFaDashArray(), localLatinSeasonData.getGoalWeightArray(), localLatinSeasonData.getGoalFatArray(), localLatinSeasonData.getWeightAxisMax(), localLatinSeasonData.getWeightAxisMin(), localLatinSeasonData.getFatAxisMax(), localLatinSeasonData.getFatAxisMin(), 3);
          this.m1.setText(localLatinSeasonData.getXLable1());
          this.m2.setText(localLatinSeasonData.getXLable2());
          this.m3.setText(localLatinSeasonData.getXLable3());
          break;
        }
        arrayOfDouble1[i] = (i + 1);
      }
      this.chartView.setFlag_int(3);
    }
  }

  private void initAnchorAndFlag()
  {
    this.goalWeightAnchor = ((ImageView)this.layout.findViewById(2131099883));
    this.goalFatAnchor = ((ImageView)this.layout.findViewById(2131099884));
    this.weightStandardFlag = ((ImageView)this.layout.findViewById(2131099889));
    this.fatStandardFlag = ((ImageView)this.layout.findViewById(2131099890));
    this.goalWeightFlag = ((ImageView)this.layout.findViewById(2131099885));
    this.goalFatFlag = ((ImageView)this.layout.findViewById(2131099887));
    this.goalWeightText = ((TextView)this.layout.findViewById(2131099886));
    this.goalWeightText.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
    this.goalFatText = ((TextView)this.layout.findViewById(2131099888));
    this.goalFatText.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
  }

  private void initBottomView()
  {
    Typeface localTypeface = TextUtils.getTypeface(getActivity(), null);
    this.picoocNum = ((TextView)this.layout.findViewById(2131099872));
    this.tizhong = ((TextView)this.layout.findViewById(2131099873));
    this.tizhi = ((TextView)this.layout.findViewById(2131099875));
    this.chengzhongtianshu = ((TextView)this.layout.findViewById(2131099877));
    this.picoocNum.setTypeface(localTypeface);
    this.tizhong.setTypeface(localTypeface);
    this.tizhi.setTypeface(localTypeface);
    this.chengzhongtianshu.setTypeface(localTypeface);
    this.tizhongText = ((TextView)this.layout.findViewById(2131099874));
    this.tizhiText = ((TextView)this.layout.findViewById(2131099876));
    this.tizhong.setOnClickListener(this.tishiClick);
    this.tizhi.setOnClickListener(this.tishiClick);
  }

  private void initChart()
  {
    this.seasonXLable = ((LinearLayout)this.layout.findViewById(2131099879));
    this.pop = ((PopView)this.layout.findViewById(2131099892));
    this.historyPop = ((GoHistoryPopView)this.layout.findViewById(2131099893));
    this.historyClock = ((ImageView)this.layout.findViewById(2131099891));
    this.historyClock.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        LatinChartFragment.this.historyClock.setVisibility(4);
        LatinChartFragment.this.historyPop.setVisibility(0);
        LatinChartFragment.this.historyPop.startAnimation(LatinChartFragment.this.anim);
        LatinChartFragment.this.pop.setVisibility(4);
      }
    });
    this.historyPop.setGoHistoryListener(new GoHistoryListener()
    {
      public void onGoHistory(int paramAnonymousInt)
      {
        LatinChartFragment.this.historyPop.setVisibility(4);
        LatinChartFragment.this.selectWeekNo = paramAnonymousInt;
        LatinChartFragment.this.refreshChartByTypeAndFlag(LatinChartFragment.this.chartMode, paramAnonymousInt);
        LatinChartFragment.this.refreshThresCycle();
        LatinChartFragment.this.changeNextButtonAndNextButtonState(LatinChartFragment.this.selectWeekNo, LatinChartFragment.this.app.getFirstWeekFlag());
      }
    });
    this.pc = new PicoocChart();
  }

  private void initDate()
  {
    this.startDay = ((TextView)this.layout.findViewById(2131099867));
    this.endDay = ((TextView)this.layout.findViewById(2131099868));
    this.startDay.setTypeface(ModUtils.getTypeface(getActivity()));
    this.endDay.setTypeface(ModUtils.getTypeface(getActivity()));
    ((TextView)this.layout.findViewById(2131099870)).setTypeface(ModUtils.getTypeface(getActivity()));
    this.prevButton = ((Button)this.layout.findViewById(2131099866));
    this.nextButton = ((Button)this.layout.findViewById(2131099869));
    this.chart_weight = ((TextView)this.layout.findViewById(2131099894));
    this.chart_fat = ((TextView)this.layout.findViewById(2131099895));
    this.chart_weight.setAlpha(0.0F);
    this.chart_fat.setAlpha(0.0F);
    this.prevButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        int j;
        int k;
        if (LatinChartFragment.this.chartMode == 2131099861)
        {
          LatinChartFragment localLatinChartFragment5 = LatinChartFragment.this;
          int i3 = LatinChartFragment.this.chartMode;
          LatinChartFragment localLatinChartFragment6 = LatinChartFragment.this;
          int i4 = 1 + localLatinChartFragment6.selectWeekNo;
          localLatinChartFragment6.selectWeekNo = i4;
          localLatinChartFragment5.refreshChartByTypeAndFlag(i3, i4);
          j = LatinChartFragment.this.selectWeekNo;
          k = LatinChartFragment.this.app.getFirstWeekFlag();
        }
        while (true)
        {
          LatinChartFragment.this.changeNextButtonAndNextButtonState(j, k);
          return;
          if (LatinChartFragment.this.chartMode == 2131099862)
          {
            LatinChartFragment localLatinChartFragment3 = LatinChartFragment.this;
            int i1 = LatinChartFragment.this.chartMode;
            LatinChartFragment localLatinChartFragment4 = LatinChartFragment.this;
            int i2 = 1 + localLatinChartFragment4.selectMonthNo;
            localLatinChartFragment4.selectMonthNo = i2;
            localLatinChartFragment3.refreshChartByTypeAndFlag(i1, i2);
            j = LatinChartFragment.this.selectMonthNo;
            k = LatinChartFragment.this.app.getFirstMonthFlag();
          }
          else
          {
            int i = LatinChartFragment.this.chartMode;
            j = 0;
            k = 0;
            if (i == 2131099863)
            {
              LatinChartFragment localLatinChartFragment1 = LatinChartFragment.this;
              int m = LatinChartFragment.this.chartMode;
              LatinChartFragment localLatinChartFragment2 = LatinChartFragment.this;
              int n = 1 + localLatinChartFragment2.selectSeasonNo;
              localLatinChartFragment2.selectSeasonNo = n;
              localLatinChartFragment1.refreshChartByTypeAndFlag(m, n);
              j = LatinChartFragment.this.selectSeasonNo;
              k = LatinChartFragment.this.app.getFirstSeasonFlag();
            }
          }
        }
      }
    });
    this.nextButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        int j;
        int k;
        if (LatinChartFragment.this.chartMode == 2131099861)
        {
          LatinChartFragment localLatinChartFragment5 = LatinChartFragment.this;
          int i3 = LatinChartFragment.this.chartMode;
          LatinChartFragment localLatinChartFragment6 = LatinChartFragment.this;
          int i4 = -1 + localLatinChartFragment6.selectWeekNo;
          localLatinChartFragment6.selectWeekNo = i4;
          localLatinChartFragment5.refreshChartByTypeAndFlag(i3, i4);
          j = LatinChartFragment.this.selectWeekNo;
          k = LatinChartFragment.this.app.getFirstWeekFlag();
        }
        while (true)
        {
          LatinChartFragment.this.changeNextButtonAndNextButtonState(j, k);
          return;
          if (LatinChartFragment.this.chartMode == 2131099862)
          {
            LatinChartFragment localLatinChartFragment3 = LatinChartFragment.this;
            int i1 = LatinChartFragment.this.chartMode;
            LatinChartFragment localLatinChartFragment4 = LatinChartFragment.this;
            int i2 = -1 + localLatinChartFragment4.selectMonthNo;
            localLatinChartFragment4.selectMonthNo = i2;
            localLatinChartFragment3.refreshChartByTypeAndFlag(i1, i2);
            j = LatinChartFragment.this.selectMonthNo;
            k = LatinChartFragment.this.app.getFirstMonthFlag();
          }
          else
          {
            int i = LatinChartFragment.this.chartMode;
            j = 0;
            k = 0;
            if (i == 2131099863)
            {
              LatinChartFragment localLatinChartFragment1 = LatinChartFragment.this;
              int m = LatinChartFragment.this.chartMode;
              LatinChartFragment localLatinChartFragment2 = LatinChartFragment.this;
              int n = -1 + localLatinChartFragment2.selectSeasonNo;
              localLatinChartFragment2.selectSeasonNo = n;
              localLatinChartFragment1.refreshChartByTypeAndFlag(m, n);
              j = LatinChartFragment.this.selectSeasonNo;
              k = LatinChartFragment.this.app.getFirstSeasonFlag();
            }
          }
        }
      }
    });
  }

  private void initDateTab()
  {
    this.radioGroup = ((RadioGroup)this.layout.findViewById(2131099860));
    this.weekButton = ((RadioButton)this.radioGroup.findViewById(2131099861));
    this.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
    {
      public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default:
          PicoocToast.showToast(LatinChartFragment.this.getActivity(), "持续使用Latin，才能有更多数据哦！");
          if (LatinChartFragment.this.chartMode != 0)
            LatinChartFragment.this.radioGroup.check(LatinChartFragment.this.chartMode);
          return;
        case 2131099861:
          LatinChartFragment.this.chartMode = 2131099861;
          LatinChartFragment.this.refreshChartByTypeAndFlag(LatinChartFragment.this.chartMode, LatinChartFragment.this.selectWeekNo);
          int n = LatinChartFragment.this.selectWeekNo;
          int i1 = LatinChartFragment.this.app.getFirstWeekFlag();
          LatinChartFragment.this.changeNextButtonAndNextButtonState(n, i1);
          LatinChartFragment.this.seasonXLable.setVisibility(4);
          return;
        case 2131099862:
          LatinChartFragment.this.chartMode = 2131099862;
          LatinChartFragment.this.refreshChartByTypeAndFlag(LatinChartFragment.this.chartMode, LatinChartFragment.this.selectMonthNo);
          int k = LatinChartFragment.this.selectMonthNo;
          int m = LatinChartFragment.this.app.getFirstMonthFlag();
          LatinChartFragment.this.changeNextButtonAndNextButtonState(k, m);
          LatinChartFragment.this.seasonXLable.setVisibility(4);
          return;
        case 2131099863:
        }
        LatinChartFragment.this.chartMode = 2131099863;
        LatinChartFragment.this.refreshChartByTypeAndFlag(LatinChartFragment.this.chartMode, LatinChartFragment.this.selectSeasonNo);
        int i = LatinChartFragment.this.selectSeasonNo;
        int j = LatinChartFragment.this.app.getFirstSeasonFlag();
        LatinChartFragment.this.changeNextButtonAndNextButtonState(i, j);
        LatinChartFragment.this.seasonXLable.setVisibility(0);
      }
    });
    this.weekButton.setChecked(true);
  }

  private void initSeasonLables()
  {
    this.m1 = ((TextView)this.seasonXLable.findViewById(2131099880));
    this.m1.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
    this.m2 = ((TextView)this.seasonXLable.findViewById(2131099881));
    this.m2.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
    this.m3 = ((TextView)this.seasonXLable.findViewById(2131099882));
    this.m3.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        LinearLayout localLinearLayout = LatinChartFragment.this.seasonXLable;
        RelativeLayout localRelativeLayout = LatinChartFragment.this.linearLayout;
        RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)localLinearLayout.getLayoutParams();
        if (localLayoutParams != null)
          localLayoutParams.setMargins((int)LatinChartFragment.this.getActivity().getResources().getDimension(2131230749), localRelativeLayout.getTop() + localRelativeLayout.getHeight() - (int)(1.2D * localLinearLayout.getHeight()), (int)LatinChartFragment.this.getActivity().getResources().getDimension(2131230750), 0);
      }
    }
    , 1500L);
  }

  private void refreshAnchorAndFlag()
  {
    if ((this.app != null) && (this.app.getCurrentRole() != null) && (this.app.getCurrentRole().getGoal_weight() > 0.0F))
      if (this.chartView != null);
    label416: label423: label560: label852: label1500: label1511: label1522: 
    do
    {
      return;
      SeriesSelection localSeriesSelection1 = this.chartView.getPointBySeriesIndexAndPointIndex(2, 0);
      float f1;
      double[] arrayOfDouble1;
      float f2;
      float f3;
      int i;
      int j;
      int i13;
      double[] arrayOfDouble2;
      float f4;
      float f5;
      int k;
      int m;
      if (localSeriesSelection1 != null)
      {
        RelativeLayout.LayoutParams localLayoutParams8 = (RelativeLayout.LayoutParams)this.goalWeightAnchor.getLayoutParams();
        int i16 = (int)localSeriesSelection1.getCenterX() - this.goalWeightAnchor.getWidth() / 5;
        int i17 = (int)localSeriesSelection1.getCenterY() + this.linearLayout.getTop() - this.goalWeightAnchor.getHeight() / 2;
        this.goalWeightAnchor.setLayoutParams(localLayoutParams8);
        localLayoutParams8.setMargins(i16, i17, 0, 0);
        this.goalWeightAnchor.setVisibility(0);
        this.goalWeightAnchor.invalidate();
        if (this.chartMode == 2131099861)
        {
          RelativeLayout.LayoutParams localLayoutParams9 = (RelativeLayout.LayoutParams)this.goalWeightFlag.getLayoutParams();
          int i18 = (int)(localSeriesSelection1.getCenterX() / 4.0D);
          int i19 = (int)localSeriesSelection1.getCenterY() + this.linearLayout.getTop() - this.goalFatFlag.getHeight();
          this.goalWeightFlag.setLayoutParams(localLayoutParams9);
          localLayoutParams9.setMargins(i18, i19, 0, 0);
          this.goalWeightFlag.setVisibility(0);
          this.goalWeightFlag.invalidate();
          RelativeLayout.LayoutParams localLayoutParams10 = (RelativeLayout.LayoutParams)this.goalWeightText.getLayoutParams();
          int i20 = 5 + ((int)localSeriesSelection1.getCenterY() + this.linearLayout.getTop());
          this.goalWeightText.setLayoutParams(localLayoutParams10);
          localLayoutParams10.setMargins(1, i20, 0, 0);
          this.goalWeightText.setText(NumUtils.roundValue(this.app.getCurrentRole().getGoal_weight()) + "kg");
          this.goalWeightText.setVisibility(0);
          this.goalWeightText.invalidate();
          f1 = getResources().getDimension(2131230752) + getResources().getDimension(2131230745);
          arrayOfDouble1 = this.app.getWeekDataByFlag(this.selectWeekNo).getWeightArray();
          f2 = this.app.getCurrentRole().getGoal_weight();
          f3 = this.app.getCurrentRole().getWeight_change_target();
          i = -1;
          j = -1;
          if (this.chartMode != 2131099861)
            break label1511;
          i13 = 0;
          if (i13 < arrayOfDouble1.length)
            break label1421;
          if (j < 0)
            break label1500;
          SeriesSelection localSeriesSelection5 = this.chartView.getPointBySeriesIndexAndPointIndex(4, j);
          if (localSeriesSelection5 == null)
            break label1489;
          RelativeLayout.LayoutParams localLayoutParams7 = (RelativeLayout.LayoutParams)this.weightStandardFlag.getLayoutParams();
          int i14 = (int)(localSeriesSelection5.getCenterX() - this.weightStandardFlag.getWidth() / 2 + f1);
          int i15 = (int)(localSeriesSelection5.getCenterY() + this.linearLayout.getTop() - 1.1D * this.weightStandardFlag.getHeight());
          this.weightStandardFlag.setLayoutParams(localLayoutParams7);
          localLayoutParams7.setMargins(i14, i15, 0, 0);
          this.weightStandardFlag.setVisibility(0);
          this.weightStandardFlag.invalidate();
          this.app.getWeekDataByFlag(this.selectWeekNo).setWeightStandardAnchor(j);
          if (this.chartMode != 2131099861)
            break label1541;
          if ((arrayOfDouble1[0] != -1.0D) || (this.app.getWeekDataByFlag(this.selectWeekNo).getWeightDashArray()[0] <= 0.0D) || (this.app.getWeekDataByFlag(this.selectWeekNo).getFirstDashPointTime() <= 0L))
            break label1522;
          SeriesSelection localSeriesSelection4 = this.chartView.getPointBySeriesIndexAndPointIndex(0, 0);
          if (localSeriesSelection4 != null)
          {
            RelativeLayout.LayoutParams localLayoutParams5 = (RelativeLayout.LayoutParams)this.historyClock.getLayoutParams();
            int i9 = (int)(localSeriesSelection4.getCenterX() + this.historyClock.getWidth() / 8 + f1);
            int i10 = (int)(localSeriesSelection4.getCenterY() + this.linearLayout.getTop() - 1.125D * this.historyClock.getHeight());
            this.historyClock.setLayoutParams(localLayoutParams5);
            localLayoutParams5.setMargins(i9, i10, 0, 0);
            this.historyClock.setVisibility(0);
            this.historyClock.invalidate();
            this.historyPop.refreshPop(this.app.getWeekDataByFlag(this.selectWeekNo).getWeightDashArray()[0], this.app.getWeekDataByFlag(this.selectWeekNo).getFirstDashPointTime());
            RelativeLayout.LayoutParams localLayoutParams6 = (RelativeLayout.LayoutParams)this.historyPop.getLayoutParams();
            int i11 = (int)(10.0D + localSeriesSelection4.getCenterX());
            int i12 = (int)(localSeriesSelection4.getCenterY() + this.linearLayout.getTop() - this.historyPop.getHeight() / 2);
            this.historyPop.setLayoutParams(localLayoutParams6);
            localLayoutParams6.setMargins(i11, i12, 0, 0);
            this.historyPop.invalidate();
          }
          SeriesSelection localSeriesSelection2 = this.chartView.getPointBySeriesIndexAndPointIndex(3, 1);
          if (localSeriesSelection2 == null)
            break label1579;
          RelativeLayout.LayoutParams localLayoutParams2 = (RelativeLayout.LayoutParams)this.goalFatAnchor.getLayoutParams();
          int i3 = (int)localSeriesSelection2.getCenterX() - 2 * this.goalFatAnchor.getWidth() / 3;
          int i4 = (int)localSeriesSelection2.getCenterY() + this.linearLayout.getTop() - this.goalFatAnchor.getHeight() / 2;
          this.goalFatAnchor.setLayoutParams(localLayoutParams2);
          localLayoutParams2.setMargins(i3, i4, 0, 0);
          this.goalFatAnchor.setVisibility(0);
          this.goalFatAnchor.invalidate();
          if (this.chartMode != 2131099861)
            break label1560;
          RelativeLayout.LayoutParams localLayoutParams3 = (RelativeLayout.LayoutParams)this.goalFatFlag.getLayoutParams();
          int i5 = (int)(19.0D + localSeriesSelection2.getCenterX());
          int i6 = (int)localSeriesSelection2.getCenterY() + this.linearLayout.getTop() - this.goalFatFlag.getHeight();
          this.goalFatFlag.setLayoutParams(localLayoutParams3);
          localLayoutParams3.setMargins(i5, i6, 0, 0);
          this.goalFatFlag.setVisibility(0);
          this.goalFatFlag.invalidate();
          RelativeLayout.LayoutParams localLayoutParams4 = (RelativeLayout.LayoutParams)this.goalFatText.getLayoutParams();
          int i7 = (int)(13.0D + localSeriesSelection2.getCenterX());
          int i8 = 5 + ((int)localSeriesSelection2.getCenterY() + this.linearLayout.getTop());
          this.goalFatText.setLayoutParams(localLayoutParams4);
          localLayoutParams4.setMargins(i7, i8, 0, 0);
          this.goalFatText.setText(NumUtils.roundValue(this.app.getCurrentRole().getGoal_fat()) + "%");
          this.goalFatText.setVisibility(0);
          this.goalFatText.invalidate();
          if (this.chartMode != 2131099861)
            break label1695;
          arrayOfDouble2 = this.app.getWeekDataByFlag(this.selectWeekNo).getBodyFatArray();
          f4 = this.app.getCurrentRole().getGoal_fat();
          f5 = this.app.getCurrentRole().getWeight_change_target();
          k = -1;
          m = -1;
        }
      }
      for (int n = 0; ; n++)
      {
        if (n >= arrayOfDouble2.length);
        while (true)
        {
          if (m < 0)
            break label1686;
          SeriesSelection localSeriesSelection3 = this.chartView.getPointBySeriesIndexAndPointIndex(5, m);
          if (localSeriesSelection3 == null)
            break label1677;
          RelativeLayout.LayoutParams localLayoutParams1 = (RelativeLayout.LayoutParams)this.fatStandardFlag.getLayoutParams();
          int i1 = (int)(localSeriesSelection3.getCenterX() - this.fatStandardFlag.getWidth() / 2 + f1);
          int i2 = (int)(localSeriesSelection3.getCenterY() + this.linearLayout.getTop() - 1.1D * this.fatStandardFlag.getHeight());
          this.fatStandardFlag.setLayoutParams(localLayoutParams1);
          localLayoutParams1.setMargins(i1, i2, 0, 0);
          this.fatStandardFlag.setVisibility(0);
          this.fatStandardFlag.invalidate();
          this.app.getWeekDataByFlag(this.selectWeekNo).setFatStandardAnchor(m);
          return;
          this.goalWeightFlag.setVisibility(4);
          this.goalWeightText.setVisibility(4);
          break;
          this.goalWeightAnchor.setVisibility(4);
          this.goalWeightFlag.setVisibility(4);
          this.goalWeightText.setVisibility(4);
          break;
          if (arrayOfDouble1[i13] > 0.0D)
          {
            i++;
            if ((f3 > 0.0F) && (arrayOfDouble1[i13] >= f2))
            {
              j = i;
              break label423;
            }
            if ((f3 <= 0.0F) && (arrayOfDouble1[i13] <= f2))
            {
              j = i;
              break label423;
            }
          }
          i13++;
          break label416;
          this.weightStandardFlag.setVisibility(4);
          break label560;
          this.weightStandardFlag.setVisibility(4);
          break label560;
          this.weightStandardFlag.setVisibility(4);
          break label560;
          this.historyClock.setVisibility(4);
          this.historyPop.setVisibility(4);
          break label852;
          this.historyClock.setVisibility(4);
          this.historyPop.setVisibility(4);
          break label852;
          this.goalFatFlag.setVisibility(4);
          this.goalFatText.setVisibility(4);
          break label1170;
          this.goalFatAnchor.setVisibility(4);
          this.goalFatFlag.setVisibility(4);
          this.goalFatText.setVisibility(4);
          break label1170;
          if (arrayOfDouble2[n] <= 0.0D)
            break label1671;
          k++;
          if ((f5 > 0.0F) && (arrayOfDouble2[n] >= f4))
          {
            m = k;
          }
          else
          {
            if ((f5 > 0.0F) || (arrayOfDouble2[n] > f4))
              break label1671;
            m = k;
          }
        }
      }
      this.fatStandardFlag.setVisibility(4);
      return;
      this.fatStandardFlag.setVisibility(4);
      return;
      this.fatStandardFlag.setVisibility(4);
      return;
      if (this.goalWeightAnchor != null)
        this.goalWeightAnchor.setVisibility(4);
      if (this.goalWeightAnchor != null)
        this.goalFatAnchor.setVisibility(4);
      if (this.weightStandardFlag != null)
        this.weightStandardFlag.setVisibility(4);
      if (this.historyClock != null)
        this.historyClock.setVisibility(4);
      if (this.fatStandardFlag != null)
        this.fatStandardFlag.setVisibility(4);
      if (this.goalWeightFlag != null)
        this.goalWeightFlag.setVisibility(4);
      if (this.goalFatFlag != null)
        this.goalFatFlag.setVisibility(4);
      if (this.goalWeightText != null)
        this.goalWeightText.setVisibility(4);
    }
    while (this.goalWeightText == null);
    label1170: label1489: this.goalFatText.setVisibility(4);
    label1421: label1560: label1579: return;
  }

  private void refreshThresCycle()
  {
    if (this.chartMode == 2131099861)
    {
      LatinWeekData localLatinWeekData = this.app.getWeekDataByFlag(this.selectWeekNo);
      if (localLatinWeekData.getWeekWeightChange() > 0.0F)
      {
        this.tizhong.setCompoundDrawablesWithIntrinsicBounds(2130838443, 0, 0, 0);
        if (Math.abs(localLatinWeekData.getWeekWeightChange()) <= 10.0F)
          break label272;
        this.tizhong.setTextSize(14.0F);
        label71: if (Math.abs(localLatinWeekData.getWeekBodyFatChange()) <= 10.0F)
          break label285;
        this.tizhi.setTextSize(14.0F);
        label96: this.tizhong.setText(NumUtils.roundValue(Math.abs(localLatinWeekData.getWeekWeightChange())) + "kg");
        if (localLatinWeekData.getWeekBodyFatChange() <= 0.0F)
          break label298;
        this.tizhi.setCompoundDrawablesWithIntrinsicBounds(2130838441, 0, 0, 0);
        label156: this.tizhi.setText(NumUtils.roundValue(Math.abs(localLatinWeekData.getWeekBodyFatChange())) + "%");
        int n = localLatinWeekData.getWeekWeightingNum();
        this.chengzhongtianshu.setText(n + "/7");
        if (this.selectWeekNo != 0)
          break label314;
        this.tizhongText.setText("本周体重");
        this.tizhiText.setText("本周脂肪率");
      }
    }
    label272: label285: label298: label314: label585: 
    do
    {
      return;
      this.tizhong.setCompoundDrawablesWithIntrinsicBounds(2130838442, 0, 0, 0);
      break;
      this.tizhong.setTextSize(17.0F);
      break label71;
      this.tizhi.setTextSize(17.0F);
      break label96;
      this.tizhi.setCompoundDrawablesWithIntrinsicBounds(2130838440, 0, 0, 0);
      break label156;
      this.tizhongText.setText("上周体重");
      this.tizhiText.setText("上周脂肪率");
      return;
      if (this.chartMode == 2131099862)
      {
        LatinMonthData localLatinMonthData = this.app.getMonthDataByFlag(this.selectMonthNo);
        if (localLatinMonthData.getMonthWeightChange() > 0.0F)
        {
          this.tizhong.setCompoundDrawablesWithIntrinsicBounds(2130838443, 0, 0, 0);
          this.tizhong.setText(NumUtils.roundValue(Math.abs(localLatinMonthData.getMonthWeightChange())) + "kg");
          if (localLatinMonthData.getMonthBodyFatChange() <= 0.0F)
            break label569;
          this.tizhi.setCompoundDrawablesWithIntrinsicBounds(2130838441, 0, 0, 0);
        }
        while (true)
        {
          this.tizhi.setText(NumUtils.roundValue(Math.abs(localLatinMonthData.getMonthBodyFatChange())) + "%");
          int k = localLatinMonthData.getMonthWeightingNum();
          int m = localLatinMonthData.getDays();
          this.chengzhongtianshu.setText(k + "/" + m);
          if (this.selectMonthNo != 0)
            break label585;
          this.tizhongText.setText("本月体重");
          this.tizhiText.setText("本月脂肪率");
          return;
          this.tizhong.setCompoundDrawablesWithIntrinsicBounds(2130838442, 0, 0, 0);
          break;
          this.tizhi.setCompoundDrawablesWithIntrinsicBounds(2130838440, 0, 0, 0);
        }
        this.tizhongText.setText("上月体重");
        this.tizhiText.setText("上月脂肪率");
        return;
      }
    }
    while (this.chartMode != 2131099863);
    label569: LatinSeasonData localLatinSeasonData = this.app.getSeasonDataByFlag(this.selectSeasonNo);
    if (localLatinSeasonData.getMonthWeightChange() > 0.0F)
    {
      this.tizhong.setCompoundDrawablesWithIntrinsicBounds(2130838443, 0, 0, 0);
      this.tizhong.setText(NumUtils.roundValue(Math.abs(localLatinSeasonData.getMonthWeightChange())) + "kg");
      if (localLatinSeasonData.getMonthBodyFatChange() <= 0.0F)
        break label829;
      this.tizhi.setCompoundDrawablesWithIntrinsicBounds(2130838441, 0, 0, 0);
    }
    while (true)
    {
      this.tizhi.setText(NumUtils.roundValue(Math.abs(localLatinSeasonData.getMonthBodyFatChange())) + "%");
      int i = localLatinSeasonData.getMonthWeightingNum();
      int j = localLatinSeasonData.getDays();
      this.chengzhongtianshu.setText(i + "/" + j);
      if (this.selectSeasonNo != 0)
        break label845;
      this.tizhongText.setText("本季体重");
      this.tizhiText.setText("本季脂肪率");
      return;
      this.tizhong.setCompoundDrawablesWithIntrinsicBounds(2130838442, 0, 0, 0);
      break;
      label829: this.tizhi.setCompoundDrawablesWithIntrinsicBounds(2130838440, 0, 0, 0);
    }
    label845: this.tizhongText.setText("上季体重");
    this.tizhiText.setText("上季脂肪率");
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.screenWidth = ((WindowManager)getActivity().getSystemService("window")).getDefaultDisplay().getWidth();
    this.app = ((MyApplication)getActivity().getApplicationContext());
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.layout = paramLayoutInflater.inflate(2130903081, paramViewGroup, false);
    this.linearLayout = ((RelativeLayout)this.layout.findViewById(2131099878));
    this.mAnim = new AnimUtils(getActivity());
    initBottomView();
    initAnchorAndFlag();
    initChart();
    initDate();
    initDateTab();
    initSeasonLables();
    this.anim = new TranslateAnimation(0.0F, 0.0F, 30.0F, 0.0F);
    this.anim.setDuration(350L);
    this.anim.setInterpolator(new OvershootInterpolator(4.3F));
    return this.layout;
  }

  public void refreshChart()
  {
    if (this.chartView != null)
      this.chartView.refreshChart();
  }

  public void refreshChartByTypeAndFlag(int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    default:
      return;
    case 2131099861:
      long[] arrayOfLong3 = DateUtils.getWeekStartTimeAndEndTimeByFlag(paramInt2);
      String[] arrayOfString3 = new String[2];
      arrayOfString3[0] = DateUtils.changeTimeStampToFormatTime(arrayOfLong3[0], "MM月dd日");
      arrayOfString3[1] = DateUtils.changeTimeStampToFormatTime(arrayOfLong3[1], "MM月dd日");
      changeStartTimeAndEndTime(arrayOfString3, 2131099861, paramInt2);
      return;
    case 2131099862:
      long[] arrayOfLong2 = DateUtils.getMonthStartTimeAndEndTimeByFlag(paramInt2);
      String[] arrayOfString2 = new String[2];
      arrayOfString2[0] = DateUtils.changeTimeStampToFormatTime(arrayOfLong2[0], "MM月dd日");
      arrayOfString2[1] = DateUtils.changeTimeStampToFormatTime(arrayOfLong2[1], "MM月dd日");
      changeStartTimeAndEndTime(arrayOfString2, 2131099862, paramInt2);
      return;
    case 2131099863:
    }
    long[] arrayOfLong1 = DateUtils.getSeasonStartTimeAndTimeAndDays(paramInt2);
    String[] arrayOfString1;
    if (DateUtils.changeTimeStampToFormatTime(arrayOfLong1[0], "yyyy").equals(DateUtils.changeTimeStampToFormatTime(System.currentTimeMillis(), "yyyy")))
    {
      arrayOfString1 = new String[2];
      arrayOfString1[0] = DateUtils.changeTimeStampToFormatTime(arrayOfLong1[0], "MM月");
      arrayOfString1[1] = DateUtils.changeTimeStampToFormatTime(arrayOfLong1[1], "MM月");
    }
    while (true)
    {
      changeStartTimeAndEndTime(arrayOfString1, 2131099863, paramInt2);
      return;
      arrayOfString1 = new String[2];
      arrayOfString1[0] = DateUtils.changeTimeStampToFormatTime(arrayOfLong1[0], "yy年MM月");
      arrayOfString1[1] = DateUtils.changeTimeStampToFormatTime(arrayOfLong1[1], "MM月");
    }
  }

  public void refreshChartWhenSettingGoalWeightSuccess()
  {
    refreshChartByTypeAndFlag(2131099861, 0);
  }

  public void refreshPrevAndNextButton()
  {
    int i = this.app.getFirstWeekFlag();
    int j = this.selectWeekNo;
    switch (this.chartMode)
    {
    case 2131099861:
    default:
    case 2131099862:
    case 2131099863:
    }
    while (true)
    {
      changeNextButtonAndNextButtonState(j, i);
      return;
      this.app.getFirstMonthFlag();
      i = this.selectMonthNo;
      continue;
      this.app.getFirstSeasonFlag();
      i = this.selectSeasonNo;
    }
  }

  public void tiShiKuang(TextView paramTextView)
  {
    switch (this.radioGroup.getCheckedRadioButtonId())
    {
    default:
      return;
    case 2131099861:
      paramTextView.setText("本周最后一次测量与上周最后一次测量的差值");
      return;
    case 2131099862:
      paramTextView.setText("本月最后一次测量与上月最后一次测量的差值");
      return;
    case 2131099863:
      paramTextView.setText("本季最后一次测量与上季最后一次测量的差值");
      return;
    case 2131099864:
    }
    paramTextView.setText("本年最后一次测量与上年最后一次测量的差值");
  }

  public void updateUI()
  {
    if (this.radioGroup.getCheckedRadioButtonId() != 2131099861)
    {
      this.selectWeekNo = 0;
      this.radioGroup.check(2131099861);
    }
    while (true)
    {
      refreshThresCycle();
      return;
      this.selectWeekNo = 0;
      refreshChartByTypeAndFlag(2131099861, this.selectWeekNo);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LatinChartFragment
 * JD-Core Version:    0.6.2
 */
