/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.protocols.theme;

import com.phwang.core.utils.encoders.Encoders;

public class ThemeInfoStr {
    public static char   getCommand(String theme_data_str_val)       { return theme_data_str_val.charAt(ThemeInfo.COMMAND_INDEX); }
    public static String getCommandStr(String theme_data_str_val)    { return theme_data_str_val.substring(ThemeInfo.COMMAND_INDEX, ThemeInfo.COMMAND_INDEX + 1); }

    public static char   getResult(String theme_data_str_val)        { return theme_data_str_val.charAt(ThemeInfo.RESULT_INDEX); }
    public static String getResultStr(String theme_data_str_val)     { return theme_data_str_val.substring(ThemeInfo.RESULT_INDEX, ThemeInfo.RESULT_INDEX + 1); }

    public static char   getThemeType(String theme_data_str_val)     { return theme_data_str_val.charAt(ThemeInfo.THEME_TYPE_INDEX); }
    public static String getThemeTypeStr(String theme_data_str_val)  { return theme_data_str_val.substring(ThemeInfo.THEME_TYPE_INDEX, ThemeInfo.THEME_TYPE_INDEX + 1); }
}
