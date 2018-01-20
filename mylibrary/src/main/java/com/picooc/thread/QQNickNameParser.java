package com.picooc.thread;

import android.os.Bundle;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class QQNickNameParser
{
  public static Bundle parserHome(String paramString)
    throws JSONException
  {
    Log.i("qianmo", "开始解析了");
    JSONObject localJSONObject = new JSONObject(paramString);
    Bundle localBundle = new Bundle();
    localBundle.putString("nickname", localJSONObject.getString("nickname"));
    localBundle.putString("figureurl", localJSONObject.getString("figureurl"));
    return localBundle;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     QQNickNameParser
 * JD-Core Version:    0.6.2
 */
