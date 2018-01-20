package com.picooc;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class NetWorkAct extends PicoocActivity
{
  ImageView left;

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968576, 2130968581);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903212);
    this.left = ((ImageView)findViewById(2131099650));
    this.left.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        NetWorkAct.this.finish();
      }
    });
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     NetWorkAct
 * JD-Core Version:    0.6.2
 */
