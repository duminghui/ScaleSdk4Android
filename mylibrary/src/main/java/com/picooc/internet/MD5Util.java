package com.picooc.internet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util
{
  protected static char[] hexDigits = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  protected static MessageDigest messagedigest = null;

  static
  {
    try
    {
      messagedigest = MessageDigest.getInstance("MD5");
      return;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      System.err.println(MD5Util.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");
      localNoSuchAlgorithmException.printStackTrace();
    }
  }

  private static void appendHexPair(byte paramByte, StringBuffer paramStringBuffer)
  {
    char c1 = hexDigits[((paramByte & 0xF0) >> 4)];
    char c2 = hexDigits[(paramByte & 0xF)];
    paramStringBuffer.append(c1);
    paramStringBuffer.append(c2);
  }

  private static String bufferToHex(byte[] paramArrayOfByte)
  {
    return bufferToHex(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  private static String bufferToHex(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramInt2 * 2);
    int i = paramInt1 + paramInt2;
    for (int j = paramInt1; ; j++)
    {
      if (j >= i)
        return localStringBuffer.toString();
      appendHexPair(paramArrayOfByte[j], localStringBuffer);
    }
  }

  public static boolean checkPassword(String paramString1, String paramString2)
  {
    return getMD5String(paramString1).equals(paramString2);
  }

  public static String getFileMD5String(File paramFile)
    throws IOException
  {
    MappedByteBuffer localMappedByteBuffer = new FileInputStream(paramFile).getChannel().map(MapMode.READ_ONLY, 0L, paramFile.length());
    messagedigest.update(localMappedByteBuffer);
    return bufferToHex(messagedigest.digest());
  }

  public static String getMD5String(String paramString)
  {
    return getMD5String(paramString.getBytes());
  }

  public static String getMD5String(byte[] paramArrayOfByte)
  {
    messagedigest.update(paramArrayOfByte);
    return bufferToHex(messagedigest.digest());
  }

  public static void main(String[] paramArrayOfString)
    throws IOException
  {
    long l1 = System.currentTimeMillis();
    String str = getFileMD5String(new File("e:/新建文件夹.rar"));
    long l2 = System.currentTimeMillis();
    System.out.println("md5:" + str + "time:" + (l2 - l1) / 1000L + "s");
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     MD5Util
 * JD-Core Version:    0.6.2
 */
