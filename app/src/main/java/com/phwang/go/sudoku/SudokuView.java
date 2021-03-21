/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.util.Log;

import com.phwang.go.R;
import com.phwang.go.sudoku.SudokuGame;

public class SudokuView  extends View {
    private static final String TAG = "SudokuView";
    private final SudokuGame sudokuGame;

    public SudokuView(Context context_val) {
        super(context_val);
        this.sudokuGame = (SudokuGame) context_val;
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    private float width;
    private float height;
    private int selX;
    private int selY;
    private final Rect selRect = new Rect();

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(TAG, "onSizeChanged");

        this.width = w / 9f;
        this.height = h / 9f;
        this.getRect(this.selX, this.selY, this.selRect);
        Log.d(TAG,"onSizeChanged: width=" + this.width + ", height=" + this.height);
        Log.d(TAG,"onSizeChanged: width=" + getWidth() + ", height=" + getHeight());
        super.onSizeChanged(w, h, oldw, oldh);

        //setWillNotDraw(false);
        //setWillNotCacheDrawing(false);
        //nvalidate();

    }

    private void getRect(int x_val, int y_val, Rect rect_val) {
        rect_val.set((int) (x_val * this.width), (int) (y_val * this.height), (int) (x_val * this.width + this.width), (int) (y_val * this.height + this.height));
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
        //setWillNotDraw(false);
        //setWillNotCacheDrawing(false);

        //invalidate();
    }

    private void drawBoard(Canvas canvas_val) {
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.sudoku_background));
        canvas_val.drawRect(0, 0, getWidth(), getHeight(), background);

        Paint dark = new Paint();
        dark.setColor(getResources().getColor(R.color.sudoku_dark));

        Paint hilite = new Paint();
        hilite.setColor(getResources().getColor(R.color.sudoku_hilite));

        Paint light = new Paint();
        light.setColor(getResources().getColor(R.color.sudoku_light));

        for (int i = 0; i < 9; i++) {
            canvas_val.drawLine(0, i * this.height, getWidth(), i * this.height, light);
            canvas_val.drawLine(0, i * this.height + 1, getWidth(), i * this.height + 1, hilite);
            canvas_val.drawLine(i * this.width, 0, i * this.width, getHeight(), light);
            canvas_val.drawLine(i * this.width + 1, 0, i * this.width + 1, getHeight(), hilite);
        }

        for (int i = 0; i < 9; i ++) {
            if (i %3 != 0)
                continue;
            canvas_val.drawLine(0, i * this.height, getWidth(), i * this.height, dark);
            canvas_val.drawLine(0, i * this.height + 1, getWidth(), i * this.height + 1, hilite);
            canvas_val.drawLine(i * this.width, 0, i * this.width, getHeight(), dark);
            canvas_val.drawLine(i * this.width + 1, 0, i * this.width + 1, getHeight(), hilite);

        }
    }
}
