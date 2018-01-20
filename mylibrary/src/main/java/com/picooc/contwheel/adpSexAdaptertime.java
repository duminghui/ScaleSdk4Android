package com.picooc.contwheel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.achartengine.tools.ModUtils;

public class adpSexAdaptertime extends adpAbstractWheelTextAdapter
{
  public static final int[] FLAGS = { 2130837963, 2130837963, 2130837963 };
  public static final String[] SEXS = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };

  public adpSexAdaptertime(Context paramContext)
  {
    super(paramContext, 2130903128, 0);
    setItemTextResource(2131100083);
  }

  public View getItem(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = super.getItem(paramInt, paramView, paramViewGroup);
    TextView localTextView = (TextView)localView.findViewById(2131100083);
    localTextView.setTypeface(ModUtils.getTypeface(this.context));
    localTextView.setTextSize(23.0F);
    return localView;
  }

  public CharSequence getItemText(int paramInt)
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
