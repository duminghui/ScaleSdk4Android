package com.picooc.lock;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import com.baidu.android.pushservice.PushManager;
import com.baidu.frontia.api.FrontiaAuthorization.MediaType;
import com.picooc.AnimCreator;
import com.picooc.FirstAct;
import com.picooc.LiftChangePwd;
import com.picooc.MyApplication;
import com.picooc.db.OperationDB;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.ThirdPartLogin;
import com.umeng.socialize.bean.SHARE_MEDIA;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.achartengine.tools.ModUtils;

public class PwdCheckActivity extends Activity
{
  public static final String LOCK_PICOOC_PWD = "lock_picooc_pwd";
  public static final String LOCK_PICOOC_UID = "lock_picooc_uid";
  public static final int L_P_P_HOMEKEY = 3;
  public static final int L_P_P_INIT = 2;
  public static final int L_P_P_LOGIN = 4;
  public static final int L_P_P_NONE = 1;
  public static final String L_P_P_STATE = "has_pwd_state";
  private Button mDeleteBtn;
  private View.OnClickListener mDeleteClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (PwdCheckActivity.this.mPwd.size() > 0)
        PwdCheckActivity.this.mPwd.pop();
      int i = PwdCheckActivity.this.mPwd.size();
      if (i < PwdCheckActivity.this.mImageViews.size())
        ((ImageView)PwdCheckActivity.this.mImageViews.get(i)).setImageResource(2130838033);
    }
  };
  private ArrayList<ImageView> mImageViews = new ArrayList();
  private View.OnClickListener mNumberClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      Button localButton = (Button)paramAnonymousView;
      int i = PwdCheckActivity.this.mPwd.size();
      if (i < PwdCheckActivity.this.mImageViews.size())
      {
        PwdCheckActivity.this.mPwd.add(localButton.getText().toString());
        ((ImageView)PwdCheckActivity.this.mImageViews.get(i)).setImageResource(2130838034);
        if (i == -1 + PwdCheckActivity.this.mImageViews.size())
        {
          if (!PwdCheckActivity.this.getPwd().equals(SharedPreferenceUtils.getPsd(PwdCheckActivity.this)))
            break label139;
          PwdCheckActivity.this.finish();
        }
      }
      while (true)
      {
        if (PwdCheckActivity.this.mDeleteBtn.getVisibility() == 8)
          PwdCheckActivity.this.mDeleteBtn.setVisibility(0);
        return;
        label139: PwdCheckActivity.this.runErroAnimation();
      }
    }
  };
  private LinkedList<String> mPwd = new LinkedList();
  private Vibrator mVibrator;

  private String getPwd()
  {
    String str1 = "";
    Iterator localIterator;
    if (this.mPwd.size() == this.mImageViews.size())
      localIterator = this.mPwd.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return str1;
      String str2 = (String)localIterator.next();
      str1 = str1 + str2;
    }
  }

  private void logout(MyApplication paramMyApplication)
  {
    SharedPreferenceUtils.savePsd(this, "");
    paramMyApplication.clearAllData();
    new ThirdPartLogin(this).delete(SHARE_MEDIA.SINA, this);
    PushManager.stopWork(this);
    Bundle localBundle = SharedPreferenceUtils.getBaiduChanelId(this);
    String str1 = localBundle.getString("baidu_user_id");
    String str2 = localBundle.getString("baiduChanel_id");
    RequestEntity localRequestEntity = new RequestEntity(HttpUtils.pLogOut, "5.2");
    localRequestEntity.addParam("myUserId", Long.valueOf(paramMyApplication.getCurrentUser().getUser_id()));
    localRequestEntity.addParam("baiduUserID", str1);
    localRequestEntity.addParam("baiduChannelID", str2);
    localRequestEntity.addParam("platform", "android");
    HttpUtils.getJson(this, localRequestEntity, null);
    SharedPreferenceUtils.clearFile(this, "user-Info");
    SharedPreferenceUtils.clearFile(this, "NEW_WEIGHTING_RECORD");
    OperationDB.deleteAllRoles(this);
    new ThirdPartLogin(this).startBaiduLogout(FrontiaAuthorization.MediaType.BAIDU, this);
    SharedPreferenceUtils.clearBaidu(this);
    finish();
    String str3 = "";
    if (!paramMyApplication.getCurrentUser().getPhone_no().equals(""))
      str3 = paramMyApplication.getCurrentUser().getPhone_no();
    while (true)
    {
      SharedPreferenceUtils.putValue(this, "userName", "userName", str3);
      Intent localIntent = new Intent();
      localIntent.setClass(this, FirstAct.class);
      localIntent.putExtra("pwd_lock_clear", true);
      startActivity(localIntent);
      paramMyApplication.exit();
      return;
      if (!paramMyApplication.getCurrentUser().getEmail().equals(""))
        str3 = paramMyApplication.getCurrentUser().getEmail();
    }
  }

  private void runErroAnimation()
  {
    TranslateAnimation localTranslateAnimation = AnimCreator.shake(1000L);
    localTranslateAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        Iterator localIterator = PwdCheckActivity.this.mImageViews.iterator();
        while (true)
        {
          if (!localIterator.hasNext())
          {
            if (PwdCheckActivity.this.mDeleteBtn.getVisibility() == 0)
              PwdCheckActivity.this.mDeleteBtn.setVisibility(8);
            PwdCheckActivity.this.mPwd.clear();
            return;
          }
          ((ImageView)localIterator.next()).setImageResource(2130838033);
        }
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
        long[] arrayOfLong = { 100L, 400L, 100L, 400L };
        PwdCheckActivity.this.mVibrator.vibrate(arrayOfLong, -1);
      }
    });
    ((View)((ImageView)this.mImageViews.get(0)).getParent()).startAnimation(localTranslateAnimation);
  }

  private void setupViews()
  {
    this.mImageViews.add((ImageView)findViewById(2131100002));
    this.mImageViews.add((ImageView)findViewById(2131100003));
    this.mImageViews.add((ImageView)findViewById(2131100004));
    this.mImageViews.add((ImageView)findViewById(2131100005));
    findViewById(2131100007).setOnClickListener(this.mNumberClickListener);
    findViewById(2131100008).setOnClickListener(this.mNumberClickListener);
    findViewById(2131100009).setOnClickListener(this.mNumberClickListener);
    findViewById(2131100010).setOnClickListener(this.mNumberClickListener);
    findViewById(2131100011).setOnClickListener(this.mNumberClickListener);
    findViewById(2131100012).setOnClickListener(this.mNumberClickListener);
    findViewById(2131100014).setOnClickListener(this.mNumberClickListener);
    findViewById(2131100015).setOnClickListener(this.mNumberClickListener);
    findViewById(2131100016).setOnClickListener(this.mNumberClickListener);
    findViewById(2131100019).setOnClickListener(this.mNumberClickListener);
    Typeface localTypeface = ModUtils.getTypeface(this);
    ((Button)findViewById(2131100007)).setTypeface(localTypeface);
    ((Button)findViewById(2131100008)).setTypeface(localTypeface);
    ((Button)findViewById(2131100009)).setTypeface(localTypeface);
    ((Button)findViewById(2131100010)).setTypeface(localTypeface);
    ((Button)findViewById(2131100011)).setTypeface(localTypeface);
    ((Button)findViewById(2131100012)).setTypeface(localTypeface);
    ((Button)findViewById(2131100014)).setTypeface(localTypeface);
    ((Button)findViewById(2131100015)).setTypeface(localTypeface);
    ((Button)findViewById(2131100016)).setTypeface(localTypeface);
    ((Button)findViewById(2131100019)).setTypeface(localTypeface);
    this.mDeleteBtn = ((Button)findViewById(2131100020));
    this.mDeleteBtn.setOnClickListener(this.mDeleteClickListener);
  }

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968576, 2130968581);
  }

  public void goToLogin(View paramView)
  {
    MyApplication localMyApplication = (MyApplication)getApplicationContext();
    if (localMyApplication.getCurrentUser().isHas_password())
    {
      logout(localMyApplication);
      return;
    }
    String str = localMyApplication.getCurrentUser().getPhone_no();
    if ((str != null) && (!"".equals(str)) && (!"null".equals(str)))
    {
      Intent localIntent = new Intent(this, LiftChangePwd.class);
      localIntent.putExtra("flag", 4);
      startActivityForResult(localIntent, 1002);
      return;
    }
    logout(localMyApplication);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    MyApplication localMyApplication;
    String str3;
    if ((paramInt1 == 1002) && (paramInt2 == 2213))
    {
      localMyApplication = (MyApplication)getApplicationContext();
      SharedPreferenceUtils.savePsd(this, "");
      localMyApplication.clearAllData();
      new ThirdPartLogin(this).delete(SHARE_MEDIA.SINA, this);
      PushManager.stopWork(this);
      Bundle localBundle = SharedPreferenceUtils.getBaiduChanelId(this);
      String str1 = localBundle.getString("baidu_user_id");
      String str2 = localBundle.getString("baiduChanel_id");
      RequestEntity localRequestEntity = new RequestEntity(HttpUtils.pLogOut, "5.2");
      localRequestEntity.addParam("myUserId", Long.valueOf(localMyApplication.getCurrentUser().getUser_id()));
      localRequestEntity.addParam("baiduUserID", str1);
      localRequestEntity.addParam("baiduChannelID", str2);
      localRequestEntity.addParam("platform", "android");
      HttpUtils.getJson(this, localRequestEntity, null);
      SharedPreferenceUtils.clearFile(this, "user-Info");
      SharedPreferenceUtils.clearFile(this, "NEW_WEIGHTING_RECORD");
      OperationDB.deleteAllRoles(this);
      new ThirdPartLogin(this).startBaiduLogout(FrontiaAuthorization.MediaType.BAIDU, this);
      SharedPreferenceUtils.clearBaidu(this);
      finish();
      str3 = "";
      if (localMyApplication.getCurrentUser().getPhone_no().equals(""))
        break label271;
      str3 = localMyApplication.getCurrentUser().getPhone_no();
    }
    while (true)
    {
      SharedPreferenceUtils.putValue(this, "userName", "userName", str3);
      Intent localIntent = new Intent();
      localIntent.setClass(this, FirstAct.class);
      localIntent.putExtra("pwd_lock_clear", true);
      startActivity(localIntent);
      localMyApplication.exit();
      return;
      label271: if (!localMyApplication.getCurrentUser().getEmail().equals(""))
        str3 = localMyApplication.getCurrentUser().getEmail();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903119);
    setupViews();
    this.mVibrator = ((Vibrator)getSystemService("vibrator"));
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      ((MyApplication)getApplication()).exit();
      finish();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PwdCheckActivity
 * JD-Core Version:    0.6.2
 */
