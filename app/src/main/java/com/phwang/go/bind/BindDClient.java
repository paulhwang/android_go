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

    public void handleSetupLinkResponse() {
        this.debug(true, "handleSetupLinkResponse", "LinkId=" + this.clientFabricInfo().linkIdStr());
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.COMMAND, CommandDefine.FABRIC_COMMAND_SETUP_LINK_STR);
        intent.putExtra(BundleIndexDefine.RESULT, ResultDefine.SUCCEED);
        intent.setAction(IntentDefine.MAIN_ACTIVITY);
        this.applicationContext_.sendBroadcast(intent);
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
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.COMMAND, CommandDefine.FABRIC_COMMAND_SETUP_SESSION_STR);
        intent.putExtra(BundleIndexDefine.RESULT, ResultDefine.SUCCEED);
        intent.setAction(IntentDefine.MAIN_ACTIVITY);
        this.applicationContext_.sendBroadcast(intent);
    }

    public void handleSetupSession2Response() {
        this.debug(true, "handleSetupSession2Response", "handleSetupSession2Response");

    }

    public void handleSetupSession3Response() {
        this.debug(true, "handleSetupSession3Response", "SessionId=" + this.clientFabricInfo().sessionIdStr());
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.COMMAND, CommandDefine.FABRIC_COMMAND_SETUP_SESSION3_STR);
        intent.putExtra(BundleIndexDefine.RESULT, ResultDefine.SUCCEED);
        intent.setAction(IntentDefine.MAIN_ACTIVITY);
        this.applicationContext_.sendBroadcast(intent);
    }

    public void handlePutSessionDataResponse() {
        this.debug(true, "handlePutSessionDataResponse", "handlePutSessionDataResponse");
    }

    public void handleGetSessionDataResponse() {
        this.debug(true, "handleGetSessionDataResponse", "handleGetSessionDataResponse");

    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
