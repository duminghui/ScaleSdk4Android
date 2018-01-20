package com.picooc.guide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.picooc.LiftReportTwo;
import com.picooc.PicoocActivity;
import com.picooc.fragment.LatinMainFragment;
import java.util.List;

public class GuideAct extends PicoocActivity
{
  public static final int REQUEST_OK = 1;
  private FrameLayout frameLayout;
  private GuideModel guide;
  private List<GuideModel> guideList;
  private ImageView imageView;
  private ImageView imageView_instruction1;
  private FrameLayout.LayoutParams layoutParams;
  private int pageId;
  public float scale = 0.0F;
  private int screenHeight;
  private int screenWidth;
  private int statusBarHeight;

  public void onBackPressed()
  {
    if (this.pageId == 6)
    {
      Intent localIntent1 = new Intent(this, LiftReportTwo.class);
      localIntent1.putExtra("page", "LiftReportTwo");
      setResult(15, localIntent1);
    }
    if (this.pageId == 2)
    {
      Intent localIntent2 = new Intent(this, LatinMainFragment.class);
      localIntent2.putExtra("page", "LatinMainFragment");
      setResult(10, localIntent2);
    }
    finish();
    overridePendingTransition(-1, -1);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
    localLayoutParams.dimAmount = 0.5F;
    getWindow().setAttributes(localLayoutParams);
    getWindow().addFlags(2);
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.screenWidth = localDisplayMetrics.widthPixels;
    this.screenHeight = localDisplayMetrics.heightPixels;
    this.scale = getResources().getDisplayMetrics().density;
    setContentView(2130903079);
    this.frameLayout = ((FrameLayout)findViewById(2131099858));
    Intent localIntent = getIntent();
    this.statusBarHeight = localIntent.getIntExtra("statusBarHeight", 0);
    this.guideList = ((List)localIntent.getSerializableExtra("guideList"));
    this.pageId = getIntent().getIntExtra("pageId", -1);
    if (this.pageId != 0)
    {
      if ((this.guideList == null) && (ModelData.mGuideList != null))
        this.guideList = ModelData.mGuideList;
      if (this.guideList == null);
    }
    for (int i24 = 0; ; i24++)
    {
      if (i24 >= this.guideList.size())
        ModelData.mGuideList = null;
      switch (this.pageId)
      {
      default:
        return;
        this.layoutParams = new FrameLayout.LayoutParams(-2, -2);
        this.layoutParams.leftMargin = ((GuideModel)this.guideList.get(i24)).getX();
        this.layoutParams.topMargin = (((GuideModel)this.guideList.get(i24)).getY() - this.statusBarHeight);
        this.imageView = new ImageView(this);
        this.imageView.setImageBitmap(((GuideModel)this.guideList.get(i24)).getBitmap());
        this.frameLayout.addView(this.imageView, this.layoutParams);
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      }
    }
    this.imageView = new ImageView(this);
    this.imageView.setImageDrawable(getResources().getDrawable(2130837934));
    BitmapFactory.decodeResource(getResources(), 2130837934);
    this.layoutParams = new FrameLayout.LayoutParams(-2, -2);
    int i22 = ((GuideModel)this.guideList.get(0)).getY();
    int i23 = ((GuideModel)this.guideList.get(0)).getX();
    this.layoutParams.leftMargin = (i23 - 170);
    this.layoutParams.topMargin = (i22 + 170);
    this.frameLayout.addView(this.imageView, this.layoutParams);
    return;
    this.imageView = new ImageView(this);
    this.imageView.setImageDrawable(getResources().getDrawable(2130837931));
    Bitmap localBitmap = BitmapFactory.decodeResource(getResources(), 2130837931);
    this.layoutParams = new FrameLayout.LayoutParams(-2, -2);
    int i18 = localBitmap.getHeight();
    int i19 = localBitmap.getWidth();
    int i20 = ((GuideModel)this.guideList.get(0)).getX();
    int i21 = ((GuideModel)this.guideList.get(0)).getY();
    this.layoutParams.leftMargin = (i20 - i19 / 7);
    this.layoutParams.topMargin = (i21 - this.statusBarHeight - i18 / 5);
    this.frameLayout.addView(this.imageView, this.layoutParams);
    return;
    this.imageView = new ImageView(this);
    this.imageView.setImageDrawable(getResources().getDrawable(2130837928));
    int i15 = BitmapFactory.decodeResource(getResources(), 2130837928).getWidth();
    this.layoutParams = new FrameLayout.LayoutParams(-2, -2);
    int i16 = ((GuideModel)this.guideList.get(0)).getY();
    int i17 = ((GuideModel)this.guideList.get(0)).getX();
    this.layoutParams.leftMargin = (i17 + i15 / 3);
    this.layoutParams.topMargin = (i16 + 10);
    this.frameLayout.addView(this.imageView, this.layoutParams);
    return;
    this.imageView = new ImageView(this);
    this.imageView.setImageDrawable(getResources().getDrawable(2130837929));
    int i12 = BitmapFactory.decodeResource(getResources(), 2130837929).getWidth();
    this.layoutParams = new FrameLayout.LayoutParams(-2, -2);
    int i13 = ((GuideModel)this.guideList.get(0)).getY();
    int i14 = ((GuideModel)this.guideList.get(0)).getX();
    this.layoutParams.leftMargin = (30 + (i14 - i12));
    this.layoutParams.topMargin = (i13 + 40);
    this.frameLayout.addView(this.imageView, this.layoutParams);
    return;
    this.imageView = new ImageView(this);
    this.imageView.setImageDrawable(getResources().getDrawable(2130837927));
    this.layoutParams = new FrameLayout.LayoutParams(-2, -2);
    int i10 = ((GuideModel)this.guideList.get(0)).getY();
    int i11 = ((GuideModel)this.guideList.get(0)).getX();
    this.layoutParams.topMargin = (20 + (i10 - 4 * this.statusBarHeight));
    this.layoutParams.leftMargin = (i11 + 30);
    this.frameLayout.addView(this.imageView, this.layoutParams);
    return;
    this.imageView = new ImageView(this);
    this.imageView.setImageDrawable(getResources().getDrawable(2130837926));
    this.layoutParams = new FrameLayout.LayoutParams(-2, -2);
    int i8 = ((GuideModel)this.guideList.get(0)).getY();
    int i9 = ((GuideModel)this.guideList.get(0)).getX();
    this.layoutParams.topMargin = (-10 + (i8 - 5 * this.statusBarHeight));
    this.layoutParams.leftMargin = (i9 + 10);
    this.frameLayout.addView(this.imageView, this.layoutParams);
    return;
    this.imageView = new ImageView(this);
    this.imageView.setImageDrawable(getResources().getDrawable(2130837935));
    this.layoutParams = new FrameLayout.LayoutParams(-2, -2);
    int i6 = ((GuideModel)this.guideList.get(0)).getY();
    int i7 = ((GuideModel)this.guideList.get(0)).getX();
    this.layoutParams.leftMargin = (i7 - 350);
    this.layoutParams.topMargin = (i6 + 30);
    this.frameLayout.addView(this.imageView, this.layoutParams);
    return;
    this.imageView = new ImageView(this);
    this.imageView.setImageDrawable(getResources().getDrawable(2130837933));
    this.layoutParams = new FrameLayout.LayoutParams(-2, -2);
    int i2 = ((GuideModel)this.guideList.get(1)).getY();
    int i3 = ((GuideModel)this.guideList.get(1)).getX();
    this.layoutParams.leftMargin = (i3 - 130);
    this.layoutParams.topMargin = (i2 - 190);
    this.frameLayout.addView(this.imageView, this.layoutParams);
    this.imageView = new ImageView(this);
    this.imageView.setImageDrawable(getResources().getDrawable(2130837936));
    this.layoutParams = new FrameLayout.LayoutParams(-2, -2);
    this.layoutParams.leftMargin = 58;
    this.layoutParams.topMargin = 458;
    this.frameLayout.addView(this.imageView, this.layoutParams);
    this.imageView = new ImageView(this);
    this.imageView.setImageDrawable(getResources().getDrawable(2130837932));
    this.layoutParams = new FrameLayout.LayoutParams(-2, -2);
    int i4 = ((GuideModel)this.guideList.get(3)).getY();
    int i5 = ((GuideModel)this.guideList.get(3)).getX();
    this.layoutParams.leftMargin = (i5 + 20);
    this.layoutParams.topMargin = (i4 - 160);
    this.frameLayout.addView(this.imageView, this.layoutParams);
    return;
    this.imageView = new ImageView(this);
    this.imageView.setImageDrawable(getResources().getDrawable(2130837930));
    BitmapFactory.decodeResource(getResources(), 2130837930).getHeight();
    this.layoutParams = new FrameLayout.LayoutParams(-2, -2);
    int n = ((GuideModel)this.guideList.get(0)).getY();
    int i1 = ((GuideModel)this.guideList.get(0)).getX();
    this.layoutParams.topMargin = (n + 10);
    this.layoutParams.leftMargin = (i1 + 40);
    this.frameLayout.addView(this.imageView, this.layoutParams);
    return;
    this.imageView = new ImageView(this);
    this.imageView.setImageDrawable(getResources().getDrawable(2130837933));
    this.layoutParams = new FrameLayout.LayoutParams(-2, -2);
    int k = ((GuideModel)this.guideList.get(1)).getY();
    int m = ((GuideModel)this.guideList.get(1)).getX();
    this.layoutParams.leftMargin = (m - 130);
    this.layoutParams.topMargin = (k - 190);
    this.frameLayout.addView(this.imageView, this.layoutParams);
    this.imageView = new ImageView(this);
    this.imageView.setImageDrawable(getResources().getDrawable(2130837938));
    this.layoutParams = new FrameLayout.LayoutParams(-2, -2);
    this.layoutParams.leftMargin = 68;
    this.layoutParams.topMargin = 448;
    this.frameLayout.addView(this.imageView, this.layoutParams);
    return;
    this.imageView = new ImageView(this);
    this.imageView.setImageDrawable(getResources().getDrawable(2130837937));
    this.layoutParams = new FrameLayout.LayoutParams(-2, -2);
    this.layoutParams.leftMargin = 38;
    this.layoutParams.topMargin = 538;
    this.frameLayout.addView(this.imageView, this.layoutParams);
    this.imageView = new ImageView(this);
    this.imageView.setImageDrawable(getResources().getDrawable(2130837932));
    this.layoutParams = new FrameLayout.LayoutParams(-2, -2);
    int i = ((GuideModel)this.guideList.get(1)).getY();
    int j = ((GuideModel)this.guideList.get(1)).getX();
    this.layoutParams.leftMargin = (j + 20);
    this.layoutParams.topMargin = (i - 160);
    this.frameLayout.addView(this.imageView, this.layoutParams);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.pageId == 6)
    {
      Intent localIntent1 = new Intent(this, LiftReportTwo.class);
      localIntent1.putExtra("page", "LiftReportTwo");
      setResult(15, localIntent1);
    }
    if (this.pageId == 2)
    {
      Intent localIntent2 = new Intent(this, LatinMainFragment.class);
      localIntent2.putExtra("page", "LatinMainFragment");
      setResult(10, localIntent2);
    }
    finish();
    overridePendingTransition(-1, -1);
    return super.onTouchEvent(paramMotionEvent);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     GuideAct
 * JD-Core Version:    0.6.2
 */
