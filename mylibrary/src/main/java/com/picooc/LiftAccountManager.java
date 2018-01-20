package com.picooc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baidu.frontia.api.FrontiaAuthorization.MediaType;
import com.picooc.constant.ThemeConstant;
import com.picooc.db.OperationDB;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.ThirdPartLogin;
import com.picooc.utils.ThirdPartLogin.thirdPartLoginListener;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.AnimUtils.selectHeitListener;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import com.picooc.widget.picoocProgress.PicoocAlertDialog;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.RequestType;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.UMDataListener;
import java.util.Map;
import org.achartengine.tools.ModUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class LiftAccountManager extends PicoocActivity
  implements View.OnClickListener, thirdPartLoginListener, AnimUtils.selectHeitListener
{
  public static final int requstCode = 1;
  public static final int requstCodePhone = 2;
  String DESCRIPTOR = "com.umeng.share";
  private ImageView EmailRight;
  private String JDNickName = "";
  private TextView JDText;
  private MyApplication app;
  private String baiduID = "";
  private TextView baiduright;
  private TextView email;
  private TextView emailName;
  private int flag = 0;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(LiftAccountManager.this, "绑定失败！");
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(LiftAccountManager.this, "绑定失败！");
      switch (LiftAccountManager.this.flag)
      {
      case 4:
      case 7:
      default:
        return;
      case 5:
        LiftAccountManager.this.thirdPart.startBaiduLogout(FrontiaAuthorization.MediaType.BAIDU, LiftAccountManager.this);
        return;
      case 2:
        LiftAccountManager.this.thirdPart.delete(SHARE_MEDIA.SINA, LiftAccountManager.this);
        return;
      case 3:
        LiftAccountManager.this.thirdPart.delete(SHARE_MEDIA.QZONE, LiftAccountManager.this);
        return;
      case 6:
      }
      LiftAccountManager.this.thirdPart.outLeYu();
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      if (localResponseEntity.getMethod().equals("updateUserMessage"))
        PicoocToast.showToast(LiftAccountManager.this, "绑定成功");
      switch (LiftAccountManager.this.flag)
      {
      case 4:
      default:
        return;
      case 5:
        LiftAccountManager.this.baiduright.setCompoundDrawablesWithIntrinsicBounds(2130838333, 0, 0, 0);
        OperationDB.updateUserThirdPartID(LiftAccountManager.this, "baidu_id", LiftAccountManager.this.app.getCurrentUser().getUser_id(), LiftAccountManager.this.baiduID);
        LiftAccountManager.this.app.getCurrentUser().setBaidu_id(LiftAccountManager.this.baiduID);
        return;
      case 2:
        LiftAccountManager.this.xinlangright.setCompoundDrawablesWithIntrinsicBounds(2130838333, 0, 0, 0);
        LiftAccountManager.this.getUserInfor(SHARE_MEDIA.SINA, 2);
        LiftAccountManager.this.app.getCurrentUser().setWeibo_id(LiftAccountManager.this.sinaID);
        OperationDB.updateUserThirdPartID(LiftAccountManager.this, "weibo_id", LiftAccountManager.this.app.getCurrentUser().getUser_id(), LiftAccountManager.this.sinaID);
        return;
      case 3:
        LiftAccountManager.this.kongjianright.setCompoundDrawablesWithIntrinsicBounds(2130838333, 0, 0, 0);
        LiftAccountManager.this.app.getCurrentUser().setQQ_id(LiftAccountManager.this.qqID);
        LiftAccountManager.this.getUserInfor(SHARE_MEDIA.QZONE, 3);
        OperationDB.updateUserThirdPartID(LiftAccountManager.this, "qq_id", LiftAccountManager.this.app.getCurrentUser().getUser_id(), LiftAccountManager.this.qqID);
        return;
      case 6:
        LiftAccountManager.this.leyuright.setCompoundDrawablesWithIntrinsicBounds(2130838333, 0, 0, 0);
        LiftAccountManager.this.app.getCurrentUser().setLy_id(LiftAccountManager.this.leyuID);
        OperationDB.updateUserThirdPartID(LiftAccountManager.this, "leyu_id", LiftAccountManager.this.app.getCurrentUser().getUser_id(), LiftAccountManager.this.leyuID);
        LiftAccountManager.this.leyuText.setText(LiftAccountManager.this.leyuNickName);
        return;
      case 7:
      }
      LiftAccountManager.this.jingdongright.setCompoundDrawablesWithIntrinsicBounds(2130838333, 0, 0, 0);
      LiftAccountManager.this.app.getCurrentUser().setJd_id(LiftAccountManager.this.jingdongID);
      OperationDB.updateUserThirdPartID(LiftAccountManager.this, "jd_id", LiftAccountManager.this.app.getCurrentUser().getUser_id(), LiftAccountManager.this.jingdongID);
      LiftAccountManager.this.JDText.setText(LiftAccountManager.this.JDNickName);
    }
  };
  private JsonHttpResponseHandler httpHandlerTwo = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(LiftAccountManager.this, "解绑失败");
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(LiftAccountManager.this, "解绑失败");
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      Log.i("http", "成功了:" + localResponseEntity.toString());
      if (localResponseEntity.getMethod().equals("updateUserMessage"))
        PicoocToast.showToast(LiftAccountManager.this, "解绑成功");
      switch (LiftAccountManager.this.flag)
      {
      case 4:
      default:
        return;
      case 5:
        LiftAccountManager.this.baiduright.setCompoundDrawablesWithIntrinsicBounds(2130838323, 0, 0, 0);
        LiftAccountManager.this.thirdPart.startBaiduLogout(FrontiaAuthorization.MediaType.BAIDU, LiftAccountManager.this);
        LiftAccountManager.this.app.getCurrentUser().setBaidu_id("");
        OperationDB.updateUserThirdPartID(LiftAccountManager.this, "baidu_id", LiftAccountManager.this.app.getCurrentUser().getUser_id(), LiftAccountManager.this.baiduID);
        return;
      case 2:
        LiftAccountManager.this.xinlangright.setCompoundDrawablesWithIntrinsicBounds(2130838323, 0, 0, 0);
        LiftAccountManager.this.app.getCurrentUser().setWeibo_id("");
        LiftAccountManager.this.thirdPart.delete(SHARE_MEDIA.SINA, LiftAccountManager.this);
        LiftAccountManager.this.xinlangText.setText("");
        SharedPreferenceUtils.saveThirdPartSinaName(LiftAccountManager.this, LiftAccountManager.this.app.getCurrentUser().getUser_id(), "");
        OperationDB.updateUserThirdPartID(LiftAccountManager.this, "weibo_id", LiftAccountManager.this.app.getCurrentUser().getUser_id(), "");
        return;
      case 3:
        LiftAccountManager.this.kongjianright.setCompoundDrawablesWithIntrinsicBounds(2130838323, 0, 0, 0);
        LiftAccountManager.this.app.getCurrentUser().setQQ_id("");
        LiftAccountManager.this.thirdPart.delete(SHARE_MEDIA.QZONE, LiftAccountManager.this);
        LiftAccountManager.this.qqText.setText("");
        SharedPreferenceUtils.saveThirdPartqqName(LiftAccountManager.this, LiftAccountManager.this.app.getCurrentUser().getUser_id(), "");
        OperationDB.updateUserThirdPartID(LiftAccountManager.this, "qq_id", LiftAccountManager.this.app.getCurrentUser().getUser_id(), "");
        return;
      case 6:
        LiftAccountManager.this.leyuright.setCompoundDrawablesWithIntrinsicBounds(2130838323, 0, 0, 0);
        LiftAccountManager.this.app.getCurrentUser().setLy_id("");
        LiftAccountManager.this.thirdPart.outLeYu();
        LiftAccountManager.this.leyuText.setText("");
        SharedPreferenceUtils.saveThirdPartLeYuName(LiftAccountManager.this, LiftAccountManager.this.app.getCurrentUser().getUser_id(), "");
        OperationDB.updateUserThirdPartID(LiftAccountManager.this, "leyu_id", LiftAccountManager.this.app.getCurrentUser().getUser_id(), "");
        return;
      case 7:
      }
      LiftAccountManager.this.jingdongright.setCompoundDrawablesWithIntrinsicBounds(2130838323, 0, 0, 0);
      LiftAccountManager.this.app.getCurrentUser().setJd_id("");
      LiftAccountManager.this.JDText.setText("");
      SharedPreferenceUtils.saveThirdPartJDName(LiftAccountManager.this, LiftAccountManager.this.app.getCurrentUser().getUser_id(), "");
      OperationDB.updateUserThirdPartID(LiftAccountManager.this, "jd_id", LiftAccountManager.this.app.getCurrentUser().getUser_id(), "");
    }
  };
  Intent i;
  private String jingdongID = "";
  private TextView jingdongright;
  private TextView kongjianright;
  private String leyuID = "";
  private String leyuNickName = "";
  private TextView leyuText;
  private TextView leyuright;
  private LinearLayout linearLayout_bg;
  private AnimUtils mAnim;
  private TextView phone;
  private TextView phoneName;
  private ImageView phoneRight;
  private String qqID = "";
  private TextView qqText;
  private LinearLayout registerLinear;
  private LinearLayout registerMail;
  private String sinaID = "";
  private ThemeConstant themeConstant;
  ThirdPartLogin thirdPart;
  private TextView tv_not_regist;
  private TextView xinlangText;
  private TextView xinlangright;

  public void alarmCount(String paramString1, String paramString2)
  {
  }

  public void finish()
  {
    super.finish();
  }

  public void getUserInfor(SHARE_MEDIA paramSHARE_MEDIA, final int paramInt)
  {
    UMServiceFactory.getUMSocialService(this.DESCRIPTOR, RequestType.SOCIAL).getPlatformInfo(this, paramSHARE_MEDIA, new SocializeListeners.UMDataListener()
    {
      public void onComplete(int paramAnonymousInt, Map<String, Object> paramAnonymousMap)
      {
        Log.i("picooc", "arg1===" + paramAnonymousMap);
        if (paramAnonymousMap == null)
          return;
        switch (paramInt)
        {
        default:
          return;
        case 2:
          LiftAccountManager.this.xinlangText.setText(paramAnonymousMap.get("screen_name"));
          SharedPreferenceUtils.saveThirdPartSinaName(LiftAccountManager.this, LiftAccountManager.this.app.getCurrentUser().getUser_id(), paramAnonymousMap.get("screen_name"));
          return;
        case 3:
        }
        LiftAccountManager.this.qqText.setText(paramAnonymousMap.get("screen_name"));
        SharedPreferenceUtils.saveThirdPartqqName(LiftAccountManager.this, LiftAccountManager.this.app.getCurrentUser().getUser_id(), paramAnonymousMap.get("screen_name"));
      }

      public void onStart()
      {
      }
    });
  }

  public void invit()
  {
    this.mAnim = new AnimUtils(this);
    this.mAnim.setoselectHeitListener(this);
    this.linearLayout_bg = ((LinearLayout)findViewById(2131099739));
    this.themeConstant = new ThemeConstant(this);
    this.themeConstant.setTheme(this.linearLayout_bg);
    this.jingdongright = ((TextView)findViewById(2131100326));
    this.leyuright = ((TextView)findViewById(2131100329));
    this.JDText = ((TextView)findViewById(2131100325));
    this.JDText.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.leyuText = ((TextView)findViewById(2131100328));
    this.leyuText.setTypeface(TypefaceUtils.getTypeface(this, null));
    ImageView localImageView1 = (ImageView)findViewById(2131099651);
    ImageView localImageView2 = (ImageView)findViewById(2131099650);
    localImageView1.setVisibility(8);
    localImageView1.setOnClickListener(this);
    localImageView2.setImageResource(2130838406);
    localImageView2.setOnClickListener(this);
    ((TextView)findViewById(2131099699)).setText("账号管理");
    this.registerLinear = ((LinearLayout)findViewById(2131100312));
    this.registerMail = ((LinearLayout)findViewById(2131100301));
    this.EmailRight = ((ImageView)findViewById(2131100304));
    this.phoneRight = ((ImageView)findViewById(2131100308));
    this.email = ((TextView)findViewById(2131100303));
    this.phone = ((TextView)findViewById(2131100307));
    this.emailName = ((TextView)findViewById(2131100305));
    this.emailName.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.phoneName = ((TextView)findViewById(2131100309));
    this.phoneName.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.tv_not_regist = ((TextView)findViewById(2131100314));
    this.tv_not_regist.setTypeface(TypefaceUtils.getTypeface(this, null));
    TextView localTextView = (TextView)findViewById(2131100310);
    if (this.app.getCurrentUser().isHas_password())
    {
      localTextView.setText("修改密码");
      ((TextView)findViewById(2131100300)).setTypeface(TypefaceUtils.getTypeface(this, null));
      ((TextView)findViewById(2131100321)).setTypeface(TypefaceUtils.getTypeface(this, null));
      this.thirdPart = new ThirdPartLogin(this);
      this.thirdPart.setthirdPartLoginListener(this);
      this.baiduright = ((TextView)findViewById(2131100315));
      this.xinlangright = ((TextView)findViewById(2131100318));
      this.kongjianright = ((TextView)findViewById(2131100323));
      this.xinlangText = ((TextView)findViewById(2131100317));
      this.xinlangText.setTypeface(TypefaceUtils.getTypeface(this, null));
      this.qqText = ((TextView)findViewById(2131100322));
      this.qqText.setTypeface(TypefaceUtils.getTypeface(this, null));
      this.app.getCurrentUser().getBaidu_id();
      this.registerLinear.setOnClickListener(this);
      invitTitle();
      if ((this.app.getCurrentUser().getBaidu_id() == null) || (this.app.getCurrentUser().getBaidu_id().equals("")) || (this.app.getCurrentUser().getBaidu_id().equals("0")))
        break label1208;
      this.baiduright.setCompoundDrawablesWithIntrinsicBounds(2130838333, 0, 0, 0);
      this.baiduright.setText("解绑");
      label623: if ((this.app.getCurrentUser().getWeibo_id() == null) || (this.app.getCurrentUser().getWeibo_id().equals("")) || (this.app.getCurrentUser().getWeibo_id().equals("0")))
        break label1234;
      this.xinlangright.setCompoundDrawablesWithIntrinsicBounds(2130838333, 0, 0, 0);
      this.xinlangright.setText("解绑");
      getUserInfor(SHARE_MEDIA.SINA, 2);
      label704: if ((this.app.getCurrentUser().getQQ_id() == null) || (this.app.getCurrentUser().getQQ_id().equals("")) || (this.app.getCurrentUser().getQQ_id().equals("0")))
        break label1260;
      this.kongjianright.setCompoundDrawablesWithIntrinsicBounds(2130838333, 0, 0, 0);
      this.kongjianright.setText("解绑");
      if (!this.app.isComeFromQQ())
        getUserInfor(SHARE_MEDIA.QZONE, 3);
      label795: if ((this.app.getCurrentUser().getJd_id() == null) || (this.app.getCurrentUser().getJd_id().equals("")) || (this.app.getCurrentUser().getJd_id().equals("0")))
        break label1286;
      this.jingdongright.setCompoundDrawablesWithIntrinsicBounds(2130838333, 0, 0, 0);
      this.jingdongright.setText("解绑");
      label868: if ((this.app.getCurrentUser().getLy_id() == null) || (this.app.getCurrentUser().getLy_id().equals("")) || (this.app.getCurrentUser().getLy_id().equals("0")))
        break label1312;
      this.leyuright.setCompoundDrawablesWithIntrinsicBounds(2130838333, 0, 0, 0);
      this.leyuright.setText("解绑");
      label941: if (SharedPreferenceUtils.getThirdPartqqNmae(this, this.app.getCurrentUser().getUser_id()) == null)
        break label1338;
      this.qqText.setText(SharedPreferenceUtils.getThirdPartqqNmae(this, this.app.getCurrentUser().getUser_id()));
      label1005: if (SharedPreferenceUtils.getThirdPartSinaNmae(this, this.app.getCurrentUser().getUser_id()) == null)
        break label1350;
      this.xinlangText.setText(SharedPreferenceUtils.getThirdPartSinaNmae(this, this.app.getCurrentUser().getUser_id()));
      label1069: if (SharedPreferenceUtils.getThirdPartJDNmae(this, this.app.getCurrentUser().getUser_id()) == null)
        break label1362;
      this.JDText.setText(SharedPreferenceUtils.getThirdPartJDNmae(this, this.app.getCurrentUser().getUser_id()));
    }
    while (true)
    {
      if (SharedPreferenceUtils.getThirdPartLeYuNmae(this, this.app.getCurrentUser().getUser_id()) == null)
        break label1374;
      this.leyuText.setText(SharedPreferenceUtils.getThirdPartLeYuNmae(this, this.app.getCurrentUser().getUser_id()));
      return;
      localTextView.setText("设置密码");
      break;
      label1208: this.baiduright.setText("绑定");
      this.baiduright.setCompoundDrawablesWithIntrinsicBounds(2130838323, 0, 0, 0);
      break label623;
      label1234: this.xinlangright.setText("绑定");
      this.xinlangright.setCompoundDrawablesWithIntrinsicBounds(2130838323, 0, 0, 0);
      break label704;
      label1260: this.kongjianright.setText("绑定");
      this.kongjianright.setCompoundDrawablesWithIntrinsicBounds(2130838323, 0, 0, 0);
      break label795;
      label1286: this.jingdongright.setText("绑定");
      this.jingdongright.setCompoundDrawablesWithIntrinsicBounds(2130838323, 0, 0, 0);
      break label868;
      label1312: this.leyuright.setText("绑定");
      this.leyuright.setCompoundDrawablesWithIntrinsicBounds(2130838323, 0, 0, 0);
      break label941;
      label1338: this.qqText.setText("");
      break label1005;
      label1350: this.xinlangText.setText("");
      break label1069;
      label1362: this.JDText.setText("");
    }
    label1374: this.leyuText.setText("");
  }

  public void invitTitle()
  {
    if ((!ModUtils.isHaveEmail(this.app.getCurrentUser()).booleanValue()) && (!ModUtils.isHavePhone(this.app.getCurrentUser()).booleanValue()))
    {
      this.registerMail.setVisibility(8);
      this.registerLinear.setVisibility(0);
    }
    do
    {
      return;
      this.registerMail.setVisibility(0);
      this.registerLinear.setVisibility(8);
      if ((ModUtils.isHaveEmail(this.app.getCurrentUser()).booleanValue()) && (ModUtils.isHavePhone(this.app.getCurrentUser()).booleanValue()))
      {
        this.email.setText("邮箱");
        this.EmailRight.setVisibility(4);
        this.emailName.setText(this.app.getCurrentUser().getEmail());
        this.phone.setText("手机");
        this.phoneRight.setVisibility(4);
        this.phoneName.setText(this.app.getCurrentUser().getPhone_no());
        return;
      }
      if ((ModUtils.isHaveEmail(this.app.getCurrentUser()).booleanValue()) && (!ModUtils.isHavePhone(this.app.getCurrentUser()).booleanValue()))
      {
        this.email.setText("邮箱");
        this.EmailRight.setVisibility(4);
        this.emailName.setText(this.app.getCurrentUser().getEmail());
        this.phone.setText("绑定手机号");
        this.phoneRight.setVisibility(0);
        this.phoneName.setText("");
        return;
      }
    }
    while ((ModUtils.isHaveEmail(this.app.getCurrentUser()).booleanValue()) || (!ModUtils.isHavePhone(this.app.getCurrentUser()).booleanValue()));
    this.email.setText("绑定邮箱");
    this.EmailRight.setVisibility(0);
    this.emailName.setText("");
    this.phone.setText("手机");
    this.phoneRight.setVisibility(4);
    this.phoneName.setText(this.app.getCurrentUser().getPhone_no());
  }

  public void jieBang(final int paramInt)
  {
    final PicoocAlertDialog localPicoocAlertDialog = new PicoocAlertDialog("您确定要解绑吗？ 解绑后，您将无法以该账号登陆查看历史测量数据。", "取消", "确定", this);
    localPicoocAlertDialog.setOnTouchOutside(Boolean.valueOf(false));
    localPicoocAlertDialog.showAlerDialogTwo(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localPicoocAlertDialog.dismissAlertDialog();
        PicoocLoading.showLoadingDialog(LiftAccountManager.this);
        RequestEntity localRequestEntity = new RequestEntity("updateUserMessage", "1.0");
        localRequestEntity.addParam("thirdPartId", "");
        localRequestEntity.addParam("type", Integer.valueOf(paramInt));
        localRequestEntity.addParam("userID", Long.valueOf(LiftAccountManager.this.app.getCurrentUser().getUser_id()));
        HttpUtils.getJson(LiftAccountManager.this, localRequestEntity, LiftAccountManager.this.httpHandlerTwo);
      }
    }
    , new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localPicoocAlertDialog.dismissAlertDialog();
      }
    });
  }

  public void notJieBang()
  {
    final PicoocAlertDialog localPicoocAlertDialog = new PicoocAlertDialog("您不能继续解绑账号啦，否则将无法登录查看您的历史数据。3秒钟注册PICOOC账号，获得更多PICOOC特权吧!", "暂不注册", "立刻注册", this);
    localPicoocAlertDialog.showAlerDialogTwo(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localPicoocAlertDialog.dismissAlertDialog();
        LiftAccountManager.this.mAnim.getPopupWindowPhoto(2);
      }
    }
    , new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localPicoocAlertDialog.dismissAlertDialog();
      }
    });
    localPicoocAlertDialog.setOnTouchOutside(Boolean.valueOf(false));
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == paramInt2)
      if (paramIntent != null)
      {
        String str7 = paramIntent.getStringExtra("email");
        this.app.getCurrentUser().setEmail(str7);
        invitTitle();
        ((TextView)findViewById(2131100310)).setText("修改密码");
      }
    do
    {
      return;
      if ((paramInt1 == 1231) && (paramInt2 == 1321))
      {
        ((TextView)findViewById(2131100310)).setText("修改密码");
        return;
      }
      if ((paramInt1 == 1001) && (paramIntent != null))
      {
        String str4 = paramIntent.getStringExtra("result");
        Log.i("qianmo2", "resultJD==" + str4);
        String str5 = "";
        String str6 = "";
        try
        {
          JSONObject localJSONObject2 = new JSONObject(str4);
          str5 = localJSONObject2.getString("uid");
          str6 = localJSONObject2.getString("access_token");
          this.JDNickName = localJSONObject2.getString("user_nick");
          thirdPartLoginSuccess(str5, str6, 7);
          Log.i("picooc", "result===" + str4);
          return;
        }
        catch (JSONException localJSONException2)
        {
          while (true)
            localJSONException2.printStackTrace();
        }
      }
    }
    while ((paramInt2 != -1) || (paramIntent == null));
    String str1 = paramIntent.getExtras().get("result_info").toString();
    Log.i("picooc", "leyu===" + str1.toString());
    String str2 = "";
    String str3 = "";
    try
    {
      JSONObject localJSONObject1 = new JSONObject(str1);
      str2 = localJSONObject1.getString("uid");
      str3 = localJSONObject1.getString("access_token");
      this.leyuNickName = localJSONObject1.getString("uname");
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
    switch (paramView.getId())
    {
    default:
    case 2131100284:
    case 2131100287:
    case 2131100316:
    case 2131100319:
    case 2131100324:
    case 2131100327:
    case 2131099650:
    case 2131100312:
    case 2131100313:
    case 2131100302:
    case 2131100306:
      do
      {
        do
        {
          return;
          this.i = new Intent(this, LiftChangePwd.class);
          if (this.app.getCurrentUser().isHas_password())
          {
            this.i.putExtra("flag", 1);
            startActivity(this.i);
            return;
          }
          this.i.putExtra("flag", 2);
          startActivityForResult(this.i, 1231);
          return;
          this.flag = 5;
          if ((this.app.getCurrentUser().getBaidu_id() != null) && (!this.app.getCurrentUser().getBaidu_id().equals("")) && (!this.app.getCurrentUser().getBaidu_id().equals("0")))
          {
            if (ModUtils.isNext(this.app.getCurrentUser()).booleanValue())
            {
              jieBang(5);
              return;
            }
            notJieBang();
            return;
          }
          this.thirdPart.startBaidu(this);
          return;
          this.flag = 2;
          if ((this.app.getCurrentUser().getWeibo_id() != null) && (!this.app.getCurrentUser().getWeibo_id().equals("")) && (!this.app.getCurrentUser().getWeibo_id().equals("0")))
          {
            if (ModUtils.isNext(this.app.getCurrentUser()).booleanValue())
            {
              jieBang(2);
              return;
            }
            notJieBang();
            return;
          }
          this.thirdPart.sina(this);
          return;
          this.flag = 3;
          if ((this.app.getCurrentUser().getQQ_id() != null) && (!this.app.getCurrentUser().getQQ_id().equals("")) && (!this.app.getCurrentUser().getQQ_id().equals("0")))
          {
            if (ModUtils.isNext(this.app.getCurrentUser()).booleanValue())
            {
              jieBang(3);
              return;
            }
            notJieBang();
            return;
          }
          this.thirdPart.startQQZone(this);
          return;
          this.flag = 7;
          if ((this.app.getCurrentUser().getJd_id() != null) && (!this.app.getCurrentUser().getJd_id().equals("")) && (!this.app.getCurrentUser().getJd_id().equals("0")))
          {
            if (ModUtils.isNext(this.app.getCurrentUser()).booleanValue())
            {
              jieBang(7);
              return;
            }
            notJieBang();
            return;
          }
          this.thirdPart.loginJD(this);
          return;
          this.flag = 6;
          if ((this.app.getCurrentUser().getLy_id() != null) && (!this.app.getCurrentUser().getLy_id().equals("")) && (!this.app.getCurrentUser().getLy_id().equals("0")))
          {
            if (ModUtils.isNext(this.app.getCurrentUser()).booleanValue())
            {
              jieBang(6);
              return;
            }
            notJieBang();
            return;
          }
          this.thirdPart.startLeyu(this);
          return;
          finish();
          return;
          this.mAnim.getPopupWindowPhoto(2);
          return;
          this.mAnim.getPopupWindowPhoto(2);
          return;
        }
        while (!this.EmailRight.isShown());
        this.i = new Intent(this, EmailRegister.class);
        this.i.putExtra("from", 1);
        startActivityForResult(this.i, 1);
        return;
      }
      while (!this.phoneRight.isShown());
      this.i = new Intent(this, BindingPhoneAct.class);
      this.i.putExtra("key", ModUtils.ACCOUNT_MANAGER_IN);
      startActivity(this.i);
      return;
    case 2131100311:
    }
    startActivity(new Intent(this, SonyTVAct.class));
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903175);
    this.app = ((MyApplication)getApplicationContext());
    invit();
  }

  public void selectFromPhone()
  {
    Intent localIntent = new Intent(this, EmailRegister.class);
    localIntent.putExtra("from", 2);
    startActivityForResult(localIntent, 1);
  }

  public void selectHeit(int paramInt, String paramString1, String paramString2)
  {
  }

  public void selectbirthDay(String paramString)
  {
  }

  public void takePoto()
  {
    Intent localIntent = new Intent(this, RegistPhoneAct.class);
    localIntent.putExtra("from", 301);
    startActivity(localIntent);
  }

  public void thirdPartLoginSuccess(String paramString1, String paramString2, int paramInt)
  {
    switch (paramInt)
    {
    case 4:
    default:
    case 2:
    case 3:
    case 5:
    case 6:
    case 7:
    }
    while (true)
    {
      RequestEntity localRequestEntity = new RequestEntity("updateUserMessage", "1.0");
      localRequestEntity.addParam("thirdPartId", paramString1);
      localRequestEntity.addParam("type", Integer.valueOf(paramInt));
      localRequestEntity.addParam("userID", Long.valueOf(this.app.getCurrentUser().getUser_id()));
      localRequestEntity.addParam("access_token", paramString2);
      HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
      return;
      this.sinaID = paramString1;
      continue;
      this.qqID = paramString1;
      continue;
      this.baiduID = paramString1;
      continue;
      this.leyuID = paramString1;
      continue;
      this.jingdongID = paramString1;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LiftAccountManager
 * JD-Core Version:    0.6.2
 */
