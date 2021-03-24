/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.bind;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import com.phwang.go.define.IntentDefine;

public class BindService extends Service {
    private static final String TAG = "BindService";
    private Context applicationContext_;
    private static BindMain bindMain_;

    protected BindUClient bindUClient() { return bindMain_.bindUClient(); }

    public Context applicationContext() { return this.applicationContext_; };

    @Override
    public void onCreate() {
        super.onCreate();
        this.applicationContext_ = getApplicationContext();
        this.registerBroadcaseReceiver();
        this.bindMain_ = new BindMain(this.applicationContext());
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
        super.onDestroy();
    }

    private BindReceiver bindReceiver_;
    private void registerBroadcaseReceiver() {
        this.bindReceiver_ = new BindReceiver(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(IntentDefine.BIND_SERVICE);
        this.registerReceiver(this.bindReceiver_, filter);
    }
}
