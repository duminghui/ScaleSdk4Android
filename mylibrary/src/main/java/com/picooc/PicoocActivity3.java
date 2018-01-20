package com.picooc;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.picooc.db.OperationDB;
import com.picooc.fragment.LatinContentFragment;
import com.picooc.fragment.LatinContentFragment.MyPageChangeListener;
import com.picooc.fragment.LiftFragment;
import com.picooc.fragment.RightFragment2;
import com.picooc.internet.AsyncMessageUtils;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.lock.PwdCheckActivity;
import com.picooc.slidingMenu.IChangeFragment;
import com.picooc.slidingMenu.SlidingMenu;
import com.picooc.utils.PicoocParser;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.widget.DownloadApkThread;
import com.picooc.widget.loading.PicoocToast;
import com.picooc.widget.picoocProgress.PicoocAlertDialog;
import com.picooc.widget.picoocProgress.PicoocAlertDialog.updateListener;
import com.umeng.analytics.MobclickAgent;
import java.io.File;

import org.achartengine.tools.ModUtils;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class PicoocActivity3 extends FragmentActivity
  implements IChangeFragment
{
  public static final int PICOOC_ACTIVITY = 2;
  public static final int PICOOC_BACKMENU = 0;
  public static final int PICOOC_CHANGEGOAL = 7;
  public static final int PICOOC_FAMILY_ACCOUNT = 77;
  public static final int PICOOC_FRIEND = 5;
  public static final int PICOOC_REPORTONE = 3;
  public static final int PICOOC_REPORTTWO = 4;
  public static final int PICOOC_SENDLATIN = 8;
  public static final int PICOOC_SETTING = 6;
  public static final int PICOOC_TODAY = 1;
  public static final String YOUMENG_ChannelId = "52d59f6c56240bb30b05b140";
  public static final String YOUMENG_KEY = "52d59f6c56240bb30b05b140";
  public static Context context = null;
  private MyApplication app;
  private boolean bluetoothDefaultState = false;
  private int current_fragmeng = 1;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      if (new ResponseEntity(paramAnonymousJSONObject).getMethod().equals("updateUserMessage"))
        PicoocToast.showToast(PicoocActivity3.this, "绑定失败");
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      String str1 = localResponseEntity.getMethod();
      if (str1.equals("checkNewVersion"));
      label184: 
      do
      {
        try
        {
          String str2 = localResponseEntity.getResp().getString("version");
          Log.i("qianmo2", "up version==" + str2 + "----bendi verson==" + ModUtils.getVersionCode(PicoocActivity3.this));
          if (Float.parseFloat(str2) > ModUtils.getVersionCode(PicoocActivity3.this))
            if (!localResponseEntity.getResp().has("mustUpdate"))
              break label184;
          for (boolean bool = localResponseEntity.getResp().getBoolean("mustUpdate"); ; bool = false)
          {
            if (bool)
            {
              final PicoocAlertDialog localPicoocAlertDialog = new PicoocAlertDialog("有新版本啦！更多新功能，就是你要的！去更新一下吧？", "先不更新", "这就更新", PicoocActivity3.this);
              localPicoocAlertDialog.showAlerDialog();
              localPicoocAlertDialog.setoselectHeitListener(new updateListener()
              {
                public void trueUpdate()
                {
                  localPicoocAlertDialog.setText("已下载   0 %");
                  new DownloadApkThread(PicoocActivity3.this, new Handler()
                  {
                    public void handleMessage(Message paramAnonymous3Message)
                    {
                      switch (paramAnonymous3Message.what)
                      {
                      default:
                        return;
                      case 1:
                        int i = ((Integer)paramAnonymous3Message.obj).intValue();
                        this.val$alertDialog.setText("已下载   " + i + " %");
                        return;
                      case 2:
                        File localFile = (File)paramAnonymous3Message.obj;
                        Intent localIntent = new Intent();
                        localIntent.addFlags(268435456);
                        localIntent.setAction("android.intent.action.VIEW");
                        localIntent.setDataAndType(Uri.fromFile(localFile), "application/vnd.android.package-archive");
                        PicoocActivity3.this.startActivity(localIntent);
                        this.val$alertDialog.dismissAlertDialog();
                        return;
                      case 3:
                      }
                      this.val$alertDialog.dismissAlertDialog();
                      PicoocToast.showToast(PicoocActivity3.this, "下载安装包失败！");
                    }
                  }
                  , "http://www.picooc.com/app/picooc.apk", "PICOOC.apk").start();
                }
              });
            }
            return;
          }
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          return;
        }
        if (str1.equals("getInvitationInformation"))
        {
          PicoocParser.parserInvitInfo(localResponseEntity.getResp(), PicoocActivity3.this);
          Intent localIntent = new Intent();
          localIntent.setAction("com.picooc.receive.push.refresh.ui");
          PicoocActivity3.context.sendBroadcast(localIntent);
          return;
        }
      }
      while (!str1.equals("updateUserMessage"));
      PicoocActivity3.this.app.getCurrentUser().setQQ_id(PicoocActivity3.this.qq_open_id);
      OperationDB.updateUserThirdPartID(PicoocActivity3.this, "qq_id", PicoocActivity3.this.app.getCurrentUser().getUser_id(), PicoocActivity3.this.qq_open_id);
      SharedPreferenceUtils.saveThirdPartqqName(PicoocActivity3.this, PicoocActivity3.this.app.getCurrentUser().getUser_id(), SharedPreferenceUtils.getThirdPartqqNmae(PicoocActivity3.this, PicoocActivity3.this.qq_open_id));
    }
  };
  Boolean isExit = Boolean.valueOf(false);
  private boolean isExitApp = false;
  private LiftFragment leftFragment;
  private BluetoothAdapter mBtAdapter;
  private FrameLayout mContainer;
  private FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
  {
    public int getCount()
    {
      return 5;
    }

    public Fragment getItem(int paramAnonymousInt)
    {
      switch (paramAnonymousInt)
      {
      case 2:
      case 3:
      case 5:
      default:
        return new LatinContentFragment();
      case 1:
        return new LatinContentFragment();
      case 4:
        return new LiftReportTwo();
      case 6:
        return new LiftSetting();
      case 7:
        return new ChangeGoalWeightAct();
      case 8:
      }
      return new PicoocSendWebViewAct();
    }
  };
  HomeKeyEventBroadCastReceiver mRecevier;
  private SlidingMenu mSlidingMenu;
  public boolean onResume = false;
  private String qq_open_id = "";
  private RightFragment2 rightFragment;

  private void init()
  {
    this.mSlidingMenu = ((SlidingMenu)findViewById(2131099938));
    this.mSlidingMenu.setLeftView(getLayoutInflater().inflate(2130903192, null));
    this.mSlidingMenu.setRightView(getLayoutInflater().inflate(2130903123, null));
    this.mContainer = ((FrameLayout)getLayoutInflater().inflate(2130903210, null));
    this.mSlidingMenu.setCenterView(this.mContainer);
    FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
    this.leftFragment = new LiftFragment();
    this.leftFragment.setChangeFragmentListener(this);
    localFragmentTransaction.replace(2131099941, this.leftFragment);
    this.rightFragment = new RightFragment2();
    localFragmentTransaction.replace(2131100064, this.rightFragment);
    Fragment localFragment = (Fragment)this.mFragmentPagerAdapter.instantiateItem(this.mContainer, 1);
    this.mFragmentPagerAdapter.setPrimaryItem(this.mContainer, 1, localFragment);
    this.mFragmentPagerAdapter.finishUpdate(this.mContainer);
    localFragmentTransaction.commit();
  }

  private void initListener(final LatinContentFragment paramLatinContentFragment)
  {
    paramLatinContentFragment.setMyPageChangeListener(new MyPageChangeListener()
    {
      public void onPageSelected(int paramAnonymousInt)
      {
        if (paramLatinContentFragment.isFirst())
        {
          PicoocActivity3.this.mSlidingMenu.setCanSliding(true, false);
          return;
        }
        if (paramLatinContentFragment.isEnd())
        {
          PicoocActivity3.this.mSlidingMenu.setCanSliding(false, true);
          return;
        }
        PicoocActivity3.this.mSlidingMenu.setCanSliding(false, false);
      }
    });
  }

  public void changeFragment(int paramInt)
  {
    Object localObject = null;
    switch (paramInt)
    {
    case 0:
    case 2:
    case 3:
    case 5:
    default:
    case 1:
    case 4:
    case 6:
    case 7:
    case 8:
    }
    while (true)
    {
      this.mFragmentPagerAdapter.setPrimaryItem(this.mContainer, this.current_fragmeng, localObject);
      this.mFragmentPagerAdapter.finishUpdate(this.mContainer);
      this.mSlidingMenu.closeLeftView();
      Log.i("error", "changeFragment,左=" + this.mSlidingMenu.isCanSlideLeft() + "  右=" + this.mSlidingMenu.isCanSlideRight());
      return;
      this.current_fragmeng = 1;
      localObject = (Fragment)this.mFragmentPagerAdapter.instantiateItem(this.mContainer, 1);
      if (((LatinContentFragment)localObject).isFirst())
      {
        this.mSlidingMenu.setCanSliding(true, false);
      }
      else if (((LatinContentFragment)localObject).isEnd())
      {
        this.mSlidingMenu.setCanSliding(false, true);
      }
      else
      {
        this.mSlidingMenu.setCanSliding(false, false);
        continue;
        this.current_fragmeng = 4;
        localObject = (Fragment)this.mFragmentPagerAdapter.instantiateItem(this.mContainer, 4);
        this.mSlidingMenu.setCanSliding(true, true);
        continue;
        this.current_fragmeng = 6;
        localObject = (Fragment)this.mFragmentPagerAdapter.instantiateItem(this.mContainer, 6);
        this.mSlidingMenu.setCanSliding(true, true);
        continue;
        this.current_fragmeng = 7;
        localObject = (Fragment)this.mFragmentPagerAdapter.instantiateItem(this.mContainer, 7);
        this.mSlidingMenu.setCanSliding(true, true);
        continue;
        this.current_fragmeng = 8;
        localObject = (Fragment)this.mFragmentPagerAdapter.instantiateItem(this.mContainer, 8);
        this.mSlidingMenu.setCanSliding(true, true);
      }
    }
  }

  public void destroyFragment()
  {
    FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
    for (int i = 0; ; i++)
    {
      if (i >= this.mFragmentPagerAdapter.getCount())
      {
        localFragmentTransaction.commit();
        return;
      }
      localFragmentTransaction.remove(this.mFragmentPagerAdapter.getItem(i));
    }
  }

  public int getCurrentFragment()
  {
    return this.current_fragmeng;
  }

  public SlidingMenu getSlidingMenu()
  {
    return this.mSlidingMenu;
  }

  public void onClickView(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131099650:
    case 2131099651:
    }
    while (true)
    {
      Log.i("error", "onClickView,左=" + this.mSlidingMenu.isCanSlideLeft() + "  右=" + this.mSlidingMenu.isCanSlideRight());
      return;
      toggleLeftMenu();
      continue;
      toggleRightMenu();
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903087);
    this.app = ((MyApplication)getApplicationContext());
    ((MyApplication)getApplication()).addActivity(this);
    init();
    initListener((LatinContentFragment)this.mFragmentPagerAdapter.instantiateItem(this.mContainer, 1));
    Log.i("qianmo2", "picoocActivity3----token===" + getIntent().getStringExtra("accesstoken") + "----openid==" + getIntent().getStringExtra("openid"));
    AsyncMessageUtils.checkNewVersion(this, this.httpHandler);
    if (Build.VERSION.SDK_INT >= 18)
    {
      this.mBtAdapter = ((BluetoothManager)getSystemService("bluetooth")).getAdapter();
      if ((this.mBtAdapter == null) || (this.mBtAdapter.isEnabled()))
        break label450;
      this.mBtAdapter.enable();
    }
    label450: for (this.bluetoothDefaultState = false; ; this.bluetoothDefaultState = true)
    {
      context = this;
      this.mRecevier = new HomeKeyEventBroadCastReceiver();
      IntentFilter localIntentFilter = new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS");
      registerReceiver(this.mRecevier, localIntentFilter);
      updateRecieveAndSend();
      if (SharedPreferenceUtils.isResetLatin(this))
        this.mSlidingMenu.startAnimations();
      MobclickAgent.onResume(this, "52d59f6c56240bb30b05b140", "52d59f6c56240bb30b05b140");
      this.qq_open_id = getIntent().getStringExtra("openid");
      if ((getIntent().getBooleanExtra("isQQfrom", false)) && (!getIntent().getBooleanExtra("isBangDing", false)))
      {
        if ((this.app.getCurrentUser().getQQ_id() == null) || (this.app.getCurrentUser().getQQ_id().equals("")) || (this.app.getCurrentUser().getQQ_id().equals("0")))
          break label481;
        if (this.app.getCurrentUser().getQQ_id().equals(this.qq_open_id))
          break label458;
        final PicoocAlertDialog localPicoocAlertDialog2 = new PicoocAlertDialog("Hi," + this.app.getCurentUserName(Long.valueOf(this.app.getCurrentUser().getUser_id())) + ":\n 您需要切换至手机QQ所登录的账号，才能在健康中心看到数据哦~", "取消", "切换", this);
        localPicoocAlertDialog2.showAlerDialogTwo(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            localPicoocAlertDialog2.dismissAlertDialog();
            RequestEntity localRequestEntity = new RequestEntity("updateUserMessage", "1.0");
            localRequestEntity.addParam("thirdPartId", PicoocActivity3.this.qq_open_id);
            localRequestEntity.addParam("type", Integer.valueOf(3));
            localRequestEntity.addParam("userID", Long.valueOf(PicoocActivity3.this.app.getCurrentUser().getUser_id()));
            localRequestEntity.addParam("access_token", PicoocActivity3.this.getIntent().getStringExtra("accesstoken"));
            HttpUtils.getJson(PicoocActivity3.this, localRequestEntity, PicoocActivity3.this.httpHandler);
          }
        }
        , new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            localPicoocAlertDialog2.dismissAlertDialog();
            PicoocActivity3.this.app.setComeFromQQ(false);
          }
        });
        localPicoocAlertDialog2.setOnTouchOutside(Boolean.valueOf(false));
      }
      return;
      this.mBtAdapter = BluetoothAdapter.getDefaultAdapter();
      break;
    }
    label458: new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        new PicoocAlertDialog("Hi," + PicoocActivity3.this.app.getCurentUserName(Long.valueOf(PicoocActivity3.this.app.getCurrentUser().getUser_id())) + ":\n您的信息将同步至QQ健康", "我知道了", "取消", PicoocActivity3.this).showComeQQDialog();
        RequestEntity localRequestEntity = new RequestEntity("updateUserMessage", "1.0");
        localRequestEntity.addParam("thirdPartId", PicoocActivity3.this.qq_open_id);
        localRequestEntity.addParam("type", Integer.valueOf(3));
        localRequestEntity.addParam("userID", Long.valueOf(PicoocActivity3.this.app.getCurrentUser().getUser_id()));
        localRequestEntity.addParam("access_token", PicoocActivity3.this.getIntent().getStringExtra("accesstoken"));
        HttpUtils.getJson(PicoocActivity3.this, localRequestEntity, PicoocActivity3.this.httpHandler);
      }
    }
    , 1000L);
    return;
    label481: final PicoocAlertDialog localPicoocAlertDialog1 = new PicoocAlertDialog("Hi," + this.app.getCurentUserName(Long.valueOf(this.app.getCurrentUser().getUser_id())) + ":\n绑定QQ(" + SharedPreferenceUtils.getThirdPartqqNmae(this, this.qq_open_id) + ")就能再健康中心看到数据啦~", "取消", "绑定", this);
    localPicoocAlertDialog1.showAlerDialogTwo(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localPicoocAlertDialog1.dismissAlertDialog();
        RequestEntity localRequestEntity = new RequestEntity("updateUserMessage", "1.0");
        localRequestEntity.addParam("thirdPartId", PicoocActivity3.this.qq_open_id);
        localRequestEntity.addParam("type", Integer.valueOf(3));
        localRequestEntity.addParam("userID", Long.valueOf(PicoocActivity3.this.app.getCurrentUser().getUser_id()));
        localRequestEntity.addParam("access_token", PicoocActivity3.this.getIntent().getStringExtra("accesstoken"));
        HttpUtils.getJson(PicoocActivity3.this, localRequestEntity, PicoocActivity3.this.httpHandler);
      }
    }
    , new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localPicoocAlertDialog1.dismissAlertDialog();
        PicoocActivity3.this.app.setComeFromQQ(false);
      }
    });
    localPicoocAlertDialog1.setOnTouchOutside(Boolean.valueOf(false));
  }

  protected void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.mRecevier);
    if ((this.mBtAdapter != null) && (this.mBtAdapter.isEnabled()) && (!this.bluetoothDefaultState))
      this.mBtAdapter.disable();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (SharedPreferenceUtils.isResetLatin(this))
      return true;
    if (paramInt == 4)
    {
      if (!this.isExit.booleanValue())
      {
        this.isExit = Boolean.valueOf(true);
        PicoocToast.showToast(this, "再按一次退出程序 ");
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            PicoocActivity3.this.isExit = Boolean.valueOf(false);
          }
        }
        , 2000L);
        return false;
      }
      ((MyApplication)getApplication()).exit();
      SharedPreferenceUtils.clearBaidu(this);
      Log.i("bluetoothW", "mPush.stop");
    }
    while (true)
    {
      return super.onKeyDown(paramInt, paramKeyEvent);
      if (3 == paramInt)
        Log.i("bluetoothW", "Home key down");
    }
  }

  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
  }

  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
  }

  protected void onResume()
  {
    this.isExitApp = false;
    super.onResume();
    MobclickAgent.onResume(this);
  }

  protected void onStart()
  {
    if (MyApplication.isShowLocalPassword)
    {
      Intent localIntent = new Intent();
      localIntent.setClass(this, PwdCheckActivity.class);
      startActivity(localIntent);
      overridePendingTransition(2130968580, 2130968577);
      MyApplication.isShowLocalPassword = false;
    }
    super.onStart();
  }

  public void toggleLeftMenu()
  {
    this.mSlidingMenu.showLeftView();
  }

  public void toggleRightMenu()
  {
    this.mSlidingMenu.showRightView();
  }

  public void updateRecieveAndSend()
  {
    if ((this.app.getCurrentUser() != null) && (this.app.getCurrentUser().getUser_id() > 0L))
    {
      RequestEntity localRequestEntity = new RequestEntity("getInvitationInformation", "5.2");
      localRequestEntity.addParam("user_id", Long.valueOf(this.app.getCurrentUser().getUser_id()));
      localRequestEntity.addParam("time", Long.valueOf(ModUtils.toTime(OperationDB.selectMastTime(this, this.app.getCurrentUser().getUser_id()))));
      HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
    }
  }

  class HomeKeyEventBroadCastReceiver extends BroadcastReceiver
  {
    static final String SYSTEM_HOME_KEY = "homekey";
    static final String SYSTEM_REASON = "reason";
    static final String SYSTEM_RECENT_APPS = "recentapps";

    HomeKeyEventBroadCastReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      String str;
      if (paramIntent.getAction().equals("android.intent.action.CLOSE_SYSTEM_DIALOGS"))
      {
        str = paramIntent.getStringExtra("reason");
        if (str != null)
        {
          if (!str.equals("homekey"))
            break label79;
          Log.i("picooc", "home键被点击");
          PicoocActivity3.this.isExitApp = true;
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              if (PicoocActivity3.this.isExitApp)
                ((MyApplication)PicoocActivity3.this.getApplication()).exit();
            }
          }
          , 300000L);
        }
      }
      while (true)
      {
        System.out.println("home pressed-----------------------");
        return;
        label79: str.equals("recentapps");
      }
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PicoocActivity3
 * JD-Core Version:    0.6.2
 */
