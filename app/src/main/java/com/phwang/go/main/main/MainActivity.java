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
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.phwang.go.R;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.go.config.GoConfigActivity;
import com.phwang.go.main.setup.SetupActivity;
import com.phwang.go.main.sign_in.SignInActivity;
import com.phwang.go.main.sign_up.SignUpActivity;
import com.phwang.go.sudoku.config.SudokuConfigActivity;
import com.phwang.go.sudoku.About;
import com.phwang.go.services.BindService;

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
        findViewById(R.id.play_go_button).setOnClickListener(this);
        findViewById(R.id.play_sudoku_button).setOnClickListener(this);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.sign_up_button).setOnClickListener(this);
        findViewById(R.id.setup_button).setOnClickListener(this);
        findViewById(R.id.about_button).setOnClickListener(this);
        findViewById(R.id.test_button).setOnClickListener(this);
        findViewById(R.id.exit_button).setOnClickListener(this);

        this.mainActivityFunc_ = new MainActivityFunc(this);
        startService(new Intent(this, BindService.class));
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
        Intent intent;
        switch (view_val.getId()) {
            case R.id.play_go_button:
                intent = new Intent(this, GoConfigActivity.class);
                startActivity(intent);
                break;
            case R.id.play_sudoku_button:
                intent = new Intent(this, SudokuConfigActivity.class);
                startActivity(intent);
                break;
            case R.id.sign_in_button:
                intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                break;
            case R.id.sign_up_button:
                intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.setup_button:
                intent = new Intent(this, SetupActivity.class);
                startActivity(intent);
                break;
            case R.id.about_button:
                intent = new Intent(this, About.class);
                startActivity(intent);
                break;
            case R.id.test_button:
                intent = new Intent(this, SetupActivity.class);
                startActivity(intent);
                break;
            case R.id.exit_button:
                finish();
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
}
