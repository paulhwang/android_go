/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.config;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.phwang.core.fabric.FabricCommands;
import com.phwang.core.fabric.FabricResults;
import com.phwang.core.fabric.FabricThemes;
import com.phwang.core.utils.encoders.Encoders;
import com.phwang.core.utils.fabric.FabricData;
import com.phwang.go.R;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.global.GlobalData;
import com.phwang.go.go.game.GoGameBoard;

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

        findViewById(R.id.go_config_black9_button).setOnClickListener(this);
        findViewById(R.id.go_config_black13_button).setOnClickListener(this);
        findViewById(R.id.go_config_black19_button).setOnClickListener(this);

        findViewById(R.id.go_config_white9_button).setOnClickListener(this);
        findViewById(R.id.go_config_white13_button).setOnClickListener(this);
        findViewById(R.id.go_config_white19_button).setOnClickListener(this);

        findViewById(R.id.go_config_peer_button).setOnClickListener(this);
        findViewById(R.id.go_config_join_button).setOnClickListener(this);

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
                this.setupSoloSession(GoGameBoard.encodeConfig(9, 0, 0, GoGameBoard.GO_BOTH_STONE));
                break;
            case R.id.go_config_solo13_button:
                this.setupSoloSession(GoGameBoard.encodeConfig(13, 0, 0, GoGameBoard.GO_BOTH_STONE));
                break;
            case R.id.go_config_solo19_button:
                this.setupSoloSession(GoGameBoard.encodeConfig(19, 0, 0, GoGameBoard.GO_BOTH_STONE));
                break;
            case R.id.go_config_black9_button:
                this.setupHeadSession(GoGameBoard.encodeConfig(9, 0, 0, GoGameBoard.GO_BLACK_STONE));
                break;
            case R.id.go_config_black13_button:
                this.setupHeadSession(GoGameBoard.encodeConfig(13, 0, 0, GoGameBoard.GO_BLACK_STONE));
                break;
            case R.id.go_config_black19_button:
                this.setupHeadSession(GoGameBoard.encodeConfig(19, 0, 0, GoGameBoard.GO_BLACK_STONE));
                break;
            case R.id.go_config_white9_button:
                this.setupHeadSession(GoGameBoard.encodeConfig(9, 0, 0, GoGameBoard.GO_WHITE_STONE));
                break;
            case R.id.go_config_white13_button:
                this.setupHeadSession(GoGameBoard.encodeConfig(13, 0, 0, GoGameBoard.GO_WHITE_STONE));
                break;
            case R.id.go_config_white19_button:
                this.setupHeadSession(GoGameBoard.encodeConfig(19, 0, 0, GoGameBoard.GO_WHITE_STONE));
                break;
            case R.id.go_config_peer_button:
                this.setupPeerSession(GoGameBoard.encodeConfig(13, 0, 0, GoGameBoard.GO_WHITE_STONE));
                break;
            case R.id.go_config_join_button:
                this.setupJoinSession(GoGameBoard.encodeConfig(19, 0, 0, GoGameBoard.GO_WHITE_STONE));
                break;
            case R.id.go_config_play_button:
                this.do_setup_session("phwang", "00000000G111111");
                break;
            case R.id.go_config_exit_button:
                finish();
                break;
        }
    }

    protected void setupSoloSession(String go_config_data_val) {
        FabricData fabric_encode = new FabricData(
                FabricCommands.FABRIC_COMMAND_SOLO_SESSION,
                FabricResults.UNDECIDED,
                FabricData.ANDROID_CLIENT,
                FabricThemes.GO,
                GlobalData.linkIdStr(),
                Encoders.IGNORE
        );
        fabric_encode.addStringList(go_config_data_val);

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_CONFIG_ACTIVITY);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.getEncodedString());
        intent.setAction(IntentDefine.CLIENT_SERVICE);
        this.sendBroadcast(intent);
    }

    protected void setupHeadSession(String go_config_data_val) {
        FabricData fabric_encode = new FabricData(
                FabricCommands.FABRIC_COMMAND_HEAD_SESSION,
                FabricResults.UNDECIDED,
                FabricData.ANDROID_CLIENT,
                FabricThemes.GO,
                GlobalData.linkIdStr(),
                Encoders.IGNORE
        );
        fabric_encode.addStringList(go_config_data_val);

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_CONFIG_ACTIVITY);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.getEncodedString());
        intent.setAction(IntentDefine.CLIENT_SERVICE);
        this.sendBroadcast(intent);
    }

    protected void setupPeerSession(String go_config_data_val) {
        FabricData fabric_encode = new FabricData(
                FabricCommands.FABRIC_COMMAND_PEER_SESSION,
                FabricResults.UNDECIDED,
                FabricData.ANDROID_CLIENT,
                FabricThemes.GO,
                GlobalData.linkIdStr(),
                Encoders.IGNORE
        );
        fabric_encode.addStringList(go_config_data_val);

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_CONFIG_ACTIVITY);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.getEncodedString());
        intent.setAction(IntentDefine.CLIENT_SERVICE);
        this.sendBroadcast(intent);
    }

    protected void setupJoinSession(String go_config_data_val) {
        FabricData fabric_encode = new FabricData(
                FabricCommands.FABRIC_COMMAND_JOIN_SESSION,
                FabricResults.UNDECIDED,
                FabricData.ANDROID_CLIENT,
                FabricThemes.GO,
                GlobalData.linkIdStr(),
                Encoders.IGNORE
        );
        fabric_encode.addStringList(go_config_data_val);

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_CONFIG_ACTIVITY);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.getEncodedString());
        intent.setAction(IntentDefine.CLIENT_SERVICE);
        this.sendBroadcast(intent);
    }

    protected void do_setup_session(String his_name_val, String theme_data_val) {
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
