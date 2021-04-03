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
import com.phwang.core.fabric.FabricCommands;
import com.phwang.core.fabric.FabricResultExport;
import com.phwang.core.utils.abend.Abend;
import com.phwang.go.define.IntentDefine;

public class BindUClient {
    private String objectName() {return "BindUClient";}

    private Context applicationContext_;
    private BindDClient bindDClient_;

    public BindDClient bindDClient() { return this.bindDClient_; }
    public ClientDExport clientDExport() { return this.bindDClient_.clientDExport(); }
    public ClientFabricInfo clientFabricInfo() { return this.bindDClient_.clientFabricInfo();}

    public BindUClient(Context application_context_val) {
        this.applicationContext_ = application_context_val;
        this.bindDClient_ = new BindDClient(applicationContext_);
    }


    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
