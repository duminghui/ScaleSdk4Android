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
import com.picooc.constant.ThemeConstant;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.StringUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.MtimerTask;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import org.achartengine.tools.ModUtils;
import org.json.JSONObject;

public class ForgetAct extends PicoocActivity
{
  int a = 31;
  Button btn_regist;
  TextView btn_registText;
  EditText email;
  Boolean flag;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      Log.i("picooc", "收到消息了");
      ForgetAct localForgetAct = ForgetAct.this;
      localForgetAct.a = (-1 + localForgetAct.a);
      if (ForgetAct.this.a == 0)
      {
        ForgetAct.this.timtask.stopTimer();
        ForgetAct.this.btn_regist.setVisibility(0);
        ForgetAct.this.btn_registText.setVisibility(8);
      }
      ForgetAct.this.btn_registText.setText("接收短信大约需要" + ForgetAct.this.a + "秒");
    }
  };
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(ForgetAct.this, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(ForgetAct.this, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      Log.i("http", "成功了:" + localResponseEntity.toString());
      PicoocToast.showToast(ForgetAct.this, localResponseEntity.getMessage().toString());
      if (ForgetAct.this.flag.booleanValue())
      {
        ForgetAct.this.btn_regist.setVisibility(8);
        ForgetAct.this.btn_registText.setVisibility(0);
        ForgetAct.this.a = 30;
        ForgetAct.this.timtask.startTimer();
      }
    }
  };
  private Intent intent;
  private int key;
  private RelativeLayout.LayoutParams reLayoutParams;
  private RelativeLayout relativelayout;
  private ThemeConstant themeConstant;
  MtimerTask timtask;
  private RelativeLayout title;

  private void findPassword()
  {
    String str = this.email.getText().toString().trim();
    this.flag = Boolean.valueOf(ModUtils.isNumeric(str));
    if (str.equals(""))
    {
      PicoocToast.showToast(this, "请填写手机或邮箱");
      return;
    }
    boolean bool;
    RequestEntity localRequestEntity;
    if (this.flag.booleanValue())
    {
      bool = ModUtils.isMobileNO(str);
      if (!bool)
        break label139;
      PicoocLoading.showLoadingDialog(this);
      localRequestEntity = new RequestEntity("findPassword", "5.1");
      if (!this.flag.booleanValue())
        break label121;
      localRequestEntity.addParam("email", "");
      localRequestEntity.addParam("phone", str);
    }
    while (true)
    {
      HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
      return;
      bool = StringUtils.isEmail(str);
      break;
      label121: localRequestEntity.addParam("email", str);
      localRequestEntity.addParam("phone", "");
    }
    label139: if (this.flag.booleanValue())
    {
      PicoocToast.showToast(this, "手机号格式错误!");
      return;
    }
    PicoocToast.showToast(this, "邮箱格式错误!");
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131099650:
      if (this.key == 77)
      {
        finish();
        overridePendingTransition(2130968594, 2130968597);
        return;
      }
      this.intent = new Intent(this, FirstAct.class);
      startActivity(this.intent);
      finish();
      overridePendingTransition(2130968594, 2130968597);
      return;
    case 2131100435:
    }
    findPassword();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903185);
    this.title = ((RelativeLayout)findViewById(2131099699));
    this.key = getIntent().getIntExtra("key", 0);
    if (this.key == 77)
    {
      this.relativelayout = ((RelativeLayout)findViewById(2131099841));
      this.relativelayout.setBackgroundResource(2130837525);
      this.title.setBackgroundResource(2130838437);
      this.reLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
      this.reLayoutParams.setMargins(10, 0, 10, 10);
      this.title.setLayoutParams(this.reLayoutParams);
      this.themeConstant = new ThemeConstant(this);
      this.themeConstant.setTheme(this.relativelayout);
    }
    this.email = ((EditText)findViewById(2131100303));
    this.email.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.btn_registText = ((TextView)findViewById(2131100369));
    this.btn_registText.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.btn_regist = ((Button)findViewById(2131100435));
    this.btn_registText.setVisibility(8);
    this.btn_regist.setVisibility(0);
    this.timtask = new MtimerTask(this.handler, 1000, Boolean.valueOf(true), 40);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (this.key != 77)
        break label33;
      finish();
      overridePendingTransition(2130968594, 2130968597);
    }
    while (true)
    {
      return super.onKeyDown(paramInt, paramKeyEvent);
      label33: this.intent = new Intent(this, FirstAct.class);
      startActivity(this.intent);
      finish();
      overridePendingTransition(2130968594, 2130968597);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ForgetAct
 * JD-Core Version:    0.6.2
 */
