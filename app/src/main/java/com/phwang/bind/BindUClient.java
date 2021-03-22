/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.bind;

import com.phwang.core.utils.Abend;

public class BindUClient {
    private String objectName() {return "BindUClient";}

    private BindRoot bindRoot_;

    public BindUClient(BindRoot bind_root_val) {
        this.bindRoot_ = bind_root_val;
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
