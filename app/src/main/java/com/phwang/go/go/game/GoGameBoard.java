/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.game;

import android.util.Log;

import com.phwang.core.go.GoConfigInfo;
import com.phwang.core.go.GoDefine;
import com.phwang.core.go.GoMoveInfo;
import com.phwang.core.utils.encoders.Encoders;

public class GoGameBoard {
    private static final String TAG = "GoGameBoard";

    private GoGameActivity goGameActivity_;
    private final int[][] boardArray_ = new int[20][20];
    private int totalMoves_ = 0;
    private int nextColor_ = 1;

    protected GoConfigInfo goConfigInfo() { return this.goGameActivity_.goConfigInfo(); };
    protected int boardArray(int x_val, int y_val) { return this.boardArray_[x_val][y_val]; }
    protected void setBoard(int x_val, int y_val, int val) { this.boardArray_[x_val][y_val] = val; }
    protected int totalMoves() { return this.totalMoves_; };
    protected int nextColor() { return this.nextColor_; };
    protected Boolean isValidMove(int x_val, int y_val) { return (this.boardArray_[x_val][y_val] == 0) ? true : false; }
    private int boardSize() { return this.goConfigInfo().boardSize(); }

    public GoGameBoard(GoGameActivity go_game_val) {
        this.goGameActivity_ = go_game_val;
    }

    protected void decodeBoard(String data_str_val) {
        String total_moves_str = data_str_val.substring(0, 3);
        this.totalMoves_ = Encoders.iDecodeRaw(total_moves_str);
        this.nextColor_ = data_str_val.charAt(3) - 48;
        String rest_str = data_str_val.substring(4);
        for (int i = 0; i < GoDefine.MAX_BOARD_SIZE; i++) {
            for (int j = 0; j < GoDefine.MAX_BOARD_SIZE; j++) {
                this.boardArray_[i][j] = rest_str.charAt(i * GoDefine.MAX_BOARD_SIZE + j) - 48;
            }
        }
    }
}
