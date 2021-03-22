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
import com.phwang.bind.BindUClient;
import com.phwang.go.MainActivity;

public class GoGame extends AppCompatActivity {
    private static final String TAG = "GoGame";
    private GoView goView;

    private BindUClient bindUClient() { return MainActivity.bindUClient(); };

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
        int x = ((int) event.getX() - this.goView.gridLen0 + this.goView.halfGridLen) / this.goView.gridLen;
        int y = ((int) event.getY() - this.goView.viewTop - this.goView.gridLen0 + this.goView.halfGridLen) / this.goView.gridLen;
        Log.d(TAG, "onTouchEvent: x=" + x + " y=" + y);
        switch( event.getAction() ) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:
                break;
        }
       return super.onTouchEvent(event);
    }
}
