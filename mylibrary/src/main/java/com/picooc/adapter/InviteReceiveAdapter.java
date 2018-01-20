package com.picooc.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.picooc.MyApplication;
import com.picooc.db.OperationDB;
import com.picooc.domain.InvitationInfos;
import com.picooc.internet.AsyncMessageUtils;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.ImageLoader;
import com.picooc.widget.anyncImageView.AsyncImageView;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import java.util.List;
import org.achartengine.tools.ModUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class InviteReceiveAdapter extends BaseAdapter
{
  private MyApplication app;
  private int childLayout = 2130903188;
  private Context context;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast((Activity)InviteReceiveAdapter.this.context, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      localResponseEntity.getMethod();
      PicoocLoading.dismissDialog();
      PicoocToast.showToast((Activity)InviteReceiveAdapter.this.context, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      PicoocLoading.dismissDialog();
      String str = localResponseEntity.getMethod();
      if (str.equals("invitationDecision"))
      {
        PicoocToast.showToast((Activity)InviteReceiveAdapter.this.context, localResponseEntity.getMessage());
        OperationDB.deleteRecevieMessage(InviteReceiveAdapter.this.context, InviteReceiveAdapter.this.remote_id, InviteReceiveAdapter.this.app.getCurrentUser().getUser_id());
        InviteReceiveAdapter.this.list.remove(InviteReceiveAdapter.this.pposition);
        InviteReceiveAdapter.this.notifyDataSetChanged();
        localJSONObject = localResponseEntity.getResp();
      }
      while (!str.equals("getRoles"))
        try
        {
          JSONObject localJSONObject;
          InviteReceiveAdapter.this.roleID = localJSONObject.getLong("remoteMainRoleId");
          AsyncMessageUtils.loadBodyIndexFromServer(InviteReceiveAdapter.this.context, InviteReceiveAdapter.this.roleID, true);
          if (localJSONObject.getInt("flag") == 1)
            AsyncMessageUtils.loadRoleAndRoleInfosFromServer(InviteReceiveAdapter.this.context, InviteReceiveAdapter.this.app.getCurrentUser().getUser_id(), InviteReceiveAdapter.this.httpHandler);
          return;
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          return;
        }
      long l = ((Long)SharedPreferenceUtils.getValue(InviteReceiveAdapter.this.context.getApplicationContext(), "user-Info", "user_id", Long.class)).longValue();
      OperationDB.updateAllRolesAndRoleInfos(InviteReceiveAdapter.this.context, localResponseEntity.getResp(), l);
      Intent localIntent = new Intent();
      localIntent.setAction("com.picooc.invitation.refresh.ui");
      InviteReceiveAdapter.this.context.sendBroadcast(localIntent);
    }
  };
  public ImageLoader imageLoader;
  List<InvitationInfos> list;
  private LayoutInflater mInflater;
  private int pposition = 0;
  private String remote_id = "";
  private long roleID = 0L;

  public InviteReceiveAdapter(Context paramContext, List<InvitationInfos> paramList)
  {
    this.mInflater = LayoutInflater.from(paramContext);
    this.context = paramContext;
    this.list = paramList;
    if (this.imageLoader == null)
      this.imageLoader = new ImageLoader(paramContext);
    this.app = ((MyApplication)paramContext.getApplicationContext());
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

  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int i = 2130838457;
    ViewHolder localViewHolder;
    final InvitationInfos localInvitationInfos;
    label425: ImageLoader localImageLoader;
    if (paramView == null)
    {
      paramView = this.mInflater.inflate(this.childLayout, null);
      localViewHolder = new ViewHolder();
      localViewHolder.tv_name = ((TextView)paramView.findViewById(2131099842));
      localViewHolder.tv_name.setTypeface(TypefaceUtils.getTypeface(this.context, null));
      localViewHolder.tv_email = ((TextView)paramView.findViewById(2131100360));
      localViewHolder.tv_email.setTypeface(TypefaceUtils.getTypeface(this.context, null));
      localViewHolder.iv_email = ((ImageView)paramView.findViewById(2131100453));
      localViewHolder.tv_time = ((TextView)paramView.findViewById(2131100449));
      localViewHolder.tv_time.setTypeface(TypefaceUtils.getTypeface(this.context, null));
      localViewHolder.tv_message = ((TextView)paramView.findViewById(2131100455));
      localViewHolder.tv_message.setTypeface(TypefaceUtils.getTypeface(this.context, null));
      localViewHolder.head_image = ((AsyncImageView)paramView.findViewById(2131099850));
      localViewHolder.iv_set_ture = ((Button)paramView.findViewById(2131100457));
      localViewHolder.iv_close = ((Button)paramView.findViewById(2131100456));
      paramView.setTag(localViewHolder);
      localInvitationInfos = (InvitationInfos)this.list.get(paramInt);
      localViewHolder.iv_set_ture.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          InviteReceiveAdapter.this.goNext("accept", localInvitationInfos.getUser_id());
          InviteReceiveAdapter.this.pposition = paramInt;
        }
      });
      localViewHolder.iv_close.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          InviteReceiveAdapter.this.goNext("reject", localInvitationInfos.getUser_id());
        }
      });
      localViewHolder.tv_name.setText(localInvitationInfos.getReceive_from_name());
      if (localInvitationInfos.getTybe() == 1)
        localViewHolder.iv_email.setBackgroundResource(2130837597);
      if (localInvitationInfos.getTybe() == 2)
        localViewHolder.iv_email.setBackgroundResource(2130838097);
      if (localInvitationInfos.getTybe() == 3)
        localViewHolder.iv_email.setBackgroundResource(2130837681);
      localViewHolder.tv_email.setText(localInvitationInfos.getName());
      localViewHolder.tv_email.setTypeface(ModUtils.getTypeface(this.context));
      if ((localInvitationInfos.getReceive_message() == null) || (localInvitationInfos.getReceive_message().equals("")) || (localInvitationInfos.getReceive_message().equals("null")))
        break label616;
      localViewHolder.tv_message.setText("附言 :" + localInvitationInfos.getReceive_message());
      if ((localInvitationInfos.getReceive_from_head_url() == null) || (localInvitationInfos.getReceive_from_head_url().equals("")) || (localInvitationInfos.getReceive_from_head_url().equals("null")))
        break label638;
      localImageLoader = this.imageLoader;
      if (!localInvitationInfos.getReceive_from_sex())
        break label630;
    }
    label616: label630: for (int j = i; ; j = 2130838460)
    {
      localImageLoader.setStub_id(j);
      localViewHolder.head_image.setTag(localInvitationInfos.getReceive_from_head_url());
      localViewHolder.head_image.setAdjustViewBounds(true);
      localViewHolder.head_image.setScaleType(ImageView.ScaleType.FIT_XY);
      this.imageLoader.DisplayImage(localInvitationInfos.getReceive_from_head_url(), (Activity)this.context, localViewHolder.head_image);
      localViewHolder.tv_time.setText(ModUtils.zhuanHuan(Long.valueOf(localInvitationInfos.getTime())));
      Log.i("picooc", "nanme=" + localInvitationInfos.getReceive_from_name() + " --time==" + localInvitationInfos.getTime());
      return paramView;
      localViewHolder = (ViewHolder)paramView.getTag();
      break;
      localViewHolder.tv_message.setText("附言 : 无");
      break label425;
    }
    label638: AsyncImageView localAsyncImageView = localViewHolder.head_image;
    if (localInvitationInfos.getReceive_from_sex());
    while (true)
    {
      localAsyncImageView.setImageResource(i);
      break;
      i = 2130838460;
    }
  }

  public void goNext(String paramString1, String paramString2)
  {
    PicoocLoading.showLoadingDialog(this.context);
    this.remote_id = paramString2;
    PicoocLoading.showLoadingDialog(this.context);
    RequestEntity localRequestEntity = new RequestEntity("invitationDecision", "5.2");
    localRequestEntity.addParam("user_id", Long.valueOf(this.app.getCurrentUser().getUser_id()));
    localRequestEntity.addParam("remote_user_id", paramString2);
    localRequestEntity.addParam("act", paramString1);
    HttpUtils.getJson(this.context, localRequestEntity, this.httpHandler);
  }

  class ViewHolder
  {
    AsyncImageView head_image;
    Button iv_close;
    ImageView iv_email;
    Button iv_set_ture;
    TextView tv_email;
    TextView tv_message;
    TextView tv_name;
    TextView tv_time;

    ViewHolder()
    {
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     InviteReceiveAdapter
 * JD-Core Version:    0.6.2
 */
