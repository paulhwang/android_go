/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.main;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.util.Log;

import com.phwang.go.R;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.go.config.GoConfigActivity;
import com.phwang.go.main.sign.SignInActivity;
import com.phwang.go.main.sign.SignUpActivity;
import com.phwang.go.sudoku.config.SudokuConfigActivity;
import com.phwang.go.go.game.GoGameActivity;
import com.phwang.go.sudoku.About;
import com.phwang.go.sudoku.SudokuGame;
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
        this.mainActivityFunc_ = new MainActivityFunc(this);

        setContentView(R.layout.activity_main);
        this.setupView();

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

    private void setupView() {
        View play_go_button = findViewById(R.id.play_go_button);
        play_go_button.setOnClickListener(this);

        View play_sudoku_button = findViewById(R.id.play_sudoku_button);
        play_sudoku_button.setOnClickListener(this);

        View sign_in_button = findViewById(R.id.sign_in_button);
        sign_in_button.setOnClickListener(this);

        View sign_up_button = findViewById(R.id.sign_up_button);
        sign_up_button.setOnClickListener(this);

        View about_button = findViewById(R.id.about_button);
        about_button.setOnClickListener(this);

        View test_button = findViewById(R.id.test_button);
        test_button.setOnClickListener(this);

        View exit_button = findViewById(R.id.exit_button);
        exit_button.setOnClickListener(this);
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
                intent = new Intent(this, SudokuGame.class);
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
            case R.id.about_button:
                intent = new Intent(this, About.class);
                startActivity(intent);
                break;
            case R.id.test_button:
                intent = new Intent(this, SudokuConfigActivity.class);
                startActivity(intent);
                break;
            case R.id.exit_button:
                finish();
                break;
        }
    }

    private void openNewGameDialog() {
        new AlertDialog.Builder(this).setTitle(R.string.new_game_title).setItems(R.array.difficulty, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                startGame(i);
            }
        }).show();
    }

    private void startGame(int i) {
        if (i == 0) {
            this.mainActivityFunc_.do_setup_link("phwang", "good");
        }

        if (this.runGo)
            this.startGoGame(i);
        else
            this.startSudokuGame(i);
    }

    private void startGoGame(int i) {
        Log.d(TAG, "startGoGame clicked on " + i);
        Intent intent = new Intent(this, GoGameActivity.class);
        startActivity(intent);
    }

    private void startSudokuGame(int i) {
        Log.d(TAG, "startSudokuGame clicked on " + i);
        Intent intent = new Intent(this, SudokuGame.class);
        intent.putExtra(SudokuGame.KEY_DIFFICULTY, i);
        startActivity(intent);
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
