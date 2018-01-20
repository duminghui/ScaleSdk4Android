package com.picooc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.picooc.utils.StringUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import org.json.JSONException;
import org.json.JSONObject;

public class EmailRegister extends PicoocActivity
  implements View.OnClickListener
{
  public static final int FROM_LOCAL_ROLE_REGISTER = 3;
  public static final int FROM_MAIN_ROLE_BIND_EMAIL = 1;
  public static final int FROM_MAIN_ROLE_REGISTER_WITH_EMAIL = 2;
  private MyApplication app;
  private Button btn_regist;
  EditText et_nicName;
  EditText et_phone_email;
  EditText et_pwd;
  EditText et_pwdAgain;
  private int from = 0;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(EmailRegister.this, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(EmailRegister.this, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      String str = localResponseEntity.getMethod();
      PicoocLoading.dismissDialog();
      if (str.equals("bindingMyEmail"));
      while (!str.equals("bindingPicooc"))
        try
        {
          EmailRegister.this.app.getCurrentUser().setEmail(localResponseEntity.getResp().getString("email"));
          EmailRegister.this.app.getCurrentUser().setHas_password(true);
          if (OperationDB.updateUserDB(EmailRegister.this, EmailRegister.this.app.getCurrentUser()) > 0L)
          {
            Intent localIntent2 = new Intent();
            localIntent2.putExtra("email", EmailRegister.this.et_phone_email.getText().toString().trim());
            EmailRegister.this.setResult(1, localIntent2);
            EmailRegister.this.finish();
          }
          PicoocLoading.dismissDialog();
          return;
        }
        catch (JSONException localJSONException2)
        {
          while (true)
            localJSONException2.printStackTrace();
        }
      RoleBin localRoleBin = EmailRegister.this.app.getCurrentRole();
      localRoleBin.setFamily_type(1);
      localRoleBin.setEmail(EmailRegister.this.et_phone_email.getText().toString().trim());
      localRoleBin.setRemark_name(localRoleBin.getName());
      localRoleBin.setName(EmailRegister.this.et_nicName.getText().toString().trim());
      try
      {
        localRoleBin.setRemote_user_id(localResponseEntity.getResp().getLong("uid"));
        localRoleBin.setIs_new_family(false);
        EmailRegister.this.app.setCurrentRole(localRoleBin);
        OperationDB.updateRoleDB(EmailRegister.this, localRoleBin);
        Intent localIntent1 = new Intent("com.picooc.local.family.upto.remote.family");
        EmailRegister.this.sendBroadcast(localIntent1);
        EmailRegister.this.finish();
        return;
      }
      catch (JSONException localJSONException1)
      {
        while (true)
          localJSONException1.printStackTrace();
      }
    }
  };
  private RelativeLayout relayout;
  private ThemeConstant themeConstant;
  private TextView title;

  private void bangDingEmail()
  {
    String str = this.et_phone_email.getText().toString().trim();
    if (!StringUtils.isEmail(str))
    {
      PicoocToast.showToast(this, "邮箱格式错误!");
      return;
    }
    PicoocLoading.showLoadingDialog(this);
    RequestEntity localRequestEntity = new RequestEntity("bindingMyEmail", "5.1");
    localRequestEntity.addParam("email", str);
    localRequestEntity.addParam("password", "");
    localRequestEntity.addParam("myUserID", Long.valueOf(this.app.getCurrentUser().getUser_id()));
    HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
  }

  private void bindEmail()
  {
    String str1 = this.et_phone_email.getText().toString().trim();
    boolean bool1 = StringUtils.isEmail(str1);
    String str2 = this.et_pwd.getText().toString().trim();
    boolean bool2 = StringUtils.isPassword(str2);
    if (this.et_pwdAgain.getText().toString().equals(str2));
    int j;
    for (int i = 1; ; i = 0)
    {
      j = 1;
      if ((this.et_nicName.getText() == null) || ("".equals(this.et_nicName.getText().toString().trim())))
        j = 0;
      if (bool1)
        break;
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
    if (j == 0)
    {
      PicoocToast.showToast(this, "请输入昵称!");
      return;
    }
    PicoocLoading.showLoadingDialog(this);
    RequestEntity localRequestEntity = new RequestEntity("bindingPicooc", "5.1");
    localRequestEntity.addParam("email", str1);
    localRequestEntity.addParam("phone", "");
    localRequestEntity.addParam("password", str2);
    localRequestEntity.addParam("nickname", this.et_nicName.getText().toString().trim());
    localRequestEntity.addParam("myUid", Long.valueOf(this.app.getCurrentRole().getUser_id()));
    localRequestEntity.addParam("roleId", Long.valueOf(this.app.getCurrentRole().getRole_id()));
    localRequestEntity.addParam("remarkName", this.app.getCurrentRole().getName());
    localRequestEntity.addParam("code", "");
    localRequestEntity.addParam("step", "1");
    HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
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
    RequestEntity localRequestEntity = new RequestEntity("bindingMyEmail", "5.1");
    localRequestEntity.addParam("email", str1);
    localRequestEntity.addParam("password", str2);
    localRequestEntity.addParam("myUserID", Long.valueOf(this.app.getCurrentUser().getUser_id()));
    HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
  }

  public void finish()
  {
    super.finish();
  }

  public void invit()
  {
    this.relayout = ((RelativeLayout)findViewById(2131100278));
    setTheme();
    this.et_phone_email = ((EditText)findViewById(2131099831));
    this.et_phone_email.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.et_pwd = ((EditText)findViewById(2131100621));
    this.et_pwd.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.et_pwdAgain = ((EditText)findViewById(2131100622));
    this.et_pwdAgain.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.app = ((MyApplication)getApplicationContext());
    this.from = getIntent().getIntExtra("from", 0);
    this.title = ((TextView)findViewById(2131100360));
    this.btn_regist = ((Button)findViewById(2131100368));
    if (this.from == 1)
    {
      this.et_pwd.setVisibility(8);
      this.et_pwdAgain.setVisibility(8);
      this.title.setText("绑定邮箱");
      this.btn_regist.setText("绑定");
    }
    do
    {
      return;
      if (this.from == 2)
      {
        this.title.setText("邮箱注册");
        this.et_pwd.setVisibility(0);
        this.et_pwdAgain.setVisibility(0);
        this.btn_regist.setText("注册");
        return;
      }
    }
    while (this.from != 3);
    this.title.setText("邮箱注册");
    this.btn_regist.setVisibility(8);
    TextView localTextView1 = (TextView)findViewById(2131100620);
    localTextView1.setVisibility(0);
    localTextView1.setTypeface(TypefaceUtils.getTypeface(this, null));
    TextView localTextView2 = (TextView)findViewById(2131100363);
    localTextView2.setVisibility(0);
    localTextView2.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.et_nicName = ((EditText)findViewById(2131100554));
    this.et_nicName.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.et_nicName.setVisibility(0);
    findViewById(2131099828).setVisibility(0);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100368:
      if (this.from == 2)
      {
        registerEmail();
        return;
      }
      bangDingEmail();
      return;
    case 2131099650:
      finish();
      return;
    case 2131099828:
    }
    bindEmail();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903214);
    invit();
  }

  public void setTheme()
  {
    this.themeConstant = new ThemeConstant(this);
    if (this.themeConstant.getbgResource().intValue() == 2130837526)
    {
      this.relayout.setBackgroundResource(2130837526);
      return;
    }
    if (this.themeConstant.getbgResource().intValue() == 2130837527)
    {
      this.relayout.setBackgroundResource(2130837527);
      return;
    }
    this.relayout.setBackgroundResource(2130837525);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     EmailRegister
 * JD-Core Version:    0.6.2
 */
