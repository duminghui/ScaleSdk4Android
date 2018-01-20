package com.picooc.domain;

public class InvitationInfos
{
  private int flag;
  private int is_already_read;
  private String name;
  private String receive_from_email;
  private String receive_from_head_url;
  private String receive_from_name;
  private String receive_from_phone;
  private String receive_from_sex;
  private String receive_message;
  private String remote_uid;
  private String send_email;
  private String send_message;
  private String send_phone;
  private int send_status_code;
  private long time;
  private int tybe = 0;
  private String user_id;

  public int getFlag()
  {
    return this.flag;
  }

  public int getIs_already_read()
  {
    return this.is_already_read;
  }

  public String getName()
  {
    return this.name;
  }

  public String getReceive_from_email()
  {
    return this.receive_from_email;
  }

  public String getReceive_from_head_url()
  {
    return this.receive_from_head_url;
  }

  public String getReceive_from_name()
  {
    return this.receive_from_name;
  }

  public String getReceive_from_phone()
  {
    return this.receive_from_phone;
  }

  public boolean getReceive_from_sex()
  {
    return this.receive_from_sex.equals("1");
  }

  public String getReceive_message()
  {
    return this.receive_message;
  }

  public String getRemote_uid()
  {
    return this.remote_uid;
  }

  public String getSend_email()
  {
    return this.send_email;
  }

  public String getSend_message()
  {
    return this.send_message;
  }

  public String getSend_phone()
  {
    return this.send_phone;
  }

  public int getSend_status_code()
  {
    return this.send_status_code;
  }

  public long getTime()
  {
    return this.time;
  }

  public int getTybe()
  {
    return this.tybe;
  }

  public String getUser_id()
  {
    return this.user_id;
  }

  public void setFlag(int paramInt)
  {
    this.flag = paramInt;
  }

  public void setIs_already_read(int paramInt)
  {
    this.is_already_read = paramInt;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setReceive_from_email(String paramString)
  {
    this.receive_from_email = paramString;
  }

  public void setReceive_from_head_url(String paramString)
  {
    this.receive_from_head_url = paramString;
  }

  public void setReceive_from_name(String paramString)
  {
    this.receive_from_name = paramString;
  }

  public void setReceive_from_phone(String paramString)
  {
    this.receive_from_phone = paramString;
  }

  public void setReceive_from_sex(String paramString)
  {
    this.receive_from_sex = paramString;
  }

  public void setReceive_message(String paramString)
  {
    this.receive_message = paramString;
  }

  public void setRemote_uid(String paramString)
  {
    this.remote_uid = paramString;
  }

  public void setSend_email(String paramString)
  {
    this.send_email = paramString;
  }

  public void setSend_message(String paramString)
  {
    this.send_message = paramString;
  }

  public void setSend_phone(String paramString)
  {
    this.send_phone = paramString;
  }

  public void setSend_status_code(int paramInt)
  {
    this.send_status_code = paramInt;
  }

  public void setTime(long paramLong)
  {
    this.time = paramLong;
  }

  public void setTybe(int paramInt)
  {
    this.tybe = paramInt;
  }

  public void setUser_id(String paramString)
  {
    this.user_id = paramString;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     InvitationInfos
 * JD-Core Version:    0.6.2
 */
