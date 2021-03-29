/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.config;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.phwang.go.R;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.CommandDefine;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.go.game.GoGameActivity;
import com.phwang.go.sudoku.About;
import com.phwang.go.sudoku.SudokuGame;

public class GoConfigActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "GoConfigActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_go_config);
        this.setupView();
    }

    private void setupView() {
        View play_button = findViewById(R.id.go_config_play_button);
        play_button.setOnClickListener(this);

        View exit_button = findViewById(R.id.go_config_exit_button);
        exit_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view_val) {
        Intent intent;
        switch (view_val.getId()) {
            case R.id.go_config_play_button:
                this.do_setup_session("phwang", "00000000G111111");

                intent = new Intent(this, GoGameActivity.class);
                startActivity(intent);
                break;
        }
        switch (view_val.getId()) {
            case R.id.go_config_exit_button:
                finish();
                break;
        }
    }

    protected void do_setup_session(String his_name_val, String theme_data_val) {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_CONFIG_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_COMMAND);
        intent.putExtra(BundleIndexDefine.COMMAND, CommandDefine.FABRIC_COMMAND_SETUP_SESSION_STR);
        intent.putExtra(BundleIndexDefine.HIS_NAME, his_name_val);
        intent.putExtra(BundleIndexDefine.THEME_DATA, theme_data_val);
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.sendBroadcast(intent);
    }
}
