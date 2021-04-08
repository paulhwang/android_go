/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.test;

import com.phwang.core.utils.binder.Binder;
import com.phwang.core.protocols.tcpip.TcpIpDefine;
import com.phwang.core.utils.threadmgr.ThreadEntityInt;
import com.phwang.core.utils.threadmgr.ThreadMgr;

public class ATestUBinder implements ThreadEntityInt {
    private String objectName() {return "ATestUBinder";}
    private String receiveThreadName() { return "UClientReceiveThread"; }

    private static final int NUMBER_OF_D_WORK_THREADS = 5;

    private ATestRoot aTestRoot_;
    private Binder uBinder_;
    private Boolean stopReceiveThreadFlag = false;

    private ATestRoot aTestRoot() { return this.aTestRoot_; }
    private ThreadMgr threadMgr() { return this.aTestRoot_.threadMgr();}
    private ATestDParser aTestDParser() { return this.aTestRoot_.aTestDParser();}
    public Binder uBinder() { return this.uBinder_; }

    protected ATestUBinder(ATestRoot root_val) {
        this.debug(false, "ClientUBinder", "init start");

        this.aTestRoot_ = root_val;
        this.uBinder_ = new Binder(this.objectName());
        this.uBinder_.bindAsTcpClient(true, TcpIpDefine.FABRIC_ANDROID_SERVER_IP_ADDRESS, TcpIpDefine.FABRIC_ANDROID_PORT);
    }

    protected void startThreads() {
        for (int i = 0; i < NUMBER_OF_D_WORK_THREADS; i++) {
            this.threadMgr().createThreadObject(this.receiveThreadName(), this);
        }
    }

    public void threadCallbackFunction() {
        this.uClientReceiveThreadFunc();
    }

    private void uClientReceiveThreadFunc() {
        this.debug(false, "uClientReceiveThreadFunc", "start " + this.receiveThreadName());

        while (true) {
            if (this.stopReceiveThreadFlag) {
                break;
            }

            String received_data = this.uBinder().receiveStringData();
            if (received_data == null) {
                this.abend("uClientReceiveThreadFunc", "null data");
                continue;
            }

            this.debug(false, "uClientReceiveThreadFunc", "received_data=" + received_data);
            this.aTestDParser().parserResponseData(received_data);
        }
        this.debug(true, "uClientReceiveThreadFunc", "exit");
    }

    protected void StopReceiveThread() {
        this.stopReceiveThreadFlag = true;
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.aTestRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { this.aTestRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
