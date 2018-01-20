package com.picooc.widget;

import android.os.Handler;
import android.os.Message;
import java.util.Timer;
import java.util.TimerTask;

public class MtimerTask
{
  private int during = 0;
  private Boolean isRunning = Boolean.valueOf(true);
  private Handler mhandler;
  int scalce = 0;
  private TimerTask task = null;
  private Timer timer = null;
  private Boolean tybe = Boolean.valueOf(true);

  public MtimerTask(Handler paramHandler, int paramInt1, Boolean paramBoolean, int paramInt2)
  {
    this.mhandler = paramHandler;
    this.during = paramInt1;
    this.tybe = paramBoolean;
    this.scalce = paramInt2;
  }

  public void invitTimerTasek()
  {
    this.task = new TimerTask()
    {
      public void run()
      {
        Message localMessage = new Message();
        localMessage.what = 1;
        MtimerTask.this.mhandler.sendMessage(localMessage);
      }
    };
  }

  public void startTimer()
  {
    if (this.isRunning.booleanValue())
    {
      stopTimer();
      if (this.timer == null)
        this.timer = new Timer();
      if (this.task == null)
        invitTimerTasek();
      if ((this.timer != null) && (this.task != null))
      {
        if (this.tybe.booleanValue())
          this.timer.schedule(this.task, this.scalce, this.during);
      }
      else
        return;
      this.timer.schedule(this.task, this.during);
      return;
    }
    stopTimer();
  }

  public void stopTimer()
  {
    this.isRunning = Boolean.valueOf(true);
    if (this.timer != null)
    {
      this.timer.cancel();
      this.timer = null;
    }
    if (this.task != null)
    {
      this.task.cancel();
      this.task = null;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     MtimerTask
 * JD-Core Version:    0.6.2
 */
