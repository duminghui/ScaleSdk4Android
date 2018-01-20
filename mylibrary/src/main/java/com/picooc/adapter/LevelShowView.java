package com.picooc.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.widget.AnimUtils;
import java.io.PrintStream;
import org.achartengine.tools.ModUtils;

public class LevelShowView extends RelativeLayout
{
  private static int[] sMarkResId = { 2130838198, 2130837549, 2130838185 };
  private LinearLayout bmi_translateImage;
  private TextView mCurrentLevel1;
  private TextView mCurrentLevel2;
  private TextView mCurrentValue;
  private int mItemWidth;
  private float[] mLevel;
  private ImageView mMarkImg;
  private TextView mTvBase;
  private TextView mTvFat;
  private TextView mTvPianshou;

  public LevelShowView(Context paramContext)
  {
    super(paramContext);
  }

  public LevelShowView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public LevelShowView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private void setLevelText(String[] paramArrayOfString)
  {
  }

  public void init()
  {
    this.mCurrentValue = ((TextView)findViewById(2131099922));
    this.mCurrentValue.setTypeface(ModUtils.getTypeface(getContext()));
    this.mCurrentLevel1 = ((TextView)findViewById(2131099926));
    this.mCurrentLevel1.setTypeface(ModUtils.getTypeface(getContext()));
    this.mCurrentLevel2 = ((TextView)findViewById(2131099927));
    this.mCurrentLevel2.setTypeface(ModUtils.getTypeface(getContext()));
    this.bmi_translateImage = ((LinearLayout)findViewById(2131099921));
    this.mTvPianshou = ((TextView)findViewById(2131099923));
    this.mTvBase = ((TextView)findViewById(2131099924));
    this.mTvFat = ((TextView)findViewById(2131099925));
    this.mMarkImg = ((ImageView)findViewById(2131099691));
  }

  public void noIndicator()
  {
    this.mCurrentValue.setVisibility(8);
  }

  public void resText(int paramInt)
  {
  }

  public void setCurrentLevel(float paramFloat)
  {
    this.mCurrentValue.setText(paramFloat + "%");
    System.out.println(" mItemWidth =" + this.mItemWidth / 2);
    int i = ModUtils.sp2px(getContext(), 20.0F);
    AnimUtils.LiftandRightMove(this.mCurrentLevel1, 0, this.mItemWidth - i, 0);
    AnimUtils.LiftandRightMove(this.mCurrentLevel2, 0, 2 * this.mItemWidth - i, 0);
    int j = ModUtils.dip2px(getContext(), 80.0F);
    int k = (this.mItemWidth - j) / 2;
    sMarkResId[0];
    int m;
    int n;
    if (paramFloat < this.mLevel[0])
    {
      m = this.mItemWidth - j;
      n = sMarkResId[0];
    }
    while (true)
    {
      AnimUtils.LiftandRightMove(this.bmi_translateImage, 0, m - k, 0);
      this.mMarkImg.setImageResource(n);
      return;
      if (paramFloat < this.mLevel[1])
      {
        m = 2 * this.mItemWidth - j;
        n = sMarkResId[1];
      }
      else
      {
        m = 3 * this.mItemWidth - j;
        n = sMarkResId[2];
      }
    }
  }

  public void setItemWidth(int paramInt)
  {
    this.mItemWidth = paramInt;
  }

  public void setLevelFloat(float[] paramArrayOfFloat)
  {
    this.mCurrentLevel1.setText(paramArrayOfFloat[0] + "%");
    this.mCurrentLevel2.setText(paramArrayOfFloat[1] + "%");
    this.mLevel = paramArrayOfFloat;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LevelShowView
 * JD-Core Version:    0.6.2
 */
