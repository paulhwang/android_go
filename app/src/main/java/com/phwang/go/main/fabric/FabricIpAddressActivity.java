/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.main.fabric;

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

public class FabricIpAddressActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "FabricIpAddressActivity";

    private EditText ServerIpAddressEditText_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fabric_ip_address);
        this.ServerIpAddressEditText_ = findViewById(R.id.fabric_ip_address);
        findViewById(R.id.fabric_confirm_button).setOnClickListener(this);
        findViewById(R.id.fabric_exit_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view_val) {
        Intent intent;
        switch (view_val.getId()) {
            case R.id.fabric_confirm_button:
                this.validateServerIpAddress();
                finish();
                break;
            case R.id.fabric_exit_button:
                finish();
                break;
        }
    }

    private void validateServerIpAddress() {
        String ip_address = this.ServerIpAddressEditText_.getText().toString();
        Log.e(TAG, "validateServerIpAddress() ip_address=" + ip_address);
        PrefDefine.writeIpAddress(this, ip_address);
    }
}
