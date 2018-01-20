package com.picooc.widget.everydayme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

public class Panel extends LinearLayout
{
  public static final int BOTTOM = 1;
  public static final int LEFT = 2;
  public static final int RIGHT = 3;
  public static final int TOP;
  private Animation.AnimationListener animationListener = new Animation.AnimationListener()
  {
    public void onAnimationEnd(Animation paramAnonymousAnimation)
    {
      Panel.this.mState = State.READY;
      if (Panel.this.mIsShrinking)
        Panel.this.mContent.setVisibility(8);
      Panel.this.postProcess();
    }

    public void onAnimationRepeat(Animation paramAnonymousAnimation)
    {
    }

    public void onAnimationStart(Animation paramAnonymousAnimation)
    {
      Panel.this.mState = State.ANIMATING;
      Panel.this.invalidate();
    }
  };
  private Drawable mClosedHandle;
  private View mContent;
  private int mContentHeight;
  private int mContentWidth;
  private int mDuration;
  private GestureDetector mGestureDetector;
  private PanelOnGestureListener mGestureListener;
  private View mHandle;
  private Interpolator mInterpolator;
  private boolean mIsShrinking;
  private boolean mLinearFlying;
  private Drawable mOpenedHandle;
  private int mOrientation;
  private int mPosition;
  private State mState;
  private float mTrackX;
  private float mTrackY;
  private float mVelocity;
  private OnPanelListener panelListener;
  Runnable startAnimation = new Runnable()
  {
    public void run()
    {
      int i = 0;
      int j = 0;
      int k = 0;
      int i3;
      int i4;
      label64: int i2;
      int i1;
      label119: boolean bool2;
      label182: label202: int n;
      if (Panel.this.mState == State.FLYING)
      {
        Panel localPanel4 = Panel.this;
        if ((Panel.this.mPosition != 0) && (Panel.this.mPosition != 2))
        {
          i3 = 0;
          if (Panel.this.mVelocity <= 0.0F)
            break label323;
          i4 = 1;
          localPanel4.mIsShrinking = (i3 ^ i4);
        }
      }
      else
      {
        if (Panel.this.mOrientation != 1)
          break label428;
        i2 = Panel.this.mContentHeight;
        if (Panel.this.mIsShrinking)
          break label338;
        if (Panel.this.mPosition != 0)
          break label329;
        i1 = -i2;
        if (Panel.this.mState != State.TRACKING)
          break label370;
        if (Math.abs(Panel.this.mTrackY - i1) < Math.abs(Panel.this.mTrackY - k))
        {
          Panel localPanel3 = Panel.this;
          if (!Panel.this.mIsShrinking)
            break label364;
          bool2 = false;
          localPanel3.mIsShrinking = bool2;
          k = i1;
        }
        i1 = (int)Panel.this.mTrackY;
        if ((Panel.this.mState != State.FLYING) || (!Panel.this.mLinearFlying))
          break label396;
        n = Math.max((int)(1000.0F * Math.abs((k - i1) / Panel.this.mVelocity)), 20);
      }
      while (true)
      {
        Panel localPanel1 = Panel.this;
        Panel.this.mTrackY = 0.0F;
        localPanel1.mTrackX = 0.0F;
        if (n != 0)
          break label696;
        Panel.this.mState = State.READY;
        if (Panel.this.mIsShrinking)
          Panel.this.mContent.setVisibility(8);
        Panel.this.postProcess();
        return;
        i3 = 1;
        break;
        label323: i4 = 0;
        break label64;
        label329: i1 = i2;
        k = 0;
        break label119;
        label338: if (Panel.this.mPosition == 0);
        for (k = -i2; ; k = i2)
        {
          i1 = 0;
          break;
        }
        label364: bool2 = true;
        break label182;
        label370: if (Panel.this.mState != State.FLYING)
          break label202;
        i1 = (int)Panel.this.mTrackY;
        break label202;
        label396: n = Panel.this.mDuration * Math.abs(k - i1) / Panel.this.mContentHeight;
        i = 0;
        j = 0;
        continue;
        label428: int m = Panel.this.mContentWidth;
        label462: boolean bool1;
        if (!Panel.this.mIsShrinking)
          if (Panel.this.mPosition == 2)
          {
            i = -m;
            if (Panel.this.mState != State.TRACKING)
              break label639;
            if (Math.abs(Panel.this.mTrackX - i) < Math.abs(Panel.this.mTrackX - j))
            {
              Panel localPanel2 = Panel.this;
              if (!Panel.this.mIsShrinking)
                break label633;
              bool1 = false;
              label524: localPanel2.mIsShrinking = bool1;
              j = i;
            }
            i = (int)Panel.this.mTrackX;
          }
        while (true)
        {
          if ((Panel.this.mState != State.FLYING) || (!Panel.this.mLinearFlying))
            break label664;
          n = Math.max((int)(1000.0F * Math.abs((j - i) / Panel.this.mVelocity)), 20);
          i1 = 0;
          k = 0;
          break;
          i = m;
          j = 0;
          break label462;
          if (Panel.this.mPosition == 2);
          for (j = -m; ; j = m)
          {
            i = 0;
            break;
          }
          label633: bool1 = true;
          break label524;
          label639: if (Panel.this.mState == State.FLYING)
            i = (int)Panel.this.mTrackX;
        }
        label664: n = Panel.this.mDuration * Math.abs(j - i) / Panel.this.mContentWidth;
        i1 = 0;
        k = 0;
      }
      label696: TranslateAnimation localTranslateAnimation = new TranslateAnimation(i, j, i1, k);
      localTranslateAnimation.setDuration(n);
      localTranslateAnimation.setAnimationListener(Panel.this.animationListener);
      if ((Panel.this.mState == State.FLYING) && (Panel.this.mLinearFlying))
        localTranslateAnimation.setInterpolator(new LinearInterpolator());
      while (true)
      {
        Panel.this.startAnimation(localTranslateAnimation);
        return;
        if (Panel.this.mInterpolator != null)
          localTranslateAnimation.setInterpolator(Panel.this.mInterpolator);
      }
    }
  };
  View.OnTouchListener touchListener = new View.OnTouchListener()
  {
    int initX;
    int initY;
    boolean setInitialPosition;

    public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
    {
      int i = -1;
      int j = paramAnonymousMotionEvent.getAction();
      if (j == 0)
      {
        this.initX = 0;
        this.initY = 0;
        if (Panel.this.mContent.getVisibility() == 8)
        {
          if (Panel.this.mOrientation != 1)
            break label111;
          if (Panel.this.mPosition == 0)
            this.initY = i;
        }
        else
        {
          this.setInitialPosition = true;
        }
      }
      while (true)
      {
        if ((!Panel.this.mGestureDetector.onTouchEvent(paramAnonymousMotionEvent)) && (j == 1))
          Panel.this.post(Panel.this.startAnimation);
        return false;
        i = 1;
        break;
        label111: if (Panel.this.mPosition == 2);
        while (true)
        {
          this.initX = i;
          break;
          i = 1;
        }
        if (this.setInitialPosition)
        {
          this.initX *= Panel.this.mContentWidth;
          this.initY *= Panel.this.mContentHeight;
          Panel.this.mGestureListener.setScroll(this.initX, this.initY);
          this.setInitialPosition = false;
          this.initX = (-this.initX);
          this.initY = (-this.initY);
        }
        paramAnonymousMotionEvent.offsetLocation(this.initX, this.initY);
      }
    }
  };

