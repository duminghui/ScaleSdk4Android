package com.picooc;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.baidu.android.pushservice.PushManager;
import com.picooc.db.OperationDB;
import com.picooc.domain.UserBin;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.StringUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import org.achartengine.tools.ModUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RegistAct extends PicoocActivity
{
  private int Height_pwd = 0;
  private AnimUtils anima;
  private Button btn_regist;
  public int distance = 0;
  private EditText et_phone_email;
  private EditText et_pwd;
  private EditText et_pwdAgain;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(RegistAct.this, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(RegistAct.this, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      if (localResponseEntity.getMethod().equals("userRegister"));
      try
      {
        long l = localResponseEntity.getResp().getLong("userID");
        SharedPreferenceUtils.saveHuanYingboolen(RegistAct.this, l);
        UserBin localUserBin = new UserBin(l, RegistAct.this.et_phone_email.getText().toString().trim(), 0L, 0L, "", "", "", "", "");
        if (OperationDB.insertUserDB(RegistAct.this, localUserBin) > 0L)
        {
          SharedPreferenceUtils.putValue(RegistAct.this, "user-Info", "user_id", Long.valueOf(l));
          Intent localIntent = new Intent(RegistAct.this, WriteReferenAct.class);
          localIntent.putExtra("userID", l);
          localIntent.putExtra("username", RegistAct.this.et_phone_email.getText().toString());
          RegistAct.this.startActivity(localIntent);
          RegistAct.this.finish();
        }
        PushManager.startWork(RegistAct.this.getApplicationContext(), 0, "7Y4L3bMxLgil7WbPuDyaaPCa");
        PicoocLoading.dismissDialog();
        return;
      }
      catch (JSONException localJSONException)
      {
        while (true)
          localJSONException.printStackTrace();
      }
    }
  };
  private JsonHttpResponseHandler httpHandlerPhone = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(RegistAct.this, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(RegistAct.this, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      localResponseEntity.getMethod();
      PicoocLoading.dismissDialog();
      RegistAct.this.intent = new Intent(RegistAct.this, RegistYanZhengAct.class);
      RegistAct.this.intent.putExtra("keyCode", 1);
      RegistAct.this.intent.putExtra("phone", RegistAct.this.et_phone_email.getText().toString());
      RegistAct.this.intent.putExtra("pwd", RegistAct.this.et_pwd.getText().toString());
      RegistAct.this.startActivity(RegistAct.this.intent);
      RegistAct.this.overridePendingTransition(2130968596, 2130968595);
    }
  };
  private Intent intent;
  private Drawable left_drawable;
  private int onClickId = 1;
  private int pwdAgain_marginTop = 0;
  private int regist_marginTop;
  private Resources resources;
  private String saveEmailStr;
  private String savePhoneStr;
  private TextView tv_email_regist;
  private TextView tv_phone_regist;

  private void initFocusChange()
  {
    this.et_pwd.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          RegistAct.this.et_pwd.setHint(2131361807);
          RegistAct.this.openPwdAgainAnim(400);
        }
      }
    });
  }

  private void registerEmail()
  {
    String str1 = this.et_phone_email.getText().toString().trim();
    boolean bool1 = StringUtils.isEmail(str1);
    String str2 = this.et_pwd.getText().toString().trim();
    boolean bool2 = StringUtils.isPassword(str2);
    if (this.et_pwdAgain.getText().toString().equals(str2));
    for (int i = 1; !bool1; i = 0)
    {
      PicoocToast.showToast(this, "邮箱格式错误!");
      return;
    }
    if (!bool2)
    {
      PicoocToast.showToast(this, "密码格式错误!");
      return;
    }
    if (i == 0)
    {
      PicoocToast.showToast(this, "两次输入的密码不一致!");
      return;
    }
    PicoocLoading.showLoadingDialog(this);
    RequestEntity localRequestEntity = new RequestEntity("userRegister", "5.1");
    localRequestEntity.addParam("email", str1);
    localRequestEntity.addParam("password", str2);
    localRequestEntity.addParam("phone", "");
    localRequestEntity.addParam("code", "");
    localRequestEntity.addParam("step", "");
    localRequestEntity.addParam("baiduUserID", "");
    localRequestEntity.addParam("baiduChannelID", "");
    HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
  }

  private void registerPhone()
  {
    String str1 = this.et_phone_email.getText().toString().trim();
    boolean bool1 = ModUtils.isMobileNO(str1);
    String str2 = this.et_pwd.getText().toString().trim();
    boolean bool2 = StringUtils.isPassword(str2);
    if (this.et_pwdAgain.getText().toString().equals(str2));
    for (int i = 1; !bool1; i = 0)
    {
      PicoocToast.showToast(this, "手机号格式错误!");
      return;
    }
    if (!bool2)
    {
      PicoocToast.showToast(this, "密码格式错误!");
      return;
    }
    if (i == 0)
    {
      PicoocToast.showToast(this, "两次输入的密码不一致!");
      return;
    }
    PicoocLoading.showLoadingDialog(this);
    RequestEntity localRequestEntity = new RequestEntity("userRegister", "5.1");
    localRequestEntity.addParam("email", "");
    localRequestEntity.addParam("password", str2);
    localRequestEntity.addParam("phone", str1);
    localRequestEntity.addParam("code", "");
    localRequestEntity.addParam("step", "1");
    localRequestEntity.addParam("baiduUserID", "");
    localRequestEntity.addParam("baiduChannelID", "");
    HttpUtils.getJson(this, localRequestEntity, this.httpHandlerPhone);
  }

  public void closePwdAgainAnim(int paramInt)
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        RegistAct.this.et_pwdAgain.setAlpha(0.0F);
      }
    }
    , paramInt);
    this.anima.downMove(this.btn_regist, 0, -this.distance, paramInt);
    this.anima.missAnima(this.et_pwdAgain, paramInt);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131100431:
    case 2131100624:
    case 2131100625:
      do
      {
        do
        {
          return;
          this.intent = new Intent(this, FirstAct.class);
          this.intent.addFlags(67108864);
          startActivity(this.intent);
          finish();
          overridePendingTransition(2130968594, 2130968597);
          return;
        }
        while (this.onClickId == 1);
        this.saveEmailStr = this.et_phone_email.getText().toString();
        this.et_pwd.setText("");
        this.et_pwdAgain.setText("");
        this.et_phone_email.setFocusable(true);
        this.et_phone_email.setFocusableInTouchMode(true);
        this.et_phone_email.requestFocus();
        this.et_phone_email.requestFocusFromTouch();
        this.et_phone_email.setInputType(3);
        this.et_phone_email.setText(this.savePhoneStr);
        if (this.et_pwdAgain.getAlpha() != 0.0F)
          closePwdAgainAnim(400);
        this.tv_phone_regist.setBackgroundResource(2130838144);
        this.tv_email_regist.setBackgroundResource(2130838142);
        this.et_phone_email.setHint(2131361802);
        this.et_phone_email.setFocusable(true);
        this.et_phone_email.setFocusableInTouchMode(true);
        this.et_phone_email.requestFocus();
        this.et_phone_email.requestFocusFromTouch();
        this.left_drawable = this.resources.getDrawable(2130838095);
        this.left_drawable.setBounds(0, 0, this.left_drawable.getMinimumWidth(), this.left_drawable.getMinimumHeight());
        this.et_phone_email.setCompoundDrawables(this.left_drawable, null, null, null);
        this.onClickId = 1;
        return;
      }
      while (this.onClickId == 2);
      this.savePhoneStr = this.et_phone_email.getText().toString();
      this.et_pwd.setText("");
      this.et_pwdAgain.setText("");
      this.et_phone_email.setFocusable(true);
      this.et_phone_email.setFocusableInTouchMode(true);
      this.et_phone_email.requestFocus();
      this.et_phone_email.requestFocusFromTouch();
      this.et_phone_email.setInputType(32);
      this.et_phone_email.setText(this.saveEmailStr);
      if (this.et_pwdAgain.getAlpha() != 0.0F)
        closePwdAgainAnim(400);
      this.tv_email_regist.setBackgroundResource(2130838141);
      this.tv_phone_regist.setBackgroundResource(2130838145);
      this.et_phone_email.setHint(2131361798);
      this.left_drawable = this.resources.getDrawable(2130838038);
      this.left_drawable.setBounds(0, 0, this.left_drawable.getMinimumWidth(), this.left_drawable.getMinimumHeight());
      this.et_phone_email.setCompoundDrawables(this.left_drawable, null, null, null);
      this.onClickId = 2;
      return;
    case 2131100368:
    }
    if (this.onClickId == 1)
    {
      registerPhone();
      return;
    }
    registerEmail();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903215);
    ((MyApplication)getApplication()).addActivity(this);
    this.resources = getResources();
    this.anima = new AnimUtils(this);
    this.tv_phone_regist = ((TextView)findViewById(2131100624));
    this.tv_email_regist = ((TextView)findViewById(2131100625));
    this.et_phone_email = ((EditText)findViewById(2131099831));
    this.et_phone_email.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.btn_regist = ((Button)findViewById(2131100368));
    this.et_pwd = ((EditText)findViewById(2131100621));
    this.et_pwd.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.et_pwdAgain = ((EditText)findViewById(2131100622));
    this.et_pwdAgain.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.regist_marginTop = ((RelativeLayout.LayoutParams)this.btn_regist.getLayoutParams()).topMargin;
    this.pwdAgain_marginTop = ((RelativeLayout.LayoutParams)this.et_pwdAgain.getLayoutParams()).topMargin;
    int i = View.MeasureSpec.makeMeasureSpec(0, 0);
    int j = View.MeasureSpec.makeMeasureSpec(0, 0);
    this.et_pwdAgain.measure(i, j);
    this.Height_pwd = this.et_pwdAgain.getMeasuredHeight();
    initFocusChange();
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

  public void openPwdAgainAnim(int paramInt)
  {
    this.et_pwdAgain.setAlpha(0.0F);
    this.et_pwdAgain.setVisibility(0);
    this.distance = (this.Height_pwd + this.regist_marginTop - (this.regist_marginTop - this.pwdAgain_marginTop));
    this.anima.downMove(this.btn_regist, -this.distance, 0, paramInt);
    this.anima.showAnima(this.et_pwdAgain, paramInt);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     RegistAct
 * JD-Core Version:    0.6.2
 */
