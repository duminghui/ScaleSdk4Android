package com.picooc.internet.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;

public class PersistentCookieStore
  implements CookieStore
{
  private static final String COOKIE_NAME_PREFIX = "cookie_";
  private static final String COOKIE_NAME_STORE = "names";
  private static final String COOKIE_PREFS = "CookiePrefsFile";
  private final SharedPreferences cookiePrefs;
  private final ConcurrentHashMap<String, Cookie> cookies;

  public PersistentCookieStore(Context paramContext)
  {
    this.cookiePrefs = paramContext.getSharedPreferences("CookiePrefsFile", 0);
    this.cookies = new ConcurrentHashMap();
    String str1 = this.cookiePrefs.getString("names", null);
    String[] arrayOfString;
    int j;
    if (str1 != null)
    {
      arrayOfString = TextUtils.split(str1, ",");
      j = arrayOfString.length;
    }
    while (true)
    {
      if (i >= j)
      {
        clearExpired(new Date());
        return;
      }
      String str2 = arrayOfString[i];
      String str3 = this.cookiePrefs.getString("cookie_" + str2, null);
      if (str3 != null)
      {
        Cookie localCookie = decodeCookie(str3);
        if (localCookie != null)
          this.cookies.put(str2, localCookie);
      }
      i++;
    }
  }

  public void addCookie(Cookie paramCookie)
  {
    String str = paramCookie.getName() + paramCookie.getDomain();
    if (!paramCookie.isExpired(new Date()))
      this.cookies.put(str, paramCookie);
    while (true)
    {
      SharedPreferences.Editor localEditor = this.cookiePrefs.edit();
      localEditor.putString("names", TextUtils.join(",", this.cookies.keySet()));
      localEditor.putString("cookie_" + str, encodeCookie(new SerializableCookie(paramCookie)));
      localEditor.commit();
      return;
      this.cookies.remove(str);
    }
  }

  protected String byteArrayToHexString(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer(2 * paramArrayOfByte.length);
    int i = paramArrayOfByte.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return localStringBuffer.toString().toUpperCase();
      int k = 0xFF & paramArrayOfByte[j];
      if (k < 16)
        localStringBuffer.append('0');
      localStringBuffer.append(Integer.toHexString(k));
    }
  }

  public void clear()
  {
    SharedPreferences.Editor localEditor = this.cookiePrefs.edit();
    Iterator localIterator = this.cookies.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localEditor.remove("names");
        localEditor.commit();
        this.cookies.clear();
        return;
      }
      String str = (String)localIterator.next();
      localEditor.remove("cookie_" + str);
    }
  }

  public boolean clearExpired(Date paramDate)
  {
    boolean bool = false;
    SharedPreferences.Editor localEditor = this.cookiePrefs.edit();
    Iterator localIterator = this.cookies.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (bool)
          localEditor.putString("names", TextUtils.join(",", this.cookies.keySet()));
        localEditor.commit();
        return bool;
      }
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      String str = (String)localEntry.getKey();
      if (((Cookie)localEntry.getValue()).isExpired(paramDate))
      {
        this.cookies.remove(str);
        localEditor.remove("cookie_" + str);
        bool = true;
      }
    }
  }

  protected Cookie decodeCookie(String paramString)
  {
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(hexStringToByteArray(paramString));
    try
    {
      Cookie localCookie = ((SerializableCookie)new ObjectInputStream(localByteArrayInputStream).readObject()).getCookie();
      return localCookie;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  protected String encodeCookie(SerializableCookie paramSerializableCookie)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      new ObjectOutputStream(localByteArrayOutputStream).writeObject(paramSerializableCookie);
      return byteArrayToHexString(localByteArrayOutputStream.toByteArray());
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public List<Cookie> getCookies()
  {
    return new ArrayList(this.cookies.values());
  }

  protected byte[] hexStringToByteArray(String paramString)
  {
    int i = paramString.length();
    byte[] arrayOfByte = new byte[i / 2];
    for (int j = 0; ; j += 2)
    {
      if (j >= i)
        return arrayOfByte;
      arrayOfByte[(j / 2)] = ((byte)((Character.digit(paramString.charAt(j), 16) << 4) + Character.digit(paramString.charAt(j + 1), 16)));
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PersistentCookieStore
 * JD-Core Version:    0.6.2
 */
