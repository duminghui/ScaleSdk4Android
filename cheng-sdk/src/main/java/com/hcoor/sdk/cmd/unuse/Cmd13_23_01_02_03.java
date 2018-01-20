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

import com.hcoor.sdk.UserInfo;
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
public class Cmd13_23_01_02_03 extends ACmd<UserInfo[]> {
    public static final byte CMD_ID = 0x23;
    private byte index;
    @Override
    protected UserInfo[] _s2a(byte[] values, UserInfo[] source) {
        index = values[4];
        int p_index = (index - 1) * 3;
        setUserInfo(source, p_index, values[4], values[5], values[6], values[7]);
        p_index++;
        setUserInfo(source, p_index, values[8], values[9], values[10], values[11]);
        p_index++;
        if (p_index != 8) {
            setUserInfo(source, p_index, values[12], values[13], values[14], values[15]);
        }
        return source;
    }

    private void setUserInfo(UserInfo[] userInfos, int index, byte p_no, byte height, byte age, byte sex) {
        userInfos[index] = new UserInfo(p_no, height, age, sex);
    }

    @Override
    protected byte[] _a2s() {
        return new byte[]{0x41, 0x05, 0x05, CMD_ID, index};
    }

    @Override
    protected String getTAG() {
        return "Cmd13_23_01_02_03";
    }
}
