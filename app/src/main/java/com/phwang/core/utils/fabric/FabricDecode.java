/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.utils.fabric;

import com.phwang.core.utils.encoders.Encoders;

public class FabricDecode {
    private static final String TAG = "FabricDecode";
    private static final int MAX_ARRAY_SIZE = 10;

    private char command_;
    private char result_;
    private char clientType_;
    private char theme_;
    private String linkIdStr_;
    private String sessionIdStr_;
    private String[] stringList_ = new String[MAX_ARRAY_SIZE];

    public char command() { return this.command_; };
    public char result() { return this.result_; };
    public char clientType() { return this.clientType_; }
    public char theme() { return this.theme_; };
    public String linkIdStr() { return this.linkIdStr_; };
    public String sessionIdStr() { return this.sessionIdStr_; };
    public String stringList(int index_val) { return this.stringList_[index_val]; };

    public FabricDecode(String fabric_data_str_val) {
        //String rest_str = Encoders.sDecode5(fabric_data_str_val);
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
}
