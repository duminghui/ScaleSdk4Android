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
 * 0x00 锁定<br>
 * 0x01 未锁定<br>
 * 实时重量传输	S-&gt;A	0x53	0x07	0x05	0x20	0x00-0x01	WeightH	WeighthL
 * ACK CMD	A-&gt;S	0x41	0x04	0x05	0x20
 * Created by dumh on 14/12/8.
 */
public class Cmd20 extends ACmd<Cmd20.Cmd20Values> {
    public static final byte CMD_ID = 0x20;

    @Override
    protected Cmd20Values _s2a(byte[] values, Cmd20Values source) {
        source.stableFlag = values[4];
        int weight = HexUtils.hexBytes2Int(values[5], values[6]);
        source.weight = ((float) weight / 200);
        return source;
    }

    @Override
    protected byte[] _a2s() {
        return new byte[]{0x41, 0x04, 0x05, CMD_ID};
    }

    @Override
    protected String getTAG() {
        return "Cmd20";
    }

    public static class Cmd20Values {

        public int stableFlag;
        public float weight;

        @Override
        public String toString() {
            return String.format("Cmd20Values{stableFlag=%d, weight=%s}", stableFlag, weight);
        }
    }
}
