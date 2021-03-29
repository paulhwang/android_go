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
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.phwang.go.R;
import com.phwang.go.sudoku.About;
import com.phwang.go.sudoku.SudokuGame;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "SignUpActivity";
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputUsername;
    private TextInputLayout textInputPassword;
    private EditText userNameEditText_;
    private EditText emailEditText_;
    private EditText passwordEditText_;
    private String email_;
    private String userName_;
    private String password_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        this.emailEditText_ = findViewById(R.id.sign_up_email);
        this.userNameEditText_ = findViewById(R.id.sign_up_username);
        this.passwordEditText_ = findViewById(R.id.sign_up_password);

        View sign_up_button = findViewById(R.id.sign_up_button);
        sign_up_button.setOnClickListener(this);
    }

    private boolean validateEmail() {
        this.email_ = this.emailEditText_.getText().toString();
        Log.e(TAG, "email=" + this.email_);

        if (this.emailEditText_.length() == 0){
            //nameLayout.setError("Error in name input");
            return false;
        }
        else{
            //nameLayout.setError(null);
            return true;
        }
    }
    private boolean validateUsername() {
        this.userName_ = this.userNameEditText_.getText().toString();
        Log.e(TAG, "username=" + this.userName_);

        if (this.userNameEditText_.length() == 0){
            //nameLayout.setError("Error in name input");
            return false;
        }
        else if (this.userNameEditText_.length() > 15) {
            //nameLayout.setError.setError("Username too long");
            return false;
        }
        else{
            //nameLayout.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        this.password_ = this.passwordEditText_.getText().toString();
        Log.e(TAG, "password=" + this.password_);

        if (this.passwordEditText_.length() == 0){
            //nameLayout.setError("Error in name input");
            return false;
        }
        else{
            //nameLayout.setError(null);
            return true;
        }
    }

    public void confirmInput() {
        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            return;
        }
    }

    @Override
    public void onClick(View view_val) {
        this.confirmInput();
        Intent intent;
        switch (view_val.getId()) {
            case R.id.sign_up_button:
                finish();
                break;
        }
    }
}
