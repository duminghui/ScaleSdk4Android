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

import com.hcoor.sdk.BodyInfo;

/**
 * 上传测量数据	S-&gt;A	0x53	0x11	0x05	0x21	0x02	0x01	P_No.	TMIE1	TMIE2	TMIE3	TMIE4	WeightH	WeighthL	BFH	BFL	WatrerH	WatrerL
 * ACK CMD	A-&gt;S	0x41	0x05	0x05	0x21	0x01
 * ↓
 * 上传测量数据	S-&gt;A	0x53	0x0E	0x05	0x21	0x02	0x02	P_No.	MuscleH	MuscleL	Bone	BMRH	BMRL	InFat	BodyAge
 * ACK CMD	A-&gt;S	0x41	0x05	0x05	0x21	0x02
 * Created by dumh on 14/12/8.
 */
public class Cmd21_02 extends ACmd<BodyInfo> {
    public static final byte CMD_ID = 0x21;
    @Override
    protected BodyInfo _s2a(byte[] values, BodyInfo source) {
        BodyInfoProcess.setGroup2(source, values);
        return source;
    }

    @Override
    protected byte[] _a2s() {
        return new byte[]{0x41, 0x05, 0x05, CMD_ID, 0x02};
    }

    @Override
    protected String getTAG() {
        return "Cmd21_02";
    }
}
