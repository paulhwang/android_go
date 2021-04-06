package com.phwang.core.protocols.engine;

import com.phwang.core.protocols.fabric.FabricClients;
import com.phwang.core.utils.encoders.Encoders;

public class EngineData {
    private static final String TAG = "EngineData";
    private static final int MAX_ARRAY_SIZE = 10;

    public static final int COMMAND_INDEX     = 0;
    public static final int RESULT_INDEX      = 1;
    public static final int THEME_INDEX       = 2;

    private char command_;
    private char result_;
    private char theme_;
    private String linkIdStr_;
    private String sessionIdStr_;
    private int stringsCount_ = 0;
    private String[] stringList_ = new String[MAX_ARRAY_SIZE];

    public char command() { return this.command_; };
    public char result() { return this.result_; };
    public char theme() { return this.theme_; };
    public String linkIdStr() { return this.linkIdStr_; };
    public String sessionIdStr() { return this.sessionIdStr_; };
    public String stringList(int index_val) { return this.stringList_[index_val]; };

    public void setResult(char result_val) { this.result_ = result_val; }
    public void setLinkIdStr(String link_id_str_val) { this.linkIdStr_ = link_id_str_val; }
    public void setSessionIdStr(String session_id_str_val) { this.sessionIdStr_ = session_id_str_val; }
    public void addStringList(String string_val) { this.stringList_[this.stringsCount_] = string_val; this.stringsCount_++; }

    public EngineData(char command_val, char result_val, char theme_val, String link_id_str_val, String session_id_str_val) {
        this.command_ = command_val;
        this.result_ = result_val;
        this.theme_ = theme_val;
        this.linkIdStr_ = link_id_str_val;
        this.sessionIdStr_ = session_id_str_val;
    }

    public EngineData(String fabric_data_str_val) {
        String rest_str = fabric_data_str_val;
        this.command_ = rest_str.charAt(COMMAND_INDEX);
        this.result_ = rest_str.charAt(RESULT_INDEX);
        this.theme_ = rest_str.charAt(THEME_INDEX);
        rest_str = rest_str.substring((THEME_INDEX + 1));

        this.linkIdStr_ = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

        this.sessionIdStr_ = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

        this.stringsCount_ = rest_str.charAt(0) - 48;
        rest_str = rest_str.substring(1);

        for (int i = 0; i < this.stringsCount_; i ++) {
            this.stringList_[i] = Encoders.sDecode6(rest_str);
            rest_str = Encoders.sSubstring6_(rest_str);
        }
    }

    public String getEncodedString() {
        StringBuilder buf = new StringBuilder();
        buf.append(this.command_);
        buf.append(this.result_);
        buf.append(this.theme_);

        buf.append((this.linkIdStr_ != null) ? this.linkIdStr_: Encoders.NULL_LINK);
        buf.append((this.sessionIdStr_ != null) ? this.sessionIdStr_: Encoders.NULL_SESSION);
        buf.append(Encoders.iEncodeRaw1(this.stringsCount_));

        for (int i = 0; i < this.stringsCount_; i++) {
            buf.append(Encoders.sEncode6(this.stringList_[i]));
        }
        return buf.toString();
    }
}
