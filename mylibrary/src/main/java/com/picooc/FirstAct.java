package com.picooc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.baidu.android.pushservice.PushManager;
import com.baidu.frontia.api.FrontiaAuthorization.MediaType;
import com.hanyou.leyusdk.LEYUUser;
import com.picooc.db.OperationDB;
import com.picooc.domain.UserBin;
import com.picooc.internet.AsyncMessageUtils;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.DateUtils;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.StringUtils;
import com.picooc.utils.ThirdPartLogin;
import com.picooc.utils.ThirdPartLogin.thirdPartLoginListener;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.RequestType;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.UMDataListener;
import java.util.Map;
import org.achartengine.tools.ModUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class FirstAct extends PicoocActivity
  implements thirdPartLoginListener
{
  EditText email;
  private int flag = -1;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(FirstAct.this, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(FirstAct.this, localResponseEntity.getMessage());
      switch (FirstAct.this.flag)
      {
      case 4:
      case 7:
      default:
        return;
      case 5:
        FirstAct.this.thirdPart.startBaiduLogout(FrontiaAuthorization.MediaType.BAIDU, FirstAct.this);
        return;
      case 2:
        FirstAct.this.thirdPart.delete(SHARE_MEDIA.SINA, FirstAct.this);
        return;
      case 3:
        FirstAct.this.thirdPart.delete(SHARE_MEDIA.QZONE, FirstAct.this);
        return;
      case 6:
      }
      FirstAct.this.thirdPart.outLeYu();
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      String str1 = localResponseEntity.getMethod();
      if ((str1.equals("userLogin")) || (str1.equals("thirdPartLogin")));
      label772: label786: 
      do
      {
        try
        {
          l1 = localResponseEntity.getResp().getLong("userID");
          localUserBin = new UserBin();
          localUserBin.setUser_id(l1);
          SharedPreferenceUtils.saveHuanYingboolen(FirstAct.this, l1);
          long l2 = System.currentTimeMillis();
          String str2 = "";
          String str3 = "";
          String str4 = "";
          String str5 = "";
          String str6 = "";
          String str7 = "";
          String str8 = "";
          str9 = "";
          long l3 = 0L;
          int i;
          if (localResponseEntity.getResp().has("roleID"))
          {
            l2 = DateUtils.changeStringToTimestamp(localResponseEntity.getResp().getString("time"), "yyyy-MM-dd HH:mm:ss");
            str2 = localResponseEntity.getResp().getString("email");
            str3 = localResponseEntity.getResp().getString("phoneNo");
            str4 = localResponseEntity.getResp().getString("weibo_id");
            str5 = localResponseEntity.getResp().getString("baidu_id");
            str6 = localResponseEntity.getResp().getString("qq_id");
            str7 = localResponseEntity.getResp().getString("dayima_id");
            l3 = localResponseEntity.getResp().getLong("roleID");
            str8 = localResponseEntity.getResp().getString("leyu_id");
            str9 = localResponseEntity.getResp().getString("jingdong_id");
            boolean bool2 = localResponseEntity.getResp().has("has_device");
            i = 0;
            if (bool2)
              i = localResponseEntity.getResp().getInt("has_device");
            localUserBin.setTime(l2);
            localUserBin.setEmail(str2);
            localUserBin.setPhone_no(str3);
            localUserBin.setWeibo_id(str4);
            localUserBin.setBaidu_id(str5);
            localUserBin.setQQ_id(str6);
            localUserBin.setDayima_id(str7);
            localUserBin.setRole_id(l3);
            localUserBin.setJd_id(str9);
            localUserBin.setLy_id(str8);
            FirstAct.this.main_role_id = l3;
            SharedPreferenceUtils.putValue(FirstAct.this, "user-Info", "has_device", Integer.valueOf(i));
            if (str1.equals("userLogin"))
            {
              localUserBin.setHas_password(true);
              if (OperationDB.selectUserByUserIdDB(FirstAct.this, l1) != null)
                break label772;
              l4 = OperationDB.insertUserDB(FirstAct.this, localUserBin);
              if (l4 <= 0L)
                break label907;
              SharedPreferenceUtils.putValue(FirstAct.this, "user-Info", "user_id", Long.valueOf(l1));
              if (localUserBin.getRole_id() <= 0L)
                break label786;
              AsyncMessageUtils.loadRoleAndRoleInfosFromServer(FirstAct.this, l1, this);
              PushManager.startWork(FirstAct.this, 0, "7Y4L3bMxLgil7WbPuDyaaPCa");
            }
          }
          else
          {
            if (FirstAct.this.thirdPartType >= 1);
            switch (FirstAct.this.thirdPartType)
            {
            default:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
              while (true)
              {
                boolean bool1 = localResponseEntity.getResp().has("has_device");
                i = 0;
                if (!bool1)
                  break;
                i = localResponseEntity.getResp().getInt("has_device");
                break;
                str4 = localResponseEntity.getResp().getString("weibo_id");
                continue;
                str6 = localResponseEntity.getResp().getString("qq_id");
                continue;
                str7 = localResponseEntity.getResp().getString("dayima_id");
                continue;
                str5 = localResponseEntity.getResp().getString("baidu_id");
                continue;
                str8 = localResponseEntity.getResp().getString("leyu_id");
                SharedPreferenceUtils.saveThirdPartLeYuName(FirstAct.this, l1, FirstAct.this.screen_name);
              }
            case 7:
            }
          }
        }
        catch (JSONException localJSONException)
        {
          while (true)
          {
            long l1;
            UserBin localUserBin;
            PicoocLoading.dismissDialog();
            PicoocToast.showToast(FirstAct.this, "登陆失败！请重试");
            localJSONException.printStackTrace();
            continue;
            String str9 = localResponseEntity.getResp().getString("jingdong_id");
            SharedPreferenceUtils.saveThirdPartJDName(FirstAct.this, l1, FirstAct.this.screen_name);
            continue;
            localUserBin.setHas_password(localResponseEntity.getResp().getInt("hasPassword"));
            continue;
            long l4 = OperationDB.updateUserDB(FirstAct.this, localUserBin);
            continue;
            if (FirstAct.this.thirdPartType == 2)
            {
              FirstAct.this.getUserInfor(SHARE_MEDIA.SINA, l1, FirstAct.this.thirdPartType);
            }
            else if (FirstAct.this.thirdPartType == 3)
            {
              if (FirstAct.this.getIntent().getBooleanExtra("isQQfrom", false))
                FirstAct.this.goInputMessage(FirstAct.this.thirdPartType, l1);
              else
                FirstAct.this.getUserInfor(SHARE_MEDIA.QZONE, l1, FirstAct.this.thirdPartType);
            }
            else
            {
              FirstAct.this.goInputMessage(FirstAct.this.thirdPartType, l1);
              continue;
              PicoocLoading.dismissDialog();
              PicoocToast.showToast(FirstAct.this, "登录失败!");
            }
          }
        }
        if (str1.equals("getRoles"))
        {
          long l5 = ((Long)SharedPreferenceUtils.getValue(FirstAct.this, "user-Info", "user_id", Long.class)).longValue();
          boolean bool3 = OperationDB.updateAllRolesAndRoleInfos(FirstAct.this, localResponseEntity.getResp(), l5);
          PicoocLoading.dismissDialog();
          if (bool3)
          {
            SharedPreferenceUtils.putValue(FirstAct.this, "user-Info", "role_id", Long.valueOf(FirstAct.this.main_role_id));
            Intent localIntent = new Intent(FirstAct.this, PicoocActivity3.class);
            localIntent.putExtra("isQQfrom", FirstAct.this.getIntent().getBooleanExtra("isQQfrom", false));
            localIntent.putExtra("openid", FirstAct.this.getIntent().getStringExtra("openid"));
            localIntent.putExtra("accesstoken", FirstAct.this.getIntent().getStringExtra("accesstoken"));
            FirstAct.this.startActivity(localIntent);
            FirstAct.this.finish();
            return;
          }
          PicoocToast.showToast(FirstAct.this, "获取个人信息失败");
          return;
        }
      }
      while (!str1.equals("findPassword"));
      label907: PicoocLoading.dismissDialog();
      PicoocToast.showToast(FirstAct.this, localResponseEntity.getMessage());
    }
  };
  private Intent intent;
  Boolean isExit = Boolean.valueOf(false);
  private LEYUUser leyuUser;
  private long main_role_id = 0L;
  private String profile_image_url = null;
  EditText pwd;
  private String screen_name = null;
  private ThirdPartLogin thirdPart;
  private int thirdPartType = -1;

  private void getUserInfor(SHARE_MEDIA paramSHARE_MEDIA, final long paramLong, final int paramInt)
  {
    UMServiceFactory.getUMSocialService("com.umeng.share", RequestType.SOCIAL).getPlatformInfo(this, paramSHARE_MEDIA, new SocializeListeners.UMDataListener()
    {
      public void onComplete(int paramAnonymousInt, Map<String, Object> paramAnonymousMap)
      {
        if (paramAnonymousMap != null)
        {
          FirstAct.this.screen_name = paramAnonymousMap.get("screen_name").toString();
          FirstAct.this.profile_image_url = paramAnonymousMap.get("profile_image_url").toString();
          if (paramInt != 2)
            break label109;
          SharedPreferenceUtils.saveThirdPartSinaName(FirstAct.this, paramLong, paramAnonymousMap.get("screen_name"));
        }
        while (true)
        {
          FirstAct.this.goInputMessage(paramInt, paramLong);
          return;
          label109: if (paramInt == 3)
            SharedPreferenceUtils.saveThirdPartqqName(FirstAct.this, paramLong, paramAnonymousMap.get("screen_name"));
        }
      }

      public void onStart()
      {
      }
    });
  }

  private void goInputMessage(int paramInt, long paramLong)
  {
    PicoocLoading.dismissDialog();
    Intent localIntent = new Intent(this, WriteReferenAct.class);
    if (getIntent().getBooleanExtra("isQQfrom", false))
    {
      this.screen_name = SharedPreferenceUtils.getThirdPartqqNmae(this, getIntent().getStringExtra("openid"));
      this.profile_image_url = SharedPreferenceUtils.getThirdPartqqTouXiangUrl(this, getIntent().getStringExtra("openid"));
    }
    localIntent.putExtra("userID", paramLong);
    localIntent.putExtra("screen_name", this.screen_name);
    localIntent.putExtra("profile_image_url", this.profile_image_url);
    startActivity(localIntent);
  }

  private void login()
  {
    String str1 = this.email.getText().toString().trim();
    String str2 = this.pwd.getText().toString().trim();
    Boolean localBoolean = Boolean.valueOf(ModUtils.isNumeric(str1));
    if (localBoolean.booleanValue());
    boolean bool2;
    for (boolean bool1 = ModUtils.isMobileNO(str1); ; bool1 = StringUtils.isEmail(str1))
    {
      bool2 = StringUtils.isPassword(str2);
      if (bool1)
        break label94;
      if (!localBoolean.booleanValue())
        break;
      PicoocToast.showToast(this, "手机号格式错误!");
      return;
    }
    PicoocToast.showToast(this, "邮箱格式错误!");
    return;
    label94: if (!bool2)
    {
      PicoocToast.showToast(this, "密码格式错误!");
      return;
    }
    PicoocLoading.showLoadingDialog(this);
    RequestEntity localRequestEntity = new RequestEntity("userLogin", "5.1");
    if (localBoolean.booleanValue())
    {
      localRequestEntity.addParam("userName", "");
      localRequestEntity.addParam("phone", str1);
    }
    while (true)
    {
      localRequestEntity.addParam("password", str2);
      localRequestEntity.addParam("baiduUserID", "");
      localRequestEntity.addParam("baiduChannelID", "");
      localRequestEntity.addParam("platform", "android");
      HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
      return;
      localRequestEntity.addParam("userName", str1);
      localRequestEntity.addParam("phone", "");
    }
  }

  public void clear()
  {
    this.screen_name = null;
    this.profile_image_url = null;
  }

  public void invit()
  {
    this.email = ((EditText)findViewById(2131100303));
    this.email.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.pwd = ((EditText)findViewById(2131100578));
    this.pwd.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.thirdPart = new ThirdPartLogin(this);
    this.thirdPart.setthirdPartLoginListener(this);
    ((TextView)findViewById(2131100591)).setTypeface(TypefaceUtils.getTypeface(this, null));
    String str = (String)SharedPreferenceUtils.getValue(this, "userName", "userName", String.class);
    if (((str != null) && (StringUtils.isEmail(str))) || (StringUtils.isMobileNO(str)))
      this.email.setText(str);
    if (getIntent().getBooleanExtra("isQQfrom", false))
      thirdPartLoginSuccess(getIntent().getStringExtra("openid"), getIntent().getStringExtra("accesstoken"), 3);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 1001) && (paramIntent != null))
    {
      str4 = paramIntent.getStringExtra("result");
      Log.i("qianmo2", "resultJD==" + str4);
      str5 = "";
      str6 = "";
    }
    while ((paramInt1 != 21) || (paramInt2 != -1) || (paramIntent == null))
      try
      {
        String str4;
        JSONObject localJSONObject2 = new JSONObject(str4);
        String str5 = localJSONObject2.getString("uid");
        String str6 = localJSONObject2.getString("access_token");
        this.screen_name = localJSONObject2.getString("user_nick");
        thirdPartLoginSuccess(str5, str6, 7);
        Log.i("picooc", "result===" + str4);
        return;
      }
      catch (JSONException localJSONException2)
      {
        while (true)
          localJSONException2.printStackTrace();
      }
    String str1 = paramIntent.getExtras().get("result_info").toString();
    Log.i("picooc", "leyu===" + str1.toString());
    String str2 = "";
    String str3 = "";
    try
    {
      JSONObject localJSONObject1 = new JSONObject(str1);
      str2 = localJSONObject1.getString("uid");
      str3 = localJSONObject1.getString("access_token");
      this.screen_name = localJSONObject1.getString("uname");
      this.profile_image_url = localJSONObject1.getString("uheadpic");
      thirdPartLoginSuccess(str2, str3, 6);
      return;
    }
    catch (JSONException localJSONException1)
    {
      while (true)
        localJSONException1.printStackTrace();
    }
  }

  public void onClick(View paramView)
  {
    this.thirdPartType = -1;
    clear();
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100582:
      this.intent = new Intent(this, ForgetAct.class);
      startActivity(this.intent);
      overridePendingTransition(2130968596, 2130968595);
      return;
    case 2131100368:
      this.intent = new Intent(this, RegistAct.class);
      startActivity(this.intent);
      overridePendingTransition(2130968596, 2130968595);
      return;
    case 2131100431:
      login();
      return;
    case 2131100587:
      this.flag = 5;
      this.thirdPart.startBaidu(this);
      return;
    case 2131100589:
      this.flag = 2;
      this.thirdPart.sina(this);
      return;
    case 2131100588:
      this.flag = 3;
      this.thirdPart.startQQZone(this);
      return;
    case 2131100590:
      this.flag = 7;
      this.thirdPart.loginJD(this);
      return;
    case 2131100592:
    }
    this.flag = 6;
    this.thirdPart.startLeyu(this);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903209);
    ((MyApplication)getApplication()).addActivity(this);
    invit();
  }

  protected void onDestroy()
  {
    super.onDestroy();
    ((MyApplication)getApplication()).removeActivity(this);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (!this.isExit.booleanValue()))
    {
      this.isExit = Boolean.valueOf(true);
      PicoocToast.showToast(this, "再按一次退出程序 ");
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          FirstAct.this.isExit = Boolean.valueOf(false);
          ((MyApplication)FirstAct.this.getApplication()).exit();
        }
      }
      , 2000L);
      return false;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void thirdPartLoginSuccess(String paramString1, String paramString2, int paramInt)
  {
    PicoocLoading.showLoadingDialog(this);
    if (paramInt == 101)
    {
      PicoocToast.showToast(this, "登陆失败!");
      return;
    }
    this.thirdPartType = paramInt;
    RequestEntity localRequestEntity = new RequestEntity("thirdPartLogin", "5.1");
    localRequestEntity.addParam("thirdPartId", paramString1);
    localRequestEntity.addParam("type", Integer.valueOf(paramInt));
    localRequestEntity.addParam("access_token", paramString2);
    HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
    this.thirdPart.startBaiduLogout(FrontiaAuthorization.MediaType.BAIDU, this);
    Log.i("qianmo2", "thirdPartId==" + paramString1);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     FirstAct
 * JD-Core Version:    0.6.2
 */
