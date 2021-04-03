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
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_GAME_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND, FabricCommands.FABRIC_COMMAND_DELETE_SESSION_STR);
        intent.putExtra(BundleIndexDefine.LINK_ID, this.linkIdStr());
        intent.putExtra(BundleIndexDefine.SESSION_ID, this.sessionIdStr());
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.goGameActivity_.sendBroadcast(intent);
    }

    protected void sendPutSessionDataCommand(String move_data_val) {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_GAME_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND, FabricCommands.FABRIC_COMMAND_PUT_SESSION_DATA_STR);
        intent.putExtra(BundleIndexDefine.LINK_ID, this.linkIdStr());
        intent.putExtra(BundleIndexDefine.SESSION_ID, this.sessionIdStr());
        intent.putExtra(BundleIndexDefine.MOVE_DATA, move_data_val);
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.goGameActivity_.sendBroadcast(intent);
    }

    protected void sendGetSessionDataCommand() {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_GAME_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND, FabricCommands.FABRIC_COMMAND_GET_SESSION_DATA_STR);
        intent.putExtra(BundleIndexDefine.LINK_ID, this.linkIdStr());
        intent.putExtra(BundleIndexDefine.SESSION_ID, this.sessionIdStr());
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.goGameActivity_.sendBroadcast(intent);
    }
}
