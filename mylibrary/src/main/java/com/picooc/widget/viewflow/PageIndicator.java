package com.picooc.widget.viewflow;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public abstract interface PageIndicator extends ViewPager.OnPageChangeListener
{
  public abstract void notifyDataSetChanged();

  public abstract void setCurrentItem(int paramInt);

  public abstract void setOnPageChangeListener(ViewPager.OnPageChangeListener paramOnPageChangeListener);

  public abstract void setViewPager(ViewPager paramViewPager);

  public abstract void setViewPager(ViewPager paramViewPager, int paramInt);
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PageIndicator
 * JD-Core Version:    0.6.2
 */
