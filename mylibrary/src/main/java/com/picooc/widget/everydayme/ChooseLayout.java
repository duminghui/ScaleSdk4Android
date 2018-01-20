package com.picooc.widget.everydayme;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.picooc.constant.ThemeConstant;
import java.util.ArrayList;

public class ChooseLayout extends LinearLayout
{
  private Context context;
  private CheckBox drink;
  private RadioButton happy;
  private RadioGroup happy_or_sad;
  private Panel panel;
  private LinearLayout panelContent;
  private CheckBox physiology;
  private RadioButton sad;
  private CheckBox sport;
  private ThemeConstant themeConstant;
  private CheckBox vegetable;

  public ChooseLayout(Context paramContext)
  {
    this(paramContext, null);
    invalidate();
  }

  public ChooseLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2130903060, this);
    this.panelContent = ((LinearLayout)findViewById(2131099657));
    this.panel = ((Panel)findViewById(2131099760));
    this.happy_or_sad = ((RadioGroup)findViewById(2131099762));
    this.happy = ((RadioButton)findViewById(2131099763));
    this.sad = ((RadioButton)findViewById(2131099764));
    this.physiology = ((CheckBox)findViewById(2131099768));
    this.drink = ((CheckBox)findViewById(2131099769));
    this.sport = ((CheckBox)findViewById(2131099770));
    this.vegetable = ((CheckBox)findViewById(2131099771));
  }

  public void closeChooseLayout()
  {
    this.panel.setOpen(false, false);
  }

  public ArrayList<Integer> getCheckedState()
  {
    ArrayList localArrayList = new ArrayList();
    int i = this.happy_or_sad.getCheckedRadioButtonId();
    if (i == 2131099763)
      localArrayList.add(Integer.valueOf(1));
    while (true)
    {
      if (this.physiology.isChecked())
        localArrayList.add(Integer.valueOf(3));
      if (this.drink.isChecked())
        localArrayList.add(Integer.valueOf(4));
      if (this.sport.isChecked())
        localArrayList.add(Integer.valueOf(5));
      if (this.vegetable.isChecked())
        localArrayList.add(Integer.valueOf(6));
      return localArrayList;
      if (i == 2131099764)
        localArrayList.add(Integer.valueOf(2));
    }
  }

  public boolean isOpened()
  {
    return this.panel.isOpen();
  }

  public void setChooseTheme()
  {
    this.themeConstant = new ThemeConstant(this.context);
    if (this.themeConstant.getbgResource().intValue() == 2130837526)
    {
      this.panelContent.setBackgroundResource(2130838162);
      return;
    }
    if (this.themeConstant.getbgResource().intValue() == 2130837527)
    {
      this.panelContent.setBackgroundResource(2130838163);
      return;
    }
    this.panelContent.setBackgroundResource(2130838160);
  }

  public void showChooseLayout()
  {
    setChooseTheme();
    Panel localPanel = this.panel;
    if (this.panel.isOpen());
    for (boolean bool = false; ; bool = true)
    {
      localPanel.setOpen(bool, true);
      return;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ChooseLayout
 * JD-Core Version:    0.6.2
 */
