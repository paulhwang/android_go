/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.util.Log;

import com.phwang.go.R;
import com.phwang.go.sudoku.SudokuGame;

public class GoView  extends View {
    private static final String TAG = "GoView";
    private int boardSize = 9;
    private final GoGame goGame;
    private int width;
    protected int viewTop;
    protected int viewLeft;
    protected int gridLen;
    protected int halfGridLen;
    protected int sideGridLen;
    protected int stoneGridLen;
    protected int dotGridLen;
    protected int gridLen0;
    protected int gridLen1;
    protected int gridLen19;
    private Canvas canvas;
    private final Paint whitePaint = new Paint();
    private final Paint blackPaint = new Paint();
    private final Paint boardPaint = new Paint();
    private final Rect rect = new Rect();

    public GoView(Context context_val) {
        super(context_val);
        this.goGame = (GoGame) context_val;

        this.whitePaint.setColor(getResources().getColor(R.color.white));
        this.blackPaint.setColor(getResources().getColor(R.color.black));
        this.boardPaint.setColor(getResources().getColor(R.color.board));

        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.width = Math.min(w, h);
        Log.d(TAG,"onSizeChanged: width=" + this.width);
        this.getViewTopAndLeft();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    void getViewTopAndLeft() {
        int[] location = new int[2];
        this.getLocationOnScreen(location);
        this.viewLeft = location[0];
        this.viewTop = location[1];
    }

    @Override
    protected void onDraw(Canvas canvas_val) {
        Log.d(TAG, "onDraw");
        this.canvas = canvas_val;
        this.setupGridLen(this.width, this.boardSize);
        this.drawBoard();
    }

    private void setupGridLen(int width_val, int board_size_val) {
        this.gridLen = width_val / (board_size_val + 2);
        this.halfGridLen = this.gridLen / 2;
        this.stoneGridLen = this.gridLen * 9 / 20;
        this.dotGridLen = this.gridLen / 5;
        this.sideGridLen = this.gridLen * 2 / 3;
        this.gridLen0 = this.halfGridLen;
        this.gridLen1 = this.gridLen + this.gridLen0;
        this.gridLen19 = this.gridLen * board_size_val + this.gridLen0;
    }

    private void drawBoard() {
        this.canvas.drawRect(this.gridLen1 - this.sideGridLen, this.gridLen1 - this.sideGridLen, this.gridLen19 + this.sideGridLen, this.gridLen19 + this.sideGridLen, this.boardPaint);

        for (int i = this.boardSize ; i > 0 ; i--) {
            this.canvas.drawLine(this.gridLen1, i * this.gridLen + this.gridLen0 - 2, this.gridLen19, i * this.gridLen + this.gridLen0 - 2, this.blackPaint);
            this.canvas.drawLine(this.gridLen1, i * this.gridLen + this.gridLen0 - 1, this.gridLen19, i * this.gridLen + this.gridLen0 - 1, this.blackPaint);
            this.canvas.drawLine(this.gridLen1, i * this.gridLen + this.gridLen0,     this.gridLen19, i * this.gridLen + this.gridLen0, this.blackPaint);
            this.canvas.drawLine(this.gridLen1, i * this.gridLen + this.gridLen0 + 1, this.gridLen19, i * this.gridLen + this.gridLen0 + 1, this.blackPaint);
            this.canvas.drawLine(this.gridLen1, i * this.gridLen + this.gridLen0 + 2, this.gridLen19, i * this.gridLen + this.gridLen0 + 2, this.blackPaint);
            this.canvas.drawLine(i * this.gridLen + this.gridLen0 - 2, this.gridLen1, i * this.gridLen + this.gridLen0 - 2, this.gridLen19, this.blackPaint);
            this.canvas.drawLine(i * this.gridLen + this.gridLen0 - 1, this.gridLen1, i * this.gridLen + this.gridLen0 - 1, this.gridLen19, this.blackPaint);
            this.canvas.drawLine(i * this.gridLen + this.gridLen0,     this.gridLen1, i * this.gridLen + this.gridLen0,     this.gridLen19, this.blackPaint);
            this.canvas.drawLine(i * this.gridLen + this.gridLen0 + 1, this.gridLen1, i * this.gridLen + this.gridLen0 + 1, this.gridLen19, this.blackPaint);
            this.canvas.drawLine(i * this.gridLen + this.gridLen0 + 2, this.gridLen1, i * this.gridLen + this.gridLen0 + 2, this.gridLen19, this.blackPaint);
        }

        if (this.boardSize == 9) {
            this.drawBoardDot(5, 5);
        } else if (this.boardSize == 13) {
            this.drawBoardDot(4, 4);
            this.drawBoardDot(4, 10);
            this.drawBoardDot(10, 4);
            this.drawBoardDot(10, 10);
            this.drawBoardDot(7, 7);
        } else if (this.boardSize == 19) {
            this.drawBoardDot(4, 4);
            this.drawBoardDot(4, 10);
            this.drawBoardDot(4, 16);
            this.drawBoardDot(10, 4);
            this.drawBoardDot(10, 10);
            this.drawBoardDot(10, 16);
            this.drawBoardDot(16, 4);
            this.drawBoardDot(16, 10);
            this.drawBoardDot(16, 16);
        }
        this.drawStones();
    }

    void drawBoardDot(int x, int y) {
        this.canvas.drawCircle(x * this.gridLen + this.gridLen0, y * this.gridLen + this.gridLen0, this.dotGridLen, this.blackPaint);
    }

    void drawStones() {
        this.drawStone(3, 3, this.blackPaint);
        this.drawStone(3, 4, this.whitePaint);
        this.drawStone(3, 5, this.blackPaint);
    }

    void drawStone(int x, int y, Paint paint_val) {
        this.canvas.drawCircle(x * this.gridLen + this.gridLen0, y * this.gridLen + this.gridLen0, this.stoneGridLen, paint_val);
    }
}
