package com.picooc;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.picooc.constant.ThemeConstant;
import com.picooc.db.OperationDB;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import org.json.JSONObject;

public class LiftChangePwd extends PicoocActivity
  implements View.OnClickListener
{
  public static final int CHANGE_PASSWORD = 1;
  public static final int SETTING_PASSWORD = 2;
  public static final int SETTING_PASSWORD_BEFORE_LOGOUT = 3;
  public static final int SETTING_PASSWORD_WHEN_CLICK_FORGET_PASSWORD = 4;
  private MyApplication app;
  private int flag;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(LiftChangePwd.this, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(LiftChangePwd.this, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      String str1 = localResponseEntity.getMethod();
      if (str1.equals("updatePassword"))
      {
        PicoocLoading.dismissDialog();
        LiftChangePwd.this.finish();
        str2 = localResponseEntity.getMessage();
        PicoocToast.showToast(LiftChangePwd.this, str2);
      }
      while (!str1.equals("setPassword"))
      {
        String str2;
        return;
      }
      PicoocLoading.dismissDialog();
      if (LiftChangePwd.this.flag == 4)
      {
        LiftChangePwd.this.setResult(2213);
        LiftChangePwd.this.app.getCurrentUser().setHas_password(true);
        OperationDB.updateUserDB(LiftChangePwd.this, LiftChangePwd.this.app.getCurrentUser());
        LiftChangePwd.this.finish();
        return;
      }
      LiftChangePwd.this.setResult(1321);
      LiftChangePwd.this.app.getCurrentUser().setHas_password(true);
      OperationDB.updateUserDB(LiftChangePwd.this, LiftChangePwd.this.app.getCurrentUser());
      LiftChangePwd.this.finish();
    }
  };
  LinearLayout linearLayout_bg;
  EditText newpwd;
  EditText oldpwd;
  EditText pwdtrue;
  private ThemeConstant themeConstant;

  public void finish()
  {
    super.finish();
  }

  public void invit()
  {
    this.linearLayout_bg = ((LinearLayout)findViewById(2131099739));
    this.themeConstant = new ThemeConstant(this);
    this.themeConstant.setTheme(this.linearLayout_bg);
    TextView localTextView1 = (TextView)findViewById(2131099699);
    if ((this.flag == 2) || (this.flag == 3) || (this.flag == 4))
    {
      localTextView1.setText("设置密码");
      this.oldpwd = ((EditText)findViewById(2131100422));
      this.newpwd = ((EditText)findViewById(2131100423));
      this.newpwd.setTypeface(TypefaceUtils.getTypeface(this, null));
      this.pwdtrue = ((EditText)findViewById(2131100424));
      this.pwdtrue.setTypeface(TypefaceUtils.getTypeface(this, null));
      if (this.flag != 1)
        break label222;
      this.oldpwd.setVisibility(0);
      this.oldpwd.setTypeface(TypefaceUtils.getTypeface(this, null));
    }
    while (true)
    {
      ImageView localImageView1 = (ImageView)findViewById(2131099651);
      ImageView localImageView2 = (ImageView)findViewById(2131099650);
      localImageView1.setImageResource(2130838344);
      localImageView1.setOnClickListener(this);
      localImageView2.setOnClickListener(this);
      localImageView2.setImageResource(2130838335);
      return;
      localTextView1.setText("修改密码");
      break;
      label222: this.oldpwd.setVisibility(8);
      if ((this.flag == 3) || (this.flag == 4))
      {
        TextView localTextView2 = (TextView)findViewById(2131100421);
        localTextView2.setText("您的PICOOC账号" + this.app.getCurrentUser().getPhone_no() + "还没有设置密码哦，为了增强密码锁定的安全性，请您先设置密码。");
        localTextView2.setVisibility(0);
        localTextView2.setTypeface(TypefaceUtils.getTypeface(this, null));
      }
      this.newpwd.setHint("6~25位数字与字母混合");
      this.pwdtrue.setHint("确认密码");
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131099651:
      if (this.flag == 1)
      {
        if (!this.oldpwd.getText().toString().equals(""))
        {
          if (!this.newpwd.getText().toString().equals(""))
          {
            if (!this.pwdtrue.getText().toString().equals(""))
            {
              if (this.pwdtrue.getText().toString().equals(this.newpwd.getText().toString()))
              {
                PicoocLoading.showLoadingDialog(this);
                RequestEntity localRequestEntity2 = new RequestEntity("updatePassword", "1.0");
                localRequestEntity2.addParam("userID", Long.valueOf(this.app.getCurrentUser().getUser_id()));
                localRequestEntity2.addParam("oldPassword", this.oldpwd.getText().toString());
                localRequestEntity2.addParam("password", this.newpwd.getText().toString());
                HttpUtils.getJson(this, localRequestEntity2, this.httpHandler);
                return;
              }
              PicoocToast.showToast(this, "您两次输入的新密码不一致!");
              return;
            }
            PicoocToast.showToast(this, "请填写新密码确认!");
            return;
          }
          PicoocToast.showToast(this, "请填写新密码!");
          return;
        }
        PicoocToast.showToast(this, "请填写原密码!");
        return;
      }
      if (!this.newpwd.getText().toString().equals(""))
      {
        if (!this.pwdtrue.getText().toString().equals(""))
        {
          if (this.pwdtrue.getText().toString().equals(this.newpwd.getText().toString()))
          {
            PicoocLoading.showLoadingDialog(this);
            RequestEntity localRequestEntity1 = new RequestEntity("setPassword", "1.0");
            localRequestEntity1.addParam("userID", Long.valueOf(this.app.getCurrentUser().getUser_id()));
            localRequestEntity1.addParam("password", this.newpwd.getText().toString());
            HttpUtils.getJson(this, localRequestEntity1, this.httpHandler);
            return;
          }
          PicoocToast.showToast(this, "您两次输入的密码不一致!");
          return;
        }
        PicoocToast.showToast(this, "请填写密码!");
        return;
      }
      PicoocToast.showToast(this, "填确认密码!");
      return;
    case 2131099650:
    }
    finish();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903182);
    this.app = ((MyApplication)getApplicationContext());
    this.flag = getIntent().getIntExtra("flag", 1);
    invit();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LiftChangePwd
 * JD-Core Version:    0.6.2
 */
