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
    private static final int STRINGS_COUNT_SIZE = 2;

    private int arraySize_ = 1;
    private int stringsCount_ = 0;
    private String[] stringArray_;

    public String stringArrayElement(int index_val) { return this.stringArray_[index_val]; };

    public StringArray() {
        this.stringArray_ = new String[this.arraySize_];
    }

    public StringArray(String string_array_val) {
        String rest_str = string_array_val;

        this.stringsCount_ = Encoders.iDecodeRaw(rest_str.substring(0, STRINGS_COUNT_SIZE));
        rest_str = rest_str.substring(STRINGS_COUNT_SIZE);

        while (this.stringsCount_ > this.arraySize_) {
            this.arraySize_ *= 2;
        }
        this.stringArray_ = new String[this.arraySize_];

        for (int i = 0; i < this.stringsCount_; i ++) {
            this.stringArray_[i] = Encoders.sDecode6(rest_str);
            rest_str = Encoders.sSubstring6_(rest_str);
        }
    }

    public String encode() {
        StringBuilder buf = new StringBuilder();
        buf.append(Encoders.iEncodeRaw(this.stringsCount_, STRINGS_COUNT_SIZE));
        for (int i = 0; i < this.stringsCount_; i++) {
            buf.append(Encoders.sEncode6(this.stringArray_[i]));
        }
        return buf.toString();
    }

    public void addString(String string_val) {
        if (this.stringsCount_ >= this.arraySize_) {
            this.arraySize_ *= 2;
            String[] new_array = new String[this.arraySize_];
            for (int i = 0; i < this.stringsCount_; i++) {
                new_array[i] = this.stringArray_[i];
            }
            this.stringArray_ = new_array;
        }

        this.stringArray_[this.stringsCount_] = string_val;
        this.stringsCount_++;
    }

    public String[] compactStringArray() {
        String[] new_array = new String[this.stringsCount_];
        for (int i = 0; i < this.stringsCount_; i++) {
            new_array[i] = this.stringArray_[i];
        }
        return new_array;
    }

    public String[] sortedStringArray() {
        String[] new_array = this.compactStringArray();
        boolean isSwapped = false;
        do {
            isSwapped = false;
            for (int i = 0; i < new_array.length - 1; i++) {
                if (new_array[i].compareTo(new_array[i+1]) > 0) {
                    String temp = new_array[i+1];
                    new_array[i+1] = new_array[i];
                    new_array[i] = temp;
                    isSwapped = true;
                }
            }
        } while ((isSwapped));
        return new_array;
    };
}
