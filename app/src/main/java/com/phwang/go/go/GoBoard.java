/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go;

import android.util.Log;

import com.phwang.core.go.GoDefine;
import com.phwang.core.utils.Define;
import com.phwang.core.utils.EncodeNumber;

public class GoBoard {
    public static final int TOTAL_MOVE_SIZE = GoDefine.TOTAL_MOVE_SIZE;

    private GoGame goGame_;
    private int boardSize_ = 9;
    private final int[][] boardArray_ = new int[20][20];
    private int totalMoves_ = 0;
    private int nextColor_ = 1;

    protected int boardSize() { return this.boardSize_; }
    protected int board(int x_val, int y_val) { return this.boardArray_[x_val][y_val]; }
    protected void setBoard(int x_val, int y_val, int val) { this.boardArray_[x_val][y_val] = val; }
    protected int totalMoves() { return this.totalMoves_; };
    protected int nextColor() { return this.nextColor_; };

    public GoBoard(GoGame go_game_val) {
        this.goGame_ = go_game_val;
        this.boardArray_[3][3] = 1;
        this.boardArray_[6][6] = 2;
        this.boardArray_[5][5] = 1;
        this.boardArray_[5][6] = 2;
        this.boardArray_[3][4] = 1;
        this.boardArray_[6][5] = 2;
    }

    protected void decodeBoard(String data_str_val) {
        String total_moves_str = data_str_val.substring(0, 3);
        this.totalMoves_ = EncodeNumber.decode(total_moves_str);
        this.nextColor_ = data_str_val.charAt(3);
        String rest_str = data_str_val.substring(4);
        for (int i = 0; i < this.boardSize_; i++) {
            for (int j = 0; j < this.boardSize_; j++) {
                this.boardArray_[i][j] = rest_str.charAt(i * this.boardSize_ + j) - 48;
            }
        }
    }

    protected String encodeMove(int x_val, int y_val) {
        StringBuilder buf = new StringBuilder("GM");
        buf.append(EncodeNumber.encode(x_val, 2));
        buf.append(EncodeNumber.encode(y_val, 2));
        buf.append(this.nextColor_);
        buf.append(EncodeNumber.encode(this.totalMoves_ + 1, 3));
        String data = buf.toString();

        buf = new StringBuilder();
        buf.append(EncodeNumber.encode(data.length(), Define.DATA_LENGTH_SIZE));
        buf.append(data);
        return buf.toString();
    }
}
