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
import android.content.Context;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.util.Log;

import com.phwang.go.R;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.CommandDefine;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.main.MainReceiver;
import com.phwang.go.sudoku.About;
import com.phwang.go.sudoku.SudokuGame;
import com.phwang.go.go.GoGame;
import com.phwang.go.bind.BindMain;
import com.phwang.go.bind.BindUClient;
import com.phwang.go.bind.BindService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private MainActivityFunc mainActivityFunc_;

    private Context applicationContext_;
    private static BindMain bindMain_;

    public Context applicationContext() { return this.applicationContext_; };
    public static BindUClient bindUClient() { return bindMain_.bindUClient(); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mainActivityFunc_ = new MainActivityFunc(this);

        setContentView(R.layout.activity_main);
        this.setupView();

        startService(new Intent(this, BindService.class));

        this.applicationContext_ = getApplicationContext();
        this.registerBroadcaseReceiver();
        this.bindMain_ = new BindMain(this.applicationContext());
    }

    private void setupView() {
        View continueButton = findViewById(R.id.continue_button);
        continueButton.setOnClickListener(this);
        View newGameButton = findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(this);
        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.continue_button:
                i = new Intent(this, About.class);
                startActivity(i);
                break;
            case R.id.new_game_button:
                this.openNewGameDialog();
                break;
            case R.id.about_button:
                i = new Intent(this, About.class);
                startActivity(i);
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

    private void do_setup_link(String my_name_val, String password_val) {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.COMMAND, CommandDefine.FABRIC_COMMAND_SETUP_LINK_STR);
        intent.putExtra(BundleIndexDefine.MY_NAME, my_name_val);
        intent.putExtra(BundleIndexDefine.PASSWORD, password_val);
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.applicationContext_.sendBroadcast(intent);

    }

    private static Boolean runGo = true;

    private void startGame(int i) {
        if (i == 0) {
            this.do_setup_link("phwang", "good");
            this.bindUClient().doSetupLink("phwang", "good");
        }

        if (this.runGo)
            this.startGoGame(i);
        else
            this.startSudokuGame(i);
    }
    private void startGoGame(int i) {
        Log.d(TAG, "startGoGame clicked on " + i);
        Intent intent = new Intent(this, GoGame.class);
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

    private MainReceiver mainReceiver_;
    private void registerBroadcaseReceiver() {
        this.mainReceiver_ = new MainReceiver(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(IntentDefine.MAIN_ACTIVITY);
        this.registerReceiver(this.mainReceiver_, filter);
    }
}
