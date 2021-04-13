/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.main.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.phwang.core.protocols.fabric.FabricCommands;
import com.phwang.core.protocols.fabric.FabricResults;
import com.phwang.core.protocols.fabric.FabricInfo;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.global.GlobalData;

public class LoginReceiver extends BroadcastReceiver {
    private static final String TAG = "LoginReceiver";
    private LoginActivity signInActivity_;

    public LoginReceiver(LoginActivity sign_in_activity_val) {
        this.signInActivity_ = sign_in_activity_val;
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();
        String fabric_data_str = bundle.getString(BundleIndexDefine.FABRIC_DATA);
        //Log.e(TAG, "handleReceivedBundle() fabric_data_str=" + fabric_data_str);

        FabricInfo fabric_decode = new FabricInfo(fabric_data_str);
        String link_id_str = fabric_decode.linkIdStr();

        switch (fabric_decode.command()) {
            case FabricCommands.FABRIC_COMMAND_LOGIN:
                if (fabric_decode.result() == FabricResults.SUCCEED) {
                    GlobalData.setLinkIdStr(link_id_str);
                    this.signInActivity_.finish();
                }
                else {

                }
                break;
            default:
                break;
        }
    }
}
