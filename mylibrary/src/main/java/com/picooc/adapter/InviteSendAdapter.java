package com.picooc.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.picooc.domain.InvitationInfos;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.ImageLoader;
import java.util.List;
import org.achartengine.tools.ModUtils;

public class InviteSendAdapter extends BaseAdapter
{
  private int childLayout = 2130903189;
  private Context context;
  private Drawable drawable;
  public ImageLoader imageLoader;
  List<InvitationInfos> list;
  private LayoutInflater mInflater;

  public InviteSendAdapter(Context paramContext, List<InvitationInfos> paramList)
  {
    this.mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    this.context = paramContext;
    this.list = paramList;
    Log.i("picooc", "listcount==" + paramList.size());
    if (this.imageLoader == null)
      this.imageLoader = new ImageLoader(paramContext);
  }

  public int getCount()
  {
    if (this.list == null)
      return 0;
    return this.list.size();
  }

  public Object getItem(int paramInt)
  {
    return this.list.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int i = 2130838457;
    Log.i("picooc", "convertView = " + paramView);
    ViewHolder localViewHolder;
    InvitationInfos localInvitationInfos;
    label440: ImageLoader localImageLoader;
    if (paramView == null)
    {
      localViewHolder = new ViewHolder();
      paramView = this.mInflater.inflate(this.childLayout, null);
      localViewHolder.tv_name = ((TextView)paramView.findViewById(2131099842));
      localViewHolder.tv_name.setTypeface(TypefaceUtils.getTypeface(this.context, null));
      localViewHolder.tv_time = ((TextView)paramView.findViewById(2131100449));
      localViewHolder.tv_time.setTypeface(TypefaceUtils.getTypeface(this.context, null));
      localViewHolder.tv_message = ((TextView)paramView.findViewById(2131100455));
      localViewHolder.tv_message.setTypeface(TypefaceUtils.getTypeface(this.context, null));
      localViewHolder.head_image = ((ImageView)paramView.findViewById(2131099850));
      localViewHolder.tv_kuohao_left = ((TextView)paramView.findViewById(2131100452));
      localViewHolder.tv_kuohao_right = ((TextView)paramView.findViewById(2131100454));
      localViewHolder.tv_kuohao_left.setTypeface(TypefaceUtils.getTypeface(this.context, null));
      localViewHolder.tv_kuohao_right.setTypeface(TypefaceUtils.getTypeface(this.context, null));
      localViewHolder.tv_reply = ((TextView)paramView.findViewById(2131100458));
      localViewHolder.tv_email = ((TextView)paramView.findViewById(2131100360));
      localViewHolder.iv_email = ((ImageView)paramView.findViewById(2131100453));
      paramView.setTag(localViewHolder);
      localInvitationInfos = (InvitationInfos)this.list.get(paramInt);
      localViewHolder.tv_name.setText(localInvitationInfos.getReceive_from_name());
      if (localInvitationInfos.getTybe() == 1)
        localViewHolder.iv_email.setBackgroundResource(2130837597);
      if (localInvitationInfos.getTybe() == 2)
        localViewHolder.iv_email.setBackgroundResource(2130838097);
      if (localInvitationInfos.getTybe() == 3)
        localViewHolder.iv_email.setBackgroundResource(2130837681);
      localViewHolder.tv_email.setText(localInvitationInfos.getName());
      localViewHolder.tv_email.setTypeface(ModUtils.getTypeface(this.context));
      if ((localInvitationInfos.getSend_message() == null) || (localInvitationInfos.getSend_message().equals("")) || (localInvitationInfos.getSend_message().equals("null")))
        break label751;
      localViewHolder.tv_message.setText("附言 :" + localInvitationInfos.getSend_message());
      localViewHolder.tv_time.setText(ModUtils.msToDate(Long.valueOf(localInvitationInfos.getTime())));
      Log.i("picooc", "head_url==" + localInvitationInfos.getReceive_from_head_url());
      localViewHolder.tv_time.setText(ModUtils.zhuanHuan(Long.valueOf(localInvitationInfos.getTime())));
      if (localInvitationInfos.getSend_status_code() != 2)
        break label765;
      localViewHolder.tv_reply.setText("请求被拒绝");
      label523: if ((localInvitationInfos.getReceive_from_head_url() == null) || (localInvitationInfos.getReceive_from_head_url().equals("")) || (localInvitationInfos.getReceive_from_head_url().equals("null")))
        break label810;
      localImageLoader = this.imageLoader;
      if (!localInvitationInfos.getReceive_from_sex())
        break label802;
    }
    label802: for (int j = i; ; j = 2130838460)
    {
      localImageLoader.setStub_id(j);
      localViewHolder.head_image.setTag(localInvitationInfos.getReceive_from_head_url());
      localViewHolder.head_image.setAdjustViewBounds(true);
      localViewHolder.head_image.setScaleType(ImageView.ScaleType.FIT_XY);
      this.imageLoader.DisplayImage(localInvitationInfos.getReceive_from_head_url(), (Activity)this.context, localViewHolder.head_image);
      localViewHolder.tv_time.setText(ModUtils.msToDate(Long.valueOf(localInvitationInfos.getTime())));
      localViewHolder.tv_time.setText(ModUtils.zhuanHuan(Long.valueOf(localInvitationInfos.getTime())));
      Log.i("picooc", "nanme=" + localInvitationInfos.getReceive_from_name() + " --time==" + localInvitationInfos.getTime());
      if (localInvitationInfos.getSend_status_code() != 2)
        break label843;
      localViewHolder.tv_reply.setText("请求被拒绝");
      return paramView;
      localViewHolder = (ViewHolder)paramView.getTag();
      break;
      label751: localViewHolder.tv_message.setText("附言 : 无");
      break label440;
      label765: if (localInvitationInfos.getSend_status_code() == 1)
      {
        localViewHolder.tv_reply.setText("请求已通过");
        break label523;
      }
      localViewHolder.tv_reply.setText("等待回复");
      break label523;
    }
    label810: ImageView localImageView = localViewHolder.head_image;
    if (localInvitationInfos.getReceive_from_sex());
    while (true)
    {
      localImageView.setImageResource(i);
      break;
      i = 2130838460;
    }
    label843: if (localInvitationInfos.getSend_status_code() == 1)
    {
      localViewHolder.tv_reply.setText("请求已通过");
      return paramView;
    }
    localViewHolder.tv_reply.setText("等待回复");
    return paramView;
  }

  class ViewHolder
  {
    ImageView head_image;
    ImageView iv_email;
    TextView tv_email;
    TextView tv_kuohao_left;
    TextView tv_kuohao_right;
    TextView tv_message;
    TextView tv_name;
    TextView tv_reply;
    TextView tv_time;

    ViewHolder()
    {
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     InviteSendAdapter
 * JD-Core Version:    0.6.2
 */
