package com.picooc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.constant.ThemeConstant;
import com.picooc.db.OperationDB;
import com.picooc.domain.RoleBin;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.MtimerTask;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import org.achartengine.tools.ModUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class BindingYanZhengAct extends PicoocActivity
{
  public static final int FROM_BINDING_PHONENO_ACCOUNT_MANAGER = 1105;
  public static final int FROM_BINDING_PHONENO_ADD_REMOTE_ROLE = 1106;
  public static final int FROM_FAMILY_REGISTER = 1104;
  int a = 31;
  private MyApplication app;
  Button btn_regist;
  TextView btn_registText;
  private int from;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      BindingYanZhengAct localBindingYanZhengAct = BindingYanZhengAct.this;
      localBindingYanZhengAct.a = (-1 + localBindingYanZhengAct.a);
      if (BindingYanZhengAct.this.a == 0)
      {
        BindingYanZhengAct.this.timtask.stopTimer();
        BindingYanZhengAct.this.btn_regist.setVisibility(0);
        BindingYanZhengAct.this.btn_registText.setVisibility(8);
      }
      BindingYanZhengAct.this.btn_registText.setText("接收短信大约需要" + BindingYanZhengAct.this.a + "秒");
    }
  };
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(BindingYanZhengAct.this, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(BindingYanZhengAct.this, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      String str = localResponseEntity.getMethod();
      if (str.equals("bindingMyPhoneNumber"));
      while (true)
      {
        try
        {
          if (localResponseEntity.getResp().getString("step").equals("2"))
          {
            BindingYanZhengAct.this.app.getCurrentUser().setPhone_no(localResponseEntity.getResp().getString("phoneNumber"));
            BindingYanZhengAct.this.app.getCurrentUser().setHas_password(localResponseEntity.getResp().getInt("hasPassword"));
            if (OperationDB.updateUserDB(BindingYanZhengAct.this, BindingYanZhengAct.this.app.getCurrentUser()) > 0L)
            {
              if (BindingYanZhengAct.this.from == 1106)
              {
                localIntent2 = new Intent(BindingYanZhengAct.this, FamilyContactsAct.class);
                localIntent2.putExtra("key", ModUtils.YANZHENGMA);
                localIntent2.setFlags(67108864);
                BindingYanZhengAct.this.startActivity(localIntent2);
                BindingYanZhengAct.this.finish();
              }
            }
            else
            {
              PicoocLoading.dismissDialog();
              return;
            }
            Intent localIntent2 = new Intent(BindingYanZhengAct.this, LiftAccountManager.class);
            continue;
          }
          BindingYanZhengAct.this.btn_regist.setVisibility(8);
          BindingYanZhengAct.this.btn_registText.setVisibility(0);
          BindingYanZhengAct.this.a = 30;
          BindingYanZhengAct.this.timtask.startTimer();
          continue;
        }
        catch (JSONException localJSONException2)
        {
          localJSONException2.printStackTrace();
          continue;
        }
        if (str.equals("bindingPicooc"))
        {
          try
          {
            if (!localResponseEntity.getResp().getString("step").equals("2"))
              break label454;
            RoleBin localRoleBin = BindingYanZhengAct.this.app.getCurrentRole();
            localRoleBin.setFamily_type(1);
            localRoleBin.setPhone_no(BindingYanZhengAct.this.getIntent().getStringExtra("phone"));
            localRoleBin.setRemark_name(localRoleBin.getName());
            localRoleBin.setName(BindingYanZhengAct.this.getIntent().getStringExtra("nic"));
            localRoleBin.setIs_new_family(false);
            localRoleBin.setRemote_user_id(localResponseEntity.getResp().getLong("uid"));
            BindingYanZhengAct.this.app.setCurrentRole(localRoleBin);
            OperationDB.updateRoleDB(BindingYanZhengAct.this, localRoleBin);
            Intent localIntent1 = new Intent("com.picooc.local.family.upto.remote.family");
            BindingYanZhengAct.this.sendBroadcast(localIntent1);
            BindingYanZhengAct.this.setResult(222);
            BindingYanZhengAct.this.finish();
          }
          catch (JSONException localJSONException1)
          {
            localJSONException1.printStackTrace();
          }
          continue;
          label454: BindingYanZhengAct.this.btn_regist.setVisibility(8);
          BindingYanZhengAct.this.btn_registText.setVisibility(0);
          BindingYanZhengAct.this.a = 30;
          BindingYanZhengAct.this.timtask.startTimer();
        }
      }
    }
  };
  EditText phone;
  RelativeLayout.LayoutParams rParams;
  RelativeLayout relativelayout;
  private ThemeConstant themeConstant;
  MtimerTask timtask;
  RelativeLayout title;
  private TextView tv_yanzheng_tip;

  public void bangDingYanzhengma()
  {
    if (this.from == 1104)
    {
      PicoocLoading.showLoadingDialog(this);
      RequestEntity localRequestEntity2 = new RequestEntity("bindingPicooc", "5.1");
      localRequestEntity2.addParam("email", "");
      localRequestEntity2.addParam("phone", getIntent().getStringExtra("phone"));
      localRequestEntity2.addParam("password", getIntent().getStringExtra("pwd"));
      localRequestEntity2.addParam("nickname", getIntent().getStringExtra("nic"));
      localRequestEntity2.addParam("myUid", Long.valueOf(this.app.getCurrentRole().getUser_id()));
      localRequestEntity2.addParam("roleId", Long.valueOf(this.app.getCurrentRole().getRole_id()));
      localRequestEntity2.addParam("remarkName", this.app.getCurrentRole().getName());
      localRequestEntity2.addParam("code", "");
      localRequestEntity2.addParam("step", "1");
      HttpUtils.getJson(this, localRequestEntity2, this.httpHandler);
      return;
    }
    PicoocLoading.showLoadingDialog(this);
    RequestEntity localRequestEntity1 = new RequestEntity("bindingMyPhoneNumber", "5.1");
    localRequestEntity1.addParam("myUserID", Long.valueOf(this.app.getCurrentUser().getUser_id()));
    localRequestEntity1.addParam("password", getIntent().getStringExtra("pwd"));
    localRequestEntity1.addParam("phoneNumber", getIntent().getStringExtra("phone"));
    localRequestEntity1.addParam("receivedCode", "");
    localRequestEntity1.addParam("step", "1");
    HttpUtils.getJson(this, localRequestEntity1, this.httpHandler);
  }

  public void bangdingNextStep()
  {
    if (!this.phone.getText().toString().equals(""))
    {
      if (this.from == 1104)
      {
        PicoocLoading.showLoadingDialog(this);
        RequestEntity localRequestEntity2 = new RequestEntity("bindingPicooc", "5.1");
        localRequestEntity2.addParam("email", "");
        localRequestEntity2.addParam("phone", getIntent().getStringExtra("phone"));
        localRequestEntity2.addParam("password", getIntent().getStringExtra("pwd"));
        localRequestEntity2.addParam("nickname", getIntent().getStringExtra("nic"));
        localRequestEntity2.addParam("myUid", Long.valueOf(this.app.getCurrentRole().getUser_id()));
        localRequestEntity2.addParam("roleId", Long.valueOf(this.app.getCurrentRole().getRole_id()));
        localRequestEntity2.addParam("remarkName", this.app.getCurrentRole().getName());
        localRequestEntity2.addParam("code", this.phone.getText().toString().trim());
        localRequestEntity2.addParam("step", "2");
        HttpUtils.getJson(this, localRequestEntity2, this.httpHandler);
        return;
      }
      PicoocLoading.showLoadingDialog(this);
      RequestEntity localRequestEntity1 = new RequestEntity("bindingMyPhoneNumber", "5.1");
      localRequestEntity1.addParam("myUserID", Long.valueOf(this.app.getCurrentUser().getUser_id()));
      localRequestEntity1.addParam("password", getIntent().getStringExtra("pwd"));
      localRequestEntity1.addParam("phoneNumber", getIntent().getStringExtra("phone"));
      localRequestEntity1.addParam("receivedCode", this.phone.getText().toString());
      localRequestEntity1.addParam("step", "2");
      HttpUtils.getJson(this, localRequestEntity1, this.httpHandler);
      return;
    }
    PicoocToast.showToast(this, "请填写验证码");
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100359:
      finish();
      overridePendingTransition(2130968594, 2130968597);
      return;
    case 2131099828:
      bangdingNextStep();
      return;
    case 2131100368:
    }
    bangDingYanzhengma();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903179);
    this.title = ((RelativeLayout)findViewById(2131099699));
    this.relativelayout = ((RelativeLayout)findViewById(2131099841));
    ((MyApplication)getApplication()).addActivity(this);
    this.from = getIntent().getIntExtra("from", 1104);
    this.app = ((MyApplication)getApplicationContext());
    this.phone = ((EditText)findViewById(2131100303));
    this.phone.setTypeface(TypefaceUtils.getTypeface(this, null));
    TextView localTextView = (TextView)findViewById(2131100367);
    localTextView.setTypeface(TypefaceUtils.getTypeface(null, null));
    localTextView.setText(getIntent().getStringExtra("phone"));
    localTextView.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.btn_registText = ((TextView)findViewById(2131100369));
    this.btn_registText.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.btn_regist = ((Button)findViewById(2131100368));
    if (this.from == 1106)
    {
      this.rParams = new RelativeLayout.LayoutParams(-1, -2);
      this.rParams.setMargins(10, 10, 10, 3);
      this.title.setLayoutParams(this.rParams);
      this.relativelayout.setBackgroundResource(2130837518);
      this.tv_yanzheng_tip = ((TextView)findViewById(2131100366));
      SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder("我们已发送验证码短信到这个号码");
      localSpannableStringBuilder.setSpan(new ForegroundColorSpan(-7102519), 5, 10, 34);
      this.tv_yanzheng_tip.setText(localSpannableStringBuilder);
    }
    while (true)
    {
      this.timtask = new MtimerTask(this.handler, 1000, Boolean.valueOf(true), 40);
      this.timtask.startTimer();
      return;
      this.title.setBackgroundResource(2130838437);
      setTheme();
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

  public void setTheme()
  {
    this.themeConstant = new ThemeConstant(this);
    if (this.themeConstant.getbgResource().intValue() == 2130837526)
    {
      this.relativelayout.setBackgroundResource(2130837526);
      return;
    }
    if (this.themeConstant.getbgResource().intValue() == 2130837527)
    {
      this.relativelayout.setBackgroundResource(2130837527);
      return;
    }
    this.relativelayout.setBackgroundResource(2130837525);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BindingYanZhengAct
 * JD-Core Version:    0.6.2
 */
