/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.client;

import com.phwang.core.fabric.FabricExport;
import com.phwang.core.fabric.FabricCommands;

public class ClientImport {
    protected static final String FABRIC_ANDROID_SERVER_IP_ADDRESS = "127.0.0.1";
    protected static final short FABRIC_ANDROID_PORT = FabricExport.FABRIC_ANDROID_PORT;

    public static final char CLIENT_IS_ANDROID = FabricCommands.CLIENT_IS_ANDROID;

    protected static final char FABRIC_COMMAND_REGISTER         = FabricCommands.FABRIC_COMMAND_REGISTER;
    protected static final char FABRIC_COMMAND_LOGIN            = FabricCommands.FABRIC_COMMAND_LOGIN;
    protected static final char FABRIC_COMMAND_LOGOUT           = FabricCommands.FABRIC_COMMAND_LOGOUT;
    protected static final char FABRIC_COMMAND_GET_GROUPS       = FabricCommands.FABRIC_COMMAND_GET_GROUPS;
    protected static final char FABRIC_COMMAND_GET_LINK_DATA    = FabricCommands.FABRIC_COMMAND_GET_LINK_DATA;
    protected static final char FABRIC_COMMAND_GET_NAME_LIST    = FabricCommands.FABRIC_COMMAND_GET_NAME_LIST;
    protected static final char FABRIC_COMMAND_SOLO_SESSION     = FabricCommands.FABRIC_COMMAND_SOLO_SESSION;
    protected static final char FABRIC_COMMAND_HEAD_SESSION     = FabricCommands.FABRIC_COMMAND_HEAD_SESSION;
    protected static final char FABRIC_COMMAND_PEER_SESSION     = FabricCommands.FABRIC_COMMAND_PEER_SESSION;
    protected static final char FABRIC_COMMAND_JOIN_SESSION     = FabricCommands.FABRIC_COMMAND_JOIN_SESSION;
    protected static final char FABRIC_COMMAND_SETUP_SESSION    = FabricCommands.FABRIC_COMMAND_SETUP_SESSION;
    protected static final char FABRIC_COMMAND_SETUP_SESSION2   = FabricCommands.FABRIC_COMMAND_SETUP_SESSION2;
    protected static final char FABRIC_COMMAND_SETUP_SESSION3   = FabricCommands.FABRIC_COMMAND_SETUP_SESSION3;
    protected static final char FABRIC_COMMAND_PUT_SESSION_DATA = FabricCommands.FABRIC_COMMAND_PUT_SESSION_DATA;
    protected static final char FABRIC_COMMAND_GET_SESSION_DATA = FabricCommands.FABRIC_COMMAND_GET_SESSION_DATA;
}
