/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.phwang.go.R;

public class About extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstancesState) {
       super.onCreate(savedInstancesState);
       setContentView(R.layout.about);
    }
}
