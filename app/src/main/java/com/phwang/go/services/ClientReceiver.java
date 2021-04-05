/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.phwang.go.define.BundleIndexDefine;

public class ClientReceiver extends BroadcastReceiver {
    private static final String TAG = "ClientReceiver";
    private ClientService clientService_;

    private ClientUParser clientUParser() { return this.clientService_.clientUParser(); }

    public ClientReceiver(ClientService client_service_val) {
        this.clientService_ = client_service_val;
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();
        String fabric_data_str = bundle.getString(BundleIndexDefine.FABRIC_DATA);
        this.clientUParser().parseUCommand(fabric_data_str);
    }
}
