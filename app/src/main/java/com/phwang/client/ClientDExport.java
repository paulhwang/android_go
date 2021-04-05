/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.client;

import com.phwang.core.fabric.FabricClients;
import com.phwang.core.fabric.FabricCommands;
import com.phwang.core.utils.binder.Binder;
import com.phwang.core.utils.encoders.Encoders;
import com.phwang.go.global.GlobalData;

public class ClientDExport {
    private String objectName() {return "ClientDExport";}
    
    private ClientRoot clientRoot_;
    
    private ClientRoot clientRoot() { return this.clientRoot_; }
    private ClientUBinder clientUBinder() { return this.clientRoot_.clientUBinder(); }
    private Binder uBinder() { return this.clientUBinder().uBinder(); }

    protected ClientDExport(ClientRoot root_val) {
        this.debug(false, "ClientDExport", "init start");
        
    	this.clientRoot_ = root_val;
    }

    public void transmitToFabric(String data_str_val) {
    	this.debug(true, "transmitToFabric", "data_str_val=" + data_str_val);
       	this.uBinder().transmitStringData(data_str_val);
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.clientRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { this.clientRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
