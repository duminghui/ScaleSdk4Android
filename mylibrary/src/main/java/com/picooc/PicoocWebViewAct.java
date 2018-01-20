package com.picooc;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.constant.ThemeConstant;

public class PicoocWebViewAct extends PicoocActivity
{
  private String URL = "http://www.picooc.com";
  private String URLTthree = "http://m.jd.com/product/1027404.html";
  private String URLTwo = "http://weibo.com/p/1006063555312184/home?from=page_100606&mod=TAB#place";
  private String URLone = "http://www.picooc.com";
  private TextView nameText;
  private RelativeLayout picture_top;
  private ProgressBar progressBar;
  private WebSettings settings;
  private ThemeConstant themeConstant;
  private WebView webView;

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968576, 2130968581);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903105);
    this.picture_top = ((RelativeLayout)findViewById(2131099979));
    setChooseTheme();
    this.progressBar = ((ProgressBar)findViewById(2131099982));
    this.webView = ((WebView)findViewById(2131099983));
    this.nameText = ((TextView)findViewById(2131099981));
    System.out.println("URL =============" + this.URL);
    int i = getIntent().getIntExtra("indexURL", 0);
    if (i == 1)
    {
      this.URL = this.URLone;
      this.nameText.setText("官方主页");
    }
    while (true)
    {
      this.settings = this.webView.getSettings();
      this.settings.setJavaScriptEnabled(true);
      this.settings.setBuiltInZoomControls(true);
      this.settings.setSupportZoom(true);
      this.settings.setPluginState(WebSettings.PluginState.ON);
      this.webView.setWebViewClient(new MyWebViewClient());
      this.webView.setWebChromeClient(new MyChromeWebClient(null));
      this.webView.loadUrl(this.URL);
      WebSettings localWebSettings = this.webView.getSettings();
      localWebSettings.setUseWideViewPort(true);
      localWebSettings.setLoadWithOverviewMode(true);
      ((Button)findViewById(2131099980)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          PicoocWebViewAct.this.finish();
        }
      });
      findViewById(2131099984).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          PicoocWebViewAct.this.webView.goBack();
        }
      });
      return;
      if (i == 2)
      {
        this.URL = this.URLTwo;
        this.nameText.setText("官方微博");
      }
      else
      {
        this.URL = this.URLTthree;
        this.nameText.setText("官方微博");
      }
    }
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (this.webView.canGoBack()))
    {
      this.webView.goBack();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void setChooseTheme()
  {
    this.themeConstant = new ThemeConstant(this);
    if (this.themeConstant.getbgResource().intValue() == 2130837526)
    {
      this.picture_top.setBackgroundResource(2130837923);
      return;
    }
    if (this.themeConstant.getbgResource().intValue() == 2130837527)
    {
      this.picture_top.setBackgroundResource(2130837924);
      return;
    }
    this.picture_top.setBackgroundResource(2130837922);
  }

  private class MyChromeWebClient extends WebChromeClient
  {
    private MyChromeWebClient()
    {
    }

    public void onProgressChanged(WebView paramWebView, int paramInt)
    {
      super.onProgressChanged(paramWebView, paramInt);
      if (PicoocWebViewAct.this.progressBar != null)
        PicoocWebViewAct.this.progressBar.setProgress(paramInt);
    }
  }

  class MyWebViewClient extends WebViewClient
  {
    MyWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      PicoocWebViewAct.this.progressBar.setVisibility(8);
      super.onPageFinished(paramWebView, paramString);
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      PicoocWebViewAct.this.progressBar.setVisibility(0);
      super.onPageStarted(paramWebView, paramString, paramBitmap);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PicoocWebViewAct
 * JD-Core Version:    0.6.2
 */
