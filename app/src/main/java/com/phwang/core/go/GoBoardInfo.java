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

    private int totalMoves_ = 0;
    private int nextColor_ = GoDefine.GO_BLACK_STONE;
    private int[][] boardArray_ = new int[GoDefine.MAX_BOARD_SIZE] [GoDefine.MAX_BOARD_SIZE];
    private int[][] markedBoardArray_ = new int[GoDefine.MAX_BOARD_SIZE] [GoDefine.MAX_BOARD_SIZE];
    private String theBoardOutputBuffer;
    private int theBlackCapturedStones;
    private int theWhiteCapturedStones;
    private int theLastDeadX;
    private int theLastDeadY;

    public String boardOutputBuffer() { return this.theBoardOutputBuffer; }
    public int totalMoves() { return this.totalMoves_; }
    public int nextColor() { return this.nextColor_; }
    public int boardArray(int x_val, int y_val) { return this.boardArray_[x_val][y_val]; }
    public void addBlackCapturedStones(int val) { this.theBlackCapturedStones += val; }
    public void addWhiteCapturedStones(int val) { this.theWhiteCapturedStones += val; }
    public void setNextColor(int val) { this.nextColor_ = val; }
    public void incrementTotalMoves() { this.totalMoves_++; }
    public void decrementTotalMoves() { this.totalMoves_--; }
    public void setTotalMoves(int val) { this.totalMoves_ = val; }
    public void setBoardArray(int x_val, int y_val, int data_val) { this.boardArray_[x_val][y_val] = data_val; }
    public void setLastDeadStone(int x_val, int y_val) { this.theLastDeadX = x_val; this.theLastDeadY = y_val; }

    public GoBoardInfo() {
        this.resetBoardObjectData();
    }

    public void resetBoardObjectData() {
        for (int i = 0; i < GoDefine.MAX_BOARD_SIZE; i++) {
            for (int j = 0; j < GoDefine.MAX_BOARD_SIZE; j++) {
                this.boardArray_[i][j] = GoDefine.GO_EMPTY_STONE;
                this.markedBoardArray_[i][j] = GoDefine.GO_EMPTY_STONE;
            }
        }
        this.theBlackCapturedStones = 0;
        this.theWhiteCapturedStones = 0;
        this.clearLastDeadStone();
    }

    public void resetMarkedBoardObjectData() {

    }

    public void clearLastDeadStone() {
        this.theLastDeadX = GoDefine.MAX_BOARD_SIZE;
        this.theLastDeadY = GoDefine.MAX_BOARD_SIZE;
    }

    private final char GO_PROTOCOL_GAME_INFO = 'G';

    public void encodeBoard() {
        this.theBoardOutputBuffer = "";
        this.theBoardOutputBuffer = this.theBoardOutputBuffer + GO_PROTOCOL_GAME_INFO;
        this.theBoardOutputBuffer = this.theBoardOutputBuffer + Encoders.iEncodeRaw(this.totalMoves_, GoDefine.TOTAL_MOVE_SIZE);
        this.theBoardOutputBuffer = this.theBoardOutputBuffer + Encoders.iEncodeRaw1(this.nextColor_);

        //int board_size = this.goConfigInfo().boardSize();
        for (int i = 0; i < GoDefine.MAX_BOARD_SIZE; i++) {
            for (int j = 0; j < GoDefine.MAX_BOARD_SIZE; j++) {
                char c = '0';
                switch (this.boardArray_[i][j]) {
                    case 1: c = '1'; break;
                    case 2: c = '2'; break;
                }
                this.theBoardOutputBuffer = this.theBoardOutputBuffer + c;
            }
        }

        this.theBoardOutputBuffer = this.theBoardOutputBuffer + Encoders.iEncodeRaw3(this.theBlackCapturedStones);
        this.theBoardOutputBuffer = this.theBoardOutputBuffer + Encoders.iEncodeRaw3(this.theWhiteCapturedStones);

        this.theBoardOutputBuffer = this.theBoardOutputBuffer + Encoders.iEncodeRaw2(this.theLastDeadX);
        this.theBoardOutputBuffer = this.theBoardOutputBuffer + Encoders.iEncodeRaw2(this.theLastDeadY);

        this.debug(false, "encodeBoard", this.theBoardOutputBuffer);
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

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    public void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
