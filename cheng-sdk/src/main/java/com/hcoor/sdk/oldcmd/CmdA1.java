package com.hcoor.sdk.oldcmd;

import android.util.Log;

import com.hcoor.sdk.HexUtils;
import com.hcoor.sdk.TimeUtils;

/**
 * 设备向App请求系统时间的命令
 * B-&gt;A 06H 04H 01H A1H(KEY)<br>
 * A-&gt;B 07H 08H 01H A1H(KEY) TIME1 TIME2 TIME3 TIME4
 * <p>
 * Created by dumh on 14/11/5.
 */
public class CmdA1 implements ICmd<Object> {
    private final static String TAG = "SDK_BT";

    @Override
    public Object s2a(byte[] values, Object source) {
        return null;
    }

    @Override
    public byte[] a2s() {
        byte[] times = TimeUtils.getTimes();
        byte[] writeValues = new byte[8];
        writeValues[0] = 0x07;
        writeValues[1] = 0x08;
        writeValues[2] = 0x01;
        writeValues[3] = (byte) 0xA1;
        System.arraycopy(times, 0, writeValues, 4, 4);
//        writeValues[4] = times[0];
//        writeValues[5] = times[1];
//        writeValues[6] = times[2];
//        writeValues[7] = times[3];
        Log.i(TAG, String.format("CmdA1:a2s:hex:%s", HexUtils.bytes2HexStr(writeValues)));
        return writeValues;
    }
}
