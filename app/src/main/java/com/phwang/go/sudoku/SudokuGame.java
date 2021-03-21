/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.sudoku;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SudokuGame extends AppCompatActivity {
    private static final String TAG = "SudokuGame";

    public static final String KEY_DIFFICULTY = "com.phwang.sudoku.difficulty";
    public static final int DIFFICUTLY_EASY = 0;
    public static final int DIFFICUTLY_MEDIUM = 1;
    public static final int DIFFICUTLY_HARD = 2;

    private int puzle[];
    private SudokuView sudokuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        this.sudokuView = new SudokuView(this);
        setContentView(this.sudokuView);
        this.sudokuView.requestFocus();
    }
}
