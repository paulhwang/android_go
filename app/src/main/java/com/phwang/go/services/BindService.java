/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.phwang.go.bind.BindDClient;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.bind.BindMain;

public class BindService extends Service {
    private static final String TAG = "BindService";
    private Context applicationContext_;
    private static BindMain bindMain_;

    public static BindMain bindMain() { return bindMain_; };
    public BindDClient bindDClient() { return this.bindMain_.bindDClient(); }

    public Context applicationContext() { return this.applicationContext_; };

    @Override
    public void onCreate() {
        super.onCreate();
        this.applicationContext_ = getApplicationContext();
        this.registerBroadcastReceiver();
        this.bindMain_ = new BindMain(this, this.applicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, " onStartCommand()");

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
        this.unregisterBroadcastReceiver();
        super.onDestroy();
    }

    private BindReceiver bindReceiver_;
    private void registerBroadcastReceiver() {
        if (this.bindReceiver_ == null) {
            this.bindReceiver_ = new BindReceiver(this);
            IntentFilter filter = new IntentFilter();
            filter.addAction(IntentDefine.BIND_SERVICE);
            this.registerReceiver(this.bindReceiver_, filter);
        }
    }

    private void unregisterBroadcastReceiver() {
        if (this.bindReceiver_ != null) {
            this.unregisterReceiver(this.bindReceiver_);
            this.bindReceiver_ = null;
        }
    }
}
