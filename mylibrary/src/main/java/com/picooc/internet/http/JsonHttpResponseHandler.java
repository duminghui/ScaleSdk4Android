package com.picooc.internet.http;

import android.os.Message;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonHttpResponseHandler extends AsyncHttpResponseHandler
{
  protected static final int SUCCESS_JSON_MESSAGE = 100;

  protected void handleFailureMessage(Throwable paramThrowable, String paramString)
  {
    if (paramString != null)
    {
      try
      {
        Object localObject = parseResponse(paramString);
        if ((localObject instanceof JSONObject))
        {
          onFailure(paramThrowable, (JSONObject)localObject);
          return;
        }
        if ((localObject instanceof JSONArray))
        {
          onFailure(paramThrowable, (JSONArray)localObject);
          return;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        onFailure(paramThrowable, paramString);
        return;
      }
      onFailure(paramThrowable, paramString);
      return;
    }
    onFailure(paramThrowable, "网络不给力:(");
  }

  protected void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      super.handleMessage(paramMessage);
      return;
    case 100:
    }
    Object[] arrayOfObject = (Object[])paramMessage.obj;
    handleSuccessJsonMessage(((Integer)arrayOfObject[0]).intValue(), (Header[])arrayOfObject[1], arrayOfObject[2]);
  }

  protected void handleSuccessJsonMessage(int paramInt, Header[] paramArrayOfHeader, Object paramObject)
  {
    if ((paramObject instanceof JSONObject))
    {
      onSuccess(paramInt, paramArrayOfHeader, (JSONObject)paramObject);
      return;
    }
    if ((paramObject instanceof JSONArray))
    {
      onSuccess(paramInt, paramArrayOfHeader, (JSONArray)paramObject);
      return;
    }
    onFailure(new JSONException("Unexpected type " + paramObject.getClass().getName()), null);
  }

  public void onFailure(Throwable paramThrowable, JSONArray paramJSONArray)
  {
  }

  public void onFailure(Throwable paramThrowable, JSONObject paramJSONObject)
  {
  }

  public void onSuccess(int paramInt, JSONArray paramJSONArray)
  {
    onSuccess(paramJSONArray);
  }

  public void onSuccess(int paramInt, JSONObject paramJSONObject)
  {
    onSuccess(paramJSONObject);
  }

  public void onSuccess(int paramInt, Header[] paramArrayOfHeader, JSONArray paramJSONArray)
  {
    onSuccess(paramInt, paramJSONArray);
  }

  public void onSuccess(int paramInt, Header[] paramArrayOfHeader, JSONObject paramJSONObject)
  {
    onSuccess(paramInt, paramJSONObject);
  }

  public void onSuccess(JSONArray paramJSONArray)
  {
  }

  public void onSuccess(JSONObject paramJSONObject)
  {
  }

  protected Object parseResponse(String paramString)
    throws JSONException
  {
    String str = paramString.trim();
    Object localObject;
    if (!str.startsWith("{"))
    {
      boolean bool = str.startsWith("[");
      localObject = null;
      if (!bool);
    }
    else
    {
      localObject = new JSONTokener(str).nextValue();
    }
    if (localObject == null)
      localObject = str;
    return localObject;
  }

  public void sendSuccessMessage(int paramInt, Header[] paramArrayOfHeader, String paramString)
  {
    if (paramInt != 204)
      try
      {
        Object localObject = parseResponse(paramString);
        Object[] arrayOfObject2 = new Object[3];
        arrayOfObject2[0] = Integer.valueOf(paramInt);
        arrayOfObject2[1] = paramArrayOfHeader;
        arrayOfObject2[2] = localObject;
        sendMessage(obtainMessage(100, arrayOfObject2));
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        sendFailureMessage(localJSONException, paramString);
        return;
      }
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Integer.valueOf(paramInt);
    arrayOfObject1[1] = new JSONObject();
    sendMessage(obtainMessage(100, arrayOfObject1));
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     JsonHttpResponseHandler
 * JD-Core Version:    0.6.2
 */
