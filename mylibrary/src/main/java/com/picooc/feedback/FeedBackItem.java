package com.picooc.feedback;

public class FeedBackItem
{
  public static final int UP_EROR = 3;
  public static final int UP_LOADING = 1;
  public static final int UP_NO = 4;
  public static final int UP_OK = 2;
  public static final int UP_SERVER_ADD = 5;
  public int fromWhere;
  public int id;
  public String mes;
  public int serverID;
  public long time;
  public int uploadState = 4;
  public long userID;

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    FeedBackItem localFeedBackItem;
    do
    {
      return true;
      if (paramObject == null)
        return false;
      if (getClass() != paramObject.getClass())
        return false;
      localFeedBackItem = (FeedBackItem)paramObject;
      if (this.fromWhere != localFeedBackItem.fromWhere)
        return false;
      if (this.id != localFeedBackItem.id)
        return false;
      if (this.time != localFeedBackItem.time)
        return false;
    }
    while (this.userID == localFeedBackItem.userID);
    return false;
  }

  public int hashCode()
  {
    return 31 * (31 * (31 * (31 + this.fromWhere) + this.id) + (int)(this.time ^ this.time >>> 32)) + (int)(this.userID ^ this.userID >>> 32);
  }

  public void set(FeedBackItem paramFeedBackItem)
  {
    this.id = paramFeedBackItem.id;
    this.mes = paramFeedBackItem.mes;
    this.fromWhere = paramFeedBackItem.fromWhere;
    this.time = paramFeedBackItem.time;
    this.serverID = paramFeedBackItem.serverID;
    this.uploadState = paramFeedBackItem.uploadState;
    System.out.println(" set .uploadState = " + this.uploadState);
  }

  public String toString()
  {
    return "FeedBackItem [id=" + this.id + ", mes=" + this.mes + ", time=" + this.time + ", userID=" + this.userID + ", fromWhere=" + this.fromWhere + ", serverID=" + this.serverID + "]";
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     FeedBackItem
 * JD-Core Version:    0.6.2
 */
