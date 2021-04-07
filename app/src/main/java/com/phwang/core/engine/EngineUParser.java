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
import com.phwang.core.protocols.engine.EngineResults;
import com.phwang.core.protocols.theme.ThemeData;
import com.phwang.core.protocols.theme.ThemeResults;
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

    public void ParseInputPacket(String engine_data_str_val) {
        this.debug(false, "ParseInputPacket", "data=" + engine_data_str_val);
        EngineData engine_data = new EngineData(engine_data_str_val);
        char command = engine_data.command();

        switch (command) {
            case EngineCommands.THEME_ENGINE_COMMAND_SETUP_BASE:
                this.processSetupBase(engine_data);
                return;

            case EngineCommands.THEME_ENGINE_COMMAND_PUT_BASE_DATA:
                this.processPutBaseData(engine_data);
                return;

            default:
                this.abend("ParseInputPacket", engine_data_str_val);
                return;
        }
    }

    private void processSetupBase(EngineData engine_data_val) {
        String room_id_str = engine_data_val.roomIdStr();
        String input_data = engine_data_val.stringList(0);
        this.debug(false, "processSetupBase", "engine_data_val=" + engine_data_val);
        this.debug(false, "processSetupBase", "input_data=" + input_data);

        EngineBase go_base = this.baseMgr().MallocGoBase(room_id_str);
        if (go_base == null) {
            this.abend("processSetupBase", "null go_base");
            return;
        }
        String output_data = go_base.setupBase(input_data);

        engine_data_val.setCommand(EngineCommands.THEME_ENGINE_RESPOND_SETUP_BASE);
        engine_data_val.setResult(EngineResults.SUCCEED);
        engine_data_val.setBaseIdStr(go_base.roomIdStr());
        engine_data_val.setBaseIdStr(go_base.BaseIdStr());
        engine_data_val.addStringList(output_data);
        this.engineDBinder().TransmitData(engine_data_val.getEncodedString());

        //StringBuilder buf = new StringBuilder();
        //buf.append(EngineCommands.THEME_ENGINE_RESPOND_SETUP_BASE);
        //buf.append(go_base.roomIdStr());
        //buf.append(go_base.BaseIdStr());
        //buf.append(output_data);
        //this.engineDBinder().TransmitData(buf.toString());
    }

    private void processPutBaseData(EngineData engine_data_val) {
        String base_id_str = engine_data_val.baseIdStr();
        String input_data = engine_data_val.stringList(0);
        this.debug(false, "processPutBaseData", "base_id_str=" + base_id_str);
        this.debug(false, "processPutBaseData", "input_data=" + input_data);

        EngineBase go_base = this.baseMgr().GetBaseByIdStr(base_id_str);
        if (go_base == null) {
            this.abend("processPutBaseData", "null go_base");
            return;
        }
        String output_data = go_base.processInputData(input_data);

        engine_data_val.setCommand(EngineCommands.THEME_ENGINE_RESPOND_PUT_BASE_DATA);
        engine_data_val.setResult(EngineResults.SUCCEED);
        engine_data_val.addStringList(output_data);
        this.engineDBinder().TransmitData(engine_data_val.getEncodedString());

        //StringBuilder buf = new StringBuilder();
        //buf.append(EngineCommands.THEME_ENGINE_RESPOND_PUT_BASE_DATA);
        //buf.append(go_base.roomIdStr());
        //buf.append(output_data);
        //this.engineDBinder().TransmitData(buf.toString());
}
    
    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.engineRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    public void abend(String s0, String s1) { this.engineRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
