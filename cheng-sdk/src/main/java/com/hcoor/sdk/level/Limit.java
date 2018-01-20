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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dumh on 14/12/11.
 */
public class Limit {
    private int min;
    private int max;
    private int level;
    private List<Limit> subs;

    public Limit(int min, int max) {
        this(min, max, -999);
    }

    public Limit(int min, int max, int level) {
        this.min = min;
        this.max = max;
        this.level = level;
        subs = new ArrayList<>();
    }

    public int getLevel() {
        return level;
    }

    public void addSub(Limit sub) {
        subs.add(sub);
    }

    public Limit addSub(int min, int max, int level) {
        Limit sub = new Limit(min, max, level);
        addSub(sub);
        return sub;
    }

    public Limit addTooLow(int min, int max) {
        return addSub(min, max, BodyInfoLevel.TOO_LOW);
    }

    public Limit addLow(int min, int max) {
        return addSub(min, max, BodyInfoLevel.LOW);
    }

    public Limit addStandard(int min, int max) {
        return addSub(min, max, BodyInfoLevel.STANDARD);
    }

    public Limit addHigh(int min, int max) {
        return addSub(min, max, BodyInfoLevel.HIGH);
    }

    public Limit addTooHigh(int min, int max) {
        return addSub(min, max, BodyInfoLevel.TOO_LOW);
    }

    public Limit addSub(int min, int max) {
        Limit sub = new Limit(min, max);
        addSub(sub);
        return sub;
    }

    public Limit findSubLimit(int value) {
        for (Limit sub : subs) {
            if (value > sub.min && value < sub.max) {
                return sub;
            }
        }
        return null;
    }
}
