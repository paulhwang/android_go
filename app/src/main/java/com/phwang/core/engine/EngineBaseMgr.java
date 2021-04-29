/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.engine;

import com.phwang.core.utils.listmgr.ListMgr;
import com.phwang.core.utils.listmgr.ListEntry;

public class EngineBaseMgr {
    private String objectName() {return "EngineBaseMgr";}
    
    public static final int ENGINE_BASE_ID_SIZE_ = 4;
    public static final int ENGINE_BASE_ID_SIZE = ENGINE_BASE_ID_SIZE_ * 2 + 2;
    
    private static final int FIRST_BASE_ID = 9000;
    private static final int LIST_MGR_ARRAY_SIZE = 128;

    private EngineRoot engineRoot_;
    private ListMgr listMgr_;

    private EngineRoot engineRoot() { return this.engineRoot_; }
    public ListMgr ListMgr() { return this.listMgr_; }
  
    public EngineBaseMgr(EngineRoot root_val) {
        this.debug(false, "EngineBaseMgr", "init start");

        this.engineRoot_ = root_val;
        this.listMgr_ = new ListMgr(ENGINE_BASE_ID_SIZE_, LIST_MGR_ARRAY_SIZE, this.objectName(), FIRST_BASE_ID);
    }

    public EngineBase MallocGoBase(String room_id_val) {
    	EngineBase go_base = new EngineBase(room_id_val);
    	ListEntry list_entry = this.listMgr_.mallocEntry(go_base);
        go_base.bindListEntry(list_entry, this.objectName());
        return go_base;
    }

    public void FreeGoBase(EngineBase base_val) {
        ListEntry list_entry = base_val.listEntry();
        base_val.unBindListEntry(this.objectName());
        this.listMgr_.freeEntry(list_entry);
    }
    
    public EngineBase GetBaseByIdStr(String base_id_str_val) {
    	ListEntry list_entry = this.listMgr_.getEntryByIdStr(base_id_str_val);
        if (list_entry == null) {
            return null;
        }
        return (EngineBase)list_entry.data();
    }
    
    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.engineRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    public void abend(String s0, String s1) { this.engineRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
