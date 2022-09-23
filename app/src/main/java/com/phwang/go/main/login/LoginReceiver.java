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
        String fabric_data = bundle.getString(BundleIndexDefine.FABRIC_DATA);
        Log.e(TAG, "LoginReceiver.onReceive() fabric_data_str=" + fabric_data);

        /* l***001002000116phwang */
        char response = fabric_data.charAt(0);
        String result = fabric_data.substring(4, 6);
        String link_id_str = fabric_data.substring(6, 14);
        String my_name = fabric_data.substring(14);
        if (my_name != this.signInActivity_.userName_) {
            Log.e(TAG, "LoginReceiver.onReceive() userName ERROR!!! " + my_name + "!=" + this.signInActivity_.userName_);
        }

        switch (response) {
            case FabricCommands.FABRIC_RESPONSE_LOGIN:
                if (result == FabricResults.SUCCEED_STR) {
                    GlobalData.setLinkIdStr(link_id_str);
                    GlobalData.setUserName(my_name);
                    Log.e(TAG, "LoginReceiver.onReceive() GlobalData.linkIdSt()=" + GlobalData.linkIdStr());
                    this.signInActivity_.finish();
                }
                else {

                }
                break;
            default:
                Log.e(TAG, "LoginReceiver.onReceive() BAD_RESPONSSE=" + response);
                break;
        }
    }
}
