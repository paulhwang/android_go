/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */
package com.phwang.go.bind;

import android.content.Context;

import com.phwang.core.utils.abend.Abend;

public class BindMain {
    private BindUClient bindUClient_;

    public BindUClient bindUClient() { return this.bindUClient_; }

    public BindMain(Context application_context_val) {
        Abend.initAbend(new BindAbend());
        new com.phwang.core.root.CoreRoot();
        this.bindUClient_ = new BindUClient(application_context_val);
    }
}
