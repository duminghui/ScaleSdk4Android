package com.picooc.bluetoothscan;

import android.bluetooth.BluetoothDevice;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BluetoothClsUtils
{
  public static boolean cancelBondProcess(Class<?> paramClass, BluetoothDevice paramBluetoothDevice)
    throws Exception
  {
    return ((Boolean)paramClass.getMethod("cancelBondProcess", new Class[0]).invoke(paramBluetoothDevice, new Object[0])).booleanValue();
  }

  public static boolean cancelPairingUserInput(Class<?> paramClass, BluetoothDevice paramBluetoothDevice)
    throws Exception
  {
    return ((Boolean)paramClass.getMethod("cancelPairingUserInput", new Class[0]).invoke(paramBluetoothDevice, new Object[0])).booleanValue();
  }

  public static boolean createBond(Class<?> paramClass, BluetoothDevice paramBluetoothDevice)
    throws Exception
  {
    return ((Boolean)paramClass.getMethod("createBond", new Class[0]).invoke(paramBluetoothDevice, new Object[0])).booleanValue();
  }

  public static void printAllInform(Class<?> paramClass)
  {
    try
    {
      Method[] arrayOfMethod = paramClass.getMethods();
      int i = 0;
      Field[] arrayOfField;
      if (i >= arrayOfMethod.length)
        arrayOfField = paramClass.getFields();
      for (int j = 0; ; j++)
      {
        if (j >= arrayOfField.length)
        {
          return;
          Log.e("method name", arrayOfMethod[i].getName());
          i++;
          break;
        }
        Log.e("Field name", arrayOfField[j].getName());
      }
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException.printStackTrace();
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static boolean removeBond(Class<?> paramClass, BluetoothDevice paramBluetoothDevice)
    throws Exception
  {
    return ((Boolean)paramClass.getMethod("removeBond", new Class[0]).invoke(paramBluetoothDevice, new Object[0])).booleanValue();
  }

  public static boolean setPin(Class<?> paramClass, BluetoothDevice paramBluetoothDevice, String paramString)
    throws Exception
  {
    try
    {
      Method localMethod = paramClass.getDeclaredMethod("setPin", new Class[] { [B.class });
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramString.getBytes();
      Boolean localBoolean = (Boolean)localMethod.invoke(paramBluetoothDevice, arrayOfObject);
      Log.e("returnValue", localBoolean);
      return true;
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException.printStackTrace();
      return true;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return true;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return true;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BluetoothClsUtils
 * JD-Core Version:    0.6.2
 */
