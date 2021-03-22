/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */
package com.phwang.bind;

public class BindMain {
    private BindClient bindClient_;

    public BindClient bindClient() { return this.bindClient_; }

    public BindMain() {
        com.phwang.core.utils.Abend.initAbend(new BindAbend());
        new com.phwang.core.root.CoreRoot();
        this.bindClient_ = new BindClient();
    }
}
