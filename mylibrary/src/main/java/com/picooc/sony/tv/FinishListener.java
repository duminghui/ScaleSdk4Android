package com.picooc.sony.tv;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;

public final class FinishListener
  implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener, Runnable
{
  private final Activity activityToFinish;

  public FinishListener(Activity paramActivity)
  {
    this.activityToFinish = paramActivity;
  }

  public void onCancel(DialogInterface paramDialogInterface)
  {
    run();
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    run();
  }

  public void run()
  {
    this.activityToFinish.finish();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     FinishListener
 * JD-Core Version:    0.6.2
 */
