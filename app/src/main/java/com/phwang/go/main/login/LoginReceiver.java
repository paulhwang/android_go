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
import com.phwang.core.protocols.fabric.FabricResponseIndex;
import com.phwang.core.protocols.fabric.FabricSize;
import com.phwang.core.protocols.fabric.FabricInfo;
import com.phwang.core.utils.encoders.DecodeStringEntry;
import com.phwang.core.utils.encoders.Encoders;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.global.GlobalData;

public class LoginReceiver extends BroadcastReceiver {
    private static final String TAG = "phwang LoginReceiver";
    private LoginActivity signInActivity_;

    public LoginReceiver(LoginActivity sign_in_activity_val) {
        this.signInActivity_ = sign_in_activity_val;
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();
        String fabric_data = bundle.getString(BundleIndexDefine.FABRIC_DATA);
        Log.e(TAG, "onReceive() fabric_data=" + fabric_data);

        /* l***001002000116phwang */
        char response = fabric_data.charAt(FabricResponseIndex.CODE_INDEX);
        String result = fabric_data.substring(FabricResponseIndex.RESULT_INDEX, FabricResponseIndex.RESULT_INDEX + FabricSize.RESULT_SIZE);
        String link_id = fabric_data.substring(FabricResponseIndex.LINK_ID_INDEX, FabricResponseIndex.LINK_ID_INDEX + FabricSize.LINK_ID_SIZE);
        String encoded_my_name = fabric_data.substring(FabricResponseIndex.NEXT_TO_LINK_ID_INDEX);

        if (response != FabricCommands.FABRIC_RESPONSE_LOGIN) {
            Log.e(TAG, "onReceive()" + " Bad response! data=" + fabric_data);
            return;
        }

        DecodeStringEntry name_string_entry = Encoders.decodeString(encoded_my_name);
        if (!name_string_entry.data.equals(signInActivity_.userName_)) {
            Log.e(TAG, "onReceive() userName ERROR!!! " + name_string_entry.data + "!=" + this.signInActivity_.userName_);
        }

        if (result.equals(FabricResults.SUCCEED_STR)) {
            GlobalData.setLinkIdStr(link_id);
            GlobalData.setUserName(name_string_entry.data);
            Log.e(TAG, "onReceive()" + " Succeed!");
            this.signInActivity_.finish();
        }
        else {
            Log.e(TAG, "onReceive()" + " Fail! error_code=" + result);
        }
    }
}
