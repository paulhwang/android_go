/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.client;

public interface ClientDImportInt {
	public void handleRegisterResponse(String result_str_val);
	public void handleLoginResponse(String result_str_val);
	public void handleLogoutResponse(String result_str_val);
	public void handleGetGroupsResponse(String result_str_val);
	public void handleGetLinkDataResponse(String result_str_val);
	public void handleGetNameListResponse(String result_str_val);
	public void handleSetupSoloSessionResponse(String result_str_val, String theme_str_val);
	public void handleSetupHeadSessionResponse(String result_str_val, String theme_str_val);
	public void handleSetupPeerSessionResponse(String result_str_val, String theme_str_val);
	public void handleSetupJoinSessionResponse(String result_str_val, String theme_str_val);
	public void handleSetupSessionResponse(String result_str_val);
	public void handleSetupSession2Response(String result_str_val);
	public void handleSetupSession3Response(String result_str_val);
	public void handlePutSessionDataResponse(String result_str_val);
	public void handleGetSessionDataResponse(String result_str_val, String data_val);
}
