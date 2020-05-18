package com.viettel.imdb.core;

public class Auth {
    public AuthToken getToken(Connection connection) {
        return new AuthToken();
    }

    public void start() {
        // start schedulers here
    }
}
