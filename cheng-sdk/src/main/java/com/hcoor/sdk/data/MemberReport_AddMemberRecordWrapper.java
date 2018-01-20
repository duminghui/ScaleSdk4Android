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
public class MemberReport_AddMemberRecordWrapper extends BaseNetRequestWrapper {
    private static final String PARAMS_KEY_MEMBERID = "memberId";
    private static final String PARAMS_KEY_ACCOUNTID = "accountId";
    private static final String PARAMS_KEY_WEIGHT = "weight";
    private static final String PARAMS_KEY_FAT = "fat";
    private static final String PARAMS_KEY_WATER = "water";
    private static final String PARAMS_KEY_MUSCLE = "muscle";
    private static final String PARAMS_KEY_BONE = "bone";
    private static final String PARAMS_KEY_METABOLIC = "metabolic";
    private static final String PARAMS_KEY_INFAT = "inFat";
    private static final String PARAMS_KEY_AGE = "age";
    private static final String PARAMS_KEY_SN
            = "sn";

    public MemberReport_AddMemberRecordWrapper(RequestQueue requestQueue, NetRequestListener listener) {
        super(requestQueue, listener);
    }

    protected void setValues(String memberId, String accountId, float weight, float fat, float water, float muscle, float bone, float metabolic, float inFat, int age, String sn) {
        addParams(PARAMS_KEY_MEMBERID, memberId);
        addParams(PARAMS_KEY_ACCOUNTID, accountId);
        addParams(PARAMS_KEY_WEIGHT, weight);
        addParams(PARAMS_KEY_FAT, fat);
        addParams(PARAMS_KEY_WATER, water);
        addParams(PARAMS_KEY_MUSCLE, muscle);
        addParams(PARAMS_KEY_BONE, bone);
        addParams(PARAMS_KEY_METABOLIC, metabolic);
        addParams(PARAMS_KEY_INFAT, inFat);
        addParams(PARAMS_KEY_AGE, age);
        addParams(PARAMS_KEY_SN, sn);
    }

    @Override
    protected String getModel() {
        return "memberReport";
    }

    @Override
    protected String getCommand() {
        return "addMemberRecord";
    }
}
