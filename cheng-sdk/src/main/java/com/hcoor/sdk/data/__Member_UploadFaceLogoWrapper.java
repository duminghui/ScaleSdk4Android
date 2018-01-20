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
package com.hcoor.sdk.data;

import android.util.Log;

import com.hcoor.sdk.net.base.HttpParamsUtils;
import com.hcoor.sdk.net.base.upload.UploadImage;

import java.util.HashMap;
/**
 * Created by dumh on 14/12/6.
 */
public class __Member_UploadFaceLogoWrapper {
    private static final String TAG = "Member_UploadFaceLogoWrapper";
    private static final String SECRET_KEY = "";
    private static final String UPLOAD_URL = "http://www.hcoor.com/sdk-interface/member?";
    private static final String URL_FORMMAT = "%s%sbdsig=%s";

    private static final String PARAMS_KEY_COMMAND = "command";
    private UploadImage.OnImageUploadCallBack callBack;
    private String fileUrl;

    public __Member_UploadFaceLogoWrapper(UploadImage.OnImageUploadCallBack callBack) {
        this.callBack = callBack;
    }

    public void setValues(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public void upload() {
        String uploadUrl = makeUrl();
        Log.i(TAG, String.format("[file:%s][url:%s]", fileUrl, uploadUrl));
        UploadImage uploadImage = new UploadImage(uploadUrl, fileUrl, callBack);
        uploadImage.execute();
    }

    private String makeUrl() {
        HashMap<String, String> params = new HashMap<>();
        params.put(PARAMS_KEY_COMMAND, "uploadfacelogo");
        return String.format(URL_FORMMAT, UPLOAD_URL, HttpParamsUtils.getBaseParams(params), HttpParamsUtils.getSignature(params, SECRET_KEY));
    }
}
