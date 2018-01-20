package com.picooc;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.picooc.adapter.MyPagerAdapter;
import com.picooc.widget.MyViewPager;
import java.util.ArrayList;
import java.util.List;
import org.achartengine.tools.ModUtils;

public class BadgeAct extends PicoocActivity
  implements View.OnClickListener
{
  static LinearLayout linerBootom;
  static TextView page;
  static RelativeLayout relaTop;
  View first;
  LayoutInflater inflater;
  boolean isup = true;
  List<String> list = new ArrayList();
  List<View> viewList = new ArrayList();
  MyViewPager viewPager;

  public static void scaleChangeanim(Object paramObject)
  {
    relaTop.setVisibility(4);
    linerBootom.setVisibility(4);
    page.setVisibility(4);
    ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(paramObject, "scaleX", new float[] { 1.0F });
    localObjectAnimator1.setDuration(200L);
    localObjectAnimator1.start();
    ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(paramObject, "scaleY", new float[] { 1.0F });
    localObjectAnimator2.setDuration(200L);
    localObjectAnimator2.start();
  }

  public static void scaleSmallanim(Object paramObject)
  {
    ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(paramObject, "scaleX", new float[] { 0.75F });
    localObjectAnimator1.setDuration(200L);
    localObjectAnimator1.start();
    ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(paramObject, "scaleY", new float[] { 0.75F });
    localObjectAnimator2.setDuration(200L);
    localObjectAnimator2.start();
    linerBootom.setVisibility(0);
    relaTop.setVisibility(0);
    page.setVisibility(0);
  }

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968576, 2130968581);
  }

  public void getlist()
  {
    int i = 0;
    if (i >= 5)
      return;
    this.first = this.inflater.inflate(2130903095, null);
    ((RelativeLayout)this.first.findViewById(2131099946)).setOnClickListener(this);
    scaleSmallanim(this.first);
    if (i == 0)
      this.list.add("肌肉男");
    while (true)
    {
      this.viewList.add(this.first);
      i++;
      break;
      if (i == 1)
        this.list.add("肥胖男");
      else if (i == 2)
        this.list.add("标准男");
      else if (i == 3)
        this.list.add("运动男");
      else if (i == 4)
        this.list.add("健身男");
    }
  }

  public void init()
  {
    this.viewPager = ((MyViewPager)findViewById(2131100355));
    relaTop = (RelativeLayout)findViewById(2131100356);
    linerBootom = (LinearLayout)findViewById(2131100357);
    page = (TextView)findViewById(2131100358);
    this.viewPager.setHorizontalScrollBarEnabled(true);
    getLayoutInflater();
    this.inflater = LayoutInflater.from(this);
    getlist();
    this.viewPager.setAdapter(new MyPagerAdapter(this.viewList, this.list, this));
    this.viewPager.setOnPageChangeListener(new MyPageListener());
    this.viewPager.setScrollable(true);
    page.setTypeface(ModUtils.getTypeface(this));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131099946:
      if (this.isup)
      {
        this.isup = false;
        scaleChangeanim(paramView);
        this.viewPager.setScrollable(false);
        return;
      }
      this.isup = true;
      scaleSmallanim(paramView);
      this.viewPager.setScrollable(true);
      return;
    case 2131099980:
      finish();
      return;
    case 2131100094:
    }
    Toast.makeText(this, "您点击了我", 1).show();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903177);
    init();
  }

  class MyPageListener
    implements ViewPager.OnPageChangeListener
  {
    MyPageListener()
    {
    }

    public void onPageScrollStateChanged(int paramInt)
    {
      Log.i("qianmo2", "scrollstateArg==" + paramInt);
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
      Log.i("qianmo2", "onPageScrolledarg0==" + paramInt1 + "--arg1==" + paramFloat + "--arg2==" + paramInt2);
    }

    public void onPageSelected(int paramInt)
    {
      Log.i("qianmo2", "onPageSelectedArg==" + paramInt);
      BadgeAct.page.setText(paramInt + 1 + "/5");
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BadgeAct
 * JD-Core Version:    0.6.2
 */
