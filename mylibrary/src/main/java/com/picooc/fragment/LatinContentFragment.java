package com.picooc.fragment;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.picooc.MyApplication;
import com.picooc.PicoocActivity3;
import com.picooc.bluetoothscan.PicoocBlueToothProfile;
import com.picooc.constant.ThemeConstant;
import com.picooc.db.OperationDB;
import com.picooc.domain.BodyIndex;
import com.picooc.internet.AsyncMessageUtils;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.shake.ShakeListener;
import com.picooc.shake.ShakeListener.OnShakeListener;
import com.picooc.utils.MusicService;
import com.picooc.utils.MusicService.MyBinder;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.anyncImageView.AsyncImageView;
import com.picooc.widget.loading.PicoocToast;
import com.picooc.widget.picoocProgress.PicoocAlertDialog;
import com.picooc.widget.viewflow.LinePageIndicator;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

@SuppressLint({"HandlerLeak", "DefaultLocale", "ValidFragment"})
public class LatinContentFragment extends Fragment
  implements ViewPager.OnPageChangeListener
{
  public static final int ALLTIMERTASK = 3;
  public static final int NOWCONNECTORSCAN = 2;
  private static final int REQUEST_ENABLE_BT = 3;
  public static final int STATSCANANDCONNECT = 1;
  public static final int WEIGHTING_SUCCESS = 772;
  public static final int WEIGHTING_SUCCESS_CLOSE = 774;
  public static final int WEIGHTING_SUCCESS_NEW = 773;
  int a = 1;
  private MyApplication app;
  private PicoocBlueToothProfile blueToothProfile;
  private Handler bluetoothHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default:
      case 401:
      case 406:
      case 403:
      case 405:
      case 7:
      case 6:
        do
        {
          while (true)
          {
            return;
            Log.e("whatlong1", "BLUETOOTH_RESET~~上秤前请摇一摇");
            if (LatinContentFragment.this.mShakeListener != null)
              LatinContentFragment.this.mShakeListener.start();
            LatinContentFragment.this.latinMain.setConnectBluetoothMessage(paramAnonymousMessage.what, "上秤前请摇一摇");
            return;
            Log.e("whatlong1", "BLUETOOTH_CONNECT_TIMEOUT~~上秤前请摇一摇");
            LatinContentFragment.this.blueToothProfile.stop();
            if (LatinContentFragment.this.latinMain != null)
              LatinContentFragment.this.latinMain.setConnectBluetoothMessage(paramAnonymousMessage.what, "上秤前请摇一摇");
            if (LatinContentFragment.this.mShakeListener != null)
            {
              LatinContentFragment.this.mShakeListener.start();
              return;
              LatinContentFragment.this.latinMain.setConnectBluetoothMessage(403, "正在连接...");
              return;
              LatinContentFragment.this.time2 = System.currentTimeMillis();
              if (paramAnonymousMessage.arg1 == 2)
                LatinContentFragment.this.latinMain.setConnectBluetoothMessage(405, "成功与 有品·S 连接");
              for (int i = 10000; ((Integer)SharedPreferenceUtils.getValue(LatinContentFragment.this.getActivity(), "user-Info", "has_device", Integer.class)).intValue() <= 0; i = 1)
              {
                RequestEntity localRequestEntity = new RequestEntity("updateUserDevice", "5.6");
                localRequestEntity.addParam("user_id", Long.valueOf(LatinContentFragment.this.app.getCurrentUser().getUser_id()));
                localRequestEntity.addParam("role_id", Long.valueOf(LatinContentFragment.this.app.getCurrentUser().getRole_id()));
                localRequestEntity.addParam("user_device", Integer.valueOf(i));
                localRequestEntity.addParam("show_weight", Integer.valueOf(1));
                HttpUtils.getJson(LatinContentFragment.this.getActivity(), localRequestEntity, null);
                SharedPreferenceUtils.putValue(LatinContentFragment.this.getActivity(), "user-Info", "has_device", Integer.valueOf(i));
                LatinContentFragment.this.latinMain.refreshWhyImage();
                return;
                LatinContentFragment.this.latinMain.setConnectBluetoothMessage(405, "成功与Latin连接");
              }
            }
          }
          LatinContentFragment.this.blueToothProfile.stop();
          sendEmptyMessageDelayed(401, 1000L);
          PicoocToast.showToast(LatinContentFragment.this.getActivity(), "没有成功接收到您的数据:)");
          if (LatinContentFragment.this.app.getTodayBody().getWeight() <= 0.0F)
          {
            LatinContentFragment.this.latinMain.refreshUI();
            return;
          }
          LatinContentFragment.this.latinMain.updateUIWhenMeasureSuccess();
          return;
          Log.e("whatlong1", "MESSAGE_CONNECT_FAILD~~上秤前请摇一摇");
          LatinContentFragment.this.latinMain.setConnectBluetoothMessage(406, "上秤前请摇一摇");
        }
        while (LatinContentFragment.this.mShakeListener == null);
        LatinContentFragment.this.mShakeListener.start();
        return;
      case 772:
        final BodyIndex localBodyIndex2 = (BodyIndex)paramAnonymousMessage.obj;
        LatinContentFragment.this.time3 = System.currentTimeMillis();
        localBodyIndex2.setShake_to_conn_time_diff(LatinContentFragment.this.time2 - LatinContentFragment.this.time1);
        localBodyIndex2.setConn_to_measure_time_diff(LatinContentFragment.this.time3 - LatinContentFragment.this.time2);
        LatinContentFragment.this.time1 = 0L;
        LatinContentFragment.this.time2 = 0L;
        LatinContentFragment.this.time3 = 0L;
        LatinContentFragment.this.musicService.play();
        if ((LatinContentFragment.this.app.getTodayBody().getWeight() > 0.0F) && (Math.abs(LatinContentFragment.this.app.getTodayBody().getWeight() - localBodyIndex2.getWeight()) > 3.0F))
        {
          LatinContentFragment.this.dialog = new PicoocAlertDialog("啊哦~您的本次称重数据与历史数据有较大差距，请问您是" + LatinContentFragment.this.app.getCurrentRole().getName() + "本人吗？", "我不是", "我是", LatinContentFragment.this.getActivity());
          LatinContentFragment.this.dialog.showAlerDialogTwo(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              LatinContentFragment.this.dialog.dismissAlertDialog();
              LatinContentFragment.this.app.updateTodayBodyIndex(localBodyIndex2);
              LatinContentFragment.this.latinMain.updateUIWhenMeasureSuccess();
              LatinContentFragment.this.app.updateThisWeekData(localBodyIndex2);
              LatinContentFragment.this.chartFragment.updateUI();
              Intent localIntent = new Intent();
              localIntent.setAction("com.picooc.latin.weight.success");
              LatinContentFragment.this.getActivity().sendBroadcast(localIntent);
            }
          }
          , new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              LatinContentFragment.this.dialog.dismissAlertDialog();
              LatinContentFragment.this.latinMain.updateUIWhenMeasureSuccess();
              LatinContentFragment.this.chartFragment.updateUI();
              PicoocToast.showToast(LatinContentFragment.this.getActivity(), "谢谢您， 本次测量将不会被记录  : )");
            }
          });
          LatinContentFragment.this.dialog.setOnTouchOutside(Boolean.valueOf(false));
        }
        while (true)
        {
          AsyncMessageUtils.uploadSupportAndroidModelInfo(LatinContentFragment.this.getActivity());
          if ((localBodyIndex2.getMac() != null) && (!"".equals(localBodyIndex2.getMac())))
            AsyncMessageUtils.uploadLatinMac(LatinContentFragment.this.getActivity(), LatinContentFragment.this.app.getCurrentUser().getUser_id(), localBodyIndex2.getMac());
          LatinContentFragment.this.blueToothProfile.stop();
          sendEmptyMessageDelayed(401, 4000L);
          return;
          LatinContentFragment.this.app.updateTodayBodyIndex(localBodyIndex2);
          LatinContentFragment.this.latinMain.updateUIWhenMeasureSuccess();
          LatinContentFragment.this.app.updateThisWeekData(localBodyIndex2);
          LatinContentFragment.this.chartFragment.updateUI();
          Intent localIntent2 = new Intent();
          localIntent2.setAction("com.picooc.latin.weight.success");
          LatinContentFragment.this.getActivity().sendBroadcast(localIntent2);
        }
      case 8:
        LatinContentFragment.this.mBtAdapter.disable();
        sendEmptyMessageDelayed(7, 3000L);
        LatinContentFragment.this.blueToothProfile.stop();
        return;
      case 773:
        final BodyIndex localBodyIndex1 = (BodyIndex)paramAnonymousMessage.obj;
        LatinContentFragment.this.time3 = System.currentTimeMillis();
        localBodyIndex1.setShake_to_conn_time_diff(LatinContentFragment.this.time2 - LatinContentFragment.this.time1);
        localBodyIndex1.setConn_to_measure_time_diff(LatinContentFragment.this.time3 - LatinContentFragment.this.time2);
        LatinContentFragment.this.time1 = 0L;
        LatinContentFragment.this.time2 = 0L;
        LatinContentFragment.this.time3 = 0L;
        LatinContentFragment.this.musicService.play();
        if ((LatinContentFragment.this.app.getTodayBody().getWeight() > 0.0F) && (Math.abs(LatinContentFragment.this.app.getTodayBody().getWeight() - localBodyIndex1.getWeight()) > 3.0F))
        {
          LatinContentFragment.this.dialog = new PicoocAlertDialog("啊哦~您的本次称重数据与历史数据有较大差距，请问您是" + LatinContentFragment.this.app.getCurrentRole().getName() + "本人吗？", "我不是", "我是", LatinContentFragment.this.getActivity());
          LatinContentFragment.this.dialog.showAlerDialogTwo(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              LatinContentFragment.this.dialog.dismissAlertDialog();
              LatinContentFragment.this.app.updateTodayBodyIndex(localBodyIndex1);
              LatinContentFragment.this.latinMain.updateUIWhenMeasureSuccess();
              LatinContentFragment.this.app.updateThisWeekData(localBodyIndex1);
              LatinContentFragment.this.chartFragment.updateUI();
              Intent localIntent = new Intent();
              localIntent.setAction("com.picooc.latin.weight.success");
              LatinContentFragment.this.getActivity().sendBroadcast(localIntent);
            }
          }
          , new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              LatinContentFragment.this.dialog.dismissAlertDialog();
              LatinContentFragment.this.latinMain.updateUIWhenMeasureSuccess();
              LatinContentFragment.this.chartFragment.updateUI();
              PicoocToast.showToast(LatinContentFragment.this.getActivity(), "谢谢您， 本次测量将不会被记录  : )");
            }
          });
          LatinContentFragment.this.dialog.setOnTouchOutside(Boolean.valueOf(false));
        }
        while (true)
        {
          AsyncMessageUtils.uploadSupportAndroidModelInfo(LatinContentFragment.this.getActivity());
          if ((localBodyIndex1.getMac() == null) || ("".equals(localBodyIndex1.getMac())))
            break;
          AsyncMessageUtils.uploadLatinMac(LatinContentFragment.this.getActivity(), LatinContentFragment.this.app.getCurrentUser().getUser_id(), localBodyIndex1.getMac());
          return;
          LatinContentFragment.this.app.updateTodayBodyIndex(localBodyIndex1);
          LatinContentFragment.this.latinMain.updateUIWhenMeasureSuccess();
          LatinContentFragment.this.app.updateThisWeekData(localBodyIndex1);
          LatinContentFragment.this.chartFragment.updateUI();
          Intent localIntent1 = new Intent();
          localIntent1.setAction("com.picooc.latin.weight.success");
          LatinContentFragment.this.getActivity().sendBroadcast(localIntent1);
        }
      case 774:
      }
      Log.e("picooc", "WEIGHTING_SUCCESS_CLOSE~~断开连接");
      LatinContentFragment.this.blueToothProfile.stop();
      sendEmptyMessageDelayed(401, 2000L);
    }
  };
  LatinChartFragment chartFragment;
  private PicoocAlertDialog dialog;
  private boolean firstToChartPage = true;
  private ImageView hasNewWeight;
  private AsyncImageView head_image;
  LatinMainFragment latinMain;
  private LinearLayout linearLayout_bg;
  private PicoocFragmentAdapter mAdapter;
  private BluetoothAdapter mBtAdapter;
  public LinePageIndicator mIndicator;
  float mLastMotionX = 0.0F;
  public ViewPager mPager;
  private final BroadcastReceiver mReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      String str1 = paramAnonymousIntent.getAction();
      if ("com.picooc.latin.addfamilysuccess".equals(str1))
      {
        LatinContentFragment.this.refreshHeadImageAndName();
        LatinContentFragment.this.latinMain.refreshUI();
        LatinContentFragment.this.chartFragment.updateUI();
        ((PicoocActivity3)LatinContentFragment.this.getActivity()).toggleRightMenu();
        if (LatinContentFragment.this.mPager.getCurrentItem() != 0)
          LatinContentFragment.this.mPager.setCurrentItem(0, false);
      }
      do
      {
        BodyIndex localBodyIndex;
        do
        {
          do
          {
            do
            {
              do
              {
                return;
                if (!"com.picooc.latin.addvisitorsuccess".equals(str1))
                  break;
                LatinContentFragment.this.refreshHeadImageAndName();
                LatinContentFragment.this.latinMain.refreshUI();
                LatinContentFragment.this.chartFragment.updateUI();
                ((PicoocActivity3)LatinContentFragment.this.getActivity()).toggleRightMenu();
              }
              while (LatinContentFragment.this.mPager.getCurrentItem() == 0);
              LatinContentFragment.this.mPager.setCurrentItem(0, false);
              return;
              if (!"com.picooc.latin.refresh.content".equals(str1))
                break;
              Log.i("picooc", "更换角色");
              Log.i("qianmo2", "接收到更改年龄的广播了");
              LatinContentFragment.this.refreshHeadImageAndName();
              LatinContentFragment.this.latinMain.refreshUI();
              LatinContentFragment.this.chartFragment.updateUI();
              LatinContentFragment.this.chartFragment.refreshPrevAndNextButton();
            }
            while (!paramAnonymousIntent.getBooleanExtra("closeMenuOrNot", true));
            ((PicoocActivity3)LatinContentFragment.this.getActivity()).toggleRightMenu();
            return;
            if ("com.picooc.latin.setting.goal.weight".equals(str1))
            {
              Log.i("picooc", "设置目标体重成功");
              LatinContentFragment.this.app.getCurrentRole().setChange_goal_weight_time(System.currentTimeMillis());
              OperationDB.updateRoleDB(paramAnonymousContext, LatinContentFragment.this.app.getCurrentRole());
              AsyncMessageUtils.updateRoleMessage(LatinContentFragment.this.getActivity(), LatinContentFragment.this.app.getCurrentRole(), null);
              LatinContentFragment.this.latinMain.refreshUIWhenSettingGoalWeight();
              LatinContentFragment.this.app.removeWeekDataByFlag(0);
              LatinContentFragment.this.chartFragment.refreshChartWhenSettingGoalWeightSuccess();
              return;
            }
            if (!"com.picooc.setting.updateRoleMessage".equals(str1))
              break;
            LatinContentFragment.this.refreshHeadImageAndName();
            LatinContentFragment.this.latinMain.refreshUIWhenUpdataRoleMessage();
          }
          while (!paramAnonymousIntent.getBooleanExtra("goalWeightChange", false));
          LatinContentFragment.this.chartFragment.refreshChartWhenSettingGoalWeightSuccess();
          return;
          if (!"com.picooc.sync.data.from.server.and.refreshui".equals(str1))
            break;
          Log.i("http", "从服务器同步数据成功");
          localBodyIndex = OperationDB.selectBodyindexBeforeTimestamp(LatinContentFragment.this.getActivity(), LatinContentFragment.this.app.getCurrentRole().getRole_id(), 1000L + com.picooc.utils.DateUtils.getDayStartTimeAndEndTimeByFlag(0)[1]);
        }
        while ((localBodyIndex == null) || (localBodyIndex.getTime() <= LatinContentFragment.this.app.getTodayBody().getTime()));
        LatinContentFragment.this.app.clearAllData();
        LatinContentFragment.this.latinMain.refreshUI();
        LatinContentFragment.this.chartFragment.updateUI();
        LatinContentFragment.this.chartFragment.refreshPrevAndNextButton();
        return;
        if ("com.picooc.sync.delete.today.weight.refresh.ui".equals(str1))
        {
          LatinContentFragment.this.app.clearAllData();
          LatinContentFragment.this.latinMain.refreshUI();
          LatinContentFragment.this.chartFragment.updateUI();
          return;
        }
        if ("com.picooc.receive.push.refresh.ui".equals(str1))
        {
          LatinContentFragment.this.updateCount();
          return;
        }
        if (str1.equals("android.net.conn.CONNECTIVITY_CHANGE"))
        {
          Log.d("mark", "网络状态已经改变");
          NetworkInfo localNetworkInfo = ((ConnectivityManager)LatinContentFragment.this.getActivity().getSystemService("connectivity")).getActiveNetworkInfo();
          if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
          {
            String str2 = localNetworkInfo.getTypeName();
            Log.i("qianmo2", "当前网络名称：" + str2);
            LatinContentFragment.this.latinMain.netWorkChange(true);
            return;
          }
          Log.i("qianmo2", "没有可用网络");
          LatinContentFragment.this.latinMain.netWorkChange(false);
          return;
        }
        if ("com.picooc.i.kown".equals(str1))
        {
          LatinContentFragment.this.latinMain.iknow();
          return;
        }
        if ("com.picooc.some.role.has.new.weighting".equals(str1))
        {
          LatinContentFragment.this.refreshHasNewWeighting(1);
          return;
        }
      }
      while (!"com.picooc.new.weighting.has.reduce".equals(str1));
      LatinContentFragment.this.refreshHasNewWeighting(paramAnonymousIntent.getIntExtra("count", 0));
    }
  };
  private ShakeListener mShakeListener = null;
  private Vibrator mVibrator;
  private MusicService musicService;
  private MyPageChangeListener myPageChangeListener;
  private TextView push_count;
  private OnShakeListener sakeListener = new OnShakeListener()
  {
    public void onShake()
    {
      if (LatinContentFragment.this.mBtAdapter.isEnabled())
      {
        LatinContentFragment.this.latinMain.setConnectBluetoothMessage(402, "请上秤");
        LatinContentFragment.this.startVibrato();
        LatinContentFragment.this.mShakeListener.stop();
        LatinContentFragment.this.blueToothProfile.startScanOrConnect();
        LatinContentFragment.this.time1 = 0L;
        LatinContentFragment.this.time2 = 0L;
        LatinContentFragment.this.time3 = 0L;
        LatinContentFragment.this.time1 = System.currentTimeMillis();
        return;
      }
      LatinContentFragment.this.mShakeListener.stop();
      Intent localIntent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
      LatinContentFragment.this.startActivityForResult(localIntent, 3);
    }
  };
  private ServiceConnection sc = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      LatinContentFragment.this.musicService = ((MyBinder)paramAnonymousIBinder).getService();
    }

    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      LatinContentFragment.this.musicService = null;
    }
  };
  int screenHeight;
  int screenWidth;
  private ThemeConstant themeConstant;
  private long time1;
  private long time2;
  private long time3;
  private Timer timer;
  private TextView title;

  private void refreshHasNewWeighting(int paramInt)
  {
    if (paramInt > 0)
    {
      this.hasNewWeight.setVisibility(0);
      return;
    }
    this.hasNewWeight.setVisibility(8);
  }

  private void refreshHeadImageAndName()
  {
    refreshNicName();
    if (this.mPager.getCurrentItem() != 0)
      this.title.setText("Latin简报");
    if (!this.app.getCurrentRole().getHead_portrait_url().equals(""))
    {
      this.head_image.setUrl(this.app.getCurrentRole().getHead_portrait_url());
      refreshHasNewWeighting(SharedPreferenceUtils.getCount(getActivity(), "NEW_WEIGHTING_RECORD"));
      return;
    }
    if (this.app.getCurrentRole().getSex() == 1)
      this.head_image.setDefaultImageResource(2130838457);
    while (true)
    {
      this.head_image.setUrl(null);
      break;
      this.head_image.setDefaultImageResource(2130838460);
    }
  }

  private void refreshNicName()
  {
    if (this.app.getCurrentRole().getFamily_type() == 0)
    {
      this.title.setText(this.app.getCurrentRole().getName());
      return;
    }
    if ((this.app.getCurrentRole().getRemark_name() != null) && (!this.app.getCurrentRole().getRemark_name().equals("")))
    {
      this.title.setText(this.app.getCurrentRole().getRemark_name());
      return;
    }
    this.title.setText(this.app.getCurrentRole().getName());
  }

  public boolean isEnd()
  {
    return this.mPager.getCurrentItem() == 1;
  }

  public boolean isFirst()
  {
    return this.mPager.getCurrentItem() == 0;
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default:
      return;
    case 3:
    }
    if (paramInt2 == -1)
    {
      this.mShakeListener.start();
      return;
    }
    this.mShakeListener.start();
  }

  @SuppressLint({"NewApi"})
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.screenWidth = localDisplayMetrics.widthPixels;
    this.screenHeight = localDisplayMetrics.heightPixels;
    this.mShakeListener = new ShakeListener(getActivity());
    this.mShakeListener.setOnShakeListener(this.sakeListener);
    if (Build.VERSION.SDK_INT >= 18);
    for (this.mBtAdapter = ((BluetoothManager)getActivity().getSystemService("bluetooth")).getAdapter(); ; this.mBtAdapter = BluetoothAdapter.getDefaultAdapter())
    {
      this.blueToothProfile = new PicoocBlueToothProfile(getActivity(), this.bluetoothHandler, this.mBtAdapter);
      this.mVibrator = ((Vibrator)getActivity().getApplication().getSystemService("vibrator"));
      this.app = ((MyApplication)getActivity().getApplicationContext());
      IntentFilter localIntentFilter1 = new IntentFilter("com.picooc.latin.addfamilysuccess");
      getActivity().registerReceiver(this.mReceiver, localIntentFilter1);
      IntentFilter localIntentFilter2 = new IntentFilter("com.picooc.latin.addvisitorsuccess");
      getActivity().registerReceiver(this.mReceiver, localIntentFilter2);
      IntentFilter localIntentFilter3 = new IntentFilter("com.picooc.latin.refresh.content");
      getActivity().registerReceiver(this.mReceiver, localIntentFilter3);
      IntentFilter localIntentFilter4 = new IntentFilter("com.picooc.latin.setting.goal.weight");
      getActivity().registerReceiver(this.mReceiver, localIntentFilter4);
      IntentFilter localIntentFilter5 = new IntentFilter("com.picooc.setting.updateRoleMessage");
      getActivity().registerReceiver(this.mReceiver, localIntentFilter5);
      IntentFilter localIntentFilter6 = new IntentFilter("com.picooc.sync.data.from.server.and.refreshui");
      getActivity().registerReceiver(this.mReceiver, localIntentFilter6);
      IntentFilter localIntentFilter7 = new IntentFilter("com.picooc.sync.delete.today.weight.refresh.ui");
      getActivity().registerReceiver(this.mReceiver, localIntentFilter7);
      IntentFilter localIntentFilter8 = new IntentFilter("com.picooc.some.role.has.new.weighting");
      getActivity().registerReceiver(this.mReceiver, localIntentFilter8);
      IntentFilter localIntentFilter9 = new IntentFilter("com.picooc.new.weighting.has.reduce");
      getActivity().registerReceiver(this.mReceiver, localIntentFilter9);
      AsyncMessageUtils.loadBodyIndexFromServer(getActivity(), 0L, true);
      Intent localIntent = new Intent(getActivity(), MusicService.class);
      getActivity().bindService(localIntent, this.sc, 1);
      IntentFilter localIntentFilter10 = new IntentFilter("com.picooc.receive.push.refresh.ui");
      getActivity().registerReceiver(this.mReceiver, localIntentFilter10);
      IntentFilter localIntentFilter11 = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
      getActivity().registerReceiver(this.mReceiver, localIntentFilter11);
      IntentFilter localIntentFilter12 = new IntentFilter("com.picooc.i.kown");
      getActivity().registerReceiver(this.mReceiver, localIntentFilter12);
      return;
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903082, paramViewGroup, false);
    this.linearLayout_bg = ((LinearLayout)localView.findViewById(2131099739));
    this.themeConstant = new ThemeConstant(getActivity());
    if (this.themeConstant.getbgResource().intValue() == 2130837526)
      this.linearLayout_bg.setBackgroundResource(2130837526);
    while (true)
    {
      this.title = ((TextView)localView.findViewById(2131099699));
      this.title.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
      this.push_count = ((TextView)localView.findViewById(2131099897));
      this.hasNewWeight = ((ImageView)localView.findViewById(2131099712));
      this.head_image = ((AsyncImageView)localView.findViewById(2131099850));
      this.mPager = ((ViewPager)localView.findViewById(2131099898));
      this.mAdapter = new PicoocFragmentAdapter(getActivity().getSupportFragmentManager());
      this.latinMain = new LatinMainFragment();
      this.mAdapter.addFragment(this.latinMain);
      this.chartFragment = new LatinChartFragment();
      this.mAdapter.addFragment(this.chartFragment);
      this.mPager.setOffscreenPageLimit(1);
      this.mPager.setAdapter(this.mAdapter);
      this.mIndicator = ((LinePageIndicator)localView.findViewById(2131099896));
      this.mIndicator.setViewPager(this.mPager);
      this.mPager.refreshDrawableState();
      this.mPager.setOnPageChangeListener(this);
      this.mAdapter.notifyDataSetChanged();
      this.mAdapter.saveState();
      refreshHeadImageAndName();
      updateCount();
      return localView;
      if (this.themeConstant.getbgResource().intValue() == 2130837527)
        this.linearLayout_bg.setBackgroundResource(2130837527);
      else
        this.linearLayout_bg.setBackgroundResource(2130837525);
    }
  }

  public void onDestroy()
  {
    if (this.mShakeListener != null)
    {
      this.mShakeListener.stop();
      this.mShakeListener.setOnShakeListener(null);
      this.mShakeListener = null;
    }
    if (this.sc != null)
      getActivity().unbindService(this.sc);
    this.blueToothProfile.destory();
    getActivity().unregisterReceiver(this.mReceiver);
    super.onDestroy();
  }

  public void onPageScrollStateChanged(int paramInt)
  {
  }

  public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
  }

  public void onPageSelected(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 0:
    case 1:
    }
    while (true)
    {
      this.mIndicator.setCurrentItem(paramInt);
      if (this.myPageChangeListener != null)
        this.myPageChangeListener.onPageSelected(paramInt);
      return;
      refreshNicName();
      this.title.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
      continue;
      if ((this.firstToChartPage) && (paramInt == 1))
      {
        this.chartFragment.refreshChart();
        this.firstToChartPage = false;
      }
      this.title.setText("Latin 简报");
      this.title.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
    }
  }

  public void onPause()
  {
    super.onPause();
    if (this.mShakeListener != null)
      this.mShakeListener.stop();
    Log.i("picooc", "latinContentFragment--onPause");
  }

  public void onResume()
  {
    Log.i("picooc", "latinContentFragment--onResume");
    if (this.mShakeListener != null)
      this.mShakeListener.start();
    super.onResume();
  }

  public void setMenuVisibility(boolean paramBoolean)
  {
    super.setMenuVisibility(paramBoolean);
    if (getView() != null)
    {
      if (paramBoolean)
      {
        getView().setVisibility(0);
        if (this.mShakeListener != null)
          this.mShakeListener.start();
        Log.i("picooc", "thisview==visible");
      }
    }
    else
      return;
    getView().setVisibility(8);
    if (this.mShakeListener != null)
      this.mShakeListener.stop();
    Log.i("picooc", "thisview==gone");
  }

  public void setMyPageChangeListener(MyPageChangeListener paramMyPageChangeListener)
  {
    this.myPageChangeListener = paramMyPageChangeListener;
  }

  public void startVibrato()
  {
    this.mVibrator.vibrate(new long[] { 500L, 200L, 500L, 200L }, -1);
  }

  public void updateCount()
  {
    if (this.push_count != null)
    {
      if ((this.app.getCurrentUser() == null) || (this.app.getCurrentUser().getUser_id() <= 0L))
        break label163;
      int i = OperationDB.selectCount(getActivity(), true, 0L, SharedPreferenceUtils.getSendAndReceiveTime(getActivity(), Boolean.valueOf(false), this.app.getCurrentUser().getUser_id()), this.app.getCurrentUser().getUser_id(), "remote_uid") + OperationDB.selectCount(getActivity(), false, SharedPreferenceUtils.getSendAndReceiveTime(getActivity(), Boolean.valueOf(true), this.app.getCurrentUser().getUser_id()), 0L, this.app.getCurrentUser().getUser_id(), "user_id");
      if (i > 0)
      {
        this.push_count.setAlpha(1.0F);
        this.push_count.setText(i);
      }
    }
    else
    {
      return;
    }
    this.push_count.setAlpha(0.0F);
    return;
    label163: this.push_count.setAlpha(0.0F);
  }

  public static abstract interface MyPageChangeListener
  {
    public abstract void onPageSelected(int paramInt);
  }

  static class PicoocFragmentAdapter extends FragmentStatePagerAdapter
  {
    private List<Fragment> content = new ArrayList();

    public PicoocFragmentAdapter(FragmentManager paramFragmentManager)
    {
      super();
    }

    public void addFragment(Fragment paramFragment)
    {
      this.content.add(paramFragment);
    }

    public int getCount()
    {
      return this.content.size();
    }

    public Fragment getItem(int paramInt)
    {
      return (Fragment)this.content.get(paramInt);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LatinContentFragment
 * JD-Core Version:    0.6.2
 */
