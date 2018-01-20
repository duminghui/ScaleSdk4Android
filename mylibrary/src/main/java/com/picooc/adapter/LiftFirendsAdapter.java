package com.picooc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.tencent.mm.sdk.platformtools.Log;
import java.util.List;
import java.util.Map;

public class LiftFirendsAdapter extends BaseAdapter
{
  private List<Map<String, String>> list = null;
  private Context mContext;

  public LiftFirendsAdapter(Context paramContext, List<Map<String, String>> paramList)
  {
    this.mContext = paramContext;
    this.list = paramList;
  }

  public void addlist(List<Map<String, String>> paramList)
  {
    this.list.addAll(paramList);
  }

  public int getCount()
  {
    return this.list.size();
  }

  public Object getItem(int paramInt)
  {
    return null;
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    ViewHolder localViewHolder;
    if (paramView == null)
    {
      localViewHolder = new ViewHolder();
      paramView = LayoutInflater.from(this.mContext).inflate(2130903055, null);
      localViewHolder.tvTitle = ((TextView)paramView.findViewById(2131099699));
      localViewHolder.unit = paramView.findViewById(2131099734);
      paramView.setTag(localViewHolder);
      if (paramInt != -1 + this.list.size())
        break label165;
      localViewHolder.unit.setVisibility(0);
    }
    while (true)
    {
      localViewHolder.tvTitle.setText((CharSequence)((Map)this.list.get(paramInt)).get("name"));
      Log.i("qianmo2", "listsize" + this.list.size() + "--------position==" + paramInt);
      return paramView;
      localViewHolder = (ViewHolder)paramView.getTag();
      break;
      label165: localViewHolder.unit.setVisibility(8);
    }
  }

  static final class ViewHolder
  {
    TextView insurance;
    TextView presell;
    TextView price;
    TextView tvLetter;
    TextView tvTitle;
    View unit;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LiftFirendsAdapter
 * JD-Core Version:    0.6.2
 */
