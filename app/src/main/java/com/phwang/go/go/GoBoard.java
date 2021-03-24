/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go;

public class GoBoard {
    private GoGame goGame_;
    private int boardSize_ = 19;
    private final int[][] board_ = new int[20][20];
    private int totalTurns_;

    protected int boardSize() { return this.boardSize_; }
    protected int board(int x_val, int y_val) { return this.board_[x_val][y_val]; }
    protected void setBoard(int x_val, int y_val, int val) { this.board_[x_val][y_val] = val; }
    protected int totalTurns() { return this.totalTurns_; };

    public GoBoard(GoGame go_game_val) {
        this.goGame_ = go_game_val;
        this.board_[3][3] = 1;
        this.board_[6][6] = 2;
        this.board_[5][5] = 1;
        this.board_[5][6] = 2;
        this.board_[3][4] = 1;
        this.board_[6][5] = 2;
    }

    protected void decodeBoard(String data_str_val) {

    }
}
