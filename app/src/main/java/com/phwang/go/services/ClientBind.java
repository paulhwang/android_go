/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.services;

import com.phwang.core.utils.abend.Abend;
import com.phwang.core.utils.sleep.Utils;
import com.phwang.test.AndroidTest;

public class ClientBind {
    public ClientBind() {
        //Utils.sleep(10);
        Abend.initAbend(new ClientAbend());
        new com.phwang.core.root.CoreRoot();
        new AndroidTest(3, 1).startTest(true);
    }
}
