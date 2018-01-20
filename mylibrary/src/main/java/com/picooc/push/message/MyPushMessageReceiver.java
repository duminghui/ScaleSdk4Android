package com.picooc.push.message;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import com.baidu.frontia.api.FrontiaPushMessageReceiver;
import com.picooc.MyApplication;
import com.picooc.WelcomeActivity;
import com.picooc.db.OperationDB;
import com.picooc.feedback.LeftFeedback;
import com.picooc.internet.AsyncMessageUtils;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.PicoocParser;
import com.picooc.utils.SharedPreferenceUtils;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.achartengine.tools.ModUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class MyPushMessageReceiver extends FrontiaPushMessageReceiver
{
  private static final String TAG = "PushMessageReceiver";
  private static String mChannelId;
  private static String mUserId;
  int a = 0;
  private MyApplication app;
  private Context context;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      String str = localResponseEntity.getMethod();
      if (str.equals(HttpUtils.pUploadBaiduPushId))
        SharedPreferenceUtils.saveBaiduChanelid(MyPushMessageReceiver.this.context, MyPushMessageReceiver.mUserId, MyPushMessageReceiver.mChannelId);
      do
      {
        return;
        if (str.equals("getInvitationInformation"))
        {
          PicoocParser.parserInvitInfo(localResponseEntity.getResp(), MyPushMessageReceiver.this.context);
          Intent localIntent2 = new Intent();
          localIntent2.setAction("com.picooc.receive.push.refresh.ui");
          MyPushMessageReceiver.this.context.sendBroadcast(localIntent2);
          return;
        }
      }
      while (!str.equals("getRoles"));
      long l = ((Long)SharedPreferenceUtils.getValue(MyPushMessageReceiver.this.context.getApplicationContext(), "user-Info", "user_id", Long.class)).longValue();
      OperationDB.updateAllRolesAndRoleInfos(MyPushMessageReceiver.this.context, localResponseEntity.getResp(), l);
      if (MyPushMessageReceiver.this.roleID != 0L)
        AsyncMessageUtils.loadBodyIndexFromServer(MyPushMessageReceiver.this.context, MyPushMessageReceiver.this.roleID, true);
      Intent localIntent1 = new Intent();
      localIntent1.setAction("com.picooc.invitation.refresh.ui");
      MyApplication.isUploadPhoneNu = true;
      MyPushMessageReceiver.this.context.sendBroadcast(localIntent1);
    }
  };
  private long roleID = 0L;

  private void showFeedNotify(Context paramContext)
  {
    NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    localNotificationManager.cancelAll();
    Notification localNotification = new Notification();
    localNotification.icon = 2130838082;
    localNotification.tickerText = "亲,您有客服给你回复信息了！";
    localNotification.defaults = 1;
    localNotification.when = System.currentTimeMillis();
    localNotification.vibrate = new long[] { 1000L, 10L, 100L, 1000L };
    Intent localIntent1 = new Intent();
    localIntent1.setClass(paramContext, LeftFeedback.FeedBackBroadcastReceiver.class);
    localNotification.setLatestEventInfo(paramContext, "亲，请查看PICOOC意见反馈界面！", "", PendingIntent.getBroadcast(paramContext, 0, localIntent1, 0));
    localNotificationManager.notify(LeftFeedback.class.hashCode(), localNotification);
    System.out.println("xxxxxxxxxxxxxxxxxxxx");
    Intent localIntent2 = new Intent();
    localIntent2.setAction("com.picooc.feedback.refresh.Action");
    paramContext.sendBroadcast(localIntent2);
  }

  public void notificationMessage(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    final NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    localNotificationManager.cancelAll();
    Notification localNotification = new Notification(2130838082, paramString2, new Date().getTime());
    PendingIntent localPendingIntent = PendingIntent.getActivity(paramContext, 0, new Intent(paramContext, WelcomeActivity.class), 134217728);
    localNotification.flags = 16;
    localNotification.defaults = (0x1 | localNotification.defaults);
    localNotification.defaults = (0x2 | localNotification.defaults);
    localNotification.contentView = null;
    localNotification.contentIntent = localPendingIntent;
    if (!paramString3.equals(""))
      paramString3 = "附言：" + paramString3;
    localNotification.setLatestEventInfo(paramContext, paramString2, paramString3, localPendingIntent);
    localNotificationManager.notify(1, localNotification);
    if (ModUtils.isRunningForeground(paramContext))
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          localNotificationManager.cancelAll();
        }
      }
      , 3000L);
  }

  public void onBind(Context paramContext, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.app = ((MyApplication)paramContext.getApplicationContext());
    this.context = paramContext;
    mUserId = paramString2;
    mChannelId = paramString3;
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("绑定成功\n");
    localStringBuffer.append("errCode:" + paramInt);
    localStringBuffer.append("appid:" + paramString1 + "\n");
    localStringBuffer.append("userId:" + paramString2 + "\n");
    localStringBuffer.append("channelId:" + paramString3 + "\n");
    localStringBuffer.append("requestId" + paramString4 + "\n");
    Log.i("picooc", "com.picooc" + localStringBuffer.toString() + "---" + this.app.getCurrentUser());
    if ((this.app.getCurrentUser() != null) && (this.app.getCurrentUser().getUser_id() > 0L))
      if (SharedPreferenceUtils.getBaiduChanelId(paramContext).getString("baidu_user_id") != null)
        break label346;
    label346: for (int i = 1; ; i = 0)
    {
      if (i != 0)
      {
        RequestEntity localRequestEntity = new RequestEntity(HttpUtils.pUploadBaiduPushId, "5.2");
        localRequestEntity.addParam("user_id", Long.valueOf(this.app.getCurrentUser().getUser_id()));
        localRequestEntity.addParam("baidu_user_id", paramString2);
        localRequestEntity.addParam("baidu_channel_id", paramString3);
        localRequestEntity.addParam("platform", "android");
        HttpUtils.getJson(paramContext, localRequestEntity, this.httpHandler);
      }
      return;
    }
  }

  public void onDelTags(Context paramContext, int paramInt, List<String> paramList1, List<String> paramList2, String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("删除tag成功\n");
    localStringBuffer.append("errCode:" + paramInt);
    localStringBuffer.append("success tags:");
    Iterator localIterator1 = paramList1.iterator();
    Iterator localIterator2;
    if (!localIterator1.hasNext())
    {
      localStringBuffer.append("fail tags:");
      localIterator2 = paramList2.iterator();
    }
    while (true)
    {
      if (!localIterator2.hasNext())
      {
        localStringBuffer.append("requestId" + paramString + "\n");
        Log.d("PushMessageReceiver", localStringBuffer.toString());
        return;
        localStringBuffer.append((String)localIterator1.next() + "\n");
        break;
      }
      localStringBuffer.append((String)localIterator2.next() + "\n");
    }
  }

  public void onListTags(Context paramContext, int paramInt, List<String> paramList, String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("list tag成功\n");
    localStringBuffer.append("errCode:" + paramInt);
    localStringBuffer.append("tags:");
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localStringBuffer.append("requestId" + paramString + "\n");
        Log.d("PushMessageReceiver", localStringBuffer.toString());
        return;
      }
      localStringBuffer.append((String)localIterator.next() + "\n");
    }
  }

  public void onMessage(Context paramContext, String paramString1, String paramString2)
  {
    this.context = paramContext;
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("收到消息\n");
    localStringBuffer.append("内容是:" + paramString2 + "\n");
    localStringBuffer.append("tags:");
    localStringBuffer.append("message:" + paramString1 + "\n");
    Log.i("picooc", "==========================================================================com.picooc" + localStringBuffer.toString());
    this.app = ((MyApplication)paramContext.getApplicationContext());
    JSONObject localJSONObject;
    String str1;
    String str2;
    while (true)
    {
      try
      {
        localJSONObject = new JSONObject(paramString1);
        Log.i("picooc", "com.picooctitle==" + localJSONObject.getString("title") + " ---description===" + localJSONObject.getString("description"));
        str1 = localJSONObject.getString("method");
        str2 = localJSONObject.getString("badge");
        Log.i("picooc", "-------------method==" + str1);
        if (!str1.equals("synchronizeInvitation"))
          break;
        if (str2.equals("1"))
        {
          AsyncMessageUtils.loadRoleAndRoleInfosFromServer(paramContext, this.app.getCurrentUser().getUser_id(), this.httpHandler);
          this.roleID = localJSONObject.getLong("num");
          notificationMessage(paramContext, paramString2, localJSONObject.getString("title"), localJSONObject.getString("description"));
          return;
        }
        if (str2.equals("2"))
        {
          Log.i("picooc", "-------------updateRecieveAndSend");
          OperationDB.updateBackSendMessage(paramContext, this.app.getCurrentUser().getUser_id(), localJSONObject.getString("num"));
          Intent localIntent3 = new Intent();
          localIntent3.setAction("com.picooc.receive.push.refresh.ui");
          paramContext.sendBroadcast(localIntent3);
          continue;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return;
      }
      updateRecieveAndSend(paramContext);
    }
    if (str1.equals("deleteTodayBodyIndex"))
    {
      if (str2.equals("0"))
      {
        long l2 = localJSONObject.getLong("num");
        if ((this.app != null) && (this.app.getTodayBody() != null) && (l2 == this.app.getTodayBody().getId_in_server()))
        {
          OperationDB.deleteBodyIndeIn_idSever(paramContext, l2);
          Intent localIntent1 = new Intent();
          localIntent1.setAction("com.picooc.sync.delete.today.weight.refresh.ui");
          paramContext.sendBroadcast(localIntent1);
          Intent localIntent2 = new Intent();
          localIntent2.setAction("com.picooc.latin.weight.success");
          paramContext.sendBroadcast(localIntent2);
          return;
        }
        OperationDB.deleteBodyIndeIn_idSever(paramContext, l2);
      }
    }
    else
    {
      if (str1.equals("refreshBodyIndexData"))
      {
        Object localObject = localJSONObject.get("num");
        long l1 = 0L;
        if (!localObject.equals(""))
          l1 = Long.parseLong(localObject.toString());
        if ((this.app != null) && (this.app.getMainRole() != null) && (l1 != this.app.getMainRole().getRole_id()))
          notificationMessage(paramContext, paramString2, localJSONObject.getString("title"), localJSONObject.getString("description"));
        AsyncMessageUtils.loadBodyIndexFromServer(paramContext, l1, true);
        return;
      }
      if ((str1.equals("getFeedback")) && (!localJSONObject.getString("description").startsWith("谢谢反馈")))
        showFeedNotify(paramContext);
    }
  }

  public void onNotificationClicked(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("通知被点击\n");
    localStringBuffer.append("title:" + paramString1 + "\n");
    localStringBuffer.append("description:" + paramString2);
    localStringBuffer.append("customContentString:" + paramString3 + "\n");
    Log.i("picooc", localStringBuffer.toString());
  }

  public void onSetTags(Context paramContext, int paramInt, List<String> paramList1, List<String> paramList2, String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("设置tag成功\n");
    localStringBuffer.append("errCode:" + paramInt);
    localStringBuffer.append("success tags:");
    Iterator localIterator1 = paramList1.iterator();
    Iterator localIterator2;
    if (!localIterator1.hasNext())
    {
      localStringBuffer.append("fail tags:");
      localIterator2 = paramList2.iterator();
    }
    while (true)
    {
      if (!localIterator2.hasNext())
      {
        localStringBuffer.append("requestId" + paramString + "\n");
        Log.d("PushMessageReceiver", localStringBuffer.toString());
        return;
        localStringBuffer.append((String)localIterator1.next() + "\n");
        break;
      }
      localStringBuffer.append((String)localIterator2.next() + "\n");
    }
  }

  public void onUnbind(Context paramContext, int paramInt, String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("解绑成功\n");
    localStringBuffer.append("errCode:" + paramInt);
    localStringBuffer.append("requestId" + paramString + "\n");
    Log.i("picooc", "com.picooc" + localStringBuffer.toString());
  }

  public void updateRecieveAndSend(Context paramContext)
  {
    if ((this.app.getCurrentUser() != null) && (this.app.getCurrentUser().getUser_id() > 0L))
    {
      RequestEntity localRequestEntity = new RequestEntity("getInvitationInformation", "5.2");
      localRequestEntity.addParam("user_id", Long.valueOf(this.app.getCurrentUser().getUser_id()));
      localRequestEntity.addParam("time", Long.valueOf(ModUtils.toTime(OperationDB.selectMastTime(paramContext, this.app.getCurrentUser().getUser_id()))));
      HttpUtils.getJson(paramContext, localRequestEntity, this.httpHandler);
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     MyPushMessageReceiver
 * JD-Core Version:    0.6.2
 */
