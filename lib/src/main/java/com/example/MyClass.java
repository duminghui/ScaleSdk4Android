package com.example;

import java.text.DecimalFormat;

public class MyClass {
    private static final String ID_MATCH_REGEX="\\d{3}-?\\d{2}(0[1-9]|1[0-2])(3[0-1]|[1-2]\\d|0[1-9])-?\\d{3}";
    public static void main(String[] args) {
//        String str = "";
//        System.out.println(str.matches(ID_MATCH_REGEX));
        DecimalFormat df1 = new DecimalFormat("###,######,###");
        System.out.println(df1.format(10000000000L));

        String a = "1000";
//        System.out.println(a.substring(0,3));
//        System.out.println(a.substring(3,9));
//        System.out.println(a.substring(9,12));
        System.out.println(formatCandidateId(a));
        int b = 1;
        b+=10-2 -1;
        System.out.println(b);
        DecimalFormat df2 = new DecimalFormat("000");
        System.out.println(df2.format(1));

        System.out.println(String.format("%02X", 55));
    }

    private static String formatCandidateId(String src) {
        int[] limits = {3,9,src.length()};
        src = src.replaceAll("-", "");
        int length = src.length();
        StringBuilder sb = new StringBuilder();
        int start = 0, end;
        for (int tmpEnd: limits) {
            end = tmpEnd;
            if (tmpEnd < length) {
                sb.append(src.substring(start, end)).append("-");
                start = end;
            }else {
                sb.append(src.substring(start, length));
                break;
            }
        }
        return sb.toString();
    }
}
