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
    private ClientUParser clientUParser_;

    protected ClientService clientService() { return this.clientService_; };
    protected ClientUParser clientUParser() { return this.clientUParser_; };
    protected ClientRoot clientRoot() { return this.clientService().clientRoot(); }

    public ClientReceiver(ClientService bind_service_val) {
        this.clientService_ = bind_service_val;
        this.clientUParser_ = new ClientUParser(this.clientRoot());
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();
        String stamp = bundle.getString(BundleIndexDefine.STAMP);
        String fabric_data_str = bundle.getString(BundleIndexDefine.FABRIC_DATA);

        if ((stamp == null) || !stamp.equals(BundleIndexDefine.THE_STAMP)) {
            Log.e(TAG, "onReceive() bad-stamp. fabric_data_str=" + fabric_data_str);
            return;
        }

        this.clientUParser_.parseUCommand(fabric_data_str);
    }
}
