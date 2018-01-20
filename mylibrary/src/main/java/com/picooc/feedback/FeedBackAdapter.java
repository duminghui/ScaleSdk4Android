package com.picooc.feedback;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.picooc.MyApplication;
import com.picooc.db.OperationDB;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.DateUtils;
import com.picooc.utils.TypefaceUtils;
import com.umeng.common.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONObject;

public class FeedBackAdapter extends BaseAdapter
{
  private static final int TYPE_SERVER = 0;
  private static final int TYPE_USER = 1;
  private Context mContext;
  private ArrayList<FeedBackItem> mData = new ArrayList();
  private LayoutInflater mInflater;
  private ListView mListView;
  private ArrayList<FeedBackItem> mUpLoadNoCommit = new ArrayList();
  private JsonHttpResponseHandler upLoadHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      System.out.println("UpLoadNoCommit onFailure===2");
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      System.out.println("UpLoadNoCommit onFailure===1");
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      if ((new ResponseEntity(paramAnonymousJSONObject).getMethod().equals("commitFeedBack")) && (FeedBackAdapter.this.mUpLoadNoCommit.size() > 0))
      {
        FeedBackItem localFeedBackItem = (FeedBackItem)FeedBackAdapter.this.mUpLoadNoCommit.get(0);
        System.out.println(localFeedBackItem.mes);
        localFeedBackItem.serverID = 1;
        OperationDB.updateFeedback(FeedBackAdapter.this.mContext, localFeedBackItem);
        FeedBackAdapter.this.mUpLoadNoCommit.remove(0);
      }
    }
  };

  public FeedBackAdapter(Context paramContext, ListView paramListView)
  {
    this.mInflater = LayoutInflater.from(paramContext);
    this.mContext = paramContext;
    this.mListView = paramListView;
    this.mUpLoadNoCommit.clear();
  }

  private void upLoadNoCommit()
  {
    Iterator localIterator = this.mData.iterator();
    if (!localIterator.hasNext())
      System.out.println(this.mUpLoadNoCommit.size() + "个没有提交");
    for (int i = 0; ; i++)
    {
      if (i >= this.mUpLoadNoCommit.size())
      {
        return;
        FeedBackItem localFeedBackItem = (FeedBackItem)localIterator.next();
        if (localFeedBackItem.serverID != 0)
          break;
        this.mUpLoadNoCommit.add(localFeedBackItem);
        break;
      }
      doRequestUpLoad((FeedBackItem)this.mUpLoadNoCommit.get(i));
    }
  }

  public int addItem(FeedBackItem paramFeedBackItem)
  {
    this.mData.add(paramFeedBackItem);
    notifyDataSetChanged();
    this.mListView.setSelection(this.mData.size());
    return this.mData.size();
  }

  public void addTemp(long paramLong)
  {
    delayScrollLast(paramLong);
  }

  public void delayRefresh(long paramLong)
  {
    synchronized (this.mData)
    {
      final ListView localListView = this.mListView;
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          ArrayList localArrayList;
          Iterator localIterator;
          if (localListView != null)
          {
            localArrayList = new ArrayList();
            localIterator = FeedBackAdapter.this.mData.iterator();
          }
          while (true)
          {
            if (!localIterator.hasNext())
            {
              FeedBackAdapter.this.mData.removeAll(localArrayList);
              localListView.smoothScrollToPosition(FeedBackAdapter.this.mData.size());
              FeedBackAdapter.this.notifyDataSetChanged();
              return;
            }
            FeedBackItem localFeedBackItem = (FeedBackItem)localIterator.next();
            if (localFeedBackItem.uploadState == 5)
              localArrayList.add(localFeedBackItem);
          }
        }
      }
      , 2000L + paramLong);
      return;
    }
  }

  public void delayScrollLast(long paramLong)
  {
    try
    {
      final ListView localListView = this.mListView;
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          if (localListView != null)
          {
            FeedBackItem localFeedBackItem = FeedBackAdapter.this.getFeedBackItemTemp();
            FeedBackAdapter.this.mData.add(localFeedBackItem);
            FeedBackAdapter.this.notifyDataSetChanged();
          }
        }
      }
      , paramLong);
      return;
    }
    finally
    {
    }
  }

  protected void doRequestUpLoad(FeedBackItem paramFeedBackItem)
  {
    RequestEntity localRequestEntity = new RequestEntity("commitFeedBack", null);
    MyApplication localMyApplication = (MyApplication)this.mContext.getApplicationContext();
    localRequestEntity.addParam("message", paramFeedBackItem.mes);
    localRequestEntity.addParam("userID", Long.valueOf(localMyApplication.getCurrentUser().getUser_id()));
    localRequestEntity.addParam("localId", Integer.valueOf(1));
    localRequestEntity.addParam("os", Integer.valueOf(1));
    localRequestEntity.addParam("time", Long.valueOf(paramFeedBackItem.time));
    HttpUtils.getJson(this.mContext, localRequestEntity, this.upLoadHandler);
  }

  public int getCount()
  {
    return this.mData.size();
  }

  public FeedBackItem getFeedBackItemTemp()
  {
    FeedBackItem localFeedBackItem = new FeedBackItem();
    localFeedBackItem.userID = ((MyApplication)this.mContext.getApplicationContext()).getCurrentUser().getUser_id();
    localFeedBackItem.fromWhere = 0;
    localFeedBackItem.mes = this.mContext.getString(2131361946);
    localFeedBackItem.time = System.currentTimeMillis();
    localFeedBackItem.uploadState = 5;
    return localFeedBackItem;
  }

  public int getFeedItemIndex(FeedBackItem paramFeedBackItem)
  {
    int i = this.mData.indexOf(paramFeedBackItem);
    System.out.println(" getFeedItemIndex =" + i);
    return i;
  }

  public Object getItem(int paramInt)
  {
    return this.mData.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public int getItemViewType(int paramInt)
  {
    return ((FeedBackItem)this.mData.get(paramInt)).fromWhere;
  }

  public int getUpdateIndex(FeedBackItem paramFeedBackItem)
  {
    return this.mData.indexOf(paramFeedBackItem);
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int i = getItemViewType(paramInt);
    FeedBackItem localFeedBackItem = (FeedBackItem)getItem(paramInt);
    ViewHolder localViewHolder;
    int j;
    label131: ProgressBar localProgressBar;
    if (paramView == null)
    {
      localViewHolder = new ViewHolder();
      if (i == 1)
      {
        paramView = this.mInflater.inflate(2130903076, null);
        j = 2130837732;
        localViewHolder.time = ((TextView)paramView.findViewById(2131099846));
        localViewHolder.time.setTypeface(TypefaceUtils.getTypeface(this.mContext, null));
        localViewHolder.msg = ((TextView)paramView.findViewById(2131099847));
        localViewHolder.msg.setTypeface(TypefaceUtils.getTypeface(this.mContext, null));
        localViewHolder.msg.setBackgroundResource(j);
        paramView.setTag(localViewHolder);
        if (i == 1)
        {
          localProgressBar = (ProgressBar)paramView.findViewById(2131099849);
          switch (localFeedBackItem.uploadState)
          {
          default:
          case 3:
          case 1:
          case 2:
          case 4:
          }
        }
      }
    }
    while (true)
    {
      localViewHolder.time.setText(DateUtils.getNormalTime(localFeedBackItem.time));
      localViewHolder.msg.setText(localFeedBackItem.mes);
      return paramView;
      paramView = this.mInflater.inflate(2130903075, null);
      j = 2130837731;
      break;
      localViewHolder = (ViewHolder)paramView.getTag();
      break label131;
      paramView.findViewById(2131099848).setVisibility(0);
      localProgressBar.setVisibility(8);
      continue;
      localProgressBar.setVisibility(0);
      paramView.findViewById(2131099848).setVisibility(8);
      continue;
      paramView.findViewById(2131099848).setVisibility(8);
      localProgressBar.setVisibility(8);
    }
  }

  public int getViewTypeCount()
  {
    return 3;
  }

  public void initFromDB()
  {
    long l = ((MyApplication)this.mContext.getApplicationContext()).getCurrentUser().getUser_id();
    this.mData = OperationDB.selectAll(this.mContext, l);
  }

  public void syncDb(ArrayList<FeedBackItem> paramArrayList)
  {
    if (paramArrayList == null);
    while (true)
    {
      return;
      Collections.reverse(paramArrayList);
      this.mData.addAll(paramArrayList);
      notifyDataSetChanged();
      Iterator localIterator = paramArrayList.iterator();
      while (localIterator.hasNext())
      {
        FeedBackItem localFeedBackItem = (FeedBackItem)localIterator.next();
        OperationDB.insertFBMesDB(this.mContext, localFeedBackItem);
      }
    }
  }

  public void updateFeedBackItem(FeedBackItem paramFeedBackItem)
  {
    int i = this.mData.indexOf(paramFeedBackItem);
    if (i >= 0)
    {
      Log.e("xxx", "1111111111111111111111=" + i);
      ((FeedBackItem)this.mData.get(i)).set(paramFeedBackItem);
      notifyDataSetInvalidated();
      return;
    }
    Log.e("xxx", "updateFeedBackItem index =" + i);
  }

  public static class ViewHolder
  {
    public TextView msg;
    public TextView time;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     FeedBackAdapter
 * JD-Core Version:    0.6.2
 */
