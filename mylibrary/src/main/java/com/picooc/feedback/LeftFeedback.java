package com.picooc.feedback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.picooc.MyApplication;
import com.picooc.PicoocActivity;
import com.picooc.constant.ThemeConstant;
import com.picooc.db.OperationDB;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.DateUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;

import java.util.ArrayList;
import org.json.JSONObject;

public class LeftFeedback extends PicoocActivity
{
  public static final String REFRESH_ACTION = "com.picooc.feedback.refresh.Action";
  private LinearLayout linearLayout_bg;
  private ArrayList<FeedBackItem> mCommitMapList = new ArrayList();
  private FeedBackAdapter mFeedBackAdapter;
  private EditText mFeedBackInput;
  private LayoutInflater mInflater;
  private ListView mListView;
  private Button mSend;
  private FeedBackBroadcastReceiver receiver = new FeedBackBroadcastReceiver();
  private View.OnClickListener sendClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      String str = LeftFeedback.this.mFeedBackInput.getText().toString();
      if (!str.equals(""))
      {
        FeedBackItem localFeedBackItem = LeftFeedback.this.getFeedBackItem(str);
        localFeedBackItem.uploadState = 1;
        LeftFeedback.this.mFeedBackAdapter.addItem(localFeedBackItem);
        LeftFeedback.this.mFeedBackInput.setText("");
        LeftFeedback.this.doRequestUpLoad(localFeedBackItem);
        return;
      }
      PicoocToast.showToast(LeftFeedback.this, "请填写内容!");
    }
  };
  private JsonHttpResponseHandler syncServerHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(LeftFeedback.this, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      PicoocLoading.dismissDialog();
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      Log.i("http", "成功了:" + localResponseEntity.toString());
      if (localResponseEntity.getMethod().equals("getFeedback"))
      {
        System.out.println(localResponseEntity.getResp());
        ArrayList localArrayList = LeftFeedback.this.jsonParser(localResponseEntity.getResp());
        System.out.println("ffeedBackItem =" + localArrayList);
        System.out.println("feedBackItem.size() =" + localArrayList.size());
        if ((localArrayList != null) && (localArrayList.size() > 0))
        {
          System.out.println("feedBackItem.size() =" + localArrayList.size());
          LeftFeedback.this.mFeedBackAdapter.syncDb(localArrayList);
        }
      }
    }
  };
  private ThemeConstant themeConstant;
  private JsonHttpResponseHandler upLoadHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      LeftFeedback.this.handleUpload(false);
      System.out.println("content =" + paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      LeftFeedback.this.handleUpload(false);
      System.out.println("errorResponse =" + paramAnonymousJSONObject);
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      if (localResponseEntity.getMethod().equals("commitFeedBack"))
      {
        Log.d("version", localResponseEntity.getMessage());
        LeftFeedback.this.handleUpload(true);
      }
    }
  };

  private FeedBackItem getFeedBackItem(String paramString)
  {
    FeedBackItem localFeedBackItem = new FeedBackItem();
    localFeedBackItem.userID = ((MyApplication)getApplicationContext()).getCurrentUser().getUser_id();
    localFeedBackItem.fromWhere = 1;
    localFeedBackItem.mes = paramString;
    localFeedBackItem.time = System.currentTimeMillis();
    return localFeedBackItem;
  }

  private void handleUpload(boolean paramBoolean)
  {
    System.out.println("handleUpload  ===commitOk " + paramBoolean + " mCommitMapList.size() =" + this.mCommitMapList.size());
    FeedBackItem localFeedBackItem;
    int i;
    if (this.mCommitMapList.size() > 0)
    {
      localFeedBackItem = (FeedBackItem)this.mCommitMapList.get(0);
      if (!paramBoolean)
        break label121;
      i = 1;
      localFeedBackItem.serverID = i;
      OperationDB.insertFBMesDB(this, localFeedBackItem);
      if (!paramBoolean)
        break label126;
    }
    label121: label126: for (int j = 2; ; j = 3)
    {
      localFeedBackItem.uploadState = j;
      this.mFeedBackAdapter.updateFeedBackItem(localFeedBackItem);
      this.mCommitMapList.remove(0);
      if (paramBoolean)
        this.mFeedBackAdapter.addTemp(2000L);
      return;
      i = 0;
      break;
    }
  }

  // ERROR //
  private ArrayList<FeedBackItem> jsonParser(JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_1
    //   3: ldc 204
    //   5: invokevirtual 209	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   8: checkcast 211	org/json/JSONArray
    //   11: astore 5
    //   13: new 38	java/util/ArrayList
    //   16: dup
    //   17: aload 5
    //   19: invokevirtual 214	org/json/JSONArray:length	()I
    //   22: invokespecial 217	java/util/ArrayList:<init>	(I)V
    //   25: astore 6
    //   27: iconst_0
    //   28: istore 7
    //   30: iload 7
    //   32: aload 5
    //   34: invokevirtual 214	org/json/JSONArray:length	()I
    //   37: if_icmplt +6 -> 43
    //   40: aload 6
    //   42: areturn
    //   43: aload 5
    //   45: iload 7
    //   47: invokevirtual 218	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   50: checkcast 206	org/json/JSONObject
    //   53: astore 8
    //   55: new 95	com/picooc/feedback/FeedBackItem
    //   58: dup
    //   59: invokespecial 96	com/picooc/feedback/FeedBackItem:<init>	()V
    //   62: astore 9
    //   64: aload 9
    //   66: aload 8
    //   68: ldc 220
    //   70: invokevirtual 224	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   73: putfield 123	com/picooc/feedback/FeedBackItem:mes	Ljava/lang/String;
    //   76: aload 9
    //   78: aload 8
    //   80: ldc 226
    //   82: invokevirtual 230	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   85: putfield 232	com/picooc/feedback/FeedBackItem:id	I
    //   88: aload 9
    //   90: aload 8
    //   92: ldc 233
    //   94: invokevirtual 224	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   97: ldc 235
    //   99: invokestatic 241	com/picooc/utils/DateUtils:changeFormatTimeToTimeStamp	(Ljava/lang/String;Ljava/lang/String;)J
    //   102: putfield 131	com/picooc/feedback/FeedBackItem:time	J
    //   105: getstatic 135	java/lang/System:out	Ljava/io/PrintStream;
    //   108: aload 9
    //   110: getfield 131	com/picooc/feedback/FeedBackItem:time	J
    //   113: invokevirtual 243	java/io/PrintStream:println	(J)V
    //   116: aload 9
    //   118: aload 8
    //   120: ldc 245
    //   122: invokevirtual 249	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   125: putfield 116	com/picooc/feedback/FeedBackItem:userID	J
    //   128: aload 9
    //   130: aload 8
    //   132: ldc 251
    //   134: invokevirtual 230	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   137: putfield 120	com/picooc/feedback/FeedBackItem:fromWhere	I
    //   140: aload 6
    //   142: aload 9
    //   144: invokevirtual 255	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   147: pop
    //   148: iinc 7 1
    //   151: goto -121 -> 30
    //   154: astore 4
    //   156: aload 4
    //   158: invokevirtual 258	org/json/JSONException:printStackTrace	()V
    //   161: getstatic 135	java/lang/System:out	Ljava/io/PrintStream;
    //   164: aload 4
    //   166: invokevirtual 261	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   169: invokevirtual 264	java/io/PrintStream:print	(Ljava/lang/String;)V
    //   172: aload_2
    //   173: areturn
    //   174: astore_3
    //   175: aload_3
    //   176: invokevirtual 265	java/text/ParseException:printStackTrace	()V
    //   179: aload_2
    //   180: areturn
    //   181: astore_3
    //   182: aload 6
    //   184: astore_2
    //   185: goto -10 -> 175
    //   188: astore 4
    //   190: aload 6
    //   192: astore_2
    //   193: goto -37 -> 156
    //
    // Exception table:
    //   from	to	target	type
    //   2	27	154	org/json/JSONException
    //   2	27	174	java/text/ParseException
    //   30	40	181	java/text/ParseException
    //   43	148	181	java/text/ParseException
    //   30	40	188	org/json/JSONException
    //   43	148	188	org/json/JSONException
  }

  private void setListViewHeader(ListView paramListView)
  {
    View localView = this.mInflater.inflate(2130903075, null);
    TextView localTextView1 = (TextView)localView.findViewById(2131099846);
    localTextView1.setTypeface(TypefaceUtils.getTypeface(this, null));
    TextView localTextView2 = (TextView)localView.findViewById(2131099847);
    localTextView2.setTypeface(TypefaceUtils.getTypeface(this, null));
    localTextView1.setText(DateUtils.getLongTimeToDateTime(System.currentTimeMillis()));
    localTextView2.setText(getString(2131361945));
    localTextView2.setBackgroundResource(2130837731);
    paramListView.addHeaderView(localView);
  }

  protected void doRequestGetAll()
  {
    System.out.println("doRequestGetAll----------------------------");
    PicoocLoading.showLoadingDialog(this);
    RequestEntity localRequestEntity = new RequestEntity("getFeedback", null);
    long l1 = ((MyApplication)getApplicationContext()).getCurrentUser().getUser_id();
    long l2 = OperationDB.getLastFeedBackSyncTime(this, l1);
    System.out.println("doRequestGetAll() server_time/1000  =" + l2 / 1000L);
    localRequestEntity.addParam("userID", Long.valueOf(l1));
    localRequestEntity.addParam("server_time", Long.valueOf(l2 / 1000L));
    System.out.println(localRequestEntity.toJson(this));
    HttpUtils.getJson(this, localRequestEntity, this.syncServerHandler);
  }

  protected void doRequestUpLoad(FeedBackItem paramFeedBackItem)
  {
    System.out.println("doRequestUpLoad----------------------------");
    this.mCommitMapList.add(paramFeedBackItem);
    RequestEntity localRequestEntity = new RequestEntity("commitFeedBack", null);
    MyApplication localMyApplication = (MyApplication)getApplicationContext();
    localRequestEntity.addParam("message", paramFeedBackItem.mes);
    localRequestEntity.addParam("userID", Long.valueOf(localMyApplication.getCurrentUser().getUser_id()));
    localRequestEntity.addParam("localId", Integer.valueOf(1));
    localRequestEntity.addParam("os", Integer.valueOf(1));
    localRequestEntity.addParam("time", Long.valueOf(paramFeedBackItem.time));
    System.out.println(localRequestEntity.toJson(this));
    HttpUtils.getJson(this, localRequestEntity, this.upLoadHandler);
  }

  public void invit()
  {
    this.linearLayout_bg = ((LinearLayout)findViewById(2131099739));
    this.themeConstant = new ThemeConstant(this);
    this.themeConstant.setTheme(this.linearLayout_bg);
    this.mSend = ((Button)findViewById(2131100434));
    this.mSend.setOnClickListener(this.sendClickListener);
    this.mFeedBackInput = ((EditText)findViewById(2131100433));
    this.mFeedBackInput.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.mFeedBackInput.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        if (paramAnonymousEditable.toString().length() > 0)
        {
          LeftFeedback.this.mSend.setBackgroundResource(2130837729);
          return;
        }
        LeftFeedback.this.mSend.setBackgroundResource(2130837730);
      }

      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }
    });
    this.mListView = ((ListView)findViewById(2131100432));
    setListViewHeader(this.mListView);
    this.mFeedBackAdapter = new FeedBackAdapter(this, this.mListView);
    this.mFeedBackAdapter.initFromDB();
    this.mListView.setAdapter(this.mFeedBackAdapter);
    doRequestGetAll();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903184);
    this.mInflater = LayoutInflater.from(this);
    this.mCommitMapList.clear();
    invit();
    IntentFilter localIntentFilter = new IntentFilter("com.picooc.feedback.refresh.Action");
    registerReceiver(this.receiver, localIntentFilter);
  }

  public void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.receiver);
  }

  public class FeedBackBroadcastReceiver extends BroadcastReceiver
  {
    public FeedBackBroadcastReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      LeftFeedback.this.doRequestGetAll();
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LeftFeedback
 * JD-Core Version:    0.6.2
 */
