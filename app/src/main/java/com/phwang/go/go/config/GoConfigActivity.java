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

import com.phwang.core.go.GoBoardInfo;
import com.phwang.core.go.GoConfigInfo;
import com.phwang.core.protocols.fabric.FabricClients;
import com.phwang.core.protocols.fabric.FabricCommands;
import com.phwang.core.protocols.fabric.FabricResults;
import com.phwang.core.protocols.fabric.FabricThemeTypes;
import com.phwang.core.utils.encoders.Encoders;
import com.phwang.core.protocols.fabric.FabricInfo;
import com.phwang.go.R;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.global.GlobalData;
import com.phwang.go.go.head.GoHeadActivity;
import com.phwang.go.go.join.GoJoinActivity;
import com.phwang.go.go.play.GoPlayActivity;
import com.phwang.go.go.solo.GoSoloActivity;
import com.phwang.go.go.watch.GoWatchActivity;

public class GoConfigActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GoConfigActivity";
    private GoConfigReceiver goConfigReceiver_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.e(TAG, "onCreate() thread_id=" + Thread.currentThread().getId());

        setContentView(R.layout.activity_go_config);

        findViewById(R.id.go_config_play_button).setOnClickListener(this);
        findViewById(R.id.go_config_watch_button).setOnClickListener(this);
        findViewById(R.id.go_config_solo_button).setOnClickListener(this);
        findViewById(R.id.go_config_start_button).setOnClickListener(this);
        findViewById(R.id.go_config_join_button).setOnClickListener(this);
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
        Intent intent;
        switch (view_val.getId()) {
            case R.id.go_config_play_button:
                intent = new Intent(this, GoPlayActivity.class);
                startActivity(intent);
                break;
            case R.id.go_config_watch_button:
                intent = new Intent(this, GoWatchActivity.class);
                startActivity(intent);
                break;
            case R.id.go_config_solo_button:
                intent = new Intent(this, GoSoloActivity.class);
                startActivity(intent);
                break;
            case R.id.go_config_start_button:
                intent = new Intent(this, GoHeadActivity.class);
                startActivity(intent);
                break;
            case R.id.go_config_join_button:
                intent = new Intent(this, GoJoinActivity.class);
                startActivity(intent);
                break;
            case R.id.go_config_exit_button:
                finish();
                break;
        }
    }

    protected void setupSoloSession(String go_config_data_val) {
        /*
        FabricInfo fabric_encode = new FabricInfo(
                FabricCommands.FABRIC_COMMAND_SOLO_SESSION,
                FabricResults.UNDECIDED,
                FabricClients.ANDROID_CLIENT,
                FabricThemeTypes.GO,
                GlobalData.linkIdStr(),
                Encoders.IGNORE
        );
        fabric_encode.addString(go_config_data_val);

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_CONFIG_ACTIVITY);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.encode());
        intent.setAction(IntentDefine.CLIENT_SERVICE);
        this.sendBroadcast(intent);

         */
    }

    protected void setupHeadSession(String go_config_data_val) {
        /*
        FabricInfo fabric_encode = new FabricInfo(
                FabricCommands.FABRIC_COMMAND_HEAD_SESSION,
                FabricResults.UNDECIDED,
                FabricClients.ANDROID_CLIENT,
                FabricThemeTypes.GO,
                GlobalData.linkIdStr(),
                Encoders.IGNORE
        );
        fabric_encode.addString(go_config_data_val);

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_CONFIG_ACTIVITY);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.encode());
        intent.setAction(IntentDefine.CLIENT_SERVICE);
        this.sendBroadcast(intent);

         */
    }

    protected void setupPeerSession(String go_config_data_val) {
        /*
        FabricInfo fabric_encode = new FabricInfo(
                FabricCommands.FABRIC_COMMAND_PEER_SESSION,
                FabricResults.UNDECIDED,
                FabricClients.ANDROID_CLIENT,
                FabricThemeTypes.GO,
                GlobalData.linkIdStr(),
                Encoders.IGNORE
        );
        fabric_encode.addString(go_config_data_val);

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_CONFIG_ACTIVITY);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.encode());
        intent.setAction(IntentDefine.CLIENT_SERVICE);
        this.sendBroadcast(intent);

         */
    }

    protected void setupJoinSession(String go_config_data_val) {
        /*
        FabricInfo fabric_encode = new FabricInfo(
                FabricCommands.FABRIC_COMMAND_JOIN_SESSION,
                FabricResults.UNDECIDED,
                FabricClients.ANDROID_CLIENT,
                FabricThemeTypes.GO,
                GlobalData.linkIdStr(),
                Encoders.IGNORE
        );
        fabric_encode.addString(go_config_data_val);

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_CONFIG_ACTIVITY);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.encode());
        intent.setAction(IntentDefine.CLIENT_SERVICE);
        this.sendBroadcast(intent);

         */
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
