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

    private int totalMoves_;
    private int nextColor_;
    private int[][] theBoardArray;
    private int[][] theMarkedBoardArray;
    private String theBoardOutputBuffer;
    private int theBlackCapturedStones;
    private int theWhiteCapturedStones;
    private int theLastDeadX;
    private int theLastDeadY;

    public String boardOutputBuffer() { return this.theBoardOutputBuffer; }
    public int totalMoves() { return this.totalMoves_; }
    public int nextColor() { return this.nextColor_; }
    public int boardArray(int x_val, int y_val) { return this.theBoardArray[x_val][y_val]; }
    public void addBlackCapturedStones(int val) { this.theBlackCapturedStones += val; }
    public void addWhiteCapturedStones(int val) { this.theWhiteCapturedStones += val; }
    public void setNextColor(int val) { this.nextColor_ = val; }
    public void incrementTotalMoves() { this.totalMoves_++; }
    public void decrementTotalMoves() { this.totalMoves_--; }
    public void setTotalMoves(int val) { this.totalMoves_ = val; }
    public void setBoardArray(int x_val, int y_val, int data_val) { this.theBoardArray[x_val][y_val] = data_val; }
    public void setLastDeadStone(int x_val, int y_val) { this.theLastDeadX = x_val; this.theLastDeadY = y_val; }

    public GoBoardInfo() {
        this.totalMoves_ = 0;
        this.nextColor_ = GoDefine.GO_BLACK_STONE;
        this.theBoardArray = new int[GoDefine.MAX_BOARD_SIZE] [GoDefine.MAX_BOARD_SIZE];
        this.theMarkedBoardArray = new int[GoDefine.MAX_BOARD_SIZE] [GoDefine.MAX_BOARD_SIZE];
        this.resetBoardObjectData();
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
                switch (this.theBoardArray[i][j]) {
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

    public void resetBoardObjectData() {
        for (int i = 0; i < GoDefine.MAX_BOARD_SIZE; i++) {
            for (int j = 0; j < GoDefine.MAX_BOARD_SIZE; j++) {
                this.theBoardArray[i][j] = GoDefine.GO_EMPTY_STONE;
                this.theMarkedBoardArray[i][j] = GoDefine.GO_EMPTY_STONE;
            }
        }
        this.theBlackCapturedStones = 0;
        this.theWhiteCapturedStones = 0;
        this.clearLastDeadStone();
    }

    public void resetMarkedBoardObjectData() {

    }

    public void clearLastDeadStone() {
        this.theLastDeadX = 19;
        this.theLastDeadY = 19;
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    public void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
