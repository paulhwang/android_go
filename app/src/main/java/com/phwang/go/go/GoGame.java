/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class GoGame extends AppCompatActivity {
    private static final String TAG = "GoGame";

    private GoView goView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        this.goView = new GoView(this);
        setContentView(this.goView);
        this.goView.requestFocus();
    }
}
