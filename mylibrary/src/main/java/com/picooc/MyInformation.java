package com.picooc;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.arithmetic.ReportDirect;
import com.picooc.constant.ThemeConstant;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.RoleBin;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.NumUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.AnimUtils;
import com.picooc.widget.WidgetMedal;
import com.picooc.widget.anyncImageView.AsyncImageView;
import com.picooc.widget.loading.PicoocLoading;
import java.text.DecimalFormat;
import org.achartengine.tools.ModUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class MyInformation extends PicoocActivity
  implements View.OnClickListener
{
  private MyApplication app;
  float bootomHeightScale = 0.2411972F;
  private RelativeLayout bootomLiner;
  private TextView days;
  private TextView geren_bootomeText;
  private TextView geren_daBiaoText;
  private ImageView geren_defenImage;
  private LinearLayout geren_leftLiner;
  private LinearLayout geren_oneLiner;
  private LinearLayout geren_rightLiner;
  private LinearLayout geren_twoLiner;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      new ResponseEntity(paramAnonymousJSONObject);
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      if (localResponseEntity.getMethod().equals(HttpUtils.pgetMaxWeightingDays));
      try
      {
        int i = localResponseEntity.getResp().getInt("days");
        String str1 = localResponseEntity.getResp().getString("endTime");
        String str2 = localResponseEntity.getResp().getString("startTime");
        if (i > 0)
        {
          MyInformation.this.middleLiner.setVisibility(0);
          MyInformation.this.rrongImage.setVisibility(8);
          MyInformation.this.geren_bootomeText.setText(str2 + "~" + str1);
          MyInformation.this.days.setText(i);
        }
        while (true)
        {
          new AnimUtils(MyInformation.this).showAnima(MyInformation.this.bootomLiner, 1000L);
          PicoocLoading.dismissDialog();
          return;
          if (MyInformation.this.app.getCurrentRole().getWeight_change_target() <= 0.0F)
            break;
          MyInformation.this.title.setText("最长连续增重天数");
          MyInformation.this.geren_bootomeText.setText("惊呆了，居然还有没有过连胖记录！");
        }
      }
      catch (JSONException localJSONException)
      {
        while (true)
        {
          localJSONException.printStackTrace();
          continue;
          MyInformation.this.title.setText("最长连续减重天数");
          MyInformation.this.geren_bootomeText.setText("惊呆了，居然还有没有过连瘦记录！");
        }
      }
    }
  };
  private LinearLayout infor_lift;
  private LinearLayout infor_right;
  float leftWightScale = 0.4421875F;
  private ImageView left_imagLinethree;
  private ImageView left_imagLinetwo;
  WidgetMedal left_jian_five;
  WidgetMedal left_jian_four;
  WidgetMedal left_jian_one;
  WidgetMedal left_jian_three;
  WidgetMedal left_jian_two;
  WidgetMedal left_mei_eight;
  WidgetMedal left_mei_eleven;
  WidgetMedal left_mei_fifteen;
  WidgetMedal left_mei_five;
  WidgetMedal left_mei_four;
  WidgetMedal left_mei_fourteen;
  WidgetMedal left_mei_nine;
  WidgetMedal left_mei_one;
  WidgetMedal left_mei_seven;
  WidgetMedal left_mei_six;
  WidgetMedal left_mei_ten;
  WidgetMedal left_mei_thirteen;
  WidgetMedal left_mei_three;
  WidgetMedal left_mei_twelve;
  WidgetMedal left_mei_two;
  float leftheightScale = 0.1760563F;
  float lineHeightScale = 0.3010563F;
  private LinearLayout linearLayout;
  private AsyncImageView logo;
  private LinearLayout middleLiner;
  private RadioGroup radioGroup;
  WidgetMedal right_jian_five;
  WidgetMedal right_jian_four;
  WidgetMedal right_jian_one;
  WidgetMedal right_jian_three;
  WidgetMedal right_jian_two;
  WidgetMedal right_mei_eight;
  WidgetMedal right_mei_eleven;
  WidgetMedal right_mei_fifteen;
  WidgetMedal right_mei_five;
  WidgetMedal right_mei_four;
  WidgetMedal right_mei_fourteen;
  WidgetMedal right_mei_nine;
  WidgetMedal right_mei_one;
  WidgetMedal right_mei_seven;
  WidgetMedal right_mei_six;
  WidgetMedal right_mei_ten;
  WidgetMedal right_mei_thirteen;
  WidgetMedal right_mei_three;
  WidgetMedal right_mei_twelve;
  WidgetMedal right_mei_two;
  private ImageView rrongImage;
  int screenHeight;
  int screenWidth;
  private ImageView shuxian;
  private TextView textAge;
  private TextView textFenShu;
  private TextView textFenShu_unit;
  private TextView textHeight;
  private TextView textWeight;
  private TextView textWeight_unit;
  private ThemeConstant themeConstant;
  private TextView title;
  RadioButton week;
  private TextView weight_title;

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968576, 2130968581);
  }

  public void invit()
  {
    this.app = ((MyApplication)getApplication().getApplicationContext());
    this.linearLayout = ((LinearLayout)findViewById(2131100595));
    this.rrongImage = ((ImageView)findViewById(2131100615));
    this.title = ((TextView)findViewById(2131100612));
    this.textWeight = ((TextView)findViewById(2131100604));
    this.textWeight_unit = ((TextView)findViewById(2131100605));
    this.weight_title = ((TextView)findViewById(2131100601));
    this.textFenShu = ((TextView)findViewById(2131100608));
    this.textFenShu_unit = ((TextView)findViewById(2131100609));
    this.geren_leftLiner = ((LinearLayout)findViewById(2131100600));
    this.geren_rightLiner = ((LinearLayout)findViewById(2131100606));
    this.bootomLiner = ((RelativeLayout)findViewById(2131100611));
    this.shuxian = ((ImageView)findViewById(2131099692));
    this.geren_daBiaoText = ((TextView)findViewById(2131100602));
    this.geren_oneLiner = ((LinearLayout)findViewById(2131100603));
    this.geren_twoLiner = ((LinearLayout)findViewById(2131100607));
    this.geren_defenImage = ((ImageView)findViewById(2131100610));
    this.geren_bootomeText = ((TextView)findViewById(2131100616));
    this.middleLiner = ((LinearLayout)findViewById(2131100613));
    this.geren_bootomeText.setTypeface(ModUtils.getTypeface(this));
    this.days = ((TextView)findViewById(2131100614));
    this.days.setTypeface(ModUtils.getTypeface(this));
    this.logo = ((AsyncImageView)findViewById(2131100336));
    label487: float f1;
    if (!this.app.getCurrentRole().getHead_portrait_url().equals(""))
    {
      this.logo.setUrl(this.app.getCurrentRole().getHead_portrait_url());
      BodyIndex localBodyIndex = this.app.getTodayBody();
      RoleBin localRoleBin = this.app.getCurrentRole();
      if (this.app.getCurrentRole().getGoal_weight() == 0.0F)
        break label1027;
      if ((localBodyIndex.getWeight() <= 0.0F) || (localBodyIndex.getBodyFat() <= 0.0F))
        break label1007;
      int[] arrayOfInt = ReportDirect.caculateBodyTotalNum2(this.app.getCurrentRole(), ReportDirect.judgeWeightByRole(localRoleBin, localBodyIndex.getWeight()), ReportDirect.judgeBodyFatByRole(localRoleBin, localBodyIndex), ReportDirect.judgeMuscleRaceByRole(localRoleBin, localBodyIndex), ReportDirect.judgeBoneMassByRole(localRoleBin, localBodyIndex), ReportDirect.judgeWaterRaceByRole(localRoleBin, localBodyIndex), ReportDirect.judgeProteinRaceByRole(localRoleBin, localBodyIndex), ReportDirect.judgeBmrByRole(localRoleBin, localBodyIndex), ReportDirect.judgeInFatByRole(localRoleBin, localBodyIndex));
      this.textFenShu.setText(arrayOfInt[8]);
      this.textFenShu_unit.setText("分");
      this.geren_twoLiner.setVisibility(0);
      this.geren_defenImage.setVisibility(8);
      this.textAge = ((TextView)findViewById(2131100598));
      if (this.app.getCurrentRole().getSex() == 1)
        this.textAge.setCompoundDrawablesWithIntrinsicBounds(2130838049, 0, 0, 0);
      this.textHeight = ((TextView)findViewById(2131100599));
      f1 = this.app.getCurrentRole().getWeight_change_target();
      if (f1 <= 0.0F)
        break label1066;
      this.weight_title.setText("我距增重目标");
      label569: String str = NumUtils.roundValue(Math.abs(this.app.getCurrentRole().getGoal_weight() - this.app.getTodayBody().getWeight()));
      if (this.app.getCurrentRole().getGoal_weight() == 0.0F)
        break label1118;
      if (Float.parseFloat(str) <= 0.0F)
        break label1098;
      this.geren_oneLiner.setVisibility(0);
      this.geren_daBiaoText.setVisibility(8);
      this.textWeight.setText(str);
      this.textWeight_unit.setText("kg");
      label672: this.textAge.setText(this.app.getCurrentRole().getAge() + "岁");
      if (this.app.getCurrentRole().getFamily_type() != 0)
        break label1148;
      ((TextView)findViewById(2131099699)).setText(this.app.getCurrentRole().getName() + "的个人主页");
    }
    while (true)
    {
      ((TextView)findViewById(2131099699)).setTypeface(TypefaceUtils.getTypeface(this, null));
      float f2 = this.app.getCurrentRole().getHeight();
      this.textHeight.setText(new DecimalFormat("0").format(f2) + "cm");
      this.textWeight.setTypeface(ModUtils.getTypeface(this));
      this.textWeight_unit.setTypeface(ModUtils.getTypeface(this));
      this.textFenShu.setTypeface(ModUtils.getTypeface(this));
      this.textFenShu_unit.setTypeface(ModUtils.getTypeface(this));
      this.textAge.setTypeface(ModUtils.getTypeface(this));
      this.textHeight.setTypeface(ModUtils.getTypeface(this));
      this.weight_title.setTypeface(ModUtils.getTypeface(this));
      ((ImageView)findViewById(2131099651)).setVisibility(8);
      if ((this.app.getTodayBody().getWeight() <= 0.0F) || (this.app.getCurrentRole().getGoal_weight() <= 0.0F))
        this.bootomLiner.setVisibility(8);
      return;
      if (this.app.getCurrentRole().getSex() == 1)
      {
        this.logo.setDefaultImageResource(2130838457);
        break;
      }
      this.logo.setDefaultImageResource(2130838460);
      break;
      label1007: this.geren_twoLiner.setVisibility(8);
      this.geren_defenImage.setVisibility(0);
      break label487;
      label1027: this.geren_twoLiner.setVisibility(0);
      this.geren_defenImage.setVisibility(8);
      this.textFenShu_unit.setText("");
      this.textFenShu.setText("--");
      break label487;
      label1066: if (f1 < 0.0F)
      {
        this.weight_title.setText("我距减重目标");
        break label569;
      }
      this.weight_title.setText("我的当前目标");
      break label569;
      label1098: this.geren_oneLiner.setVisibility(8);
      this.geren_daBiaoText.setVisibility(0);
      break label672;
      label1118: this.geren_oneLiner.setVisibility(8);
      this.geren_daBiaoText.setVisibility(0);
      this.geren_daBiaoText.setText("--");
      break label672;
      label1148: if ((this.app.getCurrentRole().getRemark_name() != null) && (!this.app.getCurrentRole().getRemark_name().equals("")))
        ((TextView)findViewById(2131099699)).setText(this.app.getCurrentRole().getRemark_name() + "的个人主页");
      else
        ((TextView)findViewById(2131099699)).setText(this.app.getCurrentRole().getName() + "的个人主页");
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131099651:
    default:
      return;
    case 2131099650:
    }
    finish();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903211);
    invit();
    this.themeConstant = new ThemeConstant(this);
    this.themeConstant.setTheme(findViewById(2131100595));
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.screenWidth = localDisplayMetrics.widthPixels;
    this.screenHeight = localDisplayMetrics.heightPixels;
    setLaoutPam(this.shuxian, (int)(this.screenHeight * this.lineHeightScale));
    setLaoutPam(this.geren_leftLiner, (int)(this.screenWidth / 2 - this.screenWidth * this.leftWightScale), 20, -3 + this.screenWidth / 2, 0, 3 + (int)(this.screenWidth * this.leftWightScale), (int)(this.screenHeight * this.leftheightScale));
    setLaoutPam(this.geren_rightLiner, -3 + this.screenWidth / 2, 60, 3 + (int)(this.screenWidth / 2 - this.screenWidth * this.leftWightScale), 0, (int)(this.screenWidth * this.leftWightScale), (int)(this.screenHeight * this.leftheightScale));
    setLaoutPamTwo(this.bootomLiner, (int)(this.screenWidth / 2 - this.screenWidth * this.leftWightScale), 0, (int)(this.screenWidth / 2 - this.screenWidth * this.leftWightScale), 0, (int)(this.screenWidth - 2.0F * (this.screenWidth / 2 - this.screenWidth * this.leftWightScale)), (int)(this.screenHeight * this.bootomHeightScale));
    RequestEntity localRequestEntity = new RequestEntity(HttpUtils.pgetMaxWeightingDays, "5.2");
    localRequestEntity.addParam("userID", Long.valueOf(this.app.getCurrentUser().getUser_id()));
    localRequestEntity.addParam("roleId", Long.valueOf(this.app.getCurrentRole().getRole_id()));
    localRequestEntity.addParam("weightChanegTarget", Float.valueOf(this.app.getCurrentRole().getWeight_change_target()));
    HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
  }

  public void setLaoutPam(ImageView paramImageView, int paramInt)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, paramInt);
    localLayoutParams.addRule(14);
    paramImageView.setLayoutParams(localLayoutParams);
  }

  public void setLaoutPam(LinearLayout paramLinearLayout, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt5, paramInt6);
    localLayoutParams.setMargins(paramInt1, paramInt2, paramInt3, paramInt4);
    paramLinearLayout.setGravity(17);
    paramLinearLayout.setLayoutParams(localLayoutParams);
  }

  public void setLaoutPamTwo(RelativeLayout paramRelativeLayout, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(paramInt5, paramInt6);
    localLayoutParams.setMargins(paramInt1, paramInt2, paramInt3, paramInt4);
    paramRelativeLayout.setLayoutParams(localLayoutParams);
  }

  public void setTheme()
  {
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     MyInformation
 * JD-Core Version:    0.6.2
 */
