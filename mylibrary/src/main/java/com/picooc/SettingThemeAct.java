package com.picooc;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.picooc.constant.ThemeConstant;
import com.picooc.utils.SharedPreferenceUtils;

public class SettingThemeAct extends PicoocActivity
{
  private ImageView iv_blue_true;
  private ImageView iv_pink_true;
  private ImageView iv_purple_true;
  private LinearLayout linearLayout_bg;
  private ImageView right;
  private SharedPreferenceUtils sp;
  private ThemeConstant themeConstant;
  private TextView title;

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968594, 2130968597);
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
    case 2131100547:
      this.linearLayout_bg.setBackgroundResource(2130837525);
      SharedPreferenceUtils.putValue(this, "settingTheme", "settingTheme", ThemeConstant.theme_blue);
      this.iv_blue_true.setVisibility(0);
      this.iv_pink_true.setVisibility(8);
      this.iv_purple_true.setVisibility(8);
      return;
    case 2131100549:
      this.linearLayout_bg.setBackgroundResource(2130837527);
      SharedPreferenceUtils.putValue(this, "settingTheme", "settingTheme", ThemeConstant.theme_purple);
      this.iv_blue_true.setVisibility(8);
      this.iv_pink_true.setVisibility(8);
      this.iv_purple_true.setVisibility(0);
      return;
    case 2131100551:
    }
    this.linearLayout_bg.setBackgroundResource(2130837526);
    SharedPreferenceUtils.putValue(this, "settingTheme", "settingTheme", ThemeConstant.theme_pink);
    this.iv_pink_true.setVisibility(0);
    this.iv_blue_true.setVisibility(8);
    this.iv_purple_true.setVisibility(8);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903203);
    this.sp = new SharedPreferenceUtils();
    this.linearLayout_bg = ((LinearLayout)findViewById(2131099739));
    this.iv_blue_true = ((ImageView)findViewById(2131100548));
    this.iv_purple_true = ((ImageView)findViewById(2131100550));
    this.iv_pink_true = ((ImageView)findViewById(2131100552));
    this.themeConstant = new ThemeConstant(this);
    this.themeConstant.setTheme(this.linearLayout_bg);
    if (this.themeConstant.getbgResource().intValue() == 2130837526)
      this.iv_pink_true.setVisibility(0);
    while (true)
    {
      this.right = ((ImageView)findViewById(2131099651));
      this.right.setVisibility(8);
      this.title = ((TextView)findViewById(2131099699));
      this.title.setText(getString(2131361869));
      return;
      if (this.themeConstant.getbgResource().intValue() == 2130837527)
        this.iv_purple_true.setVisibility(0);
      else
        this.iv_blue_true.setVisibility(0);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SettingThemeAct
 * JD-Core Version:    0.6.2
 */
