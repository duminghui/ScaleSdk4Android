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
package com.hcoor.sdk.cmd;

/**
 * 读取称体端软件版本	A-&gt;S	0x41	0x04	0x05	0x1E
 * ACK CMD	S-&gt;A	0x53	0x05	0x05	0x1E	Ver
 * Created by dumh on 14/12/8.
 */
public class Cmd1E extends ACmd<Byte> {
    public static final byte CMD_ID = 0x1e;

    @Override
    protected Byte _s2a(byte[] values, Byte source) {
        return values[4];
    }

    @Override
    protected byte[] _a2s() {
        return new byte[]{0x41, 0x04, 0x05, CMD_ID};
    }

    @Override
    protected String getTAG() {
        return "Cmd1E";
    }
}
