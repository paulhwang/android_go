/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.sudoku.config;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.phwang.go.R;
import com.phwang.go.go.game.GoGameActivity;
import com.phwang.go.sudoku.About;
import com.phwang.go.sudoku.SudokuGame;

public class SudokuConfigActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "SudokuConfigActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sudoku_config);
        this.setupView();
    }

    private void setupView() {
        View play_button = findViewById(R.id.play_button);
        play_button.setOnClickListener(this);

        View exit_button = findViewById(R.id.exit_button);
        exit_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view_val) {
        Intent intent;
        switch (view_val.getId()) {
            case R.id.play_button:
                openNewGameDialog();
                break;
            case R.id.exit_button:
                finish();
                break;
        }
    }

    private void openNewGameDialog() {
        new AlertDialog.Builder(this).setTitle(R.string.new_game_title).setItems(R.array.difficulty, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                startSudokuGame(i);
            }
        }).show();
    }

    private void startSudokuGame(int i) {
        Log.d(TAG, "startSudokuGame clicked on " + i);
        Intent intent = new Intent(this, SudokuGame.class);
        intent.putExtra(SudokuGame.KEY_DIFFICULTY, i);
        startActivity(intent);
    }
}
