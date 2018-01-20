package com.picooc.utils;

import android.annotation.SuppressLint;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SuppressLint({"SimpleDateFormat"})
public class DateUtils
{
  public static final long DAY_MILLIS = 86400000L;
  public static final long WEEK_MILLIS = 604800000L;

  public static int BirthdayToAge(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy年MM月dd");
    try
    {
      int i = getAge(localSimpleDateFormat.parse(paramString));
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }

  public static long changeFormatTimeToTimeStamp(String paramString1, String paramString2)
    throws ParseException
  {
    return new SimpleDateFormat(paramString2).parse(paramString1).getTime();
  }

  @SuppressLint({"SimpleDateFormat"})
  public static String changeOldTimeStringToNewTimeString(String paramString1, String paramString2, String paramString3)
  {
    SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat(paramString2);
    SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat(paramString3);
    try
    {
      String str = localSimpleDateFormat2.format(localSimpleDateFormat1.parse(paramString1));
      return str;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return "";
  }

  public static long changeStringToTimestamp(String paramString1, String paramString2)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString2);
    try
    {
      long l = localSimpleDateFormat.parse(paramString1).getTime();
      return l;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return 0L;
  }

  public static String changeTimeStampToFormatTime(long paramLong, String paramString)
  {
    Date localDate = new Date(paramLong);
    return new SimpleDateFormat(paramString).format(localDate);
  }

  public static int getAge(String paramString1, String paramString2)
  {
    Calendar localCalendar = Calendar.getInstance();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString2);
    int i;
    int m;
    int i2;
    do
    {
      int k;
      int i1;
      do
      {
        Date localDate;
        try
        {
          localDate = localSimpleDateFormat.parse(paramString1);
          if (localCalendar.before(localDate))
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        catch (ParseException localParseException)
        {
          localParseException.printStackTrace();
          i = 0;
          return i;
        }
        int j = localCalendar.get(1);
        k = 1 + localCalendar.get(2);
        m = localCalendar.get(5);
        localCalendar.setTime(localDate);
        int n = localCalendar.get(1);
        i1 = localCalendar.get(2);
        i2 = localCalendar.get(5);
        i = j - n;
      }
      while (k > i1);
      if (k != i1)
        break;
    }
    while (m >= i2);
    return i - 1;
    return i - 1;
  }

  public static int getAge(Date paramDate)
    throws Exception
  {
    Calendar localCalendar = Calendar.getInstance();
    if (localCalendar.before(paramDate))
      throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
    int i = localCalendar.get(1);
    int j = 1 + localCalendar.get(2);
    int k = localCalendar.get(5);
    localCalendar.setTime(paramDate);
    int m = localCalendar.get(1);
    int n = localCalendar.get(2);
    int i1 = localCalendar.get(5);
    int i2 = i - m;
    if (j <= n)
    {
      if (j != n)
        break label100;
      if (k < i1)
        i2--;
    }
    return i2;
    label100: return i2 - 1;
  }

  public static String getCurentDate()
  {
    Calendar localCalendar = Calendar.getInstance();
    return new SimpleDateFormat("MM月dd日").format(localCalendar.getTime());
  }

  public static int getCurrentHour()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(System.currentTimeMillis());
    return localCalendar.get(11);
  }

  public static String getCurrentTime(String paramString)
  {
    return new SimpleDateFormat(paramString, Locale.CHINA).format(new Date());
  }

