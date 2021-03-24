/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.CommandDefine;

public class GoReceiver extends BroadcastReceiver {
    private static final String TAG = "GoReceiver";
    private GoGame goGameActivity_;

    protected GoGameFunc goGameFunc() { return this.goGameActivity_.goGameFunc(); };

    public GoReceiver(GoGame go_game_activity_val) {
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
        Log.e(TAG, "handleReceivedBundle() command=" + command + ", result=" + result);

        if (command != null) {
            switch (command.charAt(0)) {
                case CommandDefine.FABRIC_COMMAND_PUT_SESSION_DATA:
                    this.goGameFunc().do_get_session_data();
                    break;

                case CommandDefine.FABRIC_COMMAND_GET_SESSION_DATA:
                    String data = bundle_val.getString(BundleIndexDefine.DATA);
                    this.goGameFunc().processGetSessionData(data);
                    break;

                default:
                    break;
            }
        }
    }
}