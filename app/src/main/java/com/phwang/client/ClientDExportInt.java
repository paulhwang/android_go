/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.client;

public interface ClientDExportInt {
    public void setupLink(String my_name_val, String password_val);
    public void removeLink();
    public void getLinkData();
    public void getNameList();
    public void setupSession(String his_name_val, String session_setup_data_val);
    public void setupSession2();
    public void setupSession3();
    public void removeSession();
    public void putSessionData(String data_str_val);
    public void getSessionData();
}
