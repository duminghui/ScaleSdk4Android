package com.picooc.model;

import java.io.Serializable;

public class DietAndNutritionEntity
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int brekkerOrLunchOrDinner;
  private int eat_or_not;
  private String foodTypeName;
  private String message;

  public DietAndNutritionEntity(String paramString1, int paramInt1, String paramString2, int paramInt2)
  {
    this.foodTypeName = paramString1;
    this.eat_or_not = paramInt1;
    this.message = paramString2;
    this.brekkerOrLunchOrDinner = paramInt2;
  }

  public int getBrekkerOrLunchOrDinner()
  {
    return this.brekkerOrLunchOrDinner;
  }

  public int getEat_or_not()
  {
    return this.eat_or_not;
  }

  public String getFoodTypeName()
  {
    return this.foodTypeName;
  }

  public String getMessage()
  {
    return this.message;
  }

  public void setBrekkerOrLunchOrDinner(int paramInt)
  {
    this.brekkerOrLunchOrDinner = paramInt;
  }

  public void setEat_or_not(int paramInt)
  {
    this.eat_or_not = paramInt;
  }

  public void setFoodTypeName(String paramString)
  {
    this.foodTypeName = paramString;
  }

  public void setMessage(String paramString)
  {
    this.message = paramString;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     DietAndNutritionEntity
 * JD-Core Version:    0.6.2
 */
