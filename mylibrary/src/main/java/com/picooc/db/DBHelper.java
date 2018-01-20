package com.picooc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class DBHelper
{
  private static DBHelper mInstance;
  private final String DATABASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/picooc";
  private SQLiteDatabase db2;
  private final String db_path_last = this.db_path_v11;
  private final String db_path_v08 = this.DATABASE_PATH + "/jokebook.db3";
  private final String db_path_v09 = this.DATABASE_PATH + "/picooc09.db3";
  private final String db_path_v11 = this.DATABASE_PATH + "/picooc11.db3";
  private final Context mContext;
  private Boolean mIsInitializing = Boolean.valueOf(false);

  public DBHelper(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public static DBHelper getInstance(Context paramContext)
  {
    try
    {
      if (mInstance == null)
        mInstance = new DBHelper(paramContext);
      DBHelper localDBHelper = mInstance;
      return localDBHelper;
    }
    finally
    {
    }
  }

  private void syncTableBodyIndex(SQLiteDatabase paramSQLiteDatabase1, SQLiteDatabase paramSQLiteDatabase2)
  {
    Cursor localCursor = paramSQLiteDatabase1.rawQuery("SELECT * FROM BodyIndex", null);
    while (true)
    {
      if (!localCursor.moveToNext())
        return;
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("id", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("id"))));
      localContentValues.put("weight", Float.valueOf(localCursor.getFloat(localCursor.getColumnIndex("weight"))));
      localContentValues.put("body_fat", Float.valueOf(localCursor.getFloat(localCursor.getColumnIndex("body_fat"))));
      localContentValues.put("viseral_fat_level", Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("viseral_fat_level"))));
      localContentValues.put("muscle_race", Float.valueOf(localCursor.getFloat(localCursor.getColumnIndex("muscle_race"))));
      localContentValues.put("body_age", Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("body_age"))));
      localContentValues.put("bone_mass", Float.valueOf(localCursor.getFloat(localCursor.getColumnIndex("bone_mass"))));
      localContentValues.put("basic_metabolism", Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("basic_metabolism"))));
      localContentValues.put("bmi", Float.valueOf(localCursor.getFloat(localCursor.getColumnIndex("bmi"))));
      localContentValues.put("time", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("time"))));
      localContentValues.put("role_id", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("role_id"))));
      localContentValues.put("flag", Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("flag"))));
      localContentValues.put("water_race", Float.valueOf(localCursor.getFloat(localCursor.getColumnIndex("water_race"))));
      localContentValues.put("fat_under_skin", Float.valueOf(localCursor.getFloat(localCursor.getColumnIndex("fat_under_skin"))));
      paramSQLiteDatabase2.insert("BodyIndex", null, localContentValues);
    }
  }

  private void syncTablePicoocIndex(SQLiteDatabase paramSQLiteDatabase1, SQLiteDatabase paramSQLiteDatabase2)
  {
    Cursor localCursor = paramSQLiteDatabase1.rawQuery("SELECT * FROM picoocIndex", null);
    while (true)
    {
      if (!localCursor.moveToNext())
        return;
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("id", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("id"))));
      localContentValues.put("weight_decrease", Float.valueOf(localCursor.getFloat(localCursor.getColumnIndex("weight_decrease"))));
      localContentValues.put("mood", Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("mood"))));
      localContentValues.put("drink", Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("drink"))));
      localContentValues.put("vegetable_and_fruit", Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("vegetable_and_fruit"))));
      localContentValues.put("sport", Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("sport"))));
      localContentValues.put("sleep", Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("sleep"))));
      localContentValues.put("physiology", Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("physiology"))));
      localContentValues.put("num", Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("num"))));
      localContentValues.put("flag", Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("flag"))));
      localContentValues.put("role_id", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("role_id"))));
      localContentValues.put("time", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("time"))));
      paramSQLiteDatabase2.insert("picoocIndex", null, localContentValues);
    }
  }

  private void syncTableRole(SQLiteDatabase paramSQLiteDatabase1, SQLiteDatabase paramSQLiteDatabase2)
  {
    Cursor localCursor = paramSQLiteDatabase1.rawQuery("SELECT * FROM role", null);
    while (true)
    {
      if (!localCursor.moveToNext())
        return;
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("id", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("id"))));
      localContentValues.put("name", localCursor.getString(localCursor.getColumnIndex("name")));
      localContentValues.put("height", Float.valueOf(localCursor.getFloat(localCursor.getColumnIndex("height"))));
      localContentValues.put("sex", Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("sex"))));
      localContentValues.put("birthday", localCursor.getString(localCursor.getColumnIndex("birthday")));
      localContentValues.put("time", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("time"))));
      localContentValues.put("user_id", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("user_id"))));
      localContentValues.put("goal_weight", Float.valueOf(localCursor.getFloat(localCursor.getColumnIndex("goal_weight"))));
      localContentValues.put("head_portrait_url", localCursor.getString(localCursor.getColumnIndex("head_portrait_url")));
      localContentValues.put("first_weight", Float.valueOf(localCursor.getFloat(localCursor.getColumnIndex("first_weight"))));
      localContentValues.put("first_use", Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("first_use"))));
      localContentValues.put("goal_fat", Float.valueOf(localCursor.getFloat(localCursor.getColumnIndex("goal_fat"))));
      localContentValues.put("first_fat", Float.valueOf(localCursor.getFloat(localCursor.getColumnIndex("first_fat"))));
      localContentValues.put("first_use_time", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("first_use_time"))));
      localContentValues.put("change_goal_weight_time", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("change_goal_weight_time"))));
      localContentValues.put("weight_change_target", Float.valueOf(localCursor.getFloat(localCursor.getColumnIndex("weight_change_target"))));
      localContentValues.put("remark_name", localCursor.getString(localCursor.getColumnIndex("remark_name")));
      localContentValues.put("family_type", Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("family_type"))));
      localContentValues.put("is_new_family", Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("is_new_family"))));
      localContentValues.put("email", localCursor.getString(localCursor.getColumnIndex("email")));
      localContentValues.put("remote_user_id", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("remote_user_id"))));
      localContentValues.put("phone_no", "");
      paramSQLiteDatabase2.insert("role", null, localContentValues);
    }
  }

  private void syncTableRoleInfos(SQLiteDatabase paramSQLiteDatabase1, SQLiteDatabase paramSQLiteDatabase2)
  {
    Cursor localCursor = paramSQLiteDatabase1.rawQuery("SELECT * FROM role_infos", null);
    while (true)
    {
      if (!localCursor.moveToNext())
        return;
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("role_id", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("role_id"))));
      localContentValues.put("height", Float.valueOf(localCursor.getFloat(localCursor.getColumnIndex("height"))));
      localContentValues.put("sex", Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("sex"))));
      localContentValues.put("birthday", localCursor.getString(localCursor.getColumnIndex("birthday")));
      localContentValues.put("time", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("time"))));
      localContentValues.put("goal_weight", Float.valueOf(localCursor.getFloat(localCursor.getColumnIndex("goal_weight"))));
      localContentValues.put("goal_fat", Float.valueOf(localCursor.getFloat(localCursor.getColumnIndex("goal_fat"))));
      localContentValues.put("change_goal_weight_time", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("change_goal_weight_time"))));
      localContentValues.put("record_id", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("record_id"))));
      localContentValues.put("weight_change_target", Float.valueOf(localCursor.getFloat(localCursor.getColumnIndex("weight_change_target"))));
      paramSQLiteDatabase2.insert("role_infos", null, localContentValues);
    }
  }

  private void syncTableUser(SQLiteDatabase paramSQLiteDatabase1, SQLiteDatabase paramSQLiteDatabase2)
  {
    while (true)
    {
      ContentValues localContentValues;
      try
      {
        Cursor localCursor = paramSQLiteDatabase1.rawQuery("SELECT * FROM Users", null);
        boolean bool = localCursor.moveToNext();
        if (!bool)
          return;
        localContentValues = new ContentValues();
        localContentValues.put("user_id", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("user_id"))));
        String str = localCursor.getString(localCursor.getColumnIndex("email"));
        localContentValues.put("email", str);
        localContentValues.put("time", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("time"))));
        localContentValues.put("role_id", Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("role_id"))));
        localContentValues.put("weibo_id", localCursor.getString(localCursor.getColumnIndex("weibo_id")));
        localContentValues.put("qq_id", localCursor.getString(localCursor.getColumnIndex("qq_id")));
        localContentValues.put("dayima_id", localCursor.getString(localCursor.getColumnIndex("dayima_id")));
        localContentValues.put("phone_no", localCursor.getString(localCursor.getColumnIndex("phone_no")));
        localContentValues.put("baidu_id", localCursor.getString(localCursor.getColumnIndex("baidu_id")));
        if ((str != null) && (!str.equals("")) && (!"null".equals(str)))
        {
          localContentValues.put("has_password", Boolean.valueOf(true));
          paramSQLiteDatabase2.insert("Users", null, localContentValues);
          continue;
        }
      }
      finally
      {
      }
      localContentValues.put("has_password", Boolean.valueOf(false));
    }
  }

  // ERROR //
  public SQLiteDatabase openDatabase()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 33	java/io/File
    //   5: dup
    //   6: aload_0
    //   7: getfield 57	com/picooc/db/DBHelper:DATABASE_PATH	Ljava/lang/String;
    //   10: invokespecial 291	java/io/File:<init>	(Ljava/lang/String;)V
    //   13: astore_1
    //   14: aload_1
    //   15: invokevirtual 294	java/io/File:exists	()Z
    //   18: ifne +8 -> 26
    //   21: aload_1
    //   22: invokevirtual 297	java/io/File:mkdir	()Z
    //   25: pop
    //   26: new 33	java/io/File
    //   29: dup
    //   30: aload_0
    //   31: getfield 78	com/picooc/db/DBHelper:db_path_last	Ljava/lang/String;
    //   34: invokespecial 291	java/io/File:<init>	(Ljava/lang/String;)V
    //   37: invokevirtual 294	java/io/File:exists	()Z
    //   40: ifne +62 -> 102
    //   43: aload_0
    //   44: getfield 80	com/picooc/db/DBHelper:mContext	Landroid/content/Context;
    //   47: invokevirtual 303	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   50: ldc_w 304
    //   53: invokevirtual 310	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   56: astore 17
    //   58: new 312	java/io/FileOutputStream
    //   61: dup
    //   62: aload_0
    //   63: getfield 78	com/picooc/db/DBHelper:db_path_last	Ljava/lang/String;
    //   66: invokespecial 313	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   69: astore 18
    //   71: sipush 8192
    //   74: newarray byte
    //   76: astore 19
    //   78: aload 17
    //   80: aload 19
    //   82: invokevirtual 319	java/io/InputStream:read	([B)I
    //   85: istore 20
    //   87: iload 20
    //   89: ifgt +158 -> 247
    //   92: aload 18
    //   94: invokevirtual 322	java/io/FileOutputStream:close	()V
    //   97: aload 17
    //   99: invokevirtual 323	java/io/InputStream:close	()V
    //   102: new 33	java/io/File
    //   105: dup
    //   106: aload_0
    //   107: getfield 68	com/picooc/db/DBHelper:db_path_v08	Ljava/lang/String;
    //   110: invokespecial 291	java/io/File:<init>	(Ljava/lang/String;)V
    //   113: invokevirtual 294	java/io/File:exists	()Z
    //   116: ifeq +185 -> 301
    //   119: aload_0
    //   120: getfield 68	com/picooc/db/DBHelper:db_path_v08	Ljava/lang/String;
    //   123: aconst_null
    //   124: invokestatic 327	android/database/sqlite/SQLiteDatabase:openOrCreateDatabase	(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;)Landroid/database/sqlite/SQLiteDatabase;
    //   127: astore 12
    //   129: aload_0
    //   130: getfield 78	com/picooc/db/DBHelper:db_path_last	Ljava/lang/String;
    //   133: aconst_null
    //   134: invokestatic 327	android/database/sqlite/SQLiteDatabase:openOrCreateDatabase	(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;)Landroid/database/sqlite/SQLiteDatabase;
    //   137: astore 13
    //   139: aload 13
    //   141: invokevirtual 330	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   144: aload_0
    //   145: aload 12
    //   147: aload 13
    //   149: invokespecial 332	com/picooc/db/DBHelper:syncTableUser	(Landroid/database/sqlite/SQLiteDatabase;Landroid/database/sqlite/SQLiteDatabase;)V
    //   152: aload_0
    //   153: aload 12
    //   155: aload 13
    //   157: invokespecial 334	com/picooc/db/DBHelper:syncTableRole	(Landroid/database/sqlite/SQLiteDatabase;Landroid/database/sqlite/SQLiteDatabase;)V
    //   160: aload_0
    //   161: aload 12
    //   163: aload 13
    //   165: invokespecial 336	com/picooc/db/DBHelper:syncTableRoleInfos	(Landroid/database/sqlite/SQLiteDatabase;Landroid/database/sqlite/SQLiteDatabase;)V
    //   168: aload_0
    //   169: aload 12
    //   171: aload 13
    //   173: invokespecial 338	com/picooc/db/DBHelper:syncTableBodyIndex	(Landroid/database/sqlite/SQLiteDatabase;Landroid/database/sqlite/SQLiteDatabase;)V
    //   176: aload_0
    //   177: aload 12
    //   179: aload 13
    //   181: invokespecial 340	com/picooc/db/DBHelper:syncTablePicoocIndex	(Landroid/database/sqlite/SQLiteDatabase;Landroid/database/sqlite/SQLiteDatabase;)V
    //   184: aload 13
    //   186: invokevirtual 343	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   189: aload 13
    //   191: invokevirtual 346	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   194: new 33	java/io/File
    //   197: dup
    //   198: aload_0
    //   199: getfield 68	com/picooc/db/DBHelper:db_path_v08	Ljava/lang/String;
    //   202: invokespecial 291	java/io/File:<init>	(Ljava/lang/String;)V
    //   205: invokevirtual 349	java/io/File:delete	()Z
    //   208: pop
    //   209: aload 12
    //   211: invokevirtual 350	android/database/sqlite/SQLiteDatabase:close	()V
    //   214: aload 13
    //   216: invokevirtual 350	android/database/sqlite/SQLiteDatabase:close	()V
    //   219: aload_0
    //   220: getfield 352	com/picooc/db/DBHelper:db2	Landroid/database/sqlite/SQLiteDatabase;
    //   223: ifnull +223 -> 446
    //   226: aload_0
    //   227: getfield 352	com/picooc/db/DBHelper:db2	Landroid/database/sqlite/SQLiteDatabase;
    //   230: invokevirtual 355	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   233: ifeq +213 -> 446
    //   236: aload_0
    //   237: getfield 352	com/picooc/db/DBHelper:db2	Landroid/database/sqlite/SQLiteDatabase;
    //   240: astore 4
    //   242: aload_0
    //   243: monitorexit
    //   244: aload 4
    //   246: areturn
    //   247: aload 18
    //   249: aload 19
    //   251: iconst_0
    //   252: iload 20
    //   254: invokevirtual 359	java/io/FileOutputStream:write	([BII)V
    //   257: goto -179 -> 78
    //   260: astore_3
    //   261: aload_3
    //   262: invokevirtual 362	java/lang/Exception:printStackTrace	()V
    //   265: aconst_null
    //   266: astore 4
    //   268: goto -26 -> 242
    //   271: astore 15
    //   273: aload 15
    //   275: invokevirtual 362	java/lang/Exception:printStackTrace	()V
    //   278: aload 13
    //   280: invokevirtual 346	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   283: goto -89 -> 194
    //   286: astore_2
    //   287: aload_0
    //   288: monitorexit
    //   289: aload_2
    //   290: athrow
    //   291: astore 14
    //   293: aload 13
    //   295: invokevirtual 346	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   298: aload 14
    //   300: athrow
    //   301: new 33	java/io/File
    //   304: dup
    //   305: aload_0
    //   306: getfield 72	com/picooc/db/DBHelper:db_path_v09	Ljava/lang/String;
    //   309: invokespecial 291	java/io/File:<init>	(Ljava/lang/String;)V
    //   312: invokevirtual 294	java/io/File:exists	()Z
    //   315: ifeq -96 -> 219
    //   318: aload_0
    //   319: getfield 72	com/picooc/db/DBHelper:db_path_v09	Ljava/lang/String;
    //   322: aconst_null
    //   323: invokestatic 327	android/database/sqlite/SQLiteDatabase:openOrCreateDatabase	(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;)Landroid/database/sqlite/SQLiteDatabase;
    //   326: astore 5
    //   328: aload_0
    //   329: getfield 78	com/picooc/db/DBHelper:db_path_last	Ljava/lang/String;
    //   332: aconst_null
    //   333: invokestatic 327	android/database/sqlite/SQLiteDatabase:openOrCreateDatabase	(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;)Landroid/database/sqlite/SQLiteDatabase;
    //   336: astore 6
    //   338: aload 6
    //   340: invokevirtual 330	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   343: aload_0
    //   344: aload 5
    //   346: aload 6
    //   348: invokespecial 332	com/picooc/db/DBHelper:syncTableUser	(Landroid/database/sqlite/SQLiteDatabase;Landroid/database/sqlite/SQLiteDatabase;)V
    //   351: aload_0
    //   352: aload 5
    //   354: aload 6
    //   356: invokespecial 334	com/picooc/db/DBHelper:syncTableRole	(Landroid/database/sqlite/SQLiteDatabase;Landroid/database/sqlite/SQLiteDatabase;)V
    //   359: aload_0
    //   360: aload 5
    //   362: aload 6
    //   364: invokespecial 336	com/picooc/db/DBHelper:syncTableRoleInfos	(Landroid/database/sqlite/SQLiteDatabase;Landroid/database/sqlite/SQLiteDatabase;)V
    //   367: aload_0
    //   368: aload 5
    //   370: aload 6
    //   372: invokespecial 338	com/picooc/db/DBHelper:syncTableBodyIndex	(Landroid/database/sqlite/SQLiteDatabase;Landroid/database/sqlite/SQLiteDatabase;)V
    //   375: aload_0
    //   376: aload 5
    //   378: aload 6
    //   380: invokespecial 340	com/picooc/db/DBHelper:syncTablePicoocIndex	(Landroid/database/sqlite/SQLiteDatabase;Landroid/database/sqlite/SQLiteDatabase;)V
    //   383: aload 6
    //   385: invokevirtual 343	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   388: aload 6
    //   390: invokevirtual 346	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   393: new 33	java/io/File
    //   396: dup
    //   397: aload_0
    //   398: getfield 72	com/picooc/db/DBHelper:db_path_v09	Ljava/lang/String;
    //   401: invokespecial 291	java/io/File:<init>	(Ljava/lang/String;)V
    //   404: invokevirtual 349	java/io/File:delete	()Z
    //   407: pop
    //   408: aload 5
    //   410: invokevirtual 350	android/database/sqlite/SQLiteDatabase:close	()V
    //   413: aload 6
    //   415: invokevirtual 350	android/database/sqlite/SQLiteDatabase:close	()V
    //   418: goto -199 -> 219
    //   421: astore 8
    //   423: aload 8
    //   425: invokevirtual 362	java/lang/Exception:printStackTrace	()V
    //   428: aload 6
    //   430: invokevirtual 346	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   433: goto -40 -> 393
    //   436: astore 7
    //   438: aload 6
    //   440: invokevirtual 346	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   443: aload 7
    //   445: athrow
    //   446: aload_0
    //   447: getfield 64	com/picooc/db/DBHelper:mIsInitializing	Ljava/lang/Boolean;
    //   450: invokevirtual 365	java/lang/Boolean:booleanValue	()Z
    //   453: ifeq +14 -> 467
    //   456: new 367	java/lang/IllegalStateException
    //   459: dup
    //   460: ldc_w 369
    //   463: invokespecial 370	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   466: athrow
    //   467: aconst_null
    //   468: astore 10
    //   470: aload_0
    //   471: iconst_1
    //   472: invokestatic 62	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   475: putfield 64	com/picooc/db/DBHelper:mIsInitializing	Ljava/lang/Boolean;
    //   478: aload_0
    //   479: getfield 78	com/picooc/db/DBHelper:db_path_last	Ljava/lang/String;
    //   482: aconst_null
    //   483: invokestatic 327	android/database/sqlite/SQLiteDatabase:openOrCreateDatabase	(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;)Landroid/database/sqlite/SQLiteDatabase;
    //   486: astore 10
    //   488: aload_0
    //   489: aload 10
    //   491: putfield 352	com/picooc/db/DBHelper:db2	Landroid/database/sqlite/SQLiteDatabase;
    //   494: aload_0
    //   495: getfield 352	com/picooc/db/DBHelper:db2	Landroid/database/sqlite/SQLiteDatabase;
    //   498: astore 4
    //   500: aload_0
    //   501: iconst_0
    //   502: invokestatic 62	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   505: putfield 64	com/picooc/db/DBHelper:mIsInitializing	Ljava/lang/Boolean;
    //   508: aload 10
    //   510: ifnull -268 -> 242
    //   513: aload 10
    //   515: aload_0
    //   516: getfield 352	com/picooc/db/DBHelper:db2	Landroid/database/sqlite/SQLiteDatabase;
    //   519: if_acmpeq -277 -> 242
    //   522: aload 10
    //   524: invokevirtual 350	android/database/sqlite/SQLiteDatabase:close	()V
    //   527: goto -285 -> 242
    //   530: astore 11
    //   532: aload_0
    //   533: iconst_0
    //   534: invokestatic 62	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   537: putfield 64	com/picooc/db/DBHelper:mIsInitializing	Ljava/lang/Boolean;
    //   540: aload 10
    //   542: ifnull +17 -> 559
    //   545: aload 10
    //   547: aload_0
    //   548: getfield 352	com/picooc/db/DBHelper:db2	Landroid/database/sqlite/SQLiteDatabase;
    //   551: if_acmpeq +8 -> 559
    //   554: aload 10
    //   556: invokevirtual 350	android/database/sqlite/SQLiteDatabase:close	()V
    //   559: aload 11
    //   561: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	26	260	java/lang/Exception
    //   26	78	260	java/lang/Exception
    //   78	87	260	java/lang/Exception
    //   92	102	260	java/lang/Exception
    //   102	139	260	java/lang/Exception
    //   189	194	260	java/lang/Exception
    //   194	219	260	java/lang/Exception
    //   219	242	260	java/lang/Exception
    //   247	257	260	java/lang/Exception
    //   278	283	260	java/lang/Exception
    //   293	301	260	java/lang/Exception
    //   301	338	260	java/lang/Exception
    //   388	393	260	java/lang/Exception
    //   393	418	260	java/lang/Exception
    //   428	433	260	java/lang/Exception
    //   438	446	260	java/lang/Exception
    //   446	467	260	java/lang/Exception
    //   500	508	260	java/lang/Exception
    //   513	527	260	java/lang/Exception
    //   532	540	260	java/lang/Exception
    //   545	559	260	java/lang/Exception
    //   559	562	260	java/lang/Exception
    //   139	189	271	java/lang/Exception
    //   2	26	286	finally
    //   26	78	286	finally
    //   78	87	286	finally
    //   92	102	286	finally
    //   102	139	286	finally
    //   189	194	286	finally
    //   194	219	286	finally
    //   219	242	286	finally
    //   247	257	286	finally
    //   261	265	286	finally
    //   278	283	286	finally
    //   293	301	286	finally
    //   301	338	286	finally
    //   388	393	286	finally
    //   393	418	286	finally
    //   428	433	286	finally
    //   438	446	286	finally
    //   446	467	286	finally
    //   500	508	286	finally
    //   513	527	286	finally
    //   532	540	286	finally
    //   545	559	286	finally
    //   559	562	286	finally
    //   139	189	291	finally
    //   273	278	291	finally
    //   338	388	421	java/lang/Exception
    //   338	388	436	finally
    //   423	428	436	finally
    //   470	500	530	finally
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     DBHelper
 * JD-Core Version:    0.6.2
 */
