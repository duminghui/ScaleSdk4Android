//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//                   _ooOoo_
//                  o8888888o
//                  88" . "88
//                  (| -_- |)
//                  O\  =  /O
//               ____/`---'\____
//             .'  \\|     |//  `.
//            /  \\|||  卍  |||//  \
//           /  _||||| -:- |||||-  \
//           |   | \\\  -  /// |   |
//           | \_|  ''\---/''  |   |
//          \  .-\__  `-`  ___/-. /
//         ___`. .'  /--.--\  `. . __
//      ."" '<  `.___\_<|>_/___.'  >'"".
//     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//      \  \ `-.   \_ __\ /__ _/   .-` /  /
//======`-.____`-.___\_____/___.-`____.-'======
//                   `=---='
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//              佛祖保佑    永无BUG
package demo.writehardwardid.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import demo.writehardwardid.SDKCardUtils;

/**
 * Created by dumh on 15/1/11.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "hardware_id.db";
    private static final int DATABASE_VERSION = 2;

    private static DatabaseHelper helper;

    public DatabaseHelper(Context context, String database_path) {
        super(context, database_path, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getHelper(Context context) {
        if (helper == null) {
            synchronized (DatabaseHelper.class) {
                String real_database_path;
                String dbdirurl = SDKCardUtils.getDBDriUrl();
                if (dbdirurl.equals("")) {
                    real_database_path = DATABASE_NAME;
                } else {
                    real_database_path = String.format("%s/%s", dbdirurl, DATABASE_NAME);
                }
                Log.i(TAG, String.format("DB PATH:%s", real_database_path));
                if (helper == null) {
                    helper = new DatabaseHelper(context, real_database_path);
                }
            }
        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, HardwareId.class);
            Log.i(TAG, "#####创建表：HardwareId");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.i(TAG, "创建表败");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        if (oldVersion == 1) {
            if (newVersion == 2) {
                try {
                    TableUtils.createTableIfNotExists(connectionSource, HardwareId.class);
                    Log.i(TAG, "#####创建表：HardwareId");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void close() {
        super.close();
    }
}
