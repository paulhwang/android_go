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
import com.phwang.core.utils.fabric.FabricDecode;
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
                FabricDecode fabric_decode = new FabricDecode(response_data_str_val);
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

            case FabricCommands.FABRIC_COMMAND_GET_LINK_DATA:
    		    parserGetLinkDataResponse(response_data_str_val);
    		    break;
            case FabricCommands.FABRIC_COMMAND_GET_NAME_LIST:
    		    parserGetNameListResponse(response_data_str_val);
    		    break;
            case FabricCommands.FABRIC_COMMAND_SOLO_SESSION:
                parserSoloSessionResponse(response_data_str_val);
                break;
            case FabricCommands.FABRIC_COMMAND_HEAD_SESSION:
                parserHeadSessionResponse(response_data_str_val);
                break;
            case FabricCommands.FABRIC_COMMAND_PEER_SESSION:
                parserPeerSessionResponse(response_data_str_val);
                break;
            case FabricCommands.FABRIC_COMMAND_JOIN_SESSION:
                parserJoinSessionResponse(response_data_str_val);
                break;
            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION:
    		    parserSetupSessionResponse(response_data_str_val);
    		    break;
            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION2:
    		    parserSetupSession2Response(response_data_str_val);
    		    break;
            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION3:
    		    parserSetupSession3Response(response_data_str_val);
    		    break;

            default:
    		    this.abend("parserResponseData", "input_data_val=" + response_data_str_val);
    		    break;
    	}
    }

    private void parserGetLinkDataResponse(String input_str_val) {
    	this.debug(false, "parserGetLinkDataResponse", "input_str_val=" + input_str_val);

        String result_str = input_str_val.substring(1, 2);

        if (result_str.charAt(0) == FabricResults.SUCCEED) {
            String rest_str = input_str_val.substring(2);
            String link_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String data = Encoders.sDecode2(rest_str);
            rest_str = Encoders.sDecode2_(rest_str);

            String pending_session_setup = Encoders.sDecode2(rest_str);
            //rest_str = Encoders.sDecode2_(rest_str);

            //this.importInterface().handleGetLinkDataResponse(result_str);
            this.bindReceiverDFunc().parseFabricResponse(IntentDefine.MAIN_ACTIVITY, FabricCommands.FABRIC_COMMAND_GET_LINK_DATA_STR, result_str, Encoders.NULL_DATA);
        }
    }

    private void parserGetNameListResponse(String input_str_val) {
    	this.debug(false, "parserGetNameListResponse", "input_str_val=" + input_str_val);

        String result_str = input_str_val.substring(1, 2);

        if (result_str.charAt(0) == FabricResults.SUCCEED) {
            String rest_str = input_str_val.substring(2);
            String link_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String name_list_str = Encoders.sDecode2(rest_str);
            //rest_str = Encoders.sDecode2_(rest_str);

            //this.importInterface().handleGetNameListResponse(result_str);
            this.bindReceiverDFunc().parseFabricResponse(IntentDefine.MAIN_ACTIVITY, FabricCommands.FABRIC_COMMAND_GET_NAME_LIST_STR, result_str, Encoders.NULL_DATA);
        }
    }

    private void parserSoloSessionResponse(String input_str_val) {
        this.debug(true, "parserSoloSessionResponse", "input_str_val=" + input_str_val);
        String result_str = input_str_val.substring(1, 2);

        if (result_str.charAt(0) == FabricResults.SUCCEED) {
            String rest_str = input_str_val.substring(2);

            String data_str = Encoders.sSubstring5(rest_str);
            //rest_str = Encoders.sSubstring5_(rest_str);

            //////////////////////////////////////////////
            rest_str = Encoders.sDecode5(data_str);

            String link_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String session_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String theme_str = Encoders.sSubstring2(rest_str);
            //rest_str = Encoders.sSubstring2_(rest_str);
            ///////////////////////////////////////////////////////

            this.debug(true, "parserSoloSessionResponse", "session_id_str=" + session_id_str);

            this.clientFabricInfo().setSessionIdStr(session_id_str);
            //this.importInterface().handleSetupSoloSessionResponse(result_str, data_str);
            this.bindReceiverDFunc().parseFabricResponse(IntentDefine.GO_CONFIG_ACTIVITY, FabricCommands.FABRIC_COMMAND_SOLO_SESSION_STR, result_str, data_str);
        }
    }

    private void parserHeadSessionResponse(String input_str_val) {
        this.debug(true, "parserHeadSessionResponse", "input_str_val=" + input_str_val);
        String result_str = input_str_val.substring(1, 2);

        if (result_str.charAt(0) == FabricResults.SUCCEED) {
            String rest_str = input_str_val.substring(2);

            String data_str = Encoders.sSubstring5(rest_str);
            //rest_str = Encoders.sSubstring5_(rest_str);

            //////////////////////////////////////////////
            rest_str = Encoders.sDecode5(data_str);

            String link_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String session_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String theme_str = Encoders.sSubstring2(rest_str);
            //rest_str = Encoders.sSubstring2_(rest_str);
            ///////////////////////////////////////////////////////

            this.debug(false, "parserHeadSessionResponse", "session_id_str=" + session_id_str);

            this.clientFabricInfo().setSessionIdStr(session_id_str);
            //this.importInterface().handleSetupHeadSessionResponse(result_str, data_str);
            this.bindReceiverDFunc().parseFabricResponse(IntentDefine.GO_CONFIG_ACTIVITY, FabricCommands.FABRIC_COMMAND_HEAD_SESSION_STR, result_str, data_str);
        }
    }

    private void parserPeerSessionResponse(String input_str_val) {
        this.debug(true, "parserPeerSessionResponse", "input_str_val=" + input_str_val);
        String result_str = input_str_val.substring(1, 2);

        if (result_str.charAt(0) == FabricResults.SUCCEED) {
            String rest_str = input_str_val.substring(2);

            String data_str = Encoders.sSubstring5(rest_str);
            //rest_str = Encoders.sSubstring5_(rest_str);

            //////////////////////////////////////////////
            rest_str = Encoders.sDecode5(data_str);
            String link_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String session_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String theme_str = Encoders.sSubstring2(rest_str);
            //rest_str = Encoders.sSubstring2_(rest_str);
            ///////////////////////////////////////////////////////

            this.debug(false, "parserPeerSessionResponse", "session_id_str=" + session_id_str);

            this.clientFabricInfo().setSessionIdStr(session_id_str);
            //this.importInterface().handleSetupPeerSessionResponse(result_str, data_str);
            this.bindReceiverDFunc().parseFabricResponse(IntentDefine.GO_CONFIG_ACTIVITY, FabricCommands.FABRIC_COMMAND_PEER_SESSION_STR, result_str, data_str);
        }
    }

    private void parserJoinSessionResponse(String input_str_val) {
        this.debug(true, "parserJoinSessionResponse", "input_str_val=" + input_str_val);
        String result_str = input_str_val.substring(1, 2);

        if (result_str.charAt(0) == FabricResults.SUCCEED) {
            String rest_str = input_str_val.substring(2);

            String data_str = Encoders.sSubstring5(rest_str);
            //rest_str = Encoders.sSubstring5_(rest_str);

            //////////////////////////////////////////////
            rest_str = Encoders.sDecode5(data_str);

            String link_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String session_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String theme_str = Encoders.sSubstring2(rest_str);
            //rest_str = Encoders.sSubstring2_(rest_str);
            ///////////////////////////////////////////////////////

            this.debug(false, "parserJoinSessionResponse", "session_id_str=" + session_id_str);

            this.clientFabricInfo().setSessionIdStr(session_id_str);
            //this.importInterface().handleSetupJoinSessionResponse(result_str, data_str);
            this.bindReceiverDFunc().parseFabricResponse(IntentDefine.GO_CONFIG_ACTIVITY, FabricCommands.FABRIC_COMMAND_JOIN_SESSION_STR, result_str, data_str);
        }
    }

    private void parserSetupSessionResponse(String input_str_val) {
    	this.debug(false, "parserSetupSessionResponse", "input_str_val=" + input_str_val);

        String result_str = input_str_val.substring(1, 2);

        if (result_str.charAt(0) == FabricResults.SUCCEED) {
            String rest_str = input_str_val.substring(2);
            String link_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            this.debug(false, "parserSetupSessionResponse", "rest_str=" + rest_str);

            String session_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            this.debug(false, "parserSetupSessionResponse", "session_id_str=" + session_id_str);

            this.clientFabricInfo().setSessionIdStr(session_id_str);
            //this.importInterface().handleSetupSessionResponse(result_str);
            this.bindReceiverDFunc().parseFabricResponse(IntentDefine.GO_CONFIG_ACTIVITY, FabricCommands.FABRIC_COMMAND_SETUP_SESSION_STR, result_str, Encoders.NULL_DATA);
        }
    }

    private void parserSetupSession2Response(String input_str_val) {
    	this.debug(false, "parserSetupSession2Response", "input_str_val=" + input_str_val);

        String result_str = input_str_val.substring(1, 2);

        if (result_str.charAt(0) == FabricResults.SUCCEED) {
            String rest_str = input_str_val.substring(2);
            String link_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String session_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String theme_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            //this.importInterface().handleSetupSession2Response(result_str);
            this.bindReceiverDFunc().parseFabricResponse(IntentDefine.GO_CONFIG_ACTIVITY, FabricCommands.FABRIC_COMMAND_SETUP_SESSION2_STR, result_str, Encoders.NULL_DATA);
        }
    }

    private void parserSetupSession3Response(String input_str_val) {
    	this.debug(false, "parserSetupSession3Response", "input_str_val=" + input_str_val);

        String result_str = input_str_val.substring(1, 2);

        if (result_str.charAt(0) == FabricResults.SUCCEED) {
            String rest_str = input_str_val.substring(2);
            String link_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String session_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String theme_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            //this.importInterface().handleSetupSession3Response(result_str);
            this.bindReceiverDFunc().parseFabricResponse(IntentDefine.MAIN_ACTIVITY, FabricCommands.FABRIC_COMMAND_SETUP_SESSION3_STR, result_str, Encoders.NULL_DATA);
        }
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.clientRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { this.clientRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
