package com.picooc.domain;

import java.io.Serializable;

public class FamilyContactDetailBin
  implements Serializable
{
  private String birthday;
  private String email;
  private String gender;
  private String headUrl;
  private String name;
  private String phone;
  private String remoteUserId;
  private boolean searchIsPhoneNo;

  public String getBirthday()
  {
    return this.birthday;
  }

  public String getEmail()
  {
    return this.email;
  }

  public String getGender()
  {
    return this.gender;
  }

  public String getHeadUrl()
  {
    return this.headUrl;
  }

  public String getName()
  {
    return this.name;
  }

  public String getPhone()
  {
    return this.phone;
  }

  public String getRemoteUserId()
  {
    return this.remoteUserId;
  }

  public boolean isSearchIsPhoneNo()
  {
    return this.searchIsPhoneNo;
  }

  public void setBirthday(String paramString)
  {
    this.birthday = paramString;
  }

  public void setEmail(String paramString)
  {
    this.email = paramString;
  }

  public void setGender(String paramString)
  {
    this.gender = paramString;
  }

  public void setHeadUrl(String paramString)
  {
    this.headUrl = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setPhone(String paramString)
  {
    this.phone = paramString;
  }

  public void setRemoteUserId(String paramString)
  {
    this.remoteUserId = paramString;
  }

  public void setSearchIsPhoneNo(boolean paramBoolean)
  {
    this.searchIsPhoneNo = paramBoolean;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     FamilyContactDetailBin
 * JD-Core Version:    0.6.2
 */
