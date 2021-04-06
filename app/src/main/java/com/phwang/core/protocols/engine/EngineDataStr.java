package com.phwang.core.protocols.engine;

import com.phwang.core.utils.encoders.Encoders;

public class EngineDataStr {
    public static char   getCommand(String engine_data_str_val)       { return engine_data_str_val.charAt(EngineData.COMMAND_INDEX); }
    public static String getCommandStr(String engine_data_str_val)    { return engine_data_str_val.substring(EngineData.COMMAND_INDEX, EngineData.COMMAND_INDEX + 1); }

    public static char   getResult(String engine_data_str_val)        { return engine_data_str_val.charAt(EngineData.RESULT_INDEX); }
    public static String getResultStr(String engine_data_str_val)     { return engine_data_str_val.substring(EngineData.RESULT_INDEX, EngineData.RESULT_INDEX + 1); }

    public static char   getTheme(String engine_data_str_val)         { return engine_data_str_val.charAt(EngineData.THEME_INDEX); }
    public static String getThemeStr(String engine_data_str_val)      { return engine_data_str_val.substring(EngineData.THEME_INDEX, EngineData.THEME_INDEX + 1); }
}
