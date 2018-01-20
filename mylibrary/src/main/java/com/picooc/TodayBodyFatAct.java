package com.picooc;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.picooc.adapter.TodayBodyFatAdapter;
import com.picooc.arithmetic.ReportDirect;
import com.picooc.domain.ReportEntity;
import com.picooc.oldhumen.Age;
import com.picooc.utils.DateUtils;
import com.picooc.utils.NumUtils;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.widget.AnimUtils;
import java.util.ArrayList;
import org.achartengine.tools.ModUtils;

public class TodayBodyFatAct extends PicoocActivity
{
  private AnimUtils anim;
  private MyApplication app;
  private TextView bodyfatBootom;
  private LinearLayout bodyfatTishi;
  private TextView bodyfattop;
  private ImageView bodyfatwhywen;
  private ArrayList<Object> contentStrings = new ArrayList();
  private TextView date;
  ReportEntity fatReport;
  View footview;
  ImageView image;
  private ListView listView;
  private TodayBodyFatAdapter mMyAnimListAdapter;
  private TextView state;
  TextView todayText;
  private View view;

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968576, 2130968581);
  }

  public void invit()
  {
    this.date = ((TextView)this.view.findViewById(2131099757));
    this.bodyfatTishi = ((LinearLayout)findViewById(2131100708));
    this.bodyfatwhywen = ((ImageView)findViewById(2131100707));
    this.state = ((TextView)findViewById(2131099758));
    this.bodyfattop = ((TextView)this.view.findViewById(2131099759));
    this.bodyfatBootom = ((TextView)this.footview.findViewById(2131099755));
    this.bodyfatBootom.setTypeface(ModUtils.getTypeface(this));
    this.bodyfattop.setTypeface(ModUtils.getTypeface(this));
    this.date.setTypeface(ModUtils.getTypeface(this));
    long l1 = this.app.getTodayBody().getTime();
    long l2 = DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), l1);
    String str = DateUtils.changeTimeStampToFormatTime(l1, "MM月dd日");
    if ((l2 < 0L) || (l2 == 0L))
    {
      this.todayText.setText("今日脂肪率");
      this.date.setText(DateUtils.changeTimeStampToFormatTime(this.app.getTodayBody().getTime(), "MM月dd日"));
      if (this.app.getCurrentRole().getWeight_change_target() <= 0.0F)
        break label455;
      this.state.setBackgroundResource(2130838000);
      this.state.setText("增重中");
      this.image.setBackgroundResource(2130837560);
      label232: if (this.app.getCurrentRole().getWeight_change_target() <= 0.0F)
        break label583;
      if (this.app.getTodayBody().getWeight() < this.app.getCurrentRole().getGoal_weight())
        break label526;
      this.bodyfattop.setText("恭喜您体重已经达标!");
    }
    while (true)
    {
      if (Age.isOld(this.app))
      {
        float f = getResources().getDimension(2131230872);
        this.date.setTextSize(f);
        this.state.setTextSize(f);
        this.todayText.setTextSize(f);
        this.bodyfattop.setTextSize(f);
        this.bodyfatBootom.setTextSize(f);
        this.footview.findViewById(2131099755).setVisibility(8);
        ((View)this.state.getParent()).setVisibility(8);
      }
      return;
      this.date.setText(l2 + "天前(" + str + ")");
      this.todayText.setText(DateUtils.changeTimeStampToFormatTime(this.app.getTodayBody().getTime(), "MM月dd日") + "脂肪率");
      break;
      label455: if (this.app.getCurrentRole().getWeight_change_target() < 0.0F)
      {
        this.state.setBackgroundResource(2130837999);
        this.state.setText("减重中");
        break label232;
      }
      this.state.setBackgroundResource(2130837998);
      this.state.setText("保持中");
      this.anim.missAnima(this.bodyfatwhywen, 10L);
      break label232;
      label526: this.bodyfattop.setText("距离目标体重还有" + NumUtils.roundValue(Math.abs(this.app.getCurrentRole().getGoal_weight() - this.app.getTodayBody().getWeight())) + "kg");
      continue;
      label583: if (this.app.getCurrentRole().getWeight_change_target() <= 0.0F)
        if (this.app.getTodayBody().getWeight() <= this.app.getCurrentRole().getGoal_weight())
          this.bodyfattop.setText("恭喜您体重已经达标!");
        else
          this.bodyfattop.setText("距离目标体重还有" + NumUtils.roundValue(Math.abs(this.app.getCurrentRole().getGoal_weight() - this.app.getTodayBody().getWeight())) + "kg");
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131099980:
      finish();
      return;
    case 2131100707:
      this.anim.missAnima(this.listView, 400L);
      this.anim.missAnima(this.bodyfatwhywen, 400L);
      shuoxiao(Float.valueOf(1.0F));
      return;
    case 2131099779:
    }
    SharedPreferenceUtils.putValue(this, "fat_intro", this.app.getCurrentRole().getRole_id(), Boolean.valueOf(true));
    shuoxiao(Float.valueOf(0.0F));
    this.anim.showAnima(this.listView, 400L);
    this.anim.showAnima(this.bodyfatwhywen, 400L);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903225);
    this.app = ((MyApplication)getApplication().getApplicationContext());
    this.listView = ((ListView)findViewById(2131100084));
    this.view = LayoutInflater.from(this).inflate(2130903059, null);
    this.footview = LayoutInflater.from(this).inflate(2130903058, null);
    this.image = ((ImageView)findViewById(2131099698));
    this.listView.addHeaderView(this.view);
    this.listView.addFooterView(this.footview);
    this.anim = new AnimUtils(this);
    this.todayText = ((TextView)findViewById(2131100706));
    this.todayText.setTypeface(ModUtils.getTypeface(this));
    invit();
    String str1 = ReportDirect.getBodyFatExMessageByRole(this.app.getCurrentRole(), this.app.getTodayBody());
    String str2 = ReportDirect.getBodyFatExMessage2ByRole(this.app.getCurrentRole(), this.app.getTodayBody());
    this.fatReport = ReportDirect.judgeBodyFatByRole(this.app.getCurrentRole(), this.app.getTodayBody());
    this.contentStrings.add(this.fatReport);
    this.contentStrings.add(str1);
    if (!Age.isOld(this.app))
      this.contentStrings.add(str2);
    this.mMyAnimListAdapter = new TodayBodyFatAdapter(this, this.contentStrings);
    this.listView.setAdapter(this.mMyAnimListAdapter);
    boolean bool = ((Boolean)SharedPreferenceUtils.getValue(this, "fat_intro", this.app.getCurrentRole().getRole_id(), Boolean.class)).booleanValue();
    if (!Age.isOld(this.app))
    {
      if (bool)
      {
        shuoxiao(Float.valueOf(0.0F));
        return;
      }
      this.anim.missAnima(this.listView, 1L);
      this.anim.missAnima(this.bodyfatwhywen, 1L);
      return;
    }
    this.bodyfatTishi.setVisibility(8);
    this.bodyfatwhywen.setVisibility(8);
  }

  protected void onResume()
  {
    super.onResume();
    this.listView.invalidateViews();
  }

  public void shuoxiao(Float paramFloat)
  {
    LinearLayout localLinearLayout1 = this.bodyfatTishi;
    float[] arrayOfFloat1 = new float[1];
    arrayOfFloat1[0] = paramFloat.floatValue();
    ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(localLinearLayout1, "scaleX", arrayOfFloat1);
    localObjectAnimator1.setDuration(400L);
    localObjectAnimator1.start();
    LinearLayout localLinearLayout2 = this.bodyfatTishi;
    float[] arrayOfFloat2 = new float[1];
    arrayOfFloat2[0] = paramFloat.floatValue();
    ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(localLinearLayout2, "scaleY", arrayOfFloat2);
    localObjectAnimator2.setDuration(400L);
    localObjectAnimator2.start();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     TodayBodyFatAct
 * JD-Core Version:    0.6.2
 */
