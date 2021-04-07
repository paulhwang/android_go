/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.theme;

//import com.phwang.core.utils.*;

import com.phwang.core.protocols.engine.EngineCommands;
import com.phwang.core.protocols.engine.EngineData;
import com.phwang.core.protocols.theme.ThemeCommands;
import com.phwang.core.protocols.theme.ThemeData;
import com.phwang.core.protocols.theme.ThemeResults;
import com.phwang.core.utils.encoders.Encoders;

public class ThemeUParser {
    private static String objectName() {return "ThemeUParser";}
    
    ThemeRoot themeRoot_;

    public ThemeRoot themeRoot() { return this.themeRoot_; }
    public ThemeUBinder themeUBinder() { return this.themeRoot().themeUBinder(); }
    public ThemeRoomMgr roomMgr() { return this.themeRoot().roomMgr(); }

    public ThemeUParser(ThemeRoot root_val) {
        this.debug(false, "ThemeUParser", "init start");
        this.themeRoot_ = root_val;
    }

    public void parseInputPacket(String theme_data_str_val) {
        this.debug(true, "parseInputPacket", theme_data_str_val);

        ThemeData theme_data = new ThemeData(theme_data_str_val);

        switch (theme_data.command()) {
            case ThemeCommands.FABRIC_THEME_COMMAND_SETUP_ROOM:
                this.processSetupRoom(theme_data);
                return;

            case ThemeCommands.FABRIC_THEME_COMMAND_PUT_ROOM_DATA:
                this.processPutRoomData(theme_data);
                return;

            default:
                this.abend("parseInputPacket", "bad command, theme_data_str_val=" + theme_data_str_val);
                return;
        }
    }

    private void processSetupRoom(ThemeData theme_data_val) {
        String group_id_str = theme_data_val.groupIdStr();
        this.debug(true, "processSetupRoom", "groupIdStr=" + group_id_str);

        ThemeRoom room = this.roomMgr().mallocRoom(group_id_str);
        if (room == null) {
            this.abend("processSetupRoom", "null room");
            return;
        }

        EngineData engineer_data = new EngineData(
                EngineCommands.THEME_ENGINE_COMMAND_SETUP_BASE,
                ThemeResults.UNDECIDED,
                theme_data_val.themeType(),
                room.roomIdStr(),
                Encoders.IGNORE
        );
        engineer_data.addStringList(theme_data_val.stringList(0));
        this.themeUBinder().transmitData(engineer_data.getEncodedString());

        /*
        StringBuilder buf = new StringBuilder();
        buf.append(EngineCommands.THEME_ENGINE_COMMAND_SETUP_BASE);
        buf.append(room.roomIdStr());
        buf.append(theme_data_val.stringList(0));
        this.themeUBinder().transmitData(buf.toString());

         */

    }

    private void processPutRoomData(ThemeData theme_data_val) {
        String room_id_str = theme_data_val.roomIdStr();
        this.debug(false, "processPutRoomData", "room_id_str=" + room_id_str);

        ThemeRoom room = this.roomMgr().getRoomByIdStr(room_id_str);
        if (room == null) {
            this.abend("processPutRoomData", "null room");
            return;
        }

        EngineData engineer_data = new EngineData(
                EngineCommands.THEME_ENGINE_COMMAND_PUT_BASE_DATA,
                ThemeResults.UNDECIDED,
                theme_data_val.themeType(),
                room.roomIdStr(),
                room.baseIdStr()
        );
        engineer_data.addStringList(theme_data_val.stringList(0));
        this.themeUBinder().transmitData(engineer_data.getEncodedString());

        /*
        StringBuilder buf = new StringBuilder();
        buf.append(EngineCommands.THEME_ENGINE_COMMAND_PUT_BASE_DATA);
        buf.append(room.baseIdStr());
        buf.append(theme_data_val.stringList(0));
        this.themeUBinder().transmitData(buf.toString());

         */
    }
    
    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.themeRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    public void abend(String s0, String s1) { this.themeRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
