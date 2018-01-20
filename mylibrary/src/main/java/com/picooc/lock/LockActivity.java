package com.picooc.lock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.picooc.AnimCreator;
import com.picooc.MyApplication;
import com.picooc.PicoocActivity;
import com.picooc.constant.ThemeConstant;
import com.picooc.utils.SharedPreferenceUtils;
import org.achartengine.tools.ModUtils;

public class LockActivity extends PicoocActivity
  implements CompoundButton.OnCheckedChangeListener
{
  private MyApplication app;
  private LinearLayout linearLayout_bg;
  private TextView mLockDodify;
  private TextView mLockExpl;
  private ToggleButton mLockSwitcher;
  private View.OnClickListener mModifyListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      Intent localIntent = new Intent();
      localIntent.setClass(LockActivity.this, PwdSettingActivity.class);
      localIntent.putExtra("title", 1);
      LockActivity.this.startActivityForResult(localIntent, 101);
    }
  };
  private ThemeConstant themeConstant;
  private TextView ttttttt;

  private void initTheme()
  {
    this.themeConstant = new ThemeConstant(this);
    this.linearLayout_bg = ((LinearLayout)findViewById(2131099739));
  }

  private void updateViews()
  {
    boolean bool = SharedPreferenceUtils.isOpenPsd(this);
    if (bool)
    {
      this.mLockSwitcher.setChecked(bool);
      this.mLockExpl.setVisibility(8);
      this.mLockDodify.setVisibility(0);
      this.ttttttt.setText("关闭密码锁定");
      return;
    }
    this.mLockExpl.setVisibility(0);
    this.mLockDodify.setVisibility(8);
    this.ttttttt.setText("开启密码锁定");
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1)
    {
      this.mLockExpl.setVisibility(8);
      this.mLockDodify.setVisibility(0);
      this.mLockSwitcher.setChecked(true);
      this.ttttttt.setText("关闭密码锁定");
    }
    while ((paramInt2 != 0) || (paramInt1 != 100))
      return;
    this.mLockSwitcher.setChecked(false);
  }

  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      Intent localIntent = new Intent();
      localIntent.setClass(this, PwdSettingActivity.class);
      startActivityForResult(localIntent, 100);
      this.ttttttt.setText("关闭密码锁定");
      return;
    }
    AlphaAnimation localAlphaAnimation = AnimCreator.alphaOut(300L);
    localAlphaAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        LockActivity.this.mLockDodify.setVisibility(8);
        LockActivity.this.mLockExpl.setVisibility(0);
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    });
    this.mLockDodify.setAnimation(localAlphaAnimation);
    SharedPreferenceUtils.savePsd(this, "");
    this.ttttttt.setText("开启密码锁定");
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903086);
    this.app = ((MyApplication)getApplication());
    this.mLockExpl = ((TextView)findViewById(2131099936));
    this.mLockExpl.setTypeface(ModUtils.getTypeface(this));
    this.mLockDodify = ((TextView)findViewById(2131099937));
    this.mLockDodify.setOnClickListener(this.mModifyListener);
    this.ttttttt = ((TextView)findViewById(2131099934));
    this.mLockSwitcher = ((ToggleButton)findViewById(2131099935));
    updateViews();
    this.mLockSwitcher.setOnCheckedChangeListener(this);
    initTheme();
  }

  protected void onResume()
  {
    super.onResume();
    this.themeConstant.setTheme(this.linearLayout_bg);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LockActivity
 * JD-Core Version:    0.6.2
 */
