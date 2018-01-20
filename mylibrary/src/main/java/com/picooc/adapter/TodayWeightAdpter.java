package com.picooc.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.picooc.MyApplication;
import com.picooc.db.OperationDB;
import com.picooc.domain.BodyIndex;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.oldhumen.Age;
import com.picooc.utils.DateUtils;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import java.util.List;
import org.achartengine.tools.ModUtils;
import org.json.JSONObject;

public class TodayWeightAdpter extends ArrayAdapter<BodyIndex>
{
  static final int ANIMATION_DURATION = 500;
  private View Wv = null;
  private MyApplication app;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast((Activity)TodayWeightAdpter.this.mcontext, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast((Activity)TodayWeightAdpter.this.mcontext, localResponseEntity.getMessage());
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      if (localResponseEntity.getMethod().equals("deleteBodyIndex"))
      {
        PicoocLoading.dismissDialog();
        OperationDB.deleteBodyIndeDB(TodayWeightAdpter.this.mcontext, TodayWeightAdpter.this.wCell.getRole_id(), Long.valueOf(TodayWeightAdpter.this.wCell.getTime()));
        TodayWeightAdpter.this.deleteCell(TodayWeightAdpter.this.Wv, TodayWeightAdpter.this.potion, TodayWeightAdpter.this.wCell);
        if (1 + TodayWeightAdpter.this.potion == TodayWeightAdpter.this.objects.size())
        {
          Log.i("picooc", "position===" + TodayWeightAdpter.this.potion + "---size==" + TodayWeightAdpter.this.objects.size());
          Intent localIntent1 = new Intent();
          localIntent1.setAction("com.picooc.sync.delete.today.weight.refresh.ui");
          TodayWeightAdpter.this.mcontext.sendBroadcast(localIntent1);
          Intent localIntent2 = new Intent();
          localIntent2.setAction("com.picooc.latin.weight.success");
          TodayWeightAdpter.this.mcontext.sendBroadcast(localIntent2);
        }
        PicoocToast.showToast((Activity)TodayWeightAdpter.this.mcontext, "删除成功！");
      }
    }
  };
  private Boolean isGone = Boolean.valueOf(true);
  private LayoutInflater mInflater;
  private Context mcontext;
  deleteListener notDeleteListener;
  private List<BodyIndex> objects;
  private int potion = 0;
  private int resId;
  private BodyIndex wCell = null;

  public TodayWeightAdpter(Context paramContext, int paramInt, List<BodyIndex> paramList)
  {
    super(paramContext, paramInt, paramList);
    this.resId = paramInt;
    this.mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    this.objects = paramList;
    this.mcontext = paramContext;
    this.app = ((MyApplication)this.mcontext.getApplicationContext());
  }

  private void collapse(final View paramView, Animation.AnimationListener paramAnimationListener)
  {
    Animation local4 = new Animation()
    {
      protected void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation)
      {
        if (paramAnonymousFloat == 1.0F)
        {
          paramView.setVisibility(8);
          return;
        }
        paramView.getLayoutParams().height = (this.val$initialHeight - (int)(paramAnonymousFloat * this.val$initialHeight));
        paramView.requestLayout();
      }

      public boolean willChangeBounds()
      {
        return true;
      }
    };
    if (paramAnimationListener != null)
      local4.setAnimationListener(paramAnimationListener);
    local4.setDuration(500L);
    paramView.startAnimation(local4);
  }

  private void deleteCell(final View paramView, final int paramInt, BodyIndex paramBodyIndex)
  {
    collapse(paramView, new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        TodayWeightAdpter.this.objects.remove(paramInt);
        ((ViewHolder)paramView.getTag()).needInflate = true;
        TodayWeightAdpter.this.notDeleteListener.delete();
        TodayWeightAdpter.this.notifyDataSetChanged();
        if (TodayWeightAdpter.this.objects.size() < 2)
          TodayWeightAdpter.this.notDeleteListener.notDelete();
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    });
  }

  private void setViewHolder(View paramView)
  {
    ViewHolder localViewHolder = new ViewHolder(null);
    localViewHolder.text = ((TextView)paramView.findViewById(2131099737));
    localViewHolder.text.setTypeface(ModUtils.getTypeface(this.mcontext));
    localViewHolder.imageButton = ((ImageView)paramView.findViewById(2131099738));
    localViewHolder.texttime = ((TextView)paramView.findViewById(2131099736));
    localViewHolder.needInflate = false;
    paramView.setTag(localViewHolder);
  }

  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    final BodyIndex localBodyIndex = (BodyIndex)getItem(paramInt);
    final View localView;
    ViewHolder localViewHolder;
    if (paramView == null)
    {
      localView = this.mInflater.inflate(2130903056, paramViewGroup, false);
      setViewHolder(localView);
      localViewHolder = (ViewHolder)localView.getTag();
      localViewHolder.text.setText(localBodyIndex.getWeight() + "kg");
      localViewHolder.texttime.setText(DateUtils.getLongTimeToDateTime(localBodyIndex.getTime()));
      localViewHolder.texttime.setTypeface(ModUtils.getTypeface(this.mcontext));
      localViewHolder.imageButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          TodayWeightAdpter.this.Wv = localView;
          TodayWeightAdpter.this.potion = paramInt;
          TodayWeightAdpter.this.wCell = localBodyIndex;
          TodayWeightAdpter.this.objects.size();
          if (localBodyIndex.getId_in_server() > 0L)
          {
            PicoocLoading.showLoadingDialog(TodayWeightAdpter.this.mcontext);
            RequestEntity localRequestEntity = new RequestEntity("deleteBodyIndex", "2.0");
            localRequestEntity.addParam("bodyIndexId", Long.valueOf(localBodyIndex.getId_in_server()));
            localRequestEntity.addParam("roleID", Long.valueOf(localBodyIndex.getRole_id()));
            HttpUtils.getJson(TodayWeightAdpter.this.mcontext, localRequestEntity, TodayWeightAdpter.this.httpHandler);
            return;
          }
          OperationDB.deleteBodyIndeDB(TodayWeightAdpter.this.mcontext, TodayWeightAdpter.this.wCell.getRole_id(), Long.valueOf(TodayWeightAdpter.this.wCell.getTime()));
          TodayWeightAdpter.this.deleteCell(TodayWeightAdpter.this.Wv, TodayWeightAdpter.this.potion, TodayWeightAdpter.this.wCell);
          if (1 + TodayWeightAdpter.this.potion == TodayWeightAdpter.this.objects.size())
          {
            Log.i("picooc", "position===" + TodayWeightAdpter.this.potion + "---size==" + TodayWeightAdpter.this.objects.size());
            Intent localIntent1 = new Intent();
            localIntent1.setAction("com.picooc.sync.delete.today.weight.refresh.ui");
            TodayWeightAdpter.this.mcontext.sendBroadcast(localIntent1);
            Intent localIntent2 = new Intent();
            localIntent2.setAction("com.picooc.latin.weight.success");
            TodayWeightAdpter.this.mcontext.sendBroadcast(localIntent2);
          }
          PicoocToast.showToast((Activity)TodayWeightAdpter.this.mcontext, "删除成功！");
        }
      });
      if (!this.isGone.booleanValue())
        break label223;
      localViewHolder.imageButton.setVisibility(4);
    }
    while (true)
    {
      if (Age.isOld(this.app))
      {
        Age.setTextSize(this.mcontext, localViewHolder.text);
        Age.setTextSize(this.mcontext, localViewHolder.texttime);
      }
      return localView;
      if (((ViewHolder)paramView.getTag()).needInflate)
      {
        localView = this.mInflater.inflate(2130903056, paramViewGroup, false);
        setViewHolder(localView);
        break;
      }
      localView = paramView;
      break;
      label223: localViewHolder.imageButton.setVisibility(0);
    }
  }

  public void setBeanlen(Boolean paramBoolean)
  {
    this.isGone = paramBoolean;
  }

  public void setDeleteListener(deleteListener paramdeleteListener)
  {
    this.notDeleteListener = paramdeleteListener;
  }

  private class ViewHolder
  {
    public ImageView imageButton;
    public boolean needInflate;
    public TextView text;
    public TextView texttime;

    private ViewHolder()
    {
    }
  }

  public static abstract interface deleteListener
  {
    public abstract void delete();

    public abstract void notDelete();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     TodayWeightAdpter
 * JD-Core Version:    0.6.2
 */
