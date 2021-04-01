/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.phwang.core.utils.Encoders;
import com.phwang.go.R;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;

import java.util.Timer;
import java.util.TimerTask;

public class GoGameActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GoGameActivity";
    private GoGameActivityFunc goGameFunc_;
    private GoGameBoard goBoard_;
    private GoGameView goView_;
    private GoGameReceiver goGameReceiver_;

    protected GoGameView goView() { return this.goView_; };
    protected GoGameBoard goBoard() { return this.goBoard_; };
    protected GoGameActivityFunc goGameFunc() { return this.goGameFunc_; };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.e(TAG, "onCreate()");

        String config_str = this.getIntent().getExtras().getString(BundleIndexDefine.DATA);
        //Log.e(TAG, "onCreate() config_str= " + config_str);
        this.goBoard_ = new GoGameBoard(this, config_str);
        this.goGameFunc_ = new GoGameActivityFunc(this);

        setContentView(R.layout.activity_go_game);
        this.goView_ = findViewById(R.id.go_game_view);
        this.goView_.requestFocus();
        findViewById(R.id.go_game_b_button).setOnClickListener(this);
        findViewById(R.id.go_game_fb_button).setOnClickListener(this);
        findViewById(R.id.go_game_f_button).setOnClickListener(this);
        findViewById(R.id.go_game_ff_button).setOnClickListener(this);
        findViewById(R.id.go_game_pass_button).setOnClickListener(this);
        findViewById(R.id.go_game_resign_button).setOnClickListener(this);
        findViewById(R.id.go_game_confirm_button).setOnClickListener(this);
        findViewById(R.id.go_game_exit_button).setOnClickListener(this);

        this.registerBroadcastReceiver();
        this.startWatchDog();
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
        this.stopWatchDog();
        this.unregisterBroadcastReceiver();
    }

    @Override
    public void onClick(View view_val) {
        Intent intent;
        switch (view_val.getId()) {
            case R.id.go_game_b_button:
                this.goGameFunc_.do_put_session_data(Encoders.sEncode2("Gb"));
                break;
            case R.id.go_game_fb_button:
                this.goGameFunc_.do_put_session_data(Encoders.sEncode2("GB"));
                break;
            case R.id.go_game_f_button:
                this.goGameFunc_.do_put_session_data(Encoders.sEncode2("Gf"));
                break;
            case R.id.go_game_ff_button:
                this.goGameFunc_.do_put_session_data(Encoders.sEncode2("GF"));
                break;
            case R.id.go_game_pass_button:
                this.goGameFunc_.do_put_session_data(Encoders.sEncode2("GP"));
                break;
            case R.id.go_game_confirm_button:
                finish();
                break;
            case R.id.go_game_resign_button:
                finish();
                break;
            case R.id.go_game_exit_button:
                finish();
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = ((int) event.getX() - this.goView_.viewLeft - this.goView_.gridLen0 + this.goView_.halfGridLen) / this.goView_.gridLen;
        int y = ((int) event.getY() - this.goView_.viewTop  - this.goView_.gridLen0 + this.goView_.halfGridLen) / this.goView_.gridLen;
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
        String move_str = this.goBoard_.encodeMove(x_val - 1, y_val - 1);
        if (move_str == null) {
            return;
        }
        //Log.e(TAG, "processTouchInput move=" + move_str);
        this.goGameFunc_.do_put_session_data(move_str);
    }

    private void registerBroadcastReceiver() {
        if (this.goGameReceiver_ == null) {
            this.goGameReceiver_ = new GoGameReceiver(this);
            IntentFilter filter = new IntentFilter();
            filter.addAction(IntentDefine.GO_GAME_ACTIVITY);
            this.registerReceiver(this.goGameReceiver_, filter);
        }
    }

    private void unregisterBroadcastReceiver() {
        if (this.goGameReceiver_ != null) {
            this.unregisterReceiver(this.goGameReceiver_);
            this.goGameReceiver_ = null;
        }
    }

    private Boolean watchDogOn = true;
    private Timer watchDogTimer = null;
    private TimerTask watchDogTimerTask = null;
    private Handler watchDogHandler = null;

    private void startWatchDog() {
        if (!this.watchDogOn) {
            return;
        }
        if (this.watchDogTimer != null) {
            return;
        }
        watchDogHandler = new Handler();
        watchDogTimer = new Timer();
        watchDogTimerTask = new TimerTask() {
            int count;
            @Override
            public void run() {
                watchDogHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        count++;
                        Log.e(TAG, "watchDog() " + count);
                    }
                });
            }
        };
        watchDogTimer.schedule(watchDogTimerTask, 1000, 1000);
    }

    void stopWatchDog() {
        if (this.watchDogTimer != null) {
            this.watchDogTimer.cancel();
            this.watchDogTimer = null;
            this.watchDogTimerTask = null;
            this.watchDogHandler = null;
        }
    }
}
