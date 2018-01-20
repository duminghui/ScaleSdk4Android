package com.picooc.contwheelthree;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class adpSexAdapter extends adpAbstractWheelTextAdapter
{
  public static final int[] FLAGS = { 2130837963, 2130837963, 2130837963 };
  public static final String[] SEXS = { "美女", "帅哥", "人妖" };
  public List<Map<String, String>> list;

  public adpSexAdapter(Context paramContext, List<Map<String, String>> paramList)
  {
    super(paramContext, 2130903127, 0);
    setItemTextResource(2131100083);
    this.list = paramList;
  }

  public static String dateToyyyymm(Date paramDate)
  {
    if (paramDate != null)
      return new SimpleDateFormat("MM��dd��").format(paramDate);
    return null;
  }

  public static Date getDateAfter(Date paramDate, int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(paramDate);
    localCalendar.set(5, paramInt + localCalendar.get(5));
    return localCalendar.getTime();
  }

  public View getItem(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return super.getItem(paramInt, paramView, paramViewGroup);
  }

  protected CharSequence getItemText(int paramInt)
  {
    return (CharSequence)((Map)this.list.get(paramInt)).get("name");
  }

  public int getItemsCount()
  {
    return this.list.size();
  }

  public List<Map<String, String>> getList()
  {
    return this.list;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     adpSexAdapter
 * JD-Core Version:    0.6.2
 */
