/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.join;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.phwang.go.R;
import com.phwang.go.define.IntentDefine;

public class GoJoinActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GoJoinActivity";
    private GoJoinReceiver goJoinReceiver_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.e(TAG, "onCreate() thread_id=" + Thread.currentThread().getId());
        setContentView(R.layout.activity_go_join);
        this.registerBroadcastReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterBroadcastReceiver();
    }

    @Override
    public void onClick(View view_val) {
    }

    private void registerBroadcastReceiver() {
        if (this.goJoinReceiver_ == null) {
            this.goJoinReceiver_ = new GoJoinReceiver(this);
            IntentFilter filter = new IntentFilter();
            filter.addAction(IntentDefine.GO_CONFIG_ACTIVITY);
            this.registerReceiver(this.goJoinReceiver_, filter);
        }
    }

    private void unregisterBroadcastReceiver() {
        if (this.goJoinReceiver_ != null) {
            this.unregisterReceiver(this.goJoinReceiver_);
            this.goJoinReceiver_ = null;
        }
    }
}
