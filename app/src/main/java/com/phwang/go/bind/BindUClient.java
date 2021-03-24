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
import com.phwang.core.utils.Abend;

public class BindUClient {
    private String objectName() {return "BindUClient";}

    private Context applicationContext_;
    private BindDClient bindDClient_;

    public BindDClient bindClient() { return this.bindDClient_; }
    private ClientDExport clientDExport() { return this.bindDClient_.clientDExport(); }
    private ClientFabricInfo clientFabricInfo() { return this.bindDClient_.clientFabricInfo();}

    public BindUClient(Context application_context_val) {
        this.applicationContext_ = application_context_val;
        this.bindDClient_ = new BindDClient(applicationContext_);
    }

    public void doSetupLink(String my_name_val, String password_val) {
        this.debug(false, "doSetupLink", "doSetupLink");
        this.clientFabricInfo().setMyName(my_name_val);
        this.clientFabricInfo().setPassword(password_val);
        this.clientDExport().setupLink();
    }

    public void doGetRemoveLink() {
        this.debug(false, "doGetRemoveLink", "doGetRemoveLink");

    }

    public void doGetLinkData() {
        this.debug(false, "doGetLinkData", "doGetLinkData");

    }

    public void doGetNameList() {
        this.debug(false, "doGetNameList", "doGetNameList");

    }

    public void doSetupSession(String his_name_val, String session_setup_data_val) {
        this.debug(true, "doSetupSession", "doSetupSession");
        this.clientFabricInfo().setHisName(his_name_val);
        this.clientDExport().setupSession();
    }

    public void doSetupSession2() {
        this.debug(false, "doSetupSession2", "doSetupSession2");

    }

    public void doSetupSession3() {
        this.debug(true, "doSetupSession3", "doSetupSession3");
        this.clientDExport().setupSession3();

    }

    public void doPutSessionData(String data_str_val) {
        this.debug(true, "doPutSessionData() data=", data_str_val);
        this.clientDExport().putSessionData(data_str_val);;
    }

    public void doGetSessionData() {
        this.debug(false, "doGetSessionData", "doGetSessionData");
        this.clientDExport().getSessionData();;
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
