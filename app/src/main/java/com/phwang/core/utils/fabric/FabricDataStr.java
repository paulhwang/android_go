/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.utils.fabric;

public class FabricDataStr {
    public static char   getCommand(String fabric_data_str_val)    { return fabric_data_str_val.charAt(0); }
    public static String getCommandStr(String fabric_data_str_val) { return fabric_data_str_val.substring(0, 1); }

    public static char   getResult(String fabric_data_str_val)     { return fabric_data_str_val.charAt(1); }
    public static String getResultStr(String fabric_data_str_val)  { return fabric_data_str_val.substring(1, 2); }

    public static char   getTheme(String fabric_data_str_val)      { return fabric_data_str_val.charAt(2); }
    public static String getThemeStr(String fabric_data_str_val)   { return fabric_data_str_val.substring(2, 3); }
}
