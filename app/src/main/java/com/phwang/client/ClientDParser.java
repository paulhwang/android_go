/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.client;

import com.phwang.core.fabric.FabricCommands;
import com.phwang.core.utils.encoders.Encoders;

import java.util.Base64;

public class ClientDParser {
    private String objectName() {return "ClientDParser";}
    
    private ClientRoot clientRoot_;
    
    protected ClientRoot clientRoot() { return this.clientRoot_; }
    protected ClientDImportInt importInterface() { return this.clientRoot_.importInterface(); }
    private ClientFabricInfo clientFabricInfo() { return this.clientRoot().clientFabricInfo(); }
    
    protected ClientDParser(ClientRoot root_val) {
        this.debug(false, "ClientDParser", "init start");
        this.clientRoot_ = root_val;
    }
    
    protected void parserResponseData(String response_data_str_val) {
    	this.debug(true, "parserResponseData", "response_data=" + response_data_str_val);
    	
    	switch (response_data_str_val.charAt(0)) {
            case FabricCommands.FABRIC_COMMAND_REGISTER:
    		    parserRegisterResponse(response_data_str_val.substring(1));
    		    break;
            case FabricCommands.FABRIC_COMMAND_LOGIN:
                parserLoginResponse(response_data_str_val.substring(1));
                break;
            case FabricCommands.FABRIC_COMMAND_LOGOUT:
                parserLogoutResponse(response_data_str_val.substring(1));
                break;
            case FabricCommands.FABRIC_COMMAND_GET_GROUPS:
                parserGetGroupsResponse(response_data_str_val.substring(1));
                break;
            case FabricCommands.FABRIC_COMMAND_GET_LINK_DATA:
    		    parserGetLinkDataResponse(response_data_str_val.substring(1));
    		    break;
            case FabricCommands.FABRIC_COMMAND_GET_NAME_LIST:
    		    parserGetNameListResponse(response_data_str_val.substring(1));
    		    break;
            case FabricCommands.FABRIC_COMMAND_SOLO_SESSION:
                parserSoloSessionResponse(response_data_str_val.substring(1));
                break;
            case FabricCommands.FABRIC_COMMAND_HEAD_SESSION:
                parserHeadSessionResponse(response_data_str_val.substring(1));
                break;
            case FabricCommands.FABRIC_COMMAND_PEER_SESSION:
                parserPeerSessionResponse(response_data_str_val.substring(1));
                break;
            case FabricCommands.FABRIC_COMMAND_JOIN_SESSION:
                parserJoinSessionResponse(response_data_str_val.substring(1));
                break;
            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION:
    		    parserSetupSessionResponse(response_data_str_val.substring(1));
    		    break;
            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION2:
    		    parserSetupSession2Response(response_data_str_val.substring(1));
    		    break;
            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION3:
    		    parserSetupSession3Response(response_data_str_val.substring(1));
    		    break;
            case FabricCommands.FABRIC_COMMAND_DELETE_SESSION:
                parserDeleteSessionResponse(response_data_str_val.substring(1));
                break;
            case FabricCommands.FABRIC_COMMAND_PUT_SESSION_DATA:
    		    parserPutSessionDataResponse(response_data_str_val.substring(1));
    		    break;
            case FabricCommands.FABRIC_COMMAND_GET_SESSION_DATA:
    		    parserGetSessionDataResponse(response_data_str_val.substring(1));
    		    break;
            default:
    		    this.abend("parserResponseData", "input_data_val=" + response_data_str_val);
    		    break;
    	}
    }

    private void parserRegisterResponse(String input_str_val) {
        this.debug(true, "parserRegisterResponse", "input_str_val=" + input_str_val);

        String result_str = input_str_val.substring(0, 1);

        if (result_str.charAt(0) == ClientFabricResultImport.SUCCEED) {
            String rest_str = input_str_val.substring(1);
            String my_name = Encoders.sDecode2(rest_str);
            //rest_str = Encoders.sDecode2_(rest_str);
        }

        this.importInterface().handleRegisterResponse(result_str);
    }
    
