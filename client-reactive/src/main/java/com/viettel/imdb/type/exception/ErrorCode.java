package com.viettel.imdb.type.exception;

import java.util.HashMap;
import java.util.Map;

public enum ErrorCode {
    NO_ERROR(0),
    INVALID_ERROR(1),
    CLUSTER_NOT_READY(2);

    private final short value;

    ErrorCode(int value) {
        this.value = (short) value;
    }

    public static ErrorCode valueOf(final int value) {
        try {
            return map.get((short) value);
        } catch (Exception ex) {
            return ErrorCode.INVALID_ERROR;
        }
    }

    private static Map<Short, ErrorCode> map = new HashMap<>();

    static {
        for (ErrorCode errorCode : ErrorCode.values()) {
            map.put(errorCode.value, errorCode);
        }
    }

}
