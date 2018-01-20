package com.hcoor.sdk;

/**
 * Created by dumh on 14/11/2.
 */
public class HexUtils {

    public static void main(String[] args) {
//        System.out.println(Integer.toHexString(hexBytes2Int2((byte) 0xde, (byte) 0xde)));
        long tmp = hexBytes2Long(0xdd, 0xaa,  0xcd, 0xef);
        System.out.println(tmp);
        System.out.println(Long.toHexString(tmp));
//        System.out.println(Integer.toHexString(tmp));
        int tmp2 = hexBytes2Int(0x00,0xde);
        System.out.println(tmp2);
        System.out.println(Integer.toHexString(tmp2));
    }

    public static int hexBytes2Int(int heightByte, int lowByte) {
        return ((heightByte & 0xFF) << 8 ) + (lowByte & 0xFF);
    }

    public static long hexBytes2Long(long byte1, long byte2, long byte3, long byte4) {
        return ((byte1 & 0xFF) << 24) + ((byte2 & 0xFF) << 16) + ((byte3 & 0xFF) << 8) + (byte4 & 0xFF);
    }

//    public static long hex2Long(String hex) {
//        return Long.parseLong(hex, 16);
//    }
//
//    public static long hexBytes2Long(byte[] bytes) {
//        String hex = bytes2HexStr(bytes);
////        System.out.println(String.format("hexBytes2Long:%s", hex));
//        return hex2Long(hex);
//    }

    public static int hexStr2Byte(String hex) {
        return Integer.parseInt(hex, 16);
    }

//    public static int hexBytes2Int(byte[] bytes) {
//        String hex = bytes2HexStr(bytes);
////        System.out.println(String.format("hexBytes2Int:%s",hex));
//        return hexStr2Byte(bytes2HexStr(bytes));
//    }

    public static String bytes2HexStr(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return "NULL";
        }
        final StringBuilder stringBuilder = new StringBuilder(bytes.length);
        for (byte _byte : bytes) {
            stringBuilder.append(String.format("%02X", _byte));
        }
        return stringBuilder.toString();
    }

    public static String byte2HexLog(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return "NULL";
        }
        final StringBuilder stringBuilder = new StringBuilder(bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            stringBuilder.append(String.format("%s:%02X ", i, bytes[i]));
        }
        return stringBuilder.toString();
    }

    public static byte[] hex2Bytes(String hex) {
        int len = hex.length();
        int byteLen = len / 2;
        byte[] bytes = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
        }
        return bytes;
    }
}
