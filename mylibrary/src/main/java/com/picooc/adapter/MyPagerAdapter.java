package com.picooc.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;
import org.achartengine.tools.ModUtils;

public class MyPagerAdapter extends PagerAdapter
{
  List<String> list;
  Context mContext;
  List<View> viewList;

  public MyPagerAdapter(List<View> paramList, List<String> paramList1, Context paramContext)
  {
    this.viewList = paramList;
    this.list = paramList1;
    this.mContext = paramContext;
  }

  public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
  {
    paramViewGroup.removeView((View)this.viewList.get(paramInt));
  }

  public int getCount()
  {
    return this.viewList.size();
  }

  public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
  {
    paramViewGroup.addView((View)this.viewList.get(paramInt));
    View localView = (View)this.viewList.get(paramInt);
    TextView localTextView1 = (TextView)localView.findViewById(2131099954);
    ImageView localImageView = (ImageView)localView.findViewById(2131099948);
    RelativeLayout localRelativeLayout = (RelativeLayout)localView.findViewById(2131099946);
    TextView localTextView2 = (TextView)localView.findViewById(2131099952);
    TextView localTextView3 = (TextView)localView.findViewById(2131099955);
    TextView localTextView4 = (TextView)localView.findViewById(2131099956);
    TextView localTextView5 = (TextView)localView.findViewById(2131099957);
    localTextView2.setTypeface(ModUtils.getTypeface(this.mContext));
    localTextView3.setTypeface(ModUtils.getTypeface(this.mContext));
    localTextView4.setTypeface(ModUtils.getTypeface(this.mContext));
    localTextView5.setTypeface(ModUtils.getTypeface(this.mContext));
    if (paramInt != 0)
    {
      if (paramInt != 1)
        break label201;
      localImageView.setImageResource(2130837852);
    }
    while (true)
    {
      localTextView1.setText((CharSequence)this.list.get(paramInt));
      return this.viewList.get(paramInt);
      label201: if (paramInt == 2)
      {
        localImageView.setImageResource(2130837854);
      }
      else if (paramInt == 3)
      {
        localRelativeLayout.setBackgroundResource(2130837957);
        localImageView.setImageResource(2130837858);
      }
      else if (paramInt == 4)
      {
        localImageView.setImageResource(2130837856);
      }
    }
  }

  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    return paramView == paramObject;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     MyPagerAdapter
 * JD-Core Version:    0.6.2
 */
