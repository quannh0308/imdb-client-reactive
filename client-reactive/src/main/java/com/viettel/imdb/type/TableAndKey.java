package com.viettel.imdb.type;

public class TableAndKey {
    private final TableName tableName;
    private final Key key;

    public TableAndKey(TableName tableName, Key key) {
        this.tableName = tableName;
        this.key = key;
    }

    public TableName getTableName() {
        return tableName;
    }

    public Key getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "TableAndKey{" +
                "tableName=" + tableName +
                ", key=" + key +
                '}';
    }
}
