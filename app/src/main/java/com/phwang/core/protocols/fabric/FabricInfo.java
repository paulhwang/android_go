/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.protocols.fabric;

import android.util.Log;

import com.phwang.core.protocols.fabric.FabricClients;
import com.phwang.core.utils.encoders.Encoders;

public class FabricInfo {
    private static final String TAG = "FabricInfo";
    private static final int COMMAND_SIZE = 2;
    private static final int RESULT_SIZE = 2;
    private static final int LINK_ID_SIZE = 8;
    private static final int SESSION_ID_SIZE = 8;
    private static final int STRINGS_COUNT_SIZE = 2;

    public static final int COMMAND_INDEX     = 0;
    public static final int RESULT_INDEX      = 1;
    public static final int CLIENT_TYPE_INDEX = 2;
    public static final int THEME_TYPE_INDEX  = 3;
    public static final int JOB_ID_STR_INDEX  = 4;

    private String command_;
    private String result_;
    private char clientType_;
    private char themeType_;
    private String linkIdStr_;
    private String sessionIdStr_;
    private String jobIdStr_;
    private int arraySize_ = 1;
    private int stringsCount_ = 0;
    private String[] stringArray_;

    public String command() { return this.command_; };
    public String result() { return this.result_; };
    public char clientType() { return this.clientType_; }
    public char themeType() { return this.themeType_; };
    public String linkIdStr() { return this.linkIdStr_; };
    public String sessionIdStr() { return this.sessionIdStr_; };
    public String jobIdStr() { return this.jobIdStr_; };
    public String stringArrayElement(int index_val) { return this.stringArray_[index_val]; };

    public void setResult(String result_val) { this.result_ = result_val; }
    public void setLinkIdStr(String link_id_str_val) { this.linkIdStr_ = link_id_str_val; }
    public void setSessionIdStr(String session_id_str_val) { this.sessionIdStr_ = session_id_str_val; }
    public void setJobIdStr(String job_id_str_val) { this.jobIdStr_ = job_id_str_val; }

    public FabricInfo() {
    }

    public FabricInfo(String command_val) {
        this.command_ = command_val;
        this.stringArray_ = new String[this.arraySize_];
    }

    public void decode(String fabric_data_str_val) {
        String rest_str = fabric_data_str_val;
        this.command_ = rest_str.substring(0, COMMAND_SIZE);
        int index = COMMAND_SIZE;
        this.result_ = rest_str.substring(index, index  + RESULT_SIZE);
        index += RESULT_SIZE;

        switch (this.command_.charAt(0)) {
            case '2':
                this.sessionIdStr_ = rest_str.substring(index, index  + SESSION_ID_SIZE);
                index += SESSION_ID_SIZE;

            case '1':
                this.linkIdStr_ = rest_str.substring(index, index  + LINK_ID_SIZE);
                index += LINK_ID_SIZE;
        }
        //this.stringsCount_ = rest_str.charAt(0) - 48;
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
        buf.append('A');
        buf.append(this.command_);
        switch (this.command_.charAt(0)) {
            case '2':
                buf.append(this.sessionIdStr_);
            case '1':
                buf.append(this.linkIdStr_);
        }

        buf.append(Encoders.iEncodeRaw(this.stringsCount_, STRINGS_COUNT_SIZE));
        for (int i = 0; i < this.stringsCount_; i++) {
            buf.append(Encoders.encodeString(this.stringArray_[i]));
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
}
