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

/**
 * 用于比较的无机盐算术式
 * salt = weight * 100 * (5,4)
 * Created by dumh on 14/12/11.
 */
public class SaltLevelReckoner extends BaseLevelReckoner {
    private int height;

    public SaltLevelReckoner(int height) {
        this.height = height;
    }

    @Override
    protected void init(Limit rootLimit) {
        Limit sexLimit, ageLimit, weightLimit;
        int saltMin,saltMax;
        //男性
        sexLimit = rootLimit.addSub(-1, 1);

        ageLimit = sexLimit.addSub(9, 61);
        weightLimit = ageLimit.addSub(0, 99999);
        saltMin = (height - 80) * 70 * 4;
        saltMax = (height- 80) * 70 * 6;
        weightLimit.addLow(0, saltMin);
        weightLimit.addStandard(saltMin - 1, saltMax + 1);
        weightLimit.addHigh(saltMax, 99999);

        ageLimit = sexLimit.addSub(59, 81);
        weightLimit = ageLimit.addSub(0, 99999);
        saltMin = (height - 80) * 70 * 3;
        saltMax = (height- 80) * 70 * 5;
        weightLimit.addLow(0, saltMin);
        weightLimit.addStandard(saltMin - 1, saltMax + 1);
        weightLimit.addHigh(saltMax, 99999);

        //女性
        sexLimit = rootLimit.addSub(0, 2);
        ageLimit = sexLimit.addSub(9, 56);
        weightLimit = ageLimit.addSub(0, 99999);
        saltMin = (height - 70) * 60 * 4;
        saltMax = (height- 70) * 60 * 6;
        weightLimit.addLow(0, saltMin);
        weightLimit.addStandard(saltMin - 1, saltMax + 1);
        weightLimit.addHigh(saltMax, 99999);

        ageLimit = sexLimit.addSub(54, 81);
        weightLimit = ageLimit.addSub(0, 99999);
        saltMin = (height - 70) * 60 * 3;
        saltMax = (height- 70) * 60 * 5;
        weightLimit.addLow(0, saltMin);
        weightLimit.addStandard(saltMin - 1, saltMax + 1);
        weightLimit.addHigh(saltMax, 99999);


    }
}
