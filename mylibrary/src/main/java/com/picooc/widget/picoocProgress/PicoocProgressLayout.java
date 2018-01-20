package com.picooc.widget.picoocProgress;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.domain.BodyIndex;

public class PicoocProgressLayout extends RelativeLayout
{
  private onArcProgressEndListener arcProgressListener;
  private ArcProgress arcs;
  private BodyIndex mBody = null;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      case 101:
      default:
      case 201:
      }
      do
        return;
      while (PicoocProgressLayout.this.arcProgressListener == null);
      PicoocProgressLayout.this.arcProgressListener.onArcProgressEnd(PicoocProgressLayout.this.mBody);
    }
  };
  private TextView text;

  public PicoocProgressLayout(Context paramContext)
  {
    super(paramContext, null);
  }

  public PicoocProgressLayout(Context paramContext, int paramInt1, int paramInt2, int paramInt3, String paramString, boolean paramBoolean, float paramFloat)
  {
    this(paramContext);
    this.arcs = new ArcProgress(getContext(), Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), this.mHandler, paramBoolean, paramFloat);
    this.arcs.setId(this.arcs.hashCode());
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams1.addRule(14);
    this.arcs.setPadding(5, 5, 5, 5);
    addView(this.arcs, localLayoutParams1);
    this.text = new TextView(paramContext);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams2.addRule(3, this.arcs.getId());
    localLayoutParams2.addRule(14);
    this.text.setText(paramString);
    this.text.setTextColor(getResources().getColor(2131165200));
    Typeface localTypeface = Typeface.createFromAsset(paramContext.getAssets(), "fonts/BRI293.TTF");
    this.text.setTypeface(localTypeface);
    this.text.setPadding(0, (int)getResources().getDimension(2131230741), 0, 0);
    addView(this.text, localLayoutParams2);
  }

  public PicoocProgressLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, -1);
  }

  public PicoocProgressLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet);
  }

  public onArcProgressEndListener getArcProgressListener()
  {
    return this.arcProgressListener;
  }

  public ArcProgress getArcs()
  {
    return this.arcs;
  }

  public float getSweep()
  {
    return this.arcs.getSweep();
  }

  public void initProgress(float paramFloat)
  {
    this.arcs.initProgress(paramFloat);
    this.arcs.invalidate();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramBoolean)
      this.text.setTextSize(0, 0.5F * (getHeight() - getWidth()));
  }

  public void resetProgress(int paramInt1, int paramInt2)
  {
    this.arcs.reDraw(paramInt1, paramInt2);
    this.arcs.invalidate();
  }

  public void setArcProgressListener(onArcProgressEndListener paramonArcProgressEndListener)
  {
    this.arcProgressListener = paramonArcProgressEndListener;
  }

  public void setBackGroundLineColor(int paramInt)
  {
    this.arcs.setBackGroundLineColor(paramInt);
  }

  public void setBackGroundLineColorAndRefesh(int paramInt)
  {
    this.arcs.setBackGroundLineColor(paramInt);
    this.arcs.invalidate();
  }

  public void setBodyIndex(BodyIndex paramBodyIndex)
  {
    this.mBody = paramBodyIndex;
  }

  public void setPaintColor(int paramInt)
  {
    this.arcs.setPaintColor(paramInt);
  }

  public void setPaintWight(float paramFloat)
  {
    this.arcs.setPaintWight(paramFloat);
  }

  public void setProgressName(String paramString)
  {
    this.text.setText(paramString);
    invalidate();
  }

  public void setmArcBGPaintColor(int paramInt)
  {
    this.arcs.setmArcBGPaintColor(paramInt);
  }

  public void startProgressReduceAlpha()
  {
    this.arcs.startProgressReduceAlpha();
  }

  public void startScrollText()
  {
    this.arcs.initProgress(0.0F);
  }

  public int stopAtNum(float paramFloat)
  {
    return this.arcs.startProgress(paramFloat);
  }

  public static abstract interface onArcProgressEndListener
  {
    public abstract void onArcProgressEnd(BodyIndex paramBodyIndex);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PicoocProgressLayout
 * JD-Core Version:    0.6.2
 */
