/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.main.fabric;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.phwang.go.R;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.define.PrefDefine;
import com.phwang.go.main.main.MainActivity;
import com.phwang.go.sudoku.About;
import com.phwang.go.sudoku.SudokuGame;

import static com.phwang.go.define.PrefDefine.PREF_SERVER_IP_ADDRESS;

public class FabricIpAddressActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "FabricIpAddressActivity";

    private EditText fabricIpAddressEditText_;
    private String fabricIpAddress_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.e(TAG, "onCreate() thread_id=" + Thread.currentThread().getId());

        setContentView(R.layout.activity_fabric_ip_address);
        this.fabricIpAddressEditText_ = findViewById(R.id.fabric_ip_address);
        findViewById(R.id.fabric_confirm_button).setOnClickListener(this);
        findViewById(R.id.fabric_exit_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view_val) {
        Intent intent;
        switch (view_val.getId()) {
            case R.id.fabric_confirm_button:
                this.validateFabricIpAddress();

                intent = new Intent(this, MainActivity.class);
                intent.putExtra(BundleIndexDefine.FABRIC_IP_ADDRESS, this.fabricIpAddress_);
                startActivity(intent);

                finish();
                break;
            case R.id.fabric_exit_button:
                finish();
                break;
        }
    }

    private void validateFabricIpAddress() {
        this.fabricIpAddress_ = this.fabricIpAddressEditText_.getText().toString();
        Log.e(TAG, "validateFabricIpAddress() fabricIpAddress_=" + this.fabricIpAddress_);
        PrefDefine.writeIpAddress(this, this.fabricIpAddress_);
    }
}
