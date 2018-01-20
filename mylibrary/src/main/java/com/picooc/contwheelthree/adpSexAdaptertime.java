package com.picooc.contwheelthree;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class adpSexAdaptertime extends adpAbstractWheelTextAdapter
{
  public static final int[] FLAGS = { 2130837963, 2130837963, 2130837963 };
  public static final String[] SEXS = { "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00" };

  public adpSexAdaptertime(Context paramContext)
  {
    super(paramContext, 2130903128, 0);
    setItemTextResource(2131100083);
  }

  public View getItem(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return super.getItem(paramInt, paramView, paramViewGroup);
  }

  protected CharSequence getItemText(int paramInt)
  {
    return SEXS[paramInt];
  }

  public int getItemsCount()
  {
    return SEXS.length;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     adpSexAdaptertime
 * JD-Core Version:    0.6.2
 */
