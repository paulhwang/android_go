/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.utils;

public class Encoders {
    public static String iEncode(int number_val, int size_val) {
        String str = Integer.toString(number_val);

        StringBuilder buf = new StringBuilder();
        for (int i = str.length(); i < size_val; i++) {
            buf.append('0');
        }
        buf.append(str);
        return buf.toString();
    }

    public static int iDecode(String str_val) {
        int str_len = str_val.length();
        int val = 0;

        for (int i = 0; i < str_len; i++) {
            val += str_val.charAt(i) - '0';
            if (i < str_len - 1) {
                val *= 10;
            }
        }
        return val;
    }

    public static String sEncode(String str_val, int size_val) {
        StringBuilder buf = new StringBuilder();
        buf.append(Encoders.iEncode(str_val.length(), size_val));
        buf.append(str_val);
        return buf.toString();
    }

    public static String sEncode2(String str_val) {
        return sEncode(str_val, 2);
    }

    public static String sEncode5(String str_val) {
        return sEncode(str_val, 5);
    }

    public static String sDecode(String str_val, int size_val) {
        int len = Encoders.iDecode(str_val.substring(0, size_val));
        return str_val.substring(size_val, size_val + len);
    }

    public static String sDecode2(String str_val) {
        return sDecode(str_val, 2);
    }

    public static String sDecode5(String str_val) {
        return sDecode(str_val, 5);
    }

    public static String sDecode_(String str_val, int size_val) {
        return str_val.substring(size_val + Encoders.iDecode(str_val.substring(0, size_val)));
    }

    public static String sDecode2_(String str_val) {
        return sDecode_(str_val, 2);
    }

    public static String sDecode5_(String str_val) {
        return sDecode_(str_val, 5);
    }
}
