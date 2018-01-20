package com.picooc.contwheel;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

public abstract interface CotrlWheelViewAdapter
{
  public abstract View getEmptyItem(View paramView, ViewGroup paramViewGroup);

  public abstract View getItem(int paramInt, View paramView, ViewGroup paramViewGroup);

  public abstract int getItemsCount();

  public abstract void registerDataSetObserver(DataSetObserver paramDataSetObserver);

  public abstract void unregisterDataSetObserver(DataSetObserver paramDataSetObserver);
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     CotrlWheelViewAdapter
 * JD-Core Version:    0.6.2
 */
