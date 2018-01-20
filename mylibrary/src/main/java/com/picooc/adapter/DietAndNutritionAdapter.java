package com.picooc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.picooc.model.DietAndNutritionEntity;
import com.tencent.mm.sdk.platformtools.Log;
import java.util.List;
import org.achartengine.tools.ModUtils;

public class DietAndNutritionAdapter extends BaseAdapter
{
  private List<DietAndNutritionEntity> list = null;
  private Context mContext;

  public DietAndNutritionAdapter(Context paramContext, List<DietAndNutritionEntity> paramList)
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
      paramView = LayoutInflater.from(this.mContext).inflate(2130903046, null);
      localViewHolder.tvTitle = ((TextView)paramView.findViewById(2131099699));
      localViewHolder.leftImage = ((ImageView)paramView.findViewById(2131099698));
      localViewHolder.tvTitle.setLineSpacing(3.4F, 1.3F);
      localViewHolder.tvTitle.setTypeface(ModUtils.getTypeface(this.mContext));
      paramView.setTag(localViewHolder);
      if (((DietAndNutritionEntity)this.list.get(paramInt)).getEat_or_not() != 1)
        break label194;
      localViewHolder.leftImage.setImageResource(2130838273);
    }
    while (true)
    {
      localViewHolder.tvTitle.setText(((DietAndNutritionEntity)this.list.get(paramInt)).getMessage());
      Log.i("qianmo2", "listsize" + this.list.size() + "--------position==" + paramInt);
      return paramView;
      localViewHolder = (ViewHolder)paramView.getTag();
      break;
      label194: localViewHolder.leftImage.setImageResource(2130838274);
    }
  }

  static final class ViewHolder
  {
    ImageView leftImage;
    TextView tvTitle;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     DietAndNutritionAdapter
 * JD-Core Version:    0.6.2
 */
