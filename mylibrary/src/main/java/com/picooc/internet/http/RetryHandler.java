package com.picooc.internet.http;

import android.os.SystemClock;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import javax.net.ssl.SSLException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

class RetryHandler
  implements HttpRequestRetryHandler
{
  private static final int RETRY_SLEEP_TIME_MILLIS = 1500;
  private static HashSet<Class<?>> exceptionBlacklist;
  private static HashSet<Class<?>> exceptionWhitelist = new HashSet();
  private final int maxRetries;

  static
  {
    exceptionBlacklist = new HashSet();
    exceptionWhitelist.add(NoHttpResponseException.class);
    exceptionWhitelist.add(UnknownHostException.class);
    exceptionWhitelist.add(SocketException.class);
    exceptionBlacklist.add(InterruptedIOException.class);
    exceptionBlacklist.add(SSLException.class);
  }

  public RetryHandler(int paramInt)
  {
    this.maxRetries = paramInt;
  }

  protected boolean isInList(HashSet<Class<?>> paramHashSet, Throwable paramThrowable)
  {
    Iterator localIterator = paramHashSet.iterator();
    do
      if (!localIterator.hasNext())
        return false;
    while (!((Class)localIterator.next()).isInstance(paramThrowable));
    return true;
  }

  public boolean retryRequest(IOException paramIOException, int paramInt, HttpContext paramHttpContext)
  {
    boolean bool = true;
    Boolean localBoolean = (Boolean)paramHttpContext.getAttribute("http.request_sent");
    int i;
    if ((localBoolean != null) && (localBoolean.booleanValue()))
    {
      i = 1;
      if (paramInt <= this.maxRetries)
        break label95;
      bool = false;
      label43: if (bool)
        if (!((HttpUriRequest)paramHttpContext.getAttribute("http.request")).getMethod().equals("POST"))
          break label140;
    }
    label140: for (bool = false; ; bool = true)
    {
      if (!bool)
        break label146;
      SystemClock.sleep(1500L);
      return bool;
      i = 0;
      break;
      label95: if (isInList(exceptionBlacklist, paramIOException))
      {
        bool = false;
        break label43;
      }
      if (isInList(exceptionWhitelist, paramIOException))
      {
        bool = true;
        break label43;
      }
      if (i != 0)
        break label43;
      bool = true;
      break label43;
    }
    label146: paramIOException.printStackTrace();
    return bool;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     RetryHandler
 * JD-Core Version:    0.6.2
 */
