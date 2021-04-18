/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.play;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class GoPlayReceiver extends BroadcastReceiver {
    private static final String TAG = "GoPlayReceiver";
    private GoPlayActivity goPlayActivity_;

    public GoPlayReceiver(GoPlayActivity go_play_activity_val) {
        this.goPlayActivity_ = go_play_activity_val;
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();

    }
}
