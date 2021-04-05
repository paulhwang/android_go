/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.game;

import android.content.Intent;

import com.phwang.core.fabric.FabricCommands;
import com.phwang.core.fabric.FabricResults;
import com.phwang.core.fabric.FabricThemes;
import com.phwang.core.utils.fabric.FabricData;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;

public class GoGameUFunc {
    private static final String TAG = "GoGameUFunc";
    private GoGameActivity goGameActivity_;

    private String linkIdStr() { return this.goGameActivity_.linkIdStr(); }
    private String sessionIdStr() { return this.goGameActivity_.sessionIdStr(); }

    protected GoGameUFunc(GoGameActivity go_game_activity_val) {
        this.goGameActivity_ = go_game_activity_val;
    }

    protected void sendDeleteSessionCommand() {
        FabricData fabric_encode = new FabricData(
                FabricCommands.FABRIC_COMMAND_DELETE_SESSION,
                FabricResults.UNDECIDED,
                FabricData.ANDROID_CLIENT,
                FabricThemes.GO,
                this.linkIdStr(),
                this.sessionIdStr(),
                0
        );

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_GAME_ACTIVITY);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.getEncodedString());
        intent.setAction(IntentDefine.CLIENT_SERVICE);
        this.goGameActivity_.sendBroadcast(intent);
    }

    protected void sendPutSessionDataCommand(String move_data_val) {
        FabricData fabric_encode = new FabricData(
                FabricCommands.FABRIC_COMMAND_PUT_SESSION_DATA,
                FabricResults.UNDECIDED,
                FabricData.ANDROID_CLIENT,
                FabricThemes.GO,
                this.linkIdStr(),
                this.sessionIdStr(),
                1
        );
        fabric_encode.setStringList(0, move_data_val);

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_GAME_ACTIVITY);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.getEncodedString());
        intent.setAction(IntentDefine.CLIENT_SERVICE);
        this.goGameActivity_.sendBroadcast(intent);
    }

    protected void sendGetSessionDataCommand() {
        FabricData fabric_encode = new FabricData(
                FabricCommands.FABRIC_COMMAND_GET_SESSION_DATA,
                FabricResults.UNDECIDED,
                FabricData.ANDROID_CLIENT,
                FabricThemes.GO,
                this.linkIdStr(),
                this.sessionIdStr(),
                0
        );

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_GAME_ACTIVITY);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.getEncodedString());
        intent.setAction(IntentDefine.CLIENT_SERVICE);
        this.goGameActivity_.sendBroadcast(intent);
    }
}
