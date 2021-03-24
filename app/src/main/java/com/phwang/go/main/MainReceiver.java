/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.phwang.go.bind.BindUClient;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.CommandDefine;
import com.phwang.go.define.IntentDefine;

public class MainReceiver extends BroadcastReceiver {
    private static final String TAG = "MainReceiver";
    private MainActivity mainActivity_;

    private BindUClient BindUClient() { return this.mainActivity_.bindUClient(); }

    public MainReceiver(MainActivity main_activity_val) {
        this.mainActivity_ = main_activity_val;
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();
        String stamp = bundle.getString(BundleIndexDefine.STAMP);
        if ((stamp == null) || !stamp.equals(BundleIndexDefine.THE_STAMP)) {
            Log.e(TAG, "onReceive() bad-stamp. command=" + bundle.getString(BundleIndexDefine.COMMAND));
            return;
        }
        this.handleReceivedBundle(bundle);
    }

    private void do_setup_session(String his_name_val, String theme_data_val) {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.MAIN_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_COMMAND);
        intent.putExtra(BundleIndexDefine.COMMAND, CommandDefine.FABRIC_COMMAND_SETUP_SESSION_STR);
        intent.putExtra(BundleIndexDefine.HIS_NAME, his_name_val);
        intent.putExtra(BundleIndexDefine.THEME_DATA, theme_data_val);
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.mainActivity_.sendBroadcast(intent);
    }

    private void do_setup_session3(String theme_data_val) {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.MAIN_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_COMMAND);
        intent.putExtra(BundleIndexDefine.COMMAND, CommandDefine.FABRIC_COMMAND_SETUP_SESSION3_STR);
       intent.putExtra(BundleIndexDefine.THEME_DATA, theme_data_val);
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.mainActivity_.sendBroadcast(intent);
    }


    private void handleReceivedBundle(Bundle bundle_val) {
        String command = bundle_val.getString(BundleIndexDefine.COMMAND);
        String result = bundle_val.getString(BundleIndexDefine.RESULT);
        Log.e(TAG, "handleReceivedBundle() command=" + command + ", result=" + result);

        if (command == null) {
            Log.e(TAG, "handleReceivedBundle() null command========================");
            return;
        }

        switch (command.charAt(0)) {
            case CommandDefine.FABRIC_COMMAND_SETUP_LINK:
                this.do_setup_session("phwang", "00000000G111111");
                break;

            case CommandDefine.FABRIC_COMMAND_SETUP_SESSION:
                this.do_setup_session3("00000000G111111");
                break;

            case CommandDefine.FABRIC_COMMAND_SETUP_SESSION3:
                break;

            default:
        }
    }
}
