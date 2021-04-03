/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.bind;

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

public class BindDClient implements ClientDImportInt {
    private String objectName() {
        return "BindDClient";
    }

    private Context applicationContext_;
    private ClientRoot clientRoot_;

    public ClientDExport clientDExport() {
        return this.clientRoot_.clientDExport();
    }

    protected ClientFabricInfo clientFabricInfo() {
        return this.clientRoot_.clientFabricInfo();
    }

    public BindDClient(Context application_context_val) {
        this.applicationContext_ = application_context_val;
        this.debug(false, "BindDClient", "init");
        this.clientRoot_ = new ClientRoot(this);
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

    public void handleRegisterResponse(String result_str_val) {
        this.debug(true, "handleRegisterResponse", "handleRegisterResponse");
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                FabricCommands.FABRIC_COMMAND_REGISTER_STR,
                result_str_val,
                null);
    }

    public void handleLoginResponse(String result_str_val) {
        this.debug(true, "handleSetupLinkResponse", "LinkId=" + this.clientFabricInfo().linkIdStr());
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                FabricCommands.FABRIC_COMMAND_LOGIN_STR,
                result_str_val,
                null);
    }

    public void handleLogoutResponse(String result_str_val) {
        this.debug(true, "handleLogoutResponse", "LinkId=" + this.clientFabricInfo().linkIdStr());
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                FabricCommands.FABRIC_COMMAND_LOGOUT_STR,
                result_str_val,
                null);
    }

    public void handleGetGroupsResponse(String result_str_val) {
        this.debug(true, "handleGetGroupsResponse", "LinkId=" + this.clientFabricInfo().linkIdStr());
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                FabricCommands.FABRIC_COMMAND_GET_GROUPS_STR,
                result_str_val,
                null);
    }

    public void handleGetLinkDataResponse(String result_str_val) {
        this.debug(true, "handleGetLinkDataResponse", "handleGetLinkDataResponse");
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                FabricCommands.FABRIC_COMMAND_GET_LINK_DATA_STR,
                result_str_val,
                null);
    }

    public void handleGetNameListResponse(String result_str_val) {
        this.debug(true, "handleGetNameListResponse", "handleGetNameListResponse");
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                FabricCommands.FABRIC_COMMAND_GET_NAME_LIST_STR,
                result_str_val,
                null);
    }

    public void handleSetupSoloSessionResponse(String result_str_val, String theme_str_val) {
        this.debug(true, "handleSoloSessionResponse", "theme_str_val=" + theme_str_val);
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                FabricCommands.FABRIC_COMMAND_SOLO_SESSION_STR,
                result_str_val,
                theme_str_val);
    }

    public void handleSetupHeadSessionResponse(String result_str_val, String theme_str_val) {
        this.debug(true, "handleSetupHeadSessionResponse", "theme_str_val=" + theme_str_val);
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                FabricCommands.FABRIC_COMMAND_HEAD_SESSION_STR,
                result_str_val,
                theme_str_val);
    }

    public void handleSetupPeerSessionResponse(String result_str_val, String theme_str_val) {
        this.debug(true, "handleSetupPeerSessionResponse", "theme_str_val=" + theme_str_val);
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                FabricCommands.FABRIC_COMMAND_PEER_SESSION_STR,
                result_str_val,
                theme_str_val);
    }

    public void handleSetupJoinSessionResponse(String result_str_val, String theme_str_val) {
        this.debug(true, "handleSetupJoinSessionResponse", "theme_str_val=" + theme_str_val);
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                FabricCommands.FABRIC_COMMAND_JOIN_SESSION_STR,
                result_str_val,
                theme_str_val);
    }

    public void handleSetupSessionResponse(String result_str_val) {
        this.debug(true, "handleSetupSessionResponse", "SessionId=" + this.clientFabricInfo().sessionIdStr());
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                FabricCommands.FABRIC_COMMAND_SETUP_SESSION_STR,
                result_str_val,
                null);
    }

    public void handleSetupSession2Response(String result_str_val) {
        this.debug(true, "handleSetupSession2Response", "handleSetupSession2Response");
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                FabricCommands.FABRIC_COMMAND_SETUP_SESSION2_STR,
                result_str_val,
                null);
    }

    public void handleSetupSession3Response(String result_str_val) {
        this.debug(true, "handleSetupSession3Response", "SessionId=" + this.clientFabricInfo().sessionIdStr());
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                FabricCommands.FABRIC_COMMAND_SETUP_SESSION3_STR,
                result_str_val,
                null);
    }

    public void handlePutSessionDataResponse(String result_str_val) {
        this.debug(true, "handlePutSessionDataResponse", "handlePutSessionDataResponse");
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                FabricCommands.FABRIC_COMMAND_PUT_SESSION_DATA_STR,
                result_str_val,
                null);
    }

    public void handleGetSessionDataResponse(String result_str_val, String data_package_val) {
        this.debug(true, "handleGetSessionDataResponse", "data_package=" + data_package_val);
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                FabricCommands.FABRIC_COMMAND_GET_SESSION_DATA_STR,
                result_str_val,
                data_package_val);
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
