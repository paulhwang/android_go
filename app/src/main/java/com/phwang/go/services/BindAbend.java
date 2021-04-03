/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.services;

import com.phwang.core.utils.abend.AbendInt;
import android.util.Log;

public class BindAbend implements AbendInt {
    public void log(String str_val) {
        Log.e("***", str_val);
    }
}
