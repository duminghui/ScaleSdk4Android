package com.hcoor.sdk;

/**
 * Created by dumh on 14/11/4.
 */
public class TimeUtils {
    /**
     * 2000-1-1的秒数
     */
    private final static long DATE_2000Y = 946656000L;

    /**
     * 返回当前到时间到2000年的秒数
     *
     * @return 秒数
     */
    public static byte[] getTimes() {
        long date_diff = System.currentTimeMillis() / 1000 - DATE_2000Y;
        byte[] result = new byte[4];
        result[0] = (byte)((date_diff >> 24) & 0xFF);
        result[1] = (byte)((date_diff >> 16) & 0xFF);
        result[2] = (byte)((date_diff >> 8) & 0xFF);
        result[3] = (byte)((date_diff) & 0xFF);
        return result;
    }

    public static long toTimeMillis(byte time1, byte time2, byte time3, byte time4) {
        long _timeMillis = HexUtils.hexBytes2Long(time1, time2, time3, time4);
        return (_timeMillis + DATE_2000Y) * 1000;

    }

    public static void main(String[] args) {
        byte[] times = getTimes();
        System.out.println(HexUtils.byte2HexLog(times));
    }
}
