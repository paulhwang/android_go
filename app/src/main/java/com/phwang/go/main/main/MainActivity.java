/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.main.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.phwang.core.fabric.FabricClients;
import com.phwang.core.fabric.FabricCommands;
import com.phwang.core.fabric.FabricResults;
import com.phwang.core.fabric.FabricThemes;
import com.phwang.core.utils.encoders.Encoders;
import com.phwang.core.utils.fabric.FabricEncode;
import com.phwang.go.R;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.global.GlobalData;
import com.phwang.go.go.config.GoConfigActivity;
import com.phwang.go.main.setup.SetupActivity;
import com.phwang.go.main.login.LoginActivity;
import com.phwang.go.main.register.RegisterActivity;
import com.phwang.go.sudoku.config.SudokuConfigActivity;
import com.phwang.go.sudoku.About;
import com.phwang.go.services.BindService;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private MainActivityFunc mainActivityFunc_;
    private MainReceiver mainReceiver_;
    private static Boolean runGo = true;

    protected MainActivityFunc mainActivityFunc() { return this.mainActivityFunc_; };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        findViewById(R.id.main_about_button).setOnClickListener(this);
        findViewById(R.id.main_exit_button).setOnClickListener(this);
        findViewById(R.id.main_get_groups_button).setOnClickListener(this);
        findViewById(R.id.main_login_button).setOnClickListener(this);
        findViewById(R.id.main_logout_button).setOnClickListener(this);
        findViewById(R.id.main_play_go_button).setOnClickListener(this);
        findViewById(R.id.main_play_sudoku_button).setOnClickListener(this);
        findViewById(R.id.main_register_button).setOnClickListener(this);
        findViewById(R.id.main_setup_button).setOnClickListener(this);
        findViewById(R.id.main_test_button).setOnClickListener(this);

        this.mainActivityFunc_ = new MainActivityFunc(this);
        startService(new Intent(this, BindService.class));
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
            case R.id.main_about_button:
                intent = new Intent(this, About.class);
                startActivity(intent);
                break;
            case R.id.main_exit_button:
                finish();
                break;
            case R.id.main_get_groups_button:
                this.doGetGroups();
                break;
            case R.id.main_login_button:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.main_logout_button:
                this.doLogout();
                break;
            case R.id.main_play_go_button:
                intent = new Intent(this, GoConfigActivity.class);
                startActivity(intent);
                break;
            case R.id.main_play_sudoku_button:
                intent = new Intent(this, SudokuConfigActivity.class);
                startActivity(intent);
                break;
            case R.id.main_register_button:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.main_setup_button:
                intent = new Intent(this, SetupActivity.class);
                startActivity(intent);
                break;
            case R.id.main_test_button:
                intent = new Intent(this, SetupActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu_val) {
        super.onCreateOptionsMenu(menu_val);
        MenuInflater inflater = getMenuInflater();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item_val) {
        switch (item_val.getItemId()) {
            case R.id.settings:
                //StartActivity(new Intent(this, Prefs.class));
                return true;
        }
        return false;
    }

    protected void doLogout() {
        FabricEncode fabric_encode = new FabricEncode(
                FabricCommands.FABRIC_COMMAND_LOGOUT,
                FabricResults.UNDECIDED,
                FabricClients.ANDROID,
                FabricThemes.IGNORE,
                GlobalData.linkIdStr(),
                Encoders.IGNORE,
                0
        );

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.MAIN_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND, FabricCommands.FABRIC_COMMAND_LOGOUT_STR);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.getEncodedString());
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.sendBroadcast(intent);
        this.registerBroadcastReceiver();
    }

    protected void doGetGroups() {

        FabricEncode fabric_encode = new FabricEncode(
                FabricCommands.FABRIC_COMMAND_GET_GROUPS,
                FabricResults.UNDECIDED,
                FabricClients.ANDROID,
                FabricThemes.IGNORE,
                GlobalData.linkIdStr(),
                Encoders.IGNORE,
                0
        );

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.MAIN_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND, FabricCommands.FABRIC_COMMAND_GET_GROUPS_STR);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.getEncodedString());
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.sendBroadcast(intent);
        this.registerBroadcastReceiver();
    }

    private void registerBroadcastReceiver() {
        if (this.mainReceiver_ == null) {
            this.mainReceiver_ = new MainReceiver(this);
            IntentFilter filter = new IntentFilter();
            filter.addAction(IntentDefine.MAIN_ACTIVITY);
            this.registerReceiver(this.mainReceiver_, filter);
        }
    }

    private void unregisterBroadcastReceiver() {
        if (this.mainReceiver_ != null) {
            this.unregisterReceiver(this.mainReceiver_);
            this.mainReceiver_ = null;
        }
    }

    private Boolean watchDogOn = false;
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
