/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.protocols.fabric;

import com.phwang.core.utils.encoders.Encoders;

public class FabricInfoStr {
    public static char   getCommand(String fabric_data_str_val)       { return fabric_data_str_val.charAt(FabricInfo.COMMAND_INDEX); }
    public static String getCommandStr(String fabric_data_str_val)    { return fabric_data_str_val.substring(FabricInfo.COMMAND_INDEX, FabricInfo.COMMAND_INDEX + 1); }

    public static char   getResult(String fabric_data_str_val)        { return fabric_data_str_val.charAt(FabricInfo.RESULT_INDEX); }
    public static String getResultStr(String fabric_data_str_val)     { return fabric_data_str_val.substring(FabricInfo.RESULT_INDEX, FabricInfo.RESULT_INDEX + 1); }

    public static char   getClientType(String fabric_data_str_val)    { return fabric_data_str_val.charAt(FabricInfo.CLIENT_TYPE_INDEX); }
    public static String getClientTypeStr(String fabric_data_str_val) { return fabric_data_str_val.substring(FabricInfo.CLIENT_TYPE_INDEX, FabricInfo.CLIENT_TYPE_INDEX + 1); }

    public static char   getThemeType(String fabric_data_str_val)     { return fabric_data_str_val.charAt(FabricInfo.THEME_TYPE_INDEX); }
    public static String getThemeTypeStr(String fabric_data_str_val)  { return fabric_data_str_val.substring(FabricInfo.THEME_TYPE_INDEX, FabricInfo.THEME_TYPE_INDEX + 1); }

    public static String getJobIdStr(String fabric_data_str_val)      { return Encoders.sSubstring2(fabric_data_str_val.substring(FabricInfo.JOB_ID_STR_INDEX)); }
}
