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
    private int handicapPoint_;
    private int komiPoint_;

    public int boardSize() { return this.boardSize_; }
    public int handicapPoint() { return this.handicapPoint_; }
    public int komiPoint() { return this.komiPoint_; }

    public GoConfigInfo(String go_config_info_str_val) {
        this.debug(false, "GoConfigInfo", "go_config_info_str_val=" + go_config_info_str_val);

        String rest_str = go_config_info_str_val.substring(1);
        String board_size_str = rest_str.substring(0, 2);
        String handicap_str = rest_str.substring(2, 4);
        String komi_str = rest_str.substring(4, 6);

        this.boardSize_ = Encoders.iDecodeRaw(board_size_str);
        this.handicapPoint_ = Encoders.iDecodeRaw(handicap_str);
        this.komiPoint_ = Encoders.iDecodeRaw(komi_str);

        this.debug(false, "GoConfigInfo", "boardSize=" + boardSize());
    }

    public static String encodeConfig(int board_size_val, int handicap_val, int komi_val, int color_val) {
        StringBuilder buf = new StringBuilder();
        buf.append('G');
        buf.append(Encoders.iEncodeRaw2(board_size_val));
        buf.append(Encoders.iEncodeRaw2(handicap_val));
        buf.append(Encoders.iEncodeRaw2(komi_val));
        buf.append(Encoders.iEncodeRaw1(color_val));
        return buf.toString();
    }

    private Boolean isValidCoordinate_(int coordinate_val) {
        return (0 <= coordinate_val) && (coordinate_val < this.boardSize());
    }

    public Boolean isValidCoordinates(int x_val, int y_val) {
        return this.isValidCoordinate_(x_val) && this.isValidCoordinate_(y_val);
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    public void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
