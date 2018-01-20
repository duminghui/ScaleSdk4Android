package com.picooc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

@SuppressLint({"ValidFragment"})
public class ResetLatinActivity extends FragmentActivity
{
  boolean mBlockBackEvent = true;
  ResetLatin mLatin;

  public boolean isBlock()
  {
    return this.mBlockBackEvent;
  }

  public void onBackPressed()
  {
    if (isBlock())
    {
      this.mLatin.onBackPressed();
      return;
    }
    super.onBackPressed();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903122);
    if (isBlock())
    {
      this.mLatin = new ResetLatin(this);
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          ResetLatinActivity.this.mLatin.show();
        }
      }
      , 1000L);
    }
  }

  public void reSetBlockEvent(boolean paramBoolean)
  {
    this.mBlockBackEvent = paramBoolean;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ResetLatinActivity
 * JD-Core Version:    0.6.2
 */
