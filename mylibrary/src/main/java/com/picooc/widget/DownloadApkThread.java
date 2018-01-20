package com.picooc.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadApkThread extends Thread
{
  Context context;
  String fileNames;
  String httpUrl;
  Handler mHandler;

  public DownloadApkThread(Context paramContext, Handler paramHandler, String paramString1, String paramString2)
  {
    this.context = paramContext;
    this.mHandler = paramHandler;
    this.httpUrl = paramString1;
    this.fileNames = paramString2;
  }

  public void run()
  {
    String str = this.fileNames;
    File localFile1 = new File("/sdcard/");
    if (!localFile1.exists())
      localFile1.mkdir();
    File localFile2 = new File("/sdcard/" + str);
    try
    {
      URL localURL = new URL(this.httpUrl);
      try
      {
        HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
        InputStream localInputStream = localHttpURLConnection.getInputStream();
        FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
        byte[] arrayOfByte = new byte[256];
        localHttpURLConnection.connect();
        double d = 0.0D;
        float f = localHttpURLConnection.getContentLength();
        if (localHttpURLConnection.getResponseCode() >= 400)
          this.mHandler.sendEmptyMessage(3);
        label289: 
        while (true)
        {
          localHttpURLConnection.disconnect();
          localFileOutputStream.close();
          localInputStream.close();
          Message localMessage1 = new Message();
          localMessage1.what = 2;
          localMessage1.obj = localFile2;
          this.mHandler.sendMessage(localMessage1);
          return;
          int i = 0;
          while (true)
          {
            if ((0.0D > 100.0D) || (localInputStream == null))
              break label289;
            int j = localInputStream.read(arrayOfByte);
            d += j;
            int k = (int)(100.0D * d / f);
            if (j <= 0)
              break;
            localFileOutputStream.write(arrayOfByte, 0, j);
            if (k > i)
            {
              Message localMessage2 = new Message();
              localMessage2.what = 1;
              localMessage2.obj = Integer.valueOf(k);
              this.mHandler.sendMessage(localMessage2);
              i = k;
            }
          }
        }
      }
      catch (IOException localIOException)
      {
        while (true)
          localIOException.printStackTrace();
      }
    }
    catch (MalformedURLException localMalformedURLException)
    {
      while (true)
        localMalformedURLException.printStackTrace();
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     DownloadApkThread
 * JD-Core Version:    0.6.2
 */
