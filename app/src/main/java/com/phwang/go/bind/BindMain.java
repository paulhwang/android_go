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
import com.phwang.go.services.BindService;

public class BindMain {
    private BindDClient bindDClient_;
    private BindService bindService_;

    public BindDClient bindDClient() { return this.bindDClient_; }

    public BindMain(BindService bind_service_val, Context application_context_val) {
        this.bindService_ = bind_service_val;
        Abend.initAbend(new BindAbend());
        new com.phwang.core.root.CoreRoot();
        this.bindDClient_ = new BindDClient(this.bindService_, application_context_val);
    }
}
