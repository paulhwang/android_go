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
        String data = bundle.getString("Data");
        Log.e("MainReceiver", "Data=" + data);

        //this.BindUClient().doSetupSession("phwang", "00000000G100000");
    }
}
