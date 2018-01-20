package com.picooc.internet;

import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class ResponseEntity
{
  private String method;
  private JSONObject resp;
  private HashMap<String, String> result;

  public ResponseEntity(JSONObject paramJSONObject)
  {
    try
    {
      this.method = paramJSONObject.getString("method");
      JSONObject localJSONObject = paramJSONObject.getJSONObject("result");
      this.result = new HashMap();
      this.result.put("code", localJSONObject.getString("code"));
      this.result.put("message", localJSONObject.getString("message"));
      if (paramJSONObject.has("resp"))
      {
        Object localObject = paramJSONObject.get("resp");
        if ((localObject instanceof JSONObject))
          this.resp = ((JSONObject)localObject);
      }
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      this.result = new HashMap();
      this.result.put("code", "-12");
      this.result.put("message", "系统错误!");
    }
  }

  public String getMessage()
  {
    return (String)this.result.get("message");
  }

  public String getMethod()
  {
    return this.method;
  }

  public JSONObject getResp()
  {
    return this.resp;
  }

  public <T> T getRespByName(String paramString, Class<T> paramClass)
  {
    if (this.resp == null)
      return null;
    try
    {
      Object localObject = this.resp.get(paramString);
      return localObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return null;
  }

  public String getResultCode()
  {
    return (String)this.result.get("code");
  }

  public String toString()
  {
    return "响应 [method=" + this.method + ", result=" + this.result + ", resp=" + this.resp + "]";
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ResponseEntity
 * JD-Core Version:    0.6.2
 */
