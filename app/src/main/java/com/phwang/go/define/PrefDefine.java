/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.define;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class PrefDefine {
    public static final String PREF_GO = "PREF_GO";
    public static final String PREF_SERVER_IP_ADDRESS = "SERVER_IP_ADDRESS";

    public static String readIpAddress(Context context_val) {
        SharedPreferences pref = context_val.getSharedPreferences(PREF_GO, MODE_PRIVATE);
        String ip_addr = pref.getString(PrefDefine.PREF_SERVER_IP_ADDRESS, "0");
        return ip_addr;
    }

    public static void writeIpAddress(Context context_val, String ip_address_val) {
        SharedPreferences pref = context_val.getSharedPreferences(PREF_GO, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PrefDefine.PREF_SERVER_IP_ADDRESS, ip_address_val);
        editor.commit();
    }
}
