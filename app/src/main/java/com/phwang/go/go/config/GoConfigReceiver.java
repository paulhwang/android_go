/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.config;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.phwang.client.ClientFabricResultImport;
import com.phwang.core.fabric.FabricCommands;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.go.game.GoGameActivity;
import com.phwang.go.main.login.LoginActivity;

public class GoConfigReceiver extends BroadcastReceiver {
    private static final String TAG = "GoConfigReceiver";
    private GoConfigActivity goConfigActivity_;

    public GoConfigReceiver(GoConfigActivity go_config_activity_val) {
        this.goConfigActivity_ = go_config_activity_val;
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();
        String stamp = bundle.getString(BundleIndexDefine.STAMP);
        if ((stamp == null) || !stamp.equals(BundleIndexDefine.THE_STAMP)) {
            Log.e(TAG, "onReceive() bad-stamp. command=" + bundle.getString(BundleIndexDefine.COMMAND));
            return;
        }
        this.handleReceivedBundle(bundle);
    }

    private void handleReceivedBundle(Bundle bundle_val) {
        String command = bundle_val.getString(BundleIndexDefine.COMMAND);
        char result = bundle_val.getString(BundleIndexDefine.RESULT).charAt(0);
        String data_package_str = bundle_val.getString(BundleIndexDefine.DATA_PACKAGE);

        Log.e(TAG, "handleReceivedBundle() command=" + command + ", result=" + result + " data=" + data_package_str);

        if (command == null) {
            Log.e(TAG, "handleReceivedBundle() null command========================");
            return;
        }

        switch (command.charAt(0)) {
            case FabricCommands.FABRIC_COMMAND_SOLO_SESSION:
                this.processSetupSoloSessionResponse(result, data_package_str);
                break;
            case FabricCommands.FABRIC_COMMAND_HEAD_SESSION:
                this.processSetupHeadSessionResponse(result, data_package_str);
                break;
            case FabricCommands.FABRIC_COMMAND_PEER_SESSION:
                this.processSetupPeerSessionResponse(result, data_package_str);
                break;
            case FabricCommands.FABRIC_COMMAND_JOIN_SESSION:
                this.processSetupJoinSessionResponse(result, data_package_str);
                break;
            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION:
                if (result == ClientFabricResultImport.SUCCEED) {
                    Intent intent = new Intent(this.goConfigActivity_, GoGameActivity.class);
                    this.goConfigActivity_.startActivity(intent);
                    break;
                }
                else if (result == ClientFabricResultImport.FAIL_LINK_NOT_EXIST.charAt(0)) {
                    Intent intent = new Intent(this.goConfigActivity_, LoginActivity.class);
                    this.goConfigActivity_.startActivity(intent);
                    break;
                }
                else {

                }
                break;
            default:
                break;
        }
    }

    private void processSetupSoloSessionResponse(char result_val, String data_package_str_val) {
        if (result_val == ClientFabricResultImport.SUCCEED) {
            Intent intent = new Intent(this.goConfigActivity_, GoGameActivity.class);
            intent.putExtra(BundleIndexDefine.DATA_PACKAGE, data_package_str_val);
            this.goConfigActivity_.startActivity(intent);
            return;
        }
        else if (result_val == ClientFabricResultImport.FAIL_LINK_NOT_EXIST.charAt(0)) {
            Intent intent = new Intent(this.goConfigActivity_, LoginActivity.class);
            this.goConfigActivity_.startActivity(intent);
            return;
        }
        else {

        }
    }

    private void processSetupHeadSessionResponse(char result_val, String data_package_str_val) {
        if (result_val == ClientFabricResultImport.SUCCEED) {
            Intent intent = new Intent(this.goConfigActivity_, GoGameActivity.class);
            intent.putExtra(BundleIndexDefine.DATA_PACKAGE, data_package_str_val);
            this.goConfigActivity_.startActivity(intent);
            return;
        }
        else if (result_val == ClientFabricResultImport.FAIL_LINK_NOT_EXIST.charAt(0)) {
            Intent intent = new Intent(this.goConfigActivity_, LoginActivity.class);
            this.goConfigActivity_.startActivity(intent);
            return;
        }
        else {

        }
    }

    private void processSetupPeerSessionResponse(char result_val, String data_package_str_val) {
        if (result_val == ClientFabricResultImport.SUCCEED) {
            Intent intent = new Intent(this.goConfigActivity_, GoGameActivity.class);
            intent.putExtra(BundleIndexDefine.DATA_PACKAGE, data_package_str_val);
            this.goConfigActivity_.startActivity(intent);
            return;
        }
        else if (result_val == ClientFabricResultImport.FAIL_LINK_NOT_EXIST.charAt(0)) {
            Intent intent = new Intent(this.goConfigActivity_, LoginActivity.class);
            this.goConfigActivity_.startActivity(intent);
            return;
        }
        else {

        }
    }

    private void processSetupJoinSessionResponse(char result_val, String data_package_str_val) {
        if (result_val == ClientFabricResultImport.SUCCEED) {
            Intent intent = new Intent(this.goConfigActivity_, GoGameActivity.class);
            intent.putExtra(BundleIndexDefine.DATA_PACKAGE, data_package_str_val);
            this.goConfigActivity_.startActivity(intent);
            return;
        }
        else if (result_val == ClientFabricResultImport.FAIL_LINK_NOT_EXIST.charAt(0)) {
            Intent intent = new Intent(this.goConfigActivity_, LoginActivity.class);
            this.goConfigActivity_.startActivity(intent);
            return;
        }
        else {

        }
    }
}
