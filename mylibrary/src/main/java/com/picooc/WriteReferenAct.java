package com.picooc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.picooc.domain.RoleBin;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.DateUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.AnimUtils.selectHeitListener;
import com.picooc.widget.anyncImageView.AsyncImageView;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import com.picooc.widget.picoocProgress.PicoocAlertDialog;
import java.io.File;
import org.achartengine.tools.ModUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class WriteReferenAct extends PicoocActivity
  implements AnimUtils.selectHeitListener
{
  public static final String IMAGE_UNSPECIFIED = "image/*";
  public static final int NONE = 0;
  public static final int PHOTOHRAPH = 1;
  public static final int PHOTORESOULT = 3;
  public static final int PHOTOZOOM = 2;
  public static final String cacheHeadPath = Environment.getExternalStorageDirectory().getPath() + "/picooc/cacheHead.png";
  PicoocAlertDialog alertDialog;
  AnimUtils anim;
  MyApplication application;
  private TextView birthday;
  TextView bootomText;
  private RoleBin cacheRole;
  PicoocAlertDialog diog = null;
  private String headPath = "";
  private TextView height;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(WriteReferenAct.this, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(WriteReferenAct.this, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      Intent localIntent;
      if (localResponseEntity.getMethod().equals("uploadHeadImage"))
      {
        PicoocLoading.dismissDialog();
        localIntent = new Intent(WriteReferenAct.this, WriteReferentwoAct.class);
      }
      try
      {
        WriteReferenAct.this.cacheRole.setHead_portrait_url(localResponseEntity.getResp().getString("url"));
        localIntent.putExtra("cacheRole", WriteReferenAct.this.cacheRole);
        localIntent.putExtra("from", 1);
        WriteReferenAct.this.startActivity(localIntent);
        WriteReferenAct.this.finish();
        return;
      }
      catch (JSONException localJSONException)
      {
        while (true)
          localJSONException.printStackTrace();
      }
    }
  };
  private String imageUrl = null;
  AsyncImageView imageView = null;
  InputMethodManager imm;
  Boolean isExit = Boolean.valueOf(false);
  private boolean isExitApp = false;
  HomeKeyEventBroadCastReceiver mRecevier;
  private EditText nicName;
  private RadioGroup sexGroup;
  private long userID;

  private void goNext()
  {
    if ((this.nicName.getText() == null) || ("".equals(this.nicName.getText().toString())))
    {
      PicoocToast.showToast(this, "请输入昵称！");
      return;
    }
    if ((this.birthday.getText() == null) || ("".equals(this.birthday.getText().toString())))
    {
      PicoocToast.showToast(this, "请选择生日！");
      return;
    }
    if ((this.sexGroup.getCheckedRadioButtonId() != 2131100557) && (this.sexGroup.getCheckedRadioButtonId() != 2131100558))
    {
      PicoocToast.showToast(this, "请选择性别！");
      return;
    }
    if ((this.height.getText() == null) || ("".equals(this.height.getText().toString())))
    {
      PicoocToast.showToast(this, "请选择身高！");
      return;
    }
    final PicoocAlertDialog localPicoocAlertDialog = new PicoocAlertDialog("确认提交后，资料中的性别将不能修改。", "修改", "提交", this);
    localPicoocAlertDialog.showAlerDialogTwo(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localPicoocAlertDialog.dismissAlertDialog();
        WriteReferenAct.this.cacheRole.setName(WriteReferenAct.this.nicName.getText().toString());
        RoleBin localRoleBin = WriteReferenAct.this.cacheRole;
        if (WriteReferenAct.this.sexGroup.getCheckedRadioButtonId() == 2131100557);
        for (int i = 1; ; i = 0)
        {
          localRoleBin.setSex(i);
          WriteReferenAct.this.cacheRole.setTime(System.currentTimeMillis());
          if ((WriteReferenAct.this.headPath == null) || ("".equals(WriteReferenAct.this.headPath)))
            break;
          PicoocLoading.showLoadingDialog(WriteReferenAct.this);
          File localFile = new File(WriteReferenAct.this.headPath);
          HttpUtils.uploadFile(WriteReferenAct.this, localFile, WriteReferenAct.this.httpHandler);
          return;
        }
        Intent localIntent = new Intent(WriteReferenAct.this, WriteReferentwoAct.class);
        localIntent.putExtra("from", 1);
        localIntent.putExtra("cacheRole", WriteReferenAct.this.cacheRole);
        localIntent.putExtra("username", WriteReferenAct.this.getIntent().getStringExtra("username"));
        WriteReferenAct.this.startActivity(localIntent);
        WriteReferenAct.this.finish();
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

  public void alarmCount(String paramString1, String paramString2)
  {
  }

  public void dismissKeyboard()
  {
    if ((getWindow().getAttributes().softInputMode == 0) && (getCurrentFocus() != null))
      this.imm.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), 2);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == 0);
    do
    {
      return;
      if (paramInt1 == 1)
        ModUtils.startPhotoZoom(this, Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/temp.jpg")), cacheHeadPath);
    }
    while (paramIntent == null);
    if (paramInt1 == 2)
      ModUtils.startPhotoZoom(this, paramIntent.getData(), cacheHeadPath);
    if ((paramInt1 == 3) && (paramIntent.getExtras() != null))
    {
      this.imageView.setImageBitmap(ModUtils.toRoundBitmap(BitmapFactory.decodeFile(cacheHeadPath)));
      this.headPath = cacheHeadPath;
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100420:
      goNext();
      return;
    case 2131100726:
      String str;
      if ((getIntent().getStringExtra("username") != null) && (!getIntent().getStringExtra("username").equals("")))
        str = "您确定要退出主账号\n(" + getIntent().getStringExtra("username") + ")?";
      while (true)
      {
        this.alertDialog = new PicoocAlertDialog(str, "取消", "确定", this);
        this.alertDialog.showAlerDialogTwo(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            WriteReferenAct.this.alertDialog.dismissAlertDialog();
            WriteReferenAct.this.finish();
          }
        }
        , new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            WriteReferenAct.this.alertDialog.dismissAlertDialog();
          }
        });
        return;
        if (!this.nicName.getText().toString().equals(""))
          str = "您确定要退出第三方账号昵称为\n(" + this.nicName.getText().toString() + ")?";
        else
          str = "您确定要退出第三方账号\n?";
      }
    case 2131099853:
      dismissKeyboard();
      this.anim.getPopupWindow(1);
      return;
    case 2131100351:
      dismissKeyboard();
      if (this.sexGroup.getCheckedRadioButtonId() == 2131100557);
      for (int i = 1; ; i = 0)
      {
        this.anim.getPopupWindowHeight(1, i);
        return;
      }
    case 2131100336:
    }
    Log.i("picooc", "imm.isActive();=" + this.imm.isActive());
    dismissKeyboard();
    this.anim.getPopupWindowPhoto(1);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903229);
    ((MyApplication)getApplication()).addActivity(this);
    this.nicName = ((EditText)findViewById(2131100554));
    this.birthday = ((TextView)findViewById(2131099853));
    this.height = ((TextView)findViewById(2131100351));
    this.sexGroup = ((RadioGroup)findViewById(2131100556));
    this.imageView = ((AsyncImageView)findViewById(2131100336));
    ModUtils.cacludWH(this);
    this.anim = new AnimUtils(this);
    this.anim.setoselectHeitListener(this);
    this.bootomText = ((TextView)findViewById(2131099957));
    this.application = ((MyApplication)getApplicationContext());
    this.userID = getIntent().getLongExtra("userID", 0L);
    if ((getIntent().getStringExtra("screen_name") != null) && (!getIntent().getStringExtra("screen_name").equals("null")))
      this.nicName.setText(getIntent().getStringExtra("screen_name"));
    if ((getIntent().getStringExtra("profile_image_url") != null) && (!getIntent().getStringExtra("profile_image_url").equals("null")))
    {
      this.imageUrl = getIntent().getStringExtra("profile_image_url");
      this.imageView.setUrl(this.imageUrl);
    }
    while (true)
    {
      this.cacheRole = new RoleBin();
      this.cacheRole.setUser_id(this.userID);
      if (this.imageUrl != null)
        this.cacheRole.setHead_portrait_url(this.imageUrl);
      SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder("您  请您认真填写出生日期与身高，以免影响各项身体指标和分析报告的准确性。");
      Drawable localDrawable = getResources().getDrawable(2130838676);
      localDrawable.setBounds(0, 0, localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight());
      localSpannableStringBuilder.setSpan(new ImageSpan(localDrawable, 1), 0, 1, 34);
      this.bootomText.setText(localSpannableStringBuilder);
      this.nicName.setTypeface(ModUtils.getTypeface(this));
      this.birthday.setTypeface(ModUtils.getTypeface(this));
      this.height.setTypeface(ModUtils.getTypeface(this));
      this.imm = ((InputMethodManager)getSystemService("input_method"));
      this.mRecevier = new HomeKeyEventBroadCastReceiver();
      IntentFilter localIntentFilter = new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS");
      registerReceiver(this.mRecevier, localIntentFilter);
      if ((getIntent().getStringExtra("username") != null) && (!getIntent().getStringExtra("username").equals("")))
        this.diog = new PicoocAlertDialog("您注册的账号是\n" + getIntent().getStringExtra("username"), "", "", this);
      return;
      this.imageView.setImageResource(2130838092);
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.mRecevier);
    ((MyApplication)getApplication()).removeActivity(this);
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
            WriteReferenAct.this.isExit = Boolean.valueOf(false);
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

  public void selectFromPhone()
  {
    Intent localIntent = new Intent("android.intent.action.PICK", null);
    localIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
    startActivityForResult(localIntent, 2);
  }

  public void selectHeit(int paramInt, String paramString1, String paramString2)
  {
    if (paramInt == 1)
    {
      this.cacheRole.setHeight(Float.parseFloat(paramString1));
      this.height.setText(paramString1 + " CM");
    }
  }

  public void selectbirthDay(String paramString)
  {
    this.cacheRole.setBirthday(DateUtils.changeOldTimeStringToNewTimeString(paramString, "yyyy年MM月dd", "yyyyMMdd"));
    this.birthday.setText(paramString);
  }

  public void takePoto()
  {
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    localIntent.putExtra("output", Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp.jpg")));
    startActivityForResult(localIntent, 1);
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
          WriteReferenAct.this.isExitApp = true;
        }
      }
      return;
      label41: str.equals("recentapps");
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     WriteReferenAct
 * JD-Core Version:    0.6.2
 */
