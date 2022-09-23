/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.utils.encoders;

public class DecodeStringEntry {
    public String data;
    int total_encoded_size;

    public DecodeStringEntry(String data_val, int total_str_length_val) {
        this.data = data_val;
        this.total_encoded_size = total_str_length_val;
    }
}
