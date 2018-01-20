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
import com.hcoor.sdk.HexUtils;
import com.hcoor.sdk.TimeUtils;

/**
 * 上传测量数据	S-&gt;A	0x53	0x11	0x05	0x21	0x02	0x01	P_No.	TMIE1	TMIE2	TMIE3	TMIE4	WeightH	WeighthL	BFH	BFL	WatrerH	WatrerL
 * ACK CMD	A-&gt;S	0x41	0x05	0x05	0x21	0x01
 * ↓
 * 上传测量数据	S-&gt;A	0x53	0x0E	0x05	0x21	0x02	0x02	P_No.	MuscleH	MuscleL	Bone	BMRH	BMRL	InFat	BodyAge
 * ACK CMD	A-&gt;S	0x41	0x05	0x05	0x21	0x02
 * 身体数据解析
 * Created by dumh on 14/12/9.
 */
public class BodyInfoProcess {
    public static void setGroup1(BodyInfo bodyInfo, byte[] values) {
        //53 11 05 21 02 01 09 1C A7 70 00 35 48 00 E2 02 54
        byte p_no = values[6];
        long time = TimeUtils.toTimeMillis(values[7], values[8], values[9], values[10]);
        float weight = (float) HexUtils.hexBytes2Int(values[11], values[12]) / 200;
        float bf = (float) HexUtils.hexBytes2Int(values[13], values[14]) / 10;
        float water = (float) HexUtils.hexBytes2Int(values[15], values[16]) / 10;
        bodyInfo.setGroup1(p_no, time, weight, bf, water);
    }

    public static void setGroup2(BodyInfo bodyInfo, byte[] values) {
        float muscle = (float) HexUtils.hexBytes2Int(values[7], values[8]) / 10;
        float bone = (float) values[9] / 10;
        int bmr = HexUtils.hexBytes2Int(values[10], values[11]);
        int inFat =  values[12];
        int bodyAge = values[13];
        bodyInfo.setGroup2(muscle, bone, bmr, inFat, bodyAge);
    }
}
