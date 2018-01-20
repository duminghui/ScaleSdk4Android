package com.hcoor.sdk.oldcmd;

import android.util.Log;

import com.hcoor.sdk.BodyInfo;
import com.hcoor.sdk.HexUtils;

/**
 * 稳定数据
 * S-&gt;A	0x08 0x12 0x01 0xB1 0x02 0x02 0x09 MuscleH MuscleL Bone BMRH BMRL SFatH SFatL InFat BodyAge AMRH AMRL<br>
 * * S-&gt;A	0x08 0x10 0x01 0xB1 0x02 0x02 0x09 MuscleH MuscleL Bone BMRH BMRL SFatH SFatL InFat BodyAge <br>联调时的
 * A-&gt;S	0x09 0x05 0x01 0xB1 0x02
 * Created by dumh on 14/11/6.
 */
public class CmdB102 implements ICmd<BodyInfo> {
    private static final String TAG = "SDK_BT";

    public static void main(String[] args) {
        byte[] bytes = new byte[0x12];
        bytes[0] = 0x08;
        bytes[1] = 0x11;
        bytes[2] = 0x01;
        bytes[3] = (byte) 0xb1;
        bytes[4] = 0x02;
        bytes[5] = 0x02;
        bytes[6] = 0x09;
        //96.5%
        byte[] muscle = {0x03, (byte) 0xc5};
        System.arraycopy(muscle, 0, bytes, 7, 2);
        //5.8
        byte[] bone = {(byte) 0x3A};
        System.arraycopy(bone, 0, bytes, 9, 1);
        //1101
        byte[] bmr = {0x04, 0x4D};
        System.arraycopy(bmr, 0, bytes, 10, 2);
        //67.9%
        byte[] sfat = {0x02, (byte) 0xA7};
        System.arraycopy(sfat, 0, bytes, 12, 2);
        //14 infat
        bytes[14] = 0x0e;
        //30 bodyAge
        bytes[15] = 0x32;
        //amr 1596
        byte[] amr = {0x06, 0x3c};
        System.arraycopy(amr, 0, bytes, 16, 2);
        new CmdB102().s2a(bytes, null);
    }

    @Override
    public BodyInfo s2a(byte[] values, BodyInfo source) {
        float muscle = (float) HexUtils.hexBytes2Int(values[7], values[8]) / 10;
        source.muscle = muscle;
        float bone = (float) values[9] / 10;
        int bmr = HexUtils.hexBytes2Int(values[10], values[11]);
        float sFat = (float) HexUtils.hexBytes2Int(values[12], values[13]) / 10;
        int inFat = values[14];
        int bodyAge = values[15];
//        byte[] amrBytes = {values[16], values[17]};
//        int amr = DataUtils.hexBytes2Int(amrBytes);
        int amr = 0;
        Log.i(TAG, String.format("[CmdB0102]:muscle:%s|bone:%s|bmr:%s|sFat:%s|inFat:%s|bodyAge:%s", muscle, bone, bmr, sFat, inFat, bodyAge));
        source.setGroup2(muscle, bone, bmr, inFat, bodyAge);
        return source;
    }

    @Override
    public byte[] a2s() {
        return new byte[]{0x09, 0x05, 0x01, (byte) 0xB1, 0x02};
    }
}
