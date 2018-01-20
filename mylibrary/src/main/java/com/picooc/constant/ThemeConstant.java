package com.picooc.constant;

import android.content.Context;
import android.view.View;
import com.picooc.utils.SharedPreferenceUtils;

public class ThemeConstant
{
  public static final String settingTheme = "settingTheme";
  public static final Integer theme_blue = Integer.valueOf(2130837525);
  public static final Integer theme_pink = Integer.valueOf(2130837526);
  public static final Integer theme_purple = Integer.valueOf(2130837527);
  public Context context;
  private SharedPreferenceUtils sp;

  public ThemeConstant(Context paramContext)
  {
    this.context = paramContext;
    this.sp = new SharedPreferenceUtils();
  }

  public Integer getbgResource()
  {
    return (Integer)SharedPreferenceUtils.getValue(this.context, "settingTheme", "settingTheme", Integer.class);
  }

  public void setTheme(View paramView)
  {
    Integer localInteger = (Integer)SharedPreferenceUtils.getValue(this.context, "settingTheme", "settingTheme", Integer.class);
    if (localInteger.intValue() != 0)
      paramView.setBackgroundResource(localInteger.intValue());
  }

  public void setThemeLogo(View paramView)
  {
    Integer localInteger = (Integer)SharedPreferenceUtils.getValue(this.context, "settingTheme", "settingTheme", Integer.class);
    if (localInteger.intValue() == 0)
      return;
    paramView.setBackgroundResource(localInteger.intValue());
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ThemeConstant
 * JD-Core Version:    0.6.2
 */
