package com.picooc.contwheelheight;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.picooc.contwheel.adpAbstractWheelTextAdapter;
import java.util.ArrayList;
import java.util.List;
import org.achartengine.tools.ModUtils;

public class adpSexAdapterHeight extends adpAbstractWheelTextAdapter
{
  public static final int[] FLAGS = { 2130837963, 2130837963, 2130837963 };
  public static String[] SEXS;
  int key = 2;
  private List<Object> list = new ArrayList();
  private Context mContext;
  int max = 100;
  int mini = 0;

  public adpSexAdapterHeight(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    super(paramContext, 2130903128, 0);
    setItemTextResource(2131100083);
    this.mContext = paramContext;
    this.mini = paramInt2;
    this.max = paramInt3;
    int j;
    int k;
    if (paramInt1 == 1)
    {
      j = paramInt2 - 1;
      k = 0;
      if (k < paramInt3);
    }
    while (true)
    {
      return;
      if (j < paramInt3)
      {
        j++;
        this.list.add(Integer.valueOf(j));
        k++;
        break;
        float f = paramInt2 - 1;
        for (int i = 0; (i < paramInt3) && (f != paramInt3); i++)
        {
          f += 0.5F;
          this.list.add(Float.valueOf(f));
        }
      }
    }
  }

  public View getItem(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = super.getItem(paramInt, paramView, paramViewGroup);
    TextView localTextView = (TextView)localView.findViewById(2131100083);
    localTextView.setTypeface(ModUtils.getTypeface(this.mContext));
    localTextView.setTextSize(25.0F);
    return localView;
  }

  public CharSequence getItemText(int paramInt)
  {
    return this.list.get(paramInt);
  }

  public int getItemsCount()
  {
    return this.list.size();
  }

  protected void notifyDataChangedEvent()
  {
    super.notifyDataChangedEvent();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     adpSexAdapterHeight
 * JD-Core Version:    0.6.2
 */
