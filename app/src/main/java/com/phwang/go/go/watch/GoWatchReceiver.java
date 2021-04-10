/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.watch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class GoWatchReceiver extends BroadcastReceiver {
    private static final String TAG = "GoWatchReceiver";
    private GoWatchActivity goWatchActivity_;

    public GoWatchReceiver(GoWatchActivity go_watch_activity_val) {
        this.goWatchActivity_ = go_watch_activity_val;
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();

    }
}
