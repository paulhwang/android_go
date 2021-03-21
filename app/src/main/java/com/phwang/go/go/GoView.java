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
    private final GoGame goGame;

    private float boardSizeF = 19f;
    private int boardSize = 19;

    public GoView(Context context_val) {
        super(context_val);
        this.goGame = (GoGame) context_val;
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    private int width;
    private final Rect selRect = new Rect();

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(TAG, "onSizeChanged");

        this.width = Math.min(w, h);
        Log.d(TAG,"onSizeChanged: width=" + this.width);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec,
                heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas_val) {
        Log.d(TAG, "onDraw");
        this.drawBoard(canvas_val);
    }

    private void drawBoard(Canvas canvas_val) {
        int grid_len = this.width / (this.boardSize + 2);
        int half_grid_len = grid_len / 2;
        int micro_grid_len = grid_len / 8;
        int grid_len_19 = grid_len * this.boardSize;

        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.sudoku_background));
        canvas_val.drawRect(0, 0, getWidth(), getHeight(), background);

        Paint black = new Paint();
        black.setColor(getResources().getColor(R.color.black));

        for (int i = this.boardSize ; i >= 0 ; i--) {
            canvas_val.drawLine(grid_len, i * grid_len, grid_len_19, i * grid_len, black);
            canvas_val.drawLine(i * grid_len, grid_len, i * grid_len, grid_len_19, black);
        }
    }
}
