/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.game;

import android.util.Log;

import com.phwang.core.protocols.fabric.FabricData;

public class GoGameDFunc {
    private static final String TAG = "GoGameDFunc";
    private GoGameActivity goGameActivity_;

    protected GoGameBoard goBoard() { return this.goGameActivity_.goBoard(); }
    protected GoGameView goView() { return this.goGameActivity_.goView(); }
    private String linkIdStr() { return this.goGameActivity_.linkIdStr(); }
    private String sessionIdStr() { return this.goGameActivity_.sessionIdStr(); }

    protected GoGameDFunc(GoGameActivity go_game_activity_val) {
        this.goGameActivity_ = go_game_activity_val;
    }

    protected void parseGetSessionData(String fabric_data_str_val) {
        //Log.e(TAG, "parseGetSessionData() fabric_data_str=" + fabric_data_str_val);

        FabricData fabric_decode = new FabricData(fabric_data_str_val);
        //Log.e(TAG, "parseGetSessionData() theme_data_str_=" + fabric_decode.stringList(0));

        String theme_data_str = fabric_decode.stringArrayElement(0);
        //Log.e(TAG, "parseGetSessionData() theme_data_str=" + theme_data_str);

        String board_data_str = theme_data_str.substring(1);

        this.goBoard().decodeBoard(board_data_str);
        this.goView().drawBoard();
    }
}
