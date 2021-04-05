/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.client;

import android.content.Intent;
import android.util.Log;

import com.phwang.core.fabric.FabricCommands;
import com.phwang.core.fabric.FabricResults;
import com.phwang.core.utils.encoders.Encoders;
import com.phwang.core.utils.fabric.FabricData;
import com.phwang.core.utils.fabric.FabricDataStr;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.services.BindReceiverDFunc;
import com.phwang.go.services.BindService;

import java.util.Base64;

public class ClientDParser {
    private static final String TAG = "ClientDParser";
    private String objectName() {return "ClientDParser";}
    
    private ClientRoot clientRoot_;

    protected BindService bindService() { return this.clientRoot_.bindService(); }
    protected BindReceiverDFunc bindReceiverDFunc() { return this.bindService().bindReceiverDFunc(); }
    protected ClientRoot clientRoot() { return this.clientRoot_; }

    protected ClientDParser(ClientRoot root_val) {
        this.debug(false, "ClientDParser", "init start");
        this.clientRoot_ = root_val;
    }
    
    protected void parserResponseData(String response_data_str_val) {
    	this.debug(true, "parserResponseData", "response_data=" + response_data_str_val);
    	
    	switch (response_data_str_val.charAt(0)) {
            case FabricCommands.FABRIC_COMMAND_LOGIN:
                this.sendFabricDataResponse(IntentDefine.LOGIN_ACTIVITY, response_data_str_val);
                break;

            case FabricCommands.FABRIC_COMMAND_REGISTER:
            case FabricCommands.FABRIC_COMMAND_LOGOUT:
            case FabricCommands.FABRIC_COMMAND_GET_GROUPS:
                this.sendFabricDataResponse(IntentDefine.MAIN_ACTIVITY, response_data_str_val);
                break;

            case FabricCommands.FABRIC_COMMAND_DELETE_SESSION:
            case FabricCommands.FABRIC_COMMAND_PUT_SESSION_DATA:
            case FabricCommands.FABRIC_COMMAND_GET_SESSION_DATA:
                this.sendFabricDataResponse(IntentDefine.GO_GAME_ACTIVITY, response_data_str_val);
                break;

            case FabricCommands.FABRIC_COMMAND_SOLO_SESSION:
            case FabricCommands.FABRIC_COMMAND_HEAD_SESSION:
            case FabricCommands.FABRIC_COMMAND_PEER_SESSION:
            case FabricCommands.FABRIC_COMMAND_JOIN_SESSION:
                this.sendFabricDataResponse(IntentDefine.GO_CONFIG_ACTIVITY, response_data_str_val);
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

    public void sendFabricDataResponse(String target_val, String fabric_data_str_val) {
        Log.e(TAG, "sendFabricDataResponse() fabric_data_str_val=" + fabric_data_str_val);

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.BIND_SERVICE);
        intent.putExtra(BundleIndexDefine.COMMAND, FabricDataStr.getCommandStr(fabric_data_str_val));
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_data_str_val);
        intent.setAction(target_val);
        this.bindService().sendBroadcast(intent);
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.clientRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { this.clientRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
