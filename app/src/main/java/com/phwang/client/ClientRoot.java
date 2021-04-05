/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.client;

import com.phwang.core.utils.abend.Abend;
import com.phwang.core.utils.threadmgr.ThreadMgr;
import com.phwang.go.services.BindService;

public class ClientRoot {
    private String objectName() {return "ClientRoot";}

    private BindService bindService_;
    private ThreadMgr threadMgr_;
    private ClientUBinder clientUBinder_;
    private ClientDExport clientDExport_;
    private ClientDParser clientDParser_;

    public BindService bindService() { return this.bindService_; };
    public ClientDExport clientDExport() { return this.clientDExport_; }
    public ThreadMgr threadMgr() { return this.threadMgr_; }
    public ClientUBinder clientUBinder() { return this.clientUBinder_; }
    public ClientDParser clientDParser() { return this.clientDParser_; }
    
    public ClientRoot(BindService bind_service_val) {
        this.debug(false, "ClientRoot", "init start");

        this.bindService_ = bind_service_val;
        this.threadMgr_ = new ThreadMgr();
        this.clientUBinder_ = new ClientUBinder(this);
        this.clientDExport_ = new ClientDExport(this);
        this.clientDParser_ = new ClientDParser(this);
        
        this.clientUBinder_.startThreads();
	}
    
    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.logIt(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { this.abendIt(this.objectName() + "." + s0 + "()", s1); }

    private Boolean debug_on = true;
    protected void logIt(String s0, String s1) { if (this.debug_on) Abend.log(s0, s1); }
    protected void abendIt(String s0, String s1) { if (this.debug_on) Abend.abend(s0, s1); }
}
