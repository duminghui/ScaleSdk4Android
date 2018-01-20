package com.picooc.domain;

import com.picooc.utils.DateUtils;
import java.io.Serializable;

public class RoleBin
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int age;
  private String birthday;
  private long change_goal_weight_time;
  private String email;
  private int family_type;
  private float first_fat;
  private boolean first_use;
  private long first_use_time;
  private float first_weight;
  private float goal_fat;
  private float goal_weight;
  private String head_portrait_url;
  private float height;
  private boolean is_new_family;
  private long lastWeightTime;
  private String name;
  private String phone_no;
  private String remark_name;
  private long remote_user_id;
  private long role_id;
  private int sex;
  private long time;
  private long user_id;
  private float weight_change_target;

  public RoleBin()
  {
  }

  public RoleBin(RoleBin paramRoleBin)
  {
    this.name = paramRoleBin.getName();
    this.height = paramRoleBin.getHeight();
    this.sex = paramRoleBin.getSex();
    this.birthday = paramRoleBin.getBirthday();
    this.time = paramRoleBin.getTime();
    this.user_id = paramRoleBin.getUser_id();
    this.goal_weight = paramRoleBin.getGoal_weight();
    this.head_portrait_url = paramRoleBin.getHead_portrait_url();
    this.first_weight = paramRoleBin.getFirst_weight();
    this.goal_fat = paramRoleBin.getGoal_fat();
    this.first_fat = paramRoleBin.getFirst_fat();
    this.first_use_time = paramRoleBin.getFirst_use_time();
    this.change_goal_weight_time = paramRoleBin.getChange_goal_weight_time();
    this.weight_change_target = paramRoleBin.getWeight_change_target();
    this.age = paramRoleBin.getAge();
    this.role_id = paramRoleBin.getRole_id();
    this.family_type = paramRoleBin.getFamily_type();
    this.remark_name = paramRoleBin.getRemark_name();
    this.remote_user_id = paramRoleBin.getRemote_user_id();
    this.is_new_family = paramRoleBin.isIs_new_family();
    this.email = paramRoleBin.getEmail();
    this.phone_no = paramRoleBin.getPhone_no();
    this.first_use = paramRoleBin.getFirst_use();
  }

  public static long getSerialversionuid()
  {
    return 1L;
  }

  public int getAge()
  {
    return this.age;
  }

  public String getBirthday()
  {
    return this.birthday;
  }

  public long getChange_goal_weight_time()
  {
    return this.change_goal_weight_time;
  }

  public String getEmail()
  {
    return this.email;
  }

  public int getFamily_type()
  {
    return this.family_type;
  }

  public float getFirst_fat()
  {
    return this.first_fat;
  }

  public boolean getFirst_use()
  {
    return this.first_use;
  }

  public long getFirst_use_time()
  {
    return this.first_use_time;
  }

  public float getFirst_weight()
  {
    return this.first_weight;
  }

  public float getGoal_fat()
  {
    return this.goal_fat;
  }

  public float getGoal_weight()
  {
    return this.goal_weight;
  }

  public String getHead_portrait_url()
  {
    if ((this.head_portrait_url == null) || (!this.head_portrait_url.startsWith("http")))
      this.head_portrait_url = "";
    return this.head_portrait_url;
  }

  public float getHeight()
  {
    return this.height;
  }

  public long getLastWeightTime()
  {
    return this.lastWeightTime;
  }

  public String getName()
  {
    return this.name;
  }

  public String getPhone_no()
  {
    return this.phone_no;
  }

  public String getRemark_name()
  {
    return this.remark_name;
  }

  public long getRemote_user_id()
  {
    return this.remote_user_id;
  }

  public long getRole_id()
  {
    return this.role_id;
  }

  public int getSex()
  {
    return this.sex;
  }

  public long getTime()
  {
    return this.time;
  }

  public long getUser_id()
  {
    return this.user_id;
  }

  public float getWeight_change_target()
  {
    return this.weight_change_target;
  }

  public boolean isIs_new_family()
  {
    return this.is_new_family;
  }

  public void setAge(int paramInt)
  {
    this.age = paramInt;
  }

  public void setBirthday(String paramString)
  {
    this.birthday = paramString;
    this.age = DateUtils.getAge(paramString, "yyyyMMdd");
  }

  public void setChange_goal_weight_time(long paramLong)
  {
    this.change_goal_weight_time = paramLong;
  }

  public void setEmail(String paramString)
  {
    this.email = paramString;
  }

  public void setFamily_type(int paramInt)
  {
    this.family_type = paramInt;
  }

  public void setFirst_fat(float paramFloat)
  {
    this.first_fat = paramFloat;
  }

  public void setFirst_use(String paramString)
  {
    if (paramString.equals("true"))
      this.first_use = true;
    while (!paramString.equals("false"))
      return;
    this.first_use = false;
  }

  public void setFirst_use_time(long paramLong)
  {
    this.first_use_time = paramLong;
  }

  public void setFirst_weight(float paramFloat)
  {
    this.first_weight = paramFloat;
  }

  public void setGoal_fat(float paramFloat)
  {
    this.goal_fat = paramFloat;
  }

  public void setGoal_weight(float paramFloat)
  {
    if (paramFloat < 0.0F)
      paramFloat = 0.0F;
    this.goal_weight = paramFloat;
  }

  public void setHead_portrait_url(String paramString)
  {
    this.head_portrait_url = paramString;
  }

  public void setHeight(float paramFloat)
  {
    this.height = paramFloat;
  }

  public void setIs_new_family(int paramInt)
  {
    if (paramInt == 0);
    for (boolean bool = false; ; bool = true)
    {
      this.is_new_family = bool;
      return;
    }
  }

  public void setIs_new_family(boolean paramBoolean)
  {
    this.is_new_family = paramBoolean;
  }

  public void setLastWeightTime(long paramLong)
  {
    this.lastWeightTime = paramLong;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setPhone_no(String paramString)
  {
    this.phone_no = paramString;
  }

  public void setRemark_name(String paramString)
  {
    this.remark_name = paramString;
  }

  public void setRemote_user_id(long paramLong)
  {
    this.remote_user_id = paramLong;
  }

  public void setRole_id(long paramLong)
  {
    this.role_id = paramLong;
  }

  public void setSex(int paramInt)
  {
    this.sex = paramInt;
  }

  public void setTime(long paramLong)
  {
    this.time = paramLong;
  }

  public void setUser_id(long paramLong)
  {
    this.user_id = paramLong;
  }

  public void setWeight_change_target(float paramFloat)
  {
    this.weight_change_target = paramFloat;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     RoleBin
 * JD-Core Version:    0.6.2
 */
