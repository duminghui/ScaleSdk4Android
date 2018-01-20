package com.picooc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.util.Log;
import com.baidu.frontia.FrontiaApplication;
import com.picooc.db.OperationDB;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.EveryMeEntity;
import com.picooc.domain.RoleBin;
import com.picooc.domain.UserBin;
import com.picooc.exception.CrashHandler;
import com.picooc.fragment.LatinChartFragment;
import com.picooc.fragment.LatinContentFragment;
import com.picooc.fragment.LatinMainFragment;
import com.picooc.internet.AsyncMessageUtils;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.model.ContinuousWeightingSharedModel;
import com.picooc.model.LatinMonthData;
import com.picooc.model.LatinSeasonData;
import com.picooc.model.LatinWeekData;
import com.picooc.utils.DateUtils;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.anyncImageView.ImageCache;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"UseSparseArrays"})
public class MyApplication extends FrontiaApplication
{
  private static final int CORE_POOL_SIZE = 5;
  public static boolean isShowLocalPassword = false;
  public static boolean isUploadPhoneNu = true;
  private static final ThreadFactory sThreadFactory = new ThreadFactory()
  {
    private final AtomicInteger mCount = new AtomicInteger(1);

    public Thread newThread(Runnable paramAnonymousRunnable)
    {
      return new Thread(paramAnonymousRunnable, "GreenDroid thread #" + this.mCount.getAndIncrement());
    }
  };
  private LinkedList<Activity> activityList = new LinkedList();
  private float bodyFaChangeValue = -1000.0F;
  private LatinContentFragment contentMain;
  private ContinuousWeightingSharedModel continuousWeightingSharedModel = null;
  private String curentUserName = null;
  private RoleBin currentRole;
  private UserBin currentUser;
  private EveryMeEntity everyMeToday;
  private int firstMonthFlag = -1000;
  private int firstSeasonFlag = -1000;
  private int firstWeekFlag = -1000;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      if (localResponseEntity.getMethod().equals("insertBodyIndex"));
      try
      {
        MyApplication.this.todayBody.setId_in_server(localResponseEntity.getResp().getJSONArray("bodyIndex").getJSONObject(0).getLong("id"));
        OperationDB.updateBodyIndexAfterUploadToServer(MyApplication.this, MyApplication.this.todayBody);
        return;
      }
      catch (JSONException localJSONException)
      {
        while (true)
          localJSONException.printStackTrace();
      }
    }
  };
  private boolean isComeFromQQ = false;
  boolean isExitApp = false;
  private boolean isFirstWeightChange = false;
  private boolean isShowOverweightPop = false;
  private LatinChartFragment latinChart = null;
  private LatinMainFragment latinMain = null;
  private ExecutorService mExecutorService;
  private ImageCache mImageCache;
  private ArrayList<WeakReference<OnLowMemoryListener>> mLowMemoryListeners = new ArrayList();
  HomeKeyEventBroadCastReceiver mRecevier;
  private RoleBin mainRole;
  private HashMap<Integer, LatinMonthData> monthDatas;
  private long role_id = -100L;
  private HashMap<Integer, LatinSeasonData> seasonDatas;
  private BodyIndex todayBody;
  private Typeface typeFace;
  private long user_id = -100L;
  private HashMap<Integer, LatinWeekData> weekDatas;
  private float weightChangeValue = -1000.0F;

  private float calculateFatProgressBar()
  {
    if (getTodayBody().getBodyFat() <= 0.0F)
      return 0.0F;
    if (getCurrentRole().getFirst_fat() <= 0.0F)
      getCurrentRole().setFirst_fat(this.todayBody.getBodyFat());
    float f;
    if (Math.abs(getCurrentRole().getFirst_fat() - getCurrentRole().getGoal_fat()) <= 0.0001D)
      if (this.todayBody.getBodyFat() <= getCurrentRole().getGoal_fat())
        f = 1.0F;
    while (true)
    {
      return f * 100.0F;
      f = 1.0F - (this.todayBody.getBodyFat() - getCurrentRole().getGoal_fat()) / 5.0F;
      if (f < 0.0F)
      {
        f = 0.0F;
        continue;
        f = (getCurrentRole().getFirst_fat() - this.todayBody.getBodyFat()) / (getCurrentRole().getFirst_fat() - getCurrentRole().getGoal_fat());
        if ((f >= 1.0F) || (getCurrentRole().getFirst_fat() == getCurrentRole().getGoal_fat()))
          f = 1.0F;
        else if (f < 0.0F)
          f = 0.0F;
      }
    }
  }

  private float calculatePlusFatProgressBar()
  {
    if (getTodayBody().getBodyFat() <= 0.0F)
      return 0.0F;
    if (getCurrentRole().getFirst_fat() <= 0.0F)
      getCurrentRole().setFirst_fat(getTodayBody().getBodyFat());
    float f = (this.todayBody.getBodyFat() - this.currentRole.getFirst_fat()) / (this.currentRole.getGoal_fat() - this.currentRole.getFirst_fat());
    if ((f >= 1.0F) || (this.currentRole.getFirst_fat() == this.currentRole.getGoal_fat()))
      f = 1.0F;
    while (true)
    {
      return f * 100.0F;
      if (f < 0.0F)
        f = 0.0F;
    }
  }

  private float calculatePlusWeightProgressBar()
  {
    if (getTodayBody().getWeight() <= 0.0F)
      return 0.0F;
    if (this.currentRole.getFirst_weight() <= 0.0F)
      this.currentRole.setFirst_weight(getTodayBody().getWeight());
    float f = (this.todayBody.getWeight() - this.currentRole.getFirst_weight()) / (this.currentRole.getGoal_weight() - this.currentRole.getFirst_weight());
    if ((f >= 1.0F) || (this.currentRole.getFirst_weight() == this.currentRole.getGoal_weight()))
      f = 1.0F;
    while (true)
    {
      return f * 100.0F;
      if (f < 0.0F)
        f = 0.0F;
    }
  }

  private float calculateWeightProgressBar()
  {
    if (getTodayBody().getWeight() <= 0.0F)
      return 0.0F;
    if (getCurrentRole().getFirst_weight() <= 0.0F)
      getCurrentRole().setFirst_weight(getTodayBody().getWeight());
    float f;
    if (getCurrentRole().getWeight_change_target() == 0.0F)
      if (getTodayBody().getWeight() <= getCurrentRole().getGoal_weight())
        f = 1.0F;
    while (true)
    {
      return f * 100.0F;
      f = 1.0F - (getTodayBody().getWeight() - getCurrentRole().getGoal_weight()) / 5.0F;
      if (f < 0.0F)
      {
        f = 0.0F;
        continue;
        f = (this.currentRole.getFirst_weight() - this.todayBody.getWeight()) / (this.currentRole.getFirst_weight() - this.currentRole.getGoal_weight());
        if ((f >= 1.0F) || (this.currentRole.getFirst_weight() == this.currentRole.getGoal_weight()))
          f = 1.0F;
        else if (f < 0.0F)
          f = 0.0F;
      }
    }
  }

  private void initFirstWeekFlagAndFirstMonthFlag()
  {
    BodyIndex localBodyIndex = OperationDB.selectBodyIndexAfterTimestamp(this, getCurrentRole().getRole_id(), 0L);
    if (localBodyIndex != null)
    {
      long l = localBodyIndex.getTime();
      this.firstWeekFlag = DateUtils.getWeekFlagByTimestamp(l);
      this.firstMonthFlag = DateUtils.getMonthFlagByTimeStamp(l);
      this.firstSeasonFlag = DateUtils.getSeasonFlagByTimeStamp(l);
    }
  }

  public void addActivity(Activity paramActivity)
  {
    if (!this.activityList.contains(paramActivity))
      this.activityList.add(paramActivity);
  }

  public float calculateFatDegree()
  {
    if (getCurrentRole().getWeight_change_target() > 0.0F)
      return calculatePlusFatProgressBar();
    return calculateFatProgressBar();
  }

  public float calculateWeightDegree()
  {
    if (getCurrentRole().getWeight_change_target() > 0.0F)
      return calculatePlusWeightProgressBar();
    return calculateWeightProgressBar();
  }

  public void clearAllData()
  {
    this.user_id = -100L;
    this.role_id = -100L;
    isUploadPhoneNu = true;
    this.currentRole = null;
    this.currentUser = null;
    this.todayBody = null;
    this.continuousWeightingSharedModel = null;
    this.weightChangeValue = -1000.0F;
    this.bodyFaChangeValue = -1000.0F;
    this.firstWeekFlag = -1000;
    this.firstMonthFlag = -1000;
    this.everyMeToday = null;
    this.isShowOverweightPop = false;
    if (this.weekDatas != null)
    {
      this.weekDatas.clear();
      this.weekDatas = null;
    }
    if (this.monthDatas != null)
    {
      this.monthDatas.clear();
      this.monthDatas = null;
    }
    if (this.seasonDatas != null)
    {
      this.seasonDatas.clear();
      this.seasonDatas = null;
    }
    System.gc();
  }

  public void clearChartDatas()
  {
    if (this.weekDatas != null)
    {
      this.weekDatas.clear();
      this.weekDatas = null;
    }
    if (this.monthDatas != null)
    {
      this.monthDatas.clear();
      this.monthDatas = null;
    }
    if (this.seasonDatas != null)
    {
      this.seasonDatas.clear();
      this.seasonDatas = null;
    }
  }

  public void clearContinuousWeightingSharedModel()
  {
    this.continuousWeightingSharedModel = null;
  }

  public void clearVisitorData()
  {
    OperationDB.deleteRoleByRoleId(this, -1L);
  }

  public void exit()
  {
    Iterator localIterator = this.activityList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        this.activityList.clear();
        clearAllData();
        return;
      }
      ((Activity)localIterator.next()).finish();
    }
  }

  public float getBodyFaChangeValue()
  {
    if (this.bodyFaChangeValue <= -1000.0F)
      initWeightChangeAndFatChange();
    return this.bodyFaChangeValue;
  }

  public LatinContentFragment getContentMain()
  {
    return this.contentMain;
  }

  public ContinuousWeightingSharedModel getContinuousWeightingSharedModel()
  {
    long l;
    if ((getCurrentRole().getRole_id() == getCurrentUser().getRole_id()) && (this.continuousWeightingSharedModel == null) && (this.todayBody != null))
    {
      l = DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), this.todayBody.getTime());
      if (l != 0L)
        break label81;
      this.continuousWeightingSharedModel = new ContinuousWeightingSharedModel(this, this.currentRole, this.todayBody, this.todayBody);
    }
    while (true)
    {
      return this.continuousWeightingSharedModel;
      label81: if (l == 1L)
        this.continuousWeightingSharedModel = new ContinuousWeightingSharedModel("跟昨天比", 2);
      else
        this.continuousWeightingSharedModel = new ContinuousWeightingSharedModel("跟上次比", 2);
    }
  }

  public String getCurentUserName(Long paramLong)
  {
    String str = OperationDB.selectMainName(this, paramLong.longValue());
    this.curentUserName = str;
    return str;
  }

  public RoleBin getCurrentRole()
  {
    if (this.currentRole == null)
    {
      this.currentRole = OperationDB.selectRoleDB(this, getRole_id());
      if (this.currentRole == null)
      {
        long l = getUser_id();
        if (l > 0L)
        {
          UserBin localUserBin = OperationDB.selectUserByUserIdDB(this, l);
          if (localUserBin.getRole_id() > 0L)
          {
            this.currentRole = OperationDB.selectRoleDB(this, localUserBin.getRole_id());
            SharedPreferenceUtils.putValue(this, "user-Info", "role_id", Long.valueOf(localUserBin.getRole_id()));
          }
        }
        if (this.currentRole == null)
          this.currentRole = new RoleBin();
      }
    }
    return this.currentRole;
  }

  public UserBin getCurrentUser()
  {
    if (this.currentUser == null)
    {
      this.currentUser = OperationDB.selectUserByUserIdDB(this, getUser_id());
      Log.i("qianmo", "currentUser==" + this.currentUser);
    }
    return this.currentUser;
  }

  public EveryMeEntity getEveryMeToday()
  {
    if (this.everyMeToday == null)
    {
      this.everyMeToday = OperationDB.queryEveryMeByTime(this, getCurrentRole().getRole_id(), System.currentTimeMillis());
      if (this.everyMeToday == null)
        this.everyMeToday = new EveryMeEntity();
    }
    while (true)
    {
      return this.everyMeToday;
      if (DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), this.everyMeToday.getTime()) != 0L)
      {
        this.everyMeToday = OperationDB.queryEveryMeByTime(this, getCurrentRole().getRole_id(), System.currentTimeMillis());
        if (this.everyMeToday == null)
          this.everyMeToday = new EveryMeEntity();
      }
    }
  }

  public ExecutorService getExecutor()
  {
    if (this.mExecutorService == null)
      this.mExecutorService = Executors.newFixedThreadPool(5, sThreadFactory);
    return this.mExecutorService;
  }

  public int getFirstMonthFlag()
  {
    if (this.firstMonthFlag == -1000)
      initFirstWeekFlagAndFirstMonthFlag();
    return this.firstMonthFlag;
  }

  public int getFirstSeasonFlag()
  {
    if (this.firstSeasonFlag == -1000)
      initFirstWeekFlagAndFirstMonthFlag();
    return this.firstSeasonFlag;
  }

  public int getFirstWeekFlag()
  {
    if (this.firstWeekFlag == -1000)
      initFirstWeekFlagAndFirstMonthFlag();
    return this.firstWeekFlag;
  }

  public Class<?> getHomeActivityClass()
  {
    return null;
  }

  public ImageCache getImageCache()
  {
    if (this.mImageCache == null)
      this.mImageCache = new ImageCache(this);
    return this.mImageCache;
  }

  public Intent getMainApplicationIntent()
  {
    return null;
  }

  public RoleBin getMainRole()
  {
    return this.mainRole;
  }

  public LatinMonthData getMonthDataByFlag(int paramInt)
  {
    if (this.monthDatas == null)
      this.monthDatas = new HashMap();
    if (!this.monthDatas.containsKey(Integer.valueOf(paramInt)))
    {
      LatinMonthData localLatinMonthData = new LatinMonthData(paramInt, getCurrentRole(), this);
      this.monthDatas.put(Integer.valueOf(paramInt), localLatinMonthData);
    }
    return (LatinMonthData)this.monthDatas.get(Integer.valueOf(paramInt));
  }

  public long getRole_id()
  {
    if (this.role_id == -100L)
    {
      this.role_id = ((Long)SharedPreferenceUtils.getValue(this, "user-Info", "role_id", Long.class)).longValue();
      if (this.role_id <= 0L)
        this.role_id = -100L;
    }
    return this.role_id;
  }

  public LatinSeasonData getSeasonDataByFlag(int paramInt)
  {
    if (this.seasonDatas == null)
      this.seasonDatas = new HashMap();
    if (!this.seasonDatas.containsKey(Integer.valueOf(paramInt)))
    {
      LatinSeasonData localLatinSeasonData = new LatinSeasonData(paramInt, getCurrentRole(), this);
      this.seasonDatas.put(Integer.valueOf(paramInt), localLatinSeasonData);
    }
    return (LatinSeasonData)this.seasonDatas.get(Integer.valueOf(paramInt));
  }

  public BodyIndex getTodayBody()
  {
    if (this.todayBody == null)
    {
      this.todayBody = OperationDB.selectBodyindexBeforeTimestamp(this, getCurrentRole().getRole_id(), 1000L + DateUtils.getDayStartTimeAndEndTimeByFlag(0)[1]);
      if (this.todayBody == null)
        this.todayBody = new BodyIndex();
    }
    return this.todayBody;
  }

  public Typeface getTypeFace()
  {
    if (this.typeFace == null)
      this.typeFace = TypefaceUtils.getTypeface(this, null);
    return this.typeFace;
  }

  public long getUser_id()
  {
    if (this.user_id == -100L)
    {
      this.user_id = ((Long)SharedPreferenceUtils.getValue(this, "user-Info", "user_id", Long.class)).longValue();
      if (this.user_id <= 0L)
        this.user_id = -100L;
    }
    return this.user_id;
  }

  public LatinWeekData getWeekDataByFlag(int paramInt)
  {
    if (this.weekDatas == null)
      this.weekDatas = new HashMap();
    if (!this.weekDatas.containsKey(Integer.valueOf(paramInt)))
    {
      LatinWeekData localLatinWeekData = new LatinWeekData(paramInt, getCurrentRole(), this);
      this.weekDatas.put(Integer.valueOf(paramInt), localLatinWeekData);
    }
    return (LatinWeekData)this.weekDatas.get(Integer.valueOf(paramInt));
  }

  public float getWeightChangeValue()
  {
    if (this.weightChangeValue <= -1000.0F)
      initWeightChangeAndFatChange();
    return this.weightChangeValue;
  }

  public LatinChartFragment getlatinChart()
  {
    return this.latinChart;
  }

  public LatinMainFragment getlatinMain()
  {
    return this.latinMain;
  }

  public void initWeightChangeAndFatChange()
  {
    BodyIndex localBodyIndex = OperationDB.selectBodyindexBeforeTimestamp(this, getCurrentRole().getRole_id(), getTodayBody().getTime() - 100L);
    if (localBodyIndex == null)
    {
      this.weightChangeValue = -1000.0F;
      this.bodyFaChangeValue = -1000.0F;
      return;
    }
    this.weightChangeValue = (getTodayBody().getWeight() - localBodyIndex.getWeight());
    if (localBodyIndex.getBodyFat() > 0.0F)
    {
      this.bodyFaChangeValue = (getTodayBody().getBodyFat() - localBodyIndex.getBodyFat());
      return;
    }
    this.bodyFaChangeValue = -1000.0F;
  }

  public boolean isComeFromQQ()
  {
    return this.isComeFromQQ;
  }

  public boolean isFirstWeightChange()
  {
    return this.isFirstWeightChange;
  }

  public boolean isShowOverweightPop()
  {
    return this.isShowOverweightPop;
  }

  public void onCreate()
  {
    super.onCreate();
    CrashHandler.getInstance().init(getApplicationContext());
    this.mRecevier = new HomeKeyEventBroadCastReceiver();
    IntentFilter localIntentFilter = new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS");
    registerReceiver(this.mRecevier, localIntentFilter);
    System.out.println("MyApplication onCreate() start-----");
    BootReceiver.resetAlarmBoot(this);
    System.out.println("MyApplication onCreate() finish-----");
  }

  public void onLowMemory()
  {
    super.onLowMemory();
    int i = 0;
    while (true)
    {
      if (i >= this.mLowMemoryListeners.size())
        return;
      OnLowMemoryListener localOnLowMemoryListener = (OnLowMemoryListener)((WeakReference)this.mLowMemoryListeners.get(i)).get();
      if (localOnLowMemoryListener == null)
      {
        this.mLowMemoryListeners.remove(i);
      }
      else
      {
        localOnLowMemoryListener.onLowMemoryReceived();
        i++;
      }
    }
  }

  public void registerOnLowMemoryListener(OnLowMemoryListener paramOnLowMemoryListener)
  {
    if (paramOnLowMemoryListener != null)
      this.mLowMemoryListeners.add(new WeakReference(paramOnLowMemoryListener));
  }

  public void removeActivity(Activity paramActivity)
  {
    if (this.activityList.contains(paramActivity))
      this.activityList.remove(paramActivity);
  }

  public void removeWeekDataByFlag(int paramInt)
  {
    if (this.weekDatas != null)
      this.weekDatas.remove(Integer.valueOf(paramInt));
  }

  public void setBodyFaChangeValue(float paramFloat)
  {
    this.bodyFaChangeValue = paramFloat;
  }

  public void setComeFromQQ(boolean paramBoolean)
  {
    this.isComeFromQQ = paramBoolean;
  }

  public void setContentMain(LatinContentFragment paramLatinContentFragment)
  {
    this.contentMain = paramLatinContentFragment;
  }

  public void setCurrentRole(RoleBin paramRoleBin)
  {
    this.currentRole = paramRoleBin;
  }

  public void setFirstWeightChange(boolean paramBoolean)
  {
    this.isFirstWeightChange = paramBoolean;
  }

  public void setMainRole(RoleBin paramRoleBin)
  {
    this.mainRole = paramRoleBin;
  }

  public void setRole_id(long paramLong)
  {
    this.role_id = paramLong;
  }

  public void setShowOverweightPop(boolean paramBoolean)
  {
    this.isShowOverweightPop = paramBoolean;
  }

  public void setTodayBody(BodyIndex paramBodyIndex)
  {
    this.todayBody = paramBodyIndex;
  }

  public void setUser(UserBin paramUserBin)
  {
    this.currentUser = paramUserBin;
  }

  public void setUser_id(long paramLong)
  {
    this.user_id = paramLong;
  }

  public void setWeightChangeValue(float paramFloat)
  {
    this.weightChangeValue = paramFloat;
  }

  public void setlatinChart(LatinChartFragment paramLatinChartFragment)
  {
    this.latinChart = paramLatinChartFragment;
  }

  public void setlatinMain(LatinMainFragment paramLatinMainFragment)
  {
    this.latinMain = paramLatinMainFragment;
  }

  public void unregisterOnLowMemoryListener(OnLowMemoryListener paramOnLowMemoryListener)
  {
    int i;
    if (paramOnLowMemoryListener != null)
      i = 0;
    while (true)
    {
      if (i >= this.mLowMemoryListeners.size())
        return;
      OnLowMemoryListener localOnLowMemoryListener = (OnLowMemoryListener)((WeakReference)this.mLowMemoryListeners.get(i)).get();
      if ((localOnLowMemoryListener == null) || (localOnLowMemoryListener == paramOnLowMemoryListener))
        this.mLowMemoryListeners.remove(i);
      else
        i++;
    }
  }

  public void updateEveryMe(EveryMeEntity paramEveryMeEntity)
  {
    this.everyMeToday = paramEveryMeEntity;
    getWeekDataByFlag(0).updateEveryMe(paramEveryMeEntity);
  }

  public void updateThisWeekData(BodyIndex paramBodyIndex)
  {
    if (this.weekDatas != null)
    {
      this.weekDatas.clear();
      this.weekDatas = null;
    }
  }

  public void updateTodayBodyIndex(BodyIndex paramBodyIndex)
  {
    long l;
    int i;
    if (getTodayBody().getWeight() > 0.0F)
    {
      this.weightChangeValue = (paramBodyIndex.getWeight() - this.todayBody.getWeight());
      if (this.todayBody.getBodyFat() <= 0.0F)
      {
        this.bodyFaChangeValue = -1000.0F;
        if (this.currentRole.getRole_id() == this.currentUser.getRole_id())
          this.continuousWeightingSharedModel = new ContinuousWeightingSharedModel(this, this.currentRole, paramBodyIndex, this.todayBody);
        this.todayBody = paramBodyIndex;
        l = OperationDB.insertBodyIndeDB(this, paramBodyIndex);
        this.todayBody.setId(l);
        boolean bool = getCurrentRole().getFirst_use_time() < 0L;
        i = 0;
        if (!bool)
        {
          i = 1;
          getCurrentRole().setFirst_use_time(System.currentTimeMillis());
        }
        if (getCurrentRole().getWeight_change_target() > 0.0F)
          break label452;
        if (this.todayBody.getWeight() > getCurrentRole().getFirst_weight())
        {
          if (getCurrentRole().getFirst_weight() <= 0.0F)
            break label444;
          this.isFirstWeightChange = true;
          label183: i = 1;
          getCurrentRole().setFirst_weight(this.todayBody.getWeight());
        }
        if (this.todayBody.getBodyFat() > getCurrentRole().getFirst_fat())
        {
          i = 1;
          getCurrentRole().setFirst_fat(this.todayBody.getBodyFat());
        }
      }
    }
    while (true)
    {
      if (i != 0)
      {
        OperationDB.updateRoleDB(this, getCurrentRole());
        AsyncMessageUtils.updateRoleMessage(this, getCurrentRole(), this.httpHandler);
      }
      if (l > 0L)
      {
        if ((this.monthDatas != null) && (this.monthDatas.containsKey(Integer.valueOf(0))))
          ((LatinMonthData)this.monthDatas.get(Integer.valueOf(0))).updateData(paramBodyIndex, getCurrentRole());
        if ((this.seasonDatas != null) && (this.seasonDatas.containsKey(Integer.valueOf(0))))
          ((LatinSeasonData)this.seasonDatas.get(Integer.valueOf(0))).updateData(paramBodyIndex, getCurrentRole());
        if (paramBodyIndex.getWeight() > getCurrentRole().getFirst_weight())
          this.isShowOverweightPop = true;
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(this.todayBody);
        AsyncMessageUtils.insertBodyIndexsToServer(this, localArrayList, getCurrentRole().getUser_id(), this.httpHandler);
      }
      return;
      this.bodyFaChangeValue = (paramBodyIndex.getBodyFat() - this.todayBody.getBodyFat());
      break;
      this.weightChangeValue = -1000.0F;
      this.bodyFaChangeValue = -1000.0F;
      break;
      label444: this.isFirstWeightChange = false;
      break label183;
      label452: if (this.todayBody.getWeight() < getCurrentRole().getFirst_weight())
      {
        i = 1;
        getCurrentRole().setFirst_weight(this.todayBody.getWeight());
      }
      if (this.todayBody.getBodyFat() < getCurrentRole().getFirst_fat())
      {
        i = 1;
        getCurrentRole().setFirst_fat(this.todayBody.getBodyFat());
      }
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
            break label65;
          if ((MyApplication.this.getCurrentUser() != null) && (SharedPreferenceUtils.isOpenPsd(MyApplication.this)))
            MyApplication.isShowLocalPassword = true;
        }
      }
      while (true)
      {
        System.out.println("home pressed-----------------------");
        return;
        label65: str.equals("recentapps");
      }
    }
  }

  public static abstract interface OnLowMemoryListener
  {
    public abstract void onLowMemoryReceived();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     MyApplication
 * JD-Core Version:    0.6.2
 */
