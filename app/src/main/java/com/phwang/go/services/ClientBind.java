/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.services;

import com.phwang.core.utils.abend.Abend;
import com.phwang.test.AndroidTest;

public class ClientBind {
    public ClientBind() {
        Abend.initAbend(new ClientAbend());
        new com.phwang.core.root.CoreRoot();
        new AndroidTest(1, 1).startTest(true);
    }
}
