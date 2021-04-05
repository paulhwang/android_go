/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.client;

import com.phwang.core.fabric.FabricCommands;
import com.phwang.core.fabric.FabricResults;
import com.phwang.core.utils.encoders.Encoders;
import com.phwang.core.utils.fabric.FabricData;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.services.BindReceiverDFunc;
import com.phwang.go.services.BindService;

import java.util.Base64;

public class ClientDParser {
    private String objectName() {return "ClientDParser";}
    
    private ClientRoot clientRoot_;

    protected BindService bindService() { return this.clientRoot_.bindService(); }
    protected BindReceiverDFunc bindReceiverDFunc() { return this.bindService().bindReceiverDFunc(); }
    protected ClientRoot clientRoot() { return this.clientRoot_; }
    private ClientFabricInfo clientFabricInfo() { return this.clientRoot().clientFabricInfo(); }
    
    protected ClientDParser(ClientRoot root_val) {
        this.debug(false, "ClientDParser", "init start");
        this.clientRoot_ = root_val;
    }
    
    protected void parserResponseData(String response_data_str_val) {
    	this.debug(true, "parserResponseData", "response_data=" + response_data_str_val);
    	
    	switch (response_data_str_val.charAt(0)) {
            case FabricCommands.FABRIC_COMMAND_LOGIN:
                FabricData fabric_decode = new FabricData(response_data_str_val);
                String link_id_str = fabric_decode.linkIdStr();
                if (fabric_decode.result() == FabricResults.SUCCEED) {
                    this.clientFabricInfo().setLinkIdStr(link_id_str);
                }
                this.bindReceiverDFunc().sendFabricDataResponse(IntentDefine.LOGIN_ACTIVITY, response_data_str_val);
                break;

            case FabricCommands.FABRIC_COMMAND_REGISTER:
            case FabricCommands.FABRIC_COMMAND_LOGOUT:
            case FabricCommands.FABRIC_COMMAND_GET_GROUPS:
                this.bindReceiverDFunc().sendFabricDataResponse(IntentDefine.MAIN_ACTIVITY, response_data_str_val);
                break;

            case FabricCommands.FABRIC_COMMAND_DELETE_SESSION:
            case FabricCommands.FABRIC_COMMAND_PUT_SESSION_DATA:
            case FabricCommands.FABRIC_COMMAND_GET_SESSION_DATA:
                this.bindReceiverDFunc().sendFabricDataResponse(IntentDefine.GO_GAME_ACTIVITY, response_data_str_val);
                break;

            case FabricCommands.FABRIC_COMMAND_SOLO_SESSION:
            case FabricCommands.FABRIC_COMMAND_HEAD_SESSION:
            case FabricCommands.FABRIC_COMMAND_PEER_SESSION:
            case FabricCommands.FABRIC_COMMAND_JOIN_SESSION:
                this.bindReceiverDFunc().sendFabricDataResponse(IntentDefine.GO_CONFIG_ACTIVITY, response_data_str_val);
                break;

            case FabricCommands.FABRIC_COMMAND_GET_LINK_DATA:
            case FabricCommands.FABRIC_COMMAND_GET_NAME_LIST:
            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION:
            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION2:
            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION3:
            default:
    		    this.abend("parserResponseData", "input_data_val=" + response_data_str_val);
    		    break;
    	}
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.clientRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { this.clientRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
