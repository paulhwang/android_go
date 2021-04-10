/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.head;

import android.app.ListActivity;
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

public class GoHeadActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GoHeadActivity";
    private GoHeadReceiver goHeadReceiver_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate() thread_id=" + Thread.currentThread().getId());
        setContentView(R.layout.activity_go_head);
        ListView list_view = (ListView) findViewById(R.id.head_list_view);

        StringArray string_array = new StringArray();
        string_array.addString("head");
        string_array.addString("phwang");
        string_array.addString("tennis");
        string_array.addString("paul");
        string_array.addString("David");
        string_array.addString("Tom");
        string_array.addString("Nicole");
        string_array.addString("Misa");
        string_array.addString("Joseph");
        string_array.addString("Steven");
        string_array.addString("Evan");
        string_array.addString("Susan");
        string_array.addString("Betty");
        string_array.addString("Alice");
        string_array.addString("Bill");
        string_array.addString("James");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, string_array.compactStringArray());
        list_view.setAdapter(adapter);
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
