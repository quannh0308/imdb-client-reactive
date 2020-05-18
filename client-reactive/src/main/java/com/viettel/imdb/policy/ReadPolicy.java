package com.viettel.imdb.policy;

public class ReadPolicy {
    public static ReadPolicy just() {
        return new ReadPolicy();
    }

    private ReadPolicy() {
    }
}
