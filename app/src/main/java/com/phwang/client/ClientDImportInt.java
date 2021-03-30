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
	public void handleGetLinkDataResponse();
	public void handleGetNameListResponse();
	public void handleSetupSessionResponse(String result_str_val);
	public void handleSetupSession2Response();
	public void handleSetupSession3Response();
	public void handlePutSessionDataResponse();
	public void handleGetSessionDataResponse(String data_val);
}
