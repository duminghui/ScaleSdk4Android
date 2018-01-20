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
package com.hcoor.sdk.cmd.unuse;

import com.hcoor.sdk.cmd.ACmd;

/**
 * 用户参数更新	A-&gt;S	0x41	0x08	0x05	0x12	P_No.	身高	年龄	性别
 * ACK CMD	S-&gt;A	0x53	0x05	0x05	0x12	0x01-0x04
 * Created by dumh on 14/12/8.
 */
public class Cmd12 extends ACmd<Byte> {
    public static final byte CMD_ID = 0x12;
    private int p_no;
    private int height;
    private int age;
    private int sex;

    public void setValues(int p_no, int height, int age, int sex) {
        this.p_no = p_no;
        this.height = height;
        this.age = age;
        this.sex = sex;
    }

    @Override
    protected Byte _s2a(byte[] values, Byte source) {
        return values[4];
    }

    @Override
    protected byte[] _a2s() {
        return new byte[]{0x41, 0x08, 0x05, CMD_ID, (byte) p_no, (byte) height, (byte) age, (byte) sex};
    }

    @Override
    protected String getTAG() {
        return "Cmd12";
    }
}
