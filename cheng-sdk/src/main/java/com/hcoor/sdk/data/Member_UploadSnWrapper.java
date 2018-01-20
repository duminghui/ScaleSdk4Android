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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.hcoor.sdk.net.base.BaseNetRequestWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by dumh on 15/1/16.
 */
public class Member_UploadSnWrapper extends BaseNetRequestWrapper {
    private static final String TAG = "Member_UploadSnWrapper";
    private static final String PARAMS_KEY_SNLISTJSON = "snListJson";

    public Member_UploadSnWrapper(RequestQueue requestQueue, NetRequestListener listener) {
        super(requestQueue, listener);
    }

    public void setValues(List<SNMAC> snmacs) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for (SNMAC snmac : snmacs) {
            jsonObject = new JSONObject();
            try {
                jsonObject.put("sn", snmac.sn);
                jsonObject.put("mac", snmac.mac);
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        addParams(PARAMS_KEY_SNLISTJSON, jsonArray.toString());
    }

    @Override
    protected String getModel() {
        return "member";
    }

    @Override
    protected String getCommand() {
        return "uploadSn";
    }

    @Override
    protected RetryPolicy getRetryPolicy() {
        return new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public static class SNMAC {
        private String sn;
        private String mac;

        public SNMAC(String sn, String mac) {
            this.sn = String.valueOf(sn);
            this.mac = mac;
        }

        @Override
        public String toString() {
            return "SNMAC{" +
                    "sn='" + sn + '\'' +
                    ", mac='" + mac + '\'' +
                    '}';
        }
    }

    public static class Response {
        public static final int SUCCESS = 0;
        private int errorCode = -1;
        private String msg;

        public Response(String json) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                errorCode = jsonObject.getInt("errorCode");
                msg = jsonObject.getString("msg");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getMsg() {
            return msg;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "errorCode=" + errorCode +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }
}
