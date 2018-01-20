package com.picooc;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.constant.ThemeConstant;
import com.picooc.utils.TypefaceUtils;

public class SeeFamilyPicAccount extends PicoocActivity
{
  private MyApplication app;
  private Intent intent;
  private ImageView line_1;
  private ImageView line_2;
  private RelativeLayout relativeLayout_bg;
  private RelativeLayout rl_email;
  private RelativeLayout rl_phone;
  private String str_email;
  private String str_phone;
  private ThemeConstant themeConstant;
  private TextView tv_email;
  private TextView tv_nicheng_value;
  private TextView tv_phone_value;
  private TextView tv_pic_nicheng;
  private TextView tv_picooc_account;
  private TextView tv_title_name;

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100636:
      this.intent = new Intent(this, ForgetAct.class);
      this.intent.putExtra("key", 77);
      startActivity(this.intent);
      overridePendingTransition(2130968596, 2130968595);
      return;
    case 2131100359:
    }
    finish();
    overridePendingTransition(2130968594, 2130968597);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903218);
    this.app = ((MyApplication)getApplicationContext());
    this.relativeLayout_bg = ((RelativeLayout)findViewById(2131100292));
    this.themeConstant = new ThemeConstant(this);
    this.themeConstant.setTheme(this.relativeLayout_bg);
    this.tv_title_name = ((TextView)findViewById(2131100628));
    this.tv_title_name.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.rl_email = ((RelativeLayout)findViewById(2131100629));
    this.rl_phone = ((RelativeLayout)findViewById(2131100631));
    this.tv_picooc_account = ((TextView)findViewById(2131100363));
    this.tv_email = ((TextView)findViewById(2131100360));
    this.tv_pic_nicheng = ((TextView)findViewById(2131100634));
    this.tv_nicheng_value = ((TextView)findViewById(2131100635));
    this.tv_phone_value = ((TextView)findViewById(2131100632));
    this.line_1 = ((ImageView)findViewById(2131100630));
    this.line_2 = ((ImageView)findViewById(2131100633));
    this.tv_picooc_account.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.tv_email.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.tv_pic_nicheng.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.tv_nicheng_value.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.tv_phone_value.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.tv_nicheng_value.setText(this.app.getCurrentRole().getName());
    this.str_email = this.app.getCurrentRole().getEmail();
    this.str_phone = this.app.getCurrentRole().getPhone_no();
    if ((this.str_phone != null) && (!this.str_phone.equals("")))
    {
      this.tv_phone_value.setText(this.str_phone);
      this.rl_email.setVisibility(8);
      this.line_1.setVisibility(8);
      return;
    }
    this.rl_phone.setVisibility(8);
    this.line_2.setVisibility(8);
    if ((this.str_email != null) && (!this.str_email.equals("")))
    {
      this.tv_email.setText(this.str_email);
      return;
    }
    this.rl_email.setVisibility(8);
    this.line_1.setVisibility(8);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    finish();
    overridePendingTransition(2130968594, 2130968597);
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SeeFamilyPicAccount
 * JD-Core Version:    0.6.2
 */
