package com.viettel.imdb.policy;

public class QueryPolicy {
    public static QueryPolicy just() {
        return new QueryPolicy();
    }

    private QueryPolicy() {
    }
}
