package com.picooc.widget.everydayme;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.MyApplication;
import com.picooc.constant.ThemeConstant;
import com.picooc.db.OperationDB;
import com.picooc.domain.EveryMeEntity;
import com.picooc.utils.DateUtils;
import com.picooc.utils.TextUtils;
import com.picooc.widget.loading.PicoocToast;
import java.util.ArrayList;
import java.util.Iterator;

public class EverydayMe extends RelativeLayout
{
  private Animation alphaAnim;
  private MyApplication app;
  private CheckBox check_drink;
  private CheckBox check_physiology;
  private CheckBox check_sport;
  private CheckBox check_vegetable;
  private ChooseLayout choose;
  private RadioGroup group_happyOrNotGroup;
  private RadioGroup group_sleepGroup;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 1:
      }
      EverydayMe.this.showLayoutBg.setVisibility(8);
    }
  };
  private ArcLayout mArcLayout;
  private Context mContext;
  private ImageView mHintView;
  private TextView me;
  private ImageView moonImage;
  private Panel panel;
  private TextView panelText;
  private ImageView shine;
  private ImageView showLayoutBg;
  private ThemeConstant themeConstant;

  public EverydayMe(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public EverydayMe(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  private Animation bindItemAnimation(View paramView, boolean paramBoolean, long paramLong)
  {
    Animation localAnimation = createItemDisapperAnimation(paramLong, paramBoolean);
    paramView.setAnimation(localAnimation);
    return localAnimation;
  }

  private static Animation createHintSwitchAnimation(boolean paramBoolean, int paramInt, View paramView)
  {
    int i;
    int j;
    label25: Object localObject;
    if (paramInt == 1)
      if (paramBoolean)
      {
        i = 80;
        float f = i;
        if (!paramBoolean)
          break label92;
        j = 0;
        localObject = new RotateAnimation(f, j, 1, 0.5F, 1, 0.5F);
        ((Animation)localObject).setStartOffset(0L);
        ((Animation)localObject).setDuration(100L);
        ((Animation)localObject).setInterpolator(new DecelerateInterpolator());
        ((Animation)localObject).setFillAfter(true);
      }
    while (true)
    {
      ((Animation)localObject).setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          EverydayMe.this.setClickable(true);
        }

        public void onAnimationRepeat(Animation paramAnonymousAnimation)
        {
        }

        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
          EverydayMe.this.setClickable(false);
        }
      });
      return localObject;
      i = 0;
      break;
      label92: j = 80;
      break label25;
      localObject = new ScaleAnimation(1.0F, 1.2F, 1.0F, 1.2F, 1, 0.5F, 1, 0.5F);
      ((Animation)localObject).setRepeatMode(2);
      ((Animation)localObject).setRepeatCount(1);
      ((Animation)localObject).setDuration(100L);
    }
  }

  private static Animation createItemDisapperAnimation(long paramLong, boolean paramBoolean)
  {
    float f1 = 2.0F;
    AnimationSet localAnimationSet = new AnimationSet(true);
    float f2;
    if (paramBoolean)
    {
      f2 = f1;
      if (!paramBoolean)
        break label93;
    }
    while (true)
    {
      localAnimationSet.addAnimation(new ScaleAnimation(1.0F, f2, 1.0F, f1, 1, 0.5F, 1, 0.5F));
      localAnimationSet.addAnimation(new AlphaAnimation(1.0F, 0.0F));
      localAnimationSet.setDuration(paramLong);
      localAnimationSet.setInterpolator(new DecelerateInterpolator());
      localAnimationSet.setFillAfter(true);
      return localAnimationSet;
      f2 = 0.0F;
      break;
      label93: f1 = 0.0F;
    }
  }

  private View.OnClickListener getItemClickListener(final View.OnClickListener paramOnClickListener)
  {
    return new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        EverydayMe.this.bindItemAnimation(paramAnonymousView, true, 400L).setAnimationListener(new Animation.AnimationListener()
        {
          public void onAnimationEnd(Animation paramAnonymous2Animation)
          {
            EverydayMe.this.postDelayed(new Runnable()
            {
              public void run()
              {
                EverydayMe.this.itemDidDisappear();
              }
            }
            , 0L);
          }

          public void onAnimationRepeat(Animation paramAnonymous2Animation)
          {
          }

          public void onAnimationStart(Animation paramAnonymous2Animation)
          {
          }
        });
        int i = EverydayMe.this.mArcLayout.getChildCount();
        for (int j = 0; ; j++)
        {
          if (j >= i)
          {
            EverydayMe.this.mArcLayout.invalidate();
            EverydayMe.this.mHintView.startAnimation(EverydayMe.createHintSwitchAnimation(true, 2, paramAnonymousView));
            if (paramOnClickListener != null)
              paramOnClickListener.onClick(paramAnonymousView);
            return;
          }
          View localView = EverydayMe.this.mArcLayout.getChildAt(j);
          if (paramAnonymousView != localView)
            EverydayMe.this.bindItemAnimation(localView, false, 300L);
        }
      }
    };
  }

  private void init(Context paramContext)
  {
    this.mContext = paramContext;
    this.app = ((MyApplication)paramContext.getApplicationContext());
    ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2130903067, this);
    this.mArcLayout = ((ArcLayout)findViewById(2131099789));
    this.mHintView = ((ImageView)findViewById(2131099791));
    this.showLayoutBg = ((ImageView)findViewById(2131099784));
    setEveryDayTheme();
    this.moonImage = ((ImageView)findViewById(2131099788));
    this.moonImage.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PicoocToast.showToast((Activity)EverydayMe.this.mContext, "凌晨了，还没有睡吗？睡的好才能身体好哟。");
      }
    });
    this.choose = new ChooseLayout(paramContext);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(15);
    addView(this.choose, 0, localLayoutParams);
    this.panel = ((Panel)this.choose.findViewById(2131099760));
    this.group_happyOrNotGroup = ((RadioGroup)this.panel.findViewById(2131099762));
    this.group_sleepGroup = ((RadioGroup)this.panel.findViewById(2131099765));
    this.check_physiology = ((CheckBox)this.panel.findViewById(2131099768));
    this.check_drink = ((CheckBox)this.panel.findViewById(2131099769));
    this.check_sport = ((CheckBox)this.panel.findViewById(2131099770));
    this.check_vegetable = ((CheckBox)this.panel.findViewById(2131099771));
    this.panelText = ((TextView)this.panel.findViewById(2131099761));
    this.panel.setOnPanelListener(new Panel.OnPanelListener()
    {
      public void onPanelClosed(Panel paramAnonymousPanel)
      {
        ArrayList localArrayList = EverydayMe.this.choose.getCheckedState();
        if (localArrayList.contains(Integer.valueOf(2)))
        {
          EverydayMe.this.mHintView.setBackgroundResource(2130837643);
          EverydayMe.this.postDelayed(new Runnable()
          {
            public void run()
            {
              EverydayMe.this.handler.sendEmptyMessage(1);
            }
          }
          , 3L);
          if (localArrayList.size() > 0)
            break label87;
        }
        while (true)
        {
          return;
          if (!localArrayList.contains(Integer.valueOf(1)))
            break;
          EverydayMe.this.mHintView.setBackgroundResource(2130837641);
          break;
          label87: EverydayMe.this.removeAllItem();
          Iterator localIterator = localArrayList.iterator();
          while (localIterator.hasNext())
            switch (((Integer)localIterator.next()).intValue())
            {
            default:
              break;
            case 3:
              ImageView localImageView4 = new ImageView(EverydayMe.this.mContext);
              localImageView4.setImageResource(2130837642);
              EverydayMe.this.addItem(localImageView4, null);
              break;
            case 4:
              ImageView localImageView3 = new ImageView(EverydayMe.this.mContext);
              localImageView3.setImageResource(2130837640);
              EverydayMe.this.addItem(localImageView3, null);
              break;
            case 5:
              ImageView localImageView2 = new ImageView(EverydayMe.this.mContext);
              localImageView2.setImageResource(2130837646);
              EverydayMe.this.addItem(localImageView2, null);
              break;
            case 6:
              ImageView localImageView1 = new ImageView(EverydayMe.this.mContext);
              localImageView1.setImageResource(2130837648);
              EverydayMe.this.addItem(localImageView1, null);
            }
        }
      }

      public void onPanelOpened(Panel paramAnonymousPanel)
      {
      }
    });
    this.shine = ((ImageView)findViewById(2131099787));
    this.alphaAnim = new AlphaAnimation(0.0F, 1.0F);
    this.alphaAnim.setDuration(1500L);
    this.alphaAnim.setRepeatMode(2);
    this.alphaAnim.setRepeatCount(-1);
    final ViewGroup localViewGroup = (ViewGroup)findViewById(2131099790);
    localViewGroup.setClickable(true);
    localViewGroup.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        EverydayMe.this.setEveryDayTheme();
        if (DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), EverydayMe.this.app.getTodayBody().getTime()) != 0L)
        {
          PicoocToast.showToast((Activity)EverydayMe.this.mContext, "Hi,不如先去Latin一下？才能开始每日Me哦~");
          return;
        }
        RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)EverydayMe.this.choose.getLayoutParams();
        if (!EverydayMe.this.choose.isOpened())
        {
          EverydayMe.this.showLayoutBg.setVisibility(0);
          EverydayMe.this.shine.clearAnimation();
          EverydayMe.this.shine.setVisibility(4);
        }
        while (true)
        {
          localLayoutParams.leftMargin = (EverydayMe.this.getHeight() / 2);
          EverydayMe.this.mHintView.startAnimation(EverydayMe.createHintSwitchAnimation(EverydayMe.this.choose.isOpened(), 1, localViewGroup));
          EverydayMe.this.choose.showChooseLayout();
          return;
          EverydayMe.this.saveEveryMeData();
        }
      }
    });
    Bitmap localBitmap = BitmapFactory.decodeResource(getResources(), 2130837539);
    this.mArcLayout.setMinRadius(localBitmap.getWidth() / 2);
    localBitmap.recycle();
    this.me = ((TextView)findViewById(2131099786));
    this.me.setPadding(0, (int)(1.4D * localBitmap.getWidth()), 0, 0);
    this.me.setTypeface(TextUtils.getTypeface(paramContext, null));
  }

  private void itemDidDisappear()
  {
    int i = this.mArcLayout.getChildCount();
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        this.mArcLayout.switchState(false);
        return;
      }
      this.mArcLayout.getChildAt(j).clearAnimation();
    }
  }

  private void saveEveryMeData()
  {
    EveryMeEntity localEveryMeEntity = new EveryMeEntity();
    switch (this.group_happyOrNotGroup.getCheckedRadioButtonId())
    {
    default:
      switch (this.group_sleepGroup.getCheckedRadioButtonId())
      {
      default:
        label88: if (this.check_physiology.isChecked())
        {
          localEveryMeEntity.setPhysiology(1);
          label103: if (!this.check_drink.isChecked())
            break label256;
          localEveryMeEntity.setDrink(1);
          label118: if (!this.check_sport.isChecked())
            break label264;
          localEveryMeEntity.setSport(1);
          if (!this.check_vegetable.isChecked())
            break label272;
          localEveryMeEntity.setVegetable_and_fruit(1);
        }
        label133: break;
      case -1:
      case 2131099766:
      case 2131099767:
      }
      break;
    case -1:
    case 2131099763:
    case 2131099764:
    }
    while (true)
    {
      localEveryMeEntity.setTime(System.currentTimeMillis());
      localEveryMeEntity.setRole_id(this.app.getCurrentRole().getRole_id());
      OperationDB.insertOrUpdateEveryme(this.mContext, this.app.getCurrentRole().getRole_id(), System.currentTimeMillis(), localEveryMeEntity);
      this.app.updateEveryMe(localEveryMeEntity);
      return;
      localEveryMeEntity.setMood(0);
      break;
      localEveryMeEntity.setMood(1);
      break;
      localEveryMeEntity.setMood(2);
      break;
      localEveryMeEntity.setSleep(0);
      break label88;
      localEveryMeEntity.setSleep(1);
      break label88;
      localEveryMeEntity.setSleep(2);
      break label88;
      localEveryMeEntity.setPhysiology(0);
      break label103;
      label256: localEveryMeEntity.setDrink(0);
      break label118;
      label264: localEveryMeEntity.setSport(0);
      break label133;
      label272: localEveryMeEntity.setVegetable_and_fruit(0);
    }
  }

  public void addItem(View paramView, View.OnClickListener paramOnClickListener)
  {
    this.mArcLayout.addView(paramView);
    if (paramOnClickListener != null)
      paramView.setOnClickListener(getItemClickListener(paramOnClickListener));
  }

  public void mChooseLaout()
  {
    if (this.choose.isOpened())
      this.choose.showChooseLayout();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void refresh()
  {
    if ((DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), this.app.getTodayBody().getTime()) != 0L) && (this.choose.isOpened()))
    {
      this.choose.closeChooseLayout();
      this.mHintView.setBackgroundResource(2130837641);
    }
  }

  public void refreshEveryMe(EveryMeEntity paramEveryMeEntity)
  {
    if ((paramEveryMeEntity != null) && (paramEveryMeEntity.getMood() == 2))
      this.mHintView.setBackgroundResource(2130837643);
    int i;
    while (true)
    {
      i = DateUtils.getCurrentHour();
      if ((i <= 0) || (i >= 4))
        break;
      this.moonImage.setVisibility(0);
      this.mHintView.setVisibility(8);
      return;
      this.mHintView.setBackgroundResource(2130837641);
    }
    if ((i >= 4) && (i < 10))
    {
      switch (paramEveryMeEntity.getSleep())
      {
      default:
      case 0:
      case 1:
      case 2:
      }
      while (true)
      {
        this.moonImage.setVisibility(8);
        this.mHintView.setVisibility(0);
        this.panelText.setText("早上好！昨晚睡的怎么样？");
        this.group_happyOrNotGroup.setVisibility(8);
        this.group_sleepGroup.setVisibility(0);
        this.check_drink.setVisibility(8);
        this.check_physiology.setVisibility(8);
        this.check_sport.setVisibility(8);
        this.check_vegetable.setVisibility(8);
        return;
        this.group_happyOrNotGroup.clearCheck();
        continue;
        this.group_sleepGroup.check(2131099766);
        continue;
        this.group_sleepGroup.check(2131099767);
      }
    }
    switch (paramEveryMeEntity.getMood())
    {
    default:
      this.moonImage.setVisibility(8);
      this.mHintView.setVisibility(0);
      this.panelText.setText("下午好！今天过的怎么样？");
      this.group_happyOrNotGroup.setVisibility(0);
      this.group_sleepGroup.setVisibility(8);
      if (this.app.getCurrentRole().getSex() == 1)
      {
        this.check_physiology.setVisibility(8);
        label323: this.check_drink.setVisibility(0);
        if (paramEveryMeEntity.getDrink() != 1)
          break label497;
        this.check_drink.setChecked(true);
        label347: this.check_sport.setVisibility(0);
        if (paramEveryMeEntity.getSport() != 1)
          break label508;
        this.check_sport.setChecked(true);
      }
      break;
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      this.check_vegetable.setVisibility(0);
      if (paramEveryMeEntity.getVegetable_and_fruit() != 1)
        break label519;
      this.check_vegetable.setChecked(true);
      return;
      this.group_happyOrNotGroup.clearCheck();
      break;
      this.group_happyOrNotGroup.check(2131099763);
      break;
      this.group_happyOrNotGroup.check(2131099764);
      break;
      if (this.app.getCurrentRole().getAge() < 60)
      {
        this.check_physiology.setVisibility(0);
        if (paramEveryMeEntity.getPhysiology() == 1)
        {
          this.check_physiology.setChecked(true);
          break label323;
        }
        this.check_physiology.setChecked(false);
        break label323;
      }
      this.check_physiology.setVisibility(8);
      break label323;
      label497: this.check_drink.setChecked(false);
      break label347;
      label508: this.check_sport.setChecked(false);
    }
    label519: this.check_vegetable.setChecked(false);
  }

  public void removeAllItem()
  {
    this.mArcLayout.removeAllViews();
  }

  public void setChooseLayoutShow(boolean paramBoolean)
  {
    this.shine.setVisibility(0);
    this.shine.startAnimation(this.alphaAnim);
    if (this.mArcLayout.isExpanded())
      this.mArcLayout.switchState(false);
  }

  public void setChooseTheme()
  {
    this.choose.setChooseTheme();
  }

  public void setEveryDayTheme()
  {
    this.themeConstant = new ThemeConstant(this.mContext);
    if (this.themeConstant.getbgResource().intValue() == 2130837526)
    {
      this.showLayoutBg.setBackgroundResource(2130837692);
      return;
    }
    if (this.themeConstant.getbgResource().intValue() == 2130837527)
    {
      this.showLayoutBg.setBackgroundResource(2130837693);
      return;
    }
    this.showLayoutBg.setBackgroundResource(2130837691);
  }

  public void setMinRadius(int paramInt)
  {
    this.mArcLayout.setMinRadius(paramInt);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     EverydayMe
 * JD-Core Version:    0.6.2
 */
