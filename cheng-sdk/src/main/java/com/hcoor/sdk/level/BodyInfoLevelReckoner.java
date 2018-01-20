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

import com.hcoor.sdk.BodyInfo;
import com.hcoor.sdk.UserInfo;

import java.math.BigDecimal;

/**
 * 身体数据各指标等级计算<br>
 * <p>
 * Created by dumh on 14/12/10.
 */
public class BodyInfoLevelReckoner {
    private BodyInfoLevel bodyInfoLevel;
    private int age;
    private int sex;

    private int bf;
    private int muscle;
    private int water;
    private int weight;
    private int bmi;
    private int bone;
    private int inFat;
    private int bmr;
    private int bodyAge;

    public BodyInfoLevelReckoner(UserInfo userInfo, BodyInfo bodyInfo) {
        this.age = userInfo.getAge();
        this.sex = userInfo.getSex();
        this.bf = (int) (bodyInfo.bf * 100);
        this.muscle = (int) (bodyInfo.muscle * 100);
        this.water = (int) (bodyInfo.water * 100);
        this.weight = (int) (bodyInfo.weight * 100);
        this.inFat = bodyInfo.inFat;
        bodyInfo.noBfWeight = bodyInfo.weight * (100 - bodyInfo.bf) / 100;
        BigDecimal bigDecimal = new BigDecimal(bodyInfo.noBfWeight);
        bodyInfo.noBfWeight = bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();

        int height = userInfo.getHeight();
        bodyInfo.bmi = bodyInfo.weight * 10000 / (height * height);
        bigDecimal = new BigDecimal(bodyInfo.bmi);
        bodyInfo.bmi = bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();

        this.bmi = (int) (bodyInfo.bmi * 100);
        this.bone = (int) (bodyInfo.bone * 100);
        this.inFat = bodyInfo.inFat;
        this.bmr = bodyInfo.bmr;
        this.bodyAge = bodyInfo.bodyAge;

        bodyInfoLevel = new BodyInfoLevel();
    }

    public BodyInfoLevel toReckon() {
        bf_level();
        muscle_level();
        water_level();
        bmi_level();
        noFatWeight_level();
        bone_level();
        inFat_level();
        bmr_level();
        bodyAge_level();
        return bodyInfoLevel;
    }

    private int reckonLevel(int[][] levelValues, int value) {
        for (int[] levels : levelValues) {
//            System.out.println(String.format("t:%s,sex:%s,age:(%s,%s),weight(%s,%s),value([%s,%s],%s)",value,levels[0],levels[1],levels[2],levels[3],levels[4],levels[5],levels[6],levels[7]));
//            System.out.println(String.format("s:%s,a:%s,w:%s,v:%s",sex,age,weight,value));
//            System.out.println(String.format("#levels[0]:%s != sex:%s___%s",levels[0],sex,levels[0] != sex));
            if (levels[0] != sex) {
                continue;
            }
//            System.out.println(String.format("#age:%s > levels[1]:%s && age < levels[2]:%s___%s",age,levels[1],levels[2],age > levels[1] && age < levels[2]));
            if (!(age > levels[1] && age < levels[2])) {
                continue;
            }
//            System.out.println(String.format("#weight:%s > levels[3]:%s && age < levels[4]:%s___%s",weight,levels[3],levels[4],weight > levels[3] && age < levels[4]));
            if (!(weight > levels[3] && weight < levels[4])) {
                continue;
            }
//            System.out.println(String.format("#value:%s > levels[5]:%s && value < levels[6]:%s___%s",value,levels[5],levels[6],value > levels[5] && value < levels[6]));
            if (value > levels[5] && value < levels[6]) {
                return levels[7];
            }
        }
        return BodyInfoLevel.NOT_AVAILABLE;
    }

    private void bf_level() {
        bodyInfoLevel.bf_level = reckonLevel(LevelDataBF.LEVELS, bf);
    }

    private void muscle_level() {
        bodyInfoLevel.muscle_level = reckonLevel(LevelDataMuscle.LEVELS, muscle);
    }

