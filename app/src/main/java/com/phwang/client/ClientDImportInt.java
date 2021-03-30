/*
 ******************************************************************************
 *                                       
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.client;

public interface ClientDImportInt {
	public void handleSetupLinkResponse(String result_str_val);
	public void handleRemoveLinkResponse(String result_str_val);
	public void handleGetLinkDataResponse(String result_str_val);
	public void handleGetNameListResponse(String result_str_val);
	public void handleSetupSessionResponse(String result_str_val);
	public void handleSetupSession2Response();
	public void handleSetupSession3Response();
	public void handlePutSessionDataResponse(String result_str_val);
	public void handleGetSessionDataResponse(String result_str_val, String data_val);
}
