/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.join;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.phwang.go.define.IntentDefine;

public class GoJoinReceiver extends BroadcastReceiver {
    private static final String TAG = "GoJoinReceiver";
    private GoJoinActivity goJoinActivity_;

    public GoJoinReceiver(GoJoinActivity go_join_activity_val) {
        this.goJoinActivity_ = go_join_activity_val;
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();

    }
}
