/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */
package com.phwang.go.bind;

import android.content.Context;

import com.phwang.client.ClientDExport;
import com.phwang.client.ClientFabricInfo;
import com.phwang.core.utils.abend.Abend;

public class BindMain {
    private BindUClient bindUClient_;
    private BindDClient bindDClient_;

    public BindUClient bindUClient() { return this.bindUClient_; }
    public BindDClient bindDClient() { return this.bindUClient_.bindDClient(); }
    public ClientDExport clientDExport() { return this.bindDClient().clientDExport(); }
    public ClientFabricInfo clientFabricInfo() { return this.bindDClient().clientFabricInfo();}

    public BindMain(Context application_context_val) {
        Abend.initAbend(new BindAbend());
        new com.phwang.core.root.CoreRoot();
        this.bindUClient_ = new BindUClient(application_context_val);
        this.bindDClient_ = new BindDClient(application_context_val);
    }
}
