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
package demo.writehardwardid;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by dumh on 15/1/18.
 */
public class SDKCardUtils {
    private static final String TAG = "SDKCardUtils";

    public static String getSDCardPath() {
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist) {
            File sdDir = Environment.getExternalStorageDirectory();//获取跟目录
            return sdDir.toString();
        }
        return "";
    }

    public static String getDBDriUrl() {
        return getDirUrl("db");
    }

    private static String getDirUrl(String subPath) {
        String sdPath = getSDCardPath();
        if (sdPath.equals("")) {
            return "";
        } else {
            String path = String.format("%s/com.hcoor/writeid/%s", sdPath, subPath);
            Log.i(TAG, String.format("filePath:%s", path));
            File file = new File(path);
            if (!file.exists()) {
                boolean createDir = file.mkdirs();
                Log.i(TAG, String.format("createPath:[%s][%s]", path, createDir));
                if (!createDir) {
                    return "";
                }
            }
            return path;
        }
    }
}
