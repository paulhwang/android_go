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
import com.phwang.core.fabric.FabricCommands;
import com.phwang.core.fabric.FabricResults;
import com.phwang.core.utils.fabric.FabricDecode;
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
        String fabric_data_str = bundle_val.getString(BundleIndexDefine.FABRIC_DATA);
        Log.e(TAG, "handleReceivedBundle() fabric_data_str=" + fabric_data_str);

        FabricDecode fabric_decode = new FabricDecode(fabric_data_str);
        char command = fabric_decode.command();
        char result = fabric_decode.result();

        switch (command) {
            case FabricCommands.FABRIC_COMMAND_SOLO_SESSION:
                this.processSetupSoloSessionResponse(result, fabric_data_str);
                break;
            case FabricCommands.FABRIC_COMMAND_HEAD_SESSION:
                this.processSetupHeadSessionResponse(result, fabric_data_str);
                break;
            case FabricCommands.FABRIC_COMMAND_PEER_SESSION:
                this.processSetupPeerSessionResponse(result, fabric_data_str);
                break;
            case FabricCommands.FABRIC_COMMAND_JOIN_SESSION:
                this.processSetupJoinSessionResponse(result, fabric_data_str);
                break;
            case FabricCommands.FABRIC_COMMAND_SETUP_SESSION:
                if (result == FabricResults.SUCCEED) {
                    Intent intent = new Intent(this.goConfigActivity_, GoGameActivity.class);
                    this.goConfigActivity_.startActivity(intent);
                    break;
                }
                else if (result == FabricResults.LINK_NOT_EXIST) {
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

    private void processSetupSoloSessionResponse(char result_val, String fabric_data_str_val) {
        if (result_val == FabricResults.SUCCEED) {
            Intent intent = new Intent(this.goConfigActivity_, GoGameActivity.class);
            intent.putExtra(BundleIndexDefine.DATA_PACKAGE, fabric_data_str_val);
            this.goConfigActivity_.startActivity(intent);
            return;
        }
        else if (result_val == FabricResults.LINK_NOT_EXIST) {
            Intent intent = new Intent(this.goConfigActivity_, LoginActivity.class);
            this.goConfigActivity_.startActivity(intent);
            return;
        }
        else {

        }
    }

    private void processSetupHeadSessionResponse(char result_val, String data_package_str_val) {
        if (result_val == FabricResults.SUCCEED) {
            Intent intent = new Intent(this.goConfigActivity_, GoGameActivity.class);
            intent.putExtra(BundleIndexDefine.DATA_PACKAGE, data_package_str_val);
            this.goConfigActivity_.startActivity(intent);
            return;
        }
        else if (result_val == FabricResults.LINK_NOT_EXIST) {
            Intent intent = new Intent(this.goConfigActivity_, LoginActivity.class);
            this.goConfigActivity_.startActivity(intent);
            return;
        }
        else {

        }
    }

    private void processSetupPeerSessionResponse(char result_val, String data_package_str_val) {
        if (result_val == FabricResults.SUCCEED) {
            Intent intent = new Intent(this.goConfigActivity_, GoGameActivity.class);
            intent.putExtra(BundleIndexDefine.DATA_PACKAGE, data_package_str_val);
            this.goConfigActivity_.startActivity(intent);
            return;
        }
        else if (result_val == FabricResults.LINK_NOT_EXIST) {
            Intent intent = new Intent(this.goConfigActivity_, LoginActivity.class);
            this.goConfigActivity_.startActivity(intent);
            return;
        }
        else {

        }
    }

    private void processSetupJoinSessionResponse(char result_val, String data_package_str_val) {
        if (result_val == FabricResults.SUCCEED) {
            Intent intent = new Intent(this.goConfigActivity_, GoGameActivity.class);
            intent.putExtra(BundleIndexDefine.DATA_PACKAGE, data_package_str_val);
            this.goConfigActivity_.startActivity(intent);
            return;
        }
        else if (result_val == FabricResults.LINK_NOT_EXIST) {
            Intent intent = new Intent(this.goConfigActivity_, LoginActivity.class);
            this.goConfigActivity_.startActivity(intent);
            return;
        }
        else {

        }
    }
}
