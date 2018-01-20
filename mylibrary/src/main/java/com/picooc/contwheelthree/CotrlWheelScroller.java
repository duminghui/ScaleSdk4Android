package com.picooc.contwheelthree;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class CotrlWheelScroller
{
  public static final int MIN_DELTA_FOR_SCROLLING = 1;
  private static final int SCROLLING_DURATION = 400;
  private final int MESSAGE_JUSTIFY = 1;
  private final int MESSAGE_SCROLL = 0;
  private Handler animationHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      CotrlWheelScroller.this.scroller.computeScrollOffset();
      int i = CotrlWheelScroller.this.scroller.getCurrY();
      int j = CotrlWheelScroller.this.lastScrollY - i;
      CotrlWheelScroller.this.lastScrollY = i;
      if (j != 0)
        CotrlWheelScroller.this.listener.onScroll(j);
      if (Math.abs(i - CotrlWheelScroller.this.scroller.getFinalY()) < 1)
      {
        CotrlWheelScroller.this.scroller.getFinalY();
        CotrlWheelScroller.this.scroller.forceFinished(true);
      }
      if (!CotrlWheelScroller.this.scroller.isFinished())
      {
        CotrlWheelScroller.this.animationHandler.sendEmptyMessage(paramAnonymousMessage.what);
        return;
      }
      if (paramAnonymousMessage.what == 0)
      {
        CotrlWheelScroller.this.justify();
        return;
      }
      CotrlWheelScroller.this.finishScrolling();
    }
  };
  private Context context;
  private GestureDetector gestureDetector = new GestureDetector(paramContext, this.gestureListener);
  private GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener()
  {
    public boolean onFling(MotionEvent paramAnonymousMotionEvent1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
    {
      CotrlWheelScroller.this.lastScrollY = 0;
      CotrlWheelScroller.this.scroller.fling(0, CotrlWheelScroller.this.lastScrollY, 0, (int)-paramAnonymousFloat2, 0, 0, -2147483647, 2147483647);
      CotrlWheelScroller.this.setNextMessage(0);
      return true;
    }

    public boolean onScroll(MotionEvent paramAnonymousMotionEvent1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
    {
      return true;
    }
  };
  private boolean isScrollingPerformed;
  private int lastScrollY;
  private float lastTouchedY;
  private ScrollingListener listener;
  private Scroller scroller;

  public CotrlWheelScroller(Context paramContext, ScrollingListener paramScrollingListener)
  {
    this.gestureDetector.setIsLongpressEnabled(false);
    this.scroller = new Scroller(paramContext);
    this.listener = paramScrollingListener;
    this.context = paramContext;
  }

  private void clearMessages()
  {
    this.animationHandler.removeMessages(0);
    this.animationHandler.removeMessages(1);
  }

  private void justify()
  {
    this.listener.onJustify();
    setNextMessage(1);
  }

  private void setNextMessage(int paramInt)
  {
    clearMessages();
    this.animationHandler.sendEmptyMessage(paramInt);
  }

  private void startScrolling()
  {
    if (!this.isScrollingPerformed)
    {
      this.isScrollingPerformed = true;
      this.listener.onStarted();
    }
  }

  void finishScrolling()
  {
    if (this.isScrollingPerformed)
    {
      this.listener.onFinished();
      this.isScrollingPerformed = false;
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    case 1:
    default:
    case 0:
    case 2:
    }
    while (true)
    {
      if ((!this.gestureDetector.onTouchEvent(paramMotionEvent)) && (paramMotionEvent.getAction() == 1))
        justify();
      return true;
      this.lastTouchedY = paramMotionEvent.getY();
      this.scroller.forceFinished(true);
      clearMessages();
      continue;
      int i = (int)(paramMotionEvent.getY() - this.lastTouchedY);
      if (i != 0)
      {
        startScrolling();
        this.listener.onScroll(i);
        this.lastTouchedY = paramMotionEvent.getY();
      }
    }
  }

  public void scroll(int paramInt1, int paramInt2)
  {
    this.scroller.forceFinished(true);
    this.lastScrollY = 0;
    Scroller localScroller = this.scroller;
    if (paramInt2 != 0);
    for (int i = paramInt2; ; i = 400)
    {
      localScroller.startScroll(0, 0, 0, paramInt1, i);
      setNextMessage(0);
      startScrolling();
      return;
    }
  }

  public void setInterpolator(Interpolator paramInterpolator)
  {
    this.scroller.forceFinished(true);
    this.scroller = new Scroller(this.context, paramInterpolator);
  }

  public void stopScrolling()
  {
    this.scroller.forceFinished(true);
  }

  public static abstract interface ScrollingListener
  {
    public abstract void onFinished();

    public abstract void onJustify();

    public abstract void onScroll(int paramInt);

    public abstract void onStarted();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     CotrlWheelScroller
 * JD-Core Version:    0.6.2
 */
