package com.picooc.sony.tv;

import android.graphics.Point;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;

final class PreviewCallback
  implements Camera.PreviewCallback
{
  private static final String TAG = PreviewCallback.class.getSimpleName();
  private final CameraConfigurationManager configManager;
  private Handler previewHandler;
  private int previewMessage;
  private final boolean useOneShotPreviewCallback;

  PreviewCallback(CameraConfigurationManager paramCameraConfigurationManager, boolean paramBoolean)
  {
    this.configManager = paramCameraConfigurationManager;
    this.useOneShotPreviewCallback = paramBoolean;
  }

  public void onPreviewFrame(byte[] paramArrayOfByte, Camera paramCamera)
  {
    Point localPoint = this.configManager.getCameraResolution();
    if (!this.useOneShotPreviewCallback)
      paramCamera.setPreviewCallback(null);
    if (this.previewHandler != null)
    {
      this.previewHandler.obtainMessage(this.previewMessage, localPoint.x, localPoint.y, paramArrayOfByte).sendToTarget();
      this.previewHandler = null;
      return;
    }
    Log.d(TAG, "Got preview callback, but no handler for it");
  }

  void setHandler(Handler paramHandler, int paramInt)
  {
    this.previewHandler = paramHandler;
    this.previewMessage = paramInt;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PreviewCallback
 * JD-Core Version:    0.6.2
 */
