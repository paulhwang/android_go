/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.peer;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.phwang.go.define.IntentDefine;

public class GoPeerActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GoPeerActivity";
    private GoPeerReceiver goPeerReceiver_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.e(TAG, "onCreate() thread_id=" + Thread.currentThread().getId());
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
        if (this.goPeerReceiver_ == null) {
            this.goPeerReceiver_ = new GoPeerReceiver(this);
            IntentFilter filter = new IntentFilter();
            filter.addAction(IntentDefine.GO_CONFIG_ACTIVITY);
            this.registerReceiver(this.goPeerReceiver_, filter);
        }
    }

    private void unregisterBroadcastReceiver() {
        if (this.goPeerReceiver_ != null) {
            this.unregisterReceiver(this.goPeerReceiver_);
            this.goPeerReceiver_ = null;
        }
    }
}
