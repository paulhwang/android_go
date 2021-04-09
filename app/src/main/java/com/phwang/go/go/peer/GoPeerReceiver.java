/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.peer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class GoPeerReceiver extends BroadcastReceiver {
    private static final String TAG = "GoPeerReceiver";
    private GoPeerActivity goConfigActivity_;

    public GoPeerReceiver(GoPeerActivity go_peer_activity_val) {
        this.goConfigActivity_ = go_peer_activity_val;
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();

    }
}
