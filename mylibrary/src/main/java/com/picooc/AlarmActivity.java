package com.picooc;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.picooc.constant.ThemeConstant;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.AnimUtils.selectHeitListener;

import java.util.Arrays;

public class AlarmActivity extends PicoocActivity
  implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, AnimUtils.selectHeitListener
{
  public static final String ALARM = "alarm";
  public static final String ALARM_EVENING = "alarm_evening";
  public static final String ALARM_MIDDLE = "alarm_middle";
  public static final String ALARM_MORNING = "alarm_morning";
  View clickView;
  private LinearLayout linearLayout_bg;
  private SharedPreferences mAlarmSf;
  AnimUtils mAnim;
  private LinearLayout mLayoutMiddle;
  private LinearLayout mLayoutMorning;
  private LinearLayout mLayoutNight;
  private ToggleButton mTbMiddle;
  private ToggleButton mTbMorning;
  private ToggleButton mTbNight;
  private TextView mTvMiddle;
  private TextView mTvMorning;
  private TextView mTvNight;
  private PowerManager.WakeLock mWakelock;
  private ThemeConstant themeConstant;

  private void changeAlpha(LinearLayout paramLinearLayout, float paramFloat)
  {
    LinearLayout localLinearLayout = (LinearLayout)paramLinearLayout.getChildAt(0);
    ((TextView)localLinearLayout.getChildAt(0)).setAlpha(paramFloat);
    ((TextView)localLinearLayout.getChildAt(1)).setAlpha(paramFloat);
    paramLinearLayout.getChildAt(1).setAlpha(paramFloat - 0.2F);
  }

  private void doPopMissAnimation(View paramView, String paramString)
  {
    ViewGroup localViewGroup = (ViewGroup)((ViewGroup)paramView).getChildAt(0);
    TextView localTextView1 = (TextView)localViewGroup.getChildAt(0);
    TextView localTextView2 = (TextView)localViewGroup.getChildAt(1);
    localTextView2.setTextColor(-1);
    if ("".equals(paramString))
    {
      if (!((ToggleButton)localViewGroup.getChildAt(2)).isChecked())
      {
        localTextView1.setAlpha(0.5F);
        localTextView2.setAlpha(0.5F);
        ((ViewGroup)paramView).getChildAt(1).setAlpha(0.2F);
      }
      return;
    }
    localTextView1.setAlpha(1.0F);
    localTextView2.setAlpha(1.0F);
    localTextView2.setText(paramString);
    ((ViewGroup)paramView).getChildAt(1).setAlpha(0.7F);
    ToggleButton localToggleButton = (ToggleButton)localViewGroup.getChildAt(2);
    if (!localToggleButton.isChecked())
    {
      localToggleButton.setChecked(true);
      return;
    }
    onPopWindowChangeding(localToggleButton, true);
  }

  private void doPopShowAnimations(View paramView)
  {
    System.out.println("doPopShowAnimations=========================");
    ViewGroup localViewGroup = (ViewGroup)((ViewGroup)paramView).getChildAt(0);
    ((TextView)localViewGroup.getChildAt(0)).setAlpha(1.0F);
    TextView localTextView = (TextView)localViewGroup.getChildAt(1);
    localTextView.setAlpha(1.0F);
    localTextView.setTextColor(Color.parseColor("#7D83D6"));
    ((ViewGroup)paramView).getChildAt(1).setAlpha(0.7F);
  }

  private PendingIntent getBroadcast(int paramInt)
  {
    Intent localIntent = new Intent(this, AlarmReceiver.class);
    localIntent.putExtra("requestCode", paramInt);
    return PendingIntent.getBroadcast(this, paramInt, localIntent, 0);
  }

