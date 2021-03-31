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
import com.phwang.client.ClientFabricResultImport;
import com.phwang.core.utils.Abend;
import com.phwang.go.define.CommandDefine;
import com.phwang.go.define.IntentDefine;

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
        this.clientDExport().setupLink(my_name_val, password_val);
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

    public void doSoloSession(String session_setup_data_val) {
        this.debug(true, "doSoloSession", "data=" + session_setup_data_val);
        if (this.clientFabricInfo().linkIdStr() == null) {
            this.bindDClient_.sendBroadcastMessage(
                    IntentDefine.BIND_SERVICE,
                    CommandDefine.FABRIC_COMMAND_SETUP_SESSION_STR,
                    ClientFabricResultImport.FAIL_LINK_NOT_EXIST,
                    null);

            return;
        }
        this.clientDExport().soloSession(session_setup_data_val);
    }

    public void doSetupSession(String his_name_val, String session_setup_data_val) {
        this.debug(true, "doSetupSession", "data=" + session_setup_data_val);
        if (this.clientFabricInfo().linkIdStr() == null) {
            this.bindDClient_.sendBroadcastMessage(
                    IntentDefine.BIND_SERVICE,
                    CommandDefine.FABRIC_COMMAND_SETUP_SESSION_STR,
                    ClientFabricResultImport.FAIL_LINK_NOT_EXIST,
                    null);

            return;
        }
        this.clientDExport().setupSession(his_name_val, session_setup_data_val);
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
