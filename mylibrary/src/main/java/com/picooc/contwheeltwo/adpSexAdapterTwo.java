package com.picooc.contwheeltwo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.picooc.contwheel.adpAbstractWheelTextAdapter;
import org.achartengine.tools.ModUtils;

public class adpSexAdapterTwo extends adpAbstractWheelTextAdapter
{
  public static final int[] FLAGS = { 2130837963, 2130837963, 2130837963 };
  public static String[] SEXS = null;

  public adpSexAdapterTwo(Context paramContext, String[] paramArrayOfString)
  {
    super(paramContext, 2130903128, 0);
    SEXS = paramArrayOfString;
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

  protected void notifyDataChangedEvent()
  {
    super.notifyDataChangedEvent();
  }

  public void setSexs(String[] paramArrayOfString)
  {
    SEXS = paramArrayOfString;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     adpSexAdapterTwo
 * JD-Core Version:    0.6.2
 */
