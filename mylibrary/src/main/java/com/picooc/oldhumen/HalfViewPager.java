package com.picooc.oldhumen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.picooc.MyApplication;
import com.picooc.model.BodyTypeEnum;

import java.util.ArrayList;
import org.achartengine.tools.ModUtils;

@SuppressLint({"NewApi"})
public class HalfViewPager extends ViewPager
{
  private ArrayList<View> mChildList = new ArrayList();
  private ArrayList<ImageView> mFatImgList = new ArrayList();
  private ArrayList<TextView> mFatTvList = new ArrayList();
  private View mLeft;
  private PagerAdapter mPagerAdapter = new PagerAdapter()
  {
    public void destroyItem(ViewGroup paramAnonymousViewGroup, int paramAnonymousInt, Object paramAnonymousObject)
    {
      if (getCount() > 1)
        paramAnonymousViewGroup.removeView((View)HalfViewPager.this.mChildList.get(paramAnonymousInt));
    }

    public int getCount()
    {
      return HalfViewPager.this.mChildList.size();
    }

    public int getItemPosition(Object paramAnonymousObject)
    {
      return -2;
    }

    public Object instantiateItem(ViewGroup paramAnonymousViewGroup, int paramAnonymousInt)
    {
      paramAnonymousViewGroup.addView((View)HalfViewPager.this.mChildList.get(paramAnonymousInt));
      return HalfViewPager.this.mChildList.get(paramAnonymousInt);
    }

    public boolean isViewFromObject(View paramAnonymousView, Object paramAnonymousObject)
    {
      return paramAnonymousView == paramAnonymousObject;
    }
  };
  private View mRight;

  public HalfViewPager(Context paramContext)
  {
    super(paramContext);
    initChild(paramContext);
  }

  public HalfViewPager(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initChild(paramContext);
  }

  public static int getRealOldIndex(int paramInt)
  {
    if (paramInt == 0);
    while (paramInt == 1)
      return paramInt;
    if (paramInt == 6)
      return 2;
    if (paramInt == 4)
      return 3;
    if (paramInt == 8)
      return 4;
    return 3;
  }

  private void upLeftTypeFace(View paramView)
  {
    ((TextView)paramView.findViewById(2131100061)).setTypeface(ModUtils.getTypeface(getContext()));
    ((TextView)paramView.findViewById(2131100234)).setTypeface(ModUtils.getTypeface(getContext()));
    ((TextView)paramView.findViewById(2131100238)).setTypeface(ModUtils.getTypeface(getContext()));
    ((TextView)paramView.findViewById(2131100242)).setTypeface(ModUtils.getTypeface(getContext()));
  }

  private void updateOldMan(View paramView)
  {
    String[] arrayOfString = BodyTypeEnum.bodyTypeNames_old;
    this.mFatTvList.add((TextView)paramView.findViewById(2131100260));
    this.mFatTvList.add((TextView)paramView.findViewById(2131100262));
    this.mFatTvList.add((TextView)paramView.findViewById(2131100264));
    this.mFatTvList.add((TextView)paramView.findViewById(2131100266));
    this.mFatTvList.add((TextView)paramView.findViewById(2131100268));
    this.mFatImgList.add((ImageView)paramView.findViewById(2131100259));
    this.mFatImgList.add((ImageView)paramView.findViewById(2131100261));
    this.mFatImgList.add((ImageView)paramView.findViewById(2131100263));
    this.mFatImgList.add((ImageView)paramView.findViewById(2131100265));
    this.mFatImgList.add((ImageView)paramView.findViewById(2131100267));
    int i = this.mFatTvList.size();
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      ((TextView)this.mFatTvList.get(j)).setText(arrayOfString[j]);
    }
  }

  private void updateRight(View paramView)
  {
    String[] arrayOfString = BodyTypeEnum.getBodyTypeArrayBySex(((MyApplication)getContext().getApplicationContext()).getCurrentRole().getSex(), 0);
    this.mFatTvList.add((TextView)paramView.findViewById(2131100094));
    this.mFatTvList.add((TextView)paramView.findViewById(2131100095));
    this.mFatTvList.add((TextView)paramView.findViewById(2131100096));
    this.mFatTvList.add((TextView)paramView.findViewById(2131100245));
    this.mFatTvList.add((TextView)paramView.findViewById(2131100247));
    this.mFatTvList.add((TextView)paramView.findViewById(2131100249));
    this.mFatTvList.add((TextView)paramView.findViewById(2131100254));
    this.mFatTvList.add((TextView)paramView.findViewById(2131100256));
    this.mFatTvList.add((TextView)paramView.findViewById(2131100258));
    this.mFatImgList.add((ImageView)paramView.findViewById(2131100250));
    this.mFatImgList.add((ImageView)paramView.findViewById(2131100251));
    this.mFatImgList.add((ImageView)paramView.findViewById(2131100252));
    this.mFatImgList.add((ImageView)paramView.findViewById(2131100244));
    this.mFatImgList.add((ImageView)paramView.findViewById(2131100246));
    this.mFatImgList.add((ImageView)paramView.findViewById(2131100248));
    this.mFatImgList.add((ImageView)paramView.findViewById(2131100253));
    this.mFatImgList.add((ImageView)paramView.findViewById(2131100255));
    this.mFatImgList.add((ImageView)paramView.findViewById(2131100257));
    int i = this.mFatTvList.size();
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      ((TextView)this.mFatTvList.get(j)).setText(arrayOfString[j]);
    }
  }

  public void init()
  {
    setAdapter(this.mPagerAdapter);
  }

  public void initChild(Context paramContext)
  {
  }

  public void restore(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    removeAllViews();
    this.mChildList.clear();
    this.mFatImgList.clear();
    this.mFatTvList.clear();
    setData(paramString1, paramString2, paramString3, paramString4);
  }

  public void setData(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.mLeft = LayoutInflater.from(getContext()).inflate(2130903167, null);
    upLeftTypeFace(this.mLeft);
    ((TextView)this.mLeft.findViewById(2131100061)).setText(paramString1);
    ((TextView)this.mLeft.findViewById(2131100234)).setText(paramString2);
    ((TextView)this.mLeft.findViewById(2131100238)).setText(paramString3);
    ((TextView)this.mLeft.findViewById(2131100242)).setText(paramString4);
    if (Age.isOld((MyApplication)getContext().getApplicationContext()))
    {
      this.mRight = LayoutInflater.from(getContext()).inflate(2130903169, null);
      updateOldMan(this.mRight);
    }
    while (true)
    {
      this.mChildList.add(this.mLeft);
      this.mChildList.add(this.mRight);
      setAdapter(this.mPagerAdapter);
      System.out.println("getAdapter().getCount() =" + getAdapter().getCount());
      return;
      this.mRight = LayoutInflater.from(getContext()).inflate(2130903168, null);
      updateRight(this.mRight);
    }
  }

  public void updateViews(int paramInt)
  {
    System.out.println("index =" + paramInt);
    MyApplication localMyApplication = (MyApplication)getContext().getApplicationContext();
    Age.FAT.Res localRes = Age.FAT.Res.getResByIndex(paramInt, localMyApplication);
    int i = paramInt;
    if (Age.isOld(localMyApplication))
      i = Age.FAT.Res.getRealOldIndex(i);
    System.out.println("childIndex =" + i);
    ((TextView)this.mFatTvList.get(i)).setTextColor(localRes.color);
    Drawable localDrawable = getResources().getDrawable(localRes.checkedId);
    ((ImageView)this.mFatImgList.get(i)).setImageDrawable(localDrawable);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     HalfViewPager
 * JD-Core Version:    0.6.2
 */
