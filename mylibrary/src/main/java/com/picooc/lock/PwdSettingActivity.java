package com.picooc.lock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.AnimCreator;
import com.picooc.MyApplication;
import com.picooc.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.Iterator;

public class PwdSettingActivity extends Activity
{
  private EditText mEditText;
  private String mPwd;
  private RelativeLayout mPwdLayout;
  private ArrayList<ImageView> mPwdViews = new ArrayList();
  private RelativeLayout mRePwdLayout;
  private ArrayList<ImageView> mRePwdViews = new ArrayList();

  private void clearSelected()
  {
    System.out.print("clearSelected=====================");
    Iterator localIterator1 = this.mPwdViews.iterator();
    Iterator localIterator2;
    if (!localIterator1.hasNext())
      localIterator2 = this.mRePwdViews.iterator();
    while (true)
    {
      if (!localIterator2.hasNext())
      {
        return;
        ((ImageView)localIterator1.next()).setImageResource(2130838033);
        break;
      }
      ((ImageView)localIterator2.next()).setImageResource(2130838033);
    }
  }

  private void setupViews()
  {
    this.mEditText = ((EditText)findViewById(2131100058));
    this.mEditText.addTextChangedListener(new PwdWatcher(null));
    this.mPwdViews.add((ImageView)findViewById(2131100047));
    this.mPwdViews.add((ImageView)findViewById(2131100048));
    this.mPwdViews.add((ImageView)findViewById(2131100049));
    this.mPwdViews.add((ImageView)findViewById(2131100050));
    this.mPwdLayout = ((RelativeLayout)findViewById(2131100001));
    this.mRePwdViews.add((ImageView)findViewById(2131100053));
    this.mRePwdViews.add((ImageView)findViewById(2131100054));
    this.mRePwdViews.add((ImageView)findViewById(2131100055));
    this.mRePwdViews.add((ImageView)findViewById(2131100056));
    this.mRePwdLayout = ((RelativeLayout)findViewById(2131100051));
    this.mEditText.requestFocus();
  }

  private void showInput()
  {
    ((InputMethodManager)getApplication().getSystemService("input_method")).toggleSoftInput(0, 2);
  }

  private void showRePwdLayout()
  {
    TranslateAnimation localTranslateAnimation = AnimCreator.translateX(0.0F, -this.mPwdLayout.getMeasuredWidth(), 200L);
    localTranslateAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        PwdSettingActivity.this.mPwdLayout.setVisibility(8);
        Iterator localIterator = PwdSettingActivity.this.mPwdViews.iterator();
        while (true)
        {
          if (!localIterator.hasNext())
          {
            PwdSettingActivity.this.mRePwdLayout.setVisibility(0);
            PwdSettingActivity.this.mEditText.setText("");
            ((TextView)PwdSettingActivity.this.findViewById(2131100057)).setVisibility(8);
            return;
          }
          ((View)localIterator.next()).setVisibility(0);
        }
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    });
    this.mPwdLayout.startAnimation(localTranslateAnimation);
  }

  public void onBackClick(View paramView)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this, PwdCheckActivity.class);
    setResult(0, localIntent);
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903120);
    setupViews();
    if (getIntent().getIntExtra("title", 0) == 1)
      ((TextView)findViewById(2131100000)).setText("修改密码");
  }

  protected void onResume()
  {
    super.onResume();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        PwdSettingActivity.this.showInput();
      }
    }
    , 200L);
  }

  public void reInitLayout()
  {
    TranslateAnimation localTranslateAnimation = AnimCreator.translateX(0.0F, this.mRePwdLayout.getMeasuredWidth(), 200L);
    localTranslateAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        PwdSettingActivity.this.mRePwdLayout.setVisibility(8);
        PwdSettingActivity.this.mPwdLayout.setVisibility(0);
        PwdSettingActivity.this.mEditText.setText("");
        ((TextView)PwdSettingActivity.this.findViewById(2131100057)).setVisibility(0);
        PwdSettingActivity.this.clearSelected();
        PwdSettingActivity.this.mPwd = null;
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    });
    this.mRePwdLayout.startAnimation(localTranslateAnimation);
  }

  private class PwdWatcher
    implements TextWatcher
  {
    private boolean select;

    private PwdWatcher()
    {
    }

    public void afterTextChanged(Editable paramEditable)
    {
      System.out.println("select = " + this.select);
      String str = paramEditable.toString();
      System.out.println("text = " + str);
      int i = str.length();
      if (i < 0);
      do
      {
        do
        {
          do
          {
            do
            {
              return;
              if (PwdSettingActivity.this.mPwdLayout.getVisibility() != 0)
                break;
              System.out.println("index = " + i);
            }
            while (i > PwdSettingActivity.this.mPwdViews.size());
            if (!this.select)
              break;
            ((ImageView)PwdSettingActivity.this.mPwdViews.get(i - 1)).setImageResource(2130838034);
          }
          while (i != PwdSettingActivity.this.mPwdViews.size());
          PwdSettingActivity.this.mPwd = str;
          PwdSettingActivity.this.showRePwdLayout();
          return;
          ((ImageView)PwdSettingActivity.this.mPwdViews.get(i)).setImageResource(2130838033);
          return;
        }
        while ((PwdSettingActivity.this.mRePwdLayout.getVisibility() != 0) || (i > PwdSettingActivity.this.mRePwdViews.size()));
        if (!this.select)
          break;
        ((ImageView)PwdSettingActivity.this.mRePwdViews.get(i - 1)).setImageResource(2130838034);
      }
      while (i != PwdSettingActivity.this.mPwdViews.size());
      if (str.equals(PwdSettingActivity.this.mPwd))
      {
        Intent localIntent = new Intent();
        localIntent.setClass(PwdSettingActivity.this, PwdCheckActivity.class);
        PwdSettingActivity.this.setResult(-1, localIntent);
        new StringBuilder(String.valueOf(((MyApplication)PwdSettingActivity.this.getApplication()).getCurrentUser().getUser_id())).toString();
        SharedPreferenceUtils.savePsd(PwdSettingActivity.this, PwdSettingActivity.this.mPwd);
        PwdSettingActivity.this.finish();
        return;
      }
      PwdSettingActivity.this.reInitLayout();
      return;
      ((ImageView)PwdSettingActivity.this.mRePwdViews.get(i)).setImageResource(2130838033);
    }

    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
      int i = 1;
      if (paramInt3 == i);
      while (true)
      {
        this.select = i;
        return;
        i = 0;
      }
    }

    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PwdSettingActivity
 * JD-Core Version:    0.6.2
 */
