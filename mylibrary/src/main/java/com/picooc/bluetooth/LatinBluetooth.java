package com.picooc.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class LatinBluetooth
{
  private static final boolean D = true;
  private static final UUID MY_UUID_INSECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
  private static final UUID MY_UUID_SECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
  public static final int STATE_CONNECTED = 3;
  public static final int STATE_CONNECTING = 2;
  public static final int STATE_LISTEN = 1;
  public static final int STATE_NONE = 0;
  private static final String TAG = "PicoocBluetoothLog";
  private boolean isLoopConnect = true;
  private final BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
  private ConnectThread mConnectThread;
  private ConnectedThread mConnectedThread;
  private final Handler mHandler;
  private int mState = 0;

  public LatinBluetooth(Context paramContext, Handler paramHandler)
  {
    this.mHandler = paramHandler;
  }

  private void connectionFailed(BluetoothDevice paramBluetoothDevice, boolean paramBoolean)
  {
    Log.i("picooc", "连接失败，不是丢失！");
    Message localMessage = this.mHandler.obtainMessage(5);
    Bundle localBundle = new Bundle();
    localBundle.putString("toast", "无法连接该设备！");
    localBundle.putBoolean("ConnectAgain", true);
    localMessage.setData(localBundle);
    this.mHandler.sendMessage(localMessage);
    start();
    if (this.isLoopConnect)
      connect(paramBluetoothDevice, paramBoolean);
  }

  private void connectionLost(BluetoothDevice paramBluetoothDevice, boolean paramBoolean)
  {
    Log.i("picooc", "连接失败，丢失！");
    Message localMessage = this.mHandler.obtainMessage(5);
    Bundle localBundle = new Bundle();
    localBundle.putString("toast", "设备连接已经丢失（断开）了");
    localMessage.setData(localBundle);
    this.mHandler.sendMessage(localMessage);
    start();
    if (this.isLoopConnect)
      connect(paramBluetoothDevice, paramBoolean);
  }

  private void setState(int paramInt)
  {
    try
    {
      this.mState = paramInt;
      this.mHandler.obtainMessage(1, paramInt, -1).sendToTarget();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void connect(BluetoothDevice paramBluetoothDevice, boolean paramBoolean)
  {
    try
    {
      if ((this.mState == 2) && (this.mConnectThread != null))
      {
        this.mConnectThread.cancel();
        this.mConnectThread = null;
      }
      if (this.mConnectedThread != null)
      {
        this.mConnectedThread.cancel();
        this.mConnectedThread = null;
      }
      this.mConnectThread = new ConnectThread(paramBluetoothDevice, paramBoolean);
      this.mConnectThread.start();
      setState(2);
      return;
    }
    finally
    {
    }
  }

  public void connected(BluetoothSocket paramBluetoothSocket, BluetoothDevice paramBluetoothDevice, String paramString)
  {
    try
    {
      if (this.mConnectThread != null)
      {
        this.mConnectThread.cancel();
        this.mConnectThread = null;
      }
      if (this.mConnectedThread != null)
      {
        this.mConnectedThread.cancel();
        this.mConnectedThread = null;
      }
      this.mConnectedThread = new ConnectedThread(paramBluetoothSocket, paramString);
      this.mConnectedThread.start();
      Message localMessage = this.mHandler.obtainMessage(4);
      Bundle localBundle = new Bundle();
      localBundle.putString("device_name", paramBluetoothDevice.getName());
      localMessage.setData(localBundle);
      this.mHandler.sendMessage(localMessage);
      setState(3);
      return;
    }
    finally
    {
    }
  }

  public int getState()
  {
    try
    {
      int i = this.mState;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void start()
  {
    try
    {
      this.isLoopConnect = true;
      if (this.mConnectThread != null)
      {
        this.mConnectThread.cancel();
        this.mConnectThread = null;
      }
      if (this.mConnectedThread != null)
      {
        this.mConnectedThread.cancel();
        this.mConnectedThread = null;
      }
      setState(1);
      return;
    }
    finally
    {
    }
  }

  public void stop()
  {
    try
    {
      this.isLoopConnect = false;
      if (this.mConnectThread != null)
      {
        this.mConnectThread.cancel();
        this.mConnectThread = null;
      }
      if (this.mConnectedThread != null)
      {
        this.mConnectedThread.cancel();
        this.mConnectedThread = null;
      }
      setState(0);
      return;
    }
    finally
    {
    }
  }

  public void write(byte[] paramArrayOfByte)
  {
    try
    {
      if (this.mState != 3)
        return;
      ConnectedThread localConnectedThread = this.mConnectedThread;
      localConnectedThread.write(paramArrayOfByte);
      return;
    }
    finally
    {
    }
  }

  private class ConnectThread extends Thread
  {
    private boolean loopFlag = true;
    private boolean mSecure;
    private String mSocketType;
    private final BluetoothDevice mmDevice;
    private final BluetoothSocket mmSocket;

    public ConnectThread(BluetoothDevice paramBoolean, boolean arg3)
    {
      this.mmDevice = paramBoolean;
      boolean bool;
      this.mSecure = bool;
      String str;
      if (bool)
        str = "Secure";
      while (true)
      {
        this.mSocketType = str;
        if (bool);
        try
        {
          BluetoothSocket localBluetoothSocket2 = paramBoolean.createRfcommSocketToServiceRecord(LatinBluetooth.MY_UUID_SECURE);
          BluetoothSocket localBluetoothSocket1;
          for (localObject = localBluetoothSocket2; ; localObject = localBluetoothSocket1)
          {
            this.mmSocket = ((BluetoothSocket)localObject);
            return;
            str = "Insecure";
            break;
            localBluetoothSocket1 = paramBoolean.createInsecureRfcommSocketToServiceRecord(LatinBluetooth.MY_UUID_INSECURE);
          }
        }
        catch (IOException localIOException)
        {
          while (true)
          {
            localIOException.printStackTrace();
            Object localObject = null;
          }
        }
      }
    }

    public void cancel()
    {
      try
      {
        this.loopFlag = false;
        this.mmSocket.close();
        return;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
    }

    public void run()
    {
      setName("ConnectThread" + this.mSocketType);
      LatinBluetooth.this.mAdapter.cancelDiscovery();
      try
      {
        this.mmSocket.connect();
        this.loopFlag = false;
      }
      catch (IOException localIOException)
      {
        synchronized (LatinBluetooth.this)
        {
          LatinBluetooth.this.mConnectThread = null;
          LatinBluetooth.this.connected(this.mmSocket, this.mmDevice, this.mSocketType);
          return;
          localIOException = localIOException;
          try
          {
            this.mmSocket.close();
            if (this.loopFlag)
              LatinBluetooth.this.connectionFailed(this.mmDevice, this.mSecure);
            localIOException.printStackTrace();
            return;
          }
          catch (Exception localException)
          {
            while (true)
              localException.printStackTrace();
          }
        }
      }
    }
  }

  private class ConnectedThread extends Thread
  {
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;
    private final BluetoothSocket mmSocket;

    public ConnectedThread(BluetoothSocket paramString, String arg3)
    {
      this.mmSocket = paramString;
      InputStream localInputStream = null;
      try
      {
        localInputStream = paramString.getInputStream();
        OutputStream localOutputStream2 = paramString.getOutputStream();
        localOutputStream1 = localOutputStream2;
        this.mmInStream = localInputStream;
        this.mmOutStream = localOutputStream1;
        return;
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          localIOException.printStackTrace();
          OutputStream localOutputStream1 = null;
        }
      }
    }

    public void cancel()
    {
      try
      {
        this.mmSocket.close();
        return;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
    }

    public void run()
    {
      byte[] arrayOfByte = new byte[1024];
      try
      {
        while (true)
        {
          int i = this.mmInStream.read(arrayOfByte);
          LatinBluetooth.this.mHandler.obtainMessage(2, i, -1, arrayOfByte).sendToTarget();
        }
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        LatinBluetooth.this.connectionLost(this.mmSocket.getRemoteDevice(), true);
      }
    }

    public void write(byte[] paramArrayOfByte)
    {
      try
      {
        this.mmOutStream.write(paramArrayOfByte);
        LatinBluetooth.this.mHandler.obtainMessage(3, -1, -1, paramArrayOfByte).sendToTarget();
        return;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LatinBluetooth
 * JD-Core Version:    0.6.2
 */
