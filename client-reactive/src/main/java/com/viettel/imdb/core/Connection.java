package com.viettel.imdb.core;

import com.viettel.imdb.type.IMDBSingleRequest;
import com.viettel.imdb.type.IMDBSingleResponse;
import reactor.core.publisher.MonoSink;

public abstract class Connection {
    private IMDBScheduler scheduler;
    IMDBSingleRequest request;
    private MonoSink sink;
    private Node node;

    public void setScheduler(IMDBScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public IMDBScheduler getScheduler() {
        return scheduler;
    }

    public <T extends IMDBSingleResponse> Connection attachRequest(IMDBSingleRequest<T> request, MonoSink<T> sink) {
        this.request = request;
        this.sink = sink;
        return this;
    }

    public abstract void send();

    void receiveException(/* input here */) {
        sink.error(request.deserializeException());
    }

    public void receiveValue(/* input here */) {
        sink.success(request.deserializeValue());
    }

    public AuthToken getToken() {
        return node.getToken();
    }
}
