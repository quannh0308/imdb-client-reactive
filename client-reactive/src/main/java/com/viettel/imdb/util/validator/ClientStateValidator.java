package com.viettel.imdb.util.validator;

import com.viettel.imdb.type.ClientState;
import com.viettel.imdb.type.exception.ErrorCode;

public class ClientStateValidator extends Validator {
    public static void validateClientState(ClientState state) {
        verify(state != ClientState.READY, ErrorCode.CLUSTER_NOT_READY,
                "Cluster is not ready. Errors happened, or you haven't start cluster yet!");
    }

    public static void validateClientState(ClientState state, ClientState expectedState) {
        verify(state != expectedState, ErrorCode.CLUSTER_NOT_READY,
                "Cluster is not ready. You should call this function when client is in state " + expectedState + " instead of " + state);
    }
}
