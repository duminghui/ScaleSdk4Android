package com.picooc.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.domain.FamilyContactsBin;
import java.util.List;

public class FamilyContactsAdapter extends BaseAdapter
{
  private int childLayout = 2130903074;
  private List<FamilyContactsBin> listData;
  private LayoutInflater mInflater;

  public FamilyContactsAdapter(Context paramContext, List<FamilyContactsBin> paramList)
  {
    this.mInflater = LayoutInflater.from(paramContext);
    this.listData = paramList;
  }

  public int getCount()
  {
    if (this.listData == null)
      return 0;
    return this.listData.size();
  }

  public Object getItem(int paramInt)
  {
    return this.listData.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    ViewHolder localViewHolder;
    FamilyContactsBin localFamilyContactsBin;
    if (paramView == null)
    {
      paramView = this.mInflater.inflate(this.childLayout, null);
      localViewHolder = new ViewHolder();
      localViewHolder.tv_name = ((TextView)paramView.findViewById(2131099842));
      localViewHolder.tv_add = ((TextView)paramView.findViewById(2131099843));
      localViewHolder.iv_add = ((ImageView)paramView.findViewById(2131099845));
      localViewHolder.relativeLayout = ((RelativeLayout)paramView.findViewById(2131099841));
      paramView.setTag(localViewHolder);
      localFamilyContactsBin = (FamilyContactsBin)this.listData.get(paramInt);
      localViewHolder.tv_name.setText(localFamilyContactsBin.getNickName());
      if ((localFamilyContactsBin.getUserID() == null) || (localFamilyContactsBin.getUserID().equals("")) || (localFamilyContactsBin.getUserID().equals("null")))
        break label251;
      localViewHolder.tv_add.setTextColor(-4654592);
      localViewHolder.tv_add.setText("添加");
      localViewHolder.iv_add.setImageResource(2130837712);
    }
    while (true)
    {
      if (localFamilyContactsBin.getIsAlreadMyRole())
      {
        SpannableStringBuilder localSpannableStringBuilder2 = new SpannableStringBuilder("已添加");
        localSpannableStringBuilder2.setSpan(new ForegroundColorSpan(1090519039), 0, 3, 34);
        localViewHolder.tv_add.setText(localSpannableStringBuilder2);
        localViewHolder.iv_add.setImageResource(0);
      }
      return paramView;
      localViewHolder = (ViewHolder)paramView.getTag();
      break;
      label251: SpannableStringBuilder localSpannableStringBuilder1 = new SpannableStringBuilder("邀请");
      localSpannableStringBuilder1.setSpan(new ForegroundColorSpan(-7102519), 0, 2, 34);
      localViewHolder.tv_add.setText(localSpannableStringBuilder1);
      localViewHolder.iv_add.setImageResource(2130838081);
    }
  }

  class ViewHolder
  {
    ImageView iv_add;
    RelativeLayout relativeLayout;
    TextView tv_add;
    TextView tv_name;

    ViewHolder()
    {
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     FamilyContactsAdapter
 * JD-Core Version:    0.6.2
 */
