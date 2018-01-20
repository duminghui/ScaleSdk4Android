package com.picooc.domain;

import com.picooc.utils.DateUtils;
import com.picooc.utils.NumUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import org.achartengine.tools.ModUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class BodyIndex
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private float avg;
  private float basic_metabolism;
  private float bmi;
  private float bodyBottomFat;
  private float body_age;
  private float body_fat;
  private float bone_mass;
  private long conn_to_measure_time_diff;
  private int flag;
  private long id;
  private long id_in_server;
  private String mac;
  private float musde_race;
  private float protein_race;
  private long role_id;
  private long shake_to_conn_time_diff;
  private long time;
  private float viser_fat_level;
  private float water_race;
  private float weight;

  public static BodyIndex getAvgValueByArrayList(ArrayList<BodyIndex> paramArrayList)
  {
    if ((paramArrayList != null) && (paramArrayList.size() > 0))
    {
      BodyIndex localBodyIndex1 = (BodyIndex)paramArrayList.get(-1 + paramArrayList.size());
      localBodyIndex1.setProtein_race(localBodyIndex1.getMusde_race() - localBodyIndex1.getWater_race());
      localBodyIndex1.setTime(((BodyIndex)paramArrayList.get(0)).getTime());
      if (DateUtils.getHowManyDaysBetweenNewTimeStampAndOldTimeStamp(System.currentTimeMillis(), localBodyIndex1.getTime()) == 0L);
      float f1;
      float f2;
      float f3;
      float f4;
      float f5;
      float f6;
      float f7;
      int i;
      int j;
      int k;
      int m;
      do
      {
        return localBodyIndex1;
        f1 = 0.0F;
        f2 = 0.0F;
        f3 = 0.0F;
        f4 = 0.0F;
        f5 = 0.0F;
        f6 = 0.0F;
        f7 = 0.0F;
        i = 0;
        j = 0;
        k = paramArrayList.size();
        m = k;
      }
      while (k <= 1);
      Iterator localIterator = paramArrayList.iterator();
      if (!localIterator.hasNext())
      {
        if (k > 0)
          f1 /= k;
        if (m > 0)
        {
          f2 /= m;
          f3 /= m;
          f4 /= m;
          f5 /= m;
          f6 /= m;
          f7 /= m;
          i /= m;
          j /= m;
        }
        localBodyIndex1.setBmi(f3);
        localBodyIndex1.setBodyFat(f2);
        localBodyIndex1.setWeight(f1);
        localBodyIndex1.setWater_race(f4);
        localBodyIndex1.setMusde_race(f5);
        localBodyIndex1.setBone_mass(f6);
        localBodyIndex1.setBodyAge(i);
        localBodyIndex1.setViser_fat_level(j);
        localBodyIndex1.setBasic_metabolism(f7);
        localBodyIndex1.setProtein_race(localBodyIndex1.getMusde_race() - localBodyIndex1.getWater_race());
        return localBodyIndex1;
      }
      BodyIndex localBodyIndex2 = (BodyIndex)localIterator.next();
      f1 += localBodyIndex2.getWeight();
      if (localBodyIndex2.getWeight() <= 0.0F)
        k--;
      f2 += localBodyIndex2.getBodyFat();
      if (localBodyIndex2.getBodyFat() <= 0.0F)
        m--;
      while (true)
      {
        f3 += localBodyIndex2.getBmi();
        f4 += localBodyIndex2.getWater_race();
        f6 += localBodyIndex2.getBone_mass();
        i = (int)(i + localBodyIndex2.getBodyAge());
        j = (int)(j + localBodyIndex2.getViser_fat_level());
        break;
        f5 += localBodyIndex2.getMusde_race();
        f7 += localBodyIndex2.getBasic_metabolism();
      }
    }
    return null;
  }

  public static long getSerialversionuid()
  {
    return 1L;
  }

  public float getBasic_metabolism()
  {
    return this.basic_metabolism;
  }

  public float getBmi()
  {
    return this.bmi;
  }

  public float getBodyAge()
  {
    return this.body_age;
  }

  public float getBodyBottomFat()
  {
    return this.bodyBottomFat;
  }

  public float getBodyFat()
  {
    return this.body_fat;
  }

  public float getBone_mass()
  {
    return this.bone_mass;
  }

  public int getFlag()
  {
    return this.flag;
  }

  public long getId()
  {
    return this.id;
  }

  public long getId_in_server()
  {
    return this.id_in_server;
  }

  public String getMac()
  {
    return this.mac;
  }

  public float getMusde_race()
  {
    return this.musde_race;
  }

  public float getProtein_race()
  {
    this.protein_race = Math.abs(this.musde_race - this.water_race);
    return this.protein_race;
  }

  public long getRole_id()
  {
    return this.role_id;
  }

  public long getTime()
  {
    return this.time;
  }

  public float getViser_fat_level()
  {
    return this.viser_fat_level;
  }

  public float getWater_race()
  {
    return this.water_race;
  }

  public float getWeight()
  {
    return this.weight;
  }

  public float getavg()
  {
    return this.avg;
  }

  public void setBasic_metabolism(float paramFloat)
  {
    this.basic_metabolism = paramFloat;
  }

  public void setBmi(float paramFloat)
  {
    this.bmi = paramFloat;
  }

  public void setBmi(float paramFloat1, float paramFloat2)
  {
    this.bmi = NumUtils.round(paramFloat1 / (paramFloat2 * paramFloat2), 1, 4);
  }

  public void setBodyAge(float paramFloat)
  {
    this.body_age = paramFloat;
  }

  public void setBodyBottomFat(float paramFloat)
  {
    this.bodyBottomFat = paramFloat;
  }

  public void setBodyFat(float paramFloat)
  {
    this.body_fat = ModUtils.caclutSaveOnePoint(paramFloat);
  }

  public void setBone_mass(float paramFloat)
  {
    this.bone_mass = paramFloat;
  }

  public void setConn_to_measure_time_diff(long paramLong)
  {
    this.conn_to_measure_time_diff = paramLong;
  }

  public void setFlag(int paramInt)
  {
    this.flag = paramInt;
  }

  public void setId(long paramLong)
  {
    this.id = paramLong;
  }

  public void setId_in_server(long paramLong)
  {
    this.id_in_server = paramLong;
  }

  public void setMac(String paramString)
  {
    this.mac = paramString;
  }

  public void setMusde_race(float paramFloat)
  {
    if ((paramFloat > 0.0F) && (this.water_race > 0.0F))
      this.protein_race = Math.abs(paramFloat - this.water_race);
    this.musde_race = paramFloat;
  }

  public void setProtein_race(float paramFloat)
  {
    this.protein_race = paramFloat;
  }

  public void setRole_id(long paramLong)
  {
    this.role_id = paramLong;
  }

  public void setShake_to_conn_time_diff(long paramLong)
  {
    this.shake_to_conn_time_diff = paramLong;
  }

  public void setTime(long paramLong)
  {
    this.time = paramLong;
  }

  public void setViser_fat_level(float paramFloat)
  {
    this.viser_fat_level = paramFloat;
  }

  public void setWater_race(float paramFloat)
  {
    if ((this.musde_race > 0.0F) && (paramFloat > 0.0F))
      this.protein_race = Math.abs(this.musde_race - paramFloat);
    this.water_race = paramFloat;
  }

  public void setWeight(float paramFloat)
  {
    this.weight = ModUtils.caclutSaveOnePoint(paramFloat);
  }

  public void setavg(float paramFloat)
  {
    this.avg = paramFloat;
  }

  public JSONObject toJsonObject(long paramLong)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("body_fat", this.body_fat);
      localJSONObject.put("viseral_fat_level", this.viser_fat_level);
      localJSONObject.put("roleID", this.role_id);
      localJSONObject.put("time", this.time);
      localJSONObject.put("bone_mass", this.bone_mass);
      localJSONObject.put("bmi", this.bmi);
      localJSONObject.put("subcutaneous_fat", this.bodyBottomFat);
      localJSONObject.put("basic_metabolism", this.basic_metabolism);
      localJSONObject.put("Weight", this.weight);
      localJSONObject.put("userID", paramLong);
      localJSONObject.put("water_race", this.water_race);
      localJSONObject.put("muscle_race", this.musde_race);
      localJSONObject.put("body_age", this.body_age);
      localJSONObject.put("local_bid", this.id);
      localJSONObject.put("shake_to_conn_time_diff", this.shake_to_conn_time_diff);
      localJSONObject.put("conn_to_measure_time_diff", this.conn_to_measure_time_diff);
      localJSONObject.put("achievement", "");
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return localJSONObject;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BodyIndex
 * JD-Core Version:    0.6.2
 */
