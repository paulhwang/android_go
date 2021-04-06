/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.engine;

import com.phwang.core.protocols.engine.EngineCommands;
import com.phwang.core.protocols.engine.EngineData;
import com.phwang.core.protocols.theme.ThemeData;
import com.phwang.core.utils.encoders.Encoders;

public class EngineUParser {
    private static String objectName() {return "EngineUParser";}

    private EngineRoot engineRoot_;

    public EngineRoot engineRoot() { return this.engineRoot_; }
    public EngineDBinder engineDBinder() { return this.engineRoot().engineDBinder(); }
    public EngineBaseMgr baseMgr() { return this.engineRoot().baseMgr(); }

    public EngineUParser(EngineRoot root_val) {
        this.engineRoot_ = root_val;
    }

    public void ParseInputPacket(String input_data_val) {
        this.debug(true, "ParseInputPacket", "data=" + input_data_val);
        EngineData engine_data = new EngineData(input_data_val);
        char command = engine_data.command();

        //char command = input_data_val.charAt(0);
        //String input_data = input_data_val.substring(1);

        if (command == EngineCommands.THEME_ENGINE_COMMAND_SETUP_BASE) {
            this.processSetupBase(engine_data);
            return;
        }

        if (command == EngineCommands.THEME_ENGINE_COMMAND_PUT_BASE_DATA) {
            this.processPutBaseData(engine_data);
            return;
        }

        this.abend("ParseInputPacket", input_data_val);
    }

    private void processSetupBase(EngineData engine_data_val) {
        String room_id_str = engine_data_val.roomIdStr();
        String input_data = engine_data_val.stringList(0);
        this.debug(true, "processSetupBase", "engine_data_val=" + engine_data_val);
        this.debug(true, "processSetupBase", "input_data=" + input_data);

        //String rest_str = input_str_val;
        //String room_id_str = Encoders.sSubstring2(rest_str);
        //rest_str = Encoders.sSubstring2_(rest_str);

        //String input_data = rest_str;

        EngineBase go_base = this.baseMgr().MallocGoBase(room_id_str);
        if (go_base == null) {
            this.abend("processSetupBase", "null go_base");
            return;
        }

        String output_data = go_base.setupBase(input_data);

        StringBuilder buf = new StringBuilder();
        buf.append(EngineCommands.THEME_ENGINE_RESPOND_SETUP_BASE);
        buf.append(go_base.roomIdStr());
        buf.append(go_base.BaseIdStr());
        buf.append(output_data);
        this.engineDBinder().TransmitData(buf.toString());
    }

    private void processPutBaseData(EngineData engine_data_val) {
        String base_id_str = engine_data_val.baseIdStr();
        String input_data = engine_data_val.stringList(0);
        this.debug(true, "processPutBaseData", "base_id_str=" + base_id_str);
        this.debug(true, "processPutBaseData", "input_data=" + input_data);

        //String rest_str = input_str_val;
        //String base_id_str = Encoders.sSubstring2(rest_str);
        //rest_str = Encoders.sSubstring2_(rest_str);

        //String input_data = rest_str;

        EngineBase go_base = this.baseMgr().GetBaseByIdStr(base_id_str);
        if (go_base == null) {
            this.abend("processPutBaseData", "null go_base");
            return;
        }

        String output_data = go_base.processInputData(input_data);

        StringBuilder buf = new StringBuilder();
        buf.append(EngineCommands.THEME_ENGINE_RESPOND_PUT_BASE_DATA);
        buf.append(go_base.roomIdStr());
        buf.append(output_data);
        this.engineDBinder().TransmitData(buf.toString());
}
    
    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.engineRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    public void abend(String s0, String s1) { this.engineRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
