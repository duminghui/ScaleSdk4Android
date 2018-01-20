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
 * 基础等级计算
 * Created by dumh on 14/12/11.
 */
public abstract class BaseLevelReckoner {
    private Limit rootLimit;

    public BaseLevelReckoner() {
        rootLimit = new Limit(0, 0);
        init(rootLimit);
    }

    protected abstract void init(Limit rootLimit);

    public int getLevel(int sex, int age, int weight,int value) {
        Limit sexLimit = rootLimit.findSubLimit(sex);
        if (sexLimit != null) {
            Limit ageLimit = sexLimit.findSubLimit(age);
            if (ageLimit != null) {
                Limit weightLimit = sexLimit.findSubLimit(weight);
                if (weightLimit != null) {
                    Limit valueLimit = weightLimit.findSubLimit(value);
                    if (valueLimit != null) {
                        return valueLimit.getLevel();
                    }
                }
            }
        }
        return BodyInfoLevel.NOT_AVAILABLE;
    }

}
