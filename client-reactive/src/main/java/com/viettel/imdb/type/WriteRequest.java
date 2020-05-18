package com.viettel.imdb.type;

import com.viettel.imdb.core.Cluster;
import com.viettel.imdb.core.Node;
import net.openhft.chronicle.bytes.Bytes;

public class WriteRequest extends IMDBSingleRequest<WriteResponse>/* implements IMDBSerializer*/ {
    private TableAndKey key;
    private Value value;

    public WriteRequest() {
    }

    public WriteRequest(TableAndKey key, Value value) {
        this.key = key;
        this.value = value;
    }

    public TableAndKey getKey() {
        return key;
    }

    public void setKey(TableAndKey key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public Node getNode(Cluster cluster) {
        return ClientConfig.getInstance().getHashFunction().getNode(cluster, key);
        // return cluster.getLeader();
    }

    @Override
    public void serialize(Bytes data) {

    }

    @Override
    public WriteResponse deserializeValue() {
        return null;
    }

    @Override
    public Throwable deserializeException() {
        return null;
    }

    @Override
    public String toString() {
        return "WriteRequest{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