  private String[] getStringArrSpecial(int paramInt1, int paramInt2)
  {
    String[] arrayOfString = new String[1 + (paramInt2 + (24 - paramInt1))];
    int i = 0;
    int j = paramInt1;
    int k;
    if (j > 24)
    {
      k = 1;
      if (k > paramInt2)
      {
        System.out.println(Arrays.toString(arrayOfString));
        return arrayOfString;
      }
    }
    else
    {
      if (j >= 9);
      for (String str1 = j; ; str1 = "0" + j)
      {
        arrayOfString[i] = str1;
        i++;
        j++;
        break;
      }
    }
    int m = k;
    int n = k + (24 - paramInt1);
    if (m >= 9);
    for (String str2 = m; ; str2 = "0" + m)
    {
      arrayOfString[n] = str2;
      k++;
      break;
    }
  }

  private void initPopupWindow(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    LinearLayout localLinearLayout = (LinearLayout)LayoutInflater.from(this).inflate(2130903135, null);
    updateViews(localLinearLayout, paramInt1, paramInt2, paramInt3, paramInt4);
    PopupWindow localPopupWindow = new PopupWindow(localLinearLayout, -1, -2);
    localPopupWindow.setAnimationStyle(2131427336);
    localPopupWindow.setFocusable(true);
    localPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    localPopupWindow.setOutsideTouchable(true);
    localPopupWindow.update();
    setPopListener(localLinearLayout, localPopupWindow, paramView);
    localPopupWindow.showAtLocation(paramView, 85, 0, 0);
    doPopShowAnimations(paramView);
  }

  private void initPopupWindowSpe(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    LinearLayout localLinearLayout = (LinearLayout)LayoutInflater.from(this).inflate(2130903135, null);
    updateViewsSpe(localLinearLayout, paramInt1, paramInt2, paramInt3, paramInt4);
    PopupWindow localPopupWindow = new PopupWindow(localLinearLayout, -1, -2);
    localPopupWindow.setAnimationStyle(2131427336);
    localPopupWindow.setFocusable(true);
    localPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    localPopupWindow.setOutsideTouchable(true);
    localPopupWindow.update();
    setPopListener(localLinearLayout, localPopupWindow, paramView);
    localPopupWindow.showAtLocation(paramView, 85, 0, 0);
    doPopShowAnimations(paramView);
  }

  private void initTheme()
  {
    this.themeConstant = new ThemeConstant(this);
    this.linearLayout_bg = ((LinearLayout)findViewById(2131099739));
  }

  private void onPopWindowChangeding(View paramView, boolean paramBoolean)
  {
    Context localContext = getApplicationContext();
    if (paramBoolean);
    for (float f = 1.0F; ; f = 0.5F)
      switch (paramView.getId())
      {
      default:
        return;
      case 2131099743:
      case 2131099748:
      case 2131099753:
      }
    changeAlpha(this.mLayoutMorning, f);
    int k = "alarm_morning".hashCode();
    if (paramBoolean)
    {
      String str3 = this.mTvMorning.getText().toString().trim();
      this.mAlarmSf.edit().putString("alarm_morning", str3).commit();
      BootReceiver.setAlarm(localContext, str3, k);
      return;
    }
    this.mAlarmSf.edit().putString("alarm_morning", null).commit();
    BootReceiver.cancelAlarm(localContext, getBroadcast(k));
    return;
    changeAlpha(this.mLayoutMiddle, f);
    int j = "alarm_middle".hashCode();
    if (paramBoolean)
    {
      String str2 = this.mTvMiddle.getText().toString().trim();
      this.mAlarmSf.edit().putString("alarm_middle", str2).commit();
      BootReceiver.setAlarm(localContext, str2, j);
      return;
    }
    this.mAlarmSf.edit().putString("alarm_middle", null).commit();
    BootReceiver.cancelAlarm(localContext, getBroadcast(j));
    return;
    changeAlpha(this.mLayoutNight, f);
    int i = "alarm_evening".hashCode();
    if (paramBoolean)
    {
      String str1 = this.mTvNight.getText().toString().trim();
      this.mAlarmSf.edit().putString("alarm_evening", str1).commit();
      BootReceiver.setAlarm(localContext, str1, i);
      return;
    }
    this.mAlarmSf.edit().putString("alarm_evening", null).commit();
    BootReceiver.cancelAlarm(localContext, getBroadcast(i));
  }

