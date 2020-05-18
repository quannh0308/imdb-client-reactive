package com.viettel.imdb.core;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String host;
    List<Connection> connectionPool;
    AuthToken token;

    public Node(String host) {
        this.host = host;
        connectionPool = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            connectionPool.add(new ReactorConnection()); // todo connectionPool
        }
    }

    public Connection nextConnection() {
        return connectionPool.get(0); // todo
    }

    public boolean releaseConnection(Connection connection) {
        return true;
    }

    public AuthToken getToken() {
        return token;
    }

    public void setToken(AuthToken token) {
        this.token = token;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public List<Connection> getConnectionPool() {
        return connectionPool;
    }

    public void setConnectionPool(List<Connection> connectionPool) {
        this.connectionPool = connectionPool;
    }
}
