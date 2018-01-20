package com.picooc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.picooc.arithmetic.ReportDirect;
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
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.DateUtils;
import com.picooc.utils.NumUtils;
import com.picooc.utils.PicoocFileUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.AnimUtils.selectHeitListener;
import com.picooc.widget.anyncImageView.AsyncImageView;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import java.io.File;
import org.achartengine.tools.ModUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class LiftChangeIntforAct extends PicoocActivity
  implements View.OnClickListener, AnimUtils.selectHeitListener
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
  private int Smoth = 0;
  private int Spepoheight = 0;
  private int Stady = 0;
  private int Syear = 0;
  AnimUtils anim;
  private MyApplication app;
  private TextView botomText;
  private RoleBin cacheRole;
  private int current = 3;
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
      PicoocToast.showToast(LiftChangeIntforAct.this, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      String str = localResponseEntity.getMethod();
      if (str.equals("uploadHeadImage"));
      while (!str.equals("updataRoleMessage"))
        try
        {
          LiftChangeIntforAct.this.cacheRole.setHead_portrait_url(localResponseEntity.getResp().getString("url"));
          LiftChangeIntforAct.this.updateRoleMessageToServer();
          return;
        }
        catch (JSONException localJSONException)
        {
          while (true)
            localJSONException.printStackTrace();
        }
      PicoocLoading.dismissDialog();
      if ((LiftChangeIntforAct.this.cacheRole.getGoal_fat() > 0.0F) && ((LiftChangeIntforAct.this.cacheRole.getHeight() != LiftChangeIntforAct.this.app.getCurrentRole().getHeight()) || (!LiftChangeIntforAct.this.cacheRole.getBirthday().equals(LiftChangeIntforAct.this.app.getCurrentRole().getBirthday())) || (LiftChangeIntforAct.this.cacheRole.getGoal_weight() != LiftChangeIntforAct.this.app.getCurrentRole().getGoal_weight())))
        OperationDB.insertToRoleInfos(LiftChangeIntforAct.this, LiftChangeIntforAct.this.cacheRole);
      OperationDB.updateRoleDB(LiftChangeIntforAct.this, LiftChangeIntforAct.this.cacheRole);
      LiftChangeIntforAct.this.app.setCurrentRole(LiftChangeIntforAct.this.cacheRole);
      if (!LiftChangeIntforAct.this.headPath.equals(""))
        LiftChangeIntforAct.this.setResult(1023);
      Intent localIntent = new Intent();
      localIntent.setAction("com.picooc.setting.updateRoleMessage");
      LiftChangeIntforAct.this.sendBroadcast(localIntent);
      LiftChangeIntforAct.this.finish();
    }
  };
  int idealweight;
  ImageView imageView = null;
  InputMethodManager imm;
  boolean isRunNian;
  private TextView mubiaoText;
  EditText nicName;
  private PopupWindow popupWindow;
  private PopupWindow popupWindowPhoto;
  private PopupWindow popupWindowheight;
  RadioGroup sexGroup;
  private TextView textDate;
  private int weightcout = 0;

  private void refreshIdealWeightRange()
  {
    float[] arrayOfFloat = ReportDirect.calculateIdealWeightRange(this.cacheRole);
    this.idealweight = NumUtils.roundFloatToInt(arrayOfFloat[0]);
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder("*您的理想体重区间是" + NumUtils.roundValue(arrayOfFloat[1]) + "~" + NumUtils.roundValue(arrayOfFloat[2]) + "kg");
    localSpannableStringBuilder.setSpan(new ForegroundColorSpan(-256), 3, 7, 34);
    this.botomText.setText(localSpannableStringBuilder);
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
          LiftChangeIntforAct localLiftChangeIntforAct = LiftChangeIntforAct.this;
          if (((i % 4 == 0) && (i % 100 != 0)) || (i % 400 == 0));
          for (boolean bool = false; ; bool = true)
          {
            localLiftChangeIntforAct.isRunNian = bool;
            String str = localadpSexAdaptertime.getItemText(localCotrlWheelView1.getCurrentItem());
            LiftChangeIntforAct.this.updateMoth(str, localView, -1);
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
          LiftChangeIntforAct localLiftChangeIntforAct = LiftChangeIntforAct.this;
          if (((i % 4 == 0) && (i % 100 != 0)) || (i % 400 == 0));
          for (boolean bool = false; ; bool = true)
          {
            localLiftChangeIntforAct.isRunNian = bool;
            String str = localadpSexAdaptertime.getItemText(localCotrlWheelView1.getCurrentItem());
            LiftChangeIntforAct.this.updateMoth(str, localView, 0);
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
          if (LiftChangeIntforAct.this.popupWindow != null)
            LiftChangeIntforAct.this.popupWindow.isShowing();
          return false;
        }
      });
      ((Button)localView.findViewById(2131099778)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          LiftChangeIntforAct.this.popupWindow.dismiss();
          LiftChangeIntforAct.this.popupWindow = null;
        }
      });
      ((Button)localView.findViewById(2131099779)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          LiftChangeIntforAct.this.popupWindow.dismiss();
          LiftChangeIntforAct.this.popupWindow = null;
          String str1 = adpSexAdapter.SEXS[localCotrlWheelView.getCurrentItem()];
          String str2 = adpSexAdaptertime.SEXS[localCotrlWheelView1.getCurrentItem()];
          String str3 = "";
          if (LiftChangeIntforAct.this.current == 1)
            str3 = LiftChangeIntforAct.SEXS[localCotrlWheelViewTwo.getCurrentItem()];
          while (true)
          {
            LiftChangeIntforAct.this.Syear = localCotrlWheelView.getCurrentItem();
            LiftChangeIntforAct.this.Smoth = localCotrlWheelView1.getCurrentItem();
            LiftChangeIntforAct.this.Stady = localCotrlWheelViewTwo.getCurrentItem();
            String str4 = str1 + "年" + str2 + "月" + str3 + "日";
            LiftChangeIntforAct.this.cacheRole.setBirthday(str1 + str2 + str3);
            LiftChangeIntforAct.this.refreshIdealWeightRange();
            LiftChangeIntforAct.this.textDate.setText(str4);
            return;
            if (LiftChangeIntforAct.this.current == 2)
              str3 = LiftChangeIntforAct.SEXS2[localCotrlWheelViewTwo.getCurrentItem()];
            else if (LiftChangeIntforAct.this.current == 3)
              str3 = LiftChangeIntforAct.SEXS3[localCotrlWheelViewTwo.getCurrentItem()];
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
    if (paramInt1 == 2)
    {
      localTextView.setText("体   重");
      localCotrlWheelViewheight.setCurrentItem(this.weightcout);
    }
    while (true)
    {
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
          LiftChangeIntforAct.this.popupWindow.dismiss();
          LiftChangeIntforAct.this.popupWindow = null;
          if (paramInt1 == 2)
          {
            LiftChangeIntforAct.this.weightcout = localCotrlWheelViewheight.getCurrentItem();
            LiftChangeIntforAct.this.mubiaoText.setText(localadpSexAdapterHeight.getItemText(localCotrlWheelViewheight.getCurrentItem()) + " kg");
            LiftChangeIntforAct.this.cacheRole.setGoal_weight(Float.parseFloat(localadpSexAdapterHeight.getItemText(LiftChangeIntforAct.this.weightcout).toString()));
            return;
          }
          LiftChangeIntforAct.this.height.setText(localadpSexAdapterHeight.getItemText(localCotrlWheelViewheight.getCurrentItem()) + " CM");
          LiftChangeIntforAct.this.Spepoheight = localCotrlWheelViewheight.getCurrentItem();
          LiftChangeIntforAct.this.cacheRole.setHeight(Float.parseFloat(localadpSexAdapterHeight.getItemText(LiftChangeIntforAct.this.Spepoheight).toString()));
          LiftChangeIntforAct.this.refreshIdealWeightRange();
        }
      });
      ((Button)localView.findViewById(2131099778)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          LiftChangeIntforAct.this.popupWindow.dismiss();
          LiftChangeIntforAct.this.popupWindow = null;
        }
      });
      return;
      localTextView.setText("身   高 ");
      localCotrlWheelViewheight.setCurrentItem(this.Spepoheight);
    }
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
    if (paramInt1 == 3)
    {
      Bundle localBundle = paramIntent.getExtras();
      if (localBundle != null)
      {
        Bitmap localBitmap = (Bitmap)localBundle.getParcelable("data");
        boolean bool = PicoocFileUtils.saveFileByBitmap(Environment.getExternalStorageDirectory().getPath(), "cacheHead.png", localBitmap);
        this.head_image.setImageBitmap(ModUtils.toRoundBitmap(localBitmap));
        if (bool)
          this.headPath = (Environment.getExternalStorageDirectory().getPath() + "/cacheHead.png");
      }
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131099853:
      dismissKeyboard();
      getPopupWindow();
      return;
    case 2131100351:
      dismissKeyboard();
      getPopupWindowHeight(1, 140, 220);
      return;
    case 2131099850:
      dismissKeyboard();
      this.anim.getPopupWindowPhoto(1);
      return;
    case 2131099651:
      if ((this.nicName.getText() == null) || ("".equals(this.nicName.getText().toString())))
      {
        PicoocToast.showToast(this, "请输入昵称！");
        return;
      }
      this.cacheRole.setName(this.nicName.getText().toString());
      if (!this.headPath.equals(""))
      {
        PicoocLoading.showLoadingDialog(this);
        HttpUtils.uploadFile(this, new File(this.headPath), this.httpHandler);
        return;
      }
      PicoocLoading.showLoadingDialog(this);
      updateRoleMessageToServer();
      return;
    case 2131099650:
      finish();
      return;
    case 2131100560:
    }
    dismissKeyboard();
    if (this.app.getTodayBody().getWeight() <= 0.0F)
    {
      PicoocToast.showToast(this, "只有进行首次称重才能修改目标体重哦！");
      return;
    }
    getPopupWindowHeight(2, NumUtils.roundFloatToInt(0.7F * this.idealweight), NumUtils.roundFloatToInt(1.3F * this.idealweight));
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903204);
    this.app = ((MyApplication)getApplicationContext());
    this.cacheRole = new RoleBin(this.app.getCurrentRole());
    this.imageView = ((ImageView)findViewById(2131100336));
    ImageView localImageView1 = (ImageView)findViewById(2131099651);
    ImageView localImageView2 = (ImageView)findViewById(2131099650);
    localImageView2.setImageResource(2130838406);
    localImageView1.setOnClickListener(this);
    localImageView2.setOnClickListener(this);
    localImageView1.setImageResource(2130838418);
    ((TextView)findViewById(2131099699)).setText("个人资料");
    ModUtils.cacludWH(this);
    this.head_image = ((AsyncImageView)findViewById(2131099850));
    RadioButton localRadioButton1;
    RadioButton localRadioButton2;
    if (!this.app.getCurrentRole().getHead_portrait_url().equals(""))
    {
      this.head_image.setUrl(this.app.getCurrentRole().getHead_portrait_url());
      this.nicName = ((EditText)findViewById(2131100554));
      this.nicName.setText(this.cacheRole.getName());
      this.textDate = ((TextView)findViewById(2131099853));
      this.textDate.setText(DateUtils.changeOldTimeStringToNewTimeString(this.cacheRole.getBirthday(), "yyyyMMdd", "yyyy年MM月dd日"));
      this.sexGroup = ((RadioGroup)findViewById(2131100556));
      localRadioButton1 = (RadioButton)findViewById(2131100557);
      localRadioButton2 = (RadioButton)findViewById(2131100558);
      if (this.cacheRole.getSex() != 1)
        break label681;
      localRadioButton1.setChecked(true);
      localRadioButton2.setVisibility(8);
    }
    while (true)
    {
      this.height = ((TextView)findViewById(2131100351));
      this.height.setText((int)this.cacheRole.getHeight() + " CM");
      this.mubiaoText = ((TextView)findViewById(2131100560));
      this.mubiaoText.setText(NumUtils.roundValue(this.cacheRole.getGoal_weight()) + "kg");
      this.botomText = ((TextView)findViewById(2131100561));
      refreshIdealWeightRange();
      this.textDate.setTypeface(ModUtils.getTypeface(this));
      this.nicName.setTypeface(ModUtils.getTypeface(this));
      this.height.setTypeface(ModUtils.getTypeface(this));
      this.mubiaoText.setTypeface(ModUtils.getTypeface(this));
      this.botomText.setTypeface(ModUtils.getTypeface(this));
      this.anim = new AnimUtils(this);
      this.anim.setoselectHeitListener(this);
      this.imm = ((InputMethodManager)getSystemService("input_method"));
      String str = this.cacheRole.getBirthday();
      if (str.length() == 8)
      {
        this.Syear = (-1934 + Integer.parseInt(str.substring(0, 4)));
        this.Smoth = (-1 + Integer.parseInt(str.substring(4, 6)));
        this.Stady = (-1 + Integer.parseInt(str.substring(6, 8)));
      }
      this.Spepoheight = (-140 + (int)this.cacheRole.getHeight());
      this.weightcout = (2 * ((int)this.cacheRole.getGoal_weight() - NumUtils.roundFloatToInt(0.7F * this.idealweight)));
      if (this.weightcout > NumUtils.roundFloatToInt(1.3F * this.idealweight) - NumUtils.roundFloatToInt(0.7F * this.idealweight))
        this.weightcout = 0;
      return;
      if (this.app.getCurrentRole().getSex() != 1)
        break;
      this.head_image.setDefaultImageResource(2130838457);
      break;
      label681: localRadioButton1.setVisibility(8);
      localRadioButton2.setChecked(true);
    }
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
    refreshIdealWeightRange();
  }

  public void startPhotoZoom(Uri paramUri)
  {
    Intent localIntent = new Intent("com.android.camera.action.CROP");
    localIntent.setDataAndType(paramUri, "image/*");
    localIntent.putExtra("crop", "true");
    localIntent.putExtra("aspectX", 1);
    localIntent.putExtra("aspectY", 1);
    localIntent.putExtra("outputX", 250);
    localIntent.putExtra("outputY", 250);
    localIntent.putExtra("return-data", true);
    startActivityForResult(localIntent, 3);
  }

  public void takePoto()
  {
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    localIntent.putExtra("output", Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp.jpg")));
    startActivityForResult(localIntent, 1);
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
 * Qualified Name:     LiftChangeIntforAct
 * JD-Core Version:    0.6.2
 */
