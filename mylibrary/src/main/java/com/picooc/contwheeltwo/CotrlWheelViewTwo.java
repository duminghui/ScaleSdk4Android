package com.picooc.contwheeltwo;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import com.picooc.contwheel.CotrlItemsRange;
import com.picooc.contwheel.CotrlWheelScroller;
import com.picooc.contwheel.CotrlWheelScroller.ScrollingListener;
import com.picooc.contwheel.CotrlWheelViewAdapter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CotrlWheelViewTwo extends View
{
  private static final int DEF_VISIBLE_ITEMS = 5;
  private static final int ITEM_OFFSET_PERCENT = 10;
  private static final int PADDING = 10;
  private static final int[] SHADOWS_COLORS = { -15658735, 11184810, 11184810 };
  public static int key = 0;
  private GradientDrawable bottomShadow;
  private Drawable centerDrawable;
  private List<CotrlOnWheelChangedListener> changingListeners = new LinkedList();
  private List<CotrlOnWheelClickedListener> clickingListeners = new LinkedList();
  private int currentItem = 0;
  private DataSetObserver dataObserver = new DataSetObserver()
  {
    public void onChanged()
    {
      CotrlWheelViewTwo.this.invalidateWheel(false);
    }

    public void onInvalidated()
    {
      CotrlWheelViewTwo.this.invalidateWheel(true);
    }
  };
  private int firstItem;
  boolean isCyclic = false;
  private boolean isScrollingPerformed;
  private int itemHeight = 0;
  private LinearLayout itemsLayout;
  private CotrlWheelRecycletwo recycle = new CotrlWheelRecycletwo(this);
  private CotrlWheelScroller scroller;
  ScrollingListener scrollingListener = new ScrollingListener()
  {
    public void onFinished()
    {
      if (CotrlWheelViewTwo.this.isScrollingPerformed)
      {
        CotrlWheelViewTwo.this.notifyScrollingListenersAboutEnd();
        CotrlWheelViewTwo.this.isScrollingPerformed = false;
      }
      CotrlWheelViewTwo.this.scrollingOffset = 0;
      CotrlWheelViewTwo.this.invalidate();
    }

    public void onJustify()
    {
      if (Math.abs(CotrlWheelViewTwo.this.scrollingOffset) > 1)
        CotrlWheelViewTwo.this.scroller.scroll(CotrlWheelViewTwo.this.scrollingOffset, 0);
    }

    public void onScroll(int paramAnonymousInt)
    {
      CotrlWheelViewTwo.this.doScroll(paramAnonymousInt);
      if (CotrlWheelViewTwo.this.scrollingOffset > 50)
      {
        CotrlWheelViewTwo.this.scrollingOffset = 50;
        CotrlWheelViewTwo.this.scroller.stopScrolling();
      }
      while (CotrlWheelViewTwo.this.scrollingOffset >= -50)
        return;
      CotrlWheelViewTwo.this.scrollingOffset = (-50);
      CotrlWheelViewTwo.this.scroller.stopScrolling();
    }

    public void onStarted()
    {
      CotrlWheelViewTwo.this.isScrollingPerformed = true;
      CotrlWheelViewTwo.this.notifyScrollingListenersAboutStart();
    }
  };
  private List<CotrlOnWheelScrollListener> scrollingListeners = new LinkedList();
  private int scrollingOffset;
  private GradientDrawable topShadow;
  private CotrlWheelViewAdapter viewAdapter;
  private int visibleItems = 5;

  public CotrlWheelViewTwo(Context paramContext)
  {
    super(paramContext);
    initData(paramContext);
  }

  public CotrlWheelViewTwo(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initData(paramContext);
  }

  public CotrlWheelViewTwo(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initData(paramContext);
  }

  private boolean addViewItem(int paramInt, boolean paramBoolean)
  {
    View localView = getItemView(paramInt);
    boolean bool = false;
    if (localView != null)
    {
      if (!paramBoolean)
        break label32;
      this.itemsLayout.addView(localView, 0);
    }
    while (true)
    {
      bool = true;
      return bool;
      label32: this.itemsLayout.addView(localView);
    }
  }

  private void buildViewForMeasuring()
  {
    int i;
    if (this.itemsLayout != null)
    {
      this.recycle.recycleItems(this.itemsLayout, this.firstItem, new CotrlItemsRange());
      i = this.visibleItems / 2;
    }
    for (int j = i + this.currentItem; ; j--)
    {
      if (j < this.currentItem - i)
      {
        return;
        createItemsLayout();
        break;
      }
      if (addViewItem(j, true))
        this.firstItem = j;
    }
  }

  private int calculateLayoutWidth(int paramInt1, int paramInt2)
  {
    initResourcesIfNecessary();
    this.itemsLayout.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    this.itemsLayout.measure(View.MeasureSpec.makeMeasureSpec(paramInt1, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
    int i = this.itemsLayout.getMeasuredWidth();
    if (paramInt2 == 1073741824);
    for (int j = paramInt1; ; j = paramInt1)
      do
      {
        this.itemsLayout.measure(View.MeasureSpec.makeMeasureSpec(j - 20, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
        return j;
        j = Math.max(i + 20, getSuggestedMinimumWidth());
      }
      while ((paramInt2 != -2147483648) || (paramInt1 >= j));
  }

  private void createItemsLayout()
  {
    if (this.itemsLayout == null)
    {
      this.itemsLayout = new LinearLayout(getContext());
      this.itemsLayout.setOrientation(1);
    }
  }

  private void doScroll(int paramInt)
  {
    this.scrollingOffset = (paramInt + this.scrollingOffset);
    int i = getItemHeight();
    int j = this.scrollingOffset / i;
    int k = this.currentItem - j;
    int m = this.viewAdapter.getItemsCount();
    int n = this.scrollingOffset % i;
    if (Math.abs(n) <= i / 2)
      n = 0;
    label98: int i1;
    if ((this.isCyclic) && (m > 0))
      if (n > 0)
      {
        k--;
        j++;
        if (k < 0)
          break label174;
        k %= m;
        i1 = this.scrollingOffset;
        if (k == this.currentItem)
          break label268;
        setCurrentItem(k, false);
      }
    while (true)
    {
      this.scrollingOffset = (i1 - j * i);
      if (this.scrollingOffset > getHeight())
        this.scrollingOffset = (this.scrollingOffset % getHeight() + getHeight());
      return;
      if (n >= 0)
        break;
      k++;
      j--;
      break;
      label174: k += m;
      break;
      if (k < 0)
      {
        j = this.currentItem;
        k = 0;
        break label98;
      }
      if (k >= m)
      {
        j = 1 + (this.currentItem - m);
        k = m - 1;
        break label98;
      }
      if ((k > 0) && (n > 0))
      {
        k--;
        j++;
        break label98;
      }
      if ((k >= m - 1) || (n >= 0))
        break label98;
      k++;
      j--;
      break label98;
      label268: invalidate();
    }
  }

  private void drawCenterRect(Canvas paramCanvas)
  {
    int i = getHeight() / 2;
    int j = (int)(1.2D * (getItemHeight() / 2));
    this.centerDrawable.setBounds(0, i - j, getWidth(), i + j);
    Paint localPaint = new Paint();
    localPaint.setColor(-1);
    localPaint.setStrokeWidth(1.0F);
    localPaint.setTextSize(getResources().getDimensionPixelSize(2131230802));
    localPaint.setStyle(Paint.Style.FILL);
    localPaint.setFakeBoldText(true);
    this.centerDrawable.draw(paramCanvas);
  }

  private void drawItems(Canvas paramCanvas)
  {
    paramCanvas.save();
    paramCanvas.translate(10.0F, -((this.currentItem - this.firstItem) * getItemHeight() + (getItemHeight() - getHeight()) / 2) + this.scrollingOffset);
    this.itemsLayout.draw(paramCanvas);
    paramCanvas.restore();
  }

  private void drawShadows(Canvas paramCanvas)
  {
    int i = (int)(1.5D * getItemHeight());
    this.topShadow.setBounds(0, 0, getWidth(), i);
    this.bottomShadow.setBounds(0, getHeight() - i, getWidth(), getHeight());
  }

  private int getDesiredHeight(LinearLayout paramLinearLayout)
  {
    if ((paramLinearLayout != null) && (paramLinearLayout.getChildAt(0) != null))
      this.itemHeight = paramLinearLayout.getChildAt(0).getMeasuredHeight();
    return Math.max(this.itemHeight * this.visibleItems - 10 * this.itemHeight / 50, getSuggestedMinimumHeight());
  }

  private int getItemHeight()
  {
    if (this.itemHeight != 0)
      return this.itemHeight;
    if ((this.itemsLayout != null) && (this.itemsLayout.getChildAt(0) != null))
    {
      this.itemHeight = this.itemsLayout.getChildAt(0).getHeight();
      return this.itemHeight;
    }
    return getHeight() / this.visibleItems;
  }

  private View getItemView(int paramInt)
  {
    if ((this.viewAdapter == null) || (this.viewAdapter.getItemsCount() == 0))
      return null;
    int i = this.viewAdapter.getItemsCount();
    if (!isValidItemIndex(paramInt))
      return this.viewAdapter.getEmptyItem(this.recycle.getEmptyItem(), this.itemsLayout);
    while (paramInt < 0)
      paramInt += i;
    int j = paramInt % i;
    return this.viewAdapter.getItem(j, this.recycle.getItem(), this.itemsLayout);
  }

  private CotrlItemsRange getItemsRange()
  {
    if (getItemHeight() == 0)
      return null;
    int i = this.currentItem;
    for (int j = 1; ; j += 2)
    {
      if (j * getItemHeight() >= getHeight())
      {
        if (this.scrollingOffset != 0)
        {
          if (this.scrollingOffset > 0)
            i--;
          int k = j + 1;
          int m = this.scrollingOffset / getItemHeight();
          i -= m;
          j = (int)(k + Math.asin(m));
        }
        return new CotrlItemsRange(i, j);
      }
      i--;
    }
  }

  private void initData(Context paramContext)
  {
    this.scroller = new CotrlWheelScroller(getContext(), this.scrollingListener);
  }

  private void initResourcesIfNecessary()
  {
    if (this.centerDrawable == null)
    {
      if (key != 1)
        break label80;
      this.centerDrawable = getContext().getResources().getDrawable(2130837520);
    }
    while (true)
    {
      if (this.topShadow == null)
        this.topShadow = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, SHADOWS_COLORS);
      if (this.bottomShadow == null)
        this.bottomShadow = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, SHADOWS_COLORS);
      return;
      label80: if (key == 2)
        this.centerDrawable = getContext().getResources().getDrawable(2130837519);
      else
        this.centerDrawable = getContext().getResources().getDrawable(2130838675);
    }
  }

  private boolean isValidItemIndex(int paramInt)
  {
    return (this.viewAdapter != null) && (this.viewAdapter.getItemsCount() > 0) && ((this.isCyclic) || ((paramInt >= 0) && (paramInt < this.viewAdapter.getItemsCount())));
  }

  private void layout(int paramInt1, int paramInt2)
  {
    int i = paramInt1 - 20;
    this.itemsLayout.layout(0, 0, i, paramInt2);
  }

  private boolean rebuildItems()
  {
    CotrlItemsRange localCotrlItemsRange = getItemsRange();
    boolean bool;
    label47: label78: int k;
    label108: label117: int i;
    if (this.itemsLayout != null)
    {
      int m = this.recycle.recycleItems(this.itemsLayout, this.firstItem, localCotrlItemsRange);
      if (this.firstItem != m)
      {
        bool = true;
        this.firstItem = m;
        if (!bool)
        {
          if ((this.firstItem != localCotrlItemsRange.getFirst()) || (this.itemsLayout.getChildCount() != localCotrlItemsRange.getCount()))
            break label161;
          bool = false;
        }
        if ((this.firstItem <= localCotrlItemsRange.getFirst()) || (this.firstItem > localCotrlItemsRange.getLast()))
          break label188;
        k = -1 + this.firstItem;
        if (k >= localCotrlItemsRange.getFirst())
          break label166;
        i = this.firstItem;
      }
    }
    for (int j = this.itemsLayout.getChildCount(); ; j++)
    {
      if (j >= localCotrlItemsRange.getCount())
      {
        this.firstItem = i;
        return bool;
        bool = false;
        break;
        createItemsLayout();
        bool = true;
        break label47;
        label161: bool = true;
        break label78;
        label166: if (!addViewItem(k, true))
          break label117;
        this.firstItem = k;
        k--;
        break label108;
        label188: this.firstItem = localCotrlItemsRange.getFirst();
        break label117;
      }
      if ((!addViewItem(j + this.firstItem, false)) && (this.itemsLayout.getChildCount() == 0))
        i++;
    }
  }

  private void updateView()
  {
    if (rebuildItems())
    {
      calculateLayoutWidth(getWidth(), 1073741824);
      layout(getWidth(), getHeight());
    }
  }

  public void addChangingListener(CotrlOnWheelChangedListener paramCotrlOnWheelChangedListener)
  {
    this.changingListeners.add(paramCotrlOnWheelChangedListener);
  }

  public void addClickingListener(CotrlOnWheelClickedListener paramCotrlOnWheelClickedListener)
  {
    this.clickingListeners.add(paramCotrlOnWheelClickedListener);
  }

  public void addScrollingListener(CotrlOnWheelScrollListener paramCotrlOnWheelScrollListener)
  {
    this.scrollingListeners.add(paramCotrlOnWheelScrollListener);
  }

  public int getCurrentItem()
  {
    return this.currentItem;
  }

  public CotrlWheelViewAdapter getViewAdapter()
  {
    return this.viewAdapter;
  }

  public int getVisibleItems()
  {
    return this.visibleItems;
  }

  public void invalidateWheel(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.recycle.clearAll();
      if (this.itemsLayout != null)
        this.itemsLayout.removeAllViews();
      this.scrollingOffset = 0;
    }
    while (true)
    {
      invalidate();
      return;
      if (this.itemsLayout != null)
        this.recycle.recycleItems(this.itemsLayout, this.firstItem, new CotrlItemsRange());
    }
  }

  public boolean isCyclic()
  {
    return this.isCyclic;
  }

  protected void notifyChangingListeners(int paramInt1, int paramInt2)
  {
    Iterator localIterator = this.changingListeners.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((CotrlOnWheelChangedListener)localIterator.next()).onChanged(this, paramInt1, paramInt2);
    }
  }

  protected void notifyClickListenersAboutClick(int paramInt)
  {
    Iterator localIterator = this.clickingListeners.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((CotrlOnWheelClickedListener)localIterator.next()).onItemClicked(this, paramInt);
    }
  }

  protected void notifyScrollingListenersAboutEnd()
  {
    Iterator localIterator = this.scrollingListeners.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((CotrlOnWheelScrollListener)localIterator.next()).onScrollingFinished(this);
    }
  }

  protected void notifyScrollingListenersAboutStart()
  {
    Iterator localIterator = this.scrollingListeners.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((CotrlOnWheelScrollListener)localIterator.next()).onScrollingStarted(this);
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((this.viewAdapter != null) && (this.viewAdapter.getItemsCount() > 0))
    {
      updateView();
      drawItems(paramCanvas);
      drawCenterRect(paramCanvas);
    }
    drawShadows(paramCanvas);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    layout(paramInt3 - paramInt1, paramInt4 - paramInt2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getMode(paramInt1);
    int j = View.MeasureSpec.getMode(paramInt2);
    int k = View.MeasureSpec.getSize(paramInt1);
    int m = View.MeasureSpec.getSize(paramInt2);
    buildViewForMeasuring();
    int n = calculateLayoutWidth(k, i);
    int i1;
    if (j == 1073741824)
      i1 = m;
    while (true)
    {
      setMeasuredDimension(n, i1);
      return;
      i1 = getDesiredHeight(this.itemsLayout);
      if (j == -2147483648)
        i1 = Math.min(i1, m);
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((!isEnabled()) || (getViewAdapter() == null))
      return true;
    switch (paramMotionEvent.getAction())
    {
    default:
    case 2:
    case 1:
    }
    do
      while (true)
      {
        return this.scroller.onTouchEvent(paramMotionEvent);
        if (getParent() != null)
          getParent().requestDisallowInterceptTouchEvent(true);
      }
    while (this.isScrollingPerformed);
    int i = (int)paramMotionEvent.getY() - getHeight() / 2;
    if (i > 0);
    for (int j = i + getItemHeight() / 2; ; j = i - getItemHeight() / 2)
    {
      int k = j / getItemHeight();
      if ((k == 0) || (!isValidItemIndex(k + this.currentItem)))
        break;
      notifyClickListenersAboutClick(k + this.currentItem);
      break;
    }
  }

  public void removeChangingListener(CotrlOnWheelChangedListener paramCotrlOnWheelChangedListener)
  {
    this.changingListeners.remove(paramCotrlOnWheelChangedListener);
  }

  public void removeClickingListener(CotrlOnWheelClickedListener paramCotrlOnWheelClickedListener)
  {
    this.clickingListeners.remove(paramCotrlOnWheelClickedListener);
  }

  public void removeScrollingListener(CotrlOnWheelScrollListener paramCotrlOnWheelScrollListener)
  {
    this.scrollingListeners.remove(paramCotrlOnWheelScrollListener);
  }

  public void scroll(int paramInt1, int paramInt2)
  {
    int i = paramInt1 * getItemHeight() - this.scrollingOffset;
    this.scroller.scroll(i, paramInt2);
  }

  public void setConteerDrawable(int paramInt)
  {
    this.centerDrawable = getContext().getResources().getDrawable(paramInt);
  }

  public void setCurrentItem(int paramInt)
  {
    setCurrentItem(paramInt, false);
  }

  public void setCurrentItem(int paramInt, boolean paramBoolean)
  {
    if ((this.viewAdapter == null) || (this.viewAdapter.getItemsCount() == 0));
    int i;
    do
    {
      do
      {
        return;
        i = this.viewAdapter.getItemsCount();
        if ((paramInt >= 0) && (paramInt < i))
          break;
      }
      while (!this.isCyclic);
      if (paramInt < 0)
        break;
      paramInt %= i;
    }
    while (paramInt == this.currentItem);
    if (paramBoolean)
    {
      int k = paramInt - this.currentItem;
      int m;
      if (this.isCyclic)
      {
        m = i + Math.min(paramInt, this.currentItem) - Math.max(paramInt, this.currentItem);
        if (m < Math.abs(k))
          if (k >= 0)
            break label136;
      }
      label136: for (k = m; ; k = -m)
      {
        scroll(k, 0);
        return;
        paramInt += i;
        break;
      }
    }
    this.scrollingOffset = 0;
    int j = this.currentItem;
    this.currentItem = paramInt;
    notifyChangingListeners(j, this.currentItem);
    invalidate();
  }

  public void setCyclic(boolean paramBoolean)
  {
    this.isCyclic = paramBoolean;
    invalidateWheel(false);
  }

  public void setInterpolator(Interpolator paramInterpolator)
  {
    this.scroller.setInterpolator(paramInterpolator);
  }

  public void setViewAdapter(CotrlWheelViewAdapter paramCotrlWheelViewAdapter)
  {
    if (this.viewAdapter != null)
      this.viewAdapter.unregisterDataSetObserver(this.dataObserver);
    this.viewAdapter = paramCotrlWheelViewAdapter;
    if (this.viewAdapter != null)
      this.viewAdapter.registerDataSetObserver(this.dataObserver);
    invalidateWheel(true);
  }

  public void setVisibleItems(int paramInt)
  {
    this.visibleItems = paramInt;
  }

  public void stopScrolling()
  {
    this.scroller.stopScrolling();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     CotrlWheelViewTwo
 * JD-Core Version:    0.6.2
 */
