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

import com.hcoor.sdk.HexUtils;

/**
 * 读取称体端ID	A-&gt;S	0x41	0x04	0x05	0x1D
 * ACK CMD	S-&gt;A	0x53	0x0A	0x05	0x1D	ID1	ID2	ID3	ID4	ID5	ID6
 * Created by dumh on 14/12/8.
 */
public class Cmd1D extends ACmd<String> {
    public static final byte CMD_ID = 0x1d;

    @Override
    protected String _s2a(byte[] values, String source) {
        return HexUtils.bytes2HexStr(new byte[]{values[4], values[5], values[6], values[7], values[8], values[9]});
    }

    @Override
    protected byte[] _a2s() {
        return new byte[]{0x41, 0x04, 0x05, CMD_ID};
    }

    @Override
    protected String getTAG() {
        return "Cmd1D";
    }
}
