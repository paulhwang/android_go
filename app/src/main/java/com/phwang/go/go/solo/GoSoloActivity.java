/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.solo;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.phwang.core.utils.stringarray.StringArray;
import com.phwang.go.R;
import com.phwang.go.define.IntentDefine;

public class GoSoloActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GoSoloActivity";
    private GoSoloReceiver goSoloReceiver_;
    private String[] optionArray = { "19x19", "13x13", "9x9"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate() thread_id=" + Thread.currentThread().getId());
        setContentView(R.layout.activity_go_solo);
        ListView list_view = (ListView) findViewById(R.id.solo_list_view);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.optionArray);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(onClickListView);       //指定事件 Method
        this.registerBroadcastReceiver();
    }

    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.e(TAG, "onItemClick() position=" + position + " val=" + optionArray[position]);
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

    private void registerBroadcastReceiver() {
        if (this.goSoloReceiver_ == null) {
            this.goSoloReceiver_ = new GoSoloReceiver(this);
            IntentFilter filter = new IntentFilter();
            filter.addAction(IntentDefine.GO_PEER_ACTIVITY);
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
