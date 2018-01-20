package com.picooc.widget;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.achartengine.tools.ModUtils;

public class widgetAddView
{
  Context mContext;

  public widgetAddView(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public static void addview(LinearLayout paramLinearLayout, Context paramContext, boolean paramBoolean, int paramInt1, String paramString1, String paramString2, int paramInt2)
  {
    RelativeLayout localRelativeLayout = new RelativeLayout(paramContext);
    VerticalLine localVerticalLine1 = new VerticalLine(paramContext);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(ModUtils.dip2px(paramContext, 2.0F), ModUtils.dip2px(paramContext, 30.0F));
    localLayoutParams1.setMargins(ModUtils.dip2px(paramContext, 25.0F), 0, 0, 0);
    localVerticalLine1.setLayoutParams(localLayoutParams1);
    localVerticalLine1.setId(localVerticalLine1.hashCode());
    localRelativeLayout.addView(localVerticalLine1);
    ImageView localImageView1 = new ImageView(paramContext);
    localImageView1.setImageResource(2130837612);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams2.addRule(3, localVerticalLine1.hashCode());
    localLayoutParams2.setMargins(ModUtils.dip2px(paramContext, 22.0F), 0, 0, 0);
    localImageView1.setLayoutParams(localLayoutParams2);
    localImageView1.setId(localImageView1.hashCode());
    localRelativeLayout.addView(localImageView1);
    if (!paramBoolean)
    {
      VerticalLine localVerticalLine2 = new VerticalLine(paramContext);
      RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(ModUtils.dip2px(paramContext, 2.0F), ModUtils.dip2px(paramContext, 30.0F));
      localLayoutParams3.addRule(3, localImageView1.hashCode());
      localLayoutParams3.setMargins(ModUtils.dip2px(paramContext, 25.0F), 0, 0, 0);
      localVerticalLine2.setLayoutParams(localLayoutParams3);
      localRelativeLayout.addView(localVerticalLine2);
    }
    while (true)
    {
      LinearLayout localLinearLayout = new LinearLayout(paramContext);
      RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams4.addRule(1, localImageView1.hashCode());
      localLayoutParams4.addRule(15);
      localLinearLayout.setLayoutParams(localLayoutParams4);
      localLinearLayout.setOrientation(0);
      ImageView localImageView2 = new ImageView(paramContext);
      LinearLayout.LayoutParams localLayoutParams5 = new LinearLayout.LayoutParams(-2, -2);
      localLayoutParams5.gravity = 16;
      localImageView2.setImageResource(paramInt1);
      localImageView2.setLayoutParams(localLayoutParams5);
      localLinearLayout.addView(localImageView2);
      TextView localTextView1 = new TextView(paramContext);
      LinearLayout.LayoutParams localLayoutParams6 = new LinearLayout.LayoutParams(-2, -2);
      localLayoutParams6.setMargins(10, 0, 0, 0);
      localLayoutParams6.gravity = 16;
      localTextView1.setLayoutParams(localLayoutParams6);
      localTextView1.setText(paramString1);
      localTextView1.setTextColor(-1);
      localTextView1.setGravity(16);
      localLinearLayout.addView(localTextView1);
      TextView localTextView2 = new TextView(paramContext);
      LinearLayout.LayoutParams localLayoutParams7 = new LinearLayout.LayoutParams(-2, -2);
      localLayoutParams7.setMargins(2, 0, 0, 0);
      localLayoutParams7.gravity = 16;
      localTextView2.setLayoutParams(localLayoutParams7);
      localTextView2.setText(paramString2);
      localTextView2.setTypeface(ModUtils.getTypeface(paramContext));
      localTextView2.setTextColor(Color.parseColor("#ffea00"));
      localTextView2.setGravity(16);
      localLinearLayout.addView(localTextView2);
      localRelativeLayout.addView(localLinearLayout);
      paramLinearLayout.addView(localRelativeLayout);
      return;
      TextView localTextView3 = new TextView(paramContext);
      RelativeLayout.LayoutParams localLayoutParams8 = new RelativeLayout.LayoutParams(ModUtils.dip2px(paramContext, 2.0F), ModUtils.dip2px(paramContext, 30.0F));
      localLayoutParams8.addRule(3, localImageView1.hashCode());
      localLayoutParams8.setMargins(ModUtils.dip2px(paramContext, 25.0F), 0, 0, 0);
      localTextView3.setLayoutParams(localLayoutParams8);
      localRelativeLayout.addView(localTextView3);
    }
  }

  public static void addview(LinearLayout paramLinearLayout, Context paramContext, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2)
  {
    RelativeLayout localRelativeLayout = new RelativeLayout(paramContext);
    VerticalLine localVerticalLine1 = new VerticalLine(paramContext);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(ModUtils.dip2px(paramContext, 2.0F), ModUtils.dip2px(paramContext, 30.0F));
    localLayoutParams1.setMargins(ModUtils.dip2px(paramContext, 25.0F), 0, 0, 0);
    localVerticalLine1.setLayoutParams(localLayoutParams1);
    localVerticalLine1.setId(localVerticalLine1.hashCode());
    localRelativeLayout.addView(localVerticalLine1);
    ImageView localImageView1 = new ImageView(paramContext);
    localImageView1.setImageResource(2130837612);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams2.addRule(3, localVerticalLine1.hashCode());
    localLayoutParams2.setMargins(ModUtils.dip2px(paramContext, 22.0F), 0, 0, 0);
    localImageView1.setLayoutParams(localLayoutParams2);
    localImageView1.setId(localImageView1.hashCode());
    localRelativeLayout.addView(localImageView1);
    LinearLayout localLinearLayout;
    TextView localTextView4;
    if (!paramBoolean2)
    {
      VerticalLine localVerticalLine2 = new VerticalLine(paramContext);
      RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(ModUtils.dip2px(paramContext, 2.0F), ModUtils.dip2px(paramContext, 30.0F));
      localLayoutParams3.addRule(3, localImageView1.hashCode());
      localLayoutParams3.setMargins(ModUtils.dip2px(paramContext, 25.0F), 0, 0, 0);
      localVerticalLine2.setLayoutParams(localLayoutParams3);
      localRelativeLayout.addView(localVerticalLine2);
      localLinearLayout = new LinearLayout(paramContext);
      RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
      localLinearLayout.setId(localLinearLayout.hashCode());
      localLayoutParams4.addRule(1, localImageView1.hashCode());
      localLayoutParams4.addRule(15);
      localLinearLayout.setLayoutParams(localLayoutParams4);
      localLinearLayout.setOrientation(0);
      ImageView localImageView2 = new ImageView(paramContext);
      LinearLayout.LayoutParams localLayoutParams5 = new LinearLayout.LayoutParams(-2, -2);
      localLayoutParams5.gravity = 16;
      localImageView2.setImageResource(paramInt1);
      localImageView2.setLayoutParams(localLayoutParams5);
      localLinearLayout.addView(localImageView2);
      TextView localTextView1 = new TextView(paramContext);
      LinearLayout.LayoutParams localLayoutParams6 = new LinearLayout.LayoutParams(-2, -2);
      localLayoutParams6.setMargins(10, 0, 0, 0);
      localLayoutParams6.gravity = 16;
      localTextView1.setLayoutParams(localLayoutParams6);
      localTextView1.setText(paramString1);
      localTextView1.setTextColor(-1);
      localTextView1.setGravity(16);
      localLinearLayout.addView(localTextView1);
      TextView localTextView2 = new TextView(paramContext);
      LinearLayout.LayoutParams localLayoutParams7 = new LinearLayout.LayoutParams(-2, -2);
      localLayoutParams7.setMargins(2, 0, 0, 0);
      localLayoutParams7.gravity = 16;
      localTextView2.setLayoutParams(localLayoutParams7);
      localTextView2.setText(paramString2);
      localTextView2.setTypeface(ModUtils.getTypeface(paramContext));
      localTextView2.setTextColor(Color.parseColor("#ffea00"));
      localTextView2.setGravity(16);
      localLinearLayout.addView(localTextView2);
      TextView localTextView3 = new TextView(paramContext);
      LinearLayout.LayoutParams localLayoutParams8 = new LinearLayout.LayoutParams(-2, -2);
      localLayoutParams8.setMargins(2, 0, 0, 0);
      localLayoutParams8.gravity = 16;
      localTextView3.setLayoutParams(localLayoutParams8);
      localTextView3.setText("，   还需");
      localTextView3.setTextColor(-1);
      localTextView3.setGravity(16);
      if (paramInt2 != 0)
        localLinearLayout.addView(localTextView3);
      localTextView4 = new TextView(paramContext);
      localTextView4.setText(paramString3);
      localTextView4.setTypeface(ModUtils.getTypeface(paramContext));
      localTextView4.setTextColor(Color.parseColor("#84ff00"));
      localTextView4.setBackgroundResource(2130837615);
      LinearLayout.LayoutParams localLayoutParams9 = new LinearLayout.LayoutParams(-2, -2);
      localLayoutParams9.gravity = 16;
      localLayoutParams9.setMargins(3, 0, 0, 0);
      localTextView4.setLayoutParams(localLayoutParams9);
      localTextView4.setGravity(17);
      if (paramInt2 != 1)
        break label914;
    }
    label914: for (int i = 2130837620; ; i = 2130837609)
    {
      localTextView4.setCompoundDrawablesWithIntrinsicBounds(i, 0, 0, 0);
      localTextView4.setPadding(ModUtils.dip2px(paramContext, 15.0F), ModUtils.dip2px(paramContext, 3.0F), ModUtils.dip2px(paramContext, 10.0F), ModUtils.dip2px(paramContext, 2.0F));
      localTextView4.setCompoundDrawablePadding(2);
      if (paramInt2 != 0)
        localLinearLayout.addView(localTextView4);
      localRelativeLayout.addView(localLinearLayout);
      paramLinearLayout.addView(localRelativeLayout);
      return;
      if (paramBoolean1)
      {
        TextView localTextView5 = new TextView(paramContext);
        RelativeLayout.LayoutParams localLayoutParams10 = new RelativeLayout.LayoutParams(ModUtils.dip2px(paramContext, 1.0F), ModUtils.dip2px(paramContext, 30.0F));
        localLayoutParams10.addRule(3, localImageView1.hashCode());
        localLayoutParams10.setMargins(ModUtils.dip2px(paramContext, 25.0F), 0, 0, 0);
        localTextView5.setBackgroundColor(Color.parseColor("#30ffffff"));
        localTextView5.setLayoutParams(localLayoutParams10);
        localTextView5.setId(paramInt1);
        localRelativeLayout.addView(localTextView5);
        break;
      }
      TextView localTextView6 = new TextView(paramContext);
      RelativeLayout.LayoutParams localLayoutParams11 = new RelativeLayout.LayoutParams(ModUtils.dip2px(paramContext, 2.0F), ModUtils.dip2px(paramContext, 30.0F));
      localLayoutParams11.addRule(3, localImageView1.hashCode());
      localLayoutParams11.setMargins(ModUtils.dip2px(paramContext, 25.0F), 0, 0, 0);
      localTextView6.setLayoutParams(localLayoutParams11);
      localRelativeLayout.addView(localTextView6);
      break;
    }
  }

  public void addview(LinearLayout paramLinearLayout, String paramString, int paramInt1, int paramInt2)
  {
    TextView localTextView = new TextView(this.mContext);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -2);
    localLayoutParams1.setMargins(0, paramInt1, 25, 0);
    localTextView.setLayoutParams(localLayoutParams1);
    localTextView.setTextColor(-1);
    localTextView.setBackgroundResource(2130838645);
    ModUtils.replaceString(localTextView, this.mContext, paramString, false);
    localTextView.setTypeface(ModUtils.getTypeface(this.mContext));
    localTextView.setPadding(25, 25, 25, 25);
    localTextView.setTextSize(12.0F);
    localTextView.setLineSpacing(3.4F, 1.2F);
    new HorzhiLine(this.mContext, null);
    ImageView localImageView = new ImageView(this.mContext);
    localImageView.setBackgroundResource(2130838295);
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(33, 20);
    localLayoutParams2.gravity = 16;
    localImageView.setLayoutParams(localLayoutParams2);
    LinearLayout localLinearLayout = new LinearLayout(this.mContext);
    localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    localLinearLayout.setOrientation(0);
    localLinearLayout.addView(localImageView);
    localLinearLayout.addView(localTextView);
    paramLinearLayout.addView(localLinearLayout);
  }

