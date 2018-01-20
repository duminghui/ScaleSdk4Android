package com.picooc.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.BindingPhoneAct;
import com.picooc.FamilyContactsAct;
import com.picooc.InviteAct;
import com.picooc.LiftAddFamilyInforAct;
import com.picooc.MyApplication;
import com.picooc.PicoocActivity3;
import com.picooc.db.OperationDB;
import com.picooc.domain.RoleBin;
import com.picooc.domain.UserBin;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.ViewExpandAnimation;
import com.picooc.widget.anyncImageView.AsyncImageView;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import com.picooc.widget.picoocProgress.PicoocAlertDialog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.achartengine.tools.ModUtils;
import org.json.JSONObject;

public class RightFragment2 extends Fragment
{
  private static final int COLOR = Color.parseColor("#94e50e");
  private AnimUtils anima;
  private Animation animation;
  private MyApplication app;
  private TextView bootom_push_count;
  private Button bt = null;
  String btName;
  private List<View> cacheRemoteRoleView;
  private View.OnClickListener changeRemarkNameClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      Button localButton = (Button)paramAnonymousView;
      RightFragment2.this.bt = localButton;
      ViewGroup localViewGroup = (ViewGroup)paramAnonymousView.getTag();
      final EditText localEditText = (EditText)localViewGroup.findViewById(2131099709);
      RightFragment2.this.btName = localEditText.getText().toString();
      RightFragment2.this.inputManager = ((InputMethodManager)localEditText.getContext().getSystemService("input_method"));
      if (localButton.getText().toString().equals("修改备注名"))
      {
        localButton.setText("保存");
        TextView localTextView = (TextView)localViewGroup.findViewById(2131099710);
        new AnimUtils(RightFragment2.this.getActivity()).missAnima(localTextView, 300L);
        new AnimUtils(RightFragment2.this.getActivity()).downMove(localEditText, 0, (int)(1.2D * (localEditText.getHeight() / 2)), 300);
        new Handler(RightFragment2.this.getActivity().getMainLooper()).postDelayed(new Runnable()
        {
          public void run()
          {
            localEditText.setSelection(localEditText.getText().length());
            localEditText.setFocusable(true);
            localEditText.setCursorVisible(true);
            localEditText.setFocusableInTouchMode(true);
            localEditText.requestFocus();
            RightFragment2.this.edit = localEditText;
            RightFragment2.this.inputManager.showSoftInput(localEditText, 0);
          }
        }
        , 400L);
        return;
      }
      if ((localEditText.getText() != null) && (!"".equals(localEditText.getText().toString())))
      {
        PicoocLoading.showLoadingDialog(RightFragment2.this.getActivity());
        RightFragment2.this.inputManager.hideSoftInputFromWindow(localEditText.getWindowToken(), 0);
        localEditText.setFocusable(false);
        localEditText.setClickable(false);
        localEditText.setFocusableInTouchMode(false);
        RequestEntity localRequestEntity = new RequestEntity("changeRemoteRoleRemarkName", null);
        RightFragment2.this.remarkRole = ((RoleBin)localButton.getTag(2131099660));
        localRequestEntity.addParam("myUserId", Long.valueOf(RightFragment2.this.remarkRole.getUser_id()));
        localRequestEntity.addParam("remoteUserId", Long.valueOf(RightFragment2.this.remarkRole.getRemote_user_id()));
        localRequestEntity.addParam("roleId", Long.valueOf(RightFragment2.this.remarkRole.getRole_id()));
        String str = localEditText.getText().toString();
        RightFragment2.this.remarkRole.setRemark_name(str);
        localRequestEntity.addParam("remarkName", str);
        HttpUtils.getJson(RightFragment2.this.getActivity(), localRequestEntity, RightFragment2.this.httpHandler);
        OperationDB.updateRoleDB(RightFragment2.this.getActivity(), RightFragment2.this.remarkRole);
        RightFragment2.this.remarkButton = paramAnonymousView;
        return;
      }
      PicoocToast.showToast(RightFragment2.this.getActivity(), "输入一个备注名吧:)");
    }
  };
  private ArrayList<View> deleteButtonList;
  private ViewGroup deleteItem;
  private View.OnClickListener deleteOnclick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      paramAnonymousView.setVisibility(8);
      RightFragment2.this.deleteButtonList.remove(paramAnonymousView);
      final ViewGroup localViewGroup = (ViewGroup)paramAnonymousView.getTag();
      String str = ((TextView)localViewGroup.findViewById(2131099709)).getText().toString();
      final PicoocAlertDialog localPicoocAlertDialog = new PicoocAlertDialog("您确定要删除" + str + "吗？", "取消", "确定", RightFragment2.this.getActivity());
      localPicoocAlertDialog.showAlerDialogTwo(new View.OnClickListener()
      {
        public void onClick(View paramAnonymous2View)
        {
          RightFragment2.this.deleteItem = localViewGroup;
          if (RightFragment2.this.deleteRole != null)
          {
            PicoocLoading.showLoadingDialog(RightFragment2.this.getActivity());
            RequestEntity localRequestEntity = new RequestEntity("deleteRole", "2.0");
            localRequestEntity.addParam("myUserID", Long.valueOf(RightFragment2.this.deleteRole.getUser_id()));
            localRequestEntity.addParam("roleID", Long.valueOf(RightFragment2.this.deleteRole.getRole_id()));
            localRequestEntity.addParam("remoteUserId", Long.valueOf(RightFragment2.this.deleteRole.getRemote_user_id()));
            HttpUtils.getJson(RightFragment2.this.getActivity(), localRequestEntity, RightFragment2.this.httpHandler);
          }
          localPicoocAlertDialog.dismissAlertDialog();
        }
      }
      , new View.OnClickListener()
      {
        public void onClick(View paramAnonymous2View)
        {
          localPicoocAlertDialog.dismissAlertDialog();
        }
      });
    }
  };
  private RoleBin deleteRole;
  private RelativeLayout downLiner;
  private EditText edit = null;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(RightFragment2.this.getActivity(), paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(RightFragment2.this.getActivity(), localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      Log.i("http", "成功了:" + localResponseEntity.toString());
      String str = localResponseEntity.getMethod();
      if (str.equals("deleteRole"))
      {
        OperationDB.deleteRoleByRoleId(RightFragment2.this.getActivity(), RightFragment2.this.deleteRole.getRole_id());
        if (RightFragment2.this.app.getCurrentRole().getRole_id() == RightFragment2.this.deleteRole.getRole_id())
        {
          RightFragment2.this.app.clearAllData();
          SharedPreferenceUtils.putValue(RightFragment2.this.getActivity(), "user-Info", "role_id", Long.valueOf(RightFragment2.this.app.getCurrentRole().getRole_id()));
          RightFragment2.this.app.setRole_id(RightFragment2.this.app.getCurrentRole().getRole_id());
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              Intent localIntent = new Intent();
              localIntent.putExtra("closeMenuOrNot", false);
              localIntent.setAction("com.picooc.latin.refresh.content");
              RightFragment2.this.getActivity().sendBroadcast(localIntent);
              RightFragment2.this.changeMainIcon(true);
            }
          }
          , 300L);
        }
        com.picooc.animation.AnimationUtils.animHideShowView(RightFragment2.this.deleteItem, new Animation.AnimationListener()
        {
          public void onAnimationEnd(Animation paramAnonymous2Animation)
          {
            RightFragment2.this.roles.remove(RightFragment2.this.deleteRole);
            Log.i("picooc", "delete---------------uid==" + RightFragment2.this.deleteRole.getRemote_user_id());
            OperationDB.updatePhone_book(RightFragment2.this.getActivity(), RightFragment2.this.deleteRole.getRemote_user_id(), 0);
            RightFragment2.this.deleteRole = null;
          }

          public void onAnimationRepeat(Animation paramAnonymous2Animation)
          {
          }

          public void onAnimationStart(Animation paramAnonymous2Animation)
          {
          }
        }
        , RightFragment2.this.deleteItem.getHeight(), false, 400);
      }
      do
      {
        do
          return;
        while (!str.equals("changeRemoteRoleRemarkName"));
        if (RightFragment2.this.remarkButton != null)
        {
          RightFragment2.this.remarkButton.setVisibility(8);
          RightFragment2.this.remarkRole = null;
          RightFragment2.this.remarkButton = null;
        }
      }
      while ((RightFragment2.this.cacheRemoteRoleView == null) || (RightFragment2.this.remarkButton == null));
      RightFragment2.this.cacheRemoteRoleView.remove(RightFragment2.this.remarkButton.getParent());
    }
  };
  private Intent i;
  Button imageButton = null;
  InputMethodManager inputManager;
  private TextView invitTiShi;
  private Boolean isLongOclick = Boolean.valueOf(true);
  private boolean isOpe = true;
  private boolean isOpen = true;
  private Boolean isshow = Boolean.valueOf(true);
  private RadioGroup listview;
  private float mDensity = 0.0F;
  private View mFocusBg;
  private int mLcdWidth = 0;
  private View mPhotoParent;
  private final BroadcastReceiver mReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      String str1 = paramAnonymousIntent.getAction();
      if ("com.picooc.latin.addfamilysuccess".equals(str1))
      {
        RightFragment2.this.roles.add(RightFragment2.this.app.getCurrentRole());
        View localView4 = RightFragment2.this.getRoleItemByRoleBin(RightFragment2.this.app.getCurrentRole(), TypefaceUtils.getTypeface(RightFragment2.this.getActivity(), null));
        RightFragment2.this.lightCurrentRole(localView4);
        RightFragment2.this.listview.addView(localView4);
        if (!RightFragment2.this.isOpen)
          RightFragment2.this.dismissOrShowOne(RightFragment2.this.downLiner);
      }
      while (true)
      {
        return;
        if ("com.picooc.setting.updateRoleMessage".equals(str1))
        {
          if (RightFragment2.this.app.getCurrentRole().getRole_id() == RightFragment2.this.app.getMainRole().getRole_id())
          {
            RightFragment2.this.refreshMainRoleHeadImageAndName();
            return;
          }
          RightFragment2.this.refreshRoleHeadImageAndName(RightFragment2.this.app.getCurrentRole().getRole_id());
          return;
        }
        if ("com.picooc.local.family.upto.remote.family".equals(str1))
        {
          int j = RightFragment2.this.listview.getChildCount();
          RoleBin localRoleBin2 = RightFragment2.this.app.getCurrentRole();
          for (int k = 0; k < j; k++)
          {
            View localView3 = RightFragment2.this.listview.getChildAt(k);
            RoleBin localRoleBin3 = (RoleBin)localView3.getTag();
            if (localRoleBin2.getRole_id() == localRoleBin3.getRole_id())
            {
              ImageView localImageView = (ImageView)localView3.findViewById(2131099708);
              if (localRoleBin2.getFamily_type() == 1)
                localImageView.setImageResource(2130837709);
              while (true)
              {
                localView3.setTag(localRoleBin2);
                return;
                localImageView.setImageResource(2130837705);
              }
            }
          }
        }
        else
        {
          if ("com.picooc.receive.push.refresh.ui".equals(str1))
          {
            RightFragment2.this.updateCount();
            return;
          }
          if ("com.picooc.invitation.refresh.ui".equals(str1))
          {
            RightFragment2.this.invitRole();
            RightFragment2.this.invit();
            return;
          }
          if ("com.picooc.slidingmenu.close.right.menu".equals(str1))
          {
            Log.i("picooc", "接收到BROADCAST_CLOSE_RIGHT_MENU广播了");
            if (!RightFragment2.this.isOpen)
              RightFragment2.this.dismissOrShowOne(RightFragment2.this.downLiner);
            Iterator localIterator;
            if (RightFragment2.this.cacheRemoteRoleView != null)
              localIterator = RightFragment2.this.cacheRemoteRoleView.iterator();
            while (true)
            {
              if (!localIterator.hasNext())
              {
                RightFragment2.this.cacheRemoteRoleView.clear();
                RightFragment2.this.hideAllDelBtns();
                return;
              }
              View localView2 = (View)localIterator.next();
              RoleBin localRoleBin1 = (RoleBin)localView2.getTag(2131099659);
              EditText localEditText = (EditText)localView2.findViewById(2131099709);
              localEditText.setFocusable(false);
              localEditText.setClickable(false);
              localEditText.setFocusableInTouchMode(false);
              Button localButton = (Button)localView2.findViewById(2131099711);
              localButton.setVisibility(8);
              if (!localButton.getText().toString().equals("保存"))
                localView2.findViewById(2131099710).setVisibility(8);
              String str2 = localEditText.getText().toString();
              if (str2.equals(""))
              {
                str2 = localRoleBin1.getName();
                localEditText.setText(str2);
              }
              localRoleBin1.setRemark_name(str2);
              OperationDB.updateRoleDB(RightFragment2.this.getActivity(), localRoleBin1);
              RightFragment2.this.remarkRole = null;
              RightFragment2.this.remarkButton = null;
              RequestEntity localRequestEntity = new RequestEntity("changeRemoteRoleRemarkName", null);
              localRequestEntity.addParam("myUserId", Long.valueOf(localRoleBin1.getUser_id()));
              localRequestEntity.addParam("remoteUserId", Long.valueOf(localRoleBin1.getRemote_user_id()));
              localRequestEntity.addParam("roleId", Long.valueOf(localRoleBin1.getRole_id()));
              localRequestEntity.addParam("remarkName", str2);
              HttpUtils.getJson(RightFragment2.this.getActivity(), localRequestEntity, RightFragment2.this.httpHandler);
            }
          }
          if ("com.picooc.latin.addvisitorsuccess".equals(str1))
          {
            RightFragment2.this.changeMainIcon(false);
            RightFragment2.this.onFocused(RightFragment2.this.mFocusBg, RightFragment2.this.mRoleName, RightFragment2.this.mPhotoParent, false);
            return;
          }
          if ("com.picooc.some.role.has.new.weighting".equals(str1))
          {
            Log.i("error", "有新测量了" + paramAnonymousIntent.getLongExtra("role_id", 0L));
            for (int i = 0; i < RightFragment2.this.listview.getChildCount(); i++)
            {
              View localView1 = RightFragment2.this.listview.getChildAt(i);
              if ((((RoleBin)localView1.getTag(2131099659)).getRole_id() == paramAnonymousIntent.getLongExtra("role_id", 0L)) && (localView1.findViewById(2131099711).getVisibility() == 8))
                localView1.findViewById(2131099712).setVisibility(0);
            }
          }
        }
      }
    }
  };
  private TextView mRoleName;
  private AsyncImageView mainRoleHead;
  private TextView mainRoleName;
  private LinearLayout me;
  private View.OnClickListener picoocClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      default:
        return;
      case 2131100070:
        if (RightFragment2.this.isOpe)
        {
          RightFragment2.this.isOpe = false;
          RightFragment2.this.dismissOrShowOne(RightFragment2.this.downLiner);
          return;
        }
        RightFragment2.this.isOpe = true;
        RightFragment2.this.dismissOrShowOne(RightFragment2.this.downLiner);
        return;
      case 2131100074:
        if (1 + RightFragment2.this.roles.size() >= 10)
        {
          PicoocToast.showToast(RightFragment2.this.getActivity(), "超过家庭成员上限！");
          return;
        }
        RightFragment2.this.i = new Intent(RightFragment2.this.getActivity(), LiftAddFamilyInforAct.class);
        RightFragment2.this.i.putExtra("key", ModUtils.FAMLYIN);
        RightFragment2.this.startActivity(RightFragment2.this.i);
        if (!RightFragment2.this.isOpen)
          RightFragment2.this.dismissOrShowOne(RightFragment2.this.downLiner);
        RightFragment2.this.getActivity().overridePendingTransition(2130968580, 2130968577);
        return;
      case 2131100075:
        if (1 + RightFragment2.this.roles.size() >= 10)
        {
          PicoocToast.showToast(RightFragment2.this.getActivity(), "超过家庭成员上限！");
          return;
        }
        if (!ModUtils.isHavePhone(RightFragment2.this.app.getCurrentUser()).booleanValue());
        for (RightFragment2.this.i = new Intent(RightFragment2.this.getActivity(), BindingPhoneAct.class); ; RightFragment2.this.i = new Intent(RightFragment2.this.getActivity(), FamilyContactsAct.class))
        {
          RightFragment2.this.i.putExtra("key", ModUtils.FAMLYIN);
          RightFragment2.this.startActivity(RightFragment2.this.i);
          if (!RightFragment2.this.isOpen)
            RightFragment2.this.dismissOrShowOne(RightFragment2.this.downLiner);
          RightFragment2.this.getActivity().overridePendingTransition(2130968580, 2130968577);
          return;
        }
      case 2131100077:
        RightFragment2.this.i = new Intent(RightFragment2.this.getActivity(), InviteAct.class);
        RightFragment2.this.startActivity(RightFragment2.this.i);
        if (!RightFragment2.this.isOpen)
          RightFragment2.this.dismissOrShowOne(RightFragment2.this.downLiner);
        RightFragment2.this.getActivity().overridePendingTransition(2130968580, 2130968577);
        return;
      case 2131099714:
      }
      RightFragment2.this.app.clearVisitorData();
      RightFragment2.this.i = new Intent(RightFragment2.this.getActivity(), LiftAddFamilyInforAct.class);
      RightFragment2.this.i.putExtra("key", ModUtils.visitor);
      RightFragment2.this.startActivity(RightFragment2.this.i);
      if (!RightFragment2.this.isOpen)
        RightFragment2.this.dismissOrShowOne(RightFragment2.this.downLiner);
      RightFragment2.this.getActivity().overridePendingTransition(2130968580, 2130968577);
    }
  };
  private TextView push_count;
  private View remarkButton;
  private RoleBin remarkRole;
  private View.OnClickListener roleItemClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if ((paramAnonymousView instanceof EditText))
        paramAnonymousView = (View)paramAnonymousView.getParent().getParent().getParent();
      if (RightFragment2.this.edit != null)
      {
        RightFragment2.this.inputManager.hideSoftInputFromWindow(RightFragment2.this.edit.getWindowToken(), 0);
        RightFragment2.this.edit = null;
      }
      if (RightFragment2.this.isLongOclick.booleanValue())
      {
        Button localButton = (Button)paramAnonymousView.findViewById(2131099713);
        if ((localButton != null) && (localButton.isShown()))
        {
          localButton.setVisibility(4);
          RightFragment2.this.deleteButtonList.remove(localButton);
          return;
        }
        RoleBin localRoleBin2 = (RoleBin)paramAnonymousView.getTag();
        if (RightFragment2.this.app.getRole_id() == localRoleBin2.getRole_id())
        {
          ((PicoocActivity3)RightFragment2.this.getActivity()).toggleRightMenu();
          return;
        }
        EditText localEditText = (EditText)paramAnonymousView.findViewById(2131099709);
        SharedPreferenceUtils.putValue(RightFragment2.this.getActivity(), "user-Info", "role_id", Long.valueOf(localRoleBin2.getRole_id()));
        RightFragment2.this.app.clearAllData();
        RightFragment2.this.app.setRole_id(localRoleBin2.getRole_id());
        final View localView1 = paramAnonymousView;
        Object localObject1 = localEditText;
        System.out.println("v.getId()==================" + paramAnonymousView.getId());
        if (paramAnonymousView.getId() == 2131099786)
          localObject1 = RightFragment2.this.mainRoleName;
        for (View localView2 = (View)RightFragment2.this.mainRoleHead.getParent(); ; localView2 = paramAnonymousView.findViewById(2131099706))
        {
          final Object localObject2 = localObject1;
          final View localView3 = localView2;
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              RightFragment2.this.onFocused(RightFragment2.this.mFocusBg, RightFragment2.this.mRoleName, RightFragment2.this.mPhotoParent, false);
              RightFragment2.this.onFocused(localView1, localObject2, localView3, true);
              RightFragment2.this.mFocusBg = localView1;
              RightFragment2.this.mRoleName = localObject2;
              RightFragment2.this.mPhotoParent = localView3;
              Intent localIntent = new Intent();
              localIntent.setAction("com.picooc.latin.refresh.content");
              RightFragment2.this.getActivity().sendBroadcast(localIntent);
            }
          }
          , 300L);
          if ((paramAnonymousView.findViewById(2131099712) == null) || (paramAnonymousView.findViewById(2131099712).getVisibility() != 0))
            break;
          paramAnonymousView.findViewById(2131099712).setVisibility(8);
          SharedPreferenceUtils.removeKey(RightFragment2.this.getActivity(), "NEW_WEIGHTING_RECORD", ((RoleBin)paramAnonymousView.getTag(2131099659)).getRole_id());
          if (SharedPreferenceUtils.getCount(RightFragment2.this.getActivity(), "NEW_WEIGHTING_RECORD") != 0)
            break;
          Intent localIntent3 = new Intent("com.picooc.new.weighting.has.reduce");
          localIntent3.putExtra("count", 0);
          RightFragment2.this.getActivity().sendBroadcast(localIntent3);
          return;
        }
      }
      RoleBin localRoleBin1 = (RoleBin)paramAnonymousView.getTag();
      if (RightFragment2.this.app.getRole_id() == localRoleBin1.getRole_id())
        ((PicoocActivity3)RightFragment2.this.getActivity()).toggleRightMenu();
      while (true)
      {
        RightFragment2.this.isLongOclick = Boolean.valueOf(true);
        return;
        SharedPreferenceUtils.putValue(RightFragment2.this.getActivity(), "user-Info", "role_id", Long.valueOf(localRoleBin1.getRole_id()));
        RightFragment2.this.app.clearAllData();
        RightFragment2.this.app.setRole_id(localRoleBin1.getRole_id());
        if ((paramAnonymousView.findViewById(2131099712) != null) && (paramAnonymousView.findViewById(2131099712).getVisibility() == 0))
        {
          paramAnonymousView.findViewById(2131099712).setVisibility(8);
          SharedPreferenceUtils.removeKey(RightFragment2.this.getActivity(), "NEW_WEIGHTING_RECORD", ((RoleBin)paramAnonymousView.getTag(2131099659)).getRole_id());
          if (SharedPreferenceUtils.getCount(RightFragment2.this.getActivity(), "NEW_WEIGHTING_RECORD") == 0)
          {
            Intent localIntent2 = new Intent("com.picooc.new.weighting.has.reduce");
            localIntent2.putExtra("count", 0);
            RightFragment2.this.getActivity().sendBroadcast(localIntent2);
          }
        }
        Intent localIntent1 = new Intent();
        localIntent1.setAction("com.picooc.latin.refresh.content");
        RightFragment2.this.getActivity().sendBroadcast(localIntent1);
      }
    }
  };
  private View.OnTouchListener roleTouch = new View.OnTouchListener()
  {
    private float moveX = 0.0F;
    private float startX = 0.0F;

    public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
    {
      if (paramAnonymousMotionEvent.getAction() == 0)
      {
        float f = paramAnonymousMotionEvent.getX();
        this.startX = f;
        this.moveX = f;
      }
      do
      {
        do
          while (true)
          {
            return false;
            if (paramAnonymousMotionEvent.getAction() != 1)
              break;
            this.startX = 0.0F;
            this.moveX = 0.0F;
            paramAnonymousView.findViewById(2131099709).clearFocus();
            paramAnonymousView.requestFocus();
          }
        while (paramAnonymousMotionEvent.getAction() != 2);
        this.moveX = paramAnonymousMotionEvent.getX();
      }
      while (this.moveX >= this.startX - 4.0F);
      Button localButton = (Button)paramAnonymousView.findViewById(2131099713);
      RightFragment2.this.scalce(localButton, paramAnonymousView);
      RightFragment2.this.hideAllDelBtns();
      RightFragment2.this.deleteRole = ((RoleBin)paramAnonymousView.getTag(2131099659));
      if (!RightFragment2.this.deleteButtonList.contains(localButton))
      {
        RightFragment2.this.deleteButtonList.add(localButton);
        localButton.clearFocus();
      }
      this.startX = 0.0F;
      this.moveX = 0.0F;
      return true;
    }
  };
  private List<RoleBin> roles;
  private RelativeLayout titelRelay;
  private TextView tv_add_local_home;
  private TextView tv_add_remote_home;
  private LinearLayout tv_invite;
  private LinearLayout under;
  float ux = 0.0F;
  private View v;
  private View viewline;
  float x = 0.0F;

  private void changeMainIcon(boolean paramBoolean)
  {
    this.mFocusBg = this.v.findViewById(2131099786);
    this.mRoleName = this.mainRoleName;
    this.mPhotoParent = ((View)this.v.findViewById(2131100067).getParent());
    onFocused(this.mFocusBg, this.mRoleName, this.mPhotoParent, paramBoolean);
  }

  private View getRoleItemByRoleBin(RoleBin paramRoleBin, Typeface paramTypeface)
  {
    View localView = LayoutInflater.from(getActivity()).inflate(2130903048, null);
    EditText localEditText = (EditText)localView.findViewById(2131099709);
    localEditText.setTypeface(paramTypeface);
    ImageView localImageView = (ImageView)localView.findViewById(2131099708);
    AsyncImageView localAsyncImageView;
    if (paramRoleBin.getFamily_type() == 1)
    {
      localImageView.setImageResource(2130837709);
      if (!paramRoleBin.getRemark_name().equals(""))
      {
        localEditText.setText(paramRoleBin.getRemark_name());
        if (((Boolean)SharedPreferenceUtils.getValue(getActivity(), "NEW_WEIGHTING_RECORD", paramRoleBin.getRole_id(), Boolean.class)).booleanValue())
          localView.findViewById(2131099712).setVisibility(0);
        localEditText.setTag(paramRoleBin);
        localEditText.setOnClickListener(this.roleItemClick);
        localAsyncImageView = (AsyncImageView)localView.findViewById(2131099707);
        if (paramRoleBin.getHead_portrait_url().equals(""))
          break label327;
        localAsyncImageView.setUrl(paramRoleBin.getHead_portrait_url());
      }
    }
    while (true)
    {
      localView.setTag(paramRoleBin);
      localView.setTag(2131099659, paramRoleBin);
      localView.setOnClickListener(this.roleItemClick);
      Button localButton1 = (Button)localView.findViewById(2131099713);
      localButton1.setOnClickListener(this.deleteOnclick);
      localButton1.setTag(localView);
      localView.setOnTouchListener(this.roleTouch);
      return localView;
      localEditText.setText(paramRoleBin.getName());
      ((TextView)localView.findViewById(2131099710)).setVisibility(0);
      Button localButton2 = (Button)localView.findViewById(2131099711);
      localButton2.setVisibility(0);
      localButton2.setTag(localView);
      localButton2.setTag(2131099660, paramRoleBin);
      localButton2.setOnClickListener(this.changeRemarkNameClick);
      break;
      localImageView.setImageResource(2130837705);
      localEditText.setText(paramRoleBin.getName());
      break;
      label327: if (paramRoleBin.getSex() == 1)
        localAsyncImageView.setDefaultImageResource(2130838457);
    }
  }

  private void hideAllDelBtns()
  {
    Iterator localIterator = this.deleteButtonList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      View localView = (View)localIterator.next();
      if (localView.getVisibility() != 8)
      {
        localView.setVisibility(8);
        this.deleteButtonList.remove(localView);
      }
    }
  }

  private void initRoleList()
  {
    this.listview.removeAllViews();
    int j = 1;
    long l = this.app.getCurrentRole().getRole_id();
    Typeface localTypeface = TypefaceUtils.getTypeface(getActivity(), null);
    for (int k = 0; ; k++)
    {
      if (k >= this.roles.size())
      {
        System.out.println("currId =" + l);
        if ((j != 0) && (l != -1L))
          changeMainIcon(true);
        return;
      }
      RoleBin localRoleBin = (RoleBin)this.roles.get(k);
      View localView = getRoleItemByRoleBin(localRoleBin, localTypeface);
      if (localRoleBin.getRole_id() == l)
      {
        lightCurrentRole(localView);
        j = 0;
      }
      this.listview.addView(localView);
      if ((localRoleBin.getFamily_type() == 1) && (localRoleBin.getRemark_name().equals("")))
      {
        if (this.cacheRemoteRoleView == null)
          this.cacheRemoteRoleView = new ArrayList();
        this.cacheRemoteRoleView.add(localView);
      }
    }
  }

  private void lightCurrentRole(View paramView)
  {
    TextView localTextView = (TextView)paramView.findViewById(2131099709);
    View localView = paramView.findViewById(2131099706);
    onFocused(this.mFocusBg, this.mRoleName, this.mPhotoParent, false);
    onFocused(paramView, localTextView, localView, true);
    this.mFocusBg = paramView;
    this.mRoleName = localTextView;
    this.mPhotoParent = localView;
  }

  private void onFocused(View paramView1, TextView paramTextView, View paramView2, boolean paramBoolean)
  {
    if ((paramView1 == null) || (paramTextView == null) || (paramView2 == null))
      return;
    if (paramBoolean)
    {
      paramView1.setBackgroundColor(getResources().getColor(2131165210));
      paramTextView.setTextColor(COLOR);
      paramView2.setBackgroundResource(2130838465);
      return;
    }
    paramView1.setBackgroundColor(getResources().getColor(2131165198));
    paramTextView.setTextColor(getResources().getColor(2131165207));
    paramView2.setBackgroundResource(2130838464);
  }

  private void refreshMainRoleHeadImageAndName()
  {
    if (this.app.getMainRole() != null)
    {
      this.mainRoleName.setText(this.app.getMainRole().getName());
      if (this.app.getMainRole().getHead_portrait_url().equals(""))
        break label64;
      this.mainRoleHead.setUrl(this.app.getMainRole().getHead_portrait_url());
    }
    label64: 
    while (this.app.getMainRole().getSex() != 1)
      return;
    this.mainRoleHead.setDefaultImageResource(2130838457);
  }

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

  private void refreshRoleHeadImageAndName(long paramLong)
  {
    int j = 0;
    int k = this.roles.size();
    int m = 0;
    label22: AsyncImageView localAsyncImageView;
    if (j >= k)
    {
      ViewGroup localViewGroup = (ViewGroup)this.listview.getChildAt(m);
      refreshNicName((TextView)localViewGroup.findViewById(2131099709));
      localAsyncImageView = (AsyncImageView)localViewGroup.findViewById(2131099707);
      if (this.app.getCurrentRole().getHead_portrait_url().equals(""))
        break label132;
      localAsyncImageView.setUrl(this.app.getCurrentRole().getHead_portrait_url());
    }
    label132: 
    while (this.app.getCurrentRole().getSex() != 1)
    {
      return;
      if (((RoleBin)this.roles.get(j)).getRole_id() == paramLong)
      {
        m = j;
        break label22;
      }
      j++;
      break;
    }
    localAsyncImageView.setDefaultImageResource(2130838457);
  }

  private void toggleAddFamily(int paramInt)
  {
    if (this.isshow.booleanValue())
    {
      this.isshow = Boolean.valueOf(false);
      this.anima.downMove(this.under, 0, -8 + this.downLiner.getHeight(), paramInt);
      this.anima.showAnima(this.downLiner, paramInt);
      this.anima.missAnima(this.viewline, paramInt);
      this.downLiner.setFocusable(true);
      this.downLiner.setEnabled(true);
      this.downLiner.setClickable(true);
      return;
    }
    this.isshow = Boolean.valueOf(true);
    this.anima.upMove(this.under, -8 + this.downLiner.getHeight(), 0, paramInt);
    this.anima.missAnima(this.downLiner, paramInt);
    this.anima.showAnima(this.viewline, paramInt);
    this.downLiner.setFocusable(false);
    this.downLiner.setEnabled(false);
    this.downLiner.setClickable(false);
  }

  public void dismissOrShowOne(View paramView)
  {
    try
    {
      paramView.startAnimation(new ViewExpandAnimation(paramView));
      if (this.isOpen)
      {
        this.isOpen = false;
        this.push_count.setVisibility(8);
      }
      while (true)
      {
        return;
        this.isOpen = true;
        this.push_count.setVisibility(0);
      }
    }
    finally
    {
    }
  }

  public void invit()
  {
    this.me = ((LinearLayout)this.v.findViewById(2131099786));
    this.anima = new AnimUtils(getActivity());
    this.under = ((LinearLayout)this.v.findViewById(2131100080));
    this.listview = ((RadioGroup)this.v.findViewById(2131099834));
    TextView localTextView = (TextView)this.v.findViewById(2131099714);
    this.downLiner = ((RelativeLayout)this.v.findViewById(2131100072));
    this.titelRelay = ((RelativeLayout)this.v.findViewById(2131100070));
    this.viewline = this.v.findViewById(2131099998);
    this.mainRoleName = ((TextView)this.v.findViewById(2131100068));
    this.mainRoleName.setTypeface(TypefaceUtils.getTypeface(getActivity(), null));
    this.mainRoleHead = ((AsyncImageView)this.v.findViewById(2131100067));
    refreshMainRoleHeadImageAndName();
    this.push_count = ((TextView)this.v.findViewById(2131099897));
    this.bootom_push_count = ((TextView)this.v.findViewById(2131100079));
    initRoleList();
    this.titelRelay.setOnClickListener(this.picoocClick);
    this.downLiner.setOnClickListener(this.picoocClick);
    localTextView.setOnClickListener(this.picoocClick);
    this.tv_add_local_home = ((TextView)this.v.findViewById(2131100074));
    this.tv_add_remote_home = ((TextView)this.v.findViewById(2131100075));
    this.tv_invite = ((LinearLayout)this.v.findViewById(2131100077));
    this.tv_add_local_home.setOnClickListener(this.picoocClick);
    this.tv_add_remote_home.setOnClickListener(this.picoocClick);
    this.tv_invite.setOnClickListener(this.picoocClick);
    this.downLiner.setFocusable(false);
    this.downLiner.setEnabled(false);
    this.downLiner.setClickable(false);
    this.me = ((LinearLayout)this.v.findViewById(2131099786));
    this.me.setTag(this.app.getMainRole());
    this.me.setOnClickListener(this.roleItemClick);
    updateCount();
    DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
    this.mLcdWidth = localDisplayMetrics.widthPixels;
    this.mDensity = localDisplayMetrics.density;
    this.invitTiShi = ((TextView)this.v.findViewById(2131100078));
    invitGoneTop(this.downLiner);
  }

  public void invitGoneTop(View paramView)
  {
    paramView.measure(View.MeasureSpec.makeMeasureSpec((int)(this.mLcdWidth - 10.0F * this.mDensity), 1073741824), 0);
    ((LinearLayout.LayoutParams)paramView.getLayoutParams()).bottomMargin = (-paramView.getMeasuredHeight());
    paramView.setVisibility(8);
  }

  public void invitRole()
  {
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
    this.app.setMainRole(localRoleBin);
    this.roles.remove(localRoleBin);
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Log.i("picooc", "onCreate  RightFragment2页面");
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.picooc.latin.addvisitorsuccess");
    localIntentFilter.addAction("com.picooc.latin.addfamilysuccess");
    localIntentFilter.addAction("com.picooc.setting.updateRoleMessage");
    localIntentFilter.addAction("com.picooc.local.family.upto.remote.family");
    localIntentFilter.addAction("com.picooc.receive.push.refresh.ui");
    localIntentFilter.addAction("com.picooc.invitation.refresh.ui");
    localIntentFilter.addAction("com.picooc.slidingmenu.close.right.menu");
    localIntentFilter.addAction("com.picooc.some.role.has.new.weighting");
    getActivity().registerReceiver(this.mReceiver, localIntentFilter);
    this.deleteButtonList = new ArrayList();
    this.app = ((MyApplication)getActivity().getApplicationContext());
    invitRole();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.v = paramLayoutInflater.inflate(2130903125, paramViewGroup, false);
    invit();
    return this.v;
  }

  public void onDestroy()
  {
    getActivity().unregisterReceiver(this.mReceiver);
    super.onDestroy();
  }

  public void onPause()
  {
    super.onPause();
    hideAllDelBtns();
  }

  public void scalce(final View paramView1, final View paramView2)
  {
    paramView1.setVisibility(0);
    if (this.animation == null)
      this.animation = android.view.animation.AnimationUtils.loadAnimation(getActivity(), 2130968585);
    this.animation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        paramView1.clearFocus();
        paramView2.requestFocus();
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    });
    paramView1.setAnimation(this.animation);
    this.animation.startNow();
  }

  public void updateCount()
  {
    int j = OperationDB.selectCount(getActivity(), true, 0L, SharedPreferenceUtils.getSendAndReceiveTime(getActivity(), Boolean.valueOf(false), this.app.getCurrentUser().getUser_id()), this.app.getCurrentUser().getUser_id(), "remote_uid") + OperationDB.selectCount(getActivity(), false, SharedPreferenceUtils.getSendAndReceiveTime(getActivity(), Boolean.valueOf(true), this.app.getCurrentUser().getUser_id()), 0L, this.app.getCurrentUser().getUser_id(), "user_id");
    if (j > 0)
    {
      if (this.invitTiShi != null)
        this.invitTiShi.setText("您有新邀请");
      if (this.bootom_push_count != null)
      {
        this.bootom_push_count.setAlpha(1.0F);
        this.bootom_push_count.setText(j);
      }
      if (this.push_count != null)
      {
        this.push_count.setAlpha(1.0F);
        this.push_count.setText(j);
      }
    }
    do
    {
      return;
      if (this.invitTiShi != null)
        this.invitTiShi.setText("暂无新邀请");
      if (this.bootom_push_count != null)
        this.bootom_push_count.setAlpha(0.0F);
    }
    while (this.push_count == null);
    this.push_count.setAlpha(0.0F);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     RightFragment2
 * JD-Core Version:    0.6.2
 */