    private void water_level() {
        bodyInfoLevel.water_level = reckonLevel(LevelDataWater.LEVELS, water);
    }

    private void bmi_level() {
        bodyInfoLevel.bmi_level = reckonLevel(LevelDataBMI.LEVELS, bmi);
    }

    private void noFatWeight_level() {
        bodyInfoLevel.noBfWeight_level = -bodyInfoLevel.bf_level;
    }

    private void bone_level() {
        System.out.println(bone);
        bodyInfoLevel.bone_level = reckonLevel(LevelDataBone.LEVELS, bone);
    }

    private void inFat_level() {
        bodyInfoLevel.inFat_level = reckonLevel(LevelDataInFat.LEVELS, inFat);
    }

    private void bmr_level() {
        bodyInfoLevel.bmr_level = reckonLevel(LevelDataBMR.LEVELS, bmr);
    }

    private void bodyAge_level() {
        int diff = bodyAge - age;
        int level;
        if (diff < 0) {
            level = BodyInfoLevel.HIGH;
        } else if (diff == 0 || diff == 1 || diff == 2) {
            level = BodyInfoLevel.STANDARD;
        } else {
            level = BodyInfoLevel.LOW;
        }
        bodyInfoLevel.bodyAge_level = level;
    }

    public static void main(String[] args) {


//        BodyInfo bodyInfo1 = new BodyInfo();
//        bodyInfo1.weight = 50.2f;
//        bodyInfo1.bf = 16.9f;
//        bodyInfo1.water = 56.5f;
//        bodyInfo1.muscle = 48.9f;
//        bodyInfo1.bone = 2.3f;
//        bodyInfo1.bmr = 1361;
//        bodyInfo1.inFat = 4;
//        bodyInfo1.bodyAge  = 31;
//
////        UserInfo userInfo1 = new UserInfo(9, 170, 31, 0);
//        UserInfo userInfo1 = UserInfo.newManInfo(9, 170, 29);
//        BodyInfoLevelReckoner reckoner = new BodyInfoLevelReckoner(userInfo1, bodyInfo1);
////        reckoner.water_level();
//        BodyInfoLevel bodyInfoLevel1 = reckoner.toReckon();
//        System.out.println(bodyInfoLevel1.bmi_level);
//        System.out.println(bodyInfo1.noBfWeight);
//        System.out.println(bodyInfoLevel1.noBfWeight_level);
//        System.out.println(bodyInfoLevel1.bf_level);
//        System.out.println(bodyInfoLevel1.water_level);
//        System.out.println(String.format("bone:%s", bodyInfoLevel1.bone_level));
////        System.out.println(((170 - 80)) * 70 * 4);
////        System.out.println(50.8 * 100 * 4);
////
////        //男 30岁 体重：65.5 身体：180;
////        int salt = (int) (65.5 * 100 * 5);
////        SaltLevelReckoner saltLevelReckoner = new SaltLevelReckoner(180);
////        int saltLevel = saltLevelReckoner.getLevel(0, 30, 6550, salt);

        BodyInfo bodyInfo1 = new BodyInfo();
        bodyInfo1.weight = 82.f;
        bodyInfo1.bf = 29.7f;
        bodyInfo1.water = 54.1f;
        bodyInfo1.muscle = 44.3f;
        bodyInfo1.bone = 3.0f;
        bodyInfo1.bmr = 1546;
        bodyInfo1.inFat = 9;
        bodyInfo1.bodyAge = 26;


        UserInfo userInfo1 = UserInfo.newManInfo(9,173,22);
        BodyInfoLevelReckoner reckoner1 = new BodyInfoLevelReckoner(userInfo1, bodyInfo1);
//        BodyInfoLevel level1 = reckoner1.toReckon();
//        System.out.println(level1.toString());
        reckoner1.bone_level();
        System.out.println(reckoner1.bodyInfoLevel.bone_level);
        System.out.println( (float) 0xfe / 10);
    }
}
