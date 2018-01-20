package com.picooc.model;

public class FamilyContactsModel
{
  public String name;
  public String phoneNumber;

  public FamilyContactsModel(String paramString1, String paramString2)
  {
    this.name = paramString1;
    this.phoneNumber = paramString2;
  }

  public String getName()
  {
    return this.name;
  }

  public String getPhoneNumber()
  {
    return this.phoneNumber;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setPhoneNumber(String paramString)
  {
    this.phoneNumber = paramString;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     FamilyContactsModel
 * JD-Core Version:    0.6.2
 */
