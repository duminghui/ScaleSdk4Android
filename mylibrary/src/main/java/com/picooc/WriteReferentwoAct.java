package com.picooc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.db.OperationDB;
import com.picooc.domain.RoleBin;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.widget.ViewExpandAnimation;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import com.picooc.widget.picoocProgress.PicoocAlertDialog;
import org.json.JSONException;
import org.json.JSONObject;

public class WriteReferentwoAct extends PicoocActivity
{
  private MyApplication app;
  private RelativeLayout boottom;
  private RoleBin cacheRole;
  PicoocAlertDialog diog = null;
  private LinearLayout firstLinear;
  private int from = 0;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(WriteReferentwoAct.this, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(WriteReferentwoAct.this, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      if (localResponseEntity.getMethod().equals("addRole"));
      try
      {
        WriteReferentwoAct.this.cacheRole.setRole_id(localResponseEntity.getResp().getLong("roleID"));
        if (WriteReferentwoAct.this.from == 1)
        {
          l = OperationDB.addMainRole(WriteReferentwoAct.this, WriteReferentwoAct.this.cacheRole, 0L);
          if (l > 0L)
          {
            PicoocLoading.dismissDialog();
            SharedPreferenceUtils.putValue(WriteReferentwoAct.this, "user-Info", "role_id", Long.valueOf(l));
            if (WriteReferentwoAct.this.from != 1)
              break label207;
            WriteReferentwoAct.this.app.getCurrentUser().setRole_id(l);
            Intent localIntent = new Intent(WriteReferentwoAct.this, PicoocActivity3.class);
            WriteReferentwoAct.this.startActivity(localIntent);
            WriteReferentwoAct.this.finish();
          }
        }
      }
      catch (JSONException localJSONException)
      {
        label207: 
        do
          while (true)
          {
            localJSONException.printStackTrace();
            continue;
            long l = OperationDB.insertRoleDB(WriteReferentwoAct.this, WriteReferentwoAct.this.cacheRole);
          }
        while (WriteReferentwoAct.this.from != 2);
        WriteReferentwoAct.this.app.clearAllData();
        WriteReferentwoAct.this.app.setRole_id(WriteReferentwoAct.this.cacheRole.getRole_id());
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            Intent localIntent = new Intent();
            localIntent.setAction("com.picooc.latin.addfamilysuccess");
            WriteReferentwoAct.this.sendBroadcast(localIntent);
          }
        }
        , 500L);
        WriteReferentwoAct.this.finish();
        WriteReferentwoAct.this.overridePendingTransition(2130968576, 2130968581);
      }
    }
  };
  Boolean isExit = Boolean.valueOf(false);
  private boolean isExitApp = false;
  LinearLayout linearLift;
  LinearLayout linearLiftTop;
  LinearLayout linearLiftbottom;
  LinearLayout linearRitghtbottom;
  private float mDensity = 0.0F;
  private int mLcdWidth = 0;
  HomeKeyEventBroadCastReceiver mRecevier;
  private ImageView regboy;
  private LinearLayout relayout;
  private TextView tv_wazi;
  private RelativeLayout twotLinear;

  private void initWazi()
  {
    this.tv_wazi = ((TextView)findViewById(2131100742));
    String str = getString(2131361850);
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder("替 " + str);
    Drawable localDrawable = getResources().getDrawable(2130838676);
    int i = (int)this.tv_wazi.getTextSize();
    localDrawable.setBounds(0, 0, localDrawable.getIntrinsicWidth(), i);
    localSpannableStringBuilder.setSpan(new ImageSpan(localDrawable, 1), 0, 1, 34);
    this.tv_wazi.setText(localSpannableStringBuilder);
  }

  public void dismissOrShowOne(View paramView)
  {
    try
    {
      paramView.startAnimation(new ViewExpandAnimation(paramView));
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void dismissOtherView(View paramView1, View paramView2)
  {
    try
    {
      if (paramView1.isShown())
        dismissOrShowOne(paramView1);
      if (paramView2.isShown())
        dismissOrShowOne(paramView2);
      return;
    }
    finally
    {
    }
  }

  public void goNext()
  {
    if ((this.from == 1) || (this.from == 2))
    {
      PicoocLoading.showLoadingDialog(this);
      RequestEntity localRequestEntity = new RequestEntity("addRole", "3.0");
      localRequestEntity.addParam("userID", Long.valueOf(this.cacheRole.getUser_id()));
      localRequestEntity.addParam("name", this.cacheRole.getName());
      localRequestEntity.addParam("height", Float.valueOf(this.cacheRole.getHeight() / 100.0F));
      localRequestEntity.addParam("sex", Integer.valueOf(this.cacheRole.getSex()));
      localRequestEntity.addParam("birthday", this.cacheRole.getBirthday());
      localRequestEntity.addParam("head_portrait", this.cacheRole.getHead_portrait_url());
      localRequestEntity.addParam("gole_weight", Float.valueOf(0.0F));
      localRequestEntity.addParam("changeGoalWeightTime", Long.valueOf(0L));
      localRequestEntity.addParam("goal_fat", Float.valueOf(0.0F));
      localRequestEntity.addParam("time", Long.valueOf(this.cacheRole.getTime()));
      HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
      return;
    }
    this.cacheRole.setRole_id(-1L);
    OperationDB.insertRoleDB(this, this.cacheRole);
    this.app.clearAllData();
    this.app.setRole_id(-1L);
    Intent localIntent = new Intent();
    localIntent.setAction("com.picooc.latin.addvisitorsuccess");
    sendBroadcast(localIntent);
    finish();
    overridePendingTransition(2130968576, 2130968581);
  }

  public void invitGoneTop(View paramView)
  {
    paramView.measure(View.MeasureSpec.makeMeasureSpec((int)(this.mLcdWidth - 10.0F * this.mDensity), 1073741824), 0);
    ((LinearLayout.LayoutParams)paramView.getLayoutParams()).bottomMargin = (-paramView.getMeasuredHeight());
    paramView.setVisibility(8);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100740:
      dismissOtherView(this.linearLiftbottom, this.linearRitghtbottom);
      dismissOrShowOne(this.linearLift);
      return;
    case 2131100743:
      dismissOtherView(this.linearLift, this.linearRitghtbottom);
      dismissOrShowOne(this.linearLiftbottom);
      return;
    case 2131100745:
      dismissOtherView(this.linearLift, this.linearLiftbottom);
      dismissOrShowOne(this.linearRitghtbottom);
      return;
    case 2131100420:
      goNext();
      return;
    case 2131100736:
    }
    goNext();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903232);
    ((MyApplication)getApplication()).addActivity(this);
    this.relayout = ((LinearLayout)findViewById(2131100278));
    this.app = ((MyApplication)getApplicationContext());
    this.firstLinear = ((LinearLayout)this.relayout.findViewById(2131100734));
    this.twotLinear = ((RelativeLayout)this.relayout.findViewById(2131100735));
    this.boottom = ((RelativeLayout)this.relayout.findViewById(2131100419));
    this.cacheRole = ((RoleBin)getIntent().getSerializableExtra("cacheRole"));
    this.from = getIntent().getIntExtra("from", 1);
    this.linearLift = ((LinearLayout)findViewById(2131100741));
    this.linearLiftTop = ((LinearLayout)findViewById(2131100740));
    this.linearLiftbottom = ((LinearLayout)findViewById(2131100744));
    this.linearRitghtbottom = ((LinearLayout)findViewById(2131100746));
    DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
    this.mLcdWidth = localDisplayMetrics.widthPixels;
    this.mDensity = localDisplayMetrics.density;
    initWazi();
    invitGoneTop(this.linearLift);
    invitGoneTop(this.linearLiftbottom);
    invitGoneTop(this.linearRitghtbottom);
    this.regboy = ((ImageView)findViewById(2131100739));
    if (this.cacheRole.getSex() == 1)
      this.regboy.setImageResource(2130838130);
    if ((this.from == 2) || (this.from == 3))
    {
      this.relayout.setBackgroundResource(2130837518);
      this.twotLinear.setVisibility(0);
      this.firstLinear.setVisibility(8);
      this.boottom.setVisibility(8);
    }
    this.mRecevier = new HomeKeyEventBroadCastReceiver();
    IntentFilter localIntentFilter = new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS");
    registerReceiver(this.mRecevier, localIntentFilter);
    if ((getIntent().getStringExtra("username") != null) && (!getIntent().getStringExtra("username").equals("")))
      this.diog = new PicoocAlertDialog("您注册的账号是\n" + getIntent().getStringExtra("username"), "", "", this);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    ((MyApplication)getApplication()).removeActivity(this);
    unregisterReceiver(this.mRecevier);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
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
            WriteReferentwoAct.this.isExit = Boolean.valueOf(false);
          }
        }
        , 2000L);
        return false;
      }
      ((MyApplication)getApplication()).exit();
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onResume()
  {
    super.onResume();
    if ((this.isExitApp) && (this.diog != null))
      this.diog.showWelcomeAlerDialog();
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
            break label41;
          WriteReferentwoAct.this.isExitApp = true;
        }
      }
      return;
      label41: str.equals("recentapps");
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     WriteReferentwoAct
 * JD-Core Version:    0.6.2
 */
