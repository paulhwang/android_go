/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.services;

import android.content.Context;
import android.content.Intent;

import com.phwang.core.fabric.FabricCommands;
import com.phwang.core.utils.abend.Abend;
import com.phwang.client.ClientDImportInt;
import com.phwang.client.ClientRoot;
import com.phwang.client.ClientDExport;
import com.phwang.client.ClientFabricInfo;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.services.BindService;

public class BindDClient {
    private String objectName() {
        return "BindDClient";
    }

    private BindService bindService_;
    private Context applicationContext_;


    public BindDClient(BindService bind_service_val, Context application_context_val) {
        this.bindService_ = bind_service_val;
        this.applicationContext_ = application_context_val;
        this.debug(false, "BindDClient", "init");
    }

    public void sendBroadcastMessage(String target_val, String command_val, String result_val, String data_pakage_val) {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_CLIENT);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_RESPONSE);
        intent.putExtra(BundleIndexDefine.COMMAND, command_val);
        intent.putExtra(BundleIndexDefine.RESULT, result_val);
        intent.putExtra(BundleIndexDefine.DATA_PACKAGE, data_pakage_val);
        intent.setAction(target_val);
        this.applicationContext_.sendBroadcast(intent);
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
