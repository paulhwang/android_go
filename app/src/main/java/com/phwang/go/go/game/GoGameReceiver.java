/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.game;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.phwang.core.fabric.FabricCommands;
import com.phwang.go.define.BundleIndexDefine;

public class GoGameReceiver extends BroadcastReceiver {
    private static final String TAG = "GoGameReceiver";
    private GoGameActivity goGameActivity_;

    private GoGameBoard goGameBoard() { return this.goGameActivity_.goBoard(); }
    protected GoGameDFunc goGameDFunc() { return this.goGameActivity_.goGameDFunc(); };
    protected GoGameUFunc goGameUFunc() { return this.goGameActivity_.goGameUFunc(); };
    private Boolean isDead() { return this.goGameActivity_.isDead(); };
    private String sessionIdStr() { return this.goGameActivity_.sessionIdStr(); }

    public GoGameReceiver(GoGameActivity go_game_activity_val) {
        this.goGameActivity_ = go_game_activity_val;
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();
        String stamp = bundle.getString(BundleIndexDefine.STAMP);
        if ((stamp == null) || !stamp.equals(BundleIndexDefine.THE_STAMP)) {
            Log.e(TAG, "onReceive() bad stamp. command=" + bundle.getString(BundleIndexDefine.COMMAND));
            return;
        }
        this.handleReceivedBundle(bundle);
    }

    private void handleReceivedBundle(Bundle bundle_val) {
        String command = bundle_val.getString(BundleIndexDefine.COMMAND);
        String result = bundle_val.getString(BundleIndexDefine.RESULT);
        String data_package_str = bundle_val.getString(BundleIndexDefine.DATA_PACKAGE);
        Log.e(TAG, "handleReceivedBundle(000) command=" + command + ", result=" + result + " data_package=" + data_package_str);

        if (command != null) {
            switch (command.charAt(0)) {
                case FabricCommands.FABRIC_COMMAND_SOLO_SESSION:
                    Log.e(TAG, "handleReceivedBundle(***ERROR***) fix it! command=" + command + ", result=" + result + " data_package=" + data_package_str);
                    break;

                case FabricCommands.FABRIC_COMMAND_PUT_SESSION_DATA:
                    if (!this.isDead()) {
                        this.goGameUFunc().sendGetSessionDataCommand();
                    }
                    else {
                        Log.e(TAG, "handleReceivedBundle(FABRIC_COMMAND_PUT_SESSION_DATA) isDead session_id=" + this.sessionIdStr());
                    }
                    break;

                case FabricCommands.FABRIC_COMMAND_GET_SESSION_DATA:
                    if (!this.isDead()) {
                        this.goGameDFunc().parseGetSessionData(data_package_str);
                    }
                    else {
                        Log.e(TAG, "handleReceivedBundle(FABRIC_COMMAND_GET_SESSION_DATA) isDead session_id=" + this.sessionIdStr());
                    }
                    break;

                default:
                    break;
            }
        }
    }
}
