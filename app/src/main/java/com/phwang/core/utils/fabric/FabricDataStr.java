/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.utils.fabric;

public class FabricDataStr {
    public static char   getCommand(String fabric_data_str_val)       { return fabric_data_str_val.charAt(FabricData.COMMAND_INDEX); }
    public static String getCommandStr(String fabric_data_str_val)    { return fabric_data_str_val.substring(FabricData.COMMAND_INDEX, FabricData.COMMAND_INDEX + 1); }

    public static char   getResult(String fabric_data_str_val)        { return fabric_data_str_val.charAt(FabricData.RESULT_INDEX); }
    public static String getResultStr(String fabric_data_str_val)     { return fabric_data_str_val.substring(FabricData.RESULT_INDEX, FabricData.RESULT_INDEX + 1); }

    public static char   getClientType(String fabric_data_str_val)    { return fabric_data_str_val.charAt(FabricData.CLIENT_TYPE_INDEX); }
    public static String getClientTypeStr(String fabric_data_str_val) { return fabric_data_str_val.substring(FabricData.CLIENT_TYPE_INDEX, FabricData.CLIENT_TYPE_INDEX + 1); }

    public static char   getTheme(String fabric_data_str_val)         { return fabric_data_str_val.charAt(FabricData.THEME_INDEX); }
    public static String getThemeStr(String fabric_data_str_val)      { return fabric_data_str_val.substring(FabricData.THEME_INDEX, FabricData.THEME_INDEX + 1); }
}
