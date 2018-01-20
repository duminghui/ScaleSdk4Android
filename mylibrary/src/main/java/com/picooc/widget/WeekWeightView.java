package com.picooc.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.picooc.utils.TextUtils;

public class WeekWeightView extends LinearLayout
{
  private TextView day;
  private ImageView state;
  private TextView week;

  public WeekWeightView(Context paramContext)
  {
    this(paramContext, null);
  }

  public WeekWeightView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    LinearLayout localLinearLayout = (LinearLayout)LayoutInflater.from(getContext()).inflate(2130903171, this);
    this.week = ((TextView)localLinearLayout.findViewById(2131099861));
    this.day = ((TextView)localLinearLayout.findViewById(2131100276));
    this.state = ((ImageView)localLinearLayout.findViewById(2131099758));
    this.day.setTypeface(TextUtils.getTypeface(paramContext, null));
  }

  public void changeImageResource(int paramInt)
  {
    this.state.setImageResource(paramInt);
  }

  public void changeState(int paramInt)
  {
    this.state.setImageResource(paramInt);
  }

  public void init(String paramString1, String paramString2, int paramInt)
  {
    this.week.setText(paramString1);
    this.day.setText(paramString2);
    this.state.setImageResource(paramInt);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     WeekWeightView
 * JD-Core Version:    0.6.2
 */
