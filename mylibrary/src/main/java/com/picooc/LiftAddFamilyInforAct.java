package com.picooc;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.db.OperationDB;
import com.picooc.domain.RoleBin;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.AnimUtils.selectHeitListener;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import com.picooc.widget.picoocProgress.PicoocAlertDialog;
import com.picooc.widget.picoocProgress.PicoocAlertDialog.updateListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.achartengine.tools.ModUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class LiftAddFamilyInforAct extends PicoocActivity
  implements AnimUtils.selectHeitListener, updateListener
{
  public static final String IMAGE_UNSPECIFIED = "image/*";
  public static final int NONE = 0;
  public static final int PHOTOHRAPH = 1;
  public static final int PHOTORESOULT = 3;
  public static final int PHOTOZOOM = 2;
  public static final String cacheHeadPath = Environment.getExternalStorageDirectory().getPath() + "/picooc/cacheHead.png";
  LinearLayout BirthdayLiner;
  LinearLayout HeightLiner;
  AnimUtils anim;
  private MyApplication app;
  private String birthday = "";
  private Boolean bithdayFirst = Boolean.valueOf(true);
  TextView bithdayText;
  TextView bithdayTextdown;
  private RoleBin cacheRole;
  int checksexID = -1;
  PicoocAlertDialog dilog;
  private String headPath = "";
  TextView heightText;
  private Boolean heitFirst = Boolean.valueOf(true);
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(LiftAddFamilyInforAct.this, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(LiftAddFamilyInforAct.this, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      Intent localIntent;
      if (localResponseEntity.getMethod().equals("uploadHeadImage"))
      {
        PicoocLoading.dismissDialog();
        localIntent = new Intent(LiftAddFamilyInforAct.this, WriteReferentwoAct.class);
      }
      try
      {
        LiftAddFamilyInforAct.this.cacheRole.setHead_portrait_url(localResponseEntity.getResp().getString("url"));
        localIntent.putExtra("cacheRole", LiftAddFamilyInforAct.this.cacheRole);
        localIntent.putExtra("from", 2);
        LiftAddFamilyInforAct.this.startActivity(localIntent);
        LiftAddFamilyInforAct.this.finish();
        return;
      }
      catch (JSONException localJSONException)
      {
        while (true)
          localJSONException.printStackTrace();
      }
    }
  };
  InputMethodManager imm;
  Boolean isgoright = Boolean.valueOf(true);
  private int keyteybe = 0;
  EditText nickeEdit;
  RelativeLayout relaNickname;
  RelativeLayout relatHeight;
  RelativeLayout relatbithday;
  private List<String> roleNames;
  RadioGroup tab_Radiogroup;
  ImageView touxiangImage;
  RelativeLayout touxiangyinchang;

  private void goNext()
  {
    this.cacheRole.setName(this.nickeEdit.getText().toString());
    this.cacheRole.setHead_portrait_url("");
    this.cacheRole.setSex(this.checksexID);
    this.cacheRole.setTime(System.currentTimeMillis());
    if (this.keyteybe == ModUtils.visitor)
    {
      localIntent1 = new Intent(this, WriteReferentwoAct.class);
      localIntent1.putExtra("cacheRole", this.cacheRole);
      localIntent1.putExtra("from", 3);
      startActivity(localIntent1);
      overridePendingTransition(2130968596, 2130968595);
      this.isgoright = Boolean.valueOf(false);
      finish();
    }
    while (this.keyteybe != ModUtils.FAMLYIN)
    {
      Intent localIntent1;
      return;
    }
    if ((this.headPath != null) && (!"".equals(this.headPath)))
    {
      PicoocLoading.showLoadingDialog(this);
      HttpUtils.uploadFile(this, new File(this.headPath), this.httpHandler);
      return;
    }
    Intent localIntent2 = new Intent(this, WriteReferentwoAct.class);
    localIntent2.putExtra("cacheRole", this.cacheRole);
    localIntent2.putExtra("from", 2);
    startActivity(localIntent2);
    overridePendingTransition(2130968596, 2130968595);
    this.isgoright = Boolean.valueOf(false);
    finish();
  }

  private void isNull()
  {
    if ((this.nickeEdit.getText() == null) || (this.nickeEdit.getText().toString().equals("")))
    {
      PicoocToast.showToast(this, "请输入昵称！");
      return;
    }
    if (this.roleNames.contains(this.nickeEdit.getText().toString()))
    {
      PicoocToast.showToast(this, "该昵称已存在，请修改昵称！");
      return;
    }
    if (this.checksexID == -1)
    {
      PicoocToast.showToast(this, "选择性别！");
      return;
    }
    if ((this.cacheRole.getBirthday() == null) || (this.cacheRole.getBirthday().equals("")))
    {
      PicoocToast.showToast(this, "请选择生日！");
      return;
    }
    if (this.cacheRole.getHeight() <= 10.0F)
    {
      PicoocToast.showToast(this, "请选择身高");
      return;
    }
    this.dilog.showAlerDialog();
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
    if (this.isgoright.booleanValue())
      overridePendingTransition(2130968576, 2130968581);
  }

  public void invit()
  {
    this.anim = new AnimUtils(this);
    this.relaNickname = ((RelativeLayout)findViewById(2131100340));
    this.relatHeight = ((RelativeLayout)findViewById(2131100352));
    this.relatbithday = ((RelativeLayout)findViewById(2131100347));
    this.nickeEdit = ((EditText)findViewById(2131100341));
    this.bithdayText = ((TextView)findViewById(2131100349));
    this.bithdayTextdown = ((TextView)findViewById(2131100350));
    this.heightText = ((TextView)findViewById(2131100354));
    this.HeightLiner = ((LinearLayout)findViewById(2131100353));
    this.BirthdayLiner = ((LinearLayout)findViewById(2131100348));
    this.anim.setoselectHeitListener(this);
    this.touxiangImage = ((ImageView)findViewById(2131100336));
    this.touxiangyinchang = ((RelativeLayout)findViewById(2131100334));
    this.imm = ((InputMethodManager)getSystemService("input_method"));
    this.tab_Radiogroup = ((RadioGroup)findViewById(2131100343));
    this.keyteybe = getIntent().getIntExtra("key", 0);
    if (this.keyteybe == ModUtils.visitor)
      this.touxiangyinchang.setVisibility(8);
    while (true)
    {
      this.tab_Radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
      {
        public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
        {
          switch (paramAnonymousInt)
          {
          default:
            return;
          case 2131100344:
            LiftAddFamilyInforAct.this.checksexID = 1;
            return;
          case 2131100345:
          }
          LiftAddFamilyInforAct.this.checksexID = 0;
        }
      });
      this.app = ((MyApplication)getApplicationContext());
      this.nickeEdit.setTypeface(ModUtils.getTypeface(this));
      this.heightText.setTypeface(ModUtils.getTypeface(this));
      this.bithdayText.setTypeface(ModUtils.getTypeface(this));
      this.bithdayTextdown.setTypeface(ModUtils.getTypeface(this));
      return;
      if (this.keyteybe == ModUtils.FAMLYIN)
        this.touxiangyinchang.setVisibility(0);
    }
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
      this.touxiangImage.setImageBitmap(ModUtils.toRoundBitmap(BitmapFactory.decodeFile(cacheHeadPath)));
      this.headPath = cacheHeadPath;
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onClick(View paramView)
  {
    int i = 1;
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100340:
      this.anim.animRotate(this.relaNickname, this.nickeEdit, "");
      this.relaNickname.setFocusable(false);
      this.relaNickname.setEnabled(false);
      this.relaNickname.setClickable(false);
      return;
    case 2131100347:
      dismissKeyboard();
      this.anim.getPopupWindow(2);
      return;
    case 2131100352:
      dismissKeyboard();
      if (this.checksexID == i);
      while (true)
      {
        this.anim.getPopupWindowHeight(2, i);
        return;
        i = 0;
      }
    case 2131100336:
      dismissKeyboard();
      this.anim.getPopupWindowPhoto(i);
      return;
    case 2131100333:
      isNull();
      return;
    case 2131100331:
    }
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903176);
    invit();
    this.app = ((MyApplication)getApplicationContext());
    this.cacheRole = new RoleBin();
    this.cacheRole.setUser_id(this.app.getCurrentRole().getUser_id());
    List localList = OperationDB.selectAllRoleByUserId(this, this.app.getCurrentRole().getUser_id());
    this.roleNames = new ArrayList();
    Iterator localIterator = localList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        this.dilog = new PicoocAlertDialog("确认提交后，资料中的性别将不能修改。", "修改", "提交", this);
        this.dilog.setoselectHeitListener(this);
        return;
      }
      RoleBin localRoleBin = (RoleBin)localIterator.next();
      this.roleNames.add(localRoleBin.getName());
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
    {
      this.cacheRole.setHeight(Float.parseFloat(paramString1));
      if (this.heitFirst.booleanValue())
      {
        this.anim.animRotate(this.HeightLiner, this.relatHeight, this.heightText, this.heightText, paramString1, null);
        this.heitFirst = Boolean.valueOf(false);
      }
    }
    while (paramInt != 2)
    {
      return;
      this.heightText.setText(paramString1);
      return;
    }
    this.cacheRole.setBirthday(paramString1 + paramString2.replace(".", ""));
    if (this.bithdayFirst.booleanValue())
    {
      this.anim.animRotate(this.BirthdayLiner, this.relatbithday, this.bithdayText, this.bithdayTextdown, paramString1, paramString2);
      this.bithdayFirst = Boolean.valueOf(false);
      return;
    }
    this.bithdayText.setText(paramString1);
    this.bithdayTextdown.setText(paramString2);
  }

  public void selectbirthDay(String paramString)
  {
  }

  public void takePoto()
  {
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    localIntent.putExtra("output", Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp.jpg")));
    startActivityForResult(localIntent, 1);
  }

  public void trueUpdate()
  {
    this.dilog.dismissAlertDialog();
    goNext();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LiftAddFamilyInforAct
 * JD-Core Version:    0.6.2
 */
