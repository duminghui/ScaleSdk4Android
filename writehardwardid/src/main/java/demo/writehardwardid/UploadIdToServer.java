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

import android.content.Context;
import android.util.Log;

import com.hcoor.sdk.data.Member_UploadSnWrapper;
import com.hcoor.sdk.data.NetRequestListener;
import com.hcoor.sdk.net.base.VolleyRequestQueue;

import java.util.ArrayList;
import java.util.List;

import demo.writehardwardid.db.HardwareId;
import demo.writehardwardid.db.HardwareIdDao;

/**
 * Created by dumh on 15/1/16.
 */
public class UploadIdToServer {
    private static final String TAG = "UploadIdToServer";
    private static final UploadIdToServer instance = new UploadIdToServer();

    private IUploadIdListener listener;
    /**
     * 本次处理的id集
     */
    private List<HardwareId> hardwareIds = new ArrayList<>();

    private HardwareIdDao hardwareIdDao;

    public static UploadIdToServer getInstance() {
        return instance;
    }

    private UploadIdToServer() {
    }

    public void setListener(Context context, IUploadIdListener listener) {
        hardwareIdDao = new HardwareIdDao(context);
        this.listener = listener;
    }

    public void toUpload() {
        hardwareIds.clear();
        List<HardwareId> unUploadIds = hardwareIdDao.getUnUpload();
//        if (unUploadIds.size() == 0) {
//            listener.onSuccess(0);
//            return;
//        }
        List<Member_UploadSnWrapper.SNMAC> snmacs = new ArrayList<>();
        HardwareId hardwareId;
        int length = 40;
        if (length > unUploadIds.size()) {
            length = unUploadIds.size();
        }
        for (int i = 0; i < length; i++) {
            hardwareId = unUploadIds.get(i);
            hardwareIds.add(hardwareId);
            snmacs.add(new Member_UploadSnWrapper.SNMAC(hardwareId.getHardware_id(), hardwareId.getMac()));
        }
        Member_UploadSnWrapper uploadSnWrapper = new Member_UploadSnWrapper(VolleyRequestQueue.getRequestQueue(), new NetRequestListener() {
            @Override
            public void onNetSuccess(String json) {
                Log.i(TAG, json);
                Member_UploadSnWrapper.Response response = new Member_UploadSnWrapper.Response(json);
                if (response.getErrorCode() == Member_UploadSnWrapper.Response.SUCCESS) {
                    for (HardwareId hwid : hardwareIds) {
                        hardwareIdDao.saveUpload(hwid.getHardware_id());
                    }
                    listener.onSuccess(hardwareIds.size());
                } else {
                    listener.onError(IUploadIdListener.CODE_SERVER, hardwareIds.size());
                }
            }

            @Override
            public void onError(int code, String msg) {
                listener.onError(IUploadIdListener.CODE_NET, hardwareIds.size());
            }
        });
        uploadSnWrapper.setValues(snmacs);
        uploadSnWrapper.addRequest2Queue();
    }
}
