/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */
package com.phwang.bind;

public class BindMain {
    public BindMain() {
        com.phwang.core.utils.Abend.initAbend(new MainAbend());
        new com.phwang.core.root.CoreRoot();
        new BindClient();
    }
}
