package com.picooc.widget.listview;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Utility
{
  public static void setListViewHeightBasedOnChildren(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null)
      return;
    int i = 0;
    for (int j = 0; ; j++)
    {
      if (j >= localListAdapter.getCount())
      {
        ViewGroup.LayoutParams localLayoutParams = paramListView.getLayoutParams();
        localLayoutParams.height = (i + paramListView.getDividerHeight() * (-1 + localListAdapter.getCount()));
        paramListView.setLayoutParams(localLayoutParams);
        return;
      }
      View localView = localListAdapter.getView(j, null, paramListView);
      localView.measure(0, 0);
      i += localView.getMeasuredHeight();
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     Utility
 * JD-Core Version:    0.6.2
 */
