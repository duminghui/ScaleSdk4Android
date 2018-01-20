package com.picooc.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

public class SearchActivityView extends RelativeLayout
{
  public SearchActivityView(Context paramContext)
  {
    super(paramContext);
  }

  public SearchActivityView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public SearchActivityView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private Activity getActivity()
  {
    Context localContext = getContext();
    if ((localContext instanceof Activity))
      return (Activity)localContext;
    return null;
  }

  public boolean dispatchKeyEventPreIme(KeyEvent paramKeyEvent)
  {
    if ((getActivity() != null) && (paramKeyEvent.getKeyCode() == 4))
      getKeyDispatcherState();
    return super.dispatchKeyEventPreIme(paramKeyEvent);
  }

  protected void hideInputMethod()
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getContext().getSystemService("input_method");
    if (localInputMethodManager != null)
      localInputMethodManager.toggleSoftInput(0, 2);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SearchActivityView
 * JD-Core Version:    0.6.2
 */
