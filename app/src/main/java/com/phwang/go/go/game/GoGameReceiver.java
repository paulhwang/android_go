/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.go.game;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.phwang.core.utils.Encoders;
import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.CommandDefine;

public class GoGameReceiver extends BroadcastReceiver {
    private static final String TAG = "GoGameReceiver";
    private GoGameActivity goGameActivity_;

    private GoGameBoard goGameBoard() { return this.goGameActivity_.goBoard(); }
    protected GoGameActivityFunc goGameFunc() { return this.goGameActivity_.goGameFunc(); };

    public GoGameReceiver(GoGameActivity go_game_activity_val) {
        this.goGameActivity_ = go_game_activity_val;
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();
        String stamp = bundle.getString(BundleIndexDefine.STAMP);
        if ((stamp == null) || !stamp.equals(BundleIndexDefine.THE_STAMP)) {
            Log.e(TAG, "onReceive() bad stamp. command=" + bundle.getString(BundleIndexDefine.COMMAND));
            return;
        }
        this.handleReceivedBundle(bundle);
    }

    private void handleReceivedBundle(Bundle bundle_val) {
        String command = bundle_val.getString(BundleIndexDefine.COMMAND);
        String result = bundle_val.getString(BundleIndexDefine.RESULT);
        String data = bundle_val.getString(BundleIndexDefine.DATA);
        Log.e(TAG, "handleReceivedBundle(000) command=" + command + ", result=" + result + " data=" + data);

        if (command != null) {
            Log.e(TAG, "handleReceivedBundle(bbb) command=" + command + ", result=" + result + " data=" + data);
            switch (command.charAt(0)) {
                case CommandDefine.FABRIC_COMMAND_SOLO_SESSION:
                    Log.e(TAG, "handleReceivedBundle(aaa) command=" + command + ", result=" + result + " data=" + data);
                    this.decodeGoConfig(data);
                    break;

                case CommandDefine.FABRIC_COMMAND_PUT_SESSION_DATA:
                    this.goGameFunc().do_get_session_data();
                    break;

                case CommandDefine.FABRIC_COMMAND_GET_SESSION_DATA:
                    this.goGameFunc().processGetSessionData(data);
                    break;

                default:
                    break;
            }
        }
    }

    void decodeGoConfig(String config_str_val) {
        Log.e(TAG, "decodeGoConfig() config_str=" + config_str_val);

        String len_str = config_str_val.substring(0,3);
        String board_size_str = config_str_val.substring(3, 5);
        String handicap_str = config_str_val.substring(5, 7);
        String komi_str = config_str_val.substring(7, 9);

        this.goGameBoard().setBoardSize(Encoders.iDecodeRaw(board_size_str));
        //this.handicapPoint_ = Encoders.iDecodeRaw(handicap_str);
        //this.komiPoint_ = Encoders.iDecodeRaw(komi_str);
    }
}
