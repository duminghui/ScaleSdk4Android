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
 * 优伴：0,1男2女
 * 后台: 1男0女
 * 秤：0男1女
 * Created by dumh on 15/1/22.
 */
public class SexConverter {

    private static final int UBUNTA_MAN = 1;
    private static final int UBUNTA_WOMEN = 2;
    private static final int NET_MAN = 1;
    private static final int NET_WOMEN = 0;
    private static final int SCALE_MAN = 0;
    private static final int SCALE_WOMEN = 1;

    static enum SexZone {
        /**
         * 优伴
         */
        ubunta,
        /**
         * 网络
         */
        net,
        /**
         * 称
         */
        scale
    }

    public static int converterSex(SexZone from, SexZone to, int sex) {
        //男
        if (from == SexZone.ubunta && to == SexZone.net && (sex == 1 || sex == 0)) return NET_MAN;
        if (from == SexZone.ubunta && to == SexZone.scale && (sex == 1 || sex == 0)) return SCALE_MAN;
        //女
        if (from == SexZone.ubunta && to == SexZone.net && sex == 2) return NET_WOMEN;
        if (from == SexZone.ubunta && to == SexZone.scale && sex == 2) return SCALE_WOMEN;

        //男
        if (from == SexZone.net && to == SexZone.ubunta && sex == 1) return UBUNTA_MAN;
        if (from == SexZone.net && to == SexZone.scale && sex == 1) return SCALE_MAN;
        //女
        if (from == SexZone.net && to == SexZone.ubunta && sex == 0) return UBUNTA_WOMEN;
        if (from == SexZone.net && to == SexZone.scale && sex == 0) return SCALE_WOMEN;

        //男
        if (from == SexZone.scale && to == SexZone.ubunta && sex == 0) return UBUNTA_MAN;
        if (from == SexZone.scale && to == SexZone.net && sex == 0) return NET_MAN;
        //女
        if (from == SexZone.scale && to == SexZone.ubunta && sex == 1) return UBUNTA_WOMEN;
        if (from == SexZone.scale && to == SexZone.net && sex == 1) return NET_WOMEN;

        return sex;

    }
}
