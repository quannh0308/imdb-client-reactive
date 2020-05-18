package com.viettel.imdb;

import com.viettel.imdb.mock.MockClient;
import com.viettel.imdb.type.*;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;
import reactor.core.publisher.Mono;

public class Ahihi {
    public static void main(String[] args) throws InterruptedException {
        // todo: Mono/Maybe vs Quarkus Uni
        // todo: Flux vs Quarkus Mutiny
        // todo: Reactor vs NettyReactor vs Akka vs VertX vs ReactiveX

        // todo: Mono/Flux are lazy. Nothing happens until you subscribe
        // todo: ONLY ONE subscriber??? :(:(:(:(
        // todo: Assembly time vs Execution time
        // todo: mono void cause error due to that it emits NOTHING
        // todo: Mono<Void> does not emit anything - just an end of Mono stream
        // todo: With Mono<Void>, just use doOnSuccess, doOnError functions. Just little

        /*
        Also, your Mono need to be consumed. Without the code, we don't know if it is or not.

        Some example here: I added subscribe() to consume the mono. Depending on the use of your Mono, you will have to do or not the same thing.

        This print nothing:

        Mono<String> m=Mono.just("test");
        Mono<Void> v=m.then();
        v.doOnNext(x->System.out.println("OK")).subscribe();
        This print "OK":

        Mono<String> m=Mono.just("test");
        Mono<Void> v=m.then();
        v.doOnSuccess(x->System.out.println("OK")).subscribe();
         */

        // activate TinyLog
        Configurator
                .currentConfig()
                .formatPattern("{date:HH:mm:ss} [{level}][{thread}] {method}: {message}")
                .level(Level.DEBUG)
                .activate();

        // create a mock client
        Client client = MockClient
                .just()
                .loadConfig("imdb_client_reactive_config.properties")
                .connect()
                .authen()
                .start();

        // process something with it
        final String TABLE_NAME = "TABLE_NAME";
        final String KEY_PREFIX = "KEY";
        Mono<CreateTableResponse> createTableMono = client.createTable(new TableName(TABLE_NAME));
        Mono<WriteResponse> writeMono = client.write(
                new WriteRequest(
                        new TableAndKey(new TableName(TABLE_NAME), new Key(KEY_PREFIX + "0")),
                        null // todo null value
                ));
        writeMono.doOnSuccess(aVoid -> {
            Logger.error(">>>>>> WRITE successfully here - ahihi do nogc");
        })
                .doOnError(Throwable::printStackTrace)
                .subscribe();
        //Thread.sleep(5000);

        createTableMono
                .doOnSuccess(aVoid -> {
                    Logger.error(">>>>>> Create table successfully here - ahihi do nogc");
                })
                .doOnError(Throwable::printStackTrace)
                .subscribe();

        createTableMono.subscribe(value -> {
            Logger.error(">>>>> AHIHI1 " + value);
        }, Throwable::printStackTrace);

        createTableMono.subscribe(value -> {
            Logger.error(">>>>>> AHIHI2 " + value);
        }, Throwable::printStackTrace);

        createTableMono.map(aVoid -> "AHIHI")
                .map(aVoid -> {
                    Logger.error(">>> After creating table - using map");
                    return aVoid;
                })
                .subscribe(value -> {
                    Logger.error("AHIHI ------------------------------ " + value);
                }, Throwable::printStackTrace);


        Logger.info("Process printing this line after create a mono to create table");
//        createTableMono.block();
//        otherMono.block();
        Logger.info("Create table {} successfully", TABLE_NAME);
        try {
            Thread.sleep(5000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /*Client client = Client
                .just()
                .connectToHost("172.16.31.54:12345")
                .authen(Auth.fromUsrAndPass("usr", "pass"))
                .start();
        // insert
        {
            class InsertRequest extends Mono;
            InsertRequest insertRequest = InsertRequest
                    .just()
                            .prepareRequest("tableName", "key", "data")
                            .prepareToken()
                            .route()
                            .execute();
            Mono<Void> insertResult = client.insert(insertRequest);
        }*/
    }
}
