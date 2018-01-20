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
 * Created by dumh on 14/12/11.
 */
public class BFLevelReckoner extends BaseLevelReckoner {
    private static final BFLevelReckoner instance = new BFLevelReckoner();

    public static BFLevelReckoner getInstance() {
        return instance;
    }

    @Override
    protected void init(Limit rootLimit) {
        Limit manLimit = rootLimit.addSub(-1, 1);
        Limit ageLimit, weightLimit;
        ageLimit = manLimit.addSub(9, 19);
        weightLimit = ageLimit.addSub(0, 99999);
        weightLimit.addTooLow(0, 1320);
        weightLimit.addLow(1319, 1820);
        weightLimit.addStandard(1819, 2321);
        weightLimit.addHigh(2320, 2821);
        weightLimit.addTooHigh(2820, 9999);

        ageLimit = manLimit.addSub(18, 30);
        weightLimit = ageLimit.addSub(0, 99999);
        weightLimit.addTooLow(0, 1420);
        weightLimit.addLow(1419, 1920);
        weightLimit.addStandard(1919, 2421);
        weightLimit.addHigh(2420, 2921);
        weightLimit.addTooHigh(2920, 9999);

        ageLimit = manLimit.addSub(29, 40);
        weightLimit = ageLimit.addSub(0, 99999);
        weightLimit.addTooLow(0, 1520);
        weightLimit.addLow(1519, 2020);
        weightLimit.addStandard(2019, 2521);
        weightLimit.addHigh(2520, 3021);
        weightLimit.addTooHigh(3020, 9999);

        ageLimit = manLimit.addSub(39, 50);
        weightLimit = ageLimit.addSub(0, 99999);
        weightLimit.addTooLow(0, 1620);
        weightLimit.addLow(1619, 2120);
        weightLimit.addStandard(2119, 2621);
        weightLimit.addHigh(2620, 3121);
        weightLimit.addTooHigh(3120, 9999);

        ageLimit = manLimit.addSub(49, 60);
        weightLimit = ageLimit.addSub(0, 99999);
        weightLimit.addTooLow(0, 1720);
        weightLimit.addLow(1719, 2220);
        weightLimit.addStandard(2219, 2721);
        weightLimit.addHigh(2720, 3221);
        weightLimit.addTooHigh(3220, 9999);

        ageLimit = manLimit.addSub(59, 81);
        weightLimit = ageLimit.addSub(0, 99999);
        weightLimit.addLow(0, 2220);
        weightLimit.addStandard(2219, 3021);
        weightLimit.addHigh(3020, 99999);

        ageLimit = manLimit.addSub(9, 19);
        weightLimit = ageLimit.addSub(0, 99999);
        weightLimit.addTooLow(0, 1820);
        weightLimit.addLow(1819, 2320);
        weightLimit.addStandard(2319, 2821);
        weightLimit.addHigh(2820, 3321);
        weightLimit.addTooHigh(3320, 9999);

        ageLimit = manLimit.addSub(18, 30);
        weightLimit = ageLimit.addSub(0, 99999);
        weightLimit.addTooLow(0, 1920);
        weightLimit.addLow(1919, 2420);
        weightLimit.addStandard(2419, 2921);
        weightLimit.addHigh(2920, 3421);
        weightLimit.addTooHigh(3420, 9999);

        ageLimit = manLimit.addSub(29, 40);
        weightLimit = ageLimit.addSub(0, 99999);
        weightLimit.addTooLow(0, 2020);
        weightLimit.addLow(2019, 2520);
        weightLimit.addStandard(2519, 3021);
        weightLimit.addHigh(3020, 3521);
        weightLimit.addTooHigh(3520, 9999);

        ageLimit = manLimit.addSub(39, 50);
        weightLimit = ageLimit.addSub(0, 99999);
        weightLimit.addTooLow(0, 2120);
        weightLimit.addLow(2119, 2620);
        weightLimit.addStandard(2619, 3121);
        weightLimit.addHigh(3120, 3621);
        weightLimit.addTooHigh(3620, 9999);

        ageLimit = manLimit.addSub(49, 60);
        weightLimit = ageLimit.addSub(0, 99999);
        weightLimit.addTooLow(0, 2220);
        weightLimit.addLow(2219, 2720);
        weightLimit.addStandard(2719, 3221);
        weightLimit.addHigh(3220, 3721);
        weightLimit.addTooHigh(3720, 9999);

        ageLimit = manLimit.addSub(59, 81);
        weightLimit = ageLimit.addSub(0, 99999);
        weightLimit.addLow(0, 2720);
        weightLimit.addStandard(2719, 3221);
        weightLimit.addHigh(3220, 9999);

    }
}
