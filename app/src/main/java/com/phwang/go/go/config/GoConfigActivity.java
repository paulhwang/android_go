/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.config;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.phwang.core.utils.Encoders;
import com.phwang.go.R;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.CommandDefine;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.go.game.GoGameActivity;

public class GoConfigActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "GoConfigActivity";
    private GoConfigReceiver goConfigReceiver_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_go_config);
        findViewById(R.id.go_config_solo9_button).setOnClickListener(this);
        findViewById(R.id.go_config_solo13_button).setOnClickListener(this);
        findViewById(R.id.go_config_solo19_button).setOnClickListener(this);
        findViewById(R.id.go_config_play_button).setOnClickListener(this);
        findViewById(R.id.go_config_exit_button).setOnClickListener(this);
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
            case R.id.go_config_solo9_button:
                this.do_solo_session(this.encodeConfig(9, 0, 0));
                break;
            case R.id.go_config_solo13_button:
                this.do_solo_session(this.encodeConfig(13, 0, 0));
                break;
            case R.id.go_config_solo19_button:
                this.do_solo_session(this.encodeConfig(19, 0, 0));
                break;
            case R.id.go_config_play_button:
                this.do_setup_session("phwang", "00000000G111111");
                break;
            case R.id.go_config_exit_button:
                finish();
                break;
        }
    }

    private String encodeConfig(int board_size_val, int handicap_val, int komi_val) {
        StringBuilder buf = new StringBuilder();
        buf.append('G');
        buf.append(Encoders.iEncodeRaw3(19));/////not used
        buf.append(Encoders.iEncodeRaw2(board_size_val));
        buf.append(Encoders.iEncodeRaw2(handicap_val));
        buf.append(Encoders.iEncodeRaw2(komi_val));
        String data = buf.toString();
        return Encoders.sEncode2(data);
    }

    protected void do_solo_session(String go_config_data_val) {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_CONFIG_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_COMMAND);
        intent.putExtra(BundleIndexDefine.COMMAND, CommandDefine.FABRIC_COMMAND_SOLO_SESSION_STR);
        intent.putExtra(BundleIndexDefine.THEME_DATA, go_config_data_val);
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.sendBroadcast(intent);
    }

    protected void do_setup_session(String his_name_val, String theme_data_val) {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_CONFIG_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_COMMAND);
        intent.putExtra(BundleIndexDefine.COMMAND, CommandDefine.FABRIC_COMMAND_SETUP_SESSION_STR);
        intent.putExtra(BundleIndexDefine.HIS_NAME, his_name_val);
        intent.putExtra(BundleIndexDefine.THEME_DATA, theme_data_val);
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.sendBroadcast(intent);
    }

    private void registerBroadcastReceiver() {
        if (this.goConfigReceiver_ == null) {
            this.goConfigReceiver_ = new GoConfigReceiver(this);
            IntentFilter filter = new IntentFilter();
            filter.addAction(IntentDefine.GO_CONFIG_ACTIVITY);
            this.registerReceiver(this.goConfigReceiver_, filter);
        }
    }

    private void unregisterBroadcastReceiver() {
        if (this.goConfigReceiver_ != null) {
            this.unregisterReceiver(this.goConfigReceiver_);
            this.goConfigReceiver_ = null;
        }
    }
}
