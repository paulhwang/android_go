/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.services;

import android.content.Intent;
import android.util.Log;
import com.phwang.core.utils.fabric.FabricCommands;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;

public class ClientDParser {
    private static final String TAG = "ClientDParser";

    private ClientRoot clientRoot_;

    private ClientService clientService() { return this.clientRoot_.clientService(); }

    protected ClientDParser(ClientRoot root_val) {
        this.clientRoot_ = root_val;
    }
    
    protected void parserResponseData(String response_data_str_val) {
    	Log.e(TAG, "parserResponseData() response_data=" + response_data_str_val);
    	
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
    		    Log.e(TAG, "parserResponseData() response_data_str_val=" + response_data_str_val);
    		    break;
    	}
    }

    protected void sendFabricDataResponse(String target_val, String fabric_data_str_val) {
        Log.e(TAG, "sendFabricDataResponse() fabric_data_str_val=" + fabric_data_str_val);

        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.CLIENT_SERVICE);
        intent.putExtra(BundleIndexDefine.FABRIC_DATA, fabric_data_str_val);
        intent.setAction(target_val);
        this.clientService().sendBroadcast(intent);
    }
}
