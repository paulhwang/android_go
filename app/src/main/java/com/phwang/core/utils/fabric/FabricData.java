/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.utils.fabric;

import com.phwang.core.utils.encoders.Encoders;

public class FabricData {
    private static final String TAG = "FabricData";
    private static final int MAX_ARRAY_SIZE = 10;

    private char command_;
    private char result_;
    private char clientType_;
    private char theme_;
    private String linkIdStr_;
    private String sessionIdStr_;
    private int stringsCount_;
    private String[] stringList_ = new String[MAX_ARRAY_SIZE];

    public char command() { return this.command_; };
    public char result() { return this.result_; };
    public char clientType() { return this.clientType_; }
    public char theme() { return this.theme_; };
    public String linkIdStr() { return this.linkIdStr_; };
    public String sessionIdStr() { return this.sessionIdStr_; };
    public String stringList(int index_val) { return this.stringList_[index_val]; };

    public void setResult(char result_val) { this.result_ = result_val; }
    public void setLinkIdStr(String link_id_str_val) { this.linkIdStr_ = link_id_str_val; }
    public void setSessionIdStr(String session_id_str_val) { this.sessionIdStr_ = session_id_str_val; }
    public void setStringList(int index_val, String string_val) { this.stringList_[index_val] = string_val; }

    public FabricData(char command_val, char result_val, char client_type_val, char theme_val, String link_id_str_val, String session_id_str_val, int items_count_val) {
        this.command_ = command_val;
        this.result_ = result_val;
        this.clientType_ = client_type_val;
        this.theme_ = theme_val;
        this.linkIdStr_ = link_id_str_val;
        this.sessionIdStr_ = session_id_str_val;
        this.stringsCount_ = items_count_val;
    }

    public FabricData(String fabric_data_str_val) {
        String rest_str = fabric_data_str_val;
        this.command_ = rest_str.charAt(0);
        this.result_ = rest_str.charAt(1);
        this.clientType_ = rest_str.charAt(2);
        this.theme_ = rest_str.charAt(3);
        rest_str = rest_str.substring((4));

        this.linkIdStr_ = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

        this.sessionIdStr_ = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

        int count = rest_str.charAt(0) - 48;
        rest_str = rest_str.substring(1);

        for (int i = 0; i < count; i ++) {
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
        buf.append(this.linkIdStr_);
        buf.append(this.sessionIdStr_);
        buf.append(Encoders.iEncodeRaw1(this.stringsCount_));

        for (int i = 0; i < this.stringsCount_; i++) {
            buf.append(Encoders.sEncode6(this.stringList_[i]));
        }
        return buf.toString();
    }
}
