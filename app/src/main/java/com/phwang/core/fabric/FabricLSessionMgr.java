/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.fabric;

import com.phwang.core.utils.abend.Abend;
import com.phwang.core.utils.listmgr.ListMgr;
import com.phwang.core.utils.listmgr.ListEntry;

public class FabricLSessionMgr {
    protected static String objectName() {return "FabricLSessionMgr";}
    
    protected static final int FABRIC_L_SESSION_ID_SIZE_ = 4;
    protected static final int FABRIC_L_SESSION_ID_SIZE = FABRIC_L_SESSION_ID_SIZE_ * 2 + 2;

	private static final int L_SESSION_LIST_MGR_ARRAY_SIZE = 8;
    private static final int FIRST_L_SESSION_ID = 2000;

    private FabricLink link_;
    private ListMgr listMgr_;

    private FabricLink link() { return this.link_; }
    protected ListMgr listMgr() { return this.listMgr_; }
    protected int getSessionArrayMaxIndex() { return this.listMgr_.maxIndex(); }
    protected ListEntry[] getSessionArrayEntryTable() { return this.listMgr().entryArray(); }

    protected FabricLSessionMgr(FabricLink link_val) {
        this.debug(false, "FabricLSessionMgr", "init start");
        
        this.link_ = link_val;
        this.listMgr_ = new ListMgr(FABRIC_L_SESSION_ID_SIZE_, L_SESSION_LIST_MGR_ARRAY_SIZE, this.objectName(), FIRST_L_SESSION_ID);
    }

    protected FabricSession mallocSession() {
    	FabricSession session = new FabricSession(this.link());
    	ListEntry list_entry = this.listMgr().mallocEntry(session);
        session.bindListEntry(list_entry, this.objectName());
        return session;
    }

    protected void freeSession(FabricSession session_val) {
        ListEntry list_entry = session_val.lListEntry();
        session_val.unBindListEntry(this.objectName());
        this.listMgr_.freeEntry(list_entry);
    }

    protected FabricSession getSessionByIdStr(String session_id_str_val) {
    	ListEntry list_entry = this.listMgr().getEntryByIdStr(session_id_str_val);
        if (list_entry == null) {
            return null;
        }
        return (FabricSession)list_entry.data();
    }
    
    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
