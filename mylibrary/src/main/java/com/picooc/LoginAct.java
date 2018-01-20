package com.picooc;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.frontia.Frontia;
import com.baidu.frontia.FrontiaUser;
import com.baidu.frontia.api.FrontiaAuthorization;
import com.baidu.frontia.api.FrontiaAuthorization.MediaType;
import com.baidu.frontia.api.FrontiaAuthorizationListener.AuthorizationListener;
import com.picooc.db.OperationDB;
import com.picooc.domain.UserBin;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.DateUtils;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.StringUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeConfig;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.RequestType;
import com.umeng.socialize.controller.UMInfoAgent;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.SocializeClientListener;
import com.umeng.socialize.controller.listener.SocializeListeners.UMAuthListener;
import com.umeng.socialize.controller.listener.SocializeListeners.UMDataListener;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginAct extends PicoocActivity
{
  private static final int ANIM_DURING = 500;
  public static final String DESCRIPTOR = "com.umeng.share";
  private static final int MODE_FINDPASS = 3;
  private static final int MODE_LOGIN = 2;
  private static final int MODE_REGISTER = 1;
  private static final String Scope_Basic = "basic";
  private static final String Scope_Netdisk = "netdisk";
  RelativeLayout VilbleRelayout;
  AnimUtils animUtils;
  Button bootomLogin;
  UMSocialService controller;
  int count = 0;
  int distanceY;
  private EditText emailEdit;
  TextView forgetPwd;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(LoginAct.this, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(LoginAct.this, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      String str = localResponseEntity.getMethod();
      if (str.equals("userRegister"))
        PicoocLoading.dismissDialog();
      label554: label596: 
      do
      {
        try
        {
          long l4 = localResponseEntity.getResp().getLong("userID");
          UserBin localUserBin2 = new UserBin(l4, LoginAct.this.emailEdit.getText().toString().trim(), 0L, 0L, "", "", "", "", "");
          if (OperationDB.insertUserDB(LoginAct.this, localUserBin2) > 0L)
          {
            SharedPreferenceUtils.putValue(LoginAct.this, "user-Info", "user_id", Long.valueOf(l4));
            Intent localIntent4 = new Intent(LoginAct.this, WriteReferenAct.class);
            localIntent4.putExtra("userID", l4);
            LoginAct.this.startActivity(localIntent4);
            LoginAct.this.finish();
          }
          return;
        }
        catch (JSONException localJSONException2)
        {
          localJSONException2.printStackTrace();
          return;
        }
        if ((str.equals("userLogin")) || (str.equals("thirdPartLogin")))
        {
          long l1;
          UserBin localUserBin1;
          while (true)
          {
            try
            {
              l1 = localResponseEntity.getResp().getLong("userID");
              localUserBin1 = new UserBin();
              localUserBin1.setUser_id(l1);
              localUserBin1.setTime(DateUtils.changeStringToTimestamp(localResponseEntity.getResp().getString("time"), "yyyy-MM-dd HH:mm:ss"));
              localUserBin1.setEmail(localResponseEntity.getResp().getString("email"));
              localUserBin1.setPhone_no(localResponseEntity.getResp().getString("phoneNo"));
              localUserBin1.setWeibo_id(localResponseEntity.getResp().getString("weibo_id"));
              localUserBin1.setBaidu_id(localResponseEntity.getResp().getString("baidu_id"));
              localUserBin1.setQQ_id(localResponseEntity.getResp().getString("qq_id"));
              localUserBin1.setDayima_id(localResponseEntity.getResp().getString("dayima_id"));
              localUserBin1.setRole_id(localResponseEntity.getResp().getLong("roleID"));
              if (OperationDB.selectUserByUserIdDB(LoginAct.this, l1) == null)
              {
                l2 = OperationDB.insertUserDB(LoginAct.this, localUserBin1);
                if (l2 <= 0L)
                  break label596;
                SharedPreferenceUtils.putValue(LoginAct.this, "user-Info", "user_id", Long.valueOf(l1));
                if (localUserBin1.getRole_id() <= 0L)
                  break label554;
                LoginAct.this.main_role_id = localUserBin1.getRole_id();
                if (OperationDB.selectRoleDB(LoginAct.this, LoginAct.this.main_role_id) != null)
                  break;
                RequestEntity localRequestEntity = new RequestEntity("getRoles", "3.0");
                localRequestEntity.addParam("userID", Long.valueOf(l1));
                HttpUtils.getJson(LoginAct.this, localRequestEntity, this);
                return;
              }
            }
            catch (JSONException localJSONException1)
            {
              PicoocLoading.dismissDialog();
              localJSONException1.printStackTrace();
              return;
            }
            long l2 = OperationDB.updateUserDB(LoginAct.this, localUserBin1);
          }
          PicoocLoading.dismissDialog();
          SharedPreferenceUtils.putValue(LoginAct.this, "user-Info", "role_id", Long.valueOf(localUserBin1.getRole_id()));
          Intent localIntent2 = new Intent(LoginAct.this, PicoocActivity3.class);
          LoginAct.this.startActivity(localIntent2);
          LoginAct.this.finish();
          return;
          Intent localIntent1 = new Intent(LoginAct.this, WriteReferenAct.class);
          localIntent1.putExtra("userID", l1);
          LoginAct.this.startActivity(localIntent1);
          LoginAct.this.finish();
          return;
          PicoocLoading.dismissDialog();
          PicoocToast.showToast(LoginAct.this, "登录失败!");
          return;
        }
        if (str.equals("getRoles"))
        {
          long l3 = ((Long)SharedPreferenceUtils.getValue(LoginAct.this, "user-Info", "user_id", Long.class)).longValue();
          boolean bool = OperationDB.updateAllRolesAndRoleInfos(LoginAct.this, localResponseEntity.getResp(), l3);
          PicoocLoading.dismissDialog();
          if (bool)
          {
            SharedPreferenceUtils.putValue(LoginAct.this, "user-Info", "role_id", Long.valueOf(LoginAct.this.main_role_id));
            Intent localIntent3 = new Intent(LoginAct.this, PicoocActivity3.class);
            LoginAct.this.startActivity(localIntent3);
            LoginAct.this.finish();
            return;
          }
          PicoocToast.showToast(LoginAct.this, "获取个人信息失败");
          return;
        }
      }
      while (!str.equals("findPassword"));
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(LoginAct.this, localResponseEntity.getMessage());
    }
  };
  InputMethodManager imm;
  Boolean isExit = Boolean.valueOf(false);
  LinearLayout loginLayout;
  ImageView logo;
  private FrontiaAuthorization mAuthorization;
  private long main_role_id = 0L;
  private int mode = 1;
  private EditText pwdEdit;
  private EditText pwdEditAgain;
  Button regist_button;
  RelativeLayout relayXingxing;
  RelativeLayout relayout;

  private void changeToFindPasswordMode()
  {
    if (Integer.parseInt(this.forgetPwd.getTag().toString()) == 1)
    {
      this.mode = 3;
      this.forgetPwd.setTag("2");
      this.animUtils.missAnima(this.pwdEdit, 500L);
      this.animUtils.upMove(this.regist_button, 0, -this.pwdEdit.getHeight(), 500);
      this.animUtils.missAnima(this.relayXingxing, 500L);
      this.forgetPwd.setText(2131361815);
      this.regist_button.setText(2131361814);
      return;
    }
    this.mode = 2;
    this.forgetPwd.setTag("1");
    this.animUtils.downMove(this.regist_button, -this.pwdEdit.getHeight(), 0, 500);
    this.animUtils.showAnima(this.pwdEdit, 500L);
    this.animUtils.showAnima(this.relayXingxing, 500L);
    this.regist_button.setText(2131361795);
    this.forgetPwd.setText(2131361812);
  }

  private void changeToLoginMode()
  {
    this.mode = 2;
    this.animUtils.upMove(this.relayout, 0, (int)(0.92F * -this.logo.getHeight()), 500);
    this.animUtils.missAnima(this.loginLayout, 500L);
    this.animUtils.downMove(this.relayXingxing, 0, this.VilbleRelayout.getHeight(), 500);
    this.animUtils.showAnima(this.VilbleRelayout, 500L);
    this.regist_button.setText("登录");
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        LoginAct.this.imm.toggleSoftInput(0, 2);
      }
    }
    , 500L);
  }

  private void changeToRegisterMode()
  {
    if (this.mode == 3)
      changeToFindPasswordMode();
    this.mode = 1;
    this.animUtils.upMove(this.relayXingxing, this.VilbleRelayout.getHeight(), 0, 500);
    this.animUtils.missAnima(this.VilbleRelayout, 500L);
    this.animUtils.upMove(this.relayout, (int)(0.92F * -this.logo.getHeight()), 0, 500);
    this.animUtils.showAnima(this.loginLayout, 500L);
    this.regist_button.setText("注册");
    this.imm.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), 2);
  }

  private void findPassword()
  {
    String str = this.emailEdit.getText().toString().trim();
    if (StringUtils.isEmail(str))
    {
      PicoocLoading.showLoadingDialog(this);
      RequestEntity localRequestEntity = new RequestEntity("findPassword", "1.0");
      localRequestEntity.addParam("email", str);
      HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
      return;
    }
    PicoocToast.showToast(this, "邮箱格式错误!");
  }

  private void login()
  {
    String str1 = this.emailEdit.getText().toString().trim();
    boolean bool1 = StringUtils.isEmail(str1);
    String str2 = this.pwdEdit.getText().toString().trim();
    boolean bool2 = StringUtils.isPassword(str2);
    if (!bool1)
    {
      PicoocToast.showToast(this, "邮箱格式错误!");
      return;
    }
    if (!bool2)
    {
      PicoocToast.showToast(this, "密码格式错误!");
      return;
    }
    PicoocLoading.showLoadingDialog(this);
    RequestEntity localRequestEntity = new RequestEntity("userLogin", "2.0");
    localRequestEntity.addParam("userName", str1);
    localRequestEntity.addParam("password", str2);
    HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
  }

  private void register()
  {
    String str1 = this.emailEdit.getText().toString().trim();
    boolean bool1 = StringUtils.isEmail(str1);
    String str2 = this.pwdEdit.getText().toString().trim();
    boolean bool2 = StringUtils.isPassword(str2);
    if (!bool1)
    {
      PicoocToast.showToast(this, "邮箱格式错误!");
      return;
    }
    if (!bool2)
    {
      PicoocToast.showToast(this, "密码格式错误!");
      return;
    }
    PicoocLoading.showLoadingDialog(this);
    RequestEntity localRequestEntity = new RequestEntity("userRegister", "1.0");
    localRequestEntity.addParam("email", str1);
    localRequestEntity.addParam("password", str2);
    HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
  }

  public void delete(final SHARE_MEDIA paramSHARE_MEDIA)
  {
    this.controller.deleteOauth(this, paramSHARE_MEDIA, new SocializeListeners.SocializeClientListener()
    {
      public void onComplete(int paramAnonymousInt, SocializeEntity paramAnonymousSocializeEntity)
      {
        Log.d("TestData", paramAnonymousInt + "      sina=" + UMInfoAgent.isOauthed(LoginAct.this, paramSHARE_MEDIA));
      }

      public void onStart()
      {
        Log.d("TestData", "sina=" + UMInfoAgent.isOauthed(LoginAct.this, paramSHARE_MEDIA));
      }
    });
  }

  public void getUserInfor(SHARE_MEDIA paramSHARE_MEDIA)
  {
    this.controller.getPlatformInfo(this, paramSHARE_MEDIA, new SocializeListeners.UMDataListener()
    {
      public void onComplete(int paramAnonymousInt, Map<String, Object> paramAnonymousMap)
      {
        Log.i("picooc", "arg1===" + paramAnonymousMap);
      }

      public void onStart()
      {
      }
    });
  }

  public void invit()
  {
    this.VilbleRelayout = ((RelativeLayout)findViewById(2131100581));
    this.relayXingxing = ((RelativeLayout)findViewById(2131100555));
    this.imm = ((InputMethodManager)getSystemService("input_method"));
    this.logo = ((ImageView)findViewById(2131100336));
    this.regist_button = ((Button)findViewById(2131100580));
    this.forgetPwd = ((TextView)findViewById(2131100582));
    this.bootomLogin = ((Button)findViewById(2131100586));
    this.relayout = ((RelativeLayout)findViewById(2131100278));
    this.loginLayout = ((LinearLayout)findViewById(2131100419));
    this.emailEdit = ((EditText)findViewById(2131100303));
    Typeface localTypeface = com.picooc.utils.TextUtils.getTypeface(this, null);
    this.emailEdit.setTypeface(localTypeface);
    this.pwdEdit = ((EditText)findViewById(2131100578));
    this.pwdEdit.setTypeface(localTypeface);
    this.pwdEdit.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        Log.i("qianmo2", "hasFocus==" + paramAnonymousBoolean);
        if (paramAnonymousBoolean)
        {
          LoginAct.this.pwdEdit.setHint("6~25位数字与字母混合");
          return;
        }
        LoginAct.this.pwdEdit.setHint("密码");
      }
    });
    String str = (String)SharedPreferenceUtils.getValue(this, "userName", "userName", String.class);
    if (((str != null) && (StringUtils.isEmail(str))) || (StringUtils.isMobileNO(str)))
      this.emailEdit.setText(str);
    this.pwdEditAgain = ((EditText)findViewById(2131100579));
    this.controller = UMServiceFactory.getUMSocialService("com.umeng.share", RequestType.SOCIAL);
    this.controller.getConfig().setSsoHandler(new QZoneSsoHandler(this));
    EditText localEditText = this.emailEdit;
    InputFilter[] arrayOfInputFilter = new InputFilter[1];
    arrayOfInputFilter[0] = new InputFilter.LengthFilter(200);
    localEditText.setFilters(arrayOfInputFilter);
    this.emailEdit.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
      }

      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }
    });
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    String str1 = "null";
    try
    {
      Bundle localBundle = paramIntent.getExtras();
      Set localSet = localBundle.keySet();
      if (localSet.size() > 0)
        str1 = "result size:" + localSet.size();
      Iterator localIterator = localSet.iterator();
      while (true)
      {
        boolean bool = localIterator.hasNext();
        if (!bool)
        {
          Log.d("TestData", "onActivityResult   " + paramInt1 + "   " + paramInt2 + "   " + str1);
          UMSsoHandler localUMSsoHandler = UMServiceFactory.getUMSocialService("com.umeng.share", RequestType.SOCIAL).getConfig().getSsoHandler(paramInt1);
          if (localUMSsoHandler != null)
          {
            localUMSsoHandler.authorizeCallBack(paramInt1, paramInt2, paramIntent);
            Log.d("", "#### ssoHandler.authorizeCallBack");
          }
          super.onActivityResult(paramInt1, paramInt2, paramIntent);
          return;
        }
        String str2 = (String)localIterator.next();
        Object localObject = localBundle.get(str2);
        Log.d("TestData", "Result:" + str2 + "   " + localObject.toString());
      }
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131100585:
    default:
    case 2131100586:
    case 2131100580:
      do
      {
        return;
        changeToLoginMode();
        startBaidu();
        sina();
        return;
        if (this.mode == 2)
        {
          login();
          return;
        }
        if (this.mode == 1)
        {
          register();
          return;
        }
      }
      while (this.mode != 3);
      findPassword();
      return;
    case 2131100582:
      changeToFindPasswordMode();
      return;
    case 2131100583:
      changeToRegisterMode();
      return;
    case 2131100584:
      sina();
      return;
    case 2131099944:
    }
    qzone();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903208);
    invit();
    this.animUtils = new AnimUtils(this);
    this.mAuthorization = Frontia.getAuthorization();
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
          LoginAct.this.isExit = Boolean.valueOf(false);
        }
      }
      , 2000L);
      return false;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onPause()
  {
    super.onPause();
  }

  protected void onStop()
  {
    super.onStop();
  }

  public void qzone()
  {
    PicoocLoading.showLoadingDialog(this);
    this.controller.doOauthVerify(this, SHARE_MEDIA.QZONE, new SocializeListeners.UMAuthListener()
    {
      public void onCancel(SHARE_MEDIA paramAnonymousSHARE_MEDIA)
      {
      }

      public void onComplete(Bundle paramAnonymousBundle, SHARE_MEDIA paramAnonymousSHARE_MEDIA)
      {
        Toast.makeText(LoginAct.this, "授权成功,uid=" + paramAnonymousBundle.getString("uid"), 0).show();
        LoginAct.this.getUserInfor(SHARE_MEDIA.QZONE);
        PicoocLoading.showLoadingDialog(LoginAct.this);
        RequestEntity localRequestEntity = new RequestEntity("thirdPartLogin", "5.1");
        localRequestEntity.addParam("thirdPartId", paramAnonymousBundle.getString("uid"));
        localRequestEntity.addParam("type", Integer.valueOf(3));
        HttpUtils.getJson(LoginAct.this, localRequestEntity, LoginAct.this.httpHandler);
      }

      public void onError(SocializeException paramAnonymousSocializeException, SHARE_MEDIA paramAnonymousSHARE_MEDIA)
      {
        Toast.makeText(LoginAct.this, "授权错误,错误码：" + paramAnonymousSocializeException.getErrorCode(), 0).show();
      }

      public void onStart(SHARE_MEDIA paramAnonymousSHARE_MEDIA)
      {
      }
    });
  }

  public void sina()
  {
    SHARE_MEDIA localSHARE_MEDIA = SHARE_MEDIA.SINA;
    UMSocialService localUMSocialService = UMServiceFactory.getUMSocialService("com.umeng.share", RequestType.SOCIAL);
    localUMSocialService.getConfig().setSsoHandler(new SinaSsoHandler());
    localUMSocialService.doOauthVerify(this, localSHARE_MEDIA, new SocializeListeners.UMAuthListener()
    {
      public void onCancel(SHARE_MEDIA paramAnonymousSHARE_MEDIA)
      {
      }

      public void onComplete(Bundle paramAnonymousBundle, SHARE_MEDIA paramAnonymousSHARE_MEDIA)
      {
        LoginAct.this.getUserInfor(SHARE_MEDIA.SINA);
        if ((paramAnonymousBundle != null) && (!android.text.TextUtils.isEmpty(paramAnonymousBundle.getString("uid"))))
        {
          Toast.makeText(LoginAct.this, "授权成功.id==" + paramAnonymousBundle.getString("uid"), 0).show();
          PicoocLoading.showLoadingDialog(LoginAct.this);
          RequestEntity localRequestEntity = new RequestEntity("thirdPartLogin", "5.1");
          localRequestEntity.addParam("thirdPartId", paramAnonymousBundle.getString("uid"));
          localRequestEntity.addParam("type", Integer.valueOf(2));
          HttpUtils.getJson(LoginAct.this, localRequestEntity, LoginAct.this.httpHandler);
          return;
        }
        Toast.makeText(LoginAct.this, "授权失败", 0).show();
      }

      public void onError(SocializeException paramAnonymousSocializeException, SHARE_MEDIA paramAnonymousSHARE_MEDIA)
      {
      }

      public void onStart(SHARE_MEDIA paramAnonymousSHARE_MEDIA)
      {
      }
    });
  }

  protected void startBaidu()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("basic");
    localArrayList.add("netdisk");
    this.mAuthorization.authorize(this, FrontiaAuthorization.MediaType.BAIDU.toString(), localArrayList, new FrontiaAuthorizationListener.AuthorizationListener()
    {
      public void onCancel()
      {
        Log.i("picooc", "cancel:");
      }

      public void onFailure(int paramAnonymousInt, String paramAnonymousString)
      {
        Log.i("picooc", "errCode:" + paramAnonymousInt + ", errMsg:" + paramAnonymousString);
      }

      public void onSuccess(FrontiaUser paramAnonymousFrontiaUser)
      {
        Log.i("picooc", "social id:" + paramAnonymousFrontiaUser.getId() + "\n" + "token: " + paramAnonymousFrontiaUser.getAccessToken() + "\n" + "expired: " + paramAnonymousFrontiaUser.getExpiresIn());
        PicoocLoading.showLoadingDialog(LoginAct.this);
        RequestEntity localRequestEntity = new RequestEntity("thirdPartLogin", "5.1");
        localRequestEntity.addParam("thirdPartId", paramAnonymousFrontiaUser.getId());
        localRequestEntity.addParam("type", Integer.valueOf(5));
        HttpUtils.getJson(LoginAct.this, localRequestEntity, LoginAct.this.httpHandler);
      }
    });
  }

  protected void startBaiduLogout()
  {
    if (this.mAuthorization.clearAuthorizationInfo(FrontiaAuthorization.MediaType.BAIDU.toString()))
    {
      Log.i("picooc", "百度退出成功");
      return;
    }
    Log.i("picooc", "百度退出失败");
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LoginAct
 * JD-Core Version:    0.6.2
 */
