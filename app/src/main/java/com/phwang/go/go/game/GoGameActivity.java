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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.phwang.core.go.GoBoardInfo;
import com.phwang.core.go.GoConfigInfo;
import com.phwang.core.go.GoMoveInfo;
import com.phwang.core.protocols.fabric.FabricInfo;
import com.phwang.core.utils.watchdog.WatchDog;
import com.phwang.core.utils.watchdog.WatchDogInt;
import com.phwang.go.R;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;

public class GoGameActivity extends AppCompatActivity implements View.OnClickListener, WatchDogInt {
    private static final String TAG = "GoGameActivity";
    private GoGameUFunc goGameUFunc_;
    private GoGameDFunc goGameDFunc_;
    private GoBoardInfo goBoardInfo_;
    private GoGameView goGameView_;
    private GoConfigInfo goConfigInfo_;
    private GoGameReceiver goGameReceiver_;
    private String linkIdStr_;
    private String sessionIdStr_;
    private Boolean isDead_ = false;
    private WatchDog watchDog_;
    private int touchX_;
    private int touchY_;
    private Boolean waitingForConfirm_ = false;
    private Boolean confirmNeeded = false;

    protected GoGameView goGameView() { return this.goGameView_; };
    protected GoBoardInfo goBoardInfo() { return this.goBoardInfo_; };
    protected GoConfigInfo goConfigInfo() { return this.goConfigInfo_; };
    protected GoGameUFunc goGameUFunc() { return this.goGameUFunc_; };
    protected GoGameDFunc goGameDFunc() { return this.goGameDFunc_; };
    protected String linkIdStr() { return this.linkIdStr_; }
    protected String sessionIdStr() { return this.sessionIdStr_; }
    protected int myColor() { return this.goConfigInfo_.myColor(); };
    protected int boardSize() { return this.goConfigInfo_.boardSize(); }
    protected Boolean isDead() { return this.isDead_; }
    protected int touchX() { return this.touchX_; }
    protected int touchY() { return this.touchY_; }
    protected int moveX() { return this.touchX() - 1; }
    protected int moveY() { return this.touchY() - 1; }
    protected Boolean waitingForConfirm() { return this.waitingForConfirm_; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.e(TAG, "onCreate() thread_id=" + Thread.currentThread().getId());

        String fabric_info_str = this.getIntent().getExtras().getString(BundleIndexDefine.FABRIC_DATA);
        //Log.e(TAG, "onCreate() data_package=" + fabric_data_str);

        FabricInfo fabric_info = new FabricInfo(fabric_info_str);
        this.linkIdStr_ = fabric_info.linkIdStr();
        this.sessionIdStr_ = fabric_info.sessionIdStr();
        //Log.e(TAG, "onCreate() linkIdStr_=" + linkIdStr_);
        //Log.e(TAG, "onCreate() sessionIdStr_=" + sessionIdStr_);

        String go_config_info_str = fabric_info.stringArrayElement(0);
        //Log.e(TAG, "onCreate() config_str=" + config_str);

        this.resetTouchXY();
        this.goConfigInfo_ = new GoConfigInfo(go_config_info_str);
        this.goBoardInfo_ = new GoBoardInfo();
        this.goGameUFunc_ = new GoGameUFunc(this);
        this.goGameDFunc_ = new GoGameDFunc(this);

        setContentView(R.layout.activity_go_game);
        this.goGameView_ = findViewById(R.id.go_game_view);
        this.goGameView_.requestFocus();
        findViewById(R.id.go_game_b_button).setOnClickListener(this);
        findViewById(R.id.go_game_fb_button).setOnClickListener(this);
        findViewById(R.id.go_game_f_button).setOnClickListener(this);
        findViewById(R.id.go_game_ff_button).setOnClickListener(this);
        findViewById(R.id.go_game_pass_button).setOnClickListener(this);
        findViewById(R.id.go_game_resign_button).setOnClickListener(this);
        findViewById(R.id.go_game_confirm_button).setOnClickListener(this);
        findViewById(R.id.go_game_exit_button).setOnClickListener(this);

        this.registerBroadcastReceiver();
        this.watchDog_ = new WatchDog(this, 1000, 1000);
        this.watchDog_.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Log.e(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.e(TAG, "onResume()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.watchDog_.cancel();
        this.goGameUFunc_.sendDeleteSessionCommand();
        this.unregisterBroadcastReceiver();
        this.isDead_ = true;
        //Log.e(TAG, "onDestroy()");
    }

    @Override
    public void onClick(View view_val) {
        Intent intent;
        switch (view_val.getId()) {
            case R.id.go_game_b_button:
                this.goGameUFunc_.sendPutSessionDataCommand("Gb");
                this.resetTouchXY();
                break;
            case R.id.go_game_fb_button:
                this.goGameUFunc_.sendPutSessionDataCommand("GB");
                this.resetTouchXY();
                break;
            case R.id.go_game_f_button:
                this.goGameUFunc_.sendPutSessionDataCommand("Gf");
                this.resetTouchXY();
                break;
            case R.id.go_game_ff_button:
                this.goGameUFunc_.sendPutSessionDataCommand("GF");
                this.resetTouchXY();
                break;
            case R.id.go_game_pass_button:
                this.goGameUFunc_.sendPutSessionDataCommand("GP");
                this.resetTouchXY();
                break;
            case R.id.go_game_confirm_button:
                this.processTouchInput();
                this.resetTouchXY();
                break;
            case R.id.go_game_resign_button:
                finish();
                break;
            case R.id.go_game_exit_button:
                finish();
                break;
        }
    }

    private void resetTouchXY() {
        this.waitingForConfirm_ = false;
        this.touchX_ = GoBoardInfo.GO_INVALID_COORDINATE;
        this.touchY_ = GoBoardInfo.GO_INVALID_COORDINATE;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent() myColor=" + this.myColor() + " nextColor=" + this.goBoardInfo_.nextColor());
        if ((this.myColor() != GoBoardInfo.GO_BOTH_STONE) &&
            (this.myColor() != this.goBoardInfo_.nextColor())) {
            return super.onTouchEvent(event);
        }
        this.touchX_ = ((int) event.getX() - this.goGameView_.viewLeft - this.goGameView_.gridLen0 + this.goGameView_.halfGridLen) / this.goGameView_.gridLen;
        this.touchY_ = ((int) event.getY() - this.goGameView_.viewTop  - this.goGameView_.gridLen0 + this.goGameView_.halfGridLen) / this.goGameView_.gridLen;
        //Log.e(TAG, "onTouchEvent: x=" + this.x_ + " y=" + this.y_);
        switch( event.getAction() ) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:
                if (!this.confirmNeeded) {
                    this.processTouchInput();
                    this.resetTouchXY();
                }
                break;
        }
        this.waitingForConfirm_ = true;
        return super.onTouchEvent(event);
    }

    public void processTouchInput() {
        if (!this.goConfigInfo().isValidCoordinates(this.moveX(), this.moveY())) {
            Log.e(TAG, "encodeMove() bad coordinate: " + this.moveX() + " " + this.moveY());
            return;
        }

        if (this.goBoardInfo_.boardArray(this.moveX(), this.moveY()) != 0) {
            return;
        }


        String move_str = GoMoveInfo.encodeMove(this.moveX(), this.moveY(), this.goBoardInfo_.nextColor(), this.goBoardInfo_.totalMoves() + 1);
        //Log.e(TAG, "processTouchInput move=" + move_str);

        this.goGameUFunc_.sendPutSessionDataCommand(move_str);
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

    public void watchDogFunc() {
    }
}
