package com.viettel.imdb.type;

import com.viettel.imdb.core.Cluster;
import com.viettel.imdb.core.Node;
import net.openhft.chronicle.bytes.Bytes;

public class CreateTableRequest extends IMDBSingleRequest<CreateTableResponse> {
    private TableName tableName;

    public static CreateTableRequest just() {
        return new CreateTableRequest();
    }

    private CreateTableRequest() {
    }

    public CreateTableRequest setTableName(TableName tableName) {
        this.tableName = tableName;
        return this;
    }


    @Override
    public Node getNode(Cluster cluster) {
        return cluster.getLeader();
    }

    @Override
    public void serialize(Bytes data) {
        // do nothing here
    }

    @Override
    public CreateTableResponse deserializeValue() {
        return null;
    }

    @Override
    public Throwable deserializeException() {
        return null;
    }

    @Override
    public MethodID getMethodID() {
        return MethodID.CREATE;
    }
}
