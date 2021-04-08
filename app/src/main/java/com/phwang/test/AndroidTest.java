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

public class AndroidTest implements ThreadEntityInt {
    private static final String TAG = "AndroidTest";
    private String clientTesterThreadName() { return "ClientTesterThread"; }

    private int numberOfTester_;
    private int numberOfCasePerTester_;
    private LockedInteger testerIndex_;
    private ThreadMgr threadMgr_;
    private LockedInteger threadCount_;
    private AndroidTester[] testerArray_;

    protected ThreadMgr threadMgr() { return this.threadMgr_; }

    public AndroidTest(int number_of_tester_val, int number_of_case_val) {
        Log.e(TAG, "AndroidTest() start");

        this.numberOfTester_ = number_of_tester_val;
        this.numberOfCasePerTester_ = number_of_case_val;
        this.threadMgr_ = new ThreadMgr();
        this.threadCount_ = new LockedInteger(0);
        this.testerIndex_ = new LockedInteger(-1);
        this.testerArray_ = new AndroidTester[this.numberOfTester_];
    }

    public void startTest(Boolean test_on_val) {
        if (!test_on_val) {
            return;
        }

        for (int i = 0; i < this.numberOfTester_; i++) {
            this.threadMgr().createThreadObject(this.clientTesterThreadName(), this);
        }
    }

    public void threadCallbackFunction() {
        this.incrementThreadCount();
        this.androidTesterThreadFunc();
        this.decrementThreadCount();
    }

    private void androidTesterThreadFunc() {
        //Log.e(TAG, "androidTesterThreadFunc() start");

        int tester_index = this.testerIndex_.increment();

        Utils.sleep(10);

        AndroidTester tester = new AndroidTester(this, tester_index);
        this.testerArray_[tester_index] = tester;

        for (int i = 0; i < this.numberOfCasePerTester_; i++) {
            tester.startTest();
            //Utils.sleep(1);
        }
    }

    protected void incrementThreadCount() {
        int count = this.threadCount_.increment();
        Log.e(TAG, "incrementThreadCount(*****)" + count);
    }

    protected void decrementThreadCount() {
        int count = this.threadCount_.decrement();

        if (count < 12) {
            Log.e(TAG, "decrementThreadCount(*****)" + count);
        }

        if (count < 0) {
            Log.e(TAG, "decrementThreadCount(*****) smaller than 0");
        }
    }
}
