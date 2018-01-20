package com.picooc.thread;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.picooc.internet.HttpUtils;
import com.picooc.utils.SharedPreferenceUtils;
import java.io.InputStream;
import org.achartengine.tools.ModUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class UpLoadQQNickNameThread extends Thread
{
  String UserID;
  Context context;
  InputStream is;
  Handler mHandler = null;
  String oauth_consumer_key;
  String openID;
  String token;

  public UpLoadQQNickNameThread(Context paramContext, Handler paramHandler, String paramString1, String paramString2, String paramString3)
  {
    Log.i("ibokan", "线程启动了");
    this.mHandler = paramHandler;
    this.context = paramContext;
    this.oauth_consumer_key = paramString1;
    this.token = paramString2;
    this.openID = paramString3;
  }

  public UpLoadQQNickNameThread(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Log.i("ibokan", "线程启动了");
    this.context = paramContext;
    this.oauth_consumer_key = paramString1;
    this.token = paramString2;
    this.openID = paramString3;
    this.UserID = paramString4;
  }

  public void run()
  {
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
    String str1 = this.context.getSharedPreferences("qianmo", 0).getString("operatorcode2", "");
    Log.i("qianmo", "operatorcode==========" + str1);
    HttpGet localHttpGet = new HttpGet(HttpUtils.QQNickNameURL + "oauth_consumer_key=" + this.oauth_consumer_key + "&access_token=" + this.token + "&openid=" + this.openID);
    HttpParams localHttpParams = localHttpGet.getParams();
    HttpConnectionParams.setConnectionTimeout(localHttpParams, 15000);
    HttpConnectionParams.setSoTimeout(localHttpParams, 15000);
    localHttpGet.setParams(localHttpParams);
    try
    {
      Log.i("----before", "it's run here");
      HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpGet);
      Log.i("----after", "it's run here");
      InputStream localInputStream = localHttpResponse.getEntity().getContent();
      String str2 = ModUtils.inputstream2String(localInputStream);
      Log.i("ibokan", "resuld=" + str2);
      Bundle localBundle = QQNickNameParser.parserHome(str2);
      if ((localBundle.getString("nickname") != null) && (!localBundle.getString("nickname").equals("")) && (!localBundle.getString("nickname").equals("null")))
      {
        SharedPreferenceUtils.saveThirdPartqqName(this.context, this.openID, localBundle.getString("nickname"));
        SharedPreferenceUtils.saveThirdPartqqTouXiangUrl(this.context, this.openID, localBundle.getString("figureurl"));
      }
      while (true)
      {
        Message localMessage2 = new Message();
        localMessage2.what = 1;
        localMessage2.obj = localBundle;
        if (this.mHandler != null)
          this.mHandler.sendMessage(localMessage2);
        localInputStream.close();
        return;
        SharedPreferenceUtils.saveThirdPartqqName(this.context, this.openID, "");
        SharedPreferenceUtils.saveThirdPartqqTouXiangUrl(this.context, this.openID, "");
      }
    }
    catch (Exception localException)
    {
      Message localMessage1 = new Message();
      localMessage1.what = 2;
      if (this.mHandler != null)
        this.mHandler.sendMessage(localMessage1);
      localException.printStackTrace();
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     UpLoadQQNickNameThread
 * JD-Core Version:    0.6.2
 */
