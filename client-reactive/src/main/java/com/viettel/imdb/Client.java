package com.viettel.imdb;

import com.viettel.imdb.policy.*;
import com.viettel.imdb.type.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Closeable;

public interface Client extends Closeable {
    // todo Mono is like Future. Error will always be IMDBError, or TimeoutError
    Policy getDefaultPolicy();

    WritePolicy getDefaultWritePolicy();

    ReadPolicy getReadPolicy();

    QueryPolicy getQueryPolicy();

    BatchPolicy getBatchPolicy();

    ///
    /// Initialized functions .just() to implement in the inherited classes.
    /// If we want to implement it heract cle, change @{com.viettel.imdb.Client} to be an abstract
    ///

    Client loadConfig(String configFilePath);

    // Client connectToHost(String host);
    // Client connectToHosts(List<String> hosts);
    Client connect();

    // Client authen(AuthenInfo authenInfo);
    Client authen();

    Client start();


    ///
    /// Table APIs
    ///

    Mono<CreateTableResponse> createTable(TableName tableName);

    Mono<Void> dropTable(TableName tablename);

    ///
    /// Single record APIs
    ///
    default Mono<WriteResponse> write(WriteRequest request) {
        return write(request, WritePolicy.just());
    }

    Mono<WriteResponse> write(WriteRequest request, WritePolicy policy);

    default Mono<ReadResponse> read(ReadRequest request) {
        return read(request, ReadRequest.just());
    }

    Mono<ReadResponse> read(ReadRequest request, ReadPolicy policy);

    default Mono<DeleteResponse> delete(DeleteRequest request) {
        return delete(request, DeletePolicy.just());
    }

    Mono<DeleteResponse> delete(DeleteRequest request, DeletePolicy policy);


    ///
    /// Multi recrods APIs
    ///
    default Flux<WriteResponse> batchWrite(BatchWriteRequest request) {
        return batchWrite(request, WritePolicy.just());
    }

    Flux<WriteResponse> batchWrite(BatchWriteRequest request, WritePolicy policy);

    default Flux<ReadResponse> batchRead(BatchReadRequest request) {
        return batchRead(request, ReadRequest.just());
    }

    Flux<ReadResponse> batchRead(BatchReadRequest request, ReadPolicy policy);

    default Flux<DeleteResponse> batchDelete(BatchDeleteRequest request) {
        return batchDelete(request, DeletePolicy.just());
    }

    Flux<DeleteResponse> batchDelete(BatchDeleteRequest request, DeletePolicy policy);

    /*<T extends IMDBSingleResponse>
    Mono<T> sendSingleRequest(IMDBSingleRequest<T> request);*/

    ///
    /// Query / Scan APIs
    ///


}
