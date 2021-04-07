/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.fabric;

import com.phwang.core.protocols.theme.ThemeCommands;
import com.phwang.core.protocols.theme.ThemeData;
import com.phwang.core.utils.encoders.Encoders;
import com.phwang.core.utils.listmgr.ListEntry;

public class FabricDParser {
    private String objectName() {return "FabricDParser";}

    private FabricRoot fabricRoot_;

    protected FabricRoot fabricRoot() { return this.fabricRoot_;}
    protected FabricGroupMgr groupMgr() { return this.fabricRoot().groupMgr(); }

    protected FabricDParser(FabricRoot root_val) {
        this.fabricRoot_ = root_val;
    }

    protected void parseInputPacket(String theme_data_val) {
        this.debug(false, "parseInputPacket", theme_data_val);
        ThemeData theme_data = new ThemeData(theme_data_val);
        char command = theme_data.command();

        switch (command) {
            case ThemeCommands.FABRIC_THEME_RESPOND_SETUP_ROOM:
                this.processSetupRoomResponse(theme_data);
                return;

            case ThemeCommands.FABRIC_THEME_RESPOND_PUT_ROOM_DATA:
                this.processPutRoomDataResponse(theme_data);
                return;

            default:
                this.abend("exportedParseFunction", theme_data_val);
                return;
        }
    }
    
    private void processSetupRoomResponse(ThemeData theme_data_val) {
        String group_id_str = theme_data_val.groupIdStr();
        String room_id_str = theme_data_val.roomIdStr();
        this.debug(false, "processSetupRoomResponse", "group_id_str=" + group_id_str);
        this.debug(false, "processSetupRoomResponse", "room_id_str=" + room_id_str);

        FabricGroup group = this.groupMgr().getGroupByIdStr(group_id_str);
        if (group != null) {
            group.setRoomIdStr(room_id_str);
            
            int max_index = group.gSessionMgr().listMgr().maxIndex();
            ListEntry[] list_entry_array = group.gSessionMgr().listMgr().entryArray();
            for (int i = max_index; i >= 0; i--) {
                if (list_entry_array[i] != null) {
                    FabricSession session = (FabricSession) list_entry_array[i].data();
                    session.link().setPendingSessionSetup3(session.browserThemeIdStr(), session.lSessionIdStr(), "");
                }
            }
            
            int session_array_size = group.getSessionArraySize();
            Object[] session_array = group.getSessionArray();
            //group->setSessionTableArray((SessionClass**)phwangArrayMgrGetArrayTable(group->sessionArrayMgr(), &session_array_size));
            //printf("++++++++++++++++++++++++++++++++++++++++++++%d\n", session_array_size);
            for (int i = 0; i < session_array_size; i++) {
            	FabricSession session = (FabricSession) session_array[i];
                session.link().setPendingSessionSetup3(session.browserThemeIdStr(), session.lSessionIdStr(), "");
            }
        }
    }
    
    private void processPutRoomDataResponse(ThemeData theme_data_val) {
        String group_id_str = theme_data_val.groupIdStr();
        this.debug(false, "processPutRoomDataResponse", "group_id_str=" + group_id_str);
        String input_data = theme_data_val.stringList(0);
        this.debug(false, "processPutRoomDataResponse", "input_data=" + input_data);

        FabricGroup group = this.groupMgr().getGroupByIdStr(group_id_str);
        if (group != null) {
            int max_index = group.gSessionMgr().listMgr().maxIndex();
            ListEntry[] list_entry_array = group.gSessionMgr().listMgr().entryArray();
            for (int i = max_index; i >= 0; i--) {
                if (list_entry_array[i] != null) {
                    FabricSession session = (FabricSession) list_entry_array[i].data();
                    session.enqueuePendingDownLinkData(input_data);
                }
            }
        	
            int session_array_size = group.getSessionArraySize();
            Object[] session_array = group.getSessionArray();
            for (int i = 0; i < session_array_size; i++) {
            	FabricSession session = (FabricSession)session_array[i];
                session.enqueuePendingDownLinkData(input_data);
            }
        }
    }
    
    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.fabricRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { this.fabricRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
