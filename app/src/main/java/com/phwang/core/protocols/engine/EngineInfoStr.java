/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.protocols.engine;

import com.phwang.core.utils.encoders.Encoders;

public class EngineInfoStr {
    public static char   getCommand(String engine_data_str_val)       { return engine_data_str_val.charAt(EngineInfo.COMMAND_INDEX); }
    public static String getCommandStr(String engine_data_str_val)    { return engine_data_str_val.substring(EngineInfo.COMMAND_INDEX, EngineInfo.COMMAND_INDEX + 1); }

    public static char   getResult(String engine_data_str_val)        { return engine_data_str_val.charAt(EngineInfo.RESULT_INDEX); }
    public static String getResultStr(String engine_data_str_val)     { return engine_data_str_val.substring(EngineInfo.RESULT_INDEX, EngineInfo.RESULT_INDEX + 1); }

    public static char   getThemeType(String engine_data_str_val)     { return engine_data_str_val.charAt(EngineInfo.THEME_TYPE_INDEX); }
    public static String getThemeTypeStr(String engine_data_str_val)  { return engine_data_str_val.substring(EngineInfo.THEME_TYPE_INDEX, EngineInfo.THEME_TYPE_INDEX + 1); }
}
