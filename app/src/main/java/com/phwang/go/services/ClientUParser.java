/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.services;

import android.os.Bundle;
import android.util.Log;

import com.phwang.core.fabric.FabricCommands;
import com.phwang.core.fabric.FabricResults;
import com.phwang.core.utils.binder.Binder;
import com.phwang.core.utils.fabric.FabricData;
import com.phwang.core.utils.fabric.FabricDataStr;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.global.GlobalData;

public class ClientUParser {
    private static final String TAG = "ClientUParser";
    private ClientRoot clientRoot_;

    private ClientDParser clientDParser() { return this.clientRoot_.clientDParser(); }
    private ClientUBinder clientUBinder() { return this.clientRoot_.clientUBinder(); }
    private Binder uBinder() { return this.clientUBinder().uBinder(); }

    public ClientUParser(ClientRoot client_root_val) {
        this.clientRoot_ = client_root_val;
    }

    protected void parseUCommand(String fabric_data_str_val) {
        Log.e(TAG, "parseUCommand() fabric_data_str=" + fabric_data_str_val);

        switch (FabricDataStr.getCommand(fabric_data_str_val)) {
            case FabricCommands.FABRIC_COMMAND_SOLO_SESSION:
            case FabricCommands.FABRIC_COMMAND_HEAD_SESSION:
            case FabricCommands.FABRIC_COMMAND_PEER_SESSION:
            case FabricCommands.FABRIC_COMMAND_JOIN_SESSION:
                if (GlobalData.linkIdStr() == null) {
                    FabricData fabric_data = new FabricData(fabric_data_str_val);
                    fabric_data.setResult(FabricResults.LINK_NOT_EXIST);
                    String new_fabric_data_str = fabric_data.getEncodedString();
                    this.clientDParser().sendFabricDataResponse(IntentDefine.GO_CONFIG_ACTIVITY, new_fabric_data_str);
                    return;
                }
            case FabricCommands.FABRIC_COMMAND_REGISTER:
            case FabricCommands.FABRIC_COMMAND_LOGIN:
            case FabricCommands.FABRIC_COMMAND_LOGOUT:
            case FabricCommands.FABRIC_COMMAND_GET_GROUPS:
            case FabricCommands.FABRIC_COMMAND_DELETE_SESSION:
            case FabricCommands.FABRIC_COMMAND_PUT_SESSION_DATA:
            case FabricCommands.FABRIC_COMMAND_GET_SESSION_DATA:
                this.transmitToFabric(fabric_data_str_val);
                break;

            default:
                Log.e(TAG, "handleCommand(not implemented) fabric_data_str=" + fabric_data_str_val);
        }
    }

    public void transmitToFabric(String data_str_val) {
        Log.e(TAG,  "transmitToFabric() data_str_val=" + data_str_val);
        this.uBinder().transmitStringData(data_str_val);
    }
}
