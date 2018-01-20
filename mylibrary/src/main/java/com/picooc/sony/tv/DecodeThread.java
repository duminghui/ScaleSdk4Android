package com.picooc.sony.tv;

import android.os.Handler;
import android.os.Looper;
import com.picooc.SonyTVAct;
import java.util.concurrent.CountDownLatch;

final class DecodeThread extends Thread
{
  SonyTVAct activity;
  private Handler handler;
  private final CountDownLatch handlerInitLatch;

  DecodeThread(SonyTVAct paramSonyTVAct)
  {
    this.activity = paramSonyTVAct;
    this.handlerInitLatch = new CountDownLatch(1);
  }

  Handler getHandler()
  {
    try
    {
      this.handlerInitLatch.await();
      label7: return this.handler;
    }
    catch (InterruptedException localInterruptedException)
    {
      break label7;
    }
  }

  public void run()
  {
    Looper.prepare();
    this.handler = new DecodeHandler(this.activity);
    this.handlerInitLatch.countDown();
    Looper.loop();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     DecodeThread
 * JD-Core Version:    0.6.2
 */
