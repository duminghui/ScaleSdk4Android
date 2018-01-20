package com.picooc.internet.http;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

class AsyncHttpRequest
  implements Runnable
{
  private final AbstractHttpClient client;
  private final HttpContext context;
  private int executionCount;
  private boolean isBinaryRequest;
  private final HttpUriRequest request;
  private final AsyncHttpResponseHandler responseHandler;

  public AsyncHttpRequest(AbstractHttpClient paramAbstractHttpClient, HttpContext paramHttpContext, HttpUriRequest paramHttpUriRequest, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    this.client = paramAbstractHttpClient;
    this.context = paramHttpContext;
    this.request = paramHttpUriRequest;
    this.responseHandler = paramAsyncHttpResponseHandler;
    if ((paramAsyncHttpResponseHandler instanceof BinaryHttpResponseHandler))
      this.isBinaryRequest = true;
  }

  private void makeRequest()
    throws IOException
  {
    if (!Thread.currentThread().isInterrupted());
    try
    {
      HttpResponse localHttpResponse = this.client.execute(this.request, this.context);
      if ((!Thread.currentThread().isInterrupted()) && (this.responseHandler != null))
        this.responseHandler.sendResponseMessage(localHttpResponse);
      return;
    }
    catch (IOException localIOException)
    {
      do
      {
        localIOException.printStackTrace();
        if (!Thread.currentThread().isInterrupted())
          throw localIOException;
      }
      while (this.responseHandler == null);
      this.responseHandler.onFailure(null, "网络错误!");
    }
  }

  private void makeRequestWithRetries()
    throws ConnectException
  {
    boolean bool = true;
    Object localObject = null;
    HttpRequestRetryHandler localHttpRequestRetryHandler = this.client.getHttpRequestRetryHandler();
    while (true)
    {
      if (!bool)
      {
        ConnectException localConnectException = new ConnectException();
        localConnectException.initCause((Throwable)localObject);
        throw localConnectException;
      }
      try
      {
        makeRequest();
        return;
      }
      catch (UnknownHostException localUnknownHostException)
      {
        do
          localUnknownHostException.printStackTrace();
        while (this.responseHandler == null);
        this.responseHandler.sendFailureMessage(localUnknownHostException, "网络异常!");
        return;
      }
      catch (SocketException localSocketException)
      {
        do
          localSocketException.printStackTrace();
        while (this.responseHandler == null);
        this.responseHandler.sendFailureMessage(localSocketException, "网络异常!");
        return;
      }
      catch (SocketTimeoutException localSocketTimeoutException)
      {
        do
          localSocketTimeoutException.printStackTrace();
        while (this.responseHandler == null);
        this.responseHandler.sendFailureMessage(localSocketTimeoutException, "请求超时");
        return;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        if (this.responseHandler != null)
          this.responseHandler.sendFailureMessage(localIOException, "i/o异常");
        localObject = localIOException;
        int j = 1 + this.executionCount;
        this.executionCount = j;
        bool = localHttpRequestRetryHandler.retryRequest((IOException)localObject, j, this.context);
      }
      catch (NullPointerException localNullPointerException)
      {
        localNullPointerException.printStackTrace();
        if (this.responseHandler != null)
          this.responseHandler.sendFailureMessage(localNullPointerException, "空指针异常");
        localObject = new IOException("NPE in HttpClient" + localNullPointerException.getMessage());
        int i = 1 + this.executionCount;
        this.executionCount = i;
        bool = localHttpRequestRetryHandler.retryRequest((IOException)localObject, i, this.context);
      }
    }
  }

  public void run()
  {
    try
    {
      if (this.responseHandler != null)
        this.responseHandler.sendStartMessage();
      makeRequestWithRetries();
      if (this.responseHandler != null)
        this.responseHandler.sendFinishMessage();
      return;
    }
    catch (IOException localIOException)
    {
      do
        localIOException.printStackTrace();
      while (this.responseHandler == null);
      this.responseHandler.sendFinishMessage();
      if (this.isBinaryRequest)
      {
        this.responseHandler.sendFailureMessage(localIOException, null);
        return;
      }
      this.responseHandler.sendFailureMessage(localIOException, null);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     AsyncHttpRequest
 * JD-Core Version:    0.6.2
 */