  public Panel(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.Panel);
    this.mDuration = localTypedArray.getInteger(0, 750);
    this.mPosition = localTypedArray.getInteger(i, i);
    this.mLinearFlying = localTypedArray.getBoolean(2, false);
    this.mOpenedHandle = localTypedArray.getDrawable(3);
    this.mClosedHandle = localTypedArray.getDrawable(4);
    localTypedArray.recycle();
    if ((this.mPosition == 0) || (this.mPosition == i));
    while (true)
    {
      this.mOrientation = i;
      setOrientation(this.mOrientation);
      this.mState = State.READY;
      this.mGestureListener = new PanelOnGestureListener();
      this.mGestureDetector = new GestureDetector(this.mGestureListener);
      this.mGestureDetector.setIsLongpressEnabled(false);
      return;
      i = 0;
    }
  }

  private float ensureRange(float paramFloat, int paramInt1, int paramInt2)
  {
    return Math.min(Math.max(paramFloat, paramInt1), paramInt2);
  }

  private void postProcess()
  {
    if ((this.mIsShrinking) && (this.mClosedHandle != null))
      this.mHandle.setBackgroundDrawable(this.mClosedHandle);
    while (true)
    {
      if (this.panelListener != null)
      {
        if (!this.mIsShrinking)
          break;
        this.panelListener.onPanelClosed(this);
      }
      return;
      if ((!this.mIsShrinking) && (this.mOpenedHandle != null))
        this.mHandle.setBackgroundDrawable(this.mOpenedHandle);
    }
    this.panelListener.onPanelOpened(this);
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    int i;
    if ((this.mState == State.ABOUT_TO_ANIMATE) && (!this.mIsShrinking))
    {
      if (this.mOrientation != 1)
        break label101;
      i = this.mContentHeight;
      if ((this.mPosition == 2) || (this.mPosition == 0))
        i = -i;
      if (this.mOrientation != 1)
        break label109;
      paramCanvas.translate(0.0F, i);
    }
    while (true)
    {
      if ((this.mState == State.TRACKING) || (this.mState == State.FLYING))
        paramCanvas.translate(this.mTrackX, this.mTrackY);
      super.dispatchDraw(paramCanvas);
      return;
      label101: i = this.mContentWidth;
      break;
      label109: paramCanvas.translate(i, 0.0F);
    }
  }

  public View getContent()
  {
    return this.mContent;
  }

  public View getHandle()
  {
    return this.mHandle;
  }

  public boolean isOpen()
  {
    return this.mContent.getVisibility() == 0;
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mHandle = findViewById(2131099656);
    if (this.mHandle == null)
      throw new RuntimeException("Your Panel must have a View whose id attribute is 'R.id.panelHandle'");
    this.mHandle.setOnTouchListener(this.touchListener);
    this.mContent = findViewById(2131099657);
    if (this.mContent == null)
      throw new RuntimeException("Your Panel must have a View whose id attribute is 'R.id.panelContent'");
    removeView(this.mHandle);
    removeView(this.mContent);
    if ((this.mPosition == 0) || (this.mPosition == 2))
    {
      addView(this.mContent);
      addView(this.mHandle);
    }
    while (true)
    {
      if (this.mClosedHandle != null)
        this.mHandle.setBackgroundDrawable(this.mClosedHandle);
      this.mContent.setVisibility(8);
      return;
      addView(this.mHandle);
      addView(this.mContent);
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.mContentWidth = this.mContent.getWidth();
    this.mContentHeight = this.mContent.getHeight();
  }

  public void setInterpolator(Interpolator paramInterpolator)
  {
    this.mInterpolator = paramInterpolator;
  }

  public void setOnPanelListener(OnPanelListener paramOnPanelListener)
  {
    this.panelListener = paramOnPanelListener;
  }

  public void setOpen(boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramBoolean1 ^ isOpen()))
      if (!paramBoolean1)
        break label56;
    label56: for (boolean bool = false; ; bool = true)
    {
      this.mIsShrinking = bool;
      if (!paramBoolean2)
        break;
      this.mState = State.ABOUT_TO_ANIMATE;
      if (!this.mIsShrinking)
        this.mContent.setVisibility(0);
      post(this.startAnimation);
      return;
    }
    View localView = this.mContent;
    int i = 0;
    if (paramBoolean1);
    while (true)
    {
      localView.setVisibility(i);
      postProcess();
      return;
      i = 8;
    }
  }

  public static abstract interface OnPanelListener
  {
    public abstract void onPanelClosed(Panel paramPanel);

    public abstract void onPanelOpened(Panel paramPanel);
  }

  class PanelOnGestureListener
    implements GestureDetector.OnGestureListener
  {
    float scrollX;
    float scrollY;

    PanelOnGestureListener()
    {
    }

    public boolean onDown(MotionEvent paramMotionEvent)
    {
      this.scrollY = 0.0F;
      this.scrollX = 0.0F;
      if (Panel.this.mState != State.READY)
        return false;
      Panel.this.mState = State.ABOUT_TO_ANIMATE;
      Panel localPanel = Panel.this;
      if (Panel.this.mContent.getVisibility() == 0);
      for (boolean bool = true; ; bool = false)
      {
        localPanel.mIsShrinking = bool;
        if (!Panel.this.mIsShrinking)
          Panel.this.mContent.setVisibility(0);
        return true;
      }
    }

    public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      Panel.this.mState = State.FLYING;
      Panel localPanel = Panel.this;
      if (Panel.this.mOrientation == 1);
      while (true)
      {
        localPanel.mVelocity = paramFloat2;
        Panel.this.post(Panel.this.startAnimation);
        return true;
        paramFloat2 = paramFloat1;
      }
    }

    public void onLongPress(MotionEvent paramMotionEvent)
    {
    }

    public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      Panel.this.mState = State.TRACKING;
      float f1 = 0.0F;
      float f2;
      if (Panel.this.mOrientation == 1)
      {
        this.scrollY -= paramFloat2;
        if (Panel.this.mPosition == 0)
          f2 = Panel.this.ensureRange(this.scrollY, -Panel.this.mContentHeight, 0);
      }
      while (true)
      {
        if ((f1 != Panel.this.mTrackX) || (f2 != Panel.this.mTrackY))
        {
          Panel.this.mTrackX = f1;
          Panel.this.mTrackY = f2;
          Panel.this.invalidate();
        }
        return true;
        f2 = Panel.this.ensureRange(this.scrollY, 0, Panel.this.mContentHeight);
        f1 = 0.0F;
        continue;
        this.scrollX -= paramFloat1;
        if (Panel.this.mPosition == 2)
        {
          f1 = Panel.this.ensureRange(this.scrollX, -Panel.this.mContentWidth, 0);
          f2 = 0.0F;
        }
        else
        {
          f1 = Panel.this.ensureRange(this.scrollX, 0, Panel.this.mContentWidth);
          f2 = 0.0F;
        }
      }
    }

    public void onShowPress(MotionEvent paramMotionEvent)
    {
    }

    public boolean onSingleTapUp(MotionEvent paramMotionEvent)
    {
      Panel.this.post(Panel.this.startAnimation);
      return true;
    }

    public void setScroll(int paramInt1, int paramInt2)
    {
      this.scrollX = paramInt1;
      this.scrollY = paramInt2;
    }
  }

  private static enum State
  {
    static
    {
      FLYING = new State("FLYING", 4);
      State[] arrayOfState = new State[5];
      arrayOfState[0] = ABOUT_TO_ANIMATE;
      arrayOfState[1] = ANIMATING;
      arrayOfState[2] = READY;
      arrayOfState[3] = TRACKING;
      arrayOfState[4] = FLYING;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     Panel
 * JD-Core Version:    0.6.2
 */
