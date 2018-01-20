package com.picooc.internet.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.HashMap;

import org.apache.http.Header;
import org.json.JSONObject;

public class AsyncHttpResponseHandler
{
  protected static final int FAILURE_MESSAGE = 1;
  protected static final int FINISH_MESSAGE = 3;
  protected static final int START_MESSAGE = 2;
  protected static final int SUCCESS_MESSAGE;
  private Handler handler;

  public AsyncHttpResponseHandler()
  {
    if (Looper.myLooper() != null)
      this.handler = new Handler()
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          AsyncHttpResponseHandler.this.handleMessage(paramAnonymousMessage);
        }
      };
  }

  protected void handleFailureMessage(Throwable paramThrowable, String paramString)
  {
    onFailure(paramThrowable, paramString);
  }

  protected void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      return;
    case 0:
      Object[] arrayOfObject2 = (Object[])paramMessage.obj;
      handleSuccessMessage(((Integer)arrayOfObject2[0]).intValue(), (Header[])arrayOfObject2[1], (String)arrayOfObject2[2]);
      return;
    case 1:
      Object[] arrayOfObject1 = (Object[])paramMessage.obj;
      handleFailureMessage((Throwable)arrayOfObject1[0], (String)arrayOfObject1[1]);
      return;
    case 2:
      onStart();
      return;
    case 3:
    }
    onFinish();
  }

  protected void handleSuccessMessage(int paramInt, Header[] paramArrayOfHeader, String paramString)
  {
    onSuccess(paramInt, paramArrayOfHeader, paramString);
  }

  protected Message obtainMessage(int paramInt, Object paramObject)
  {
    if (this.handler != null)
      return this.handler.obtainMessage(paramInt, paramObject);
    Message localMessage = Message.obtain();
    localMessage.what = paramInt;
    localMessage.obj = paramObject;
    return localMessage;
  }

  @Deprecated
  public void onFailure(Throwable paramThrowable)
  {
  }

  public void onFailure(Throwable paramThrowable, String paramString)
  {
    onFailure(paramThrowable);
  }

  public void onFinish()
  {
  }

  public void onStart()
  {
  }

  public void onSuccess(int paramInt, String paramString)
  {
    onSuccess(paramString);
  }

  public void onSuccess(int paramInt, Header[] paramArrayOfHeader, String paramString)
  {
    onSuccess(paramInt, paramString);
  }

  public void onSuccess(String paramString)
  {
  }

  protected void sendFailureMessage(Throwable paramThrowable, String paramString)
  {
    sendMessage(obtainMessage(1, new Object[] { paramThrowable, paramString }));
  }

  protected void sendFailureMessage(Throwable paramThrowable, byte[] paramArrayOfByte)
  {
    sendMessage(obtainMessage(1, new Object[] { paramThrowable, paramArrayOfByte }));
  }

  protected void sendFinishMessage()
  {
    sendMessage(obtainMessage(3, null));
  }

  protected void sendMessage(Message paramMessage)
  {
    if (this.handler != null)
    {
      this.handler.sendMessage(paramMessage);
      return;
    }
    handleMessage(paramMessage);
  }

  void sendResponseMessage(String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("code", Integer.valueOf(1));
    localHashMap.put("message", paramString);
    sendFailureMessage(null, new JSONObject(localHashMap).toString());
  }

  // ERROR //
  void sendResponseMessage(org.apache.http.HttpResponse paramHttpResponse)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface 148 1 0
    //   6: astore_2
    //   7: aload_1
    //   8: invokeinterface 152 1 0
    //   13: astore 6
    //   15: aconst_null
    //   16: astore 4
    //   18: aload 6
    //   20: ifnull +27 -> 47
    //   23: new 154	org/apache/http/entity/BufferedHttpEntity
    //   26: dup
    //   27: aload 6
    //   29: invokespecial 157	org/apache/http/entity/BufferedHttpEntity:<init>	(Lorg/apache/http/HttpEntity;)V
    //   32: astore 7
    //   34: aload 7
    //   36: ldc 159
    //   38: invokestatic 164	org/apache/http/util/EntityUtils:toString	(Lorg/apache/http/HttpEntity;Ljava/lang/String;)Ljava/lang/String;
    //   41: astore 8
    //   43: aload 8
    //   45: astore 4
    //   47: aload_2
    //   48: invokeinterface 169 1 0
    //   53: sipush 300
    //   56: if_icmplt +46 -> 102
    //   59: aload_0
    //   60: new 171	org/apache/http/client/HttpResponseException
    //   63: dup
    //   64: aload_2
    //   65: invokeinterface 169 1 0
    //   70: aload_2
    //   71: invokeinterface 174 1 0
    //   76: invokespecial 176	org/apache/http/client/HttpResponseException:<init>	(ILjava/lang/String;)V
    //   79: aload 4
    //   81: invokevirtual 137	com/picooc/internet/http/AsyncHttpResponseHandler:sendFailureMessage	(Ljava/lang/Throwable;Ljava/lang/String;)V
    //   84: return
    //   85: astore_3
    //   86: aload_3
    //   87: invokevirtual 179	java/io/IOException:printStackTrace	()V
    //   90: aload_0
    //   91: aload_3
    //   92: aconst_null
    //   93: invokevirtual 137	com/picooc/internet/http/AsyncHttpResponseHandler:sendFailureMessage	(Ljava/lang/Throwable;Ljava/lang/String;)V
    //   96: aconst_null
    //   97: astore 4
    //   99: goto -52 -> 47
    //   102: new 181	com/picooc/internet/ResponseEntity
    //   105: dup
    //   106: new 128	org/json/JSONObject
    //   109: dup
    //   110: aload 4
    //   112: invokespecial 183	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   115: invokespecial 186	com/picooc/internet/ResponseEntity:<init>	(Lorg/json/JSONObject;)V
    //   118: invokevirtual 189	com/picooc/internet/ResponseEntity:getResultCode	()Ljava/lang/String;
    //   121: invokestatic 193	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   124: ifne +44 -> 168
    //   127: aload_0
    //   128: aload_2
    //   129: invokeinterface 169 1 0
    //   134: aload_1
    //   135: invokeinterface 197 1 0
    //   140: aload 4
    //   142: invokevirtual 200	com/picooc/internet/http/AsyncHttpResponseHandler:sendSuccessMessage	(I[Lorg/apache/http/Header;Ljava/lang/String;)V
    //   145: return
    //   146: astore 5
    //   148: aload 5
    //   150: invokevirtual 201	org/json/JSONException:printStackTrace	()V
    //   153: aload_0
    //   154: new 203	java/lang/Exception
    //   157: dup
    //   158: ldc 205
    //   160: invokespecial 206	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   163: aconst_null
    //   164: invokevirtual 137	com/picooc/internet/http/AsyncHttpResponseHandler:sendFailureMessage	(Ljava/lang/Throwable;Ljava/lang/String;)V
    //   167: return
    //   168: aload_0
    //   169: new 203	java/lang/Exception
    //   172: dup
    //   173: ldc 208
    //   175: invokespecial 206	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   178: aload 4
    //   180: invokevirtual 137	com/picooc/internet/http/AsyncHttpResponseHandler:sendFailureMessage	(Ljava/lang/Throwable;Ljava/lang/String;)V
    //   183: return
    //   184: astore_3
    //   185: goto -99 -> 86
    //
    // Exception table:
    //   from	to	target	type
    //   7	15	85	java/io/IOException
    //   23	34	85	java/io/IOException
    //   102	145	146	org/json/JSONException
    //   168	183	146	org/json/JSONException
    //   34	43	184	java/io/IOException
  }

  protected void sendStartMessage()
  {
    sendMessage(obtainMessage(2, null));
  }

  protected void sendSuccessMessage(int paramInt, Header[] paramArrayOfHeader, String paramString)
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = new Integer(paramInt);
    arrayOfObject[1] = paramArrayOfHeader;
    arrayOfObject[2] = paramString;
    sendMessage(obtainMessage(0, arrayOfObject));
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     AsyncHttpResponseHandler
 * JD-Core Version:    0.6.2
 */
