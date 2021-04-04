/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.global;

public class GlobalData {
    private static String linkIdStr_;
    public static String linkIdStr() { return linkIdStr_; }
    public static void setLinkIdStr(String str_val) { linkIdStr_ = str_val; }
}
