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
 * 身体各数据等级
 * Created by dumh on 14/12/10.
 */
public class BodyInfoLevel {
    /**
     * 过低
     */
    public static final int TOO_LOW = -2;
    /**
     * 偏低、未达标、差、偏瘦、未达标
     */
    public static final int LOW = -1;
    /**
     * 标准、良、标准、达标
     */
    public static final int STANDARD = 0;
    /**
     * 偏高、优、偏胖
     */
    public static final int HIGH = 1;
    /**
     * 过高、肥胖
     */
    public static final int TOO_HIGH = 2;

    public static final int NOT_AVAILABLE = -999;
    /**
     * 脂肪等级
     */
    public int bf_level = STANDARD;

    /**
     * 肌肉等级
     */
    public int muscle_level = STANDARD;

    /**
     * 体水分率等级
     */
    public int water_level = STANDARD;
    /**
     * BMI指数等级
     */
    public int bmi_level = STANDARD;

    /**
     * 去脂体重等级
     */
    public int noBfWeight_level = STANDARD;

    /**
     * 骨骼重量等级
     */
    public int bone_level = STANDARD;

    /**
     * 内脏脂肪等级
     */
    public int inFat_level = STANDARD;

    /**
     * 基础代谢等级
     */
    public int bmr_level = STANDARD;

    /**
     * 身体年龄等级
     */
    public int bodyAge_level = STANDARD;

    @Override
    public String toString() {
        return "BodyInfoLevel{" +
                "bf_level=" + bf_level +
                ", muscle_level=" + muscle_level +
                ", water_level=" + water_level +
                ", bmi_level=" + bmi_level +
                ", noBfWeight_level=" + noBfWeight_level +
                ", bone_level=" + bone_level +
                ", inFat_level=" + inFat_level +
                ", bmr_level=" + bmr_level +
                ", bodyAge_level=" + bodyAge_level +
                '}';
    }
}
