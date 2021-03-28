/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.go.main.main;

import android.content.Intent;

import com.phwang.go.define.BundleIndexDefine;
import com.phwang.go.define.CommandDefine;
import com.phwang.go.define.IntentDefine;

public class MainActivityFunc {
    private static final String TAG = "MainActivityFunc";
    private MainActivity mainActivity_;

    public MainActivityFunc(MainActivity main_activity_val) {
        this.mainActivity_= main_activity_val;
    }

    protected void do_setup_link(String my_name_val, String password_val) {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.MAIN_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_COMMAND);
        intent.putExtra(BundleIndexDefine.COMMAND, CommandDefine.FABRIC_COMMAND_SETUP_LINK_STR);
        intent.putExtra(BundleIndexDefine.MY_NAME, my_name_val);
        intent.putExtra(BundleIndexDefine.PASSWORD, password_val);
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.mainActivity_.sendBroadcast(intent);
    }

    protected void do_setup_session(String his_name_val, String theme_data_val) {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.MAIN_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_COMMAND);
        intent.putExtra(BundleIndexDefine.COMMAND, CommandDefine.FABRIC_COMMAND_SETUP_SESSION_STR);
        intent.putExtra(BundleIndexDefine.HIS_NAME, his_name_val);
        intent.putExtra(BundleIndexDefine.THEME_DATA, theme_data_val);
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.mainActivity_.sendBroadcast(intent);
    }

    protected void do_setup_session3(String theme_data_val) {
        Intent intent = new Intent();
        intent.putExtra(BundleIndexDefine.STAMP, BundleIndexDefine.THE_STAMP);
        intent.putExtra(BundleIndexDefine.FROM, IntentDefine.MAIN_ACTIVITY);
        intent.putExtra(BundleIndexDefine.COMMAND_OR_RESPONSE, BundleIndexDefine.IS_COMMAND);
        intent.putExtra(BundleIndexDefine.COMMAND, CommandDefine.FABRIC_COMMAND_SETUP_SESSION3_STR);
        intent.putExtra(BundleIndexDefine.THEME_DATA, theme_data_val);
        intent.setAction(IntentDefine.BIND_SERVICE);
        this.mainActivity_.sendBroadcast(intent);
    }
}
