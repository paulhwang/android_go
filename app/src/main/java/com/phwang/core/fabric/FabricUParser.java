/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.fabric;

import com.phwang.core.protocols.fabric.FabricClients;
import com.phwang.core.protocols.fabric.FabricThemeTypes;
import com.phwang.core.protocols.theme.ThemeCommands;
import com.phwang.core.protocols.theme.ThemeInfo;
import com.phwang.core.protocols.theme.ThemeResults;
import com.phwang.core.utils.encoders.Encoders;
import com.phwang.core.protocols.fabric.FabricCommands;
import com.phwang.core.protocols.fabric.FabricInfo;
import com.phwang.core.protocols.fabric.FabricResults;
import com.phwang.core.utils.listmgr.ListEntry;
import com.phwang.core.utils.binder.BinderBundle;
import com.phwang.go.global.GlobalData;

public class FabricUParser {
    private static String objectName() {return "FabricUParser";}

    private String RESPONSE_IS_GET_LINK_DATA_NAME_LIST = FabricExport.WEB_FABRIC_PROTOCOL_RESPOND_IS_GET_LINK_DATA_NAME_LIST;

    private FabricRoot fabricRoot_;

    protected FabricRoot fabricRoot() { return this.fabricRoot_; }
    private FabricDBinder fabricDBinder() { return this.fabricRoot().fabricDBinder(); }
    private FabricUBinder fabricUBinder() { return this.fabricRoot().fabricUBinder(); }
    private FabricLinkMgr linkMgr() { return this.fabricRoot().linkMgr(); }
    private FabricGroupMgr groupMgr() { return this.fabricRoot().groupMgr(); }

    protected FabricUParser(FabricRoot root_val) {
        this.debug(false, "FabricUParser", "init start");

        this.fabricRoot_ = root_val;
    }

    protected void parseInputPacket(BinderBundle bundle_val) {
        /*
        String input_fabric_data_str = bundle_val.data();
        this.debug(true, "parseInputPacket", "input_fabric_data_str = " + input_fabric_data_str);
        FabricInfo fabric_data = new FabricInfo(input_fabric_data_str);
        String command = fabric_data.command();

        switch (command.charAt(1)) {
            case FabricCommands.FABRIC_COMMAND_REGISTER:
                this.processRegisterRequest(fabric_data);
                break;
            case FabricCommands.FABRIC_COMMAND_LOGIN:
                this.processLoginRequest(fabric_data);
                break;
            case FabricCommands.FABRIC_COMMAND_LOGOUT:
                this.processLogoutRequest(fabric_data);
                break;
            case FabricCommands.FABRIC_COMMAND_GET_GROUPS:
                this.processGetGroupsRequest(fabric_data);
                break;
            case FabricCommands.FABRIC_COMMAND_SOLO_SESSION:
                this.processSoloSessionRequest(fabric_data);
                break;
            case FabricCommands.FABRIC_COMMAND_HEAD_SESSION:
                this.processHeadSessionRequest(fabric_data);
                break;
            case FabricCommands.FABRIC_COMMAND_PEER_SESSION:
                this.processPeerSessionRequest(fabric_data);
                break;
            case FabricCommands.FABRIC_COMMAND_JOIN_SESSION:
                this.processJoinSessionRequest(fabric_data);
                break;
            case FabricCommands.FABRIC_COMMAND_DELETE_SESSION:
                this.processDeleteSessionRequest(fabric_data);
                break;
            case FabricCommands.FABRIC_COMMAND_PUT_SESSION_DATA:
                this.processPutSessionDataRequest(fabric_data);
                break;
            case FabricCommands.FABRIC_COMMAND_GET_SESSION_DATA:
                this.processGetSessionDataRequest(fabric_data);
                break;


            //case FabricCommands.FABRIC_COMMAND_GET_LINK_DATA:
            //    response_data = this.processGetLinkDataRequest(rest_str.substring(1));
            //    break;
            //case FabricCommands.FABRIC_COMMAND_GET_NAME_LIST:
            //    response_data = this.processGetNameListRequest(rest_str.substring(1));
            //    break;
            //case FabricCommands.FABRIC_COMMAND_SETUP_SESSION:
            //    response_data = this.processSetupSessionRequest(rest_str.substring(1));
            //   break;
            //case FabricCommands.FABRIC_COMMAND_SETUP_SESSION2:
            //    response_data = this.processSetupSession2Request(rest_str.substring(1));
            //    break;
            //case FabricCommands.FABRIC_COMMAND_SETUP_SESSION3:
            //    response_data = this.processSetupSession3Request(rest_str.substring(1));
            //    break;

            default:
        	    this.abend("parseInputPacket", "should not reach here, input_fabric_data_str=" + input_fabric_data_str);
        	    break;
        }

        String output_fabric_data_str = fabric_data.encode();
        this.debug(false, "parseInputPacket", "output_fabric_data_str = " + output_fabric_data_str);
        bundle_val.setData(output_fabric_data_str);
        this.fabricDBinder().transmitBundleData(bundle_val);
    */
}

