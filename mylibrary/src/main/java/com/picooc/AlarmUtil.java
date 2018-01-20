package com.picooc;

public class AlarmUtil
{
  // ERROR //
  public static void getToRectTask(android.content.Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 14
    //   3: invokevirtual 20	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   6: checkcast 22	android/app/ActivityManager
    //   9: astore_1
    //   10: aload_0
    //   11: invokevirtual 26	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   14: astore_2
    //   15: aload_1
    //   16: bipush 64
    //   18: iconst_0
    //   19: invokevirtual 30	android/app/ActivityManager:getRecentTasks	(II)Ljava/util/List;
    //   22: invokeinterface 36 1 0
    //   27: astore 4
    //   29: aload 4
    //   31: invokeinterface 42 1 0
    //   36: istore 5
    //   38: iload 5
    //   40: ifne +37 -> 77
    //   43: aconst_null
    //   44: astore 8
    //   46: aload 8
    //   48: ifnonnull +84 -> 132
    //   51: new 44	android/content/Intent
    //   54: dup
    //   55: aload_0
    //   56: ldc 46
    //   58: invokespecial 49	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   61: astore 10
    //   63: ldc 51
    //   65: ldc 53
    //   67: invokestatic 59	com/umeng/common/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   70: aload_0
    //   71: aload 10
    //   73: invokevirtual 63	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   76: return
    //   77: aload 4
    //   79: invokeinterface 67 1 0
    //   84: checkcast 69	android/app/ActivityManager$RecentTaskInfo
    //   87: getfield 73	android/app/ActivityManager$RecentTaskInfo:baseIntent	Landroid/content/Intent;
    //   90: astore 6
    //   92: aload_2
    //   93: aload 6
    //   95: iconst_0
    //   96: invokevirtual 79	android/content/pm/PackageManager:resolveActivity	(Landroid/content/Intent;I)Landroid/content/pm/ResolveInfo;
    //   99: astore 7
    //   101: aload_0
    //   102: invokevirtual 83	android/content/Context:getPackageName	()Ljava/lang/String;
    //   105: aload 7
    //   107: getfield 89	android/content/pm/ResolveInfo:activityInfo	Landroid/content/pm/ActivityInfo;
    //   110: getfield 95	android/content/pm/ActivityInfo:packageName	Ljava/lang/String;
    //   113: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   116: ifeq -87 -> 29
    //   119: aload_0
    //   120: aload 6
    //   122: invokevirtual 63	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   125: aload 6
    //   127: astore 8
    //   129: goto -83 -> 46
    //   132: ldc 51
    //   134: ldc 103
    //   136: invokestatic 59	com/umeng/common/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   139: aload 8
    //   141: astore 10
    //   143: goto -73 -> 70
    //   146: astore_3
    //   147: aload_3
    //   148: invokevirtual 106	java/lang/SecurityException:printStackTrace	()V
    //   151: return
    //   152: astore_3
    //   153: aload 8
    //   155: pop
    //   156: goto -9 -> 147
    //
    // Exception table:
    //   from	to	target	type
    //   15	29	146	java/lang/SecurityException
    //   29	38	146	java/lang/SecurityException
    //   63	70	146	java/lang/SecurityException
    //   70	76	146	java/lang/SecurityException
    //   77	125	146	java/lang/SecurityException
    //   51	63	152	java/lang/SecurityException
    //   132	139	152	java/lang/SecurityException
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     AlarmUtil
 * JD-Core Version:    0.6.2
 */
