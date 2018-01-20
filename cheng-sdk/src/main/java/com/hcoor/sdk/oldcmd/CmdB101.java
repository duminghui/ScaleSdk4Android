package com.hcoor.sdk.oldcmd;

import android.util.Log;

import com.hcoor.sdk.BodyInfo;
import com.hcoor.sdk.HexUtils;
import com.hcoor.sdk.TimeUtils;

/**
 * 稳定数据
 * S-&gt;A	0x08 0x11 0x01 0yxB1 0x02 0x01 0x09 TMIE1 TMIE2 TMIE3 TMIE4 WeightH WeighthL BFH BFL WatrerH WatrerL<br>
 * A-&gt;S	0x09 0x05 0x01 0xB1 0x01
 * Created by dumh on 14/11/6.
 */
public class CmdB101 implements ICmd<BodyInfo> {
    private static final String TAG = "SDK_BT";

    public static void main(String[] args) {
        byte[] bytes = new byte[0x11];
        bytes[0] = 0x08;
        bytes[1] = 0x11;
        bytes[2] = 0x01;
        bytes[3] = (byte) 0xb1;
        bytes[4] = 0x02;
        bytes[5] = 0x01;
        bytes[6] = 0x09;
        byte[] times = TimeUtils.getTimes();
        System.arraycopy(times, 0, bytes, 7, 4);
        //59.8
        byte[] weight = {0x04, (byte) 0xac};
        System.arraycopy(weight, 0, bytes, 11, 2);
        //30.8%
        byte[] bf = {0x01, 0x34};
        System.arraycopy(bf, 0, bytes, 13, 2);
        //67.9%
        byte[] writer = {0x02, (byte) 0xA7};
        System.arraycopy(writer, 0, bytes, 15, 2);
        new CmdB101().s2a(bytes, null);
    }

    @Override
    public BodyInfo s2a(byte[] values, BodyInfo source) {
//        byte[] timeBytes = new byte[]{values[7], values[8], values[9], values[10]};
        long time = HexUtils.hexBytes2Long(values[7],values[8],values[9],values[10]);
        float weight = (float) HexUtils.hexBytes2Int(values[11], values[12]) / 200;
        float bf = (float) HexUtils.hexBytes2Int(values[13], values[14]) / 10;
        float water = (float) HexUtils.hexBytes2Int(values[15], values[16]) / 10;
        Log.i(TAG, String.format("[CmdB0101]time:%s,weight:%s;bf:%s:water:%s", time, weight, bf, water));
        source.setGroup1(9, time, weight, bf, water);
        return source;
    }

    @Override
    public byte[] a2s() {
        return new byte[]{0x09, 0x05, 0x01, (byte) 0xB1, 0x01};
    }
}
