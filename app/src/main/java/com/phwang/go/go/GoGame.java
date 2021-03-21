/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class GoGame extends AppCompatActivity {
    private static final String TAG = "GoGame";

    private GoView goView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        this.goView = new GoView(this);
        setContentView(this.goView);
        this.goView.requestFocus();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        Log.d(TAG, "*****onTouchEvent" + event.getAction() + " " + x + " " + y);

        //touchX = event.getX();       // 觸控的 X 軸位置
        //touchY = event.getY() - 50;  // 觸控的 Y 軸位置

        // 判斷觸控動作
        switch( event.getAction() ) {

            case MotionEvent.ACTION_DOWN:  // 按下
                /*
                // 設定 TextView 內容, 大小, 位置
                tv.setText("X: " + touchX + ", Y: " + touchY + ", 按下");
                tv.setLayoutParams( new AbsoluteLayout.LayoutParams( tvWidth
                        , tvHeight
                        , (int)touchX
                        , (int)touchY
                ));
                */
                break;

            case MotionEvent.ACTION_MOVE:  // 拖曳移動
                /*
                // 設定 TextView 內容, 大小, 位置
                tv.setText("X: " + touchX + ", Y: " + touchY + ", 拖曳移動");
                tv.setLayoutParams( new AbsoluteLayout.LayoutParams( tvWidth
                        , tvHeight
                        , (int)touchX
                        , (int)touchY
                ));

                 */
                break;

            case MotionEvent.ACTION_UP:  // 放開
                /*
                // 設定 TextView 內容
                tv.setText("X: " + touchX + ", Y: " + touchY + ", 放開");
                */
                break;
        }
       return super.onTouchEvent(event);
    }
}
