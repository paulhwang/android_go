/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.head;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.phwang.core.go.GoBoardInfo;
import com.phwang.core.go.GoConfigInfo;
import com.phwang.core.protocols.fabric.FabricClients;
import com.phwang.core.protocols.fabric.FabricCommands;
import com.phwang.core.protocols.fabric.FabricInfo;
import com.phwang.core.protocols.fabric.FabricResults;
import com.phwang.core.protocols.fabric.FabricThemeTypes;
import com.phwang.core.utils.encoders.Encoders;
import com.phwang.go.R;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.global.GlobalData;

public class GoHeadActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GoHeadActivity";
    private GoHeadReceiver goHeadReceiver_;
    private String[] optionArray = { "19x19 Black", "19x19 White", "13x13 Black", "13x13 White", "9x9 Black", "9x9 White", "Exit"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate() thread_id=" + Thread.currentThread().getId());
        setContentView(R.layout.activity_go_head);
        ListView list_view = (ListView) findViewById(R.id.head_list_view);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.optionArray);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(onClickListView);       //指定事件 Method
        this.registerBroadcastReceiver();
    }

    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.e(TAG, "onItemClick() position=" + position + " val=" + optionArray[position]);
            switch (position) {
                case 0:
                    setupHeadSession(GoConfigInfo.encodeConfig(19, GoBoardInfo.GO_BLACK_STONE, 0, 0));
                    break;
                case 1:
                    setupHeadSession(GoConfigInfo.encodeConfig(19, GoBoardInfo.GO_WHITE_STONE, 0, 0));
                    break;
                case 2:
                    setupHeadSession(GoConfigInfo.encodeConfig(13, GoBoardInfo.GO_BLACK_STONE, 0, 0));
                    break;
                case 3:
                    setupHeadSession(GoConfigInfo.encodeConfig(13, GoBoardInfo.GO_WHITE_STONE, 0, 0));
                    break;
                case 4:
                    setupHeadSession(GoConfigInfo.encodeConfig(9, GoBoardInfo.GO_BLACK_STONE, 0, 0));
                    break;
                case 5:
                    setupHeadSession(GoConfigInfo.encodeConfig(9, GoBoardInfo.GO_WHITE_STONE, 0, 0));
                    break;
                case 6:
                    finish();
                    break;
            }
        }
    };

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
    }

    private void setupHeadSession(String go_config_data_val) {
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
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_HEAD_ACTIVITY);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.encode());
        intent.setAction(IntentDefine.CLIENT_SERVICE);
        this.sendBroadcast(intent);
    }

    private void registerBroadcastReceiver() {
        if (this.goHeadReceiver_ == null) {
            this.goHeadReceiver_ = new GoHeadReceiver(this);
            IntentFilter filter = new IntentFilter();
            filter.addAction(IntentDefine.GO_HEAD_ACTIVITY);
            this.registerReceiver(this.goHeadReceiver_, filter);
        }
    }

    private void unregisterBroadcastReceiver() {
        if (this.goHeadReceiver_ != null) {
            this.unregisterReceiver(this.goHeadReceiver_);
            this.goHeadReceiver_ = null;
        }
    }
}
