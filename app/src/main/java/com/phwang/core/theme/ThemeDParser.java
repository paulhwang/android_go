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
import com.phwang.core.protocols.theme.ThemeResults;
import com.phwang.core.utils.encoders.Encoders;

public class ThemeDParser {
    private static String objectName() {return "ThemeDParser";}
    
    private ThemeRoot themeRoot_;

    public ThemeRoot themeRoot() { return this.themeRoot_; }
    public ThemeDBinder themeDBinder() { return this.themeRoot().themeDBinder(); }
    public ThemeUBinder themeUBinder() { return this.themeRoot().themeUBinder(); }
    public ThemeRoomMgr roomMgr() { return this.themeRoot().roomMgr(); }

    public ThemeDParser(ThemeRoot root_val) {
        this.debug(false, "ThemeDParser", "init start");
        this.themeRoot_ = root_val;
    }
    
    public void parseInputPacket(String engine_data_str_val) {
        this.debug(false, "parseInputPacket", engine_data_str_val);
        EngineData engine_data = new EngineData(engine_data_str_val);
        char command = engine_data.command();

        switch (command) {
            case EngineCommands.THEME_ENGINE_RESPOND_SETUP_BASE:
                this.processSetupBaseResponse(engine_data);
                return;

            case EngineCommands.THEME_ENGINE_RESPOND_PUT_BASE_DATA:
                this.processPutBaseDataResponse(engine_data);
                return;

            default:
                this.abend("parseInputPacket", engine_data_str_val);
                return;

        }
    }

    private void processSetupBaseResponse(EngineData engine_data_val) {
        String room_id_str = engine_data_val.roomIdStr();
        String base_id_str = engine_data_val.baseIdStr();
        this.debug(false, "processSetupBaseResponse", "room_id_str=" + room_id_str);
        this.debug(false, "processSetupBaseResponse", "base_id_str=" + base_id_str);

        ThemeRoom room_object = this.roomMgr().getRoomByIdStr(room_id_str);
        room_object.setBaseIdStr(base_id_str);



        EngineData engineer_data = new EngineData(
                ThemeCommands.FABRIC_THEME_RESPOND_SETUP_ROOM,
                ThemeResults.SUCCEED,
                engine_data_val.themeType(),
                room_object.groupIdStr(),
                room_object.roomIdStr()
        );
        this.themeDBinder().transmitData(engineer_data.getEncodedString());


        //StringBuilder buf = new StringBuilder();
        //buf.append(ThemeCommands.FABRIC_THEME_RESPOND_SETUP_ROOM);
        //buf.append(room_object.groupIdStr());
        //buf.append(room_object.roomIdStr());
        //this.themeDBinder().transmitData(buf.toString());

        /*
        char* room_id_index_val = data_val;

        char* downlink_data;
        char* data_ptr;
        int group_array_size;

        RoomClass* room = this->theThemeObject->searchRoom(room_id_index_val);
        if (!room)
        {
            this->abend("processSetupBaseResponse", "null room");
            return;
        }

        data_val += ROOM_MGR_PROTOCOL_ROOM_ID_INDEX_SIZE;
        room->setBaseIdIndex(data_val);

        downlink_data = data_ptr = (char*)phwangMalloc(ROOM_MGR_DATA_BUFFER_SIZE + 4, "UTSB");
        *data_ptr++ = FABRIC_THEME_PROTOCOL_RESPOND_IS_SETUP_ROOM;

        room->setGroupTableArray((char**)phwangArrayMgrGetArrayTable(room->groupArrayMgr(), &group_array_size));
        memcpy(data_ptr, room->groupTableArray(0), GROUP_MGR_PROTOCOL_GROUP_ID_INDEX_SIZE);
        data_ptr += GROUP_MGR_PROTOCOL_GROUP_ID_INDEX_SIZE;

        memcpy(data_ptr, room->roomIdIndex(), ROOM_MGR_PROTOCOL_ROOM_ID_INDEX_SIZE);
        data_ptr += ROOM_MGR_PROTOCOL_ROOM_ID_INDEX_SIZE;
        *data_ptr = 0;
        this->theThemeObject->dThemeObject()->transmitFunction(downlink_data);
        */
    }

    private void processPutBaseDataResponse(EngineData engine_data_val) {
        String room_id_str = engine_data_val.roomIdStr();
        String base_id_str = engine_data_val.baseIdStr();
        String out_put_data_str = engine_data_val.stringList(1);
        this.debug(false, "processPutBaseDataResponse", "room_id_str=" + room_id_str);
        this.debug(false, "processPutBaseDataResponse", "base_id_str=" + base_id_str);

        ThemeRoom room_object = this.roomMgr().getRoomByIdStr(room_id_str);
        if (room_object == null) {
            this.abend("processPutBaseDataResponse", "null room");
            return;
        }


        EngineData engineer_data = new EngineData(
                ThemeCommands.FABRIC_THEME_RESPOND_PUT_ROOM_DATA,
                ThemeResults.SUCCEED,
                engine_data_val.themeType(),
                room_object.groupIdStr(),
                room_object.roomIdStr()
        );
        engineer_data.addStringList(out_put_data_str);
        this.themeDBinder().transmitData(engineer_data.getEncodedString());



        //StringBuilder buf = new StringBuilder();
        //buf.append(ThemeCommands.FABRIC_THEME_RESPOND_PUT_ROOM_DATA);
        //buf.append(room_object.groupIdStr());
        //buf.append(out_put_data_str);
        //this.themeDBinder().transmitData(buf.toString());

        /*
        char* downlink_data;
        char* data_ptr;
        int group_array_size;


        RoomClass* room = this->theThemeObject->searchRoom(data_val);
        if (!room)
        {
            this->abend("processPutBaseDataResponse", "null room");
            return;
        }
        data_val += ROOM_MGR_PROTOCOL_ROOM_ID_INDEX_SIZE;

        room->setGroupTableArray((char**)phwangArrayMgrGetArrayTable(room->groupArrayMgr(), &group_array_size));
        for (int i = 0; i < group_array_size; i++)
        {
            if (room->groupTableArray(i))
            {
                downlink_data = data_ptr = (char*)phwangMalloc(ROOM_MGR_DATA_BUFFER_SIZE + 4, "UTPB");
                *data_ptr++ = FABRIC_THEME_PROTOCOL_RESPOND_IS_PUT_ROOM_DATA;
                memcpy(data_ptr, room->groupTableArray(i), GROUP_MGR_PROTOCOL_GROUP_ID_INDEX_SIZE);
                data_ptr += GROUP_MGR_PROTOCOL_GROUP_ID_INDEX_SIZE;
                strcpy(data_ptr, data_val);
                this->theThemeObject->dThemeObject()->transmitFunction(downlink_data);
            }
        }
        */
    }
    
    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.themeRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    public void abend(String s0, String s1) { this.themeRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
