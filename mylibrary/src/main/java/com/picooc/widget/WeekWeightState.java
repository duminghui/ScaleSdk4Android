package com.picooc.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.picooc.utils.DateUtils;
import java.util.ArrayList;

public class WeekWeightState extends LinearLayout
{
  private WeekWeightView currentDate;
  private String currentDay;
  private ArrayList<WeekWeightView> lists;
  private Context mContext;

  public WeekWeightState(Context paramContext)
  {
    super(paramContext);
  }

  public WeekWeightState(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    LayoutInflater.from(getContext()).inflate(2130903170, this);
    this.lists = new ArrayList();
    this.currentDay = DateUtils.getCurrentTime("dd");
    String[] arrayOfString = DateUtils.getCurrentWeekDays("dd");
    WeekWeightView localWeekWeightView1 = (WeekWeightView)findViewById(2131100269);
    localWeekWeightView1.init("一", arrayOfString[0], 2130837584);
    this.lists.add(localWeekWeightView1);
    WeekWeightView localWeekWeightView2 = (WeekWeightView)findViewById(2131100270);
    localWeekWeightView2.init("二", arrayOfString[1], 2130837584);
    this.lists.add(localWeekWeightView2);
    WeekWeightView localWeekWeightView3 = (WeekWeightView)findViewById(2131100271);
    localWeekWeightView3.init("三", arrayOfString[2], 2130837584);
    this.lists.add(localWeekWeightView3);
    WeekWeightView localWeekWeightView4 = (WeekWeightView)findViewById(2131100272);
    localWeekWeightView4.init("四", arrayOfString[3], 2130837584);
    this.lists.add(localWeekWeightView4);
    WeekWeightView localWeekWeightView5 = (WeekWeightView)findViewById(2131100273);
    localWeekWeightView5.init("五", arrayOfString[4], 2130837584);
    this.lists.add(localWeekWeightView5);
    WeekWeightView localWeekWeightView6 = (WeekWeightView)findViewById(2131100274);
    localWeekWeightView6.init("六", arrayOfString[5], 2130837584);
    this.lists.add(localWeekWeightView6);
    WeekWeightView localWeekWeightView7 = (WeekWeightView)findViewById(2131100275);
    localWeekWeightView7.init("日", arrayOfString[6], 2130837584);
    this.lists.add(localWeekWeightView7);
  }

  public void changeCurrentDayState(int paramInt)
  {
    this.currentDate.changeState(paramInt);
  }

  public void init(double[] paramArrayOfDouble, long paramLong)
  {
    int i = DateUtils.getWeekendByTimestamp(System.currentTimeMillis());
    if (paramLong == 0L)
      for (int k = 1; ; k++)
      {
        if (k > i)
          return;
        ((WeekWeightView)this.lists.get(k - 1)).changeImageResource(2130837584);
      }
    long l = DateUtils.getWeekStartTimeAndEndTimeByFlag(0)[0];
    int j = 1;
    label61: if (j <= i)
    {
      if (paramArrayOfDouble[(j - 1)] <= 0.0D)
        break label104;
      ((WeekWeightView)this.lists.get(j - 1)).changeImageResource(2130837585);
    }
    while (true)
    {
      j++;
      break label61;
      break;
      label104: if (l + 86400000 * (j - 1) > paramLong)
        if (j != i)
          ((WeekWeightView)this.lists.get(j - 1)).changeImageResource(2130837586);
        else
          ((WeekWeightView)this.lists.get(j - 1)).changeImageResource(2130837584);
    }
  }

  public void refreshState(long paramLong)
  {
    int i = DateUtils.getWeekendByTimestamp(paramLong);
    ((WeekWeightView)this.lists.get(i - 1)).changeImageResource(2130837585);
  }

  public static enum WeekDay
  {
    static
    {
      THU = new WeekDay("THU", 3);
      FRI = new WeekDay("FRI", 4);
      SAT = new WeekDay("SAT", 5);
      SUN = new WeekDay("SUN", 6);
      WeekDay[] arrayOfWeekDay = new WeekDay[7];
      arrayOfWeekDay[0] = MON;
      arrayOfWeekDay[1] = TUE;
      arrayOfWeekDay[2] = WEN;
      arrayOfWeekDay[3] = THU;
      arrayOfWeekDay[4] = FRI;
      arrayOfWeekDay[5] = SAT;
      arrayOfWeekDay[6] = SUN;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     WeekWeightState
 * JD-Core Version:    0.6.2
 */
