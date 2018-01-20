package com.picooc.sony.tv;

import android.os.IBinder;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class FlashlightManager
{
  private static final String TAG = FlashlightManager.class.getSimpleName();
  private static final Object iHardwareService = getHardwareService();
  private static final Method setFlashEnabledMethod = getSetFlashEnabledMethod(iHardwareService);

  static
  {
    if (iHardwareService == null)
    {
      Log.v(TAG, "This device does supports control of a flashlight");
      return;
    }
    Log.v(TAG, "This device does not support control of a flashlight");
  }

  static void disableFlashlight()
  {
    setFlashlight(false);
  }

  static void enableFlashlight()
  {
    setFlashlight(true);
  }

  private static Object getHardwareService()
  {
    Class localClass1 = maybeForName("android.os.ServiceManager");
    if (localClass1 == null);
    Object localObject;
    Method localMethod2;
    do
    {
      Class localClass2;
      do
      {
        do
        {
          Method localMethod1;
          do
          {
            return null;
            localMethod1 = maybeGetMethod(localClass1, "getService", new Class[] { String.class });
          }
          while (localMethod1 == null);
          localObject = invoke(localMethod1, null, new Object[] { "hardware" });
        }
        while (localObject == null);
        localClass2 = maybeForName("android.os.IHardwareService$Stub");
      }
      while (localClass2 == null);
      localMethod2 = maybeGetMethod(localClass2, "asInterface", new Class[] { IBinder.class });
    }
    while (localMethod2 == null);
    return invoke(localMethod2, null, new Object[] { localObject });
  }

  private static Method getSetFlashEnabledMethod(Object paramObject)
  {
    if (paramObject == null)
      return null;
    Class localClass = paramObject.getClass();
    Class[] arrayOfClass = new Class[1];
    arrayOfClass[0] = Boolean.TYPE;
    return maybeGetMethod(localClass, "setFlashlightEnabled", arrayOfClass);
  }

  private static Object invoke(Method paramMethod, Object paramObject, Object[] paramArrayOfObject)
  {
    try
    {
      Object localObject = paramMethod.invoke(paramObject, paramArrayOfObject);
      return localObject;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      Log.w(TAG, "Unexpected error while invoking " + paramMethod, localIllegalAccessException);
      return null;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      Log.w(TAG, "Unexpected error while invoking " + paramMethod, localInvocationTargetException.getCause());
      return null;
    }
    catch (RuntimeException localRuntimeException)
    {
      Log.w(TAG, "Unexpected error while invoking " + paramMethod, localRuntimeException);
    }
    return null;
  }

  private static Class<?> maybeForName(String paramString)
  {
    try
    {
      Class localClass = Class.forName(paramString);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      return null;
    }
    catch (RuntimeException localRuntimeException)
    {
      Log.w(TAG, "Unexpected error while finding class " + paramString, localRuntimeException);
    }
    return null;
  }

  private static Method maybeGetMethod(Class<?> paramClass, String paramString, Class<?>[] paramArrayOfClass)
  {
    try
    {
      Method localMethod = paramClass.getMethod(paramString, paramArrayOfClass);
      return localMethod;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      return null;
    }
    catch (RuntimeException localRuntimeException)
    {
      Log.w(TAG, "Unexpected error while finding method " + paramString, localRuntimeException);
    }
    return null;
  }

  private static void setFlashlight(boolean paramBoolean)
  {
    if (iHardwareService != null)
    {
      Method localMethod = setFlashEnabledMethod;
      Object localObject = iHardwareService;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Boolean.valueOf(paramBoolean);
      invoke(localMethod, localObject, arrayOfObject);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     FlashlightManager
 * JD-Core Version:    0.6.2
 */
