package com.picooc.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.MyApplication;
import com.picooc.arithmetic.BodyCompositionSectionGlobal;
import com.picooc.arithmetic.ReportDirect;
import com.picooc.domain.ReportEntity;
import com.picooc.oldhumen.Age;
import com.picooc.utils.NumUtils;
import com.picooc.widget.AnimUtils;
import java.util.List;
import org.achartengine.tools.ModUtils;

public class TodayBodyFatAdapter extends BaseAdapter
{
  MyApplication app;
  private int chazhi = 0;
  ReportEntity fatReport;
  private int fatWihgt = 123;
  private int itemH = this.fatWihgt;
  private List<Object> list = null;
  private AnimUtils mAnim;
  private Context mContext;
  private int nowWidth = 480;

  public TodayBodyFatAdapter(Context paramContext, List<Object> paramList)
  {
    this.mContext = paramContext;
    this.list = paramList;
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((Activity)paramContext).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.nowWidth = localDisplayMetrics.widthPixels;
    this.chazhi = ModUtils.dip2px(paramContext, 15.0F);
    this.itemH = (localDisplayMetrics.heightPixels / 4);
    this.app = ((MyApplication)paramContext.getApplicationContext());
  }

  private void updateViews(int paramInt, View paramView, ViewHolder paramViewHolder)
  {
    if (paramInt == 0)
    {
      paramView.requestLayout();
      if ((this.list.get(0) instanceof ReportEntity))
      {
        this.fatReport = ((ReportEntity)this.list.get(0));
        paramViewHolder.curentweight.setText(this.fatReport.getNum() + "%");
        paramViewHolder.shuxian.setVisibility(8);
        paramViewHolder.tvTitle.setVisibility(8);
        paramViewHolder.fatImage.setVisibility(0);
        paramViewHolder.translateImage.setVisibility(0);
        paramViewHolder.mark.setImageResource(BodyCompositionSectionGlobal.getFatMarkeImage(this.fatReport.getStateCode()));
        String[] arrayOfString = BodyCompositionSectionGlobal.FAT;
        float[] arrayOfFloat = this.fatReport.getRegionArray();
        paramViewHolder.textOne.setText(NumUtils.roundValue(arrayOfFloat[1]) + "%");
        paramViewHolder.textTwo.setText(NumUtils.roundValue(arrayOfFloat[2]) + "%");
        paramViewHolder.textThree.setText(NumUtils.roundValue(arrayOfFloat[3]) + "%");
        paramViewHolder.textFour.setText(NumUtils.roundValue(arrayOfFloat[4]) + "%");
        paramViewHolder.textOneBottom.setText(arrayOfString[0]);
        paramViewHolder.textTwoBottom.setText(arrayOfString[1]);
        paramViewHolder.textThreeBottom.setText(arrayOfString[2]);
        paramViewHolder.textFourBottom.setText(arrayOfString[3]);
        paramViewHolder.textFiveBottom.setText(arrayOfString[4]);
        AnimUtils.LiftandRightMove(paramViewHolder.translateImage, 0, this.fatWihgt * (-1 + this.fatReport.getStateCode()) + this.fatWihgt / 2 - this.chazhi, 0);
        setLaoutPam(paramViewHolder.textOne, -10 + this.fatWihgt, 0, 10);
        setLaoutPam(paramViewHolder.textTwo, -9 + 2 * this.fatWihgt, 0, 10);
        setLaoutPam(paramViewHolder.textThree, -8 + 3 * this.fatWihgt, 0, 10);
        setLaoutPam(paramViewHolder.textFour, -7 + 4 * this.fatWihgt, 0, 10);
        setLaoutPamBotton(paramViewHolder.textOneBottom, 2 + this.fatWihgt / 2, 10, 0);
        setLaoutPamBotton(paramViewHolder.textTwoBottom, -6 + this.fatWihgt + this.fatWihgt / 2, 10, 0);
        setLaoutPamBotton(paramViewHolder.textThreeBottom, -5 + 2 * this.fatWihgt + this.fatWihgt / 2, 10, 0);
        setLaoutPamBotton(paramViewHolder.textFourBottom, -4 + 3 * this.fatWihgt + this.fatWihgt / 2, 10, 0);
        setLaoutPamBotton(paramViewHolder.textFiveBottom, -3 + 4 * this.fatWihgt + this.fatWihgt / 2, 10, 0);
      }
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return;
            if (paramInt != 1)
              break;
          }
          while (!(this.list.get(1) instanceof String));
          paramViewHolder.translateImage.setVisibility(8);
          paramViewHolder.tvTitle.setVisibility(0);
          paramViewHolder.shuxian.setVisibility(8);
          paramViewHolder.fatImage.setVisibility(8);
          paramViewHolder.tvTitle.setPadding(20, 20, 20, 10);
          paramViewHolder.tvTitle.setLineSpacing(3.4F, 1.2F);
        }
        while ((this.list.get(1) == null) || (this.list.get(1).equals("")));
        ModUtils.replaceString(paramViewHolder.tvTitle, this.mContext, this.list.get(1), false);
        return;
      }
      while ((paramInt != 2) || (!(this.list.get(2) instanceof String)));
      paramViewHolder.translateImage.setVisibility(8);
      paramViewHolder.tvTitle.setVisibility(0);
      paramViewHolder.shuxian.setVisibility(8);
      paramViewHolder.fatImage.setVisibility(8);
      paramViewHolder.tvTitle.setLineSpacing(3.4F, 1.2F);
      paramViewHolder.tvTitle.setPadding(20, 20, 20, 10);
    }
    while ((this.list.get(2) == null) || (this.list.get(2).equals("")));
    ModUtils.replaceString(paramViewHolder.tvTitle, this.mContext, this.list.get(2), false);
  }

  public int getCount()
  {
    return this.list.size();
  }

  public Object getItem(int paramInt)
  {
    return null;
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    ViewHolder localViewHolder;
    if (paramView == null)
    {
      localViewHolder = new ViewHolder();
      paramView = LayoutInflater.from(this.mContext).inflate(2130903043, null);
      localViewHolder.tvTitle = ((TextView)paramView.findViewById(2131099693));
      localViewHolder.fatImage = ((RelativeLayout)paramView.findViewById(2131099678));
      localViewHolder.shuxian = ((TextView)paramView.findViewById(2131099692));
      localViewHolder.translateImage = ((LinearLayout)paramView.findViewById(2131099689));
      localViewHolder.curentweight = ((TextView)paramView.findViewById(2131099690));
      localViewHolder.curentweight.setTypeface(ModUtils.getTypeface(this.mContext));
      this.fatWihgt = ((-20 + (this.nowWidth - ModUtils.dip2px(this.mContext, 41.0F))) / 5);
      paramView.setTag(localViewHolder);
      localViewHolder.tvTitle.setTypeface(ModUtils.getTypeface(this.mContext));
      localViewHolder.fatImage.getWidth();
      localViewHolder.textOne = ((TextView)paramView.findViewById(2131099680));
      localViewHolder.textTwo = ((TextView)paramView.findViewById(2131099681));
      localViewHolder.textThree = ((TextView)paramView.findViewById(2131099682));
      localViewHolder.textFour = ((TextView)paramView.findViewById(2131099683));
      localViewHolder.textOne.setTypeface(ModUtils.getTypeface(this.mContext));
      localViewHolder.textTwo.setTypeface(ModUtils.getTypeface(this.mContext));
      localViewHolder.textThree.setTypeface(ModUtils.getTypeface(this.mContext));
      localViewHolder.textFour.setTypeface(ModUtils.getTypeface(this.mContext));
      localViewHolder.textOneBottom = ((TextView)paramView.findViewById(2131099684));
      localViewHolder.textTwoBottom = ((TextView)paramView.findViewById(2131099685));
      localViewHolder.textThreeBottom = ((TextView)paramView.findViewById(2131099686));
      localViewHolder.textFourBottom = ((TextView)paramView.findViewById(2131099687));
      localViewHolder.textFiveBottom = ((TextView)paramView.findViewById(2131099688));
      localViewHolder.mark = ((ImageView)paramView.findViewById(2131099691));
      if (!Age.isOld(this.app))
        break label662;
      this.fatReport = ((ReportEntity)this.list.get(0));
      if ((paramInt != 0) || (!(this.list.get(0) instanceof ReportEntity)))
        break label546;
      paramView = LayoutInflater.from(this.mContext).inflate(2130903084, null);
      localLevelShowView = (LevelShowView)paramView.findViewById(2131099920);
      localLevelShowView.init();
      localLevelShowView.setItemWidth((this.nowWidth - ModUtils.dip2px(this.mContext, 64.0F)) / 3);
      if ((this.list.get(0) instanceof ReportEntity))
        localLevelShowView.setLevelFloat(this.fatReport.getRegionArray());
      localLevelShowView.setMinimumHeight(this.itemH);
      localLevelShowView.setCurrentLevel(this.fatReport.getNum());
    }
    label546: 
    while (paramInt != 1)
    {
      LevelShowView localLevelShowView;
      return paramView;
      localViewHolder = (ViewHolder)paramView.getTag();
      break;
    }
    View localView = LayoutInflater.from(this.mContext).inflate(2130903092, null);
    TextView localTextView = (TextView)localView.findViewById(2131099945);
    localView.findViewById(2131099944).setAlpha(0.1F);
    localTextView.setText(ReportDirect.getBodyFatExMessageByRole(this.app.getCurrentRole(), this.app.getTodayBody()));
    localTextView.setTypeface(ModUtils.getTypeface(this.mContext));
    localTextView.setPadding(17, 17, 19, 17);
    localTextView.setTextSize(this.mContext.getResources().getDimension(2131230872));
    return localView;
    label662: updateViews(paramInt, paramView, localViewHolder);
    return paramView;
  }

  public void setLaoutPam(TextView paramTextView, int paramInt1, int paramInt2, int paramInt3)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(2, 2131099679);
    localLayoutParams.setMargins(paramInt1, paramInt2, 0, ModUtils.dip2px(this.mContext, 15.0F));
    paramTextView.setLayoutParams(localLayoutParams);
  }

  public void setLaoutPamBotton(TextView paramTextView, int paramInt1, int paramInt2, int paramInt3)
  {
    Log.i("picooc", "magLift=" + paramInt1);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(3, 2131099679);
    localLayoutParams.setMargins(paramInt1, ModUtils.dip2px(this.mContext, 15.0F), 0, paramInt3);
    paramTextView.setLayoutParams(localLayoutParams);
  }

  static final class ViewHolder
  {
    TextView curentweight;
    RelativeLayout fatImage;
    ImageView liftline;
    ImageView mark;
    TextView shuxian;
    TextView textFiveBottom;
    TextView textFour;
    TextView textFourBottom;
    TextView textOne;
    TextView textOneBottom;
    TextView textThree;
    TextView textThreeBottom;
    TextView textTwo;
    TextView textTwoBottom;
    LinearLayout translateImage;
    TextView tvTitle;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     TodayBodyFatAdapter
 * JD-Core Version:    0.6.2
 */
