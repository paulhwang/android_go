/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.fabric;

import com.phwang.core.protocols.fabric.FabricInfo;
import com.phwang.core.protocols.theme.ThemeCommands;
import com.phwang.core.protocols.theme.ThemeInfo;
import com.phwang.core.protocols.theme.ThemeResults;
import com.phwang.core.utils.binder.Binder;
import com.phwang.core.protocols.tcpip.TcpIpDefine;
import com.phwang.core.utils.encoders.Encoders;
import com.phwang.core.utils.threadmgr.ThreadMgr;
import com.phwang.core.utils.threadmgr.ThreadEntityInt;

public class FabricUBinder implements ThreadEntityInt {
    private String objectName() {return "FabricUBinder";}
    private String receiveThreadName() { return "UFabricReceiveThread"; }
    
	private static final int NUMBER_OF_D_WORK_THREADS = 5;

    private FabricRoot fabricRoot_;
    private Binder uBinder_;
    
    protected FabricRoot fabricRoot() { return this.fabricRoot_; }
    protected FabricDParser fabricDParser() { return this.fabricRoot().fabricDParser(); }
    private ThreadMgr ThreadMgr() { return this.fabricRoot().threadMgr();}
    private Binder uBinder() { return this.uBinder_; }

    protected FabricUBinder(FabricRoot root_val) {
        this.debug(false, "FabricUBinder", "init start");
        this.fabricRoot_ = root_val;
        this.uBinder_ = new Binder(this.objectName());
        this.uBinder().bindAsTcpServer(true, TcpIpDefine.FABRIC_THEME_PORT, true);
    }

    protected void startThreads() {
    	for (int i = 0; i < NUMBER_OF_D_WORK_THREADS; i++) {
    		this.ThreadMgr().createThreadObject(this.receiveThreadName(), this);
    	}
     }
    
	public void threadCallbackFunction() {
		this.uBinderRreceiveThreadFunc();
	}

    private void uBinderRreceiveThreadFunc() {
        this.debug(false, "uBinderRreceiveThreadFunc", "start " + this.receiveThreadName());

        String data;
        while (true) {
            data = this.uBinder().receiveStringData();
            if (data == null) {
                this.abend("uBinderRreceiveThreadFunc", "null data");
                continue;
            }
            
            this.debug(false, "uBinderRreceiveThreadFunc", "data=" + data);
            this.fabricDParser().parseInputPacket(data);
        }
    }

    protected void transmitData(String data_val) {
        this.debug(false, "transmitData", "data=" + data_val);
        this.uBinder().transmitStringData(data_val);
    }

    public void sendMallocRoomRequestToThemeServer(char theme_type_val, String group_id_str_val, String theme_str_val) {
        this.debug(false, "sendMallocRoomRequestToThemeServer", "theme_str_val=" + theme_str_val);
        ThemeInfo theme_data = new ThemeInfo(
                ThemeCommands.FABRIC_THEME_COMMAND_SETUP_ROOM,
                ThemeResults.UNDECIDED,
                theme_type_val,
                group_id_str_val,
                Encoders.IGNORE
        );
        theme_data.addString(theme_str_val);
        this.transmitData(theme_data.encode());
    }

    protected void sendDataToThemeServer(char theme_type_val, String group_id_str_val, String room_id_str_val, String data_str_val) {
        ThemeInfo theme_data = new ThemeInfo(
                ThemeCommands.FABRIC_THEME_COMMAND_PUT_ROOM_DATA,
                ThemeResults.UNDECIDED,
                theme_type_val,
                group_id_str_val,
                room_id_str_val
        );
        theme_data.addString(data_str_val);
        this.transmitData(theme_data.encode());

    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.fabricRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { this.fabricRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
