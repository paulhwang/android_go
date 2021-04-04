/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.main.register;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.phwang.core.fabric.FabricClients;
import com.phwang.core.fabric.FabricCommands;
import com.phwang.core.fabric.FabricResults;
import com.phwang.core.fabric.FabricThemes;
import com.phwang.core.utils.encoders.Encoders;
import com.phwang.core.utils.fabric.FabricEncode;
import com.phwang.go.R;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "RegisterActivity";
    private RegisterReceiver registerReceiver_;
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

        setContentView(R.layout.activity_register);

        this.emailEditText_ = findViewById(R.id.sign_up_email);
        this.userNameEditText_ = findViewById(R.id.sign_up_username);
        this.passwordEditText_ = findViewById(R.id.sign_up_password);
        findViewById(R.id.sign_up_confirm_button).setOnClickListener(this);
        findViewById(R.id.sign_up_exit_button).setOnClickListener(this);

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

    @Override
    public void onClick(View view_val) {
        switch (view_val.getId()) {
            case R.id.sign_up_confirm_button:
                this.doRegister();
                finish();
                break;
        }
        switch (view_val.getId()) {
            case R.id.sign_up_exit_button:
                finish();
                break;
        }
    }

    private void doRegister() {
        if (!validateEmail() || !validateUsername() || !validatePassword()) {
            return;
        }

        FabricEncode fabric_encode = new FabricEncode(
                FabricCommands.FABRIC_COMMAND_REGISTER,
                FabricResults.UNDECIDED,
                FabricClients.ANDROID,
                FabricThemes.IGNORE,
                Encoders.IGNORE,
                Encoders.IGNORE,
                3
        );
        fabric_encode.setStringList(0, this.userName_);
        fabric_encode.setStringList(1, this.email_);
        fabric_encode.setStringList(2, this.password_);

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.REGISTER_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND, FabricCommands.FABRIC_COMMAND_REGISTER_STR);
        intent.putExtra(BundleIndexDefine.MY_NAME, this.userName_);
        intent.putExtra(BundleIndexDefine.EMAIL, this.email_);
        intent.putExtra(BundleIndexDefine.PASSWORD, this.password_);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.getEncodedString());
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

    private void registerBroadcastReceiver() {
        if (this.registerReceiver_ == null) {
            this.registerReceiver_ = new RegisterReceiver(this);
            IntentFilter filter = new IntentFilter();
            filter.addAction(IntentDefine.REGISTER_ACTIVITY);
            this.registerReceiver(this.registerReceiver_, filter);
        }
    }

    private void unregisterBroadcastReceiver() {
        if (this.registerReceiver_ != null) {
            this.unregisterReceiver(this.registerReceiver_);
            this.registerReceiver_ = null;
        }
    }
}
