package com.picooc.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import com.baidu.frontia.Frontia;
import com.baidu.frontia.FrontiaUser;
import com.baidu.frontia.api.FrontiaAuthorization;
import com.baidu.frontia.api.FrontiaAuthorization.MediaType;
import com.baidu.frontia.api.FrontiaAuthorizationListener.AuthorizationListener;
import com.hanyou.leyusdk.LEYUApplication;
import com.hanyou.leyusdk.LEYUApplication.ICallBack;
import com.picooc.JDAuthActivity;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeConfig;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.RequestType;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.SnsPostListener;
import com.umeng.socialize.controller.listener.SocializeListeners.SocializeClientListener;
import com.umeng.socialize.controller.listener.SocializeListeners.UMAuthListener;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.media.UMImage;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class ThirdPartLogin
{
  private static final String Scope_Basic = "basic";
  private static final String Scope_Netdisk = "netdisk";
  private static FrontiaAuthorization mAuthorization;
  public static QQAuth mQQAuth;
  String DESCRIPTOR = "com.umeng.share";
  private String JDOptionAppKey = "CA381094F4AFA6041D2DC0CB7DE7CDB1";
  private String JDOptionAppRedirectUri = "http://www.picooc.com/CH/index_CH.html";
  private String JDOptionAppSecret = "4cc4f28ef98140a2bc5587df882faf38";
  private int NavaigationColor = Color.parseColor("#ff6666");
  private final String contentUrl = "http://www.picooc.com/CH/index_CH.html";
  public LEYUApplication leyuapp;
  QQShare mQQShare = null;
  private Tencent mTencent;
  thirdPartLoginListener thirdPartLoginlistener;

  public ThirdPartLogin(Context paramContext)
  {
    mQQAuth = QQAuth.createInstance("101016543", paramContext);
    this.mQQShare = new QQShare(paramContext, mQQAuth.getQQToken());
    this.mTencent = Tencent.createInstance("101016543", paramContext);
  }

  private void addWXPlatform(Context paramContext, UMSocialService paramUMSocialService)
  {
    paramUMSocialService.getConfig().supportWXCirclePlatform(paramContext, "wx03f3ff480b0f89bc", "http://www.picooc.com/CH/index_CH.html");
  }

  private void addWXPlatformTwo(Context paramContext, UMSocialService paramUMSocialService)
  {
    paramUMSocialService.getConfig().supportWXPlatform(paramContext, "wx03f3ff480b0f89bc", "http://www.picooc.com/CH/index_CH.html");
  }

  private void doShareToQQ(final Bundle paramBundle, final Context paramContext)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        Looper.prepare();
        ThirdPartLogin.this.mQQShare.shareToQQ((Activity)paramContext, paramBundle, new IUiListener()
        {
          public void onCancel()
          {
          }

          public void onComplete(Object paramAnonymous2Object)
          {
            Toast.makeText((Activity)this.val$context, "分享完成", 0).show();
          }

          public void onError(UiError paramAnonymous2UiError)
          {
            Toast.makeText((Activity)this.val$context, "分享失败", 0).show();
          }
        });
        Looper.loop();
      }
    }).start();
  }

  public void addAuth(Context paramContext)
  {
    mQQAuth.login((Activity)paramContext, "all", new addBaseUiListener(null));
  }

  public void addAuthOld(Context paramContext)
  {
    mQQAuth.login((Activity)paramContext, "all", new addBaseUiListenerOld(null));
  }

  public void delete(SHARE_MEDIA paramSHARE_MEDIA, Context paramContext)
  {
    UMServiceFactory.getUMSocialService("com.umeng.share", RequestType.SOCIAL).deleteOauth(paramContext, paramSHARE_MEDIA, new SocializeListeners.SocializeClientListener()
    {
      public void onComplete(int paramAnonymousInt, SocializeEntity paramAnonymousSocializeEntity)
      {
      }

      public void onStart()
      {
      }
    });
  }

  public boolean isAuth(String paramString1, String paramString2, String paramString3)
  {
    this.mTencent.getAppId();
    this.mTencent.getAccessToken();
    Log.i("picooc", "-------------getAppId=" + this.mTencent.getOpenId() + "------getAccessToken()==" + this.mTencent.getAccessToken());
    this.mTencent.setAccessToken(paramString2, paramString3);
    this.mTencent.setOpenId(paramString1);
    String str = this.mTencent.getOpenId();
    Log.i("picooc", "-------------getOpenid=" + str + "------token==" + this.mTencent.getAccessToken() + " isAurth==" + mQQAuth.isSessionValid());
    return this.mTencent.isSessionValid();
  }

  public void loginJD(Context paramContext)
  {
    Intent localIntent = new Intent(paramContext, JDAuthActivity.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("JDOptionAppKey", this.JDOptionAppKey);
    localBundle.putString("JDOptionAppSecret", this.JDOptionAppSecret);
    localBundle.putString("JDOptionAppRedirectUri", this.JDOptionAppRedirectUri);
    localBundle.putInt("NavaigationColor", this.NavaigationColor);
    localIntent.putExtra("JDAuth", localBundle);
    ((Activity)paramContext).startActivityForResult(localIntent, 1001);
    ((Activity)paramContext).overridePendingTransition(2130968580, 2130968577);
  }

  public void loginQQOutho(Context paramContext)
  {
    mQQAuth.login((Activity)paramContext, "all", new BaseUiListener(null));
  }

  public void outJD()
  {
  }

  public void outLeYu()
  {
    if (this.leyuapp != null)
      this.leyuapp.Logout();
  }

  public void setthirdPartLoginListener(thirdPartLoginListener paramthirdPartLoginListener)
  {
    this.thirdPartLoginlistener = paramthirdPartLoginListener;
  }

  public void shareQQ(Context paramContext, String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("imageLocalUrl", paramString);
    localBundle.putString("appName", "picooc");
    localBundle.putInt("req_type", 5);
    localBundle.putInt("cflag", 1);
    localBundle.putString("title", "分享");
    doShareToQQ(localBundle, paramContext);
  }

  public void shareQzone(Context paramContext, String paramString)
  {
    shareQQ(paramContext, paramString);
  }

  public void shareSina(final Context paramContext, Bitmap paramBitmap)
  {
    UMSocialService localUMSocialService = UMServiceFactory.getUMSocialService(this.DESCRIPTOR, RequestType.SOCIAL);
    localUMSocialService.setShareImage(new UMImage(paramContext, paramBitmap));
    localUMSocialService.postShare(paramContext, SHARE_MEDIA.SINA, new SocializeListeners.SnsPostListener()
    {
      public void onComplete(SHARE_MEDIA paramAnonymousSHARE_MEDIA, int paramAnonymousInt, SocializeEntity paramAnonymousSocializeEntity)
      {
        Toast.makeText(paramContext, "分享完成", 1).show();
      }

      public void onStart()
      {
      }
    });
  }

  public void sina(final Context paramContext)
  {
    SHARE_MEDIA localSHARE_MEDIA = SHARE_MEDIA.SINA;
    UMServiceFactory.getUMSocialService(this.DESCRIPTOR, RequestType.SOCIAL).doOauthVerify(paramContext, localSHARE_MEDIA, new SocializeListeners.UMAuthListener()
    {
      public void onCancel(SHARE_MEDIA paramAnonymousSHARE_MEDIA)
      {
      }

      public void onComplete(Bundle paramAnonymousBundle, SHARE_MEDIA paramAnonymousSHARE_MEDIA)
      {
        if ((paramAnonymousBundle != null) && (!com.picooc.utils.TextUtils.isEmpty(paramAnonymousBundle.getString("uid"))))
        {
          ThirdPartLogin.this.thirdPartLoginlistener.thirdPartLoginSuccess(paramAnonymousBundle.getString("uid"), "", 2);
          return;
        }
        PicoocToast.showToast((Activity)paramContext, "授权失败");
      }

      public void onError(SocializeException paramAnonymousSocializeException, SHARE_MEDIA paramAnonymousSHARE_MEDIA)
      {
      }

      public void onStart(SHARE_MEDIA paramAnonymousSHARE_MEDIA)
      {
      }
    });
  }

  public void startBaidu(final Context paramContext)
  {
    mAuthorization = Frontia.getAuthorization();
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("basic");
    localArrayList.add("netdisk");
    mAuthorization.authorize((Activity)paramContext, FrontiaAuthorization.MediaType.BAIDU.toString(), localArrayList, new FrontiaAuthorizationListener.AuthorizationListener()
    {
      public void onCancel()
      {
        Log.i("picooc", "cancel:");
      }

      public void onFailure(int paramAnonymousInt, String paramAnonymousString)
      {
        Log.i("picooc", "errCode:" + paramAnonymousInt + ", errMsg:" + paramAnonymousString);
        PicoocLoading.dismissDialog();
        PicoocToast.showToast((Activity)paramContext, paramAnonymousString);
      }

      public void onSuccess(FrontiaUser paramAnonymousFrontiaUser)
      {
        Log.i("picooc", "social id:" + paramAnonymousFrontiaUser.getMediaUserId() + "\n" + "token: " + paramAnonymousFrontiaUser.getAccessToken() + "\n" + "expired: " + paramAnonymousFrontiaUser.getExpiresIn());
        SharedPreferenceUtils.putValue(paramContext, "BaiduUserSession", "token", paramAnonymousFrontiaUser.getAccessToken());
        ThirdPartLogin.this.thirdPartLoginlistener.thirdPartLoginSuccess(paramAnonymousFrontiaUser.getMediaUserId(), paramAnonymousFrontiaUser.getAccessToken(), 5);
      }
    });
  }

  public void startBaiduLogout(FrontiaAuthorization.MediaType paramMediaType, Context paramContext)
  {
    Frontia.init(paramContext.getApplicationContext(), "7Y4L3bMxLgil7WbPuDyaaPCa");
    mAuthorization = Frontia.getAuthorization();
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("basic");
    localArrayList.add("netdisk");
    mAuthorization.clearAuthorizationInfo(paramMediaType.toString());
  }

  public void startLeyu(final Context paramContext)
  {
    this.leyuapp = new LEYUApplication(paramContext);
    this.leyuapp.GetDeveloperAccessToken();
    Log.i("qianmo2", "3333333333leyuapp==" + this.leyuapp.toString());
    this.leyuapp._callback = new LEYUApplication.ICallBack()
    {
      public void OnCompleted(String paramAnonymousString)
      {
        Toast.makeText(paramContext, "OnCompleted:" + paramAnonymousString, 1).show();
      }

      public void ReturnAccessToken(String paramAnonymousString)
      {
        ThirdPartLogin.this.leyuapp.LEYULoginview();
      }

      public void onFailed(String paramAnonymousString)
      {
        Toast.makeText(paramContext, "onFailed:" + paramAnonymousString, 1).show();
      }
    };
  }

  public void startQQZone(Context paramContext)
  {
    loginQQOutho(paramContext);
  }

  public void weinxin(final Context paramContext, Bitmap paramBitmap)
  {
    UMSocialService localUMSocialService = UMServiceFactory.getUMSocialService(this.DESCRIPTOR, RequestType.SOCIAL);
    addWXPlatformTwo(paramContext, localUMSocialService);
    localUMSocialService.setShareImage(new UMImage(paramContext, paramBitmap));
    localUMSocialService.postShare(paramContext, SHARE_MEDIA.WEIXIN, new SocializeListeners.SnsPostListener()
    {
      public void onComplete(SHARE_MEDIA paramAnonymousSHARE_MEDIA, int paramAnonymousInt, SocializeEntity paramAnonymousSocializeEntity)
      {
        Toast.makeText((Activity)paramContext, "分享完成", 0).show();
      }

      public void onStart()
      {
        Toast.makeText((Activity)paramContext, "开始分享", 0).show();
      }
    });
  }

  public void weinxinCircle(final Context paramContext, Bitmap paramBitmap)
  {
    UMSocialService localUMSocialService = UMServiceFactory.getUMSocialService(this.DESCRIPTOR, RequestType.SOCIAL);
    addWXPlatform(paramContext, localUMSocialService);
    localUMSocialService.setShareImage(new UMImage(paramContext, paramBitmap));
    SocializeConfig localSocializeConfig = localUMSocialService.getConfig();
    SHARE_MEDIA[] arrayOfSHARE_MEDIA = new SHARE_MEDIA[4];
    arrayOfSHARE_MEDIA[0] = SHARE_MEDIA.WEIXIN_CIRCLE;
    arrayOfSHARE_MEDIA[1] = SHARE_MEDIA.SINA;
    arrayOfSHARE_MEDIA[2] = SHARE_MEDIA.WEIXIN;
    arrayOfSHARE_MEDIA[3] = SHARE_MEDIA.TENCENT;
    localSocializeConfig.setPlatformOrder(arrayOfSHARE_MEDIA);
    localUMSocialService.postShare(paramContext, SHARE_MEDIA.WEIXIN_CIRCLE, new SocializeListeners.SnsPostListener()
    {
      public void onComplete(SHARE_MEDIA paramAnonymousSHARE_MEDIA, int paramAnonymousInt, SocializeEntity paramAnonymousSocializeEntity)
      {
        Toast.makeText((Activity)paramContext, "分享完成", 0).show();
      }

      public void onStart()
      {
        Toast.makeText((Activity)paramContext, "开始分享", 0).show();
      }
    });
  }

  private class BaseUiListener
    implements IUiListener
  {
    private BaseUiListener()
    {
    }

    protected void doComplete(JSONObject paramJSONObject)
    {
    }

    public void onCancel()
    {
    }

    public void onComplete(Object paramObject)
    {
      try
      {
        JSONObject localJSONObject = new JSONObject(paramObject.toString());
        ThirdPartLogin.this.thirdPartLoginlistener.thirdPartLoginSuccess(localJSONObject.getString("openid"), localJSONObject.getString("access_token"), 3);
        Log.i("picooc", "openid==" + localJSONObject.getString("openid") + "----token==" + localJSONObject.getString("access_token"));
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        ThirdPartLogin.this.thirdPartLoginlistener.thirdPartLoginSuccess("", "", 101);
      }
    }

    public void onError(UiError paramUiError)
    {
    }
  }

  private class addBaseUiListener
    implements IUiListener
  {
    private addBaseUiListener()
    {
    }

    public void onCancel()
    {
      ThirdPartLogin.this.thirdPartLoginlistener.thirdPartLoginSuccess("", "", 34);
    }

    public void onComplete(Object paramObject)
    {
      try
      {
        JSONObject localJSONObject = new JSONObject(paramObject.toString());
        ThirdPartLogin.this.thirdPartLoginlistener.thirdPartLoginSuccess(localJSONObject.getString("openid"), localJSONObject.getString("access_token"), 33);
        Log.i("picooc", "openid==" + localJSONObject.getString("openid") + "----token==" + localJSONObject.getString("access_token"));
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }

    public void onError(UiError paramUiError)
    {
      ThirdPartLogin.this.thirdPartLoginlistener.thirdPartLoginSuccess("", "", 34);
    }
  }

  private class addBaseUiListenerOld
    implements IUiListener
  {
    private addBaseUiListenerOld()
    {
    }

    public void onCancel()
    {
      ThirdPartLogin.this.thirdPartLoginlistener.thirdPartLoginSuccess("", "", 34);
    }

    public void onComplete(Object paramObject)
    {
      try
      {
        JSONObject localJSONObject = new JSONObject(paramObject.toString());
        ThirdPartLogin.this.thirdPartLoginlistener.thirdPartLoginSuccess(localJSONObject.getString("openid"), localJSONObject.getString("access_token"), 32);
        Log.i("picooc", "openid==" + localJSONObject.getString("openid") + "----token==" + localJSONObject.getString("access_token"));
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }

    public void onError(UiError paramUiError)
    {
      ThirdPartLogin.this.thirdPartLoginlistener.thirdPartLoginSuccess("", "", 34);
    }
  }

  public static abstract interface thirdPartLoginListener
  {
    public abstract void thirdPartLoginSuccess(String paramString1, String paramString2, int paramInt);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ThirdPartLogin
 * JD-Core Version:    0.6.2
 */
