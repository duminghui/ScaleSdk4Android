package com.picooc.bluetooth;

public class BluetoothUtils
{
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

  private static byte charToByte(char paramChar)
  {
    return (byte)"0123456789ABCDEF".indexOf(paramChar);
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
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BluetoothUtils
 * JD-Core Version:    0.6.2
 */
