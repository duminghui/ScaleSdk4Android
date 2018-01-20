package com.picooc;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.constant.ThemeConstant;
import com.picooc.utils.TypefaceUtils;

public class LeftAboutPicooc extends PicoocActivity
  implements View.OnClickListener
{
  Intent intent;
  private RelativeLayout relativeLayout_bg;
  private ThemeConstant themeConstant;

  private String getVersionName()
    throws Exception
  {
    String str = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
    if ((str == null) || (str.length() <= 0))
      str = "";
    return str;
  }

  public void finish()
  {
    super.finish();
  }

  public void invit()
  {
    this.relativeLayout_bg = ((RelativeLayout)findViewById(2131100292));
    this.themeConstant = new ThemeConstant(this);
    this.themeConstant.setTheme(this.relativeLayout_bg);
    Typeface localTypeface = TypefaceUtils.getTypeface(this, null);
    ImageView localImageView1 = (ImageView)findViewById(2131099651);
    ImageView localImageView2 = (ImageView)findViewById(2131099650);
    localImageView1.setVisibility(8);
    localImageView1.setOnClickListener(this);
    localImageView2.setOnClickListener(this);
    localImageView2.setImageResource(2130838406);
    TextView localTextView1 = (TextView)findViewById(2131099699);
    localTextView1.setText("关于PICOOC");
    localTextView1.setTypeface(localTypeface);
    TextView localTextView2 = (TextView)findViewById(2131100294);
    localTextView2.setTypeface(localTypeface);
    try
    {
      localTextView2.setText("PICOOC " + "V " + getVersionName());
      ((TextView)findViewById(2131100298)).setTypeface(localTypeface);
      ((TextView)findViewById(2131100299)).setTypeface(localTypeface);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100283:
      this.intent = new Intent(this, SettingFunction.class);
      startActivity(this.intent);
      return;
    case 2131100284:
      this.intent = new Intent(this, SettingPrivacyStatement.class);
      startActivity(this.intent);
      return;
    case 2131100296:
      this.intent = new Intent(this, PicoocWebViewAct.class);
      this.intent.putExtra("indexURL", 1);
      startActivity(this.intent);
      overridePendingTransition(2130968580, 2130968577);
      return;
    case 2131099650:
      finish();
      return;
    case 2131100297:
    }
    this.intent = new Intent(this, PicoocWebViewAct.class);
    this.intent.putExtra("indexURL", 2);
    startActivity(this.intent);
    overridePendingTransition(2130968580, 2130968577);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903174);
    invit();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LeftAboutPicooc
 * JD-Core Version:    0.6.2
 */
