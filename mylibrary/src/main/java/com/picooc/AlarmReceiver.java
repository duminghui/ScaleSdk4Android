package com.picooc;

import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.achartengine.tools.ModUtils;

public class AlarmReceiver extends BroadcastReceiver
{
  private KeyguardManager.KeyguardLock kl;
  private KeyguardManager km;
  private PowerManager pm;
  private PowerManager.WakeLock wl;

  private static String getMesByHashCode(Context paramContext, int paramInt)
  {
    if (paramInt == "alarm_morning".hashCode())
      return paramContext.getString(2131361941);
    if (paramInt == "alarm_middle".hashCode())
      return paramContext.getString(2131361942);
    if (paramInt == "alarm_evening".hashCode())
      return paramContext.getString(2131361943);
    return "Erro";
  }

  public static String getNextAlarmTime(String paramString)
  {
    if (paramString == null)
      return null;
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(new Date().getTime());
    localCalendar.add(5, 1);
    String str = localSimpleDateFormat.format(new Date(localCalendar.getTimeInMillis()));
    Log.d("xxx", str + " " + paramString);
    return str;
  }

  private void showNotification(Context paramContext, String paramString)
  {
    final NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    Notification localNotification = new Notification();
    localNotification.icon = 2130838082;
    localNotification.tickerText = "您有称重提醒";
    localNotification.defaults = (0x1 | localNotification.defaults);
    localNotification.when = System.currentTimeMillis();
    localNotification.vibrate = new long[] { 1000L, 10L, 100L, 1000L };
    Intent localIntent = new Intent();
    localIntent.putExtra("recent", "recent");
    localIntent.setClass(paramContext, WelcomeActivity.class);
    localIntent.setFlags(67108864);
    localNotification.setLatestEventInfo(paramContext, "称重提醒", paramString, PendingIntent.getActivity(paramContext, 0, localIntent, 0));
    localNotificationManager.notify(0, localNotification);
    wakeAndUnlock(paramContext, true);
    if (ModUtils.isRunningForeground(paramContext))
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          localNotificationManager.cancelAll();
        }
      }
      , 3000L);
  }

  private void wakeAndUnlock(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.pm = ((PowerManager)paramContext.getSystemService("power"));
      this.wl = this.pm.newWakeLock(268435466, "bright");
      this.wl.acquire();
      this.km = ((KeyguardManager)paramContext.getSystemService("keyguard"));
      this.kl = this.km.newKeyguardLock("unLock");
      this.kl.disableKeyguard();
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          AlarmReceiver.this.kl.reenableKeyguard();
          AlarmReceiver.this.wl.release();
        }
      }
      , 100L);
      return;
    }
    this.kl.reenableKeyguard();
    this.wl.release();
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.d("xxx", "onReceive ==" + paramIntent.getIntExtra("requestCode", 0));
    Log.d("xxx", "whichTime ==" + paramIntent.getStringExtra("whichTime"));
    int i = paramIntent.getIntExtra("requestCode", 0);
    if (i != 0)
      showNotification(paramContext, getMesByHashCode(paramContext, i));
    String str = paramIntent.getStringExtra("whichTime");
    BootReceiver.setTomorrowAlarm(paramContext, getNextAlarmTime(str), str, i);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     AlarmReceiver
 * JD-Core Version:    0.6.2
 */
