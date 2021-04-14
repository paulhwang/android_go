/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.fabric;

public class FabricGroupInfo {
    private static final String TAG = "FabricGroupInfo";

    private String groupIdStr_;

    public String groupIdStr() { return this.groupIdStr_; };

    public FabricGroupInfo(String fabric_group_info_str_val) {
        String rest_str = fabric_group_info_str_val;

        this.groupIdStr_ = rest_str.substring(0, FabricGroupMgr.FABRIC_GROUP_ID_SIZE);
        rest_str = rest_str.substring(FabricGroupMgr.FABRIC_GROUP_ID_SIZE);
    }

    public static String encode(String group_id_str_val) {
        StringBuilder buf = new StringBuilder();
        buf.append(group_id_str_val);
        return buf.toString();
    }
}