    private void processRegisterRequest(FabricInfo fabric_data_val) {
        String my_name = fabric_data_val.stringArrayElement(0);
        String email = fabric_data_val.stringArrayElement(1);
        String password = fabric_data_val.stringArrayElement(2);
        this.debug(false, "processRegisterRequest", "my_name=" + my_name);
        this.debug(false, "processRegisterRequest", "email=" + email);
        this.debug(false, "processRegisterRequest", "password=" + password);

        ///////////////fabric_data_val.setResult(FabricResults.SUCCEED);
    }

    /*
    private void processLoginRequest(FabricInfo fabric_data_val) {
        char client_type = fabric_data_val.clientType();
        String my_name = fabric_data_val.stringArrayElement(0);
        String password = fabric_data_val.stringArrayElement(1);
        this.debug(false, "processLoginRequest", "my_name=" + my_name);
        this.debug(false, "processLoginRequest", "password=" + password);

        FabricLink link = this.linkMgr().mallocLink(client_type, my_name);
        if (link == null) {
            fabric_data_val.setResult(FabricResults.BAD_PASSWORD);
            return;
        }

        fabric_data_val.setLinkIdStr(link.linkIdStr());
        fabric_data_val.setResult(FabricResults.SUCCEED);
    }

    private void processLogoutRequest(FabricInfo fabric_data_val) {
        String link_id_str = fabric_data_val.linkIdStr();
        this.debug(false, "processLogoutRequest", "link_id_str=" + link_id_str);

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            fabric_data_val.setResult(FabricResults.LINK_NOT_EXIST);
            return;
        }
        this.linkMgr().freeLink(link);

        fabric_data_val.setResult(FabricResults.SUCCEED);
    }

    private void processGetGroupsRequest(FabricInfo fabric_data_val) {
        String link_id_str = fabric_data_val.linkIdStr();
        this.debug(false, "processGetGroupsRequest", "link_id_str=" + link_id_str);

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            fabric_data_val.setResult(FabricResults.LINK_NOT_EXIST);
            return;
        }

        fabric_data_val.setResult(FabricResults.SUCCEED);
    }

    private void processSoloSessionRequest(FabricInfo fabric_data_val) {
        String link_id_str = fabric_data_val.linkIdStr();
        String data_str = fabric_data_val.stringArrayElement(0);
        this.debug(false, "processSoloSessionRequest", "link_id = " + link_id_str);
        this.debug(false, "processSoloSessionRequest", "theme_data = " + data_str);

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            fabric_data_val.setResult(FabricResults.LINK_NOT_EXIST);
            return;
        }

        FabricSession session = link.mallocSession();
        FabricGroup group = this.groupMgr().mallocGroup(data_str);
        group.insertSession(session);
        session.bindGroup(group);

        this.fabricUBinder().sendMallocRoomRequestToThemeServer(fabric_data_val.themeType(), group.groupIdStr(), data_str);

        fabric_data_val.setSessionIdStr(session.lSessionIdStr());
        fabric_data_val.setResult(FabricResults.SUCCEED);
    }

    private void processHeadSessionRequest(FabricInfo fabric_data_val) {
        String link_id_str = fabric_data_val.linkIdStr();
        String theme_data_str = fabric_data_val.stringArrayElement(0);
        this.debug(false, "processHeadSessionRequest", "link_id_str=" + link_id_str);
        this.debug(false, "processHeadSessionRequest", "theme_data_str=" + theme_data_str);

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            fabric_data_val.setResult(FabricResults.LINK_NOT_EXIST);
            return;
        }

        FabricSession session = link.mallocSession();
        FabricGroup group = this.groupMgr().mallocGroup(theme_data_str);
        group.insertSession(session);
        session.bindGroup(group);

        this.fabricUBinder().sendMallocRoomRequestToThemeServer(fabric_data_val.themeType(), group.groupIdStr(), theme_data_str);

        fabric_data_val.setSessionIdStr(session.lSessionIdStr());
        fabric_data_val.setResult(FabricResults.SUCCEED);
    }

    private void processPeerSessionRequest(FabricInfo fabric_data_val) {
        String link_id_str = fabric_data_val.linkIdStr();
        String theme_data_str = fabric_data_val.stringArrayElement(0);
        this.debug(false, "processPeerSessionRequest", "link_id_str=" + link_id_str);
        this.debug(false, "processPeerSessionRequest", "theme_data_str=" + theme_data_str);

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            fabric_data_val.setResult(FabricResults.LINK_NOT_EXIST);
            return;
        }

        FabricSession session = link.mallocSession();
        FabricGroup group = this.groupMgr().mallocGroup(theme_data_str);
        group.insertSession(session);
        session.bindGroup(group);

        this.fabricUBinder().sendMallocRoomRequestToThemeServer(fabric_data_val.themeType(), group.groupIdStr(), theme_data_str);

        fabric_data_val.setSessionIdStr(session.lSessionIdStr());
        fabric_data_val.setResult(FabricResults.SUCCEED);
    }

    private void processJoinSessionRequest(FabricInfo fabric_data_val) {
        String link_id_str = fabric_data_val.linkIdStr();
        String theme_data_str = fabric_data_val.stringArrayElement(0);
        this.debug(false, "processJoinSessionRequest", "link_id_str=" + link_id_str);
        this.debug(true, "processJoinSessionRequest", "theme_data_str=" + theme_data_str);

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            fabric_data_val.setResult(FabricResults.LINK_NOT_EXIST);
            return;
        }

        FabricSession session = link.mallocSession();
        FabricGroup group = this.groupMgr().mallocGroup(theme_data_str);
        group.insertSession(session);
        session.bindGroup(group);

        this.fabricUBinder().sendMallocRoomRequestToThemeServer(fabric_data_val.themeType(), group.groupIdStr(), theme_data_str);

        fabric_data_val.setSessionIdStr(session.lSessionIdStr());
        fabric_data_val.setResult(FabricResults.SUCCEED);
    }

    private void processDeleteSessionRequest(FabricInfo fabric_data_val) {
        String link_id_str = fabric_data_val.linkIdStr();
        String session_id_str = fabric_data_val.sessionIdStr();
        this.debug(false, "processDeleteSessionRequest", "link_id_str=" + link_id_str);
        this.debug(false, "processDeleteSessionRequest", "session_id_str=" + session_id_str);

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            fabric_data_val.setResult(FabricResults.LINK_NOT_EXIST);
            return;
        }

        FabricSession session = link.sessionMgr().getSessionByIdStr(session_id_str);
        if (session == null) {
            fabric_data_val.setResult(FabricResults.SESSION_NOT_EXIST);
            return;
        }

        link.sessionMgr().freeSession(session);

        fabric_data_val.setResult(FabricResults.SUCCEED);
    }

    private void processPutSessionDataRequest(FabricInfo fabric_data_val) {
        String link_id_str = fabric_data_val.linkIdStr();
        String session_id_str = fabric_data_val.sessionIdStr();
        String data_str = fabric_data_val.stringArrayElement(0);
        this.debug(false, "processPutSessionDataRequest", "link_id_str=" + link_id_str);
        this.debug(false, "processPutSessionDataRequest", "session_id_str=" + session_id_str);
        this.debug(false, "processPutSessionDataRequest", "data_str=" + data_str);

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            fabric_data_val.setResult(FabricResults.LINK_NOT_EXIST);
            return;
        }

        FabricSession session = link.sessionMgr().getSessionByIdStr(session_id_str);
        if (session == null) {
            fabric_data_val.setResult(FabricResults.SESSION_NOT_EXIST);
            return;
        }

        String room_id_str = session.group().roomIdStr();
        if (room_id_str == null) {
            fabric_data_val.setResult(FabricResults.ROOM_NOT_EXIST);
            return;
        }

        this.fabricUBinder().sendDataToThemeServer(fabric_data_val.themeType(), session.group().groupIdStr(), room_id_str, data_str);
        fabric_data_val.setResult(FabricResults.SUCCEED);
    }

    private void processGetSessionDataRequest(FabricInfo fabric_data_val) {
        String link_id_str = fabric_data_val.linkIdStr();
        String session_id_str = fabric_data_val.sessionIdStr();
        this.debug(false, "processGetSessionDataRequest", "link_id_str=" + link_id_str);
        this.debug(false, "processGetSessionDataRequest", "session_id_str=" + session_id_str);

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            fabric_data_val.setResult(FabricResults.LINK_NOT_EXIST);
            return;
        }

        FabricSession session = link.sessionMgr().getSessionByIdStr(session_id_str);
        if (session == null) {
            fabric_data_val.setResult(FabricResults.SESSION_NOT_EXIST);
            return;
        }

        String data_str = session.getPendingDownLinkData();

        fabric_data_val.addString(data_str);
        fabric_data_val.setResult(FabricResults.SUCCEED);
    }

    private String processGetLinkDataRequest(String input_str_val) {
        this.debug(false, "processGetLinkDataRequest", "input_str_val = " + input_str_val);
        
        String rest_str = input_str_val;
        String link_id_str = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);
    	
        this.debug(false, "processGetLinkDataRequest", "link_id = " + link_id_str);

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            return this.errorProcessGetLinkData(link_id_str, "*************null link");
        }

        String downlink_data = RESPONSE_IS_GET_LINK_DATA_NAME_LIST + this.fabricRoot().nameList().nameListTagStr();

        int max_session_table_array_index = link.GetSessionArrayMaxIndex();
        ListEntry[] session_table_array = link.GetSessionArrayEntryTable();
        String pending_session_data = "";
        for (int i = 0; i <= max_session_table_array_index; i++) {
        	ListEntry list_entry = session_table_array[i];
        	FabricSession session = (FabricSession)list_entry.data();
            if (session != null) {
               if (session.getPendingDownLinkDataCount() > 0) {
                    downlink_data = downlink_data + FabricExport.WEB_FABRIC_PROTOCOL_RESPOND_IS_GET_LINK_DATA_PENDING_DATA + link.linkIdStr() + session.lSessionIdStr();
                }
            }
        }
       
        String pending_session_setup = "";
        String pending_session_str = link.getPendingSessionSetup();
        if (pending_session_str != null) {
            pending_session_setup = pending_session_setup + FabricExport.WEB_FABRIC_PROTOCOL_RESPOND_IS_GET_LINK_DATA_PENDING_SESSION;
            pending_session_setup = pending_session_setup + pending_session_str;
        }

        String pending_session_str3 = link.getPendingSessionSetup3();
        if (pending_session_str3 != null) {
            pending_session_setup = pending_session_setup + FabricExport.WEB_FABRIC_PROTOCOL_RESPOND_IS_GET_LINK_DATA_PENDING_SESSION3;
            pending_session_setup = pending_session_setup + pending_session_str3;
        }

        downlink_data = downlink_data + pending_session_setup;

        String response_data = this.generateGetLinkDataResponse(FabricResults.SUCCEED, link.linkIdStr(), downlink_data, pending_session_setup);
        return response_data;
    }

    private String errorProcessGetLinkData(String link_id_val, String error_msg_val) {
        return error_msg_val;
    }

    public String generateGetLinkDataResponse(char result_val, String link_id_str_val, String data_val, String pending_session_setup_val) {
        StringBuilder response_buf = new StringBuilder();
        response_buf.append(FabricCommands.FABRIC_COMMAND_GET_LINK_DATA);
        response_buf.append(result_val);
        response_buf.append(link_id_str_val);
        response_buf.append(Encoders.sEncode2(data_val));
        response_buf.append(Encoders.sEncode2(pending_session_setup_val));
        return response_buf.toString();
    }
    
    private String processGetNameListRequest(String input_str_val) {
        this.debug(false, "processGetNameListRequest", "input_str_val = " + input_str_val);
        
        String rest_str = input_str_val;
        String link_id_str = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

        String name_list_tag_str = rest_str.substring(0, FabricExport.NAME_LIST_TAG_SIZE);
        rest_str = rest_str.substring(FabricExport.NAME_LIST_TAG_SIZE);
    	
        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            return this.errorProcessGetNameList(link_id_str, "*************null link");
        }

        int name_list_tag = Encoders.iDecode111(name_list_tag_str);
        String name_list = this.fabricRoot().nameList().getNameList(name_list_tag);

        String response_data = this.generateGetNameListResponse(FabricResults.SUCCEED, link.linkIdStr(), name_list);
        return response_data;
    }

    private String errorProcessGetNameList(String link_id_val, String error_msg_val) {
        return error_msg_val;
    }

    protected String generateGetNameListResponse(char result_val, String link_id_str_val, String name_list_str_val) {
        StringBuilder response_buf = new StringBuilder();
        response_buf.append(FabricCommands.FABRIC_COMMAND_GET_NAME_LIST);
        response_buf.append(result_val);
        response_buf.append(link_id_str_val);
        response_buf.append(Encoders.sEncode2(name_list_str_val));
        return response_buf.toString();
    }

    private String processSetupSessionRequest(String input_str_val) {
        this.debug(false, "processSetupSessionRequest", "input_str_val=" + input_str_val);

        String rest_str = input_str_val;
        String link_id_str = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

        String his_name = Encoders.sDecode2(rest_str);
        rest_str = Encoders.sDecode2_(rest_str);

        String theme_data_str = Encoders.sDecode2(rest_str);
        //rest_str = Encoders.sDecode2_(rest_str);

        this.debug(false, "processSetupSessionRequest", "link_id = " + link_id_str);
        this.debug(false, "processSetupSessionRequest", "his_name = " + his_name);
        this.debug(false, "processSetupSessionRequest", "theme_data = " + theme_data_str);

        String rest_str1 = input_str_val;
        String theme_id_str = Encoders.sSubstring2(rest_str1);
        rest_str1 = Encoders.sSubstring2_(rest_str1);

        String theme_data = rest_str1;
        theme_data = theme_data_str;

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            return this.errorProcessSetupSession(link_id_str, "*************null link");
        }

        FabricSession session = link.mallocSession();
        session.setBrowserThemeIdStr(theme_id_str);
        FabricGroup group = this.groupMgr().mallocGroup(theme_data);
        if (group == null) {
            this.abend("processSetupSessionRequest", "null group");
            return this.errorProcessSetupSession(link_id_str, "null group");
        }
        group.insertSession(session);
        session.bindGroup(group);

        if (his_name.equals(link.myName())) {
            this.fabricUBinder().sendMallocRoomRequestToThemeServer('G', group.groupIdStr(), theme_data_str);
        }
        else {
            FabricLink his_link = this.linkMgr().GetLinkByMyName(his_name);
            if (his_link == null) {
                return this.errorProcessSetupSession(link_id_str, "his_link does not exist");
            }
            FabricSession his_session = his_link.mallocSession();
            if (his_session == null) {
                return this.errorProcessSetupSession(link_id_str, "null his_session");
            }

            group.insertSession(his_session);
            his_session.bindGroup(group);

            his_link.setPendingSessionSetup(his_link.linkIdStr() + his_session.lSessionIdStr(), theme_data);
        }

        String response_data = this.generateSetupSessionResponse(FabricResults.SUCCEED, link.linkIdStr(), session.lSessionIdStr());
        return response_data;
    }

    private String errorProcessSetupSession(String link_id_val, String error_msg_val) {
        return error_msg_val;
    }

    protected String generateSetupSessionResponse(char result_val, String link_id_str_val, String session_id_str_val) {
        StringBuilder response_buf = new StringBuilder();
        response_buf.append(FabricCommands.FABRIC_COMMAND_SETUP_SESSION);
        response_buf.append(result_val);
        response_buf.append(link_id_str_val);
        response_buf.append(session_id_str_val);
        return response_buf.toString();
    }

    private String processSetupSession2Request(String input_str_val) {
        this.debug(false, "processSetupSession2Request", "input_str_val = " + input_str_val);
    	/////String accept_str;
        
        String rest_str = input_str_val;
        String link_id_str = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

        String session_id_str = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

        String theme_id_str = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

        String theme_data_str = Encoders.sDecode2(rest_str);
        //rest_str = Encoders.sDecode2_(rest_str);

        this.debug(false, "processSetupSession2Request", "link_id = " + link_id_str);
        this.debug(false, "processSetupSession2Request", "session_id = " + session_id_str);

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            return this.errorProcessSetupSession2(link_id_str, "null link");
        }
        
        FabricSession session = link.sessionMgr().getSessionByIdStr(session_id_str);
        if (session == null) {
            return errorProcessSetupSession2(link_id_str, "null session");
        }

        session.setBrowserThemeIdStr(theme_id_str);
        FabricGroup group = session.group();
        if (group == null) {
            return errorProcessSetupSession2(link_id_str, "null group");
        }
        this.fabricUBinder().sendMallocRoomRequestToThemeServer('G', group.groupIdStr(), theme_data_str);

        String response_data = this.generateSetupSession2Response(FabricResults.SUCCEED, link.linkIdStr(), session.lSessionIdStr(), session.browserThemeIdStr());
        return response_data;
    }

    private String errorProcessSetupSession2(String link_id_val, String error_msg_val) {
        return error_msg_val;
    }

    protected String generateSetupSession2Response(char result_val, String link_id_str_val, String session_id_str_val, String theme_id_str_val) {
        StringBuilder response_buf = new StringBuilder();
        response_buf.append(FabricCommands.FABRIC_COMMAND_SETUP_SESSION3);
        response_buf.append(result_val);
        response_buf.append(link_id_str_val);
        response_buf.append(session_id_str_val);
        response_buf.append(theme_id_str_val);
        return response_buf.toString();
    }

    private String processSetupSession3Request(String input_str_val) {
        this.debug(true, "processSetupSession3Request", "input_str_val = " + input_str_val);
        
        String rest_str = input_str_val;
        String link_id_str = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

        String session_id_str = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);
    	
        this.debug(false, "processSetupSession3Request", "link_id = " + link_id_str);
        this.debug(false, "processSetupSession3Request", "session_id = " + session_id_str);

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            return this.errorProcessSetupSession3(link_id_str, "null link");
        }
        
        FabricSession session = link.sessionMgr().getSessionByIdStr(session_id_str);
        if (session == null) {
            return errorProcessSetupSession3(link_id_str, "null session");
        }

        String response_data = this.generateSetupSession3Response(FabricResults.SUCCEED, link_id_str, session_id_str, session.browserThemeIdStr());
        return response_data;
    }

    private String errorProcessSetupSession3(String link_id_val, String error_msg_val) {
        return error_msg_val;
    }

    protected String generateSetupSession3Response(char result_val, String link_id_str_val, String session_id_str_val, String theme_id_str_val) {
        StringBuilder response_buf = new StringBuilder();
        response_buf.append(FabricCommands.FABRIC_COMMAND_SETUP_SESSION3);
        response_buf.append(result_val);
        response_buf.append(link_id_str_val);
        response_buf.append(session_id_str_val);
        response_buf.append(theme_id_str_val);
        return response_buf.toString();
    }
*/
    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.fabricRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { this.fabricRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
