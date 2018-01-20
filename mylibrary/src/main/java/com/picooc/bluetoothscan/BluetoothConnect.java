package com.picooc.bluetoothscan;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.util.UUID;

@SuppressLint({"NewApi"})
public class BluetoothConnect
{
  private static final UUID MY_UUID_INSECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
  private static final UUID MY_UUID_SECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
  public static final int STATE_CONNECTED = 3;
  public static final int STATE_CONNECTING = 2;
  public static final int STATE_LISTEN = 1;
  public static final int STATE_NONE;
  ConnectThread conThread;
  private int loopCount = 1;
  private final BluetoothAdapter mAdapter;
  Context mContext;
  Handler mHandler;
  private int mState = 0;

  public BluetoothConnect(Context paramContext, Handler paramHandler)
  {
    this.mContext = paramContext;
    this.mHandler = paramHandler;
    if (Build.VERSION.SDK_INT >= 18)
    {
      this.mAdapter = ((BluetoothManager)paramContext.getSystemService("bluetooth")).getAdapter();
      return;
    }
    this.mAdapter = BluetoothAdapter.getDefaultAdapter();
  }

  private void connectionFailed()
  {
    Log.i("picooc", "连接失败，不是丢失！");
    Message localMessage = this.mHandler.obtainMessage(6);
    this.mHandler.sendMessage(localMessage);
    setState(1);
  }

  private void connectionLost()
  {
    Message localMessage = this.mHandler.obtainMessage(7);
    Bundle localBundle = new Bundle();
    localBundle.putString("toast", "设备连接已经丢失（断开）了");
    localMessage.setData(localBundle);
    this.mHandler.sendMessage(localMessage);
    setState(1);
  }

