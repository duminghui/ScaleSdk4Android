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
 * 读取存储数据	A-&gt;S	0x41	0x05	0x05	0x16	P_No.
 * ACK CMD	S-&gt;A	0x53	0x06	0x05	0x16	0x01-0x03	N
 * ↓
 * 上传存储数据	S-&gt;A	0x53	0x11	0x05	0x26	N	0x01	P_No.	TMIE1	TMIE2	TMIE3	TMIE4	WeightH	WeighthL	BFH	BFL	WatrerH	WatrerL
 * ACK CMD	A-&gt;S	0x41	0x06	0x05	0x26	N	0x01
 * ↓
 * 上传存储数据	S-&gt;A	0x53	0x0E	0x05	0x26	N	0x02	P_No.	MuscleH	MuscleL	Bone	BMRH	BMRL	InFat	BodyAge
 * ACK CMD	A-&gt;S	0x41	0x06	0x05	0x26	N	0x02
 * Created by dumh on 14/12/8.
 */
public class Cmd16_26 extends ACmd<Cmd16_26.ResultInfo> {
    public static final byte CMD_ID = 0x16;
    private byte p_no;


    public void setValues(int p_no) {
        this.p_no = (byte) p_no;
    }

    @Override
    protected ResultInfo _s2a(byte[] values, ResultInfo source) {
        source.result = values[4];
        if (source.result == 0x01) {
            source.n = values[5];
        }
        return source;
    }

    @Override
    protected byte[] _a2s() {
        return new byte[]{0x41, 0x05, 0x05, CMD_ID, p_no};
    }

    @Override
    protected String getTAG() {
        return "Cmd16_26";
    }

    public static class ResultInfo {
        public byte result;
        public byte n;

        @Override
        public String toString() {
            return String.format("ResultInfo{result=%s, n=%s}", result, n);
        }
    }
}
