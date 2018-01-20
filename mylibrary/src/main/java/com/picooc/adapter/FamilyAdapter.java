package com.picooc.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.picooc.db.OperationDB;
import com.picooc.domain.RoleBin;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.anyncImageView.AsyncImageView;
import java.util.List;

public class FamilyAdapter extends ArrayAdapter<RoleBin>
{
  static final int ANIMATION_DURATION = 200;
  int a = 0;
  private Button curDel_btn;
  private Boolean isshow = Boolean.valueOf(true);
  private Context mContext;
  private LayoutInflater mInflater;
  private List<RoleBin> objects;
  int pos;
  private int resId;
  private Typeface typeFace;
  private float ux;
  private float x;

  public FamilyAdapter(Context paramContext, int paramInt, List<RoleBin> paramList)
  {
    super(paramContext, paramInt, paramList);
    this.resId = paramInt;
    this.mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    this.objects = paramList;
    this.typeFace = TypefaceUtils.getTypeface(paramContext, null);
    this.mContext = paramContext;
  }

  private void setViewHolder(View paramView)
  {
    ViewHolder localViewHolder = new ViewHolder(null);
    localViewHolder.text = ((TextView)paramView.findViewById(2131099709));
    localViewHolder.text.setTypeface(this.typeFace);
    localViewHolder.imageButton = ((Button)paramView.findViewById(2131099713));
    localViewHolder.needInflate = false;
    localViewHolder.headImage = ((AsyncImageView)paramView.findViewById(2131099707));
    paramView.setTag(localViewHolder);
  }

  @SuppressLint({"ResourceAsColor"})
  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    RoleBin localRoleBin = (RoleBin)this.objects.get(paramInt);
    View localView;
    label64: ViewHolder localViewHolder;
    if (paramView == null)
    {
      localView = this.mInflater.inflate(2130903048, paramViewGroup, false);
      setViewHolder(localView);
      if ((this.a != 1) || (this.pos != paramInt))
        break label183;
      localView.setBackgroundColor(Color.parseColor("#da3b27"));
      localView.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          ViewHolder localViewHolder = (ViewHolder)paramAnonymousView.getTag();
          if (paramAnonymousMotionEvent.getAction() == 0)
          {
            FamilyAdapter.this.x = paramAnonymousMotionEvent.getX();
            if ((FamilyAdapter.this.curDel_btn != null) && (FamilyAdapter.this.curDel_btn.isShown()))
            {
              FamilyAdapter.this.curDel_btn.setVisibility(4);
              FamilyAdapter.this.isshow = Boolean.valueOf(false);
            }
          }
          do
          {
            do
            {
              return true;
              FamilyAdapter.this.isshow = Boolean.valueOf(true);
              return true;
              if (paramAnonymousMotionEvent.getAction() != 1)
                break;
              FamilyAdapter.this.ux = paramAnonymousMotionEvent.getX();
            }
            while ((localViewHolder.imageButton == null) || (Math.abs(FamilyAdapter.this.x - FamilyAdapter.this.ux) <= 20.0F));
            if (localViewHolder.imageButton.isShown())
            {
              localViewHolder.imageButton.setVisibility(4);
              FamilyAdapter.this.curDel_btn = localViewHolder.imageButton;
              return true;
            }
          }
          while (!FamilyAdapter.this.isshow.booleanValue());
          localViewHolder.imageButton.setVisibility(0);
          FamilyAdapter.this.scalce(localViewHolder.imageButton);
          FamilyAdapter.this.curDel_btn = localViewHolder.imageButton;
          return true;
          paramAnonymousMotionEvent.getAction();
          return true;
        }
      });
      localViewHolder = (ViewHolder)localView.getTag();
      localViewHolder.text.setText(localRoleBin.getName());
      localViewHolder.imageButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (FamilyAdapter.this.curDel_btn != null)
            FamilyAdapter.this.curDel_btn.setVisibility(4);
          FamilyAdapter.this.objects.remove(paramInt);
          OperationDB.deleteRoleByRoleId(FamilyAdapter.this.mContext, ((RoleBin)FamilyAdapter.this.objects.get(paramInt)).getRole_id());
          FamilyAdapter.this.notifyDataSetChanged();
        }
      });
      Log.i("picooc", "head_url=" + localRoleBin.getHead_portrait_url() + "!");
      if (localRoleBin.getHead_portrait_url().equals(""))
        break label196;
      localViewHolder.headImage.setUrl(localRoleBin.getHead_portrait_url());
    }
    label183: label196: 
    while (localRoleBin.getSex() != 1)
    {
      return localView;
      localView = paramView;
      break;
      localView.setBackgroundColor(Color.parseColor("#4E5268"));
      break label64;
    }
    localViewHolder.headImage.setDefaultImageResource(2130838457);
    return localView;
  }

  public void scalce(View paramView)
  {
    Animation localAnimation = AnimationUtils.loadAnimation(this.mContext, 2130968585);
    paramView.setAnimation(localAnimation);
    localAnimation.start();
  }

  public void setColor(int paramInt1, int paramInt2)
  {
    this.a = paramInt1;
    this.pos = paramInt2;
  }

  private class ViewHolder
  {
    public AsyncImageView headImage;
    public Button imageButton;
    public boolean needInflate;
    public TextView text;

    private ViewHolder()
    {
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     FamilyAdapter
 * JD-Core Version:    0.6.2
 */
