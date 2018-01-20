package com.picooc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.constant.ThemeConstant;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import com.picooc.widget.picoocProgress.PicoocAlertDialog;
import org.achartengine.tools.ModUtils;
import org.json.JSONObject;

public class BindingPhoneAct extends PicoocActivity
{
  private MyApplication app;
  private EditText et_phone_email;
  private JsonHttpResponseHandler httpHandlerPhone = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(BindingPhoneAct.this, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(BindingPhoneAct.this, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      PicoocLoading.dismissDialog();
      Intent localIntent = new Intent(BindingPhoneAct.this, BindingYanZhengAct.class);
      if (BindingPhoneAct.this.key == ModUtils.FAMLYIN)
        localIntent.putExtra("from", 1106);
      while (true)
      {
        localIntent.putExtra("phone", BindingPhoneAct.this.et_phone_email.getText().toString());
        localIntent.putExtra("pwd", "");
        BindingPhoneAct.this.startActivity(localIntent);
        BindingPhoneAct.this.finish();
        return;
        localIntent.putExtra("from", 1105);
      }
    }
  };
  private ImageView iv_left;
  private ImageView iv_right;
  private int key = 0;
  private RelativeLayout.LayoutParams reLayoutParams;
  private RelativeLayout relativelayout;
  private ThemeConstant themeConstant;
  private RelativeLayout title;
  private TextView tv_picooc_account;
  private TextView tv_tip1;
  private TextView tv_tip2;

  private void registerPhone()
  {
    final String str = this.et_phone_email.getText().toString().trim();
    if (!ModUtils.isMobileNO(str))
    {
      PicoocToast.showToast(this, "手机号格式错误!");
      return;
    }
    final PicoocAlertDialog localPicoocAlertDialog = new PicoocAlertDialog("我们将发送短信到这个号码  \n  " + str, "取消", "确定", this);
    localPicoocAlertDialog.showAlerDialogTwo(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PicoocLoading.showLoadingDialog(BindingPhoneAct.this);
        RequestEntity localRequestEntity = new RequestEntity("bindingMyPhoneNumber", "5.1");
        localRequestEntity.addParam("myUserID", Long.valueOf(BindingPhoneAct.this.app.getCurrentUser().getUser_id()));
        localRequestEntity.addParam("password", "");
        localRequestEntity.addParam("phoneNumber", str);
        localRequestEntity.addParam("receivedCode", "");
        localRequestEntity.addParam("step", "1");
        HttpUtils.getJson(BindingPhoneAct.this, localRequestEntity, BindingPhoneAct.this.httpHandlerPhone);
        localPicoocAlertDialog.dismissAlertDialog();
      }
    }
    , new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localPicoocAlertDialog.dismissAlertDialog();
      }
    });
    localPicoocAlertDialog.setTextTitel("确认手机号码");
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100359:
      if (this.key == ModUtils.FAMLYIN)
      {
        finish();
        overridePendingTransition(2130968576, 2130968581);
        return;
      }
      finish();
      return;
    case 2131099828:
    }
    registerPhone();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903178);
    this.title = ((RelativeLayout)findViewById(2131099699));
    this.tv_picooc_account = ((TextView)findViewById(2131100363));
    this.tv_tip1 = ((TextView)findViewById(2131100364));
    this.tv_tip2 = ((TextView)findViewById(2131100365));
    this.relativelayout = ((RelativeLayout)findViewById(2131099841));
    this.title = ((RelativeLayout)findViewById(2131099699));
    this.et_phone_email = ((EditText)findViewById(2131100362));
    this.et_phone_email.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.iv_left = ((ImageView)findViewById(2131100359));
    this.iv_right = ((ImageView)findViewById(2131099828));
    this.app = ((MyApplication)getApplicationContext());
    this.key = getIntent().getIntExtra("key", ModUtils.FAMLYIN);
    getResources().getDrawable(2130838389);
    if (this.key == ModUtils.FAMLYIN)
    {
      this.tv_picooc_account.setAlpha(0.5F);
      this.tv_tip1.setAlpha(0.5F);
      this.tv_tip2.setAlpha(0.5F);
      this.relativelayout.setBackgroundResource(2130837518);
      this.iv_left.setBackgroundResource(2130838389);
      this.iv_right.setBackgroundResource(2130838407);
      this.reLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
      this.reLayoutParams.setMargins(10, 10, 10, 0);
      this.title.setLayoutParams(this.reLayoutParams);
      return;
    }
    this.title.setBackgroundResource(2130838437);
    setTheme();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (this.key != ModUtils.FAMLYIN)
        break label34;
      finish();
      overridePendingTransition(2130968576, 2130968581);
    }
    while (true)
    {
      return super.onKeyDown(paramInt, paramKeyEvent);
      label34: finish();
    }
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
 * Qualified Name:     BindingPhoneAct
 * JD-Core Version:    0.6.2
 */
