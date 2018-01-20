package com.picooc.widget.picoocProgress;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.picooc.utils.TypefaceUtils;

public class PicoocAlertDialog
{
  String RightButon;
  String content;
  Context context;
  String leftButton;
  AlertDialog myDialog;
  TextView textView_mydialog;
  TextView titele;
  updateListener updatelistener;

  public PicoocAlertDialog(String paramString1, String paramString2, String paramString3, Context paramContext)
  {
    this.context = paramContext;
    this.content = paramString1;
    this.leftButton = paramString2;
    this.RightButon = paramString3;
  }

  public void dismissAlertDialog()
  {
    if ((this.myDialog != null) && (this.myDialog.isShowing()))
      this.myDialog.dismiss();
  }

  public boolean isDialogShowing()
  {
    if (this.myDialog == null)
      return false;
    return this.myDialog.isShowing();
  }

  public void setOnTouchOutside(Boolean paramBoolean)
  {
    if (this.myDialog != null)
      this.myDialog.setCanceledOnTouchOutside(paramBoolean.booleanValue());
  }

  public void setText(String paramString)
  {
    this.textView_mydialog.setText(paramString);
  }

  public void setTextTitel(String paramString)
  {
    if (this.titele != null)
    {
      this.titele.setVisibility(0);
      this.titele.setText(paramString);
    }
  }

  public void setoselectHeitListener(updateListener paramupdateListener)
  {
    this.updatelistener = paramupdateListener;
  }

  public void showAlerDialog()
  {
    this.myDialog = new AlertDialog.Builder(this.context).create();
    this.myDialog.show();
    this.myDialog.getWindow().setContentView(2130903062);
    this.textView_mydialog = ((TextView)this.myDialog.getWindow().findViewById(2131099777));
    this.textView_mydialog.setTypeface(TypefaceUtils.getTypeface(this.context, null));
    this.textView_mydialog.setText(this.content);
    Button localButton1 = (Button)this.myDialog.getWindow().findViewById(2131099778);
    this.titele = ((TextView)this.myDialog.getWindow().findViewById(2131099776));
    localButton1.setText(this.leftButton);
    localButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PicoocAlertDialog.this.myDialog.dismiss();
      }
    });
    final Button localButton2 = (Button)this.myDialog.getWindow().findViewById(2131099779);
    localButton2.setText(this.RightButon);
    localButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PicoocAlertDialog.this.updatelistener.trueUpdate();
        localButton2.setClickable(false);
        localButton2.setEnabled(false);
      }
    });
  }

  public void showAlerDialogShare(View.OnClickListener paramOnClickListener1, View.OnClickListener paramOnClickListener2, String paramString, int paramInt)
  {
    this.myDialog = new AlertDialog.Builder(this.context).create();
    this.myDialog.getWindow().setType(2003);
    this.myDialog.show();
    this.myDialog.getWindow().setContentView(2130903116);
    this.myDialog.setCanceledOnTouchOutside(false);
    TextView localTextView = (TextView)this.myDialog.getWindow().findViewById(2131100029);
    localTextView.setText(paramString);
    localTextView.setTypeface(TypefaceUtils.getTypeface(this.context, null));
    ((ImageView)this.myDialog.getWindow().findViewById(2131100028)).setImageResource(paramInt);
    ((ImageView)this.myDialog.getWindow().findViewById(2131100027)).setOnClickListener(paramOnClickListener2);
    ((Button)this.myDialog.getWindow().findViewById(2131100030)).setOnClickListener(paramOnClickListener1);
  }

  public void showAlerDialogTwo(View.OnClickListener paramOnClickListener1, View.OnClickListener paramOnClickListener2)
  {
    this.myDialog = new AlertDialog.Builder(this.context).create();
    this.myDialog.getWindow().setFlags(4, 4);
    this.myDialog.show();
    this.myDialog.getWindow().setContentView(2130903062);
    this.textView_mydialog = ((TextView)this.myDialog.getWindow().findViewById(2131099777));
    this.textView_mydialog.setTypeface(TypefaceUtils.getTypeface(this.context, null));
    this.textView_mydialog.setText(this.content);
    Button localButton1 = (Button)this.myDialog.getWindow().findViewById(2131099778);
    localButton1.setText(this.leftButton);
    localButton1.setOnClickListener(paramOnClickListener2);
    Button localButton2 = (Button)this.myDialog.getWindow().findViewById(2131099779);
    localButton2.setText(this.RightButon);
    localButton2.setOnClickListener(paramOnClickListener1);
    this.titele = ((TextView)this.myDialog.getWindow().findViewById(2131099776));
  }

  public void showComeQQDialog()
  {
    this.myDialog = new AlertDialog.Builder(this.context).create();
    this.myDialog.show();
    this.myDialog.getWindow().setContentView(2130903063);
    this.myDialog.setCanceledOnTouchOutside(false);
    this.textView_mydialog = ((TextView)this.myDialog.getWindow().findViewById(2131099780));
    this.textView_mydialog.setTypeface(TypefaceUtils.getTypeface(this.context, null));
    this.textView_mydialog.setText(this.content);
    Button localButton = (Button)this.myDialog.getWindow().findViewById(2131099781);
    localButton.setText(this.leftButton);
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PicoocAlertDialog.this.myDialog.dismiss();
      }
    });
  }

  public void showWelcomeAlerDialog()
  {
    this.myDialog = new AlertDialog.Builder(this.context).create();
    this.myDialog.show();
    this.myDialog.getWindow().setContentView(2130903066);
    this.textView_mydialog = ((TextView)this.myDialog.getWindow().findViewById(2131099780));
    this.textView_mydialog.setTypeface(TypefaceUtils.getTypeface(this.context, null));
    this.textView_mydialog.setText(this.content);
    ((Button)this.myDialog.getWindow().findViewById(2131099781)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PicoocAlertDialog.this.myDialog.dismiss();
      }
    });
  }

  public static abstract interface updateListener
  {
    public abstract void trueUpdate();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PicoocAlertDialog
 * JD-Core Version:    0.6.2
 */
