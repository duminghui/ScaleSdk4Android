package com.picooc.internet.http;

import android.os.Message;
import java.io.IOException;
import java.util.regex.Pattern;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;

public class BinaryHttpResponseHandler extends AsyncHttpResponseHandler
{
  private static String[] mAllowedContentTypes = { "image/jpeg", "image/png" };

  public BinaryHttpResponseHandler()
  {
  }

  public BinaryHttpResponseHandler(String[] paramArrayOfString)
  {
    this();
    mAllowedContentTypes = paramArrayOfString;
  }

  protected void handleFailureMessage(Throwable paramThrowable, byte[] paramArrayOfByte)
  {
    onFailure(paramThrowable, paramArrayOfByte);
  }

  protected void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      super.handleMessage(paramMessage);
      return;
    case 0:
      Object[] arrayOfObject2 = (Object[])paramMessage.obj;
      handleSuccessMessage(((Integer)arrayOfObject2[0]).intValue(), (byte[])arrayOfObject2[1]);
      return;
    case 1:
    }
    Object[] arrayOfObject1 = (Object[])paramMessage.obj;
    handleFailureMessage((Throwable)arrayOfObject1[0], arrayOfObject1[1].toString());
  }

  protected void handleSuccessMessage(int paramInt, byte[] paramArrayOfByte)
  {
    onSuccess(paramInt, paramArrayOfByte);
  }

  @Deprecated
  public void onFailure(Throwable paramThrowable, byte[] paramArrayOfByte)
  {
    onFailure(paramThrowable);
  }

  public void onSuccess(int paramInt, byte[] paramArrayOfByte)
  {
    onSuccess(paramArrayOfByte);
  }

  public void onSuccess(byte[] paramArrayOfByte)
  {
  }

  protected void sendFailureMessage(Throwable paramThrowable, byte[] paramArrayOfByte)
  {
    sendMessage(obtainMessage(1, new Object[] { paramThrowable, paramArrayOfByte }));
  }

  void sendResponseMessage(HttpResponse paramHttpResponse)
  {
    int i = 0;
    StatusLine localStatusLine = paramHttpResponse.getStatusLine();
    Header[] arrayOfHeader = paramHttpResponse.getHeaders("Content-Type");
    if (arrayOfHeader.length != 1)
    {
      sendFailureMessage(new HttpResponseException(localStatusLine.getStatusCode(), "None, or more than one, Content-Type Header found!"), null);
      return;
    }
    Header localHeader = arrayOfHeader[0];
    int j = 0;
    String[] arrayOfString = mAllowedContentTypes;
    int k = arrayOfString.length;
    while (true)
    {
      if (i >= k)
      {
        if (j != 0)
          break;
        sendFailureMessage(new HttpResponseException(localStatusLine.getStatusCode(), "Content-Type not allowed!"), null);
        return;
      }
      if (Pattern.matches(arrayOfString[i], localHeader.getValue()))
        j = 1;
      i++;
    }
    try
    {
      HttpEntity localHttpEntity = paramHttpResponse.getEntity();
      BufferedHttpEntity localBufferedHttpEntity = null;
      if (localHttpEntity != null)
        localBufferedHttpEntity = new BufferedHttpEntity(localHttpEntity);
      byte[] arrayOfByte2 = EntityUtils.toByteArray(localBufferedHttpEntity);
      arrayOfByte1 = arrayOfByte2;
      if (localStatusLine.getStatusCode() >= 300)
      {
        sendFailureMessage(new HttpResponseException(localStatusLine.getStatusCode(), localStatusLine.getReasonPhrase()), arrayOfByte1);
        return;
      }
    }
    catch (IOException localIOException)
    {
      byte[] arrayOfByte1;
      while (true)
      {
        localIOException.printStackTrace();
        sendFailureMessage(localIOException, null);
        arrayOfByte1 = null;
      }
      sendSuccessMessage(localStatusLine.getStatusCode(), arrayOfByte1);
    }
  }

  protected void sendSuccessMessage(int paramInt, byte[] paramArrayOfByte)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    arrayOfObject[1] = paramArrayOfByte;
    sendMessage(obtainMessage(0, arrayOfObject));
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BinaryHttpResponseHandler
 * JD-Core Version:    0.6.2
 */
