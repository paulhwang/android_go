/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.services;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.phwang.core.fabric.FabricCommands;
import com.phwang.core.utils.encoders.Encoders;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.define.ThemeDefine;

public class BindReceiverDFunc {
    private static final String TAG = "BindReceiverDFunc";
    BindReceiver bindReceiver_;

    private BindService bindService() { return this.bindReceiver_.bindService(); };
    protected BindReceiverUFunc bindServiceUFunc() { return this.bindReceiver_.bindServiceUFunc(); };

    public BindReceiverDFunc(BindReceiver bind_receiver_val) {
        this.bindReceiver_ = bind_receiver_val;
    }

    public void sendResponseBroadcastMessage(String target_val, String command_val, String result_val, String data_package_val) {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.BIND_SERVICE);
        intent.putExtra(BundleIndexDefine.COMMAND, command_val);
        intent.putExtra(BundleIndexDefine.RESULT, result_val);
        intent.putExtra(BundleIndexDefine.DATA_PACKAGE, data_package_val);
        intent.setAction(target_val);
        this.bindService().sendBroadcast(intent);
    }

    public void parseFabricResponse(String target_val, String command_val, String result_val, String data_package_str_val) {
        Log.e(TAG, "parseFabricResponse() command=" + command_val + " result=" + result_val + " data=" + data_package_str_val);

        this.sendResponseBroadcastMessage(target_val, command_val, result_val, data_package_str_val);
    }

    private String getSessionDataTargetActivity(String data_package_str_val) {
        String rest_str = Encoders.sDecode5(data_package_str_val);
        String link_id_str = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

        String session_id_str = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

        String data_str = Encoders.sSubstring5(rest_str);
        //rest_str = Encoders.sSubstring5_(rest_str);

        Log.e(TAG, "handleResponse(FABRIC_COMMAND_GET_SESSION_DATA) data=" + data_str);

        ///////////////////////////////
        String theme_data_str = Encoders.sDecode5(data_str);

        Log.e(TAG, "handleResponse(FABRIC_COMMAND_GET_SESSION_DATA) theme_data=" + theme_data_str);

        if (theme_data_str.charAt(0) == ThemeDefine.THEME_GO) {
            return IntentDefine.GO_GAME_ACTIVITY;
        }
        else {
            Log.e(TAG, "handleResponse() ***not implemented yet+++");
            return IntentDefine.GO_GAME_ACTIVITY;
        }
    }
}
