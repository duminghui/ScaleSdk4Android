package com.picooc.camera;

import android.util.Log;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class CameraSizeUtil
{
  private static final Comparator CAMERA = new Comparator()
  {
    public int compare(Camera.Size paramAnonymousSize1, Camera.Size paramAnonymousSize2)
    {
      if (paramAnonymousSize1.width - paramAnonymousSize2.width < 0)
        return 1;
      if (paramAnonymousSize1.width == paramAnonymousSize2.width)
      {
        if (paramAnonymousSize1.height - paramAnonymousSize2.height < 0)
          return 0;
        if (paramAnonymousSize1.height == paramAnonymousSize2.height)
          return -1;
        return -2;
      }
      return -3;
    }
  };
  private static final boolean DEBUG = true;
  private static final String TAG = CameraSizeUtil.class.getSimpleName();

  private static Camera.Size getSizeByH(List<Camera.Size> paramList, Camera.Size paramSize, int paramInt1, int paramInt2)
  {
    int i = -1;
    int j = paramList.size();
    int k = 0;
    label21: Camera.Size localSize2;
    Camera.Size localSize3;
    if (k >= j)
    {
      Log.d(TAG, i + " : " + paramInt1);
      if ((i == paramInt1) || (i == -1))
        break label289;
      localSize2 = (Camera.Size)paramList.get(i);
      localSize3 = (Camera.Size)paramList.get(i - 1);
      Log.d(TAG, localSize2.width + ":" + localSize3.width);
      if (localSize2.width == localSize3.width)
        break label263;
    }
    label263: 
    while (localSize2.height - paramSize.height > localSize3.height - paramSize.height)
    {
      return localSize2;
      Camera.Size localSize1 = (Camera.Size)paramList.get(k);
      if (localSize1.width == paramSize.width)
      {
        if (localSize1.height >= paramInt2)
        {
          Log.d(TAG, localSize1.height + ":" + paramSize.height);
          Log.d(TAG, "(s.height >=defaultSize.height)-------------------->continue");
        }
      }
      else
      {
        k++;
        break;
      }
      i = k;
      Log.d(TAG, "(s.height < defaultSize.height)-------------------->continue" + i);
      break label21;
    }
    return localSize3;
    label289: return paramSize;
  }

  private static Camera.Size getSizeByIndex(List<Camera.Size> paramList, int paramInt1, int paramInt2, int paramInt3)
  {
    Log.d(TAG, "(getSizeByIndex)----->" + paramInt1 + ":" + paramInt2 + " : " + paramInt3);
    if (paramInt1 == 0)
      return (Camera.Size)paramList.get(paramInt1);
    Camera.Size localSize1 = (Camera.Size)paramList.get(paramInt1);
    Camera.Size localSize2 = (Camera.Size)paramList.get(paramInt1 - 1);
    Camera.Size localSize3;
    if (localSize1.width - paramInt2 > localSize2.width - paramInt2)
    {
      localSize3 = localSize1;
      if (localSize1.width - paramInt2 <= localSize2.width - paramInt2)
        break label180;
    }
    label180: for (int i = paramInt1; ; i = paramInt1 - 1)
    {
      Log.d(TAG, "(getSizeByIndex)----->" + localSize3.width + ":" + localSize3.height);
      return getSizeByH(paramList, localSize3, i, paramInt3);
      localSize3 = localSize2;
      break;
    }
  }

  public static Camera.Size getSizeByWH(List<Camera.Size> paramList, float paramFloat, Camera.Size paramSize)
  {
    Collections.sort(paramList, CAMERA);
    Log.d(TAG, "getSizeByWH Scale  w : h   =" + paramSize.width + ":" + paramSize.height);
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        Log.d(TAG, "near == null");
        Camera.Size localSize2 = null;
        if (0 == 0)
        {
          int i = (int)(paramFloat * paramSize.width);
          int j = (int)(paramFloat * paramSize.height);
          Log.d(TAG, "previewSize.width  =" + paramSize.width + "   previewSize.height= " + paramSize.height + "   screenScale =" + paramFloat);
          localSize2 = getSizeByWH(paramList, i, j);
          Log.d(TAG, localSize2.width + ":" + localSize2.height);
        }
        return localSize2;
      }
      Camera.Size localSize1 = (Camera.Size)localIterator.next();
      Log.d(TAG, localSize1.width + ":" + localSize1.height);
    }
  }

  public static Camera.Size getSizeByWH(List<Camera.Size> paramList, int paramInt1, int paramInt2)
  {
    if ((paramInt1 == 0) && (paramInt2 == 0))
      return null;
    Log.d(TAG, "getSizeByWH  [w : h]   =" + paramInt1 + ":" + paramInt2);
    Collections.sort(paramList, CAMERA);
    Iterator localIterator = paramList.iterator();
    int j;
    int k;
    if (!localIterator.hasNext())
    {
      int i = paramList.size();
      j = -1;
      k = 0;
      label81: if (k < i)
        break label189;
    }
    while (true)
    {
      Log.d(TAG, "near = " + null + "  okIndex  =" + j);
      if (j == -1)
        break label243;
      return getSizeByIndex(paramList, j, paramInt1, paramInt2);
      Camera.Size localSize1 = (Camera.Size)localIterator.next();
      Log.d(TAG, localSize1.width + ":" + localSize1.height);
      break;
      label189: Camera.Size localSize2 = (Camera.Size)paramList.get(k);
      if (localSize2.width > paramInt1)
      {
        k++;
        break label81;
      }
      if (localSize2.width == paramInt1)
        return getSizeByH(paramList, localSize2, k, paramInt2);
      j = k;
    }
    label243: return (Camera.Size)paramList.get(-1 + paramList.size());
  }

  public static Camera.Size getSizeByWH(List<Camera.Size> paramList1, List<Camera.Size> paramList2, int paramInt1, int paramInt2)
  {
    paramList1.retainAll(paramList2);
    if (paramList1.size() <= 0)
      throw new RuntimeException("您的机器不支持主流的相机尺寸");
    Log.d(TAG, "测试数据---------------------------------------start");
    Log.d(TAG, getSizeByWH(paramList1, 240, 320).width + ":" + getSizeByWH(paramList1, 240, 320).height);
    Log.d(TAG, getSizeByWH(paramList1, 320, 480).width + ":" + getSizeByWH(paramList1, 320, 480).height);
    Log.d(TAG, getSizeByWH(paramList1, 460, 640).width + ":" + getSizeByWH(paramList1, 460, 640).height);
    Log.d(TAG, getSizeByWH(paramList1, 480, 854).width + ":" + getSizeByWH(paramList1, 480, 854).height);
    Log.d(TAG, getSizeByWH(paramList1, 540, 960).width + ":" + getSizeByWH(paramList1, 540, 960).height);
    Log.d(TAG, getSizeByWH(paramList1, 540, 960).width + ":" + getSizeByWH(paramList1, 540, 960).height);
    Log.d(TAG, getSizeByWH(paramList1, 640, 320).width + ":" + getSizeByWH(paramList1, 640, 320).height);
    Log.d(TAG, getSizeByWH(paramList1, 650, 320).width + ":" + getSizeByWH(paramList1, 650, 320).height);
    Log.d(TAG, getSizeByWH(paramList1, 680, 320).width + ":" + getSizeByWH(paramList1, 680, 320).height);
    Log.d(TAG, getSizeByWH(paramList1, 700, 320).width + ":" + getSizeByWH(paramList1, 700, 320).height);
    Log.d(TAG, getSizeByWH(paramList1, 720, 320).width + ":" + getSizeByWH(paramList1, 720, 320).height);
    Log.d(TAG, getSizeByWH(paramList1, 970, 320).width + ":" + getSizeByWH(paramList1, 970, 320).height);
    Log.d(TAG, getSizeByWH(paramList1, 1290, 320).width + ":" + getSizeByWH(paramList1, 1290, 320).height);
    Log.d(TAG, getSizeByWH(paramList1, 2000, 320).width + ":" + getSizeByWH(paramList1, 2000, 320).height);
    Log.d(TAG, getSizeByWH(paramList1, 4000, 320).width + ":" + getSizeByWH(paramList1, 4000, 320).height);
    Log.d(TAG, "测试数据---------------------------------------end");
    return getSizeByWH(paramList1, paramInt1, paramInt2);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     CameraSizeUtil
 * JD-Core Version:    0.6.2
 */
