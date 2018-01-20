package com.picooc.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.picooc.MyApplication;
import com.picooc.domain.ComponentBodyTypeEnum;
import com.picooc.model.SportPlanEnum;
import com.picooc.widget.widgetAddView;
import java.util.ArrayList;
import java.util.Iterator;
import org.achartengine.tools.ModUtils;

public class LiftMovePlanAdapter extends BaseAdapter
{
  SportPlanModel.DayPlan dayPlan;
  private ArrayList<SportPlanModel.DayPlan> dayPlans = null;
  private View firstView;
  private Context mContext;
  int nub = 0;
  private int tatePading = 10;
  widgetAddView wigeht;

  public LiftMovePlanAdapter(Context paramContext, ArrayList<SportPlanModel.DayPlan> paramArrayList)
  {
    this.mContext = paramContext;
    this.dayPlans = paramArrayList;
    this.wigeht = new widgetAddView(paramContext);
    this.tatePading = ModUtils.dip2px(paramContext, 7.0F);
  }

  public int getCount()
  {
    return this.dayPlans.size();
  }

  public View getFirstView()
  {
    return this.firstView;
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
    SportPlanModel.DayPlan localDayPlan;
    label493: Iterator localIterator;
    label570: label603: ArrayList localArrayList1;
    label797: ArrayList localArrayList2;
    if (paramView == null)
    {
      localViewHolder = new ViewHolder();
      paramView = LayoutInflater.from(this.mContext).inflate(2130903068, null);
      localViewHolder.tvTitle = ((TextView)paramView.findViewById(2131099696));
      localViewHolder.textline = ((ImageView)paramView.findViewById(2131099719));
      localViewHolder.linear = ((LinearLayout)paramView.findViewById(2131099701));
      localViewHolder.LinearLayout = ((LinearLayout)paramView.findViewById(2131099705));
      localViewHolder.cout = ((TextView)paramView.findViewById(2131099792));
      localViewHolder.yundong_time = ((TextView)paramView.findViewById(2131099793));
      localViewHolder.titleOne = ((TextView)paramView.findViewById(2131099796));
      localViewHolder.yundong_oneLeft = ((TextView)paramView.findViewById(2131099797));
      localViewHolder.yundong_oneRight = ((TextView)paramView.findViewById(2131099798));
      localViewHolder.yundong_oneLeftbootom = ((TextView)paramView.findViewById(2131099799));
      localViewHolder.yundong_oneRightbootom = ((TextView)paramView.findViewById(2131099800));
      localViewHolder.titleTwo = ((TextView)paramView.findViewById(2131099803));
      localViewHolder.yundong_TwoLeft = ((TextView)paramView.findViewById(2131099804));
      localViewHolder.yundong_twoRight = ((TextView)paramView.findViewById(2131099805));
      localViewHolder.yundong_twobootomLeft = ((TextView)paramView.findViewById(2131099806));
      localViewHolder.yundong_twobootomRight = ((TextView)paramView.findViewById(2131099807));
      paramView.setTag(localViewHolder);
      localViewHolder.cout.setTypeface(ModUtils.getTypeface(this.mContext));
      localViewHolder.yundong_time.setTypeface(ModUtils.getTypeface(this.mContext));
      localViewHolder.liftImage = ((ImageView)paramView.findViewById(2131099702));
      localViewHolder.up = ((LinearLayout)paramView.findViewById(2131099795));
      localViewHolder.down = ((LinearLayout)paramView.findViewById(2131099802));
      localViewHolder.firstLine = ((ImageView)paramView.findViewById(2131099794));
      localViewHolder.twoLine = ((ImageView)paramView.findViewById(2131099801));
      SpannableStringBuilder localSpannableStringBuilder1 = new SpannableStringBuilder("有助于降低");
      localSpannableStringBuilder1.setSpan(new ForegroundColorSpan(Color.parseColor("#8cff79")), 3, 5, 34);
      localViewHolder.titleOne.setText(localSpannableStringBuilder1);
      SpannableStringBuilder localSpannableStringBuilder2 = new SpannableStringBuilder("有助于提升");
      localSpannableStringBuilder2.setSpan(new ForegroundColorSpan(Color.parseColor("#8cff79")), 3, 5, 34);
      localViewHolder.titleTwo.setText(localSpannableStringBuilder2);
      localViewHolder.LinearLayout.removeAllViews();
      localDayPlan = (SportPlanModel.DayPlan)this.dayPlans.get(paramInt);
      if (localDayPlan.getSportType() != SportPlanEnum.DQD)
        break label1014;
      localViewHolder.liftImage.setImageResource(2130838288);
      localViewHolder.cout.setBackgroundResource(2130838287);
      localViewHolder.tvTitle.setText(localDayPlan.getSportTypeName());
      localViewHolder.cout.setText(localDayPlan.getSportCount());
      localViewHolder.yundong_time.setText(localDayPlan.getSportDuringTime());
      localViewHolder.cout.setGravity(17);
      localViewHolder.cout.setPadding(this.tatePading, 2, this.tatePading, 0);
      localIterator = localDayPlan.getMessages().iterator();
      if (localIterator.hasNext())
        break label1086;
      if (paramInt != -1 + this.dayPlans.size())
        break label1119;
      localViewHolder.textline.setVisibility(8);
      localArrayList1 = localDayPlan.getHelpToReduce();
      if (localArrayList1.size() <= 0)
        break label1455;
      localViewHolder.firstLine.setVisibility(0);
      localViewHolder.up.setVisibility(0);
      if (localArrayList1.size() != 4)
        break label1131;
      this.nub = ((Integer)localArrayList1.get(0)).intValue();
      invitText(this.nub, localViewHolder.yundong_oneLeft);
      this.nub = ((Integer)localArrayList1.get(1)).intValue();
      invitText(this.nub, localViewHolder.yundong_oneRight);
      this.nub = ((Integer)localArrayList1.get(2)).intValue();
      invitText(this.nub, localViewHolder.yundong_oneLeftbootom);
      this.nub = ((Integer)localArrayList1.get(3)).intValue();
      invitText(this.nub, localViewHolder.yundong_oneRightbootom);
      localViewHolder.yundong_oneLeft.setVisibility(0);
      localViewHolder.yundong_oneRight.setVisibility(0);
      localViewHolder.yundong_oneLeftbootom.setVisibility(0);
      localViewHolder.yundong_oneRightbootom.setVisibility(0);
      localArrayList2 = localDayPlan.getHelpToAdd();
      if (localArrayList2.size() <= 0)
        break label1802;
      localViewHolder.twoLine.setVisibility(0);
      localViewHolder.down.setVisibility(0);
      if (localArrayList2.size() != 4)
        break label1478;
      this.nub = ((Integer)localArrayList2.get(0)).intValue();
      invitText(this.nub, localViewHolder.yundong_TwoLeft);
      this.nub = ((Integer)localArrayList2.get(1)).intValue();
      invitText(this.nub, localViewHolder.yundong_twoRight);
      this.nub = ((Integer)localArrayList2.get(2)).intValue();
      invitText(this.nub, localViewHolder.yundong_twobootomLeft);
      this.nub = ((Integer)localArrayList2.get(3)).intValue();
      invitText(this.nub, localViewHolder.yundong_twobootomRight);
      localViewHolder.yundong_TwoLeft.setVisibility(0);
      localViewHolder.yundong_twoRight.setVisibility(0);
      localViewHolder.yundong_twobootomLeft.setVisibility(0);
      localViewHolder.yundong_twobootomRight.setVisibility(0);
    }
    label1802: 
    while (true)
    {
      if (paramInt == 0)
        this.firstView = paramView;
      return paramView;
      localViewHolder = (ViewHolder)paramView.getTag();
      break;
      label1014: if (localDayPlan.getSportType() == SportPlanEnum.YY)
      {
        localViewHolder.liftImage.setImageResource(2130838311);
        localViewHolder.cout.setBackgroundResource(2130838310);
        break label493;
      }
      if (localDayPlan.getSportType() != SportPlanEnum.WY)
        break label493;
      localViewHolder.liftImage.setImageResource(2130838308);
      localViewHolder.cout.setBackgroundResource(2130838307);
      break label493;
      label1086: String str = (String)localIterator.next();
      this.wigeht.addview(localViewHolder.LinearLayout, str, 30, 30);
      break label570;
      label1119: localViewHolder.textline.setVisibility(0);
      break label603;
      label1131: if (localArrayList1.size() == 3)
      {
        this.nub = ((Integer)localArrayList1.get(0)).intValue();
        invitText(this.nub, localViewHolder.yundong_oneLeft);
        this.nub = ((Integer)localArrayList1.get(1)).intValue();
        invitText(this.nub, localViewHolder.yundong_oneRight);
        this.nub = ((Integer)localArrayList1.get(2)).intValue();
        invitText(this.nub, localViewHolder.yundong_oneLeftbootom);
        localViewHolder.yundong_oneLeft.setVisibility(0);
        localViewHolder.yundong_oneRight.setVisibility(0);
        localViewHolder.yundong_oneLeftbootom.setVisibility(0);
        localViewHolder.yundong_oneRightbootom.setVisibility(8);
        break label797;
      }
      if (localArrayList1.size() == 2)
      {
        this.nub = ((Integer)localArrayList1.get(0)).intValue();
        invitText(this.nub, localViewHolder.yundong_oneLeft);
        this.nub = ((Integer)localArrayList1.get(1)).intValue();
        invitText(this.nub, localViewHolder.yundong_oneRight);
        localViewHolder.yundong_oneLeft.setVisibility(0);
        localViewHolder.yundong_oneRight.setVisibility(0);
        localViewHolder.yundong_oneLeftbootom.setVisibility(8);
        localViewHolder.yundong_oneRightbootom.setVisibility(8);
        break label797;
      }
      if (localArrayList1.size() != 1)
        break label797;
      this.nub = ((Integer)localArrayList1.get(0)).intValue();
      invitText(this.nub, localViewHolder.yundong_oneLeft);
      localViewHolder.yundong_oneLeft.setVisibility(0);
      localViewHolder.yundong_oneRight.setVisibility(8);
      localViewHolder.yundong_oneLeftbootom.setVisibility(8);
      localViewHolder.yundong_oneRightbootom.setVisibility(8);
      break label797;
      label1455: localViewHolder.firstLine.setVisibility(8);
      localViewHolder.up.setVisibility(8);
      break label797;
      label1478: if (localArrayList2.size() == 3)
      {
        this.nub = ((Integer)localArrayList2.get(0)).intValue();
        invitText(this.nub, localViewHolder.yundong_TwoLeft);
        this.nub = ((Integer)localArrayList2.get(1)).intValue();
        invitText(this.nub, localViewHolder.yundong_twoRight);
        this.nub = ((Integer)localArrayList2.get(2)).intValue();
        invitText(this.nub, localViewHolder.yundong_twobootomLeft);
        localViewHolder.yundong_TwoLeft.setVisibility(0);
        localViewHolder.yundong_twoRight.setVisibility(0);
        localViewHolder.yundong_twobootomLeft.setVisibility(0);
        localViewHolder.yundong_twobootomRight.setVisibility(8);
      }
      else if (localArrayList2.size() == 2)
      {
        this.nub = ((Integer)localArrayList2.get(0)).intValue();
        invitText(this.nub, localViewHolder.yundong_TwoLeft);
        this.nub = ((Integer)localArrayList2.get(1)).intValue();
        invitText(this.nub, localViewHolder.yundong_twoRight);
        localViewHolder.yundong_TwoLeft.setVisibility(0);
        localViewHolder.yundong_twoRight.setVisibility(0);
        localViewHolder.yundong_twobootomLeft.setVisibility(8);
        localViewHolder.yundong_twobootomRight.setVisibility(8);
      }
      else if (localArrayList2.size() == 1)
      {
        this.nub = ((Integer)localArrayList2.get(0)).intValue();
        invitText(this.nub, localViewHolder.yundong_TwoLeft);
        localViewHolder.yundong_TwoLeft.setVisibility(0);
        localViewHolder.yundong_twoRight.setVisibility(8);
        localViewHolder.yundong_twobootomLeft.setVisibility(8);
        localViewHolder.yundong_twobootomRight.setVisibility(8);
        continue;
        localViewHolder.twoLine.setVisibility(8);
        localViewHolder.down.setVisibility(8);
      }
    }
  }

