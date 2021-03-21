/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.bind;

import com.phwang.core.utils.AbendInterface;
import android.util.Log;

public class BindAbend implements AbendInterface {
    public void log(String str_val) {
        Log.e("***", str_val);
    }
}
