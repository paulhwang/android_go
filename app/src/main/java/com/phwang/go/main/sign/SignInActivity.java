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

import com.google.android.material.textfield.TextInputLayout;
import com.phwang.go.R;
import com.phwang.go.sudoku.About;
import com.phwang.go.sudoku.SudokuGame;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SignInActivity";
    private TextInputLayout userNameLayout_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);

        //this.userNameLayout_ = findViewById(R.id.sign_in_username);
        findViewById(R.id.sign_in_confirm_button).setOnClickListener(this);
        findViewById(R.id.sign_in_exit_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view_val) {
        Intent intent;
        switch (view_val.getId()) {
            case R.id.sign_in_confirm_button:
                finish();
                break;
            case R.id.sign_in_exit_button:
                finish();
                break;
        }
    }
}
