/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.fabric;

public class FabricExport {
    public static final String FABRIC_THEME_IP_ADDRESS = "127.0.0.1";
    
    public static final short FABRIC_ANDROID_PORT = 8001;
    public static final short FABRIC_FRONT_PORT = 8001;
    public static final short FABRIC_THEME_PORT = 8009;

    public static final int NAME_LIST_TAG_SIZE = FabricNameList.NAME_LIST_TAG_SIZE;

    public static final String WEB_FABRIC_PROTOCOL_RESPOND_IS_GET_LINK_DATA_PENDING_SESSION = "S";
    public static final String WEB_FABRIC_PROTOCOL_RESPOND_IS_GET_LINK_DATA_PENDING_SESSION3 = "T";
    public static final String WEB_FABRIC_PROTOCOL_RESPOND_IS_GET_LINK_DATA_PENDING_DATA = "D";
    public static final String WEB_FABRIC_PROTOCOL_RESPOND_IS_GET_LINK_DATA_NAME_LIST = "N";

    public static final char FABRIC_THEME_COMMAND_SETUP_ROOM = 'R';
    public static final char FABRIC_THEME_RESPOND_SETUP_ROOM = 'r';
    public static final char FABRIC_THEME_COMMAND_PUT_ROOM_DATA = 'D';
    public static final char FABRIC_THEME_RESPOND_PUT_ROOM_DATA = 'd';
}
