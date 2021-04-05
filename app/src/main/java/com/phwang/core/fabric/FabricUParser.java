/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.fabric;

import android.provider.MediaStore;

import com.phwang.core.utils.encoders.Encoders;
import com.phwang.core.utils.fabric.FabricData;
import com.phwang.core.utils.listmgr.ListEntry;
import com.phwang.core.utils.binder.BinderBundle;

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
        String response_data = null;
        String job_id_str = null;
        String input_str = bundle_val.data();
        this.debug(true, "parseInputPacket", "input_str = " + input_str);
        FabricData fabric_decode = new FabricData(input_str);

        String rest_str = input_str;
        if (rest_str.charAt(0) == FabricCommands.FABRIC_COMMAND_HTTP_DATA) {
            rest_str = rest_str.substring(1);
            job_id_str = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);
        }

        this.debug(false, "parseInputPacket", "data_str = " + rest_str);
        switch (fabric_decode.command()) {
            case FabricCommands.FABRIC_COMMAND_REGISTER:
                response_data = this.processRegisterRequest(fabric_decode);
                break;
            case FabricCommands.FABRIC_COMMAND_LOGIN:
                response_data = this.processLoginRequest(fabric_decode);
                break;
            case FabricCommands.FABRIC_COMMAND_LOGOUT:
                response_data = this.processLogoutRequest(fabric_decode);
                break;
            case FabricCommands.FABRIC_COMMAND_GET_GROUPS:
                response_data = this.processGetGroupsRequest(fabric_decode);
                break;
            case FabricCommands.FABRIC_COMMAND_GET_LINK_DATA:
                response_data = this.processGetLinkDataRequest(rest_str.substring(1));
                break;
            case FabricCommands.FABRIC_COMMAND_GET_NAME_LIST:
                response_data = this.processGetNameListRequest(rest_str.substring(1));
                break;
            case FabricCommands.FABRIC_COMMAND_SOLO_SESSION:
                response_data = this.processSoloSessionRequest(fabric_decode);
                break;
            case FabricCommands.FABRIC_COMMAND_HEAD_SESSION:
                response_data = this.processHeadSessionRequest(rest_str);
                break;
            case FabricCommands.FABRIC_COMMAND_PEER_SESSION:
                response_data = this.processPeerSessionRequest(rest_str);
                break;
            case FabricCommands.FABRIC_COMMAND_JOIN_SESSION:
                response_data = this.processJoinSessionRequest(rest_str);
                break;
            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION:
                response_data = this.processSetupSessionRequest(rest_str.substring(1));
                break;
            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION2:
                response_data = this.processSetupSession2Request(rest_str.substring(1));
                break;
            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION3:
                response_data = this.processSetupSession3Request(rest_str.substring(1));
                break;
            case FabricCommands.FABRIC_COMMAND_DELETE_SESSION:
                response_data = this.processDeleteSessionRequest(fabric_decode);
                break;
            case FabricCommands.FABRIC_COMMAND_PUT_SESSION_DATA:
                response_data = this.processPutSessionDataRequest(fabric_decode);
                break;
            case FabricCommands.FABRIC_COMMAND_GET_SESSION_DATA:
                response_data = this.processGetSessionDataRequest(fabric_decode);
                break;
            default:
                response_data = "*** Bad command! Fix it!";
        	    this.abend("parseInputPacket", "should not reach here, data=" + input_str);
        	    break;
        }

        if (job_id_str != null) {
            response_data = job_id_str + response_data;
        }
        bundle_val.setData(response_data);
        this.fabricDBinder().transmitBundleData(bundle_val);
    }

    private String generateFabricResponse(char command_val, char result_val, String link_id_str_val, String session_id_str_val, String data_str_val) {
        StringBuilder response_buf = new StringBuilder();
        response_buf.append(link_id_str_val);
        response_buf.append(session_id_str_val);
        response_buf.append(data_str_val);
        String data_package_str = Encoders.sEncode5(response_buf.toString());

        response_buf = new StringBuilder();
        response_buf.append(command_val);
        response_buf.append(result_val);
        response_buf.append(data_package_str);
        return response_buf.toString();
    }

    private String generateFabricData0(char command_val, char result_val, char  client_type_val, char theme_val, String link_id_str_val, String session_id_str_val) {
        FabricData fabric_encode = new FabricData(command_val, result_val, client_type_val, theme_val, link_id_str_val, session_id_str_val, 0);
        return fabric_encode.getEncodedString();
    }

    private String generateFabricData1(char command_val, char result_val, char  client_type_val, char theme_val, String link_id_str_val, String session_id_str_val, String str0_val) {
        FabricData fabric_encode = new FabricData(command_val, result_val, client_type_val, theme_val, link_id_str_val, session_id_str_val, 1);
        fabric_encode.setStringList(0, str0_val);
        return fabric_encode.getEncodedString();
    }

    private String generateFabricData2(char command_val, char result_val, char  client_type_val, char theme_val, String link_id_str_val, String session_id_str_val, String str0_val, String str1_val) {
        FabricData fabric_encode = new FabricData(command_val, result_val, client_type_val, theme_val, link_id_str_val, session_id_str_val, 2);
        fabric_encode.setStringList(0, str0_val);
        fabric_encode.setStringList(1, str1_val);
        return fabric_encode.getEncodedString();
    }

    private String processRegisterRequest(FabricData fabric_decode_val) {
        this.debug(true, "processRegisterRequest", "");
        FabricData fabric_decode = fabric_decode_val;

        String my_name = fabric_decode.stringList(0);
        String email = fabric_decode.stringList(1);
        String password = fabric_decode.stringList(1);
        this.debug(true, "processRegisterRequest", "my_name=" + my_name);
        this.debug(true, "processRegisterRequest", "email=" + email);
        this.debug(true, "processRegisterRequest", "password=" + password);

        return this.generateFabricData0(fabric_decode.command(), FabricResults.SUCCEED, fabric_decode.clientType(), fabric_decode.theme(), Encoders.IGNORE, Encoders.IGNORE);
    }

    private String processLoginRequest(FabricData fabric_data_val) {
        this.debug(false, "processLoginRequest", "");
        FabricData fabric_data = fabric_data_val;

        char client_type = fabric_data.clientType();
        String my_name = fabric_data.stringList(0);
        String password = fabric_data.stringList(1);
        this.debug(true, "processDeleteSessionRequest", "my_name=" + my_name);
        this.debug(true, "processDeleteSessionRequest", "password=" + password);

        FabricLink link = this.linkMgr().mallocLink(client_type, my_name);
        if (link == null) {
            fabric_data.setResult(FabricResults.BAD_PASSWORD);
            return fabric_data.getEncodedString();
        }

        fabric_data.setResult(FabricResults.SUCCEED);
        fabric_data.setLinkIdStr(link.linkIdStr());
        return fabric_data.getEncodedString();
    }

    private String processLogoutRequest(FabricData fabric_decode_val) {
        this.debug(true, "processLogoutRequest", "input_str_val");
        FabricData fabric_decode = fabric_decode_val;

        String link_id_str = fabric_decode.linkIdStr();
        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            return this.generateFabricData0(fabric_decode.command(), FabricResults.LINK_NOT_EXIST, fabric_decode.clientType(), fabric_decode.theme(), link_id_str, Encoders.IGNORE);
        }
        this.debug(false, "processLogoutRequest", "link_id = " + link_id_str);

        return this.generateFabricData0(fabric_decode.command(), FabricResults.SUCCEED, fabric_decode.clientType(), fabric_decode.theme(), link_id_str, Encoders.IGNORE);
    }

    private String processGetGroupsRequest(FabricData fabric_decode_val) {
        this.debug(true, "processGetGroupsRequest", "");
        FabricData fabric_decode = fabric_decode_val;

        String link_id_str = fabric_decode.linkIdStr();
        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            return this.generateFabricData0(fabric_decode.command(), FabricResults.LINK_NOT_EXIST, fabric_decode.clientType(), fabric_decode.theme(), link_id_str, Encoders.IGNORE);
        }
        this.debug(false, "processGetGroupsRequest", "link_id = " + link_id_str);

        return this.generateFabricData1(fabric_decode.command(), FabricResults.SUCCEED, fabric_decode.clientType(), fabric_decode.theme(), link_id_str, Encoders.IGNORE, Encoders.NULL_DATA);
    }

    private String processSoloSessionRequest(FabricData fabric_decode_val) {
        this.debug(true, "processSoloSessionRequest", "");
        FabricData fabric_decode = fabric_decode_val;

        String link_id_str = fabric_decode.linkIdStr();
        String theme_data_str = fabric_decode.stringList(0);


        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            return this.generateFabricData0(fabric_decode.command(), FabricResults.LINK_NOT_EXIST, fabric_decode.clientType(), fabric_decode.theme(), link_id_str, Encoders.IGNORE);
        }

        this.debug(true, "processSoloSessionRequest", "link_id = " + link_id_str);
        this.debug(true, "processSoloSessionRequest", "theme_data = " + theme_data_str);

        FabricSession session = link.mallocSession();
        FabricGroup group = this.groupMgr().mallocGroup(theme_data_str);
        group.insertSession(session);
        session.bindGroup(group);

        this.mallocRoom(group, theme_data_str);

        return this.generateFabricData1(fabric_decode.command(), FabricResults.SUCCEED, fabric_decode.clientType(), fabric_decode.theme(), link_id_str, session.lSessionIdStr(), theme_data_str);
    }

    private String processDeleteSessionRequest(FabricData fabric_decode_val) {
        this.debug(true, "processDeleteSessionRequest", "");
        FabricData fabric_decode = fabric_decode_val;

        String link_id_str = fabric_decode.linkIdStr();
        String session_id_str = fabric_decode.sessionIdStr();

        this.debug(false, "processDeleteSessionRequest", "link_id=" + link_id_str);
        this.debug(false, "processDeleteSessionRequest", "session_id=" + session_id_str);

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            return this.generateFabricData0(fabric_decode.command(), FabricResults.LINK_NOT_EXIST, fabric_decode.clientType(), fabric_decode.theme(), link_id_str, session_id_str);
        }

        FabricSession session = link.sessionMgr().getSessionByIdStr(session_id_str);
        if (session == null) {
            return this.generateFabricData0(fabric_decode.command(), FabricResults.SESSION_NOT_EXIST, fabric_decode.clientType(), fabric_decode.theme(), link_id_str, session_id_str);
        }

        link.sessionMgr().freeSession(session);

        return this.generateFabricData0(fabric_decode.command(), FabricResults.SUCCEED, fabric_decode.clientType(), fabric_decode.theme(), link_id_str, session_id_str);
    }

    private String processPutSessionDataRequest(FabricData fabric_decode_val) {
        this.debug(false, "processPutSessionDataRequest", "");
        FabricData fabric_decode = fabric_decode_val;

        String link_id_str = fabric_decode.linkIdStr();
        String session_id_str = fabric_decode.sessionIdStr();

        this.debug(false, "processPutSessionDataRequest", "link_id=" + link_id_str);
        this.debug(false, "processPutSessionDataRequest", "session_id=" + session_id_str);
        this.debug(true, "processPutSessionDataRequest", "data=" + fabric_decode.stringList(0));

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            return this.generateFabricData0(fabric_decode.command(), FabricResults.LINK_NOT_EXIST, fabric_decode.clientType(), fabric_decode.theme(), link_id_str, session_id_str);
        }

        FabricSession session = link.sessionMgr().getSessionByIdStr(session_id_str);
        if (session == null) {
            return this.generateFabricData0(fabric_decode.command(), FabricResults.SESSION_NOT_EXIST, fabric_decode.clientType(), fabric_decode.theme(), link_id_str, session_id_str);
        }

        String room_id_str = session.group().roomIdStr();
        if (room_id_str == null) {
            return this.generateFabricData0(fabric_decode.command(), FabricResults.ROOM_NOT_EXIST, fabric_decode.clientType(), fabric_decode.theme(), link_id_str, session_id_str);
        }

        /* transfer data up */
        StringBuilder buf = new StringBuilder();
        buf.append(FabricExport.FABRIC_THEME_COMMAND_PUT_ROOM_DATA);
        buf.append(room_id_str);
        buf.append(fabric_decode.stringList(0));
        this.fabricUBinder().transmitData(buf.toString());

        return this.generateFabricData0(fabric_decode.command(), FabricResults.SUCCEED, fabric_decode.clientType(), fabric_decode.theme(), link_id_str, session_id_str);
    }

    private String processGetSessionDataRequest(FabricData fabric_decode_val) {
        this.debug(false, "processPutSessionDataRequest", "");
        FabricData fabric_data = fabric_decode_val;

        String link_id_str = fabric_data.linkIdStr();
        String session_id_str = fabric_data.sessionIdStr();

        this.debug(false, "processGetSessionDataRequest", "link_id=" + link_id_str);
        this.debug(false, "processGetSessionDataRequest", "session_id=" + session_id_str);

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            fabric_data.setResult(FabricResults.LINK_NOT_EXIST);
            return fabric_data.getEncodedString();
        }

        FabricSession session = link.sessionMgr().getSessionByIdStr(session_id_str);
        if (session == null) {
             fabric_data.setResult(FabricResults.SESSION_NOT_EXIST);
            return fabric_data.getEncodedString();
        }

        String data = session.getPendingDownLinkData();

        fabric_data.addStringList(data);
        fabric_data.setResult(FabricResults.SUCCEED);
        return fabric_data.getEncodedString();
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

    private String processHeadSessionRequest(String input_str_val) {
        this.debug(true, "processHeadSessionRequest", "input_str_val=" + input_str_val);

        String rest_str = input_str_val.substring(1);
        String link_id_str = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

        String data_str = Encoders.sSubstring5(rest_str);
        //rest_str = Encoders.sDecode5_(rest_str);

        this.debug(true, "processHeadSessionRequest", "link_id = " + link_id_str);
        this.debug(true, "processHeadSessionRequest", "theme_data = " + data_str);

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            return this.generateFabricResponse(input_str_val.charAt(0), FabricResults.LINK_NOT_EXIST, link_id_str, Encoders.NULL_SESSION, data_str);
        }

        FabricSession session = link.mallocSession();
        FabricGroup group = this.groupMgr().mallocGroup(data_str);
        group.insertSession(session);
        session.bindGroup(group);

        this.mallocRoom(group, data_str);

        String response_data = this.generateFabricResponse(input_str_val.charAt(0), FabricResults.SUCCEED, link_id_str, session.lSessionIdStr(), data_str);
        return response_data;
    }

    private String processPeerSessionRequest(String input_str_val) {
        this.debug(true, "processPeerSessionRequest", "input_str_val=" + input_str_val);

        String rest_str = input_str_val.substring(1);
        String link_id_str = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

        String data_str = Encoders.sSubstring5(rest_str);
        //rest_str = Encoders.sDecode5_(rest_str);

        this.debug(true, "processPeerSessionRequest", "link_id = " + link_id_str);
        this.debug(true, "processPeerSessionRequest", "theme_data = " + data_str);

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            return this.generateFabricResponse(input_str_val.charAt(0), FabricResults.LINK_NOT_EXIST, link_id_str, Encoders.NULL_SESSION, data_str);
        }

        FabricSession session = link.mallocSession();
        FabricGroup group = this.groupMgr().mallocGroup(data_str);
        group.insertSession(session);
        session.bindGroup(group);

        this.mallocRoom(group, data_str);

        String response_data = this.generateFabricResponse(input_str_val.charAt(0), FabricResults.SUCCEED, link_id_str, session.lSessionIdStr(), data_str);
        return response_data;
    }

    private String processJoinSessionRequest(String input_str_val) {
        this.debug(true, "processJoinSessionRequest", "input_str_val=" + input_str_val);

        String rest_str = input_str_val.substring(1);
        String link_id_str = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

        String data_str = Encoders.sSubstring5(rest_str);
        //rest_str = Encoders.sDecode5_(rest_str);

        this.debug(true, "processJoinSessionRequest", "link_id = " + link_id_str);
        this.debug(true, "processJoinSessionRequest", "theme_data = " + data_str);

        FabricLink link = this.linkMgr().getLinkByIdStr(link_id_str);
        if (link == null) {
            return this.generateFabricResponse(input_str_val.charAt(0), FabricResults.LINK_NOT_EXIST, link_id_str, Encoders.NULL_SESSION, data_str);
        }

        FabricSession session = link.mallocSession();
        FabricGroup group = this.groupMgr().mallocGroup(data_str);
        group.insertSession(session);
        session.bindGroup(group);

        this.mallocRoom(group, data_str);

        String response_data = this.generateFabricResponse(input_str_val.charAt(0), FabricResults.SUCCEED, link_id_str, session.lSessionIdStr(), data_str);
        return response_data;
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
            this.mallocRoom(group, theme_data);
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

    private void mallocRoom(FabricGroup group_val, String theme_info_val) {
        this.debug(false, "mallocRoom", "theme_info_val=" + theme_info_val);
    	
        StringBuilder data_buf = new StringBuilder();
        data_buf.append(FabricExport.FABRIC_THEME_COMMAND_SETUP_ROOM);
        data_buf.append(group_val.groupIdStr());
        data_buf.append(theme_info_val);
        String uplink_data = data_buf.toString();
        this.fabricRoot().fabricUBinder().transmitData(uplink_data);
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
        this.mallocRoom(group, theme_data_str);

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

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.fabricRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { this.fabricRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
