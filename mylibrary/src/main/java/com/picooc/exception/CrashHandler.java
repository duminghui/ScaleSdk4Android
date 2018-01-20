package com.picooc.exception;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;

import com.picooc.utils.DateUtils;
import java.io.File;
import java.io.FileOutputStream;

public class CrashHandler
  implements Thread.UncaughtExceptionHandler
{
  public static final boolean DEBUG = true;
  private static CrashHandler INSTANCE;
  private Thread.UncaughtExceptionHandler mDefaultHandler;

  public static CrashHandler getInstance()
  {
    if (INSTANCE == null)
      INSTANCE = new CrashHandler();
    return INSTANCE;
  }

  private boolean handleException(Throwable paramThrowable)
  {
    if (paramThrowable == null)
      return false;
    final StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
    new Thread()
    {
      public void run()
      {
        Looper.prepare();
        String str = "crash-" + DateUtils.changeTimeStampToFormatTime(System.currentTimeMillis(), "yyyy-MM-dd~HH-mm-ss") + ".log";
        File localFile1 = new File(Environment.getExternalStorageDirectory().getPath() + "/picooc/logs/");
        if (!localFile1.exists())
          localFile1.mkdirs();
        File localFile2 = new File(localFile1, str);
        try
        {
          FileOutputStream localFileOutputStream = new FileOutputStream(localFile2, true);
          localFileOutputStream.write(this.val$message.getBytes());
          for (int i = 0; ; i++)
          {
            if (i >= arrayOfStackTraceElement.length)
            {
              localFileOutputStream.flush();
              localFileOutputStream.close();
              Looper.loop();
              return;
            }
            localFileOutputStream.write(arrayOfStackTraceElement.toString().getBytes());
          }
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
    }
    .start();
    return false;
  }

  public void init(Context paramContext)
  {
    this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(this);
  }

  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    paramThrowable.printStackTrace();
    if ((!handleException(paramThrowable)) && (this.mDefaultHandler != null))
    {
      this.mDefaultHandler.uncaughtException(paramThread, paramThrowable);
      return;
    }
    try
    {
      Thread.sleep(3000L);
      label37: java.lang.Process.killProcess(java.lang.Process.myPid());
      System.exit(10);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      break label37;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     CrashHandler
 * JD-Core Version:    0.6.2
 */
