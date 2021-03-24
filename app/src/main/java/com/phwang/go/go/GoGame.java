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
    private GoGameFunc goGameFunc_;
    private GoBoard goBoard_;
    private GoView goView_;

    protected GoView goView() { return this.goView_; };
    protected GoBoard goBoard() { return this.goBoard_; };
    protected GoGameFunc goGameFunc() { return this.goGameFunc_; };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.goBoard_ = new GoBoard(this);
        this.goGameFunc_ = new GoGameFunc(this);

        this.goView_ = new GoView(this);
        setContentView(this.goView_);
        this.goView_.requestFocus();

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
    public boolean onTouchEvent(MotionEvent event) {
        int x = ((int) event.getX() - this.goView_.gridLen0 + this.goView_.halfGridLen) / this.goView_.gridLen;
        int y = ((int) event.getY() - this.goView_.viewTop - this.goView_.gridLen0 + this.goView_.halfGridLen) / this.goView_.gridLen;
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
        String move_str = this.goBoard_.encodeMove(x_val, y_val);
        //Log.e(TAG, "processTouchInput move=" + move_str);
        this.goGameFunc_.do_put_session_data(move_str);
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
