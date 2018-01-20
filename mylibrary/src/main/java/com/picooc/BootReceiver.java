package com.picooc;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BootReceiver extends BroadcastReceiver
{
  public static void cancelAlarm(Context paramContext, PendingIntent paramPendingIntent)
  {
    ((AlarmManager)paramContext.getSystemService("alarm")).cancel(paramPendingIntent);
  }

  public static long getAlarmTime(String paramString)
  {
    long l1 = 0L;
    try
    {
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.setTimeInMillis(new Date().getTime());
      localSimpleDateFormat.applyPattern("HH:mm");
      System.out.println("111startTime =" + paramString);
      Date localDate = localSimpleDateFormat.parse(paramString);
      System.out.println("222startTime =" + paramString);
      localCalendar.set(11, localDate.getHours());
      localCalendar.set(12, localDate.getMinutes());
      localCalendar.set(13, 0);
      localCalendar.set(14, 0);
      l1 = localCalendar.getTimeInMillis();
      long l2 = System.currentTimeMillis();
      if (l2 >= l1)
        return 0L;
      localSimpleDateFormat.applyPattern("yyyy-MM-dd HH:mm");
      Log.d("xxx", "now =" + localSimpleDateFormat.format(new Date(l2)));
      Log.d("xxx", "nextTime =" + localSimpleDateFormat.format(new Date(l1)));
      Log.d("xxx", "多少毫秒响应  " + (l1 - l2));
      return l1;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return l1;
  }

  public static PendingIntent getBroadcast(Context paramContext, int paramInt, String paramString)
  {
    System.out.println(paramInt + " : " + paramString);
    Intent localIntent = new Intent();
    localIntent.putExtra("requestCode", paramInt);
    localIntent.putExtra("whichTime", paramString);
    localIntent.setClass(paramContext, AlarmReceiver.class);
    return PendingIntent.getBroadcast(paramContext, paramInt, localIntent, 134217728);
  }

  public static void resetAlarmBoot(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("alarm", 0);
    String str1 = localSharedPreferences.getString("alarm_morning", null);
    System.out.println("moring =" + str1);
    if (str1 != null)
      setAlarm(paramContext, str1, "alarm_morning".hashCode());
    String str2 = localSharedPreferences.getString("alarm_middle", null);
    System.out.println("middle =" + str2);
    if (str2 != null)
      setAlarm(paramContext, str2, "alarm_middle".hashCode());
    String str3 = localSharedPreferences.getString("alarm_evening", null);
    System.out.println("evening =" + str3);
    if (str3 != null)
      setAlarm(paramContext, str3, "alarm_evening".hashCode());
  }

  public static void setAlarm(Context paramContext, String paramString, int paramInt)
  {
    PendingIntent localPendingIntent = getBroadcast(paramContext, paramInt, paramString);
    AlarmManager localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
    long l = getAlarmTime(paramString);
    if (l > 100L)
      localAlarmManager.set(0, l, localPendingIntent);
  }

  public static void setTomorrowAlarm(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    PendingIntent localPendingIntent = getBroadcast(paramContext, paramInt, paramString2);
    AlarmManager localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    long l1 = 0L;
    try
    {
      Date localDate = localSimpleDateFormat.parse(paramString1 + " " + paramString2);
      System.out.println(localSimpleDateFormat.format(localDate));
      long l2 = localDate.getTime();
      l1 = l2;
      if (l1 > 100L)
        localAlarmManager.set(0, l1, localPendingIntent);
      return;
    }
    catch (ParseException localParseException)
    {
      while (true)
        localParseException.printStackTrace();
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
    {
      System.out.println("android.intent.action.BOOT_COMPLETED");
      resetAlarmBoot(paramContext);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BootReceiver
 * JD-Core Version:    0.6.2
 */
