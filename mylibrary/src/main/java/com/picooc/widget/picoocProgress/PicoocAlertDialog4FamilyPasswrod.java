package com.picooc.widget.picoocProgress;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.picooc.utils.TypefaceUtils;

public class PicoocAlertDialog4FamilyPasswrod
{
  String RightButon;
  String content;
  Context context;
  EditText inputMessage;
  String leftButton;
  AlertDialog myDialog;
  TextView textView_mydialog;
  updateListener updatelistener;

  public PicoocAlertDialog4FamilyPasswrod(String paramString1, String paramString2, String paramString3, Context paramContext)
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

  public String getInputMessage()
  {
    String str = "";
    if (this.inputMessage.getText() != null)
      str = this.inputMessage.getText().toString().trim();
    return str;
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

  public void setoselectHeitListener(updateListener paramupdateListener)
  {
    this.updatelistener = paramupdateListener;
  }

  public void showAlerDialog(View.OnClickListener paramOnClickListener1, View.OnClickListener paramOnClickListener2)
  {
    this.myDialog = new AlertDialog.Builder(this.context).create();
    this.myDialog.setView(LayoutInflater.from(this.context).inflate(2130903064, null));
    this.myDialog.show();
    this.myDialog.getWindow().setContentView(2130903064);
    this.textView_mydialog = ((TextView)this.myDialog.getWindow().findViewById(2131099777));
    this.textView_mydialog.setTypeface(TypefaceUtils.getTypeface(this.context, null));
    this.textView_mydialog.setText(this.content);
    this.inputMessage = ((EditText)this.myDialog.getWindow().findViewById(2131099782));
    this.inputMessage.setTypeface(TypefaceUtils.getTypeface(this.context, null));
    Button localButton1 = (Button)this.myDialog.getWindow().findViewById(2131099778);
    localButton1.setText(this.leftButton);
    localButton1.setOnClickListener(paramOnClickListener2);
    Button localButton2 = (Button)this.myDialog.getWindow().findViewById(2131099779);
    localButton2.setText(this.RightButon);
    localButton2.setOnClickListener(paramOnClickListener1);
  }

  public static abstract interface updateListener
  {
    public abstract void trueUpdate();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PicoocAlertDialog4FamilyPasswrod
 * JD-Core Version:    0.6.2
 */
