package com.picooc.internet.http;

import android.content.Context;
import android.os.Message;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

public abstract class SyncHttpClient extends AsyncHttpClient
{
  private int responseCode;
  protected AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      SyncHttpClient.this.result = SyncHttpClient.this.onRequestFailed(paramAnonymousThrowable, paramAnonymousString);
    }

    public void onSuccess(String paramAnonymousString)
    {
      SyncHttpClient.this.result = paramAnonymousString;
    }

    protected void sendMessage(Message paramAnonymousMessage)
    {
      handleMessage(paramAnonymousMessage);
    }

    void sendResponseMessage(HttpResponse paramAnonymousHttpResponse)
    {
      SyncHttpClient.this.responseCode = paramAnonymousHttpResponse.getStatusLine().getStatusCode();
      super.sendResponseMessage(paramAnonymousHttpResponse);
    }
  };
  protected String result;

  public String delete(String paramString)
  {
    delete(paramString, null, this.responseHandler);
    return this.result;
  }

  public String delete(String paramString, RequestParams paramRequestParams)
  {
    delete(paramString, paramRequestParams, this.responseHandler);
    return this.result;
  }

  public void delete(String paramString, RequestParams paramRequestParams, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    delete(paramString, paramAsyncHttpResponseHandler);
  }

  public String get(String paramString)
  {
    get(paramString, null, this.responseHandler);
    return this.result;
  }

  public String get(String paramString, RequestParams paramRequestParams)
  {
    get(paramString, paramRequestParams, this.responseHandler);
    return this.result;
  }

  public int getResponseCode()
  {
    return this.responseCode;
  }

  public abstract String onRequestFailed(Throwable paramThrowable, String paramString);

  public String post(String paramString)
  {
    post(paramString, null, this.responseHandler);
    return this.result;
  }

  public String post(String paramString, RequestParams paramRequestParams)
  {
    post(paramString, paramRequestParams, this.responseHandler);
    return this.result;
  }

  public String put(String paramString)
  {
    put(paramString, null, this.responseHandler);
    return this.result;
  }

  public String put(String paramString, RequestParams paramRequestParams)
  {
    put(paramString, paramRequestParams, this.responseHandler);
    return this.result;
  }

  protected void sendRequest(DefaultHttpClient paramDefaultHttpClient, HttpContext paramHttpContext, HttpUriRequest paramHttpUriRequest, String paramString, AsyncHttpResponseHandler paramAsyncHttpResponseHandler, Context paramContext)
  {
    if (paramString != null)
      paramHttpUriRequest.addHeader("Content-Type", paramString);
    new AsyncHttpRequest(paramDefaultHttpClient, paramHttpContext, paramHttpUriRequest, paramAsyncHttpResponseHandler).run();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SyncHttpClient
 * JD-Core Version:    0.6.2
 */
