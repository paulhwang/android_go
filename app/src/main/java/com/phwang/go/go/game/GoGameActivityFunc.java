/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.game;

import android.content.Intent;
import android.util.Log;

import com.phwang.core.fabric.FabricCommands;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;

public class GoGameActivityFunc {
    private static final String TAG = "GoGameActivityFunc";
    private GoGameActivity goGame_;

    protected GoGameView goView() { return this.goGame_.goView(); }
    protected GoGameBoard goBoard() { return this.goGame_.goBoard(); }
    private String linkIdStr() { return this.goGame_.linkIdStr(); }
    private String sessionIdStr() { return this.goGame_.sessionIdStr(); }

    protected GoGameActivityFunc(GoGameActivity go_game_val) {
        this.goGame_ = go_game_val;
    }

    protected void do_delete_session() {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_GAME_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_COMMAND);
        intent.putExtra(BundleIndexDefine.COMMAND, FabricCommands.FABRIC_COMMAND_DELETE_SESSION_STR);
        intent.putExtra(BundleIndexDefine.LINK_ID, this.linkIdStr());
        intent.putExtra(BundleIndexDefine.SESSION_ID, this.sessionIdStr());
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.goGame_.sendBroadcast(intent);
    }

    protected void do_put_session_data(String move_data_val) {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_GAME_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_COMMAND);
        intent.putExtra(BundleIndexDefine.COMMAND, FabricCommands.FABRIC_COMMAND_PUT_SESSION_DATA_STR);
        intent.putExtra(BundleIndexDefine.LINK_ID, this.linkIdStr());
        intent.putExtra(BundleIndexDefine.SESSION_ID, this.sessionIdStr());
        intent.putExtra(BundleIndexDefine.MOVE_DATA, move_data_val);
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.goGame_.sendBroadcast(intent);
    }

    protected void do_get_session_data() {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_GAME_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_COMMAND);
        intent.putExtra(BundleIndexDefine.COMMAND, FabricCommands.FABRIC_COMMAND_GET_SESSION_DATA_STR);
        intent.putExtra(BundleIndexDefine.LINK_ID, this.linkIdStr());
        intent.putExtra(BundleIndexDefine.SESSION_ID, this.sessionIdStr());
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.goGame_.sendBroadcast(intent);
    }

    protected void processGetSessionData(String board_data_val) {
        Log.e(TAG, "processGetSessionData() data=" + board_data_val);
        this.goBoard().decodeBoard(board_data_val);
        this.goView().drawBoard();
    }
}
