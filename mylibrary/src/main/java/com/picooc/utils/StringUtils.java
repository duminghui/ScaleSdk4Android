package com.picooc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils
{
  public static boolean isEmail(String paramString)
  {
    try
    {
      boolean bool = Pattern.compile("^([a-z0-9A-Z]+[_\\-\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").matcher(paramString).matches();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public static boolean isMobileNO(String paramString)
  {
    try
    {
      boolean bool = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$").matcher(paramString).matches();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public static boolean isPassword(String paramString)
  {
    try
    {
      boolean bool = Pattern.compile("^[0-9a-zA-Z]{6,16}$").matcher(paramString).matches();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     StringUtils
 * JD-Core Version:    0.6.2
 */
