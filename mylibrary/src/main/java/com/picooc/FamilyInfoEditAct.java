package com.picooc;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.arithmetic.ReportDirect;
import com.picooc.constant.ThemeConstant;
import com.picooc.contwheel.adpSexAdapter;
import com.picooc.contwheel.adpSexAdaptertime;
import com.picooc.contwheelheight.CotrlWheelViewheight;
import com.picooc.contwheelheight.adpSexAdapterHeight;
import com.picooc.contwheeltwo.CotrlWheelViewTwo;
import com.picooc.contwheeltwo.adpSexAdapterTwo;
import com.picooc.db.OperationDB;
import com.picooc.domain.BodyFatReport;
import com.picooc.domain.RoleBin;
import com.picooc.internet.AsyncMessageUtils;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.DateUtils;
import com.picooc.utils.StringUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.AnimUtils.selectHeitListener;
import com.picooc.widget.anyncImageView.AsyncImageView;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import com.picooc.widget.picoocProgress.PicoocAlertDialog4FamilyPasswrod;
import com.picooc.widget.picoocProgress.PicoocAlertDialog4FamilyPasswrod.updateListener;
import java.io.File;
import java.io.IOException;
import org.achartengine.tools.ModUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class FamilyInfoEditAct extends PicoocActivity
  implements View.OnClickListener, AnimUtils.selectHeitListener, updateListener
{
  public static final String IMAGE_UNSPECIFIED = "image/*";
  public static final int NONE = 0;
  public static final int PHOTOHRAPH = 1;
  public static final int PHOTORESOULT = 3;
  public static final int PHOTOZOOM = 2;
  public static final String[] SEXS = { "01", "02", "03", "05", "04", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28" };
  public static final String[] SEXS0 = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29" };
  public static final String[] SEXS2 = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" };
  public static final String[] SEXS3 = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
  public static final String cacheHeadPath = Environment.getExternalStorageDirectory().getPath() + "/picooc/cacheHead.png";
  private int Smoth = 0;
  private int Spepoheight = 0;
  private int Stady = 0;
  private int Syear = 0;
  AnimUtils anim;
  private MyApplication app;
  private TextView bithdayTextdown;
  private RoleBin cacheRole;
  private int current = 3;
  PicoocAlertDialog4FamilyPasswrod dilog;
  private String headPath = "";
  private AsyncImageView head_image;
  private TextView height;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(FamilyInfoEditAct.this, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      String str = localResponseEntity.getMethod();
      if (str.equals("uploadHeadImage"));
      do
      {
        try
        {
          FamilyInfoEditAct.this.cacheRole.setHead_portrait_url(localResponseEntity.getResp().getString("url"));
          FamilyInfoEditAct.this.updateRoleMessageToServer();
          return;
        }
        catch (JSONException localJSONException2)
        {
          while (true)
            localJSONException2.printStackTrace();
        }
        if (str.equals("updataRoleMessage"))
        {
          PicoocLoading.dismissDialog();
          if ((FamilyInfoEditAct.this.cacheRole.getGoal_fat() > 0.0F) && ((FamilyInfoEditAct.this.cacheRole.getHeight() != FamilyInfoEditAct.this.app.getCurrentRole().getHeight()) || (!FamilyInfoEditAct.this.cacheRole.getBirthday().equals(FamilyInfoEditAct.this.app.getCurrentRole().getBirthday())) || (FamilyInfoEditAct.this.cacheRole.getGoal_weight() != FamilyInfoEditAct.this.app.getCurrentRole().getGoal_weight())))
            OperationDB.insertToRoleInfos(FamilyInfoEditAct.this, FamilyInfoEditAct.this.cacheRole);
          OperationDB.updateRoleDB(FamilyInfoEditAct.this, FamilyInfoEditAct.this.cacheRole);
          FamilyInfoEditAct.this.app.setCurrentRole(FamilyInfoEditAct.this.cacheRole);
          if (!FamilyInfoEditAct.this.headPath.equals(""))
            FamilyInfoEditAct.this.setResult(1023);
          Intent localIntent = new Intent();
          localIntent.setAction("com.picooc.setting.updateRoleMessage");
          FamilyInfoEditAct.this.sendBroadcast(localIntent);
          FamilyInfoEditAct.this.finish();
          return;
        }
      }
      while (!str.equals("verifyPassword"));
      PicoocLoading.dismissDialog();
      try
      {
        if (localResponseEntity.getResp().getInt("is_validate") == 1)
        {
          FamilyInfoEditAct.this.isHasLock = false;
          FamilyInfoEditAct.this.iv_head_lock.setVisibility(8);
          FamilyInfoEditAct.this.iv_birthday_lock.setVisibility(8);
          FamilyInfoEditAct.this.iv_height_lock.setVisibility(8);
          return;
        }
      }
      catch (JSONException localJSONException1)
      {
        localJSONException1.printStackTrace();
        return;
      }
      PicoocToast.showToast(FamilyInfoEditAct.this, "您输入的密码不正确！");
    }
  };
  int idealweight;
  ImageView imageView = null;
  InputMethodManager imm;
  private boolean isHasLock = false;
  boolean isRunNian;
  private ImageView iv_birthday_lock;
  private ImageView iv_head_lock;
  private ImageView iv_height_lock;
  private RelativeLayout logokuang;
  EditText nicName;
  private PopupWindow popupWindow;
  private PopupWindow popupWindowPhoto;
  private PopupWindow popupWindowheight;
  private RelativeLayout relativelayout_bg;
  private TextView textDate;
  private ThemeConstant themeConstant;
  private TextView tv_height_unit;
  private TextView tv_huashu;
  private TextView writeInformation;

  private void refreshNicName(TextView paramTextView)
  {
    if (this.app.getCurrentRole().getFamily_type() == 0)
    {
      paramTextView.setText(this.app.getCurrentRole().getName());
      return;
    }
    if ((this.app.getCurrentRole().getRemark_name() != null) && (!this.app.getCurrentRole().getRemark_name().equals("")))
    {
      paramTextView.setText(this.app.getCurrentRole().getRemark_name());
      return;
    }
    paramTextView.setText(this.app.getCurrentRole().getName());
  }

  private void showInputPasswordDialog()
  {
    this.dilog.showAlerDialog(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        String str = FamilyInfoEditAct.this.dilog.getInputMessage();
        if (!StringUtils.isPassword(str))
        {
          PicoocToast.showToast(FamilyInfoEditAct.this, "请输入正确格式的密码!");
          return;
        }
        PicoocLoading.showLoadingDialog(FamilyInfoEditAct.this);
        RequestEntity localRequestEntity = new RequestEntity("verifyPassword", "5.2");
        localRequestEntity.addParam("email", FamilyInfoEditAct.this.app.getCurrentRole().getEmail());
        localRequestEntity.addParam("phoneNumber", FamilyInfoEditAct.this.app.getCurrentRole().getPhone_no());
        localRequestEntity.addParam("remoteUserId", Long.valueOf(FamilyInfoEditAct.this.app.getCurrentRole().getRemote_user_id()));
        localRequestEntity.addParam("password", str);
        HttpUtils.getJson(FamilyInfoEditAct.this, localRequestEntity, FamilyInfoEditAct.this.httpHandler);
        FamilyInfoEditAct.this.dilog.dismissAlertDialog();
      }
    }
    , new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FamilyInfoEditAct.this.dilog.dismissAlertDialog();
      }
    });
  }

  private void updateRoleMessageToServer()
  {
    if (this.cacheRole.getGoal_weight() != this.app.getCurrentRole().getGoal_weight())
    {
      this.cacheRole.setChange_goal_weight_time(System.currentTimeMillis());
      this.cacheRole.setWeight_change_target(this.cacheRole.getGoal_weight() - this.app.getTodayBody().getWeight());
      long l = this.app.getTodayBody().getTime();
      if (DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), l) > 0L)
        break label194;
      BodyFatReport localBodyFatReport = ReportDirect.calculateIdealBodyFat(this.cacheRole, this.app.getTodayBody(), true);
      this.cacheRole.setGoal_fat(localBodyFatReport.getGoalFatRace());
    }
    while (true)
    {
      if ((this.cacheRole.getHeight() != this.app.getCurrentRole().getHeight()) || (!this.cacheRole.getBirthday().equals(this.app.getCurrentRole().getBirthday())) || (this.cacheRole.getGoal_weight() != this.app.getCurrentRole().getGoal_weight()))
        this.cacheRole.setTime(System.currentTimeMillis());
      AsyncMessageUtils.updateRoleMessage(this, this.cacheRole, this.httpHandler);
      return;
      label194: this.cacheRole.setGoal_fat(0.0F);
    }
  }

  public void alarmCount(String paramString1, String paramString2)
  {
  }

  public void dismissKeyboard()
  {
    if ((getWindow().getAttributes().softInputMode == 0) && (getCurrentFocus() != null))
      this.imm.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), 2);
  }

  public void finish()
  {
    super.finish();
  }

  public void getPopupWindow()
  {
    if ((this.popupWindow != null) && (this.popupWindow.isShowing()))
    {
      this.popupWindow.dismiss();
      this.popupWindow = null;
      return;
    }
    initPopuptWindow();
  }

  public void getPopupWindowHeight(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((this.popupWindowheight != null) && (this.popupWindowheight.isShowing()))
    {
      this.popupWindowheight.dismiss();
      this.popupWindowheight = null;
      return;
    }
    initPopuptWindowHeight(paramInt1, paramInt2, paramInt3);
  }

  protected void initPopuptWindow()
  {
    final View localView = getLayoutInflater().inflate(2130903110, null, false);
    final com.picooc.contwheel.CotrlWheelView localCotrlWheelView = (com.picooc.contwheel.CotrlWheelView)localView.findViewById(2131099994);
    final adpSexAdapter localadpSexAdapter = new adpSexAdapter(this);
    localCotrlWheelView.setViewAdapter(localadpSexAdapter);
    localCotrlWheelView.setCurrentItem(this.Syear);
    final adpSexAdaptertime localadpSexAdaptertime = new adpSexAdaptertime(this);
    int i = Integer.parseInt(localadpSexAdapter.getItemText(localCotrlWheelView.getCurrentItem()));
    if (((i % 4 == 0) && (i % 100 != 0)) || (i % 400 == 0));
    for (boolean bool = false; ; bool = true)
    {
      this.isRunNian = bool;
      final com.picooc.contwheelthree.CotrlWheelView localCotrlWheelView1 = (com.picooc.contwheelthree.CotrlWheelView)localView.findViewById(2131099995);
      localCotrlWheelView1.setViewAdapter(localadpSexAdaptertime);
      localCotrlWheelView1.setCurrentItem(this.Smoth);
      localCotrlWheelView.addScrollingListener(new com.picooc.contwheel.CotrlOnWheelScrollListener()
      {
        public void onScrollingFinished(com.picooc.contwheel.CotrlWheelView paramAnonymousCotrlWheelView)
        {
          int i = Integer.parseInt(localadpSexAdapter.getItemText(localCotrlWheelView.getCurrentItem()));
          FamilyInfoEditAct localFamilyInfoEditAct = FamilyInfoEditAct.this;
          if (((i % 4 == 0) && (i % 100 != 0)) || (i % 400 == 0));
          for (boolean bool = false; ; bool = true)
          {
            localFamilyInfoEditAct.isRunNian = bool;
            String str = localadpSexAdaptertime.getItemText(localCotrlWheelView1.getCurrentItem());
            FamilyInfoEditAct.this.updateMoth(str, localView, -1);
            return;
          }
        }

        public void onScrollingStarted(com.picooc.contwheel.CotrlWheelView paramAnonymousCotrlWheelView)
        {
        }
      });
      adpSexAdapterTwo localadpSexAdapterTwo = new adpSexAdapterTwo(this, SEXS3);
      final CotrlWheelViewTwo localCotrlWheelViewTwo = (CotrlWheelViewTwo)localView.findViewById(2131099996);
      localCotrlWheelViewTwo.setViewAdapter(localadpSexAdapterTwo);
      updateMoth(localadpSexAdaptertime.getItemText(localCotrlWheelView1.getCurrentItem()), localView, 0);
      localCotrlWheelViewTwo.setCurrentItem(this.Stady);
      localCotrlWheelView1.addScrollingListener(new com.picooc.contwheelthree.CotrlOnWheelScrollListener()
      {
        public void onScrollingFinished(com.picooc.contwheelthree.CotrlWheelView paramAnonymousCotrlWheelView)
        {
          int i = Integer.parseInt(localadpSexAdapter.getItemText(localCotrlWheelView.getCurrentItem()));
          FamilyInfoEditAct localFamilyInfoEditAct = FamilyInfoEditAct.this;
          if (((i % 4 == 0) && (i % 100 != 0)) || (i % 400 == 0));
          for (boolean bool = false; ; bool = true)
          {
            localFamilyInfoEditAct.isRunNian = bool;
            String str = localadpSexAdaptertime.getItemText(localCotrlWheelView1.getCurrentItem());
            FamilyInfoEditAct.this.updateMoth(str, localView, 0);
            return;
          }
        }

        public void onScrollingStarted(com.picooc.contwheelthree.CotrlWheelView paramAnonymousCotrlWheelView)
        {
        }
      });
      this.popupWindow = new PopupWindow(localView, ModUtils.wiht, ModUtils.heiht / 2, true);
      this.popupWindow.setBackgroundDrawable(new BitmapDrawable());
      this.popupWindow.setAnimationStyle(2131427336);
      PopupWindow localPopupWindow = this.popupWindow;
      Button localButton = new Button(this);
      localPopupWindow.showAtLocation(localButton, 80, 0, 0);
      this.popupWindow.setFocusable(true);
      this.popupWindow.setBackgroundDrawable(new BitmapDrawable());
      this.popupWindow.setOutsideTouchable(true);
      this.popupWindow.update();
      localView.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if (FamilyInfoEditAct.this.popupWindow != null)
            FamilyInfoEditAct.this.popupWindow.isShowing();
          return false;
        }
      });
      ((Button)localView.findViewById(2131099778)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          FamilyInfoEditAct.this.popupWindow.dismiss();
          FamilyInfoEditAct.this.popupWindow = null;
        }
      });
      ((Button)localView.findViewById(2131099779)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          FamilyInfoEditAct.this.popupWindow.dismiss();
          FamilyInfoEditAct.this.popupWindow = null;
          String str1 = adpSexAdapter.SEXS[localCotrlWheelView.getCurrentItem()];
          String str2 = adpSexAdaptertime.SEXS[localCotrlWheelView1.getCurrentItem()];
          String str3 = "";
          if (FamilyInfoEditAct.this.current == 1)
            str3 = FamilyInfoEditAct.SEXS[localCotrlWheelViewTwo.getCurrentItem()];
          while (true)
          {
            FamilyInfoEditAct.this.Syear = localCotrlWheelView.getCurrentItem();
            FamilyInfoEditAct.this.Smoth = localCotrlWheelView1.getCurrentItem();
            FamilyInfoEditAct.this.Stady = localCotrlWheelViewTwo.getCurrentItem();
            String str4 = str1 + "年" + str2 + "月" + str3 + "日";
            FamilyInfoEditAct.this.cacheRole.setBirthday(str1 + str2 + str3);
            FamilyInfoEditAct.this.textDate.setText(str1);
            FamilyInfoEditAct.this.bithdayTextdown.setText(str2 + "." + str3);
            int i = DateUtils.getAge(str4, "yyyy年MM月dd");
            if ((i > 60) || (i < 18))
              AnimUtils.showDialog(FamilyInfoEditAct.this);
            return;
            if (FamilyInfoEditAct.this.current == 2)
              str3 = FamilyInfoEditAct.SEXS2[localCotrlWheelViewTwo.getCurrentItem()];
            else if (FamilyInfoEditAct.this.current == 3)
              str3 = FamilyInfoEditAct.SEXS3[localCotrlWheelViewTwo.getCurrentItem()];
          }
        }
      });
      return;
    }
  }

  protected void initPopuptWindowHeight(final int paramInt1, int paramInt2, int paramInt3)
  {
    View localView = getLayoutInflater().inflate(2130903109, null, false);
    TextView localTextView = (TextView)localView.findViewById(2131099985);
    final CotrlWheelViewheight localCotrlWheelViewheight = (CotrlWheelViewheight)localView.findViewById(2131099994);
    CotrlWheelViewheight.key = paramInt1;
    final adpSexAdapterHeight localadpSexAdapterHeight = new adpSexAdapterHeight(this, paramInt1, paramInt2, paramInt3);
    localCotrlWheelViewheight.setViewAdapter(localadpSexAdapterHeight);
    if (paramInt1 != 2)
    {
      localTextView.setText("身   高 ");
      localCotrlWheelViewheight.setCurrentItem(this.Spepoheight);
    }
    this.popupWindow = new PopupWindow(localView, ModUtils.wiht, ModUtils.heiht / 2, true);
    this.popupWindow.setBackgroundDrawable(new BitmapDrawable());
    this.popupWindow.setAnimationStyle(2131427336);
    this.popupWindow.showAtLocation(new Button(this), 80, 0, 0);
    this.popupWindow.setFocusable(true);
    this.popupWindow.setBackgroundDrawable(new BitmapDrawable());
    this.popupWindow.setOutsideTouchable(true);
    this.popupWindow.update();
    ((Button)localView.findViewById(2131099779)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FamilyInfoEditAct.this.popupWindow.dismiss();
        FamilyInfoEditAct.this.popupWindow = null;
        if (paramInt1 != 2)
        {
          FamilyInfoEditAct.this.height.setText(localadpSexAdapterHeight.getItemText(localCotrlWheelViewheight.getCurrentItem()));
          FamilyInfoEditAct.this.Spepoheight = localCotrlWheelViewheight.getCurrentItem();
          FamilyInfoEditAct.this.cacheRole.setHeight(Float.parseFloat(localadpSexAdapterHeight.getItemText(FamilyInfoEditAct.this.Spepoheight).toString()));
        }
      }
    });
    ((Button)localView.findViewById(2131099778)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FamilyInfoEditAct.this.popupWindow.dismiss();
        FamilyInfoEditAct.this.popupWindow = null;
      }
    });
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == 0);
    do
    {
      return;
      if (paramInt1 == 1)
        startPhotoZoom(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/temp.jpg")));
    }
    while (paramIntent == null);
    if (paramInt1 == 2)
      startPhotoZoom(paramIntent.getData());
    if ((paramInt1 == 3) && (paramIntent.getExtras() != null))
    {
      this.head_image.setImageBitmap(ModUtils.toRoundBitmap(BitmapFactory.decodeFile(cacheHeadPath)));
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
    case 2131100347:
      if (this.isHasLock)
      {
        showInputPasswordDialog();
        return;
      }
      dismissKeyboard();
      getPopupWindow();
      return;
    case 2131100351:
      if (this.isHasLock)
      {
        showInputPasswordDialog();
        return;
      }
      dismissKeyboard();
      getPopupWindowHeight(1, 140, 220);
      return;
    case 2131099850:
      if (this.isHasLock)
      {
        showInputPasswordDialog();
        return;
      }
      dismissKeyboard();
      this.anim.getPopupWindowPhoto(1);
      return;
    case 2131100335:
      dismissKeyboard();
      this.anim.getPopupWindowPhoto(1);
      return;
    case 2131099651:
      if ((this.nicName.getText() == null) || ("".equals(this.nicName.getText().toString())))
      {
        PicoocToast.showToast(this, "请输入昵称！");
        return;
      }
      if (this.isHasLock)
        this.cacheRole.setRemark_name(this.nicName.getText().toString());
      while (!this.headPath.equals(""))
      {
        PicoocLoading.showLoadingDialog(this);
        HttpUtils.uploadFile(this, new File(this.headPath), this.httpHandler);
        return;
        this.cacheRole.setName(this.nicName.getText().toString());
      }
      PicoocLoading.showLoadingDialog(this);
      updateRoleMessageToServer();
      return;
    case 2131099650:
    }
    finish();
    overridePendingTransition(-1, 2130968597);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903207);
    this.relativelayout_bg = ((RelativeLayout)findViewById(2131100562));
    this.themeConstant = new ThemeConstant(this);
    this.themeConstant.setTheme(this.relativelayout_bg);
    this.writeInformation = ((TextView)findViewById(2131100332));
    this.app = ((MyApplication)getApplicationContext());
    this.cacheRole = new RoleBin(this.app.getCurrentRole());
    label211: boolean bool;
    if (this.app.getCurrentUser().getRole_id() == this.app.getCurrentRole().getRole_id())
    {
      this.writeInformation.setText("修改资料");
      this.imageView = ((ImageView)findViewById(2131100336));
      ImageView localImageView1 = (ImageView)findViewById(2131099651);
      ImageView localImageView2 = (ImageView)findViewById(2131099650);
      localImageView1.setOnClickListener(this);
      localImageView2.setOnClickListener(this);
      ModUtils.cacludWH(this);
      this.logokuang = ((RelativeLayout)findViewById(2131100335));
      if (this.cacheRole.getSex() != 1)
        break label862;
      this.logokuang.setBackgroundResource(2130838462);
      this.iv_head_lock = ((ImageView)findViewById(2131100567));
      this.iv_birthday_lock = ((ImageView)findViewById(2131100573));
      this.iv_height_lock = ((ImageView)findViewById(2131100576));
      this.head_image = ((AsyncImageView)findViewById(2131099850));
      if (this.app.getCurrentRole().getHead_portrait_url().equals(""))
        break label875;
      this.head_image.setUrl(this.app.getCurrentRole().getHead_portrait_url());
      label302: this.nicName = ((EditText)findViewById(2131100554));
      refreshNicName(this.nicName);
      String str1 = this.cacheRole.getBirthday().toString();
      String str2 = str1.substring(0, 4);
      String str3 = str1.substring(4, 6) + "." + str1.substring(6, 8);
      this.textDate = ((TextView)findViewById(2131100572));
      this.bithdayTextdown = ((TextView)findViewById(2131100350));
      this.textDate.setText(str2);
      this.bithdayTextdown.setText(str3);
      if (this.app.getCurrentRole().getFamily_type() != 1)
        break label902;
      bool = true;
      label449: this.isHasLock = bool;
      this.tv_huashu = ((TextView)findViewById(2131100577));
      this.tv_huashu.setTypeface(TypefaceUtils.getTypeface(this, null));
      if (this.isHasLock)
        break label908;
      this.iv_head_lock.setVisibility(8);
      this.iv_birthday_lock.setVisibility(8);
      this.iv_height_lock.setVisibility(8);
      SpannableStringBuilder localSpannableStringBuilder2 = new SpannableStringBuilder("您 " + this.cacheRole.getName() + "是您的本机使用家人。");
      Drawable localDrawable2 = getResources().getDrawable(2130837705);
      localDrawable2.setBounds(0, 0, localDrawable2.getIntrinsicWidth(), localDrawable2.getIntrinsicHeight());
      localSpannableStringBuilder2.setSpan(new ImageSpan(localDrawable2, 1), 0, 1, 34);
      this.tv_huashu.setText(localSpannableStringBuilder2);
    }
    while (true)
    {
      this.height = ((TextView)findViewById(2131100574));
      this.height.setText((int)this.cacheRole.getHeight());
      this.tv_height_unit = ((TextView)findViewById(2131100575));
      this.textDate.setTypeface(TypefaceUtils.getTypeface(this, null));
      this.bithdayTextdown.setTypeface(TypefaceUtils.getTypeface(this, null));
      this.nicName.setTypeface(TypefaceUtils.getTypeface(this, null));
      this.height.setTypeface(TypefaceUtils.getTypeface(this, null));
      this.tv_height_unit.setTypeface(TypefaceUtils.getTypeface(this, null));
      this.anim = new AnimUtils(this);
      this.anim.setoselectHeitListener(this);
      this.imm = ((InputMethodManager)getSystemService("input_method"));
      String str4 = this.cacheRole.getBirthday();
      if (str4.length() == 8)
      {
        this.Syear = (-1934 + Integer.parseInt(str4.substring(0, 4)));
        this.Smoth = (-1 + Integer.parseInt(str4.substring(4, 6)));
        this.Stady = (-1 + Integer.parseInt(str4.substring(6, 8)));
      }
      this.Spepoheight = (-140 + (int)this.cacheRole.getHeight());
      return;
      this.writeInformation.setText("修改家人资料");
      break;
      label862: this.logokuang.setBackgroundResource(2130838459);
      break label211;
      label875: if (this.app.getCurrentRole().getSex() != 1)
        break label302;
      this.head_image.setDefaultImageResource(2130838457);
      break label302;
      label902: bool = false;
      break label449;
      label908: SpannableStringBuilder localSpannableStringBuilder1 = new SpannableStringBuilder("您 " + this.cacheRole.getName() + "是您的远程关注家人。如非家人允许，请不要随意修改家人资料，否则会影响测量数据的准确性哦。");
      Drawable localDrawable1 = getResources().getDrawable(2130837709);
      localDrawable1.setBounds(0, 0, localDrawable1.getIntrinsicWidth(), localDrawable1.getIntrinsicHeight());
      localSpannableStringBuilder1.setSpan(new ImageSpan(localDrawable1, 1), 0, 1, 34);
      this.tv_huashu.setText(localSpannableStringBuilder1);
      this.dilog = new PicoocAlertDialog4FamilyPasswrod("为确保测量数据的准确性，请您不要随意修改家人资料", "取消", "确定", this);
    }
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      finish();
      overridePendingTransition(-1, 2130968597);
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
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
      this.height.setText(paramString1 + " CM");
  }

  public void selectbirthDay(String paramString)
  {
    this.textDate.setText(paramString);
    this.cacheRole.setBirthday(DateUtils.changeOldTimeStringToNewTimeString(paramString, "yyyy年MM月dd", "yyyyMMdd"));
  }

  public void startPhotoZoom(Uri paramUri)
  {
    Intent localIntent = new Intent("com.android.camera.action.CROP");
    localIntent.setDataAndType(paramUri, "image/*");
    localIntent.putExtra("crop", "true");
    File localFile = new File(cacheHeadPath);
    if (!localFile.exists());
    try
    {
      localFile.createNewFile();
      localIntent.putExtra("aspectX", 1);
      localIntent.putExtra("aspectY", 1);
      localIntent.putExtra("outputX", 250);
      localIntent.putExtra("outputY", 250);
      localIntent.putExtra("output", Uri.fromFile(localFile));
      localIntent.putExtra("return-data", false);
      startActivityForResult(localIntent, 3);
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public void takePoto()
  {
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    localIntent.putExtra("output", Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp.jpg")));
    startActivityForResult(localIntent, 1);
  }

  public void trueUpdate()
  {
  }

  public void updateMoth(String paramString, View paramView, int paramInt)
  {
    if (Integer.valueOf(paramString).intValue() == 2)
    {
      if (!this.isRunNian)
      {
        updateTaday(paramView, SEXS0, 0);
        return;
      }
      updateTaday(paramView, SEXS, 0);
      return;
    }
    if ((Integer.valueOf(paramString).intValue() == 4) || (Integer.valueOf(paramString).intValue() == 6) || (Integer.valueOf(paramString).intValue() == 9) || (Integer.valueOf(paramString).intValue() == 11))
    {
      updateTaday(paramView, SEXS2, paramInt);
      return;
    }
    updateTaday(paramView, SEXS3, paramInt);
  }

  public void updateTaday(View paramView, String[] paramArrayOfString, int paramInt)
  {
    if (paramInt != -1)
    {
      adpSexAdapterTwo localadpSexAdapterTwo = new adpSexAdapterTwo(this, paramArrayOfString);
      CotrlWheelViewTwo localCotrlWheelViewTwo = (CotrlWheelViewTwo)paramView.findViewById(2131099996);
      localCotrlWheelViewTwo.setViewAdapter(localadpSexAdapterTwo);
      localCotrlWheelViewTwo.setCurrentItem(paramInt);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     FamilyInfoEditAct
 * JD-Core Version:    0.6.2
 */
