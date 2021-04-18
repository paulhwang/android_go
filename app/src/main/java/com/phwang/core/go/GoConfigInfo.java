/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.go;

import com.phwang.core.utils.abend.Abend;
import com.phwang.core.utils.encoders.Encoders;

public class GoConfigInfo {
    private String objectName() {return "GoConfigInfo";}

    private int boardSize_;
    private int myColor_;
    private int handicapPoint_;
    private int komiPoint_;

    public int boardSize() { return this.boardSize_; }
    public int myColor() { return this.myColor_; }
    public int handicapPoint() { return this.handicapPoint_; }
    public int komiPoint() { return this.komiPoint_; }

    public GoConfigInfo(int board_size_val, int my_color_val, int handicap_val, int komi_val) {
        this.boardSize_ = board_size_val;
        this.myColor_ = my_color_val;
        this.handicapPoint_ = handicap_val;
        this.komiPoint_ = komi_val;
    }

    public GoConfigInfo(String go_config_info_str_val) {
        this.debug(true, "GoConfigInfo", "go_config_info_str_val=" + go_config_info_str_val);

        String board_size_str = go_config_info_str_val.substring(1, 3);
        String color_str      = go_config_info_str_val.substring(3, 4);
        String handicap_str   = go_config_info_str_val.substring(4, 6);
        String komi_str       = go_config_info_str_val.substring(6, 8);

        this.boardSize_     = Encoders.iDecodeRaw(board_size_str);
        this.myColor_       = Encoders.iDecodeRaw(color_str);
        this.handicapPoint_ = Encoders.iDecodeRaw(handicap_str);
        this.komiPoint_     = Encoders.iDecodeRaw(komi_str);

        this.debug(true, "GoConfigInfo", "boardSize=" + this.boardSize_ + " myColor_=" + this.myColor_);
    }

    public String encode() {
        StringBuilder buf = new StringBuilder();
        buf.append('G');
        buf.append(Encoders.iEncodeRaw2(this.boardSize_));
        buf.append(Encoders.iEncodeRaw1(this.myColor_));
        buf.append(Encoders.iEncodeRaw2(this.handicapPoint_));
        buf.append(Encoders.iEncodeRaw2(this.komiPoint_));
        return buf.toString();
     }

    public static String encodeConfig(int board_size_val, int my_color_val, int handicap_val, int komi_val) {
        GoConfigInfo go_config_info = new GoConfigInfo(board_size_val, my_color_val, handicap_val, komi_val);
        return go_config_info.encode();
    }

    private Boolean isValidCoordinate_(int coordinate_val) {
        return (0 <= coordinate_val) && (coordinate_val < this.boardSize_);
    }

    public Boolean isValidCoordinates(int x_val, int y_val) {
        return this.isValidCoordinate_(x_val) && this.isValidCoordinate_(y_val);
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    public void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
