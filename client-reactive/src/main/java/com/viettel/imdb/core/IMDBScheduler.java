package com.viettel.imdb.core;

import com.viettel.imdb.type.ClientConfig;

public class IMDBScheduler {
    ClientConfig config;
    Cluster cluster;
    Auth auth;

    // todo should be interface/abstract class
    public static IMDBScheduler just() {
        return new IMDBScheduler();
    }

    private IMDBScheduler() {
        config = ClientConfig.just();
        // reactor.netty.Connection connection = TcpClient.newConnection().connectNow();
        cluster = new Cluster();// Cluster.just().initialize();
        auth = new Auth();
    }

    public IMDBScheduler connect() {
        cluster.connect();
        return this;
    }

    public IMDBScheduler authen() {
        auth.start();
        return this;
    }

    public IMDBScheduler start() {
        return this;
    }

    public Cluster getCluster() {
        return cluster;
    }
}
