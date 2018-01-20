package com.picooc.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

public class MyEditText extends EditText
{
  private OnFinishComposingListener mFinishComposingListener;

  public MyEditText(Context paramContext)
  {
    super(paramContext);
  }

  public MyEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public MyEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo)
  {
    return new MyInputConnection(super.onCreateInputConnection(paramEditorInfo), false);
  }

  public void setOnFinishComposingListener(OnFinishComposingListener paramOnFinishComposingListener)
  {
    this.mFinishComposingListener = paramOnFinishComposingListener;
  }

  public class MyInputConnection extends InputConnectionWrapper
  {
    public MyInputConnection(InputConnection paramBoolean, boolean arg3)
    {
      super(bool);
    }

    public boolean finishComposingText()
    {
      boolean bool = super.finishComposingText();
      if (MyEditText.this.mFinishComposingListener != null)
        MyEditText.this.mFinishComposingListener.finishComposing();
      return bool;
    }
  }

  public static abstract interface OnFinishComposingListener
  {
    public abstract void finishComposing();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     MyEditText
 * JD-Core Version:    0.6.2
 */
