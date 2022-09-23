/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.utils.encoders;

public class Encoders {
    public static final int DATA_LENGTH_SIZE = 2;
    public static final String IGNORE       = "06IGNORE";
    public static final String NULL_JOB     = "08NULL_JOB";
    public static final String NULL_DATA    = "09NULL_DATA";
    public static final String NULL_LINK    = "09NULL_LINK";
    public static final String JOB_IS_DONE  = "11JOB_IS_DONE";
    public static final String NULL_SESSION = "12NULL_SESSION";

    public static String charToString(char val) { StringBuilder buf = new StringBuilder(val); return buf.toString(); }

    public static String iEncodeRaw(int number_val, int size_val) {
        String str = Integer.toString(number_val);

        StringBuilder buf = new StringBuilder();
        for (int i = str.length(); i < size_val; i++) {
            buf.append('0');
        }
        buf.append(str);
        return buf.toString();
    }
    public static String iEncodeRaw1(int number_val) { return iEncodeRaw(number_val, 1); }
    public static String iEncodeRaw2(int number_val) { return iEncodeRaw(number_val, 2); }
    public static String iEncodeRaw3(int number_val) { return iEncodeRaw(number_val, 3); }
    public static String iEncodeRaw4(int number_val) { return iEncodeRaw(number_val, 4); }
    public static String iEncodeRaw5(int number_val) { return iEncodeRaw(number_val, 5); }
    public static String iEncodeRaw6(int number_val) { return iEncodeRaw(number_val, 6); }

    public static String iEncode111(int number_val, int size_val) {
        return iEncodeRaw(number_val, size_val);
    }

    public static String iEncodeLen(int number_val, int size_val) {
        String str = iEncodeRaw(number_val, size_val);
        return sEncode2(str);
    }
    public static String iEncodeLen1(int number_val) { return iEncodeLen(number_val, 1); }
    public static String iEncodeLen2(int number_val) { return iEncodeLen(number_val, 2); }
    public static String iEncodeLen3(int number_val) { return iEncodeLen(number_val, 3); }
    public static String iEncodeLen4(int number_val) { return iEncodeLen(number_val, 4); }
    public static String iEncodeLen5(int number_val) { return iEncodeLen(number_val, 5); }
    public static String iEncodeLen6(int number_val) { return iEncodeLen(number_val, 6); }

