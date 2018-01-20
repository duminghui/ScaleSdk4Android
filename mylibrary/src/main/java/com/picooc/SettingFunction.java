package com.picooc;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.picooc.constant.ThemeConstant;
import org.achartengine.tools.ModUtils;

public class SettingFunction extends PicoocActivity
  implements View.OnClickListener
{
  private LinearLayout linearLayout_bg;
  private ThemeConstant themeConstant;

  private String getVersionName()
    throws Exception
  {
    return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
  }

  public void finish()
  {
    super.finish();
  }

  public void invit()
    throws Exception
  {
    this.linearLayout_bg = ((LinearLayout)findViewById(2131099739));
    this.themeConstant = new ThemeConstant(this);
    this.themeConstant.setTheme(this.linearLayout_bg);
    Typeface localTypeface = ModUtils.getTypeface(this);
    ImageView localImageView1 = (ImageView)findViewById(2131099651);
    ImageView localImageView2 = (ImageView)findViewById(2131099650);
    localImageView1.setVisibility(8);
    localImageView1.setOnClickListener(this);
    localImageView2.setImageResource(2130838406);
    localImageView2.setOnClickListener(this);
    ((TextView)findViewById(2131099699)).setText("功能介绍");
    TextView localTextView = (TextView)findViewById(2131100294);
    localTextView.setTypeface(localTypeface);
    String str = getVersionName();
    localTextView.setText("PICOOC for 安卓 " + str + "版4大功能介绍");
    ((TextView)findViewById(2131100298)).setTypeface(localTypeface);
    ((TextView)findViewById(2131100299)).setTypeface(localTypeface);
    ((TextView)findViewById(2131100643)).setTypeface(localTypeface);
    ((TextView)findViewById(2131100644)).setTypeface(localTypeface);
    ((TextView)findViewById(2131100645)).setTypeface(localTypeface);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131099651:
    default:
      return;
    case 2131099650:
    }
    finish();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903220);
    ((TextView)findViewById(2131099808)).setTypeface(ModUtils.getTypeface(this));
    try
    {
      invit();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SettingFunction
 * JD-Core Version:    0.6.2
 */
