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
 * Created by dumh on 14/12/8.
 */
public class UserInfo {
    public static final int MAN = 0;
    public static final int WOMAN = 1;
    private int p_no;
    private int height;
    private int age;
    private int sex;

    /**
     * 新的男性信息
     * @param p_no p_no
     * @param height height
     * @param age age
     * @return UserInfo
     */
    public static UserInfo newManInfo(int p_no, int height, int age) {
        return new UserInfo(p_no, height, age, MAN);
    }

    /**
     * 新的女性信息
     * @param p_no p_no
     * @param height height
     * @param age age
     * @return UserInfo
     */
    public static UserInfo newWomenInfo(int p_no, int height, int age) {
        return new UserInfo(p_no, height, age, WOMAN);
    }

    public UserInfo(int p_no, int height, int age, int sex) {
        this.p_no = p_no;
        this.height = height;
        this.age = age;
        this.sex = sex;
    }

    public int getP_no() {
        return p_no;
    }

    public int getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public int getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return String.format("UserInfo{p_no=%d, height=%d, age=%d, sex=%d}", p_no, height, age, sex);
    }
}
