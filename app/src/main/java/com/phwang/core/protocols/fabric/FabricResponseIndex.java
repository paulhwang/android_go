package com.phwang.core.protocols.fabric;

public class FabricResponseIndex {
    /* l***001002000116phwang */
    public static final int CODE_INDEX = 0;
    public static final int RESULT_INDEX = 4;
    public static final int LINK_ID_INDEX = RESULT_INDEX + FabricSize.RESULT_SIZE;
    public static final int NEXT_TO_LINK_ID_INDEX = LINK_ID_INDEX + FabricSize.LINK_ID_SIZE;
    public static final int SESSION_ID_INDEX = LINK_ID_INDEX + FabricSize.LINK_ID_SIZE;
    public static final int NEXT_TO_SESSION_ID_INDEX = SESSION_ID_INDEX + FabricSize.SESSION_ID_SIZE;

}
