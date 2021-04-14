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

    private GoGameActivity goGame_;
    private final int[][] boardArray_ = new int[20][20];
    private int totalMoves_ = 0;
    private int nextColor_ = 1;

    protected GoConfigInfo goConfigInfo() { return this.goGame_.goConfigInfo(); };
    protected int board(int x_val, int y_val) { return this.boardArray_[x_val][y_val]; }
    protected void setBoard(int x_val, int y_val, int val) { this.boardArray_[x_val][y_val] = val; }
    protected int totalMoves() { return this.totalMoves_; };
    protected int nextColor() { return this.nextColor_; };
    protected Boolean isValidMove(int x_val, int y_val) { return (this.boardArray_[x_val][y_val] == 0) ? true : false; }
    private int boardSize() { return this.goConfigInfo().boardSize(); }

    public GoGameBoard(GoGameActivity go_game_val, String go_config_info_str_val) {
        this.goGame_ = go_game_val;
    }

    protected void decodeBoard(String data_str_val) {
        String total_moves_str = data_str_val.substring(0, 3);
        this.totalMoves_ = Encoders.iDecodeRaw(total_moves_str);
        this.nextColor_ = data_str_val.charAt(3) - 48;
        String rest_str = data_str_val.substring(4);
        for (int i = 0; i < this.boardSize(); i++) {
            for (int j = 0; j < this.boardSize(); j++) {
                this.boardArray_[i][j] = rest_str.charAt(i * this.boardSize() + j) - 48;
            }
        }
    }

    protected String encodeMove(int x_val, int y_val) {
        if (!isValidCoordinates(x_val, y_val)) {
            Log.e(TAG, "encodeMove() bad coordinate: " + x_val + " " + y_val);
            return null;
        }

        if (this.boardArray_[x_val][y_val] != 0) {
            return null;
        }

        return GoMoveInfo.encodeMove(x_val, y_val, this.nextColor_, this.totalMoves_ + 1);
    }

    private Boolean isValidCoordinate_(int coordinate_val) {
        return (0 <= coordinate_val) && (coordinate_val < this.boardSize());
    }

    private Boolean isValidCoordinates(int x_val, int y_val) {
        return this.isValidCoordinate_(x_val) && this.isValidCoordinate_(y_val);
    }
}
