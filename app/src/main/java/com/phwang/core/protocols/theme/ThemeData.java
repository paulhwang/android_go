package com.phwang.core.protocols.theme;

import com.phwang.core.protocols.fabric.FabricClients;
import com.phwang.core.utils.encoders.Encoders;

public class ThemeData {
    private static final String TAG = "ThemeData";
    private static final int MAX_ARRAY_SIZE = 10;

    public static final int COMMAND_INDEX     = 0;
    public static final int RESULT_INDEX      = 1;
    public static final int CLIENT_TYPE_INDEX = 2;
    public static final int THEME_INDEX       = 3;
    public static final int JOB_ID_STR_INDEX  = 4;

    private char command_;
    private char result_;
    private char clientType_;
    private char theme_;
    private String linkIdStr_;
    private String sessionIdStr_;
    private String jobIdStr_;
    private int stringsCount_ = 0;
    private String[] stringList_ = new String[MAX_ARRAY_SIZE];

    public char command() { return this.command_; };
    public char result() { return this.result_; };
    public char clientType() { return this.clientType_; }
    public char theme() { return this.theme_; };
    public String linkIdStr() { return this.linkIdStr_; };
    public String sessionIdStr() { return this.sessionIdStr_; };
    public String jobIdStr() { return this.jobIdStr_; };
    public String stringList(int index_val) { return this.stringList_[index_val]; };

    public void setResult(char result_val) { this.result_ = result_val; }
    public void setLinkIdStr(String link_id_str_val) { this.linkIdStr_ = link_id_str_val; }
    public void setSessionIdStr(String session_id_str_val) { this.sessionIdStr_ = session_id_str_val; }
    public void setJobIdStr(String job_id_str_val) { this.jobIdStr_ = job_id_str_val; }
    public void addStringList(String string_val) { this.stringList_[this.stringsCount_] = string_val; this.stringsCount_++; }

    public ThemeData(char command_val, char result_val, char client_type_val, char theme_val, String link_id_str_val, String session_id_str_val) {
        this.command_ = command_val;
        this.result_ = result_val;
        this.clientType_ = client_type_val;
        this.theme_ = theme_val;
        this.linkIdStr_ = link_id_str_val;
        this.sessionIdStr_ = session_id_str_val;
    }

    public ThemeData(String fabric_data_str_val) {
        String rest_str = fabric_data_str_val;
        this.command_ = rest_str.charAt(COMMAND_INDEX);
        this.result_ = rest_str.charAt(RESULT_INDEX);
        this.clientType_ = rest_str.charAt(CLIENT_TYPE_INDEX);
        this.theme_ = rest_str.charAt(THEME_INDEX);
        rest_str = rest_str.substring((JOB_ID_STR_INDEX));

        if (this.clientType_ == FabricClients.HTTP_CLIENT) {
            this.jobIdStr_ = Encoders.sSubstring2(rest_str);
            rest_str = Encoders.sSubstring2_(rest_str);
        }

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
        buf.append(this.clientType_);
        buf.append(this.theme_);

        if (this.clientType_ == FabricClients.HTTP_CLIENT) {
            buf.append((this.jobIdStr_ != null) ? this.jobIdStr_: Encoders.NULL_JOB);
        }

        buf.append((this.linkIdStr_ != null) ? this.linkIdStr_: Encoders.NULL_LINK);
        buf.append((this.sessionIdStr_ != null) ? this.sessionIdStr_: Encoders.NULL_SESSION);
        buf.append(Encoders.iEncodeRaw1(this.stringsCount_));

        for (int i = 0; i < this.stringsCount_; i++) {
            buf.append(Encoders.sEncode6(this.stringList_[i]));
        }
        return buf.toString();
    }
}
