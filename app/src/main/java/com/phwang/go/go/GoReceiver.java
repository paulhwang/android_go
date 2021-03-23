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

import com.phwang.go.bind.BindService;
import com.phwang.go.define.BundleIndexDefine;

public class GoReceiver extends BroadcastReceiver {
    private GoGame goGameActivity_;

    public GoReceiver(GoGame go_game_activity_val) {
        this.goGameActivity_ = go_game_activity_val;
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Log.e("GoReceiver", "command=+++++++++++++++++++");
        Bundle bundle = intent_val.getExtras();
        String command = bundle.getString(BundleIndexDefine.COMMAND);
        Log.e("GoReceiver", "command=" + command);
    }
}
