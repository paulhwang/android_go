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

    private ClientDExport clientDExport() { return this.clientRoot_.clientDExport(); }
    private ClientFabricInfo clientFabricInfo() { return this.clientRoot_.clientFabricInfo();}

    public BindClient() {
        this.debug(false, "BindClient", "init");
        this.clientRoot_ = new ClientRoot(this);
    }

    public void doSetupLink(String my_name_val, String password_val) {
        this.debug(false, "doSetupLink", "doSetupLink");
        this.clientFabricInfo().setMyName(my_name_val);
        this.clientFabricInfo().setPassword(password_val);
        this.clientDExport().setupLink();
    }

    public void handleSetupLinkResponse() {
        this.debug(true, "handleSetupLinkResponse", "handleSetupLinkResponse");
        //Intent intent = new Intent();
        //intent.putExtra("LinkSetupResponse", "Succeed");
        //intent.setAction("sss");
        //sendBroadcast(intent);

    }

    public void doGetRemoveLink() {

    }

    public void handleRemoveLinkResponse() {

    }

    public void doGetLinkData() {

    }

    public void handleGetLinkDataResponse() {

    }

    public void doGetNameList() {

    }

    public void handleGetNameListResponse() {

    }

    public void doSetupSession(String his_name_val, String session_setup_data_val) {
        this.debug(false, "doSetupLink", "doSetupLink");
        this.clientFabricInfo().setMyName(his_name_val);
        this.clientDExport().setupLink();
    }

    public void handleSetupSessionResponse() {

    }

    public void doSetupSession2() {

    }

    public void handleSetupSession2Response() {

    }

    public void doSetupSession3() {

    }

    public void handleSetupSession3Response() {

    }

    public void doPutSessionData(String session_put_data_val) {
   }

    public void handlePutSessionDataResponse() {
    }

    public void doGetSessionData() {
    }

    public void handleGetSessionDataResponse() {

    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
