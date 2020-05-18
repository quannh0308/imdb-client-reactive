package com.viettel.imdb.mock;

import com.viettel.imdb.Client;
import com.viettel.imdb.core.Engine;
import com.viettel.imdb.policy.*;
import com.viettel.imdb.type.*;
import org.pmw.tinylog.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

import static com.viettel.imdb.core.Transporter.sendSingleRequest;
import static com.viettel.imdb.util.validator.ClientStateValidator.validateClientState;

public class MockClient implements Client {
    private Random random;

    private ClientState state;
    private ClientConfig clientConfig;
    private Engine engine;


    private MockClient() {
        random = new SecureRandom();
        state = ClientState.INITIALIZE;
        clientConfig = ClientConfig.just();
        engine = Engine.just();
        Logger.info("Initialize a mock client");
    }

    public static MockClient just() {
        return new MockClient();
    }

    @Override
    public Policy getDefaultPolicy() {
        return Policy.just();
    }

    @Override
    public WritePolicy getDefaultWritePolicy() {
        return WritePolicy.just();
    }

    @Override
    public ReadPolicy getReadPolicy() {
        return ReadPolicy.just();
    }

    @Override
    public QueryPolicy getQueryPolicy() {
        return QueryPolicy.just();
    }

    @Override
    public BatchPolicy getBatchPolicy() {
        return BatchPolicy.just();
    }

    @Override
    public Client loadConfig(String configFilePath) {
        validateClientState(state, ClientState.INITIALIZE);
        clientConfig.loadConfig(configFilePath);
        state = ClientState.CONFIGURED;
        Logger.info("Client load config from file {}", configFilePath);
        return this;
    }

    /*@Override
    public Client connectToHost(String host) {
        return null;
    }

    @Override
    public Client connectToHosts(List<String> hosts) {
        return null;
    }*/

    @Override
    public Client connect() {
        validateClientState(state, ClientState.CONFIGURED);
        Logger.info("Client try to connect to server {}", clientConfig.getHosts());
        Logger.info("Client connect to server {} successfully", clientConfig.getHosts());
        engine.connect();
        state = ClientState.CONNECTED;
        return this;
    }

    /*@Override
    public Client authen(AuthenInfo authenInfo) {
        return null;
    }*/

    @Override
    public Client authen() {
        validateClientState(state, ClientState.CONNECTED);
        engine.authen();
        state = ClientState.AUTHENTICATED;
        Logger.info("Client authenticated successfully with ({},{})", clientConfig.getUsername(), clientConfig.getPassword());
        return this;
    }

    @Override
    public Client start() {
        validateClientState(state, ClientState.AUTHENTICATED);
        engine.start();
        state = ClientState.READY;
        Logger.info("Client started");
        return this;
    }

    @Override
    public Mono<CreateTableResponse> createTable(TableName tableName) {
        validateClientReady();
        return sendSingleRequest(CreateTableRequest
                .just()
                .setTableName(tableName));

        // return Mono.delay(Duration.ofMillis(random.nextInt(2000))).then();
    }

    @Override
    public Mono<Void> dropTable(TableName tablename) {
        return null;
    }

    @Override
    public Mono<WriteResponse> write(WriteRequest request, WritePolicy policy) {
        validateClientReady();
        return sendSingleRequest(request);
    }

    @Override
    public Mono<ReadResponse> read(ReadRequest request, ReadPolicy policy) {
        return null;
    }

    @Override
    public Mono<DeleteResponse> delete(DeleteRequest request, DeletePolicy policy) {
        return null;
    }

    @Override
    public Flux<WriteResponse> batchWrite(BatchWriteRequest request, WritePolicy policy) {
        return null;
    }

    @Override
    public Flux<ReadResponse> batchRead(BatchReadRequest request, ReadPolicy policy) {
        return null;
    }

    @Override
    public Flux<DeleteResponse> batchDelete(BatchDeleteRequest request, DeletePolicy policy) {
        return null;
    }

    /*@Override
    public <T extends IMDBSingleResponse> Mono<T> sendSingleRequest(IMDBSingleRequest<T> request) {
        return request
                .route()
                *//*.auth()*//*
                .pack()
                .send()
                .getMono();
    }*/

    private void validateClientReady() {
        validateClientState(ClientState.READY);
    }

    @Override
    public void close() throws IOException {

    }
}
