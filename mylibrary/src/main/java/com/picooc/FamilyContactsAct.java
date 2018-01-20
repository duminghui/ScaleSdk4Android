package com.picooc;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.adapter.FamilyContactsAdapter;
import com.picooc.db.OperationDB;
import com.picooc.domain.FamilyContactDetailBin;
import com.picooc.domain.FamilyContactsBin;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.PicoocParser;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.StringUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import com.picooc.widget.picoocProgress.PicoocAlertDialog;
import java.util.List;
import java.util.Map;
import org.achartengine.tools.ModUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FamilyContactsAct extends PicoocActivity
{
  public static final int fromBootom = 2;
  public static final int fromList = 1;
  public static final int requestCode = 1;
  AutoCompleteTextView AutoText;
  private ListView Autolist;
  private AnimUtils anima;
  private MyApplication app;
  private List<FamilyContactsBin> autoList = null;
  private List<FamilyContactsBin> binList = null;
  private List<FamilyContactsBin> contractBin;
  private Cursor cursor;
  private int during = 400;
  private EditText et_phone_email;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, final String paramAnonymousString)
    {
      new Handler(FamilyContactsAct.this.getMainLooper()).post(new Runnable()
      {
        public void run()
        {
          PicoocLoading.dismissDialog();
          PicoocToast.showToast(FamilyContactsAct.this, paramAnonymousString);
        }
      });
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      final ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      final String str = localResponseEntity.getMethod();
      new Handler(FamilyContactsAct.this.getMainLooper()).post(new Runnable()
      {
        public void run()
        {
          PicoocLoading.dismissDialog();
          if (str.equals("uploadMyPhoneNumbers"))
            FamilyContactsAct.this.finish();
          PicoocToast.showToast(FamilyContactsAct.this, localResponseEntity.getMessage());
          FamilyContactsAct.this.et_phone_email.setText("");
        }
      });
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      String str = localResponseEntity.getMethod();
      if (str.equals("uploadMyPhoneNumbers"));
      while (!str.equals("getMasterRoleByEmailOrPhoneNumber"))
        try
        {
          JSONArray localJSONArray = localResponseEntity.getResp().getJSONArray("bindingPhoneUsers");
          Log.i("http", "list==" + PicoocParser.parserDownLoadPhoneNub(localJSONArray));
          List localList = ModUtils.getData(FamilyContactsAct.this.contractBin, PicoocParser.parserDownLoadPhoneNub(localJSONArray), FamilyContactsAct.this);
          FamilyContactsAct.this.showAdapter(localList);
          MyApplication.isUploadPhoneNu = false;
          SharedPreferenceUtils.saveIsFirstUpLoadPhoneNb(FamilyContactsAct.this);
          return;
        }
        catch (JSONException localJSONException2)
        {
          PicoocLoading.dismissDialog();
          PicoocToast.showToast(FamilyContactsAct.this, "访问网络失败");
          localJSONException2.printStackTrace();
          return;
        }
      PicoocLoading.dismissDialog();
      JSONObject localJSONObject = localResponseEntity.getResp();
      FamilyContactDetailBin localFamilyContactDetailBin = new FamilyContactDetailBin();
      try
      {
        localFamilyContactDetailBin.setName(localJSONObject.getString("name"));
        localFamilyContactDetailBin.setBirthday(localJSONObject.getString("birthday"));
        localFamilyContactDetailBin.setEmail(localJSONObject.getString("email"));
        localFamilyContactDetailBin.setGender(localJSONObject.getString("gender"));
        localFamilyContactDetailBin.setHeadUrl(localJSONObject.getString("headUrl"));
        localFamilyContactDetailBin.setPhone(localJSONObject.getString("phone"));
        localFamilyContactDetailBin.setRemoteUserId(localJSONObject.getString("remoteUserId"));
        localFamilyContactDetailBin.setSearchIsPhoneNo(FamilyContactsAct.this.isPhone);
        Intent localIntent = new Intent(FamilyContactsAct.this, FamilyDetailInfo.class);
        localIntent.putExtra("fromeKey", 2);
        localIntent.putExtra("bean", localFamilyContactDetailBin);
        FamilyContactsAct.this.startActivityForResult(localIntent, 1);
        FamilyContactsAct.this.overridePendingTransition(2130968596, 2130968595);
        return;
      }
      catch (JSONException localJSONException1)
      {
        localJSONException1.printStackTrace();
      }
    }
  };
  InputMethodManager imm;
  private Intent intent;
  boolean isPhone;
  private ImageView iv_left;
  private ImageView iv_right;
  int key = 0;
  private View line1;
  private ListView listView;
  private LinearLayout ll_open_contacts;
  RelativeLayout rl_bottom;
  RelativeLayout rl_shade;
  RelativeLayout rl_shade2;
  private RelativeLayout rl_title;
  private RelativeLayout rl_title2;
  Editable s2;
  private int screanWidth = 0;
  private TextView tv_input_phone_email;
  private TextView tv_not_open_contacts;
  private WindowManager wm;

  private void initFocusChange()
  {
    this.et_phone_email.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          FamilyContactsAct.this.et_phone_email.setHint("");
          FamilyContactsAct.this.tv_input_phone_email.setVisibility(0);
          FamilyContactsAct.this.rl_shade2.setVisibility(0);
          return;
        }
        FamilyContactsAct.this.tv_input_phone_email.setVisibility(8);
        FamilyContactsAct.this.et_phone_email.setHint(FamilyContactsAct.this.getString(2131361939));
        FamilyContactsAct.this.et_phone_email.setText("");
        FamilyContactsAct.this.rl_shade2.setVisibility(8);
      }
    });
  }

  private void startAnima1(int paramInt)
  {
    AnimUtils.LiftandRightMove(this.rl_title, 0, -this.screanWidth, paramInt);
  }

  private void startAnima2(int paramInt)
  {
    AnimUtils.LiftandRightMove(this.rl_title2, this.screanWidth, 0, paramInt);
  }

  private void startAnima3(int paramInt)
  {
    AnimUtils.LiftandRightMove(this.rl_title, -this.screanWidth, 0, paramInt);
  }

  private void startAnima4(int paramInt)
  {
    AnimUtils.LiftandRightMove(this.rl_title2, 0, this.screanWidth, paramInt);
  }

  public void Autoinvit()
  {
    this.AutoText.setThreshold(1);
    this.AutoText.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        Log.i("ibokan", "改变之后：" + paramAnonymousEditable);
        FamilyContactsAct.this.s2 = paramAnonymousEditable;
        if (paramAnonymousEditable == null)
        {
          Log.i("qianmo2", "s!=null s===" + paramAnonymousEditable);
          FamilyContactsAct.this.rl_shade.setVisibility(0);
          FamilyContactsAct.this.listView.setVisibility(0);
          FamilyContactsAct.this.Autolist.setVisibility(8);
          return;
        }
        if (paramAnonymousEditable.toString().equals(""))
        {
          FamilyContactsAct.this.rl_shade.setVisibility(0);
          FamilyContactsAct.this.Autolist.setVisibility(8);
          FamilyContactsAct.this.listView.setVisibility(0);
          return;
        }
        FamilyContactsAct.this.rl_shade.setVisibility(8);
        FamilyContactsAct.this.autoList = OperationDB.searchPhone_book(FamilyContactsAct.this, paramAnonymousEditable);
        FamilyContactsAct.this.Autolist.setAdapter(new FamilyContactsAdapter(FamilyContactsAct.this, FamilyContactsAct.this.autoList));
        FamilyContactsAct.this.Autolist.setVisibility(0);
        FamilyContactsAct.this.listView.setVisibility(8);
        Log.i("ibokan", "listview is show");
      }

      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        Log.i("ibokan", "改变之前：" + paramAnonymousCharSequence);
      }

      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        Log.i("ibokan", "正在改变：" + paramAnonymousCharSequence);
      }
    });
  }

  public void bootomGoSearch()
  {
    String str = this.et_phone_email.getText().toString().trim();
    this.isPhone = ModUtils.isNumeric(str);
    if (str.equals(""))
    {
      PicoocToast.showToast(this, "请填写手机或邮箱");
      return;
    }
    boolean bool;
    RequestEntity localRequestEntity;
    if (this.isPhone)
    {
      bool = ModUtils.isMobileNO(str);
      if (!bool)
        break label130;
      PicoocLoading.showLoadingDialog(this);
      localRequestEntity = new RequestEntity("getMasterRoleByEmailOrPhoneNumber", "5.2");
      if (!this.isPhone)
        break label112;
      localRequestEntity.addParam("email", "");
      localRequestEntity.addParam("phoneNumber", str);
    }
    while (true)
    {
      HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
      return;
      bool = StringUtils.isEmail(str);
      break;
      label112: localRequestEntity.addParam("email", str);
      localRequestEntity.addParam("phoneNumber", "");
    }
    label130: if (this.isPhone)
      PicoocToast.showToast(this, "手机号格式错误!");
    while (true)
    {
      this.et_phone_email.setText("");
      return;
      PicoocToast.showToast(this, "邮箱格式错误!");
    }
  }

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968576, 2130968581);
  }

  public void goNext()
  {
    Log.i("picooc", "PicoocLoading.showLoadingDialog(this)");
    PicoocLoading.showLoadingDialog(this);
    new myThread(null).start();
  }

  public void goToNextPage(List<FamilyContactsBin> paramList, int paramInt)
  {
    FamilyContactsBin localFamilyContactsBin;
    if (paramList != null)
    {
      localFamilyContactsBin = (FamilyContactsBin)paramList.get(paramInt);
      if (!localFamilyContactsBin.getIsAlreadMyRole())
      {
        if ((localFamilyContactsBin.getUserID() == null) || (localFamilyContactsBin.getUserID().equals("")) || (localFamilyContactsBin.getUserID().equals("null")))
          break label146;
        this.intent = new Intent(this, FamilyDetailInfo.class);
        this.intent.putExtra("userID", localFamilyContactsBin.getUserID());
        this.intent.putExtra("phone", localFamilyContactsBin.getPhoneNumer());
        this.intent.putExtra("phoneName", localFamilyContactsBin.getNickName());
        this.intent.putExtra("fromeKey", 1);
        startActivityForResult(this.intent, 1);
        overridePendingTransition(2130968596, 2130968595);
      }
    }
    return;
    label146: Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.putExtra("address", localFamilyContactsBin.getPhoneNumer());
    localIntent.putExtra("sms_body", "我正在PICOOC app里添加您为家庭成员，请您在PICOOC app的\"设定-账号管理\"里绑定手机号,我就可以方便地找到您啦.点击打开PICOOC http://t.n/8FVyZ7A");
    localIntent.setType("vnd.android-dir/mms-sms");
    startActivity(localIntent);
    overridePendingTransition(2130968596, 2130968595);
  }

  public void invitData()
  {
    if (this.key == ModUtils.YANZHENGMA)
      if (SharedPreferenceUtils.getIsFirstUpLoadPhoneNb(this))
        showTiShiDialog();
    while ((this.key != ModUtils.FAMLYIN) || (SharedPreferenceUtils.getIsFirstUpLoadPhoneNb(this)))
    {
      return;
      goNext();
      return;
    }
    if (MyApplication.isUploadPhoneNu)
    {
      goNext();
      return;
    }
    PicoocLoading.showLoadingDialog(this);
    new Handler().post(new Runnable()
    {
      public void run()
      {
        FamilyContactsAct.this.showAdapter(OperationDB.selectPhone_book(FamilyContactsAct.this));
      }
    });
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramIntent != null)
    {
      Log.i("picooc", "email===" + paramIntent.getStringExtra("email"));
      if (paramInt2 == 1)
        finish();
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131099838:
      PicoocLoading.showLoadingDialog(this);
      this.ll_open_contacts.setVisibility(8);
      this.rl_title2.setVisibility(0);
      this.listView.setVisibility(0);
      this.iv_right.setVisibility(0);
      this.line1.setVisibility(0);
      this.iv_left.setBackgroundResource(2130838389);
      PicoocLoading.showLoadingDialog(this);
      goNext();
      return;
    case 2131099827:
      finish();
      return;
    case 2131099828:
      this.rl_title2.setAlpha(1.0F);
      this.rl_title2.setAlpha(1.0F);
      this.rl_shade.setVisibility(0);
      startAnima1(this.during);
      startAnima2(this.during);
      return;
    case 2131099824:
      this.rl_shade.setVisibility(8);
      startAnima3(this.during);
      startAnima4(this.during);
      this.listView.setVisibility(0);
      this.Autolist.setVisibility(8);
      return;
    case 2131099832:
      bootomGoSearch();
      return;
    case 2131099840:
      this.rl_shade.setVisibility(8);
      startAnima3(this.during);
      startAnima4(this.during);
      this.listView.setVisibility(0);
      this.Autolist.setVisibility(8);
      return;
    case 2131099839:
    }
    this.rl_shade2.setVisibility(4);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903073);
    this.tv_input_phone_email = ((TextView)findViewById(2131099830));
    this.imm = ((InputMethodManager)getSystemService("input_method"));
    this.app = ((MyApplication)getApplicationContext());
    this.key = getIntent().getIntExtra("key", 0);
    this.rl_shade = ((RelativeLayout)findViewById(2131099840));
    this.rl_shade2 = ((RelativeLayout)findViewById(2131099839));
    this.rl_bottom = ((RelativeLayout)findViewById(2131099829));
    this.tv_not_open_contacts = ((TextView)findViewById(2131099837));
    this.tv_not_open_contacts.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.listView = ((ListView)findViewById(2131099834));
    this.iv_right = ((ImageView)findViewById(2131099828));
    this.iv_left = ((ImageView)findViewById(2131099827));
    this.line1 = findViewById(2131099833);
    this.ll_open_contacts = ((LinearLayout)findViewById(2131099836));
    this.rl_title = ((RelativeLayout)findViewById(2131099826));
    this.rl_title2 = ((RelativeLayout)findViewById(2131099822));
    this.et_phone_email = ((EditText)findViewById(2131099831));
    this.et_phone_email.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.Autolist = ((ListView)findViewById(2131099835));
    this.AutoText = ((AutoCompleteTextView)findViewById(2131099825));
    this.AutoText.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.rl_title2.setVisibility(4);
    this.rl_bottom.setFocusable(true);
    this.rl_bottom.setFocusableInTouchMode(true);
    this.rl_bottom.requestFocus();
    if (!SharedPreferenceUtils.getIsFirstUpLoadPhoneNb(this))
    {
      this.ll_open_contacts.setVisibility(8);
      this.rl_title2.setVisibility(0);
      this.listView.setVisibility(0);
      this.iv_right.setVisibility(0);
      this.line1.setVisibility(0);
      this.iv_left.setBackgroundResource(2130838389);
    }
    while (true)
    {
      this.rl_shade2.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          FamilyContactsAct.this.rl_bottom.setFocusable(true);
          FamilyContactsAct.this.rl_bottom.setFocusableInTouchMode(true);
          FamilyContactsAct.this.rl_bottom.requestFocus();
          FamilyContactsAct.this.imm.hideSoftInputFromWindow(FamilyContactsAct.this.et_phone_email.getWindowToken(), 0);
          return false;
        }
      });
      this.rl_shade.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          FamilyContactsAct.this.imm.hideSoftInputFromWindow(FamilyContactsAct.this.AutoText.getWindowToken(), 0);
          return false;
        }
      });
      Autoinvit();
      this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          FamilyContactsAct.this.goToNextPage(FamilyContactsAct.this.binList, paramAnonymousInt);
        }
      });
      this.Autolist.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          FamilyContactsAct.this.goToNextPage(FamilyContactsAct.this.autoList, paramAnonymousInt);
        }
      });
      this.wm = ((WindowManager)getSystemService("window"));
      this.screanWidth = this.wm.getDefaultDisplay().getWidth();
      initFocusChange();
      this.rl_title2.setAlpha(0.0F);
      Log.i("picooc", "key==" + this.key);
      invitData();
      return;
      if (this.key != ModUtils.YANZHENGMA)
        showTiShiDialog();
    }
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (this.rl_shade2.getVisibility() != 0)
        break label50;
      this.rl_shade2.setVisibility(8);
      this.rl_bottom.setFocusable(true);
      this.rl_bottom.setFocusableInTouchMode(true);
      this.rl_bottom.requestFocus();
    }
    while (true)
    {
      return false;
      label50: if (this.rl_shade.getVisibility() == 0)
      {
        this.rl_shade.setVisibility(8);
        this.rl_bottom.setFocusable(true);
        this.rl_bottom.setFocusableInTouchMode(true);
        this.rl_bottom.requestFocus();
      }
      else
      {
        finish();
        overridePendingTransition(2130968576, 2130968581);
      }
    }
  }

  protected void onResume()
  {
    super.onResume();
  }

  public void showAdapter(List<FamilyContactsBin> paramList)
  {
    this.binList = paramList;
    FamilyContactsAdapter localFamilyContactsAdapter = new FamilyContactsAdapter(this, paramList);
    this.listView.setAdapter(localFamilyContactsAdapter);
    this.ll_open_contacts.setVisibility(8);
    this.listView.setVisibility(0);
    this.iv_right.setVisibility(0);
    this.line1.setVisibility(0);
    this.iv_left.setBackgroundResource(2130838389);
    localFamilyContactsAdapter.notifyDataSetChanged();
    PicoocLoading.dismissDialog();
  }

  public void showTiShiDialog()
  {
    final PicoocAlertDialog localPicoocAlertDialog = new PicoocAlertDialog("开启通讯录功能,您可以找到通讯录中实用PICOOC app的家人和朋友，我们不会泄露您的隐私", "暂不开启", "马上开启", this);
    localPicoocAlertDialog.showAlerDialogTwo(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localPicoocAlertDialog.dismissAlertDialog();
        FamilyContactsAct.this.goNext();
      }
    }
    , new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localPicoocAlertDialog.dismissAlertDialog();
        FamilyContactsAct.this.showTishi();
      }
    });
  }

  public void showTishi()
  {
    this.ll_open_contacts.setVisibility(0);
    this.listView.setVisibility(8);
    this.iv_right.setVisibility(8);
    this.line1.setVisibility(8);
  }

  private class myThread extends Thread
  {
    private myThread()
    {
    }

    public void run()
    {
      super.run();
      Map localMap = ModUtils.getPhoneContacts(FamilyContactsAct.this);
      List localList = (List)localMap.get("list");
      FamilyContactsAct.this.contractBin = ((List)localMap.get("listBin"));
      RequestEntity localRequestEntity = new RequestEntity("uploadMyPhoneNumbers", "5.2");
      JSONArray localJSONArray = new JSONArray();
      for (int i = 0; ; i++)
      {
        if (i >= localList.size())
        {
          localRequestEntity.addParam("myUserID", Long.valueOf(FamilyContactsAct.this.app.getCurrentUser().getUser_id()));
          localRequestEntity.addParam("phoneNumbers", localJSONArray);
          HttpUtils.getJson(FamilyContactsAct.this, localRequestEntity, FamilyContactsAct.this.httpHandler);
          return;
        }
        localJSONArray.put((String)localList.get(i));
      }
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     FamilyContactsAct
 * JD-Core Version:    0.6.2
 */
