/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.utils.fabric;

public class FabricData {
    public static char getCommand(String fabric_data_str_val) { return fabric_data_str_val.charAt(0); }
    public static char getResult(String fabric_data_str_val) { return fabric_data_str_val.charAt(1); }
    public static char getTheme(String fabric_data_str_val) { return fabric_data_str_val.charAt(2); }
}
