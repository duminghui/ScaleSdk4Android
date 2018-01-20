package com.picooc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.picooc.adapter.LiftFirendsAdapter;
import com.picooc.widget.listview.ModXListView;
import com.picooc.widget.listview.ModXListView.IXListViewListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LiftFirends extends Fragment
  implements View.OnClickListener
{
  private View layout;
  private List<Map<String, String>> list = null;
  private ModXListView listview;
  private IXListViewListener xListViewListener = new IXListViewListener()
  {
    public void onLoadMore()
    {
    }

    public void onRefresh()
    {
    }
  };

  public void invit()
  {
    this.listview = ((ModXListView)this.layout.findViewById(2131099834));
    this.listview.setPullLoadEnable(false);
    this.listview.setPullRefreshEnable(true);
    this.listview.setXListViewListener(this.xListViewListener);
    ImageView localImageView1 = (ImageView)this.layout.findViewById(2131099651);
    ImageView localImageView2 = (ImageView)this.layout.findViewById(2131099650);
    localImageView1.setImageResource(2130838428);
    localImageView1.setOnClickListener(this);
    localImageView2.setOnClickListener(this);
    localImageView1.setVisibility(8);
    ((TextView)this.layout.findViewById(2131099699)).setText("朋友");
  }

  public void invitList()
  {
    this.list = new ArrayList();
    for (int i = 0; ; i++)
    {
      if (i >= 20)
        return;
      HashMap localHashMap = new HashMap();
      localHashMap.put("name", i + "号");
      this.list.add(localHashMap);
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131099650:
    case 2131099651:
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.layout = paramLayoutInflater.inflate(2130903191, paramViewGroup, false);
    invitList();
    invit();
    LiftFirendsAdapter localLiftFirendsAdapter = new LiftFirendsAdapter(getActivity(), this.list);
    this.listview.setAdapter(localLiftFirendsAdapter);
    return this.layout;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LiftFirends
 * JD-Core Version:    0.6.2
 */
