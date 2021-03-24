/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;
import com.phwang.go.define.IntentDefine;

public class GoGame extends AppCompatActivity {
    private static final String TAG = "GoGame";
    private Context applicationContext_;
    private GoView goView;

    public Context applicationContext() { return this.applicationContext_; };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.applicationContext_ = getApplicationContext();
        Toast.makeText(this, "GoGame onCreate", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate");
        this.registerBroadcastReceiver();
        this.goView = new GoView(this);
        setContentView(this.goView);
        this.goView.requestFocus();
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
    public boolean onTouchEvent(MotionEvent event) {
        int x = ((int) event.getX() - this.goView.gridLen0 + this.goView.halfGridLen) / this.goView.gridLen;
        int y = ((int) event.getY() - this.goView.viewTop - this.goView.gridLen0 + this.goView.halfGridLen) / this.goView.gridLen;
        Log.e(TAG, "onTouchEvent: x=" + x + " y=" + y);
        switch( event.getAction() ) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:
                this.processTouchInput(x, y);
                break;
        }
       return super.onTouchEvent(event);
    }

    public void processTouchInput(int x_val, int y_val) {
        //this.BindUClient().doPutSessionData();

    }

    private GoReceiver goReceiver_;
    private void registerBroadcastReceiver() {
        if (this.goReceiver_ == null) {
            this.goReceiver_ = new GoReceiver(this);
            IntentFilter filter = new IntentFilter();
            filter.addAction(IntentDefine.GO_GAME_ACTIVITY);
            this.registerReceiver(this.goReceiver_, filter);
        }
    }

    private void unregisterBroadcastReceiver() {
        if (this.goReceiver_ != null) {
            this.unregisterReceiver(this.goReceiver_);
            this.goReceiver_ = null;
        }
    }
}
