package com.phwang.go.services;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.phwang.go.bind.BindUClient;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.CommandDefine;
import com.phwang.go.define.IntentDefine;

public class BindServiceUFunc {
    private static final String TAG = "BindServiceUFunc";
    BindReceiver bindReceiver_;

    private BindService bindService() { return this.bindReceiver_.bindService(); };
    private BindUClient bindUClient() { return bindReceiver_.bindUClient(); }

    public BindServiceUFunc(BindReceiver bind_receiver_val) {
        this.bindReceiver_ = bind_receiver_val;
    }

    protected void handleCommand(Bundle bundle_val) {
        String command = bundle_val.getString(BundleIndexDefine.COMMAND);
        Log.e(TAG, "handleCommand() command=" + command);

        if (command == null) {
            Log.e(TAG, "handleCommand() null command=");
            return;
        }

        switch (command.charAt(0)) {
            case CommandDefine.FABRIC_COMMAND_SETUP_LINK:
                String my_name = bundle_val.getString(BundleIndexDefine.MY_NAME);
                String password = bundle_val.getString(BundleIndexDefine.PASSWORD);
                Log.e(TAG, "handleReceivedBundle() command=" + command + " name=" + my_name + "," + password);
                this.bindUClient().doSetupLink(my_name, password);
                break;

            case CommandDefine.FABRIC_COMMAND_SETUP_SESSION:
                String his_name = bundle_val.getString(BundleIndexDefine.HIS_NAME);
                String theme_data = bundle_val.getString(BundleIndexDefine.THEME_DATA);
                this.bindUClient().doSetupSession(his_name, theme_data);
                break;

            case CommandDefine.FABRIC_COMMAND_SETUP_SESSION3:
                this.bindUClient().doSetupSession3();
                break;

            case CommandDefine.FABRIC_COMMAND_PUT_SESSION_DATA:
                String move_data = bundle_val.getString(BundleIndexDefine.MOVE_DATA);
                this.bindUClient().doPutSessionData(move_data);
                break;

            case CommandDefine.FABRIC_COMMAND_GET_SESSION_DATA:
                this.bindUClient().doGetSessionData();
                break;

            default:
        }
    }

    protected void sendResponseBroadcastMessage(String target_val, String command_val, String result_val, String data_val) {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.BIND_SERVICE);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_RESPONSE);
        intent.putExtra(BundleIndexDefine.COMMAND, command_val);
        intent.putExtra(BundleIndexDefine.RESULT, result_val);
        intent.putExtra(BundleIndexDefine.DATA, data_val);
        intent.setAction(target_val);
        this.bindService().sendBroadcast(intent);
    }
}
