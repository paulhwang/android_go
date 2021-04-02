package com.phwang.go.go.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.phwang.core.utils.encoders.Encoders;
import com.phwang.core.utils.watchdog.WatchDog;
import com.phwang.core.utils.watchdog.WatchDogInt;
import com.phwang.go.R;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;

public class GoGameActivity extends AppCompatActivity implements View.OnClickListener, WatchDogInt {
    private static final String TAG = "GoGameActivity";
    private GoGameActivityFunc goGameFunc_;
    private GoGameBoard goBoard_;
    private GoGameView goView_;
    private GoGameReceiver goGameReceiver_;
    private String linkIdStr_;
    private String sessionIdStr_;

    protected GoGameView goView() { return this.goView_; };
    protected GoGameBoard goBoard() { return this.goBoard_; };
    protected GoGameActivityFunc goGameFunc() { return this.goGameFunc_; };
    protected String linkIdStr() { return this.linkIdStr_; }
    protected String sessionIdStr() { return this.sessionIdStr_; }
    private WatchDog watchDog_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.e(TAG, "onCreate()");

        String data_str = this.getIntent().getExtras().getString(BundleIndexDefine.DATA);
        //Log.e(TAG, "onCreate() data_str= " + data_str);

        String rest_str = Encoders.sDecode2(data_str);
        this.linkIdStr_ = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

        this.sessionIdStr_ = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

        String config_str = Encoders.sSubstring2(rest_str);
        //rest_str = Encoders.sSubstring2_(rest_str);

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
        this.goGameFunc_.do_delete_session();
        //Log.e(TAG, "onDestroy()");
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

    public void watchDogFunc() {
    }
}
