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
import com.phwang.go.define.CommandDefine;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.define.ThemeDefine;
import com.phwang.go.bind.BindUClient;

public class BindReceiver extends BroadcastReceiver {
    private static final String TAG = "BindReceiver";
    private BindService bindService_;
    private BindReceiverUFunc bindServiceUFunc_;
    private BindReceiverDFunc bindServiceDFunc_;

    protected BindService bindService() { return this.bindService_; };
    protected BindUClient bindUClient() { return bindService_.bindUClient(); }
    protected BindReceiverUFunc bindServiceUFunc() { return this.bindServiceUFunc_; };

    public BindReceiver(BindService bind_service_val) {
        this.bindService_ = bind_service_val;
        this.bindServiceDFunc_ = new BindReceiverDFunc(this);
        this.bindServiceUFunc_ = new BindReceiverUFunc(this);
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();
        String stamp = bundle.getString(BundleIndexDefine.STAMP);
        if ((stamp == null) || !stamp.equals(BundleIndexDefine.THE_STAMP)) {
            Log.e(TAG, "onReceive() bad-stamp. command=" + bundle.getString(BundleIndexDefine.COMMAND));
            return;
        }

        //Log.e(TAG, "onReceive() from=" + bundle.getString(BundleIndexDefine.FROM));
        String command_or_response = bundle.getString(BundleIndexDefine.COMMAND_OR_RESPONSE);
        if (command_or_response.equals(BundleIndexDefine.IS_COMMAND)) {
            this.bindServiceUFunc_.handleCommand(bundle);
        }
        else if (command_or_response.equals(BundleIndexDefine.IS_RESPONSE)) {
            this.bindServiceDFunc_.handleResponse(bundle);
        }
        else {
            Log.e(TAG, "onReceive() TBD=");
        }
    }
}
