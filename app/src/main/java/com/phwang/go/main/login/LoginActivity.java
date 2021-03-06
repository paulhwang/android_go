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
import com.phwang.core.protocols.fabric.FabricClients;
import com.phwang.core.protocols.fabric.FabricCommands;
import com.phwang.core.protocols.fabric.FabricResults;
import com.phwang.core.protocols.fabric.FabricThemeTypes;
import com.phwang.core.utils.encoders.Encoders;
import com.phwang.core.protocols.fabric.FabricInfo;
import com.phwang.go.R;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private LoginReceiver loginReceiver_;
    private TextInputLayout userNameLayout_;
    private EditText userNameEditText_;
    private EditText passwordEditText_;
    private String userName_;
    private String password_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.e(TAG, "onCreate() thread_id=" + Thread.currentThread().getId());

        setContentView(R.layout.activity_login);
        this.userNameEditText_ = findViewById(R.id.sign_in_username);
        this.passwordEditText_ = findViewById(R.id.sign_in_password);
        findViewById(R.id.sign_in_confirm_button).setOnClickListener(this);
        findViewById(R.id.sign_in_exit_button).setOnClickListener(this);

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
        Intent intent;
        switch (view_val.getId()) {
            case R.id.sign_in_confirm_button:
                if (this.validateUsername() && this.validatePassword()) {
                    this.do_setup_link();
                }
                finish();
                break;
            case R.id.sign_in_exit_button:
                finish();
                break;
        }
    }

    protected void do_setup_link() {
        if (!validateUsername() || !validatePassword()) {
            return;
        }

        FabricInfo fabric_encode = new FabricInfo(
                FabricCommands.FABRIC_COMMAND_LOGIN,
                FabricResults.UNDECIDED,
                FabricClients.ANDROID_CLIENT,
                FabricThemeTypes.ALL,
                Encoders.IGNORE,
                Encoders.IGNORE
        );
        fabric_encode.addString(this.userName_);
        fabric_encode.addString(this.password_);

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.LOGIN_ACTIVITY);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.encode());
        intent.setAction(IntentDefine.CLIENT_SERVICE);
        this.sendBroadcast(intent);
        this.registerBroadcastReceiver();
    }

    private boolean validateUsername() {
        this.userName_ = this.userNameEditText_.getText().toString();
        //Log.e(TAG, "username=" + this.userName_);

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
        //Log.e(TAG, "password=" + this.password_);

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
            filter.addAction(IntentDefine.LOGIN_ACTIVITY);
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
