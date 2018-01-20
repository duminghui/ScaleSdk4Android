package com.picooc.internet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FormFile
{
  private String contentType = "application/octet-stream";
  private byte[] data;
  private File file;
  private String filname;
  private InputStream inStream;
  private String parameterName;

  public FormFile(String paramString1, File paramFile, String paramString2, String paramString3)
  {
    this.filname = paramString1;
    this.parameterName = paramString2;
    this.file = paramFile;
    try
    {
      this.inStream = new FileInputStream(paramFile);
      if (paramString3 != null)
        this.contentType = paramString3;
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      while (true)
        localFileNotFoundException.printStackTrace();
    }
  }

  public FormFile(String paramString1, byte[] paramArrayOfByte, String paramString2, String paramString3)
  {
    this.data = paramArrayOfByte;
    this.filname = paramString1;
    this.parameterName = paramString2;
    if (paramString3 != null)
      this.contentType = paramString3;
  }

  public String getContentType()
  {
    return this.contentType;
  }

  public byte[] getData()
  {
    return this.data;
  }

  public File getFile()
  {
    return this.file;
  }

  public String getFilname()
  {
    return this.filname;
  }

  public InputStream getInStream()
  {
    return this.inStream;
  }

  public String getParameterName()
  {
    return this.parameterName;
  }

  public void setContentType(String paramString)
  {
    this.contentType = paramString;
  }

  public void setFilname(String paramString)
  {
    this.filname = paramString;
  }

  public void setParameterName(String paramString)
  {
    this.parameterName = paramString;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     FormFile
 * JD-Core Version:    0.6.2
 */
