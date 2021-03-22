package com.phwang.bind;

import com.phwang.core.utils.Abend;

public class BindRoot {
    private String objectName() {return "BindRoot";}

    private BindUClient bindUClient_;
    private BindDClient bindDClient_;

    public BindRoot() {
        this.bindDClient_ = new BindDClient(this);
        this.bindUClient_ = new BindUClient(this);
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
