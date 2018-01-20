package com.picooc.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.LiftAddFamilyInforAct;
import com.picooc.MyApplication;
import com.picooc.adapter.FamilyAdapter;
import com.picooc.db.OperationDB;
import com.picooc.domain.RoleBin;
import com.picooc.domain.UserBin;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.anyncImageView.AsyncImageView;
import java.util.Iterator;
import java.util.List;
import org.achartengine.tools.ModUtils;

public class RightFragment extends Fragment
{
  AnimUtils anima;
  MyApplication app;
  private LinearLayout downLiner;
  Intent i;
  private Boolean isshow = Boolean.valueOf(true);
  private ListView listview;
  private final BroadcastReceiver mReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      String str = paramAnonymousIntent.getAction();
      "com.picooc.latin.addfamilysuccess".equals(str);
      if ("com.picooc.setting.updateRoleMessage".equals(str))
      {
        RightFragment.this.app.getCurrentRole().getRole_id();
        RightFragment.this.mainRole.getRole_id();
      }
    }
  };
  private RoleBin mainRole;
  private AsyncImageView mainRoleHead;
  private TextView mainRoleName;
  private View.OnClickListener picoocClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      default:
        return;
      case 2131100070:
        if (RightFragment.this.isshow.booleanValue())
        {
          RightFragment.this.isshow = Boolean.valueOf(false);
          RightFragment.this.anima.downMove(RightFragment.this.listview, 0, -8 + RightFragment.this.downLiner.getHeight(), 400);
          RightFragment.this.anima.showAnima(RightFragment.this.downLiner, 400L);
          RightFragment.this.anima.missAnima(RightFragment.this.viewline, 400L);
          RightFragment.this.downLiner.setFocusable(true);
          RightFragment.this.downLiner.setEnabled(true);
          RightFragment.this.downLiner.setClickable(true);
          return;
        }
        RightFragment.this.isshow = Boolean.valueOf(true);
        RightFragment.this.anima.upMove(RightFragment.this.listview, -8 + RightFragment.this.downLiner.getHeight(), 0, 400);
        RightFragment.this.anima.missAnima(RightFragment.this.downLiner, 400L);
        RightFragment.this.anima.showAnima(RightFragment.this.viewline, 400L);
        RightFragment.this.downLiner.setFocusable(false);
        RightFragment.this.downLiner.setEnabled(false);
        RightFragment.this.downLiner.setClickable(false);
        return;
      case 2131100072:
        RightFragment.this.i = new Intent(RightFragment.this.getActivity(), LiftAddFamilyInforAct.class);
        RightFragment.this.i.putExtra("key", ModUtils.FAMLYIN);
        RightFragment.this.startActivity(RightFragment.this.i);
        RightFragment.this.getActivity().overridePendingTransition(2130968580, 2130968577);
        return;
      case 2131099714:
      }
      RightFragment.this.app.clearVisitorData();
      RightFragment.this.i = new Intent(RightFragment.this.getActivity(), LiftAddFamilyInforAct.class);
      RightFragment.this.i.putExtra("key", ModUtils.visitor);
      RightFragment.this.startActivity(RightFragment.this.i);
      RightFragment.this.getActivity().overridePendingTransition(2130968580, 2130968577);
    }
  };
  private List<RoleBin> roles;
  private RelativeLayout titelRelay;
  private View v;
  private View viewline;

  public void invit()
  {
    this.anima = new AnimUtils(getActivity());
    View localView = LayoutInflater.from(getActivity()).inflate(2130903049, null);
    LinearLayout localLinearLayout = (LinearLayout)localView.findViewById(2131099714);
    this.listview.addFooterView(localView);
    this.downLiner = ((LinearLayout)this.v.findViewById(2131100072));
    this.anima.missAnima(this.downLiner, 400L);
    this.titelRelay = ((RelativeLayout)this.v.findViewById(2131100070));
    this.viewline = this.v.findViewById(2131099998);
    this.mainRoleName = ((TextView)this.v.findViewById(2131100068));
    this.mainRoleName.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
    this.mainRoleHead = ((AsyncImageView)this.v.findViewById(2131100067));
    final FamilyAdapter localFamilyAdapter = new FamilyAdapter(getActivity(), 2130903048, this.roles);
    this.listview.setAdapter(localFamilyAdapter);
    this.titelRelay.setOnClickListener(this.picoocClick);
    this.downLiner.setOnClickListener(this.picoocClick);
    localLinearLayout.setOnClickListener(this.picoocClick);
    this.downLiner.setFocusable(false);
    this.downLiner.setEnabled(false);
    this.downLiner.setClickable(false);
    this.listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        localFamilyAdapter.setColor(1, paramAnonymousInt);
        localFamilyAdapter.notifyDataSetChanged();
      }
    });
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    IntentFilter localIntentFilter1 = new IntentFilter("com.picooc.latin.addfamilysuccess");
    getActivity().registerReceiver(this.mReceiver, localIntentFilter1);
    IntentFilter localIntentFilter2 = new IntentFilter("com.picooc.setting.updateRoleMessage");
    getActivity().registerReceiver(this.mReceiver, localIntentFilter2);
    this.app = ((MyApplication)getActivity().getApplicationContext());
    UserBin localUserBin = this.app.getCurrentUser();
    this.roles = OperationDB.selectAllRoleByUserId(getActivity(), localUserBin.getUser_id());
    Iterator localIterator = this.roles.iterator();
    RoleBin localRoleBin;
    do
    {
      if (!localIterator.hasNext())
        return;
      localRoleBin = (RoleBin)localIterator.next();
    }
    while (localRoleBin.getRole_id() != localUserBin.getRole_id());
    this.mainRole = localRoleBin;
    this.roles.remove(localRoleBin);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    Log.i("picooc", "picooc 右侧抽屉 onCreateView");
    this.v = paramLayoutInflater.inflate(2130903124, paramViewGroup, false);
    this.listview = ((ListView)this.v.findViewById(2131099834));
    invit();
    return this.v;
  }

  public void onDestroy()
  {
    getActivity().unregisterReceiver(this.mReceiver);
    super.onDestroy();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     RightFragment
 * JD-Core Version:    0.6.2
 */
