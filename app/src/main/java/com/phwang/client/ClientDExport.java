/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.client;

import com.phwang.core.fabric.FabricClients;
import com.phwang.core.fabric.FabricCommands;
import com.phwang.core.utils.binder.Binder;
import com.phwang.core.utils.encoders.Encoders;

public class ClientDExport implements ClientDExportInt {
    private String objectName() {return "ClientDExport";}
    
    private ClientRoot clientRoot_;
    
    private ClientRoot clientRoot() { return this.clientRoot_; }
    private ClientUBinder clientUBinder() { return this.clientRoot_.clientUBinder(); }
    private Binder uBinder() { return this.clientUBinder().uBinder(); }
    private ClientFabricInfo clientFabricInfo() { return this.clientRoot_.clientFabricInfo(); }
    private ClientGoConfig goConfig() { return this.clientRoot_.goConfig(); }

    protected ClientDExport(ClientRoot root_val) {
        this.debug(false, "ClientDExport", "init start");
        
    	this.clientRoot_ = root_val;
    }

    public void transmitToFabric(String data_str_val) {
    	this.debug(true, "transmitToFabric", "data_str_val=" + data_str_val);
       	this.uBinder().transmitStringData(data_str_val);
    }

    public void doRegister(String my_name_val, String password_val, String email_val) {
        this.debug(true, "doRegister", "name=" + my_name_val);

        StringBuilder command_buf = new StringBuilder();
        command_buf.append(FabricCommands.FABRIC_COMMAND_REGISTER);
        command_buf.append(Encoders.sEncode2(my_name_val));
        command_buf.append(Encoders.sEncode2(password_val));
        command_buf.append(Encoders.sEncode2(email_val));
        String command_str = command_buf.toString();

        this.debug(false, "doRegister", "command_str=" + command_str);

        this.transmitToFabric(command_str);
    }
    
    public void doLogin(String my_name_val, String password_val) {
    	this.debug(false, "doLogin", "name=" + my_name_val);
    	
        StringBuilder command_buf = new StringBuilder();
        command_buf.append(FabricCommands.FABRIC_COMMAND_LOGIN);
        command_buf.append(FabricClients.ANDROID);
        command_buf.append(Encoders.sEncode2(my_name_val));
        command_buf.append(Encoders.sEncode2(password_val));
        String command_str = command_buf.toString();
        
    	this.debug(false, "doLogin", "command_str=" + command_str);
    	
    	this.transmitToFabric(command_str);
    }

    public void doLogout() {
        this.debug(true, "doLogout", "doLogout");
        if (this.clientFabricInfo().linkIdStr() == null) {
            return;
        }

        StringBuilder command_buf = new StringBuilder();
        command_buf.append(FabricCommands.FABRIC_COMMAND_LOGOUT);
        command_buf.append(this.clientFabricInfo().linkIdStr());
        String command_str = command_buf.toString();

        this.debug(false, "doLogout", "command_str=" + command_str);

        this.transmitToFabric(command_str);
    }

    public void doGetGroups() {
        this.debug(true, "doGetGroups", "doGetGroups");

        StringBuilder command_buf = new StringBuilder();
        command_buf.append(FabricCommands.FABRIC_COMMAND_GET_GROUPS);
        command_buf.append(this.clientFabricInfo().linkIdStr());
        String command_str = command_buf.toString();

        this.debug(false, "doGetGroups", "command_str=" + command_str);

        this.transmitToFabric(command_str);
    }
    
    public void getLinkData() {
    	this.debug(false, "getLinkData", "link_id=" + this.clientFabricInfo().linkIdStr());
    	
        StringBuilder command_buf = new StringBuilder();
        command_buf.append(FabricCommands.FABRIC_COMMAND_GET_LINK_DATA);
        command_buf.append(this.clientFabricInfo().linkIdStr()); 
        String command_str = command_buf.toString();
        
    	this.debug(false, "getLinkData", "command_str=" + command_str);
    	
    	this.transmitToFabric(command_str);
    }
    
    public void getNameList() {
    	this.debug(false, "getNameList", "link_id=" + this.clientFabricInfo().linkIdStr());
    	
        StringBuilder command_buf = new StringBuilder();
        command_buf.append(FabricCommands.FABRIC_COMMAND_GET_LINK_DATA);
        command_buf.append(this.clientFabricInfo().linkIdStr()); 
        String command_str = command_buf.toString();
        
    	this.debug(false, "getNameList", "command_str=" + command_str);
    	
    	this.transmitToFabric(command_str);
    }

    public void setupSoloSession(String data_str_val) {
        this.debug(false, "setupSoloSession", "data_str_val=" + data_str_val);

        StringBuilder command_buf = new StringBuilder();
        command_buf.append(FabricCommands.FABRIC_COMMAND_SOLO_SESSION);
        command_buf.append(this.clientFabricInfo().linkIdStr());
        command_buf.append(data_str_val);
        String command_str = command_buf.toString();

        this.debug(false, "setupSoloSession", "command_str=" + command_str);

        this.transmitToFabric(command_str);
    }

    public void setupHeadSession(String data_str_val) {
        this.debug(false, "setupHeadSession", "data_str_val=" + data_str_val);

        StringBuilder command_buf = new StringBuilder();
        command_buf.append(FabricCommands.FABRIC_COMMAND_HEAD_SESSION);
        command_buf.append(this.clientFabricInfo().linkIdStr());
        command_buf.append(data_str_val);
        String command_str = command_buf.toString();

        this.debug(false, "setupHeadSession", "command_str=" + command_str);

        this.transmitToFabric(command_str);
    }

    public void setupPeerSession(String data_str_val) {
        this.debug(false, "setupPeerSession", "data_str_val=" + data_str_val);

        StringBuilder command_buf = new StringBuilder();
        command_buf.append(FabricCommands.FABRIC_COMMAND_PEER_SESSION);
        command_buf.append(this.clientFabricInfo().linkIdStr());
        command_buf.append(data_str_val);
        String command_str = command_buf.toString();

        this.debug(false, "setupPeerSession", "command_str=" + command_str);

        this.transmitToFabric(command_str);
    }

    public void setupJoinSession(String data_str_val) {
        this.debug(false, "setupJoinSession", "data_str_val=" + data_str_val);

        StringBuilder command_buf = new StringBuilder();
        command_buf.append(FabricCommands.FABRIC_COMMAND_JOIN_SESSION);
        command_buf.append(this.clientFabricInfo().linkIdStr());
        command_buf.append(data_str_val);
        String command_str = command_buf.toString();

        this.debug(false, "setupJoinSession", "command_str=" + command_str);

        this.transmitToFabric(command_str);
    }

    private void debug(Boolean on_off, String s0, String s1) { if (on_off) this.log(s0, s1); }
    private void log(String s0, String s1) { this.clientRoot().logIt(this.objectName() + "." + s0 + "()", s1); }
    protected void abend(String s0, String s1) { this.clientRoot().abendIt(this.objectName() + "." + s0 + "()", s1); }
}
