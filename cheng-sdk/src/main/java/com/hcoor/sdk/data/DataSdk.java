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

import com.hcoor.sdk.BodyInfo;
import com.hcoor.sdk.net.base.VolleyRequestQueue;

/**
 * 网络数据处理
 * Created by dumh on 14/12/6.
 */
public class DataSdk {
    private static boolean isDebugLine = false;

    public static void setDebug(boolean isDebugLine) {
        DataSdk.isDebugLine = isDebugLine;
    }

    public static boolean isDebugLine(){
        return isDebugLine;
    }
//    /**
//     * 添加成员
//     *
//     * @param accountId  账号主键Id
//     * @param memberType 成员类型  1：主帐号,2：默认成员,3：游客
//     * @param nickname   昵称 30字符以内
//     * @param sex        性别，0：女，1：男
//     * @param birthday   生日，格式 yyyy-MM-dd
//     * @param height     身高，格式：170，范围:100~220
//     * @param waist      腰围,格式:60,40~130 非必填
//     * @param hip        臀围，格式:70,范围:50~130(cm) 非必填
//     * @param facelogo   头像url
//     * @param listener   处理结果监听
//     */
//    public static void addMembership(String accountId, int memberType, String nickname, int sex, String birthday, int height, int waist, int hip, String facelogo, NetRequestListener listener) {
//        __Member_AddMembershipWrapper addMembershipWrapper = new __Member_AddMembershipWrapper(VolleyRequestQueue.getRequestQueue(), listener);
//        addMembershipWrapper.setValues(nickname, sex, birthday, height, waist, hip, accountId, facelogo, memberType);
//        addMembershipWrapper.addRequest2Queue();
//    }
//
//    /**
//     * 编辑成员
//     *
//     * @param memberId  主键
//     * @param accountId 账号主键Id
//     * @param nickname  昵称 30字符以内
//     * @param sex       性别，0：女，1：男
//     * @param birthday  生日，格式 yyyy-MM-dd
//     * @param height    身高，格式：170，范围:100~220
//     * @param waist     腰围,格式:60,40~130 非必填
//     * @param hip       臀围，格式:70,范围:50~130(cm) 非必填
//     * @param facelogo  头像url
//     * @param listener  处理结果监听
//     */
//    public static void updateMembership(String accountId,String memberId, String nickname, int sex, String birthday, int height, int waist, int hip, String facelogo, NetRequestListener listener) {
//        __Member_UpdateMembershipWrapper updateMembershipWrapper = new __Member_UpdateMembershipWrapper(VolleyRequestQueue.getRequestQueue(), listener);
//        updateMembershipWrapper.setValues(memberId, accountId, nickname, sex, birthday, height, waist, hip, facelogo);
//        updateMembershipWrapper.addRequest2Queue();
//    }
//
//    /**
//     * 删除成员
//     *
//     * @param memberId  成员主键，正整数
//     * @param accountId 账号主键ID
//     * @param listener  处理结果监听
//     */
//    public static void delMembership(String accountId, String memberId, NetRequestListener listener) {
//        __Member_DelMembershipWrapper delMembershipWrapper = new __Member_DelMembershipWrapper(VolleyRequestQueue.getRequestQueue(), listener);
//        delMembershipWrapper.setValues(memberId, accountId);
//        delMembershipWrapper.addRequest2Queue();
//    }
//
//    /**
//     * 查询成员列表
//     *
//     * @param accountId 账号主键ID
//     * @param listener  处理结果监听
//     */
//    public static void queryMembershipList(String accountId, NetRequestListener listener) {
//        __Member_QueryMembershipListWrapper queryMembershipListWrapper = new __Member_QueryMembershipListWrapper(VolleyRequestQueue.getRequestQueue(), listener);
//        queryMembershipListWrapper.setValues(accountId);
//        queryMembershipListWrapper.addRequest2Queue();
//    }

