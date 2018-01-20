package com.picooc.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SharedPreferenceUtils
{
  public static final String BAIDU_INFO = "baidu_info";
  public static final String HuanYing = "huanying";
  public static final String ISOPENPSWD = "is.open.pswd";
  public static final String LIFT_FRAGMENT = "lift_fragment";
  public static final String LIFT_MOVEMENTPLAN = "lift_movementplan";
  public static final String LIFT_REPORTTWO = "lift_reporttwo";
  public static final String LIFT_REPORT_BODYTPYE = "lift_report_bodytype";
  public static final String MAIN_FRAGMENT = "main_fragment";
  public static final String MAIN_FRAGMENT_FAT = "fragment_fat";
  public static final String MAIN_FRAGMENT_WEIGHT = "fragment_weight";
  public static final String PHYSICAL_QUALITY = "physical_quality";
  public static final String PSD = "userid.psd";
  public static final String RESETLATIN = "resetLatin";
  public static final String SHARE_ACT = "share_act";
  public static final String TODAYWEIGHTTIME = "todayweighttime";
  public static final String TODAY_WEIGHT = "today_weight";
  public static final String USER_INFO = "user-Info";

  public static void clearBaidu(Context paramContext)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("baidu_info", 0).edit();
    localEditor.clear();
    localEditor.commit();
  }

  public static void clearFile(Context paramContext, String paramString)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(paramString, 0).edit();
    localEditor.clear();
    localEditor.commit();
  }

  public static Bundle getBaiduChanelId(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("baidu_info", 0);
    Bundle localBundle = new Bundle();
    localBundle.putString("baidu_user_id", localSharedPreferences.getString("baidu_user_id", null));
    localBundle.putString("baiduChanel_id", localSharedPreferences.getString("baiduChanel_id", null));
    return localBundle;
  }

  public static int getCount(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getSharedPreferences(paramString, 0).getAll().size();
      return i;
    }
    catch (Exception localException)
    {
    }
    return 0;
  }

  public static boolean getHuanYingboolen(Context paramContext)
  {
    boolean bool1 = paramContext.getSharedPreferences("huanying", 0).getString("userID", "").equals("");
    boolean bool2 = false;
    if (bool1)
      bool2 = true;
    return bool2;
  }

  public static boolean getIsFirstUpLoadPhoneNb(Context paramContext)
  {
    return paramContext.getSharedPreferences("user-Info", 0).getBoolean("IsFirstUpLoadPhoneNb", true);
  }

  public static String getPsd(Context paramContext)
  {
    return (String)getValue(paramContext, "userid.psd", "psd", String.class);
  }

  public static long getSendAndReceiveTime(Context paramContext, Boolean paramBoolean, long paramLong)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("user-Info", 0);
    if (paramBoolean.booleanValue())
      return localSharedPreferences.getLong(paramLong + "sendTime", 0L);
    return localSharedPreferences.getLong(paramLong + "receiveTime", 0L);
  }

  public static String getThirdPartJDNmae(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("user-Info", 0).getString(paramString + "jd", null);
  }

  public static String getThirdPartLeYuNmae(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("user-Info", 0).getString(paramString + "leyu", null);
  }

  public static String getThirdPartSinaNmae(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("user-Info", 0).getString(paramString + "sina", null);
  }

  public static String getThirdPartqqNmae(Context paramContext, String paramString)
  {
    String str = paramContext.getSharedPreferences("user-Info", 0).getString(paramString + "qq", null);
    if ((str != null) && (!str.equals("null")) && (!str.equals("")))
      return str;
    return "";
  }

  public static String getThirdPartqqTouXiangUrl(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("user-Info", 0).getString(paramString + "qqTouXiangUrl", null);
  }

  public static Object getValue(Context paramContext, String paramString1, String paramString2, Class<?> paramClass)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences(paramString1, 0);
    if (paramClass == Long.class)
      return Long.valueOf(localSharedPreferences.getLong(paramString2, 0L));
    if (paramClass == Float.class)
      return Float.valueOf(localSharedPreferences.getFloat(paramString2, 0.0F));
    if (paramClass == Integer.class)
      return Integer.valueOf(localSharedPreferences.getInt(paramString2, 0));
    if (paramClass == Boolean.class)
      try
      {
        Boolean localBoolean = Boolean.valueOf(localSharedPreferences.getBoolean(paramString2, false));
        return localBoolean;
      }
      catch (Exception localException)
      {
        return Boolean.valueOf(false);
      }
    return localSharedPreferences.getString(paramString2, "");
  }

  @SuppressLint({"CommitPrefEdits"})
  public static Boolean iSMeRiMeTime(Context paramContext)
  {
    Boolean.valueOf(false);
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("MeiRiMe", 0);
    long l = localSharedPreferences.getLong("meiRiTime", 0L);
    Boolean localBoolean;
    if (l != 0L)
      if (System.currentTimeMillis() - l >= 86400000L)
        localBoolean = Boolean.valueOf(true);
    while (true)
    {
      SharedPreferences.Editor localEditor = localSharedPreferences.edit();
      localEditor.putLong("meiRiTime", System.currentTimeMillis());
      localEditor.commit();
      return localBoolean;
      localBoolean = Boolean.valueOf(false);
      continue;
      localBoolean = Boolean.valueOf(true);
    }
  }

  public static boolean isFirstEnterCurPage(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences(paramString, 0).getBoolean(paramString, true);
  }

  public static boolean isOpenPsd(Context paramContext)
  {
    return !getPsd(paramContext).equals("");
  }

  public static boolean isResetLatin(Context paramContext)
  {
    return paramContext.getSharedPreferences("resetLatin", 0).getBoolean("isResetLatin", false);
  }

  public static boolean isShowImage(Context paramContext)
  {
    long l = paramContext.getSharedPreferences("resetLatin", 0).getLong("ShowImage", 0L);
    if (l == 0L)
      return true;
    boolean bool1 = Math.abs(System.currentTimeMillis() - l) < 86400000L;
    boolean bool2 = false;
    if (!bool1)
      bool2 = true;
    return bool2;
  }

  public static boolean isShowOldThis(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("todayweighttime", 0);
    long l = localSharedPreferences.getLong("TodayWeightTime", 0L);
    if (l == 0L)
    {
      localEditor2 = localSharedPreferences.edit();
      localEditor2.putLong("TodayWeightTime", System.currentTimeMillis());
      localEditor2.commit();
    }
    while (DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), l) <= 0L)
    {
      SharedPreferences.Editor localEditor2;
      return true;
    }
    SharedPreferences.Editor localEditor1 = localSharedPreferences.edit();
    localEditor1.putLong("TodayWeightTime", System.currentTimeMillis());
    localEditor1.commit();
    return false;
  }

  public static boolean putValue(Context paramContext, String paramString1, String paramString2, Object paramObject)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(paramString1, 0).edit();
    if ((paramObject instanceof Long))
      localEditor.putLong(paramString2, ((Long)paramObject).longValue());
    while (true)
    {
      return localEditor.commit();
      if ((paramObject instanceof Float))
        localEditor.putFloat(paramString2, ((Float)paramObject).floatValue());
      else if ((paramObject instanceof Integer))
        localEditor.putInt(paramString2, ((Integer)paramObject).intValue());
      else if ((paramObject instanceof Boolean))
        localEditor.putBoolean(paramString2, ((Boolean)paramObject).booleanValue());
      else
        localEditor.putString(paramString2, (String)paramObject);
    }
  }

  public static boolean removeKey(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(paramString1, 0).edit();
    localEditor.remove(paramString2);
    return localEditor.commit();
  }

  public static void resetCurPage(Context paramContext, boolean paramBoolean, String paramString)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(paramString, 0).edit();
    localEditor.putBoolean(paramString, paramBoolean);
    localEditor.commit();
  }

  public static void resetLatin(Context paramContext, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("resetLatin", 0).edit();
    localEditor.putBoolean("isResetLatin", paramBoolean);
    localEditor.commit();
  }

  public static void saveBaiduChanelid(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("baidu_info", 0).edit();
    localEditor.putString("baidu_user_id", paramString1);
    localEditor.putString("baiduChanel_id", paramString2);
    localEditor.commit();
  }

  public static void saveHuanYingboolen(Context paramContext, String paramString)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("huanying", 0).edit();
    localEditor.putString("userID", paramString);
    localEditor.commit();
  }

  public static String saveIsFirstUpLoadPhoneNb(Context paramContext)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("user-Info", 0).edit();
    localEditor.putBoolean("IsFirstUpLoadPhoneNb", false);
    localEditor.commit();
    return null;
  }

  public static void savePsd(Context paramContext, String paramString)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("userid.psd", 0).edit();
    localEditor.putString("psd", paramString);
    localEditor.commit();
  }

  public static void saveSendTim(Context paramContext, long paramLong, Long paramLong1, Long paramLong2)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("user-Info", 0).edit();
    if (paramLong1 != null)
      localEditor.putLong(paramLong + "sendTime", paramLong1.longValue());
    while (true)
    {
      localEditor.commit();
      return;
      localEditor.putLong(paramLong + "receiveTime", paramLong2.longValue());
    }
  }

  public static void saveShowImageTime(Context paramContext)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("resetLatin", 0).edit();
    localEditor.putLong("ShowImage", System.currentTimeMillis());
    localEditor.commit();
  }

  public static void saveThirdPartJDName(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("user-Info", 0).edit();
    localEditor.putString(paramString1 + "jd", paramString2);
    localEditor.commit();
  }

  public static void saveThirdPartLeYuName(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("user-Info", 0).edit();
    localEditor.putString(paramString1 + "leyu", paramString2);
    localEditor.commit();
  }

  public static void saveThirdPartSinaName(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("user-Info", 0).edit();
    localEditor.putString(paramString1 + "sina", paramString2);
    localEditor.commit();
  }

  public static void saveThirdPartqqName(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("user-Info", 0).edit();
    localEditor.putString(paramString1 + "qq", paramString2);
    localEditor.commit();
  }

  public static void saveThirdPartqqTouXiangUrl(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("user-Info", 0).edit();
    localEditor.putString(paramString1 + "qqTouXiangUrl", paramString2);
    localEditor.commit();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SharedPreferenceUtils
 * JD-Core Version:    0.6.2
 */
