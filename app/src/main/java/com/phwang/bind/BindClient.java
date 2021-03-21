/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.bind;

import com.phwang.client.ClientDImportInt;
import com.phwang.client.ClientRoot;
import com.phwang.client.ClientDExport;
import com.phwang.client.ClientFabricInfo;

public class BindClient implements ClientDImportInt {
    private ClientRoot clientRoot_;

    private ClientDExport clientDExport() { return this.clientRoot_.clientDExport(); }
    private ClientFabricInfo clientFabricInfo() { return this.clientRoot_.clientFabricInfo();}

    public BindClient() {
        this.clientRoot_ = new ClientRoot(this);

    }

    public void handleSetupLinkResponse() {

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
}
