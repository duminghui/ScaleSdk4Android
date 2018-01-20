package com.picooc.internet.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class RequestParams
{
  private static String ENCODING = "UTF-8";
  protected ConcurrentHashMap<String, FileWrapper> fileParams;
  protected ConcurrentHashMap<String, String> urlParams;
  protected ConcurrentHashMap<String, ArrayList<String>> urlParamsWithArray;

  public RequestParams()
  {
    init();
  }

  public RequestParams(String paramString1, String paramString2)
  {
    init();
    put(paramString1, paramString2);
  }

  public RequestParams(Map<String, String> paramMap)
  {
    init();
    Iterator localIterator = paramMap.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Entry localEntry = (Entry)localIterator.next();
      put((String)localEntry.getKey(), (String)localEntry.getValue());
    }
  }

  public RequestParams(Object[] paramArrayOfObject)
  {
    init();
    int i = paramArrayOfObject.length;
    if (i % 2 != 0)
      throw new IllegalArgumentException("Supplied arguments must be even");
    for (int j = 0; ; j += 2)
    {
      if (j >= i)
        return;
      put(String.valueOf(paramArrayOfObject[j]), String.valueOf(paramArrayOfObject[(j + 1)]));
    }
  }

  private void init()
  {
    this.urlParams = new ConcurrentHashMap();
    this.fileParams = new ConcurrentHashMap();
    this.urlParamsWithArray = new ConcurrentHashMap();
  }

  public HttpEntity getEntity()
  {
    if (!this.fileParams.isEmpty())
    {
      SimpleMultipartEntity localSimpleMultipartEntity = new SimpleMultipartEntity();
      Iterator localIterator1 = this.urlParams.entrySet().iterator();
      Iterator localIterator2;
      if (!localIterator1.hasNext())
        localIterator2 = this.urlParamsWithArray.entrySet().iterator();
      int i;
      int j;
      Iterator localIterator4;
      while (true)
      {
        if (!localIterator2.hasNext())
        {
          i = 0;
          j = -1 + this.fileParams.entrySet().size();
          localIterator4 = this.fileParams.entrySet().iterator();
          if (localIterator4.hasNext())
            break label212;
          return localSimpleMultipartEntity;
          Entry localEntry1 = (Entry)localIterator1.next();
          localSimpleMultipartEntity.addPart((String)localEntry1.getKey(), (String)localEntry1.getValue());
          break;
        }
        Entry localEntry2 = (Entry)localIterator2.next();
        Iterator localIterator3 = ((ArrayList)localEntry2.getValue()).iterator();
        while (localIterator3.hasNext())
        {
          String str = (String)localIterator3.next();
          localSimpleMultipartEntity.addPart((String)localEntry2.getKey(), str);
        }
      }
      label212: Entry localEntry3 = (Entry)localIterator4.next();
      FileWrapper localFileWrapper = (FileWrapper)localEntry3.getValue();
      boolean bool;
      if (localFileWrapper.inputStream != null)
      {
        if (i != j)
          break label299;
        bool = true;
        label254: if (localFileWrapper.contentType == null)
          break label305;
        localSimpleMultipartEntity.addPart((String)localEntry3.getKey(), localFileWrapper.getFileName(), localFileWrapper.inputStream, localFileWrapper.contentType, bool);
      }
      while (true)
      {
        i++;
        break;
        label299: bool = false;
        break label254;
        label305: localSimpleMultipartEntity.addPart((String)localEntry3.getKey(), localFileWrapper.getFileName(), localFileWrapper.inputStream, bool);
      }
    }
    try
    {
      UrlEncodedFormEntity localUrlEncodedFormEntity = new UrlEncodedFormEntity(getParamsList(), ENCODING);
      return localUrlEncodedFormEntity;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
    }
    return null;
  }

  protected String getParamString()
  {
    return URLEncodedUtils.format(getParamsList(), ENCODING);
  }

  protected List<BasicNameValuePair> getParamsList()
  {
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator1 = this.urlParams.entrySet().iterator();
    Iterator localIterator2;
    if (!localIterator1.hasNext())
      localIterator2 = this.urlParamsWithArray.entrySet().iterator();
    while (true)
    {
      if (!localIterator2.hasNext())
      {
        return localLinkedList;
        Entry localEntry1 = (Entry)localIterator1.next();
        localLinkedList.add(new BasicNameValuePair((String)localEntry1.getKey(), (String)localEntry1.getValue()));
        break;
      }
      Entry localEntry2 = (Entry)localIterator2.next();
      Iterator localIterator3 = ((ArrayList)localEntry2.getValue()).iterator();
      while (localIterator3.hasNext())
      {
        String str = (String)localIterator3.next();
        localLinkedList.add(new BasicNameValuePair((String)localEntry2.getKey(), str));
      }
    }
  }

  public void put(String paramString, File paramFile)
    throws FileNotFoundException
  {
    put(paramString, new FileInputStream(paramFile), paramFile.getName());
  }

  public void put(String paramString, InputStream paramInputStream)
  {
    put(paramString, paramInputStream, null);
  }

  public void put(String paramString1, InputStream paramInputStream, String paramString2)
  {
    put(paramString1, paramInputStream, paramString2, null);
  }

  public void put(String paramString1, InputStream paramInputStream, String paramString2, String paramString3)
  {
    if ((paramString1 != null) && (paramInputStream != null))
      this.fileParams.put(paramString1, new FileWrapper(paramInputStream, paramString2, paramString3));
  }

  public void put(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (paramString2 != null))
      this.urlParams.put(paramString1, paramString2);
  }

  public void put(String paramString, ArrayList<String> paramArrayList)
  {
    if ((paramString != null) && (paramArrayList != null))
      this.urlParamsWithArray.put(paramString, paramArrayList);
  }

  public void remove(String paramString)
  {
    this.urlParams.remove(paramString);
    this.fileParams.remove(paramString);
    this.urlParamsWithArray.remove(paramString);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator1 = this.urlParams.entrySet().iterator();
    Iterator localIterator2;
    label44: Iterator localIterator3;
    if (!localIterator1.hasNext())
    {
      localIterator2 = this.fileParams.entrySet().iterator();
      if (localIterator2.hasNext())
        break label145;
      localIterator3 = this.urlParamsWithArray.entrySet().iterator();
    }
    while (true)
    {
      if (!localIterator3.hasNext())
      {
        return localStringBuilder.toString();
        Entry localEntry1 = (Entry)localIterator1.next();
        if (localStringBuilder.length() > 0)
          localStringBuilder.append("&");
        localStringBuilder.append((String)localEntry1.getKey());
        localStringBuilder.append("=");
        localStringBuilder.append((String)localEntry1.getValue());
        break;
        label145: Entry localEntry2 = (Entry)localIterator2.next();
        if (localStringBuilder.length() > 0)
          localStringBuilder.append("&");
        localStringBuilder.append((String)localEntry2.getKey());
        localStringBuilder.append("=");
        localStringBuilder.append("FILE");
        break label44;
      }
      Entry localEntry3 = (Entry)localIterator3.next();
      if (localStringBuilder.length() > 0)
        localStringBuilder.append("&");
      ArrayList localArrayList = (ArrayList)localEntry3.getValue();
      Iterator localIterator4 = localArrayList.iterator();
      while (localIterator4.hasNext())
      {
        String str = (String)localIterator4.next();
        if (localArrayList.indexOf(str) != 0)
          localStringBuilder.append("&");
        localStringBuilder.append((String)localEntry3.getKey());
        localStringBuilder.append("=");
        localStringBuilder.append(str);
      }
    }
  }

  private static class FileWrapper
  {
    public String contentType;
    public String fileName;
    public InputStream inputStream;

    public FileWrapper(InputStream paramInputStream, String paramString1, String paramString2)
    {
      this.inputStream = paramInputStream;
      this.fileName = paramString1;
      this.contentType = paramString2;
    }

    public String getFileName()
    {
      if (this.fileName != null)
        return this.fileName;
      return "nofilename";
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     RequestParams
 * JD-Core Version:    0.6.2
 */
