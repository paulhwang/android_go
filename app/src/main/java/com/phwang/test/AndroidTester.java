/*  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.test;

import android.util.Log;

import com.phwang.core.protocols.fabric.FabricClients;
import com.phwang.core.protocols.fabric.FabricCommands;
import com.phwang.core.protocols.fabric.FabricData;
import com.phwang.core.protocols.fabric.FabricResults;
import com.phwang.core.protocols.fabric.FabricThemeTypes;
import com.phwang.core.utils.encoders.Encoders;

class AndroidTester {
    private static final String TAG = "AndroidTester";

    private AndroidTest androidTest_;
    private String myMame_;
    private String password_ = "Tennis";

    protected AndroidTester(AndroidTest adnroid_test_val, int tester_index_val) {
        this.androidTest_ = adnroid_test_val;

        this.myMame_ = "Android_" + Encoders.iEncodeRaw5(tester_index_val);
        Log.e(TAG, "AndroidTester(init) " + this.myMame_ + " thread=" + Thread.currentThread().getId());
    }

    protected void startTest() {
        FabricData fabric_data = new FabricData(
                FabricCommands.FABRIC_COMMAND_LOGIN,
                FabricResults.UNDECIDED,
                FabricClients.ANDROID_CLIENT,
                FabricThemeTypes.GO,
                Encoders.IGNORE,
                Encoders.IGNORE
        );
    }
}

