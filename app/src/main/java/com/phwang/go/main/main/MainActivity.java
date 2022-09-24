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
import com.phwang.core.protocols.fabric.FabricClients;
import com.phwang.core.protocols.fabric.FabricCommands;
import com.phwang.core.protocols.fabric.FabricResults;
import com.phwang.core.protocols.fabric.FabricThemeTypes;
import com.phwang.core.utils.encoders.Encoders;
import com.phwang.core.protocols.fabric.FabricInfo;
import com.phwang.go.R;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.define.PrefDefine;
import com.phwang.go.global.GlobalData;
import com.phwang.go.go.config.GoConfigActivity;
import com.phwang.go.main.setup.SetupActivity;
import com.phwang.go.main.login.LoginActivity;
import com.phwang.go.main.register.RegisterActivity;
import com.phwang.go.sudoku.config.SudokuConfigActivity;
import com.phwang.go.services.ClientService;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "phwang MainActivity";
    private MainActivityFunc mainActivityFunc_;
    private MainReceiver mainReceiver_;
    private static Boolean runGo = true;

    protected MainActivityFunc mainActivityFunc() { return this.mainActivityFunc_; };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate() thread_id=" + Thread.currentThread().getId());

        String fabric_ip_address = getIntent().getStringExtra(BundleIndexDefine.FABRIC_IP_ADDRESS);
        Log.e(TAG, "onCreate() fabric_ip_address=" + fabric_ip_address);

        setContentView(R.layout.activity_main);
        //findViewById(R.id.main_about_button).setOnClickListener(this);
        findViewById(R.id.main_exit_button).setOnClickListener(this);
        findViewById(R.id.main_get_groups_button).setOnClickListener(this);
        findViewById(R.id.main_login_button).setOnClickListener(this);
        findViewById(R.id.main_logout_button).setOnClickListener(this);
        findViewById(R.id.main_play_go_button).setOnClickListener(this);
        findViewById(R.id.main_play_sudoku_button).setOnClickListener(this);
        findViewById(R.id.main_register_button).setOnClickListener(this);
        findViewById(R.id.main_setup_button).setOnClickListener(this);
        //findViewById(R.id.main_test_button).setOnClickListener(this);

        this.mainActivityFunc_ = new MainActivityFunc(this);

        Intent intent = new Intent(this, ClientService.class);
        intent.putExtra(BundleIndexDefine.FABRIC_IP_ADDRESS, fabric_ip_address);
        startService(intent);
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
        Log.e(TAG, "onClick() thread_id=" + Thread.currentThread().getId());
        Intent intent;
        switch (view_val.getId()) {
            case R.id.main_exit_button:
                finish();
                break;
            case R.id.main_get_groups_button:
                this.doGetGroups();
                break;
            case R.id.main_login_button:
                Log.e(TAG, "onClick() login_button thread_id=" + Thread.currentThread().getId());
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
            //case R.id.main_about_button:
            //    intent = new Intent(this, About.class);
            //    startActivity(intent);
            //    break;
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
        /*
        FabricInfo fabric_encode = new FabricInfo(
                FabricCommands.FABRIC_COMMAND_LOGOUT,
                FabricResults.UNDECIDED,
                FabricClients.ANDROID_CLIENT,
                FabricThemeTypes.ALL,
                GlobalData.linkIdStr(),
                Encoders.IGNORE
        );

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.MAIN_ACTIVITY);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.encode());
        intent.setAction(IntentDefine.CLIENT_SERVICE);
        this.sendBroadcast(intent);
        this.registerBroadcastReceiver();

         */
    }

    protected void doGetGroups() {
        /*
        FabricInfo fabric_encode = new FabricInfo(
                FabricCommands.FABRIC_COMMAND_GET_GROUPS,
                FabricResults.UNDECIDED,
                FabricClients.ANDROID_CLIENT,
                FabricThemeTypes.ALL,
                GlobalData.linkIdStr(),
                Encoders.IGNORE
        );

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.MAIN_ACTIVITY);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.encode());
        intent.setAction(IntentDefine.CLIENT_SERVICE);
        this.sendBroadcast(intent);
        this.registerBroadcastReceiver();

         */
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