  private void setPopListener(final LinearLayout paramLinearLayout, final PopupWindow paramPopupWindow, final View paramView)
  {
    ((Button)paramLinearLayout.findViewById(2131099778)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramPopupWindow.dismiss();
        AlarmActivity.this.doPopMissAnimation(paramView, "");
      }
    });
    ((Button)paramLinearLayout.findViewById(2131099779)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramPopupWindow.dismiss();
        NumberPicker localNumberPicker1 = (NumberPicker)paramLinearLayout.findViewById(2131100100);
        NumberPicker localNumberPicker2 = (NumberPicker)paramLinearLayout.findViewById(2131100101);
        int i = localNumberPicker1.getValue();
        int j = localNumberPicker2.getValue();
        String str1;
        if (i < 10)
        {
          str1 = "0" + i;
          if (j >= 10)
            break label154;
        }
        label154: for (String str2 = "0" + j; ; str2 = j)
        {
          AlarmActivity.this.doPopMissAnimation(paramView, str1 + ":" + str2);
          return;
          str1 = i;
          break;
        }
      }
    });
  }

  private void setTypeface()
  {
    Typeface localTypeface = TypefaceUtils.getTypeface(this, null);
    this.mTvMorning.setTypeface(localTypeface);
    this.mTvMiddle.setTypeface(localTypeface);
    this.mTvNight.setTypeface(localTypeface);
  }

  private void setupViews()
  {
    this.mTvMorning = ((TextView)findViewById(2131099742));
    this.mTvMiddle = ((TextView)findViewById(2131099747));
    this.mTvNight = ((TextView)findViewById(2131099752));
    this.mTbMorning = ((ToggleButton)findViewById(2131099743));
    this.mTbMorning.setOnCheckedChangeListener(this);
    this.mTbMiddle = ((ToggleButton)findViewById(2131099748));
    this.mTbMiddle.setOnCheckedChangeListener(this);
    this.mTbNight = ((ToggleButton)findViewById(2131099753));
    this.mTbNight.setOnCheckedChangeListener(this);
    this.mLayoutMorning = ((LinearLayout)findViewById(2131099740));
    this.mLayoutMorning.setOnClickListener(this);
    this.mLayoutMiddle = ((LinearLayout)findViewById(2131099745));
    this.mLayoutMiddle.setOnClickListener(this);
    this.mLayoutNight = ((LinearLayout)findViewById(2131099750));
    this.mLayoutNight.setOnClickListener(this);
    setTypeface();
    changeAlpha(this.mLayoutMorning, 0.5F);
    changeAlpha(this.mLayoutMiddle, 0.5F);
    changeAlpha(this.mLayoutNight, 0.5F);
  }

  private void updateViews(LinearLayout paramLinearLayout, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    NumberPicker localNumberPicker1 = (NumberPicker)paramLinearLayout.findViewById(2131100100);
    localNumberPicker1.setMinValue(paramInt1);
    localNumberPicker1.setMaxValue(paramInt2);
    localNumberPicker1.setValue(paramInt3);
    localNumberPicker1.setWrapSelectorWheel(false);
    NumberPicker localNumberPicker2 = (NumberPicker)paramLinearLayout.findViewById(2131100101);
    localNumberPicker2.setMinValue(0);
    localNumberPicker2.setMaxValue(59);
    localNumberPicker2.setValue(paramInt4);
    localNumberPicker2.setWrapSelectorWheel(false);
  }

  private void updateViewsFromSharedPreferences()
  {
    String str1 = this.mAlarmSf.getString("alarm_morning", null);
    String str2 = this.mAlarmSf.getString("alarm_middle", null);
    String str3 = this.mAlarmSf.getString("alarm_evening", null);
    if (str1 != null)
    {
      this.mTvMorning.setText(str1);
      this.mTbMorning.setChecked(true);
    }
    if (str2 != null)
    {
      this.mTvMiddle.setText(str2);
      this.mTbMiddle.setChecked(true);
    }
    if (str3 != null)
    {
      this.mTvNight.setText(str3);
      this.mTbNight.setChecked(true);
    }
  }

  private void updateViewsSpe(LinearLayout paramLinearLayout, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    NumberPicker localNumberPicker1 = (NumberPicker)paramLinearLayout.findViewById(2131100100);
    localNumberPicker1.setDisplayedValues(getStringArrSpecial(paramInt1, paramInt2));
    localNumberPicker1.setWrapSelectorWheel(false);
    NumberPicker localNumberPicker2 = (NumberPicker)paramLinearLayout.findViewById(2131100101);
    localNumberPicker2.setMinValue(0);
    localNumberPicker2.setMaxValue(59);
    localNumberPicker2.setValue(paramInt4);
    localNumberPicker2.setWrapSelectorWheel(false);
  }

  public void alarmCount(String paramString1, String paramString2)
  {
    int i = Integer.parseInt(paramString1);
    int j = Integer.parseInt(paramString2);
    String str1;
    if (i < 10)
    {
      str1 = "0" + i;
      if (j >= 10)
        break label115;
    }
    label115: for (String str2 = "0" + j; ; str2 = j)
    {
      doPopMissAnimation(this.clickView, str1 + ":" + str2);
      return;
      str1 = i;
      break;
    }
  }

  public void onBackClick(View paramView)
  {
    finish();
  }

  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    System.out.println("onCheckedChanged===" + paramCompoundButton.getId());
    onPopWindowChangeding(paramCompoundButton, paramBoolean);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131099740:
      this.clickView = paramView;
      String[] arrayOfString3 = this.mTvMorning.getText().toString().split(":");
      int n = Integer.parseInt(arrayOfString3[0]);
      int i1 = Integer.parseInt(arrayOfString3[1]);
      this.mAnim.getPopupWindowAlarm(1, n, i1);
      return;
    case 2131099745:
      this.clickView = paramView;
      String[] arrayOfString2 = this.mTvMiddle.getText().toString().split(":");
      int k = Integer.parseInt(arrayOfString2[0]);
      int m = Integer.parseInt(arrayOfString2[1]);
      this.mAnim.getPopupWindowAlarm(2, k, m);
      return;
    case 2131099750:
    }
    this.clickView = paramView;
    String[] arrayOfString1 = this.mTvNight.getText().toString().split(":");
    int i = Integer.parseInt(arrayOfString1[0]);
    int j = Integer.parseInt(arrayOfString1[1]);
    this.mAnim.getPopupWindowAlarm(3, i, j);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mAnim = new AnimUtils(this);
    Window localWindow = getWindow();
    localWindow.addFlags(4718592);
    localWindow.addFlags(2097280);
    setContentView(2130903057);
    this.mAlarmSf = getSharedPreferences("alarm", 0);
    setupViews();
    updateViewsFromSharedPreferences();
    this.mAnim.setoselectHeitListener(this);
    initTheme();
  }

  public void onPause()
  {
    super.onPause();
    if (this.mWakelock != null)
      this.mWakelock.release();
  }

  public void onResume()
  {
    super.onResume();
    this.themeConstant.setTheme(this.linearLayout_bg);
    this.mWakelock = ((PowerManager)getSystemService("power")).newWakeLock(268435482, "WakeLock");
    this.mWakelock.acquire();
  }

  public void selectFromPhone()
  {
  }

  public void selectHeit(int paramInt, String paramString1, String paramString2)
  {
  }

  public void selectbirthDay(String paramString)
  {
  }

  public void takePoto()
  {
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     AlarmActivity
 * JD-Core Version:    0.6.2
 */
