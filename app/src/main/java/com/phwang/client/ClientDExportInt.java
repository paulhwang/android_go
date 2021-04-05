/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.client;

public interface ClientDExportInt {
    public void doRegister(String my_name_val, String password_val, String email_str_val);
    public void doLogin(String my_name_val, String password_val);
    public void doLogout();
    public void doGetGroups();
    public void getLinkData();
    public void getNameList();
    public void setupSoloSession(String data_str_val);
    public void setupHeadSession(String data_str_val);
    public void setupPeerSession(String data_str_val);
    public void setupJoinSession(String data_str_val);
}
