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
 * 数据集格式
 * {0:sex,1:minAge,2:maxAge,3:minWeight,4:maxWeight,5:minValue,6:maxValue,level}
 * 其中weight,value的值都是100的倍数
 * Created by dumh on 14/12/10.
 */
class LevelDataBF {
    public static int[][] LEVELS = {
            {UserInfo.MAN, 9, 19, 0, 99999, 0, 400, BodyInfoLevel.TOO_LOW},
            {UserInfo.MAN, 9, 19, 0, 99999, 399, 900, BodyInfoLevel.LOW},
            {UserInfo.MAN, 9, 19, 0, 99999, 899, 1901, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 9, 19, 0, 99999, 1900, 2401, BodyInfoLevel.HIGH},
            {UserInfo.MAN, 9, 19, 0, 99999, 2400, 9999, BodyInfoLevel.TOO_HIGH},

            {UserInfo.MAN, 18, 30, 0, 99999, 0, 500, BodyInfoLevel.TOO_LOW},
            {UserInfo.MAN, 18, 30, 0, 99999, 499, 1000, BodyInfoLevel.LOW},
            {UserInfo.MAN, 18, 30, 0, 99999, 999, 2001, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 18, 30, 0, 99999, 2000, 2501, BodyInfoLevel.HIGH},
            {UserInfo.MAN, 18, 30, 0, 99999, 2500, 9999, BodyInfoLevel.TOO_HIGH},

            {UserInfo.MAN, 29, 40, 0, 99999, 0, 600, BodyInfoLevel.TOO_LOW},
            {UserInfo.MAN, 29, 40, 0, 99999, 599, 1100, BodyInfoLevel.LOW},
            {UserInfo.MAN, 29, 40, 0, 99999, 1099, 2101, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 29, 40, 0, 99999, 2100, 2601, BodyInfoLevel.HIGH},
            {UserInfo.MAN, 29, 40, 0, 99999, 2600, 9999, BodyInfoLevel.TOO_HIGH},

            {UserInfo.MAN, 39, 50, 0, 99999, 0, 700, BodyInfoLevel.TOO_LOW},
            {UserInfo.MAN, 39, 50, 0, 99999, 699, 1200, BodyInfoLevel.LOW},
            {UserInfo.MAN, 39, 50, 0, 99999, 1199, 2201, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 39, 50, 0, 99999, 2200, 2701, BodyInfoLevel.HIGH},
            {UserInfo.MAN, 39, 50, 0, 99999, 2700, 9999, BodyInfoLevel.TOO_HIGH},

            {UserInfo.MAN, 49, 60, 0, 99999, 0, 800, BodyInfoLevel.TOO_LOW},
            {UserInfo.MAN, 49, 60, 0, 99999, 799, 1300, BodyInfoLevel.LOW},
            {UserInfo.MAN, 49, 60, 0, 99999, 1299, 2301, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 49, 60, 0, 99999, 2300, 2801, BodyInfoLevel.HIGH},
            {UserInfo.MAN, 49, 60, 0, 99999, 2800, 9999, BodyInfoLevel.TOO_HIGH},

            {UserInfo.MAN, 59, 81, 0, 99999, 0, 1400, BodyInfoLevel.LOW},
            {UserInfo.MAN, 59, 81, 0, 99999, 1399, 2401, BodyInfoLevel.STANDARD},
            {UserInfo.MAN, 59, 81, 0, 99999, 2400, 9999, BodyInfoLevel.HIGH},

            {UserInfo.WOMAN, 9, 19, 0, 99999, 0, 900, BodyInfoLevel.TOO_LOW},
            {UserInfo.WOMAN, 9, 19, 0, 99999, 899, 1400, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 9, 19, 0, 99999, 1399, 2401, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 9, 19, 0, 99999, 2400, 3401, BodyInfoLevel.HIGH},
            {UserInfo.WOMAN, 9, 19, 0, 99999, 3400, 9999, BodyInfoLevel.TOO_HIGH},

            {UserInfo.WOMAN, 18, 30, 0, 99999, 0, 1000, BodyInfoLevel.TOO_LOW},
            {UserInfo.WOMAN, 18, 30, 0, 99999, 999, 1500, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 18, 30, 0, 99999, 1499, 2501, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 18, 30, 0, 99999, 2500, 3501, BodyInfoLevel.HIGH},
            {UserInfo.WOMAN, 18, 30, 0, 99999, 3500, 9999, BodyInfoLevel.TOO_HIGH},

            {UserInfo.WOMAN, 29, 40, 0, 99999, 0, 1100, BodyInfoLevel.TOO_LOW},
            {UserInfo.WOMAN, 29, 40, 0, 99999, 1099, 1600, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 29, 40, 0, 99999, 1599, 2601, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 29, 40, 0, 99999, 2600, 3601, BodyInfoLevel.HIGH},
            {UserInfo.WOMAN, 29, 40, 0, 99999, 3600, 9999, BodyInfoLevel.TOO_HIGH},

            {UserInfo.WOMAN, 39, 50, 0, 99999, 0, 1200, BodyInfoLevel.TOO_LOW},
            {UserInfo.WOMAN, 39, 50, 0, 99999, 1199, 1700, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 39, 50, 0, 99999, 1699, 2701, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 39, 50, 0, 99999, 2700, 3701, BodyInfoLevel.HIGH},
            {UserInfo.WOMAN, 39, 50, 0, 99999, 3700, 9999, BodyInfoLevel.TOO_HIGH},

            {UserInfo.WOMAN, 49, 60, 0, 99999, 0, 1300, BodyInfoLevel.TOO_LOW},
            {UserInfo.WOMAN, 49, 60, 0, 99999, 1299, 1800, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 49, 60, 0, 99999, 1799, 2801, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 49, 60, 0, 99999, 2800, 3801, BodyInfoLevel.HIGH},
            {UserInfo.WOMAN, 49, 60, 0, 99999, 3800, 9999, BodyInfoLevel.TOO_HIGH},

            {UserInfo.WOMAN, 59, 81, 0, 99999, 0, 1900, BodyInfoLevel.LOW},
            {UserInfo.WOMAN, 59, 81, 0, 99999, 1899, 2901, BodyInfoLevel.STANDARD},
            {UserInfo.WOMAN, 59, 81, 0, 99999, 2900, 9999, BodyInfoLevel.HIGH},
    };
}
