package com.picooc.sony.tv;

import android.os.Handler;
import android.os.Message;
import com.picooc.SonyTVAct;

public final class SonyTVActHandler extends Handler
{
  SonyTVAct activity = null;
  DecodeThread decodeThread = null;
  private State state;

  public SonyTVActHandler(SonyTVAct paramSonyTVAct)
  {
    this.activity = paramSonyTVAct;
    this.decodeThread = new DecodeThread(paramSonyTVAct);
    this.decodeThread.start();
    this.state = State.SUCCESS;
    CameraManager.get().startPreview();
    restartPreviewAndDecode();
  }

  public void StartSynchronously()
  {
    this.state = State.SUCCESS;
    CameraManager.get().startPreview();
  }

  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    case 2131099662:
    default:
    case 2131099661:
      do
        return;
      while (this.state != State.PREVIEW);
      CameraManager.get().requestAutoFocus(this, 2131099661);
      return;
    case 2131099665:
      restartPreviewAndDecode();
      return;
    case 2131099664:
      this.state = State.SUCCESS;
      this.activity.handleDecode((String)paramMessage.obj);
      return;
    case 2131099663:
    }
    this.state = State.PREVIEW;
    CameraManager.get().requestPreviewFrame(this.decodeThread.getHandler(), 2131099662);
  }

  public void quitSynchronously()
  {
    this.state = State.DONE;
    CameraManager.get().stopPreview();
    removeMessages(2131099664);
    removeMessages(2131099663);
    removeMessages(2131099662);
    removeMessages(2131099661);
  }

  public void restartPreviewAndDecode()
  {
    if (this.state == State.SUCCESS)
    {
      this.state = State.PREVIEW;
      CameraManager.get().requestPreviewFrame(this.decodeThread.getHandler(), 2131099662);
      CameraManager.get().requestAutoFocus(this, 2131099661);
    }
  }

  private static enum State
  {
    static
    {
      DONE = new State("DONE", 2);
      State[] arrayOfState = new State[3];
      arrayOfState[0] = PREVIEW;
      arrayOfState[1] = SUCCESS;
      arrayOfState[2] = DONE;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SonyTVActHandler
 * JD-Core Version:    0.6.2
 */
