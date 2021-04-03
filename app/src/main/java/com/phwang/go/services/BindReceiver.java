/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.phwang.go.define.BundleIndexDefine;

public class BindReceiver extends BroadcastReceiver {
    private static final String TAG = "BindReceiver";
    private BindService bindService_;
    private BindReceiverUFunc bindReceiverUFunc_;
    private BindReceiverDFunc bindReceiverDFunc_;

    protected BindService bindService() { return this.bindService_; };
    protected BindReceiverUFunc bindServiceUFunc() { return this.bindReceiverUFunc_; };
    protected BindReceiverDFunc bindReceiverDFunc() { return this.bindReceiverDFunc_; };

    public BindReceiver(BindService bind_service_val) {
        this.bindService_ = bind_service_val;
        this.bindReceiverDFunc_ = new BindReceiverDFunc(this);
        this.bindReceiverUFunc_ = new BindReceiverUFunc(this);
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();
        String stamp = bundle.getString(BundleIndexDefine.STAMP);
        if ((stamp == null) || !stamp.equals(BundleIndexDefine.THE_STAMP)) {
            Log.e(TAG, "onReceive() bad-stamp. command=" + bundle.getString(BundleIndexDefine.COMMAND));
            return;
        }

        this.bindReceiverUFunc_.handleCommand(bundle);
    }
}
