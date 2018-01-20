package com.picooc.widget.anyncImageView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util
{
  private static MessageDigest sMd5MessageDigest;
  private static StringBuilder sStringBuilder;

  static
  {
    try
    {
      sMd5MessageDigest = MessageDigest.getInstance("MD5");
      sStringBuilder = new StringBuilder();
      return;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
        localNoSuchAlgorithmException.printStackTrace();
    }
  }

  public static String md5(String paramString)
  {
    sMd5MessageDigest.reset();
    sMd5MessageDigest.update(paramString.getBytes());
    byte[] arrayOfByte = sMd5MessageDigest.digest();
    sStringBuilder.setLength(0);
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfByte.length)
        return sStringBuilder.toString();
      int j = 0xFF & arrayOfByte[i];
      if (j < 16)
        sStringBuilder.append('0');
      sStringBuilder.append(Integer.toHexString(j));
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     Md5Util
 * JD-Core Version:    0.6.2
 */
