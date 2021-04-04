/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.utils.fabric;

import com.phwang.core.utils.encoders.Encoders;

public class FabricEncode {
    private static final String TAG = "FabricEncode";
    private static final int MAX_ARRAY_SIZE = 10;

    private char command_;
    private char result_;
    private char theme_;
    private String linkIdStr_;
    private String sessionIdStr_;
    private int stringsCount_;
    private String[] stringList_ = new String[MAX_ARRAY_SIZE];

    public void setStringList(int index_val, String string_val) { this.stringList_[index_val] = string_val; }

    public FabricEncode(char command_val, char result_val, char theme_val, String link_id_str_val, String session_id_str_val, int items_count_val) {
        this.command_ = command_val;
        this.result_ = result_val;
        this.theme_ = theme_val;
        this.linkIdStr_ = link_id_str_val;
        this.sessionIdStr_ = session_id_str_val;
        this.stringsCount_ = items_count_val;
    }

    public String getEncodedString() {
        StringBuilder buf = new StringBuilder();
        buf.append(this.command_);
        buf.append(this.result_);
        buf.append(this.theme_);
        buf.append(this.linkIdStr_);
        buf.append(this.sessionIdStr_);
        buf.append(Encoders.iEncodeRaw1(this.stringsCount_));

        for (int i = 0; i < this.stringsCount_; i++) {
            buf.append(this.stringList_[i]);
        }
        return Encoders.sEncode5(buf.toString());
    }
}
