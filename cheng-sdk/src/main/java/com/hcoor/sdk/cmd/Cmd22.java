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

import com.hcoor.sdk.TimeUtils;

/**
 * 读取APP系统时间	S-&gt;A	0x53	0x04	0x05	0x22
 * ACK CMD	A-&gt;S	0x41	0x08	0x05	0x22	TMIE1	TMIE2	TMIE3	TMIE4
 * Created by dumh on 14/12/8.
 */
public class Cmd22 extends ACmd<Object> {
    public static final byte CMD_ID = 0x22;

    @Override
    protected Object _s2a(byte[] values, Object source) {
        return null;
    }

    @Override
    protected byte[] _a2s() {
        byte[] times = TimeUtils.getTimes();
        byte[] writeValues = new byte[8];
        writeValues[0] = 0x41;
        writeValues[1] = 0x08;
        writeValues[2] = 0x05;
        writeValues[3] = CMD_ID;
        System.arraycopy(times, 0, writeValues, 4, 4);
//        writeValues[4] = times[0];
//        writeValues[5] = times[1];
//        writeValues[6] = times[2];
//        writeValues[7] = times[3];
        return writeValues;
    }

    @Override
    protected String getTAG() {
        return "Cmd22";
    }
}
