/*  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.test;

import android.util.Log;

import com.phwang.core.protocols.fabric.FabricClients;
import com.phwang.core.protocols.fabric.FabricCommands;
import com.phwang.core.protocols.fabric.FabricData;
import com.phwang.core.protocols.fabric.FabricResults;
import com.phwang.core.protocols.fabric.FabricThemeTypes;
import com.phwang.core.utils.abend.Abend;
import com.phwang.core.utils.binder.Binder;
import com.phwang.core.utils.encoders.Encoders;
import com.phwang.core.utils.threadmgr.ThreadMgr;
import com.phwang.go.services.ClientDParser;
import com.phwang.go.services.ClientUBinder;
import com.phwang.go.services.ClientUParser;

class ATestRoot {
    private String objectName() {return "ATestRoot";}
    private static final String TAG = "ATestRoot";

    private String goConfig = "G1900003";
    private String myMame_;
    private String password_ = "Tennis";
    private ThreadMgr threadMgr_;
    private ATestUBinder aTestUBinder_;
    private ATestDParser aTestDParser_;
    private ATestUParser aTestUParser_;

    protected ThreadMgr threadMgr() { return this.threadMgr_; }
    protected ATestUBinder aTestUBinder() { return this.aTestUBinder_; }
    protected ATestDParser aTestDParser() { return this.aTestDParser_; }
    protected ATestUParser aTestUParser() { return this.aTestUParser_; };
    protected Binder uBinder() { return this.aTestUBinder_.uBinder(); }

    protected ATestRoot(int a_test_root_index_val) {
        this.myMame_ = "Android_" + Encoders.iEncodeRaw5(a_test_root_index_val);
        Log.e(TAG, "ATestRoot(init) " + this.myMame_ + " thread=" + Thread.currentThread().getId());

        this.threadMgr_ = new ThreadMgr();
        this.aTestUBinder_ = new ATestUBinder(this);
        this.aTestDParser_ = new ATestDParser(this);
        this.aTestUBinder_.startThreads();
    }

    protected void startTest() {
        this.sendLoginRequest();
    }

    private void sendLoginRequest() {
        FabricData fabric_data = new FabricData(
                FabricCommands.FABRIC_COMMAND_LOGIN,
                FabricResults.UNDECIDED,
                FabricClients.ANDROID_CLIENT,
                FabricThemeTypes.GO,
                Encoders.IGNORE,
                Encoders.IGNORE
        );
        fabric_data.addStringList(this.goConfig);
        this.uBinder().transmitStringData(fabric_data.encode());
    }

    protected void sendSoloSessionRequest(String link_id_str_val) {
        FabricData fabric_data = new FabricData(
                FabricCommands.FABRIC_COMMAND_SOLO_SESSION,
                FabricResults.UNDECIDED,
                FabricClients.ANDROID_CLIENT,
                FabricThemeTypes.GO,
                link_id_str_val,
                Encoders.IGNORE
        );
        fabric_data.addStringList(this.goConfig);
        this.uBinder().transmitStringData(fabric_data.encode());
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.logIt(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { this.abendIt(this.objectName() + "." + s0 + "()", s1); }

    private Boolean debug_on = true;
    protected void logIt(String s0, String s1) { if (this.debug_on) Abend.log(s0, s1); }
    protected void abendIt(String s0, String s1) { if (this.debug_on) Abend.abend(s0, s1); }
}
