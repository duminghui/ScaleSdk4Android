package com.picooc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.android.pushservice.PushManager;
import com.baidu.frontia.api.FrontiaAuthorization.MediaType;
import com.picooc.constant.ThemeConstant;
import com.picooc.db.OperationDB;
import com.picooc.feedback.LeftFeedback;
import com.picooc.internet.AsyncMessageUtils;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.lock.LockActivity;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.ThirdPartLogin;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.AnimUtils.selectHeitListener;
import com.picooc.widget.DownloadApkThread;
import com.picooc.widget.anyncImageView.AsyncImageView;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import com.picooc.widget.picoocProgress.PicoocAlertDialog;
import com.picooc.widget.picoocProgress.PicoocAlertDialog.updateListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import java.io.File;
import org.achartengine.tools.ModUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class LiftSetting extends Fragment
  implements View.OnClickListener, updateListener, AnimUtils.selectHeitListener
{
  private TextView accountManagerTextView;
  PicoocAlertDialog alertDialog;
  private MyApplication app;
  private Button backOver;
  private TextView changeGoalWeight;
  private TextView changeInfoTextView;
  private MyHandler handler = null;
  private AsyncImageView head_image;
  private ImageView head_right;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(LiftSetting.this.getActivity(), paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(LiftSetting.this.getActivity(), localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      String str1 = localResponseEntity.getMethod();
      if (str1.equals("checkNewVersion"))
      {
        PicoocLoading.dismissDialog();
        try
        {
          if (Float.parseFloat(localResponseEntity.getResp().getString("version")) > ModUtils.getVersionCode(LiftSetting.this.getActivity()))
          {
            LiftSetting.this.alertDialog.showAlerDialog();
            return;
          }
          PicoocToast.showToast(LiftSetting.this.getActivity(), "您使用的已经是最新版本!");
          return;
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          return;
        }
      }
      if (str1.equals(HttpUtils.pLogOut))
      {
        PicoocLoading.dismissDialog();
        SharedPreferenceUtils.clearFile(LiftSetting.this.getActivity(), "user-Info");
        SharedPreferenceUtils.clearFile(LiftSetting.this.getActivity(), "NEW_WEIGHTING_RECORD");
        OperationDB.deleteAllRoles(LiftSetting.this.getActivity());
        LiftSetting.this.intent = new Intent(LiftSetting.this.getActivity(), FirstAct.class);
        String str2 = "";
        if (!LiftSetting.this.app.getCurrentUser().getPhone_no().equals(""))
          str2 = LiftSetting.this.app.getCurrentUser().getPhone_no();
        while (true)
        {
          SharedPreferenceUtils.putValue(LiftSetting.this.getActivity(), "userName", "userName", str2);
          LiftSetting.this.startActivity(LiftSetting.this.intent);
          LiftSetting.this.app.clearAllData();
          new ThirdPartLogin(LiftSetting.this.getActivity()).startBaiduLogout(FrontiaAuthorization.MediaType.BAIDU, LiftSetting.this.getActivity());
          ((PicoocActivity3)LiftSetting.this.getActivity()).destroyFragment();
          LiftSetting.this.getActivity().finish();
          SharedPreferenceUtils.clearBaidu(LiftSetting.this.getActivity());
          return;
          if (!LiftSetting.this.app.getCurrentUser().getEmail().equals(""))
            str2 = LiftSetting.this.app.getCurrentUser().getEmail();
        }
      }
    }
  };
  Intent intent;
  private LinearLayout item2;
  private LinearLayout item3;
  private View layout;
  private LinearLayout linearLayout_bg;
  private AnimUtils mAnim;
  private final BroadcastReceiver mReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      String str = paramAnonymousIntent.getAction();
      if ("com.picooc.latin.addfamilysuccess".equals(str))
      {
        LiftSetting.this.refreshHeaderImage();
        LiftSetting.this.refreshSettingItem();
        if (((PicoocActivity3)LiftSetting.this.getActivity()).getCurrentFragment() == 6)
          ((PicoocActivity3)LiftSetting.this.getActivity()).changeFragment(1);
      }
      do
      {
        do
        {
          return;
          if (!"com.picooc.latin.addvisitorsuccess".equals(str))
            break;
        }
        while (((PicoocActivity3)LiftSetting.this.getActivity()).getCurrentFragment() != 6);
        ((PicoocActivity3)LiftSetting.this.getActivity()).changeFragment(1);
        return;
        if ("com.picooc.latin.refresh.content".equals(str))
        {
          LiftSetting.this.refreshHeaderImage();
          LiftSetting.this.refreshSettingItem();
          return;
        }
      }
      while (!"com.picooc.local.family.upto.remote.family".equals(str));
      LiftSetting.this.accountManagerTextView.setText("查看家人PICOOC账号");
      LiftSetting.this.accountManagerTextView.setTag(Integer.valueOf(3));
    }
  };
  private TextView settingThemeTextView;
  private ThemeConstant themeConstant;

  private void logout()
  {
    PicoocLoading.showLoadingDialog(getActivity());
    PushManager.stopWork(getActivity());
    Bundle localBundle = SharedPreferenceUtils.getBaiduChanelId(getActivity());
    String str1 = localBundle.getString("baidu_user_id");
    String str2 = localBundle.getString("baiduChanel_id");
    RequestEntity localRequestEntity = new RequestEntity(HttpUtils.pLogOut, "5.2");
    localRequestEntity.addParam("myUserId", Long.valueOf(this.app.getCurrentUser().getUser_id()));
    localRequestEntity.addParam("baiduUserID", str1);
    localRequestEntity.addParam("baiduChannelID", str2);
    localRequestEntity.addParam("platform", "android");
    HttpUtils.getJson(getActivity(), localRequestEntity, this.httpHandler);
  }

  private void openFile(File paramFile)
  {
    Log.e("OpenFile", paramFile.getName());
    Intent localIntent = new Intent();
    localIntent.addFlags(268435456);
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
    startActivity(localIntent);
    this.alertDialog.dismissAlertDialog();
  }

  private void refreshHeaderImage()
  {
    if (!this.app.getCurrentRole().getHead_portrait_url().equals(""))
    {
      this.head_image.setUrl(this.app.getCurrentRole().getHead_portrait_url());
      this.backOver.setText("退出登陆(" + this.app.getCurrentRole().getName() + ")");
      return;
    }
    if (this.app.getCurrentRole().getSex() == 1)
      this.head_image.setDefaultImageResource(2130838457);
    while (true)
    {
      this.head_image.setUrl(null);
      break;
      this.head_image.setDefaultImageResource(2130838460);
    }
  }

  private void refreshLockText()
  {
    TextView localTextView = (TextView)this.layout.findViewById(2131100546);
    if (SharedPreferenceUtils.isOpenPsd(getActivity()));
    for (String str = "开启"; ; str = "关闭")
    {
      localTextView.setText(str);
      return;
    }
  }

  private void refreshSettingItem()
  {
    if (this.app.getCurrentRole().getRole_id() == this.app.getCurrentUser().getRole_id())
    {
      this.item3.setVisibility(0);
      this.changeInfoTextView.setText("修改资料");
      this.accountManagerTextView.setText("账号管理");
      this.item2.setVisibility(0);
      this.accountManagerTextView.setTag(Integer.valueOf(1));
      return;
    }
    this.item3.setVisibility(8);
    this.item2.setVisibility(8);
    this.changeInfoTextView.setText("修改家人资料");
    if (this.app.getCurrentRole().getFamily_type() == 0)
    {
      this.accountManagerTextView.setText("注册PICOOC账号");
      this.accountManagerTextView.setTag(Integer.valueOf(2));
      return;
    }
    this.accountManagerTextView.setText("查看家人PICOOC账号");
    this.accountManagerTextView.setTag(Integer.valueOf(3));
  }

  public void alarmCount(String paramString1, String paramString2)
  {
  }

  public void deleteBaidu()
  {
    if (this.app.getCurrentUser().isHas_password())
    {
      logout();
      return;
    }
    String str = this.app.getCurrentUser().getPhone_no();
    if ((str != null) && (!"".equals(str)) && (!"null".equals(str)))
    {
      Intent localIntent = new Intent(getActivity(), LiftChangePwd.class);
      localIntent.putExtra("flag", 3);
      startActivityForResult(localIntent, 1231);
      return;
    }
    logout();
  }

  public void invit()
  {
    ((ImageView)this.layout.findViewById(2131099650)).setImageResource(2130838421);
    this.linearLayout_bg = ((LinearLayout)this.layout.findViewById(2131099739));
    ((RelativeLayout)this.layout.findViewById(2131100279)).setOnClickListener(this);
    ((RelativeLayout)this.layout.findViewById(2131100543)).setOnClickListener(this);
    this.changeInfoTextView = ((TextView)this.layout.findViewById(2131100280));
    this.accountManagerTextView = ((TextView)this.layout.findViewById(2131100283));
    this.accountManagerTextView.setOnClickListener(this);
    this.accountManagerTextView.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
    this.settingThemeTextView = ((TextView)this.layout.findViewById(2131100285));
    this.settingThemeTextView.setOnClickListener(this);
    this.item3 = ((LinearLayout)this.layout.findViewById(2131100286));
    this.item2 = ((LinearLayout)this.layout.findViewById(2131100541));
    this.changeGoalWeight = ((TextView)this.layout.findViewById(2131100282));
    this.changeGoalWeight.setOnClickListener(this);
    ((TextView)this.layout.findViewById(2131100288)).setOnClickListener(this);
    ((TextView)this.layout.findViewById(2131100289)).setOnClickListener(this);
    TextView localTextView = (TextView)this.layout.findViewById(2131100290);
    localTextView.setOnClickListener(this);
    localTextView.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
    ((ImageView)this.layout.findViewById(2131099650)).setOnClickListener(this);
    ((TextView)this.layout.findViewById(2131099699)).setText("设定");
    this.backOver = ((Button)this.layout.findViewById(2131100291));
    this.backOver.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
    this.backOver.setOnClickListener(this);
    this.alertDialog = new PicoocAlertDialog("有新版本啦！更多新功能，就是你要的！去更新一下吧？", "先不更新", "这就更新", getActivity());
    this.alertDialog.setoselectHeitListener(this);
    this.head_right = ((ImageView)this.layout.findViewById(2131099651));
    this.head_right.setVisibility(8);
    this.head_image = ((AsyncImageView)this.layout.findViewById(2131099850));
    refreshHeaderImage();
    refreshSettingItem();
    ((RelativeLayout)this.layout.findViewById(2131100542)).setOnClickListener(this);
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 1023)
      refreshHeaderImage();
    while (paramInt2 != 1321)
      return;
    new Handler(getActivity().getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        LiftSetting.this.deleteBaidu();
      }
    }
    , 500L);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131100279:
    case 2131100282:
    case 2131100283:
      int i;
      do
      {
        return;
        if (this.app.getCurrentRole().getFamily_type() == 0)
          if (this.app.getCurrentUser().getRole_id() == this.app.getCurrentRole().getRole_id())
            this.intent = new Intent(getActivity(), LiftChangeIntforAct_new.class);
        while (true)
        {
          startActivityForResult(this.intent, 12);
          return;
          this.intent = new Intent(getActivity(), FamilyInfoEditAct.class);
          continue;
          if (this.app.getCurrentRole().getFamily_type() == 1)
            this.intent = new Intent(getActivity(), FamilyInfoEditAct.class);
        }
        if (this.app.getTodayBody().getWeight() <= 0.0F)
        {
          PicoocToast.showToast(getActivity(), "只有进行首次称重才能修改目标体重哦！");
          return;
        }
        this.intent = new Intent(getActivity(), SetGoalWeightAct.class);
        startActivity(this.intent);
        return;
        i = ((Integer)paramView.getTag()).intValue();
        if (i == 1)
        {
          this.intent = new Intent(getActivity(), LiftAccountManager.class);
          startActivity(this.intent);
          return;
        }
        if (i == 2)
        {
          this.mAnim.getPopupWindowPhoto(2);
          return;
        }
      }
      while (i != 3);
      this.intent = new Intent(getActivity(), SeeFamilyPicAccount.class);
      startActivity(this.intent);
      return;
    case 2131100285:
      this.intent = new Intent(getActivity(), SettingThemeAct.class);
      startActivity(this.intent);
      return;
    case 2131100543:
      startActivity(new Intent(getActivity(), LockActivity.class));
      return;
    case 2131100287:
      this.intent = new Intent(getActivity(), LiftChangePwd.class);
      startActivity(this.intent);
      return;
    case 2131100288:
      this.intent = new Intent(getActivity(), LeftFeedback.class);
      startActivity(this.intent);
      return;
    case 2131100289:
      PicoocLoading.showLoadingDialog(getActivity());
      AsyncMessageUtils.checkNewVersion(getActivity(), this.httpHandler);
      return;
    case 2131100290:
      this.intent = new Intent(getActivity(), LeftAboutPicooc.class);
      startActivity(this.intent);
      return;
    case 2131100291:
      this.alertDialog = new PicoocAlertDialog("您确定要退出主账号\n(" + this.app.getCurrentRole().getName() + ")?", "取消", "确定", getActivity());
      this.alertDialog.showAlerDialogTwo(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          LiftSetting.this.deleteBaidu();
          ThirdPartLogin localThirdPartLogin = new ThirdPartLogin(LiftSetting.this.getActivity());
          localThirdPartLogin.delete(SHARE_MEDIA.SINA, LiftSetting.this.getActivity());
          localThirdPartLogin.outLeYu();
          LiftSetting.this.alertDialog.dismissAlertDialog();
        }
      }
      , new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          LiftSetting.this.alertDialog.dismissAlertDialog();
        }
      });
      return;
    case 2131099650:
      ((PicoocActivity3)getActivity()).toggleLeftMenu();
      return;
    case 2131100542:
    }
    this.intent = new Intent(getActivity(), AlarmActivity.class);
    startActivity(this.intent);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.app = ((MyApplication)getActivity().getApplicationContext());
    this.themeConstant = new ThemeConstant(getActivity());
    this.mAnim = new AnimUtils(getActivity());
    this.mAnim.setoselectHeitListener(this);
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.picooc.latin.addfamilysuccess");
    localIntentFilter.addAction("com.picooc.latin.addvisitorsuccess");
    localIntentFilter.addAction("com.picooc.latin.refresh.content");
    localIntentFilter.addAction("com.picooc.local.family.upto.remote.family");
    getActivity().registerReceiver(this.mReceiver, localIntentFilter);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.layout = paramLayoutInflater.inflate(2130903202, paramViewGroup, false);
    invit();
    this.handler = new MyHandler(null);
    return this.layout;
  }

  public void onDestroy()
  {
    super.onDestroy();
    getActivity().unregisterReceiver(this.mReceiver);
  }

  public void onResume()
  {
    super.onResume();
    this.themeConstant.setTheme(this.linearLayout_bg);
    refreshLockText();
  }

  public void selectFromPhone()
  {
    Intent localIntent = new Intent(getActivity(), EmailRegister.class);
    localIntent.putExtra("from", 3);
    startActivityForResult(localIntent, 1);
  }

  public void selectHeit(int paramInt, String paramString1, String paramString2)
  {
  }

  public void selectbirthDay(String paramString)
  {
  }

  public void setMenuVisibility(boolean paramBoolean)
  {
    super.setMenuVisibility(paramBoolean);
    View localView;
    if (getView() != null)
    {
      localView = getView();
      if (!paramBoolean)
        break label29;
    }
    label29: for (int i = 0; ; i = 8)
    {
      localView.setVisibility(i);
      return;
    }
  }

  public void takePoto()
  {
    Intent localIntent = new Intent(getActivity(), RegistPhoneAct.class);
    localIntent.putExtra("from", 123);
    startActivity(localIntent);
  }

  public void trueUpdate()
  {
    Log.i("picooc", "收到回调了");
    this.alertDialog.setText("已下载   0 %");
    new DownloadApkThread(getActivity(), this.handler, "http://www.picooc.com/app/picooc.apk", "PICOOC.apk").start();
  }

  private class MyHandler extends Handler
  {
    private MyHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 1:
        int i = ((Integer)paramMessage.obj).intValue();
        LiftSetting.this.alertDialog.setText("已下载   " + i + " %");
        Log.i("picooc", "progress==" + i);
        return;
      case 2:
      }
      File localFile = (File)paramMessage.obj;
      LiftSetting.this.openFile(localFile);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LiftSetting
 * JD-Core Version:    0.6.2
 */
