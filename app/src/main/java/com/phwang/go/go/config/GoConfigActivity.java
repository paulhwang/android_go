/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.config;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.phwang.go.R;
import com.phwang.go.sudoku.About;
import com.phwang.go.sudoku.SudokuGame;

public class GoConfigActivity extends AppCompatActivity implements View.OnClickListener{
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
                finish();
                break;
        }
        switch (view_val.getId()) {
            case R.id.go_config_exit_button:
                finish();
                break;
        }
    }
}
