/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.utils.abend;

import com.phwang.core.utils.abend.AbendInt;

import java.util.concurrent.locks.*;

public class Abend {
	static private boolean logStopped_ = false;
    static private Lock abendLock_ = new ReentrantLock();
    static private AbendInt abendInt_;
	
    static public void initAbend(AbendInt abend_interface_val) {
		abendInt_ = abend_interface_val;
    }
    
    static public void log(String s0, String s1) {
    	if (logStopped_) {
    		return;
    	}
		abendInt_.log(s0 + " " + s1);
    }

    static public void abend(String s0, String s1) {
    	if (logStopped_) {
    		return;
    	}
    	logStopped_ = true;
   	
    	abendLock_.lock();
    	System.out.println("***ABEND*** " + s0 + " " + s1);
    	forceCrash();
    	abendLock_.unlock();
    }
    
	static Boolean crashHere_;
    static private void forceCrash() {
    	if (crashHere_) return;
    }
}
