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
 * 新建用户	A-&gt;S	0x41	0x07	0x05	0x10	身高	年龄	性别
 * ACK CMD	S-&gt;A	0x53	0x05	0x05	0x10	0x01-0x0C
 * Created by dumh on 14/12/8.
 */
public class Cmd10 extends ACmd<Byte> {
    public static final byte CMD_ID = 0x10;
    private int height;
    private int age;
    private int sex;

    public void setValues(int height, int age, int sex) {
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
        return new byte[]{0x41, 0x07, 0x05, CMD_ID, (byte) height, (byte) age, (byte) sex};
    }

    @Override
    protected String getTAG() {
        return "Cmd10";
    }
}
