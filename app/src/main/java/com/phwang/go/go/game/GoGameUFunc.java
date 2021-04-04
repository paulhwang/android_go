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
import com.phwang.core.utils.fabric.FabricEncode;
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
        FabricEncode fabric_encode = new FabricEncode(
                FabricCommands.FABRIC_COMMAND_DELETE_SESSION,
                FabricResults.IGNORE,
                FabricThemes.GO,
                this.linkIdStr(),
                this.sessionIdStr(),
                0
        );

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_GAME_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND, FabricCommands.FABRIC_COMMAND_DELETE_SESSION_STR);
        intent.putExtra(BundleIndexDefine.LINK_ID, this.linkIdStr());
        intent.putExtra(BundleIndexDefine.SESSION_ID, this.sessionIdStr());
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.getEncodedString());
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.goGameActivity_.sendBroadcast(intent);
    }

    protected void sendPutSessionDataCommand(String move_data_val) {
        FabricEncode fabric_encode = new FabricEncode(
                FabricCommands.FABRIC_COMMAND_PUT_SESSION_DATA,
                FabricResults.IGNORE,
                FabricThemes.GO,
                this.linkIdStr(),
                this.sessionIdStr(),
                1
        );
        fabric_encode.setStringList(0, move_data_val);

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_GAME_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND, FabricCommands.FABRIC_COMMAND_PUT_SESSION_DATA_STR);
        intent.putExtra(BundleIndexDefine.LINK_ID, this.linkIdStr());
        intent.putExtra(BundleIndexDefine.SESSION_ID, this.sessionIdStr());
        intent.putExtra(BundleIndexDefine.MOVE_DATA, move_data_val);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.getEncodedString());
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.goGameActivity_.sendBroadcast(intent);
    }

    protected void sendGetSessionDataCommand() {
        FabricEncode fabric_encode = new FabricEncode(
                FabricCommands.FABRIC_COMMAND_GET_SESSION_DATA,
                FabricResults.IGNORE,
                FabricThemes.GO,
                this.linkIdStr(),
                this.sessionIdStr(),
                0
        );

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_GAME_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND, FabricCommands.FABRIC_COMMAND_GET_SESSION_DATA_STR);
        intent.putExtra(BundleIndexDefine.LINK_ID, this.linkIdStr());
        intent.putExtra(BundleIndexDefine.SESSION_ID, this.sessionIdStr());
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_encode.getEncodedString());
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.goGameActivity_.sendBroadcast(intent);
    }
}
