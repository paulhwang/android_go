/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */
package com.phwang.bind;

import android.content.Context;

import com.phwang.go.MainActivity;

public class BindMain {
    private BindUClient bindUClient_;

    public BindUClient bindUClient() { return this.bindUClient_; }

    public BindMain(Context application_context_val) {
        com.phwang.core.utils.Abend.initAbend(new BindAbend());
        new com.phwang.core.root.CoreRoot();
        this.bindUClient_ = new BindUClient(application_context_val);
    }
}
