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
 * 烧录称体端ID	A-&gt;S	0x41	0x0A	0x05	0x1C	ID1	ID2	ID3	ID4	ID5	ID6<br>
 * ACK CMD	S-&gt;A	0x53	0x05	0x05	0x1C	0x01-0x02<br>
 * 1:失败<br>
 * 2:成功<br>
 * Created by dumh on 14/12/8.
 */
public class Cmd1C extends ACmd<Byte> {
    public static final byte CMD_ID = 0x1c;
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected Byte _s2a(byte[] values, Byte source) {
        return values[4];
    }

    @Override
    protected byte[] _a2s() {
        byte[] ids = HexUtils.hex2Bytes(id);
        return new byte[]{0x41, 0x0a, 0x05, CMD_ID, ids[0], ids[1], ids[2], ids[3], ids[4], ids[5]};
    }

    @Override
    protected String getTAG() {
        return "Cmd1C";
    }
}
