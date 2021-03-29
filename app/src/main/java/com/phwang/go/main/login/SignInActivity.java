/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.main.login;

import android.content.Intent;
import android.content.IntentFilter;
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
import com.phwang.go.main.login.LoginReceiver;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SignInActivity";
    private LoginReceiver loginReceiver_;
    private TextInputLayout userNameLayout_;
    private EditText userNameEditText_;
    private EditText passwordEditText_;
    private String userName_;
    private String password_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);

        //this.userNameLayout_ = findViewById(R.id.sign_in_username);
        findViewById(R.id.sign_in_confirm_button).setOnClickListener(this);
        findViewById(R.id.sign_in_exit_button).setOnClickListener(this);

        this.registerBroadcastReceiver();
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

    protected void do_setup_link(String my_name_val, String password_val) {
        if (!validateUsername() || !validatePassword()) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.SIGN_IN_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_COMMAND);
        intent.putExtra(BundleIndexDefine.COMMAND, CommandDefine.FABRIC_COMMAND_SETUP_LINK_STR);
        intent.putExtra(BundleIndexDefine.MY_NAME, my_name_val);
        intent.putExtra(BundleIndexDefine.PASSWORD, password_val);
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.sendBroadcast(intent);
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

    private void registerBroadcastReceiver() {
        if (this.loginReceiver_ == null) {
            this.loginReceiver_ = new LoginReceiver(this);
            IntentFilter filter = new IntentFilter();
            filter.addAction(IntentDefine.SIGN_IN_ACTIVITY);
            this.registerReceiver(this.loginReceiver_, filter);
        }
    }

    private void unregisterBroadcastReceiver() {
        if (this.loginReceiver_ != null) {
            this.unregisterReceiver(this.loginReceiver_);
            this.loginReceiver_ = null;
        }
    }
}
