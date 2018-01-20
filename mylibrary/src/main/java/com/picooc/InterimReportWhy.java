package com.picooc;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class InterimReportWhy extends PicoocActivity
  implements View.OnClickListener
{
  ImageView imageVaiew;

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968576, 2130968581);
  }

  public void invit()
  {
    ((ImageView)findViewById(2131099980)).setOnClickListener(this);
    this.imageVaiew = ((ImageView)findViewById(2131100437));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131099651:
    default:
      return;
    case 2131099980:
    }
    finish();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903186);
    invit();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     InterimReportWhy
 * JD-Core Version:    0.6.2
 */
