/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.services;

import android.os.Bundle;
import android.util.Log;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.CommandDefine;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.define.ThemeDefine;

public class BindReceiverDFunc {
    private static final String TAG = "BindReceiverDFunc";
    BindReceiver bindReceiver_;

    protected BindReceiverUFunc bindServiceUFunc() { return this.bindReceiver_.bindServiceUFunc(); };

    public BindReceiverDFunc(BindReceiver bind_receiver_val) {
        this.bindReceiver_ = bind_receiver_val;
    }

    protected void handleResponse(Bundle bundle_val) {
        String command = bundle_val.getString(BundleIndexDefine.COMMAND);
        String result = bundle_val.getString(BundleIndexDefine.RESULT);
        String data = bundle_val.getString(BundleIndexDefine.DATA);
        Log.e(TAG, "handleResponse() command=" + command + ", result=" + result);

        if (command == null) {
            Log.e(TAG, "handleResponse() null command=");
            return;
        }

        switch (command.charAt(0)) {
            case CommandDefine.FABRIC_COMMAND_SETUP_LINK:
                this.bindServiceUFunc().sendResponseBroadcastMessage(IntentDefine.LOGIN_ACTIVITY, command, result, data);
                break;

            case CommandDefine.FABRIC_COMMAND_SOLO_SESSION:
                this.bindServiceUFunc().sendResponseBroadcastMessage(IntentDefine.GO_CONFIG_ACTIVITY, command, result, data);
                break;

            case CommandDefine.FABRIC_COMMAND_HEAD_SESSION:
                this.bindServiceUFunc().sendResponseBroadcastMessage(IntentDefine.GO_CONFIG_ACTIVITY, command, result, data);
                break;

            case CommandDefine.FABRIC_COMMAND_PEER_SESSION:
                this.bindServiceUFunc().sendResponseBroadcastMessage(IntentDefine.GO_CONFIG_ACTIVITY, command, result, data);
                break;

            case CommandDefine.FABRIC_COMMAND_JOIN_SESSION:
                this.bindServiceUFunc().sendResponseBroadcastMessage(IntentDefine.GO_CONFIG_ACTIVITY, command, result, data);
                break;

            case CommandDefine.FABRIC_COMMAND_SETUP_SESSION:
                this.bindServiceUFunc().sendResponseBroadcastMessage(IntentDefine.GO_CONFIG_ACTIVITY, command, result, data);
                break;

            case CommandDefine.FABRIC_COMMAND_SETUP_SESSION3:
                this.bindServiceUFunc().sendResponseBroadcastMessage(IntentDefine.MAIN_ACTIVITY, command, result, data);
                break;

            case CommandDefine.FABRIC_COMMAND_PUT_SESSION_DATA:
                this.bindServiceUFunc().sendResponseBroadcastMessage(IntentDefine.GO_GAME_ACTIVITY, command, result, data);
                break;

            case CommandDefine.FABRIC_COMMAND_GET_SESSION_DATA:
                if (data.charAt(0) == ThemeDefine.THEME_GO) {
                    this.bindServiceUFunc().sendResponseBroadcastMessage(IntentDefine.GO_GAME_ACTIVITY, command, result, data.substring(1));
                }
                else {
                    Log.e(TAG, "handleResponse() ***not implemented yet+++");
                }
                break;

            default:
        }
    }
}
