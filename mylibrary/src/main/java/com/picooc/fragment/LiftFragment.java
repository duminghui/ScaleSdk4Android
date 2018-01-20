package com.picooc.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.picooc.MyApplication;
import com.picooc.MyInformation;
import com.picooc.PicoocActivity3;
import com.picooc.guide.GuideAct;
import com.picooc.guide.GuideModel;
import com.picooc.slidingMenu.IChangeFragment;
import com.picooc.slidingMenu.SlidingMenu;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.anyncImageView.AsyncImageView;
import com.picooc.widget.loading.PicoocToast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.achartengine.tools.ModUtils;

public class LiftFragment extends Fragment
{
  private MyApplication app;
  private IChangeFragment changeFragmeng;
  private AsyncImageView headImage;
  private LinearLayout linerIcon;
  private int[] locationheadImageView;
  private GuideModel mGuide;
  private List<GuideModel> mGuideList;
  private final BroadcastReceiver mReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      String str = paramAnonymousIntent.getAction();
      if ("com.picooc.setting.updateRoleMessage".equals(str))
        LiftFragment.this.refreshHeadImageAndNic();
      do
      {
        do
        {
          return;
          if (!"com.picooc.latin.refresh.content".equals(str))
            break;
          LiftFragment.this.refreshHeadImageAndNic();
        }
        while (LiftFragment.this.app.getTodayBody().getBodyFat() > 0.0F);
        LiftFragment.this.radioGroup.check(2131099963);
        return;
        if ("com.picooc.latin.addfamilysuccess".equals(str))
        {
          LiftFragment.this.refreshHeadImageAndNic();
          LiftFragment.this.radioGroup.check(2131099963);
          return;
        }
      }
      while (!"com.picooc.latin.addvisitorsuccess".equals(str));
      LiftFragment.this.refreshHeadImageAndNic();
      LiftFragment.this.radioGroup.check(2131099963);
    }
  };
  private AsyncImageView moveHeadImage;
  private View.OnClickListener picoocClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      default:
      case 2131099963:
      case 2131099966:
      case 2131099968:
      case 2131099961:
      case 2131100492:
      case 2131100493:
      }
      do
      {
        do
        {
          do
          {
            do
            {
              do
                return;
              while (LiftFragment.this.changeFragmeng == null);
              LiftFragment.this.changeFragmeng.changeFragment(1);
              return;
              if (LiftFragment.this.app.getTodayBody().getWeight() <= 0.0F)
              {
                PicoocToast.showToast(LiftFragment.this.getActivity(), "请先称重哦!");
                return;
              }
              if ((LiftFragment.this.app.getTodayBody().getWeight() > 0.0F) && (LiftFragment.this.app.getTodayBody().getBodyFat() <= 0.0F))
              {
                PicoocToast.showToast(LiftFragment.this.getActivity(), "请用正确的方式进行测量，才能看到深度报告哦!");
                return;
              }
            }
            while (LiftFragment.this.changeFragmeng == null);
            LiftFragment.this.changeFragmeng.changeFragment(4);
            return;
          }
          while (LiftFragment.this.changeFragmeng == null);
          if (LiftFragment.this.app.getCurrentRole().getRole_id() > 0L)
          {
            LiftFragment.this.changeFragmeng.changeFragment(6);
            return;
          }
          PicoocToast.showToast(LiftFragment.this.getActivity(), "您现在是访客,无法进入设定哦！");
          return;
          Intent localIntent = new Intent(LiftFragment.this.getActivity(), MyInformation.class);
          LiftFragment.this.startActivity(localIntent);
          LiftFragment.this.getActivity().overridePendingTransition(2130968580, 2130968577);
          return;
        }
        while (LiftFragment.this.changeFragmeng == null);
        if (LiftFragment.this.app.getTodayBody().getWeight() <= 0.0F)
        {
          PicoocToast.showToast(LiftFragment.this.getActivity(), "只有进行首次称重才能修改目标体重哦！");
          return;
        }
        LiftFragment.this.changeFragmeng.changeFragment(7);
        return;
      }
      while (LiftFragment.this.changeFragmeng == null);
      LiftFragment.this.changeFragmeng.changeFragment(8);
      LiftFragment.this.changeImage(1);
    }
  };
  private RadioButton picooc_changeGoal;
  private RadioButton picooc_reportTwo;
  private RadioButton picooc_setting;
  private RadioButton picooc_today;
  private RadioGroup radioGroup;
  private TextView userNic;
  View v;

  private void init()
  {
    this.radioGroup = ((RadioGroup)this.v.findViewById(2131100491));
    this.radioGroup.check(2131099963);
    this.picooc_today = ((RadioButton)this.v.findViewById(2131099963));
    this.picooc_reportTwo = ((RadioButton)this.v.findViewById(2131099966));
    this.picooc_setting = ((RadioButton)this.v.findViewById(2131099968));
    this.picooc_changeGoal = ((RadioButton)this.v.findViewById(2131100492));
    this.headImage = ((AsyncImageView)this.v.findViewById(2131099850));
    this.userNic = ((TextView)this.v.findViewById(2131099962));
    this.userNic.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
    refreshHeadImageAndNic();
    this.linerIcon = ((LinearLayout)this.v.findViewById(2131099961));
    this.picooc_today.setOnClickListener(this.picoocClick);
    this.picooc_reportTwo.setOnClickListener(this.picoocClick);
    this.picooc_setting.setOnClickListener(this.picoocClick);
    this.linerIcon.setOnClickListener(this.picoocClick);
    this.picooc_changeGoal.setOnClickListener(this.picoocClick);
    this.picooc_today.setTypeface(ModUtils.getTypeface(getActivity()));
    this.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
    {
      public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
      {
        LiftFragment.this.changeImage(paramAnonymousInt);
      }
    });
    TextView localTextView = (TextView)this.v.findViewById(2131100493);
    localTextView.setTypeface(ModUtils.getTypeface(getActivity()));
    localTextView.setOnClickListener(this.picoocClick);
  }

  private void refreshHeadImageAndNic()
  {
    if (!this.app.getCurrentRole().getHead_portrait_url().equals(""))
      this.headImage.setUrl(this.app.getCurrentRole().getHead_portrait_url());
    while (true)
    {
      refreshNicName();
      return;
      if (this.app.getCurrentRole().getSex() == 1)
        this.headImage.setImageResource(2130838457);
      else
        this.headImage.setImageResource(2130838460);
    }
  }

  private void refreshNicName()
  {
    if (this.app.getCurrentRole().getFamily_type() == 0)
    {
      this.userNic.setText(this.app.getCurrentRole().getName());
      return;
    }
    if ((this.app.getCurrentRole().getRemark_name() != null) && (!this.app.getCurrentRole().getRemark_name().equals("")))
    {
      this.userNic.setText(this.app.getCurrentRole().getRemark_name());
      return;
    }
    this.userNic.setText(this.app.getCurrentRole().getName());
  }

  public void changeImage(int paramInt)
  {
    if (paramInt == 2131099963)
    {
      this.picooc_today.setCompoundDrawablesWithIntrinsicBounds(2130838008, 0, 0, 0);
      this.picooc_reportTwo.setCompoundDrawablesWithIntrinsicBounds(2130838158, 0, 0, 0);
      this.picooc_setting.setCompoundDrawablesWithIntrinsicBounds(2130838348, 0, 0, 0);
      this.picooc_changeGoal.setCompoundDrawablesWithIntrinsicBounds(2130837574, 0, 0, 0);
      this.picooc_today.setTextColor(Color.parseColor("#94e50e"));
      this.picooc_reportTwo.setTextColor(-1);
      this.picooc_setting.setTextColor(-1);
      this.picooc_changeGoal.setTextColor(-1);
      return;
    }
    if (paramInt == 2131099966)
    {
      this.picooc_today.setCompoundDrawablesWithIntrinsicBounds(2130838007, 0, 0, 0);
      this.picooc_reportTwo.setCompoundDrawablesWithIntrinsicBounds(2130838159, 0, 0, 0);
      this.picooc_setting.setCompoundDrawablesWithIntrinsicBounds(2130838348, 0, 0, 0);
      this.picooc_changeGoal.setCompoundDrawablesWithIntrinsicBounds(2130837574, 0, 0, 0);
      this.picooc_today.setTextColor(-1);
      this.picooc_reportTwo.setTextColor(Color.parseColor("#94e50e"));
      this.picooc_setting.setTextColor(-1);
      this.picooc_changeGoal.setTextColor(-1);
      return;
    }
    if (paramInt == 2131099968)
    {
      this.picooc_today.setCompoundDrawablesWithIntrinsicBounds(2130838007, 0, 0, 0);
      this.picooc_reportTwo.setCompoundDrawablesWithIntrinsicBounds(2130838158, 0, 0, 0);
      this.picooc_setting.setCompoundDrawablesWithIntrinsicBounds(2130838349, 0, 0, 0);
      this.picooc_changeGoal.setCompoundDrawablesWithIntrinsicBounds(2130837574, 0, 0, 0);
      this.picooc_today.setTextColor(-1);
      this.picooc_reportTwo.setTextColor(-1);
      this.picooc_setting.setTextColor(Color.parseColor("#94e50e"));
      this.picooc_changeGoal.setTextColor(-1);
      return;
    }
    if (paramInt == 2131100492)
    {
      this.picooc_today.setCompoundDrawablesWithIntrinsicBounds(2130838007, 0, 0, 0);
      this.picooc_reportTwo.setCompoundDrawablesWithIntrinsicBounds(2130838158, 0, 0, 0);
      this.picooc_setting.setCompoundDrawablesWithIntrinsicBounds(2130838348, 0, 0, 0);
      this.picooc_changeGoal.setCompoundDrawablesWithIntrinsicBounds(2130837575, 0, 0, 0);
      this.picooc_today.setTextColor(-1);
      this.picooc_reportTwo.setTextColor(-1);
      this.picooc_setting.setTextColor(-1);
      this.picooc_changeGoal.setTextColor(Color.parseColor("#94e50e"));
      return;
    }
    this.picooc_today.setChecked(false);
    this.picooc_reportTwo.setChecked(false);
    this.picooc_setting.setChecked(false);
    this.picooc_changeGoal.setChecked(false);
    this.picooc_today.setCompoundDrawablesWithIntrinsicBounds(2130838007, 0, 0, 0);
    this.picooc_reportTwo.setCompoundDrawablesWithIntrinsicBounds(2130838158, 0, 0, 0);
    this.picooc_setting.setCompoundDrawablesWithIntrinsicBounds(2130838348, 0, 0, 0);
    this.picooc_changeGoal.setCompoundDrawablesWithIntrinsicBounds(2130837574, 0, 0, 0);
    this.picooc_today.setTextColor(-1);
    this.picooc_reportTwo.setTextColor(-1);
    this.picooc_setting.setTextColor(-1);
    this.picooc_changeGoal.setTextColor(-1);
  }

  public IChangeFragment getChangeFragmengListener()
  {
    return this.changeFragmeng;
  }

  public void moveHeadGuide()
  {
    this.moveHeadImage = ((AsyncImageView)this.v.findViewById(2131099850));
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        LiftFragment.this.mGuideList = new ArrayList();
        Rect localRect = new Rect();
        LiftFragment.this.getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        int i = localRect.top;
        LiftFragment.this.locationheadImageView = new int[2];
        LiftFragment.this.moveHeadImage.setDrawingCacheEnabled(true);
        LiftFragment.this.moveHeadImage.getLocationInWindow(LiftFragment.this.locationheadImageView);
        LiftFragment.this.mGuide = new GuideModel(LiftFragment.this.moveHeadImage.getDrawingCache(), LiftFragment.this.locationheadImageView[0], LiftFragment.this.locationheadImageView[1]);
        LiftFragment.this.mGuideList.add(LiftFragment.this.mGuide);
        Intent localIntent = new Intent(LiftFragment.this.getActivity(), GuideAct.class);
        localIntent.putExtra("pageId", 1);
        localIntent.putExtra("statusBarHeight", i);
        localIntent.putExtra("guideList", (Serializable)LiftFragment.this.mGuideList);
        LiftFragment.this.startActivity(localIntent);
        LiftFragment.this.getActivity().overridePendingTransition(-1, -1);
      }
    }
    , 200L);
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Log.i("picooc", "onCreate  LiftFragment页面");
    this.app = ((MyApplication)getActivity().getApplicationContext());
    IntentFilter localIntentFilter1 = new IntentFilter("com.picooc.setting.updateRoleMessage");
    getActivity().registerReceiver(this.mReceiver, localIntentFilter1);
    IntentFilter localIntentFilter2 = new IntentFilter("com.picooc.latin.refresh.content");
    getActivity().registerReceiver(this.mReceiver, localIntentFilter2);
    IntentFilter localIntentFilter3 = new IntentFilter("com.picooc.latin.addfamilysuccess");
    getActivity().registerReceiver(this.mReceiver, localIntentFilter3);
    IntentFilter localIntentFilter4 = new IntentFilter("com.picooc.latin.addvisitorsuccess");
    getActivity().registerReceiver(this.mReceiver, localIntentFilter4);
    if (((PicoocActivity3)getActivity()).getSlidingMenu() != null)
      ((PicoocActivity3)getActivity()).getSlidingMenu().setToggleStateChanegListener(new SlidingMenu.OnToggleStateChangeListener()
      {
        public void onToggleStateChange(int paramAnonymousInt, boolean paramAnonymousBoolean)
        {
          if ((SharedPreferenceUtils.isFirstEnterCurPage(LiftFragment.this.getActivity(), "lift_fragment")) && (paramAnonymousInt == 11) && (paramAnonymousBoolean))
          {
            LiftFragment.this.moveHeadGuide();
            SharedPreferenceUtils.resetCurPage(LiftFragment.this.getActivity(), false, "lift_fragment");
          }
        }
      });
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.v = paramLayoutInflater.inflate(2130903195, paramViewGroup, false);
    init();
    changeImage(2131099963);
    return this.v;
  }

  public void onDestroy()
  {
    super.onDestroy();
    getActivity().unregisterReceiver(this.mReceiver);
  }

  public void setChangeFragmentListener(IChangeFragment paramIChangeFragment)
  {
    this.changeFragmeng = paramIChangeFragment;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LiftFragment
 * JD-Core Version:    0.6.2
 */
