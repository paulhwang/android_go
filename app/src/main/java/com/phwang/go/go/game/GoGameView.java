/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.util.Log;

import com.phwang.core.go.GoBoardInfo;
import com.phwang.core.go.GoConfigInfo;
import com.phwang.go.R;

public class GoGameView  extends View {
    private static final String TAG = "GoGameView";

    private final GoGameActivity goGameActivity_;
    protected GoConfigInfo goConfigInfo() { return this.goGameActivity_.goConfigInfo(); };
    private GoBoardInfo goBoardInfo() { return this.goGameActivity_.goBoardInfo(); }
    private int boardSize() { return this.goConfigInfo().boardSize(); }
    private int boardArray(int x_val, int y_val) { return this.goBoardInfo().boardArray(x_val, y_val); }
    private int touchX() { return this.goGameActivity_.touchX(); }
    private int touchY() { return this.goGameActivity_.touchY(); }
    private int moveX() { return this.goGameActivity_.moveX(); }
    private int moveY() { return this.goGameActivity_.moveY(); }

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
    private final Paint redPaint = new Paint();
    private final Paint boardPaint = new Paint();
    private final Rect rect = new Rect();

    public GoGameView(Context context_val, AttributeSet attrs) {
        super(context_val, attrs);
        this.goGameActivity_ = (GoGameActivity) context_val;

        this.whitePaint.setColor(getResources().getColor(R.color.white));
        this.blackPaint.setColor(getResources().getColor(R.color.black));
        this.redPaint.setColor(getResources().getColor(R.color.red));
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

    private void getViewTopAndLeft() {
        int[] location = new int[2];
        this.getLocationOnScreen(location);
        this.viewLeft = location[0];
        this.viewTop = location[1];
    }

    @Override
    protected void onDraw(Canvas canvas_val) {
        Log.d(TAG, "onDraw");
        this.canvas = canvas_val;
        this.setupGridLen(this.width, this.boardSize());
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

    protected void drawBoard() {
        this.canvas.drawRect(this.gridLen1 - this.sideGridLen, this.gridLen1 - this.sideGridLen, this.gridLen19 + this.sideGridLen, this.gridLen19 + this.sideGridLen, this.boardPaint);

        for (int i = this.boardSize() ; i > 0 ; i--) {
            Paint paint = (this.goGameActivity_.waitingForConfirm() && this.touchX() == i) ? this.redPaint : this.blackPaint;
            this.canvas.drawLine(i * this.gridLen + this.gridLen0 - 2, this.gridLen1, i * this.gridLen + this.gridLen0 - 2, this.gridLen19, paint);
            this.canvas.drawLine(i * this.gridLen + this.gridLen0 - 1, this.gridLen1, i * this.gridLen + this.gridLen0 - 1, this.gridLen19, paint);
            this.canvas.drawLine(i * this.gridLen + this.gridLen0,     this.gridLen1, i * this.gridLen + this.gridLen0,     this.gridLen19, paint);
            this.canvas.drawLine(i * this.gridLen + this.gridLen0 + 1, this.gridLen1, i * this.gridLen + this.gridLen0 + 1, this.gridLen19, paint);
            this.canvas.drawLine(i * this.gridLen + this.gridLen0 + 2, this.gridLen1, i * this.gridLen + this.gridLen0 + 2, this.gridLen19, paint);

            paint = (this.goGameActivity_.waitingForConfirm() && this.touchY() == i) ? this.redPaint : this.blackPaint;
            this.canvas.drawLine(this.gridLen1, i * this.gridLen + this.gridLen0 - 2, this.gridLen19, i * this.gridLen + this.gridLen0 - 2, paint);
            this.canvas.drawLine(this.gridLen1, i * this.gridLen + this.gridLen0 - 1, this.gridLen19, i * this.gridLen + this.gridLen0 - 1, paint);
            this.canvas.drawLine(this.gridLen1, i * this.gridLen + this.gridLen0,     this.gridLen19, i * this.gridLen + this.gridLen0,     paint);
            this.canvas.drawLine(this.gridLen1, i * this.gridLen + this.gridLen0 + 1, this.gridLen19, i * this.gridLen + this.gridLen0 + 1, paint);
            this.canvas.drawLine(this.gridLen1, i * this.gridLen + this.gridLen0 + 2, this.gridLen19, i * this.gridLen + this.gridLen0 + 2, paint);
        }

        switch (this.boardSize()) {
            case 9:
                this.drawBoardDot(5, 5);
                break;
            case 13:
                this.drawBoardDot(4, 4);
                this.drawBoardDot(4, 10);
                this.drawBoardDot(10, 4);
                this.drawBoardDot(10, 10);
                this.drawBoardDot(7, 7);
                break;
            case 19:
                this.drawBoardDot(4, 4);
                this.drawBoardDot(4, 10);
                this.drawBoardDot(4, 16);
                this.drawBoardDot(10, 4);
                this.drawBoardDot(10, 10);
                this.drawBoardDot(10, 16);
                this.drawBoardDot(16, 4);
                this.drawBoardDot(16, 10);
                this.drawBoardDot(16, 16);
                break;
        }

        this.drawStones();
        this.invalidate();
    }

    private void drawBoardDot(int x, int y) {
        this.canvas.drawCircle(x * this.gridLen + this.gridLen0, y * this.gridLen + this.gridLen0, this.dotGridLen, this.blackPaint);
    }

    private void drawStones() {
        for (int i = this.boardSize() - 1; i >= 0; i--) {
            for (int j = this.boardSize() - 1; j >= 0; j--) {
                if (this.boardArray(i, j) == GoBoardInfo.GO_BLACK_STONE){
                    this.drawStone(i, j, this.blackPaint);
                }
                else if (this.boardArray(i, j) == GoBoardInfo.GO_WHITE_STONE){
                    this.drawStone(i, j, this.whitePaint);
                }
            }
        }

        if (this.touchX() != GoBoardInfo.GO_INVALID_COORDINATE) {
            this.drawStone(this.moveX(), this.moveY(), this.goBoardInfo().nextColor() == GoBoardInfo.GO_BLACK_STONE ? this.blackPaint : this.whitePaint);
        }
    }

    private void drawStone(int x, int y, Paint paint_val) {
        this.canvas.drawCircle(x * this.gridLen + this.gridLen1, y * this.gridLen + this.gridLen1, this.stoneGridLen, paint_val);
    }
}
