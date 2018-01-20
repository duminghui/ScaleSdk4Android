package com.picooc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.util.Log;
import com.picooc.MyApplication;
import com.picooc.domain.BodyIndex;
import com.picooc.domain.EveryMeEntity;
import com.picooc.domain.FamilyContactsBin;
import com.picooc.domain.InvitationInfos;
import com.picooc.domain.ReportEntity;
import com.picooc.domain.RoleBin;
import com.picooc.domain.UserBin;
import com.picooc.emun.SharedEntityTypeEmun;
import com.picooc.feedback.FeedBackItem;
import com.picooc.internet.AsyncMessageUtils;
import com.picooc.model.BodyTypeEnum;
import com.picooc.model.DietAndNutritionEntity;
import com.picooc.model.SportPlanEnum;
import com.picooc.model.SportPlanModel;
import com.picooc.utils.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OperationDB
{
  static SQLiteDatabase db;

  public static long addMainRole(Context paramContext, RoleBin paramRoleBin, long paramLong)
  {
    long l = insertRoleDB(paramContext, paramRoleBin);
    if ((l > 0L) && (l > 0L))
    {
      db = DBHelper.getInstance(paramContext).openDatabase();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("role_id", Long.valueOf(paramRoleBin.getRole_id()));
      SQLiteDatabase localSQLiteDatabase = db;
      String[] arrayOfString = new String[1];
      arrayOfString[0] = paramRoleBin.getUser_id();
      localSQLiteDatabase.update("Users", localContentValues, "user_id = ?", arrayOfString);
      db.close();
    }
    return l;
  }

  public static int booleantoInt(boolean paramBoolean)
  {
    if (paramBoolean)
      return 1;
    return 0;
  }

  public static void deleteAllRoles(Context paramContext)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    db.execSQL("delete from role");
    db.close();
  }

  public static void deleteBodyIndeByRoleId(Context paramContext, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "delete from BodyIndex where role_id=" + paramLong;
    db.execSQL(str);
  }

  public static void deleteBodyIndeDB(Context paramContext, long paramLong, Long paramLong1)
  {
    Long localLong1 = Long.valueOf(paramLong1.longValue() - 100L);
    Long localLong2 = Long.valueOf(100L + paramLong1.longValue());
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "delete from BodyIndex where role_id=" + paramLong + " and time between " + localLong1 + " and " + localLong2;
    Log.i("picooc", "sql==" + str);
    db.execSQL(str);
  }

  public static void deleteBodyIndeIn_idSever(Context paramContext, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "delete from BodyIndex where id_in_server=" + paramLong;
    db.execSQL(str);
  }

  public static void deleteDietAndNutritionPrincipleDataById(Context paramContext, int paramInt)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "delete from DietAndNutritionPrinciple where id=" + paramInt;
    db.execSQL(str);
    db.close();
  }

  public static void deleteRecevieMessage(Context paramContext, String paramString, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "delete from invitation_infos  where user_id=" + paramString + " and flag=0 and remote_uid=" + paramLong;
    db.execSQL(str);
    db.close();
  }

  public static void deleteRoleByRoleId(Context paramContext, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str1 = "delete from role_infos where role_id=" + paramLong;
    db.execSQL(str1);
    String str2 = "delete from role where id=" + paramLong;
    db.execSQL(str2);
    String str3 = "delete from BodyIndex where role_id=" + paramLong;
    db.execSQL(str3);
  }

  public static void deleteSendMessage(Context paramContext, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "delete from invitation_infos  where send_status_code=1 or send_status_code=2 and user_id=" + paramLong + " and flag=1";
    db.execSQL(str);
    db.close();
  }

  private static HashMap<String, ArrayList<Object>> getDaySportMessageAndHelpBySex(int paramInt, BodyTypeEnum paramBodyTypeEnum, SportPlanEnum paramSportPlanEnum, SQLiteDatabase paramSQLiteDatabase)
  {
    Object localObject = null;
    Cursor localCursor = paramSQLiteDatabase.rawQuery("select * from sportPlan_message where sex = " + paramInt + " and body_type = " + paramBodyTypeEnum.getIndex() + " and sport_type = " + paramSportPlanEnum.getIndex(), null);
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    if (!localCursor.moveToNext())
    {
      localArrayList1 = new ArrayList();
      localArrayList2 = new ArrayList();
      switch ($SWITCH_TABLE$com$picooc$model$SportPlanEnum()[paramSportPlanEnum.ordinal()])
      {
      default:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("messages", localObject);
      localHashMap.put("helpToAdd", localArrayList2);
      localHashMap.put("helpToReduce", localArrayList1);
      return localHashMap;
      if (localObject == null)
        localObject = new ArrayList();
      ((ArrayList)localObject).add(localCursor.getString(localCursor.getColumnIndex("message")));
      break;
      localArrayList2.add(Integer.valueOf(10));
      continue;
      localArrayList1.add(Integer.valueOf(4));
      localArrayList1.add(Integer.valueOf(2));
      localArrayList1.add(Integer.valueOf(5));
      localArrayList2.add(Integer.valueOf(10));
      localArrayList2.add(Integer.valueOf(6));
      localArrayList2.add(Integer.valueOf(3));
      localArrayList2.add(Integer.valueOf(8));
      continue;
      localArrayList2.add(Integer.valueOf(10));
      localArrayList2.add(Integer.valueOf(1));
      localArrayList2.add(Integer.valueOf(6));
      localArrayList2.add(Integer.valueOf(8));
    }
  }

  public static ArrayList<DietAndNutritionEntity> getDietAndNutritionByBodyTypeAndSex(Context paramContext, BodyTypeEnum paramBodyTypeEnum, int paramInt)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "select * from DietAndNutritionNew where sex = " + paramInt + " and bodyType = " + paramBodyTypeEnum.getIndex();
    Cursor localCursor = db.rawQuery(str, null);
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        db.close();
        return localArrayList;
      }
      localArrayList.add(new DietAndNutritionEntity(localCursor.getString(localCursor.getColumnIndex("foodType")), localCursor.getInt(localCursor.getColumnIndex("eatOrNot")), "", 1));
    }
  }

  public static long getLastFeedBackSyncTime(Context paramContext, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    System.out.println("selectAll =" + db);
    String str1 = "SELECT time,message from FeedbackMessage WHERE fromUserID=" + paramLong + " ORDER BY id desc LIMIT 1";
    Cursor localCursor = db.rawQuery(str1, null);
    long l = 0L;
    for (String str2 = ""; ; str2 = localCursor.getString(localCursor.getColumnIndex("message")))
    {
      if (!localCursor.moveToNext())
      {
        System.out.println("time = " + str2);
        return l;
      }
      l = localCursor.getLong(localCursor.getColumnIndex("time"));
    }
  }

  public static ArrayList<DietAndNutritionEntity> getPrincipleWithBodyType(Context paramContext, BodyTypeEnum paramBodyTypeEnum, int paramInt)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    ArrayList localArrayList = new ArrayList();
    String str1 = "select * from DietAndNutritionPrinciple where sex = " + paramInt + " and bodyType = " + paramBodyTypeEnum.getIndex() + " order by mustOrOptional asc";
    Cursor localCursor = db.rawQuery(str1, null);
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        db.close();
        return localArrayList;
      }
      String str2 = localCursor.getString(localCursor.getColumnIndex("message"));
      localArrayList.add(new DietAndNutritionEntity("", localCursor.getInt(localCursor.getColumnIndex("mustOrOptional")), str2, 1));
    }
  }

  public static HashMap<String, Object> getSportPlanByWeekNo(Context paramContext, int paramInt1, int paramInt2, BodyTypeEnum paramBodyTypeEnum, ArrayList<ReportEntity> paramArrayList, SportPlanModel paramSportPlanModel)
  {
    HashMap localHashMap1 = new HashMap();
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str1 = "select * from sportPlan where sex = " + paramInt2 + " and body_type = " + paramBodyTypeEnum.getIndex() + " and week_no <= " + paramInt1 + " order by week_no desc LIMIT 1";
    Cursor localCursor = db.rawQuery(str1, null);
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        db.close();
        localHashMap1.put("daySport", localArrayList2);
        localHashMap1.put("weekSport", localArrayList1);
        return localHashMap1;
      }
      int i = localCursor.getInt(localCursor.getColumnIndex("dqd_day_sport_count"));
      if (i > 0)
      {
        String str6 = localCursor.getString(localCursor.getColumnIndex("dqd_week_sport_cycle"));
        String str7 = localCursor.getString(localCursor.getColumnIndex("dqd_day_during_time"));
        paramSportPlanModel.getClass();
        SportPlanModel.WeekPlan localWeekPlan3 = new SportPlanModel.WeekPlan(paramSportPlanModel, SportPlanEnum.DQD, str6);
        localArrayList1.add(localWeekPlan3);
        HashMap localHashMap4 = getDaySportMessageAndHelpBySex(paramInt2, paramBodyTypeEnum, SportPlanEnum.DQD, db);
        paramSportPlanModel.getClass();
        localArrayList2.add(new SportPlanModel.DayPlan(paramSportPlanModel, SportPlanEnum.DQD, i + "次", str7, (ArrayList)localHashMap4.get("messages"), (ArrayList)localHashMap4.get("helpToReduce"), (ArrayList)localHashMap4.get("helpToAdd"), paramArrayList));
      }
      int j = localCursor.getInt(localCursor.getColumnIndex("yy_day_sport_count"));
      if (j > 0)
      {
        String str4 = localCursor.getString(localCursor.getColumnIndex("yy_week_sport_cycle"));
        String str5 = localCursor.getString(localCursor.getColumnIndex("yy_day_during_time"));
        paramSportPlanModel.getClass();
        SportPlanModel.WeekPlan localWeekPlan2 = new SportPlanModel.WeekPlan(paramSportPlanModel, SportPlanEnum.YY, str4);
        localArrayList1.add(localWeekPlan2);
        HashMap localHashMap3 = getDaySportMessageAndHelpBySex(paramInt2, paramBodyTypeEnum, SportPlanEnum.YY, db);
        paramSportPlanModel.getClass();
        SportPlanModel.DayPlan localDayPlan2 = new SportPlanModel.DayPlan(paramSportPlanModel, SportPlanEnum.YY, j + "次", str5, (ArrayList)localHashMap3.get("messages"), (ArrayList)localHashMap3.get("helpToReduce"), (ArrayList)localHashMap3.get("helpToAdd"), paramArrayList);
        localArrayList2.add(localDayPlan2);
      }
      int k = localCursor.getInt(localCursor.getColumnIndex("wy_day_sport_count"));
      if (k > 0)
      {
        String str2 = localCursor.getString(localCursor.getColumnIndex("wy_week_sport_cycle"));
        String str3 = localCursor.getString(localCursor.getColumnIndex("wy_day_during_time"));
        paramSportPlanModel.getClass();
        SportPlanModel.WeekPlan localWeekPlan1 = new SportPlanModel.WeekPlan(paramSportPlanModel, SportPlanEnum.WY, str2);
        HashMap localHashMap2 = getDaySportMessageAndHelpBySex(paramInt2, paramBodyTypeEnum, SportPlanEnum.WY, db);
        paramSportPlanModel.getClass();
        SportPlanModel.DayPlan localDayPlan1 = new SportPlanModel.DayPlan(paramSportPlanModel, SportPlanEnum.WY, k + "次", str3, (ArrayList)localHashMap2.get("messages"), (ArrayList)localHashMap2.get("helpToReduce"), (ArrayList)localHashMap2.get("helpToAdd"), paramArrayList);
        if (localArrayList2.size() >= 2)
          localArrayList2.add(1, localDayPlan1);
        while (true)
        {
          if (localArrayList1.size() < 2)
            break label728;
          localArrayList1.add(1, localWeekPlan1);
          break;
          localArrayList2.add(localDayPlan1);
        }
        label728: localArrayList1.add(localWeekPlan1);
      }
    }
  }

  public static String getTipsMessage(Context paramContext)
  {
    int i = (int)Math.round(1.0D + 40.0D * Math.random());
    String str1 = "select tips from sport_tips where id =" + i;
    db = DBHelper.getInstance(paramContext).openDatabase();
    Cursor localCursor = db.rawQuery(str1, null);
    for (String str2 = null; ; str2 = localCursor.getString(localCursor.getColumnIndex("tips")))
      if (!localCursor.moveToNext())
      {
        if (str2 == null)
          str2 = "上班族要保证每周至少出透一次汗";
        db.close();
        return str2;
      }
  }

  public static long insertBodyIndeDB(Context paramContext, BodyIndex paramBodyIndex)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("weight", Float.valueOf(paramBodyIndex.getWeight()));
    localContentValues.put("body_fat", Float.valueOf(paramBodyIndex.getBodyFat()));
    localContentValues.put("viseral_fat_level", Float.valueOf(paramBodyIndex.getViser_fat_level()));
    localContentValues.put("muscle_race", Float.valueOf(paramBodyIndex.getMusde_race()));
    localContentValues.put("body_age", Float.valueOf(paramBodyIndex.getBodyAge()));
    localContentValues.put("bone_mass", Float.valueOf(paramBodyIndex.getBone_mass()));
    localContentValues.put("basic_metabolism", Float.valueOf(paramBodyIndex.getBasic_metabolism()));
    localContentValues.put("bmi", Float.valueOf(paramBodyIndex.getBmi()));
    localContentValues.put("time", Long.valueOf(paramBodyIndex.getTime()));
    localContentValues.put("water_race", Float.valueOf(paramBodyIndex.getWater_race()));
    localContentValues.put("fat_under_skin", Float.valueOf(paramBodyIndex.getBodyBottomFat()));
    localContentValues.put("role_id", Long.valueOf(paramBodyIndex.getRole_id()));
    localContentValues.put("flag", Integer.valueOf(0));
    localContentValues.put("id_in_server", Long.valueOf(paramBodyIndex.getId_in_server()));
    long l = db.insert("BodyIndex", null, localContentValues);
    db.close();
    return l;
  }

  public static long insertBodyIndexAfterDownloadFromServer(Context paramContext, JSONArray paramJSONArray)
  {
    long l = 0L;
    db = DBHelper.getInstance(paramContext).openDatabase();
    int i = 0;
    try
    {
      while (true)
      {
        int j = paramJSONArray.length();
        if (i >= j)
        {
          db.close();
          return l;
        }
        JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("weight", Double.valueOf(localJSONObject.getDouble("2")));
        localContentValues.put("body_fat", Double.valueOf(localJSONObject.getDouble("3")));
        localContentValues.put("viseral_fat_level", Integer.valueOf(localJSONObject.getInt("5")));
        localContentValues.put("muscle_race", Double.valueOf(localJSONObject.getDouble("6")));
        localContentValues.put("body_age", Integer.valueOf(localJSONObject.getInt("8")));
        localContentValues.put("bone_mass", Double.valueOf(localJSONObject.getDouble("9")));
        localContentValues.put("basic_metabolism", Integer.valueOf(localJSONObject.getInt("10")));
        localContentValues.put("bmi", Double.valueOf(localJSONObject.getDouble("4")));
        l = 1000L * localJSONObject.getLong("11");
        localContentValues.put("time", Long.valueOf(l));
        localContentValues.put("water_race", Double.valueOf(localJSONObject.getDouble("7")));
        localContentValues.put("fat_under_skin", Double.valueOf(localJSONObject.getDouble("13")));
        localContentValues.put("role_id", Long.valueOf(localJSONObject.getLong("1")));
        localContentValues.put("flag", Integer.valueOf(2));
        localContentValues.put("id_in_server", Long.valueOf(localJSONObject.getLong("14")));
        db.insert("BodyIndex", null, localContentValues);
        i++;
      }
    }
    catch (JSONException localJSONException)
    {
      while (true)
        localJSONException.printStackTrace();
    }
  }

  public static long insertFBMesDB(Context paramContext, FeedBackItem paramFeedBackItem)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    System.out.println("insertFBMesDB =" + db);
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("message", paramFeedBackItem.mes);
    localContentValues.put("time", Long.valueOf(paramFeedBackItem.time));
    localContentValues.put("fromUserID", Long.valueOf(paramFeedBackItem.userID));
    localContentValues.put("toUserID", Integer.valueOf(paramFeedBackItem.fromWhere));
    localContentValues.put("serverID", Integer.valueOf(paramFeedBackItem.serverID));
    long l = db.insert("FeedbackMessage", null, localContentValues);
    db.close();
    return l;
  }

  public static long insertInvitation_infos(Context paramContext, InvitationInfos paramInvitationInfos)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("user_id", paramInvitationInfos.getUser_id());
    localContentValues.put("time", Long.valueOf(paramInvitationInfos.getTime()));
    localContentValues.put("flag", Integer.valueOf(paramInvitationInfos.getFlag()));
    localContentValues.put("receive_from_email", paramInvitationInfos.getReceive_from_email());
    localContentValues.put("remote_uid", paramInvitationInfos.getRemote_uid());
    localContentValues.put("receive_from_name", paramInvitationInfos.getReceive_from_name());
    localContentValues.put("receive_message", paramInvitationInfos.getReceive_message());
    localContentValues.put("receive_from_head_url", paramInvitationInfos.getReceive_from_head_url());
    localContentValues.put("is_already_read", Integer.valueOf(paramInvitationInfos.getIs_already_read()));
    localContentValues.put("send_status_code", Integer.valueOf(paramInvitationInfos.getSend_status_code()));
    localContentValues.put("send_email", paramInvitationInfos.getSend_email());
    localContentValues.put("send_message", paramInvitationInfos.getSend_message());
    localContentValues.put("receive_from_sex", Boolean.valueOf(paramInvitationInfos.getReceive_from_sex()));
    localContentValues.put("receive_from_phone", paramInvitationInfos.getReceive_from_phone());
    localContentValues.put("send_phone", paramInvitationInfos.getSend_phone());
    long l = db.insert("invitation_infos", null, localContentValues);
    db.close();
    return l;
  }

  public static long insertOrUpdateEveryme(Context paramContext, long paramLong1, long paramLong2, EveryMeEntity paramEveryMeEntity)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("mood", Integer.valueOf(paramEveryMeEntity.getMood()));
    localContentValues.put("drink", Integer.valueOf(paramEveryMeEntity.getDrink()));
    localContentValues.put("vegetable_and_fruit", Integer.valueOf(paramEveryMeEntity.getVegetable_and_fruit()));
    localContentValues.put("sport", Integer.valueOf(paramEveryMeEntity.getSport()));
    localContentValues.put("sleep", Integer.valueOf(paramEveryMeEntity.getSleep()));
    localContentValues.put("physiology", Integer.valueOf(paramEveryMeEntity.getPhysiology()));
    localContentValues.put("time", Long.valueOf(paramEveryMeEntity.getTime()));
    localContentValues.put("flag", Integer.valueOf(0));
    localContentValues.put("role_id", Long.valueOf(paramEveryMeEntity.getRole_id()));
    EveryMeEntity localEveryMeEntity = queryEveryMeByTime(paramContext, paramLong1, paramLong2);
    SQLiteDatabase localSQLiteDatabase;
    String[] arrayOfString;
    if (localEveryMeEntity != null)
    {
      localSQLiteDatabase = db;
      arrayOfString = new String[1];
      arrayOfString[0] = localEveryMeEntity.getID();
    }
    for (long l = localSQLiteDatabase.update("picoocIndex", localContentValues, "id = ?", arrayOfString); ; l = db.insert("picoocIndex", null, localContentValues))
    {
      db.close();
      return l;
    }
  }

  public static long insertPhone_book(Context paramContext, FamilyContactsBin paramFamilyContactsBin)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("name", paramFamilyContactsBin.getNickName());
    localContentValues.put("phone", paramFamilyContactsBin.getPhoneNumer());
    localContentValues.put("is_already_my_role", Boolean.valueOf(paramFamilyContactsBin.getIsAlreadMyRole()));
    localContentValues.put("user_id", paramFamilyContactsBin.getUserID());
    localContentValues.put("id", Long.valueOf(paramFamilyContactsBin.getPhoneID()));
    long l = db.insert("phone_book", null, localContentValues);
    db.close();
    return l;
  }

  public static long insertRoleDB(Context paramContext, RoleBin paramRoleBin)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("name", paramRoleBin.getName());
    localContentValues.put("height", Float.valueOf(paramRoleBin.getHeight()));
    localContentValues.put("sex", Integer.valueOf(paramRoleBin.getSex()));
    localContentValues.put("birthday", paramRoleBin.getBirthday());
    localContentValues.put("time", Long.valueOf(paramRoleBin.getTime()));
    localContentValues.put("user_id", Long.valueOf(paramRoleBin.getUser_id()));
    localContentValues.put("goal_weight", Float.valueOf(paramRoleBin.getGoal_weight()));
    localContentValues.put("head_portrait_url", paramRoleBin.getHead_portrait_url());
    localContentValues.put("first_weight", Float.valueOf(paramRoleBin.getFirst_weight()));
    localContentValues.put("first_use", Boolean.valueOf(true));
    localContentValues.put("goal_fat", Float.valueOf(paramRoleBin.getGoal_fat()));
    localContentValues.put("first_fat", Float.valueOf(paramRoleBin.getFirst_fat()));
    localContentValues.put("first_use_time", Long.valueOf(paramRoleBin.getFirst_use_time()));
    localContentValues.put("change_goal_weight_time", Long.valueOf(paramRoleBin.getChange_goal_weight_time()));
    localContentValues.put("weight_change_target", Float.valueOf(paramRoleBin.getWeight_change_target()));
    localContentValues.put("id", Long.valueOf(paramRoleBin.getRole_id()));
    localContentValues.put("family_type", Integer.valueOf(0));
    localContentValues.put("remark_name", "");
    localContentValues.put("phone_no", "");
    localContentValues.put("email", "");
    localContentValues.put("remote_user_id", Integer.valueOf(0));
    localContentValues.put("is_new_family", Boolean.valueOf(false));
    long l = db.insert("role", null, localContentValues);
    db.close();
    return l;
  }

  public static long insertToRoleInfos(Context paramContext, RoleBin paramRoleBin)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("role_id", Long.valueOf(paramRoleBin.getRole_id()));
    localContentValues.put("height", Float.valueOf(paramRoleBin.getHeight()));
    localContentValues.put("sex", Integer.valueOf(paramRoleBin.getSex()));
    localContentValues.put("birthday", paramRoleBin.getBirthday());
    localContentValues.put("time", Long.valueOf(paramRoleBin.getTime()));
    localContentValues.put("goal_weight", Float.valueOf(paramRoleBin.getGoal_weight()));
    localContentValues.put("goal_fat", Float.valueOf(paramRoleBin.getGoal_fat()));
    localContentValues.put("change_goal_weight_time", Long.valueOf(paramRoleBin.getChange_goal_weight_time()));
    localContentValues.put("weight_change_target", Float.valueOf(paramRoleBin.getWeight_change_target()));
    long l = db.insert("role_infos", null, localContentValues);
    db.close();
    return l;
  }

  public static long insertUserDB(Context paramContext, UserBin paramUserBin)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("user_id", Long.valueOf(paramUserBin.getUser_id()));
    localContentValues.put("email", paramUserBin.getEmail());
    localContentValues.put("time", Long.valueOf(paramUserBin.getTime()));
    localContentValues.put("role_id", Long.valueOf(paramUserBin.getRole_id()));
    localContentValues.put("weibo_id", paramUserBin.getWeibo_id());
    localContentValues.put("qq_id", paramUserBin.getQQ_id());
    localContentValues.put("dayima_id", paramUserBin.getDayima_id());
    localContentValues.put("phone_no", paramUserBin.getPhone_no());
    localContentValues.put("baidu_id", paramUserBin.getBaidu_id());
    localContentValues.put("has_password", Boolean.valueOf(paramUserBin.isHas_password()));
    localContentValues.put("jd_id", paramUserBin.getJd_id());
    localContentValues.put("leyu_id", paramUserBin.getLy_id());
    long l = db.insert("Users", null, localContentValues);
    db.close();
    return l;
  }

  public static boolean isNoEmailAndPhone(Context paramContext, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str1 = "SELECT * FROM Users WHERE user_id=" + paramLong;
    Cursor localCursor = db.rawQuery(str1, null);
    String str2 = "";
    for (String str3 = ""; ; str3 = localCursor.getString(localCursor.getColumnIndex("phone_no")))
    {
      if (!localCursor.moveToNext())
      {
        if (((str2.equals("")) || (str2.equals("null"))) && ((str3.equals("")) || (str3.equals("null"))))
          break;
        Log.i("qianmo", "--------------------isNoEmailAndPhone====false");
        return false;
      }
      str2 = localCursor.getString(localCursor.getColumnIndex("email"));
    }
    Log.i("qianmo", "--------------------isNoEmailAndPhone====true");
    return true;
  }

  public static ArrayList<RoleBin> queryAllRoleInfosByRoleIDBeforeTimeStamp(Context paramContext, long paramLong1, long paramLong2)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "SELECT * FROM role_infos where role_id=" + paramLong1 + " AND change_goal_weight_time <= " + paramLong2 + "  ORDER BY change_goal_weight_time ASC";
    Cursor localCursor = db.rawQuery(str, null);
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        db.close();
        return localArrayList;
      }
      RoleBin localRoleBin = new RoleBin();
      localRoleBin.setRole_id(localCursor.getLong(localCursor.getColumnIndex("role_id")));
      localRoleBin.setHeight(localCursor.getFloat(localCursor.getColumnIndex("height")));
      localRoleBin.setSex(localCursor.getInt(localCursor.getColumnIndex("sex")));
      localRoleBin.setBirthday(localCursor.getString(localCursor.getColumnIndex("birthday")));
      localRoleBin.setTime(localCursor.getLong(localCursor.getColumnIndex("time")));
      localRoleBin.setGoal_weight(localCursor.getFloat(localCursor.getColumnIndex("goal_weight")));
      localRoleBin.setGoal_fat(localCursor.getFloat(localCursor.getColumnIndex("goal_fat")));
      localRoleBin.setChange_goal_weight_time(localCursor.getLong(localCursor.getColumnIndex("change_goal_weight_time")));
      localRoleBin.setWeight_change_target(localCursor.getFloat(localCursor.getColumnIndex("weight_change_target")));
      localArrayList.add(localRoleBin);
    }
  }

  public static ArrayList<EveryMeEntity> queryEveryMeByStartTimeAndTime(Context paramContext, long paramLong1, long paramLong2, long paramLong3)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "SELECT * FROM picoocIndex WHERE role_id=" + paramLong1 + " AND time BETWEEN " + paramLong2 + " AND " + paramLong3;
    Cursor localCursor = db.rawQuery(str, null);
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      if (!localCursor.moveToNext())
        return localArrayList;
      EveryMeEntity localEveryMeEntity = new EveryMeEntity();
      localEveryMeEntity.setID(localCursor.getInt(localCursor.getColumnIndex("id")));
      localEveryMeEntity.setMood(localCursor.getInt(localCursor.getColumnIndex("mood")));
      localEveryMeEntity.setDrink(localCursor.getInt(localCursor.getColumnIndex("drink")));
      localEveryMeEntity.setVegetable_and_fruit(localCursor.getInt(localCursor.getColumnIndex("vegetable_and_fruit")));
      localEveryMeEntity.setSport(localCursor.getInt(localCursor.getColumnIndex("sport")));
      localEveryMeEntity.setSleep(localCursor.getInt(localCursor.getColumnIndex("sleep")));
      localEveryMeEntity.setPhysiology(localCursor.getInt(localCursor.getColumnIndex("physiology")));
      localEveryMeEntity.setTime(localCursor.getLong(localCursor.getColumnIndex("time")));
      localEveryMeEntity.setFlag(localCursor.getInt(localCursor.getColumnIndex("flag")));
      localEveryMeEntity.setRole_id(localCursor.getLong(localCursor.getColumnIndex("role_id")));
      localArrayList.add(localEveryMeEntity);
    }
  }

  public static EveryMeEntity queryEveryMeByTime(Context paramContext, long paramLong1, long paramLong2)
  {
    long[] arrayOfLong = DateUtils.getDayStartTimeAndEndTimeByTimestamp(paramLong2);
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "SELECT * FROM picoocIndex WHERE role_id=" + paramLong1 + " AND time BETWEEN " + arrayOfLong[0] + " AND " + arrayOfLong[1] + " LIMIT 1";
    Cursor localCursor = db.rawQuery(str, null);
    EveryMeEntity localEveryMeEntity = null;
    while (true)
    {
      if (!localCursor.moveToNext())
        return localEveryMeEntity;
      localEveryMeEntity = new EveryMeEntity();
      localEveryMeEntity.setID(localCursor.getInt(localCursor.getColumnIndex("id")));
      localEveryMeEntity.setMood(localCursor.getInt(localCursor.getColumnIndex("mood")));
      localEveryMeEntity.setDrink(localCursor.getInt(localCursor.getColumnIndex("drink")));
      localEveryMeEntity.setVegetable_and_fruit(localCursor.getInt(localCursor.getColumnIndex("vegetable_and_fruit")));
      localEveryMeEntity.setSport(localCursor.getInt(localCursor.getColumnIndex("sport")));
      localEveryMeEntity.setSleep(localCursor.getInt(localCursor.getColumnIndex("sleep")));
      localEveryMeEntity.setPhysiology(localCursor.getInt(localCursor.getColumnIndex("physiology")));
      localEveryMeEntity.setTime(localCursor.getLong(localCursor.getColumnIndex("time")));
      localEveryMeEntity.setFlag(localCursor.getInt(localCursor.getColumnIndex("flag")));
      localEveryMeEntity.setRole_id(localCursor.getLong(localCursor.getColumnIndex("role_id")));
    }
  }

  public static long queryLastBodyIndexTime(Context paramContext, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "SELECT time from BodyIndex WHERE role_id=" + paramLong + " ORDER BY time desc LIMIT 1";
    Cursor localCursor = db.rawQuery(str, null);
    for (long l = 0L; ; l = localCursor.getLong(localCursor.getColumnIndex("time")))
      if (!localCursor.moveToNext())
        return l;
  }

  public static long queryLastTimeOnRoleInfosByRoleID(Context paramContext, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "SELECT time FROM role_infos where role_id=" + paramLong + "  ORDER BY time DESC LIMIT 1";
    Cursor localCursor = db.rawQuery(str, null);
    for (long l = 0L; ; l = localCursor.getLong(localCursor.getColumnIndex("time")))
      if (!localCursor.moveToNext())
        return l;
  }

  public static int queryShareAchievementLevelWithLastTimeByRoleId(Context paramContext, RoleBin paramRoleBin, SharedEntityTypeEmun paramSharedEntityTypeEmun, double paramDouble)
  {
    int i = -1;
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "select level from ShareAchievementCache where rid = " + paramRoleBin.getRole_id() + " and type = %d and time < " + paramDouble + " order by time desc limit 1";
    Cursor localCursor = db.rawQuery(str, null);
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        db.close();
        return i;
      }
      i = localCursor.getInt(localCursor.getColumnIndex("level"));
    }
  }

  public static boolean remoteIdIsExist(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "select 1 from invitation_infos where user_id=" + paramString1 + " and remote_uid=" + paramString2 + " and flag=" + paramString3;
    Cursor localCursor = db.rawQuery(str, null);
    for (int i = 0; ; i = localCursor.getInt(0))
      if (!localCursor.moveToNext())
      {
        if (i != 1)
          break;
        return true;
      }
    return false;
  }

  public static List<FamilyContactsBin> searchPhone_book(Context paramContext, Editable paramEditable)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    SQLiteDatabase localSQLiteDatabase = db;
    String[] arrayOfString1 = { "name,phone,user_id,is_already_my_role" };
    String[] arrayOfString2 = new String[2];
    arrayOfString2[0] = ("%" + paramEditable + "%");
    arrayOfString2[1] = ("%" + paramEditable + "%");
    Cursor localCursor = localSQLiteDatabase.query("phone_book", arrayOfString1, "name like ? OR phone like ?", arrayOfString2, null, null, " name COLLATE LOCALIZED ");
    ArrayList localArrayList = new ArrayList();
    if (localCursor.getCount() > 0);
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        db.close();
        return localArrayList;
      }
      FamilyContactsBin localFamilyContactsBin = new FamilyContactsBin();
      localFamilyContactsBin.setNickName(localCursor.getString(localCursor.getColumnIndex("name")));
      localFamilyContactsBin.setIsAlreadMyRole(localCursor.getInt(localCursor.getColumnIndex("is_already_my_role")));
      localFamilyContactsBin.setPhoneNumer(localCursor.getString(localCursor.getColumnIndex("phone")));
      localFamilyContactsBin.setUserID(localCursor.getString(localCursor.getColumnIndex("user_id")));
      localArrayList.add(localFamilyContactsBin);
    }
  }

  public static ArrayList<FeedBackItem> selectAll(Context paramContext, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    System.out.println("selectAll =" + db);
    String str = "select  * from FeedbackMessage where fromUserID= " + paramLong;
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = db.rawQuery(str, null);
    Iterator localIterator;
    if ((localCursor != null) && (localCursor.getCount() > 0))
    {
      if (localCursor.moveToNext());
    }
    else
    {
      db.close();
      localIterator = localArrayList.iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        return localArrayList;
        FeedBackItem localFeedBackItem2 = new FeedBackItem();
        localFeedBackItem2.id = localCursor.getInt(0);
        localFeedBackItem2.mes = localCursor.getString(1);
        localFeedBackItem2.time = localCursor.getLong(2);
        localFeedBackItem2.userID = localCursor.getInt(3);
        localFeedBackItem2.fromWhere = localCursor.getInt(4);
        localFeedBackItem2.serverID = localCursor.getInt(5);
        System.out.println("selectAll ================serverID =" + localFeedBackItem2.serverID);
        int i = localCursor.getColumnIndex("id");
        System.out.println(localCursor.getString(i));
        localArrayList.add(localFeedBackItem2);
        break;
      }
      FeedBackItem localFeedBackItem1 = (FeedBackItem)localIterator.next();
      Log.i("picooc", localFeedBackItem1.id + " " + localFeedBackItem1.mes + localFeedBackItem1.fromWhere);
    }
  }

  public static List<RoleBin> selectAllRoleByUserId(Context paramContext, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    ArrayList localArrayList = new ArrayList();
    String str = "SELECT * FROM role WHERE user_id = " + paramLong + " and id > 0" + " ORDER BY id ASC";
    Cursor localCursor = db.rawQuery(str, null);
    while (true)
    {
      if (!localCursor.moveToNext())
        return localArrayList;
      RoleBin localRoleBin = new RoleBin();
      localRoleBin.setRole_id(localCursor.getLong(localCursor.getColumnIndex("id")));
      localRoleBin.setName(localCursor.getString(localCursor.getColumnIndex("name")));
      localRoleBin.setHeight(localCursor.getFloat(localCursor.getColumnIndex("height")));
      localRoleBin.setSex(localCursor.getInt(localCursor.getColumnIndex("sex")));
      localRoleBin.setBirthday(localCursor.getString(localCursor.getColumnIndex("birthday")));
      localRoleBin.setTime(localCursor.getLong(localCursor.getColumnIndex("time")));
      localRoleBin.setUser_id(localCursor.getInt(localCursor.getColumnIndex("user_id")));
      localRoleBin.setGoal_weight(localCursor.getFloat(localCursor.getColumnIndex("goal_weight")));
      localRoleBin.setHead_portrait_url(localCursor.getString(localCursor.getColumnIndex("head_portrait_url")));
      localRoleBin.setFirst_weight(localCursor.getFloat(localCursor.getColumnIndex("first_weight")));
      localRoleBin.setGoal_fat(localCursor.getFloat(localCursor.getColumnIndex("goal_fat")));
      localRoleBin.setFirst_fat(localCursor.getFloat(localCursor.getColumnIndex("first_fat")));
      localRoleBin.setFirst_use_time(localCursor.getLong(localCursor.getColumnIndex("first_use_time")));
      localRoleBin.setChange_goal_weight_time(localCursor.getLong(localCursor.getColumnIndex("change_goal_weight_time")));
      localRoleBin.setWeight_change_target(localCursor.getFloat(localCursor.getColumnIndex("weight_change_target")));
      localRoleBin.setFamily_type(localCursor.getInt(localCursor.getColumnIndex("family_type")));
      localRoleBin.setRemark_name(localCursor.getString(localCursor.getColumnIndex("remark_name")));
      localRoleBin.setPhone_no(localCursor.getString(localCursor.getColumnIndex("phone_no")));
      localRoleBin.setEmail(localCursor.getString(localCursor.getColumnIndex("email")));
      localRoleBin.setRemote_user_id(localCursor.getLong(localCursor.getColumnIndex("remote_user_id")));
      localRoleBin.setIs_new_family(localCursor.getInt(localCursor.getColumnIndex("is_new_family")));
      localArrayList.add(localRoleBin);
    }
  }

  public static BodyIndex selectBodyIndexAfterTimestamp(Context paramContext, long paramLong1, long paramLong2)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "select * from BodyIndex where role_id=" + paramLong1 + " AND time > " + paramLong2 + " order by time asc limit 1";
    Cursor localCursor = db.rawQuery(str, null);
    boolean bool = localCursor.moveToNext();
    BodyIndex localBodyIndex = null;
    if (bool)
    {
      localBodyIndex = new BodyIndex();
      localBodyIndex.setWeight(localCursor.getFloat(localCursor.getColumnIndex("weight")));
      localBodyIndex.setBodyFat(localCursor.getFloat(localCursor.getColumnIndex("body_fat")));
      localBodyIndex.setViser_fat_level(localCursor.getFloat(localCursor.getColumnIndex("viseral_fat_level")));
      localBodyIndex.setMusde_race(localCursor.getFloat(localCursor.getColumnIndex("muscle_race")));
      localBodyIndex.setBodyAge(localCursor.getFloat(localCursor.getColumnIndex("body_age")));
      localBodyIndex.setBone_mass(localCursor.getFloat(localCursor.getColumnIndex("bone_mass")));
      localBodyIndex.setBasic_metabolism(localCursor.getFloat(localCursor.getColumnIndex("basic_metabolism")));
      localBodyIndex.setBmi(localCursor.getFloat(localCursor.getColumnIndex("bmi")));
      localBodyIndex.setTime(localCursor.getLong(localCursor.getColumnIndex("time")));
      localBodyIndex.setFlag(localCursor.getInt(localCursor.getColumnIndex("flag")));
      localBodyIndex.setWater_race(localCursor.getFloat(localCursor.getColumnIndex("water_race")));
      localBodyIndex.setRole_id(localCursor.getLong(localCursor.getColumnIndex("role_id")));
      localBodyIndex.setBodyBottomFat(localCursor.getFloat(localCursor.getColumnIndex("fat_under_skin")));
      localBodyIndex.setId_in_server(localCursor.getLong(localCursor.getColumnIndex("id_in_server")));
    }
    db.close();
    return localBodyIndex;
  }

  public static ArrayList<BodyIndex> selectBodyIndexBetweenStartTimeAndEndTimeAndRoleID(Context paramContext, long paramLong1, long paramLong2, long paramLong3)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "SELECT * FROM BodyIndex WHERE role_id=" + paramLong3 + " AND time BETWEEN " + paramLong1 + " AND " + paramLong2 + " ORDER BY time ASC";
    Cursor localCursor = db.rawQuery(str, null);
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      if (!localCursor.moveToNext())
        return localArrayList;
      BodyIndex localBodyIndex = new BodyIndex();
      localBodyIndex.setWeight(localCursor.getFloat(localCursor.getColumnIndex("weight")));
      localBodyIndex.setBodyFat(localCursor.getFloat(localCursor.getColumnIndex("body_fat")));
      localBodyIndex.setViser_fat_level(localCursor.getFloat(localCursor.getColumnIndex("viseral_fat_level")));
      localBodyIndex.setMusde_race(localCursor.getFloat(localCursor.getColumnIndex("muscle_race")));
      localBodyIndex.setBodyAge(localCursor.getFloat(localCursor.getColumnIndex("body_age")));
      localBodyIndex.setBone_mass(localCursor.getFloat(localCursor.getColumnIndex("bone_mass")));
      localBodyIndex.setBasic_metabolism(localCursor.getFloat(localCursor.getColumnIndex("basic_metabolism")));
      localBodyIndex.setBmi(localCursor.getFloat(localCursor.getColumnIndex("bmi")));
      localBodyIndex.setTime(localCursor.getLong(localCursor.getColumnIndex("time")));
      localBodyIndex.setFlag(localCursor.getInt(localCursor.getColumnIndex("flag")));
      localBodyIndex.setWater_race(localCursor.getFloat(localCursor.getColumnIndex("water_race")));
      localBodyIndex.setRole_id(localCursor.getLong(localCursor.getColumnIndex("role_id")));
      localBodyIndex.setId_in_server(localCursor.getLong(localCursor.getColumnIndex("id_in_server")));
      localArrayList.add(localBodyIndex);
    }
  }

  public static BodyIndex selectBodyindexBeforeTimestamp(Context paramContext, long paramLong1, long paramLong2)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "select * from BodyIndex where role_id=" + paramLong1 + " AND time < " + paramLong2 + " order by time desc limit 1";
    Cursor localCursor = db.rawQuery(str, null);
    boolean bool = localCursor.moveToNext();
    BodyIndex localBodyIndex = null;
    if (bool)
    {
      localBodyIndex = new BodyIndex();
      localBodyIndex.setWeight(localCursor.getFloat(localCursor.getColumnIndex("weight")));
      localBodyIndex.setBodyFat(localCursor.getFloat(localCursor.getColumnIndex("body_fat")));
      localBodyIndex.setViser_fat_level(localCursor.getFloat(localCursor.getColumnIndex("viseral_fat_level")));
      localBodyIndex.setMusde_race(localCursor.getFloat(localCursor.getColumnIndex("muscle_race")));
      localBodyIndex.setBodyAge(localCursor.getFloat(localCursor.getColumnIndex("body_age")));
      localBodyIndex.setBone_mass(localCursor.getFloat(localCursor.getColumnIndex("bone_mass")));
      localBodyIndex.setBasic_metabolism(localCursor.getFloat(localCursor.getColumnIndex("basic_metabolism")));
      localBodyIndex.setBmi(localCursor.getFloat(localCursor.getColumnIndex("bmi")));
      localBodyIndex.setTime(localCursor.getLong(localCursor.getColumnIndex("time")));
      localBodyIndex.setFlag(localCursor.getInt(localCursor.getColumnIndex("flag")));
      localBodyIndex.setWater_race(localCursor.getFloat(localCursor.getColumnIndex("water_race")));
      localBodyIndex.setRole_id(localCursor.getLong(localCursor.getColumnIndex("role_id")));
      localBodyIndex.setBodyBottomFat(localCursor.getFloat(localCursor.getColumnIndex("fat_under_skin")));
      localBodyIndex.setId_in_server(localCursor.getLong(localCursor.getColumnIndex("id_in_server")));
      localBodyIndex.setId(localCursor.getLong(localCursor.getColumnIndex("id")));
    }
    return localBodyIndex;
  }

  public static BodyIndex selectBodyindexDB(Context paramContext, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "select * from BodyIndex where role_id=" + paramLong + " order by time desc limit 1";
    Cursor localCursor = db.rawQuery(str, null);
    boolean bool = localCursor.moveToNext();
    BodyIndex localBodyIndex = null;
    if (bool)
    {
      localBodyIndex = new BodyIndex();
      localBodyIndex.setWeight(localCursor.getFloat(localCursor.getColumnIndex("weight")));
      localBodyIndex.setBodyFat(localCursor.getFloat(localCursor.getColumnIndex("body_fat")));
      localBodyIndex.setViser_fat_level(localCursor.getFloat(localCursor.getColumnIndex("viseral_fat_level")));
      localBodyIndex.setMusde_race(localCursor.getFloat(localCursor.getColumnIndex("muscle_race")));
      localBodyIndex.setBodyAge(localCursor.getFloat(localCursor.getColumnIndex("body_age")));
      localBodyIndex.setBone_mass(localCursor.getFloat(localCursor.getColumnIndex("bone_mass")));
      localBodyIndex.setBasic_metabolism(localCursor.getFloat(localCursor.getColumnIndex("basic_metabolism")));
      localBodyIndex.setBmi(localCursor.getFloat(localCursor.getColumnIndex("bmi")));
      localBodyIndex.setTime(localCursor.getLong(localCursor.getColumnIndex("time")));
      localBodyIndex.setFlag(localCursor.getInt(localCursor.getColumnIndex("flag")));
      localBodyIndex.setWater_race(localCursor.getFloat(localCursor.getColumnIndex("water_race")));
      localBodyIndex.setRole_id(localCursor.getLong(localCursor.getColumnIndex("role_id")));
      localBodyIndex.setBodyBottomFat(localCursor.getFloat(localCursor.getColumnIndex("fat_under_skin")));
      localBodyIndex.setId_in_server(localCursor.getLong(localCursor.getColumnIndex("id_in_server")));
    }
    db.close();
    return localBodyIndex;
  }

  public static int selectCount(Context paramContext, boolean paramBoolean, long paramLong1, long paramLong2, long paramLong3, String paramString)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    Object localObject = "select count(*) as num from invitation_infos where is_already_read=0 and flag=0 and time>" + paramLong2 + " and " + paramString + "=" + paramLong3;
    String str = "select count(*) as num from invitation_infos where is_already_read=0 and send_status_code=2 and flag= 1 and time>" + paramLong1 / 1000L + " and user_id=" + paramLong3;
    if (paramBoolean);
    while (true)
    {
      Log.i("picooc", "sql=---" + (String)localObject);
      Cursor localCursor = db.rawQuery((String)localObject, null);
      int i = localCursor.getCount();
      int j = 0;
      if (i > 0)
      {
        localCursor.moveToNext();
        j = localCursor.getInt(0);
      }
      db.close();
      return j;
      localObject = str;
    }
  }

  public static ArrayList<BodyIndex> selectDayValuesAfterTimestampAndRid(Context paramContext, long paramLong1, long paramLong2)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "SELECT time FROM BodyIndex WHERE role_id=" + paramLong2 + " AND time > " + paramLong1 + " ORDER BY time ASC LIMIT 1";
    Cursor localCursor = db.rawQuery(str, null);
    for (long l = 0L; ; l = localCursor.getLong(localCursor.getColumnIndex("time")))
      if (!localCursor.moveToNext())
      {
        if (l != 0L)
          break;
        return null;
      }
    long[] arrayOfLong = DateUtils.getDayStartTimeAndEndTimeByTimestamp(l);
    ArrayList localArrayList = selectBodyIndexBetweenStartTimeAndEndTimeAndRoleID(paramContext, arrayOfLong[0], arrayOfLong[1], paramLong2);
    db.close();
    return localArrayList;
  }

  public static ArrayList<BodyIndex> selectDayValuesBeforeTimestampAndRid(Context paramContext, long paramLong1, long paramLong2)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "SELECT time FROM BodyIndex WHERE role_id=" + paramLong2 + " AND time < " + paramLong1 + " ORDER BY time DESC LIMIT 1";
    Cursor localCursor = db.rawQuery(str, null);
    for (long l = 0L; ; l = localCursor.getLong(localCursor.getColumnIndex("time")))
      if (!localCursor.moveToNext())
      {
        if (l != 0L)
          break;
        return null;
      }
    long[] arrayOfLong = DateUtils.getDayStartTimeAndEndTimeByTimestamp(l);
    ArrayList localArrayList = selectBodyIndexBetweenStartTimeAndEndTimeAndRoleID(paramContext, arrayOfLong[0], arrayOfLong[1], paramLong2);
    db.close();
    return localArrayList;
  }

  public static ArrayList<BodyIndex> selectDayValuesByTimestampAndRid(Context paramContext, long paramLong1, long paramLong2)
  {
    long[] arrayOfLong = DateUtils.getDayStartTimeAndEndTimeByTimestamp(paramLong1);
    return selectBodyIndexBetweenStartTimeAndEndTimeAndRoleID(paramContext, arrayOfLong[0], arrayOfLong[1], paramLong2);
  }

  public static List<InvitationInfos> selectInvitation_infos(Context paramContext, String paramString1, int paramInt, String paramString2)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str1 = "select invitation_infos.*, phone_book.name as n  from invitation_infos left join phone_book on invitation_infos.send_phone = phone_book.phone or invitation_infos.receive_from_phone = phone_book.phone  where invitation_infos." + paramString2 + "=" + paramString1 + " and invitation_infos.is_already_read=0 and invitation_infos.flag= " + paramInt + "  order by invitation_infos.time desc";
    Cursor localCursor = db.rawQuery(str1, null);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator;
    if (localCursor.getCount() > 0)
    {
      if (localCursor.moveToNext());
    }
    else
    {
      db.close();
      localIterator = localArrayList.iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        return localArrayList;
        InvitationInfos localInvitationInfos2 = new InvitationInfos();
        localInvitationInfos2.setReceive_from_head_url(localCursor.getString(localCursor.getColumnIndex("receive_from_head_url")));
        localInvitationInfos2.setReceive_from_name(localCursor.getString(localCursor.getColumnIndex("receive_from_name")));
        localInvitationInfos2.setReceive_from_sex(localCursor.getString(localCursor.getColumnIndex("receive_from_sex")));
        localInvitationInfos2.setReceive_message(localCursor.getString(localCursor.getColumnIndex("receive_message")));
        localInvitationInfos2.setRemote_uid(localCursor.getString(localCursor.getColumnIndex("remote_uid")));
        localInvitationInfos2.setSend_message(localCursor.getString(localCursor.getColumnIndex("send_message")));
        localInvitationInfos2.setSend_status_code(localCursor.getInt(localCursor.getColumnIndex("send_status_code")));
        localInvitationInfos2.setTime(localCursor.getLong(localCursor.getColumnIndex("time")));
        Log.i("picooc", "time==" + localCursor.getInt(localCursor.getColumnIndex("time")));
        String str2 = localCursor.getString(localCursor.getColumnIndex("n"));
        String str3 = localCursor.getString(localCursor.getColumnIndex("send_phone"));
        String str4 = localCursor.getString(localCursor.getColumnIndex("receive_from_phone"));
        String str5 = localCursor.getString(localCursor.getColumnIndex("send_email"));
        String str6 = localCursor.getString(localCursor.getColumnIndex("receive_from_email"));
        localInvitationInfos2.setSend_phone(str3);
        localInvitationInfos2.setReceive_from_phone(str4);
        localInvitationInfos2.setSend_email(str5);
        localInvitationInfos2.setReceive_from_email(str6);
        localInvitationInfos2.setUser_id(localCursor.getString(localCursor.getColumnIndex("user_id")));
        if ((str2 != null) && (!str2.equals("")) && (!str2.equals("null")))
        {
          localInvitationInfos2.setName(str2);
          localInvitationInfos2.setTybe(1);
        }
        while (true)
        {
          localArrayList.add(localInvitationInfos2);
          break;
          if ((str3 != null) && (!str3.equals("")) && (!str3.equals("null")))
          {
            localInvitationInfos2.setName(str3);
            localInvitationInfos2.setTybe(2);
          }
          else if ((str4 != null) && (!str4.equals("")) && (!str4.equals("null")))
          {
            localInvitationInfos2.setName(str4);
            localInvitationInfos2.setTybe(2);
          }
          else if ((str5 != null) && (!str5.equals("")) && (!str5.equals("null")))
          {
            localInvitationInfos2.setName(str5);
            localInvitationInfos2.setTybe(3);
          }
          else if ((str6 != null) && (!str6.equals("")) && (!str6.equals("null")))
          {
            localInvitationInfos2.setName(str5);
            localInvitationInfos2.setTybe(3);
          }
          else
          {
            localInvitationInfos2.setName("--");
          }
        }
      }
      InvitationInfos localInvitationInfos1 = (InvitationInfos)localIterator.next();
      Log.i("picooc", "send_phone=" + localInvitationInfos1.getSend_phone() + "   receive_from_phone=" + localInvitationInfos1.getReceive_from_phone() + "   name=" + localInvitationInfos1.getName());
    }
  }

  public static String selectMainName(Context paramContext, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str1 = "SELECT role_id FROM Users WHERE user_id=" + paramLong;
    Cursor localCursor1 = db.rawQuery(str1, null);
    long l = 0L;
    Cursor localCursor2;
    if (!localCursor1.moveToNext())
    {
      if (l != 0L)
      {
        String str2 = "SELECT name FROM role WHERE id=" + l;
        localCursor2 = db.rawQuery(str2, null);
      }
    }
    else
    {
      for (String str3 = ""; ; str3 = localCursor2.getString(localCursor2.getColumnIndex("name")))
        if (!localCursor2.moveToNext())
        {
          if ((str3.equals("")) || (str3.equals("null")))
            break label170;
          return str3;
          l = localCursor1.getLong(localCursor1.getColumnIndex("role_id"));
          break;
        }
      label170: return "☺";
    }
    return "☺";
  }

  public static long selectMastTime(Context paramContext, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "select time from invitation_infos where user_id=" + paramLong + " order by time desc limit 1";
    Cursor localCursor = db.rawQuery(str, null);
    for (Long localLong = Long.valueOf(0L); ; localLong = Long.valueOf(localCursor.getLong(0)))
      if (!localCursor.moveToNext())
      {
        db.close();
        return localLong.longValue();
      }
  }

  public static List<FamilyContactsBin> selectPhone_book(Context paramContext)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    Cursor localCursor = db.rawQuery("select name,phone,user_id,is_already_my_role from phone_book order by is_already_my_role asc , user_id desc ,id asc", null);
    ArrayList localArrayList = new ArrayList();
    if (localCursor.getCount() > 0);
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        db.close();
        return localArrayList;
      }
      FamilyContactsBin localFamilyContactsBin = new FamilyContactsBin();
      localFamilyContactsBin.setNickName(localCursor.getString(localCursor.getColumnIndex("name")));
      localFamilyContactsBin.setIsAlreadMyRole(localCursor.getInt(localCursor.getColumnIndex("is_already_my_role")));
      localFamilyContactsBin.setPhoneNumer(localCursor.getString(localCursor.getColumnIndex("phone")));
      localFamilyContactsBin.setUserID(localCursor.getString(localCursor.getColumnIndex("user_id")));
      localArrayList.add(localFamilyContactsBin);
    }
  }

  public static long selectPhone_bookID(Context paramContext)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    Cursor localCursor = db.rawQuery("select id from phone_book order by id desc", null);
    long l = 0L;
    if (localCursor.getCount() > 0)
    {
      localCursor.moveToNext();
      l = localCursor.getLong(0);
    }
    db.close();
    return l;
  }

  public static String selectQQ_id(Context paramContext, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str1 = "SELECT qq_id FROM Users WHERE user_id=" + paramLong;
    Cursor localCursor = db.rawQuery(str1, null);
    for (String str2 = ""; ; str2 = localCursor.getString(localCursor.getColumnIndex("qq_id")))
      if (!localCursor.moveToNext())
      {
        if ((str2.equals("null")) || (str2.equals("")))
          break;
        return str2;
      }
    return null;
  }

  public static int selectRecevieAndSendCount(Context paramContext, int paramInt)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "select count(*) as num from invitation_infos where is_already_read=0 and flag= " + paramInt;
    Cursor localCursor = db.rawQuery(str, null);
    int i = localCursor.getCount();
    int j = 0;
    if (i > 0)
    {
      localCursor.moveToNext();
      j = localCursor.getInt(0);
    }
    db.close();
    return j;
  }

  public static RoleBin selectRoleDB(Context paramContext, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "select * from role where id=" + paramLong + " limit 1";
    Log.i("sql", "selectRoleDB = " + str);
    Cursor localCursor = db.rawQuery(str, null);
    RoleBin localRoleBin = null;
    while (true)
    {
      if (!localCursor.moveToNext())
        return localRoleBin;
      localRoleBin = new RoleBin();
      localRoleBin.setRole_id(localCursor.getLong(localCursor.getColumnIndex("id")));
      localRoleBin.setName(localCursor.getString(localCursor.getColumnIndex("name")));
      localRoleBin.setHeight(localCursor.getFloat(localCursor.getColumnIndex("height")));
      localRoleBin.setSex(localCursor.getInt(localCursor.getColumnIndex("sex")));
      localRoleBin.setBirthday(localCursor.getString(localCursor.getColumnIndex("birthday")));
      localRoleBin.setTime(localCursor.getLong(localCursor.getColumnIndex("time")));
      localRoleBin.setUser_id(localCursor.getInt(localCursor.getColumnIndex("user_id")));
      localRoleBin.setGoal_weight(localCursor.getFloat(localCursor.getColumnIndex("goal_weight")));
      localRoleBin.setHead_portrait_url(localCursor.getString(localCursor.getColumnIndex("head_portrait_url")));
      localRoleBin.setFirst_weight(localCursor.getFloat(localCursor.getColumnIndex("first_weight")));
      localRoleBin.setGoal_fat(localCursor.getFloat(localCursor.getColumnIndex("goal_fat")));
      localRoleBin.setFirst_fat(localCursor.getFloat(localCursor.getColumnIndex("first_fat")));
      localRoleBin.setFirst_use_time(localCursor.getLong(localCursor.getColumnIndex("first_use_time")));
      localRoleBin.setChange_goal_weight_time(localCursor.getLong(localCursor.getColumnIndex("change_goal_weight_time")));
      localRoleBin.setWeight_change_target(localCursor.getFloat(localCursor.getColumnIndex("weight_change_target")));
      localRoleBin.setFamily_type(localCursor.getInt(localCursor.getColumnIndex("family_type")));
      localRoleBin.setRemark_name(localCursor.getString(localCursor.getColumnIndex("remark_name")));
      localRoleBin.setPhone_no(localCursor.getString(localCursor.getColumnIndex("phone_no")));
      localRoleBin.setEmail(localCursor.getString(localCursor.getColumnIndex("email")));
      localRoleBin.setRemote_user_id(localCursor.getLong(localCursor.getColumnIndex("remote_user_id")));
      localRoleBin.setIs_new_family(localCursor.getInt(localCursor.getColumnIndex("is_new_family")));
    }
  }

  public static UserBin selectUserByUserIdDB(Context paramContext, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "select * from Users where user_id=" + paramLong + "  limit 1";
    Cursor localCursor = db.rawQuery(str, null);
    if (localCursor.getCount() <= 0)
    {
      db.close();
      return null;
    }
    if (localCursor.moveToNext())
    {
      UserBin localUserBin = new UserBin();
      localUserBin.setUser_id(localCursor.getLong(localCursor.getColumnIndex("user_id")));
      localUserBin.setEmail(localCursor.getString(localCursor.getColumnIndex("email")));
      localUserBin.setTime(localCursor.getLong(localCursor.getColumnIndex("time")));
      localUserBin.setRole_id(localCursor.getLong(localCursor.getColumnIndex("role_id")));
      localUserBin.setWeibo_id(localCursor.getString(localCursor.getColumnIndex("weibo_id")));
      localUserBin.setQQ_id(localCursor.getString(localCursor.getColumnIndex("qq_id")));
      localUserBin.setDayima_id(localCursor.getString(localCursor.getColumnIndex("dayima_id")));
      localUserBin.setPhone_no(localCursor.getString(localCursor.getColumnIndex("phone_no")));
      localUserBin.setBaidu_id(localCursor.getString(localCursor.getColumnIndex("baidu_id")));
      localUserBin.setHas_password(localCursor.getInt(localCursor.getColumnIndex("has_password")));
      localUserBin.setJd_id(localCursor.getString(localCursor.getColumnIndex("jd_id")));
      localUserBin.setLy_id(localCursor.getString(localCursor.getColumnIndex("leyu_id")));
      return localUserBin;
    }
    db.close();
    return null;
  }

  public static void updatPhone_book(Context paramContext, FamilyContactsBin paramFamilyContactsBin)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "update phone_book set user_id=" + paramFamilyContactsBin.getUserID() + ",is_already_my_role=" + booleantoInt(paramFamilyContactsBin.getIsAlreadMyRole()) + " where phone=" + paramFamilyContactsBin.getPhoneNumer();
    db.execSQL(str);
    db.execSQL(str);
    db.close();
  }

  public static boolean updateAllRolesAndRoleInfos(Context paramContext, JSONObject paramJSONObject, long paramLong)
  {
    boolean bool = true;
    db = DBHelper.getInstance(paramContext).openDatabase();
    db.beginTransaction();
    int k;
    label83: int n;
    float f1;
    RoleBin localRoleBin1;
    label365: int i3;
    label1431: 
    while (true)
    {
      ArrayList localArrayList;
      JSONObject localJSONObject1;
      long l1;
      ContentValues localContentValues1;
      String str5;
      long l5;
      JSONArray localJSONArray2;
      long l6;
      try
      {
        JSONArray localJSONArray1 = paramJSONObject.getJSONArray("roles");
        List localList = selectAllRoleByUserId(paramContext, paramLong);
        localArrayList = new ArrayList();
        int i = 0;
        int j = localList.size();
        if (i >= j)
        {
          k = 0;
          int m = localJSONArray1.length();
          if (k >= m)
          {
            break;
            int i1 = localArrayList.size();
            if (n < i1)
              break label1431;
            db.setTransactionSuccessful();
            db.endTransaction();
            return bool;
          }
        }
        else
        {
          localArrayList.add(Long.valueOf(((RoleBin)localList.get(i)).getRole_id()));
          i++;
          continue;
        }
        localJSONObject1 = localJSONArray1.getJSONObject(k);
        l1 = localJSONObject1.getLong("roleID");
        String str1 = localJSONObject1.getString("name");
        f1 = 100.0F * (float)localJSONObject1.getDouble("height");
        int i2 = localJSONObject1.getInt("sex");
        String str2 = DateUtils.changeOldTimeStringToNewTimeString(localJSONObject1.getString("birthday"), "yyyy-MM-dd", "yyyyMMdd");
        long l2 = 1000L * localJSONObject1.getLong("time");
        float f2 = (float)localJSONObject1.getDouble("gole_weight");
        String str3 = localJSONObject1.getString("head_protail_url");
        float f3 = (float)localJSONObject1.getDouble("first_weight");
        float f4 = (float)localJSONObject1.getDouble("goal_fat");
        float f5 = (float)localJSONObject1.getDouble("first_fat");
        long l3 = 1000L * localJSONObject1.getLong("first_use_time");
        long l4 = 1000L * localJSONObject1.getLong("change_goal_weight_time");
        float f6 = (float)localJSONObject1.getDouble("weight_change_target");
        String str4;
        if (f1 > 221.0F)
        {
          localRoleBin1 = selectRoleDB(paramContext, l1);
          if (localRoleBin1 == null)
            break label1460;
          if (localRoleBin1.getHeight() > 221.0F)
          {
            break label1460;
            localRoleBin2 = new RoleBin();
            localRoleBin2.setRole_id(l1);
            localRoleBin2.setName(str1);
            localRoleBin2.setHeight(f1);
            localRoleBin2.setSex(i2);
            localRoleBin2.setBirthday(str2);
            localRoleBin2.setTime(l2);
            localRoleBin2.setGoal_weight(f2);
            localRoleBin2.setHead_portrait_url(str3);
            localRoleBin2.setFirst_weight(f3);
            localRoleBin2.setFirst_fat(f5);
            localRoleBin2.setGoal_fat(f4);
            localRoleBin2.setFirst_use_time(l3);
            localRoleBin2.setChange_goal_weight_time(l4);
            localRoleBin2.setWeight_change_target(f6);
            localRoleBin2.setUser_id(paramLong);
            localRoleBin2.setFamily_type(localJSONObject1.getInt("is_remote"));
            str4 = localJSONObject1.getString("alias_name");
            localRoleBin2.setRemark_name(str4);
            localRoleBin2.setPhone_no(localJSONObject1.getString("phoneNumber"));
            localRoleBin2.setEmail(localJSONObject1.getString("email"));
            if (localJSONObject1.getLong("user_id") != paramLong)
              continue;
            localRoleBin2.setRemote_user_id(0L);
            localRoleBin2.setIs_new_family(false);
            AsyncMessageUtils.updateRoleMessage(paramContext, localRoleBin2, null);
          }
        }
        else
        {
          localContentValues1 = new ContentValues();
          localContentValues1.put("id", Long.valueOf(l1));
          localContentValues1.put("name", str1);
          localContentValues1.put("height", Float.valueOf(f1));
          localContentValues1.put("sex", Integer.valueOf(i2));
          localContentValues1.put("birthday", str2);
          localContentValues1.put("time", Long.valueOf(l2));
          localContentValues1.put("goal_weight", Float.valueOf(f2));
          localContentValues1.put("head_portrait_url", str3);
          localContentValues1.put("first_weight", Float.valueOf(f3));
          localContentValues1.put("first_use", Integer.valueOf(0));
          localContentValues1.put("goal_fat", Float.valueOf(f4));
          localContentValues1.put("first_fat", Float.valueOf(f5));
          localContentValues1.put("first_use_time", Long.valueOf(l3));
          localContentValues1.put("change_goal_weight_time", Long.valueOf(l4));
          localContentValues1.put("weight_change_target", Float.valueOf(f6));
          localContentValues1.put("user_id", Long.valueOf(paramLong));
          localContentValues1.put("family_type", Integer.valueOf(localJSONObject1.getInt("is_remote")));
          str5 = localJSONObject1.getString("alias_name");
          localContentValues1.put("remark_name", str5);
          localContentValues1.put("phone_no", localJSONObject1.getString("phoneNumber"));
          localContentValues1.put("email", localJSONObject1.getString("email"));
          if (localJSONObject1.getLong("user_id") != paramLong)
            break label1058;
          localContentValues1.put("remote_user_id", Integer.valueOf(0));
          localContentValues1.put("is_new_family", Boolean.valueOf(false));
          l5 = -1L;
          if (localArrayList.contains(Long.valueOf(l1)))
            break label1117;
          l5 = db.insert("role", null, localContentValues1);
          if (l5 <= 0L)
            break label1498;
          localArrayList.remove(Long.valueOf(l1));
          localJSONArray2 = localJSONObject1.getJSONArray("role_infos");
          l6 = queryLastTimeOnRoleInfosByRoleID(paramContext, l1);
          i3 = -1 + localJSONArray2.length();
          break label1473;
          label960: if (localRoleBin1.getSex() != 1)
            break label1484;
          f1 = 170.0F;
          continue;
        }
        f1 = localRoleBin1.getHeight();
        continue;
        localRoleBin2.setRemote_user_id(localJSONObject1.getLong("user_id"));
        if (!"".equals(str4))
        {
          localRoleBin2.setIs_new_family(false);
          continue;
        }
      }
      catch (JSONException localJSONException)
      {
        RoleBin localRoleBin2;
        localJSONException.printStackTrace();
        db.endTransaction();
        bool = false;
        continue;
        localRoleBin2.setIs_new_family(false);
        continue;
      }
      finally
      {
        db.endTransaction();
      }
      label1058: localContentValues1.put("remote_user_id", Long.valueOf(localJSONObject1.getLong("user_id")));
      label1187: if (!"".equals(str5))
      {
        localContentValues1.put("is_new_family", Boolean.valueOf(false));
      }
      else
      {
        localContentValues1.put("is_new_family", Boolean.valueOf(true));
        continue;
        label1117: if (localArrayList.contains(Long.valueOf(l1)))
        {
          SQLiteDatabase localSQLiteDatabase = db;
          String[] arrayOfString = new String[1];
          arrayOfString[0] = localJSONObject1.getLong("roleID");
          l5 = localSQLiteDatabase.update("role", localContentValues1, "id=?", arrayOfString);
          continue;
          JSONObject localJSONObject2 = localJSONArray2.getJSONObject(i3);
          long l7 = 1000L * localJSONObject2.getLong("time");
          if (l7 <= l6)
            break label1478;
          ContentValues localContentValues2 = new ContentValues();
          localContentValues2.put("role_id", Long.valueOf(localJSONObject2.getLong("role_id")));
          localContentValues2.put("height", Float.valueOf(100.0F * (float)localJSONObject2.getDouble("height")));
          localContentValues2.put("sex", Integer.valueOf(localJSONObject2.getInt("sex")));
          localContentValues2.put("birthday", DateUtils.changeOldTimeStringToNewTimeString(localJSONObject2.getString("birthday"), "yyyy-MM-dd", "yyyyMMdd"));
          localContentValues2.put("time", Long.valueOf(l7));
          localContentValues2.put("goal_weight", Float.valueOf((float)localJSONObject2.getDouble("goal_weight")));
          localContentValues2.put("goal_fat", Float.valueOf((float)localJSONObject2.getDouble("goal_fat")));
          localContentValues2.put("change_goal_weight_time", Long.valueOf(1000L * localJSONObject2.getLong("change_goal_weight_time")));
          localContentValues2.put("weight_change_target", Float.valueOf((float)localJSONObject2.getDouble("weight_change_target")));
          if (db.insert("role_infos", null, localContentValues2) > 0L)
            break label1492;
          bool = false;
          break label1478;
          deleteRoleByRoleId(paramContext, ((Long)localArrayList.get(n)).longValue());
          n++;
        }
      }
    }
    while (true)
    {
      n = 0;
      break label83;
      label1460: if (localRoleBin1 != null)
        break label960;
      f1 = 165.0F;
      break label365;
      while (true)
      {
        label1473: if (i3 >= 0)
          break label1496;
        label1478: k++;
        break;
        label1484: f1 = 160.0F;
        break label365;
        label1492: i3--;
      }
      label1496: break label1187;
      label1498: bool = false;
    }
  }

  public static void updateBackSendMessage(Context paramContext, long paramLong, String paramString)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "update invitation_infos set is_already_read=0,send_status_code=2 where flag= 1 and remote_uid=" + paramString + " and user_id=" + paramLong;
    db.execSQL(str);
    db.close();
  }

  public static void updateBodyIndexAfterUploadToServer(Context paramContext, BodyIndex paramBodyIndex)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("flag", Integer.valueOf(1));
    localContentValues.put("id_in_server", Long.valueOf(paramBodyIndex.getId_in_server()));
    SQLiteDatabase localSQLiteDatabase = db;
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramBodyIndex.getId();
    localSQLiteDatabase.update("BodyIndex", localContentValues, "id = ?", arrayOfString);
    db.close();
  }

  public static void updateBodyIndexDB(Context paramContext, BodyIndex paramBodyIndex)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "update BodyIndex set weight=" + paramBodyIndex.getWeight() + "," + "body_fat=" + paramBodyIndex.getBodyFat() + "," + "viseral_fat_level=" + paramBodyIndex.getViser_fat_level() + "," + "muscle_race=" + paramBodyIndex.getMusde_race() + "," + "body_age=" + paramBodyIndex.getBodyAge() + "," + "bone_mass=" + paramBodyIndex.getBone_mass() + "," + "basic_metabolism=" + paramBodyIndex.getBasic_metabolism() + "," + "bmi=" + paramBodyIndex.getBmi() + "," + "flag=" + paramBodyIndex.getFlag() + "," + "time=" + paramBodyIndex.getTime() + "," + "water_race=" + paramBodyIndex.getWater_race() + " where role_id=" + paramBodyIndex.getRole_id();
    db.execSQL(str);
  }

  public static void updateDietAndNutritionPrincipleDataById(Context paramContext, int paramInt, String paramString)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "update DietAndNutritionPrinciple set message = '" + paramString + "'  where id = " + paramInt;
    db.execSQL(str);
    db.close();
  }

  public static void updateFeedback(Context paramContext, FeedBackItem paramFeedBackItem)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("serverID", Integer.valueOf(paramFeedBackItem.serverID));
    long l = ((MyApplication)paramContext.getApplicationContext()).getUser_id();
    SQLiteDatabase localSQLiteDatabase = db;
    String[] arrayOfString = new String[3];
    arrayOfString[0] = l;
    arrayOfString[1] = paramFeedBackItem.mes;
    arrayOfString[2] = paramFeedBackItem.id;
    int i = localSQLiteDatabase.update("FeedbackMessage", localContentValues, "fromUserID = ? and message = ? and id = ?;", arrayOfString);
    System.out.println("updateFeedback=====" + i);
    db.close();
  }

  public static void updateInvitation_infos(Context paramContext, InvitationInfos paramInvitationInfos)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("time", Long.valueOf(paramInvitationInfos.getTime()));
    localContentValues.put("receive_from_email", paramInvitationInfos.getReceive_from_email());
    localContentValues.put("receive_from_name", paramInvitationInfos.getReceive_from_name());
    localContentValues.put("receive_message", paramInvitationInfos.getReceive_message());
    localContentValues.put("receive_from_head_url", paramInvitationInfos.getReceive_from_head_url());
    localContentValues.put("is_already_read", Integer.valueOf(paramInvitationInfos.getIs_already_read()));
    localContentValues.put("send_status_code", Integer.valueOf(paramInvitationInfos.getSend_status_code()));
    localContentValues.put("send_email", paramInvitationInfos.getSend_email());
    localContentValues.put("send_message", paramInvitationInfos.getSend_message());
    localContentValues.put("receive_from_sex", Boolean.valueOf(paramInvitationInfos.getReceive_from_sex()));
    localContentValues.put("receive_from_phone", paramInvitationInfos.getReceive_from_phone());
    localContentValues.put("send_phone", paramInvitationInfos.getSend_phone());
    SQLiteDatabase localSQLiteDatabase = db;
    String[] arrayOfString = new String[2];
    arrayOfString[0] = paramInvitationInfos.getUser_id();
    arrayOfString[1] = paramInvitationInfos.getRemote_uid();
    localSQLiteDatabase.update("invitation_infos", localContentValues, "user_id = ? and remote_uid = ? and flag=1", arrayOfString);
    db.close();
  }

  public static void updateInvitation_infosParser(Context paramContext, InvitationInfos paramInvitationInfos)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("time", Long.valueOf(paramInvitationInfos.getTime()));
    localContentValues.put("receive_from_email", paramInvitationInfos.getReceive_from_email());
    localContentValues.put("receive_from_name", paramInvitationInfos.getReceive_from_name());
    localContentValues.put("receive_message", paramInvitationInfos.getReceive_message());
    localContentValues.put("receive_from_head_url", paramInvitationInfos.getReceive_from_head_url());
    localContentValues.put("send_status_code", Integer.valueOf(paramInvitationInfos.getSend_status_code()));
    localContentValues.put("send_email", paramInvitationInfos.getSend_email());
    localContentValues.put("send_message", paramInvitationInfos.getSend_message());
    localContentValues.put("receive_from_sex", Boolean.valueOf(paramInvitationInfos.getReceive_from_sex()));
    localContentValues.put("receive_from_phone", paramInvitationInfos.getReceive_from_phone());
    localContentValues.put("send_phone", paramInvitationInfos.getSend_phone());
    SQLiteDatabase localSQLiteDatabase = db;
    String[] arrayOfString = new String[2];
    arrayOfString[0] = paramInvitationInfos.getUser_id();
    arrayOfString[1] = paramInvitationInfos.getRemote_uid();
    localSQLiteDatabase.update("invitation_infos", localContentValues, "user_id = ? and remote_uid = ? and flag=1", arrayOfString);
    db.close();
  }

  public static void updatePhone_book(Context paramContext, String paramString, int paramInt)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "update phone_book set is_already_my_role=" + paramInt + " where user_id=" + paramString;
    db.execSQL(str);
    db.close();
  }

  public static void updateRecevieMessage(Context paramContext, String paramString, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "update invitation_infos set is_already_read=1 where user_id=" + paramString + " and flag=0 and remote_uid=" + paramLong;
    db.execSQL(str);
    db.close();
  }

  public static int updateRoleDB(Context paramContext, RoleBin paramRoleBin)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("name", paramRoleBin.getName());
    localContentValues.put("height", Float.valueOf(paramRoleBin.getHeight()));
    localContentValues.put("sex", Integer.valueOf(paramRoleBin.getSex()));
    localContentValues.put("birthday", paramRoleBin.getBirthday());
    localContentValues.put("time", Long.valueOf(paramRoleBin.getTime()));
    localContentValues.put("goal_weight", Float.valueOf(paramRoleBin.getGoal_weight()));
    localContentValues.put("head_portrait_url", paramRoleBin.getHead_portrait_url());
    localContentValues.put("first_use", Boolean.valueOf(paramRoleBin.getFirst_use()));
    localContentValues.put("first_weight", Float.valueOf(paramRoleBin.getFirst_weight()));
    localContentValues.put("goal_fat", Float.valueOf(paramRoleBin.getGoal_fat()));
    localContentValues.put("first_fat", Float.valueOf(paramRoleBin.getFirst_fat()));
    localContentValues.put("first_use_time", Long.valueOf(paramRoleBin.getFirst_use_time()));
    localContentValues.put("change_goal_weight_time", Long.valueOf(paramRoleBin.getChange_goal_weight_time()));
    localContentValues.put("weight_change_target", Float.valueOf(paramRoleBin.getWeight_change_target()));
    localContentValues.put("family_type", Integer.valueOf(paramRoleBin.getFamily_type()));
    localContentValues.put("remark_name", paramRoleBin.getRemark_name());
    localContentValues.put("phone_no", paramRoleBin.getPhone_no());
    localContentValues.put("email", paramRoleBin.getEmail());
    localContentValues.put("remote_user_id", Long.valueOf(paramRoleBin.getRemote_user_id()));
    localContentValues.put("is_new_family", Boolean.valueOf(paramRoleBin.isIs_new_family()));
    SQLiteDatabase localSQLiteDatabase = db;
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramRoleBin.getRole_id();
    int i = localSQLiteDatabase.update("role", localContentValues, "id = ?", arrayOfString);
    db.close();
    return i;
  }

  public static void updateRoleUrl(Context paramContext, String paramString1, String paramString2)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "update role set head_portrait_url='" + paramString2 + "' where id='" + paramString1 + "'";
    db.execSQL(str);
  }

  public static void updateSendMessage(Context paramContext, long paramLong)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "update invitation_infos set is_already_read=1 where flag= 1 and send_status_code=1 or send_status_code=2 and user_id=" + paramLong;
    db.execSQL(str);
    db.close();
  }

  public static long updateUserDB(Context paramContext, UserBin paramUserBin)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("email", paramUserBin.getEmail());
    localContentValues.put("time", Long.valueOf(paramUserBin.getTime()));
    localContentValues.put("weibo_id", paramUserBin.getWeibo_id());
    localContentValues.put("qq_id", paramUserBin.getQQ_id());
    localContentValues.put("dayima_id", paramUserBin.getDayima_id());
    localContentValues.put("phone_no", paramUserBin.getPhone_no());
    localContentValues.put("baidu_id", paramUserBin.getBaidu_id());
    localContentValues.put("role_id", Long.valueOf(paramUserBin.getRole_id()));
    localContentValues.put("has_password", Boolean.valueOf(paramUserBin.isHas_password()));
    localContentValues.put("jd_id", paramUserBin.getJd_id());
    localContentValues.put("leyu_id", paramUserBin.getLy_id());
    SQLiteDatabase localSQLiteDatabase = db;
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramUserBin.getUser_id();
    long l = localSQLiteDatabase.update("Users", localContentValues, "user_id = ?", arrayOfString);
    db.close();
    return l;
  }

  public static void updateUserThirdPartID(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    db = DBHelper.getInstance(paramContext).openDatabase();
    String str = "update Users set " + paramString1 + "='" + paramString3 + "' where user_id='" + paramString2 + "'";
    db.execSQL(str);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     OperationDB
 * JD-Core Version:    0.6.2
 */
