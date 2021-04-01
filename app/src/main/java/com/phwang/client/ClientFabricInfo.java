/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.client;

public class ClientFabricInfo {
    private String linkIdStr_;
    private String sessionIdStr_;

    public String linkIdStr() { return this.linkIdStr_; }
    public String sessionIdStr() { return this.sessionIdStr_; }

    public void setLinkIdStr(String val) { this.linkIdStr_ = val; }
    public void setSessionIdStr(String val) { this.sessionIdStr_ = val; }
}
