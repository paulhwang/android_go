/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.main.register;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.phwang.core.protocols.fabric.FabricCommands;
import com.phwang.core.protocols.fabric.FabricDataStr;
import com.phwang.go.define.BundleIndexDefine;

public class RegisterReceiver extends BroadcastReceiver {
    private static final String TAG = "RegisterReceiver";
    private RegisterActivity signUpActivity_;

    public RegisterReceiver(RegisterActivity sign_up_activity_val) {
        this.signUpActivity_ = sign_up_activity_val;
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();
        String fabric_data_str = bundle.getString(BundleIndexDefine.FABRIC_DATA);
        char command = FabricDataStr.getCommand(fabric_data_str);
        Log.e(TAG, "handleReceivedBundle() command=" + command + ", fabric_data_str=" + fabric_data_str);

        switch (command) {
            case FabricCommands.FABRIC_COMMAND_REGISTER:
                break;

            default:
                break;
        }
    }
}
