/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.utils.stringarray;

import com.phwang.core.utils.encoders.Encoders;

public class StringArray {
    private static final int STRINGS_COUNT_SIZE = 1;
    private static final int MAX_ARRAY_SIZE = 10;

    private int stringsCount_ = 0;
    private String[] stringList_ = new String[MAX_ARRAY_SIZE];

    public String stringList(int index_val) { return this.stringList_[index_val]; };
    public void addStringList(String string_val) { this.stringList_[this.stringsCount_] = string_val; this.stringsCount_++; }

    public StringArray(String string_array_val) {
        String rest_str = string_array_val;

        this.stringsCount_ = Encoders.iDecodeRaw(rest_str.substring(0, STRINGS_COUNT_SIZE));
        rest_str = rest_str.substring(STRINGS_COUNT_SIZE);

        for (int i = 0; i < this.stringsCount_; i ++) {
            this.stringList_[i] = Encoders.sDecode6(rest_str);
            rest_str = Encoders.sSubstring6_(rest_str);
        }
    }
    public String getEncodedString() {
        StringBuilder buf = new StringBuilder();
        buf.append(Encoders.iEncodeRaw(this.stringsCount_, STRINGS_COUNT_SIZE));
        for (int i = 0; i < this.stringsCount_; i++) {
            buf.append(Encoders.sEncode6(this.stringList_[i]));
        }
        return buf.toString();
    }
}
