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
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.CommandDefine;
import com.phwang.go.define.IntentDefine;
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

        findViewById(R.id.sign_up_confirm_button).setOnClickListener(this);
        findViewById(R.id.sign_up_exit_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view_val) {
        switch (view_val.getId()) {
            case R.id.sign_up_confirm_button:
                //this.registerAccount();
                finish();
                break;
        }
        switch (view_val.getId()) {
            case R.id.sign_up_exit_button:
                finish();
                break;
        }
    }

    private void registerAccount() {
        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.SIGN_UP_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_COMMAND);
        intent.putExtra(BundleIndexDefine.COMMAND, CommandDefine.FABRIC_COMMAND_REGISTER_STR);
        intent.putExtra(BundleIndexDefine.MY_NAME, this.userName_);
        intent.putExtra(BundleIndexDefine.EMAIL, this.email_);
        intent.putExtra(BundleIndexDefine.PASSWORD, this.password_);
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.sendBroadcast(intent);
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
}