  public void addviewYajiank(LinearLayout paramLinearLayout, String paramString, int paramInt1, int paramInt2)
  {
    TextView localTextView = new TextView(this.mContext);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -2);
    localLayoutParams1.setMargins(0, paramInt1, 25, 0);
    localTextView.setLayoutParams(localLayoutParams1);
    localTextView.setTextColor(-1);
    localTextView.setBackgroundResource(2130838190);
    ModUtils.replaceString(localTextView, this.mContext, paramString, true);
    localTextView.setTypeface(ModUtils.getTypeface(this.mContext));
    localTextView.setPadding(25, 25, 25, 25);
    localTextView.setTextSize(12.0F);
    localTextView.setLineSpacing(3.4F, 1.2F);
    new HorzhiLine(this.mContext, null);
    ImageView localImageView = new ImageView(this.mContext);
    localImageView.setBackgroundResource(2130838295);
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(33, 20);
    localLayoutParams2.gravity = 16;
    localImageView.setLayoutParams(localLayoutParams2);
    LinearLayout localLinearLayout = new LinearLayout(this.mContext);
    localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    localLinearLayout.setOrientation(0);
    localLinearLayout.addView(localImageView);
    localLinearLayout.addView(localTextView);
    paramLinearLayout.addView(localLinearLayout);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     widgetAddView
 * JD-Core Version:    0.6.2
 */
