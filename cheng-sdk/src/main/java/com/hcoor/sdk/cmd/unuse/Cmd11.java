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
 * 删除用户	A-&gt;S	0x41	0x05	0x05	0x11	P_No.
 * ACK CMD	S-&gt;A	0x53	0x05	0x05	0x11	0x01-0x03
 * Created by dumh on 14/12/8.
 */
public class Cmd11 extends ACmd<Byte> {
    public static final byte CMD_ID = 0x11;
    private int p_no;

    public void setValues(int p_no) {
        this.p_no = p_no;
    }

    @Override
    protected Byte _s2a(byte[] values, Byte source) {
        return values[4];
    }

    @Override
    protected byte[] _a2s() {
        return new byte[]{0x41, 0x05, 0x05, CMD_ID, (byte) p_no};
    }

    @Override
    protected String getTAG() {
        return "Cmd11";
    }
}
