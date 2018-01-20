package com.picooc.sony.tv;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.picooc.SonyTVAct;
import com.zbar.lib.ZbarManager;

final class DecodeHandler extends Handler
{
  SonyTVAct activity = null;

  DecodeHandler(SonyTVAct paramSonyTVAct)
  {
    this.activity = paramSonyTVAct;
  }

  private void decode(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[paramArrayOfByte.length];
    int i = 0;
    if (i >= paramInt2)
    {
      String str = ZbarManager.decode(arrayOfByte, paramInt2, paramInt1, true, this.activity.getX(), this.activity.getY(), this.activity.getCropWidth(), this.activity.getCropHeight());
      if (str == null)
        break label149;
      if (this.activity.getHandler() != null)
      {
        localMessage = new Message();
        localMessage.obj = str;
        localMessage.what = 2131099664;
        this.activity.getHandler().sendMessage(localMessage);
      }
    }
    label149: 
    while (this.activity.getHandler() == null)
    {
      Message localMessage;
      return;
      for (int j = 0; ; j++)
      {
        if (j >= paramInt1)
        {
          i++;
          break;
        }
        arrayOfByte[(-1 + (paramInt2 + j * paramInt2 - i))] = paramArrayOfByte[(j + i * paramInt1)];
      }
    }
    this.activity.getHandler().sendEmptyMessage(2131099663);
  }

  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      return;
    case 2131099662:
      decode((byte[])paramMessage.obj, paramMessage.arg1, paramMessage.arg2);
      return;
    case 2131099666:
    }
    Looper.myLooper().quit();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     DecodeHandler
 * JD-Core Version:    0.6.2
 */
