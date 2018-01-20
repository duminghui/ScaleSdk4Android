package com.hcoor.sdk.oldcmd;

import android.util.Log;

import com.hcoor.sdk.HexUtils;

/**
 * 选择用户称
 * A-&gt;S 0x09 0x0F 0x01 0xA5(Key) MAC1 MAC2 MAC3 MAC4 MAC5 MAC6 0x09 身高 年龄 性别(0:男,1:女) 单位(1:kg,2:lb,4:st)
 * S-&gt;A 0x09 0c05 0x01 0xA5(KEY) 0x01
 * Created by dumh on 14/11/6.
 */
public class CmdA5 implements ICmd<Integer> {
    private static final String TAG = "SDK_BT";
    private String mac;
    private int height;
    private int age;
    private int sex;
    private int unit;

    public CmdA5() {

    }

    public void setSendDatas(String mac, int height, int age, int sex, int unit) {
        this.mac = mac;
        this.height = height;
        this.age = age;
        this.sex = sex;
        this.unit = unit;
    }

    public static void main(String[] args) {
        CmdA5 a5 = new CmdA5();
        a5.setSendDatas("D0:39:72:C7:BF:F4", 170, 20, 0, 1);
        a5.a2s();
    }

    @Override
    public Integer s2a(byte[] values, Integer source) {
        return (int) values[4];
    }

    @Override
    public byte[] a2s() {
        byte[] bytes = new byte[15];
        bytes[0] = 0x09;
        bytes[1] = 0x0f;
        bytes[2] = 0x01;
        bytes[3] = (byte) 0xA5;
        String[] macHexs = mac.split(":");
        for (int index = 0; index < 6; index++) {
            bytes[index + 4] = (byte) HexUtils.hexStr2Byte(macHexs[5 - index]);
        }
        bytes[10] = 0x09;
        bytes[11] = (byte) height;
        bytes[12] = (byte) age;
        bytes[13] = (byte) sex;
        bytes[14] = (byte) unit;
//        System.out.println(String.format("CmdA5:a2s:hex:%s", DataUtils.bytes2HexStr(bytes)));
        Log.i(TAG, String.format("CmdA5:a2s:hex:%s", HexUtils.bytes2HexStr(bytes)));
        return bytes;
    }
}