    private void parserLoginResponse(String input_str_val) {
    	this.debug(true, "parserLoginResponse", "input_str_val=" + input_str_val);

    	String result_str = input_str_val.substring(0, 1);

    	if (result_str.charAt(0) == ClientFabricResultImport.SUCCEED) {
            String rest_str = input_str_val.substring(1);
            String link_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String my_name = Encoders.sDecode2(rest_str);
            //rest_str = Encoders.sDecode2_(rest_str);

            this.clientFabricInfo().setLinkIdStr(link_id_str);
        }

    	this.importInterface().handleLoginResponse(result_str);
    }

    private void parserLogoutResponse(String input_str_val) {
        this.debug(true, "parserLogoutResponse", "input_str_val=" + input_str_val);

        String result_str = input_str_val.substring(0, 1);

        if (result_str.charAt(0) == ClientFabricResultImport.SUCCEED) {
            String rest_str = input_str_val.substring(1);
            String link_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            this.clientFabricInfo().setLinkIdStr(null);
        }

        this.importInterface().handleLogoutResponse(result_str);
    }

    private void parserGetGroupsResponse(String input_str_val) {
        this.debug(true, "parserGetGroupsResponse", "input_str_val=" + input_str_val);

        String result_str = input_str_val.substring(0, 1);

        if (result_str.charAt(0) == ClientFabricResultImport.SUCCEED) {
            String rest_str = input_str_val.substring(1);
            String link_id_str = Encoders.sSubstring2(rest_str);
            //rest_str = Encoders.sSubstring2_(rest_str);

        }

        this.importInterface().handleGetGroupsResponse(result_str);
    }

    private void parserGetLinkDataResponse(String input_str_val) {
    	this.debug(false, "parserGetLinkDataResponse", "input_str_val=" + input_str_val);

        String result_str = input_str_val.substring(0, 1);

        if (result_str.charAt(0) == ClientFabricResultImport.SUCCEED) {
            String rest_str = input_str_val.substring(1);
            String link_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String data = Encoders.sDecode2(rest_str);
            rest_str = Encoders.sDecode2_(rest_str);

            String pending_session_setup = Encoders.sDecode2(rest_str);
            //rest_str = Encoders.sDecode2_(rest_str);

            this.importInterface().handleGetLinkDataResponse(result_str);
        }
    }

    private void parserGetNameListResponse(String input_str_val) {
    	this.debug(false, "parserGetNameListResponse", "input_str_val=" + input_str_val);

        String result_str = input_str_val.substring(0, 1);

        if (result_str.charAt(0) == ClientFabricResultImport.SUCCEED) {
            String rest_str = input_str_val.substring(1);
            String link_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String name_list_str = Encoders.sDecode2(rest_str);
            //rest_str = Encoders.sDecode2_(rest_str);

            this.importInterface().handleGetNameListResponse(result_str);
        }
    }

    private void parserSoloSessionResponse(String input_str_val) {
        this.debug(true, "parserSoloSessionResponse", "input_str_val=" + input_str_val);
        String result_str = input_str_val.substring(0, 1);

        if (result_str.charAt(0) == ClientFabricResultImport.SUCCEED) {
            String rest_str = input_str_val.substring(1);

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
            this.importInterface().handleSetupSoloSessionResponse(result_str, data_str);
        }
    }

    private void parserHeadSessionResponse(String input_str_val) {
        this.debug(true, "parserHeadSessionResponse", "input_str_val=" + input_str_val);
        String result_str = input_str_val.substring(0, 1);

        if (result_str.charAt(0) == ClientFabricResultImport.SUCCEED) {
            String rest_str = input_str_val.substring(1);

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
            this.importInterface().handleSetupHeadSessionResponse(result_str, data_str);
        }
    }

