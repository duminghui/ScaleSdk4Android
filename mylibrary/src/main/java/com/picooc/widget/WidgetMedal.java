package com.picooc.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.picooc.picoocenum.MedalEnum;

public class WidgetMedal extends LinearLayout
{
  Context context;
  ImageView imagview;
  LinearLayout li = null;
  TextView pop;
  TextView textContent;

  public WidgetMedal(final Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.li = ((LinearLayout)LayoutInflater.from(paramContext).inflate(2130903227, this));
    this.textContent = ((TextView)this.li.findViewById(2131100563));
    this.pop = ((TextView)this.li.findViewById(2131099892));
    this.imagview = ((ImageView)this.li.findViewById(2131100722));
    this.context = paramContext;
    this.li.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Toast.makeText(paramContext, "您点击了我", 1).show();
      }
    });
  }

  public void setItem(MedalEnum paramMedalEnum, Boolean paramBoolean, boolean paramBoolean1, int paramInt)
  {
    this.textContent.setText(paramMedalEnum.getName());
    switch ($SWITCH_TABLE$com$picooc$picoocenum$MedalEnum()[paramMedalEnum.ordinal()])
    {
    default:
      return;
    case 1:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837854, 2130837853);
      return;
    case 2:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837858, 2130837857);
      return;
    case 3:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837856, 2130837855);
      return;
    case 4:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837852, 2130837851);
      return;
    case 5:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837850, 2130837849);
      return;
    case 6:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837878, 2130837877);
      return;
    case 7:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837894, 2130837893);
      return;
    case 8:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837890, 2130837889);
      return;
    case 9:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837871, 2130837870);
      return;
    case 10:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837869, 2130837868);
      return;
    case 11:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837884, 2130837883);
      return;
    case 12:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837880, 2130837879);
      return;
    case 13:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837863, 2130837862);
      return;
    case 14:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837875, 2130837874);
      return;
    case 15:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837886, 2130837885);
      return;
    case 16:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837865, 2130837864);
      return;
    case 17:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837892, 2130837891);
      return;
    case 18:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837888, 2130837887);
      return;
    case 19:
      showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837873, 2130837872);
      return;
    case 20:
    }
    showItem(paramBoolean, Boolean.valueOf(paramBoolean1), paramInt, 2130837867, 2130837866);
  }

  public void showItem(Boolean paramBoolean1, Boolean paramBoolean2, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramBoolean1.booleanValue())
    {
      this.li.setVisibility(0);
      if (!paramBoolean2.booleanValue())
        break label78;
      this.imagview.setImageResource(paramInt2);
    }
    while (true)
    {
      if (paramInt1 <= 1)
        break label90;
      this.pop.setVisibility(0);
      this.pop.setText(paramInt1);
      return;
      this.li.setVisibility(8);
      break;
      label78: this.imagview.setImageResource(paramInt3);
    }
    label90: this.pop.setVisibility(8);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     WidgetMedal
 * JD-Core Version:    0.6.2
 */
