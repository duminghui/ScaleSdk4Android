package com.picooc.utils;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service
{
  private final IBinder binder = new MyBinder();
  private MediaPlayer mPlayer;

  public IBinder onBind(Intent paramIntent)
  {
    return this.binder;
  }

  public void onCreate()
  {
    super.onCreate();
  }

  public void onDestroy()
  {
    super.onDestroy();
    if (this.mPlayer != null)
    {
      this.mPlayer.stop();
      this.mPlayer.release();
    }
  }

  public boolean onUnbind(Intent paramIntent)
  {
    return super.onUnbind(paramIntent);
  }

  public void play()
  {
    if (this.mPlayer == null)
    {
      this.mPlayer = MediaPlayer.create(this, 2131034113);
      this.mPlayer.setLooping(false);
    }
    if (!this.mPlayer.isPlaying())
      this.mPlayer.start();
  }

  public class MyBinder extends Binder
  {
    public MyBinder()
    {
    }

    public MusicService getService()
    {
      return MusicService.this;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     MusicService
 * JD-Core Version:    0.6.2
 */
