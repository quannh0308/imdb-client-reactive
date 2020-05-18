package com.viettel.imdb.type;

import com.viettel.imdb.core.Cluster;
import com.viettel.imdb.core.Node;
import net.openhft.chronicle.bytes.Bytes;

public class ClusterRequest extends IMDBSingleRequest<ClusterResponse> {
    private String host;
    public ClusterRequest(String host) {
        this.host = host;
    }

    @Override
    public Node getNode(Cluster cluster) {
        return cluster.getNodeByHost(host);
    }

    @Override
    public void serialize(Bytes data) {

    }

    @Override
    public ClusterResponse deserializeValue() {
        return null;
    }

    @Override
    public Throwable deserializeException() {
        return null;
    }
}
