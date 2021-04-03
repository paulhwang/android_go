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

    protected void handleResponse(Bundle bundle_val) {
        String link_id_str;
        String session_id_str;
        String command = bundle_val.getString(BundleIndexDefine.COMMAND);
        String result = bundle_val.getString(BundleIndexDefine.RESULT);
        String data_package_str = bundle_val.getString(BundleIndexDefine.DATA_PACKAGE);
        Log.e(TAG, "handleResponse() command=" + command + ", result=" + result + " data_package=" + data_package_str);

        if (command == null) {
            Log.e(TAG, "handleResponse() null command=");
            return;
        }

        switch (command.charAt(0)) {
            case FabricCommands.FABRIC_COMMAND_REGISTER:
                this.sendResponseBroadcastMessage(IntentDefine.MAIN_ACTIVITY, command, result, data_package_str);
                break;

            case FabricCommands.FABRIC_COMMAND_LOGIN:
                this.sendResponseBroadcastMessage(IntentDefine.LOGIN_ACTIVITY, command, result, data_package_str);
                break;

            case FabricCommands.FABRIC_COMMAND_LOGOUT:
                this.sendResponseBroadcastMessage(IntentDefine.MAIN_ACTIVITY, command, result, data_package_str);
                break;

            case FabricCommands.FABRIC_COMMAND_GET_GROUPS:
                this.sendResponseBroadcastMessage(IntentDefine.MAIN_ACTIVITY, command, result, data_package_str);
                break;

            case FabricCommands.FABRIC_COMMAND_SOLO_SESSION:
                this.sendResponseBroadcastMessage(IntentDefine.GO_CONFIG_ACTIVITY, command, result, data_package_str);
                break;

            case FabricCommands.FABRIC_COMMAND_HEAD_SESSION:
                this.sendResponseBroadcastMessage(IntentDefine.GO_CONFIG_ACTIVITY, command, result, data_package_str);
                break;

            case FabricCommands.FABRIC_COMMAND_PEER_SESSION:
                this.sendResponseBroadcastMessage(IntentDefine.GO_CONFIG_ACTIVITY, command, result, data_package_str);
                break;

            case FabricCommands.FABRIC_COMMAND_JOIN_SESSION:
                this.sendResponseBroadcastMessage(IntentDefine.GO_CONFIG_ACTIVITY, command, result, data_package_str);
                break;

            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION:
                this.sendResponseBroadcastMessage(IntentDefine.GO_CONFIG_ACTIVITY, command, result, data_package_str);
                break;

            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION3:
                this.sendResponseBroadcastMessage(IntentDefine.MAIN_ACTIVITY, command, result, data_package_str);
                break;

            case FabricCommands.FABRIC_COMMAND_PUT_SESSION_DATA:
                this.sendResponseBroadcastMessage(IntentDefine.GO_GAME_ACTIVITY, command, result, data_package_str);
                break;

            case FabricCommands.FABRIC_COMMAND_GET_SESSION_DATA:
                String rest_str = Encoders.sDecode5(data_package_str);
                link_id_str = Encoders.sSubstring2(rest_str);
                rest_str = Encoders.sSubstring2_(rest_str);

                session_id_str = Encoders.sSubstring2(rest_str);
                rest_str = Encoders.sSubstring2_(rest_str);

                String data_str = Encoders.sSubstring5(rest_str);
                //rest_str = Encoders.sSubstring5_(rest_str);

                Log.e(TAG, "handleResponse(FABRIC_COMMAND_GET_SESSION_DATA) data=" + data_str);

                ///////////////////////////////
                String theme_data_str = Encoders.sDecode5(data_str);

                Log.e(TAG, "handleResponse(FABRIC_COMMAND_GET_SESSION_DATA) theme_data=" + theme_data_str);

                if (theme_data_str.charAt(0) == ThemeDefine.THEME_GO) {
                    this.sendResponseBroadcastMessage(IntentDefine.GO_GAME_ACTIVITY, command, result, data_package_str);
                }
                else {
                    Log.e(TAG, "handleResponse() ***not implemented yet+++");
                }
                break;

            default:
        }
    }

    private void sendResponseBroadcastMessage(String target_val, String command_val, String result_val, String data_package_val) {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.BIND_SERVICE);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_RESPONSE);
        intent.putExtra(BundleIndexDefine.COMMAND, command_val);
        intent.putExtra(BundleIndexDefine.RESULT, result_val);
        intent.putExtra(BundleIndexDefine.DATA_PACKAGE, data_package_val);
        intent.setAction(target_val);
        this.bindService().sendBroadcast(intent);
    }
}
