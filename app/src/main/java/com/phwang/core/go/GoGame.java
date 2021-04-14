/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.go;

import com.phwang.core.utils.abend.Abend;

public class GoGame {
    private String objectName() {return "GoGame";}
    private static final int GO_GAME_CLASS_MAX_MOVES_ARRAY_SIZE = 1024;

    private GoRoot goRoot_;
    private int maxMove_;
    private Boolean thePassReceived = false;
    private Boolean theGameIsOver = false;
    private GoMoveInfo[] theMovesArray;

    public GoConfigInfo goConfigInfo() { return this.goRoot_.goConfigInfo();  }
    public GoBoardInfo goBoard() { return this.goRoot_.goBoard(); }
    public GoFight goFight() { return this.goRoot_.goFight(); }
    private int totalMoves() { return this.goBoard().totalMoves(); }
    public int nextColor() { return this.goBoard().nextColor(); }

    public GoGame(GoRoot go_root_val) {
        this.goRoot_ = go_root_val;
        this.theMovesArray = new GoMoveInfo[GO_GAME_CLASS_MAX_MOVES_ARRAY_SIZE];
    }

    private void resetGameObjectPartialData() {
        this.thePassReceived = false;
        this.theGameIsOver = false;
    }

    public void addNewMoveAndFight(GoMoveInfo move_val) {
        this.debug(false, "AddNewMoveAndFight", "Move=" + move_val.moveDebugStr());

        if (move_val.TurnIndex() != this.totalMoves() + 1) {
            this.log("AddNewMoveAndFight", "duplicated move received ***************** index= " + move_val.TurnIndex());
            return;
        }

        if (this.theGameIsOver) {
            this.abend("AddNewMoveAndFight", "theGameIsOver");
            return;
        }

        this.thePassReceived = false;
        this.goBoard().clearLastDeadStone();
        this.insertMoveToMoveList(move_val);
        this.goFight().enterBattle(move_val);
        this.goBoard().setNextColor(GoDefine.getOppositeColor(move_val.MyColor()));
    }

    private void insertMoveToMoveList(GoMoveInfo move_val) {
        this.theMovesArray[this.totalMoves()] = move_val;
        this.goBoard().incrementTotalMoves();
        this.maxMove_ = this.totalMoves();
    }

    public void processBackwardMove() {
        this.debug(true, "ProcessBackwardMove", "");

        this.thePassReceived = false;
        if (this.goBoard().totalMoves() <= this.goConfigInfo().handicapPoint()) {
            return;
        }
        this.goBoard().decrementTotalMoves();
        this.processTheWholeMoveList();
    }

    public void processDoubleBackwardMove() {
        this.debug(true, "ProcessDoubleBackwardMove", "");

        this.thePassReceived = false;
        if (this.totalMoves() <= this.goConfigInfo().handicapPoint()) {
            return;
        }
        this.goBoard().setTotalMoves(this.goConfigInfo().handicapPoint());
        this.processTheWholeMoveList();
    }

    public void processForwardMove() {
        this.debug(true, "ProcessForwardMove", "");

        this.thePassReceived = false;
        if (this.totalMoves() > this.maxMove_) {
            this.abend("ProcessForwardMove", "totalMoves > maxMove=");
            return;
        }
        if (this.totalMoves() == this.maxMove_) {
            return;
        }
        this.goBoard().incrementTotalMoves();
        this.processTheWholeMoveList();
    }

    public void processDoubleForwardMove() {
        this.debug(true, "ProcessDoubleForwardMove", "");

        this.thePassReceived = false;
        if (this.totalMoves() > this.maxMove_) {
            this.abend("ProcessDoubleForwardMove", "totalMoves > maxMove=");
            return;
        }
        if (this.totalMoves() == this.maxMove_) {
            return;
        }
        this.goBoard().setTotalMoves(this.maxMove_);
        this.processTheWholeMoveList();
    }

    private void processTheWholeMoveList() {
        this.goBoard().resetBoardObjectData();
        this.goFight().resetEngineObjectData();
        this.resetGameObjectPartialData();

        int i = 0;
        while (i < this.totalMoves()) {
            GoMoveInfo move = this.theMovesArray[i];
            this.goFight().enterBattle(move);
            this.goBoard().setNextColor(GoDefine.getOppositeColor(move.MyColor()));
            i += 1;
        }
    }
    
    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    public void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
