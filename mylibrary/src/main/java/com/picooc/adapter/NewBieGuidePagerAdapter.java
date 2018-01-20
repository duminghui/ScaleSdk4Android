package com.picooc.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.MyApplication;
import java.util.List;
import org.achartengine.tools.ModUtils;

public class NewBieGuidePagerAdapter extends PagerAdapter
{
  private MyApplication app;
  String[] imageString;
  int[] images;
  int[] imagesBg;
  List<String> list;
  Context mContext;
  List<View> viewList;

  public NewBieGuidePagerAdapter(List<View> paramList, Context paramContext, int[] paramArrayOfInt1, int[] paramArrayOfInt2, String[] paramArrayOfString)
  {
    this.viewList = paramList;
    this.images = paramArrayOfInt1;
    this.imagesBg = paramArrayOfInt2;
    this.imageString = paramArrayOfString;
    this.app = ((MyApplication)paramContext.getApplicationContext());
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
    RelativeLayout localRelativeLayout = (RelativeLayout)localView.findViewById(2131099946);
    ImageView localImageView = (ImageView)localView.findViewById(2131099948);
    TextView localTextView = (TextView)localView.findViewById(2131099951);
    localTextView.setTypeface(ModUtils.getTypeface(this.mContext));
    localTextView.setText(this.imageString[paramInt]);
    localRelativeLayout.setBackgroundResource(this.imagesBg[paramInt]);
    localImageView.setImageResource(this.images[paramInt]);
    return this.viewList.get(paramInt);
  }

  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    return paramView == paramObject;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     NewBieGuidePagerAdapter
 * JD-Core Version:    0.6.2
 */
