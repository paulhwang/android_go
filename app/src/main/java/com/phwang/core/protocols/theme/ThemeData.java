package com.phwang.core.protocols.theme;

import com.phwang.core.protocols.fabric.FabricClients;
import com.phwang.core.utils.encoders.Encoders;

public class ThemeData {
    private static final String TAG = "ThemeData";
    private static final int STRINGS_COUNT_SIZE = 2;
    private static final int MAX_ARRAY_SIZE = 10;

    public static final int COMMAND_INDEX     = 0;
    public static final int RESULT_INDEX      = 1;
    public static final int THEME_TYPE_INDEX  = 2;

    private char command_;
    private char result_;
    private char themeType_;
    private String groupIdStr_;
    private String roomIdStr_;
    private int arraySize_ = 10;
    private int stringsCount_ = 0;
    private String[] stringArray_;

    public char command() { return this.command_; };
    public char result() { return this.result_; };
    public char themeType() { return this.themeType_; };
    public String groupIdStr() { return this.groupIdStr_; };
    public String roomIdStr() { return this.roomIdStr_; };
    public String stringArrayElement(int index_val) { return this.stringArray_[index_val]; };

    public void setCommand(char command_val) { this.command_ = command_val; };
    public void setResult(char result_val) { this.result_ = result_val; }
    public void setGroupIdStr(String link_id_str_val) { this.groupIdStr_ = link_id_str_val; }
    public void setRoomIdStr(String session_id_str_val) { this.roomIdStr_ = session_id_str_val; }

    public ThemeData(char command_val, char result_val, char theme_type_val, String group_id_str_val, String room_id_str_val) {
        this.command_ = command_val;
        this.result_ = result_val;
        this.themeType_ = theme_type_val;
        this.groupIdStr_ = group_id_str_val;
        this.roomIdStr_ = room_id_str_val;
        this.stringArray_ = new String[this.arraySize_];
    }

    public ThemeData(String fabric_data_str_val) {
        String rest_str = fabric_data_str_val;
        this.command_ = rest_str.charAt(COMMAND_INDEX);
        this.result_ = rest_str.charAt(RESULT_INDEX);
        this.themeType_ = rest_str.charAt(THEME_TYPE_INDEX);
        rest_str = rest_str.substring((THEME_TYPE_INDEX + 1));

        this.groupIdStr_ = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

        this.roomIdStr_ = Encoders.sSubstring2(rest_str);
        rest_str = Encoders.sSubstring2_(rest_str);

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
        buf.append(this.command_);
        buf.append(this.result_);
        buf.append(this.themeType_);

        buf.append((this.groupIdStr_ != null) ? this.groupIdStr_: Encoders.NULL_LINK);
        buf.append((this.roomIdStr_ != null) ? this.roomIdStr_: Encoders.NULL_SESSION);

        buf.append(Encoders.iEncodeRaw(this.stringsCount_, STRINGS_COUNT_SIZE));
        for (int i = 0; i < this.stringsCount_; i++) {
            buf.append(Encoders.sEncode6(this.stringArray_[i]));
        }
        return buf.toString();
    }

    public void addString(String string_val) {
        while (this.stringsCount_ >= this.arraySize_) {
            this.arraySize_ *= 2;
        }

        this.stringArray_[this.stringsCount_] = string_val;
        this.stringsCount_++;
    }

    public void addString1(String string_val) {
        String[] new_array = new String[this.stringsCount_ + 1];
        for (int i = 0; i < this.stringsCount_; i++) {
            new_array[i] = this.stringArray_[i];
        }
        new_array[this.stringsCount_] = string_val;
        this.stringArray_ = new_array;
        this.stringsCount_++;
    }
}