  public static String[] getCurrentWeekDays(String paramString)
  {
    String[] arrayOfString = new String[7];
    Calendar localCalendar = Calendar.getInstance();
    if (localCalendar.get(7) == 1)
      localCalendar.setTimeInMillis(System.currentTimeMillis() - 86400000L);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString, Locale.CHINA);
    localCalendar.set(7, 2);
    arrayOfString[0] = localSimpleDateFormat.format(localCalendar.getTime());
    localCalendar.add(7, 1);
    arrayOfString[1] = localSimpleDateFormat.format(localCalendar.getTime());
    localCalendar.add(7, 1);
    arrayOfString[2] = localSimpleDateFormat.format(localCalendar.getTime());
    localCalendar.add(7, 1);
    arrayOfString[3] = localSimpleDateFormat.format(localCalendar.getTime());
    localCalendar.add(7, 1);
    arrayOfString[4] = localSimpleDateFormat.format(localCalendar.getTime());
    localCalendar.add(7, 1);
    arrayOfString[5] = localSimpleDateFormat.format(localCalendar.getTime());
    localCalendar.add(7, 1);
    arrayOfString[6] = localSimpleDateFormat.format(localCalendar.getTime());
    return arrayOfString;
  }

  public static int getCurrentWeekInYear()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(new Date());
    return localCalendar.get(3);
  }

  public static String[] getDayOnMonthInYear(String paramString, int paramInt)
  {
    String[] arrayOfString = new String[2];
    Calendar localCalendar = Calendar.getInstance();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString);
    localCalendar.add(2, paramInt);
    localCalendar.set(5, localCalendar.getActualMinimum(5));
    arrayOfString[0] = localSimpleDateFormat.format(localCalendar.getTime());
    localCalendar.set(5, localCalendar.getActualMaximum(5));
    arrayOfString[1] = localSimpleDateFormat.format(localCalendar.getTime());
    return arrayOfString;
  }

  public static String[] getDayOnWeekInYear(String paramString, int paramInt)
  {
    String[] arrayOfString = new String[2];
    Calendar localCalendar = Calendar.getInstance();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString, Locale.CHINA);
    localCalendar.add(3, paramInt);
    localCalendar.set(7, 2);
    arrayOfString[0] = localSimpleDateFormat.format(localCalendar.getTime());
    localCalendar.set(7, 1);
    localCalendar.add(3, 1);
    arrayOfString[1] = localSimpleDateFormat.format(localCalendar.getTime());
    return arrayOfString;
  }

  public static long[] getDayStartTimeAndEndTimeByFlag(int paramInt)
  {
    long[] arrayOfLong = new long[2];
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(6, -paramInt);
    localCalendar.set(11, 0);
    localCalendar.set(12, 0);
    localCalendar.set(13, 0);
    arrayOfLong[0] = localCalendar.getTimeInMillis();
    arrayOfLong[1] = (86400000L + arrayOfLong[0] - 1000L);
    return arrayOfLong;
  }

  public static long[] getDayStartTimeAndEndTimeByTimestamp(long paramLong)
  {
    long[] arrayOfLong = new long[2];
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    localCalendar.set(11, 0);
    localCalendar.set(12, 0);
    localCalendar.set(13, 0);
    arrayOfLong[0] = localCalendar.getTimeInMillis();
    arrayOfLong[1] = (86400000L + arrayOfLong[0] - 1000L);
    return arrayOfLong;
  }

  public static Long getEndTime()
  {
    return Long.valueOf(getDayStartTimeAndEndTimeByTimestamp(System.currentTimeMillis())[1]);
  }

  public static long getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(long paramLong1, long paramLong2)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong1);
    localCalendar.set(11, 0);
    localCalendar.set(12, 0);
    localCalendar.set(13, 0);
    long l1 = localCalendar.getTimeInMillis();
    localCalendar.setTimeInMillis(paramLong2);
    localCalendar.set(11, 0);
    localCalendar.set(12, 0);
    localCalendar.set(13, 0);
    long l2 = localCalendar.getTimeInMillis();
    return (l1 / 1000L - l2 / 1000L) / 86400L;
  }

  public static int getHowManyDaysOnAmonth(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    localCalendar.set(5, 5);
    return localCalendar.getActualMaximum(5);
  }

  public static int getHowManyWeekBetweenNewTimeStampAndOldTimeStamp(long paramLong1, long paramLong2)
  {
    int i = getWeekFlagByTimestamp(paramLong1);
    return getWeekFlagByTimestamp(paramLong2) - i;
  }

  public static String getLongTimeToDateTime(long paramLong)
  {
    Date localDate = new Date(paramLong);
    return new SimpleDateFormat("HH:mm").format(localDate);
  }

  public static int getMonthFlagByTimeStamp(long paramLong)
  {
    Calendar localCalendar1 = Calendar.getInstance();
    localCalendar1.setTimeInMillis(paramLong);
    int i = localCalendar1.get(1);
    int j = localCalendar1.get(2);
    Calendar localCalendar2 = Calendar.getInstance();
    localCalendar2.setTimeInMillis(System.currentTimeMillis());
    int k = localCalendar2.get(1);
    int m = localCalendar2.get(2);
    return 12 * (k - i) + (m - j);
  }

  public static long[] getMonthStartTimeAndEndTimeByFlag(int paramInt)
  {
    long[] arrayOfLong = new long[2];
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(2, -paramInt);
    int i = localCalendar.getActualMinimum(5);
    int j = localCalendar.getActualMaximum(5);
    localCalendar.set(localCalendar.get(1), localCalendar.get(2), i, 0, 0, 0);
    arrayOfLong[0] = localCalendar.getTimeInMillis();
    localCalendar.set(localCalendar.get(1), localCalendar.get(2), j, 23, 59, 59);
    arrayOfLong[1] = localCalendar.getTimeInMillis();
    return arrayOfLong;
  }

  public static String getNormalTime(long paramLong)
  {
    int i = (int)getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(paramLong, System.currentTimeMillis());
    if (i == 0)
      return getLongTimeToDateTime(paramLong);
    if (i == 1)
      return "昨天  " + getLongTimeToDateTime(paramLong);
    return changeTimeStampToFormatTime(paramLong, "yyyy-MM-dd HH:mm");
  }

  public static int getSeasonFlagByTimeStamp(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    localCalendar.set(5, 15);
    long l = localCalendar.getTimeInMillis();
    return (int)((getSeasonStartTimeAndTimeAndDays(System.currentTimeMillis())[1] - l) / 7776000000L);
  }

  public static long[] getSeasonStartTimeAndTimeAndDays(int paramInt)
  {
    long l = System.currentTimeMillis();
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(l);
    localCalendar.set(5, 15);
    return getSeasonStartTimeAndTimeAndDays(localCalendar.getTimeInMillis() - 7776000000L * paramInt);
  }

  public static long[] getSeasonStartTimeAndTimeAndDays(long paramLong)
  {
    Calendar localCalendar1 = Calendar.getInstance();
    localCalendar1.setTimeInMillis(paramLong);
    int i = localCalendar1.get(2);
    int j = localCalendar1.get(1);
    int k = (i + 3) / 3;
    int[] arrayOfInt = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    if (((j % 4 == 0) && (j % 100 != 0)) || (j % 400 == 0))
      arrayOfInt[1] = (1 + arrayOfInt[1]);
    Calendar localCalendar2 = Calendar.getInstance();
    long[] arrayOfLong = new long[6];
    int m = 0;
    switch (k)
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      arrayOfLong[2] = m;
      return arrayOfLong;
      m = 0 + (arrayOfInt[0] + arrayOfInt[1] + arrayOfInt[2]);
      localCalendar2.set(j, 0, 1, 0, 0, 0);
      arrayOfLong[0] = localCalendar2.getTimeInMillis();
      localCalendar2.set(j, 2, arrayOfInt[2], 23, 59, 59);
      arrayOfLong[1] = localCalendar2.getTimeInMillis();
      arrayOfLong[3] = 1L;
      arrayOfLong[4] = 2L;
      arrayOfLong[5] = 3L;
      continue;
      m = 0 + (arrayOfInt[3] + arrayOfInt[4] + arrayOfInt[5]);
      localCalendar2.set(j, 3, 1, 0, 0, 0);
      arrayOfLong[0] = localCalendar2.getTimeInMillis();
      localCalendar2.set(j, 5, arrayOfInt[5], 23, 59, 59);
      arrayOfLong[1] = localCalendar2.getTimeInMillis();
      arrayOfLong[3] = 4L;
      arrayOfLong[4] = 5L;
      arrayOfLong[5] = 6L;
      continue;
      m = 0 + (arrayOfInt[6] + arrayOfInt[7] + arrayOfInt[8]);
      localCalendar2.set(j, 6, 1, 0, 0, 0);
      arrayOfLong[0] = localCalendar2.getTimeInMillis();
      localCalendar2.set(j, 8, arrayOfInt[8], 23, 59, 59);
      arrayOfLong[1] = localCalendar2.getTimeInMillis();
      arrayOfLong[3] = 7L;
      arrayOfLong[4] = 8L;
      arrayOfLong[5] = 9L;
      continue;
      m = 0 + (arrayOfInt[9] + arrayOfInt[10] + arrayOfInt[11]);
      localCalendar2.set(j, 9, 1, 0, 0, 0);
      arrayOfLong[0] = localCalendar2.getTimeInMillis();
      localCalendar2.set(j, 11, arrayOfInt[11], 23, 59, 59);
      arrayOfLong[1] = localCalendar2.getTimeInMillis();
      arrayOfLong[3] = 10L;
      arrayOfLong[4] = 11L;
      arrayOfLong[5] = 12L;
    }
  }

  public static Long getStartTime()
  {
    return Long.valueOf(getDayStartTimeAndEndTimeByTimestamp(System.currentTimeMillis())[0]);
  }

  public static int getWeekFlagByTimestamp(long paramLong)
  {
    return (int)((getWeekStartTimeAndEndTimeByFlag(0)[1] - paramLong) / 604800000L);
  }

  public static long[] getWeekStartTimeAndEndTimeByFlag(int paramInt)
  {
    long l = System.currentTimeMillis() - 604800000L * paramInt;
    long[] arrayOfLong = getDayStartTimeAndEndTimeByTimestamp(l);
    int i = getWeekendByTimestamp(l);
    arrayOfLong[0] -= 86400000L * (i - 1);
    arrayOfLong[1] = (604800000L + arrayOfLong[0] - 1000L);
    return arrayOfLong;
  }

  public static int getWeekendByTimestamp(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    int i = localCalendar.get(7);
    if (i == 1)
      return 7;
    return i - 1;
  }

  public static String getYesterdayDate()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(5, -1);
    return new SimpleDateFormat("MM月dd日 ", Locale.CHINA).format(localCalendar.getTime());
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     DateUtils
 * JD-Core Version:    0.6.2
 */
