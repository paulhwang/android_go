package com.phwang.go;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.phwang.bind.BindUClient;

public class MainReceiver extends BroadcastReceiver {
    private MainActivity mainActivity_;

    private BindUClient BindUClient() { return this.mainActivity_.bindUClient(); }

    public MainReceiver(MainActivity mainActivity_) {
        this.mainActivity_ = mainActivity_;
    }

    @Override
    public void onReceive(Context context_val, Intent intent_val) {
        Bundle bundle = intent_val.getExtras();
        String command = bundle.getString("command");
        String result = bundle.getString("result");
        Log.e("MainReceiver", "command=" + command + ", result=" + result);

        switch (command.charAt(0)) {
            case 'L':
                this.BindUClient().doSetupSession("phwang", "00000000G111111");
                break;

            case 'S':
                this.BindUClient().doSetupSession3();
                break;

            case 'T':
                break;

            default:

        }

        //this.BindUClient().doSetupSession("phwang", "00000000G100000");
    }
}
