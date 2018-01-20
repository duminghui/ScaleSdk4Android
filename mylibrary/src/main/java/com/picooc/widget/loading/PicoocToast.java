package com.picooc.widget.loading;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.picooc.MyApplication;

public class PicoocToast
{
  private static TextView messageView;
  private static LinearLayout toastView;

  public static void showToast(Activity paramActivity, String paramString)
  {
    if (paramActivity == null)
      return;
    LayoutInflater localLayoutInflater = paramActivity.getLayoutInflater();
    TextView localTextView;
    LinearLayout localLinearLayout;
    if (0 != 0)
    {
      localTextView = null;
      localLinearLayout = null;
      if (0 != 0);
    }
    else
    {
      localLinearLayout = (LinearLayout)localLayoutInflater.inflate(2130903104, null);
      localTextView = (TextView)localLinearLayout.findViewById(2131099978);
    }
    localTextView.setTypeface(((MyApplication)paramActivity.getApplicationContext()).getTypeFace());
    localTextView.setText(paramString);
    Toast localToast = new Toast(paramActivity);
    localToast.setDuration(0);
    localToast.setGravity(16, 0, -200);
    localToast.setView(localLinearLayout);
    localToast.show();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PicoocToast
 * JD-Core Version:    0.6.2
 */
