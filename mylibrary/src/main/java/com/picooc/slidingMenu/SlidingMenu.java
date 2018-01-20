package com.picooc.slidingMenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import com.picooc.ResetLatinActivity;

public class SlidingMenu extends RelativeLayout
{
  public static final int LEFT_MENU = 11;
  public static final int RIGHT_MENU = 22;
  private static int VELOCITY = 0;
  private static final int VELOCITY_CLOSE = 4000;
  private static final int VELOCITY_OPEN = 50;
  private RelativeLayout bgShade;
  private boolean canSlideLeft = true;
  private boolean canSlideRight = false;
  private boolean hasClickLeft = false;
  private boolean hasClickRight = false;
  private boolean isMenuOpen = false;
  private View leftOffsetView;
  private Context mContext;
  private boolean mIsBeingDragged = true;
  private float mLastMotionX;
  private float mLastMotionY;
  private View mLeftView;
  private View mRightView;
  private Scroller mScroller;
  private View mSlidingView;
  private int mTouchSlop;
  private VelocityTracker mVelocityTracker;
  private int moveOrientation = -1;
  private View.OnClickListener offsetClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (paramAnonymousView == SlidingMenu.this.leftOffsetView)
        SlidingMenu.this.showRightView();
      while (paramAnonymousView != SlidingMenu.this.rightOffsetView)
        return;
      SlidingMenu.this.showLeftView();
    }
  };
  private int offsetWidth = 420;
  private View rightOffsetView;
  private int screenHeight;
  private int screenWidth;
  private boolean tCanSlideLeft = true;
  private boolean tCanSlideRight = false;
  private OnToggleStateChangeListener toggleStateChanegListener;

  public SlidingMenu(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public SlidingMenu(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  public SlidingMenu(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }

  private void changeOffsetView(int paramInt1, int paramInt2)
  {
    if ((paramInt1 <= 0) && (paramInt2 < 0))
    {
      this.rightOffsetView.setClickable(true);
      this.leftOffsetView.setClickable(false);
      this.isMenuOpen = true;
      menuStateChange(11, true);
      return;
    }
    if ((paramInt1 >= 0) && (paramInt2 <= 0))
    {
      this.isMenuOpen = false;
      this.rightOffsetView.setClickable(false);
      this.leftOffsetView.setClickable(false);
      menuStateChange(11, false);
      return;
    }
    if ((paramInt1 >= 0) && (paramInt2 >= 0))
    {
      this.isMenuOpen = true;
      this.rightOffsetView.setClickable(false);
      this.leftOffsetView.setClickable(true);
      menuStateChange(22, true);
      return;
    }
    this.isMenuOpen = false;
    this.rightOffsetView.setClickable(false);
    this.leftOffsetView.setClickable(false);
    menuStateChange(22, false);
  }

  private int getLeftMenuWidth()
  {
    if (this.mLeftView == null)
      return 0;
    return this.mLeftView.getWidth();
  }

  private int getRightMenuWidth()
  {
    if (this.mRightView == null)
      return 0;
    return this.mRightView.getWidth();
  }

  private void init(Context paramContext)
  {
    this.mContext = paramContext;
    this.bgShade = new RelativeLayout(paramContext);
    this.mScroller = new Scroller(getContext());
    this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    Display localDisplay = ((Activity)paramContext).getWindow().getWindowManager().getDefaultDisplay();
    this.screenWidth = localDisplay.getWidth();
    this.screenHeight = localDisplay.getHeight();
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(this.screenWidth, this.screenHeight);
    localLayoutParams1.addRule(13);
    this.bgShade.setLayoutParams(localLayoutParams1);
    this.offsetWidth = ((int)(0.85D * this.screenWidth));
    this.leftOffsetView = new View(paramContext);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(this.screenWidth - this.offsetWidth, this.screenHeight);
    this.leftOffsetView.setLayoutParams(localLayoutParams2);
    this.rightOffsetView = new View(paramContext);
    RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(this.screenWidth - this.offsetWidth, this.screenHeight);
    localLayoutParams3.addRule(11);
    this.rightOffsetView.setLayoutParams(localLayoutParams3);
    this.leftOffsetView.setOnClickListener(this.offsetClickListener);
    this.rightOffsetView.setOnClickListener(this.offsetClickListener);
    this.leftOffsetView.setClickable(false);
    this.rightOffsetView.setClickable(false);
  }

  private void menuStateChange(int paramInt, boolean paramBoolean)
  {
    if (this.toggleStateChanegListener != null)
      this.toggleStateChanegListener.onToggleStateChange(paramInt, paramBoolean);
  }

  public void addViews(View paramView1, View paramView2, View paramView3)
  {
  }

  public void bringToFront()
  {
    this.mSlidingView.bringToFront();
  }

  public void closeLeftView()
  {
    int i = this.mLeftView.getWidth();
    int j = this.mSlidingView.getScrollX();
    Log.e("xxx", "menuWidth: " + i);
    Log.e("xxx", "oldScrollX: " + j);
    if (j == 0)
    {
      Log.e("xxx", "打开左侧抽屉-----");
      this.mLeftView.setVisibility(0);
      this.mRightView.setVisibility(4);
      smoothScrollTo(-i);
      this.tCanSlideLeft = this.canSlideLeft;
      this.tCanSlideRight = this.canSlideRight;
      this.hasClickLeft = true;
      setCanSliding(true, false);
      this.rightOffsetView.setClickable(true);
      this.leftOffsetView.setClickable(false);
    }
    while (j != -i)
      return;
    smoothScrollTo(this.mLeftView.getWidth());
    if (this.hasClickLeft)
      this.hasClickLeft = false;
    this.rightOffsetView.setClickable(false);
    this.leftOffsetView.setClickable(false);
  }

  public void computeScroll()
  {
    int k;
    int m;
    if ((!this.mScroller.isFinished()) && (this.mScroller.computeScrollOffset()))
    {
      int i = this.mSlidingView.getScrollX();
      int j = this.mSlidingView.getScrollY();
      k = this.mScroller.getCurrX();
      m = this.mScroller.getCurrY();
      if (((i != k) || (j != m)) && (this.mSlidingView != null))
      {
        this.mSlidingView.scrollTo(k, m);
        if (k >= 0)
          break label103;
        this.bgShade.scrollTo(k + 20, m);
      }
    }
    while (true)
    {
      invalidate();
      return;
      label103: this.bgShade.scrollTo(k - 20, m);
    }
  }

  public TranslateAnimation createTranslateAnimation(boolean paramBoolean, Animation.AnimationListener paramAnimationListener, int paramInt)
  {
    int i;
    float f;
    int j;
    if (paramBoolean)
    {
      i = this.offsetWidth;
      f = i;
      j = 0;
      if (!paramBoolean)
        break label84;
    }
    while (true)
    {
      TranslateAnimation localTranslateAnimation = new TranslateAnimation(f, j, 0.0F, 0.0F);
      localTranslateAnimation.setDuration(paramInt);
      localTranslateAnimation.setFillBefore(true);
      localTranslateAnimation.setFillAfter(true);
      localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
      localTranslateAnimation.setAnimationListener(paramAnimationListener);
      return localTranslateAnimation;
      i = 0;
      break;
      label84: j = this.offsetWidth;
    }
  }

  public boolean isCanSlideLeft()
  {
    return this.canSlideLeft;
  }

  public boolean isCanSlideRight()
  {
    return this.canSlideRight;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    float f1 = paramMotionEvent.getX();
    float f2 = paramMotionEvent.getY();
    switch (i)
    {
    case 1:
    default:
    case 0:
    case 2:
    }
    while (true)
    {
      return this.mIsBeingDragged;
      this.mLastMotionX = f1;
      this.mLastMotionY = f2;
      this.mIsBeingDragged = false;
      if ((!this.canSlideLeft) || (!this.canSlideRight))
      {
        if (this.canSlideLeft)
        {
          this.mLeftView.setVisibility(0);
          this.mRightView.setVisibility(4);
        }
        if (this.canSlideRight)
        {
          this.mLeftView.setVisibility(4);
          this.mRightView.setVisibility(0);
          continue;
          float f3 = f1 - this.mLastMotionX;
          float f4 = Math.abs(f3);
          float f5 = Math.abs(f2 - this.mLastMotionY);
          if ((f4 > this.mTouchSlop) && (f4 > f5))
            if ((this.canSlideLeft) && (this.canSlideRight))
            {
              float f6 = this.mSlidingView.getScrollX();
              if ((f3 > 0.0F) && (!this.isMenuOpen))
              {
                this.mLeftView.setVisibility(0);
                this.mRightView.setVisibility(4);
              }
              while (true)
              {
                if (f6 >= 0.0F)
                  break label281;
                this.mIsBeingDragged = true;
                this.mLastMotionX = f1;
                break;
                if ((f3 < 0.0F) && (!this.isMenuOpen))
                {
                  this.mLeftView.setVisibility(4);
                  this.mRightView.setVisibility(0);
                }
              }
              label281: if (f4 > 0.0F)
              {
                this.mIsBeingDragged = true;
                this.mLastMotionX = f1;
              }
            }
            else if (this.canSlideLeft)
            {
              if (this.mSlidingView.getScrollX() < 0.0F)
              {
                this.mIsBeingDragged = true;
                this.mLastMotionX = f1;
              }
              else if (f3 > 0.0F)
              {
                this.mIsBeingDragged = true;
                this.mLastMotionX = f1;
              }
            }
            else if (this.canSlideRight)
            {
              if (this.mSlidingView.getScrollX() > 0.0F)
              {
                this.mIsBeingDragged = true;
                this.mLastMotionX = f1;
              }
              else if (f3 < 0.0F)
              {
                this.mIsBeingDragged = true;
                this.mLastMotionX = f1;
              }
            }
        }
      }
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain();
    this.mVelocityTracker.addMovement(paramMotionEvent);
    int i = paramMotionEvent.getAction();
    float f1 = paramMotionEvent.getX();
    float f2 = paramMotionEvent.getY();
    switch (i)
    {
    default:
    case 0:
    case 2:
      while (true)
      {
        return false;
        if (!this.mScroller.isFinished())
          this.mScroller.abortAnimation();
        this.mLastMotionX = f1;
        this.mLastMotionY = f2;
        if ((this.mSlidingView.getScrollX() == -getLeftMenuWidth()) && (this.mLastMotionX < getLeftMenuWidth()))
          return false;
        if ((this.mSlidingView.getScrollX() == getRightMenuWidth()) && (this.mLastMotionX > getLeftMenuWidth()))
        {
          return false;
          if (this.mIsBeingDragged)
          {
            float f4 = this.mLastMotionX - f1;
            this.mLastMotionX = f1;
            float f5 = this.mSlidingView.getScrollX();
            float f6 = f5 + f4;
            if ((!this.canSlideLeft) || (!this.canSlideRight))
            {
              if ((this.canSlideLeft) && (f6 > 0.0F))
                f6 = 0.0F;
              if ((this.canSlideRight) && (f6 < 0.0F))
                f6 = 0.0F;
            }
            if ((f4 < 0.0F) && (f5 <= 0.0F))
            {
              if (this.moveOrientation == 1)
                return true;
              f8 = -getLeftMenuWidth();
              if (f6 > 0.0F)
              {
                f6 = 0.0F;
                this.moveOrientation = 2;
              }
            }
            while ((f4 <= 0.0F) || (f5 < 0.0F))
            {
              float f8;
              while (this.mSlidingView != null)
              {
                this.mSlidingView.scrollTo((int)f6, this.mSlidingView.getScrollY());
                if (f6 >= 0.0F)
                  break label422;
                this.bgShade.scrollTo(20 + (int)f6, this.mSlidingView.getScrollY());
                break;
                if (f6 < f8)
                  f6 = f8;
              }
            }
            if (this.moveOrientation == 2)
              return true;
            float f7 = getRightMenuWidth();
            if (f6 < 0.0F)
              f6 = 0.0F;
            while (true)
            {
              this.moveOrientation = 1;
              break;
              if (f6 > f7)
                f6 = f7;
            }
            label422: this.bgShade.scrollTo(-20 + (int)f6, this.mSlidingView.getScrollY());
          }
        }
      }
    case 1:
    case 3:
    }
    float f3;
    int j;
    label493: int k;
    if (this.mIsBeingDragged)
    {
      VelocityTracker localVelocityTracker = this.mVelocityTracker;
      localVelocityTracker.computeCurrentVelocity(100);
      f3 = localVelocityTracker.getXVelocity();
      j = this.mSlidingView.getScrollX();
      if (!this.isMenuOpen)
        break label613;
      VELOCITY = 50;
      k = 0;
      if (j <= 0)
      {
        boolean bool = this.canSlideLeft;
        k = 0;
        if (bool)
        {
          if (f3 <= VELOCITY)
            break label622;
          k = -getLeftMenuWidth() - j;
        }
      }
      label535: if ((j >= 0) && (this.canSlideRight))
      {
        if (f3 >= -VELOCITY)
          break label741;
        k = getRightMenuWidth() - j;
      }
    }
    while (true)
    {
      if (k < 0)
      {
        Intent localIntent = new Intent();
        localIntent.setAction("com.picooc.slidingmenu.close.right.menu");
        this.mContext.sendBroadcast(localIntent);
      }
      smoothScrollTo(k);
      this.moveOrientation = -1;
      break;
      label613: VELOCITY = 4000;
      break label493;
      label622: if (f3 < -VELOCITY)
      {
        k = -j;
        if (!this.hasClickLeft)
          break label535;
        this.hasClickLeft = false;
        setCanSliding(this.tCanSlideLeft, this.tCanSlideRight);
        break label535;
      }
      if (j < -getLeftMenuWidth() / 3)
      {
        k = -getLeftMenuWidth() - j;
        break label535;
      }
      int m = -getLeftMenuWidth() / 3;
      k = 0;
      if (j < m)
        break label535;
      k = -j;
      if (!this.hasClickLeft)
        break label535;
      this.hasClickLeft = false;
      setCanSliding(this.tCanSlideLeft, this.tCanSlideRight);
      break label535;
      label741: if (f3 > VELOCITY)
      {
        k = -j;
        if (this.hasClickRight)
        {
          this.hasClickRight = false;
          setCanSliding(this.tCanSlideLeft, this.tCanSlideRight);
        }
      }
      else if (j > getRightMenuWidth() / 3)
      {
        k = getRightMenuWidth() - j;
      }
      else if (j <= getRightMenuWidth() / 3)
      {
        k = -j;
        if (this.hasClickRight)
        {
          this.hasClickRight = false;
          setCanSliding(this.tCanSlideLeft, this.tCanSlideRight);
        }
      }
    }
  }

  public void scrollTo(int paramInt1, int paramInt2)
  {
    super.scrollTo(paramInt1, paramInt2);
    postInvalidate();
  }

  public void setCanSliding(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.canSlideLeft = paramBoolean1;
    this.canSlideRight = paramBoolean2;
  }

  public void setCenterView(View paramView)
  {
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -1);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(this.screenWidth, this.screenHeight);
    localLayoutParams2.addRule(13);
    View localView = new View(this.mContext);
    localView.setBackgroundDrawable(getResources().getDrawable(2130838351));
    this.bgShade.addView(localView, localLayoutParams2);
    addView(this.bgShade, localLayoutParams2);
    addView(paramView, 0, localLayoutParams1);
    this.mSlidingView = paramView;
    this.mSlidingView.bringToFront();
    addView(this.leftOffsetView);
    addView(this.rightOffsetView);
  }

  public void setLeftView(View paramView)
  {
    addView(paramView, new RelativeLayout.LayoutParams(this.offsetWidth, -1));
    this.mLeftView = paramView;
  }

  public void setRightView(View paramView)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.offsetWidth, -1);
    localLayoutParams.addRule(11);
    addView(paramView, localLayoutParams);
    this.mRightView = paramView;
  }

  public void setToggleStateChanegListener(OnToggleStateChangeListener paramOnToggleStateChangeListener)
  {
    this.toggleStateChanegListener = paramOnToggleStateChangeListener;
  }

  public void showAnimationLeft(int paramInt)
  {
    scrollTo(0, this.mScroller.getCurrY());
  }

  public void showLeftView()
  {
    int i = this.mLeftView.getWidth();
    int j = this.mSlidingView.getScrollX();
    Log.e("xxx", "menuWidth: " + i);
    Log.e("xxx", "oldScrollX: " + j);
    if (j == 0)
    {
      Log.e("xxx", "打开左侧抽屉-----");
      this.mLeftView.setVisibility(0);
      this.mRightView.setVisibility(4);
      smoothScrollTo(-i);
      this.tCanSlideLeft = this.canSlideLeft;
      this.tCanSlideRight = this.canSlideRight;
      this.hasClickLeft = true;
      setCanSliding(true, false);
      this.rightOffsetView.setClickable(true);
      this.leftOffsetView.setClickable(false);
      menuStateChange(11, true);
    }
    while (j != -i)
      return;
    Log.e("xxx", "关闭左侧抽屉-----");
    smoothScrollTo(i);
    if (this.hasClickLeft)
    {
      this.hasClickLeft = false;
      setCanSliding(this.tCanSlideLeft, this.tCanSlideRight);
    }
    this.rightOffsetView.setClickable(false);
    this.leftOffsetView.setClickable(false);
    menuStateChange(11, false);
  }

  public void showRightView()
  {
    int i = this.mRightView.getWidth();
    int j = this.mSlidingView.getScrollX();
    if (j == 0)
    {
      this.mLeftView.setVisibility(4);
      this.mRightView.setVisibility(0);
      smoothScrollTo(i);
      this.tCanSlideLeft = this.canSlideLeft;
      this.tCanSlideRight = this.canSlideRight;
      this.hasClickRight = true;
      setCanSliding(false, true);
      this.rightOffsetView.setClickable(false);
      this.leftOffsetView.setClickable(true);
      menuStateChange(22, true);
    }
    while (j != i)
      return;
    smoothScrollTo(-i);
    if (this.hasClickRight)
    {
      this.hasClickRight = false;
      setCanSliding(this.tCanSlideLeft, this.tCanSlideRight);
    }
    this.rightOffsetView.setClickable(false);
    this.leftOffsetView.setClickable(false);
    menuStateChange(22, false);
    Intent localIntent = new Intent();
    localIntent.setAction("com.picooc.slidingmenu.close.right.menu");
    this.mContext.sendBroadcast(localIntent);
  }

  void smoothScrollTo(int paramInt)
  {
    int i = this.mSlidingView.getScrollX();
    changeOffsetView(paramInt, i);
    this.mScroller.startScroll(i, this.mSlidingView.getScrollY(), paramInt, this.mSlidingView.getScrollY(), 500);
    invalidate();
  }

  public void startAnimations()
  {
    this.mRightView.setVisibility(4);
    this.mLeftView.setVisibility(0);
    this.bgShade.setVisibility(8);
    this.mSlidingView.startAnimation(createTranslateAnimation(false, new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        SlidingMenu.this.mSlidingView.startAnimation(SlidingMenu.this.createTranslateAnimation(true, new Animation.AnimationListener()
        {
          public void onAnimationEnd(Animation paramAnonymous2Animation)
          {
            Intent localIntent = new Intent();
            localIntent.setClass(SlidingMenu.this.getContext(), ResetLatinActivity.class);
            ((Activity)SlidingMenu.this.getContext()).startActivity(localIntent);
          }

          public void onAnimationRepeat(Animation paramAnonymous2Animation)
          {
          }

          public void onAnimationStart(Animation paramAnonymous2Animation)
          {
          }
        }
        , 1000));
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    }
    , 2000));
  }

  public static abstract interface OnToggleStateChangeListener
  {
    public abstract void onToggleStateChange(int paramInt, boolean paramBoolean);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SlidingMenu
 * JD-Core Version:    0.6.2
 */
