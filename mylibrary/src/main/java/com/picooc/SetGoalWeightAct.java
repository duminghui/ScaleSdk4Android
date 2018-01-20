package com.picooc;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.adapter.GalleryAdapter;
import com.picooc.arithmetic.ReportDirect;
import com.picooc.constant.ThemeConstant;
import com.picooc.db.OperationDB;
import com.picooc.domain.BodyFatReport;
import com.picooc.domain.ReportEntity;
import com.picooc.domain.RoleBin;
import com.picooc.internet.AsyncMessageUtils;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.DateUtils;
import com.picooc.utils.NumUtils;
import com.picooc.utils.StringUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.MyGallery;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import com.picooc.widget.picoocProgress.PicoocAlertDialog4FamilyPasswrod;
import com.picooc.widget.picoocProgress.PicoocAlertDialog4GoalWeight;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.achartengine.tools.ModUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class SetGoalWeightAct extends PicoocActivity
  implements AdapterView.OnItemSelectedListener
{
  private static final long DELAY = 500L;
  private static final int MSG_ZOOM_IN = 1;
  private RelativeLayout RelatGllger;
  private TextView TextCurrentWeight;
  private TextView WeightFanWei;
  int addInvitCount = 0;
  private GalleryAdapter adpter;
  private MyApplication app;
  private RoleBin cacheRole;
  PicoocAlertDialog4GoalWeight dialog_goal_Weight;
  PicoocAlertDialog4FamilyPasswrod dilog;
  private MyGallery gallery;
  private float goalWeight;
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
      PicoocToast.showToast(SetGoalWeightAct.this, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      String str = localResponseEntity.getMethod();
      if (str.equals("updataRoleMessage"))
      {
        PicoocLoading.dismissDialog();
        if ((SetGoalWeightAct.this.cacheRole.getGoal_fat() > 0.0F) && ((SetGoalWeightAct.this.cacheRole.getHeight() != SetGoalWeightAct.this.app.getCurrentRole().getHeight()) || (!SetGoalWeightAct.this.cacheRole.getBirthday().equals(SetGoalWeightAct.this.app.getCurrentRole().getBirthday())) || (SetGoalWeightAct.this.cacheRole.getGoal_weight() != SetGoalWeightAct.this.app.getCurrentRole().getGoal_weight())))
          OperationDB.insertToRoleInfos(SetGoalWeightAct.this, SetGoalWeightAct.this.cacheRole);
        if (((SetGoalWeightAct.this.app.getCurrentRole().getWeight_change_target() <= 0.0F) || (SetGoalWeightAct.this.cacheRole.getWeight_change_target() <= 0.0F)) && ((SetGoalWeightAct.this.app.getCurrentRole().getWeight_change_target() >= 0.0F) || (SetGoalWeightAct.this.cacheRole.getWeight_change_target() >= 0.0F)) && ((SetGoalWeightAct.this.app.getCurrentRole().getWeight_change_target() != 0.0F) || (SetGoalWeightAct.this.cacheRole.getWeight_change_target() != 0.0F)))
          SetGoalWeightAct.this.app.clearContinuousWeightingSharedModel();
        SetGoalWeightAct.this.app.clearChartDatas();
        OperationDB.updateRoleDB(SetGoalWeightAct.this, SetGoalWeightAct.this.cacheRole);
        SetGoalWeightAct.this.app.setCurrentRole(SetGoalWeightAct.this.cacheRole);
        localIntent = new Intent();
        localIntent.putExtra("goalWeightChange", true);
        localIntent.setAction("com.picooc.setting.updateRoleMessage");
        SetGoalWeightAct.this.sendBroadcast(localIntent);
        SetGoalWeightAct.this.finish();
      }
      while (!str.equals("verifyPassword"))
      {
        Intent localIntent;
        return;
      }
      try
      {
        if (localResponseEntity.getResp().getInt("is_validate") == 1)
        {
          SetGoalWeightAct.this.updateRoleMessageToServer();
          return;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return;
      }
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(SetGoalWeightAct.this, "您输入的密码不正确！");
    }
  };
  private final float invitrighandlifttcout = 20.0F;
  Boolean isExit = Boolean.valueOf(false);
  Handler mGalleryHandler = new Handler()
  {
    public void dispatchMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 1)
      {
        SetGoalWeightAct.this.goalWeight = Float.parseFloat((String)SetGoalWeightAct.this.weightList.get(paramAnonymousMessage.arg1));
        SetGoalWeightAct.this.textgange.setText(SetGoalWeightAct.this.goalWeight);
        DecimalFormat localDecimalFormat = new DecimalFormat("#.#");
        float f = Math.abs(SetGoalWeightAct.this.realityWeight - SetGoalWeightAct.this.goalWeight);
        SetGoalWeightAct.this.tv_juli_goal.setText("* 距离目标还有" + localDecimalFormat.format(f) + "kg");
      }
    }
  };
  private float markCurrentwith = 0.0F;
  private float normMaxi = 56.299999F;
  private float normMini = 46.099998F;
  private float nowWidth;
  private TextView picc;
  private float realityWeight = 64.599998F;
  private float[] regionArray;
  private int selectIndex;
  private TextView textState;
  private TextView textgange;
  private TextView tv_juli_goal;
  private TextView tv_weight_unit;
  private ObjectAnimator upmator;
  private List<String> weightList;
  private ReportEntity weightReport;

  private void showInputPasswordDialog()
  {
    this.dilog.showAlerDialog(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        String str = SetGoalWeightAct.this.dilog.getInputMessage();
        if (!StringUtils.isPassword(str))
        {
          PicoocToast.showToast(SetGoalWeightAct.this, "请输入正确格式的密码!");
          return;
        }
        PicoocLoading.showLoadingDialog(SetGoalWeightAct.this);
        RequestEntity localRequestEntity = new RequestEntity("verifyPassword", "5.2");
        localRequestEntity.addParam("email", SetGoalWeightAct.this.app.getCurrentRole().getEmail());
        localRequestEntity.addParam("phoneNumber", SetGoalWeightAct.this.app.getCurrentRole().getPhone_no());
        localRequestEntity.addParam("remoteUserId", Long.valueOf(SetGoalWeightAct.this.app.getCurrentRole().getRemote_user_id()));
        localRequestEntity.addParam("password", str);
        HttpUtils.getJson(SetGoalWeightAct.this, localRequestEntity, SetGoalWeightAct.this.httpHandler);
        SetGoalWeightAct.this.dilog.dismissAlertDialog();
      }
    }
    , new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SetGoalWeightAct.this.dilog.dismissAlertDialog();
      }
    });
  }

  private void updateRoleMessageToServer()
  {
    this.cacheRole.setChange_goal_weight_time(System.currentTimeMillis());
    this.cacheRole.setWeight_change_target(this.cacheRole.getGoal_weight() - this.app.getTodayBody().getWeight());
    long l = this.app.getTodayBody().getTime();
    if (DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), l) <= 0L)
    {
      BodyFatReport localBodyFatReport = ReportDirect.calculateIdealBodyFat(this.cacheRole, this.app.getTodayBody(), true);
      this.cacheRole.setGoal_fat(localBodyFatReport.getGoalFatRace());
    }
    while (true)
    {
      PicoocLoading.showLoadingDialog(this);
      AsyncMessageUtils.updateRoleMessage(this, this.cacheRole, this.httpHandler);
      return;
      this.cacheRole.setGoal_fat(0.0F);
    }
  }

  public float calculateLift(float paramFloat)
  {
    return this.markCurrentwith / 3.0F - this.markCurrentwith / 3.0F * (this.normMini - paramFloat) / 20.0F;
  }

  public float calculateLiftmin(float paramFloat)
  {
    return 0.0F;
  }

  public float calculateMiddle(float paramFloat)
  {
    float f1 = paramFloat - this.normMini;
    float f2 = this.normMaxi - this.normMini;
    return f1 * (this.markCurrentwith / 3.0F) / f2 + this.markCurrentwith / 3.0F;
  }

  public float calculateRight(float paramFloat)
  {
    return (paramFloat - this.normMaxi) * (this.markCurrentwith / 3.0F) / 20.0F + 2.0F * (this.markCurrentwith / 3.0F);
  }

  public float calculateRihtMax(float paramFloat)
  {
    return this.markCurrentwith / 3.0F * (paramFloat - this.normMaxi) / (this.realityWeight - this.normMaxi) + 2.0F * (this.markCurrentwith / 3.0F);
  }

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968594, 2130968597);
  }

  public double getDouble(double paramDouble)
  {
    return new BigDecimal(paramDouble).setScale(1, 4).doubleValue();
  }

  public double getDouble2(double paramDouble)
  {
    return new BigDecimal(paramDouble).setScale(2, 4).doubleValue();
  }

  public void initWeightList()
  {
    this.weightList = new ArrayList();
    float f = ReportDirect.calculateIdealWeight(this.cacheRole);
    double d1;
    double d2;
    int i;
    double d3;
    if (this.realityWeight <= 1.5D * f)
    {
      d1 = getDouble(getDouble(f) / 2.0D);
      d2 = d1 + getDouble(f);
      i = 0;
      d3 = d1;
    }
    while (true)
    {
      if (d3 > d2)
      {
        return;
        d1 = 2.0F * f - this.realityWeight;
        d2 = f;
        break;
      }
      this.weightList.add(d3);
      if (d3 == getDouble(this.goalWeight))
        this.selectIndex = i;
      d3 = getDouble(0.1D + d3);
      i++;
    }
  }

  public void invit()
  {
    new ThemeConstant(this).setTheme(findViewById(2131100562));
    Log.i("touch", "进入了~~~");
    this.gallery = ((MyGallery)findViewById(2131100564));
    this.gallery.setCallbackDuringFling(false);
    this.gallery.setOnItemSelectedListener(this);
    this.textgange = ((TextView)findViewById(2131100409));
    this.textgange.setText(this.goalWeight);
    this.tv_juli_goal = ((TextView)findViewById(2131100642));
    this.tv_juli_goal.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.tv_juli_goal.setText("* 距离目标还有" + Math.abs(this.realityWeight - this.goalWeight) + "kg");
    this.RelatGllger = ((RelativeLayout)findViewById(2131100640));
    this.TextCurrentWeight = ((TextView)findViewById(2131100406));
    this.TextCurrentWeight.setText("当前体重为" + NumUtils.roundValue(this.app.getTodayBody().getWeight()) + "kg");
    this.textState = ((TextView)findViewById(2131099730));
    if (this.weightReport.getStateCode() <= 1)
    {
      this.textState.setBackgroundResource(2130838197);
      this.textState.setText(" 偏低 ");
    }
    while (true)
    {
      this.regionArray = this.weightReport.getRegionArray();
      this.picc = ((TextView)findViewById(2131100637));
      this.WeightFanWei = ((TextView)findViewById(2131100638));
      String str = "您的标准体重区间为" + NumUtils.roundValue(this.regionArray[0]) + "kg~" + NumUtils.roundValue(this.regionArray[1]) + "kg";
      this.WeightFanWei.setText(str);
      this.normMini = this.regionArray[0];
      this.normMaxi = this.regionArray[1];
      this.tv_weight_unit = ((TextView)findViewById(2131100641));
      this.tv_weight_unit.setTypeface(TypefaceUtils.getTypeface(this, null));
      this.textgange.setTypeface(ModUtils.getTypeface(this));
      this.TextCurrentWeight.setTypeface(ModUtils.getTypeface(this));
      this.picc.setTypeface(ModUtils.getTypeface(this));
      this.WeightFanWei.setTypeface(ModUtils.getTypeface(this));
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      this.nowWidth = localDisplayMetrics.widthPixels;
      this.markCurrentwith = (this.nowWidth - ModUtils.dip2px(this, 32.0F));
      this.RelatGllger.setVisibility(0);
      if (this.cacheRole.getFamily_type() == 1)
        this.dilog = new PicoocAlertDialog4FamilyPasswrod("为确保测量数据的准确性，请您不要随意修改家人资料", "取消", "确定", this);
      return;
      if (this.weightReport.getStateCode() >= 3)
      {
        this.textState.setBackgroundResource(2130838184);
        this.textState.setText(" 超标 ");
      }
      else
      {
        this.textState.setBackgroundResource(2130838179);
        this.textState.setText(" 标准 ");
      }
    }
  }

  public void invitAdapter()
  {
    initWeightList();
    this.adpter = new GalleryAdapter(this, this.weightList);
    this.gallery.setAdapter(this.adpter);
    this.gallery.setSelection(this.selectIndex);
    this.gallery.setOnItemSelectedListener(this);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 101)
      finish();
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100359:
      finish();
      return;
    case 2131099828:
    }
    float f = 0.8F * this.regionArray[0];
    if (this.goalWeight < f)
    {
      showWeightLowDialog();
      return;
    }
    this.cacheRole.setGoal_weight(this.goalWeight);
    if (this.cacheRole.getGoal_weight() != this.app.getCurrentRole().getGoal_weight())
    {
      this.cacheRole.setWeight_change_target(this.cacheRole.getGoal_weight() - this.app.getTodayBody().getWeight());
      if (this.cacheRole.getFamily_type() == 1)
      {
        showInputPasswordDialog();
        return;
      }
      updateRoleMessageToServer();
      return;
    }
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903219);
    this.app = ((MyApplication)getApplicationContext());
    this.cacheRole = new RoleBin();
    this.cacheRole = this.app.getCurrentRole();
    this.realityWeight = this.app.getTodayBody().getWeight();
    this.goalWeight = this.cacheRole.getGoal_weight();
    this.weightReport = ReportDirect.judgeWeightByRole(this.cacheRole, this.realityWeight);
    invit();
    invitAdapter();
  }

  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this.mGalleryHandler.removeCallbacksAndMessages(paramAdapterView);
    Message localMessage = Message.obtain(this.mGalleryHandler, 1, paramAdapterView);
    localMessage.arg1 = paramInt;
    this.mGalleryHandler.sendMessageDelayed(localMessage, 500L);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onNothingSelected(AdapterView<?> paramAdapterView)
  {
  }

  public void showWeightLowDialog()
  {
    this.dialog_goal_Weight = new PicoocAlertDialog4GoalWeight(this);
    this.dialog_goal_Weight.showAlerDialog_Goal_Weight();
  }

  public void translateRelayWeight(Object paramObject, float paramFloat)
  {
    if ((paramFloat >= this.normMaxi) && (paramFloat <= 20.0F + this.normMaxi))
      translateText(paramObject, calculateRight(paramFloat));
    do
    {
      return;
      if (paramFloat > 20.0F + this.normMaxi)
      {
        translateText(paramObject, this.markCurrentwith);
        return;
      }
      if ((paramFloat >= this.normMini) && (paramFloat < this.normMaxi))
      {
        translateText(paramObject, calculateMiddle(paramFloat));
        return;
      }
      if ((paramFloat >= this.normMini - 20.0F) && (paramFloat < this.normMini))
      {
        translateText(paramObject, calculateLift(paramFloat));
        return;
      }
    }
    while ((paramFloat <= 0.0F) || (paramFloat >= this.normMini - 20.0F));
    translateText(paramObject, calculateLiftmin(paramFloat));
  }

  public void translateText(Object paramObject, float paramFloat)
  {
    this.upmator = ObjectAnimator.ofFloat(paramObject, "translationX", new float[] { 0.0F, paramFloat });
    this.upmator.setDuration(1L);
    this.upmator.start();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SetGoalWeightAct
 * JD-Core Version:    0.6.2
 */
