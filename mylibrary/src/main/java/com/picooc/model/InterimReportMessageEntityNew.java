package com.picooc.model;

import java.io.Serializable;
import java.util.List;

public class InterimReportMessageEntityNew
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String content;
  private List<Object> ranges;
  private List<Object> subColors;
  private List<Object> subFonts;

  public String getContent()
  {
    return this.content;
  }

  public List<Object> getRanges()
  {
    return this.ranges;
  }

  public List<Object> getSubColors()
  {
    return this.subColors;
  }

  public List<Object> getSubFonts()
  {
    return this.subFonts;
  }

  public void setContent(String paramString)
  {
    this.content = paramString;
  }

  public void setRanges(List<Object> paramList)
  {
    this.ranges = paramList;
  }

  public void setSubColors(List<Object> paramList)
  {
    this.subColors = paramList;
  }

  public void setSubFonts(List<Object> paramList)
  {
    this.subFonts = paramList;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     InterimReportMessageEntityNew
 * JD-Core Version:    0.6.2
 */
