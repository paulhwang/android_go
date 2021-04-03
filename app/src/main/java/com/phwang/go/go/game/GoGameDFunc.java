/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.game;

import android.util.Log;

public class GoGameDFunc {
    private static final String TAG = "GoGameDFunc";
    private GoGameActivity goGameActivity_;

    protected GoGameBoard goBoard() { return this.goGameActivity_.goBoard(); }
    protected GoGameView goView() { return this.goGameActivity_.goView(); }

    protected GoGameDFunc(GoGameActivity go_game_activity_val) {
        this.goGameActivity_ = go_game_activity_val;
    }

    protected void parseGetSessionData(String board_data_val) {
        Log.e(TAG, "processGetSessionData() data=" + board_data_val);
        this.goBoard().decodeBoard(board_data_val);
        this.goView().drawBoard();
    }
}
