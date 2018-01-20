package com.picooc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.picooc.adapter.InviteReceiveAdapter;
import com.picooc.adapter.InviteSendAdapter;
import com.picooc.db.OperationDB;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.loading.PicoocToast;
import org.json.JSONObject;

public class InviteAct extends Activity
{
  private MyApplication app;
  private int clickID = 1;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocToast.showToast(InviteAct.this, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(InviteAct.this, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      localResponseEntity.getMethod().equals("uploadMyPhoneNumbers");
    }
  };
  private ListView list_receive;
  private ListView list_send;
  private LinearLayout ll_no_invite;
  private LinearLayout ll_receive;
  private LinearLayout ll_send;
  private InviteReceiveAdapter receiveAdapter;
  private InviteSendAdapter sendAdapter;
  private TextView tv_receive_send_word;
  TextView tv_receive_tip;
  TextView tv_send_tip;

  public void finish()
  {
    super.finish();
    Intent localIntent = new Intent();
    localIntent.setAction("com.picooc.receive.push.refresh.ui");
    sendBroadcast(localIntent);
    overridePendingTransition(2130968576, 2130968581);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131100438:
    case 2131100441:
      do
      {
        do
          return;
        while (this.clickID == 1);
        this.ll_receive.setBackgroundResource(2130837970);
        this.ll_send.setBackgroundResource(2130837973);
        this.list_send.setVisibility(8);
        this.receiveAdapter.notifyDataSetChanged();
        this.list_receive.setVisibility(0);
        if (this.receiveAdapter.getCount() < 1)
        {
          this.tv_receive_send_word.setText("没有新收到的邀请~");
          this.ll_no_invite.setVisibility(0);
        }
        while (true)
        {
          this.clickID = 1;
          return;
          this.ll_no_invite.setVisibility(8);
        }
      }
      while (this.clickID == 2);
      this.ll_send.setBackgroundResource(2130837972);
      this.ll_receive.setBackgroundResource(2130837971);
      this.list_receive.setVisibility(8);
      this.list_send.setVisibility(0);
      if (this.sendAdapter == null)
        this.sendAdapter = new InviteSendAdapter(this, OperationDB.selectInvitation_infos(this, this.app.getCurrentUser().getUser_id(), 1, "user_id"));
      this.list_send.setAdapter(this.sendAdapter);
      this.sendAdapter.notifyDataSetChanged();
      updateTime();
      if (this.sendAdapter.getCount() < 1)
      {
        this.tv_receive_send_word.setText("没有新发出的邀请~");
        this.ll_no_invite.setVisibility(0);
      }
      while (true)
      {
        this.clickID = 2;
        return;
        this.ll_no_invite.setVisibility(8);
      }
    case 2131100359:
    }
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903187);
    this.ll_no_invite = ((LinearLayout)findViewById(2131100444));
    this.tv_receive_tip = ((TextView)findViewById(2131100440));
    this.tv_send_tip = ((TextView)findViewById(2131100443));
    this.ll_send = ((LinearLayout)findViewById(2131100441));
    this.ll_receive = ((LinearLayout)findViewById(2131100438));
    this.tv_receive_send_word = ((TextView)findViewById(2131100446));
    this.list_receive = ((ListView)findViewById(2131100447));
    this.app = ((MyApplication)getApplicationContext());
    this.receiveAdapter = new InviteReceiveAdapter(this, OperationDB.selectInvitation_infos(this, this.app.getCurrentUser().getUser_id(), 0, "remote_uid"));
    this.list_receive.setAdapter(this.receiveAdapter);
    this.list_send = ((ListView)findViewById(2131100448));
    if (this.list_receive.getCount() <= 0)
    {
      this.tv_receive_send_word.setText("没有新收到的邀请~");
      this.ll_no_invite.setVisibility(0);
    }
    if (OperationDB.selectCount(this, true, 0L, SharedPreferenceUtils.getSendAndReceiveTime(this, Boolean.valueOf(false), this.app.getCurrentUser().getUser_id()), this.app.getCurrentUser().getUser_id(), "remote_uid") > 0)
    {
      this.tv_receive_tip.setAlpha(1.0F);
      this.tv_receive_tip.setText(this.list_receive.getCount());
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          InviteAct.this.tv_receive_tip.setAlpha(0.0F);
          new AnimUtils(InviteAct.this).missAnima(InviteAct.this.tv_receive_tip, 800L);
        }
      }
      , 2000L);
      int i = OperationDB.selectCount(this, false, SharedPreferenceUtils.getSendAndReceiveTime(this, Boolean.valueOf(true), this.app.getCurrentUser().getUser_id()), 0L, this.app.getCurrentUser().getUser_id(), "user_id");
      if (i <= 0)
        break label405;
      this.tv_send_tip.setAlpha(1.0F);
      this.tv_send_tip.setText(i);
    }
    while (true)
    {
      SharedPreferenceUtils.saveSendTim(this, this.app.getCurrentUser().getUser_id(), null, Long.valueOf(System.currentTimeMillis()));
      return;
      this.tv_receive_tip.setAlpha(0.0F);
      break;
      label405: this.tv_send_tip.setAlpha(0.0F);
    }
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    finish();
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void updateTime()
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        if (InviteAct.this.tv_send_tip.getAlpha() > 0.0F)
          InviteAct.this.tv_send_tip.setAlpha(0.0F);
        OperationDB.updateSendMessage(InviteAct.this, InviteAct.this.app.getCurrentUser().getUser_id());
        SharedPreferenceUtils.saveSendTim(InviteAct.this, InviteAct.this.app.getCurrentUser().getUser_id(), Long.valueOf(System.currentTimeMillis()), null);
        RequestEntity localRequestEntity = new RequestEntity(HttpUtils.pInvitationUpdateToRead, "5.2");
        localRequestEntity.addParam("user_id", Long.valueOf(InviteAct.this.app.getCurrentUser().getUser_id()));
        localRequestEntity.addParam("time", Long.valueOf(System.currentTimeMillis()));
        HttpUtils.getJson(InviteAct.this, localRequestEntity, InviteAct.this.httpHandler);
      }
    }
    , 500L);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     InviteAct
 * JD-Core Version:    0.6.2
 */
