package com.picooc.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class PicoocFileUtils
{
  public static void copyFile(String paramString1, String paramString2)
  {
    try
    {
      if (new File(paramString1).exists())
      {
        FileInputStream localFileInputStream = new FileInputStream(paramString1);
        FileOutputStream localFileOutputStream = new FileOutputStream(paramString2);
        byte[] arrayOfByte = new byte[1444];
        while (true)
        {
          int i = localFileInputStream.read(arrayOfByte);
          if (i == -1)
          {
            localFileInputStream.close();
            localFileOutputStream.close();
            return;
          }
          localFileOutputStream.write(arrayOfByte, 0, i);
        }
      }
    }
    catch (Exception localException)
    {
      System.out.println("复制单个文件操作出错");
      localException.printStackTrace();
    }
  }

  public static boolean saveFileByBitmap(String paramString1, String paramString2, Bitmap paramBitmap)
  {
    File localFile = new File(paramString1, paramString2);
    if (localFile.exists())
      localFile.delete();
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 40, localFileOutputStream);
      localFileOutputStream.flush();
      localFileOutputStream.close();
      return true;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
      return false;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return false;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PicoocFileUtils
 * JD-Core Version:    0.6.2
 */
