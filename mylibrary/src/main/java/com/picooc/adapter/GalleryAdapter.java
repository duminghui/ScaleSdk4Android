package com.picooc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import org.achartengine.tools.ModUtils;

public class GalleryAdapter extends BaseAdapter
{
  List<String> list;
  private Context mContext;
  int mGalleryItemBackground;

  public GalleryAdapter(Context paramContext, List<String> paramList)
  {
    this.mContext = paramContext;
    this.list = paramList;
  }

  public int getCount()
  {
    return this.list.size();
  }

  public Object getItem(int paramInt)
  {
    return Integer.valueOf(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
      paramView = LayoutInflater.from(this.mContext).inflate(2130903050, null);
    TextView localTextView = (TextView)paramView.findViewById(2131099715);
    localTextView.setText((CharSequence)this.list.get(paramInt));
    localTextView.setTypeface(ModUtils.getTypeface(this.mContext));
    return paramView;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     GalleryAdapter
 * JD-Core Version:    0.6.2
 */
