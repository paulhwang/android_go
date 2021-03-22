/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */
package com.phwang.bind;

public class BindMain {
    private BindUClient bindUClient_;
    private BindClient bindClient_;

    public BindUClient bindUClient() { return this.bindUClient_; }
    public BindClient bindClient() { return this.bindClient_; }

    public BindMain() {
        com.phwang.core.utils.Abend.initAbend(new BindAbend());
        new com.phwang.core.root.CoreRoot();
        this.bindUClient_ = new BindUClient();
        this.bindClient_ = new BindClient();
    }
}
