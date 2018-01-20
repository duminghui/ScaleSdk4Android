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

import com.android.volley.RequestQueue;
import com.hcoor.sdk.net.base.BaseNetRequestWrapper;

/**
 * Created by dumh on 14/12/6.
 */
public class FitnessServlet_GetFitnessReportWrapper extends BaseNetRequestWrapper {
    private static final String PARAMS_KEY_MEMBERID = "memberId";
    private static final String PARAMS_KEY_ACCOUNTID = "accountId";

    protected FitnessServlet_GetFitnessReportWrapper(RequestQueue requestQueue, NetRequestListener listener) {
        super(requestQueue, listener);
    }

    public void setValues(String accountId, String memberId) {
        addParams(PARAMS_KEY_ACCOUNTID, accountId);
        addParams(PARAMS_KEY_MEMBERID, memberId);
    }

    @Override
    protected String getModel() {
        return "fitnessServlet";
    }

    @Override
    protected String getCommand() {
        return "getFitnessReport";
    }
}
