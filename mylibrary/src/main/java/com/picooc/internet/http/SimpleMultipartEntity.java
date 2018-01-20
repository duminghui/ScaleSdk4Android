package com.picooc.internet.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

class SimpleMultipartEntity
  implements HttpEntity
{
  private static final char[] MULTIPART_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
  private String boundary = null;
  boolean isSetFirst = false;
  boolean isSetLast = false;
  ByteArrayOutputStream out = new ByteArrayOutputStream();

  public SimpleMultipartEntity()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Random localRandom = new Random();
    for (int i = 0; ; i++)
    {
      if (i >= 30)
      {
        this.boundary = localStringBuffer.toString();
        return;
      }
      localStringBuffer.append(MULTIPART_CHARS[localRandom.nextInt(MULTIPART_CHARS.length)]);
    }
  }

  public void addPart(String paramString, File paramFile, boolean paramBoolean)
  {
    try
    {
      addPart(paramString, paramFile.getName(), new FileInputStream(paramFile), paramBoolean);
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
    }
  }

  public void addPart(String paramString1, String paramString2)
  {
    writeFirstBoundaryIfNeeds();
    try
    {
      this.out.write(("Content-Disposition: form-data; name=\"" + paramString1 + "\"\r\n\r\n").getBytes());
      this.out.write(paramString2.getBytes());
      this.out.write(("\r\n--" + this.boundary + "\r\n").getBytes());
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }

  // ERROR //
  public void addPart(String paramString1, String paramString2, InputStream paramInputStream, String paramString3, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 85	com/picooc/internet/http/SimpleMultipartEntity:writeFirstBoundaryIfNeeds	()V
    //   4: new 87	java/lang/StringBuilder
    //   7: dup
    //   8: ldc 114
    //   10: invokespecial 92	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   13: aload 4
    //   15: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: ldc 110
    //   20: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   26: astore 10
    //   28: aload_0
    //   29: getfield 37	com/picooc/internet/http/SimpleMultipartEntity:out	Ljava/io/ByteArrayOutputStream;
    //   32: new 87	java/lang/StringBuilder
    //   35: dup
    //   36: ldc 89
    //   38: invokespecial 92	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   41: aload_1
    //   42: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: ldc 116
    //   47: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: aload_2
    //   51: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: ldc 118
    //   56: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   62: invokevirtual 102	java/lang/String:getBytes	()[B
    //   65: invokevirtual 106	java/io/ByteArrayOutputStream:write	([B)V
    //   68: aload_0
    //   69: getfield 37	com/picooc/internet/http/SimpleMultipartEntity:out	Ljava/io/ByteArrayOutputStream;
    //   72: aload 10
    //   74: invokevirtual 102	java/lang/String:getBytes	()[B
    //   77: invokevirtual 106	java/io/ByteArrayOutputStream:write	([B)V
    //   80: aload_0
    //   81: getfield 37	com/picooc/internet/http/SimpleMultipartEntity:out	Ljava/io/ByteArrayOutputStream;
    //   84: ldc 120
    //   86: invokevirtual 102	java/lang/String:getBytes	()[B
    //   89: invokevirtual 106	java/io/ByteArrayOutputStream:write	([B)V
    //   92: sipush 4096
    //   95: newarray byte
    //   97: astore 11
    //   99: aload_3
    //   100: aload 11
    //   102: invokevirtual 126	java/io/InputStream:read	([B)I
    //   105: istore 12
    //   107: iload 12
    //   109: iconst_m1
    //   110: if_icmpne +54 -> 164
    //   113: iload 5
    //   115: ifne +37 -> 152
    //   118: aload_0
    //   119: getfield 37	com/picooc/internet/http/SimpleMultipartEntity:out	Ljava/io/ByteArrayOutputStream;
    //   122: new 87	java/lang/StringBuilder
    //   125: dup
    //   126: ldc 108
    //   128: invokespecial 92	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   131: aload_0
    //   132: getfield 32	com/picooc/internet/http/SimpleMultipartEntity:boundary	Ljava/lang/String;
    //   135: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: ldc 110
    //   140: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   146: invokevirtual 102	java/lang/String:getBytes	()[B
    //   149: invokevirtual 106	java/io/ByteArrayOutputStream:write	([B)V
    //   152: aload_0
    //   153: getfield 37	com/picooc/internet/http/SimpleMultipartEntity:out	Ljava/io/ByteArrayOutputStream;
    //   156: invokevirtual 129	java/io/ByteArrayOutputStream:flush	()V
    //   159: aload_3
    //   160: invokevirtual 132	java/io/InputStream:close	()V
    //   163: return
    //   164: aload_0
    //   165: getfield 37	com/picooc/internet/http/SimpleMultipartEntity:out	Ljava/io/ByteArrayOutputStream;
    //   168: aload 11
    //   170: iconst_0
    //   171: iload 12
    //   173: invokevirtual 135	java/io/ByteArrayOutputStream:write	([BII)V
    //   176: goto -77 -> 99
    //   179: astore 8
    //   181: aload 8
    //   183: invokevirtual 111	java/io/IOException:printStackTrace	()V
    //   186: aload_3
    //   187: invokevirtual 132	java/io/InputStream:close	()V
    //   190: return
    //   191: astore 9
    //   193: aload 9
    //   195: invokevirtual 111	java/io/IOException:printStackTrace	()V
    //   198: return
    //   199: astore 6
    //   201: aload_3
    //   202: invokevirtual 132	java/io/InputStream:close	()V
    //   205: aload 6
    //   207: athrow
    //   208: astore 7
    //   210: aload 7
    //   212: invokevirtual 111	java/io/IOException:printStackTrace	()V
    //   215: goto -10 -> 205
    //   218: astore 13
    //   220: aload 13
    //   222: invokevirtual 111	java/io/IOException:printStackTrace	()V
    //   225: return
    //
    // Exception table:
    //   from	to	target	type
    //   4	99	179	java/io/IOException
    //   99	107	179	java/io/IOException
    //   118	152	179	java/io/IOException
    //   152	159	179	java/io/IOException
    //   164	176	179	java/io/IOException
    //   186	190	191	java/io/IOException
    //   4	99	199	finally
    //   99	107	199	finally
    //   118	152	199	finally
    //   152	159	199	finally
    //   164	176	199	finally
    //   181	186	199	finally
    //   201	205	208	java/io/IOException
    //   159	163	218	java/io/IOException
  }

  public void addPart(String paramString1, String paramString2, InputStream paramInputStream, boolean paramBoolean)
  {
    addPart(paramString1, paramString2, paramInputStream, "application/octet-stream", paramBoolean);
  }

  public void consumeContent()
    throws IOException, UnsupportedOperationException
  {
    if (isStreaming())
      throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
  }

  public InputStream getContent()
    throws IOException, UnsupportedOperationException
  {
    return new ByteArrayInputStream(this.out.toByteArray());
  }

  public Header getContentEncoding()
  {
    return null;
  }

  public long getContentLength()
  {
    writeLastBoundaryIfNeeds();
    return this.out.toByteArray().length;
  }

  public Header getContentType()
  {
    return new BasicHeader("Content-Type", "multipart/form-data; boundary=" + this.boundary);
  }

  public boolean isChunked()
  {
    return false;
  }

  public boolean isRepeatable()
  {
    return false;
  }

  public boolean isStreaming()
  {
    return false;
  }

  public void writeFirstBoundaryIfNeeds()
  {
    if (!this.isSetFirst);
    try
    {
      this.out.write(("--" + this.boundary + "\r\n").getBytes());
      this.isSetFirst = true;
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public void writeLastBoundaryIfNeeds()
  {
    if (this.isSetLast)
      return;
    try
    {
      this.out.write(("\r\n--" + this.boundary + "--\r\n").getBytes());
      this.isSetLast = true;
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.write(this.out.toByteArray());
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SimpleMultipartEntity
 * JD-Core Version:    0.6.2
 */
