package com.picooc;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.picooc.adapter.DietAndNutritionAdapter;
import com.picooc.model.DietAndNutritionEntity;
import com.picooc.model.DietAndNutritionModel;
import java.util.ArrayList;
import org.achartengine.tools.ModUtils;

public class LiftDietAndNutritionAct extends PicoocActivity
  implements View.OnClickListener
{
  ListView die_listview;
  ArrayList<DietAndNutritionEntity> lists;
  TextView s_yin_gonggao;
  TextView s_yin_gonggaotwo;

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968576, 2130968581);
  }

  public void invit()
  {
    ImageView localImageView1 = (ImageView)findViewById(2131099651);
    ImageView localImageView2 = (ImageView)findViewById(2131099650);
    localImageView1.setImageResource(2130838428);
    localImageView1.setOnClickListener(this);
    localImageView2.setOnClickListener(this);
    localImageView2.setImageResource(2130838406);
    localImageView1.setVisibility(8);
    ((TextView)findViewById(2131099699)).setText("饮食与营养");
    this.die_listview = ((ListView)findViewById(2131100462));
    View localView1 = LayoutInflater.from(this).inflate(2130903044, null);
    View localView2 = LayoutInflater.from(this).inflate(2130903045, null);
    this.s_yin_gonggao = ((TextView)localView1.findViewById(2131099694));
    this.s_yin_gonggaotwo = ((TextView)localView1.findViewById(2131099695));
    this.die_listview.addFooterView(localView1);
    this.die_listview.addHeaderView(localView2);
    DietAndNutritionAdapter localDietAndNutritionAdapter = new DietAndNutritionAdapter(this, this.lists);
    this.die_listview.setAdapter(localDietAndNutritionAdapter);
  }

  public void invitData()
  {
    SpannableStringBuilder localSpannableStringBuilder1 = new SpannableStringBuilder("您 饮食与营养基于您的身体类型和个人饮食习惯，为您制定饮食结构均衡的食谱。帮助您调整饮食规律和身体状态，以最健康的方式达到减重 /增重目标.");
    localSpannableStringBuilder1.setSpan(new ImageSpan(this, 2130838666), 0, 1, 33);
    localSpannableStringBuilder1.setSpan(new ForegroundColorSpan(Color.parseColor("#8cff79")), 1, 7, 34);
    this.s_yin_gonggao.setText(localSpannableStringBuilder1);
    this.s_yin_gonggao.setLineSpacing(3.4F, 1.3F);
    SpannableStringBuilder localSpannableStringBuilder2 = new SpannableStringBuilder("正在由PICOOC 和中国营养学会联合开发中！");
    localSpannableStringBuilder2.setSpan(new ForegroundColorSpan(Color.parseColor("#8cff79")), 3, 9, 34);
    localSpannableStringBuilder2.setSpan(new ForegroundColorSpan(Color.parseColor("#8cff79")), 11, 17, 34);
    this.s_yin_gonggaotwo.setText(localSpannableStringBuilder2);
    this.s_yin_gonggaotwo.setTypeface(ModUtils.getTypeface(this));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131099650:
    }
    finish();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903190);
    this.lists = ((DietAndNutritionModel)getIntent().getSerializableExtra("model")).getPrincipleArray();
    invit();
    invitData();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LiftDietAndNutritionAct
 * JD-Core Version:    0.6.2
 */
