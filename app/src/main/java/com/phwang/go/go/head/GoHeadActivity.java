/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.head;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.phwang.go.R;
import com.phwang.go.define.IntentDefine;

public class GoHeadActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GoHeadActivity";
    private GoHeadReceiver goHeadReceiver_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.e(TAG, "onCreate() thread_id=" + Thread.currentThread().getId());
        setContentView(R.layout.activity_go_head);
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
        if (this.goHeadReceiver_ == null) {
            this.goHeadReceiver_ = new GoHeadReceiver(this);
            IntentFilter filter = new IntentFilter();
            filter.addAction(IntentDefine.GO_HEAD_ACTIVITY);
            this.registerReceiver(this.goHeadReceiver_, filter);
        }
    }

    private void unregisterBroadcastReceiver() {
        if (this.goHeadReceiver_ != null) {
            this.unregisterReceiver(this.goHeadReceiver_);
            this.goHeadReceiver_ = null;
        }
    }
}
