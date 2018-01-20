package com.picooc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.baidu.frontia.Frontia;
import com.picooc.db.OperationDB;
import com.picooc.internet.AsyncMessageUtils;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.thread.UpLoadQQNickNameThread;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.ThirdPartLogin;
import com.picooc.utils.ThirdPartLogin.thirdPartLoginListener;
import com.picooc.widget.loading.PicoocToast;
import com.picooc.widget.picoocProgress.PicoocAlertDialog;
import java.util.List;
import org.achartengine.tools.ModUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class WelcomeActivity extends Activity
  implements thirdPartLoginListener
{
  public static final String APIKEY = "7Y4L3bMxLgil7WbPuDyaaPCa";
  public static final String QQ_HULIAN_APPId = "101016543";
  public static final String YOUMENG_ChannelId = "52d59f6c56240bb30b05b140";
  public static final String YOUMENG_KEY = "52d59f6c56240bb30b05b140";
  public static final String YOUMENG_WeiXinAppId = "wx03f3ff480b0f89bc";
  boolean IsfromQQ = false;
  MyApplication app;
  long backUser_id;
  String figureurl;
  private String fromoOpenid = "";
  private String fromoToken = "";

  @SuppressLint({"HandlerLeak"})
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 8:
      }
      WelcomeActivity.this.user_id = ((Long)SharedPreferenceUtils.getValue(WelcomeActivity.this, "user-Info", "user_id", Long.class));
      Log.i("qianmo", "user_id===" + WelcomeActivity.this.user_id);
      if (WelcomeActivity.this.user_id.longValue() <= 0L)
      {
        Intent localIntent4;
        if (WelcomeActivity.this.IsfromQQ)
        {
          localIntent4 = new Intent(WelcomeActivity.this, FirstAct.class);
          localIntent4.putExtra("isQQfrom", WelcomeActivity.this.IsfromQQ);
          localIntent4.putExtra("openid", WelcomeActivity.this.fromoOpenid);
          localIntent4.putExtra("accesstoken", WelcomeActivity.this.fromoToken);
        }
        while (true)
        {
          WelcomeActivity.this.startActivity(localIntent4);
          WelcomeActivity.this.finish();
          return;
          if (SharedPreferenceUtils.getHuanYingboolen(WelcomeActivity.this))
            localIntent4 = new Intent(WelcomeActivity.this, NewbieGuideAct.class);
          else
            localIntent4 = new Intent(WelcomeActivity.this, FirstAct.class);
        }
      }
      WelcomeActivity.this.app = ((MyApplication)WelcomeActivity.this.getApplicationContext());
      if (WelcomeActivity.this.app.getCurrentUser() == null)
      {
        Intent localIntent3;
        if (WelcomeActivity.this.IsfromQQ)
        {
          localIntent3 = new Intent(WelcomeActivity.this, FirstAct.class);
          localIntent3.putExtra("isQQfrom", WelcomeActivity.this.IsfromQQ);
          localIntent3.putExtra("openid", WelcomeActivity.this.fromoOpenid);
          localIntent3.putExtra("accesstoken", WelcomeActivity.this.fromoToken);
        }
        while (true)
        {
          WelcomeActivity.this.app.setUser_id(-100L);
          WelcomeActivity.this.startActivity(localIntent3);
          WelcomeActivity.this.finish();
          return;
          if (SharedPreferenceUtils.getHuanYingboolen(WelcomeActivity.this))
            localIntent3 = new Intent(WelcomeActivity.this, NewbieGuideAct.class);
          else
            localIntent3 = new Intent(WelcomeActivity.this, FirstAct.class);
        }
      }
      List localList = OperationDB.selectAllRoleByUserId(WelcomeActivity.this, WelcomeActivity.this.user_id.longValue());
      Log.i("qianmo", "roles===" + localList);
      if ((localList == null) || (localList.size() == 0))
      {
        Intent localIntent1;
        if (WelcomeActivity.this.IsfromQQ)
        {
          localIntent1 = new Intent(WelcomeActivity.this, FirstAct.class);
          localIntent1.putExtra("isQQfrom", WelcomeActivity.this.IsfromQQ);
          localIntent1.putExtra("openid", WelcomeActivity.this.fromoOpenid);
          localIntent1.putExtra("accesstoken", WelcomeActivity.this.fromoToken);
        }
        while (true)
        {
          WelcomeActivity.this.startActivity(localIntent1);
          WelcomeActivity.this.finish();
          return;
          if (SharedPreferenceUtils.getHuanYingboolen(WelcomeActivity.this))
            localIntent1 = new Intent(WelcomeActivity.this, NewbieGuideAct.class);
          else
            localIntent1 = new Intent(WelcomeActivity.this, FirstAct.class);
        }
      }
      Long localLong = (Long)SharedPreferenceUtils.getValue(WelcomeActivity.this, "user-Info", "role_id", Long.class);
      Log.i("qianmo", "role_id===" + localLong);
      if (localLong.longValue() <= 0L)
      {
        Intent localIntent2 = new Intent(WelcomeActivity.this, WriteReferenAct.class);
        localIntent2.putExtra("screen_name", WelcomeActivity.this.nickName);
        localIntent2.putExtra("profile_image_url", WelcomeActivity.this.figureurl);
        localIntent2.putExtra("userID", WelcomeActivity.this.user_id);
        localIntent2.putExtra("isQQfrom", WelcomeActivity.this.IsfromQQ);
        localIntent2.putExtra("openid", WelcomeActivity.this.fromoOpenid);
        localIntent2.putExtra("accesstoken", WelcomeActivity.this.fromoToken);
        WelcomeActivity.this.startActivity(localIntent2);
        WelcomeActivity.this.finish();
        return;
      }
      if (WelcomeActivity.this.IsfromQQ)
      {
        WelcomeActivity.this.checkOpenID(WelcomeActivity.this.fromoOpenid, 3, WelcomeActivity.this.fromoToken, WelcomeActivity.this.thirdPartHttpHandler);
        return;
      }
      String str = OperationDB.selectQQ_id(WelcomeActivity.this, WelcomeActivity.this.user_id.longValue());
      Log.i("ibokan", "--------查出的qq_id===" + str);
      if (str != null)
      {
        WelcomeActivity.this.checkOpenID(str, 3, "", WelcomeActivity.this.httpHandlerCheckOld);
        return;
      }
      AsyncMessageUtils.loadUserMessage(WelcomeActivity.this.getApplicationContext(), WelcomeActivity.this.app.getUser_id());
      AsyncMessageUtils.loadRoleAndRoleInfosFromServer(WelcomeActivity.this, WelcomeActivity.this.user_id.longValue(), WelcomeActivity.this.httpHandler);
    }
  };
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      if ((WelcomeActivity.this.isBinding == 1) && (WelcomeActivity.this.backUser_id != WelcomeActivity.this.user_id.longValue()))
        WelcomeActivity.this.startActivity3(true);
      while (true)
      {
        if (SharedPreferenceUtils.isOpenPsd(WelcomeActivity.this))
          MyApplication.isShowLocalPassword = true;
        return;
        WelcomeActivity.this.startActivity3(false);
      }
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      if ((WelcomeActivity.this.isBinding == 1) && (WelcomeActivity.this.backUser_id != WelcomeActivity.this.user_id.longValue()))
        WelcomeActivity.this.startActivity3(true);
      while (true)
      {
        if (SharedPreferenceUtils.isOpenPsd(WelcomeActivity.this))
          MyApplication.isShowLocalPassword = true;
        return;
        WelcomeActivity.this.startActivity3(false);
      }
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      if (localResponseEntity.getMethod().equals("getRoles"))
      {
        long l = ((Long)SharedPreferenceUtils.getValue(WelcomeActivity.this.getApplicationContext(), "user-Info", "user_id", Long.class)).longValue();
        OperationDB.updateAllRolesAndRoleInfos(WelcomeActivity.this, localResponseEntity.getResp(), l);
        if ((WelcomeActivity.this.isBinding != 1) || (WelcomeActivity.this.backUser_id == l))
          break label131;
        WelcomeActivity.this.startActivity3(true);
      }
      while (true)
      {
        if (SharedPreferenceUtils.isOpenPsd(WelcomeActivity.this))
          MyApplication.isShowLocalPassword = true;
        return;
        label131: WelcomeActivity.this.startActivity3(false);
      }
    }
  };
  private JsonHttpResponseHandler httpHandlerCheckOld = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      WelcomeActivity.this.startActivity3(false);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      WelcomeActivity.this.startActivity3(false);
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      String str = localResponseEntity.getMethod();
      if (str.equals(HttpUtils.pthirdPartLoginStateCheck))
      {
        try
        {
          int i = localResponseEntity.getResp().getInt("isBinding");
          int j = localResponseEntity.getResp().getInt("isOutDate");
          if (i == 1)
          {
            if (j == 1)
            {
              WelcomeActivity.this.third.addAuthOld(WelcomeActivity.this);
              return;
            }
            AsyncMessageUtils.loadUserMessage(WelcomeActivity.this.getApplicationContext(), WelcomeActivity.this.app.getUser_id());
            AsyncMessageUtils.loadRoleAndRoleInfosFromServer(WelcomeActivity.this, WelcomeActivity.this.user_id.longValue(), WelcomeActivity.this.httpHandler);
            return;
          }
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          return;
        }
        AsyncMessageUtils.loadUserMessage(WelcomeActivity.this.getApplicationContext(), WelcomeActivity.this.app.getUser_id());
        AsyncMessageUtils.loadRoleAndRoleInfosFromServer(WelcomeActivity.this, WelcomeActivity.this.user_id.longValue(), WelcomeActivity.this.httpHandler);
        return;
      }
      str.equals(HttpUtils.pthirdPartLoginStateCheck);
    }
  };
  int isBinding;
  String nickName;
  public long openid;
  public final String qqhealth = "qqhealth";
  ThirdPartLogin third;
  private JsonHttpResponseHandler thirdPartHttpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      WelcomeActivity.this.startFirstActivity();
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      WelcomeActivity.this.startFirstActivity();
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      if (localResponseEntity.getMethod().equals(HttpUtils.pthirdPartLoginStateCheck))
      {
        try
        {
          WelcomeActivity.this.isBinding = localResponseEntity.getResp().getInt("isBinding");
          if (WelcomeActivity.this.isBinding == 1)
          {
            WelcomeActivity.this.backUser_id = localResponseEntity.getResp().getLong("userID");
            if (WelcomeActivity.this.backUser_id != WelcomeActivity.this.user_id.longValue())
            {
              final PicoocAlertDialog localPicoocAlertDialog2 = new PicoocAlertDialog("Hi," + WelcomeActivity.this.app.getCurentUserName(WelcomeActivity.this.user_id) + ":\n" + "您需要切换至手机QQ所登录的账号，才能在健康中心看到数据哦~", "取消", "切换", WelcomeActivity.this);
              localPicoocAlertDialog2.showAlerDialogTwo(new View.OnClickListener()
              {
                public void onClick(View paramAnonymous2View)
                {
                  WelcomeActivity.this.startFirstActivity();
                  localPicoocAlertDialog2.dismissAlertDialog();
                }
              }
              , new View.OnClickListener()
              {
                public void onClick(View paramAnonymous2View)
                {
                  AsyncMessageUtils.loadUserMessage(WelcomeActivity.this.getApplicationContext(), WelcomeActivity.this.app.getUser_id());
                  AsyncMessageUtils.loadRoleAndRoleInfosFromServer(WelcomeActivity.this, WelcomeActivity.this.user_id.longValue(), WelcomeActivity.this.httpHandler);
                  localPicoocAlertDialog2.dismissAlertDialog();
                  WelcomeActivity.this.app.setComeFromQQ(false);
                }
              });
              localPicoocAlertDialog2.setOnTouchOutside(Boolean.valueOf(false));
              return;
            }
            AsyncMessageUtils.loadUserMessage(WelcomeActivity.this.getApplicationContext(), WelcomeActivity.this.app.getUser_id());
            AsyncMessageUtils.loadRoleAndRoleInfosFromServer(WelcomeActivity.this, WelcomeActivity.this.user_id.longValue(), WelcomeActivity.this.httpHandler);
            return;
          }
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          WelcomeActivity.this.startFirstActivity();
          AsyncMessageUtils.loadUserMessage(WelcomeActivity.this.getApplicationContext(), WelcomeActivity.this.app.getUser_id());
          AsyncMessageUtils.loadRoleAndRoleInfosFromServer(WelcomeActivity.this, WelcomeActivity.this.user_id.longValue(), WelcomeActivity.this.httpHandler);
          return;
        }
        if (OperationDB.isNoEmailAndPhone(WelcomeActivity.this, WelcomeActivity.this.user_id.longValue()))
        {
          final PicoocAlertDialog localPicoocAlertDialog1 = new PicoocAlertDialog("Hi," + WelcomeActivity.this.app.getCurentUserName(WelcomeActivity.this.user_id) + ":\n" + "您需要切换至手机QQ所登录的账号，才能在健康中心看到数据哦~", "取消", "切换", WelcomeActivity.this);
          localPicoocAlertDialog1.showAlerDialogTwo(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              WelcomeActivity.this.startFirstActivity();
              localPicoocAlertDialog1.dismissAlertDialog();
            }
          }
          , new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              AsyncMessageUtils.loadUserMessage(WelcomeActivity.this.getApplicationContext(), WelcomeActivity.this.app.getUser_id());
              AsyncMessageUtils.loadRoleAndRoleInfosFromServer(WelcomeActivity.this, WelcomeActivity.this.user_id.longValue(), WelcomeActivity.this.httpHandler);
              localPicoocAlertDialog1.dismissAlertDialog();
              WelcomeActivity.this.app.setComeFromQQ(false);
            }
          });
          localPicoocAlertDialog1.setOnTouchOutside(Boolean.valueOf(false));
          return;
        }
        AsyncMessageUtils.loadUserMessage(WelcomeActivity.this.getApplicationContext(), WelcomeActivity.this.app.getUser_id());
        AsyncMessageUtils.loadRoleAndRoleInfosFromServer(WelcomeActivity.this, WelcomeActivity.this.user_id.longValue(), WelcomeActivity.this.httpHandler);
      }
    }
  };
  Long user_id;

  private void startActivity3(boolean paramBoolean)
  {
    Intent localIntent = new Intent(this, PicoocActivity3.class);
    localIntent.putExtra("isQQfrom", this.IsfromQQ);
    localIntent.putExtra("openid", this.fromoOpenid);
    localIntent.putExtra("accesstoken", this.fromoToken);
    localIntent.putExtra("isBangDing", paramBoolean);
    startActivity(localIntent);
    finish();
  }

  private void startFirstActivity()
  {
    this.app = ((MyApplication)getApplication());
    this.app.clearAllData();
    Intent localIntent = new Intent(this, FirstAct.class);
    localIntent.putExtra("isQQfrom", this.IsfromQQ);
    localIntent.putExtra("openid", this.fromoOpenid);
    localIntent.putExtra("accesstoken", this.fromoToken);
    startActivity(localIntent);
    finish();
  }

  public void checkAuth(Class paramClass)
  {
  }

  public void checkOpenID(String paramString1, int paramInt, String paramString2, JsonHttpResponseHandler paramJsonHttpResponseHandler)
  {
    RequestEntity localRequestEntity = new RequestEntity(HttpUtils.pthirdPartLoginStateCheck, "5.1");
    localRequestEntity.addParam("thirdPartId", paramString1);
    localRequestEntity.addParam("type", Integer.valueOf(paramInt));
    localRequestEntity.addParam("access_token", paramString2);
    HttpUtils.getJson(this, localRequestEntity, paramJsonHttpResponseHandler);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903172);
    ((MyApplication)getApplication()).addActivity(this);
    Log.i("qianmo2", "WelcomeActivity----token===" + getIntent().getStringExtra("accesstoken") + "----openid==" + getIntent().getStringExtra("openid"));
    this.third = new ThirdPartLogin(this);
    this.third.setthirdPartLoginListener(this);
    String str = ModUtils.isSdCard();
    if (str != null)
      PicoocToast.showToast(this, str);
    ((MyApplication)getApplicationContext()).clearAllData();
    Frontia.init(getApplicationContext(), "7Y4L3bMxLgil7WbPuDyaaPCa");
    ((TextView)findViewById(2131099957)).setTypeface(ModUtils.getTypeface(this));
    ((TextView)findViewById(2131100277)).setTypeface(ModUtils.getTypeface(this));
    ((NotificationManager)getSystemService("notification")).cancelAll();
    if ((getIntent().getStringExtra("from") != null) && (getIntent().getStringExtra("from").equals("qqhealth")))
    {
      this.fromoToken = getIntent().getStringExtra("accesstoken");
      this.fromoOpenid = getIntent().getStringExtra("openid");
      this.IsfromQQ = true;
      new UpLoadQQNickNameThread(this, new myHandler(null), "101016543", this.fromoToken, this.fromoOpenid).start();
      ((MyApplication)getApplication()).setComeFromQQ(true);
    }
    while (true)
    {
      if (!((Boolean)SharedPreferenceUtils.getValue(this, "deleteDietAndNutritionPrincipleFlag", "flag", Boolean.class)).booleanValue())
      {
        OperationDB.deleteDietAndNutritionPrincipleDataById(this, 16);
        OperationDB.updateDietAndNutritionPrincipleDataById(this, 36, "您的肌肉量充足，为保持肌肉量，三餐中要注意保证蛋白质的摄入哦。");
        OperationDB.updateDietAndNutritionPrincipleDataById(this, 43, "您的体型偏瘦，请注意不要挑食，保证营养均衡。");
        OperationDB.updateDietAndNutritionPrincipleDataById(this, 44, "您的体型偏瘦，请注意不要挑食，保证营养均衡。");
        SharedPreferenceUtils.putValue(this, "deleteDietAndNutritionPrincipleFlag", "flag", Boolean.valueOf(true));
      }
      return;
      Message localMessage = new Message();
      localMessage.what = 8;
      this.handler.sendMessageDelayed(localMessage, 500L);
    }
  }

  protected void onPause()
  {
    super.onPause();
  }

  protected void onResume()
  {
    super.onResume();
  }

  protected void onStart()
  {
    super.onStart();
  }

  public void thirdPartLoginSuccess(String paramString1, String paramString2, int paramInt)
  {
    this.fromoOpenid = paramString1;
    this.fromoToken = paramString2;
    if (paramInt == 33)
    {
      Message localMessage1 = new Message();
      localMessage1.what = 8;
      this.handler.sendMessageDelayed(localMessage1, 10L);
    }
    if (paramInt == 34)
    {
      PicoocToast.showToast(this, "您的qq账号授权失败，将无法同步数据到qq健康平台");
      Message localMessage2 = new Message();
      localMessage2.what = 8;
      this.handler.sendMessageDelayed(localMessage2, 10L);
      this.IsfromQQ = false;
    }
    if (paramInt == 32)
    {
      AsyncMessageUtils.loadUserMessage(getApplicationContext(), this.app.getUser_id());
      AsyncMessageUtils.loadRoleAndRoleInfosFromServer(this, this.user_id.longValue(), this.httpHandler);
      RequestEntity localRequestEntity = new RequestEntity(HttpUtils.pupdateTokenDate, "5.1");
      localRequestEntity.addParam("thirdPartId", paramString1);
      localRequestEntity.addParam("type", Integer.valueOf(3));
      localRequestEntity.addParam("access_token", paramString2);
      HttpUtils.getJson(this, localRequestEntity, this.httpHandlerCheckOld);
    }
  }

  private class myHandler extends Handler
  {
    private myHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 1:
        Bundle localBundle = (Bundle)paramMessage.obj;
        WelcomeActivity.this.nickName = localBundle.getString("nickname");
        WelcomeActivity.this.figureurl = localBundle.getString("figureurl");
        Log.i("ibokan", "nickname=" + localBundle.getString("nickname") + "---qqurl==" + localBundle.getString("figureurl"));
        WelcomeActivity.this.third.addAuth(WelcomeActivity.this);
        return;
      case 2:
      }
      Message localMessage = new Message();
      localMessage.what = 8;
      WelcomeActivity.this.handler.sendMessageDelayed(localMessage, 10L);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     WelcomeActivity
 * JD-Core Version:    0.6.2
 */
