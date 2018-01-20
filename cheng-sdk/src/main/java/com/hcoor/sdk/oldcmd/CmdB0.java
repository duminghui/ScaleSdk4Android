package com.hcoor.sdk.oldcmd;

import com.hcoor.sdk.HexUtils;

/**
 * 临时体重
 * S-&gt;A 0x08 0x07 0x01 0xB0(KEY) 0/1(0:重量锁定，1：重定未锁定) WeightH WeightL<br>
 * A-&gt;S 0x09 0x04 0x01 0xB0(KEY)<br>
 * H和L组合后除以200得到KG数
 * Created by dumh on 14/11/6.
 */
public class CmdB0 implements ICmd<Float> {

    @Override
    public Float s2a(byte[] values, Float source) {
//        byte[] weightBytes = new byte[2];
//        weightBytes[0] = values[5];
//        weightBytes[1] = values[6];
        int weight = HexUtils.hexBytes2Int(values[5],values[6]);
        return ((float) weight / 200);
    }

    @Override
    public byte[] a2s() {
        return new byte[]{0x09, 0x04, 0x01, (byte) 0xB0};
    }
}
