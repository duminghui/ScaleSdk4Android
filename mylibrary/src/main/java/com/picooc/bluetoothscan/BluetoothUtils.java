package com.picooc.bluetoothscan;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class BluetoothUtils
{
  public static final String BLUETOOTHKEY = "picoocKey";
  public static final String DEVICE_NAME = "device_name";
  public static final int MESSAGE_BLUETOOTH_ERROR = 8;
  public static final int MESSAGE_CONNECT_FAILD = 6;
  public static final int MESSAGE_CONNECT_LOST = 7;
  public static final int MESSAGE_DEVICE_NAME = 4;
  public static final int MESSAGE_NOTSUPPORT_BLE = 9;
  public static final int MESSAGE_READ = 2;
  public static final int MESSAGE_STATE_CHANGE = 1;
  public static final int MESSAGE_WRITE = 3;
  public static final String TOAST = "toast";
  public static final String UERKEY = "picooc.com";

  public static String bytesToHexString(byte[] paramArrayOfByte, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0))
      return null;
    for (int i = 0; ; i++)
    {
      if (i >= paramInt)
        return localStringBuilder.toString();
      String str = Integer.toHexString(0xFF & paramArrayOfByte[i]);
      if (str.length() < 2)
        localStringBuilder.append(0);
      localStringBuilder.append(str);
    }
  }

  public static String bytesToHexString2(int[] paramArrayOfInt, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    if ((paramArrayOfInt == null) || (paramArrayOfInt.length <= 0))
      return null;
    for (int i = 0; ; i++)
    {
      if (i >= paramInt)
        return localStringBuilder.toString();
      String str = Integer.toHexString(0xFF & paramArrayOfInt[i]);
      if (str.length() < 2)
        localStringBuilder.append(0);
      localStringBuilder.append(str);
    }
  }

  private static byte charToByte(char paramChar)
  {
    return (byte)"0123456789ABCDEF".indexOf(paramChar);
  }

  public static String deUnicode(String paramString)
  {
    String str1 = null;
    String str2 = null;
    int i = 0;
    if (i >= paramString.length())
      return str2;
    if (str1 == null)
    {
      str1 = String.valueOf(paramString.charAt(i));
      label29: if (i % 4 == 3)
        if (str1 != null)
          if (str2 != null)
            break label92;
    }
    label92: for (str2 = String.valueOf((char)Integer.valueOf(str1, 16).intValue()); ; str2 = str2 + String.valueOf((char)Integer.valueOf(str1, 16).intValue()))
    {
      str1 = null;
      i++;
      break;
      str1 = str1 + paramString.charAt(i);
      break label29;
    }
  }

  public static String enUnicode(String paramString)
  {
    String str = null;
    int i = 0;
    if (i >= paramString.length())
      return str;
    if (i == 0);
    for (str = getHexString(Integer.toHexString(paramString.charAt(i)).toUpperCase()); ; str = str + getHexString(Integer.toHexString(paramString.charAt(i)).toUpperCase()))
    {
      i++;
      break;
    }
  }

  private static String getHexString(String paramString)
  {
    String str = "";
    int i = paramString.length();
    if (i >= 4)
      return str + paramString;
    if (i == paramString.length());
    for (str = "0"; ; str = str + "0")
    {
      i++;
      break;
    }
  }

  public static String getadress(Context paramContext, String paramString)
  {
    String str = "";
    if (paramContext != null)
      str = paramContext.getSharedPreferences("picooc.com", 0).getString(paramString, "");
    return str;
  }

  public static Boolean getkey(Context paramContext)
  {
    boolean bool = false;
    if (paramContext != null)
      bool = paramContext.getSharedPreferences("picooc.com", 0).getBoolean("picoocKey", true);
    return Boolean.valueOf(bool);
  }

  public static byte[] hexStringToBytes(String paramString)
  {
    byte[] arrayOfByte;
    if ((paramString == null) || (paramString.equals("")))
      arrayOfByte = null;
    while (true)
    {
      return arrayOfByte;
      String str = paramString.toUpperCase();
      int i = str.length() / 2;
      char[] arrayOfChar = str.toCharArray();
      arrayOfByte = new byte[i];
      for (int j = 0; j < i; j++)
      {
        int k = j * 2;
        arrayOfByte[j] = ((byte)(charToByte(arrayOfChar[k]) << 4 | charToByte(arrayOfChar[(k + 1)])));
      }
    }
  }

  // ERROR //
  public static void method1(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 156	java/io/BufferedWriter
    //   5: dup
    //   6: new 158	java/io/OutputStreamWriter
    //   9: dup
    //   10: new 160	java/io/FileOutputStream
    //   13: dup
    //   14: aload_0
    //   15: iconst_1
    //   16: invokespecial 163	java/io/FileOutputStream:<init>	(Ljava/lang/String;Z)V
    //   19: invokespecial 166	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   22: invokespecial 169	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   25: astore_3
    //   26: aload_3
    //   27: aload_1
    //   28: invokevirtual 172	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   31: aload_3
    //   32: invokevirtual 175	java/io/BufferedWriter:close	()V
    //   35: return
    //   36: astore 4
    //   38: aload 4
    //   40: invokevirtual 178	java/lang/Exception:printStackTrace	()V
    //   43: aload_2
    //   44: invokevirtual 175	java/io/BufferedWriter:close	()V
    //   47: return
    //   48: astore 7
    //   50: aload 7
    //   52: invokevirtual 179	java/io/IOException:printStackTrace	()V
    //   55: return
    //   56: astore 5
    //   58: aload_2
    //   59: invokevirtual 175	java/io/BufferedWriter:close	()V
    //   62: aload 5
    //   64: athrow
    //   65: astore 6
    //   67: aload 6
    //   69: invokevirtual 179	java/io/IOException:printStackTrace	()V
    //   72: goto -10 -> 62
    //   75: astore 8
    //   77: aload 8
    //   79: invokevirtual 179	java/io/IOException:printStackTrace	()V
    //   82: return
    //   83: astore 5
    //   85: aload_3
    //   86: astore_2
    //   87: goto -29 -> 58
    //   90: astore 4
    //   92: aload_3
    //   93: astore_2
    //   94: goto -56 -> 38
    //
    // Exception table:
    //   from	to	target	type
    //   2	26	36	java/lang/Exception
    //   43	47	48	java/io/IOException
    //   2	26	56	finally
    //   38	43	56	finally
    //   58	62	65	java/io/IOException
    //   31	35	75	java/io/IOException
    //   26	31	83	finally
    //   26	31	90	java/lang/Exception
  }

  public static void saveadress(String paramString1, String paramString2, Context paramContext)
  {
    if (paramContext != null)
    {
      SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("picooc.com", 0).edit();
      localEditor.putString(paramString1, paramString2);
      localEditor.commit();
    }
  }

  public static void savekey(Boolean paramBoolean, Context paramContext)
  {
    if (paramContext != null)
    {
      SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("picooc.com", 0).edit();
      localEditor.putBoolean("picoocKey", paramBoolean.booleanValue());
      localEditor.commit();
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BluetoothUtils
 * JD-Core Version:    0.6.2
 */
