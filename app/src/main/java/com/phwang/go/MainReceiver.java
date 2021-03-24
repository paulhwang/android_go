/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.phwang.go.bind.BindUClient;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.CommandDefine;

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

    private void handleReceivedBundle(Bundle bundle_val) {
        String command = bundle_val.getString(BundleIndexDefine.COMMAND);
        String result = bundle_val.getString(BundleIndexDefine.RESULT);
        Log.e(TAG, "handleReceivedBundle() command=" + command + ", result=" + result);

        if (command != null) {
            switch (command.charAt(0)) {
                case CommandDefine.FABRIC_COMMAND_SETUP_LINK:
                    this.BindUClient().doSetupSession("phwang", "00000000G111111");
                    break;

                case CommandDefine.FABRIC_COMMAND_SETUP_SESSION:
                    this.BindUClient().doSetupSession3();
                    break;

                case CommandDefine.FABRIC_COMMAND_SETUP_SESSION3:
                    break;

                default:
            }
        }

        //this.BindUClient().doSetupSession("phwang", "00000000G100000");
    }
}
