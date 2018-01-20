package com.picooc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.picooc.lock.PwdCheckActivity;

public class PicoocActivity extends Activity
{
  public void onBackClick(View paramView)
  {
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    ((MyApplication)getApplication()).addActivity(this);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    ((MyApplication)getApplication()).removeActivity(this);
  }

  protected void onStart()
  {
    super.onStart();
    if (MyApplication.isShowLocalPassword)
    {
      Intent localIntent = new Intent();
      localIntent.setClass(this, PwdCheckActivity.class);
      startActivity(localIntent);
      overridePendingTransition(2130968580, 2130968577);
      MyApplication.isShowLocalPassword = false;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PicoocActivity
 * JD-Core Version:    0.6.2
 */
