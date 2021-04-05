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
import com.phwang.core.utils.fabric.FabricDataStr;
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

    public void sendFabricDataResponse(String target_val, String fabric_data_str_val) {
        Log.e(TAG, "sendFabricDataResponse() fabric_data_str_val=" + fabric_data_str_val);

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.BIND_SERVICE);
        intent.putExtra(BundleIndexDefine.COMMAND, FabricDataStr.getCommandStr(fabric_data_str_val));
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_data_str_val);
        intent.setAction(target_val);
        this.bindService().sendBroadcast(intent);
    }
}
