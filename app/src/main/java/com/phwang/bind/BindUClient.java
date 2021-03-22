/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.bind;

import com.phwang.client.ClientDExport;
import com.phwang.client.ClientFabricInfo;
import com.phwang.core.utils.Abend;

public class BindUClient {
    private String objectName() {return "BindUClient";}

    private BindDClient bindDClient_;

    public BindDClient bindClient() { return this.bindDClient_; }
    private ClientDExport clientDExport() { return this.bindDClient_.clientDExport(); }
    private ClientFabricInfo clientFabricInfo() { return this.bindDClient_.clientFabricInfo();}

    public BindUClient() {
        this.bindDClient_ = new BindDClient();
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
        this.debug(false, "doSetupSession", "doSetupSession");
        this.clientFabricInfo().setMyName(his_name_val);
        this.clientDExport().setupLink();
    }

    public void doSetupSession2() {
        this.debug(false, "doSetupSession2", "doSetupSession2");

    }

    public void doSetupSession3() {
        this.debug(false, "doSetupSession3", "doSetupSession3");

    }

    public void doPutSessionData(String session_put_data_val) {
        this.debug(false, "doPutSessionData", "doPutSessionData");
    }

    public void doGetSessionData() {
        this.debug(false, "doGetSessionData", "doGetSessionData");
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
