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
package com.hcoor.sdk;

/**
 * Created by dumh on 15/5/20.
 */
public interface IDateListenerUnuse {
    /**
     * 新建用户
     *
     * @param result 新建结果
     */
    void onCreateUser(int result);

    void onDelUser(int result);

    void onUpdateUser(int result);

    void onWeighSelectUser(int result);

    void onReadUserBodyInfos(BodyInfo[] infos);

    void onDelUserBodyInfos(int result);

    void onReadUserInfos(UserInfo[] infos);
}