  private static BluetoothSocket createBluetoothSocket(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, String paramString, int paramInt3)
  {
    try
    {
      Class[] arrayOfClass = new Class[6];
      arrayOfClass[0] = Integer.TYPE;
      arrayOfClass[1] = Integer.TYPE;
      arrayOfClass[2] = Boolean.TYPE;
      arrayOfClass[3] = Boolean.TYPE;
      arrayOfClass[4] = String.class;
      arrayOfClass[5] = Integer.TYPE;
      Constructor localConstructor = BluetoothSocket.class.getDeclaredConstructor(arrayOfClass);
      localConstructor.setAccessible(true);
      Object[] arrayOfObject = new Object[6];
      arrayOfObject[0] = Integer.valueOf(paramInt1);
      arrayOfObject[1] = Integer.valueOf(paramInt2);
      arrayOfObject[2] = Boolean.valueOf(paramBoolean1);
      arrayOfObject[3] = Boolean.valueOf(paramBoolean2);
      arrayOfObject[4] = paramString;
      arrayOfObject[5] = Integer.valueOf(paramInt3);
      BluetoothSocket localBluetoothSocket = (BluetoothSocket)localConstructor.newInstance(arrayOfObject);
      return localBluetoothSocket;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
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

  private void startConnect(BluetoothDevice paramBluetoothDevice)
  {
    Message localMessage = this.mHandler.obtainMessage(4);
    Bundle localBundle = new Bundle();
    localBundle.putString("device_name", paramBluetoothDevice.getName());
    localMessage.setData(localBundle);
    this.mHandler.sendMessage(localMessage);
    setState(3);
  }

  public void connect(BluetoothDevice paramBluetoothDevice)
  {
    try
    {
      if (this.conThread != null)
      {
        this.conThread.cancel();
        ConnectThread localConnectThread = this.conThread;
        this.conThread = null;
        localConnectThread.interrupt();
      }
      this.loopCount = 1;
      this.conThread = new ConnectThread(paramBluetoothDevice);
      this.conThread.start();
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

  public void stop()
  {
    try
    {
      if (this.conThread != null)
      {
        this.conThread.cancel();
        ConnectThread localConnectThread = this.conThread;
        this.conThread = null;
        localConnectThread.interrupt();
      }
      setState(0);
      return;
    }
    finally
    {
    }
  }

  public void thisconnect(BluetoothDevice paramBluetoothDevice)
  {
    try
    {
      if (this.conThread != null)
      {
        this.conThread.cancel();
        ConnectThread localConnectThread = this.conThread;
        this.conThread = null;
        localConnectThread.interrupt();
      }
      this.conThread = new ConnectThread(paramBluetoothDevice);
      this.conThread.start();
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
      ConnectThread localConnectThread = this.conThread;
      localConnectThread.write(paramArrayOfByte);
      return;
    }
    finally
    {
    }
  }

  private class ConnectThread extends Thread
  {
    int flag = 3;
    private boolean loopFlag = true;
    private boolean mSecure;
    private String mSocketType;
    private BluetoothDevice mmDevice;
    private InputStream mmInStream;
    private OutputStream mmOutStream;
    private BluetoothSocket mmSocket;

    public ConnectThread(BluetoothDevice arg2)
    {
      Object localObject;
      this.mmDevice = localObject;
      try
      {
        BluetoothSocket localBluetoothSocket2 = localObject.createInsecureRfcommSocketToServiceRecord(BluetoothConnect.MY_UUID_INSECURE);
        localBluetoothSocket1 = localBluetoothSocket2;
        this.mmSocket = localBluetoothSocket1;
        return;
      }
      catch (Exception localException)
      {
        while (true)
        {
          localException.printStackTrace();
          BluetoothSocket localBluetoothSocket1 = null;
        }
      }
    }

    public void cancel()
    {
      try
      {
        Log.i("picooc", "取消连接~~" + this);
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
      if (BluetoothConnect.this.mAdapter.isDiscovering())
        BluetoothConnect.this.mAdapter.cancelDiscovery();
      int i = 1;
      int j = 2;
      try
      {
        while (true)
        {
          BluetoothConnect.this.setState(2);
          Log.i("picooc", "开始连接~~" + this + "   设备地址=" + this.mmDevice.getAddress());
          this.mmSocket.connect();
          Log.i("picooc", "连接成功了!" + this.mmDevice.getAddress());
          i = 0;
          this.loopFlag = false;
          BluetoothConnect.this.startConnect(this.mmDevice);
          this.mmInStream = this.mmSocket.getInputStream();
          this.mmOutStream = this.mmSocket.getOutputStream();
          byte[] arrayOfByte = new byte[1024];
          try
          {
            while (true)
            {
              int k = this.mmInStream.read(arrayOfByte);
              BluetoothConnect.this.mHandler.obtainMessage(2, k, -1, arrayOfByte).sendToTarget();
              Log.i("picooc2", "messge==" + k);
            }
          }
          catch (IOException localIOException3)
          {
            localIOException3.printStackTrace();
            BluetoothConnect.this.connectionLost();
            this.mmSocket.close();
            if (i != 0)
            {
              j--;
              if (j > 0);
            }
            else if (i != 0)
            {
              BluetoothConnect.this.connectionFailed();
            }
          }
        }
      }
      catch (IOException localIOException1)
      {
        try
        {
          while (true)
          {
            this.mmSocket.close();
            return;
            localIOException1 = localIOException1;
            Log.i("temp test", "connect exception: " + localIOException1.getMessage());
            try
            {
              if (!this.mmSocket.isConnected())
                Thread.sleep(1000L);
            }
            catch (InterruptedException localInterruptedException)
            {
              localInterruptedException.printStackTrace();
            }
          }
        }
        catch (IOException localIOException2)
        {
        }
      }
    }

    public void write(byte[] paramArrayOfByte)
    {
      try
      {
        if (this.mmOutStream != null)
          this.mmOutStream.write(paramArrayOfByte);
        BluetoothConnect.this.mHandler.obtainMessage(3, -1, -1, paramArrayOfByte).sendToTarget();
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
 * Qualified Name:     BluetoothConnect
 * JD-Core Version:    0.6.2
 */
