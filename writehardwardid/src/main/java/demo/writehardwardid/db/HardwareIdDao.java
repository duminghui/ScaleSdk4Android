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

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.orhanobut.logger.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dumh on 15/1/11.
 */
public class HardwareIdDao {
    private static final String TAG = "HWIDDao";
    private Dao<HardwareId, Integer> hardwareIdDao;

    public HardwareIdDao(Context context) {
        DatabaseHelper helper = DatabaseHelper.getHelper(context);
        try {
            hardwareIdDao = helper.getDao(HardwareId.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newHardwareId(String hardwareId) {
        HardwareId hwId = new HardwareId(hardwareId, "");
        save(hwId);
    }


    public void bindMac(String hardwareId, String mac) {
        HardwareId hwId = getHardwareIdById(hardwareId);
        if (hwId == null) {
            return;
        }
        hwId.setMac(mac);
        hwId.setModifyTime(System.currentTimeMillis());
        save(hwId);
    }

    public void saveUpload(String hardwareId) {
        HardwareId hwId = getHardwareIdById(hardwareId);
        if (hwId == null) {
            return;
        }
        hwId.setIsUpload(true);
        hwId.setModifyTime(System.currentTimeMillis());
        save(hwId);
    }

    public void saveWrited(String hardwareId) {
        HardwareId hwId = getHardwareIdById(hardwareId);
        if (hwId == null) {
            return;
        }
        hwId.setIsWrite(true);
        hwId.setModifyTime(System.currentTimeMillis());
        save(hwId);
    }

    public String idUse4Mac(String hardwardId) {
        HardwareId hwid = getHardwareIdById(hardwardId);
        if (hwid != null) {
            return hwid.getMac();
        } else {
            return "";
        }
    }

    public HardwareId getHardwareIdById(String id) {
        QueryBuilder<HardwareId, Integer> queryBuilder = hardwareIdDao.queryBuilder();
        try {
            queryBuilder.where().eq(HardwareId.FIELD_HARDWARE_ID, id);
            Logger.t(TAG, 2).i(queryBuilder.prepareStatementString());
            return queryBuilder.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HardwareId getLastHardwareId() {
        QueryBuilder<HardwareId, Integer> queryBuilder = hardwareIdDao.queryBuilder();
        try {
            queryBuilder.orderBy(HardwareId.FIELD_CREATE_TIME, false);
            Logger.t(TAG, 2).i(queryBuilder.prepareStatementString());
            return queryBuilder.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUnbindID() {
        DeleteBuilder<HardwareId, Integer> deleteBuilder = hardwareIdDao.deleteBuilder();
        try {
            deleteBuilder.where().eq(HardwareId.FIELD_MAC, "");
            Logger.t(TAG, 2).i(deleteBuilder.prepareStatementString());
            hardwareIdDao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // public HardwareId getIdByMac(String mac) {
    //     QueryBuilder<HardwareId,Integer> queryBuilder = hardwareIdDao.queryBuilder();
    //     try {
    //         queryBuilder.where().eq(HardwareId.FIELD_MAC, mac);
    //         Logger.t(TAG, 2).i(queryBuilder.prepareStatementString());
    //         return queryBuilder.queryForFirst();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }

    private long getCount4UploadOrUnUpload(int isUpload) {
        QueryBuilder<HardwareId, Integer> queryBuilder = hardwareIdDao.queryBuilder();
        try {
            queryBuilder.where()
                    .eq(HardwareId.FIELD_IS_UPLOAD, isUpload);
            queryBuilder.setCountOf(true);
            Logger.t(TAG, 2).i(queryBuilder.prepareStatementString());
            return hardwareIdDao.countOf(queryBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long getUploadCount() {
        return getCount4UploadOrUnUpload(HardwareId.VALUE_IS_ENABLE);
    }

    public long getUnUploadCount() {
        return getCount4UploadOrUnUpload(HardwareId.VALUE_IS_DISABLE);
    }

    // public List<HardwareId> getAll() {
    //     List<HardwareId> hardwareIds = new ArrayList<>();
    //     QueryBuilder<HardwareId, Integer> queryBuilder = hardwareIdDao.queryBuilder();
    //     try {
    //         Logger.t(TAG, 2).i(queryBuilder.prepareStatementString());
    //         hardwareIds.addAll(queryBuilder.query());
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return hardwareIds;
    // }

    public List<HardwareId> getUnUpload() {
        List<HardwareId> hardwareIds = new ArrayList<>();
        QueryBuilder<HardwareId, Integer> queryBuilder = hardwareIdDao.queryBuilder();
        try {
            queryBuilder.where()
                    .eq(HardwareId.FIELD_IS_UPLOAD, HardwareId.VALUE_IS_DISABLE);
            Logger.t(TAG, 2).i(queryBuilder.prepareStatementString());
            hardwareIds.addAll(queryBuilder.query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hardwareIds;
    }

    private void save(HardwareId hardwareId) {
        try {
            hardwareIdDao.createOrUpdate(hardwareId);
            Logger.t(TAG, 2).i("add hardwareId:{%s}", hardwareId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