    private void parserPeerSessionResponse(String input_str_val) {
        this.debug(true, "parserPeerSessionResponse", "input_str_val=" + input_str_val);
        String result_str = input_str_val.substring(0, 1);

        if (result_str.charAt(0) == ClientFabricResultImport.SUCCEED) {
            String rest_str = input_str_val.substring(1);

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
            this.importInterface().handleSetupPeerSessionResponse(result_str, data_str);
        }
    }

    private void parserJoinSessionResponse(String input_str_val) {
        this.debug(true, "parserJoinSessionResponse", "input_str_val=" + input_str_val);
        String result_str = input_str_val.substring(0, 1);

        if (result_str.charAt(0) == ClientFabricResultImport.SUCCEED) {
            String rest_str = input_str_val.substring(1);

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
            this.importInterface().handleSetupJoinSessionResponse(result_str, data_str);
        }
    }

    private void parserSetupSessionResponse(String input_str_val) {
    	this.debug(false, "parserSetupSessionResponse", "input_str_val=" + input_str_val);

        String result_str = input_str_val.substring(0, 1);

        if (result_str.charAt(0) == ClientFabricResultImport.SUCCEED) {
            String rest_str = input_str_val.substring(1);
            String link_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            this.debug(false, "parserSetupSessionResponse", "rest_str=" + rest_str);

            String session_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            this.debug(false, "parserSetupSessionResponse", "session_id_str=" + session_id_str);

            this.clientFabricInfo().setSessionIdStr(session_id_str);
            this.importInterface().handleSetupSessionResponse(result_str);
        }
    }

    private void parserSetupSession2Response(String input_str_val) {
    	this.debug(false, "parserSetupSession2Response", "input_str_val=" + input_str_val);

        String result_str = input_str_val.substring(0, 1);

        if (result_str.charAt(0) == ClientFabricResultImport.SUCCEED) {
            String rest_str = input_str_val.substring(1);
            String link_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String session_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String theme_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            this.importInterface().handleSetupSession2Response(result_str);
        }
    }

    private void parserSetupSession3Response(String input_str_val) {
    	this.debug(false, "parserSetupSession3Response", "input_str_val=" + input_str_val);

        String result_str = input_str_val.substring(0, 1);

        if (result_str.charAt(0) == ClientFabricResultImport.SUCCEED) {
            String rest_str = input_str_val.substring(1);
            String link_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String session_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String theme_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            this.importInterface().handleSetupSession3Response(result_str);
        }
    }

    private void parserDeleteSessionResponse(String input_str_val) {
        this.debug(true, "parserDeleteSessionResponse", "input_str_val=" + input_str_val);
    }

    private void parserPutSessionDataResponse(String input_str_val) {
    	this.debug(false, "parserPutSessionDataResponse", "input_str_val=" + input_str_val);

        String result_str = input_str_val.substring(0, 1);

        if (result_str.charAt(0) == ClientFabricResultImport.SUCCEED) {
            String rest_str = input_str_val.substring(1);
            String link_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String session_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);

            String c_data = Encoders.sDecode2(rest_str);
            //rest_str = Encoders.sDecode2_(rest_str);

            this.importInterface().handlePutSessionDataResponse(result_str);
        }
    }

    private void parserGetSessionDataResponse(String input_str_val) {
    	this.debug(false, "parserGetSessionDataResponse", "input_str_val=" + input_str_val);

        String result_str = input_str_val.substring(0, 1);

        if (result_str.charAt(0) == ClientFabricResultImport.SUCCEED) {
            String rest_str = input_str_val.substring(1);

            String data_package_str = Encoders.sSubstring5(rest_str);
            //rest_str = Encoders.sDecode5_(rest_str);

            this.importInterface().handleGetSessionDataResponse(result_str, data_package_str);
        }
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.clientRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { this.clientRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
