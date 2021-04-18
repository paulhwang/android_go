/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.main.setup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.phwang.go.R;
import com.phwang.go.define.PrefDefine;
import com.phwang.go.sudoku.About;
import com.phwang.go.sudoku.SudokuGame;

import static com.phwang.go.define.PrefDefine.PREF_SERVER_IP_ADDRESS;

public class SetupActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "SetupActivity";

    private EditText ServerIpAddressEditText_;
    private String serverIpAddress_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setup);
        this.ServerIpAddressEditText_ = findViewById(R.id.setup_server_ip_address);
        findViewById(R.id.setup_admin_button).setOnClickListener(this);
        findViewById(R.id.setup_confirm_button).setOnClickListener(this);
        findViewById(R.id.setup_exit_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view_val) {
        Intent intent;
        switch (view_val.getId()) {
            case R.id.setup_confirm_button:
                this.validateServerIpAddress();
                finish();
                break;
            case R.id.setup_admin_button:
                finish();
                break;
            case R.id.setup_exit_button:
                finish();
                break;
        }
    }

    private void validateServerIpAddress() {
        this.serverIpAddress_ = this.ServerIpAddressEditText_.getText().toString();
        Log.e(TAG, "validateServerIpAddress() ip_addr=" + this.serverIpAddress_);
        getSharedPreferences(PrefDefine.PREF_GO, MODE_PRIVATE).edit().putString(PrefDefine.PREF_SERVER_IP_ADDRESS, this.serverIpAddress_).commit();
    }
}
