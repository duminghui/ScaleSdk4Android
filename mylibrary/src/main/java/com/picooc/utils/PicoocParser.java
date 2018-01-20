package com.picooc.utils;

import android.content.Context;
import com.picooc.db.OperationDB;
import com.picooc.domain.FamilyContactsBin;
import com.picooc.domain.InvitationInfos;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PicoocParser
{
  public static List<FamilyContactsBin> parserDownLoadPhoneNub(JSONArray paramJSONArray)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (true)
    {
      if (i >= paramJSONArray.length())
        return localArrayList;
      try
      {
        FamilyContactsBin localFamilyContactsBin = new FamilyContactsBin();
        JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
        localFamilyContactsBin.setPhoneNumer(localJSONObject.getString("phoneNumer"));
        if (Integer.parseInt(localJSONObject.getString("isAlreadMyRole")) == 1)
          localFamilyContactsBin.setIsAlreadMyRole(true);
        while (true)
        {
          localFamilyContactsBin.setRoleID(localJSONObject.getString("roleID"));
          localFamilyContactsBin.setUserID(localJSONObject.getString("userID"));
          localFamilyContactsBin.setRegist(true);
          localArrayList.add(localFamilyContactsBin);
          break;
          localFamilyContactsBin.setIsAlreadMyRole(false);
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        i++;
      }
    }
  }

  public static void parserInvitInfo(JSONObject paramJSONObject, Context paramContext)
  {
    while (true)
    {
      int i;
      JSONArray localJSONArray2;
      int j;
      try
      {
        JSONArray localJSONArray1 = paramJSONObject.getJSONArray("my_receive_list");
        if (localJSONArray1.length() > 0)
        {
          i = 0;
          if (i < localJSONArray1.length());
        }
        else
        {
          localJSONArray2 = paramJSONObject.getJSONArray("my_send_list");
          if (localJSONArray2.length() <= 0)
            break label448;
          j = 0;
          if (j < localJSONArray2.length())
            break label242;
          return;
        }
        InvitationInfos localInvitationInfos2 = new InvitationInfos();
        JSONObject localJSONObject2 = localJSONArray1.getJSONObject(i);
        localInvitationInfos2.setReceive_from_sex(localJSONObject2.getString("receive_from_sex"));
        localInvitationInfos2.setFlag(0);
        localInvitationInfos2.setIs_already_read(0);
        localInvitationInfos2.setReceive_from_email(localJSONObject2.getString("receive_from_email"));
        localInvitationInfos2.setReceive_from_head_url(localJSONObject2.getString("receive_from_head_url"));
        localInvitationInfos2.setReceive_from_name(localJSONObject2.getString("receive_from_name"));
        localInvitationInfos2.setReceive_from_phone(localJSONObject2.getString("receive_from_phone"));
        localInvitationInfos2.setReceive_message(localJSONObject2.getString("post_script"));
        localInvitationInfos2.setRemote_uid(localJSONObject2.getString("remote_user_id"));
        localInvitationInfos2.setUser_id(localJSONObject2.getString("user_id"));
        localInvitationInfos2.setTime(DateUtils.changeStringToTimestamp(localJSONObject2.getString("update_time"), "yy-MM-dd HH:mm:ss"));
        if (OperationDB.remoteIdIsExist(paramContext, localInvitationInfos2.getUser_id(), localInvitationInfos2.getRemote_uid(), "0"))
          OperationDB.updateInvitation_infos(paramContext, localInvitationInfos2);
        else
          OperationDB.insertInvitation_infos(paramContext, localInvitationInfos2);
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return;
      }
      label242: InvitationInfos localInvitationInfos1 = new InvitationInfos();
      JSONObject localJSONObject1 = localJSONArray2.getJSONObject(j);
      localInvitationInfos1.setFlag(1);
      localInvitationInfos1.setReceive_from_name(localJSONObject1.getString("remoteName"));
      localInvitationInfos1.setSend_message(localJSONObject1.getString("send_message"));
      localInvitationInfos1.setReceive_from_sex(localJSONObject1.getString("remoteSex"));
      localInvitationInfos1.setSend_phone(localJSONObject1.getString("send_phone"));
      localInvitationInfos1.setSend_email(localJSONObject1.getString("send_email"));
      localInvitationInfos1.setReceive_from_head_url(localJSONObject1.getString("remoteHeadUrl"));
      localInvitationInfos1.setRemote_uid(localJSONObject1.getString("remote_user_id"));
      localInvitationInfos1.setUser_id(localJSONObject1.getString("user_id"));
      localInvitationInfos1.setTime(localJSONObject1.getLong("update_time"));
      localInvitationInfos1.setSend_status_code(localJSONObject1.getInt("send_status_code"));
      if (localInvitationInfos1.getSend_status_code() == 1)
        localInvitationInfos1.setIs_already_read(1);
      while (OperationDB.remoteIdIsExist(paramContext, localInvitationInfos1.getUser_id(), localInvitationInfos1.getRemote_uid(), "1"))
      {
        OperationDB.updateInvitation_infosParser(paramContext, localInvitationInfos1);
        break label455;
        localInvitationInfos1.setIs_already_read(0);
      }
      OperationDB.insertInvitation_infos(paramContext, localInvitationInfos1);
      break label455;
      label448: return;
      i++;
      continue;
      label455: j++;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PicoocParser
 * JD-Core Version:    0.6.2
 */
