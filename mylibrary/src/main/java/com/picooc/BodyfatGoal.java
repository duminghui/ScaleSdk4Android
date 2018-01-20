package com.picooc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.picooc.arithmetic.ReportDirect;
import com.picooc.constant.ThemeConstant;
import com.picooc.domain.BodyFatReport;
import com.picooc.utils.NumUtils;
import org.achartengine.tools.ModUtils;

public class BodyfatGoal extends PicoocActivity
  implements View.OnClickListener
{
  private TextView TextCurrentBodyfat;
  private TextView TextCurrentWeight;
  private LinearLayout add_linearLayout;
  private MyApplication app;
  private TextView b_mubiaoText;
  LinearLayout b_mubiaokuang;
  private LinearLayout ba_linearloayout;
  private TextView ba_mubiaozhifangText;
  private TextView ba_tzhichaobiaoText;
  private BodyFatReport bodyFatReport;
  private TextView bodyfatbootomText;
  private ImageView image;
  private LinearLayout left;
  private LinearLayout m;
  private TextView mubiaotext;
  private TextView picooc;
  private LinearLayout right;
  private RadioGroup swicht;
  private TextView textState;
  private TextView textchange;
  private TextView titelMiaoshu;
  private TextView upText;
  private TextView xiajiangText;

  public void finish()
  {
    super.finish();
  }

  public void invit()
  {
    new ThemeConstant(this).setTheme(findViewById(2131100278));
    this.TextCurrentBodyfat = ((TextView)findViewById(2131100399));
    this.TextCurrentBodyfat.setText("当前脂肪率为" + NumUtils.roundValue(this.app.getTodayBody().getBodyFat()) + "%");
    this.textState = ((TextView)findViewById(2131099730));
    switch (this.bodyFatReport.getRealFatState())
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      this.textchange = ((TextView)findViewById(2131100409));
      this.add_linearLayout = ((LinearLayout)findViewById(2131100400));
      this.titelMiaoshu = ((TextView)findViewById(2131100401));
      this.xiajiangText = ((TextView)findViewById(2131100404));
      this.bodyfatbootomText = ((TextView)findViewById(2131100403));
      this.upText = ((TextView)findViewById(2131100405));
      this.mubiaotext = ((TextView)findViewById(2131100407));
      this.picooc = ((TextView)findViewById(2131099854));
      this.image = ((ImageView)findViewById(2131099698));
      this.TextCurrentWeight = ((TextView)findViewById(2131100406));
      this.left = ((LinearLayout)findViewById(2131099650));
      this.right = ((LinearLayout)findViewById(2131099651));
      this.b_mubiaokuang = ((LinearLayout)findViewById(2131100408));
      this.b_mubiaoText = ((TextView)findViewById(2131100410));
      this.ba_linearloayout = ((LinearLayout)findViewById(2131100411));
      this.ba_tzhichaobiaoText = ((TextView)findViewById(2131100413));
      this.ba_mubiaozhifangText = ((TextView)findViewById(2131100418));
      this.swicht = ((RadioGroup)findViewById(2131100414));
      this.TextCurrentBodyfat.setTypeface(ModUtils.getTypeface(this));
      this.textState.setTypeface(ModUtils.getTypeface(this));
      this.titelMiaoshu.setTypeface(ModUtils.getTypeface(this));
      this.xiajiangText.setTypeface(ModUtils.getTypeface(this));
      this.bodyfatbootomText.setTypeface(ModUtils.getTypeface(this));
      this.upText.setTypeface(ModUtils.getTypeface(this));
      this.mubiaotext.setTypeface(ModUtils.getTypeface(this));
      this.picooc.setTypeface(ModUtils.getTypeface(this));
      this.textchange.setTypeface(ModUtils.getTypeface(this));
      this.b_mubiaoText.setTypeface(ModUtils.getTypeface(this));
      if (this.app.getCurrentRole().getWeight_change_target() != 0.0F)
        break;
      this.b_mubiaokuang.setVisibility(0);
      this.textchange.setText("您的目标脂肪率为");
      this.b_mubiaoText.setText(this.app.getTodayBody().getBodyFat() + "%");
      this.textchange.setTextSize(2, 16.0F);
      this.b_mubiaoText.setTextSize(2, 22.0F);
      this.add_linearLayout.setVisibility(8);
      this.xiajiangText.setVisibility(8);
      this.upText.setVisibility(8);
      this.mubiaotext.setVisibility(8);
      this.xiajiangText.setVisibility(8);
      this.picooc.setVisibility(8);
      this.image.setVisibility(8);
      this.TextCurrentWeight.setVisibility(8);
      this.bodyfatbootomText.setVisibility(8);
      this.left.setVisibility(8);
      this.right.setVisibility(8);
      this.m = ((LinearLayout)findViewById(2131100402));
      this.m.setVisibility(8);
      return;
      this.textState.setText(" 瘦 ");
      this.textState.setBackgroundResource(2130838208);
      continue;
      this.textState.setBackgroundResource(2130838203);
      this.textState.setText(" 偏瘦 ");
      continue;
      this.textState.setBackgroundResource(2130838179);
      this.textState.setText(" 标准 ");
      continue;
      this.textState.setBackgroundResource(2130838201);
      this.textState.setText(" 偏胖 ");
      continue;
      this.textState.setBackgroundResource(2130838201);
      this.textState.setText(" 肥胖 ");
    }
    String str = "";
    if (this.app.getCurrentRole().getWeight_change_target() > 0.0F)
    {
      this.image.setImageResource(2130838681);
      str = "在您增重" + NumUtils.roundValue(this.app.getCurrentRole().getWeight_change_target()) + "kg的过程中,脂肪率的变化";
    }
    while (true)
    {
      this.titelMiaoshu.setText(str);
      this.xiajiangText.setText(NumUtils.roundValue(this.bodyFatReport.getGoalFatRace()) + "%");
      this.upText.setText(NumUtils.roundValue(3.0F + this.app.getTodayBody().getBodyFat()) + "%");
      this.mubiaotext.setText(" " + NumUtils.roundValue(this.bodyFatReport.getGoalFatRace()) + "% ");
      this.bodyfatbootomText.setText(NumUtils.roundValue(this.app.getTodayBody().getBodyFat()) + "%");
      if (this.bodyFatReport.getGoalFatRace() <= this.app.getTodayBody().getBodyFat())
        break;
      this.xiajiangText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 2130838684, 0);
      return;
      if (this.app.getCurrentRole().getWeight_change_target() < 0.0F)
        str = "在您减重" + NumUtils.roundValue(-this.app.getCurrentRole().getWeight_change_target()) + "kg的过程中,脂肪率的变化";
    }
    this.xiajiangText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 2130838685, 0);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100331:
      finish();
      return;
    case 2131100420:
    }
    this.app.getCurrentRole().setGoal_fat(this.bodyFatReport.getGoalFatRace());
    Intent localIntent = new Intent();
    localIntent.setAction("com.picooc.latin.setting.goal.weight");
    sendBroadcast(localIntent);
    setResult(101);
    super.finish();
    overridePendingTransition(2130968576, 2130968581);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903181);
    this.app = ((MyApplication)getApplicationContext());
    this.bodyFatReport = ReportDirect.calculateIdealBodyFat(this.app.getCurrentRole(), this.app.getTodayBody(), true);
    if (this.app.getCurrentRole().getWeight_change_target() == 0.0F)
      this.bodyFatReport.setGoalFatRace(this.app.getTodayBody().getBodyFat());
    invit();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BodyfatGoal
 * JD-Core Version:    0.6.2
 */
