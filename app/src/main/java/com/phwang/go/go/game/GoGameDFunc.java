/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.game;

import android.util.Log;

import com.phwang.core.utils.encoders.Encoders;

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

    protected void parseGetSessionData(String data_package_str_val) {
        Log.e(TAG, "parseGetSessionData() data=" + data_package_str_val);

        String rest_str = Encoders.sDecode5(data_package_str_val);
        String link_id_str = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);
        //Log.e(TAG, "parseGetSessionData() link_id_str=" + link_id_str);
        if (!this.linkIdStr().equals(link_id_str)) {
            Log.e(TAG, "parseGetSessionData(link_id is different) link_id=" + this.linkIdStr() + " " + link_id_str);
            return;
        }

        String session_id_str = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);
        //Log.e(TAG, "parseGetSessionData() session_id_str=" + session_id_str);
        if (!this.sessionIdStr().equals(session_id_str)) {
            Log.e(TAG, "parseGetSessionData(session_id is different) link_id=" + this.sessionIdStr() + " " + session_id_str);
            return;
        }

        String encoded_theme_data_str = Encoders.sSubstring5(rest_str);
        rest_str = Encoders.sSubstring5_(rest_str);
        //Log.e(TAG, "parseGetSessionData() encoded_theme_data_str=" + encoded_theme_data_str);

        String theme_data_str = Encoders.sDecode5(encoded_theme_data_str);
        //Log.e(TAG, "parseGetSessionData() theme_data_str=" + theme_data_str);

        String board_data_str = theme_data_str.substring(1);

        this.goBoard().decodeBoard(board_data_str);
        this.goView().drawBoard();
    }
}
