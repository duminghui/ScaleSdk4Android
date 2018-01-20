package com.picooc.widget.chart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.picooc.utils.DateUtils;
import com.picooc.utils.NumUtils;
import com.picooc.utils.TypefaceUtils;

public class GoHistoryPopView extends LinearLayout
{
  private GoHistoryListener goHistoryListener;
  private LinearLayout layout = (LinearLayout)LayoutInflater.from(getContext()).inflate(2130903078, this);
  private TextView timeText = (TextView)this.layout.findViewById(2131099855);
  private int weekFlag;
  private TextView weightText = (TextView)this.layout.findViewById(2131099856);

  public GoHistoryPopView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.timeText.setTypeface(TypefaceUtils.getTypeface(paramContext, null));
    this.weightText.setTypeface(TypefaceUtils.getTypeface(paramContext, null));
    ((Button)this.layout.findViewById(2131099857)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (GoHistoryPopView.this.goHistoryListener != null)
          GoHistoryPopView.this.goHistoryListener.onGoHistory(GoHistoryPopView.this.weekFlag);
      }
    });
  }

  public void refreshPop(double paramDouble, long paramLong)
  {
    this.weightText.setText(NumUtils.roundValue(paramDouble) + "kg");
    this.timeText.setText(DateUtils.changeTimeStampToFormatTime(paramLong, "MM月dd日"));
    this.weekFlag = DateUtils.getWeekFlagByTimestamp(paramLong);
  }

  public void setGoHistoryListener(GoHistoryListener paramGoHistoryListener)
  {
    this.goHistoryListener = paramGoHistoryListener;
  }

  public static abstract interface GoHistoryListener
  {
    public abstract void onGoHistory(int paramInt);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     GoHistoryPopView
 * JD-Core Version:    0.6.2
 */
