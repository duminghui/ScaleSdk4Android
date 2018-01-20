package com.picooc.sony.tv;

import android.app.Activity;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public final class InactivityTimer
{
  private static final int INACTIVITY_DELAY_SECONDS = 300;
  private final Activity activity;
  private ScheduledFuture<?> inactivityFuture = null;
  private final ScheduledExecutorService inactivityTimer = Executors.newSingleThreadScheduledExecutor(new DaemonThreadFactory(null));

  public InactivityTimer(Activity paramActivity)
  {
    this.activity = paramActivity;
    onActivity();
  }

  private void cancel()
  {
    if (this.inactivityFuture != null)
    {
      this.inactivityFuture.cancel(true);
      this.inactivityFuture = null;
    }
  }

  public void onActivity()
  {
    cancel();
    this.inactivityFuture = this.inactivityTimer.schedule(new FinishListener(this.activity), 300L, TimeUnit.SECONDS);
  }

  public void shutdown()
  {
    cancel();
    this.inactivityTimer.shutdown();
  }

  private static final class DaemonThreadFactory
    implements ThreadFactory
  {
    public Thread newThread(Runnable paramRunnable)
    {
      Thread localThread = new Thread(paramRunnable);
      localThread.setDaemon(true);
      return localThread;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     InactivityTimer
 * JD-Core Version:    0.6.2
 */
