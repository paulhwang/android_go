/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.peer;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.phwang.core.utils.stringarray.StringArray;
import com.phwang.go.R;
import com.phwang.go.define.IntentDefine;

import java.util.ArrayList;

public class GoPeerActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GoPeerActivity";
    private GoPeerReceiver goPeerReceiver_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate() thread_id=" + Thread.currentThread().getId());
        setContentView(R.layout.activity_go_peer);
        ListView list_view = (ListView) findViewById(R.id.listview);

        StringArray string_array = new StringArray();
        string_array.addString("phwang");
        string_array.addString("tennis");
        string_array.addString("paul");
        string_array.addString("David");
        string_array.addString("Tom");
        String[] str = {"aaa", "bbb", "ccc"};
        Log.e(TAG, "onCreate(1)");

        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, str);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, string_array.compactStringArray());
        Log.e(TAG, "onCreate(2)");
        list_view.setAdapter(adapter);
        Log.e(TAG, "onCreate(3)");
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
    }

    private void registerBroadcastReceiver() {
        if (this.goPeerReceiver_ == null) {
            this.goPeerReceiver_ = new GoPeerReceiver(this);
            IntentFilter filter = new IntentFilter();
            filter.addAction(IntentDefine.GO_PEER_ACTIVITY);
            this.registerReceiver(this.goPeerReceiver_, filter);
        }
    }

    private void unregisterBroadcastReceiver() {
        if (this.goPeerReceiver_ != null) {
            this.unregisterReceiver(this.goPeerReceiver_);
            this.goPeerReceiver_ = null;
        }
    }
}
