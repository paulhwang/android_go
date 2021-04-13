/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.game;

import android.util.Log;
import com.phwang.core.go.GoDefine;
import com.phwang.core.go.GoMoveInfo;
import com.phwang.core.utils.encoders.Encoders;

public class GoGameBoard {
    private static final String TAG = "GoGameBoard";

    private GoGameActivity goGame_;
    private int boardSize_ = 19;
    private final int[][] boardArray_ = new int[20][20];
    private int totalMoves_ = 0;
    private int nextColor_ = 1;
    private int myColor_;

    protected int myColor() { return this.myColor_; };
    protected int boardSize() { return this.boardSize_; }
    protected void setBoardSize(int val) { this.boardSize_ = val; }
    protected int board(int x_val, int y_val) { return this.boardArray_[x_val][y_val]; }
    protected void setBoard(int x_val, int y_val, int val) { this.boardArray_[x_val][y_val] = val; }
    protected int totalMoves() { return this.totalMoves_; };
    protected int nextColor() { return this.nextColor_; };
    protected Boolean isValidMove(int x_val, int y_val) { return (this.boardArray_[x_val][y_val] == 0) ? true : false; }

    public GoGameBoard(GoGameActivity go_game_val, String config_str_val) {
        this.goGame_ = go_game_val;
        this.decodeConfig(config_str_val);
    }

    void decodeConfig(String config_str_val) {
        //Log.e(TAG, "decodeConfig() config_str=" + config_str_val);

        String board_size_str = config_str_val.substring(1, 3);
        //String handicap_str = config_str.substring(3, 5);
        //String komi_str = config_str.substring(5, 7);
        String color_str = config_str_val.substring(7, 8);

        this.boardSize_ = Encoders.iDecodeRaw(board_size_str);
        //this.handicapPoint_ = Encoders.iDecodeRaw(handicap_str);
        //this.komiPoint_ = Encoders.iDecodeRaw(komi_str);
        this.myColor_ = Encoders.iDecodeRaw(color_str);
    }

    protected void decodeBoard(String data_str_val) {
        String total_moves_str = data_str_val.substring(0, 3);
        this.totalMoves_ = Encoders.iDecodeRaw(total_moves_str);
        this.nextColor_ = data_str_val.charAt(3) - 48;
        String rest_str = data_str_val.substring(4);
        for (int i = 0; i < this.boardSize_; i++) {
            for (int j = 0; j < this.boardSize_; j++) {
                this.boardArray_[i][j] = rest_str.charAt(i * this.boardSize_ + j) - 48;
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