  public void invitText(int paramInt, TextView paramTextView)
  {
    if (ComponentBodyTypeEnum.getEmnuByIndex(paramInt) == ComponentBodyTypeEnum.MUSCLE)
    {
      paramTextView.setText(ComponentBodyTypeEnum.MUSCLE.getName());
      paramTextView.setCompoundDrawablesWithIntrinsicBounds(2130838294, 0, 0, 0);
    }
    do
    {
      return;
      if (ComponentBodyTypeEnum.getEmnuByIndex(paramInt) == ComponentBodyTypeEnum.BONE)
      {
        paramTextView.setText(ComponentBodyTypeEnum.BONE.getName());
        paramTextView.setCompoundDrawablesWithIntrinsicBounds(2130838289, 0, 0, 0);
        return;
      }
      if (ComponentBodyTypeEnum.getEmnuByIndex(paramInt) == ComponentBodyTypeEnum.BMR)
      {
        paramTextView.setText(ComponentBodyTypeEnum.BMR.getName());
        paramTextView.setCompoundDrawablesWithIntrinsicBounds(2130838293, 0, 0, 0);
        return;
      }
      if (ComponentBodyTypeEnum.getEmnuByIndex(paramInt) == ComponentBodyTypeEnum.FAT)
      {
        paramTextView.setText(ComponentBodyTypeEnum.FAT.getName());
        paramTextView.setCompoundDrawablesWithIntrinsicBounds(2130838312, 0, 0, 0);
        return;
      }
      if (ComponentBodyTypeEnum.getEmnuByIndex(paramInt) == ComponentBodyTypeEnum.WEIGHT)
      {
        paramTextView.setText(ComponentBodyTypeEnum.WEIGHT.getName());
        ((MyApplication)this.mContext.getApplicationContext()).getCurrentRole().getSex();
        paramTextView.setCompoundDrawablesWithIntrinsicBounds(2130838305, 0, 0, 0);
        return;
      }
      if (ComponentBodyTypeEnum.getEmnuByIndex(paramInt) == ComponentBodyTypeEnum.INFAT)
      {
        paramTextView.setText(ComponentBodyTypeEnum.INFAT.getName());
        paramTextView.setCompoundDrawablesWithIntrinsicBounds(2130838297, 0, 0, 0);
        return;
      }
      if (ComponentBodyTypeEnum.getEmnuByIndex(paramInt) == ComponentBodyTypeEnum.WATER)
      {
        paramTextView.setText(ComponentBodyTypeEnum.WATER.getName());
        paramTextView.setCompoundDrawablesWithIntrinsicBounds(2130838298, 0, 0, 0);
        return;
      }
      if (ComponentBodyTypeEnum.getEmnuByIndex(paramInt) == ComponentBodyTypeEnum.PROTEIN)
      {
        paramTextView.setText(ComponentBodyTypeEnum.PROTEIN.getName());
        paramTextView.setCompoundDrawablesWithIntrinsicBounds(2130838285, 0, 0, 0);
        return;
      }
      if (ComponentBodyTypeEnum.getEmnuByIndex(paramInt) == ComponentBodyTypeEnum.BMI)
      {
        paramTextView.setText(ComponentBodyTypeEnum.BMI.getName());
        paramTextView.setCompoundDrawablesWithIntrinsicBounds(2130838284, 0, 0, 0);
        return;
      }
    }
    while (ComponentBodyTypeEnum.getEmnuByIndex(paramInt) != ComponentBodyTypeEnum.TX);
    paramTextView.setText(ComponentBodyTypeEnum.TX.getName());
    paramTextView.setCompoundDrawablesWithIntrinsicBounds(2130838304, 0, 0, 0);
  }

  static final class ViewHolder
  {
    LinearLayout LinearLayout;
    TextView cout;
    LinearLayout down;
    ImageView firstLine;
    ImageView liftImage;
    LinearLayout linear;
    ImageView textline;
    TextView titleOne;
    TextView titleTwo;
    TextView tvTitle;
    ImageView twoLine;
    LinearLayout up;
    TextView yundong_TwoLeft;
    TextView yundong_oneLeft;
    TextView yundong_oneLeftbootom;
    TextView yundong_oneRight;
    TextView yundong_oneRightbootom;
    TextView yundong_time;
    TextView yundong_twoRight;
    TextView yundong_twobootomLeft;
    TextView yundong_twobootomRight;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LiftMovePlanAdapter
 * JD-Core Version:    0.6.2
 */
