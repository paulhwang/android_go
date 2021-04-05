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

import com.phwang.core.utils.abend.Abend;
import com.phwang.go.define.IntentDefine;

public class ClientService extends Service {
    private static final String TAG = "ClientService";
    private Context applicationContext_;
    private ClientReceiver bindReceiver_;
    private ClientRoot clientRoot_;

    public ClientRoot clientRoot() { return this.clientRoot_; };
    public ClientDParser clientDParser() { return this.clientRoot_.clientDParser(); }
    public ClientUBinder clientUBinder() { return this.clientRoot_.clientUBinder(); }
    public Context applicationContext() { return this.applicationContext_; };

    @Override
    public void onCreate() {
        super.onCreate();
        this.applicationContext_ = getApplicationContext();
        this.registerBroadcastReceiver();
        this.clientRoot_ = new ClientRoot(this);
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

    private void registerBroadcastReceiver() {
        if (this.bindReceiver_ == null) {
            this.bindReceiver_ = new ClientReceiver(this);
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
