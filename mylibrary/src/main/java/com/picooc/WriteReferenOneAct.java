package com.picooc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WriteReferenOneAct extends PicoocActivity
{
  public void invit()
  {
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100335:
    }
    startActivity(new Intent(this, WriteReferenAct.class));
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903230);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     WriteReferenOneAct
 * JD-Core Version:    0.6.2
 */
