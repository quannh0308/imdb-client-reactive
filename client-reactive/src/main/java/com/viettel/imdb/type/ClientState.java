package com.viettel.imdb.type;

import java.util.HashMap;
import java.util.Map;

public enum ClientState {
    INITIALIZE(0),
    CONFIGURED(1),
    CONNECTED(2),
    AUTHENTICATED(3),
    READY(4);
    private final byte value;

    ClientState(int value) {
        this.value = (byte) value;
    }

    public static ClientState valueOf(final int value) {
        try {
            return map.get((byte) value);
        } catch (Exception ex) {
            return ClientState.INITIALIZE;
        }
    }

    private static Map<Byte, ClientState> map = new HashMap<>();

    static {
        for (ClientState stage : ClientState.values()) {
            map.put(stage.value, stage);
        }
    }

}
