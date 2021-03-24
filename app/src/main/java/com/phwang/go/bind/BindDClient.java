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

import com.phwang.core.utils.Abend;
import com.phwang.client.ClientDImportInt;
import com.phwang.client.ClientRoot;
import com.phwang.client.ClientDExport;
import com.phwang.client.ClientFabricInfo;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.CommandDefine;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.define.ResultDefine;

public class BindDClient implements ClientDImportInt {
    private String objectName() {return "BindDClient";}

    private Context applicationContext_;
    private ClientRoot clientRoot_;

    protected ClientDExport clientDExport() { return this.clientRoot_.clientDExport(); }
    protected ClientFabricInfo clientFabricInfo() { return this.clientRoot_.clientFabricInfo();}

    public BindDClient(Context application_context_val) {
        this.applicationContext_ = application_context_val;
        this.debug(false, "BindDClient", "init");
        this.clientRoot_ = new ClientRoot(this);
    }

    private void sendBroadcastMessage(String target_val, String command_val, String result_val, String data_val) {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.GO_CLIENT);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_RESPONSE);
        intent.putExtra(BundleIndexDefine.COMMAND, command_val);
        intent.putExtra(BundleIndexDefine.RESULT, result_val);
        intent.putExtra(BundleIndexDefine.DATA, data_val);
        intent.setAction(target_val);
        this.applicationContext_.sendBroadcast(intent);
    }

    public void handleSetupLinkResponse() {
        this.debug(true, "handleSetupLinkResponse", "LinkId=" + this.clientFabricInfo().linkIdStr());
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                CommandDefine.FABRIC_COMMAND_SETUP_LINK_STR,
                ResultDefine.SUCCEED,
                null);
    }

    public void handleRemoveLinkResponse() {
        this.debug(true, "handleRemoveLinkResponse", "LinkId=" + this.clientFabricInfo().linkIdStr());

    }

    public void handleGetLinkDataResponse() {
        this.debug(true, "handleGetLinkDataResponse", "handleGetLinkDataResponse");

    }

    public void handleGetNameListResponse() {
        this.debug(true, "handleGetNameListResponse", "handleGetNameListResponse");

    }

    public void handleSetupSessionResponse() {
        this.debug(true, "handleSetupSessionResponse", "SessionId=" + this.clientFabricInfo().sessionIdStr());
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                CommandDefine.FABRIC_COMMAND_SETUP_SESSION_STR,
                ResultDefine.SUCCEED,
                null);
    }

    public void handleSetupSession2Response() {
        this.debug(true, "handleSetupSession2Response", "handleSetupSession2Response");

    }

    public void handleSetupSession3Response() {
        this.debug(true, "handleSetupSession3Response", "SessionId=" + this.clientFabricInfo().sessionIdStr());
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                CommandDefine.FABRIC_COMMAND_SETUP_SESSION3_STR,
                ResultDefine.SUCCEED,
                null);
    }

    public void handlePutSessionDataResponse() {
        this.debug(true, "handlePutSessionDataResponse", "handlePutSessionDataResponse");
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                CommandDefine.FABRIC_COMMAND_PUT_SESSION_DATA_STR,
                ResultDefine.SUCCEED,
                null);
    }

    public void handleGetSessionDataResponse(String data_val) {
        this.debug(true, "handleGetSessionDataResponse", "handleGetSessionDataResponse");
        this.sendBroadcastMessage(
                IntentDefine.BIND_SERVICE,
                CommandDefine.FABRIC_COMMAND_GET_SESSION_DATA_STR,
                ResultDefine.SUCCEED,
                data_val);

    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
