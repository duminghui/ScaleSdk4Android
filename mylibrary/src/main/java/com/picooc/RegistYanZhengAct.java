package com.picooc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.android.pushservice.PushManager;
import com.picooc.db.OperationDB;
import com.picooc.domain.UserBin;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.MtimerTask;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import org.json.JSONException;
import org.json.JSONObject;

public class RegistYanZhengAct extends PicoocActivity
{
  int a = 31;
  private MyApplication app;
  Button btn_regist;
  TextView btn_registText;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      RegistYanZhengAct localRegistYanZhengAct = RegistYanZhengAct.this;
      localRegistYanZhengAct.a = (-1 + localRegistYanZhengAct.a);
      if (RegistYanZhengAct.this.a == 0)
      {
        RegistYanZhengAct.this.timtask.stopTimer();
        RegistYanZhengAct.this.btn_regist.setVisibility(0);
        RegistYanZhengAct.this.btn_registText.setVisibility(8);
      }
      RegistYanZhengAct.this.btn_registText.setText("接收短信大约需要" + RegistYanZhengAct.this.a + "秒");
    }
  };
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(RegistYanZhengAct.this, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(RegistYanZhengAct.this, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      String str = localResponseEntity.getMethod();
      if (str.equals("userRegister"));
      while (true)
      {
        try
        {
          if (localResponseEntity.getResp().getString("step").equals("2"))
          {
            long l = localResponseEntity.getResp().getLong("userID");
            UserBin localUserBin = new UserBin(l, "", 0L, 0L, "", "", "", RegistYanZhengAct.this.getIntent().getStringExtra("phone"), "");
            localUserBin.setHas_password(true);
            SharedPreferenceUtils.saveHuanYingboolen(RegistYanZhengAct.this, l);
            if (OperationDB.insertUserDB(RegistYanZhengAct.this, localUserBin) > 0L)
            {
              SharedPreferenceUtils.putValue(RegistYanZhengAct.this, "user-Info", "user_id", Long.valueOf(l));
              PushManager.startWork(RegistYanZhengAct.this.getApplicationContext(), 0, "7Y4L3bMxLgil7WbPuDyaaPCa");
              Intent localIntent2 = new Intent(RegistYanZhengAct.this, WriteReferenAct.class);
              localIntent2.putExtra("userID", l);
              RegistYanZhengAct.this.startActivity(localIntent2);
              RegistYanZhengAct.this.finish();
            }
            PicoocLoading.dismissDialog();
            return;
          }
          RegistYanZhengAct.this.btn_regist.setVisibility(8);
          RegistYanZhengAct.this.btn_registText.setVisibility(0);
          RegistYanZhengAct.this.a = 30;
          RegistYanZhengAct.this.timtask.startTimer();
          continue;
        }
        catch (JSONException localJSONException2)
        {
          localJSONException2.printStackTrace();
          continue;
        }
        if (str.equals("bindingMyPhoneNumber"))
        {
          try
          {
            if (!localResponseEntity.getResp().getString("step").equals("2"))
              break label433;
            RegistYanZhengAct.this.app.getCurrentUser().setPhone_no(localResponseEntity.getResp().getString("phoneNumber"));
            if (OperationDB.updateUserDB(RegistYanZhengAct.this, RegistYanZhengAct.this.app.getCurrentUser()) <= 0L)
              continue;
            RegistYanZhengAct.this.app.getCurrentUser().setPhone_no(RegistYanZhengAct.this.getIntent().getStringExtra("phone"));
            Intent localIntent1 = new Intent(RegistYanZhengAct.this, LiftAccountManager.class);
            localIntent1.setFlags(67108864);
            RegistYanZhengAct.this.startActivity(localIntent1);
            RegistYanZhengAct.this.finish();
          }
          catch (JSONException localJSONException1)
          {
            localJSONException1.printStackTrace();
          }
          continue;
          label433: RegistYanZhengAct.this.btn_regist.setVisibility(8);
          RegistYanZhengAct.this.btn_registText.setVisibility(0);
          RegistYanZhengAct.this.a = 30;
          RegistYanZhengAct.this.timtask.startTimer();
        }
      }
    }
  };
  int key = 0;
  int key_z = 0;
  EditText phone;
  RelativeLayout.LayoutParams reLayoutParams;
  RelativeLayout relayout;
  MtimerTask timtask;
  RelativeLayout title;

  public void bangDingYanzhengma()
  {
    PicoocLoading.showLoadingDialog(this);
    RequestEntity localRequestEntity = new RequestEntity("bindingMyPhoneNumber", "5.1");
    localRequestEntity.addParam("myUserID", Long.valueOf(this.app.getCurrentUser().getUser_id()));
    localRequestEntity.addParam("password", getIntent().getStringExtra("pwd"));
    localRequestEntity.addParam("phoneNumber", getIntent().getStringExtra("phone"));
    localRequestEntity.addParam("receivedCode", "");
    localRequestEntity.addParam("step", "1");
    HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
  }

  public void bangdingNextStep()
  {
    if (!this.phone.getText().toString().equals(""))
    {
      PicoocLoading.showLoadingDialog(this);
      RequestEntity localRequestEntity = new RequestEntity("bindingMyPhoneNumber", "5.1");
      localRequestEntity.addParam("myUserID", Long.valueOf(this.app.getCurrentUser().getUser_id()));
      localRequestEntity.addParam("password", getIntent().getStringExtra("pwd"));
      localRequestEntity.addParam("phoneNumber", getIntent().getStringExtra("phone"));
      localRequestEntity.addParam("receivedCode", this.phone.getText().toString());
      localRequestEntity.addParam("step", "2");
      HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
      return;
    }
    PicoocToast.showToast(this, "请填写验证码");
  }

  public void nextStep()
  {
    if (!this.phone.getText().toString().equals(""))
    {
      PicoocLoading.showLoadingDialog(this);
      RequestEntity localRequestEntity = new RequestEntity("userRegister", "5.1");
      localRequestEntity.addParam("email", "");
      localRequestEntity.addParam("password", getIntent().getStringExtra("pwd"));
      localRequestEntity.addParam("phone", getIntent().getStringExtra("phone"));
      localRequestEntity.addParam("code", this.phone.getText().toString());
      localRequestEntity.addParam("step", "2");
      localRequestEntity.addParam("baiduUserID", "");
      localRequestEntity.addParam("baiduChannelID", "");
      HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
      return;
    }
    PicoocToast.showToast(this, "请填写验证码");
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131100359:
    case 2131099828:
    case 2131100368:
    }
    do
    {
      do
      {
        return;
        if (this.key == 1)
        {
          finish();
          overridePendingTransition(2130968594, 2130968597);
          return;
        }
        finish();
        return;
        if (this.key == 1)
        {
          nextStep();
          return;
        }
      }
      while (this.key != 2);
      bangdingNextStep();
      return;
      if (this.key == 1)
      {
        registYanzhengma();
        return;
      }
    }
    while (this.key != 2);
    bangDingYanzhengma();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903217);
    this.relayout = ((RelativeLayout)findViewById(2131099841));
    this.title = ((RelativeLayout)findViewById(2131099699));
    ((MyApplication)getApplication()).addActivity(this);
    this.key = getIntent().getIntExtra("keyCode", 0);
    this.key_z = getIntent().getIntExtra("key", 0);
    this.app = ((MyApplication)getApplicationContext());
    this.phone = ((EditText)findViewById(2131100303));
    this.phone.setTypeface(TypefaceUtils.getTypeface(this, null));
    TextView localTextView = (TextView)findViewById(2131100367);
    localTextView.setTypeface(TypefaceUtils.getTypeface(null, null));
    localTextView.setText("您的手机 " + getIntent().getStringExtra("phone") + "将收到一条4位验证短信");
    localTextView.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.btn_registText = ((TextView)findViewById(2131100369));
    this.btn_registText.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.btn_regist = ((Button)findViewById(2131100368));
    this.timtask = new MtimerTask(this.handler, 1000, Boolean.valueOf(true), 40);
    this.timtask.startTimer();
    if (this.key_z == 301)
    {
      this.relayout.setBackgroundResource(2130837525);
      this.title.setBackgroundResource(2130838437);
      this.reLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
      this.reLayoutParams.setMargins(0, 0, 0, 0);
      this.title.setLayoutParams(this.reLayoutParams);
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    ((MyApplication)getApplication()).removeActivity(this);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      finish();
      overridePendingTransition(2130968594, 2130968597);
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void registYanzhengma()
  {
    PicoocLoading.showLoadingDialog(this);
    RequestEntity localRequestEntity = new RequestEntity("userRegister", "5.1");
    localRequestEntity.addParam("email", "");
    localRequestEntity.addParam("password", getIntent().getStringExtra("pwd"));
    localRequestEntity.addParam("phone", getIntent().getStringExtra("phone"));
    localRequestEntity.addParam("code", "");
    localRequestEntity.addParam("step", "1");
    localRequestEntity.addParam("baiduUserID", "");
    localRequestEntity.addParam("baiduChannelID", "");
    HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     RegistYanZhengAct
 * JD-Core Version:    0.6.2
 */
