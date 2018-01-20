package com.picooc;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.picooc.adapter.liftPhysicalAdapter;
import com.picooc.model.BodyCompositionAnalysisModel;
import com.picooc.widget.picoocProgress.ArcProgress;
import com.slideexpandlistview.sample.ActionSlideExpandableListView;
import com.slideexpandlistview.sample.ActionSlideExpandableListView.OnActionClickListener;
import org.achartengine.tools.ModUtils;

public class LiftPhysicalQuality extends PicoocActivity
  implements View.OnClickListener
{
  private BodyCompositionAnalysisModel model;

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968576, 2130968581);
  }

  public void invit()
  {
    ActionSlideExpandableListView localActionSlideExpandableListView = (ActionSlideExpandableListView)findViewById(2131100494);
    ImageView localImageView1 = (ImageView)findViewById(2131099651);
    ImageView localImageView2 = (ImageView)findViewById(2131099650);
    localImageView1.setImageResource(2130838428);
    localImageView1.setOnClickListener(this);
    localImageView2.setOnClickListener(this);
    localImageView2.setImageResource(2130838335);
    localImageView1.setVisibility(8);
    ((TextView)findViewById(2131099699)).setText("身体成分评测");
    LinearLayout localLinearLayout = (LinearLayout)LayoutInflater.from(this).inflate(2130903051, null);
    TextView localTextView1 = (TextView)localLinearLayout.findViewById(2131099717);
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
      RelativeLayout localRelativeLayout = (RelativeLayout)localLinearLayout.findViewById(2131099716);
      TextView localTextView2 = (TextView)localLinearLayout.findViewById(2131099718);
      localTextView2.setText(this.model.getTotalString());
      localTextView2.setLineSpacing(3.4F, 1.2F);
      localTextView2.setTypeface(ModUtils.getTypeface(this));
      localRelativeLayout.addView(localArcProgress, localLayoutParams);
      localActionSlideExpandableListView.addHeaderView(localLinearLayout);
      localActionSlideExpandableListView.setAdapter(new liftPhysicalAdapter(this, this.model));
      localActionSlideExpandableListView.setAnimationCacheEnabled(true);
      localActionSlideExpandableListView.setItemActionListener(new ActionSlideExpandableListView.OnActionClickListener()
      {
        public void onClick(View paramAnonymousView1, View paramAnonymousView2, int paramAnonymousInt)
        {
          Toast.makeText(LiftPhysicalQuality.this, "您点击了" + paramAnonymousInt, 1).show();
        }
      }
      , null);
      return;
      if (i >= 420)
        j = 8;
      else
        j = 4;
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
    setContentView(2130903197);
    this.model = ((BodyCompositionAnalysisModel)getIntent().getSerializableExtra("model"));
    invit();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LiftPhysicalQuality
 * JD-Core Version:    0.6.2
 */
