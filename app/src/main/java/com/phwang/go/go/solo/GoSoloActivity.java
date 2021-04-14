/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.solo;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
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

public class GoSoloActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GoSoloActivity";
    private GoSoloReceiver goSoloReceiver_;
    private String[] boardSizeArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate() thread_id=" + Thread.currentThread().getId());
        setContentView(R.layout.activity_go_solo);
        ListView list_view = (ListView) findViewById(R.id.solo_list_view);
        Resources res = getResources();

        this.boardSizeArray = new String[4];
        this.boardSizeArray[0] = getResources().getString(R.string.solo19_label);
        this.boardSizeArray[1] = getResources().getString(R.string.solo13_label);
        this.boardSizeArray[2] = getResources().getString(R.string.solo9_label);
        this.boardSizeArray[3] = getResources().getString(R.string.exit_label);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.boardSizeArray);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(onClickListView);       //指定事件 Method
        this.registerBroadcastReceiver();
    }

    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.e(TAG, "onItemClick() position=" + position + " val=" + boardSizeArray[position]);
            switch (position) {
                case 0:
                    setupSoloSession(GoConfigInfo.encodeConfig(19, 0, 0, GoBoardInfo.GO_BOTH_STONE));
                    break;
                case 1:
                    setupSoloSession(GoConfigInfo.encodeConfig(13, 0, 0, GoBoardInfo.GO_BOTH_STONE));
                    break;
                case 2:
                    setupSoloSession(GoConfigInfo.encodeConfig(9, 0, 0, GoBoardInfo.GO_BOTH_STONE));
                    break;
                case 3:
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

    private void setupSoloSession(String go_config_data_val) {
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
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_SOLO_ACTIVITY);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.encode());
        intent.setAction(IntentDefine.CLIENT_SERVICE);
        this.sendBroadcast(intent);
    }

    private void registerBroadcastReceiver() {
        if (this.goSoloReceiver_ == null) {
            this.goSoloReceiver_ = new GoSoloReceiver(this);
            IntentFilter filter = new IntentFilter();
            filter.addAction(IntentDefine.GO_SOLO_ACTIVITY);
            this.registerReceiver(this.goSoloReceiver_, filter);
        }
    }

    private void unregisterBroadcastReceiver() {
        if (this.goSoloReceiver_ != null) {
            this.unregisterReceiver(this.goSoloReceiver_);
            this.goSoloReceiver_ = null;
        }
    }
}
