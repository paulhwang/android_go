package com.phwang.test;

import com.phwang.core.utils.binder.Binder;

public class ATestUParser {
    private static final String TAG = "ATestUParser";
    private ATestRoot aTestRoot_;

    private ATestDParser aTestDParser() { return this.aTestRoot_.aTestDParser(); }
    private Binder uBinder() { return this.aTestRoot_.uBinder(); }

    public ATestUParser(ATestRoot a_test_root_val) {
        this.aTestRoot_ = a_test_root_val;
    }

    protected void parseUCommand(String fabric_data_str_val) {

    }
}
