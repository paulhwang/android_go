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
import com.phwang.client.ClientDExport;
import com.phwang.core.fabric.FabricCommands;
import com.phwang.core.fabric.FabricResults;
import com.phwang.core.utils.fabric.FabricData;
import com.phwang.core.utils.fabric.FabricDataStr;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;
import com.phwang.go.global.GlobalData;

public class BindReceiverUFunc {
    private static final String TAG = "BindReceiverUFunc";
    BindReceiver bindReceiver_;

    private BindService bindService() { return this.bindReceiver_.bindService(); };
    public ClientDExport clientDExport() { return this.bindService().clientDExport(); }
    public BindReceiverDFunc bindReceiverDFunc() { return this.bindService().bindReceiverDFunc(); }

    public BindReceiverUFunc(BindReceiver bind_receiver_val) {
        this.bindReceiver_ = bind_receiver_val;
    }

    protected void handleCommand(Bundle bundle_val) {
        String command = bundle_val.getString(BundleIndexDefine.COMMAND);
        String fabric_data_str = bundle_val.getString(BundleIndexDefine.FABRIC_DATA);

        Log.e(TAG, "handleCommand() fabric_data_str=" + fabric_data_str);

        switch (FabricDataStr.getCommand(fabric_data_str)) {
            case FabricCommands.FABRIC_COMMAND_SOLO_SESSION:
            case FabricCommands.FABRIC_COMMAND_HEAD_SESSION:
            case FabricCommands.FABRIC_COMMAND_PEER_SESSION:
            case FabricCommands.FABRIC_COMMAND_JOIN_SESSION:
                if (GlobalData.linkIdStr() == null) {
                    FabricData fabric_data = new FabricData(fabric_data_str);
                    fabric_data.setResult(FabricResults.LINK_NOT_EXIST);
                    String new_fabric_data_str = fabric_data.getEncodedString();
                    this.bindReceiverDFunc().sendFabricDataResponse(IntentDefine.GO_CONFIG_ACTIVITY, new_fabric_data_str);
                    return;
                }
            case FabricCommands.FABRIC_COMMAND_REGISTER:
            case FabricCommands.FABRIC_COMMAND_LOGIN:
            case FabricCommands.FABRIC_COMMAND_LOGOUT:
            case FabricCommands.FABRIC_COMMAND_GET_GROUPS:
            case FabricCommands.FABRIC_COMMAND_DELETE_SESSION:
            case FabricCommands.FABRIC_COMMAND_PUT_SESSION_DATA:
            case FabricCommands.FABRIC_COMMAND_GET_SESSION_DATA:
                this.clientDExport().transmitToFabric(fabric_data_str);
                break;

            default:
                Log.e(TAG, "handleCommand(not implemented) fabric_data_str=" + fabric_data_str);
        }
    }
}
