/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.theme;

import com.phwang.core.utils.listmgr.ListEntry;
import com.phwang.core.utils.listmgr.ListMgr;

public class ThemeRoomMgr {
    private String objectName() {return "ThemeRoomMgr";}

    public static final int THEME_ROOM_ID_SIZE_ = 4;
    public static final int THEME_ROOM_ID_SIZE = THEME_ROOM_ID_SIZE_ * 2 + 2;
    
    private static final int LIST_MGR_ARRAY_SIZE = 128;
    private static final int FIRST_ROOM_ID = 7000;

    private ThemeRoot themeRoot_;
    private ListMgr listMgr_;
    
    public ThemeRoot themeRoot() { return this.themeRoot_; }

    public ThemeRoomMgr(ThemeRoot theme_root_object_val) {
        this.debug(false, "ThemeRoomMgr", "init start");

        this.themeRoot_ = theme_root_object_val;
        this.listMgr_ = new ListMgr(THEME_ROOM_ID_SIZE_, LIST_MGR_ARRAY_SIZE, this.objectName(), FIRST_ROOM_ID);
    }
    
    public ThemeRoom mallocRoom(String group_id_str_val) {
    	ThemeRoom room = new ThemeRoom(group_id_str_val);
    	ListEntry list_entry = this.listMgr_.mallocEntry(room);
        room.bindListEntry(list_entry, this.objectName());
        return room;
    }

    public void freeRoom(ThemeRoom room_val) {
        ListEntry list_entry = room_val.listEntry();
        room_val.unBindListEntry(this.objectName());
        this.listMgr_.freeEntry(list_entry);
    }

    public ThemeRoom getRoomByIdStr(String room_id_str_val) {
    	ListEntry list_entry = this.listMgr_.getEntryByIdStr(room_id_str_val);
        if (list_entry == null) {
        	this.abend("getRoomByIdStr", "null data");
            return null;
        }
        return (ThemeRoom)list_entry.data();
    }
    
    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.themeRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    public void abend(String s0, String s1) { this.themeRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
