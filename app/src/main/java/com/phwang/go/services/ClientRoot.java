/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.services;

import com.phwang.core.utils.abend.Abend;
import com.phwang.core.utils.binder.Binder;
import com.phwang.core.utils.threadmgr.ThreadMgr;

public class ClientRoot {
    private String objectName() {return "ClientRoot";}

    private ClientService clientService_;
    private ThreadMgr threadMgr_;
    private ClientUBinder clientUBinder_;
    private ClientDParser clientDParser_;
    private ClientUParser clientUParser_;

    protected ThreadMgr threadMgr() { return this.threadMgr_; }
    protected ClientService clientService() { return this.clientService_; };
    protected ClientUBinder clientUBinder() { return this.clientUBinder_; }
    protected ClientDParser clientDParser() { return this.clientDParser_; }
    protected ClientUParser clientUParser() { return this.clientUParser_; };
    protected Binder uBinder() { return this.clientUBinder_.uBinder(); }

    public ClientRoot(ClientService client_service_val) {
        this.debug(false, "ClientRoot", "init start");

        this.clientService_ = client_service_val;

        Abend.initAbend(new ClientAbend());
        new com.phwang.core.root.CoreRoot();

        this.threadMgr_ = new ThreadMgr();
        this.clientUBinder_ = new ClientUBinder(this);
        this.clientDParser_ = new ClientDParser(this);
        this.clientUParser_ = new ClientUParser(this);

        this.clientUBinder_.startThreads();
	}
    
    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.logIt(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { this.abendIt(this.objectName() + "." + s0 + "()", s1); }

    private Boolean debug_on = true;
    protected void logIt(String s0, String s1) { if (this.debug_on) Abend.log(s0, s1); }
    protected void abendIt(String s0, String s1) { if (this.debug_on) Abend.abend(s0, s1); }
}
