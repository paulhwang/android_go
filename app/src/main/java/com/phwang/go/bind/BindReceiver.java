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

public class BindReceiver extends BroadcastReceiver {
    private static final String TAG = "BindReceiver";
    private BindService bindService_;

    //private BindUClient BindUClient() { return this.mainActivity_.bindUClient(); }

    public Context applicationContext() { return this.bindService_.applicationContext(); };

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
        this.handleReceivedBundle(bundle);
    }

    private void handleReceivedBundle(Bundle bundle_val) {
        String command = bundle_val.getString(BundleIndexDefine.COMMAND);
        String result = bundle_val.getString(BundleIndexDefine.RESULT);
        Log.e(TAG, "handleReceivedBundle() command=" + command + ", result=" + result);

        /////////////////////////////////////////////
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.COMMAND, CommandDefine.FABRIC_COMMAND_SETUP_LINK_STR);
        intent.setAction(IntentDefine.GO_GAME_ACTIVITY);
        this.applicationContext().sendBroadcast(intent);
        /////////////////////////////////////////////////////


        if (command != null) {
            switch (command.charAt(0)) {
                case CommandDefine.FABRIC_COMMAND_SETUP_LINK:
                    String name = bundle_val.getString(BundleIndexDefine.MY_NAME);
                    String password = bundle_val.getString(BundleIndexDefine.PASSWORD);
                    Log.e("BindReceiver", "command=" + command + " name=" + name + "," + password);
                    //this.BindUClient().doSetupSession(name, password);
                    break;

                case CommandDefine.FABRIC_COMMAND_SETUP_SESSION:
                    //this.BindUClient().doSetupSession3();
                    break;

                case CommandDefine.FABRIC_COMMAND_SETUP_SESSION3:
                    break;

                default:
            }
        }
    }
}
