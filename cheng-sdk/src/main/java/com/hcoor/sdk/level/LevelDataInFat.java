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
package com.hcoor.sdk.level;

import com.hcoor.sdk.UserInfo;

/**
 * Created by dumh on 14/12/11.
 */
public class LevelDataInFat {
    public static int[][] LEVELS = {
            {UserInfo.MAN, 0, 99, 0, 99999, 0, 10, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 0, 99, 0, 99999, 9, 15, BodyInfoLevel.HIGH},
            {UserInfo.MAN, 0, 99, 0, 99999, 14, 99, BodyInfoLevel.TOO_HIGH},

            {UserInfo.WOMAN, 0, 99, 0, 99999, 0, 10, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 0, 99, 0, 99999, 9, 15, BodyInfoLevel.HIGH},
            {UserInfo.WOMAN, 0, 99, 0, 99999, 14, 99, BodyInfoLevel.TOO_HIGH},
    };
}
