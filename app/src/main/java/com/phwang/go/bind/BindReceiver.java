/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.bind;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.CommandDefine;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.define.ThemeDefine;

public class BindReceiver extends BroadcastReceiver {
    private static final String TAG = "BindReceiver";
    private BindService bindService_;

    private BindUClient bindUClient() { return bindService_.bindUClient(); }

    public BindReceiver(BindService bind_service_val) {
        this.bindService_ = bind_service_val;
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();
        String stamp = bundle.getString(BundleIndexDefine.STAMP);
        if ((stamp == null) || !stamp.equals(BundleIndexDefine.THE_STAMP)) {
            Log.e(TAG, "onReceive() bad-stamp. command=" + bundle.getString(BundleIndexDefine.COMMAND));
            return;
        }

        //Log.e(TAG, "onReceive() from=" + bundle.getString(BundleIndexDefine.FROM));
        String command_or_response = bundle.getString(BundleIndexDefine.COMMAND_OR_RESPONSE);
        if (command_or_response.equals(BundleIndexDefine.IS_COMMAND)) {
            this.handleCommand(bundle);
        }
        else if (command_or_response.equals(BundleIndexDefine.IS_RESPONSE)) {
            this.handleResponse(bundle);
        }
        else {
            Log.e(TAG, "onReceive() TBD=");
        }
    }

    private void handleCommand(Bundle bundle_val) {
        String command = bundle_val.getString(BundleIndexDefine.COMMAND);
        Log.e(TAG, "handleCommand() command=" + command);

        if (command == null) {
            Log.e(TAG, "handleCommand() null command=");
            return;
        }

        switch (command.charAt(0)) {
            case CommandDefine.FABRIC_COMMAND_SETUP_LINK:
                String my_name = bundle_val.getString(BundleIndexDefine.MY_NAME);
                String password = bundle_val.getString(BundleIndexDefine.PASSWORD);
                Log.e(TAG, "handleReceivedBundle() command=" + command + " name=" + my_name + "," + password);
                this.bindUClient().doSetupLink(my_name, password);
                break;

            case CommandDefine.FABRIC_COMMAND_SETUP_SESSION:
                String his_name = bundle_val.getString(BundleIndexDefine.HIS_NAME);
                String theme_data = bundle_val.getString(BundleIndexDefine.THEME_DATA);
                this.bindUClient().doSetupSession(his_name, theme_data);
                break;

            case CommandDefine.FABRIC_COMMAND_SETUP_SESSION3:
                this.bindUClient().doSetupSession3();
                break;

            case CommandDefine.FABRIC_COMMAND_PUT_SESSION_DATA:
                String move_data = bundle_val.getString(BundleIndexDefine.MOVE_DATA);
                this.bindUClient().doPutSessionData(move_data);
                break;

            case CommandDefine.FABRIC_COMMAND_GET_SESSION_DATA:
                this.bindUClient().doGetSessionData();
                break;

            default:
        }
    }

    private void sendResponseBroadcastMessage(String target_val, String command_val, String result_val, String data_val) {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.BIND_SERVICE);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_RESPONSE);
        intent.putExtra(BundleIndexDefine.COMMAND, command_val);
        intent.putExtra(BundleIndexDefine.RESULT, result_val);
        intent.putExtra(BundleIndexDefine.DATA, data_val);
        intent.setAction(target_val);
        this.bindService_.sendBroadcast(intent);
    }

    private void handleResponse(Bundle bundle_val) {
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
                this.sendResponseBroadcastMessage(IntentDefine.MAIN_ACTIVITY, command, result, data);
                break;

            case CommandDefine.FABRIC_COMMAND_SETUP_SESSION:
                this.sendResponseBroadcastMessage(IntentDefine.MAIN_ACTIVITY, command, result, data);
                break;

            case CommandDefine.FABRIC_COMMAND_SETUP_SESSION3:
                this.sendResponseBroadcastMessage(IntentDefine.MAIN_ACTIVITY, command, result, data);
                break;

            case CommandDefine.FABRIC_COMMAND_PUT_SESSION_DATA:
                this.sendResponseBroadcastMessage(IntentDefine.GO_GAME_ACTIVITY, command, result, data);
                break;

            case CommandDefine.FABRIC_COMMAND_GET_SESSION_DATA:
                if (data.charAt(0) == ThemeDefine.THEME_GO) {
                    this.sendResponseBroadcastMessage(IntentDefine.GO_GAME_ACTIVITY, command, result, data.substring(1));
                }
                else {
                    Log.e(TAG, "handleResponse() ***not implemented yet+++");
                }
                break;

            default:
        }
    }
}
