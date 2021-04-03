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
    private BindService bindService_;


    public BindMain(BindService bind_service_val, Context application_context_val) {
        this.bindService_ = bind_service_val;
    }
}
