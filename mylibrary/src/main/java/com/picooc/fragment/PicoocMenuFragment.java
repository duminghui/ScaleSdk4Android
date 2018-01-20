package com.picooc.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import com.picooc.PicoocActivity3;

public class PicoocMenuFragment extends Fragment
{
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 1:
        PicoocMenuFragment.this.left.setVisibility(0);
        PicoocMenuFragment.this.right.setVisibility(4);
        return;
      case 2:
      }
      PicoocMenuFragment.this.left.setVisibility(4);
      PicoocMenuFragment.this.right.setVisibility(0);
    }
  };
  private ScrollView left;
  private View.OnClickListener picoocClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      ((PicoocActivity3)PicoocMenuFragment.this.getActivity());
      switch (paramAnonymousView.getId())
      {
      case 2131099963:
      case 2131099964:
      case 2131099965:
      case 2131099966:
      case 2131099967:
      case 2131099968:
      }
    }
  };
  private ImageView picooc_activity;
  private ImageView picooc_friends;
  private ImageView picooc_reportOne;
  private ImageView picooc_reportTwo;
  private ImageView picooc_setting;
  private ImageView picooc_today;
  private ListView right;
  private View v;

  private void init()
  {
    this.picooc_today = ((ImageView)this.v.findViewById(2131099963));
    this.picooc_activity = ((ImageView)this.v.findViewById(2131099964));
    this.picooc_reportOne = ((ImageView)this.v.findViewById(2131099965));
    this.picooc_reportTwo = ((ImageView)this.v.findViewById(2131099966));
    this.picooc_friends = ((ImageView)this.v.findViewById(2131099967));
    this.picooc_setting = ((ImageView)this.v.findViewById(2131099968));
    this.picooc_today.setOnClickListener(this.picoocClick);
    this.picooc_activity.setOnClickListener(this.picoocClick);
    this.picooc_reportOne.setOnClickListener(this.picoocClick);
    this.picooc_reportTwo.setOnClickListener(this.picoocClick);
    this.picooc_friends.setOnClickListener(this.picoocClick);
    this.picooc_setting.setOnClickListener(this.picoocClick);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.v = paramLayoutInflater.inflate(2130903099, paramViewGroup, false);
    this.left = ((ScrollView)this.v.findViewById(2131099650));
    this.right = ((ListView)this.v.findViewById(2131099651));
    String[] arrayOfString = { "安娜", "皮特", "朱莉", "吉姆" };
    ArrayAdapter localArrayAdapter = new ArrayAdapter(getActivity(), 17367043, arrayOfString);
    this.right.setAdapter(localArrayAdapter);
    init();
    return this.v;
  }

  public void onPause()
  {
    super.onPause();
  }

  public void setMode(int paramInt, boolean paramBoolean)
  {
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PicoocMenuFragment
 * JD-Core Version:    0.6.2
 */
