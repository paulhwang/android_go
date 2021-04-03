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
import com.phwang.client.ClientFabricInfo;
import com.phwang.core.fabric.FabricCommands;
import com.phwang.core.fabric.FabricResultExport;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.IntentDefine;

public class BindReceiverUFunc {
    private static final String TAG = "BindReceiverUFunc";
    BindReceiver bindReceiver_;

    private BindService bindService() { return this.bindReceiver_.bindService(); };
    private BindDClient bindDClient() { return bindService().bindDClient(); }
    public ClientDExport clientDExport() { return this.bindService().clientDExport(); }
    public ClientFabricInfo clientFabricInfo() { return this.bindService().clientFabricInfo();}

    public BindReceiverUFunc(BindReceiver bind_receiver_val) {
        this.bindReceiver_ = bind_receiver_val;
    }

    protected void handleCommand(Bundle bundle_val) {
        String command = bundle_val.getString(BundleIndexDefine.COMMAND);
        String data_str;
        String theme_data;
        String my_name;
        String email;
        String password;
        String link_id_str;
        String session_id_str;

        Log.e(TAG, "handleCommand() command=" + command);

        if (command == null) {
            Log.e(TAG, "handleCommand() null command=");
            return;
        }

        switch (command.charAt(0)) {
            case FabricCommands.FABRIC_COMMAND_LOGIN:
                my_name = bundle_val.getString(BundleIndexDefine.MY_NAME);
                password = bundle_val.getString(BundleIndexDefine.PASSWORD);
                Log.e(TAG, "handleReceivedBundle() command=" + command + " name=" + my_name + "," + password);
                this.clientDExport().doLogin(my_name, password);
                break;

            case FabricCommands.FABRIC_COMMAND_LOGOUT:
                Log.e(TAG, "handleReceivedBundle() command=" + command);
                this.clientDExport().doLogout();
                break;

            case FabricCommands.FABRIC_COMMAND_REGISTER:
                my_name = bundle_val.getString(BundleIndexDefine.MY_NAME);
                email = bundle_val.getString(BundleIndexDefine.EMAIL);
                password = bundle_val.getString(BundleIndexDefine.PASSWORD);
                Log.e(TAG, "handleReceivedBundle() command=" + command);
                this.clientDExport().doRegister(my_name, password, email);
                break;

            case FabricCommands.FABRIC_COMMAND_GET_GROUPS:
                Log.e(TAG, "handleReceivedBundle() command=" + command);
                this.clientDExport().doGetGroups();
                break;

            case FabricCommands.FABRIC_COMMAND_SOLO_SESSION:
                theme_data = bundle_val.getString(BundleIndexDefine.THEME_DATA);
                Log.e(TAG, "handleCommand(FABRIC_COMMAND_SOLO_SESSION) data=" + theme_data);

                if (this.clientFabricInfo().linkIdStr() == null) {
                    this.bindDClient().sendBroadcastMessageOld(
                            IntentDefine.BIND_SERVICE,
                            FabricCommands.FABRIC_COMMAND_SOLO_SESSION_STR,
                            FabricResultExport.LINK_NOT_EXIST_STR,
                            null);
                    return;
                }

                this.clientDExport().setupSoloSession(theme_data);
                break;

            case FabricCommands.FABRIC_COMMAND_HEAD_SESSION:
                theme_data = bundle_val.getString(BundleIndexDefine.THEME_DATA);
                Log.e(TAG, "handleCommand(FABRIC_COMMAND_SOLO_SESSION) data=" + theme_data);

                if (this.clientFabricInfo().linkIdStr() == null) {
                    this.bindDClient().sendBroadcastMessageOld(
                            IntentDefine.BIND_SERVICE,
                            FabricCommands.FABRIC_COMMAND_HEAD_SESSION_STR,
                            FabricResultExport.LINK_NOT_EXIST_STR,
                            null);
                    return;
                }

                this.clientDExport().setupHeadSession(theme_data);
                break;

            case FabricCommands.FABRIC_COMMAND_PEER_SESSION:
                theme_data = bundle_val.getString(BundleIndexDefine.THEME_DATA);
                Log.e(TAG, "handleCommand(FABRIC_COMMAND_SOLO_SESSION) data=" + theme_data);

                if (this.clientFabricInfo().linkIdStr() == null) {
                    this.bindDClient().sendBroadcastMessageOld(
                            IntentDefine.BIND_SERVICE,
                            FabricCommands.FABRIC_COMMAND_PEER_SESSION_STR,
                            FabricResultExport.LINK_NOT_EXIST_STR,
                            null);
                    return;
                }

                this.clientDExport().setupPeerSession(theme_data);
                break;

            case FabricCommands.FABRIC_COMMAND_JOIN_SESSION:
                theme_data = bundle_val.getString(BundleIndexDefine.THEME_DATA);
                Log.e(TAG, "handleCommand(FABRIC_COMMAND_SOLO_SESSION) data=" + theme_data);

                if (this.clientFabricInfo().linkIdStr() == null) {
                    this.bindDClient().sendBroadcastMessageOld(
                            IntentDefine.BIND_SERVICE,
                            FabricCommands.FABRIC_COMMAND_JOIN_SESSION_STR,
                            FabricResultExport.LINK_NOT_EXIST_STR,
                            null);
                    return;
                }

                this.clientDExport().setupJoinSession(theme_data);
                break;

            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION:
                String his_name = bundle_val.getString(BundleIndexDefine.HIS_NAME);
                theme_data = bundle_val.getString(BundleIndexDefine.THEME_DATA);

                if (this.clientFabricInfo().linkIdStr() == null) {
                    this.bindDClient().sendBroadcastMessageOld(
                            IntentDefine.BIND_SERVICE,
                            FabricCommands.FABRIC_COMMAND_SETUP_SESSION_STR,
                            FabricResultExport.LINK_NOT_EXIST_STR,
                            null);

                    return;
                }
                this.clientDExport().setupSession(his_name, theme_data);
                break;

            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION3:
                this.clientDExport().setupSession3();
                break;

            case FabricCommands.FABRIC_COMMAND_DELETE_SESSION:
                link_id_str = bundle_val.getString(BundleIndexDefine.LINK_ID);
                session_id_str = bundle_val.getString(BundleIndexDefine.SESSION_ID);
                //this.bindUClient().deleteSession(link_id_str, session_id_str);
                this.clientDExport().deleteSession(link_id_str, session_id_str);;
                break;

            case FabricCommands.FABRIC_COMMAND_PUT_SESSION_DATA:
                String move_data_str = bundle_val.getString(BundleIndexDefine.MOVE_DATA);
                link_id_str = bundle_val.getString(BundleIndexDefine.LINK_ID);
                session_id_str = bundle_val.getString(BundleIndexDefine.SESSION_ID);
                this.clientDExport().putSessionData(link_id_str, session_id_str, move_data_str);;
                break;

            case FabricCommands.FABRIC_COMMAND_GET_SESSION_DATA:
                link_id_str = bundle_val.getString(BundleIndexDefine.LINK_ID);
                session_id_str = bundle_val.getString(BundleIndexDefine.SESSION_ID);
                this.clientDExport().getSessionData(link_id_str, session_id_str);;
                break;

            default:
                Log.e(TAG, "handleCommand() command=" + command + " not implemented*********************");
        }
    }
}
