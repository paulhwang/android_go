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

    public BindUClient() {
    }

    public void doSetupLink(String my_name_val, String password_val) {
        this.debug(false, "doSetupLink", "doSetupLink");
        /*
        this.clientFabricInfo().setMyName(my_name_val);
        this.clientFabricInfo().setPassword(password_val);
        this.clientDExport().setupLink();

         */
    }

    public void doGetRemoveLink() {

    }

    public void doGetLinkData() {

    }

    public void doGetNameList() {

    }

    public void doSetupSession(String his_name_val, String session_setup_data_val) {
        this.debug(false, "doSetupLink", "doSetupLink");
        /*
        this.clientFabricInfo().setMyName(his_name_val);
        this.clientDExport().setupLink();
        
         */
    }

    public void doSetupSession2() {

    }

    public void doSetupSession3() {

    }

    public void doPutSessionData(String session_put_data_val) {
    }

    public void doGetSessionData() {
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { Abend.log(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { Abend.abend(this.objectName() + "." + s0 + "()", s1); }
}
