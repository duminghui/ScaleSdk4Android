package com.picooc.domain;

import java.io.Serializable;

public class UserBin
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String baidu_id;
  private String dayima_id;
  private String email;
  private boolean has_password;
  private String jd_id;
  private String ly_id;
  private String phone_no;
  private String qq_id;
  private long role_id;
  private long time;
  private long user_id;
  private String weibo_id;

  public UserBin()
  {
  }

  public UserBin(long paramLong1, String paramString1, long paramLong2, long paramLong3, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    this.user_id = paramLong1;
    this.email = paramString1;
    this.time = paramLong2;
    this.role_id = paramLong3;
    this.weibo_id = paramString2;
    this.qq_id = paramString3;
    this.dayima_id = paramString4;
    this.phone_no = paramString5;
    this.baidu_id = paramString6;
  }

  public static long getSerialversionuid()
  {
    return 1L;
  }

  public String getBaidu_id()
  {
    return this.baidu_id;
  }

  public String getDayima_id()
  {
    return this.dayima_id;
  }

  public String getEmail()
  {
    return this.email;
  }

  public String getJd_id()
  {
    return this.jd_id;
  }

  public String getLy_id()
  {
    return this.ly_id;
  }

  public String getPhone_no()
  {
    return this.phone_no;
  }

  public String getQQ_id()
  {
    return this.qq_id;
  }

  public long getRole_id()
  {
    return this.role_id;
  }

  public long getTime()
  {
    return this.time;
  }

  public long getUser_id()
  {
    return this.user_id;
  }

  public String getWeibo_id()
  {
    return this.weibo_id;
  }

  public boolean isHas_password()
  {
    return this.has_password;
  }

  public void setBaidu_id(String paramString)
  {
    this.baidu_id = paramString;
  }

  public void setDayima_id(String paramString)
  {
    this.dayima_id = paramString;
  }

  public void setEmail(String paramString)
  {
    this.email = paramString;
  }

  public void setHas_password(int paramInt)
  {
    if (paramInt == 0);
    for (boolean bool = false; ; bool = true)
    {
      this.has_password = bool;
      return;
    }
  }

  public void setHas_password(boolean paramBoolean)
  {
    this.has_password = paramBoolean;
  }

  public void setJd_id(String paramString)
  {
    this.jd_id = paramString;
  }

  public void setLy_id(String paramString)
  {
    this.ly_id = paramString;
  }

  public void setPhone_no(String paramString)
  {
    this.phone_no = paramString;
  }

  public void setQQ_id(String paramString)
  {
    this.qq_id = paramString;
  }

  public void setRole_id(long paramLong)
  {
    this.role_id = paramLong;
  }

  public void setTime(long paramLong)
  {
    this.time = paramLong;
  }

  public void setUser_id(long paramLong)
  {
    this.user_id = paramLong;
  }

  public void setWeibo_id(String paramString)
  {
    this.weibo_id = paramString;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     UserBin
 * JD-Core Version:    0.6.2
 */
