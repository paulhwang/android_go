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
}
