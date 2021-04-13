package com.phwang.test;

import com.phwang.core.protocols.fabric.FabricInfo;
import com.phwang.core.protocols.fabric.FabricCommands;

public class ATestDParser {
    private String objectName() {return "ATestDParser";}
    private static final String TAG = "ATestDParser";
    private ATestRoot aTestRoot_;

    private ATestRoot aTestRoot() { return this.aTestRoot_; };
    protected ATestDParser(ATestRoot a_test_root_val) {
        this.aTestRoot_ = a_test_root_val;
    }

    protected void parserResponseData(String fabric_data_str_val) {
        this.debug(true, "parserResponseData", "fabric_data_str_val=" + fabric_data_str_val);
        FabricInfo fabric_data = new FabricInfo(fabric_data_str_val);
        char command = fabric_data.command();

        switch (command) {
            case FabricCommands.FABRIC_COMMAND_LOGIN:
                this.aTestRoot_.sendSoloSessionRequest(fabric_data.linkIdStr());
                break;
        }
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.aTestRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { this.aTestRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
