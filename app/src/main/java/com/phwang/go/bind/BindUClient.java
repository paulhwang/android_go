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

    public void doLogin(String my_name_val, String password_val) {
        this.debug(true, "doLogin", "doLogin");
        this.clientDExport().doLogin(my_name_val, password_val);
    }

    public void doLogout() {
        this.debug(true, "doLogout", "doLogout");
        this.clientDExport().doLogout();
    }

    public void doRegister(String my_name_val, String password_val, String email_val) {
        this.debug(true, "doRegister", "doRegister");
        this.clientDExport().doRegister(my_name_val, password_val, email_val);
    }

    public void doGetGroups() {
        this.debug(false, "doGetGroups", "doGetGroups");
        this.clientDExport().doGetGroups();
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

    public void setupSoloSession(String data_str_val) {
        this.debug(true, "setupSoloSession", "data=" + data_str_val);
        if (this.clientFabricInfo().linkIdStr() == null) {
            this.bindDClient_.sendBroadcastMessage(
                    IntentDefine.BIND_SERVICE,
                    FabricCommands.FABRIC_COMMAND_SOLO_SESSION_STR,
                    FabricResultExport.LINK_NOT_EXIST_STR,
                    null);
            return;
        }
        this.clientDExport().setupSoloSession(data_str_val);
    }

    public void setupHeadSession(String data_str_val) {
        this.debug(true, "setupHeadSession", "data=" + data_str_val);
        if (this.clientFabricInfo().linkIdStr() == null) {
            this.bindDClient_.sendBroadcastMessage(
                    IntentDefine.BIND_SERVICE,
                    FabricCommands.FABRIC_COMMAND_HEAD_SESSION_STR,
                    FabricResultExport.LINK_NOT_EXIST_STR,
                    null);
            return;
        }
        this.clientDExport().setupHeadSession(data_str_val);
    }

    public void setupPeerSession(String data_str_val) {
        this.debug(true, "setupPeerSession", "data=" + data_str_val);
        if (this.clientFabricInfo().linkIdStr() == null) {
            this.bindDClient_.sendBroadcastMessage(
                    IntentDefine.BIND_SERVICE,
                    FabricCommands.FABRIC_COMMAND_PEER_SESSION_STR,
                    FabricResultExport.LINK_NOT_EXIST_STR,
                    null);
            return;
        }
        this.clientDExport().setupPeerSession(data_str_val);
    }

    public void setupJoinSession(String data_str_val) {
        this.debug(true, "setupJoinSession", "data=" + data_str_val);
        if (this.clientFabricInfo().linkIdStr() == null) {
            this.bindDClient_.sendBroadcastMessage(
                    IntentDefine.BIND_SERVICE,
                    FabricCommands.FABRIC_COMMAND_JOIN_SESSION_STR,
                    FabricResultExport.LINK_NOT_EXIST_STR,
                    null);
            return;
        }
        this.clientDExport().setupJoinSession(data_str_val);
    }

    public void doSetupSession(String his_name_val, String session_setup_data_val) {
        this.debug(true, "doSetupSession", "data=" + session_setup_data_val);
        if (this.clientFabricInfo().linkIdStr() == null) {
            this.bindDClient_.sendBroadcastMessage(
                    IntentDefine.BIND_SERVICE,
                    FabricCommands.FABRIC_COMMAND_SETUP_SESSION_STR,
                    FabricResultExport.LINK_NOT_EXIST_STR,
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

    public void deleteSession(String link_id_str_val, String session_id_str_val) {
        this.debug(true, "deleteSession() session_id=", session_id_str_val);
        this.clientDExport().deleteSession(link_id_str_val, session_id_str_val);;
    }

    public void putSessionData(String link_id_str_val, String session_id_str_val, String data_str_val) {
        this.debug(true, "putSessionData() data=", data_str_val);
        this.clientDExport().putSessionData(link_id_str_val, session_id_str_val, data_str_val);;
    }

    public void getSessionData(String link_id_str_val, String session_id_str_val) {
        this.debug(true, "getSessionData", "session_id=" + session_id_str_val);
        this.clientDExport().getSessionData(link_id_str_val, session_id_str_val);;
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
