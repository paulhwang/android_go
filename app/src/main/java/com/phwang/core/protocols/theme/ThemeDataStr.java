package com.phwang.core.protocols.theme;

import com.phwang.core.utils.encoders.Encoders;

public class ThemeDataStr {
    public static char   getCommand(String theme_data_str_val)       { return theme_data_str_val.charAt(ThemeData.COMMAND_INDEX); }
    public static String getCommandStr(String theme_data_str_val)    { return theme_data_str_val.substring(ThemeData.COMMAND_INDEX, ThemeData.COMMAND_INDEX + 1); }

    public static char   getResult(String theme_data_str_val)        { return theme_data_str_val.charAt(ThemeData.RESULT_INDEX); }
    public static String getResultStr(String theme_data_str_val)     { return theme_data_str_val.substring(ThemeData.RESULT_INDEX, ThemeData.RESULT_INDEX + 1); }

    public static char   getThemeType(String theme_data_str_val)     { return theme_data_str_val.charAt(ThemeData.THEME_TYPE_INDEX); }
    public static String getThemeTypeStr(String theme_data_str_val)  { return theme_data_str_val.substring(ThemeData.THEME_TYPE_INDEX, ThemeData.THEME_TYPE_INDEX + 1); }
}
