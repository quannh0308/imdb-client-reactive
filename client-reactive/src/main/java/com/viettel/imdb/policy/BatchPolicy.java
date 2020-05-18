package com.viettel.imdb.policy;

public class BatchPolicy {
    public static BatchPolicy just() {
        return new BatchPolicy();
    }

    private BatchPolicy() {

    }
}
