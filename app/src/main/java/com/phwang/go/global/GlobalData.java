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

    private static String userName_;
    public static String userName() { return userName_; }
    public static void setUserName(String str_val) { userName_ = str_val; }
}
