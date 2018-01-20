package com.picooc.widget.chart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.picooc.domain.EveryMeEntity;
import com.picooc.utils.DateUtils;
import com.picooc.utils.NumUtils;
import com.picooc.utils.TypefaceUtils;
import org.achartengine.tools.ModUtils;

public class PopView extends FrameLayout
{
  private TextView bmi;
  private TextView body_fat;
  private TextView dabiaoText;
  private TextView date;
  private ImageView drink;
  private ImageView happy;
  private FrameLayout linearLayout = (FrameLayout)LayoutInflater.from(getContext()).inflate(2130903117, this);
  private Context mContext;
  private ImageView physiology;
  private LinearLayout picooc_imgs;
  private LinearLayout poptitle;
  private ImageView sad;
  private ImageView sleepBad;
  private ImageView sleepGood;
  private ImageView sport;
  private ImageView vegetable;
  private TextView weight;

  public PopView(Context paramContext)
  {
    this(paramContext, null);
  }

  public PopView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    Typeface localTypeface = TypefaceUtils.getTypeface(paramContext, null);
    this.date = ((TextView)this.linearLayout.findViewById(2131100032));
    this.body_fat = ((TextView)this.linearLayout.findViewById(2131100034));
    this.bmi = ((TextView)this.linearLayout.findViewById(2131100035));
    this.weight = ((TextView)this.linearLayout.findViewById(2131099856));
    this.poptitle = ((LinearLayout)findViewById(2131100031));
    this.sleepGood = ((ImageView)findViewById(2131100037));
    this.sleepBad = ((ImageView)findViewById(2131100038));
    this.happy = ((ImageView)findViewById(2131100039));
    this.sad = ((ImageView)findViewById(2131100040));
    this.drink = ((ImageView)findViewById(2131100041));
    this.vegetable = ((ImageView)findViewById(2131100042));
    this.sport = ((ImageView)findViewById(2131100043));
    this.physiology = ((ImageView)findViewById(2131100044));
    this.picooc_imgs = ((LinearLayout)this.linearLayout.findViewById(2131100036));
    Bitmap localBitmap = BitmapFactory.decodeResource(paramContext.getResources(), 2130838108);
    int i = localBitmap.getHeight();
    this.linearLayout.setMinimumWidth(i * 7);
    localBitmap.recycle();
    this.date.setTypeface(localTypeface);
    this.body_fat.setTypeface(localTypeface);
    this.bmi.setTypeface(localTypeface);
    this.weight.setTypeface(localTypeface);
    this.dabiaoText = ((TextView)findViewById(2131100033));
    this.mContext = paramContext;
  }

  public void changeData(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, EveryMeEntity paramEveryMeEntity, Boolean paramBoolean, int paramInt)
  {
    this.date.setText(DateUtils.changeTimeStampToFormatTime(paramLong, "MM月dd日"));
    this.body_fat.setText("脂肪率 " + NumUtils.roundValue(paramFloat2) + "%");
    this.bmi.setText("BMI " + NumUtils.roundValue(paramFloat3));
    this.weight.setText(NumUtils.roundValue(paramFloat1) + "kg");
    if (paramBoolean.booleanValue())
    {
      this.poptitle.setBackgroundResource(2130838118);
      this.weight.setBackgroundResource(2130838119);
      this.dabiaoText.setVisibility(0);
      FrameLayout.LayoutParams localLayoutParams2 = new FrameLayout.LayoutParams(-2, -2);
      localLayoutParams2.setMargins(0, this.date.getHeight() + ModUtils.dip2px(this.mContext, 3.0F), ModUtils.dip2px(this.mContext, 3.0F), 0);
      localLayoutParams2.gravity = 5;
      this.weight.setLayoutParams(localLayoutParams2);
      this.date.setTextColor(Color.parseColor("#ffffff"));
      this.body_fat.setTextColor(Color.parseColor("#ffffff"));
      this.weight.setTextColor(Color.parseColor("#ffffff"));
      this.bmi.setTextColor(Color.parseColor("#ffffff"));
      this.dabiaoText.setTextColor(Color.parseColor("#ffffff"));
      switch (paramInt)
      {
      default:
      case 1:
      case 2:
      case 3:
      }
    }
    while (paramEveryMeEntity == null)
    {
      this.happy.setVisibility(8);
      this.sad.setVisibility(8);
      this.sleepGood.setVisibility(8);
      this.sleepBad.setVisibility(8);
      this.drink.setVisibility(8);
      this.vegetable.setVisibility(8);
      this.sport.setVisibility(8);
      this.physiology.setVisibility(8);
      invalidate();
      return;
      this.linearLayout.setBackgroundResource(2130838116);
      continue;
      this.linearLayout.setBackgroundResource(2130838115);
      continue;
      this.linearLayout.setBackgroundResource(2130838117);
      continue;
      this.poptitle.setBackgroundResource(2130838112);
      this.weight.setBackgroundResource(2130838113);
      this.dabiaoText.setVisibility(8);
      FrameLayout.LayoutParams localLayoutParams1 = new FrameLayout.LayoutParams(-2, -2);
      localLayoutParams1.setMargins(0, ModUtils.dip2px(this.mContext, 3.0F), ModUtils.dip2px(this.mContext, 3.0F), 0);
      localLayoutParams1.gravity = 5;
      this.weight.setLayoutParams(localLayoutParams1);
      this.date.setTextColor(Color.parseColor("#bbffffff"));
      this.body_fat.setTextColor(Color.parseColor("#55a3ac"));
      this.weight.setTextColor(Color.parseColor("#bbffffff"));
      this.bmi.setTextColor(Color.parseColor("#55a3ac"));
      this.dabiaoText.setTextColor(Color.parseColor("#bbffffff"));
      switch (paramInt)
      {
      default:
        break;
      case 1:
        this.linearLayout.setBackgroundResource(2130838100);
        break;
      case 2:
        this.linearLayout.setBackgroundResource(2130838099);
        break;
      case 3:
        this.linearLayout.setBackgroundResource(2130838101);
      }
    }
    int i3;
    label664: int i2;
    label707: label714: int i1;
    label757: label764: int n;
    label807: label814: int m;
    label857: label864: int k;
    label907: label914: int j;
    label957: label964: int i;
    if (paramEveryMeEntity.getMood() == 1)
    {
      this.happy.setVisibility(0);
      this.happy.invalidate();
      ImageView localImageView8 = this.happy;
      if (paramBoolean.booleanValue())
      {
        i3 = 2130837656;
        localImageView8.setImageResource(i3);
        if (paramEveryMeEntity.getMood() != 2)
          break label1047;
        this.sad.setVisibility(0);
        this.sad.invalidate();
        ImageView localImageView7 = this.sad;
        if (!paramBoolean.booleanValue())
          break label1039;
        i2 = 2130837664;
        localImageView7.setImageResource(i2);
        if (paramEveryMeEntity.getSleep() != 1)
          break label1066;
        this.sleepGood.setVisibility(0);
        this.sleepGood.invalidate();
        ImageView localImageView6 = this.sleepGood;
        if (!paramBoolean.booleanValue())
          break label1059;
        i1 = 2130838110;
        localImageView6.setImageResource(i1);
        if (paramEveryMeEntity.getSleep() != 2)
          break label1086;
        this.sleepBad.setVisibility(0);
        this.sleepBad.invalidate();
        ImageView localImageView5 = this.sleepBad;
        if (!paramBoolean.booleanValue())
          break label1078;
        n = 2130838107;
        localImageView5.setImageResource(n);
        if (paramEveryMeEntity.getDrink() != 1)
          break label1106;
        this.drink.setVisibility(0);
        this.drink.invalidate();
        ImageView localImageView4 = this.drink;
        if (!paramBoolean.booleanValue())
          break label1098;
        m = 2130837652;
        localImageView4.setImageResource(m);
        if (paramEveryMeEntity.getVegetable_and_fruit() != 1)
          break label1126;
        this.vegetable.setVisibility(0);
        this.vegetable.invalidate();
        ImageView localImageView3 = this.vegetable;
        if (!paramBoolean.booleanValue())
          break label1118;
        k = 2130837679;
        localImageView3.setImageResource(k);
        if (paramEveryMeEntity.getSport() != 1)
          break label1146;
        this.sport.setVisibility(0);
        this.sport.invalidate();
        ImageView localImageView2 = this.sport;
        if (!paramBoolean.booleanValue())
          break label1138;
        j = 2130837674;
        localImageView2.setImageResource(j);
        if (paramEveryMeEntity.getPhysiology() != 1)
          break label1166;
        this.physiology.setVisibility(0);
        this.physiology.invalidate();
        ImageView localImageView1 = this.physiology;
        if (!paramBoolean.booleanValue())
          break label1158;
        i = 2130837660;
        label1007: localImageView1.setImageResource(i);
      }
    }
    while (true)
    {
      invalidate();
      return;
      i3 = 2130838103;
      break;
      this.happy.setVisibility(8);
      break label664;
      label1039: i2 = 2130838105;
      break label707;
      label1047: this.sad.setVisibility(8);
      break label714;
      label1059: i1 = 2130838108;
      break label757;
      label1066: this.sleepGood.setVisibility(8);
      break label764;
      label1078: n = 2130838106;
      break label807;
      label1086: this.sleepBad.setVisibility(8);
      break label814;
      label1098: m = 2130838102;
      break label857;
      label1106: this.drink.setVisibility(8);
      break label864;
      label1118: k = 2130838114;
      break label907;
      label1126: this.vegetable.setVisibility(8);
      break label914;
      label1138: j = 2130838111;
      break label957;
      label1146: this.sport.setVisibility(8);
      break label964;
      label1158: i = 2130838104;
      break label1007;
      label1166: this.physiology.setVisibility(8);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PopView
 * JD-Core Version:    0.6.2
 */
