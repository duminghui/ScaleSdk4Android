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
 * 添加成员
 * Created by dumh on 14/12/6.
 */
class __Member_AddMembershipWrapper extends BaseNetRequestWrapper {
    private static final String PARAMS_KEY_NICKNAME = "nickname";
    private static final String PARAMS_KEY_SEX = "sex";
    private static final String PARAMS_KEY_BIRTHDAY = "birthday";
    private static final String PARAMS_KEY_HEIGHT = "height";
    private static final String PARAMS_KEY_WAIST = "waist";
    private static final String PARAMS_KEY_HIP = "hip";
    private static final String PARAMS_KEY_ACCOUNTID = "accountId";
    private static final String PARAMS_KEY_FACELOGO = "facelogo";
    private static final String PARAMS_KEY_MEMBERTYPE = "memberType";

    protected __Member_AddMembershipWrapper(RequestQueue requestQueue, NetRequestListener listener) {
        super(requestQueue, listener);
    }

    protected void setValues(String nickname, int sex, String birthday, int height, int waist, int hip, String accountId, String facelogo, int memberType) {
        addParams(PARAMS_KEY_NICKNAME, nickname);
        addParams(PARAMS_KEY_SEX, sex);
        addParams(PARAMS_KEY_BIRTHDAY, birthday);
        addParams(PARAMS_KEY_HEIGHT, height);
        addParams(PARAMS_KEY_WAIST, waist);
        addParams(PARAMS_KEY_HIP, hip);
        addParams(PARAMS_KEY_ACCOUNTID, accountId);
        addParams(PARAMS_KEY_FACELOGO, facelogo);
        addParams(PARAMS_KEY_MEMBERTYPE, memberType);
    }

    @Override
    protected String getModel() {
        return "member";
    }

    @Override
    protected String getCommand() {
        return "addMembership";
    }
}
