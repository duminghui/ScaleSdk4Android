package com.picooc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.picooc.adapter.NewBieGuidePagerAdapter;
import com.picooc.widget.MyViewPager;
import java.util.ArrayList;
import java.util.List;

public class NewbieGuideAct extends PicoocActivity
{
  int[] ImageBg = { 2130837941, 2130837944, 2130837954, 2130837952 };
  private int curIndex;
  private ImageView[] dots;
  private View.OnClickListener dotsOnClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      int i = ((Integer)paramAnonymousView.getTag()).intValue();
      NewbieGuideAct.this.setCurView(i);
    }
  };
  private View first;
  private int[] images = { 2130837942, 2130837945, 2130837955, 2130837943 };
  LayoutInflater inflater;
  private LinearLayout l;
  private int position;
  String[] titelString = { "身体得分来到首页\n还可以随时随地分享哟", "修改目标体重更方便\n向着更美丽的自己前进吧", "实时的意见反馈系统\n小管家和您更亲近", "新增老人算法\n80岁的奶奶也能Latin啦", "" };
  private List<View> viewList = new ArrayList();
  private MyViewPager viewPager;

  private void initDots()
  {
    this.dots = new ImageView[this.images.length];
    for (int i = 0; ; i++)
    {
      if (i >= this.images.length)
      {
        Log.v("curIndex", this.curIndex);
        this.curIndex = 0;
        this.dots[this.curIndex].setEnabled(false);
        return;
      }
      this.dots[i] = new ImageView(this);
      this.dots[i].setImageResource(2130837627);
      this.dots[i].setPadding(10, 10, 10, 10);
      this.dots[i].setEnabled(true);
      this.dots[i].setOnClickListener(this.dotsOnClick);
      this.dots[i].setTag(Integer.valueOf(i));
      this.l.addView(this.dots[i]);
    }
  }

  private void setCurDot(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.images.length))
      return;
    Log.v("position", paramInt);
    this.dots[paramInt].setEnabled(false);
    this.dots[this.curIndex].setEnabled(true);
    this.curIndex = paramInt;
  }

  private void setCurView(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.images.length))
      return;
    Log.v("position2", paramInt);
    this.viewPager.setCurrentItem(paramInt);
  }

  public void finish()
  {
    super.finish();
  }

  public void getlist()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this.images.length)
        return;
      this.first = this.inflater.inflate(2130903094, null);
      this.viewList.add(this.first);
    }
  }

  public void loginClick(View paramView)
  {
    startActivity(new Intent(this, FirstAct.class));
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903213);
    getLayoutInflater();
    this.inflater = LayoutInflater.from(this);
    this.viewPager = ((MyViewPager)findViewById(2131100355));
    this.l = ((LinearLayout)findViewById(2131100619));
    this.viewPager.setHorizontalScrollBarEnabled(true);
    MyPageListener localMyPageListener = new MyPageListener();
    this.viewPager.setOnPageChangeListener(localMyPageListener);
    this.viewPager.setScrollable(true);
    getlist();
    this.viewPager.setAdapter(new NewBieGuidePagerAdapter(this.viewList, this, this.images, this.ImageBg, this.titelString));
    initDots();
  }

  public void registOnClick(View paramView)
  {
    startActivity(new Intent(this, RegistAct.class));
    finish();
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
      NewbieGuideAct.this.position = paramInt;
      Log.i("qianmo2", "onPageSelectedArg==" + paramInt);
      NewbieGuideAct.this.setCurDot(paramInt);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     NewbieGuideAct
 * JD-Core Version:    0.6.2
 */
