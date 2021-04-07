/*  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.test;

import android.util.Log;

class AndroidTester {
    private static final String TAG = "AndroidTester";

    private AndroidTest androidTest_;

    protected AndroidTester(AndroidTest adnroid_test_val, int tester_index_val) {
        Log.e(TAG, "AndroidTest() start");

        this.androidTest_ = adnroid_test_val;
    }

    protected void startTest() {

    }
}

