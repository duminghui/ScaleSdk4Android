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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by dumh on 15/1/11.
 */
@DatabaseTable(tableName = "HardwareId2")
public class HardwareId {
    public static final String FIELD_HARDWARE_ID = "hardware_id";
    public static final String FIELD_MAC = "mac";
    public static final String FIELD_IS_UPLOAD = "isUpload";
    public static final String FIELD_IS_WRITE = "isWrite";
    public static final String FIELD_CREATE_TIME = "createTime";
    public static final String FIELD_MODIFY_TIME = "modifyTime";
    public static final int VALUE_IS_DISABLE = 1;

    public static final int VALUE_IS_ENABLE = 2;
    @DatabaseField(columnName = FIELD_HARDWARE_ID, id = true)
    private String hardware_id;

    @DatabaseField(columnName = FIELD_MAC)
    private String mac;

    @DatabaseField(columnName = FIELD_IS_UPLOAD)
    private int isUpload;

    @DatabaseField(columnName = FIELD_IS_WRITE)
    private int isWrite;

    @DatabaseField(columnName = FIELD_CREATE_TIME)
    private long createTime;

    @DatabaseField(columnName = FIELD_MODIFY_TIME)
    private long modifyTime;

    public HardwareId() {

    }

    public HardwareId(String hardware_id, String mac) {
        this.hardware_id = hardware_id;
        this.mac = mac;
        this.isUpload = VALUE_IS_DISABLE;
        this.isWrite = VALUE_IS_DISABLE;
        this.createTime = System.currentTimeMillis();
    }

    public String getHardware_id() {
        return hardware_id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public void setIsWrite(boolean isWrite) {
        this.isWrite = isWrite ? VALUE_IS_ENABLE : VALUE_IS_DISABLE;
    }

    public boolean isWrite() {
        return isWrite == VALUE_IS_ENABLE;
    }

    public void setIsUpload(boolean isUpload) {
        this.isUpload = isUpload ? VALUE_IS_ENABLE : VALUE_IS_DISABLE;
    }

    @Override
    public String toString() {
        return "HardwareId{" +
                "hardware_id='" + hardware_id + '\'' +
                ", mac='" + mac + '\'' +
                ", isUpload=" + isUpload +
                ", isWrite=" + isWrite +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
