/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.go;

import com.phwang.core.utils.abend.Abend;
import com.phwang.core.utils.encoders.Encoders;

public class GoBoardInfo {
    private String objectName() {return "GoBoardInfo";}

    private final char GO_PROTOCOL_GAME_INFO = 'G';
    public static final int GO_INVALID_COORDINATE = 20;
    public static final int GO_MAX_BOARD_SIZE = 19;
    public static final int GO_TOTAL_MOVE_SIZE = 3;
    public static final int GO_EMPTY_STONE = 0;
    public static final int GO_BLACK_STONE = 1;
    public static final int GO_WHITE_STONE = 2;
    public static final int GO_BOTH_STONE = 3;
    public static final int GO_MARK_DEAD_STONE_DIFF = 4;
    public static final int GO_MARK_EMPTY_STONE_DIFF = 8;

    public static final int GO_MARKED_DEAD_BLACK_STONE = (GO_BLACK_STONE + GO_MARK_DEAD_STONE_DIFF);
    public static final int GO_MARKED_DEAD_WHITE_STONE = (GO_WHITE_STONE + GO_MARK_DEAD_STONE_DIFF);

    public static final int GO_MARKED_EMPTY_BLACK_STONE = (GO_BLACK_STONE + GO_MARK_EMPTY_STONE_DIFF);
    public static final int GO_MARKED_EMPTY_WHITE_STONE = (GO_WHITE_STONE + GO_MARK_EMPTY_STONE_DIFF);

    private int totalMoves_;
    private int nextColor_;
    private int[][] boardArray_ = new int[GO_MAX_BOARD_SIZE][GO_MAX_BOARD_SIZE];
    private int[][] markedBoardArray_ = new int[GO_MAX_BOARD_SIZE][GO_MAX_BOARD_SIZE];
    private int blackCapturedStones_;
    private int whiteCapturedStones_;
    private int lastDeadX_;
    private int lastDeadY_;
    private String boardOutputBuffer_;

    public String boardOutputBuffer() { return this.boardOutputBuffer_; }
    public int totalMoves() { return this.totalMoves_; }
    public int nextColor() { return this.nextColor_; }
    public int boardArray(int x_val, int y_val) { return this.boardArray_[x_val][y_val]; }
    public void addBlackCapturedStones(int val) { this.blackCapturedStones_ += val; }
    public void addWhiteCapturedStones(int val) { this.whiteCapturedStones_ += val; }
    public void setNextColor(int val) { this.nextColor_ = val; }
    public void incrementTotalMoves() { this.totalMoves_++; }
    public void decrementTotalMoves() { this.totalMoves_--; }
    public void setTotalMoves(int val) { this.totalMoves_ = val; }
    public void setBoardArray(int x_val, int y_val, int data_val) { this.boardArray_[x_val][y_val] = data_val; }
    public void setLastDeadStone(int x_val, int y_val) { this.lastDeadX_ = x_val; this.lastDeadY_ = y_val; }

    public GoBoardInfo() {
        this.totalMoves_ = 0;
        this.nextColor_ = GO_BLACK_STONE;
        this.clearBoardArrays();
    }

    public void clearBoardArrays() {
        for (int i = 0; i < GO_MAX_BOARD_SIZE; i++) {
            for (int j = 0; j < GO_MAX_BOARD_SIZE; j++) {
                this.boardArray_[i][j] = GO_EMPTY_STONE;
                this.markedBoardArray_[i][j] = GO_EMPTY_STONE;
            }
        }
        this.blackCapturedStones_ = 0;
        this.whiteCapturedStones_ = 0;
        this.clearLastDeadStone();
    }

    public void clearLastDeadStone() {
        this.lastDeadX_ = GO_MAX_BOARD_SIZE;
        this.lastDeadY_ = GO_MAX_BOARD_SIZE;
    }

    public void resetMarkedBoardObjectData() {

    }

    public void encodeBoard() {
        StringBuilder buf = new StringBuilder();
        buf.append(GO_PROTOCOL_GAME_INFO);
        buf.append(Encoders.iEncodeRaw(this.totalMoves_, GO_TOTAL_MOVE_SIZE));
        buf.append(Encoders.iEncodeRaw1(this.nextColor_));

        for (int i = 0; i < GO_MAX_BOARD_SIZE; i++) {
            for (int j = 0; j < GO_MAX_BOARD_SIZE; j++) {
                char c = '0';
                switch (this.boardArray_[i][j]) {
                    case 1: c = '1'; break;
                    case 2: c = '2'; break;
                }
                buf.append(c);
            }
        }

        buf.append(Encoders.iEncodeRaw3(this.blackCapturedStones_));
        buf.append(Encoders.iEncodeRaw3(this.whiteCapturedStones_));

        buf.append(Encoders.iEncodeRaw2(this.lastDeadX_));
        buf.append(Encoders.iEncodeRaw2(this.lastDeadY_));

        this.boardOutputBuffer_ = buf.toString();

        this.debug(false, "encodeBoard", this.boardOutputBuffer_);
    }

    public void decodeBoard(String go_board_info_str_val) {
        String total_moves_str = go_board_info_str_val.substring(1, 4);
        this.totalMoves_ = Encoders.iDecodeRaw(total_moves_str);
        this.nextColor_ = go_board_info_str_val.charAt(4) - 48;
        String rest_str = go_board_info_str_val.substring(5);
        for (int i = 0; i < GO_MAX_BOARD_SIZE; i++) {
            for (int j = 0; j < GO_MAX_BOARD_SIZE; j++) {
                this.boardArray_[i][j] = rest_str.charAt(i * GO_MAX_BOARD_SIZE + j) - 48;
            }
        }
    }

    public static int getOppositeColor(int color_val) {
        switch (color_val) {
            case GO_BLACK_STONE:
                return GO_WHITE_STONE;
            case GO_WHITE_STONE:
                return GO_BLACK_STONE;
            default:
                return GO_EMPTY_STONE;
        }
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    public void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
