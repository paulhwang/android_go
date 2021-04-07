/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.services;

import com.phwang.core.utils.abend.Abend;

public class ClientBind {
    public ClientBind() {
        Abend.initAbend(new ClientAbend());
        new com.phwang.core.root.CoreRoot();
    }
}
