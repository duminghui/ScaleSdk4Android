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
 * 获取所有用户参数	A-&gt;S	0x41	0x04	0x05	0x13
 * ↓
 * 上传用户参数	S-&gt;A	0x53	0x11	0x05	0x23	0x01	P_No.	身高	年龄	性别	P_No.	身高	年龄	性别	P_No.	身高	年龄	性别
 * ACK CMD	A-&gt;S	0x41	0x05	0x05	0x23	0x01
 * ↓
 * 上传用户参数	S-&gt;A	0x53	0x11	0x05	0x23	0x02	P_No.	身高	年龄	性别	P_No.	身高	年龄	性别	P_No.	身高	年龄	性别
 * ACK CMD	A-&gt;S	0x41	0x05	0x05	0x23	0x02
 * ↓
 * 上传用户参数	S-&gt;A	0x53	0x0D	0x05	0x23	0x03	P_No.	身高	年龄	性别	P_No.	身高	年龄	性别
 * ACK CMD	A-&gt;S	0x41	0x05	0x05	0x23	0x03
 * Created by dumh on 14/12/8.
 */
public class Cmd13_23 extends ACmd<Byte> {
    public static final byte CMD_ID = 0x13;
    @Override
    protected Byte _s2a(byte[] values, Byte source) {
        return null;
    }

    @Override
    protected byte[] _a2s() {
        return new byte[]{0x41, 0x04, 0x05, CMD_ID};
    }

    @Override
    protected String getTAG() {
        return "Cmd13_23";
    }
}
