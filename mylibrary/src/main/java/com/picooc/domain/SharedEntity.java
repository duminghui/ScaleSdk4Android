package com.picooc.domain;

import com.picooc.emun.SharedEntityTypeEmun;

public class SharedEntity
{
  private String imageName;
  private int level;
  private String message;
  private String name;
  private float persent;
  private SharedEntityTypeEmun type;

  public String getImageName()
  {
    return this.imageName;
  }

  public int getLevel()
  {
    return this.level;
  }

  public String getMessage()
  {
    return this.message;
  }

  public String getName()
  {
    return this.name;
  }

  public float getPersent()
  {
    return this.persent;
  }

  public SharedEntityTypeEmun getType()
  {
    return this.type;
  }

  public void setImageName(String paramString)
  {
    this.imageName = paramString;
  }

  public void setLevel(int paramInt)
  {
    this.level = paramInt;
  }

  public void setMessage(String paramString)
  {
    this.message = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setPersent(float paramFloat)
  {
    this.persent = paramFloat;
  }

  public void setType(SharedEntityTypeEmun paramSharedEntityTypeEmun)
  {
    this.type = paramSharedEntityTypeEmun;
    this.name = paramSharedEntityTypeEmun.getName();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SharedEntity
 * JD-Core Version:    0.6.2
 */
