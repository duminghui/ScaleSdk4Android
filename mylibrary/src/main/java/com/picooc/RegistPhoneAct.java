package com.picooc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import com.picooc.constant.ThemeConstant;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.StringUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import org.achartengine.tools.ModUtils;
import org.json.JSONObject;

public class RegistPhoneAct extends PicoocActivity
{
  public static final int REQUEST_CODE_FROM_BINDING_PHONENO = 301;
  public static final int REQUEST_CODE_FROM_FAMILY_REGISTER = 123;
  private MyApplication app;
  EditText et_nicName;
  EditText et_phone_email;
  EditText et_pwd;
  EditText et_pwdAgain;
  private int from;
  private JsonHttpResponseHandler httpHandlerPhone = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(RegistPhoneAct.this, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(RegistPhoneAct.this, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      PicoocLoading.dismissDialog();
      String str = localResponseEntity.getMethod();
      if (str.equals("bindingMyPhoneNumber"))
      {
        localIntent1 = new Intent(RegistPhoneAct.this, BindingYanZhengAct.class);
        localIntent1.putExtra("from", 1105);
        localIntent1.putExtra("phone", RegistPhoneAct.this.et_phone_email.getText().toString());
        localIntent1.putExtra("pwd", RegistPhoneAct.this.et_pwd.getText().toString());
        RegistPhoneAct.this.startActivity(localIntent1);
        RegistPhoneAct.this.overridePendingTransition(2130968596, 2130968595);
      }
      while (!str.equals("bindingPicooc"))
      {
        Intent localIntent1;
        return;
      }
      Intent localIntent2 = new Intent(RegistPhoneAct.this, BindingYanZhengAct.class);
      localIntent2.putExtra("from", 1104);
      localIntent2.putExtra("phone", RegistPhoneAct.this.et_phone_email.getText().toString());
      localIntent2.putExtra("pwd", RegistPhoneAct.this.et_pwd.getText().toString());
      localIntent2.putExtra("nic", RegistPhoneAct.this.et_nicName.getText().toString().trim());
      RegistPhoneAct.this.startActivityForResult(localIntent2, 221);
      RegistPhoneAct.this.overridePendingTransition(2130968596, 2130968595);
    }
  };
  private ScrollView scrollView;
  private ThemeConstant themeConstant;

  private void registerPhone()
  {
    String str1 = this.et_phone_email.getText().toString().trim();
    boolean bool1 = ModUtils.isMobileNO(str1);
    String str2 = this.et_pwd.getText().toString().trim();
    boolean bool2 = StringUtils.isPassword(str2);
    if (this.et_pwdAgain.getText().toString().equals(str2));
    int j;
    for (int i = 1; ; i = 0)
    {
      j = 1;
      if ((this.from == 123) && ((this.et_nicName.getText() == null) || ("".equals(this.et_nicName.getText().toString().trim()))))
        j = 0;
      if (bool1)
        break;
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
    if (j == 0)
    {
      PicoocToast.showToast(this, "请输入昵称!");
      return;
    }
    if (this.from == 123)
    {
      PicoocLoading.showLoadingDialog(this);
      RequestEntity localRequestEntity2 = new RequestEntity("bindingPicooc", "5.1");
      localRequestEntity2.addParam("email", "");
      localRequestEntity2.addParam("phone", str1);
      localRequestEntity2.addParam("password", str2);
      localRequestEntity2.addParam("nickname", this.et_nicName.getText().toString().trim());
      localRequestEntity2.addParam("myUid", Long.valueOf(this.app.getCurrentRole().getUser_id()));
      localRequestEntity2.addParam("roleId", Long.valueOf(this.app.getCurrentRole().getRole_id()));
      localRequestEntity2.addParam("remarkName", this.app.getCurrentRole().getName());
      localRequestEntity2.addParam("code", "");
      localRequestEntity2.addParam("step", "1");
      HttpUtils.getJson(this, localRequestEntity2, this.httpHandlerPhone);
      return;
    }
    PicoocLoading.showLoadingDialog(this);
    RequestEntity localRequestEntity1 = new RequestEntity("bindingMyPhoneNumber", "5.1");
    localRequestEntity1.addParam("myUserID", Long.valueOf(this.app.getCurrentUser().getUser_id()));
    localRequestEntity1.addParam("password", str2);
    localRequestEntity1.addParam("phoneNumber", str1);
    localRequestEntity1.addParam("receivedCode", "");
    localRequestEntity1.addParam("step", "1");
    HttpUtils.getJson(this, localRequestEntity1, this.httpHandlerPhone);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 221) && (paramInt2 == 222))
      finish();
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100359:
      finish();
      return;
    case 2131099828:
    }
    registerPhone();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903216);
    this.scrollView = ((ScrollView)findViewById(2131100627));
    setTheme();
    this.et_phone_email = ((EditText)findViewById(2131099831));
    this.et_phone_email.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.et_pwd = ((EditText)findViewById(2131100621));
    this.et_pwd.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.et_pwdAgain = ((EditText)findViewById(2131100622));
    this.et_pwdAgain.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.et_nicName = ((EditText)findViewById(2131100554));
    this.et_nicName.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.app = ((MyApplication)getApplicationContext());
    this.from = getIntent().getIntExtra("from", 123);
    ((TextView)findViewById(2131100363)).setTypeface(TypefaceUtils.getTypeface(this, null));
    ((TextView)findViewById(2131100620)).setTypeface(TypefaceUtils.getTypeface(this, null));
    if (this.from == 123)
      this.et_nicName.setVisibility(0);
  }

  public void setTheme()
  {
    this.themeConstant = new ThemeConstant(this);
    if (this.themeConstant.getbgResource().intValue() == 2130837526)
    {
      this.scrollView.setBackgroundResource(2130837526);
      return;
    }
    if (this.themeConstant.getbgResource().intValue() == 2130837527)
    {
      this.scrollView.setBackgroundResource(2130837527);
      return;
    }
    this.scrollView.setBackgroundResource(2130837525);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     RegistPhoneAct
 * JD-Core Version:    0.6.2
 */
