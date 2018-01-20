package com.picooc.widget.loading;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.widget.TextView;

public class PicoocLoading extends Dialog
{
  private static PicoocLoading customProgressDialog = null;
  Context context;

  public PicoocLoading(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public PicoocLoading(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public static PicoocLoading createDialog(Context paramContext)
  {
    customProgressDialog = new PicoocLoading(paramContext, 2131427343);
    customProgressDialog.setContentView(2130903097);
    customProgressDialog.getWindow().getAttributes().gravity = 17;
    return customProgressDialog;
  }

  public static void dismissDialog()
  {
    if ((customProgressDialog != null) && (customProgressDialog.isShowing()))
    {
      customProgressDialog.dismiss();
      customProgressDialog = null;
    }
  }

  public static void showLoadingDialog(Context paramContext)
  {
    if (customProgressDialog == null)
    {
      customProgressDialog = createDialog(paramContext);
      customProgressDialog.show();
      customProgressDialog.setCancelable(false);
      customProgressDialog.setCanceledOnTouchOutside(false);
    }
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    if (customProgressDialog == null)
      return;
    ((AnimationDrawable)((ImageView)customProgressDialog.findViewById(2131099958)).getBackground()).start();
  }

  public PicoocLoading setMessage(String paramString)
  {
    TextView localTextView = (TextView)customProgressDialog.findViewById(2131099959);
    if (localTextView != null)
      localTextView.setText(paramString);
    return customProgressDialog;
  }

  public PicoocLoading setTitile(String paramString)
  {
    return customProgressDialog;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PicoocLoading
 * JD-Core Version:    0.6.2
 */
