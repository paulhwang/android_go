/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.go;

import android.util.Log;

import com.phwang.core.utils.encoders.Encoders;

public class GoMoveInfo {
    private int x_;
    private int y_;
    private int myColor_;
    private int turnIndex_;
    private String moveDebugStr_;

    public int X() { return this.x_; }
    public int Y() { return this.y_; }
    public int MyColor() { return this.myColor_; }
    public int TurnIndex() { return this.turnIndex_; }
    public String moveDebugStr() { return this.moveDebugStr_; }

    public GoMoveInfo(String go_move_info_str_val) {
        this.x_ = (go_move_info_str_val.charAt(1) - '0') * 10 + (go_move_info_str_val.charAt(2) - '0');
        this.y_ = (go_move_info_str_val.charAt(3) - '0') * 10 + (go_move_info_str_val.charAt(4) - '0');
        this.myColor_ = go_move_info_str_val.charAt(5) - '0';
        this.turnIndex_ = (go_move_info_str_val.charAt(6) - '0') * 100 + (go_move_info_str_val.charAt(7) - '0') * 10 + (go_move_info_str_val.charAt(8) - '0');
        this.moveDebugStr_ = "(" + this.x_ + ", " + this.y_ + ") " + this.myColor_ + ", " + this.turnIndex_;
    }

    public static String encodeMove(int x_val, int y_val, int my_color_val, int turn_index_val) {
        StringBuilder buf = new StringBuilder("GM");
        buf.append(Encoders.iEncodeRaw2(x_val));
        buf.append(Encoders.iEncodeRaw2(y_val));
        buf.append(Encoders.iEncodeRaw1(my_color_val));
        buf.append(Encoders.iEncodeRaw3(turn_index_val));
        return buf.toString();
    }
}
