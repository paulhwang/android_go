package com.phwang.go.go.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.phwang.core.utils.encoders.Encoders;
import com.phwang.core.utils.fabric.FabricDecode;
import com.phwang.core.utils.watchdog.WatchDog;
import com.phwang.core.utils.watchdog.WatchDogInt;
import com.phwang.go.R;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;

public class GoGameActivity extends AppCompatActivity implements View.OnClickListener, WatchDogInt {
    private static final String TAG = "GoGameActivity";
    private GoGameUFunc goGameUFunc_;
    private GoGameDFunc goGameDFunc_;
    private GoGameBoard goBoard_;
    private GoGameView goView_;
    private GoGameReceiver goGameReceiver_;
    private String linkIdStr_;
    private String sessionIdStr_;
    private Boolean isDead_ = false;
    private WatchDog watchDog_;

    protected GoGameView goView() { return this.goView_; };
    protected GoGameBoard goBoard() { return this.goBoard_; };
    protected GoGameUFunc goGameUFunc() { return this.goGameUFunc_; };
    protected GoGameDFunc goGameDFunc() { return this.goGameDFunc_; };
    protected String linkIdStr() { return this.linkIdStr_; }
    protected String sessionIdStr() { return this.sessionIdStr_; }
    protected Boolean isDead() { return this.isDead_; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.e(TAG, "onCreate()");

        String data_package_str = this.getIntent().getExtras().getString(BundleIndexDefine.DATA_PACKAGE);
        Log.e(TAG, "onCreate() data_package=" + data_package_str);

        FabricDecode fabric_decode = new FabricDecode(data_package_str);
        this.linkIdStr_ = fabric_decode.linkIdStr();
        this.sessionIdStr_ = fabric_decode.sessionIdStr();
        String config_str = fabric_decode.stringList(0);
        Log.e(TAG, "onCreate() linkIdStr_=" + linkIdStr_);
        Log.e(TAG, "onCreate() sessionIdStr_=" + sessionIdStr_);
        Log.e(TAG, "onCreate() config_str=" + config_str);
        config_str = Encoders.sDecode6(config_str);
        Log.e(TAG, "onCreate() config_str=" + config_str);

        this.goBoard_ = new GoGameBoard(this, config_str);
        this.goGameUFunc_ = new GoGameUFunc(this);
        this.goGameDFunc_ = new GoGameDFunc(this);

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
                break;
            case R.id.go_game_fb_button:
                this.goGameUFunc_.sendPutSessionDataCommand("GB");
                break;
            case R.id.go_game_f_button:
                this.goGameUFunc_.sendPutSessionDataCommand("Gf");
                break;
            case R.id.go_game_ff_button:
                this.goGameUFunc_.sendPutSessionDataCommand("GF");
                break;
            case R.id.go_game_pass_button:
                this.goGameUFunc_.sendPutSessionDataCommand("GP");
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
