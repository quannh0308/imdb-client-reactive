package com.viettel.imdb.core;

import com.viettel.imdb.type.IMDBSingleRequest;
import com.viettel.imdb.type.IMDBSingleResponse;
import reactor.core.publisher.Mono;

public class Transporter {
    public static <T extends IMDBSingleResponse> Mono<T> sendSingleRequest(IMDBSingleRequest<T> request) {
        // todo validate Client Cluster state
        return request
                .route()
                /*.auth()*/
                .pack()
                .send()
                .getMono();
    }
}
