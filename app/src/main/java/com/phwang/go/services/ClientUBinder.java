/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.services;

import com.phwang.core.utils.binder.Binder;
import com.phwang.core.protocols.tcpip.TcpIpDefine;
import com.phwang.core.utils.threadmgr.ThreadEntityInt;
import com.phwang.core.utils.threadmgr.ThreadMgr;
import com.phwang.core.utils.abend.Logit;

public class ClientUBinder implements ThreadEntityInt {
    private static final String TAG = "ClientUBinder";
    private String objectName() {return "ClientUBinder";}
    private String receiveThreadName() { return "UClientReceiveThread"; }

	private static final int NUMBER_OF_D_WORK_THREADS = 1;
    
    private ClientRoot clientRoot_;
    private Binder uBinder_;
    private Boolean stopReceiveThreadFlag = false;
        
    private ClientRoot clientRoot() { return this.clientRoot_; }
    private ThreadMgr threadMgr() { return this.clientRoot().threadMgr();}
    private ClientDParser clientDParser() { return this.clientRoot().clientDParser();}
    public Binder uBinder() { return this.uBinder_; }
    
    protected ClientUBinder(ClientRoot root_val) {
        Logit.e(TAG, "ClientUBinder() thread_id=" + Thread.currentThread().getId());

        this.clientRoot_ = root_val;
        this.uBinder_ = new Binder(this.objectName());
    }

    protected void runAsTcpClient(String ip_address_val) {
        this.uBinder_.bindAsTcpClient(true, ip_address_val, TcpIpDefine.FABRIC_ANDROID_PORT);
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
       Logit.e(TAG, "uClientReceiveThreadFunc() thread_id=" + Thread.currentThread().getId() + " owner=" + this.receiveThreadName());

        while (true) {
            if (this.stopReceiveThreadFlag) {
                break;
            }

            String received_data = this.uBinder().receiveStringData();
            if (received_data == null) {
                this.abend("uClientReceiveThreadFunc", "null data");
            	continue;
            }

            Logit.e(TAG, "uClientReceiveThreadFunc() received_data=" + received_data);
            this.clientDParser().parserResponseData(received_data);
        }
        Logit.e(TAG, "uClientReceiveThreadFunc() exit");
    }
    
    protected void StopReceiveThread() {
        this.stopReceiveThreadFlag = true;
    }
    
    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.clientRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { this.clientRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
