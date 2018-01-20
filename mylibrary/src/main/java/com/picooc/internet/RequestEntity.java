package com.picooc.internet;

import android.content.Context;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestEntity
{
  private String appver = "1.0";
  private String method;
  private HashMap<String, Object> req;

  public RequestEntity(String paramString1, String paramString2)
  {
    if ((paramString2 != null) && ("".equals(paramString2)));
    this.method = paramString1;
    this.appver = "5.6";
    this.req = new HashMap();
  }

  public RequestEntity(String paramString1, String paramString2, HashMap<String, Object> paramHashMap)
  {
    this.method = paramString1;
    if ((paramString2 != null) && (!"".equals(paramString2)))
      this.appver = paramString2;
    this.req = paramHashMap;
  }

  public void addParam(String paramString, Object paramObject)
  {
    this.req.put(paramString, paramObject);
  }

  public String getMethod()
  {
    return this.method;
  }

  public Object getParam(String paramString)
  {
    return this.req.get(paramString);
  }

  public HashMap<String, Object> getReq()
  {
    return this.req;
  }

  public void setReq(HashMap<String, Object> paramHashMap)
  {
    this.req = paramHashMap;
  }

  public JSONObject toJson(Context paramContext)
  {
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      localJSONObject1.put("method", this.method);
      localJSONObject1.put("appver", this.appver);
      long l = System.currentTimeMillis();
      localJSONObject1.put("timestamp", l);
      localJSONObject1.put("sign", MD5Util.getMD5String(l + MD5Util.getMD5String(this.method).toUpperCase() + MD5Util.getMD5String(this.appver).toUpperCase()).toUpperCase());
      JSONObject localJSONObject2 = new JSONObject(this.req);
      if (this.method.equals(HttpUtils.psony_tv_interface))
      {
        localJSONObject1.put("data", localJSONObject2);
        return localJSONObject1;
      }
      localJSONObject1.put("req", localJSONObject2);
      return localJSONObject1;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return localJSONObject1;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     RequestEntity
 * JD-Core Version:    0.6.2
 */
