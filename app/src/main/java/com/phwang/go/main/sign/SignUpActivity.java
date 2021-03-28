/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.main.sign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.phwang.go.R;
import com.phwang.go.sudoku.About;
import com.phwang.go.sudoku.SudokuGame;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);
        this.setupView();
    }

    private void setupView() {
        View sign_up_button = findViewById(R.id.sign_up_button);
        sign_up_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view_val) {
        Intent intent;
        switch (view_val.getId()) {
            case R.id.sign_up_button:
                finish();
                break;
        }
    }
}
