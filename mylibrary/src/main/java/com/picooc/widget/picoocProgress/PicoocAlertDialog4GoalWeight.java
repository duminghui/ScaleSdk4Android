package com.picooc.widget.picoocProgress;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

public class PicoocAlertDialog4GoalWeight
{
  String content;
  Context context;
  AlertDialog myDialog;

  public PicoocAlertDialog4GoalWeight(Context paramContext)
  {
    this.context = paramContext;
  }

  public void showAlerDialog_Goal_Weight()
  {
    this.myDialog = new AlertDialog.Builder(this.context).create();
    this.myDialog.show();
    this.myDialog.getWindow().setContentView(2130903065);
    ((Button)this.myDialog.getWindow().findViewById(2131099781)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PicoocAlertDialog4GoalWeight.this.myDialog.dismiss();
      }
    });
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PicoocAlertDialog4GoalWeight
 * JD-Core Version:    0.6.2
 */
