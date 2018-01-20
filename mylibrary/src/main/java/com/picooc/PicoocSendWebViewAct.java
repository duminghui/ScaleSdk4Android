package com.picooc;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.picooc.constant.ThemeConstant;

import org.achartengine.tools.ModUtils;

public class PicoocSendWebViewAct extends Fragment
{
  private String URL = "http://www.picooc.com";
  private String URLTthree = "http://m.jd.com/product/1027404.html";
  private String URLTwo = "http://weibo.com/p/1006063555312184/home?from=page_100606&mod=TAB#place";
  private String URLone = "http://www.picooc.com";
  private Button back;
  private View layout;
  private LinearLayout linearLayout_bg;
  private TextView nameText;
  private RelativeLayout picture_top;
  private ProgressBar progressBar;
  private WebSettings settings;
  ThemeConstant themeConstant;
  private WebView webView;

  private void invit()
  {
    this.picture_top = ((RelativeLayout)this.layout.findViewById(2131099979));
    this.progressBar = ((ProgressBar)this.layout.findViewById(2131099982));
    this.webView = ((WebView)this.layout.findViewById(2131099983));
    this.nameText = ((TextView)this.layout.findViewById(2131099981));
    System.out.println("URL =============" + this.URL);
    this.URL = this.URLTthree;
    this.nameText.setText("购买Latin");
    this.nameText.setTypeface(ModUtils.getTypeface(getActivity()));
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
    Button localButton = (Button)this.layout.findViewById(2131099980);
    localButton.setBackgroundResource(2130838421);
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ((PicoocActivity3)PicoocSendWebViewAct.this.getActivity()).toggleLeftMenu();
      }
    });
    ((Button)this.layout.findViewById(2131099984)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PicoocSendWebViewAct.this.webView.goBack();
      }
    });
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.layout = paramLayoutInflater.inflate(2130903105, paramViewGroup, false);
    invit();
    this.linearLayout_bg = ((LinearLayout)this.layout.findViewById(2131099739));
    this.themeConstant = new ThemeConstant(getActivity());
    this.themeConstant.setTheme(this.linearLayout_bg);
    return this.layout;
  }

  public void onResume()
  {
    super.onResume();
    this.themeConstant.setTheme(this.linearLayout_bg);
  }

  public void setMenuVisibility(boolean paramBoolean)
  {
    super.setMenuVisibility(paramBoolean);
    View localView;
    if (getView() != null)
    {
      localView = getView();
      if (!paramBoolean)
        break label29;
    }
    label29: for (int i = 0; ; i = 8)
    {
      localView.setVisibility(i);
      return;
    }
  }

  private class MyChromeWebClient extends WebChromeClient
  {
    private MyChromeWebClient()
    {
    }

    public void onProgressChanged(WebView paramWebView, int paramInt)
    {
      super.onProgressChanged(paramWebView, paramInt);
      if (PicoocSendWebViewAct.this.progressBar != null)
        PicoocSendWebViewAct.this.progressBar.setProgress(paramInt);
    }
  }

  class MyWebViewClient extends WebViewClient
  {
    MyWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      PicoocSendWebViewAct.this.progressBar.setVisibility(8);
      super.onPageFinished(paramWebView, paramString);
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      PicoocSendWebViewAct.this.progressBar.setVisibility(0);
      super.onPageStarted(paramWebView, paramString, paramBitmap);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PicoocSendWebViewAct
 * JD-Core Version:    0.6.2
 */
