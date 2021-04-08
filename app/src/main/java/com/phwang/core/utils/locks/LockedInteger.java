/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.utils.locks;

import java.util.concurrent.locks.*;

public class LockedInteger {
	private Lock lock_;
	private int it_;
	
	public LockedInteger(int val) {
        this.lock_= new ReentrantLock();
        this.it_ = val;
	}
	
	public int get() { return this.it_; }
	
	public int increment() {
		int i;
		this.lock_.lock();
		this.it_++;
		i = this.it_;
		this.lock_.unlock();
		return i;
	}
		
	public int decrement() {
		int i;
		this.lock_.lock();
		this.it_--;
		i = this.it_;
		this.lock_.unlock();
		return i;
	}
}