    /**
     * 数据上报
     *
     * @param memberId  成员id，正整数
     * @param accountId 账号
     * @param bodyInfo  测量数据
     * @param sn        SN 编码
     * @param listener  处理结果监听
     */
    public static void addMemberRecord(String accountId, String memberId, String sn, BodyInfo bodyInfo, NetRequestListener listener) {
        MemberReport_AddMemberRecordWrapper addMemberRecordWrapper = new MemberReport_AddMemberRecordWrapper(VolleyRequestQueue.getRequestQueue(), listener);
        addMemberRecordWrapper.setValues(memberId, accountId, bodyInfo.weight, bodyInfo.bf, bodyInfo.water, bodyInfo.muscle, bodyInfo.bone, bodyInfo.bmr, bodyInfo.inFat, bodyInfo.bodyAge, sn);
        addMemberRecordWrapper.addRequest2Queue();
    }

    /**
     * 获取用户报告
     *
     * @param accountId 账户名称，唯一标识
     * @param memberId  成员ID
     * @param listener  处理结果监听
     */
    public static void getMemberReport(String accountId, String memberId, NetRequestListener listener) {
        MemberReport_GetMemberReportWrapper getMemberReportWrapper = new MemberReport_GetMemberReportWrapper(VolleyRequestQueue.getRequestQueue(), listener);
        getMemberReportWrapper.setValues(accountId, memberId);
        getMemberReportWrapper.addRequest2Queue();
    }

    /**
     * 获取个人键康报告
     *
     * @param accountId 账户名称，唯一标识
     * @param memberId  成员ID
     * @param listener  处理结果监听
     */
    public static void getFitnessReport(String accountId, String memberId, NetRequestListener listener) {
        FitnessServlet_GetFitnessReportWrapper getFitnessReportWrapper = new FitnessServlet_GetFitnessReportWrapper(VolleyRequestQueue.getRequestQueue(), listener);
        getFitnessReportWrapper.setValues(accountId, memberId);
        getFitnessReportWrapper.addRequest2Queue();
    }

    /**
     * 获取个人键康建议
     *
     * @param accountId 账户名称，唯一标识
     * @param memberId  成员ID
     * @param listener  处理结果监听
     */
    public static void getFitnessProposal(String accountId, String memberId, NetRequestListener listener) {
        FitnessServlet_GetFitnessProposalWrapper getFitnessProposalWrapper = new FitnessServlet_GetFitnessProposalWrapper(VolleyRequestQueue.getRequestQueue(), listener);
        getFitnessProposalWrapper.setValues(accountId, memberId);
        getFitnessProposalWrapper.addRequest2Queue();
    }

    /**
     * 获取趋势图
     *
     * @param accountId 账户名称，唯一标识
     * @param memberId  成员ID
     * @param startDate 开始时间 yyyy-MM-dd
     * @param endDate   结束时间 yyyy-MM-dd
     * @param listener  处理结果监听
     */
    public static void getTendencyChart(String accountId, String memberId, String startDate, String endDate, NetRequestListener listener) {
        FitnessServlet_GetTendencyChartWrapper getTendencyChartWrapper = new FitnessServlet_GetTendencyChartWrapper(VolleyRequestQueue.getRequestQueue(), listener);
        getTendencyChartWrapper.setValues(accountId, memberId, startDate, endDate);
        getTendencyChartWrapper.addRequest2Queue();
    }

    /**
     * 获取家庭报告
     *
     * @param accountId 账户名称，唯一标识
     * @param listener  处理结果监听
     */
    public static void getFamilyReport(String accountId, NetRequestListener listener) {
        FitnessServlet_GetFamilyReportWrapper getFamilyReportWrapper = new FitnessServlet_GetFamilyReportWrapper(VolleyRequestQueue.getRequestQueue(), listener);
        getFamilyReportWrapper.setValues(accountId);
        getFamilyReportWrapper.addRequest2Queue();
    }

//    /**
//     * 上传用户头像
//     *
//     * @param fileUrl  图片路径
//     * @param callBack 上传处理过程
//     */
//    public static void uploadFaceLogo(String fileUrl, UploadImage.OnImageUploadCallBack callBack) {
//        __Member_UploadFaceLogoWrapper uploadFaceLogoWrapper = new __Member_UploadFaceLogoWrapper(callBack);
//        uploadFaceLogoWrapper.setValues(fileUrl);
//        uploadFaceLogoWrapper.upload();
//    }
}

