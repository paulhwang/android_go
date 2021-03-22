/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.bind;

import android.content.Intent;
import com.phwang.core.utils.Abend;
import com.phwang.client.ClientDImportInt;
import com.phwang.client.ClientRoot;
import com.phwang.client.ClientDExport;
import com.phwang.client.ClientFabricInfo;

public class BindClient implements ClientDImportInt {
    private String objectName() {return "BindClient";}

    private ClientRoot clientRoot_;

    protected ClientDExport clientDExport() { return this.clientRoot_.clientDExport(); }
    protected ClientFabricInfo clientFabricInfo() { return this.clientRoot_.clientFabricInfo();}

    public BindClient() {
        this.debug(false, "BindClient", "init");
        this.clientRoot_ = new ClientRoot(this);
    }

    public void handleSetupLinkResponse() {
        this.debug(true, "handleSetupLinkResponse", "handleSetupLinkResponse");
        //Intent intent = new Intent();
        //intent.putExtra("LinkSetupResponse", "Succeed");
        //intent.setAction("sss");
        //sendBroadcast(intent);

    }

    public void handleRemoveLinkResponse() {

    }

    public void handleGetLinkDataResponse() {

    }

    public void handleGetNameListResponse() {

    }

    public void handleSetupSessionResponse() {

    }

    public void handleSetupSession2Response() {

    }

    public void handleSetupSession3Response() {

    }

    public void handlePutSessionDataResponse() {
    }

    public void handleGetSessionDataResponse() {

    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
