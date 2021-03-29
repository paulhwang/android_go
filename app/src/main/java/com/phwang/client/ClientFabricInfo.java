/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.client;

import com.phwang.core.go.GoRoot;
import com.phwang.core.utils.Encoders;

public class ClientFabricInfo {
    private String linkIdStr_;
    private String sessionIdStr_;
    private String myName_;

    public String linkIdStr() { return this.linkIdStr_; }
    public String sessionIdStr() { return this.sessionIdStr_; }
    public String myName() { return this.myName_; }

    public void setLinkIdStr(String val) { this.linkIdStr_ = val; }
    public void setSessionIdStr(String val) { this.sessionIdStr_ = val; }
    public void setMyName(String val) { this.myName_ = val; }
}
