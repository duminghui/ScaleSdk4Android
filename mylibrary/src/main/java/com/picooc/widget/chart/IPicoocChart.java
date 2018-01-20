package com.picooc.widget.chart;

import android.content.Context;
import android.view.View;

public abstract interface IPicoocChart
{
  public static final String DESC = "desc";
  public static final String NAME = "name";

  public abstract View execute(Context paramContext);

  public abstract String getDesc();

  public abstract String getName();
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     IPicoocChart
 * JD-Core Version:    0.6.2
 */
