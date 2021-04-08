/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */


package com.phwang.test;

import android.util.Log;

import com.phwang.core.utils.sleep.Utils;
import com.phwang.core.utils.threadmgr.ThreadEntityInt;
import com.phwang.core.utils.threadmgr.ThreadMgr;
import com.phwang.core.utils.locks.LockedInteger;

public class ATestMain implements ThreadEntityInt {
    private static final String TAG = "ATestMain";
    private String aTestRootThreadName() { return "ATestRootThread"; }

    private int numberOfATestRoot_;
    private int numberOfCasePerATestRoot_;
    private LockedInteger aTestRootIndex_;
    private ThreadMgr threadMgr_;
    private LockedInteger threadCount_;
    private ATestRoot[] aTestRootArray_;

    protected ThreadMgr threadMgr() { return this.threadMgr_; }

    public ATestMain(int number_of_tester_val, int number_of_case_val) {
        Log.e(TAG, "ATestMain() start");

        this.numberOfATestRoot_ = number_of_tester_val;
        this.numberOfCasePerATestRoot_ = number_of_case_val;
        this.threadMgr_ = new ThreadMgr();
        this.threadCount_ = new LockedInteger(0);
        this.aTestRootIndex_ = new LockedInteger(-1);
        this.aTestRootArray_ = new ATestRoot[this.numberOfATestRoot_];
    }

    public void startTest(Boolean test_on_val) {
        if (!test_on_val) {
            return;
        }

        for (int i = 0; i < this.numberOfATestRoot_; i++) {
            this.threadMgr().createThreadObject(this.aTestRootThreadName(), this);
        }
    }

    public void threadCallbackFunction() {
        this.incrementThreadCount();
        this.androidATestRootThreadFunc();
        this.decrementThreadCount();
    }

    private void androidATestRootThreadFunc() {
        //Log.e(TAG, "androidATestRootThreadFunc() start");

        int a_test_root_index = this.aTestRootIndex_.increment();

        Utils.sleep(10);

        ATestRoot a_test_root = new ATestRoot(a_test_root_index);
        this.aTestRootArray_[a_test_root_index] = a_test_root;

        for (int i = 0; i < this.numberOfCasePerATestRoot_; i++) {
            a_test_root.startTest();
            //Utils.sleep(1);
        }
    }

    protected void incrementThreadCount() {
        int count = this.threadCount_.increment();
        //Log.e(TAG, "incrementThreadCount(*****)" + count);
    }

    protected void decrementThreadCount() {
        int count = this.threadCount_.decrement();

        if (count < 1200) {
            //Log.e(TAG, "decrementThreadCount(*****)" + count);
        }

        if (count < 0) {
            Log.e(TAG, "decrementThreadCount(*****) smaller than 0");
        }
    }
}
