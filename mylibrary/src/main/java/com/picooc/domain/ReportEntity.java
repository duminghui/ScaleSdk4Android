package com.picooc.domain;

import java.io.Serializable;

public class ReportEntity
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private boolean isSubHealth;
  private String[] messages;
  private float num;
  private float num2;
  private String numStr;
  private float[] regionArray;
  private String reportName;
  private int reportType;
  private boolean standardOrNot;
  private String state;
  private int stateCode;
  private String subHealthMsg;
  private int weightAnchorFlag;
  private int weightState;

  public Boolean getIsSubHealth()
  {
    return Boolean.valueOf(this.isSubHealth);
  }

  public String[] getMessages()
  {
    return this.messages;
  }

  public float getNum()
  {
    return this.num;
  }

  public float getNum2()
  {
    return this.num2;
  }

  public String getNumStr()
  {
    return this.numStr;
  }

  public float[] getRegionArray()
  {
    return this.regionArray;
  }

  public String getReportName()
  {
    return this.reportName;
  }

  public int getReportType()
  {
    return this.reportType;
  }

  public Boolean getStandardOrNot()
  {
    return Boolean.valueOf(this.standardOrNot);
  }

  public String getState()
  {
    return this.state;
  }

  public int getStateCode()
  {
    return this.stateCode;
  }

  public String getSubHealthMsg()
  {
    return this.subHealthMsg;
  }

  public int getWeightAnchorFlag()
  {
    return this.weightAnchorFlag;
  }

  public int getWeightState()
  {
    return this.weightState;
  }

  public void setIsSubHealth(Boolean paramBoolean)
  {
    this.isSubHealth = paramBoolean.booleanValue();
  }

  public void setMessages(String[] paramArrayOfString)
  {
    this.messages = paramArrayOfString;
  }

  public void setNum(float paramFloat)
  {
    this.num = paramFloat;
  }

  public void setNum2(float paramFloat)
  {
    this.num2 = paramFloat;
  }

  public void setNumStr(String paramString)
  {
    this.numStr = paramString;
  }

  public void setRegionArray(float[] paramArrayOfFloat)
  {
    this.regionArray = paramArrayOfFloat;
  }

  public void setReportName(String paramString)
  {
    this.reportName = paramString;
  }

  public void setReportType(int paramInt)
  {
    this.reportType = paramInt;
  }

  public void setStandardOrNot(Boolean paramBoolean)
  {
    this.standardOrNot = paramBoolean.booleanValue();
  }

  public void setState(String paramString)
  {
    this.state = paramString;
  }

  public void setStateCode(int paramInt)
  {
    this.stateCode = paramInt;
  }

  public void setSubHealthMsg(String paramString)
  {
    this.subHealthMsg = paramString;
  }

  public void setWeightAnchorFlag(int paramInt)
  {
    this.weightAnchorFlag = paramInt;
  }

  public void setWeightState(int paramInt)
  {
    this.weightState = paramInt;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ReportEntity
 * JD-Core Version:    0.6.2
 */
