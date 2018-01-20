package com.picooc.internet;

import android.util.Log;
import com.picooc.internet.http.JsonHttpResponseHandler;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

public class SocketHttpRequester
{
  private static final String upload_pic_url = "http://www.picooc.com/picooc/up/";

  public static boolean post(String paramString, Map<String, String> paramMap, FormFile[] paramArrayOfFormFile, JsonHttpResponseHandler paramJsonHttpResponseHandler)
    throws Exception
  {
    int i = 0;
    int j = paramArrayOfFormFile.length;
    int k = 0;
    StringBuilder localStringBuilder1;
    Iterator localIterator;
    label39: URL localURL;
    int n;
    label93: Socket localSocket;
    OutputStream localOutputStream;
    int i2;
    BufferedReader localBufferedReader;
    String str1;
    label337: String str2;
    label349: JSONObject localJSONObject;
    if (k >= j)
    {
      localStringBuilder1 = new StringBuilder();
      localIterator = paramMap.entrySet().iterator();
      if (localIterator.hasNext())
        break label620;
      int m = i + localStringBuilder1.toString().getBytes().length + "-----------------------------7da2137580612--\r\n".getBytes().length;
      localURL = new URL(paramString);
      if (localURL.getPort() != -1)
        break label723;
      n = 80;
      localSocket = new Socket(InetAddress.getByName(localURL.getHost()), n);
      localOutputStream = localSocket.getOutputStream();
      localOutputStream.write(("POST " + localURL.getPath() + " HTTP/1.1\r\n").getBytes());
      localOutputStream.write("Accept: image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*\r\n".getBytes());
      localOutputStream.write("Accept-Language: zh-CN\r\n".getBytes());
      localOutputStream.write("Content-Type: multipart/form-data; boundary=---------------------------7da2137580612\r\n".getBytes());
      localOutputStream.write(("Content-Length: " + m + "\r\n").getBytes());
      localOutputStream.write("Connection: Keep-Alive\r\n".getBytes());
      localOutputStream.write(("Host: " + localURL.getHost() + ":" + n + "\r\n").getBytes());
      localOutputStream.write("\r\n".getBytes());
      localOutputStream.write(localStringBuilder1.toString().getBytes());
      int i1 = paramArrayOfFormFile.length;
      i2 = 0;
      if (i2 < i1)
        break label733;
      localOutputStream.write("-----------------------------7da2137580612--\r\n".getBytes());
      localBufferedReader = new BufferedReader(new InputStreamReader(localSocket.getInputStream()));
      str1 = "";
      str2 = localBufferedReader.readLine();
      if (str2 != null)
        break label954;
      Log.i("picooc", "~~~" + str1);
      if (paramJsonHttpResponseHandler != null)
      {
        localJSONObject = new JSONObject(str1);
        localJSONObject.put("method", "uploadHeadImage");
        if (localJSONObject.getJSONObject("result").getInt("code") != 0)
          break label989;
        paramJsonHttpResponseHandler.sendSuccessMessage(-1, null, localJSONObject.toString());
      }
    }
    while (true)
    {
      localOutputStream.flush();
      localOutputStream.close();
      localBufferedReader.close();
      localSocket.close();
      return true;
      FormFile localFormFile2 = paramArrayOfFormFile[k];
      StringBuilder localStringBuilder4 = new StringBuilder();
      localStringBuilder4.append("--");
      localStringBuilder4.append("---------------------------7da2137580612");
      localStringBuilder4.append("\r\n");
      localStringBuilder4.append("Content-Disposition: form-data;name=\"" + localFormFile2.getParameterName() + "\";filename=\"" + localFormFile2.getFilname() + "\"\r\n");
      localStringBuilder4.append("Content-Type: " + localFormFile2.getContentType() + "\r\n\r\n");
      localStringBuilder4.append("\r\n");
      int i4 = i + localStringBuilder4.length();
      if (localFormFile2.getInStream() != null);
      for (i = (int)(i4 + localFormFile2.getFile().length()); ; i = i4 + localFormFile2.getData().length)
      {
        k++;
        break;
      }
      label620: Entry localEntry = (Entry)localIterator.next();
      localStringBuilder1.append("--");
      localStringBuilder1.append("---------------------------7da2137580612");
      localStringBuilder1.append("\r\n");
      StringBuilder localStringBuilder2 = new StringBuilder("Content-Disposition: form-data; name=\"");
      localStringBuilder1.append((String)localEntry.getKey() + "\"\r\n\r\n");
      localStringBuilder1.append((String)localEntry.getValue());
      localStringBuilder1.append("\r\n");
      break label39;
      label723: n = localURL.getPort();
      break label93;
      label733: FormFile localFormFile1 = paramArrayOfFormFile[i2];
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append("--");
      localStringBuilder3.append("---------------------------7da2137580612");
      localStringBuilder3.append("\r\n");
      localStringBuilder3.append("Content-Disposition: form-data;name=\"" + localFormFile1.getParameterName() + "\";filename=\"" + localFormFile1.getFilname() + "\"\r\n");
      localStringBuilder3.append("Content-Type: " + localFormFile1.getContentType() + "\r\n\r\n");
      localOutputStream.write(localStringBuilder3.toString().getBytes());
      byte[] arrayOfByte;
      label875: int i3;
      if (localFormFile1.getInStream() != null)
      {
        arrayOfByte = new byte[1024];
        i3 = localFormFile1.getInStream().read(arrayOfByte, 0, 1024);
        if (i3 == -1)
          localFormFile1.getInStream().close();
      }
      while (true)
      {
        localOutputStream.write("\r\n".getBytes());
        i2++;
        break;
        localOutputStream.write(arrayOfByte, 0, i3);
        break label875;
        localOutputStream.write(localFormFile1.getData(), 0, localFormFile1.getData().length);
      }
      label954: if (!str2.contains("result"))
        break label337;
      str1 = str1 + str2;
      break label349;
      label989: paramJsonHttpResponseHandler.onFailure(null, localJSONObject);
    }
  }

  public static boolean post(Map<String, String> paramMap, FormFile paramFormFile, JsonHttpResponseHandler paramJsonHttpResponseHandler)
    throws Exception
  {
    return post("http://www.picooc.com/picooc/up/", paramMap, new FormFile[] { paramFormFile }, paramJsonHttpResponseHandler);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SocketHttpRequester
 * JD-Core Version:    0.6.2
 */
