/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.solo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class GoSoloReceiver extends BroadcastReceiver {
    private static final String TAG = "GoSoloReceiver";
    private GoSoloActivity goSoloActivity_;

    public GoSoloReceiver(GoSoloActivity go_solo_activity_val) {
        this.goSoloActivity_ = go_solo_activity_val;
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();

    }
}
