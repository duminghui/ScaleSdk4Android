package com.picooc.widget.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class ModXListViewFooter extends LinearLayout
{
  public static final int STATE_LOADING = 2;
  public static final int STATE_NORMAL = 0;
  public static final int STATE_READY = 1;
  private View mContentView;
  private Context mContext;
  private TextView mHintView;
  private View mProgressBar;

  public ModXListViewFooter(Context paramContext)
  {
    super(paramContext);
    initView(paramContext);
  }

  public ModXListViewFooter(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
  }

  private void initView(Context paramContext)
  {
    this.mContext = paramContext;
    LinearLayout localLinearLayout = (LinearLayout)LayoutInflater.from(this.mContext).inflate(2130903233, null);
    addView(localLinearLayout);
    localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    this.mContentView = localLinearLayout.findViewById(2131100748);
    this.mProgressBar = localLinearLayout.findViewById(2131100749);
    this.mHintView = ((TextView)localLinearLayout.findViewById(2131099757));
  }

  public int getBottomMargin()
  {
    return ((LinearLayout.LayoutParams)this.mContentView.getLayoutParams()).bottomMargin;
  }

  public void hide()
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mContentView.getLayoutParams();
    localLayoutParams.height = 0;
    this.mContentView.setLayoutParams(localLayoutParams);
  }

  public void loading()
  {
    this.mHintView.setVisibility(8);
    this.mProgressBar.setVisibility(0);
  }

  public void normal()
  {
    this.mHintView.setVisibility(0);
    this.mProgressBar.setVisibility(8);
  }

  public void setBottomMargin(int paramInt)
  {
    if (paramInt < 0)
      return;
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mContentView.getLayoutParams();
    localLayoutParams.bottomMargin = paramInt;
    this.mContentView.setLayoutParams(localLayoutParams);
  }

  public void setState(int paramInt)
  {
    this.mHintView.setVisibility(4);
    this.mProgressBar.setVisibility(4);
    this.mHintView.setVisibility(4);
    if (paramInt == 1)
    {
      this.mHintView.setVisibility(0);
      this.mHintView.setText(2131361917);
      return;
    }
    if (paramInt == 2)
    {
      this.mProgressBar.setVisibility(0);
      return;
    }
    this.mHintView.setVisibility(0);
    this.mHintView.setText(2131361916);
  }

  public void show()
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mContentView.getLayoutParams();
    localLayoutParams.height = -2;
    this.mContentView.setLayoutParams(localLayoutParams);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ModXListViewFooter
 * JD-Core Version:    0.6.2
 */
