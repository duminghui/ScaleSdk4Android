package com.picooc;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.picooc.model.InterimReportMessageEntityNew;
import com.picooc.model.InterimReportModel;
import com.picooc.utils.NumUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.MyCircleView;
import com.picooc.widget.ViewExpandAnimation;
import com.picooc.widget.chart.PicoocChart;
import java.util.ArrayList;
import org.achartengine.GraphicalView;
import org.achartengine.model.SeriesSelection;
import org.achartengine.tools.ModUtils;

public class LiftInterimReport extends PicoocActivity
  implements View.OnClickListener
{
  LinearLayout botoom;
  private RelativeLayout chartLayout;
  private GraphicalView chartView;
  private int firstWeightIndex = 0;
  private int lastWeightIndex = 0;
  private float mDensity = 0.0F;
  private int mLcdWidth = 0;
  private int maxWeightIndex = 0;
  private int minWeightIndex = 0;
  private InterimReportModel model;
  private PicoocChart pc;
  private TextView s_jd_bootomTextOne;
  private TextView s_jd_bootomTextTwo;
  private TextView s_jd_endDate;
  private TextView s_jd_jzState;
  private TextView s_jd_jzfsText;
  private TextView s_jd_startDate;
  private TextView s_jd_titleMiaoshuText;
  private TextView s_jd_todayWeight;
  private TextView s_jd_weightMiaoShu;

  private void initChart()
  {
    this.pc = new PicoocChart();
    this.chartLayout = ((RelativeLayout)findViewById(2131099878));
    float f1 = this.model.getRole().getGoal_weight();
    ArrayList localArrayList = this.model.getWeightArray();
    double[] arrayOfDouble1 = new double[localArrayList.size()];
    double[] arrayOfDouble2 = new double[localArrayList.size()];
    double[] arrayOfDouble3 = new double[localArrayList.size()];
    int i = 0;
    float f2;
    float f3;
    if (i >= localArrayList.size())
    {
      f2 = this.model.getMaxWeight();
      f3 = this.model.getMinWeight();
      if (f2 <= f1)
        break label272;
      label103: if (f1 <= f3)
        break label278;
    }
    while (true)
    {
      float f4 = f2 - f3;
      float f5 = f2 + f4 / 3.0F;
      float f6 = f3 - f4 / 3.0F;
      this.chartView = this.pc.execute(this, arrayOfDouble1, arrayOfDouble2, arrayOfDouble3, f5, f6);
      this.chartView.setFlag_int(3);
      this.chartView.setZOrderOnTop(true);
      this.chartView.getHolder().setFormat(-3);
      this.chartLayout.addView(this.chartView, 0);
      refreshAnchorAndFlag();
      return;
      arrayOfDouble1[i] = (i + 1);
      arrayOfDouble2[i] = ((Float)localArrayList.get(i)).floatValue();
      if ((i == 0) || (i == -1 + localArrayList.size()))
        arrayOfDouble3[i] = f1;
      while (true)
      {
        i++;
        break;
        arrayOfDouble3[i] = -1.0D;
      }
      label272: f2 = f1;
      break label103;
      label278: f3 = f1;
    }
  }

  private void refreshAnchorAndFlag()
  {
    Typeface localTypeface = TypefaceUtils.getTypeface(this, null);
    final TextView localTextView1 = (TextView)findViewById(2131100471);
    localTextView1.setText(NumUtils.roundValue(this.model.getFirstWeight()));
    localTextView1.setTypeface(localTypeface);
    final TextView localTextView2 = (TextView)findViewById(2131100472);
    localTextView2.setText(NumUtils.roundValue(this.model.getLastWeight()));
    localTextView2.setTypeface(localTypeface);
    final TextView localTextView3 = (TextView)findViewById(2131100473);
    localTextView3.setText(NumUtils.roundValue(this.model.getMaxWeight()));
    localTextView3.setTypeface(localTypeface);
    final MyCircleView localMyCircleView1 = (MyCircleView)findViewById(2131100474);
    localMyCircleView1.setCircle(12, Color.parseColor("#ff0000"));
    final TextView localTextView4 = (TextView)findViewById(2131100475);
    localTextView4.setText(NumUtils.roundValue(this.model.getMinWeight()));
    localTextView4.setTypeface(localTypeface);
    final MyCircleView localMyCircleView2 = (MyCircleView)findViewById(2131100476);
    localMyCircleView2.setCircle(12, Color.parseColor("#B5F280"));
    this.maxWeightIndex = this.model.getMaxWeightIndex();
    this.minWeightIndex = this.model.getMinWeightIndex();
    ArrayList localArrayList = this.model.getWeightArray();
    this.lastWeightIndex = (-1 + localArrayList.size());
    int i = 0;
    if (i >= this.model.getWeightArray().size())
    {
      if (this.model.getMaxWeight() == this.model.getMinWeight())
      {
        this.maxWeightIndex = -1;
        this.minWeightIndex = -1;
      }
      if (this.maxWeightIndex != this.firstWeightIndex)
        break label470;
      this.firstWeightIndex = -1;
      label278: if (this.minWeightIndex != this.firstWeightIndex)
        break label489;
      this.firstWeightIndex = -1;
    }
    while (true)
    {
      final TextView localTextView5 = (TextView)findViewById(2131100477);
      localTextView5.setText(NumUtils.roundValue(this.model.getRole().getGoal_weight()));
      localTextView5.setTypeface(localTypeface);
      final ImageView localImageView = (ImageView)findViewById(2131099885);
      if (this.model.getRole().getGoal_weight() > 0.0F)
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            if (LiftInterimReport.this.firstWeightIndex >= 0)
            {
              SeriesSelection localSeriesSelection5 = LiftInterimReport.this.chartView.getPointBySeriesIndexAndPointIndex(1, LiftInterimReport.this.firstWeightIndex);
              if (localSeriesSelection5 != null)
              {
                RelativeLayout.LayoutParams localLayoutParams8 = (RelativeLayout.LayoutParams)localTextView1.getLayoutParams();
                int i9 = (int)localSeriesSelection5.getCenterX() - localTextView1.getWidth() / 2;
                int i10 = (int)(localSeriesSelection5.getCenterY() - 1.1D * localTextView1.getHeight());
                localTextView1.setLayoutParams(localLayoutParams8);
                localLayoutParams8.setMargins(i9, i10, 0, 0);
              }
              if (LiftInterimReport.this.lastWeightIndex < 0)
                break label802;
              SeriesSelection localSeriesSelection4 = LiftInterimReport.this.chartView.getPointBySeriesIndexAndPointIndex(1, LiftInterimReport.this.lastWeightIndex);
              if (localSeriesSelection4 != null)
              {
                RelativeLayout.LayoutParams localLayoutParams7 = (RelativeLayout.LayoutParams)localTextView2.getLayoutParams();
                int i7 = (int)localSeriesSelection4.getCenterX() - localTextView2.getWidth() / 2;
                int i8 = (int)(localSeriesSelection4.getCenterY() - 1.1D * localTextView2.getHeight());
                localTextView2.setLayoutParams(localLayoutParams7);
                localLayoutParams7.setMargins(i7, i8, 0, 0);
              }
              label212: if (LiftInterimReport.this.maxWeightIndex < 0)
                break label814;
              SeriesSelection localSeriesSelection3 = LiftInterimReport.this.chartView.getPointBySeriesIndexAndPointIndex(1, LiftInterimReport.this.maxWeightIndex);
              if (localSeriesSelection3 != null)
              {
                RelativeLayout.LayoutParams localLayoutParams5 = (RelativeLayout.LayoutParams)localTextView3.getLayoutParams();
                int i3 = (int)localSeriesSelection3.getCenterX() - localTextView3.getWidth() / 2;
                int i4 = (int)(localSeriesSelection3.getCenterY() - 1.1D * localTextView3.getHeight());
                localTextView3.setLayoutParams(localLayoutParams5);
                localLayoutParams5.setMargins(i3, i4, 0, 0);
                RelativeLayout.LayoutParams localLayoutParams6 = (RelativeLayout.LayoutParams)localMyCircleView1.getLayoutParams();
                int i5 = (int)localSeriesSelection3.getCenterX() - localMyCircleView1.getWidth() / 2;
                int i6 = (int)localSeriesSelection3.getCenterY() - localMyCircleView1.getHeight() / 2;
                localMyCircleView1.setLayoutParams(localLayoutParams6);
                localLayoutParams6.setMargins(i5, i6, 0, 0);
              }
              label386: if (LiftInterimReport.this.minWeightIndex < 0)
                break label835;
              SeriesSelection localSeriesSelection2 = LiftInterimReport.this.chartView.getPointBySeriesIndexAndPointIndex(1, LiftInterimReport.this.minWeightIndex);
              Log.i("http", "minWeightIndex=" + LiftInterimReport.this.minWeightIndex + "  ss=" + localSeriesSelection2);
              if (localSeriesSelection2 != null)
              {
                RelativeLayout.LayoutParams localLayoutParams3 = (RelativeLayout.LayoutParams)localTextView4.getLayoutParams();
                int m = (int)localSeriesSelection2.getCenterX() - localTextView4.getWidth() / 2;
                int n = (int)(localSeriesSelection2.getCenterY() - 1.1D * localTextView4.getHeight());
                localTextView4.setLayoutParams(localLayoutParams3);
                localLayoutParams3.setMargins(m, n, 0, 0);
                RelativeLayout.LayoutParams localLayoutParams4 = (RelativeLayout.LayoutParams)localMyCircleView2.getLayoutParams();
                int i1 = (int)localSeriesSelection2.getCenterX() - localMyCircleView2.getWidth() / 2;
                int i2 = (int)localSeriesSelection2.getCenterY() - localMyCircleView2.getHeight() / 2;
                localMyCircleView2.setLayoutParams(localLayoutParams4);
                localLayoutParams4.setMargins(i1, i2, 0, 0);
                Log.i("http", "minWeightIndex=" + LiftInterimReport.this.minWeightIndex + "  ss=" + localSeriesSelection2 + " x=" + i1 + " y=" + i2);
              }
            }
            while (true)
            {
              SeriesSelection localSeriesSelection1 = LiftInterimReport.this.chartView.getPointBySeriesIndexAndPointIndex(0, 1);
              if (localSeriesSelection1 != null)
              {
                RelativeLayout.LayoutParams localLayoutParams1 = (RelativeLayout.LayoutParams)localTextView5.getLayoutParams();
                int i = (int)localSeriesSelection1.getCenterX() + localTextView5.getWidth() / 10;
                int j = (int)(localSeriesSelection1.getCenterY() + 0.1D * localTextView5.getHeight());
                localTextView5.setLayoutParams(localLayoutParams1);
                localLayoutParams1.setMargins(i, j, 0, 0);
                RelativeLayout.LayoutParams localLayoutParams2 = (RelativeLayout.LayoutParams)localImageView.getLayoutParams();
                int k = (int)(localSeriesSelection1.getCenterY() - 1.1D * localImageView.getHeight());
                localImageView.setLayoutParams(localLayoutParams2);
                localLayoutParams2.setMargins(i, k, 0, 0);
              }
              return;
              localTextView1.setVisibility(8);
              break;
              label802: localTextView2.setVisibility(8);
              break label212;
              label814: localTextView3.setVisibility(8);
              localMyCircleView1.setVisibility(8);
              break label386;
              label835: localMyCircleView2.setVisibility(8);
              localTextView4.setVisibility(8);
            }
          }
        }
        , 100L);
      return;
      if (((Float)localArrayList.get(i)).floatValue() <= 0.0F)
      {
        this.lastWeightIndex = (-1 + this.lastWeightIndex);
        if (i < this.model.getMaxWeightIndex())
          this.maxWeightIndex = (-1 + this.maxWeightIndex);
        if (i < this.model.getMinWeightIndex())
          this.minWeightIndex = (-1 + this.minWeightIndex);
      }
      i++;
      break;
      label470: if (this.maxWeightIndex != this.lastWeightIndex)
        break label278;
      this.lastWeightIndex = -1;
      break label278;
      label489: if (this.minWeightIndex == this.lastWeightIndex)
        this.lastWeightIndex = -1;
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
    Typeface localTypeface = TypefaceUtils.getTypeface(this, null);
    ImageView localImageView1 = (ImageView)findViewById(2131099651);
    ImageView localImageView2 = (ImageView)findViewById(2131099650);
    localImageView1.setImageResource(2130838408);
    localImageView1.setOnClickListener(this);
    localImageView2.setOnClickListener(this);
    localImageView2.setImageResource(2130838406);
    ((TextView)findViewById(2131099699)).setText("阶段报告");
    this.botoom = ((LinearLayout)findViewById(2131100486));
    this.s_jd_titleMiaoshuText = ((TextView)findViewById(2131100468));
    this.s_jd_titleMiaoshuText.setText(this.model.getTableHeaderViewTitle());
    this.s_jd_todayWeight = ((TextView)findViewById(2131100469));
    this.s_jd_todayWeight.setText(this.model.getSectionHeaderText1());
    this.s_jd_startDate = ((TextView)findViewById(2131100479));
    this.s_jd_startDate.setText(this.model.getStartData());
    this.s_jd_endDate = ((TextView)findViewById(2131100480));
    this.s_jd_endDate.setText(this.model.getEndDate());
    this.s_jd_weightMiaoShu = ((TextView)findViewById(2131100482));
    ModUtils.replaceNumColor(this.s_jd_weightMiaoShu, this, this.model.getTodayWeightMessage());
    this.s_jd_jzfsText = ((TextView)findViewById(2131100484));
    this.s_jd_jzfsText.setText(this.model.getSectionHeaderText2());
    this.s_jd_jzState = ((TextView)findViewById(2131100485));
    String str = this.model.getLoseWeightPattern();
    this.s_jd_jzState.setText(str);
    if (!str.contains("不"))
    {
      this.s_jd_jzState.setBackgroundResource(2130838179);
      this.s_jd_jzState.setPadding(12, 0, 12, 0);
    }
    this.s_jd_bootomTextOne = ((TextView)findViewById(2131100488));
    this.s_jd_bootomTextTwo = ((TextView)findViewById(2131100490));
    ArrayList localArrayList = this.model.getLoseWeightMessages();
    if (localArrayList.size() == 2)
    {
      this.s_jd_bootomTextOne.setTypeface(localTypeface);
      this.s_jd_bootomTextOne.setLineSpacing(3.4F, 1.2F);
      ModUtils.replaceNumColor(this.s_jd_bootomTextOne, this, ((InterimReportMessageEntityNew)localArrayList.get(0)).getContent());
      this.s_jd_bootomTextTwo.setTypeface(localTypeface);
      this.s_jd_bootomTextTwo.setLineSpacing(3.4F, 1.2F);
      ModUtils.replaceNumColor(this.s_jd_bootomTextTwo, this, ((InterimReportMessageEntityNew)localArrayList.get(1)).getContent());
    }
    while (true)
    {
      this.s_jd_titleMiaoshuText.setTypeface(localTypeface);
      this.s_jd_todayWeight.setTypeface(localTypeface);
      this.s_jd_startDate.setTypeface(localTypeface);
      this.s_jd_endDate.setTypeface(localTypeface);
      this.s_jd_weightMiaoShu.setTypeface(localTypeface);
      this.s_jd_titleMiaoshuText.setTypeface(localTypeface);
      DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
      this.mLcdWidth = localDisplayMetrics.widthPixels;
      this.mDensity = localDisplayMetrics.density;
      initChart();
      return;
      if (localArrayList.size() == 1)
      {
        this.s_jd_bootomTextOne.setText(((InterimReportMessageEntityNew)localArrayList.get(0)).getContent());
        this.s_jd_bootomTextOne.setTypeface(localTypeface);
        findViewById(2131100489).setVisibility(8);
      }
      else
      {
        findViewById(2131100487).setVisibility(8);
        findViewById(2131100489).setVisibility(8);
      }
    }
  }

  public void invitGoneTop(View paramView)
  {
    paramView.measure(View.MeasureSpec.makeMeasureSpec((int)(this.mLcdWidth - 10.0F * this.mDensity), 1073741824), 0);
    ((LinearLayout.LayoutParams)paramView.getLayoutParams()).bottomMargin = (-paramView.getMeasuredHeight());
    paramView.setVisibility(8);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131099650:
      finish();
      return;
    case 2131099651:
      startActivity(new Intent(this, InterimReportWhy.class));
      overridePendingTransition(2130968580, 2130968577);
      return;
    case 2131100483:
    }
    dismissOrShowOne(this.botoom);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903193);
    this.model = ((InterimReportModel)getIntent().getSerializableExtra("model"));
    invit();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LiftInterimReport
 * JD-Core Version:    0.6.2
 */
