package com.picooc.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.picooc.MyApplication;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.anyncImageView.AsyncImageView;
import java.util.List;
import org.achartengine.tools.ModUtils;

public class SharePagerAdapter extends PagerAdapter
{
  private MyApplication app;
  int[] images;
  List<String> list;
  Context mContext;
  String[] messages;
  List<View> viewList;

  public SharePagerAdapter(List<View> paramList, int[] paramArrayOfInt, String[] paramArrayOfString, Context paramContext)
  {
    this.viewList = paramList;
    this.images = paramArrayOfInt;
    this.messages = paramArrayOfString;
    this.mContext = paramContext;
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
    AsyncImageView localAsyncImageView = (AsyncImageView)localView.findViewById(2131099850);
    TextView localTextView1 = (TextView)localView.findViewById(2131099852);
    if (localTextView1 != null)
      localTextView1.setTypeface(TypefaceUtils.getTypeface(this.mContext, null));
    TextView localTextView2 = (TextView)localView.findViewById(2131099853);
    if (localTextView1 != null)
      localTextView1.setText(this.app.getCurrentRole().getName());
    if (localTextView2 != null)
    {
      localTextView2.setText(ModUtils.getStrTime(this.app.getTodayBody().getTime()));
      localTextView2.setTypeface(ModUtils.getTypeface(this.mContext));
    }
    if (localAsyncImageView != null)
    {
      if (this.app.getCurrentRole().getHead_portrait_url().equals(""))
        break label255;
      localAsyncImageView.setUrl(this.app.getCurrentRole().getHead_portrait_url());
    }
    while (true)
    {
      TextView localTextView3 = (TextView)localView.findViewById(2131099949);
      ImageView localImageView = (ImageView)localView.findViewById(2131099948);
      localTextView3.setTypeface(ModUtils.getTypeface(this.mContext));
      localImageView.setImageResource(this.images[paramInt]);
      localTextView3.setText(this.messages[paramInt]);
      return this.viewList.get(paramInt);
      label255: if (this.app.getCurrentRole().getSex() == 1)
        localAsyncImageView.setDefaultImageResource(2130838457);
      else
        localAsyncImageView.setDefaultImageResource(2130838460);
    }
  }

  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    return paramView == paramObject;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SharePagerAdapter
 * JD-Core Version:    0.6.2
 */
