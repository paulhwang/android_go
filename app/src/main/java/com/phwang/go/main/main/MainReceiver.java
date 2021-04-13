/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.main.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.phwang.core.protocols.fabric.FabricCommands;
import com.phwang.core.protocols.fabric.FabricInfo;
import com.phwang.go.define.BundleIndexDefine;

public class MainReceiver extends BroadcastReceiver {
    private static final String TAG = "MainReceiver";
    private MainActivity mainActivity_;

    protected MainActivityFunc mainActivityFunc() { return this.mainActivity_.mainActivityFunc(); };

    public MainReceiver(MainActivity main_activity_val) {
        this.mainActivity_ = main_activity_val;
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();
        String fabric_data_str = bundle.getString(BundleIndexDefine.FABRIC_DATA);
        //Log.e(TAG, "handleReceivedBundle() fabric_data_str=" + fabric_data_str);

        FabricInfo fabric_decode = new FabricInfo(fabric_data_str);
        String link_id_str = fabric_decode.linkIdStr();

        //Log.e(TAG, "handleReceivedBundle() command=" + fabric_decode.command() + ", result=" + fabric_decode.result());

        switch (fabric_decode.command()) {
            case FabricCommands.FABRIC_COMMAND_REGISTER:
                Log.e(TAG, "handleReceivedBundle() FABRIC_COMMAND_REGISTER");
                break;

            case FabricCommands.FABRIC_COMMAND_LOGOUT:
                Log.e(TAG, "handleReceivedBundle() FABRIC_COMMAND_LOGOUT");
                break;

            case FabricCommands.FABRIC_COMMAND_GET_GROUPS:
                Log.e(TAG, "handleReceivedBundle() FABRIC_COMMAND_GET_GROUPS");
                break;

            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION:
                this.mainActivityFunc().do_setup_session3("00000000G111111");
                break;

            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION3:
                break;

            default:
                Log.e(TAG, "handleReceivedBundle() ***** not supported command=" + fabric_decode.command());
        }
    }
}
