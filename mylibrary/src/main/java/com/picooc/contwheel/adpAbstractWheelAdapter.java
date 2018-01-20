package com.picooc.contwheel;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class adpAbstractWheelAdapter
  implements CotrlWheelViewAdapter
{
  private List<DataSetObserver> datasetObservers;

  public View getEmptyItem(View paramView, ViewGroup paramViewGroup)
  {
    return null;
  }

  protected void notifyDataChangedEvent()
  {
    Iterator localIterator;
    if (this.datasetObservers != null)
      localIterator = this.datasetObservers.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((DataSetObserver)localIterator.next()).onChanged();
    }
  }

  protected void notifyDataInvalidatedEvent()
  {
    Iterator localIterator;
    if (this.datasetObservers != null)
      localIterator = this.datasetObservers.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((DataSetObserver)localIterator.next()).onInvalidated();
    }
  }

  public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    if (this.datasetObservers == null)
      this.datasetObservers = new LinkedList();
    this.datasetObservers.add(paramDataSetObserver);
  }

  public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    if (this.datasetObservers != null)
      this.datasetObservers.remove(paramDataSetObserver);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     adpAbstractWheelAdapter
 * JD-Core Version:    0.6.2
 */
