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

import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.CommandDefine;
import com.phwang.go.define.IntentDefine;

public class GoGameFunc {
    private static final String TAG = "GoGameFunc";
    private GoGameActivity goGame_;

    protected GoGameView goView() { return this.goGame_.goView(); }
    protected GoGameBoard goBoard() { return this.goGame_.goBoard(); }

    protected GoGameFunc(GoGameActivity go_game_val) {
        this.goGame_ = go_game_val;
    }

    protected void do_put_session_data(String move_data_val) {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_GAME_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_COMMAND);
        intent.putExtra(BundleIndexDefine.COMMAND, CommandDefine.FABRIC_COMMAND_PUT_SESSION_DATA_STR);
        intent.putExtra(BundleIndexDefine.MOVE_DATA, move_data_val);
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.goGame_.sendBroadcast(intent);
    }

    protected void do_get_session_data() {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_GAME_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_COMMAND);
        intent.putExtra(BundleIndexDefine.COMMAND, CommandDefine.FABRIC_COMMAND_GET_SESSION_DATA_STR);
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.goGame_.sendBroadcast(intent);
    }

    protected void processGetSessionData(String board_data_val) {
        Log.e(TAG, "processGetSessionData() data=" + board_data_val);
        this.goBoard().decodeBoard(board_data_val);
        this.goView().drawBoard();
    }
}
