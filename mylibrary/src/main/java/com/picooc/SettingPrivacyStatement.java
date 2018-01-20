package com.picooc;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.picooc.constant.ThemeConstant;
import com.picooc.utils.TypefaceUtils;

public class SettingPrivacyStatement extends PicoocActivity
  implements View.OnClickListener
{
  private LinearLayout linearLayout_bg;
  private ThemeConstant themeConstant;

  public void finish()
  {
    super.finish();
  }

  public void invit()
  {
    this.linearLayout_bg = ((LinearLayout)findViewById(2131099739));
    this.themeConstant = new ThemeConstant(this);
    this.themeConstant.setTheme(this.linearLayout_bg);
    Typeface localTypeface = TypefaceUtils.getTypeface(this, null);
    ImageView localImageView1 = (ImageView)findViewById(2131099651);
    ImageView localImageView2 = (ImageView)findViewById(2131099650);
    localImageView1.setVisibility(8);
    localImageView2.setImageResource(2130838406);
    localImageView1.setOnClickListener(this);
    localImageView2.setOnClickListener(this);
    ((TextView)findViewById(2131099699)).setText("隐私声明");
    TextView localTextView1 = (TextView)findViewById(2131100294);
    localTextView1.setTypeface(localTypeface);
    localTextView1.setLineSpacing(3.4F, 1.2F);
    TextView localTextView2 = (TextView)findViewById(2131100298);
    localTextView2.setTypeface(localTypeface);
    localTextView2.setLineSpacing(3.4F, 1.4F);
    TextView localTextView3 = (TextView)findViewById(2131100299);
    localTextView3.setTypeface(localTypeface);
    localTextView3.setLineSpacing(3.4F, 1.4F);
    TextView localTextView4 = (TextView)findViewById(2131100643);
    localTextView4.setTypeface(localTypeface);
    localTextView4.setLineSpacing(3.4F, 1.4F);
    TextView localTextView5 = (TextView)findViewById(2131100644);
    localTextView5.setTypeface(localTypeface);
    localTextView5.setLineSpacing(3.4F, 1.4F);
    TextView localTextView6 = (TextView)findViewById(2131100645);
    localTextView6.setTypeface(localTypeface);
    localTextView6.setLineSpacing(3.4F, 1.4F);
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
    setContentView(2130903221);
    invit();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SettingPrivacyStatement
 * JD-Core Version:    0.6.2
 */
