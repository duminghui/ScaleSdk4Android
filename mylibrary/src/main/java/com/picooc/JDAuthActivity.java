package com.picooc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.joauth.sdk.utils.UrlUtil;

public class JDAuthActivity extends Activity
{
  private String JDOptionAppKey = "";
  private String JDOptionAppRedirectUri = "";
  private String JDOptionAppSecret = "";
  private int color;
  private Bundle params = new Bundle();
  private ProgressDialog progressDialog;
  private RelativeLayout rlNavigation;
  private TextView tvLeft;
  private String url = "";
  private WebView webView;

  private void showDialog()
  {
  }

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968576, 2130968581);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903041);
    this.tvLeft = ((TextView)findViewById(2131099939));
    this.rlNavigation = ((RelativeLayout)findViewById(2131099674));
    this.tvLeft.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        JDAuthActivity.this.finish();
      }
    });
    Bundle localBundle = getIntent().getBundleExtra("JDAuth");
    if (localBundle != null)
    {
      this.JDOptionAppKey = localBundle.getString("JDOptionAppKey");
      this.JDOptionAppSecret = localBundle.getString("JDOptionAppSecret");
      this.JDOptionAppRedirectUri = localBundle.getString("JDOptionAppRedirectUri");
      this.color = localBundle.getInt("NavaigationColor", Color.parseColor("#cc0033"));
    }
    this.rlNavigation.setBackgroundColor(Color.parseColor("#cc0033"));
    this.progressDialog = new ProgressDialog(this);
    this.progressDialog.setMessage("loading..");
    this.webView = ((WebView)findViewById(2131099675));
    this.webView.setVerticalScrollBarEnabled(false);
    this.webView.setHorizontalScrollBarEnabled(false);
    this.webView.setWebViewClient(new JdWebViewClient(null));
    this.webView.getSettings().setJavaScriptEnabled(true);
    this.webView.getSettings().setSavePassword(true);
    this.webView.getSettings().setSaveFormData(true);
    this.params.putString("response_type", "code");
    this.params.putString("client_id", this.JDOptionAppKey);
    this.params.putString("redirect_uri", this.JDOptionAppRedirectUri);
    this.params.putString("state", "GET_AUTH_KEY");
    this.params.putString("view", "wap");
    this.url = ("https://auth.360buy.com/oauth/authorize?" + UrlUtil.encodeUrl(this.params));
    this.webView.loadUrl(this.url);
  }

  private class GetAccessTokenTask extends AsyncTask<String, Integer, Object>
  {
    private GetAccessTokenTask()
    {
    }

    protected Object doInBackground(String[] paramArrayOfString)
    {
      String str1 = paramArrayOfString[0];
      Bundle localBundle = new Bundle();
      localBundle.putString("grant_type", "authorization_code");
      localBundle.putString("client_id", JDAuthActivity.this.JDOptionAppKey);
      localBundle.putString("redirect_uri", JDAuthActivity.this.JDOptionAppRedirectUri);
      localBundle.putString("client_secret", JDAuthActivity.this.JDOptionAppSecret);
      localBundle.putString("state", "GET_TOKEN");
      localBundle.putString("code", str1);
      String str2 = "https://auth.360buy.com/oauth/token?" + UrlUtil.encodeUrl(localBundle);
      try
      {
        String str3 = UrlUtil.openUrl(str2, "POST", localBundle);
        System.out.println(str3);
        return str3;
      }
      catch (Exception localException)
      {
        Log.e("Jingdong-WebView", "can not get access code: ", localException);
        return localException;
      }
    }

    protected void onPostExecute(Object paramObject)
    {
      super.onPostExecute(paramObject);
      JDAuthActivity.this.progressDialog.dismiss();
      if ((paramObject instanceof Exception))
        ((Exception)paramObject).printStackTrace();
      while (!(paramObject instanceof String))
        return;
      Intent localIntent = new Intent();
      localIntent.putExtra("result", (String)paramObject);
      JDAuthActivity.this.setResult(1000, localIntent);
      JDAuthActivity.this.finish();
    }

    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }

  private class JdWebViewClient extends WebViewClient
  {
    private JdWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      System.out.println(paramString);
      if (paramString.startsWith(JDAuthActivity.this.JDOptionAppRedirectUri))
      {
        Bundle localBundle = UrlUtil.parseUrl(paramString);
        String str1 = localBundle.getString("error");
        if (str1 == null)
          str1 = localBundle.getString("error_type");
        if (str1 == null)
        {
          String str2 = localBundle.getString("code");
          new GetAccessTokenTask(JDAuthActivity.this, null).execute(new String[] { str2 });
        }
      }
      do
      {
        return;
        if (paramString.startsWith("https://passport.m.jd.com/user/home.action"))
        {
          JDAuthActivity.this.webView.loadUrl(JDAuthActivity.this.url);
          return;
        }
      }
      while (JDAuthActivity.this.progressDialog == null);
      JDAuthActivity.this.progressDialog.dismiss();
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      super.onPageStarted(paramWebView, JDAuthActivity.this.url, paramBitmap);
      if (JDAuthActivity.this.progressDialog != null)
        JDAuthActivity.this.progressDialog.show();
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      Log.e("url", paramString);
      JDAuthActivity.this.webView.loadUrl(paramString);
      return false;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     JDAuthActivity
 * JD-Core Version:    0.6.2
 */
