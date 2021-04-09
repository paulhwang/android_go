/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.head;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class GoHeadReceiver extends BroadcastReceiver {
    private static final String TAG = "GoPeerReceiver";
    private GoHeadActivity goHeadActivity_;

    public GoHeadReceiver(GoHeadActivity go_head_activity_val) {
        this.goHeadActivity_ = go_head_activity_val;
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();

    }
}