    public static int iDecodeRaw(String str_val) {
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

    public static int iDecode111(String str_val) {
        return iDecodeRaw(str_val);
    }

    public static int iDecodeLen(String str_val) {
        String str = sDecode2(str_val);
        return iDecodeRaw(str);
    }

    public static String encodeString(String str_val) {
        int length_size;
        int length = str_val.length();

        StringBuilder buf = new StringBuilder();
        if (length < 10) {
            buf.append("1");
            buf.append(iEncodeRaw(length, 1));
        }
        else if (length < 100) {
            buf.append("2");
            buf.append(iEncodeRaw(length, 2));
        }
        else if (length < 1000) {
            buf.append("3");
            buf.append(iEncodeRaw(length, 3));
        }
        else if (length < 10000) {
            buf.append("4");
            buf.append(iEncodeRaw(length, 4));
        }
        else if (length < 100000) {
            buf.append("5");
            buf.append(iEncodeRaw(length, 5));
        }

        buf.append(str_val);
        return buf.toString();
    }

    public static DecodeStringEntry decodeString(String input_val) {
        DecodeStringEntry output = null;
        int total_str_size;
        int length = 0;
        int head_size = 2;
        int index = 1;

        switch (input_val.charAt(0)) {
            case '5':
                length = length * 10 + input_val.charAt(index) - 48;
                index++;
                head_size++;

            case '4':
                length = length * 10 + input_val.charAt(index) - 48;
                index++;
                head_size++;

            case '3':
                length = length * 10 + input_val.charAt(index) - 48;
                index++;
                head_size++;

            case '2':
                length = length * 10 + input_val.charAt(index) - 48;
                index++;
                head_size++;

            case '1':
                length = length * 10 + input_val.charAt(index) - 48;
                index++;
                //head_size++;

                StringBuilder buf = new StringBuilder();
                buf.append(input_val.substring(index, index + length));
                output = new DecodeStringEntry(buf.toString(), length + head_size);
                break;

            default:
                break;
        }
        return output;
    }

    public static String sEncode(String str_val, int size_val) {
        StringBuilder buf = new StringBuilder();
        buf.append(Encoders.iEncodeRaw(str_val.length(), size_val));
        buf.append(str_val);
        return buf.toString();
    }
    public static String sEncode1(String str_val) { return sEncode(str_val, 1); }
    public static String sEncode2(String str_val) { return sEncode(str_val, 2); }
    public static String sEncode3(String str_val) { return sEncode(str_val, 3); }
    public static String sEncode4(String str_val) { return sEncode(str_val, 4); }
    public static String sEncode5(String str_val) { return sEncode(str_val, 5); }
    public static String sEncode6(String str_val) { return sEncode(str_val, 6); }

    public static String sDecode(String str_val, int size_val) {
        int len = Encoders.iDecodeRaw(str_val.substring(0, size_val));
        return str_val.substring(size_val, size_val + len);
    }
    public static String sDecode1(String str_val) { return sDecode(str_val, 1); }
    public static String sDecode2(String str_val) { return sDecode(str_val, 2); }
    public static String sDecode3(String str_val) { return sDecode(str_val, 3); }
    public static String sDecode4(String str_val) { return sDecode(str_val, 4); }
    public static String sDecode5(String str_val) { return sDecode(str_val, 5); }
    public static String sDecode6(String str_val) { return sDecode(str_val, 6); }

    public static String sDecode_(String str_val, int size_val) {
        return str_val.substring(size_val + Encoders.iDecodeRaw(str_val.substring(0, size_val)));
    }
    public static String sDecode1_(String str_val) { return sDecode_(str_val, 1); }
    public static String sDecode2_(String str_val) { return sDecode_(str_val, 2); }
    public static String sDecode3_(String str_val) { return sDecode_(str_val, 3); }
    public static String sDecode4_(String str_val) { return sDecode_(str_val, 4); }
    public static String sDecode5_(String str_val) { return sDecode_(str_val, 5); }
    public static String sDecode6_(String str_val) { return sDecode_(str_val, 6); }

    public static String sSubstring(String str_val, int size_val) {
        int len = Encoders.iDecodeRaw(str_val.substring(0, size_val));
        return str_val.substring(0, size_val + len);
    }
    public static String sSubstring1(String str_val) { return sSubstring(str_val, 1); }
    public static String sSubstring2(String str_val) { return sSubstring(str_val, 2); }
    public static String sSubstring3(String str_val) { return sSubstring(str_val, 3); }
    public static String sSubstring4(String str_val) { return sSubstring(str_val, 4); }
    public static String sSubstring5(String str_val) { return sSubstring(str_val, 5); }
    public static String sSubstring6(String str_val) { return sSubstring(str_val, 6); }


    public static String sSubstring_(String str_val, int size_val) {
        return str_val.substring(size_val + Encoders.iDecodeRaw(str_val.substring(0, size_val)));
    }
    public static String sSubstring1_(String str_val) { return sSubstring_(str_val, 1); }
    public static String sSubstring2_(String str_val) { return sSubstring_(str_val, 2); }
    public static String sSubstring3_(String str_val) { return sSubstring_(str_val, 3); }
    public static String sSubstring4_(String str_val) { return sSubstring_(str_val, 4); }
    public static String sSubstring5_(String str_val) { return sSubstring_(str_val, 5); }
    public static String sSubstring6_(String str_val) { return sSubstring_(str_val, 6); }
}
