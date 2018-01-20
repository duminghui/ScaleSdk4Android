package com.picooc;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.adapter.GalleryAdapter;
import com.picooc.arithmetic.ReportDirect;
import com.picooc.constant.ThemeConstant;
import com.picooc.domain.ReportEntity;
import com.picooc.utils.NumUtils;
import com.picooc.widget.MyGallery;
import com.picooc.widget.loading.PicoocToast;
import com.picooc.widget.picoocProgress.PicoocAlertDialog4FamilyPasswrod;
import com.picooc.widget.picoocProgress.PicoocAlertDialog4GoalWeight;
import java.util.ArrayList;
import java.util.List;
import org.achartengine.tools.ModUtils;

public class WriteReferenThreeAct extends PicoocActivity
  implements AdapterView.OnItemSelectedListener
{
  private static final long DELAY = 500L;
  private static final int MSG_ZOOM_IN = 1;
  private RelativeLayout RelatGllger;
  private TextView TextCurrentWeight;
  private TextView WeightFanWei;
  int addInvitCount = 0;
  private int addOrReduceOrState = -1;
  private List<String> addlist;
  private GalleryAdapter adpter;
  private MyApplication app;
  private TextView curenttext;
  private TextView curentweight;
  PicoocAlertDialog4GoalWeight dialog_goal_Weight;
  PicoocAlertDialog4FamilyPasswrod dilog;
  private MyGallery gallery;
  private int invitSubtractCout = 0;
  private float invitSubtractWeiht = 0.0F;
  private final float invitrighandlifttcout = 20.0F;
  Boolean isExit = Boolean.valueOf(false);
  private List<String> list;
  private LinearLayout logokuang;
  Handler mGalleryHandler = new Handler()
  {
    public void dispatchMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 1)
      {
        if (WriteReferenThreeAct.this.addOrReduceOrState == 1)
        {
          WriteReferenThreeAct.this.textgange.setText((String)WriteReferenThreeAct.this.list.get(paramAnonymousMessage.arg1));
          float f2 = Float.parseFloat((String)WriteReferenThreeAct.this.list.get(paramAnonymousMessage.arg1));
          WriteReferenThreeAct.this.translateMark(WriteReferenThreeAct.this.mark, WriteReferenThreeAct.this.realityWeight - f2, WriteReferenThreeAct.this.markX);
          WriteReferenThreeAct.this.curenttext.setText(ModUtils.caclutSaveOnePoint(WriteReferenThreeAct.this.realityWeight - f2));
        }
      }
      else
        return;
      WriteReferenThreeAct.this.textgange.setText((CharSequence)WriteReferenThreeAct.this.addlist.get(paramAnonymousMessage.arg1));
      float f1 = Float.parseFloat((String)WriteReferenThreeAct.this.addlist.get(paramAnonymousMessage.arg1));
      WriteReferenThreeAct.this.translateMark(WriteReferenThreeAct.this.mark, f1 + WriteReferenThreeAct.this.realityWeight, WriteReferenThreeAct.this.markX);
      WriteReferenThreeAct.this.curenttext.setText(ModUtils.caclutSaveOnePoint(f1 + WriteReferenThreeAct.this.realityWeight));
    }
  };
  private LinearLayout m_mubiaokuang;
  private LinearLayout mark;
  private float markCurrentwith = 0.0F;
  private float markX = 0.0F;
  private float markziji = 0.0F;
  private TextView mubiaoText;
  private float normMaxi = 56.299999F;
  private float normMini = 46.099998F;
  private float nowHeiht;
  private float nowWidth;
  private TextView picc;
  private ImageView pointImage = null;
  private LinearLayout realityImage;
  private float realityWeight = 64.599998F;
  float[] regionArray;
  private TextView textLift;
  private TextView textState;
  private TextView textgange;
  private TextView textritht;
  private float twoTextWitht = 0.0F;
  private ObjectAnimator upmator;
  private ReportEntity weightReport;

  public void calculateAddList(float paramFloat)
  {
    if (paramFloat > this.normMini)
      getAddlist(paramFloat / 2.0F);
    while (paramFloat > this.normMini)
      return;
    if (paramFloat + paramFloat / 2.0F >= this.normMaxi)
    {
      getAddlist(paramFloat / 2.0F);
      return;
    }
    getAddlistTwo(this.realityWeight, this.normMaxi - paramFloat);
  }

  public float calculateLift(float paramFloat)
  {
    return this.markCurrentwith / 3.0F - this.markCurrentwith / 3.0F * (this.normMini - paramFloat) / 20.0F;
  }

  public float calculateLiftmin(float paramFloat)
  {
    return 0.0F;
  }

  public float calculateMiddle(float paramFloat)
  {
    float f1 = paramFloat - this.normMini;
    float f2 = this.normMaxi - this.normMini;
    return f1 * (this.markCurrentwith / 3.0F) / f2 + this.markCurrentwith / 3.0F;
  }

  public float calculateRight(float paramFloat)
  {
    return (paramFloat - this.normMaxi) * (this.markCurrentwith / 3.0F) / 20.0F + 2.0F * (this.markCurrentwith / 3.0F);
  }

  public float calculateRihtMax(float paramFloat)
  {
    return this.markCurrentwith / 3.0F * (paramFloat - this.normMaxi) / (this.realityWeight - this.normMaxi) + 2.0F * (this.markCurrentwith / 3.0F);
  }

  public int calculateSubtractList(float paramFloat)
  {
    int i;
    if (paramFloat / 2.0F <= this.normMini)
      i = getlist(paramFloat, paramFloat / 2.0F);
    boolean bool;
    do
    {
      return i;
      bool = paramFloat / 2.0F < this.normMini;
      i = 0;
    }
    while (!bool);
    return getlist(paramFloat, paramFloat - this.normMini);
  }

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968576, 2130968581);
  }

  public void getAddlist(float paramFloat)
  {
    this.addlist = new ArrayList();
    float f = 0.0F;
    for (int i = 0; ; i++)
    {
      if (i >= 5000);
      do
      {
        return;
        f += 0.5F;
      }
      while (f >= 0.5D + paramFloat);
      this.addlist.add(f);
    }
  }

  public void getAddlistTwo(float paramFloat1, float paramFloat2)
  {
    this.addlist = new ArrayList();
    float f = 0.0F;
    Boolean localBoolean = Boolean.valueOf(true);
    for (int i = 0; ; i++)
    {
      if (i >= 5000);
      do
      {
        return;
        f += 0.5F;
        if ((localBoolean.booleanValue()) && (paramFloat1 + f >= this.normMini + (this.normMaxi - this.normMini) / 2.0F))
        {
          localBoolean = Boolean.valueOf(false);
          this.addInvitCount = i;
        }
      }
      while (f >= 0.5D + paramFloat2);
      this.addlist.add(f);
    }
  }

  public int getlist(float paramFloat1, float paramFloat2)
  {
    this.list = new ArrayList();
    int i = 0;
    float f = 0.0F;
    Boolean localBoolean = Boolean.valueOf(true);
    for (int j = 0; ; j++)
    {
      if (j >= 5000);
      do
      {
        return i;
        f += 0.5F;
        if ((localBoolean.booleanValue()) && (paramFloat1 - f <= this.normMini + (this.normMaxi - this.normMini) / 2.0F))
        {
          localBoolean = Boolean.valueOf(false);
          i = j;
          this.invitSubtractWeiht = (paramFloat1 - f);
        }
      }
      while (f >= 0.5D + paramFloat2);
      this.list.add(f);
    }
  }

  public void invit()
  {
    new ThemeConstant(this).setTheme(findViewById(2131100727));
    this.gallery = ((MyGallery)findViewById(2131100564));
    this.gallery.setCallbackDuringFling(false);
    this.gallery.setOnItemSelectedListener(this);
    this.textgange = ((TextView)findViewById(2131100409));
    this.textLift = ((TextView)findViewById(2131100730));
    this.textritht = ((TextView)findViewById(2131100731));
    this.pointImage = ((ImageView)findViewById(2131100729));
    this.realityImage = ((LinearLayout)findViewById(2131100732));
    this.mark = ((LinearLayout)findViewById(2131099691));
    this.curenttext = ((TextView)findViewById(2131100728));
    this.curentweight = ((TextView)findViewById(2131099690));
    this.RelatGllger = ((RelativeLayout)findViewById(2131100640));
    this.TextCurrentWeight = ((TextView)findViewById(2131100406));
    this.TextCurrentWeight.setText("当前体重为" + NumUtils.roundValue(this.app.getTodayBody().getWeight()) + "kg");
    this.textState = ((TextView)findViewById(2131099730));
    if (this.weightReport.getStateCode() <= 1)
    {
      this.textState.setBackgroundResource(2130838197);
      this.textState.setText(" 偏低 ");
    }
    while (true)
    {
      this.regionArray = this.weightReport.getRegionArray();
      this.picc = ((TextView)findViewById(2131100637));
      this.WeightFanWei = ((TextView)findViewById(2131100638));
      String str = "您的标准体重区间为" + NumUtils.roundValue(this.regionArray[0]) + "kg~" + NumUtils.roundValue(this.regionArray[1]) + "kg";
      this.WeightFanWei.setText(str);
      this.normMini = this.regionArray[0];
      this.normMaxi = this.regionArray[1];
      this.curenttext.setTypeface(ModUtils.getTypeface(this));
      this.textgange.setTypeface(ModUtils.getTypeface(this));
      this.textLift.setTypeface(ModUtils.getTypeface(this));
      this.textritht.setTypeface(ModUtils.getTypeface(this));
      this.curentweight.setTypeface(ModUtils.getTypeface(this));
      this.TextCurrentWeight.setTypeface(ModUtils.getTypeface(this));
      this.picc.setTypeface(ModUtils.getTypeface(this));
      this.WeightFanWei.setTypeface(ModUtils.getTypeface(this));
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      this.nowWidth = localDisplayMetrics.widthPixels;
      this.nowHeiht = localDisplayMetrics.heightPixels;
      this.markCurrentwith = (this.nowWidth - ModUtils.dip2px(this, 32.0F));
      this.m_mubiaokuang = ((LinearLayout)findViewById(2131100417));
      this.logokuang = ((LinearLayout)findViewById(2131100335));
      this.mubiaoText = ((TextView)findViewById(2131100560));
      this.mubiaoText.setTypeface(ModUtils.getTypeface(this));
      this.markziji = ModUtils.dip2px(this, 15.0F);
      this.twoTextWitht = ModUtils.dip2px(this, 30.0F);
      translateText(this.textLift, this.markCurrentwith / 3.0F - this.twoTextWitht);
      translateText(this.textritht, 2.0F * (this.markCurrentwith / 3.0F));
      translateRelayWeight(this.realityImage, this.realityWeight);
      translateRelayWeight(this.mark, this.realityWeight);
      this.mark.setVisibility(4);
      this.invitSubtractCout = calculateSubtractList(this.realityWeight);
      this.RelatGllger.setVisibility(4);
      calculateAddList(this.realityWeight);
      this.textLift.setText(NumUtils.roundValue(this.normMini));
      this.textritht.setText(NumUtils.roundValue(this.normMaxi));
      this.curentweight.setText(this.realityWeight);
      return;
      if (this.weightReport.getStateCode() >= 3)
      {
        this.textState.setBackgroundResource(2130838184);
        this.textState.setText(" 超标 ");
      }
      else
      {
        this.textState.setBackgroundResource(2130838179);
        this.textState.setText(" 标准 ");
      }
    }
  }

  public void invitAdapter(int paramInt, Boolean paramBoolean)
  {
    if (paramBoolean.booleanValue());
    for (this.adpter = new GalleryAdapter(this, this.list); ; this.adpter = new GalleryAdapter(this, this.addlist))
    {
      this.gallery.setAdapter(this.adpter);
      this.gallery.setSelection(paramInt);
      this.gallery.setOnItemSelectedListener(this);
      return;
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 101)
      finish();
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100420:
      if (this.addOrReduceOrState == -1)
      {
        PicoocToast.showToast(this, "您需要选择一个目标哦~");
        return;
      }
      float f = this.app.getTodayBody().getWeight();
      if (this.addOrReduceOrState == 1)
      {
        f -= Float.parseFloat(this.textgange.getText().toString());
        if (f < 0.8F * this.regionArray[0])
          showWeightLowDialog();
      }
      else if (this.addOrReduceOrState == 2)
      {
        f += Float.parseFloat(this.textgange.getText().toString());
      }
      this.app.getCurrentRole().setWeight_change_target(f - this.app.getTodayBody().getWeight());
      this.app.getCurrentRole().setGoal_weight(f);
      if (this.app.getTodayBody().getBodyFat() > 0.0F)
      {
        Intent localIntent1 = new Intent(this, BodyfatGoal.class);
        localIntent1.putExtra("goalWeight", f);
        startActivityForResult(localIntent1, 501);
        return;
      }
      Intent localIntent2 = new Intent();
      localIntent2.setAction("com.picooc.latin.setting.goal.weight");
      sendBroadcast(localIntent2);
      super.finish();
      overridePendingTransition(2130968576, 2130968581);
      return;
    case 2131100415:
      this.mark.setVisibility(0);
      this.RelatGllger.setVisibility(0);
      this.logokuang.setBackgroundResource(2130838054);
      this.m_mubiaokuang.setVisibility(8);
      invitAdapter(this.invitSubtractCout, Boolean.valueOf(true));
      this.addOrReduceOrState = 1;
      return;
    case 2131100733:
      this.mark.setVisibility(0);
      this.RelatGllger.setVisibility(0);
      this.m_mubiaokuang.setVisibility(8);
      this.logokuang.setBackgroundResource(2130838058);
      invitAdapter(this.addInvitCount, Boolean.valueOf(false));
      this.addOrReduceOrState = 2;
      return;
    case 2131100416:
    }
    this.RelatGllger.setVisibility(8);
    this.m_mubiaokuang.setVisibility(0);
    this.mubiaoText.setText(this.realityWeight + " kg");
    this.mark.setVisibility(8);
    this.addOrReduceOrState = 3;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903231);
    this.app = ((MyApplication)getApplicationContext());
    this.realityWeight = this.app.getTodayBody().getWeight();
    this.weightReport = ReportDirect.judgeWeightByRole(this.app.getCurrentRole(), this.realityWeight);
    invit();
  }

  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this.mGalleryHandler.removeCallbacksAndMessages(paramAdapterView);
    Message localMessage = Message.obtain(this.mGalleryHandler, 1, paramAdapterView);
    localMessage.arg1 = paramInt;
    this.mGalleryHandler.sendMessageDelayed(localMessage, 500L);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
      return true;
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onNothingSelected(AdapterView<?> paramAdapterView)
  {
  }

  public void showWeightLowDialog()
  {
    this.dialog_goal_Weight = new PicoocAlertDialog4GoalWeight(this);
    this.dialog_goal_Weight.showAlerDialog_Goal_Weight();
  }

  public void translateMark(Object paramObject, float paramFloat1, float paramFloat2)
  {
    if ((paramFloat1 >= this.normMaxi) && (paramFloat1 <= 20.0F + this.normMaxi))
      translateXiaoQi(paramObject, paramFloat2, calculateRight(paramFloat1));
    do
    {
      return;
      if (paramFloat1 > 20.0F + this.normMaxi)
      {
        translateXiaoQi(paramObject, paramFloat2, this.markCurrentwith);
        return;
      }
      if ((paramFloat1 >= this.normMini) && (paramFloat1 < this.normMaxi))
      {
        translateXiaoQi(paramObject, paramFloat2, calculateMiddle(paramFloat1));
        return;
      }
      if ((paramFloat1 >= this.normMini - 20.0F) && (paramFloat1 < this.normMini))
      {
        translateXiaoQi(paramObject, paramFloat2, calculateLift(paramFloat1));
        return;
      }
    }
    while ((paramFloat1 <= 0.0F) || (paramFloat1 >= this.normMini - 20.0F));
    translateXiaoQi(paramObject, paramFloat2, calculateLiftmin(paramFloat1));
  }

  public void translateRelayWeight(Object paramObject, float paramFloat)
  {
    if ((paramFloat >= this.normMaxi) && (paramFloat <= 20.0F + this.normMaxi))
      translateText(paramObject, calculateRight(paramFloat));
    do
    {
      return;
      if (paramFloat > 20.0F + this.normMaxi)
      {
        translateText(paramObject, this.markCurrentwith);
        return;
      }
      if ((paramFloat >= this.normMini) && (paramFloat < this.normMaxi))
      {
        translateText(paramObject, calculateMiddle(paramFloat));
        return;
      }
      if ((paramFloat >= this.normMini - 20.0F) && (paramFloat < this.normMini))
      {
        translateText(paramObject, calculateLift(paramFloat));
        return;
      }
    }
    while ((paramFloat <= 0.0F) || (paramFloat >= this.normMini - 20.0F));
    translateText(paramObject, calculateLiftmin(paramFloat));
  }

  public void translateText(Object paramObject, float paramFloat)
  {
    this.upmator = ObjectAnimator.ofFloat(paramObject, "translationX", new float[] { 0.0F, paramFloat });
    this.upmator.setDuration(1L);
    this.upmator.start();
    this.markX = paramFloat;
  }

  public void translateXiaoQi(Object paramObject, float paramFloat1, float paramFloat2)
  {
    this.upmator = ObjectAnimator.ofFloat(paramObject, "translationX", new float[] { paramFloat1, paramFloat2 });
    this.upmator.setDuration(1000L);
    this.upmator.start();
    this.markX = paramFloat2;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     WriteReferenThreeAct
 * JD-Core Version:    0.6.2
 */
