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
            Log.e("MainReceiver", "++++++++++++++++stamp" + bundle.getString(BundleIndexDefine.COMMAND));
            //return;
        }

        String command = bundle.getString(BundleIndexDefine.COMMAND);
        String result = bundle.getString(BundleIndexDefine.RESULT);
        Log.e("MainReceiver", "command=" + command + ", result=" + result);

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
