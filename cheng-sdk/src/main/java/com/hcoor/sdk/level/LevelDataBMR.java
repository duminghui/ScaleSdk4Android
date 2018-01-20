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
public class LevelDataBMR {
    public static int[][] LEVELS = {
            {UserInfo.MAN, 9, 21, 0, 6000, 0, 2050, BodyInfoLevel.LOW},
            {UserInfo.MAN, 9, 21, 0, 6000, 2049, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 20, 31, 0, 6000, 0, 1650, BodyInfoLevel.LOW},
            {UserInfo.MAN, 20, 31, 0, 6000, 1649, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 30, 46, 0, 6000, 0, 1550, BodyInfoLevel.LOW},
            {UserInfo.MAN, 30, 46, 0, 6000, 1549, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 45, 61, 0, 6000, 0, 1450, BodyInfoLevel.LOW},
            {UserInfo.MAN, 45, 61, 0, 6000, 1449, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 60, 81, 0, 6000, 0, 1320, BodyInfoLevel.LOW},
            {UserInfo.MAN, 60, 81, 0, 6000, 1319, 9999, BodyInfoLevel.STANDARD},

            {UserInfo.MAN, 9, 21, 5999, 7501, 0, 1950, BodyInfoLevel.LOW},
            {UserInfo.MAN, 9, 21, 5999, 7501, 1949, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 20, 31, 5999, 7501, 0, 1550, BodyInfoLevel.LOW},
            {UserInfo.MAN, 20, 31, 5999, 7501, 1549, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 30, 46, 5999, 7501, 0, 1450, BodyInfoLevel.LOW},
            {UserInfo.MAN, 30, 46, 5999, 7501, 1449, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 45, 61, 5999, 7501, 0, 1350, BodyInfoLevel.LOW},
            {UserInfo.MAN, 45, 61, 5999, 7501, 1349, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 60, 81, 5999, 7501, 0, 1220, BodyInfoLevel.LOW},
            {UserInfo.MAN, 60, 81, 5999, 7501, 1219, 9999, BodyInfoLevel.STANDARD},

            {UserInfo.MAN, 9, 21, 7500, 99999, 0, 1850, BodyInfoLevel.LOW},
            {UserInfo.MAN, 9, 21, 7500, 99999, 1849, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 20, 31, 7500, 99999, 0, 1450, BodyInfoLevel.LOW},
            {UserInfo.MAN, 20, 31, 7500, 99999, 1449, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 30, 46, 7500, 99999, 0, 1350, BodyInfoLevel.LOW},
            {UserInfo.MAN, 30, 46, 7500, 99999, 1349, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 45, 61, 7500, 99999, 0, 1250, BodyInfoLevel.LOW},
            {UserInfo.MAN, 45, 61, 7500, 99999, 1249, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 60, 81, 7500, 99999, 0, 1120, BodyInfoLevel.LOW},
            {UserInfo.MAN, 60, 81, 7500, 99999, 1119, 9999, BodyInfoLevel.STANDARD},

            {UserInfo.WOMAN, 9, 21, 0, 4500, 0, 1850, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 9, 21, 0, 4500, 1849, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 20, 31, 0, 4500, 0, 1450, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 20, 31, 0, 4500, 1449, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 30, 46, 0, 4500, 0, 1350, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 30, 46, 0, 4500, 1349, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 45, 61, 0, 4500, 0, 1250, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 45, 61, 0, 4500, 1249, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 60, 81, 0, 4500, 0, 1120, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 60, 81, 0, 4500, 1119, 9999, BodyInfoLevel.STANDARD},

            {UserInfo.WOMAN, 9, 21, 4499, 6001, 0, 1750, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 9, 21, 4499, 6001, 1749, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 20, 31, 4499, 6001, 0, 1350, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 20, 31, 4499, 6001, 1349, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 30, 46, 4499, 6001, 0, 1250, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 30, 46, 4499, 6001, 1249, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 45, 61, 4499, 6001, 0, 1150, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 45, 61, 4499, 6001, 1149, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 60, 81, 4499, 6001, 0, 1020, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 60, 81, 4499, 6001, 1019, 9999, BodyInfoLevel.STANDARD},

            {UserInfo.WOMAN, 9, 21, 6000, 99999, 0, 1650, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 9, 21, 6000, 99999, 1649, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 20, 31, 6000, 99999, 0, 1250, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 20, 31, 6000, 99999, 1249, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 30, 46, 6000, 99999, 0, 1150, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 30, 46, 6000, 99999, 1149, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 45, 61, 6000, 99999, 0, 1050, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 45, 61, 6000, 99999, 1049, 9999, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 60, 81, 6000, 99999, 0, 920, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 60, 81, 6000, 99999, 919, 9999, BodyInfoLevel.STANDARD},
    };
}
