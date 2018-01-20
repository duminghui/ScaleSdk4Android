package com.picooc;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.picooc.adapter.LiftMovePlanAdapter;
import com.picooc.guide.GuideAct;
import com.picooc.guide.GuideModel;
import com.picooc.model.SportPlanEnum;
import com.picooc.model.SportPlanModel;
import com.picooc.model.SportPlanModel.DayPlan;
import com.picooc.model.SportPlanModel.WeekPlan;
import com.picooc.utils.SharedPreferenceUtils;
import com.slideexpandlistview.sample.ActionSlideExpandableListView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.achartengine.tools.ModUtils;

public class LiftMovementPlanAct extends PicoocActivity
  implements View.OnClickListener
{
  View Fview;
  View Hview;
  private LiftMovePlanAdapter adp;
  ArrayList<DayPlan> dayPlans;
  LinearLayout headLiner;
  ActionSlideExpandableListView list;
  private int[] locPersonImage;
  private int[] locSportText;
  private int[] locTimesText;
  private GuideModel mGuide;
  private List<GuideModel> mGuideList;
  private ImageView pImageView;
  private TextView sTextView;
  private SportPlanModel sportPlan;
  private TextView tTextView;
  ArrayList<WeekPlan> weekPlans;
  TextView yundong_diqiang;
  TextView yundong_diqiangCout;
  TextView yundong_footText;
  TextView yundong_wuyang;
  TextView yundong_wuyangCount;
  TextView yundong_youyang;
  TextView yundong_youyangCount;

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968576, 2130968581);
  }

  public void invit()
  {
    ImageView localImageView1 = (ImageView)findViewById(2131099651);
    ImageView localImageView2 = (ImageView)findViewById(2131099650);
    localImageView1.setImageResource(2130838428);
    localImageView1.setOnClickListener(this);
    localImageView2.setOnClickListener(this);
    localImageView2.setImageResource(2130838406);
    localImageView1.setVisibility(8);
    ((TextView)findViewById(2131099699)).setText("运动方案");
    this.yundong_diqiang = ((TextView)this.Hview.findViewById(2131099722));
    this.yundong_diqiangCout = ((TextView)this.Hview.findViewById(2131099723));
    this.yundong_youyang = ((TextView)this.Hview.findViewById(2131099724));
    this.yundong_youyangCount = ((TextView)this.Hview.findViewById(2131099725));
    this.yundong_wuyang = ((TextView)this.Hview.findViewById(2131099726));
    this.yundong_wuyangCount = ((TextView)this.Hview.findViewById(2131099727));
    this.yundong_footText = ((TextView)this.Hview.findViewById(2131099720));
    this.yundong_youyangCount.setTypeface(ModUtils.getTypeface(this));
    this.headLiner = ((LinearLayout)this.Hview.findViewById(2131099721));
    Iterator localIterator;
    if (this.weekPlans.size() > 0)
    {
      localIterator = this.weekPlans.iterator();
      if (localIterator.hasNext());
    }
    else
    {
      return;
    }
    WeekPlan localWeekPlan = (WeekPlan)localIterator.next();
    View localView = LayoutInflater.from(this).inflate(2130903091, null);
    TextView localTextView1 = (TextView)localView.findViewById(2131099722);
    TextView localTextView2 = (TextView)localView.findViewById(2131099723);
    LinearLayout localLinearLayout = (LinearLayout)localView.findViewById(2131099943);
    if (localWeekPlan.getSportType() == SportPlanEnum.DQD)
    {
      localTextView1.setCompoundDrawablesWithIntrinsicBounds(2130838286, 0, 0, 0);
      localTextView2.setTextColor(Color.parseColor("#7fc169"));
    }
    while (true)
    {
      localTextView1.setText(localWeekPlan.getSportTypeName());
      localTextView2.setText(localWeekPlan.getSportCycle() + localWeekPlan.getSportCycleUnit());
      localTextView2.setTypeface(ModUtils.getTypeface(this));
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
      localLayoutParams.setMargins(0, 30, 0, 30);
      localLinearLayout.setGravity(1);
      localLinearLayout.setLayoutParams(localLayoutParams);
      this.headLiner.addView(localView);
      break;
      if (localWeekPlan.getSportType() == SportPlanEnum.YY)
      {
        localTextView1.setCompoundDrawablesWithIntrinsicBounds(2130838309, 0, 0, 0);
        localTextView2.setTextColor(Color.parseColor("#f8b551"));
      }
      else if (localWeekPlan.getSportType() == SportPlanEnum.WY)
      {
        localTextView1.setCompoundDrawablesWithIntrinsicBounds(2130838306, 0, 0, 0);
        localTextView2.setTextColor(Color.parseColor("#f19049"));
      }
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
    setContentView(2130903196);
    this.sportPlan = ((SportPlanModel)getIntent().getSerializableExtra("model"));
    this.weekPlans = this.sportPlan.getWeekSport();
    this.dayPlans = this.sportPlan.getDaySport();
    this.list = ((ActionSlideExpandableListView)findViewById(2131100084));
    this.Hview = LayoutInflater.from(this).inflate(2130903053, null);
    this.Fview = LayoutInflater.from(this).inflate(2130903052, null);
    ((TextView)this.Fview.findViewById(2131099720)).setText(this.sportPlan.getTipsMessage(this));
    this.list.addHeaderView(this.Hview);
    this.list.addFooterView(this.Fview);
    invit();
    this.adp = new LiftMovePlanAdapter(this, this.dayPlans);
    this.list.setAdapter(this.adp);
    this.list.setAnimationCacheEnabled(true);
    if (SharedPreferenceUtils.isFirstEnterCurPage(this, "lift_movementplan"))
    {
      new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
      {
        public void run()
        {
          View localView = LiftMovementPlanAct.this.adp.getFirstView();
          LiftMovementPlanAct.this.pImageView = ((ImageView)localView.findViewById(2131099702));
          LiftMovementPlanAct.this.sTextView = ((TextView)localView.findViewById(2131099696));
          LiftMovementPlanAct.this.tTextView = ((TextView)localView.findViewById(2131099792));
          LiftMovementPlanAct.this.mGuideList = new ArrayList();
          Rect localRect = new Rect();
          LiftMovementPlanAct.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
          int i = localRect.top;
          LiftMovementPlanAct.this.locPersonImage = new int[2];
          LiftMovementPlanAct.this.pImageView.setDrawingCacheEnabled(true);
          LiftMovementPlanAct.this.pImageView.getLocationInWindow(LiftMovementPlanAct.this.locPersonImage);
          LiftMovementPlanAct.this.mGuide = new GuideModel(LiftMovementPlanAct.this.pImageView.getDrawingCache(), LiftMovementPlanAct.this.locPersonImage[0], LiftMovementPlanAct.this.locPersonImage[1]);
          LiftMovementPlanAct.this.mGuideList.add(LiftMovementPlanAct.this.mGuide);
          LiftMovementPlanAct.this.locSportText = new int[2];
          LiftMovementPlanAct.this.sTextView.setDrawingCacheEnabled(true);
          LiftMovementPlanAct.this.sTextView.getLocationInWindow(LiftMovementPlanAct.this.locSportText);
          LiftMovementPlanAct.this.mGuide = new GuideModel(LiftMovementPlanAct.this.sTextView.getDrawingCache(), LiftMovementPlanAct.this.locSportText[0], LiftMovementPlanAct.this.locSportText[1]);
          LiftMovementPlanAct.this.mGuideList.add(LiftMovementPlanAct.this.mGuide);
          LiftMovementPlanAct.this.locTimesText = new int[2];
          LiftMovementPlanAct.this.tTextView.setDrawingCacheEnabled(true);
          LiftMovementPlanAct.this.tTextView.getLocationInWindow(LiftMovementPlanAct.this.locTimesText);
          LiftMovementPlanAct.this.mGuide = new GuideModel(LiftMovementPlanAct.this.tTextView.getDrawingCache(), LiftMovementPlanAct.this.locTimesText[0], LiftMovementPlanAct.this.locTimesText[1]);
          LiftMovementPlanAct.this.mGuideList.add(LiftMovementPlanAct.this.mGuide);
          Intent localIntent = new Intent(LiftMovementPlanAct.this, GuideAct.class);
          localIntent.putExtra("pageId", 5);
          localIntent.putExtra("statusBarHeight", i);
          localIntent.putExtra("guideList", (Serializable)LiftMovementPlanAct.this.mGuideList);
          LiftMovementPlanAct.this.startActivity(localIntent);
          LiftMovementPlanAct.this.overridePendingTransition(-1, -1);
        }
      }
      , 1000L);
      SharedPreferenceUtils.resetCurPage(this, false, "lift_movementplan");
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LiftMovementPlanAct
 * JD-Core Version:    0.6.2
 */
