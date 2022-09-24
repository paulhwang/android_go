/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.utils.abend;

import android.util.Log;

public class Logit {
    private static final String phwang = "PHWANG ";

    public static void v(String tag_val, String str_val) {
        Log.e(phwang + tag_val, str_val);
    }
    public static void d(String tag_val, String str_val) {
        Log.e(phwang + tag_val, str_val);
    }
    public static void i(String tag_val, String str_val) {
        Log.e(phwang + tag_val, str_val);
    }
    public static void w(String tag_val, String str_val) {
        Log.e(phwang + tag_val, str_val);
    }
    public static void e(String tag_val, String str_val) {
        Log.e(phwang + tag_val, str_val);
    }
    public static void f(String tag_val, String str_val) {
        Log.e(phwang + tag_val, str_val);
    }
}
