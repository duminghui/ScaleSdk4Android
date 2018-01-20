package com.picooc;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.adapter.SharePagerAdapter;
import com.picooc.camera.CameraPreview;
import com.picooc.camera.CameraPreview.OnCameraStatusListener;
import com.picooc.camera.zhuzhuangtuView;
import com.picooc.guide.GuideAct;
import com.picooc.guide.GuideModel;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.model.BodyScoreModel;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.ThirdPartLogin;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.MyViewPager;
import com.picooc.widget.anyncImageView.AsyncImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.achartengine.tools.ModUtils;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class ShareAct extends Activity
  implements OnCameraStatusListener
{
  public static int CUTPIC = 6;
  private static int PICTURE = 5;
  private Bitmap FenBitmap = null;
  private RelativeLayout LayoutbootomRight;
  ImageView MoveImag;
  TextView Movetext;
  private int _xDelta;
  private int _yDelta;
  ImageView add_Image;
  AnimUtils animeUtlils;
  private MyApplication app;
  private LinearLayout beatLayout;
  private BodyScoreModel bodyScoreModel;
  boolean bootomIsOpen = true;
  ImageView bootomleftImage;
  ImageView camera_Image;
  private ImageView camera_led;
  private int curIndex;
  private ImageView[] dots;
  private View.OnClickListener dotsOnClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      int i = ((Integer)paramAnonymousView.getTag()).intValue();
      ShareAct.this.setCurView(i);
    }
  };
  int during = 500;
  private ImageView fenShuRightImage;
  LinearLayout fenxiangLinear;
  private RelativeLayout fenxiangRelativeOut;
  private ImageView fenxiang_huizhuangImage;
  private TextView fenxiang_miaoshu;
  private View first;
  private int flag = 0;
  private ImageView focusView;
  LinearLayout greenLinear;
  private TextView greenNoDataText;
  private LinearLayout greenNotData;
  private TextView greenText;
  RelativeLayout greenTitel;
  private LinearLayout greenTitelLiner;
  TextView green_dateText;
  private AsyncImageView head_image;
  float heith;
  LinearLayout hong_liner;
  private TextView hong_text;
  private TextView hong_textTwo;
  private TextView hong_textmiaoshu;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      if (localResponseEntity.getMethod().equals("getMyScorePosition"))
        while (true)
        {
          int i;
          try
          {
            i = localResponseEntity.getResp().getInt("defeat");
            if (i <= 0)
              break;
            if (ShareAct.this.bodyScoreModel.getTotalScore() >= 100)
              break label427;
            ShareAct.this.hong_textmiaoshu.setText("击败" + i + "%的Latin用户");
            ShareAct.this.beatLayout = ((LinearLayout)ShareAct.this.findViewById(2131100703));
            if ((ShareAct.this.bodyScoreModel != null) && (ShareAct.this.bodyScoreModel.getTitle_out() != null) && (!ShareAct.this.bodyScoreModel.getTitle_out().equals("")) && (ShareAct.this.app.getTodayBody().getBodyFat() > 0.0F) && (ShareAct.this.bodyScoreModel != null) && (!ShareAct.this.bodyScoreModel.getSharedMessage().equals("")))
            {
              ShareAct.this.fenxiang_miaoshu.setText("打败了" + i + "%的Latin用户，" + ShareAct.this.bodyScoreModel.getSharedMessage());
              ShareAct.this.fenxiang_miaoshu.setTextColor(ModUtils.getPaintColor(ShareAct.this.bodyScoreModel.getTotalScore(), ShareAct.this.fenShuRightImage, false));
              if (ShareAct.this.bodyScoreModel.isGood() == -1)
                ShareAct.this.redBaozi.setImageResource(2130837773);
            }
            else
            {
              LinearLayout localLinearLayout = ShareAct.this.beatLayout;
              float[] arrayOfFloat = new float[2];
              arrayOfFloat[0] = ShareAct.this.beatLayout.getAlpha();
              arrayOfFloat[1] = 1.0F;
              ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(localLinearLayout, "alpha", arrayOfFloat);
              localObjectAnimator.setDuration(500L);
              localObjectAnimator.start();
              return;
            }
            if (ShareAct.this.bodyScoreModel.isGood() == 0)
            {
              ShareAct.this.redBaozi.setImageResource(2130837529);
              continue;
            }
          }
          catch (JSONException localJSONException)
          {
            localJSONException.printStackTrace();
            return;
          }
          ShareAct.this.redBaozi.setImageResource(2130837772);
          continue;
          label427: ShareAct.this.hong_textmiaoshu.setText("和全国" + i + "%的人一起稳居榜首！");
          if ((ShareAct.this.bodyScoreModel != null) && (ShareAct.this.bodyScoreModel.getTitle_out() != null) && (!ShareAct.this.bodyScoreModel.getTitle_out().equals("")) && (ShareAct.this.app.getTodayBody().getBodyFat() > 0.0F) && (ShareAct.this.bodyScoreModel != null) && (!ShareAct.this.bodyScoreModel.getSharedMessage().equals("")))
          {
            ShareAct.this.fenxiang_miaoshu.setText(ShareAct.this.bodyScoreModel.getSharedMessage() + "，和全国" + i + "%的人一起稳居榜首哦！");
            ShareAct.this.fenxiang_miaoshu.setTextColor(ModUtils.getPaintColor(ShareAct.this.bodyScoreModel.getTotalScore(), ShareAct.this.fenShuRightImage, false));
            if (ShareAct.this.bodyScoreModel.isGood() == -1)
              ShareAct.this.redBaozi.setImageResource(2130837773);
            else if (ShareAct.this.bodyScoreModel.isGood() == 0)
              ShareAct.this.redBaozi.setImageResource(2130837529);
            else
              ShareAct.this.redBaozi.setImageResource(2130837772);
          }
        }
    }
  };
  ImageView image0;
  private ImageView imagePic;
  private int[] images = null;
  LayoutInflater inflater;
  private boolean isClickble = true;
  boolean isOpen = true;
  private boolean isResume = true;
  private LinearLayout l;
  LinearLayout linearLayoutbootom;
  private LinearLayout linearViewPager;
  private List<String> list = new ArrayList();
  private int[] locImageView;
  private ImageView lvBaozi;
  LinearLayout lv_liner;
  private TextView lv_text;
  private TextView lv_textRight;
  private TextView lv_textleft;
  private TextView lv_textmiaoshu;
  private CameraPreview mCameraPreview;
  private GuideModel mGuide;
  private List<GuideModel> mGuideList;
  private View.OnTouchListener movingEventListener = new View.OnTouchListener()
  {
    int bottom;
    int lastX;
    int lastX2;
    int lastY;
    int lastY2;
    int left;
    int right;
    int top;

    public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
    {
      switch (paramAnonymousMotionEvent.getAction())
      {
      default:
      case 0:
      case 2:
      case 1:
      }
      while (true)
      {
        return true;
        this.lastX = ((int)paramAnonymousMotionEvent.getRawX());
        this.lastY = ((int)paramAnonymousMotionEvent.getRawY());
        this.lastX2 = this.lastX;
        this.lastY2 = this.lastY;
        Log.i("picooc", "1111lastx==" + this.lastX + "---lasty==" + this.lastY + "--left==");
        continue;
        int i = (int)paramAnonymousMotionEvent.getRawX() - this.lastX;
        int j = (int)paramAnonymousMotionEvent.getRawY() - this.lastY;
        this.left = (i + paramAnonymousView.getLeft());
        this.top = (j + paramAnonymousView.getTop());
        this.right = (i + paramAnonymousView.getRight());
        this.bottom = (j + paramAnonymousView.getBottom());
        if (this.left < 0)
        {
          this.left = 0;
          this.right = (this.left + paramAnonymousView.getWidth());
        }
        if (this.right > ShareAct.this.screenWidth)
        {
          this.right = ShareAct.this.screenWidth;
          this.left = (this.right - paramAnonymousView.getWidth());
        }
        if (this.top < ModUtils.dip2px(ShareAct.this, 15.0F) + ShareAct.this.touMingText.getHeight())
        {
          this.top = (ModUtils.dip2px(ShareAct.this, 15.0F) + ShareAct.this.touMingText.getHeight());
          this.bottom = (this.top + paramAnonymousView.getHeight());
        }
        if (this.bottom > ShareAct.this.fenxiangRelativeOut.getHeight() - ShareAct.this.linearLayoutbootom.getHeight())
        {
          this.bottom = (ShareAct.this.fenxiangRelativeOut.getHeight() - ShareAct.this.linearLayoutbootom.getHeight());
          this.top = (this.bottom - paramAnonymousView.getHeight());
        }
        paramAnonymousView.layout(this.left, this.top, this.right, this.bottom);
        this.lastX = ((int)paramAnonymousMotionEvent.getRawX());
        this.lastY = ((int)paramAnonymousMotionEvent.getRawY());
        continue;
        Log.i("picooc", "lastx==" + this.lastX + "---lasty==" + this.lastY + "--left==" + paramAnonymousView.getTag().toString());
      }
    }
  };
  private View.OnTouchListener movingEventListenerTwo = new View.OnTouchListener()
  {
    public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
    {
      int i = (int)paramAnonymousMotionEvent.getRawX();
      int j = (int)paramAnonymousMotionEvent.getRawY();
      switch (0xFF & paramAnonymousMotionEvent.getAction())
      {
      case 3:
      case 4:
      case 5:
      case 6:
      default:
        return true;
      case 0:
        RelativeLayout.LayoutParams localLayoutParams2 = (RelativeLayout.LayoutParams)paramAnonymousView.getLayoutParams();
        ShareAct.this._xDelta = (i - localLayoutParams2.leftMargin);
        ShareAct.this._yDelta = (j - localLayoutParams2.topMargin);
        ShareAct.this.viewpagerTwo.setScrollable(false);
        return true;
      case 1:
        ShareAct.this.viewpagerTwo.setScrollable(true);
        return true;
      case 2:
      }
      Log.i("picooc", "left==" + (i - ShareAct.this._xDelta) + "  top==" + (j - ShareAct.this._yDelta) + "---height==" + (ShareAct.this.fenxiangRelativeOut.getHeight() - ShareAct.this.linearLayoutbootom.getHeight()));
      int k = i - ShareAct.this._xDelta;
      int m = j - ShareAct.this._yDelta;
      if (k < 0)
        k = 0;
      if (k > ShareAct.this.screenWidth - paramAnonymousView.getWidth())
        k = ShareAct.this.screenWidth - paramAnonymousView.getWidth();
      if (m < ModUtils.dip2px(ShareAct.this, 15.0F) + ShareAct.this.touMingText.getHeight())
        m = ModUtils.dip2px(ShareAct.this, 15.0F) + ShareAct.this.touMingText.getHeight();
      if (m > ShareAct.this.fenxiangRelativeOut.getHeight() - ShareAct.this.linearLayoutbootom.getHeight() - paramAnonymousView.getHeight())
        m = ShareAct.this.fenxiangRelativeOut.getHeight() - ShareAct.this.linearLayoutbootom.getHeight() - paramAnonymousView.getHeight();
      RelativeLayout.LayoutParams localLayoutParams1 = (RelativeLayout.LayoutParams)paramAnonymousView.getLayoutParams();
      localLayoutParams1.leftMargin = k;
      localLayoutParams1.topMargin = m;
      localLayoutParams1.rightMargin = -250;
      localLayoutParams1.bottomMargin = -250;
      paramAnonymousView.setLayoutParams(localLayoutParams1);
      return true;
    }
  };
  boolean openLed = true;
  Animation operatingAnim_off;
  Animation operatingAnim_on;
  private ImageView picoocImage;
  private int position;
  private final int qqZoneKey = 3;
  private ImageView redBaozi;
  LinearLayout redLinear;
  private LinearLayout redNotData;
  private TextView redText;
  RelativeLayout redTitel;
  private ImageView redXiaoImage;
  TextView redataText;
  ImageView redright_Image;
  RelativeLayout.LayoutParams rel;
  RelativeLayout relativeTitel;
  LinearLayout.LayoutParams rl;
  private final int savePhotoKey = 4;
  private int screenHeight = 960;
  private int screenWidth = 720;
  LinearLayout shuiYinLinear;
  private LinearLayout shuiyinLinerOne;
  private RelativeLayout shuiyinLinerTwo;
  private final int sinaKey = 0;
  private LinearLayout takeTitelRight;
  private TextView textDate;
  private TextView textFenShu;
  private TextView textName;
  TextView textview;
  private ThirdPartLogin thirdPart;
  private int titelDistance;
  ImageView topleftImage;
  TextView touMingText;
  private List<View> viewList = new ArrayList();
  private List<View> viewListTwo = new ArrayList();
  private MyViewPager viewPager;
  private MyViewPager viewpagerTwo;
  LinearLayout waiBaoLinear;
  private final int weiXinCrileKey = 2;
  private final int weiXinKey = 1;
  float witht;
  zhuzhuangtuView zhu;
  LinearLayout zhuLeralout;

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
      this.dots[i].setImageResource(2130837625);
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
    if (this.linearViewPager.isShown())
    {
      this.viewpagerTwo.setCurrentItem(paramInt);
      return;
    }
    this.viewPager.setCurrentItem(paramInt);
  }

  public static void setLayout(View paramView, int paramInt1, int paramInt2)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = new ViewGroup.MarginLayoutParams(paramView.getLayoutParams());
    localMarginLayoutParams.setMargins(paramInt1, paramInt2, paramInt1 + localMarginLayoutParams.width, paramInt2 + localMarginLayoutParams.height);
    paramView.setLayoutParams(new RelativeLayout.LayoutParams(localMarginLayoutParams));
  }

  private void startAnima1(Object paramObject, int paramInt)
  {
    AnimUtils.LiftandRightMove(paramObject, 0, -this.screenWidth, paramInt);
  }

  private void startAnima2(Object paramObject, int paramInt)
  {
    AnimUtils.LiftandRightMove(paramObject, this.screenWidth, 0, paramInt);
  }

  private void startAnima3(Object paramObject, int paramInt)
  {
    AnimUtils.LiftandRightMove(paramObject, -this.screenWidth, 0, paramInt);
  }

  private void startAnima4(Object paramObject, int paramInt)
  {
    AnimUtils.LiftandRightMove(paramObject, 0, this.screenWidth, paramInt);
  }

  public static int statusBarHeight(Activity paramActivity)
  {
    Rect localRect = new Rect();
    paramActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
    int i = localRect.top;
    System.out.println(i);
    return i;
  }

  private Bitmap takeScreenShot(Activity paramActivity)
  {
    (getGreenOpenHeight() / this.heith);
    float f = (this.heith - getGreenOpenHeight() - this.linearLayoutbootom.getHeight() - statusBarHeight(paramActivity)) / this.heith;
    View localView = paramActivity.getWindow().getDecorView();
    localView.setDrawingCacheEnabled(true);
    localView.buildDrawingCache();
    Bitmap localBitmap1 = localView.getDrawingCache();
    Rect localRect = new Rect();
    paramActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
    int i = localRect.top;
    System.out.println(i);
    if (this.l.isShown());
    for (int j = this.l.getHeight() + ModUtils.dip2px(this, 5.0F); ; j = 0)
    {
      Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1, 0, i + getGreenOpenHeight(), localBitmap1.getWidth(), (int)(f * localBitmap1.getHeight()) - j);
      localView.destroyDrawingCache();
      return localBitmap2;
    }
  }

  private Bitmap takeScreenShot(Activity paramActivity, Bitmap paramBitmap)
  {
    float f1 = getGreenOpenHeight() / this.heith;
    float f2 = (this.heith - getGreenOpenHeight() - this.linearLayoutbootom.getHeight()) / this.heith;
    Log.i("picooc", "-----------jieping  relativeTitel.getHeight()=" + f1 + "  heitg=" + f2);
    return Bitmap.createBitmap(paramBitmap, 0, (int)(f1 * paramBitmap.getHeight()), paramBitmap.getWidth(), (int)(f2 * paramBitmap.getHeight()));
  }

  public Bitmap HeChengBitmap(Bitmap paramBitmap)
  {
    getCurrentImage();
    Log.i("picooc", "jieshoudao   ==========data");
    this.MoveImag.setDrawingCacheEnabled(true);
    Bitmap localBitmap1 = ((BitmapDrawable)this.MoveImag.getDrawable()).getBitmap();
    this.MoveImag.setDrawingCacheEnabled(false);
    Log.i("picooc", "imageWiht==" + this.MoveImag.getWidth() + "  imageHeight==" + this.MoveImag.getHeight() + "    bitmapW=" + localBitmap1.getWidth() + "  bitmapH=" + localBitmap1.getHeight());
    Bitmap localBitmap2 = ((BitmapDrawable)this.picoocImage.getDrawable()).getBitmap();
    float f = paramBitmap.getWidth() / this.screenWidth;
    return createBitmap(takeScreenShot(this, paramBitmap), ModUtils.zoomImg(localBitmap1, (int)(f * this.MoveImag.getWidth()), (int)(f * this.MoveImag.getHeight())), ModUtils.zoomImg(localBitmap2, (int)(f * localBitmap2.getWidth()), (int)(f * localBitmap2.getHeight())), "wo shi hecheng");
  }

  public void MarginLayoutParams(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ((RelativeLayout.LayoutParams)paramView.getLayoutParams()).setMargins(paramInt1, paramInt2, paramInt3, paramInt4);
    paramView.requestLayout();
    paramView.bringToFront();
  }

  public void botoomClosed()
  {
    if ((this.bodyScoreModel != null) && (!this.bodyScoreModel.getTitle_out().equals("")))
      this.redText.setText(this.bodyScoreModel.getTitle_out());
    this.redText.setTextColor(Color.parseColor("#b57b90"));
    this.bootomIsOpen = true;
    this.animeUtlils.upMove(this.waiBaoLinear, -(this.waiBaoLinear.getHeight() - (this.relativeTitel.getHeight() + ModUtils.dip2px(this, 15.0F))), 0, this.during);
    this.animeUtlils.upMove(this.fenxiangLinear, 0, 2000, this.during);
    this.animeUtlils.missAnima(this.linearLayoutbootom, 300L);
    this.animeUtlils.upMove(this.redTitel, this.greenLinear.getHeight() - (this.relativeTitel.getHeight() + ModUtils.dip2px(this, 20.0F)), 0, this.during);
    this.redright_Image.startAnimation(this.operatingAnim_off);
    this.bootomleftImage.setImageResource(2130837739);
    this.animeUtlils.showAnima(this.hong_liner, this.during);
  }

  public void botoomOpen()
  {
    this.redText.setText("今日身体得分");
    this.redText.setTextColor(-1);
    this.FenBitmap = null;
    this.l.setVisibility(8);
    this.shuiYinLinear.setAlpha(0.0F);
    this.fenxiangLinear.setAlpha(1.0F);
    this.fenxiangLinear.setVisibility(0);
    this.bootomIsOpen = false;
    this.animeUtlils.upMove(this.waiBaoLinear, 0, -(this.waiBaoLinear.getHeight() - (this.relativeTitel.getHeight() + ModUtils.dip2px(this, 15.0F))), this.during);
    this.animeUtlils.upMove(this.fenxiangLinear, 2000, 0, this.during);
    this.animeUtlils.showAnima(this.linearLayoutbootom, 300L);
    this.animeUtlils.downMove(this.redTitel, 0, this.greenLinear.getHeight() - (this.relativeTitel.getHeight() + ModUtils.dip2px(this, 20.0F)), this.during);
    this.redright_Image.startAnimation(this.operatingAnim_on);
    this.bootomleftImage.setImageResource(2130837738);
    this.animeUtlils.missAnima(this.hong_liner, this.during);
  }

  public Bitmap createBitmap(Bitmap paramBitmap1, Bitmap paramBitmap2, Bitmap paramBitmap3, String paramString)
  {
    getCurrentImage();
    float f1 = this.MoveImag.getLeft() / this.witht;
    float f2 = (this.MoveImag.getTop() - getGreenOpenHeight()) / (this.heith - getGreenOpenHeight() - this.linearLayoutbootom.getHeight() - statusBarHeight(this));
    float f3 = this.Movetext.getLeft() / this.witht;
    float f4 = (this.Movetext.getTop() - getGreenOpenHeight() + (this.Movetext.getHeight() - this.Movetext.getHeight() / 3)) / (this.heith - getGreenOpenHeight() - this.linearLayoutbootom.getHeight() - statusBarHeight(this));
    Log.i("picooc", "88888888888888viewHeight==" + this.fenxiangRelativeOut.getHeight());
    if (paramBitmap1 == null)
      return paramBitmap1;
    int i = paramBitmap1.getWidth();
    int j = paramBitmap1.getHeight();
    int k = paramBitmap2.getWidth();
    int m = paramBitmap2.getHeight();
    Log.i("jiangqq", "w = " + i + ",h = " + j + ",ww = " + k + ",wh = " + m);
    Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    localCanvas.drawBitmap(paramBitmap1, 0.0F, 0.0F, null);
    Paint localPaint1 = new Paint();
    localPaint1.setAntiAlias(true);
    Log.i("picooc", "picBitmap==" + paramBitmap3.getWidth());
    localCanvas.drawBitmap(paramBitmap3, -20 + (i - paramBitmap3.getWidth()), 20.0F, localPaint1);
    localCanvas.drawBitmap(paramBitmap2, f1 * i, f2 * j, localPaint1);
    if (paramString != null)
    {
      Paint localPaint2 = new Paint();
      localPaint2.setAntiAlias(true);
      localPaint2.setColor(-1);
      localPaint2.setTypeface(ModUtils.getTypeface(this));
      localPaint2.setTextSize(30.0F);
      localCanvas.drawText(this.Movetext.getText().toString(), f3 * i, f4 * j, localPaint2);
    }
    localCanvas.save(31);
    localCanvas.restore();
    return localBitmap;
  }

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968576, 2130968581);
  }

  public void getCurrentImage()
  {
    View localView = (View)this.viewListTwo.get(this.position);
    this.MoveImag = ((ImageView)localView.findViewById(2131099948));
    this.Movetext = ((TextView)localView.findViewById(2131099949));
    Log.i("picooc", "position1111111=" + this.position + "----movetextH==" + this.Movetext.getTop());
  }

  public int getGreenOpenHeight()
  {
    return this.titelDistance + this.touMingText.getHeight();
  }

  public RelativeLayout.LayoutParams getlayoutParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt3, paramInt4);
    localLayoutParams.leftMargin = paramInt1;
    localLayoutParams.topMargin = paramInt2;
    localLayoutParams.bottomMargin = -250;
    localLayoutParams.rightMargin = -250;
    return localLayoutParams;
  }

  public void getlist()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this.app.getContinuousWeightingSharedModel().getImages().length)
        return;
      this.first = this.inflater.inflate(2130903093, null);
      this.viewList.add(this.first);
    }
  }

  public void getlistTwo()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this.app.getContinuousWeightingSharedModel().getImages().length)
        return;
      View localView = this.inflater.inflate(2130903096, null);
      ImageView localImageView = (ImageView)localView.findViewById(2131099948);
      TextView localTextView = (TextView)localView.findViewById(2131099949);
      localImageView.setLayoutParams(getlayoutParams(160, 300, ModUtils.dip2px(this, 160.0F), ModUtils.dip2px(this, 135.0F)));
      localTextView.setLayoutParams(getlayoutParams(150, 600, -2, -2));
      localImageView.setOnTouchListener(this.movingEventListenerTwo);
      localTextView.setOnTouchListener(this.movingEventListenerTwo);
      this.viewListTwo.add(localView);
    }
  }

  public void guidePhotoShare()
  {
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        ShareAct.this.mGuideList = new ArrayList();
        Rect localRect = new Rect();
        ShareAct.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        int i = localRect.top;
        ShareAct.this.locImageView = new int[2];
        ShareAct.this.camera_Image.setDrawingCacheEnabled(true);
        ShareAct.this.camera_Image.getLocationInWindow(ShareAct.this.locImageView);
        ShareAct.this.mGuide = new GuideModel(ShareAct.this.camera_Image.getDrawingCache(), ShareAct.this.locImageView[0], ShareAct.this.locImageView[1]);
        ShareAct.this.mGuideList.add(ShareAct.this.mGuide);
        Intent localIntent = new Intent(ShareAct.this, GuideAct.class);
        localIntent.putExtra("pageId", 7);
        localIntent.putExtra("statusBarHeight", i);
        localIntent.putExtra("guideList", (Serializable)ShareAct.this.mGuideList);
        ShareAct.this.startActivity(localIntent);
        ShareAct.this.overridePendingTransition(-1, -1);
      }
    }
    , 1000L);
  }

  public void invit()
  {
    this.fenxiangLinear.setAlpha(0.0F);
    this.operatingAnim_on = AnimationUtils.loadAnimation(this, 2130968579);
    this.operatingAnim_off = AnimationUtils.loadAnimation(this, 2130968578);
    LinearInterpolator localLinearInterpolator = new LinearInterpolator();
    this.operatingAnim_off.setInterpolator(localLinearInterpolator);
    this.operatingAnim_on.setInterpolator(localLinearInterpolator);
    DisplayMetrics localDisplayMetrics1 = getResources().getDisplayMetrics();
    this.screenWidth = localDisplayMetrics1.widthPixels;
    this.screenHeight = localDisplayMetrics1.heightPixels;
    this.lv_textRight.setTypeface(ModUtils.getTypeface(this));
    this.lv_text.setTypeface(ModUtils.getTypeface(this));
    this.green_dateText.setTypeface(ModUtils.getTypeface(this));
    this.lv_textmiaoshu.setTypeface(ModUtils.getTypeface(this));
    this.hong_text.setTypeface(ModUtils.getTypeface(this));
    this.hong_textmiaoshu.setTypeface(ModUtils.getTypeface(this));
    this.textDate.setTypeface(ModUtils.getTypeface(this));
    this.textFenShu.setTypeface(ModUtils.getTypeface(this));
    this.fenxiang_miaoshu.setTypeface(ModUtils.getTypeface(this));
    getLayoutInflater();
    this.inflater = LayoutInflater.from(this);
    this.viewPager.setHorizontalScrollBarEnabled(true);
    MyPageListener localMyPageListener = new MyPageListener();
    this.viewPager.setOnPageChangeListener(localMyPageListener);
    this.viewPager.setScrollable(true);
    this.viewpagerTwo.setOnPageChangeListener(localMyPageListener);
    this.viewpagerTwo.setScrollable(true);
    label406: int i;
    float f;
    if (!this.app.getCurrentRole().getHead_portrait_url().equals(""))
    {
      this.head_image.setUrl(this.app.getCurrentRole().getHead_portrait_url());
      this.textName.setText(this.app.getCurrentRole().getName());
      this.textName.setTypeface(ModUtils.getTypeface(this));
      this.textDate.setText(ModUtils.getStrTime(this.app.getTodayBody().getTime()));
      if (this.bodyScoreModel.getTotalScore() != 100)
        break label1047;
      this.hong_text.setVisibility(8);
      this.hong_textTwo.setText(this.bodyScoreModel.getTotalScore());
      this.hong_textTwo.setVisibility(0);
      this.hong_textTwo.setTypeface(ModUtils.getTypeface(this));
      ModUtils.getPaintColor(this.bodyScoreModel.getTotalScore(), this.fenxiang_huizhuangImage, true);
      this.textFenShu.setText(this.bodyScoreModel.getTotalScore());
      DisplayMetrics localDisplayMetrics2 = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics2);
      i = localDisplayMetrics2.widthPixels;
      f = this.textFenShu.getPaint().measureText(this.bodyScoreModel.getTotalScore());
      if (this.bodyScoreModel.getTotalScore() != 100)
        break label1094;
      this.fenShuRightImage.setPadding((int)(i / 2 + f / 2.75D), 0, 0, this.fenShuRightImage.getPaddingBottom());
      label553: this.textFenShu.setTextColor(zhuzhuangtuView.getPaintColor(this.bodyScoreModel.getTotalScore()));
      this.camera_Image.setClickable(false);
      if (this.app.getContinuousWeightingSharedModel() == null)
        break label1565;
      this.greenText.setText(this.app.getContinuousWeightingSharedModel().getTitle1());
      if (this.app.getContinuousWeightingSharedModel().getShowFlag() != 3)
        break label1311;
      if (!this.app.getContinuousWeightingSharedModel().isCanShared())
        break label1174;
      getlist();
      getlistTwo();
      this.viewPager.setAdapter(new SharePagerAdapter(this.viewList, this.app.getContinuousWeightingSharedModel().getImages(), this.app.getContinuousWeightingSharedModel().getMessages(), this));
      this.viewpagerTwo.setAdapter(new SharePagerAdapter(this.viewListTwo, this.app.getContinuousWeightingSharedModel().getImages(), this.app.getContinuousWeightingSharedModel().getMessages(), this));
      this.images = this.app.getContinuousWeightingSharedModel().getImages();
      initDots();
      if ((this.app.getContinuousWeightingSharedModel().getMessage2() != null) && (!this.app.getContinuousWeightingSharedModel().getMessage2().equals("")))
      {
        this.lv_textleft.setText(this.app.getContinuousWeightingSharedModel().getMessage2().substring(0, 2));
        this.lv_text.setText(ModUtils.cutString(this.app.getContinuousWeightingSharedModel().getMessage2()));
      }
      label810: if ((this.app.getContinuousWeightingSharedModel().getTitle2() == null) || (this.app.getContinuousWeightingSharedModel().getTitle2().equals("")))
        break label1278;
      this.green_dateText.setText(this.app.getContinuousWeightingSharedModel().getTitle2());
      label859: if ((this.app.getContinuousWeightingSharedModel().getMessage3() == null) || (this.app.getContinuousWeightingSharedModel().getMessage3().equals("")))
        break label1290;
      this.lv_textmiaoshu.setText(this.app.getContinuousWeightingSharedModel().getMessage3());
      if (this.app.getContinuousWeightingSharedModel().isGood())
        this.lvBaozi.setImageResource(2130837784);
    }
    while (true)
    {
      if (this.app.getContinuousWeightingSharedModel().getShowFlag() != 2)
        break label1381;
      this.redText.setText("今日身体得分");
      this.isClickble = false;
      this.redXiaoImage.setImageResource(2130837774);
      this.hong_liner.setVisibility(8);
      this.redNotData.setVisibility(0);
      this.redright_Image.setVisibility(8);
      this.redataText.setText("还没有最新身体评分哦，快去称重吧~");
      return;
      if (this.app.getCurrentRole().getSex() == 1)
      {
        this.head_image.setDefaultImageResource(2130838457);
        break;
      }
      this.head_image.setDefaultImageResource(2130838460);
      break;
      label1047: this.hong_text.setVisibility(0);
      this.hong_textTwo.setVisibility(8);
      this.hong_text.setText(this.bodyScoreModel.getTotalScore());
      break label406;
      label1094: if (this.bodyScoreModel.getTotalScore() % 10 == 1)
      {
        this.fenShuRightImage.setPadding((int)(i / 2 + f / 4.9D), 0, 0, this.fenShuRightImage.getPaddingBottom());
        break label553;
      }
      this.fenShuRightImage.setPadding((int)(i / 2 + f / 3.6D), 0, 0, this.fenShuRightImage.getPaddingBottom());
      break label553;
      label1174: this.greenLinear.setClickable(false);
      if ((this.app.getContinuousWeightingSharedModel().getMessage2() == null) || (this.app.getContinuousWeightingSharedModel().getMessage2().equals("")))
        break label810;
      this.greenNotData.setVisibility(0);
      this.lv_liner.setVisibility(8);
      this.greenNoDataText.setText(this.app.getContinuousWeightingSharedModel().getMessage2());
      this.lv_text.setVisibility(8);
      this.lv_textRight.setVisibility(8);
      this.add_Image.setVisibility(8);
      break label810;
      label1278: this.green_dateText.setVisibility(8);
      break label859;
      label1290: this.lv_textmiaoshu.setVisibility(8);
      this.lvBaozi.setVisibility(8);
      continue;
      label1311: if (this.app.getContinuousWeightingSharedModel().getShowFlag() == 2)
        this.greenNoDataText.setText("今天还没有测量，\n不能分享哦～快去Latin一下吧！");
      this.lv_liner.setVisibility(8);
      this.greenNotData.setVisibility(0);
      this.greenLinear.setClickable(false);
      this.green_dateText.setVisibility(8);
      this.add_Image.setVisibility(8);
    }
    label1381: if ((this.bodyScoreModel.getTitle_out() != null) && (!this.bodyScoreModel.getTitle_out().equals("")))
      this.redText.setText(this.bodyScoreModel.getTitle_out());
    if (this.app.getTodayBody().getBodyFat() > 0.0F)
    {
      this.zhu = new zhuzhuangtuView(this, this.screenWidth, this.screenHeight, this.bodyScoreModel.getScores());
      this.zhuLeralout.addView(this.zhu);
      this.fenxiang_miaoshu.setText(this.bodyScoreModel.getSharedMessage());
      this.fenxiang_miaoshu.setTextColor(ModUtils.getPaintColor(this.bodyScoreModel.getTotalScore(), this.fenShuRightImage, false));
      return;
    }
    this.isClickble = false;
    this.redXiaoImage.setImageResource(2130837816);
    this.hong_liner.setVisibility(8);
    this.redNotData.setVisibility(0);
    this.redright_Image.setVisibility(8);
    this.redataText.setText("本次称量错误，无法查看和分享身体得分哦~");
    return;
    label1565: if (this.app.getTodayBody().getWeight() > 0.0F)
      this.greenNoDataText.setText("今天还没有测量，\n不能分享哦～快去Latin一下吧！");
    this.lv_liner.setVisibility(8);
    this.greenNotData.setVisibility(0);
    this.greenLinear.setClickable(false);
    this.hong_liner.setVisibility(8);
    this.redNotData.setVisibility(0);
    this.isClickble = false;
    this.green_dateText.setVisibility(8);
    this.add_Image.setVisibility(8);
    this.redright_Image.setVisibility(8);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == PICTURE) && (paramInt2 == -1) && (paramIntent != null))
    {
      localUri = paramIntent.getData();
      arrayOfString = new String[] { "_data" };
      localCursor = getContentResolver().query(localUri, arrayOfString, null, null, null);
      localCursor.moveToFirst();
      str = localCursor.getString(localCursor.getColumnIndex(arrayOfString[0]));
      localCursor.close();
      localIntent = new Intent(this, ClipPictureActivity.class);
      localIntent.putExtra("height", this.heith - getGreenOpenHeight() - this.linearLayoutbootom.getHeight() - statusBarHeight(this));
      localIntent.putExtra("wight", this.witht);
      localIntent.putExtra("path", str);
      startActivityForResult(localIntent, CUTPIC);
    }
    while ((paramIntent == null) || (paramInt1 != CUTPIC))
    {
      Uri localUri;
      String[] arrayOfString;
      Cursor localCursor;
      String str;
      Intent localIntent;
      return;
    }
    this.isResume = false;
    this.image0.setVisibility(0);
    this.imagePic.setVisibility(0);
    byte[] arrayOfByte = paramIntent.getByteArrayExtra("bitmap");
    this.FenBitmap = BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length);
    BitmapDrawable localBitmapDrawable = new BitmapDrawable(this.FenBitmap);
    this.imagePic.setBackgroundDrawable(localBitmapDrawable);
    this.takeTitelRight.setVisibility(8);
    startAnima1(this.LayoutbootomRight, this.during);
    startAnima2(this.linearLayoutbootom, this.during);
  }

  public void onAutoFocus(boolean paramBoolean)
  {
  }

  public void onCameraStopped(Bitmap paramBitmap)
  {
    this.FenBitmap = paramBitmap;
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131100646:
    default:
    case 2131100670:
    case 2131100690:
    case 2131100675:
    case 2131100678:
    case 2131100679:
    case 2131100652:
    case 2131100653:
      do
      {
        do
        {
          do
            return;
          while (ModUtils.isFastDoubleClick());
          if (this.isOpen)
          {
            topOpen();
            return;
          }
          topClosed();
          return;
        }
        while ((!this.isClickble) || (ModUtils.isFastDoubleClick()));
        if (this.bootomIsOpen)
        {
          botoomOpen();
          return;
        }
        botoomClosed();
        return;
        Log.i("picooc", "dianji l camera");
        this.viewpagerTwo.setCurrentItem(this.curIndex);
        this.dots[this.curIndex].setEnabled(false);
        this.LayoutbootomRight.setVisibility(0);
        this.shuiyinLinerOne.setVisibility(8);
        this.greenTitelLiner.setVisibility(8);
        this.shuiyinLinerTwo.setVisibility(0);
        this.takeTitelRight.setVisibility(0);
        startAnima1(this.linearLayoutbootom, this.during);
        startAnima2(this.LayoutbootomRight, this.during);
        this.textview.setVisibility(8);
        this.fenxiangLinear.setVisibility(8);
        this.mCameraPreview.startPrivew();
        this.greenLinear.setClickable(false);
        this.linearViewPager.setVisibility(0);
        return;
        this.mCameraPreview.openLed();
        if (this.openLed)
        {
          this.camera_led.setImageResource(2130837810);
          this.openLed = false;
          return;
        }
        this.camera_led.setImageResource(2130837750);
        this.openLed = true;
        return;
        this.mCameraPreview.translateCamera(this.camera_led);
        return;
        this.linearViewPager.setVisibility(8);
        this.image0.setVisibility(8);
        startAnima3(this.linearLayoutbootom, this.during);
        startAnima4(this.LayoutbootomRight, this.during);
        this.greenTitelLiner.setVisibility(0);
        this.takeTitelRight.setVisibility(8);
        this.shuiyinLinerOne.setVisibility(0);
        this.shuiyinLinerTwo.setVisibility(8);
        this.mCameraPreview.stopPreview();
        this.textview.setVisibility(0);
        this.greenLinear.setClickable(true);
        this.viewPager.setCurrentItem(this.curIndex);
        this.dots[this.curIndex].setEnabled(false);
        return;
        this.isResume = true;
      }
      while (ModUtils.isFastDoubleClick());
      this.mCameraPreview.takePicture();
      this.image0.setVisibility(0);
      startAnima1(this.LayoutbootomRight, this.during);
      startAnima2(this.linearLayoutbootom, this.during);
      this.takeTitelRight.setVisibility(8);
      return;
    case 2131100654:
      startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), PICTURE);
      this.mCameraPreview.stopPreview();
      return;
    case 2131100655:
      this.FenBitmap = null;
      this.takeTitelRight.setVisibility(0);
      this.mCameraPreview.startPrivew();
      startAnima3(this.LayoutbootomRight, this.during);
      startAnima4(this.linearLayoutbootom, this.during);
      this.imagePic.setVisibility(8);
      return;
    case 2131100656:
      startFenXiang(0);
      return;
    case 2131100657:
      startFenXiang(1);
      return;
    case 2131100658:
      startFenXiang(2);
      return;
    case 2131100659:
      startFenXiang(3);
      return;
    case 2131100660:
      startFenXiang(4);
      return;
    case 2131099980:
    }
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903222);
    this.app = ((MyApplication)getApplicationContext());
    this.animeUtlils = new AnimUtils(this);
    this.relativeTitel = ((RelativeLayout)findViewById(2131100356));
    this.greenLinear = ((LinearLayout)findViewById(2131100670));
    this.redLinear = ((LinearLayout)findViewById(2131100690));
    this.linearLayoutbootom = ((LinearLayout)findViewById(2131100357));
    this.shuiYinLinear = ((LinearLayout)findViewById(2131100646));
    this.fenxiangLinear = ((LinearLayout)findViewById(2131100664));
    this.waiBaoLinear = ((LinearLayout)findViewById(2131100668));
    this.touMingText = ((TextView)findViewById(2131100669));
    this.greenTitel = ((RelativeLayout)findViewById(2131100671));
    this.redTitel = ((RelativeLayout)findViewById(2131100691));
    this.add_Image = ((ImageView)findViewById(2131100676));
    this.redright_Image = ((ImageView)findViewById(2131100693));
    this.topleftImage = ((ImageView)findViewById(2131100672));
    this.bootomleftImage = ((ImageView)findViewById(2131100692));
    this.camera_Image = ((ImageView)findViewById(2131100675));
    this.green_dateText = ((TextView)findViewById(2131100680));
    this.LayoutbootomRight = ((RelativeLayout)findViewById(2131100651));
    this.takeTitelRight = ((LinearLayout)findViewById(2131100677));
    this.greenTitelLiner = ((LinearLayout)findViewById(2131100674));
    this.shuiyinLinerOne = ((LinearLayout)findViewById(2131100647));
    this.shuiyinLinerTwo = ((RelativeLayout)findViewById(2131100648));
    this.fenxiangRelativeOut = ((RelativeLayout)findViewById(2131100617));
    this.lv_liner = ((LinearLayout)findViewById(2131100684));
    this.hong_liner = ((LinearLayout)findViewById(2131100698));
    this.textview = ((TextView)findViewById(2131100563));
    this.image0 = ((ImageView)findViewById(2131100655));
    this.imagePic = ((ImageView)findViewById(2131100661));
    this.camera_led = ((ImageView)findViewById(2131100678));
    this.zhuLeralout = ((LinearLayout)findViewById(2131100667));
    this.lv_textleft = ((TextView)findViewById(2131100685));
    this.lv_text = ((TextView)findViewById(2131100686));
    this.lv_textRight = ((TextView)findViewById(2131100687));
    this.lv_textmiaoshu = ((TextView)findViewById(2131100689));
    this.hong_text = ((TextView)findViewById(2131100701));
    this.hong_textmiaoshu = ((TextView)findViewById(2131100705));
    this.beatLayout = ((LinearLayout)findViewById(2131100703));
    this.l = ((LinearLayout)findViewById(2131100619));
    this.textName = ((TextView)findViewById(2131099852));
    this.head_image = ((AsyncImageView)findViewById(2131099850));
    this.textDate = ((TextView)findViewById(2131099853));
    this.textFenShu = ((TextView)findViewById(2131100608));
    this.fenxiang_miaoshu = ((TextView)findViewById(2131100666));
    this.linearViewPager = ((LinearLayout)findViewById(2131100662));
    this.greenText = ((TextView)findViewById(2131100673));
    this.redText = ((TextView)findViewById(2131100694));
    this.picoocImage = ((ImageView)findViewById(2131099854));
    this.greenNotData = ((LinearLayout)findViewById(2131100681));
    this.redNotData = ((LinearLayout)findViewById(2131100695));
    this.lvBaozi = ((ImageView)findViewById(2131100688));
    this.redBaozi = ((ImageView)findViewById(2131100704));
    this.redXiaoImage = ((ImageView)findViewById(2131100696));
    this.fenShuRightImage = ((ImageView)findViewById(2131100665));
    this.fenxiang_huizhuangImage = ((ImageView)findViewById(2131100700));
    this.greenNoDataText = ((TextView)findViewById(2131100683));
    this.hong_textTwo = ((TextView)findViewById(2131100702));
    this.hong_textTwo.setTypeface(TypefaceUtils.getTypeface(this, null));
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.heith = localDisplayMetrics.heightPixels;
    this.witht = localDisplayMetrics.widthPixels;
    Log.i("picooc", "heith11==" + this.heith);
    this.mCameraPreview = ((CameraPreview)findViewById(2131100649));
    this.focusView = ((ImageView)findViewById(2131100650));
    this.mCameraPreview.setOnCameraStatusListener(this);
    this.titelDistance = ModUtils.dip2px(this, 15.0F);
    this.viewPager = ((MyViewPager)findViewById(2131100355));
    this.viewpagerTwo = ((MyViewPager)findViewById(2131100663));
    this.thirdPart = new ThirdPartLogin(this);
    this.mCameraPreview.setKeepScreenOn(true);
    this.redText.setTypeface(ModUtils.getTypeface(this));
    this.redataText = ((TextView)findViewById(2131100697));
    this.bodyScoreModel = new BodyScoreModel(this.app.getCurrentRole(), this.app.getTodayBody());
    invit();
    if (this.app.getTodayBody().getBodyFat() > 0.0F)
    {
      RequestEntity localRequestEntity = new RequestEntity("getMyScorePosition", null);
      localRequestEntity.addParam("roleId", Long.valueOf(this.app.getCurrentRole().getRole_id()));
      localRequestEntity.addParam("userId", Long.valueOf(this.app.getCurrentRole().getUser_id()));
      localRequestEntity.addParam("score", Integer.valueOf(this.bodyScoreModel.getTotalScore()));
      HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
    }
    if (getIntent().getBooleanExtra("isDialog", false))
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          ShareAct.this.topOpen();
        }
      }
      , 100L);
  }

  protected void onDestroy()
  {
    if (this.mCameraPreview != null)
      this.mCameraPreview.destory();
    this.FenBitmap = null;
    super.onDestroy();
  }

  protected void onResume()
  {
    Log.i("picooc", "fenxiang  huilai   l  ");
    if (this.FenBitmap != null)
    {
      this.FenBitmap = null;
      if (this.isResume)
      {
        this.takeTitelRight.setVisibility(0);
        this.mCameraPreview.startPrivew();
        startAnima3(this.LayoutbootomRight, this.during);
        startAnima4(this.linearLayoutbootom, this.during);
        this.imagePic.setVisibility(8);
      }
    }
    super.onResume();
  }

  public void setLayoutX(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Log.i("picooc", "wight==" + paramView.getWidth() + "---height==" + paramView.getHeight());
    if (Integer.decode(paramView.getTag().toString()).intValue() == 1);
    for (RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(200, 100); ; localLayoutParams = new RelativeLayout.LayoutParams(-2, -2))
    {
      localLayoutParams.setMargins(paramInt1, paramInt2, paramInt3, paramInt4);
      paramView.setLayoutParams(localLayoutParams);
      return;
    }
  }

  public void startFenXiang(int paramInt)
  {
    if (this.FenBitmap == null);
    for (Bitmap localBitmap = takeScreenShot(this); ; localBitmap = HeChengBitmap(this.FenBitmap))
      switch (paramInt)
      {
      default:
        return;
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      }
    this.thirdPart.shareSina(this, localBitmap);
    return;
    this.thirdPart.weinxin(this, localBitmap);
    return;
    this.thirdPart.weinxinCircle(this, localBitmap);
    return;
    if (this.FenBitmap == null);
    for (String str = ModUtils.saveFile(this, takeScreenShot(this), "123456789.jpg", false); ; str = ModUtils.saveFile(this, HeChengBitmap(this.FenBitmap), "123456789.jpg", false))
    {
      this.thirdPart.shareQzone(this, str);
      return;
    }
    ModUtils.saveFile(this, localBitmap, System.currentTimeMillis() + ".jpg", true);
  }

  public void topClosed()
  {
    this.camera_Image.setClickable(false);
    this.greenText.setTextColor(Color.parseColor("#6e9796"));
    this.isOpen = true;
    this.animeUtlils.upMove(this.greenLinear, -(this.greenLinear.getHeight() - this.titelDistance), 0, this.during);
    this.animeUtlils.upMove(this.touMingText, -this.relativeTitel.getHeight(), 0, 500);
    this.animeUtlils.downMove(this.redLinear, 100 + this.redLinear.getHeight(), 0, this.during);
    this.animeUtlils.showAnima(this.redLinear, 100L);
    this.animeUtlils.upMove(this.shuiYinLinear, 0, 1000, this.during);
    this.animeUtlils.missAnima(this.linearLayoutbootom, this.during);
    this.animeUtlils.upMove(this.greenTitel, this.greenLinear.getHeight() - (this.relativeTitel.getHeight() + ModUtils.dip2px(this, 20.0F)), 0, this.during);
    this.add_Image.startAnimation(this.operatingAnim_off);
    this.topleftImage.setImageResource(2130837821);
    this.animeUtlils.missAnima(this.camera_Image, this.during);
    this.animeUtlils.showAnima(this.green_dateText, this.during);
    this.animeUtlils.showAnima(this.lv_liner, this.during);
  }

  public void topOpen()
  {
    this.camera_Image.setClickable(true);
    this.greenText.setTextColor(-1);
    this.FenBitmap = null;
    this.l.setVisibility(0);
    this.shuiYinLinear.setAlpha(1.0F);
    this.isOpen = false;
    this.animeUtlils.upMove(this.greenLinear, 0, -(this.greenLinear.getHeight() - this.titelDistance), this.during);
    this.animeUtlils.upMove(this.touMingText, 0, -this.relativeTitel.getHeight(), this.during);
    this.animeUtlils.downMove(this.redLinear, 0, this.redLinear.getHeight(), this.during);
    this.animeUtlils.missAnima(this.redLinear, 800L);
    this.animeUtlils.upMove(this.shuiYinLinear, 800, 0, this.during);
    this.animeUtlils.showAnima(this.shuiYinLinear, 100L);
    this.animeUtlils.showAnima(this.linearLayoutbootom, this.during);
    this.animeUtlils.downMove(this.greenTitel, 0, this.greenLinear.getHeight() - (this.relativeTitel.getHeight() + ModUtils.dip2px(this, 20.0F)), this.during);
    this.add_Image.startAnimation(this.operatingAnim_on);
    this.topleftImage.setImageResource(2130837820);
    this.animeUtlils.showAnima(this.camera_Image, this.during);
    this.animeUtlils.missAnima(this.green_dateText, this.during);
    this.animeUtlils.missAnima(this.lv_liner, this.during);
    if (SharedPreferenceUtils.isFirstEnterCurPage(this, "share_act"))
    {
      guidePhotoShare();
      SharedPreferenceUtils.resetCurPage(this, false, "share_act");
    }
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
      ShareAct.this.position = paramInt;
      Log.i("qianmo2", "onPageSelectedArg==" + paramInt);
      ShareAct.this.setCurDot(paramInt);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ShareAct
 * JD-Core Version:    0.6.2
 */
